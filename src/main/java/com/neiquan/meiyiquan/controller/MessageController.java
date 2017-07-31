package com.neiquan.meiyiquan.controller;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.neiquan.meiyiquan.code.Code;
import com.neiquan.meiyiquan.dao.ObjectDao;
import com.neiquan.meiyiquan.pojo.Admin;
import com.neiquan.meiyiquan.pojo.Course;
import com.neiquan.meiyiquan.pojo.Job;
import com.neiquan.meiyiquan.pojo.Sys;
import com.neiquan.meiyiquan.pojo.TeacherSend;
import com.neiquan.meiyiquan.service.BannerService;
import com.neiquan.meiyiquan.service.MessageService;
import com.neiquan.meiyiquan.util.ExtraSpringHibernateTemplate;
import com.neiquan.meiyiquan.util.StringUtil;


/**
 * 作者：温尉棨
 * 创建日期：2017年2月14日
 * 类说明：
 */
@Controller
@RequestMapping(value="message")
public class MessageController {
	
	@Autowired
	private MessageService ms;
	@Autowired
	private BannerService bs;
	@Autowired
	private ExtraSpringHibernateTemplate esht;
	@Autowired
	private ObjectDao od;
	
	/**
	 * 公共参数列表
	 * @return
	 */
	@RequestMapping(value="publicList")
	public ModelAndView public_parameter(){
		ModelAndView mav= new ModelAndView();
		mav.addObject(ms.public_paratemer());
		mav.setViewName("banner/banner/public_paratemer");
		return mav;
	}
	/**
	 * 公共参数详情
	 * @param id
	 * @return
	 */
	@RequestMapping(value="publicInfo")
	public ModelAndView publicInfo(String id){
		ModelAndView mav=new ModelAndView();
		mav.addAllObjects(ms.publicInfo(id).getMapData());
		
		mav.setViewName("banner/banner/public_info");
		return mav;
	}
	/**
	 * 修改公共参数
	 * @param id
	 * @param parameter_name
	 * @param pic_url
	 * @return
	 */
	@RequestMapping(value="publicUpdat")
	public ModelAndView publicUpdat(String id,String title,String sys_value,String sys_valuee
			, @RequestParam(required=false)MultipartFile file0,String type){
		
		ModelAndView mav=new ModelAndView();
		if(type.equals("1")){
			if(sys_valuee.contains("blob")){
				try {
					sys_valuee=bs.updateHead(file0);
					long i=ms.publicUpdate(id, title, sys_valuee);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}else{
			long i=ms.publicUpdate(id, title, sys_value); 
		}
		mav= new ModelAndView("redirect:/message/publicList.do");
		return mav;
	}
	/**
	 * 可修改公共参数列表
	 */
	@RequestMapping(value="jobList")
	public ModelAndView  jobList(String type){
		ModelAndView mav=new ModelAndView();
		mav.addObject(ms.job_list(type));
		mav.addObject("type",type);
		mav.setViewName("banner/banner/public_job_list");
		return mav;
	}
	/**
	 * 可修改公共参数获取详情
	 * @param id
	 * @return
	 */
	@RequestMapping(value="jobInfo")
	public ModelAndView jobInfo(String id){
		ModelAndView mav=new ModelAndView();
		Map map=od.getObjByIdForMap(Job.class, id);
		mav.addAllObjects(map);
		mav.setViewName("banner/banner/pubic_job");
		return mav;
	}
	
	
	/**
	 * 修改可添加参数
	 * @param id
	 * @param num
	 * @param type
	 * @param value
	 * @return
	 */
	@RequestMapping(value="jobUpdate")
	public ModelAndView jobUpdate(String id,String orderInt,String type,String job_name){
		ModelAndView mav=new ModelAndView();
		Job job=esht.findFirstOneById(Job.class, id);
		Date date = new Date();
		long lSysTime1 = date.getTime(); 
		job.setUpdate_time(lSysTime1);
		job.setJob_name(job_name);
		int i=Integer.parseInt(orderInt);
		job.setOrderInt(i);
		esht.getHibernateTemplate().update(job);
		mav= new ModelAndView("redirect:/message/jobList.do?type="+type+"");
		return mav;
	}
	/**
	 * 跳转添加
	 * @param type
	 * @return
	 */
	@RequestMapping(value="toadd")
	public ModelAndView toadd(String type){
		ModelAndView mav=new ModelAndView();
		mav.addObject("type",type);
		mav.setViewName("banner/banner/public_job_add");
		return mav;
	}
	
	/**
	 * 添加可添加参数
	 * @param id
	 * @param num
	 * @param type
	 * @param value
	 * @return
	 */
	@RequestMapping(value="jobAdd")
	public ModelAndView jobAdd(String id,String orderInt,String type,String job_name){
		ModelAndView mav=new ModelAndView();
		Job job=new Job();
		Date date = new Date();
		long lSysTime1 = date.getTime(); 
		job.setCreate_time(lSysTime1);
		job.setJob_name(job_name);
		int i=Integer.parseInt(orderInt);
		job.setOrderInt(i);
		int t=Integer.parseInt(type);
		job.setType(t);
		esht.getHibernateTemplate().save(job);
		mav= new ModelAndView("redirect:/message/jobList.do?type="+type+"");
		return mav;
	}
	/**
	 * 管理员列表
	 * @param page
	 * @param size
	 * @param count
	 * @return
	 */
	@RequestMapping(value="adminList")
	public ModelAndView adminList(String page,String size,String count,String name){
		page=StringUtil.isNullOrBlank(page)?"1":page;
		size=StringUtil.isNullOrBlank(size)?"5":size;
		name=StringUtil.isNullOrBlank(name)?"":name;
		ModelAndView mav=new ModelAndView();
		mav.addObject(ms.adminList(page, size,name));
		mav.setViewName("public/admin/admin_list");
		mav.addObject("page", page);
		mav.addObject("size", size);
		mav.addObject("count", count);
		mav.addObject("name",name);
		return mav;
	}
	/**
	 * 管理员详情
	 * @param id
	 * @return
	 */
	@RequestMapping(value="admininfo")
	public ModelAndView admininfo(String id){
		ModelAndView mav=new ModelAndView();
		
		Code code= ms.adminInfo(id);
		Code code1= ms.role();
		
		mav.addObject("adCode", code);
		mav.addObject("roCode", code1);
		mav.setViewName("public/admin/admin_info");
		return mav;
	}
	/**
	 * 修改管理员
	 * @param id
	 * @param name
	 * @param password
	 * @param roleid
	 * @return
	 */
	@RequestMapping(value="updateAdmin")
	public ModelAndView updateAdmin(String id,String name,String password,String roleid){
		ModelAndView mav=new ModelAndView();
		ms.updateAdmin(id, name, password, roleid);
		mav= new ModelAndView("redirect:/message/adminList.do");
		return mav;
	}
	/**
	 * 跳转添加
	 * @return
	 */
	@RequestMapping(value="toaddd")
	public ModelAndView toaddd(){
		ModelAndView mav=new ModelAndView();
		Code code1= ms.role();
		mav.addObject("roCode", code1);
		mav.setViewName("public/admin/admin_add");
		return mav;
	}
	/**
	 * 添加
	 * @param name
	 * @param password
	 * @param roleid
	 * @return
	 */
	@RequestMapping(value="addd")
	public ModelAndView addd(String name,String password,String roleid){
		ModelAndView mav=new ModelAndView();
		ms.addAdmin(name, password, roleid);
		mav= new ModelAndView("redirect:/message/adminList.do");
		return mav;
	}
	/**
	 * 取消权限
	 * @param id
	 * @return
	 */
	@RequestMapping(value="quxiao")
	public ModelAndView quxiao(String id){
		ModelAndView mav=new ModelAndView();
		Admin ad= esht.findFirstOneById(Admin.class, id);
		ad.setRoleId("0");
		esht.getHibernateTemplate().update(ad);
		mav= new ModelAndView("redirect:/message/adminList.do");
		return mav;
	}
	/**
	 * 删除管理员
	 * @param id
	 * @return
	 */
	@RequestMapping(value="delAdmin")
	public ModelAndView delAdmin(String id){
		ModelAndView mav=new ModelAndView();
		Admin ad= esht.findFirstOneById(Admin.class, id);
		ad.setRoleId("");
		esht.getHibernateTemplate().update(ad);
		mav= new ModelAndView("redirect:/message/adminList.do");
		return mav;
	}
	/**
	 * 教师提交审核列表
	 * @param page
	 * @param size
	 * @param count
	 * @param name
	 * @return
	 */
	@RequestMapping(value="tmList")
	public ModelAndView tmList(String page,String size,String count,String name){
		page=StringUtil.isNullOrBlank(page)?"1":page;
		size=StringUtil.isNullOrBlank(size)?"5":size;
		name=StringUtil.isNullOrBlank(name)?"":name;
		ModelAndView mav=new ModelAndView();
		mav.addObject(ms.tmList(page, size, name));
		mav.setViewName("public/TeacherMoney/tm_list");
		mav.addObject("page", page);
		mav.addObject("size", size);
		mav.addObject("count", count);
		mav.addObject("name",name);
		return mav;
	}
	
	/**
	 * 修改讲师审核表
	 * @param id
	 * @param type
	 * @return
	 */
	@RequestMapping(value="tmupdate")
	public ModelAndView tmupdate(String id,String status,HttpSession session){
		ModelAndView mav=new ModelAndView();
		TeacherSend ths=esht.findFirstOneByPropEq(TeacherSend.class, "id", id);
		Map<String, Object> admin = (Map<String, Object>) session.getAttribute("user");
		String adminId = admin.get("id").toString();
		int statuss = Integer.valueOf(status).intValue();
		ths.setStatus(statuss);
		ths.setVerify_admin_id(adminId);
		ths.setVerify_time(System.currentTimeMillis());
		esht.getHibernateTemplate().update(ths);
		mav= new ModelAndView("redirect:/message/tmList.do");
		return mav;
	}
	
	
}
