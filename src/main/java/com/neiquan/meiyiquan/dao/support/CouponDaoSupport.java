package com.neiquan.meiyiquan.dao.support;

import com.neiquan.meiyiquan.util.Constant;
import com.qc.util.Condition;
import com.qc.util.DateFormate;
import com.qc.util.Condition.Con;
import com.qc.util.Condition.Cs;
import com.qc.util.Orders.Od;
import com.qc.util.enums.E;

/**
 * 作者：齐潮
 * 创建日期：2017年2月18日
 * 类说明：用于产生有关优惠券的sql
 */
public class CouponDaoSupport {
	
	
	/**
	 * 在查询优惠券审核列表时，获得条件cs
	 * @param applyLoginName
	 * @param aproveLoginName
	 * @param couponName
	 * @param applyTimeBegin
	 * @param applyTimeEnd
	 * @param verifyTimeBegin
	 * @param verifyTimeEnd
	 * @param type
	 * @param status
	 * @return
	 */
	public final static Cs getApplyCs(String applyLoginName,String aproveLoginName,
			String couponName,String applyTimeBegin,String applyTimeEnd,String verifyTimeBegin,
			String verifyTimeEnd,String type,String status){
		
		Cs cs = Condition.getConditions(
				Con.getCon("`table`.`applyName`", E.LIKE, applyLoginName),
				Con.getCon("`table`.`verifyName`", E.LIKE, aproveLoginName),
				Con.getCon("`table`.`couponName`", E.LIKE, couponName),
				Con.getCon("`table`.`applyTime`", E.GE, (applyTimeBegin==null||applyTimeBegin.trim().equals(""))?null:DateFormate.getDateLong(applyTimeBegin + " 00:00:00")),
				Con.getCon("`table`.`applyTime`", E.LT, (applyTimeEnd==null||applyTimeEnd.trim().equals(""))?null:DateFormate.getDateLong(applyTimeEnd + " 00:00:00")),
				Con.getCon("`table`.`verifyTime`", E.GE, (verifyTimeBegin==null||verifyTimeBegin.trim().equals(""))?null:DateFormate.getDateLong(verifyTimeBegin + " 00:00:00")),
				Con.getCon("`table`.`verifyTime`", E.LT, (verifyTimeEnd==null||verifyTimeEnd.trim().equals(""))?null:DateFormate.getDateLong(verifyTimeEnd + " 00:00:00")),
				Con.getCon("`table`.`couponType`", E.EQ, type),
				Con.getCon("`table`.`status`", E.EQ, status)
				);
		
		return cs;
	}
	
	/**
	 * 获得优惠券券申请个数的sql语句
	 * @param conditions
	 * @return
	 */
	public final static String getApplyCount(String conditions){
		String base = getApplySupport() + conditions;
		String sql = ObjectSupport.getCountSql("table", base);
		return sql;
	}
	
	/**
	 * 获得优惠券券申请列表的sql语句
	 * @param conditions
	 * @return
	 */
	public final static String getApplySelect(String conditions){
		String sql = "select `table`.* from " + getApplySupport() + conditions;
		return sql;
	}
	
	/**
	 * 获得优惠券审核列表的基查询语句
	 * @return
	 */
	private final static String getApplySupport(){
		String sql = "(select "
							+ "`will`.`id` as `id`,"
							+ "`coupon`.`name` as `couponName`,"
							+ "`coupon`.`coupon_type` as `couponType`,"
							+ "`coupon`.`denomination` as `couponPrice`,"
							+ "`user`.`username` as `userName`,"
							+ "`applyAdmin`.`loginname` as `applyName`,"
							+ "`will`.`req_time` as `applyTime`,"
							+ "ifnull(`verifyAdmin`.`loginname`,'无') as `verifyName`,"
							+ "`will`.`resp_time` as `verifyTime`,"
							+ "`will`.`use_type` as `status` "
						+ "from `tb_coupon_willsend_user` as `will` "
						+ "LEFT JOIN `tb_coupon` as `coupon` on `coupon`.`id` = `will`.`coupon_id` "
						+ "LEFT JOIN `tb_admin` as `applyAdmin` on `applyAdmin`.`id` = `will`.`req_admin_id` "
						+ "LEFT JOIN `tb_admin` as `verifyAdmin` on `verifyAdmin`.`id` = `will`.`resp_admin_id` "
						+ "LEFT JOIN `tb_user` as `user` on `user`.`id` = `will`.`user_id` "
						+ "group by `will`.`id` "
						+ "order by `will`.`req_time` desc) as `table` "
						+ "where 1 = 1 ";
		return sql;
	}
	
	/**
	 * 在发送优惠券时用于查询用户集合
	 * @param conditions
	 * @return
	 */
	public final static String getSentUsers(String conditions){
		String sql = "select * from `tb_user` where 1 = 1 " + conditions;
		return sql;
	}
	
	/**
	 * 在发送优惠券时用于查询人数
	 * @param conditions
	 * @return
	 */
	public final static String getSendUserCount(String conditions){
		String sql = "select count(`id`) as `count` from `tb_user` where 1 = 1 " + conditions;
		return sql;
	}
	
	/**
	 * 在给用户发送优惠券需要查询人数和用户列表时需要用到
	 * @param phone
	 * @param ageid
	 * @param zodicid
	 * @param sexid
	 * @param jobid
	 * @param tree
	 * @param type
	 * @return
	 */
	public final static Cs getSendUserCs(String phone,String ageid,String zodicid,
			String sexid,String jobid,String tree ,String type){
		Cs cs = Condition.getConditions(
				Con.getCon("`phone`", E.LIKE, phone),
				Con.getCon("`ageId`", E.EQ, ageid),
				Con.getCon("`zodiacIndex`", E.EQ, zodicid),
				Con.getCon("`gender`", E.EQ, sexid),
				Con.getCon("`jobId`", E.EQ, jobid),
				Con.getCon("`district`", E.LIKE, tree),
				Con.getCon("`user_type`", E.EQ, type)
				);
		return cs;
	}
	
	/**
	 * 优惠券查询时，获取条件和排序信息
	 * @param type
	 * @param lastUpBegin
	 * @param lastUpEnd
	 * @param sentCount
	 * @param useCount
	 * @param noUseExpiredCount
	 * @param noUseNoExpiredCount
	 * @return
	 */
	public final static Cs getCouponCs(Integer type,Integer status,String lastUpBegin,String lastUpEnd,
			String sentCount,String useCount,String noUseExpiredCount,String noUseNoExpiredCount){
		Cs cs = Condition.getConditions(
				Con.getCon("`coupon`.`coupon_type`", E.EQ, type),
				Con.getCon("`coupon`.`status`", E.EQ, status),
				Con.getCon("`coupon`.`latest_update_time`", E.GE, (lastUpBegin==null||lastUpBegin.trim().equals(""))?null:DateFormate.getDateLong(lastUpBegin + " 00:00:00")),
				Con.getCon("`coupon`.`latest_update_time`", E.LT, (lastUpEnd==null||lastUpEnd.trim().equals(""))?null:DateFormate.getDateLong(lastUpEnd + " 00:00:00"))
				);
		cs.initOrder(
				Od.getOd("`other`.`sentCount`", sentCount),
				Od.getOd("`other`.`useCount`", useCount),
				Od.getOd("`other`.`noUseExpiredCount`", noUseExpiredCount),
				Od.getOd("`other`.`noUseNoExpiredCount`", noUseNoExpiredCount),
				Od.getOd("`coupon`.`latest_update_time`", "2")
				);
		
		return cs;
	}
	
	/**
	 * 优惠券获得用于查询信息数量的sql
	 * @param conditions
	 * @return
	 */
	public final static String getCouponCountSql(String conditions){
		String sql = ObjectSupport.getCountSql("coupon", getS() + conditions);
		return sql;
	}
	
	/**
	 * 优惠券获得用于查询结果集的sql语句
	 * @param conditions
	 * @return
	 */
	public final static String getCouponsSql(String conditions){
		String sql = "select `coupon`.*,"
							+ "ifnull(`admin`.`loginname`,'无') as `admin`,"
							+ "`other`.`sentCount` as `sentCount`,"
							+ "`other`.`useCount` as `useCount`,"
							+ "`other`.`noUseExpiredCount` as `noUseExpiredCount`,"
							+ "`other`.`noUseNoExpiredCount` as `noUseNoExpiredCount` "
						+ "from " + getS() + conditions;
		return sql;
	}
	
	
	/**
	 * 获得条件查询coupon结果集的原始sql语句
	 * @return
	 */
	private final static String getS(){
		long now = System.currentTimeMillis();
		String sql = "`tb_coupon` as `coupon` "
					+ "LEFT JOIN "
						+ "("
							+ "select `cou`.`id` as `couId`,"
								+ "(select ifnull(count(`id`),0) from `tb_coupon_user` where `coupon_id` = `cou`.`id`) as `sentCount`,"
								+ "(select ifnull(count(`id`),0) from `tb_coupon_user` where `coupon_id` = `cou`.`id` and `status` = " + Constant.NO_INT + " and `use_type_id` is not null and `use_type_id` != '') as `useCount`,"
								+ "(select ifnull(count(`id`),0) from `tb_coupon_user` where `coupon_id` = `cou`.`id` and (`use_type_id` is null or `use_type_id` = '') and `expire_time` <= " + now + ") as `noUseExpiredCount`,"
								+ "(select ifnull(count(`id`),0) from `tb_coupon_user` where `coupon_id` = `cou`.`id` and (`use_type_id` is null or `use_type_id` = '') and `expire_time` > " + now + ") as `noUseNoExpiredCount` "
							+ "from `tb_coupon` as `cou`"
						+ ") as `other` on `other`.`couId` = `coupon`.`id` "
						+ "LEFT JOIN `tb_admin` as `admin` on `admin`.`id` = `coupon`.`admin_id` "
					+ "where 1 = 1 ";
		return sql;
	}
}
