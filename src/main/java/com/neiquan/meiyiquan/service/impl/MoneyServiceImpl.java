package com.neiquan.meiyiquan.service.impl;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neiquan.meiyiquan.code.Code;
import com.neiquan.meiyiquan.dao.ObjectDao;
import com.neiquan.meiyiquan.dao.support.CouponDaoSupport;
import com.neiquan.meiyiquan.dao.support.MoneyDaoSupport;
import com.neiquan.meiyiquan.pojo.Coupon;
import com.neiquan.meiyiquan.pojo.CouponUser;
import com.neiquan.meiyiquan.pojo.CouponWillSendUser;
import com.neiquan.meiyiquan.service.MoneyService;
import com.neiquan.meiyiquan.service.PushService;
import com.neiquan.meiyiquan.util.Constant;
import com.neiquan.meiyiquan.util.ExtraSpringHibernateTemplate;
import com.neiquan.meiyiquan.util.Paging;
import com.neiquan.meiyiquan.util.Statics;
import com.neiquan.meiyiquan.util.TimeCleanUtil;
import com.qc.util.Condition.Cs;
import com.qc.util.DateFormate;

/**
 * 作者：温尉棨
 * 创建日期：2017年2月16日
 * 类说明：
 */
@Service
public class MoneyServiceImpl implements MoneyService{

	@Autowired
	private ExtraSpringHibernateTemplate esht;
	
	@Autowired
	private ObjectDao od;
	
	@Autowired
	private PushService push;
	
	/**
	 * 获取订单未扣除金额详情
	 */
	@Override
	public Map<String,Object> moneyInfo(String beginTime, String endTime, String tree, String orderNum, String zongshu,String pay_type) {
		String sql=MoneyDaoSupport.moneyInfo(beginTime, endTime, tree, orderNum, zongshu, pay_type);
		Map<String, Object> params=new HashMap();
		Map<String,Object> map=od.getObjectBySql(sql, params);
		
		return map;
	}

	/**
	 * 订单列表
	 */
	@Override
	public Code orderList(String text,String page,String size) {
		String sql=MoneyDaoSupport.orderlist(text);
		int pageindex = Integer.valueOf(page).intValue();
		int pagesize = Integer.valueOf(size).intValue();
		Paging<Map>	p= esht.createSQLQueryfindPage(sql, (pageindex-1)*pagesize, pagesize);
		return Code.init(0, null, p);
	}

	/**
	 * 获取收益列表
	 * 1 ，日2，月 1，订单2，充值
	 */
	@Override
	public Code gainsList(String beginTime, String endTime, String type, String dateType, String page, String size) {
		Paging<Map>	p=null;
		if(type.equals("1")){
			String sql=MoneyDaoSupport.gainsList(beginTime, endTime, dateType);
			int pageindex = Integer.valueOf(page).intValue();
			int pagesize = Integer.valueOf(size).intValue();
			p= esht.createSQLQueryfindPage(sql, (pageindex-1)*pagesize, pagesize);
		}
		if(type.equals("2")){
			String sql=MoneyDaoSupport.gainsios(beginTime, endTime, dateType);
			int pageindex = Integer.valueOf(page).intValue();
			int pagesize = Integer.valueOf(size).intValue();
			p= esht.createSQLQueryfindPage(sql, (pageindex-1)*pagesize, pagesize);
			
		}
		return Code.init(0, null, p);
	}

	@Override
	public Code getApplyList(Cs cs, int page, int size) {
		String selectSql = CouponDaoSupport.getApplySelect(cs.getConditions());
		String countSql = CouponDaoSupport.getApplyCount(cs.getConditions());
		Code code = od.getObjects(countSql, selectSql, cs.getParams(), page, size);
		Map<String,Object> map = code.getMapData();
		Object list = map.get("result");
		TimeCleanUtil.clean(list,"applyTime","verifyTime");
		return code;
	}

	@Override
	public Code shenhe(String id, int status,String adminId,String adminName) throws Exception {
		CouponWillSendUser will = od.getObjById(CouponWillSendUser.class, id);
		if(will==null)
			return Code.init(-1, "该申请已经不存在！");
		if(will.getUse_type()!=Statics.COUPON_WILLSEND_TYPE_WILL)
			return Code.init(-1, "该申请已经审核过，不能重复审核！请刷新页面！");
		long now = System.currentTimeMillis();
		will.setResp_admin_id(adminId);
		will.setResp_time(now);
		will.setUse_type(status);
		
		// 将对CouponWillSendUser的更新放在最后，先给用户发送优惠券，并且给出通知
		if(status==Statics.COUPON_WILLSEND_TYPE_YES){
			Coupon coupon = od.getObjById(Coupon.class, will.getCoupon_id());
			if(coupon==null)
				return Code.init(-1, "该优惠券已经不存在！");
			if(coupon.getStatus()!=Constant.YES_INT)
				return Code.init(-1, "该优惠券已经被禁用！");
			CouponUser cu = new CouponUser();
			cu.setCoupon_id(will.getCoupon_id());
			cu.setCoupon_provide_type(Statics.COUPON_PROVIDE_TYPE_ADMIN);
			cu.setCoupon_type(coupon.getCoupon_type());
			cu.setDenomination(coupon.getDenomination());
			
			long expireTime = now + (long)(coupon.getExpire_time() * 24 * 60 * 60 * 1000);
			Calendar cal = Calendar.getInstance();
			cal.setTimeInMillis(expireTime);
			cal.add(Calendar.DATE, -7);
			long warnTime = cal.getTimeInMillis();
			
			cu.setExpire_time(expireTime);
			cu.setGet_time(now);
			cu.setGet_type(-1);
			cu.setGet_type_id("本字段作废");
			if(coupon.getExpire_type()==Statics.COUPON_EXPIRE_TYPE_FOREVER){
				cu.setIsForever(Constant.YES_INT);
			}else{
				cu.setIsForever(Constant.NO_INT);
			}
			cu.setNumber(UUID.randomUUID().toString().replace("-", "").toUpperCase());
			cu.setStatus(Constant.YES_INT);
			cu.setUse_time(0L);
			cu.setWarn_time(warnTime);
			cu.setUser_id(will.getUser_id());
			
			String cuId = od.save(cu);
			
			if(cuId==null)
				return Code.init(-1, "数据库错误！");
			// 给用户发送成功，进行推送消息给该用户
			push.pushEveryOne("", "优惠券消息", Statics.PUSH_TYPE_FORTHWITH_NOTABLE, "恭喜您获得了我们赠送的优惠券~~~请注意查收哦！", null, will.getUser_id());
		}
		
		od.update(will);
		
		Map<String,Object> data = new HashMap<String,Object>();
		data.put("upTime", DateFormate.getDateFormateCH(now));
		data.put("admin", adminName);
		data.put("status", status);
		
		return Code.init(0, "审核成功！", data);
	}
	
	

}
