package com.neiquan.meiyiquan.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.neiquan.meiyiquan.code.Code;
import com.neiquan.meiyiquan.dao.ObjectDao;
import com.neiquan.meiyiquan.dao.support.CouponDaoSupport;
import com.neiquan.meiyiquan.pojo.Coupon;
import com.neiquan.meiyiquan.pojo.CouponWillSendUser;
import com.neiquan.meiyiquan.pojo.Sys;
import com.neiquan.meiyiquan.pojo.User;
import com.neiquan.meiyiquan.service.CouponService;
import com.neiquan.meiyiquan.util.Constant;
import com.neiquan.meiyiquan.util.Enums.SystemCoupons;
import com.neiquan.meiyiquan.util.Statics;
import com.neiquan.meiyiquan.util.TimeCleanUtil;
import com.qc.util.Condition;
import com.qc.util.Condition.Con;
import com.qc.util.Condition.Cs;
import com.qc.util.enums.E;

/**
 * 作者：齐潮
 * 创建日期：2017年2月18日
 * 类说明：
 */
@Service
public class CouponServiceImpl implements CouponService {

	@Autowired
	private ObjectDao od;

	@SuppressWarnings("unchecked")
	@Override
	public Code getCoupons(Cs cs, int page, int size) {
		String selectSql = CouponDaoSupport.getCouponsSql(cs.getConditions()) + cs.getOrderSql();
		String countSql = CouponDaoSupport.getCouponCountSql(cs.getConditions());
		Code code = od.getObjects(countSql, selectSql, cs.getParams(), page, size);
		Map<String,Object> map = code.getMapData();
		Object list = map.get("result");
		TimeCleanUtil.clean(list,"latest_update_time");
		return code;
	}

	@Override
	public Code saveCoupon(Coupon coupon) {
//		String maxSql = "select ifnull(max(`number`),0) as `max` from `tb_coupon`";
//		Map<String, Object> map = od.getObjectBySql(maxSql, null);
		String couponId = od.save(coupon);
		if(couponId==null)
			return Code.init(-1, "数据库错误！");
		return Code.init(0, "新增成功！");
	}

	@Override
	public Code updateCoupon(String id, Map<String, Object> settings) {
		int upResult = od.updateById(Coupon.class, id, settings);
		if(upResult!=1)
			return Code.init(-1, "数据库错误！");
		return Code.init(0, "修改成功！");
	}

	@Override
	public Code getCoupon(String id) {
		Coupon coupon = od.getObjById(Coupon.class, id);
		return Code.init(0, "", coupon);
	}

	@Override
	public Code getTasks() {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("type", Constant.SYSTEM_TYPE_COUPONS);
		params.put("status", Constant.YES_INT);
		List<Sys> syss = od.getPos(Sys.class, params, null, null);
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		if(syss!=null){
			StringBuffer sql = new StringBuffer("select * from `tb_coupon` where 1 = 1 ");
			Set<String> ids = new HashSet<String>();
			for(Sys sys:syss){
				Map<String,Integer> coupons = JSONObject.parseObject(sys.getSys_value(), Map.class);
				List<Map<String,Object>> couList = new ArrayList<Map<String,Object>>();
				if(coupons!=null&&coupons.size()>0){
					Set<String> keys = coupons.keySet();
					for(String key:keys){
						Map<String,Object> map = new HashMap<String,Object>();
						map.put("id", key);
						map.put("count", coupons.get(key));
						ids.add(key);
						couList.add(map);
					}
				}
				Map<String,Object> cc = new HashMap<String,Object>();
				SystemCoupons sc = SystemCoupons.getSystemCoupons(sys.getSys_key());
				if(sc==null){
					cc.put("title","未知任务");
				}else{
					cc.put("title", sc.getRemark());
				}
				cc.put("content",couList);
				cc.put("id", sys.getId());
				list.add(cc);
			}
			Cs cs = Condition.getConditions(Con.getCon("`id`", E.IN, ids));
			sql.append(cs.getConditions());
			List<Map<String, Object>> coups = od.getListBySql(sql.toString(), cs.getParams(), null, null);
			for(Map<String,Object> mmap:list){
				List<Map<String, Object>> clist = (List<Map<String, Object>>) mmap.get("content");
				for(Map<String,Object> cmap:clist){
					String id = cmap.get("id").toString();
					boolean b = true;
					for(Map<String, Object> cou:coups){
						if(id.equals(cou.get("id"))){
							cmap.putAll(cou);
							b = false;
							break;
						}
					}
					if(b){
						cmap.put("name", "该优惠券已经删除！");
					}
				}
			}
		}
		return Code.init(0, "", list);
	}

	@Override
	public Code sendCoupons(List<Map<String,Object>> users, String couponId, String adminId) {
		long now = System.currentTimeMillis();
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		for(Map<String,Object> user:users){
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("coupon_id", couponId);
			map.put("req_admin_id", adminId);
			map.put("req_time", now);
			map.put("use_type", Statics.COUPON_WILLSEND_TYPE_WILL);
			map.put("user_id", user.get("id"));
			list.add(map);
		}
		int result = od.saveObjects(CouponWillSendUser.class, list);
		return Code.init(0, "发送成功！");
	}
}
