package com.neiquan.meiyiquan.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.neiquan.meiyiquan.code.Code;
import com.neiquan.meiyiquan.service.DistrictService;
import com.neiquan.meiyiquan.util.StringUtil;

/**
 * 作者：刘丹
 * 创建日期：2017年1月7日
 * 类说明：后台区域管理
 */
@Controller
@RequestMapping(value="district")
public class DistrictController {

	@Autowired
	private DistrictService ds;
	
	/**
	 * 区域第一级
	 *
	 * @param id 
	 * @return
	 */
	@RequestMapping(value="getDistrictListTypeOne")
	public ModelAndView getDistrictListTypeOne(String keywords,String status){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("district/district_list");
		mav.addObject(ds.getDistrictsList(keywords,status));
		mav.addObject("onurl", "district/getDistrictListTypeOne");
		return mav;
	}
	
	/**
	 * 区域第二级
	 *
	 * @param id 
	 * @return
	 */
	@RequestMapping(value="getDistrictListTypeTwo")
	public ModelAndView getDistrictListTypeTwo(String id){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("district/district_list2");
		mav.addObject(ds.getDistrictsChilds(id));
		mav.addObject("id", id);
		return mav;
	}
	
	/**
	 * 异步获得节点
	 *
	 * @param id 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="getDistrictList")
	public Code getDistrictList(String pid){
		pid=StringUtil.isNullOrBlank(pid)?"0":pid;
		return ds.getDistrictsChilds(pid);
	}
	/**
	 * 区域第三级
	 *
	 * @param id 
	 * @return
	 */
	@RequestMapping(value="getDistrictListTypeThere")
	public ModelAndView getDistrictListTypeThere(String id,String pid){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("district/district_list3");
		mav.addObject("id", id);
		mav.addObject("pid", pid);
		mav.addObject(ds.getDistrictsChilds(id));
		System.out.println("22");
		return mav;
	}
	
	/**
	 * 添加
	 * @param name 名称
	 * @param pid 父ID
	 * @return
	 */
	@RequestMapping(value="add")
	public ModelAndView add(String name,String pid,HttpSession session){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("district/district_list");
		mav.addObject(ds.add(name,pid,session));
		return mav;
	}
	/**
	 * 修改
	 * @param name 名称
	 * @param pid 父ID
	 * @return
	 */
	@RequestMapping(value="update")
	public ModelAndView update(String name,String id,HttpSession session){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("district/district_list");
		mav.addObject(ds.update(name,id,session));
		return mav;
	}
	
	/**
	 * 修改
	 * @param name 名称
	 * @param pid 父ID
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="upStatus")
	public Code upStatus(String id,String status,HttpSession session){
		return ds.upStatus(id,status,session);
	}
	
	/**
	 * 删除节点及全部子节点
	 * @param name 名称
	 * @param pid 父ID
	 * @return
	 */
	@RequestMapping(value="delAll")
	public ModelAndView delAll(String id){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("district/district_list");
		mav.addObject(ds.delAll(id));
		return mav;
	}
	
}
 
