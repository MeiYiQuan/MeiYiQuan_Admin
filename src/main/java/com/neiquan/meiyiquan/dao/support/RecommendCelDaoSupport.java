package com.neiquan.meiyiquan.dao.support;

import com.qc.util.Condition;
import com.qc.util.Condition.Con;
import com.qc.util.Condition.Cs;
import com.qc.util.enums.E;

/**
 * 作者：温尉棨
 * 创建日期：2017年1月4日
 * 类说明：
 * 用于拼接teacher的sql内容
 */
public class RecommendCelDaoSupport {
	
	private final static String se = "`tb_teacher` as `teacher` where 1 = 1 ";
	
	/**
	 * 获取用于查询条数的sql
	 * @param conditions
	 * @return
	 */
	public final static String getCountSql(String conditions){
		String sql = ObjectSupport.getCountSql("teacher",se + conditions);
		return sql;
	}
	/**
	 * 
	 * @return
	 */
	public final static String getTeacherSql(){
		String sql ="select `teacher`.* from "+se+"and `teacher`.`put_home`=2";
		return sql;
	}
	
	public static Cs getCs(int put_home){
		Cs cs = Condition.getConditions(Con.getCon("`teacher`.`put_home`", E.EQ, put_home));
		return cs;
	}
}
