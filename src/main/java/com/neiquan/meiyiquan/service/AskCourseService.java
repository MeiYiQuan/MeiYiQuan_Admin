package com.neiquan.meiyiquan.service;

import com.neiquan.meiyiquan.code.Code;

/**
 * 作者：温尉棨
 * 创建日期：2017年1月21日
 * 类说明：
 */
public interface AskCourseService {
	/**
	 * 求教程已通过列表
	 * @param page
	 * @param size
	 * @param count
	 * @param kaystatus
	 * @param kaytxt
	 * @param createBegin
	 * @param createEnd
	 * @param status
	 * @param tree
	 * @return
	 */
	public Code getAskList(String page,String size,String count,String kaystatus,String kaytxt,
			String createBegin,String createEnd,String status,String tree,String orderBy,String collation,String channelid);
	/**
	 * 修改虚拟投票
	 * @param vote
	 * @return
	 */
	public Code addVote(String id,String number);
	/**
	 * 审核列表（全部展示）
	 * @param page
	 * @param size
	 * @param count
	 * @param kaystatus
	 * @param kaytxt
	 * @return
	 */
	public Code review(String page,String size,String count,String kaystatus,String kaytxt,String createBegin,String createEnd
			 ,String status);
	/**
	 * 榜首列表
	 * @param page
	 * @param size
	 * @param count
	 * @param createBegin
	 * @param createEnd
	 * @return
	 */
	public Code top(String page,String size,String count,String createBegin,String createEnd, String kaystatus, String kaytxt);
	/**
	 * 获取详情
	 * @param id
	 * @return
	 */
	public Code getInfo(String id);
	/**
	 * 修改求教程详情
	 */
	public Integer updateInfo(String id,String firstCate,String cate,String teacher_id,String question
			,String course_name,String feedback,String feedback_status,String pic_url);
	/**
	 * 评论列表
	 * @param name
	 * @return
	 */
	public Code commentList(String page,String size,String count,String name);
	/**
	 * 添加教程
	 * @param firstCate
	 * @param cate
	 * @param teacher_id
	 * @param question
	 * @param course_name
	 * @param feedback
	 * @param pic_url
	 * @return
	 */
	public String save(String firstCate,String cate,String teacher_id,String question
			,String course_name,String feedback,String pic_url,String uid);
	/**
	 * 评论二级列表
	 */
	public  Code commentInfo(String id);	
	/**
	 * 
	 * @param type 是否是平台
	 * @param typee  是普通用户 还是讲师
	 * @return
	 */
	public long typeTongji(String type,String typee);
	/**
	 * 统计  点击量 投票量 
	 * @param type
	 * @param typee
	 * @return
	 */
	public Code numTongji(String type,String typee);
	/**
	 * 推送
	 * @param id
	 * @return
	 */
	Code push(String id);
	
	void time();
}
