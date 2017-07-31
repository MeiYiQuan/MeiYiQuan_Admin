package com.neiquan.meiyiquan.service;

import com.neiquan.meiyiquan.code.Code;

/**
 * 作者：温尉棨
 * 创建日期：2017年2月5日
 * 类说明：
 */
public interface SuggestionService {
	/**
	 * 客户反馈列表
	 * @param page
	 * @param size
	 * @param count
	 * @param kaystatus
	 * @param kaytxt
	 * @param createBegin
	 * @param createEnd
	 * @param orderBy
	 * @param collation
	 * @param status
	 * @param tree
	 * @return
	 */
	public Code getList(String page, String size, String kaytxt, String createBegin,
			 String tree,String type,String status);
	/**
	 * 回访反馈详情
	 * @param id
	 * @return
	 */
	public Code getInfo(String id);
	/**
	 * 添加反馈回复消息
	 * @param recount
	 * @param id
	 * @return
	 */
	public Code updatCount(String recount,String id,String user_id );
	/**
	 * 反馈统计查询
	 * @param timeBegin
	 * @param timeEnd
	 * @return
	 */
	public Code tongji(String timeBegin,String timeEnd, String type);
	/**
	 * 处理状态统计
	 * @param timeBegin
	 * @param timeEnd
	 * @return
	 */
	public Code zongtongji(String timeBegin,String timeEnd);
}
