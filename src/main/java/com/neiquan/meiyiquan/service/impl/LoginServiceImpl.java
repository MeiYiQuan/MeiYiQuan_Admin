package com.neiquan.meiyiquan.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neiquan.meiyiquan.code.Code;
import com.neiquan.meiyiquan.dao.ObjectDao;
import com.neiquan.meiyiquan.dao.support.LoginDaoSupport;
import com.neiquan.meiyiquan.pojo.Menu;
import com.neiquan.meiyiquan.service.LoginService;
import com.neiquan.meiyiquan.util.Sql;


/**
 * 作者：齐潮
 * 创建日期：2016年12月8日
 * 类说明：
 */
@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private ObjectDao od;
	
	@Override
	public Code login(String loginName, String password) {
		Sql sql = LoginDaoSupport.getLoginUser(loginName, password);
		Map<String,Object> map = od.getObjectBySql(sql.getSql(), sql.getParams());
		if(map==null)
			return Code.init(-1, "账号或者密码输入错误！");
		if(map.get("rolId")==null)
			return Code.init(-1, "该角色已经不存在！");
		Object menusObj = map.get("menuIds");
		if(menusObj==null)
			return Code.init(-1, "该管理员没有可用菜单！");
		String[] menus = menusObj.toString().split(",");
		if(menus==null||menus.length<1)
			return Code.init(-1, "该管理员没有可用菜单！");
		List<String> usermenuIds = new ArrayList<String>();
		for(String menuId:menus){
			usermenuIds.add(menuId);
		}
		List<Map<String, Object>> allmenus = od.getPosForMap(Menu.class, null, null, null, Order.asc("createTime"));
		map.put("menuIds", usermenuIds);
		map.put("allMenus", allmenus);
		return Code.init(1, "成功登陆！", map);
	}

}
