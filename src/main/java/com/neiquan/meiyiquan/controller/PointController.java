package com.neiquan.meiyiquan.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.neiquan.meiyiquan.code.Code;
import com.neiquan.meiyiquan.service.PointService;

/**
 * 作者：刘丹
 * 创建日期：2017年2月6日
 * 类说明：积分管理
 */
@Controller
@RequestMapping(value="point")
public class PointController {

	@Autowired
	private PointService ds;
	
	/**
	 * 积分列表
	 *
	 * @param id 
	 * @return
	 */
	@RequestMapping(value="getPointList")
	public ModelAndView getCourseList(){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("point/point_manage_list");
		mav.addObject(ds.getPointList( ));
		return mav;
	}
	/**
	 * 修改积分
	 * @param id
	 * @param str
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="update")
	public Code update( String id,String str,HttpSession session){
		return ds.upDate(id, str, session);
	}
}
 
