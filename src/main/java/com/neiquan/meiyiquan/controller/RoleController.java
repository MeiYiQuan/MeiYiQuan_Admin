package com.neiquan.meiyiquan.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.neiquan.meiyiquan.code.Code;
import com.neiquan.meiyiquan.dao.ObjectDao;
import com.neiquan.meiyiquan.pojo.Role;
import com.neiquan.meiyiquan.service.RoleService;
import com.neiquan.meiyiquan.util.Statics;
import com.qc.util.tag.QcPageType;

/**
 * 作者：齐潮
 * 创建日期：2017年2月21日
 * 类说明：处理有关角色的请求
 */
@Controller
@RequestMapping(value="role")
public class RoleController {
	
	@Autowired
	private ObjectDao od;
	
	@Resource(name="qcpagetype")
	private QcPageType pageType;
	
	@Autowired
	private RoleService rs;
	
	/**
	 * 保存对一个角色菜单的授权
	 * @param roleId
	 * @param select
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="save",method=RequestMethod.POST)
	public Code save(String roleId,String[] select){
		Code code = rs.save(roleId, select);
		return code;
	}
	
	/**
	 * 将要给某个角色设置菜单
	 * @return
	 */
	@RequestMapping(value="sendRoleMenus")
	public ModelAndView sendRoleMenus(String id){
		ModelAndView mav = null;
		Code code = rs.getRoleMenus(id);
		if(code.getCode()!=0){
			mav = getRoles();
			mav.addObject("message", code.getMessage());
			return mav;
		}
		mav = new ModelAndView();
		mav.addObject("data", code.getData());
		mav.addObject("roleId", id);
		mav.setViewName("public/role/role_menus");
		return mav;
	}
	
	/**
	 * 通过左侧导航栏进入角色列表
	 * @return
	 */
	@RequestMapping(value="getRoles")
	public ModelAndView getRoles(){
		ModelAndView mav = toAll(1, Statics.EACHROWS_DEFAULT);
		mav.setViewName("public/role/role_list");
		return mav;
	}
	
	/**
	 * 通过分页和刷新进入角色列表
	 * @return
	 */
	@RequestMapping(value="getRolesList")
	public ModelAndView getRolesList(int page,int size){
		ModelAndView mav = toAll(page, size);
		mav.setViewName("public/role/role_list");
		return mav;
	}
	
	/**
	 * 添加角色
	 * @param name
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="addRole",method=RequestMethod.POST)
	public Code upImage(String name){
		if(name==null||name.trim().equals(""))
			return Code.init(-1, "角色名称不能为空！");
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("roleName", name);
		Role role = od.getObjByParams(Role.class, params);
		if(role!=null)
			return Code.init(-1, "该角色名称已经被占用！");
		role = new Role();
		role.setRoleName(name);
		String roleId = od.save(role);
		if(roleId==null)
			return Code.init(-1, "数据库错误！");
		return Code.init(0, "新增成功！请刷新页面！");
	}
	
	/**
	 * 删除角色
	 * @param name
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="deleteRole",method=RequestMethod.POST)
	public Code deleteRole(String id){
		if(id==null||id.trim().equals(""))
			return Code.init(-1, "非法操作！");
		int result = od.deleteById(Role.class, id);
		if(result!=1)
			return Code.init(-1, "删除失败！请稍后再试！");
		return Code.init(1, "删除成功！请刷新页面！");
	}
	
	/**
	 * 对获取角色列表的统一处理方法
	 * @param page
	 * @param size
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private ModelAndView toAll(int page,int size){
		Code code = rs.getRoles(page, size);
		ModelAndView mav = new ModelAndView();
		mav.addAllObjects(code.getMapData());
		mav.addObject("pageType", pageType);
		return mav;
	}
	
}
