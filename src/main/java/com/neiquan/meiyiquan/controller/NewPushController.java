package com.neiquan.meiyiquan.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.neiquan.meiyiquan.code.Code;
import com.neiquan.meiyiquan.service.ChannelService;
import com.neiquan.meiyiquan.service.MessageService;
import com.neiquan.meiyiquan.service.NewPushService;
import com.neiquan.meiyiquan.util.Statics;


/**
 * 作者：温尉棨
 * 创建日期：2017年2月15日
 * 类说明：消息推送
 */
@Controller
@RequestMapping(value="push")
public class NewPushController {

	@Autowired
	private NewPushService nps;
	
	@Autowired
	private MessageService ms;
	
	/**
	 * 消息推送条件
	 * @param ageid
	 * @param zodicid
	 * @param sexid
	 * @param jobid
	 * @param adressid
	 * @param type
	 * @return
	 * 设置用户筛选条件，选择，用户年龄段，星座、性别、区域，等信息用户职称，学历
	 */
	@RequestMapping(value="newsPush")
	public ModelAndView push(String ageid,String zodicid,String sexid,String jobid,String tree 
			,String type){
		ModelAndView mav =new ModelAndView();
		Code code= ms.job_list(String.valueOf(Statics.JOB_TYPE_AGE));
		Code code1= ms.job_list(String.valueOf(Statics.JOB_TYPE_JOB));
		mav.addObject("ageCode", code);
		mav.addObject("jobCode", code1);
		mav.setViewName("newspush/push/push_choose");
		
		return mav;
	}
	
	@RequestMapping(value="send")
	public ModelAndView send(String title,String content,String ageid,String zodicid,
					String sexid,String jobid,String tree ,String type){
		ModelAndView mav = push(null, null, null, null, null, null);
		Code code = nps.send(title, content, ageid, zodicid, sexid, jobid, tree, type);
		mav.addObject("message", code.getMessage());
		return mav;
	}
	
	@ResponseBody
	@RequestMapping(value="count")
	public Code count(String title,String content,String ageid,String zodicid,
					String sexid,String jobid,String tree ,String type){
		Code result= nps.count(title, content, ageid, zodicid, sexid, jobid, tree, type);
		
		return result;
	}
	
}
