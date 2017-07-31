package com.neiquan.meiyiquan.dao.support;

import com.qc.util.Condition;
import com.qc.util.Condition.Con;
import com.qc.util.Condition.Cs;
import com.qc.util.Orders.Od;
import com.qc.util.enums.E;

/**
 * 作者：齐潮
 * 创建日期：2017年3月2日
 * 类说明：用于产生有关相关推荐的sql
 */
public class HomePageSupport {

	/**
	 * 查询列表时的基语句
	 */
	private final static String S = "`tb_homepage` where 1 = 1 ";
	
	/**
	 * 获取相关推荐列表时的条件和排序信息
	 * @param type
	 * @param status
	 * @param order
	 * @return
	 */
	public final static Cs getCs(String type,String status,String order){
		
		Cs cs = Condition.getConditions(
				Con.getCon("`type`", E.EQ, type),
				Con.getCon("`status`", E.EQ, status)
				);
		
		cs.initOrder(Od.getOd("`top_num`", order));
		
		return cs;
	}
	
	/**
	 * 获得查询内容的语句
	 * @param conditions
	 * @return
	 */
	public final static String getSelect(String conditions){
		String sql = "select * from " + S + conditions;
		return sql;
	}
	
	/**
	 * 获得查询个数的语句
	 * @param conditions
	 * @return
	 */
	public final static String getCount(String conditions){
		String sql = ObjectSupport.getCountSql(null, S + conditions);
		return sql;
	}
	
}
