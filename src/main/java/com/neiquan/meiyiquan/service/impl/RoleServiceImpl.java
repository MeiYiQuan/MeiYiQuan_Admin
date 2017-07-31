package com.neiquan.meiyiquan.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neiquan.meiyiquan.code.Code;
import com.neiquan.meiyiquan.dao.ObjectDao;
import com.neiquan.meiyiquan.dao.support.RoleDaoSupport;
import com.neiquan.meiyiquan.pojo.Role;
import com.neiquan.meiyiquan.pojo.RoleToMenu;
import com.neiquan.meiyiquan.service.RoleService;
import com.neiquan.meiyiquan.util.Sql;

/**
 * 作者：齐潮
 * 创建日期：2017年2月21日
 * 类说明：
 */
@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private ObjectDao od;
	
	@Override
	public Code getRoles(int page, int size) {
		Code code = od.getObjects(RoleDaoSupport.getRoleCount(), RoleDaoSupport.getRoles(), null, page, size);
		return code;
	}

	@Override
	public Code getRoleMenus(String roleId) {
		Role role = od.getObjById(Role.class, roleId);
		if(role==null)
			return Code.init(-1, "该角色已经不存在！");
		Sql sql = RoleDaoSupport.getRoleMenus(roleId);
		List<Map<String, Object>> menus = od.getListBySql(sql.getSql(), sql.getParams(), null, null);
		if(menus==null)
			return Code.init(-1, "菜单列表为空！");
		Map<String,Object> data = new HashMap<String,Object>();
		data.put("menus", menus);
		data.put("role", role);
		return Code.init(0, "", data);
	}

	@Override
	public Code save(String roleId, String[] menuIds) {
		Role role = od.getObjById(Role.class, roleId);
		if(role==null)
			return Code.init(-1, "该角色已经不存在！");
		Map<String,Object> conditions = new HashMap<String,Object>();
		conditions.put("roleId", roleId);
		od.delete(RoleToMenu.class, conditions);
		if(menuIds==null||menuIds.length<1)
			return Code.init(0, "更新成功！已经去除该角色所有菜单！");
		Sql sql = RoleDaoSupport.getMenus(menuIds);
		List<Map<String, Object>> list = od.getListBySql(sql.getSql(), sql.getParams(), null, null);
		List<Map<String, Object>> rms = new ArrayList<Map<String, Object>>();
		long now = System.currentTimeMillis();
		for(Map<String, Object> menu:list){
			Map<String, Object> rm = new HashMap<String,Object>();
			rm.put("roleId", roleId);
			rm.put("menuId", menu.get("id"));
			rm.put("createTime", now);
			rms.add(rm);
		}
		int result = od.saveObjects(RoleToMenu.class, rms);
		if(result!=rms.size())
			return Code.init(-1, "数据库错误！");
		return Code.init(0, "更新成功！");
	}

}
