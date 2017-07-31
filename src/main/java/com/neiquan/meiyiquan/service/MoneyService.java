package com.neiquan.meiyiquan.service;

import java.util.Map;

import com.neiquan.meiyiquan.code.Code;
import com.qc.util.Condition.Cs;

/**
 * 作者：温尉棨
 * 创建日期：2017年2月16日
 * 类说明：
 */
public interface MoneyService {
	
	/**
	 * 获取订单未扣除金额详情
	 * @param beginTime
	 * @param endTime
	 * @param tree
	 * @param orderNum
	 * @param zongshu
	 * @return
	 */
	public Map<String,Object> moneyInfo(String beginTime,String endTime,String tree,String orderNum,String zongshu,String pay_type);
    /**
     * 订单列表
     * @param text
     * @param page
     * @param size
     * @return
     */
	public Code orderList(String text,String page,String size);
	/**
	 * 收益列表
	 * @param beginTime
	 * @param endTime
	 * @param type
	 * @param dateType
	 * @param page
	 * @param size
	 * @return
	 */
	public Code gainsList(String beginTime,String endTime,String type,String dateType,String page,String size);
	
	/**
	 * 分页条件获取优惠券发放审核列表
	 * @param cs
	 * @param page
	 * @param size
	 * @return
	 */
	public Code getApplyList(Cs cs,int page,int size);
	
	/**
	 * 审核操作
	 * @param id
	 * @param status
	 * @param adminId
	 * @param adminName
	 * @return
	 * @throws Exception 
	 */
	public Code shenhe(String id,int status,String adminId,String adminName) throws Exception;
}
