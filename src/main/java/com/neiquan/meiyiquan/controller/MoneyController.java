package com.neiquan.meiyiquan.controller;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.neiquan.meiyiquan.code.Code;
import com.neiquan.meiyiquan.dao.support.CouponDaoSupport;
import com.neiquan.meiyiquan.service.MoneyService;
import com.neiquan.meiyiquan.service.ObjectService;
import com.neiquan.meiyiquan.util.Statics;
import com.neiquan.meiyiquan.util.StringUtil;
import com.qc.util.Condition.Cs;
import com.qc.util.tag.QcPageType;
import com.sun.org.apache.xpath.internal.operations.Mod;


/**
 * 作者：温尉棨
 * 创建日期：2017年2月16日
 * 类说明：
 */
@Controller
@RequestMapping(value="money")
public class MoneyController {
	
	@Autowired
	private MoneyService ms;
	
	@Resource(name="qcpagetype")
	private QcPageType pageType;
	
	/**
	 * 第三方支付列表
	 * @param beginTime
	 * @param endTime
	 * @param tree
	 * @param orderNum
	 * @return
	 */
	@RequestMapping(value="moneyList")
	public ModelAndView moneyList(String beginTime,String endTime,String tree,String orderNum){
		beginTime=StringUtil.isNullOrBlank(beginTime)?"":beginTime;
		endTime=StringUtil.isNullOrBlank(endTime)?"":endTime;
		tree=StringUtil.isNullOrBlank(tree)?"":tree;
		orderNum=StringUtil.isNullOrBlank(orderNum)?"":orderNum;
		ModelAndView mav=new ModelAndView();
		String zongshu="1";
		//1ping++支付宝,2ping++微信,4IOS内购
		String pay_type="";
		//获取支付宝map
		Map<String,Object> zmap= ms.moneyInfo(beginTime, endTime, tree, orderNum, "1", "1");
		String zcon= zmap.get("con").toString();
		String zsu=zmap.get("su").toString();
		//获取微信map
		Map<String,Object> wmap= ms.moneyInfo(beginTime, endTime, tree, orderNum, "1", "2");
		String wcon=wmap.get("con").toString();
		String wsu=wmap.get("su").toString();
		//获取iso内购map
		Map<String,Object> imap= ms.moneyInfo(beginTime, endTime, tree, orderNum, "1", "4");
		String icon=imap.get("con").toString();
		String isu=imap.get("su").toString();
		//获取总数map
		Map<String,Object> zsmap= ms.moneyInfo(beginTime, endTime, tree, orderNum, "", "");
		String zscon=zsmap.get("con").toString();
		String zssu=zsmap.get("su").toString();
		
		Map<String,Object> map=new HashMap();
		map.put("zcon", zcon);
		map.put("zsu", zsu);
		map.put("wcon", wcon);
		map.put("wsu", wsu);
		map.put("icon", icon);
		map.put("isu", isu);
		map.put("zscon", zscon);
		map.put("zssu", zssu);
		
		mav.addAllObjects(map);
		mav.addObject("beginTime",beginTime);
		mav.addObject("endTime",endTime);
		mav.addObject("tree",tree);
		mav.addObject("orderNum",orderNum);
		
		mav.setViewName("money/money/money_list");
		return mav;
	}
	/**
	 * 订单列表
	 * @param text
	 * @return
	 */
	@RequestMapping(value="orderList")
	public  ModelAndView orderList(String textt,String page,String size,String count ){
		page=StringUtil.isNullOrBlank(page)?"1":page;
		size=StringUtil.isNullOrBlank(size)?"5":size;
		ModelAndView mav =new ModelAndView();
		mav.addObject(ms.orderList(textt, page, size));
		mav.addObject("page", page);
		mav.addObject("size", size);
		mav.addObject("count", count);
		mav.setViewName("money/money/order_list");
		return mav;
	}
	/**
	 * 收益列表
	 * @param beginTime
	 * @param endTime
	 * @param type
	 * @param dateType
	 * @param page
	 * @param size
	 * @param count
	 * @return
	 */
	@RequestMapping(value="gainsList")
	public ModelAndView  gainsList(String beginTime,String endTime,String type,String dateType,String page,String size,String count){
		page=StringUtil.isNullOrBlank(page)?"1":page;
		size=StringUtil.isNullOrBlank(size)?"5":size;
		beginTime=StringUtil.isNullOrBlank(beginTime)?"":beginTime;
		endTime=StringUtil.isNullOrBlank(endTime)?"":endTime;
		type=StringUtil.isNullOrBlank(type)?"":type;
		dateType=StringUtil.isNullOrBlank(dateType)?"":dateType;
		ModelAndView mav =new ModelAndView();
		if(beginTime.equals("")&&endTime.equals("")&&dateType.equals("")&&type.equals("")){
			mav.setViewName("money/money/gains_list_s");
		}else{
			mav.addObject(ms.gainsList(beginTime, endTime, type, dateType, page, size));
			mav.addObject("beginTime", beginTime);
			mav.addObject("endTime", endTime);
			mav.addObject("type", type);
			mav.addObject("dateType", dateType);
			mav.addObject("page", page);
			mav.addObject("size", size);
			mav.addObject("count", count);
			mav.setViewName("money/money/gains_list");
		}
		
		return mav;
	}
	
	/**
	 * 从左侧导航栏进入优惠券申请列表
	 * @return
	 */
	@RequestMapping(value="shenqingList")
	public ModelAndView shenqingList(){
		ModelAndView mav = couponSupport(1, Statics.EACHROWS_DEFAULT, null, null, null, null, null, null, null, null, null);
		mav.setViewName("coupon/verify/coupon_verify_list");
		return mav;
	}
	
	/**
	 * 通过点击搜索或者分页获得申请优惠券列表
	 * @param page
	 * @param size
	 * @param applyLoginName
	 * @param aproveLoginName
	 * @param couponName
	 * @param applyTimeBegin
	 * @param applyTimeEnd
	 * @param verifyTimeBegin
	 * @param verifyTimeEnd
	 * @param type
	 * @param status
	 * @return
	 */
	@RequestMapping(value="getqcList")
	public ModelAndView getqcList(int page,int size,String applyLoginName,String aproveLoginName,
			String couponName,String applyTimeBegin,String applyTimeEnd,String verifyTimeBegin,
			String verifyTimeEnd,String type,String status){
		ModelAndView mav = couponSupport(page, size, applyLoginName, aproveLoginName, couponName, 
				applyTimeBegin, applyTimeEnd, verifyTimeBegin, verifyTimeEnd, type, status);
		mav.setViewName("coupon/verify/coupon_verify_list");
		return mav;
	}
	
	/**
	 * 获取审核列表的核心方法
	 * @param page
	 * @param size
	 * @param applyLoginName
	 * @param aproveLoginName
	 * @param couponName
	 * @param applyTimeBegin
	 * @param applyTimeEnd
	 * @param verifyTimeBegin
	 * @param verifyTimeEnd
	 * @param type
	 * @param status
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private ModelAndView couponSupport(int page,int size,String applyLoginName,String aproveLoginName,
			String couponName,String applyTimeBegin,String applyTimeEnd,String verifyTimeBegin,
			String verifyTimeEnd,String type,String status){
		// 临时用的转码
		try {
			applyLoginName = applyLoginName==null?null:new String(applyLoginName.getBytes("ISO-8859-1"),"UTF-8");
			aproveLoginName = aproveLoginName==null?null:new String(aproveLoginName.getBytes("ISO-8859-1"),"UTF-8");
			couponName = couponName==null?null:new String(couponName.getBytes("ISO-8859-1"),"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Cs cs = CouponDaoSupport.getApplyCs(applyLoginName, aproveLoginName, couponName, applyTimeBegin, 
				applyTimeEnd, verifyTimeBegin, verifyTimeEnd, type, status);
		Code code = ms.getApplyList(cs, page, size);
		ModelAndView mav = new ModelAndView();
		mav.addAllObjects(code.getMapData());
		mav.addObject("pageType", pageType);
		mav.addObject("applyLoginName", applyLoginName);
		mav.addObject("aproveLoginName", aproveLoginName);
		mav.addObject("couponName", couponName);
		mav.addObject("applyTimeBegin", applyTimeBegin);
		mav.addObject("applyTimeEnd", applyTimeEnd);
		mav.addObject("verifyTimeBegin", verifyTimeBegin);
		mav.addObject("verifyTimeEnd", verifyTimeEnd);
		mav.addObject("type", type);
		mav.addObject("status", status);
		return mav;
	}
	
	/**
	 * 审核操作
	 * @param id
	 * @param status
	 * @return
	 * @throws Exception 
	 */
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping(value="updateShengqin",method=RequestMethod.POST)
	public Code updateShengqin(String id,int status,HttpSession session) throws Exception{
		if(id==null||id.trim().equals("")||(status!=Statics.COUPON_WILLSEND_TYPE_NO&&status!=Statics.COUPON_WILLSEND_TYPE_YES))
			return Code.init(-1, "参数不合法！");
		Map<String, Object> admin = (Map<String, Object>) session.getAttribute("user");
		String adminId = admin.get("id").toString();
		String adminName = admin.get("loginname").toString();
		Code code = ms.shenhe(id, status, adminId, adminName);
		return code;
	}
}
