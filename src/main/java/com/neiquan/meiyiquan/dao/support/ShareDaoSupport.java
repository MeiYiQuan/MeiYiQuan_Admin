package com.neiquan.meiyiquan.dao.support;

/**
 * 作者：温尉棨
 * 创建日期：2017年2月19日
 * 类说明：
 */
public class ShareDaoSupport {

	
	public final static String shareList(){
		
		String sql ="SELECT "
				+ "s.type,FROM_UNIXTIME(s.share_time/1000, '%Y-%m-%d') share_time ,u.username,u.district dis"
				+ " FROM tb_shared s "
				+ " LEFT JOIN tb_user u ON s.userId=u.id";
		return sql;
	}
}
