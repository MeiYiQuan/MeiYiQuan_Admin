package com.neiquan.meiyiquan.service;

import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;

import com.neiquan.meiyiquan.code.Code;
import com.neiquan.meiyiquan.pojo.Teacher;
/**
 * 作者：刘丹
 * 创建日期：2017年1月13日
 * 类说明：讲师
 */
public interface TeacherService {

	Code upStatus(String id, String status, HttpSession session);

	
	/**
	 * 修改讲师身价
	 * @param id
	 * @param worth
	 * @param session
	 * @return
	 */
	Code upWorth(String id, String worth, HttpSession session);
	/**
	 * 讲师查询
	 * @param keywords
	 * @param status
	 * @param pageIndex
	 * @param pageSize
	 * @param timeStar
	 * @param timeEnd
	 * @param orderBy
	 * @param collation
	 * @return
	 */
	Code getTeacherList(String keywords, String status, Integer pageIndex,
			Integer pageSize, String timeStar, String timeEnd, String orderBy,
			String collation, String tree);


	Code commnet(String id, Integer firstResult, Integer maxResults,
			HttpSession session);



	Code verPhone(Long phone);


	Code getTeacherById(String id);


	Code add(Long phone, String password, String username, String remark,
			HttpSession session); 
	/**
	 * 讲师统计
	 * @param order
	 * @return
	 */
	public Code countTeacher(String order);



	Code add(Long phone, String password, String username, String remark,
			HttpSession session, String tree, String first_word,
			String head_url, String job,String percent,String make_type,String make_account
			,String top_type,String top_pic_url,String fileData);


	Code update(String id, String username, String remark, HttpSession session,
			String tree, String first_word, String head_url, String job,String percent,String make_type,String make_account
			,String top_type,String top_pic_url,String fileData);
 

	
}
