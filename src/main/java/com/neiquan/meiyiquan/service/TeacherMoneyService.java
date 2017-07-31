package com.neiquan.meiyiquan.service;

import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;

import com.neiquan.meiyiquan.code.Code;

/**
 * 作者：温尉棨
 * 创建日期：2017年2月27日
 * 类说明：
 */
public interface TeacherMoneyService {
	/**
	 * 讲师收益列表
	 * @param page
	 * @param size
	 * @param name
	 * @return
	 */
	public Code tmList(String page,String size,String name);
	/**
	 * 添加讲师申请
	 * @param id
	 * @param money
	 * @param zong
	 * @return
	 */
	public Code tmsend(String id,String money,String zong,String aid);
}
