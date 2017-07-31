package com.neiquan.meiyiquan.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.neiquan.meiyiquan.code.Code;
import com.neiquan.meiyiquan.dao.ObjectDao;
import com.neiquan.meiyiquan.dao.support.CouponDaoSupport;
import com.neiquan.meiyiquan.pojo.Coupon;
import com.neiquan.meiyiquan.service.BannerService;
import com.neiquan.meiyiquan.service.CouponService;
import com.neiquan.meiyiquan.service.MessageService;
import com.neiquan.meiyiquan.service.ObjectService;
import com.neiquan.meiyiquan.util.Constant;
import com.neiquan.meiyiquan.util.Statics;
import com.qc.util.Condition.Cs;
import com.qc.util.tag.QcPageType;

/**
 * 作者：齐潮
 * 创建日期：2017年2月18日
 * 类说明：处理有关优惠券的请求
 */
@Controller
@RequestMapping(value="coupon")
public class CouponController {

	@Resource(name="qcpagetype")
	private QcPageType pageType;
	
	@Autowired
	private ObjectService os;
	
	@Autowired
	private ObjectDao od;
	
	@Autowired
	private MessageService ms;
	
	@Autowired
	private BannerService bs;
	
	@Autowired
	private CouponService ccs;
	
	/**
	 * 从左侧导航栏获得优惠券列表
	 * @return
	 */
	@RequestMapping(value="getCoupons")
	public ModelAndView getCoupons(){
		ModelAndView mav = couponsToAll(1, Statics.EACHROWS_DEFAULT, null,null, null, null, null, null, null, null);
		mav.setViewName("coupon/coupons/coupon_list");
		return mav;
	}
	
	/**
	 * 预添加优惠券
	 * @return
	 */
	@RequestMapping(value="toadd")
	public ModelAndView toAdd(){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("coupon/coupons/coupon_add");
		return mav;
	}
	
	/**
	 * 添加一个优惠券
	 * @param name
	 * @param url
	 * @param type
	 * @param price
	 * @param remark
	 * @param expireDay
	 * @return
	 */
	@RequestMapping(value="addCoupon")
	public ModelAndView addCoupon(String name,String url,Integer type,Double price,String remark,Integer expireDay){
		Code vali = validate(name, url, type, price, remark, expireDay);
		ModelAndView mav = new ModelAndView();
		if(vali.getCode()!=0){
			mav.addObject("message", vali.getMessage());
			mav.setViewName("coupon/coupons/coupon_add");
			return mav;
		}
		Coupon coupon = new Coupon();
		coupon.setAdmin_id("");
		coupon.setBackground_pic_url(url);
		coupon.setCoupon_type(type);
		coupon.setDenomination(price);
		coupon.setExpire_time(expireDay);
		coupon.setExpire_type(Statics.COUPON_EXPIRE_TYPE_DAY);
		coupon.setIntroduction(remark);
		coupon.setLatest_update_time(System.currentTimeMillis());
		coupon.setName(name);
		coupon.setNumber(UUID.randomUUID().toString().replace("-", ""));
		System.out.println(UUID.randomUUID().toString().replace("-", ""));
		coupon.setStatus(Statics.YES_INT);
		
		coupon.setExpire_count(0);
		coupon.setCoupon_provide_type(Statics.COUPON_PROVIDE_TYPE_ADMIN);
		
		Code code = ccs.saveCoupon(coupon);
		if(code.getCode()!=0){
			mav.setViewName("coupon/coupons/coupon_add");
		}else{
			mav = getCoupons();
		}
		mav.addObject("message", code.getMessage());
		return mav;
	}
	
	/**
	 * 获得一个优惠券详情，用于查看优惠券详情或者欲编辑优惠券。
	 * type是1表示跳转到详情页面，是2表示跳转到欲编辑界面
	 * @param id
	 * @return
	 */
	@RequestMapping(value="couponDetail")
	public ModelAndView couponDetail(String id,int type){
		ModelAndView mav = new ModelAndView();
		if(id==null||id.equals("")||(type!=1&&type!=2)){
			mav = getCoupons();
			mav.addObject("message", "非法操作！");
			return mav;
		}
		Code code = ccs.getCoupon(id);
		mav.addObject("coupon", code.getData());
		switch(type){
		case 1:
			mav.setViewName("coupon/coupons/coupon_look");
			break;
		case 2:
			mav.setViewName("coupon/coupons/coupon_edit");
			break;
		}
		return mav;
	}
	
	/**
	 * 修改优惠券信息并且保存
	 * @param id
	 * @param name
	 * @param url
	 * @param type
	 * @param price
	 * @param remark
	 * @param expireDay
	 * @return
	 */
	@RequestMapping(value="editCoupon")
	public ModelAndView editCoupon(String id,String name,String url,Integer type,Double price,String remark,Integer expireDay){
		Map<String,Object> settings = new HashMap<String,Object>();
		settings.put("name", name);
		settings.put("background_pic_url", url);
		settings.put("coupon_type", type);
		settings.put("denomination", price);
		settings.put("introduction", remark);
		settings.put("expire_time", expireDay);
		settings.put("latest_update_time", System.currentTimeMillis());
		ModelAndView mav = new ModelAndView();
		if(id==null||id.equals("")){
			mav.addObject("message", "非法操作！");
			settings.put("id", id);
			mav.addObject("coupon", settings);
			mav.setViewName("coupon/coupons/coupon_edit");
			return mav;
		}
		Code vali = validate(name, url, type, price, remark, expireDay);
		if(vali.getCode()!=0){
			mav.addObject("message", vali.getMessage());
			settings.put("id", id);
			mav.addObject("coupon", settings);
			mav.setViewName("coupon/coupons/coupon_edit");
			return mav;
		}
		Code code = ccs.updateCoupon(id, settings);
		mav = getCoupons();
		mav.addObject("message", code.getMessage());
		return mav;
	}
	
	private Code validate(String name,String url,Integer type,Double price,String remark,Integer expireDay){
		if(type==null||price==null||expireDay==null)
			return Code.init(-1, "优惠券类型、面值、有效天数不能为空！");
		if(name==null||name.trim().equals(""))
			return Code.init(-1, "优惠券名称不能为空！");
		if(url==null||url.trim().equals(""))
			return Code.init(-1, "请上传优惠券图片！");
		if(type!=Statics.COUPON_TYPE_DISCOUNT&&type!=Statics.COUPON_TYPE_VOUCHER)
			return Code.init(-1, "请正确选择优惠券类型！");
		if(price<=0)
			return Code.init(-1, "优惠券面额必须大于0！");
		if(type==Statics.COUPON_TYPE_DISCOUNT&&price>=1)
			return Code.init(-1, "打折券的面额必须是介于0-1之间的数，不包括0和1！");
		if(expireDay<0)
			return Code.init(-1, "优惠券有效期必须大于0！");
		return Code.init(0, null);
	}
	
	/**
	 * 上传优惠券图片
	 * @param file
	 * @return
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(value="upImage",method=RequestMethod.POST)
	public Code upImage(@RequestParam(value = "file") MultipartFile file) throws IOException{
		String url = bs.updateHead(file);
		if(url==null)
			return Code.init(-1, "图片上传失败，请稍后再试！");
		return Code.init(0, "图片上传成功！", url);
	}
	
	/**
	 * 跳转至发送界面
	 * @param id
	 * @return
	 */
	@RequestMapping(value="willSend")
	public ModelAndView willSend(String id){
		ModelAndView mav = null;
		Coupon coupon = od.getObjById(Coupon.class, id);
		if(coupon==null){
			mav = getCoupons();
			mav.addObject("message", "该优惠券已经不存在！");
			return mav;
		}
		if(coupon.getStatus()!=Constant.YES_INT){
			mav = getCoupons();
			mav.addObject("message", "该优惠券已经被禁用！");
			return mav;
		}
		Code code= ms.job_list(String.valueOf(Statics.JOB_TYPE_AGE));
		Code code1= ms.job_list(String.valueOf(Statics.JOB_TYPE_JOB));
		mav = new ModelAndView();
		mav.addObject("ageCode", code);
		mav.addObject("jobCode", code1);
		mav.addObject("id", id);
		mav.setViewName("coupon/coupons/coupon_send");
		return mav;
	}
	
	/**
	 * 发送优惠券
	 * @param id
	 * @param ageid
	 * @param zodicid
	 * @param sexid
	 * @param jobid
	 * @param tree
	 * @param type
	 * @return
	 */
	@RequestMapping(value="send")
	public ModelAndView send(String id,String phone,String ageid,String zodicid,
			String sexid,String jobid,String tree ,String type,HttpSession session){
		ModelAndView mav = null;
		Coupon coupon = od.getObjById(Coupon.class, id);
		if(coupon==null){
			mav = getCoupons();
			mav.addObject("message", "该优惠券已经不存在！");
			return mav;
		}
		if(coupon.getStatus()!=Constant.YES_INT){
			mav = getCoupons();
			mav.addObject("message", "该优惠券已经被禁用！");
			return mav;
		}
		Cs cs = CouponDaoSupport.getSendUserCs(phone, ageid, zodicid, sexid, jobid, tree, type);
		String sql = CouponDaoSupport.getSendUserCount(cs.getConditions());
		int count = od.getObjCountBySql(sql, cs.getParams());
		if(count==0){
			mav = getCoupons();
			mav.addObject("message", "没有查询到用户！");
			return mav;
		}
		sql = CouponDaoSupport.getSentUsers(cs.getConditions());
		List<Map<String, Object>> users = od.getListBySql(sql, cs.getParams(), null, null);
		Map<String, Object> admin = (Map<String, Object>) session.getAttribute("user");
		String adminId = admin.get("id").toString();
		Code code = ccs.sendCoupons(users, id, adminId);
		mav = getCoupons();
		mav.addObject("message", code.getMessage());
		return mav;
	}
	
	/**
	 * 查询人数
	 * @param id
	 * @param phone
	 * @param ageid
	 * @param zodicid
	 * @param sexid
	 * @param jobid
	 * @param tree
	 * @param type 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="select")
	public Code select(String phone,String ageid,String zodicid,
			String sexid,String jobid,String tree ,String type){
		Cs cs = CouponDaoSupport.getSendUserCs(phone, ageid, zodicid, sexid, jobid, tree, type);
		String sql = CouponDaoSupport.getSendUserCount(cs.getConditions());
		int count = od.getObjCountBySql(sql, cs.getParams());
		return Code.init(0, "", count);
	}
	
	
	
	/**
	 * 点击搜索或者刷新或者按页码来查询
	 * @param page
	 * @param size
	 * @param type
	 * @param lastUpBegin
	 * @param lastUpEnd
	 * @param sentCount
	 * @param useCount
	 * @param noUseExpiredCount
	 * @param noUseNoExpiredCount
	 * @return
	 */
	@RequestMapping(value="getCouponsList")
	public ModelAndView getCouponsList(int page,int size,Integer type,Integer status,String lastUpBegin,String lastUpEnd,
			String sentCount,String useCount,String noUseExpiredCount,String noUseNoExpiredCount){
		ModelAndView mav = couponsToAll(page, size, type,status, lastUpBegin, lastUpEnd, sentCount, useCount, noUseExpiredCount, noUseNoExpiredCount);
		mav.setViewName("coupon/coupons/coupon_list");
		return mav;
	}
	
	/**
	 * 启用，禁用优惠券
	 * @param id
	 * @param status
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="upStatus",method=RequestMethod.POST)
	public Code upStatus(String id,int status){
		String updateTime = os.updateOneProReturnDate(Coupon.class, id, "status", status, "latest_update_time");
		if(updateTime==null)
			return Code.init(-1, "更新失败！请刷新页面重试！");
		return Code.init(1, "修改成功！", updateTime);
	}
	
	/**
	 * 删除优惠券
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="deleteCoupon",method=RequestMethod.POST)
	public Code delete(String id){
		int result = os.delete(Coupon.class, id);
		if(result<1)
			return Code.init(-1, "删除失败！请刷新页面重试！");
		return Code.init(1, "删除成功！");
	}

	/**
	 * 对获得优惠券列表的统一处理条件查询
	 * @param type
	 * @param createBegin
	 * @param createEnd
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private ModelAndView couponsToAll(int page,int size,Integer type,Integer status,String lastUpBegin,String lastUpEnd,
			String sentCount,String useCount,String noUseExpiredCount,String noUseNoExpiredCount){
		Cs cs = CouponDaoSupport.getCouponCs(type,status, lastUpBegin, lastUpEnd, sentCount, useCount, noUseExpiredCount, noUseNoExpiredCount);
		Code code = ccs.getCoupons(cs, page, size);
		ModelAndView mav = new ModelAndView();
		mav.addAllObjects(code.getMapData());
		mav.addObject("pageType", pageType);
		mav.addObject("type", type);
		mav.addObject("status", status);
		mav.addObject("lastUpBegin", lastUpBegin);
		mav.addObject("lastUpEnd", lastUpEnd);
		mav.addObject("sentCount", sentCount);
		mav.addObject("useCount", useCount);
		mav.addObject("noUseExpiredCount", noUseExpiredCount);
		mav.addObject("noUseNoExpiredCount", noUseNoExpiredCount);
		return mav;
	}
	
	/**
	 * 获取优惠券获得的游戏规则列表，暂时不做，业务复杂
	 * @return
	 */
	@RequestMapping(value="getCouponTasks")
	public ModelAndView getCouponTasks(){
		Code code = ccs.getTasks();
		ModelAndView mav = new ModelAndView();
		mav.addObject("result", code.getData());
		mav.setViewName("coupon/tasks/coupon_task_list");
		return mav;
	}
	

	
	/**
	 * 对获得优惠券任务规则列表的统一处理条件查询，暂时不做，业务复杂
	 * @return
	 */
	private ModelAndView tasksToAll(){
		return null;
	}
}
