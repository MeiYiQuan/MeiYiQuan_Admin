package com.neiquan.meiyiquan.service;

import javax.servlet.http.HttpSession;

import com.neiquan.meiyiquan.code.Code;
import com.neiquan.meiyiquan.pojo.Course;
/**
 * 作者：刘丹
 * 创建日期：2017年1月7日
 * 类说明：积分
 */
public interface PointService {
	
	/**
	 *  积分查询
	 * @param cs
	 * @return
	 */
	 Code getPointList();

	Code upDate(String id, String str, HttpSession session);
	
}
