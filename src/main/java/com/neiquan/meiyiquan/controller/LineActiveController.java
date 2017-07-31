package com.neiquan.meiyiquan.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.neiquan.meiyiquan.code.Code;
import com.neiquan.meiyiquan.pojo.Activity;
import com.neiquan.meiyiquan.pojo.ActivityUser;
import com.neiquan.meiyiquan.pojo.Comment;
import com.neiquan.meiyiquan.pojo.Course;
import com.neiquan.meiyiquan.pojo.User;
import com.neiquan.meiyiquan.pojo.UserVideoRequest;
import com.neiquan.meiyiquan.service.ActiveService;
import com.neiquan.meiyiquan.service.AskCourseService;
import com.neiquan.meiyiquan.service.ObjectService;
import com.neiquan.meiyiquan.util.ExtraSpringHibernateTemplate;
import com.neiquan.meiyiquan.util.Statics;
import com.neiquan.meiyiquan.util.StringUtil;

/**
 * 作者：温尉棨
 * 创建日期：2017年1月13日
 * 类说明：
 */
@Controller
@RequestMapping(value="/active")
public class LineActiveController {

	@Autowired
	private ActiveService as;
	
	@Autowired
	private ObjectService os;
	
	
	@Autowired
	private ExtraSpringHibernateTemplate esht;
	/**
	 * 线下活动列表
	 * @param page
	 * @param size
	 * @param count
	 * @param kaystatus
	 * @param kaytxt
	 * @param createBegin
	 * @param createEnd
	 * @param click_count
	 * @param fee
	 * @param forward
	 * @param collection
	 * @param comment
	 * @param status
	 * @return
	 */
	@RequestMapping(value="activelist")
	public ModelAndView activelist(String page,String size,String count,String kaystatus,String kaytxt,
			String createBegin,String createEnd,String orderBy,String collation,String status,String tree){
		page=StringUtil.isNullOrBlank(page)?"1":page;
		size=StringUtil.isNullOrBlank(size)?"5":size;
		kaystatus=StringUtil.isNullOrBlank(kaystatus)?"":kaystatus;
		kaytxt=StringUtil.isNullOrBlank(kaytxt)?"":kaytxt;
		createBegin=StringUtil.isNullOrBlank(createBegin)?"":createBegin;
		createEnd=StringUtil.isNullOrBlank(createEnd)?"":createEnd;
		collation=StringUtil.isNullOrBlank(tree)?"":tree;
		status=StringUtil.isNullOrBlank(status)?"":status;
		orderBy=StringUtil.isNullOrBlank(orderBy)?" click_count":orderBy;//默认点击量排序
		collation=StringUtil.isNullOrBlank(collation)?" DESC":collation;//默认倒序
		ModelAndView mav = new ModelAndView();
		mav.setViewName("line/line/lineactive_list");
		mav.addObject(as.getList(page, size, count, kaystatus, kaytxt, createBegin, createEnd, orderBy, collation, status,tree));
		mav.addObject("page", page);
		mav.addObject("size", size);
		mav.addObject("count", count);
		mav.addObject("kaystatus", kaystatus);
		mav.addObject("kaytxt", kaytxt);
		mav.addObject("createBegin", createBegin);
		mav.addObject("createEnd", createEnd);
		mav.addObject("orderBy", orderBy);
		mav.addObject("collation", collation);
		mav.addObject("tree", tree);
		mav.addObject("status", status);
		
		return mav;
	}
	
	/**
	 * 根据id去删除活动
	 * @param id
	 * @return
	 */
	@RequestMapping(value="deleteActivity")
	public ModelAndView deleteActivityById(String id){
		ModelAndView mav = null;
		if(StringUtil.isNullOrBlank(id)){
			mav = activelist("1", "5", null, null, null, null, null, null, null, null, null);
			mav.addObject("message", "参数缺失！");
			return mav;
		}
		int result = as.deleteById(id);
		mav = activelist("1", "5", null, null, null, null, null, null, null, null, null);
		if(result!=0){
			mav.addObject("message", "删除失败！");
			return mav;
		}
		mav.addObject("message", "删除成功！");
		return mav;
	}
	
	/**
	 * 活动收益列表
	 * @param page
	 * @param size
	 * @param count
	 * @param kaystatus
	 * @param kaytxt
	 * @param createBegin
	 * @param createEnd
	 * @param orderBy
	 * @param collation
	 * @param status
	 * @return
	 */
	@RequestMapping(value="incomeList")
	public  ModelAndView incomeList(String page,String size,String count,String kaystatus,String kaytxt,
			String createBegin,String createEnd,String tree ){
		page=StringUtil.isNullOrBlank(page)?"1":page;
		size=StringUtil.isNullOrBlank(size)?"5":size;
		kaystatus=StringUtil.isNullOrBlank(kaystatus)?"":kaystatus;
		kaytxt=StringUtil.isNullOrBlank(kaytxt)?"":kaytxt;
		createBegin=StringUtil.isNullOrBlank(createBegin)?"":createBegin;
		createEnd=StringUtil.isNullOrBlank(createEnd)?"":createEnd;
		ModelAndView mav = new ModelAndView();
		
		mav.addObject(as.incomeList(page, size, count, kaystatus, kaytxt, createBegin, createEnd, "", "", tree));
		mav.addObject("page", page);
		mav.addObject("size", size);
		mav.addObject("count", count);
		mav.addObject("kaystatus", kaystatus);
		mav.addObject("kaytxt", kaytxt);
		mav.addObject("createBegin", createBegin);
		mav.addObject("createEnd", createEnd);
		mav.addObject("tree", tree);
		mav.setViewName("line/lineincome/income_list");
		return mav;
	}
	

	
	/**
	 * 跳转添加页面
	 * @return
	 */
	@RequestMapping(value="toadd")
	public String toadd(){
		
		return "line/line/lineactive_add";
	}
	/**
	 * 添加活动
	 * @param tital
	 * @param enroll_begin_time
	 * @param enroll_end_time
	 * @param prepare_start_time
	 * @param prepare_end_time
	 * @param activity_start_time
	 * @param activity_end_time
	 * @param show_type
	 * @param show_pic_url
	 * @param remark
	 * @param cost
	 * @param most_man
	 * @param appearance
	 * @param canUseCoupon
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="saveActive")
	public Code  saveActive(String tital,String enroll_begin_time,
			String enroll_end_time ,String prepare_start_time,String prepare_end_time,
			String activity_start_time,String activity_end_time,
			HttpServletRequest request ,String show_type,String show_pic_url,String remark,String cost,
			String most_man,String canUseCoupon,
			String fileData,String price,String address,String organiser){
		
		if(StringUtil.isNullOrBlank(tital)) return Code.init(1, "标题必填", null);
		if(StringUtil.isNullOrBlank(enroll_begin_time)) return Code.init(1, "报名开始时间必填", null);
		if(StringUtil.isNullOrBlank(enroll_end_time)) return Code.init(1, "报名结束时间必填", null);
		if(StringUtil.isNullOrBlank(prepare_start_time)) return Code.init(1, "活动准备开始时间必填", null);
		if(StringUtil.isNullOrBlank(prepare_end_time)) return Code.init(1, "活动准备结束时间必填", null);
		if(StringUtil.isNullOrBlank(activity_start_time)) return Code.init(1, "活动开始时间必填", null);
		if(StringUtil.isNullOrBlank(activity_end_time)) return Code.init(1, "活动结束时间必填", null);
		if(StringUtil.isNullOrBlank(remark)) return Code.init(1, "活动详情必填", null);
		if(StringUtil.isNullOrBlank(cost)) return Code.init(1, "运营成本必填", null);
		if(StringUtil.isNullOrBlank(most_man)) return Code.init(1, "最大人数必填", null);
		if(StringUtil.isNullOrBlank(price)) return Code.init(1, "活动价格必填", null);
		if(StringUtil.isNullOrBlank(address)) return Code.init(1, "活动地址必填", null);
		if(StringUtil.isNullOrBlank(organiser)) return Code.init(1, "主办方必填", null);
		if(StringUtil.isNullOrBlank(show_pic_url)) return Code.init(1, "请确定图片是否上传完毕!", null);
		if(show_type.equals("1")){
			if(StringUtil.isNullOrBlank(fileData)) return Code.init(1, "请确定视频是否上传完毕!", null);
		}
		
		
		
		
		
		
		ModelAndView mav=new ModelAndView();
		
		if(price==null||price.trim().equals("")||Double.parseDouble(price)<=0){
			return Code.init(1, "活动价格不合法", null);
		}
		
		tital=StringUtil.isNullOrBlank(tital)?"":tital;
		enroll_begin_time=StringUtil.isNullOrBlank(enroll_begin_time)?"0":enroll_begin_time;
		enroll_end_time=StringUtil.isNullOrBlank(enroll_end_time)?"0":enroll_end_time;
		prepare_start_time=StringUtil.isNullOrBlank(prepare_start_time)?"0":prepare_start_time;
		prepare_end_time=StringUtil.isNullOrBlank(prepare_end_time)?"0":prepare_end_time;
		activity_start_time=StringUtil.isNullOrBlank(activity_start_time)?"0":activity_start_time;
		activity_end_time=StringUtil.isNullOrBlank(activity_end_time)?"0":activity_end_time;
		show_type=StringUtil.isNullOrBlank(show_type)?"":show_type;
		show_pic_url=StringUtil.isNullOrBlank(show_pic_url)?"":show_pic_url;
		remark=StringUtil.isNullOrBlank(remark)?"":remark;
		cost=StringUtil.isNullOrBlank(cost)?"":cost;
		most_man=StringUtil.isNullOrBlank(most_man)?"":most_man;
		String appearance="";
		canUseCoupon=StringUtil.isNullOrBlank(canUseCoupon)?"":canUseCoupon;
		fileData=StringUtil.isNullOrBlank(fileData)?"":fileData;
		
		/*if(show_pic_url.contains("blob")){
			try {
				show_pic_url=as.updateHead(file0);
			} catch (IOException e) {
				 return Code.init(1, "图片大小有误,上传oss失败!", null);
			}
		}*/
		
		String result = as.saveActive(tital, enroll_begin_time, enroll_end_time,
							prepare_start_time, prepare_end_time, activity_start_time, 
							activity_end_time,show_type, show_pic_url, remark, 
							cost, most_man, appearance, canUseCoupon,fileData,price,address,organiser);
		
		if(result.equals("1")){
			/*mav.setViewName("line/line/lineactive_add");
			mav.addObject("message", "活动封面类型不合法！");*/
			 return Code.init(1, "活动封面类型不合法！", null);
		}
		
		if(result.equals("2")){
			/*mav.setViewName("line/line/lineactive_add");
			mav.addObject("message", "报名开始时间不能大于结束时间！");*/
			 return Code.init(1, "报名开始时间不能大于结束时间！", null);
		}
		
		if(result.equals("3")){
			/*mav.setViewName("line/line/lineactive_add");
			mav.addObject("message", "准备开始时间不能大于结束时间！");*/
			 return Code.init(1, "准备开始时间不能大于结束时间！", null);
		}
		
		if(result.equals("4")){
			/*mav.setViewName("line/line/lineactive_add");
			mav.addObject("message", "活动开始时间不能大于结束时间！");*/
			 return Code.init(1, "活动开始时间不能大于结束时间！", null);
		}
		
		if(result.equals("5")){
			/*mav.setViewName("line/line/lineactive_add");
			mav.addObject("message", "报名结束时间不能大于准备开始时间！");*/
			 return Code.init(1, "报名结束时间不能大于准备开始时间！", null);
		}
		
		if(result.equals("6")){
			/*mav.setViewName("line/line/lineactive_add");
			mav.addObject("message", "准备结束时间不能大于活动开始时间！");*/
			 return Code.init(1, "准备结束时间不能大于活动开始时间！", null);
		}
		
		//mav= new ModelAndView("redirect:/active/activelist.do");
		return Code.init(0, "添加成功", result);
		
		
	}
	/**
	 * 详情页面
	 * @param id
	 * @return
	 */
	@RequestMapping(value="infoActive")
	public ModelAndView  infoActive(String id){
		ModelAndView mav = new ModelAndView();
		Map<String,Object> list=as.infoActive(id);
		/*Map<String,Object> map=as.infoActive(id);
		mav.addObject("map", map);*/
		mav.addObject("list", list);
		mav.setViewName("line/line/lineactive_eduit");
		return mav;
	}
	
	/**
	 * 修改活动
	 * @param id
	 * @param tital
	 * @param enroll_begin_time
	 * @param enroll_end_time
	 * @param prepare_start_time
	 * @param prepare_end_time
	 * @param activity_start_time
	 * @param activity_end_time
	 * @param file0
	 * @param request
	 * @param show_type
	 * @param show_pic_url
	 * @param remark
	 * @param cost
	 * @param most_man
	 * @param appearance
	 * @param canUseCoupon
	 * @return
	 */
	@RequestMapping(value="updateActive")
	public ModelAndView  updateActive(String id,String tital,String enroll_begin_time,
			String enroll_end_time ,String prepare_start_time,String prepare_end_time,
			String activity_start_time,String activity_end_time,
			HttpServletRequest request ,String show_type,String show_pic_url,String remark,String cost,
			String most_man,String appearance,String canUseCoupon,@RequestParam MultipartFile file0
			,@RequestParam MultipartFile file1,String show_video_picurl,String fileData,String price,String address
			,String organiser){
		appearance=StringUtil.isNullOrBlank(appearance)?"0":appearance;
		ModelAndView mav=new ModelAndView();
		
		if(price==null||price.trim().equals("")||Double.parseDouble(price)<=0){
			mav = infoActive(id);
			mav.addObject("message", "活动价格不合法！");
			return mav;
		}
		if(show_type.equals("0")){
			if(show_pic_url.contains("blob")){
				try {
					show_pic_url=as.updateHead(file0);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}else{
			if(show_video_picurl.contains("blob")){
				try {
					show_pic_url=as.updateHead(file1);
				} catch (IOException e) {
					
					e.printStackTrace();
				}
			}else{
				show_pic_url=show_video_picurl;
			}
		}
		
		
		
		String result = as.updateActive(id, tital, enroll_begin_time, enroll_end_time, prepare_start_time, prepare_end_time, activity_start_time, activity_end_time, show_type, show_pic_url, remark, cost, most_man, appearance, canUseCoupon, fileData,price,address,organiser);
		
		if(result.equals("1")){
			mav = infoActive(id);
			mav.addObject("message", "活动封面类型不合法！");
			return mav;
		}
		
		if(result.equals("2")){
			mav = infoActive(id);
			mav.addObject("message", "报名开始时间不能大于结束时间！");
			return mav;
		}
		
		if(result.equals("3")){
			mav = infoActive(id);
			mav.addObject("message", "准备开始时间不能大于结束时间！");
			return mav;
		}
		
		if(result.equals("4")){
			mav = infoActive(id);
			mav.addObject("message", "活动开始时间不能大于结束时间！");
			return mav;
		}
		
		if(result.equals("5")){
			mav = infoActive(id);
			mav.addObject("message", "报名结束时间不能大于准备开始时间！");
			return mav;
		}
		
		if(result.equals("6")){
			mav = infoActive(id);
			mav.addObject("message", "准备结束时间不能大于活动开始时间！");
			return mav;
		}
		
		if(result.equals("7")){
			mav = infoActive(id);
			mav.addObject("message", "修改失败！");
			return mav;
		}
		
		mav= new ModelAndView("redirect:/active/activelist.do");
		return mav;
	}
	/**
	 * 活动报名管理列表
	 * @param page
	 * @param size
	 * @param title
	 * @return
	 */
	@RequestMapping(value="peoActivelist")
	public ModelAndView peoActivelist(String page,String size,String count,String id){
		page=StringUtil.isNullOrBlank(page)?"1":page;
		size=StringUtil.isNullOrBlank(size)?"5":size;
		id=StringUtil.isNullOrBlank(id)?"":id;
		ModelAndView mav = new ModelAndView();
		mav.setViewName("line/linepeople/active_list");
		mav.addObject(as.getPeoList(page, size, id));
		
		mav.addObject("page", page);
		mav.addObject("size", size);
		mav.addObject("count", count);
		mav.addObject("id", id);
		return mav;
	}
	
	/**
	 * 活动嘉宾列表
	 * @param page
	 * @param size
	 * @param title
	 * @return
	 */
	@RequestMapping(value="jiabinList")
	public ModelAndView jiabinList(String page,String size,String count,String id){
		page=StringUtil.isNullOrBlank(page)?"1":page;
		size=StringUtil.isNullOrBlank(size)?"5":size;
		id=StringUtil.isNullOrBlank(id)?"":id;
		ModelAndView mav = new ModelAndView();
		mav.setViewName("line/linepeople/activepeo_list");
		mav.addObject(as.jabinList(page, size, id));
		mav.addObject("id", id);
		mav.addObject("page", page);
		mav.addObject("size", size);
		mav.addObject("count", count);

		return mav;
	}
	/**
	 * 获取嘉宾详情
	 * @param id
	 * @return
	 */
	@RequestMapping(value="jiabinInfo")
	public ModelAndView jiabinInfo(String id){
		ModelAndView mav=new ModelAndView();
		mav.addObject(as.jiabinInfo(id));
		mav.setViewName("line/linepeople/activepeo_edit");
		return mav;
	}
	
	/**
	 * 根据id去删除活动嘉宾
	 * @param id
	 * @return
	 */
	@RequestMapping(value="deletejiabin")
	public ModelAndView deletejiabin(String id,String aid){
		ModelAndView mav=new ModelAndView();
		int result = os.delete(ActivityUser.class, id);
		mav= new ModelAndView("redirect:/active/jiabinList.do?id="+aid+"");
		return mav;
	}
	/**
	 * 跳转添加活动嘉宾
	 * @param id
	 * @return
	 */
	@RequestMapping(value="toAddpeoActive")
	public ModelAndView toAddpeoActive(String id){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("line/linepeople/activepeo_add");
		mav.addObject("id",id);
		return mav;
	}
	/**
	 * 添加嘉宾
	 * @param id
	 * @param uid
	 * @param money
	 * @return
	 */
	@RequestMapping(value="saveJiabin")
	public ModelAndView saveJiabin(String aid,String uid,String money){
		ModelAndView mav = new ModelAndView();
		ActivityUser au = new  ActivityUser();
		au.setUser_id(uid);
		au.setActivity_id(aid);
		double d = Double.parseDouble(money);
		au.setAppearance(d);
		au.setMan_type(Statics.ACTIVITY_USER_TYPE_TOP);
		au.setEnter_time(System.currentTimeMillis());
		esht.getHibernateTemplate().save(au);
		
		mav= new ModelAndView("redirect:/active/jiabinList.do?id="+aid+"");
		return mav;
	}
	
	
	/**
	 * 修改嘉宾
	 * @param id
	 * @param uid
	 * @param money
	 * @return
	 */
	@RequestMapping(value="updateJiabin")
	public ModelAndView updateJiabin(String id,String uid,String money){
		ModelAndView mav = new ModelAndView();
		ActivityUser au=esht.findFirstOneByPropEq(ActivityUser.class, "id", id);
		au.setUser_id(uid);
		double d = Double.parseDouble(money);
		au.setAppearance(d);
		esht.getHibernateTemplate().update(au);
		return mav;
	}
	
	/**
	 * 活动订单管理
	 * @param page
	 * @param size
	 * @param count
	 * @param title
	 * @return
	 */
	@RequestMapping(value="activeOrder")
	public  ModelAndView activeOrder(String oid,String page,String size,String count,String aid,String phone){
		page=StringUtil.isNullOrBlank(page)?"1":page;
		size=StringUtil.isNullOrBlank(size)?"5":size;
		aid=StringUtil.isNullOrBlank(aid)?"":aid;
		oid=StringUtil.isNullOrBlank(oid)?"":oid;
		phone=StringUtil.isNullOrBlank(phone)?"":phone;
		ModelAndView mav = new ModelAndView();
		mav.setViewName("line/lineorder/lineorder_list");
		mav.addObject(as.getActiveOrder(oid,page, size, aid,phone));
		mav.addObject("page", page);
		mav.addObject("size", size);
		mav.addObject("count", count);
		mav.addObject("oid", oid);
		mav.addObject("aid", aid);
		mav.addObject("phone", phone);
		return mav;
	}
	/**
	 * 评论列表
	 * @param name
	 * @return
	 */
	@RequestMapping(value="commentlist")
	public ModelAndView commentlist(String page,String size,String count,String name){
		page=StringUtil.isNullOrBlank(page)?"1":page;
		size=StringUtil.isNullOrBlank(size)?"5":size;
		name=StringUtil.isNullOrBlank(name)?"":name;
		ModelAndView mav = new ModelAndView();
		mav.addObject(as.getcomment(page, size, name));
		mav.setViewName("line/linecommen/linecomment");
		mav.addObject("page",page);
		mav.addObject("size",size);
		mav.addObject("count",count);
		mav.addObject("name",name);
		return mav;
	}
	/**
	 * 评论详情（含二级评论列表）	
	 * @param id
	 * @return
	 */
	@RequestMapping(value="commentInfo")
	public ModelAndView commentInfo(String id){
		Comment com=esht.findFirstOneByPropEq(Comment.class, "id", id);
		String comm_content=com.getComm_content();
		String comm_id=com.getCommed_id();
		String user_id=com.getUser_id();
		User user=esht.findFirstOneByPropEq(User.class,"id", user_id);
		String user_name=user.getUsername();
		Activity uvr=esht.findFirstOneByPropEq(Activity.class, "id", comm_id);
		String course_name=uvr.getTitle();
		Integer status=com.getStatus();
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("topid",id);
		mav.addObject(as.commentInfo(user_id));
		mav.addObject("comm_content",comm_content);
		mav.addObject("course_name",course_name);
		mav.addObject("status",status);
		mav.addObject("user_name",user_name);
		mav.setViewName("line/linecommen/linecomment_info");
		return mav;
	}
	/**
	 * 拉黑评论
	 * @return
	 */
	@RequestMapping(value="blackComent")
	public ModelAndView blackComent(String id,String topid){
		ModelAndView mav =new ModelAndView();
		Comment com= esht.findFirstOneByPropEq(Comment.class, "id", id);
		com.setStatus(Statics.NO_INT);
		esht.getHibernateTemplate().update(com);
		mav= new ModelAndView("redirect:/active/commentInfo.do?id="+topid+" ");
		return mav;
	}
	/**
	 * 恢复评论
	 * @return
	 */
	@RequestMapping(value="reginaComent")
	public ModelAndView reginaComent(String id,String topid){
		ModelAndView mav =new ModelAndView();
		Comment com= esht.findFirstOneByPropEq(Comment.class, "id", id);
		com.setStatus(Statics.YES_INT);
		esht.getHibernateTemplate().update(com);
		mav= new ModelAndView("redirect:/active/commentInfo.do?id="+topid+" ");
		return mav;
	}
	
	

	
}
