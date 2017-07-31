package com.neiquan.meiyiquan.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.neiquan.meiyiquan.code.Code;
import com.neiquan.meiyiquan.pojo.Comment;
import com.neiquan.meiyiquan.pojo.Teacher;
import com.neiquan.meiyiquan.service.TeacherMoneyService;
import com.neiquan.meiyiquan.util.ExtraSpringHibernateTemplate;
import com.neiquan.meiyiquan.util.StringUtil;

/**
 * 作者：温尉棨
 * 创建日期：2017年2月27日
 * 类说明：
 */
@Controller
@RequestMapping(value="teachermoney")
public class TeacherMoneyController {

	@Autowired
	private ExtraSpringHibernateTemplate esht;
	@Autowired
	private TeacherMoneyService tms;
	/**
	 * 讲师收益列表
	 * @param page
	 * @param size
	 * @param count
	 * @param name 讲师名称
	 * @param session
	 * @return
	 */
	@RequestMapping(value="tmList")
	public ModelAndView tmList(String page,String size,String count,String name){
		page=StringUtil.isNullOrBlank(page)?"1":page;
		size=StringUtil.isNullOrBlank(size)?"5":size;
		name=StringUtil.isNullOrBlank(name)?"":name;
		ModelAndView mav=new ModelAndView();
		mav.addObject(tms.tmList(page, size, name));
		mav.addObject("page",page);
		mav.addObject("size",size);
		mav.addObject("name",name);
		mav.setViewName("teacher/tm_shouyi");
		return mav;
	}
	/**
	 * 讲师提交详情
	 * @param id 讲师id
	 * @param name
	 * @param zong
	 * @return
	 */
	@RequestMapping(value="tmInfo")
	public ModelAndView tmInfo(String id,String zong){
		ModelAndView mav=new ModelAndView();
		Teacher tch=esht.findFirstOneByPropEq(Teacher.class, "id", id);
		String name=tch.getName();
		mav.addObject("tid",id);
		mav.addObject("zong",zong);
		mav.addObject("name",name);
		mav.setViewName("teacher/tm_tijiao");
		return mav;
	}
	
	/**
	 * 添加信息至申请表
	 * @param id
	 * @param money
	 * @param zong
	 * @return
	 */
	@RequestMapping(value="tmsend")
	public ModelAndView tmsend(String tid,String money,String zong,HttpSession session){
		Map<String, Object> admin = (Map<String, Object>) session.getAttribute("user");
		String aid = admin.get("id").toString();
		ModelAndView mav=new ModelAndView();
		Code result=tms.tmsend(tid, money, zong, aid);
		mav= new ModelAndView("redirect:/teachermoney/tmList.do");
		return mav;
	}
	
	
}
