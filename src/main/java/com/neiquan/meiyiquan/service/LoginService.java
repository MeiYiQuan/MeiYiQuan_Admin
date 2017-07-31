package com.neiquan.meiyiquan.service;

import com.neiquan.meiyiquan.code.Code;

/**
 * 作者：齐潮
 * 创建日期：2016年12月8日
 * 类说明：用于处理登录的业务逻辑
 */
public interface LoginService {

	/**
	 * 登录
	 * @param loginName
	 * @param password
	 * @return
	 */
	public Code login(String loginName,String password);
}
