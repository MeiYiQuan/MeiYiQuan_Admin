package com.neiquan.meiyiquan.service;

import com.neiquan.meiyiquan.code.Code;
/**
 * 作者：刘丹
 * 创建日期：2017年2月9日
 * 类说明：订单管理
 */
public interface OrderService {
	/**
	 * 查询
	 * 
	 * @param cs
	 * @return
	 */
	Code getList(String keywords,String status ,Integer pageIndex,Integer pageSize,
			String timeStar,String timeEnd ,String orderBy,String collation);
 
	/**
	 * 订单
	 * 
	 * @param id
	 * @return
	 */
	Code getOrder(String id);
	/**
	 * 课程订单
	 * @param id
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	Code getListByVideoId(String id,Integer pageIndex,Integer pageSize);
}
