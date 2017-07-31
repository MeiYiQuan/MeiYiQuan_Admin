package com.neiquan.meiyiquan.dao.support;

/**
 * 作者：齐潮
 * 创建日期：2016年12月12日
 * 类说明：提供一些用于拼接字符串公用的方法
 */
public class ObjectSupport {

	/**
	 * 用于拼接查询条数的sql
	 * @param oldSql
	 * @return
	 */
	public static String getCountSql(String tableOtherName,String oldSql){
		String sql = null;
		if(tableOtherName!=null){
			sql = "select count(`" + tableOtherName + "`.`id`) as `count` from " + oldSql;
		}else{
			sql = "select count(`id`) as `count` from " + oldSql;
		}
		return sql;
	}
	
}
