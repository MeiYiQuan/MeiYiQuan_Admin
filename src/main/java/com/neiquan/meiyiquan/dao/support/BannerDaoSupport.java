package com.neiquan.meiyiquan.dao.support;

import com.qc.util.Condition;
import com.qc.util.Condition.Con;
import com.qc.util.Condition.Cs;
import com.qc.util.DateFormate;
import com.qc.util.Orders.Od;
import com.qc.util.enums.E;


/**
 * 作者：齐潮
 * 创建日期：2016年12月12日
 * 类说明：用于拼接关于banner的sql
 */
public class BannerDaoSupport {

	private final static String se = "`tb_banner` as `banner` where 1 = 1 ";
	
	/**
	 * 获得Cs
	 * @param bannerType
	 * @param status
	 * @param createBegin
	 * @param createEnd
	 * @param updateBegin
	 * @param updateEnd
	 * @return
	 */
	public static Cs getCs(String orderNum,String createTime,String updateTime,String bannerType,String status,String createBegin,String createEnd,
			String updateBegin,String updateEnd,String type){
		Cs cs = Condition.getConditions(Con.getCon("`banner`.`jump_type`", E.EQ, bannerType),
					Con.getCon("`banner`.`status`", E.EQ, status),
					Con.getCon("`banner`.`showtype`", E.EQ, type),
					Con.getCon("`banner`.`create_time`", E.GE, (createBegin==null||createBegin.trim().equals(""))?null:DateFormate.getDateLong(createBegin + " 00:00:00")),
					Con.getCon("`banner`.`create_time`", E.LT, (createEnd==null||createEnd.trim().equals(""))?null:DateFormate.getDateLong(createEnd + " 00:00:00")),
					Con.getCon("`banner`.`update_time`", E.GE, (updateBegin==null||updateBegin.trim().equals(""))?null:DateFormate.getDateLong(updateBegin + " 00:00:00")),
					Con.getCon("`banner`.`update_time`", E.LT, (updateEnd==null||updateEnd.trim().equals(""))?null:DateFormate.getDateLong(updateEnd + " 00:00:00"))
					);
		cs.initOrder(Od.getOd("`banner`.`order_num`", orderNum),
							Od.getOd("`banner`.`create_time`", createTime),
							Od.getOd("`banner`.`update_time`", updateTime));
		return cs;
	}
	
	/**
	 * 获取用于查询首页条数的sql
	 * @param conditions
	 * @return
	 */
	public static String getCountSql(String conditions){
		String sql = ObjectSupport.getCountSql("banner",se + conditions);
		return sql;
	}
	
	/**
	 * 获取用于查询首页结果集的sql
	 * @param conditions
	 * @return
	 */
	public static String getResultSql(String conditions){
		String sql = "select `banner`.*,"
					+ "if(`banner`.`jump_type`=0,(select `course`.`title` from `tb_course` as `course` where `course`.`id` = `banner`.`jump_id` limit 0,1)," 
					+ "	  if(`banner`.`jump_type`=1,(select `teac`.`name` from `tb_teacher` as `teac` where `teac`.`id` = `banner`.`jump_id` limit 0,1),"
					+ "		if(`banner`.`jump_type`=2,(select `activ`.`title` from `tb_activity` as `activ` where `activ`.`id` = `banner`.`jump_id` limit 0,1),'未知类型'))"
					+ ") as `jump_type_name` "
					+ "from " 
					+ se + conditions ;
		return sql;
	}
	/**
	 * 获取用于查询首页条数的sql
	 * @param conditions
	 * @return
	 */
	public static String getFCountSql(String conditions){
		String sql = ObjectSupport.getCountSql("banner",se +" and showtype= 2 "+ conditions);
		return sql;
	}
	
	/**
	 * 获取用于查询首页结果集的sql
	 * @param conditions
	 * @return
	 */
	public static String getFResultSql(String conditions){
		String sql = "select `banner`.*,"
					+ "if(`banner`.`jump_type`=0,(select `video`.`title` from `tb_video` as `video` where `video`.`id` = `banner`.`jump_id` limit 0,1)," 
					+ "	  if(`banner`.`jump_type`=1,(select `teac`.`name` from `tb_teacher` as `teac` where `teac`.`id` = `banner`.`jump_id` limit 0,1),"
					+ "		if(`banner`.`jump_type`=2,(select `activ`.`title` from `tb_activity` as `activ` where `activ`.`id` = `banner`.`jump_id` limit 0,1),'未知类型'))"
					+ ") as `jump_type_name` "
					+ "from " 
					+ se+" and showtype= 2 " +conditions+"ORDER BY banner.order_num desc,banner.create_time asc ";
		return sql;
	}
	/**
	 * 获取当前排序最大值
	 * @return
	 */
	public static String getMax(){
		String sql="SELECT MAX(b.order_num) as maxnum FROM tb_banner b";
		return sql;
	}
}
