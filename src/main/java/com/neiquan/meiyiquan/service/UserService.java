package com.neiquan.meiyiquan.service;

import javax.servlet.http.HttpSession;

import com.neiquan.meiyiquan.code.Code;
import com.neiquan.meiyiquan.pojo.User;
/**
 * 作者：刘丹
 * 创建日期：2017年1月7日
 * 类说明：用户管理
 */
public interface UserService {
	/**
	 * 查询
	 * 
	 * @param cs
	 * @return
	 */
	Code getList(String keywords,String status ,Integer pageIndex,Integer pageSize,
			String timeStar,String timeEnd ,String orderBy,String collation,String tree,
			String  zodiac, String gender, String  user_state,String job);

	/**
	 * 更新状态
	 * 
	 * @param id
	 * @param user_state
	 * @param session
	 * @return
	 */
	Code upDate(String id, String user_state, HttpSession session);
	/**
	 * 更新状态
	 * 
	 * @param id
	 * @return
	 */
	User getUserById(String id);
	/**
	 * 用户订单
	 * 
	 * @param id
	 * @return
	 */
	Code getUserOrder(String id);
	

	Code getSurveyList(String keywords, String user_type, Integer pageIndex,
			Integer pageSize, String timeStar, String timeEnd, String orderBy,
			String collation, String district, String zodiac, String gender,
			String user_state, String job);

	

	Code getUserSurvey(String id, Integer pageIndex, Integer pageSize,
			String orderBy, String collation);
	
	Code addUserSurvey(String id,String remark, HttpSession session);

	Code upComment(String id, Integer status, HttpSession session);

	Code verPhone(Long phone);
	/**
	 * 会员统计
	 * @return
	 */
	public Code userCount(String orderby);
}
