package com.neiquan.meiyiquan.service;

import org.springframework.web.servlet.ModelAndView;

import com.neiquan.meiyiquan.code.Code;

/**
 * 作者：温尉棨
 * 创建日期：2017年2月14日
 * 类说明：
 */
public interface MessageService {

	/**
	 * 平台参数
	 * @return
	 */
	public Code public_paratemer();
	/**
	 * 可修改平台列表
	 * @param type
	 * @return
	 */
	public Code job_list(String type);
	/**
	 * 平台详情
	 * @param id
	 * @return
	 */
	public Code publicInfo(String id);
	/**
	 * 修改公共参数
	 * @param id
	 * @param parameter_name
	 * @param pic_url
	 * @return
	 */
	public long publicUpdate(String id,String parameter_name,String pic_url);
	/**
	 * 管理员列表
	 * @param page
	 * @param size
	 * @return
	 */
	public Code adminList(String page,String size,String name);
	/**
	 * 管理员详情
	 * @param id
	 * @return
	 */
	public Code adminInfo(String id);
	/**
	 * 添加用户
	 * @param name
	 * @param password
	 * @param roleid
	 * @return
	 */
	public String addAdmin(String name,String password,String roleid);
	/**
	 * 修改用户
	 * @param id
	 * @param name
	 * @param password
	 * @param roleid
	 * @return
	 */
	public String updateAdmin(String id,String name,String password,String roleid);
	/**
	 * 权限列表
	 * @return
	 */
	public Code role();
	/**
	 * 
	 * @param page
	 * @param size
	 * @param name
	 * @return
	 */
	public Code tmList(String page,String size,String name);
}
