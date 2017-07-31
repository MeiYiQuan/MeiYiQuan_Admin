package com.neiquan.meiyiquan.tag;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 * 作者：齐潮
 * 创建日期：2016年12月8日
 * 类说明：用于处理根据权限去显示父级子级菜单
 */
public class MenuTag extends SimpleTagSupport {

	/**
	 * 用来表示用作选择的所有的menu
	 */
	private List<Map<String,Object>> allMenu;
	
	/**
	 * 在allMenu中的元素里用于表示id的字段名
	 */
	private String idName;
	
	/**
	 * 用于表示元素的父菜单id的字段名
	 */
	private String parentIdName;
	
	/**
	 * 用于表示是否是父级菜单的字段名
	 */
	private String isParentName;
	
	/**
	 * 用于表示是否是父级菜单的字段值(比如：isParentName="par",isParentValue="parent",则在allMenu的元素中如果par="parent"，则表示是父菜单)
	 */
	private Object isParentValue;
	
	/**
	 * 用于表示url的字段名
	 */
	private String urlName;
	
	/**
	 * 用于表示菜单名称的字段名
	 */
	private String menuName;
	
	/**
	 * 用于表示用户可以访问的所有的菜单的id集合
	 */
	private List<String> userIds;
	
	
	@Override
	public void doTag() throws JspException, IOException {
		// 获得输出流
		// PrintWriter pw=new PrintWriter(getJspContext().getOut());	用这个方法向页面输出内容也可以
		
		JspWriter jw=getJspContext().getOut();
		// 获得存储字符串的容器
		StringBuffer buf=new StringBuffer();
		
		if(allMenu==null||allMenu.size()<1){
			// 数据库中还没有菜单
//			buf.append("<li>\n\t<a class=\"J_menuItem\" href=\"#\" onclick=\"javascript:return false;\"><i class=\"fa fa-columns\"></i> <span class=\"nav-label\">暂无菜单</span></a>\n</li>\n");
			buf.append("<li><a href=\"#\"><i class=\"fa fa-home\"></i><span class=\"nav-label\">暂无菜单</span><span class=\"fa arrow\"></span></a></li>\n");
			jw.print(buf.toString());
			return;
		}
		
		// 父菜单与子菜单分离
		List<Map<String,Object>> parentList = new ArrayList<Map<String,Object>>();
		List<Map<String,Object>> childList = new ArrayList<Map<String,Object>>();
		for(Map<String,Object> map:allMenu){
			Object parvalue = map.get(isParentName);
			if(isParentValue.equals(parvalue)){
				parentList.add(map);
			}else{
				childList.add(map);
			}
		}
		
		if(parentList==null||parentList.size()<1){
			// 父菜单为空
//			buf.append("<li>\n\t<a class=\"J_menuItem\" href=\"#\" onclick=\"javascript:return false;\"><i class=\"fa fa-columns\"></i> <span class=\"nav-label\">暂无菜单</span></a>\n</li>\n");
			buf.append("<li><a href=\"#\"><i class=\"fa fa-home\"></i><span class=\"nav-label\">父菜单为空</span><span class=\"fa arrow\"></span></a></li>\n");
			jw.print(buf.toString());
			return;
		}
		
		// 循环父菜单集合，同时去循环子菜单集合，将属于这个父菜单的子菜单嵌入进去
		for(Map<String,Object> par:parentList){
			String parId = par.get(idName).toString();
			if(userIds.contains(parId)){
				// 用户菜单中有去访问这个父菜单的权限
				// 判断这个父菜单有没有url，如果有，就不必再循环出他的子菜单
				Object parUrl = par.get(urlName);
				String name = par.get(menuName)==null||par.get(menuName).equals("")?"未命名":par.get(menuName).toString();
				if(parUrl==null||parUrl.equals("")||parUrl.equals("#")){
					// 说明这个父菜单没有超链接，可能会有子菜单
	                buf.append("<li>\n\t<a href=\"#\"><i class=\"fa fa-home\"></i><span class=\"nav-label\">" + name + "</span><span class=\"fa arrow\"></span></a>\n");
	                buf.append("\t<ul class=\"nav nav-second-level\">\n");
	                for(Map<String,Object> map:childList){
	                	String chilId = map.get(idName).toString();
	                	String parentId = map.get(parentIdName).toString();
	                	String chilname = map.get(menuName)==null||map.get(menuName).equals("")?"未命名":map.get(menuName).toString();
	                	String chilUrl = map.get(urlName).toString();
	                	if(userIds.contains(chilId)&&parId.equals(parentId)){
	                		// 说明用户的菜单列表中有这个子菜单，并且这个子菜单的父菜单就是par
	                    	buf.append("\t\t<li><a class=\"J_menuItem\" href=\"" + chilUrl + "\">" + chilname + "</a></li>\n");
	                	}
	                }
	                buf.append("\t</ul>\n</li>");
				}else{
					// 说明这个父菜单有超链接，不用循环子菜单
					buf.append("<li>\n\t<a class=\"J_menuItem\" href=\"" + parUrl.toString() + "\"><i class=\"fa fa-home\"></i> <span class=\"nav-label\">" + name + "</span></a>\n</li>\n");
				}
			}
		}
		
		jw.print(buf.toString());
	}

	
	
	
	

	public String getParentIdName() {
		return parentIdName;
	}

	public void setParentIdName(String parentIdName) {
		this.parentIdName = parentIdName;
	}

	public String getIsParentName() {
		return isParentName;
	}

	public void setIsParentName(String isParentName) {
		this.isParentName = isParentName;
	}

	public Object getIsParentValue() {
		return isParentValue;
	}

	public void setIsParentValue(Object isParentValue) {
		this.isParentValue = isParentValue;
	}

	public String getUrlName() {
		return urlName;
	}

	public void setUrlName(String urlName) {
		this.urlName = urlName;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public List<Map<String, Object>> getAllMenu() {
		return allMenu;
	}


	public void setAllMenu(List<Map<String, Object>> allMenu) {
		this.allMenu = allMenu;
	}


	public String getIdName() {
		return idName;
	}


	public void setIdName(String idName) {
		this.idName = idName;
	}


	public List<String> getUserIds() {
		return userIds;
	}


	public void setUserIds(List<String> userIds) {
		this.userIds = userIds;
	}
	
}
