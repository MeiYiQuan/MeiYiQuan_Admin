package com.neiquan.meiyiquan.dao.support;

import com.neiquan.meiyiquan.util.Sql;

/**
 * 作者：齐潮
 * 创建日期：2016年12月12日
 * 类说明：
 */
public class GuidePicDaoSupport {

	/**
	 * 获取所有的引导图
	 * @return
	 */
	public static Sql getAll(){
		String sql = "select * from `tb_guidpic` where `inde` in (1,2,3) order by `inde` asc ";
		Sql s = Sql.get(sql, null);
		return s;
	}
}
