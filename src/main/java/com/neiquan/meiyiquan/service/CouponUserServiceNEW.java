package com.neiquan.meiyiquan.service;

/**
 * 作者：温尉棨
 * 创建日期：2017年2月18日
 * 类说明：
 */
public interface CouponUserServiceNEW {

	public void sendForUser(String couponid ,long now,String userId,String pushTitle,String pushContent);
}
