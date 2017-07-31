package com.neiquan.meiyiquan.service;

import com.neiquan.meiyiquan.code.Code;

/**
 * 作者：齐潮
 * 创建日期：2017年2月21日
 * 类说明：用于处理角色的业务逻辑
 */
public interface RoleService {

	/**
	 * 分页获取角色列表
	 * @param page
	 * @param size
	 * @return
	 */
	public Code getRoles(int page,int size);
	
	/**
	 * 获取所有的父级菜单，并且携带该角色是否拥有该父级菜单的操作权限
	 * @return
	 */
	public Code getRoleMenus(String roleId);
	
	/**
	 * 保存一个角色的授权菜单信息
	 * @param roleId
	 * @param menuIds
	 * @return
	 */
	public Code save(String roleId,String[] menuIds);
	
}
