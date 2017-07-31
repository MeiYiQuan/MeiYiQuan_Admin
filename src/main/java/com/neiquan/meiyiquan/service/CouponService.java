package com.neiquan.meiyiquan.service;

import java.util.List;
import java.util.Map;

import com.neiquan.meiyiquan.code.Code;
import com.neiquan.meiyiquan.pojo.Coupon;
import com.neiquan.meiyiquan.pojo.User;
import com.qc.util.Condition.Cs;

/**
 * 作者：齐潮
 * 创建日期：2017年2月18日
 * 类说明：处理有关优惠券的业务逻辑
 */
public interface CouponService {

	/**
	 * 条件，分页获取优惠券集合
	 * @param cs
	 * @param page
	 * @param size
	 * @return
	 */
	public Code getCoupons(Cs cs, int page, int size);
	
	/**
	 * 添加一个优惠券
	 * @param coupon
	 * @return
	 */
	public Code saveCoupon(Coupon coupon);
	
	/**
	 * 编辑一个优惠券
	 * @param id
	 * @param settings
	 * @return
	 */
	public Code updateCoupon(String id,Map<String,Object> settings);
	
	/**
	 * 获取一个优惠券详情
	 * @param id
	 * @return
	 */
	public Code getCoupon(String id);
	
	/**
	 * 获得所有的系统自动发放优惠券的规则信息
	 * @return
	 */
	public Code getTasks();
	
	
	/**
	 * 给指定的一些用户发送优惠券
	 * @param users
	 * @param couponId
	 * @param adminId
	 * @return
	 */
	public Code sendCoupons(List<Map<String,Object>> users,String couponId, String adminId);
	
}
