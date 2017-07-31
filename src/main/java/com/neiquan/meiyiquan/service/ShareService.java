package com.neiquan.meiyiquan.service;

import com.neiquan.meiyiquan.code.Code;

/**
 * 作者：温尉棨
 * 创建日期：2017年2月19日
 * 类说明：
 */
public interface ShareService {

	/**
	 * 分享列表
	 * @return
	 */
	public Code shareList(String page,String size);
}
