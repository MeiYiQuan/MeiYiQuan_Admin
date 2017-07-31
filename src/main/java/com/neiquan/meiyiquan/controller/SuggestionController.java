package com.neiquan.meiyiquan.controller;

import org.hibernate.mapping.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.neiquan.meiyiquan.code.Code;
import com.neiquan.meiyiquan.service.PushService;
import com.neiquan.meiyiquan.service.SuggestionService;
import com.neiquan.meiyiquan.util.Statics;
import com.neiquan.meiyiquan.util.StringUtil;

/**
 * 作者：温尉棨
 * 创建日期：2017年2月5日
 * 类说明：客服投诉
 */
@Controller
@RequestMapping(value="suggestion")
public class SuggestionController {

	@Autowired
	private SuggestionService ss;
	@Autowired
	private PushService push;
	/**
	 * 回访列表
	 * @param page
	 * @param size
	 * @param count
	 * @param kaytxt
	 * @param createBegin
	 * @param tree
	 * @return
	 */
	@RequestMapping(value="suggestionlist")
	public ModelAndView activelist(String page,String size,String count,String kaytxt,
			String createBegin,String tree,String type,String status){
		page=StringUtil.isNullOrBlank(page)?"1":page;
		size=StringUtil.isNullOrBlank(size)?"5":size;
		kaytxt=StringUtil.isNullOrBlank(kaytxt)?"":kaytxt;
		createBegin=StringUtil.isNullOrBlank(createBegin)?"":createBegin;
		tree=StringUtil.isNullOrBlank(tree)?"":tree;
		type=StringUtil.isNullOrBlank(type)?"":type;
		status=StringUtil.isNullOrBlank(status)?"":status;
		ModelAndView mav = new ModelAndView();
		mav.setViewName("suggestion/suggestion/untreated_list");
		mav.addObject(ss.getList(page, size, kaytxt, createBegin, tree, type, status));
		mav.addObject("page",page);
		mav.addObject("size",size);
		mav.addObject("count",count);
		mav.addObject("kaytxt",kaytxt);
		mav.addObject("createBegin",createBegin);
		mav.addObject("type",type);
		mav.addObject("status",status);
		mav.addObject("tree",tree);
		
		
		return mav;
	}
	/**
	 * 回访反馈详情
	 * @param id
	 * @return
	 */
	@RequestMapping(value="suggestionInfo")
	public ModelAndView getInfo(String id){
		ModelAndView mav = new ModelAndView();
		mav.addObject(ss.getInfo(id));
		mav.setViewName("suggestion/suggestion/untreated_info");
		return mav;
	}
	/**
	 * 获取发送内容
	 * @param id
	 * @return
	 */
	@RequestMapping(value="updateCount")
	public ModelAndView updateCount(String id,String recount,String user_id){
		push.pushEveryOne("", "意见反馈回复",Statics.PUSH_TYPE_FORTHWITH_NOTABLE, recount, null, user_id);
		Code result2 = ss.updatCount(recount, id, user_id);
		ModelAndView mav = new ModelAndView();
		
		mav= new ModelAndView("redirect:/suggestion/suggestionlist.do");
		return mav;
	}
	/**
	 * 反馈统计
	 * @param timeBegin
	 * @param timeEnd
	 * @return
	 */
	@RequestMapping(value="topngji")
	public ModelAndView topngji(String type,String timeBegin,String timeEnd ){
		ModelAndView mav = new ModelAndView();
		timeBegin=StringUtil.isNullOrBlank(timeBegin)?"":timeBegin;
		timeEnd=StringUtil.isNullOrBlank(timeEnd)?"":timeEnd;
		mav.addObject(ss.tongji(timeBegin, timeEnd,type));
		mav.setViewName("suggestion/suggestion/untreated_tongjiinfo");
		return mav;
	}
	/**
	 * 根据类型统计数量
	 * @param timeBegin
	 * @param timeEnd
	 * @return
	 */
	@RequestMapping(value="zongtopngji")
	public ModelAndView zongtopngji(String timeBegin,String timeEnd){
		timeBegin=StringUtil.isNullOrBlank(timeBegin)?"":timeBegin;
		timeEnd=StringUtil.isNullOrBlank(timeEnd)?"":timeEnd;
		ModelAndView mav = new ModelAndView();
		mav.addAllObjects(ss.zongtongji(timeBegin, timeEnd).getMapData());
		mav.setViewName("suggestion/suggestion/untreadted_tongji");
		return mav;
	}
	
}
