package com.neiquan.meiyiquan.service;

import javax.servlet.http.HttpSession;

import com.neiquan.meiyiquan.code.Code;
import com.neiquan.meiyiquan.pojo.Course;
import com.neiquan.meiyiquan.pojo.VideoStandard;
/**
 * 作者：刘丹
 * 创建日期：2017年1月7日
 * 类说明：课程
 */
public interface CourseService {
	
	/**
	 *  课程查询
	 * @param cs
	 * @return
	 */
	Code getCourseList(String keywords, String status, Integer pageIndex,
			Integer pageSize, String timeStar, String timeEnd, String orderBy,
			String collation, String channel); 
	
	Code upStatus(String id, String status, HttpSession session);


	Code add(Course course, HttpSession session);

	Code del(String id, HttpSession session);

	Code getCourseById(String id);

	 

	Code getCourseStandardList(String keywords, String status,
			Integer pageIndex, Integer pageSize, String timeStar,
			String timeEnd, String orderBy, String collation, String channel);

	Code addStandard(VideoStandard v, HttpSession session);

	Code updateStandard(VideoStandard v, HttpSession session);

	Code commnet(String id, Integer firstResult, Integer maxResults,
			HttpSession session);
	/**
	 * 课程统计
	 * @param orderby
	 * @return
	 */
	public Code  courseCount(String orderby);

	Code update(String id,String title, String description, String remark,
			Integer course_compaign_type, String course_compaign_video_url,
			String pic_big_url, String channel_id, String teacher_id,
			HttpSession session);
	
}
