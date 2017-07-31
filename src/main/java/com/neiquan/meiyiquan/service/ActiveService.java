package com.neiquan.meiyiquan.service;

import java.io.IOException;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.neiquan.meiyiquan.code.Code;

/**
 * 作者：齐潮
 * 创建日期：2017年1月13日
 * 类说明：
 */
public interface ActiveService {
	
	
	/**
	 * 根据id删除活动
	 * @param id
	 * @return
	 */
	int deleteById(String id);
	
	/**
	 * 活动列表
	 * @param kaystatus
	 * @param kaytxt
	 * @param createBegin
	 * @param createEnd
	 * @param orderBy
	 * @param collation
	 * @param status
	 * @return
	 */
	public Code getList(String page,String size,String count,String kaystatus,String kaytxt,
			String createBegin,String createEnd,String orderBy,String collation,String status,String tree );
	/**
	 * 活动收益列表
	 * @param kaystatus
	 * @param kaytxt
	 * @param createBegin
	 * @param createEnd
	 * @param orderBy
	 * @param collation
	 * @return
	 */
	public Code incomeList(String page,String size,String count,String kaystatus,String kaytxt,
			String createBegin,String createEnd,String orderBy,String collation,String tree); 

	/**
	 * 上传图片至oss
	 * @param file
	 * @return
	 */
	String updateHead(MultipartFile file ) throws IOException;
	/**
	 * 添加活动
	 * @param tital
	 * @param enroll_begin_time
	 * @param enroll_end_time
	 * @param prepare_start_time
	 * @param prepare_end_time
	 * @param activity_start_time
	 * @param activity_end_time
	 * @param show_type
	 * @param show_pic_url
	 * @param remark
	 * @param cost
	 * @param most_man
	 * @param appearance
	 * @param canUseCoupon
	 * @return
	 */
	public String saveActive(String tital,String enroll_begin_time,
			String enroll_end_time ,String prepare_start_time,String prepare_end_time,
			String activity_start_time,String activity_end_time ,
			String show_type,String show_pic_url,String remark,String cost,
			String most_man,String appearance,String canUseCoupon,String fileData,String price
			,String address,String organiser);
	/**
	 * 修改活动
	 * @param id
	 * @param tital
	 * @param enroll_begin_time
	 * @param enroll_end_time
	 * @param prepare_start_time
	 * @param prepare_end_time
	 * @param activity_start_time
	 * @param activity_end_time
	 * @param show_type
	 * @param show_pic_url
	 * @param remark
	 * @param cost
	 * @param most_man
	 * @param appearance
	 * @param canUseCoupon
	 * @return
	 */
	public String updateActive(String id,String tital,String enroll_begin_time,
			String enroll_end_time ,String prepare_start_time,String prepare_end_time,
			String activity_start_time,String activity_end_time ,
			String show_type,String show_pic_url,String remark,String cost,
			String most_man,String appearance,String canUseCoupon,String fileData,String price
			,String address,String organiser);
	/**
	 * 获取详情
	 * @param id
	 * @return
	 */
	public Map<String,Object> infoActive(String id);
	/**
	 * 获取时间详情
	 * @param id
	 * @return
	 */
	public Map<String,Object> infoActivetime(String id);
	/**
	 * 活动报名列表
	 * @param page
	 * @param size
	 * @param title
	 * @return
	 */
 
	public Code getPeoList(String page,String size,String id);
	/**
	 * 活动报名列表
	 * @param page
	 * @param size
	 * @param title
	 * @return
	 */
 
	public Code jabinList(String page,String size,String id);
	/**
	 * 活动订单列表
	 * @param page
	 * @param size
	 * @param title
	 * @return
	 */
	public Code getActiveOrder(String id,String page,String size,String title,String phone);
 
	 
	/**
	 * 活动评论列表
	 * @param page
	 * @param size
	 * @param title
	 * @return
	 */
	public Code getcomment(String page,String size,String title);
	/**
	 * 活动详情
	 * @param id
	 * @return
	 */
	public Code commentInfo(String id);
	/**
	 * 嘉宾详情
	 * @param id
	 * @return
	 */
	public Code jiabinInfo(String id);
	
 
}
