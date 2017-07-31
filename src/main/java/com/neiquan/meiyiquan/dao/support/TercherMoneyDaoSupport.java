package com.neiquan.meiyiquan.dao.support;

import com.neiquan.meiyiquan.util.StringUtil;

/**
 * 作者：温尉棨
 * 创建日期：2017年2月27日
 * 类说明：
 */
public class TercherMoneyDaoSupport {

	public final static String tmList(String name){
		String and="";
		if(!StringUtil.isNullOrBlank(name)){
			and="AND t.`name`='"+name+"'";
		}
		
		String sql="SELECT "
				+ " t.id"
				+ ",IFNULL(sum(tor.teacher_achieve)-sum(ts.send_money),0) money"
				+ ",t.`name`"
				+ ",IFNULL(SUM(tor.teacher_achieve),0) zong"
				+ " FROM tb_teacher t"
				+ " LEFT JOIN tb_teacher_order tor ON tor.teacher_id=t.id" 
				+ " LEFT JOIN tb_teacher_send ts ON t.id=ts.teacher_id"
				+ " WHERE t.`status`='1' "
				+ " "+and+" "
				+ " GROUP BY t.id ORDER BY zong DESC";
		return sql;
	}
}
