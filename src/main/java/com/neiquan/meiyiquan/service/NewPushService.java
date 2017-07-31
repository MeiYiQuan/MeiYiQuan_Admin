package com.neiquan.meiyiquan.service;

import com.neiquan.meiyiquan.code.Code;

/**
 * 作者：温尉棨
 * 创建日期：2017年2月15日
 * 类说明：消息推送
 */
public interface NewPushService {
	/**
	 * 推送
	 * @param title
	 * @param content
	 * @param ageid
	 * @param zodicid
	 * @param sexid
	 * @param jobid
	 * @param tree
	 * @param type
	 * @return
	 */
	public Code send(String title,String content,String ageid,String zodicid,
			String sexid,String jobid,String tree ,String type);

	/**
	 * @param title
	 * @param content
	 * @param ageid
	 * @param zodicid
	 * @param sexid
	 * @param jobid
	 * @param tree
	 * @param type
	 * @return
	 */
	Code count(String title, String content, String ageid, String zodicid, String sexid, String jobid, String tree,
			String type);
}
