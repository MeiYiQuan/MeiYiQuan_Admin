package com.neiquan.meiyiquan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.neiquan.meiyiquan.code.Code;
import com.neiquan.meiyiquan.pojo.Order;
import com.neiquan.meiyiquan.pojo.Video;
import com.neiquan.meiyiquan.service.ActiveService;
import com.neiquan.meiyiquan.service.OrderService;
import com.neiquan.meiyiquan.service.VideoService;
import com.neiquan.meiyiquan.util.Statics;
import com.neiquan.meiyiquan.util.StringUtil;

/**
 * 作者：刘丹
 * 创建日期：2017年2月9日
 * 类说明：订单管理
 */
@Controller
@RequestMapping(value="order")
public class OrderController {

	@Autowired
	private OrderService ds;
	@Autowired
	private	VideoService vs;
	@Autowired
	private ActiveService as;
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
	/*	mav.addObject(ds.getList( keywords,user_type ,pIndex,pSize, timeStar, timeEnd,orderBy,
				collation,tree,zodiac,gender,user_state,job));*/
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
	 * 根据课程查询订单
	 *
	 * @param id 
	 * @return
	 */
	@RequestMapping(value="getListByVideoId")
	public ModelAndView getListByVideoId(String id,String pageIndex,String pageSize){
		pageIndex=	StringUtil.isNullOrBlank(pageIndex)?"1":pageIndex;
		Integer	pIndex=Integer.valueOf(pageIndex);
		pageSize=StringUtil.isNullOrBlank(pageSize)?"10":pageSize;
		Integer	pSize=Integer.valueOf(pageSize);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("course/course_order_list");
		mav.addObject(ds.getListByVideoId(id, pIndex, pSize));
		mav.addObject("pageIndex", pageIndex);
		mav.addObject("pageSize", pageSize);
		mav.addObject("id", id);
		return mav;
	}
	/**
	 * 订单查询
	 *
	 * @param id 
	 * @return
	 */
	@RequestMapping(value="getDetails")
	public ModelAndView getOrderList(String id ,String order_type){
		id=StringUtil.isNullOrBlank(id)?"":id;
		ModelAndView mav = new ModelAndView();
		mav.setViewName("order/order_details");
		Code	code=ds.getOrder(id);
		mav.addObject("order",code);
		Order order=(Order)code.getData();
		if(order_type.equals(Statics.ORDER_TYPE_VIDEO)){
			Video	video=(Video)vs.getVideoById(order.getVideo_id()).getData();
			mav.addObject("video",video);
		}else if(order_type.equals(Statics.ORDER_TYPE_ACTIVITY)){
			mav.addObject("activity",as.infoActive(order.getVideo_id()));
		}
		return mav;
	}
}
 
