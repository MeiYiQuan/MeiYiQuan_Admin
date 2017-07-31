package com.neiquan.meiyiquan.dao.support;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.neiquan.meiyiquan.util.Constant;
import com.neiquan.meiyiquan.util.Sql;
import com.neiquan.meiyiquan.util.Statics;
import com.qc.util.Condition;
import com.qc.util.Condition.Con;
import com.qc.util.Condition.Cs;
import com.qc.util.enums.E;

/**
 * 作者：齐潮
 * 创建日期：2017年2月21日
 * 类说明：用于产生角色相关的sql
 */
public class RoleDaoSupport {

	/**
	 * 获得角色个数
	 * @return
	 */
	public final static String getRoleCount(){
		String sql = ObjectSupport.getCountSql(null, "`tb_role`");
		return sql;
	}
	
	/**
	 * 获得所有的角色
	 * @return
	 */
	public final static String getRoles(){
		String sql = "select * from `tb_role`";
		return sql;
	}
	
	/**
	 * 获得所有的一级菜单，并且有当前管理员可以访问的所有菜单
	 * @param roleId
	 * @return
	 */
	public final static Sql getRoleMenus(String roleId){
		String sql = "select `menu`.*,if(`rm`.`id` is null," + Constant.NO_INT + "," + Constant.YES_INT + ") as `adminIsHave` "
						+ "from `tb_menu` as `menu` "
						+ "LEFT JOIN `tb_role_menu` as `rm` on `rm`.`menuId` = `menu`.`id` and `rm`.`roleId` = :roleId "
						+ "where `menu`.`parentId` = '" + Statics.TOPMENU_PARENTID + "' "
						+ "GROUP BY `menu`.`id` "
						+ "ORDER BY `menu`.`createTime` ASC";
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("roleId", roleId);
		Sql s = Sql.get(sql, params);
		return s;
	}
	
	/**
	 * 通过传进来的父级menuId数组，来获取其下所有的子级菜单，包括当前的父级菜单。
	 * 使用前要保证menuIds不是null并且长度大于0
	 * @param menuIds
	 * @return
	 */
	public final static Sql getMenus(String[] menuIds){
		Set<String> ids = new HashSet<String>();
		for(String id:menuIds){
			ids.add(id);
		}
		Cs cs = Condition.getConditions(Con.getCon("`id`", E.IN, ids),Con.getCon("`parentId`", E.IN, ids));
		String addStr = cs.getConditions().substring(4, cs.getConditions().length()).replace(") and `parentId`", ") or `parentId`");
		String sql = "select * from `tb_menu` where " + addStr;
		Sql s = Sql.get(sql, cs.getParams());
		return s;
	}
	
}
