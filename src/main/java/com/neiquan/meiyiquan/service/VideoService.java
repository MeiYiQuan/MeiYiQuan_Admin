package com.neiquan.meiyiquan.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.neiquan.meiyiquan.code.Code;
import com.neiquan.meiyiquan.pojo.Course;
import com.neiquan.meiyiquan.pojo.Video;
/**
 * 作者：刘丹
 * 创建日期：2017年1月21日
 * 类说明：视频
 */
public interface VideoService {
	
	/**
	 * 视频查询根据视频ID
	 * @param id
	 * @return
	 */
	Code getListByCourseId( String id);
	/**
	 * 视频查询根据ID
	 * @param id
	 * @return
	 */
	Code getVideoById( String id);
	Code upStatus(String id, String status, HttpSession session);
	 
	Code add(String title, String course_id, String time_long,
			String video_save_url, String video_pic_url, String remark,
			Double per_cost, String video_size, Integer free, Integer freeTime,
			Integer canUseCoupon, HttpSession session);
	Code overhead(String id);
	Code getListByCourseId(String id, String orderBy, String collation);
	Code del(String id);
	Code update(String title, String course_id, String time_long,
			String video_save_url, String video_pic_url, String remark,
			Double per_cost, String video_size, Integer free, Integer freeTime,
			Integer canUseCoupon, HttpSession session, String fileData);
	Code delete(String id, HttpSession session);
	/**
	 * 播放节点统计
	 * @return
	 */
	Map nodeCount(String type,String id);
	
}
