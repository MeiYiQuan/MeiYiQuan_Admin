package com.neiquan.meiyiquan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.neiquan.meiyiquan.service.AskCourseService;
import com.neiquan.meiyiquan.service.ShareService;
import com.neiquan.meiyiquan.util.StringUtil;

/**
 * 作者：温尉棨
 * 创建日期：2017年2月19日
 * 类说明：
 */
@Controller
@RequestMapping(value="share")
public class ShareController {
	@Autowired
	private ShareService ss;
	
	@RequestMapping(value="shareList")
	public ModelAndView shareList(String page,String size,String count){
		ModelAndView mav=new ModelAndView();
		page=StringUtil.isNullOrBlank(page)?"1":page;
		size=StringUtil.isNullOrBlank(size)?"5":size;
		mav.addObject(ss.shareList(page, size));
		mav.addObject("page", page);
		mav.addObject("size", size);
		mav.addObject("count", count);
		mav.setViewName("share/share/share_list");
		return mav;
	}

}
