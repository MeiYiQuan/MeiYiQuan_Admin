package com.neiquan.meiyiquan.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neiquan.meiyiquan.code.Code;
import com.neiquan.meiyiquan.pojo.District;
import com.neiquan.meiyiquan.service.DistrictService;
import com.neiquan.meiyiquan.util.ExtraSpringHibernateTemplate;
import com.neiquan.meiyiquan.util.SessionUtil;
import com.neiquan.meiyiquan.util.Statics;
import com.neiquan.meiyiquan.util.StringUtil;


/**
 * 作者：刘丹
 * 创建日期：2017年1月7日
 * 类说明：区域管理模块
 */
@Service
public class DistrictServiceImpl implements DistrictService {
	
	@Autowired
	private ExtraSpringHibernateTemplate esht;

	@Override
	public Code getDistrictsList(String keywords,String status ) {
		keywords=StringUtil.isNullOrBlank(keywords)?"":keywords;
		status=StringUtil.isNullOrBlank(status)?"":status;
		
		List<District> list=esht.findAllByPropEq(District.class,"pid" ,"0");
		
		return Code.init(0, null, list);
	}
	@Override
	public Code getDistrictsChilds(String  id) {
		Map<String, Object> map=new HashMap<String, Object>();
		List<District> list=esht.findAllByPropEq(District.class,"pid" ,id);
		if(!id.equals("0")){
			District d=	esht.findFirstOneByPropEq(District.class, "id", id);
			map.put("clickName", d.getName());
		}
		map.put("data", list);
		return Code.init(0, null, map);
	}
	@Override
	public Code add(String  name,String pid ,HttpSession session) {
		District nd=new District();
		if(pid.equals("0")){
			nd.setDistrict_grade(1);
		}else{
			District d	=	esht.findFirstOneByPropEq(District.class,"id" ,pid);
			if(d==null)return Code.init(1, "未找到此节点", null);
			nd.setDistrict_grade(d.getDistrict_grade()+1);
		}
		nd.setCreate_time(System.currentTimeMillis());
		nd.setPid(pid);
		nd.setName(name);
		nd.setStatus(Statics.NO_INT);
		nd.setUpdate_time(System.currentTimeMillis());
		nd.setUpdate_admin_id(SessionUtil.getUserId(session));
		esht.getHibernateTemplate().save(nd);
		return Code.init(0, null, null);
	}
	
	@Override
	public Code update(String  name,String id ,HttpSession session) {
		District nd	=	esht.findFirstOneByPropEq(District.class,"id" ,id);
		if(nd==null)return Code.init(1, "未找到此节点", null);
		nd.setName(name);
		nd.setUpdate_time(System.currentTimeMillis());
		nd.setUpdate_admin_id(SessionUtil.getUserId(session));
		esht.getHibernateTemplate().update(nd);
		return Code.init(0, "修改成功", null);
	}
	@Override
	public Code upStatus(String id,String  status,HttpSession session) {
		District nd	=	esht.findFirstOneByPropEq(District.class,"id" ,id);
		if(nd==null)return Code.init(1, "未找到此节点", null);
		nd.setStatus(Integer.valueOf(status));
		nd.setUpdate_time(System.currentTimeMillis());
		nd.setUpdate_admin_id(SessionUtil.getUserId(session));
		esht.getHibernateTemplate().update(nd);
		return Code.init(0, "执行成功", null);
	}
	@Override
	public Code delAll(String id ) {
		District nd	=esht.findFirstOneByPropEq(District.class,"id" ,id);
		if(nd==null)return Code.init(1, "未找到此节点", null);
		nd.setUpdate_time(System.currentTimeMillis());
		getChilds(id);
		 esht.getHibernateTemplate().delete(nd);
		return Code.init(0, "删除成功", null);
	}
	/**
	 * 递归删除子节点
	 * @param id
	 */
	public void getChilds(String id ) {
		List<District> 	list=esht.findAllByPropEq(District.class, "pid", id);
		for(int i=0;i<list.size();i++){
			 getChilds(list.get(i).getId().toString());
			 list.get(i).setStatus(0);
			 esht.getHibernateTemplate().delete(list.get(i));
		}
	}
}
