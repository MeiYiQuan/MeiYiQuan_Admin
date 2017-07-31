package com.neiquan.meiyiquan.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.neiquan.meiyiquan.code.Code;
import com.neiquan.meiyiquan.util.ExtraSpringHibernateTemplate;

/**
 * 作者：温尉棨
 * 创建日期：2017年1月20日
 * 类说明：
 */
public interface SoonCourseService {
	/**
	 * 即将上映课程表
	 * @param page
	 * @param size
	 * @param count
	 * @param createBegin
	 * @param createEnd
	 * @param orderBy
	 * @param collation
	 * @param pid
	 * @return
	 */
	public Code getSoonList(String page,String size,String count,String createBegin,String createEnd,String orderBy,String collation, String pid);
	/**
	 * 添加即将上演课程
	 * @param playing_time
	 * @param title
	 * @param pic_big_url
	 * @param pic_small_url
	 * @param teacher_id
	 * @param cost
	 * @param channel_id
	 * @param fileData
	 * @param course_compaign_type
	 * @param description
	 * @return
	 */
	public Code addSoon(String playing_time,String title,String pic_big_url,String pic_small_url,
			String teacher_id,String cost,String channel_id,String fileData,String course_compaign_type,
			String description);
	/**
	 * 修改即将上映
	 * @param id
	 * @param playing_time
	 * @param title
	 * @param pic_big_url
	 * @param pic_small_url
	 * @param teacher_id
	 * @param cost
	 * @param channel_id
	 * @param fileData
	 * @param course_compaign_type
	 * @param description
	 * @return
	 */
	public Code updateSoon(String id,String playing_time,String title,String pic_big_url,String pic_small_url,
			String teacher_id,String cost,String channel_id,String fileData,String course_compaign_type,
			String description);
	/**
	 * 获取即将上映详情
	 * @param id
	 * @return
	 */
	public Code soonInfo(String id);
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	public Code delAll(String id ) ;
	/**
	 * 讨论列表
	 * @param title
	 * @return
	 */
	public Code commentReplay(String title,String page,String size,String count);
	/**
	 * 删除讨论
	 * @param id
	 * @return
	 */
	public Code delete(String id);
	/**
	 * 
	 * @param id
	 * @return
	 */
	public Code updatesoon(String id,String like_expect_count);
}
