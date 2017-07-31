package com.neiquan.meiyiquan.dao.support;

import java.util.HashMap;
import java.util.Map;

import com.neiquan.meiyiquan.util.Sql;


/**
 * 作者：齐潮
 * 创建日期：2016年12月8日
 * 类说明：用于处理与登录有关的字符串拼接工作
 */
public class LoginDaoSupport {

	/**
	 * 通过账号和密码来获取一个Sql信息
	 * @param loginname
	 * @param password
	 * @return
	 */
	public static Sql getLoginUser(String loginname,String password){
		String sql = "select `admin`.`id` as `id`,`admin`.`name` as `name`,`admin`.`rolId` as `rolId`,`admin`.`headUrl` as `headUrl`,`admin`.`loginname` as `loginname`,group_concat(`admin`.`menuId`) as `menuIds` " + 
						"from " + 
						"(" + 
							"select `admin`.`id` as `id`,`role`.`id` as `rolId`,`role`.`roleName` as `name`,`rol_me`.`menuId` as `menuId`,`admin`.`headUrl` as `headUrl`,`admin`.`loginname` as `loginname` " + 
							"from `tb_admin` as `admin` " + 
							"LEFT JOIN `tb_role` as `role` on `role`.`id` = `admin`.`roleId` " + 
							"LEFT JOIN `tb_role_menu` as `rol_me` on `rol_me`.`roleId` = `role`.`id` " + 
							"LEFT JOIN `tb_menu` as `menu` on `menu`.`id` = `rol_me`.`menuId` " + 
							"where `admin`.`loginname` = :loginname and `admin`.`password` = :password " + 
							"GROUP BY `rol_me`.`menuId`" + 
						") as `admin` " + 
						"group by `admin`.`id`";
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("loginname", loginname);
		params.put("password", password);
		return Sql.get(sql, params);
	}
}
