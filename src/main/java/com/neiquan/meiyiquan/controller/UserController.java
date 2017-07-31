package com.neiquan.meiyiquan.controller;

import java.util.regex.Pattern;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.neiquan.meiyiquan.code.Code;
import com.neiquan.meiyiquan.service.UserService;
import com.neiquan.meiyiquan.util.StringUtil;

/**
 * 作者：刘丹
 * 创建日期：2017年2月6日
 * 类说明：用户管理
 */
@Controller
@RequestMapping(value="user")
public class UserController {

	@Autowired
	private UserService ds;
	
	/**
	 * 用户多条件查询
	 *
	 * @param id 
	 * @return
	 */
	@RequestMapping(value="getList")
	public ModelAndView getList(String keywords,String user_type ,String pageIndex,String pageSize,
			String timeStar,String timeEnd ,String orderBy,String collation,String gender,
			String tree,String zodiac,String user_state,String job
			){
		keywords=StringUtil.isNullOrBlank(keywords)?"":keywords;
		user_type=StringUtil.isNullOrBlank(user_type)?"":user_type;
		pageIndex=	StringUtil.isNullOrBlank(pageIndex)?"1":pageIndex;
		Integer	pIndex=Integer.valueOf(pageIndex);
		pageSize=StringUtil.isNullOrBlank(pageSize)?"10":pageSize;
		Integer	pSize=Integer.valueOf(pageSize);
		orderBy=StringUtil.isNullOrBlank(orderBy)?" tb_user.create_time":orderBy;//默认点击量排序
		collation=StringUtil.isNullOrBlank(collation)?" DESC":collation;//默认倒序
		tree=StringUtil.isNullOrBlank(tree)?"":tree;//地址树
		zodiac=StringUtil.isNullOrBlank(zodiac)?"":zodiac;//星座
		gender=StringUtil.isNullOrBlank(gender)?"":gender;//性别
		user_type=StringUtil.isNullOrBlank(user_type)?"":user_type;//用户类型
		user_state=StringUtil.isNullOrBlank(user_state)?"":user_state;//用户状态
		job=StringUtil.isNullOrBlank(job)?"":job;//用户状态
		ModelAndView mav = new ModelAndView();
		mav.setViewName("user/user_list");
		mav.addObject(ds.getList( keywords,user_type ,pIndex,pSize, timeStar, timeEnd,orderBy,
				collation,tree,zodiac,gender,user_state,job));
		mav.addObject("keywords", keywords);
		mav.addObject("user_type", user_type);
		mav.addObject("pageIndex", pageIndex);
		mav.addObject("pageSize", pageSize);
		mav.addObject("timeStar", timeStar);
		mav.addObject("timeEnd", timeEnd);
		mav.addObject("orderBy", orderBy);
		mav.addObject("collation", collation);
		mav.addObject("tree", tree);
		mav.addObject("gender", gender);
		mav.addObject("user_type", user_type);
		mav.addObject("user_state", user_state);
		mav.addObject("zodiac", zodiac);
		mav.addObject("job", job);
		return mav;
	}
	/**
	 * 用户订单查询
	 *
	 * @param id 
	 * @return
	 */
	@RequestMapping(value="getDetails")
	public ModelAndView getOrderList(String keywords,String id ){
		keywords=StringUtil.isNullOrBlank(keywords)?"":keywords;
		id=StringUtil.isNullOrBlank(id)?"":id;
		ModelAndView mav = new ModelAndView();
		mav.setViewName("user/user_details");
		mav.addObject("keywords", keywords);
		mav.addObject("user", ds.getUserById(id));
		mav.addObject("order",ds.getUserOrder(id));
		return mav;
	}
	/**
	 * 启用禁用（拉黑，选正）
	 * @param id
	 * @param status
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="verPhone")
	public Code verPhone(Long phone){
		if(StringUtil.isNullOrBlank(phone))	return Code.init(1, "手机号不能为空");
		boolean b = Pattern.compile("^1[3|4|5|8|7]\\d{9}$").matcher(String.valueOf(phone)).find();
		if(!b)return Code.init(1, "手机号格式不正确");
		return ds.verPhone(phone);
	}
	/**
	 * 启用禁用（拉黑，选正）
	 * @param id
	 * @param status
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="upStatus")
	public Code upStatus(String id,String status,HttpSession session){
		return ds.upDate(id,status,session);
	}
	
	
	/**
	 * 禁言
	 * @param id
	 * @param status
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="upComment")
	public Code upComment(String id,Integer status,HttpSession session){
		return ds.upComment(id,status,session);
	}
	/**
	 * 用户多条件查询
	 *
	 * @param id 
	 * @return
	 */
	@RequestMapping(value="getSurveyList")
	public ModelAndView getSurveyList(String keywords,String user_type ,String pageIndex,String pageSize,
			String timeStar,String timeEnd ,String orderBy,String collation,String gender,
			String tree,String zodiac,String user_state,String job
			){
		keywords=StringUtil.isNullOrBlank(keywords)?"":keywords;
		user_type=StringUtil.isNullOrBlank(user_type)?"":user_type;
		pageIndex=	StringUtil.isNullOrBlank(pageIndex)?"1":pageIndex;
		Integer	pIndex=Integer.valueOf(pageIndex);
		pageSize=StringUtil.isNullOrBlank(pageSize)?"10":pageSize;
		Integer	pSize=Integer.valueOf(pageSize);
		orderBy=StringUtil.isNullOrBlank(orderBy)?" tb_user.create_time":orderBy;//默认点击量排序
		collation=StringUtil.isNullOrBlank(collation)?" DESC":collation;//默认倒序
		tree=StringUtil.isNullOrBlank(tree)?"":tree;//地址树
		zodiac=StringUtil.isNullOrBlank(zodiac)?"":zodiac;//星座
		gender=StringUtil.isNullOrBlank(gender)?"":gender;//性别
		user_type=StringUtil.isNullOrBlank(user_type)?"":user_type;//用户类型
		user_state=StringUtil.isNullOrBlank(user_state)?"":user_state;//用户状态
		job=StringUtil.isNullOrBlank(job)?"":job;//用户状态
		ModelAndView mav = new ModelAndView();
		mav.setViewName("user/user_survey_list");
		mav.addObject(ds.getSurveyList( keywords,user_type ,pIndex,pSize, timeStar, timeEnd,orderBy,
				collation,tree,zodiac,gender,user_state,job));
		mav.addObject("keywords", keywords);
		mav.addObject("user_type", user_type);
		mav.addObject("pageIndex", pageIndex);
		mav.addObject("pageSize", pageSize);
		mav.addObject("timeStar", timeStar);
		mav.addObject("timeEnd", timeEnd);
		mav.addObject("orderBy", orderBy);
		mav.addObject("collation", collation);
		mav.addObject("tree", tree);
		mav.addObject("gender", gender);
		mav.addObject("user_type", user_type);
		mav.addObject("user_state", user_state);
		mav.addObject("zodiac", zodiac);
		mav.addObject("job", job);
		return mav;
	}
	/**
	 * 用户多条件查询
	 *
	 * @param id 
	 * @return
	 */
	@RequestMapping(value="getUserSurvey")
	public ModelAndView getUserSurvey(String id ,String pageIndex,String pageSize,String orderBy,String collation
			){
		pageIndex=	StringUtil.isNullOrBlank(pageIndex)?"1":pageIndex;
		Integer	pIndex=Integer.valueOf(pageIndex);
		pageSize=StringUtil.isNullOrBlank(pageSize)?"10":pageSize;
		Integer	pSize=Integer.valueOf(pageSize);
		orderBy=StringUtil.isNullOrBlank(orderBy)?" tb_user_survey.create_time":orderBy;//默认点击量排序
		collation=StringUtil.isNullOrBlank(collation)?" DESC":collation;//默认倒序
		ModelAndView mav = new ModelAndView();
		mav.setViewName("user/user_survey");
		mav.addObject(ds.getUserSurvey( id,pIndex,pSize,orderBy,
				collation));
		mav.addObject("id", id);
		mav.addObject("pageIndex", pageIndex);
		mav.addObject("pageSize", pageSize);
		return mav;
	}
	
	/**
	 * 添加调查
	 * @param id
	 * @param status
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="addSurvey")
	public Code addSurvey(String id,String remark,HttpSession session){
		return ds.addUserSurvey(id,remark,session);
	}
	/**
	 * 会员统计
	 * @return
	 */
	@RequestMapping(value="userCount")
	public ModelAndView  userCount(String orderby){
		orderby=StringUtil.isNullOrBlank(orderby)?"ask":orderby;
		ModelAndView mav=new ModelAndView();
		mav.addObject(ds.userCount(orderby));
		mav.setViewName("user/user_count");
		return mav;
	}
}
 
