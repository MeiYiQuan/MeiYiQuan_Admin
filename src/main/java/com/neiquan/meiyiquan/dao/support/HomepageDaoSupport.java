package com.neiquan.meiyiquan.dao.support;

import com.qc.util.Condition;
import com.qc.util.Condition.Con;
import com.qc.util.Condition.Cs;
import com.qc.util.enums.E;

/**
 * 作者：齐潮
 * 创建日期：2017年1月5日
 * 类说明：
 */
public class HomepageDaoSupport {
	private final static String hp = "`tb_homepage` as `homepage` where 1 = 1 ";
	
	/**
	 * 获取用于查询条数的sql
	 * @param conditions
	 * @return
	 */
	public final static String getCountSql(String conditions){
		String sql = ObjectSupport.getCountSql("homepage",hp + conditions);
		return sql;
	}
	/**
	 * 获取homepage所有值
	 * @return
	 */
	public final static String getTeacherSql(){
		String sql ="select `homepage`.* "
				+ " from tb_homepage homepage"
				+ " LEFT JOIN tb_teacher tea"
				+ " ON homepage.relation_id=tea.id";
		return sql;
	}
	/**
	 * 获取当前排序最大值
	 * @return
	 */
	public final static String getTopnum(){
		String sql="SELECT ifnull(MAX(h.top_num),0) as maxnum FROM tb_homepage h";
		return sql;
	}
	
	public static Cs getCs(String status){
		Cs cs = Condition.getConditions(Con.getCon("`homepage`.`status`", E.EQ, status));
		return cs;
	}
}
