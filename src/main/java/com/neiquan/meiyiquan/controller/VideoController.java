package com.neiquan.meiyiquan.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.neiquan.meiyiquan.code.Code;
import com.neiquan.meiyiquan.pojo.Video;
import com.neiquan.meiyiquan.service.VideoService;
import com.neiquan.meiyiquan.util.Statics;
import com.neiquan.meiyiquan.util.StringUtil;
import com.neiquan.meiyiquan.util.TimeCleanUtil;

/**
 * 作者：刘丹
 * 创建日期：2017年1月7日
 * 类说明：后台视频管理
 */
@Controller
@RequestMapping(value="video")
public class VideoController {

	@Autowired
	private VideoService service;
	
	/**
	 * 课程多条件查询
	 *
	 * @param id 
	 * @return
	 */
	@RequestMapping(value="getVideoList")
	public ModelAndView getVideoList(String id,String orderBy,String collation){
		ModelAndView mav = new ModelAndView();
		orderBy=StringUtil.isNullOrBlank(orderBy)?"tb_video.order_num":orderBy;
		collation=StringUtil.isNullOrBlank(collation)?" desc ":collation;
		mav.setViewName("video/video_list");
		mav.addObject("data",service.getListByCourseId(id, orderBy, collation));
		mav.addObject("id", id);
		return mav;
	}
	/**
	 * 添加
	 * @param id
	 * @param title
	 * @param course_id
	 * @param time_long
	 * @param video_save_url
	 * @param video_pic_url
	 * @param remark
	 * @param per_cost
	 * @param video_size
	 * @param free
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="add")
	public Code add(String title,String course_id,String time_long,String video_save_url
			,String video_pic_url,String remark,Double per_cost,String video_size,Integer free,Integer freeTime,Integer canUseCoupon,HttpSession session
			,String fileData,String video_url,String video_tl,String video_sz,String type){
		time_long=StringUtil.isNullOrBlank(time_long)?"0":time_long;
		
		if(StringUtil.isNullOrBlank(title))return Code.init(1, "标题必填");
		if(StringUtil.isNullOrBlank(video_pic_url))return Code.init(1, "封面必填");
		if(free==2){
			if(StringUtil.isNullOrBlank(per_cost))return Code.init(1, "价格必填");
			if(StringUtil.isNullOrBlank(freeTime))return Code.init(1, "免费时长必填");
		}
		
		if(type.equals("1")){
			if(StringUtil.isNullOrBlank(video_url))return Code.init(1, "链接地址必填");
			if(StringUtil.isNullOrBlank(video_tl))return Code.init(1, "视频长度必填");
			if(StringUtil.isNullOrBlank(video_sz))return Code.init(1, "视频大小必填");
			
			if(!video_sz.contains("G")&&!video_sz.contains("M")){
				return Code.init(1, "视频大小格式错误");
			}
			if(!video_tl.contains("时")&&!video_tl.contains("分")&&!video_tl.contains("秒")){
				return Code.init(1, "视频长度格式错误");
			}
			if(video_sz.contains("G")){
				Double s=Double.parseDouble(video_sz.substring(0,video_sz.indexOf("G")));
				Double ss=s*1024;
				video_sz=String.valueOf(ss);
			}
			if(video_sz.contains("M")){
				Double s=Double.parseDouble(video_sz.substring(0,video_sz.indexOf("G")));
				video_sz=String.valueOf(s);
			}
			video_save_url=video_url;
			video_size=video_sz;
			time_long=String.valueOf(TimeCleanUtil.timeString(video_tl));
		}else{
			if(StringUtil.isNullOrBlank(video_save_url))return Code.init(0, "视频不能为空");
		}
		
		
		Code code=	service.add(  title,  course_id,  time_long,  video_save_url
				,  video_pic_url,  remark,  per_cost,  video_size,  free,freeTime,canUseCoupon,session);
		return code;
	}

	
	/**
	 * 添加跳转
	 * @return
	 */
	@RequestMapping(value="addPage")
	public ModelAndView addPage(String id){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("video/video_add");
		mav.addObject("id", id);
		return mav;
	}
	/**
	 * 修改跳转
	 * @return
	 */
	@RequestMapping(value="updatePage")
	public ModelAndView updatePage(String id,String did){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("video/video_update");
		mav.addObject("data", service.getVideoById(did));
		mav.addObject("id",id);
		mav.addObject("did",did);
		return mav;
	}
	
	/**
	 * 修改
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="update")
	public Code update(String title,String id,String time_long,String video_save_url
			,String video_pic_url,String remark,Double per_cost,String video_size,Integer free,String freeTime,Integer canUseCoupon,HttpSession session
			,String fileData ,String video_url,String video_tl,String video_sz,String type){
		time_long=StringUtil.isNullOrBlank(time_long)?"0":time_long;
		freeTime=	StringUtil.isNullOrBlank(freeTime)?"0":freeTime;
		Integer ft=	(int)Double.valueOf(freeTime).doubleValue();//String转Integer
		if(StringUtil.isNullOrBlank(title))return Code.init(1, "标题必填");
		if(StringUtil.isNullOrBlank(video_pic_url))return Code.init(1, "封面必填");
		if(free==2){
			if(StringUtil.isNullOrBlank(per_cost))return Code.init(1, "价格必填");
			if(StringUtil.isNullOrBlank(freeTime))return Code.init(1, "免费时长必填");
		}
		if(type.equals("1")){
			if(StringUtil.isNullOrBlank(video_url))return Code.init(1, "链接地址必填");
			if(StringUtil.isNullOrBlank(video_tl))return Code.init(1, "视频长度必填");
			if(StringUtil.isNullOrBlank(video_sz))return Code.init(1, "视频大小必填");
			
			if(!video_sz.contains("G")&&!video_sz.contains("M")){
				return Code.init(1, "视频大小格式错误");
			}
			if(!video_tl.contains("时")&&!video_tl.contains("分")&&!video_tl.contains("秒")){
				return Code.init(1, "视频长度格式错误");
			}
			
			if(video_sz.contains("G")){
				Double s=Double.parseDouble(video_sz.substring(0,video_sz.indexOf("G")));
				Double ss=s*1024;
				video_sz=String.valueOf(ss);
			}
			if(video_sz.contains("M")){
				Double s=Double.parseDouble(video_sz.substring(0,video_sz.indexOf("G")));
				video_sz=String.valueOf(s);
			}
			
			
			video_save_url=video_url;
			video_size=video_sz;
			time_long=String.valueOf(TimeCleanUtil.timeString(video_tl));
		}
		else{
			if(StringUtil.isNullOrBlank(video_save_url))return Code.init(1, "视频不能为空");
		}
		return  service.update(title,id,time_long,video_save_url
				,video_pic_url,remark, per_cost,video_size, free,ft,canUseCoupon, session
				,fileData);
	}
	/**
	 * 视频跳转
	 * @return
	 */
	@RequestMapping(value="videoPage")
	public ModelAndView videoPage(String id){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("video/video_list.jsp");
		return mav;
	}
	
	/**
	 * 启用禁用
	 * @param id
	 * @param status
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="upStatus")
	public Code upStatus(String id,String status,HttpSession session){
		return service.upStatus(id,status,session);
	}
	
/**
	 * 顶置
	 * @return
	 */ 
	@ResponseBody
	@RequestMapping(value="overhead")
	public Code overhead(String id){
		return service.overhead(id);
	}
	
/**
	 * 顶置
	 * @return
	 */ 
	@ResponseBody
	@RequestMapping(value="del")
	public Code del(String id, HttpSession session){
		return service.delete(id,session);
	}
	/**
	 * 播放节点统计
	 * @return
	 */
	@RequestMapping(value="nodeCount")
	public ModelAndView nodeCount(String id){
		ModelAndView mav =new ModelAndView();
		//五种播放节点统计
		Map list1=service.nodeCount(Statics.PLAYRECORD_TYPE_ONE,id);
		Map list2=service.nodeCount(Statics.PLAYRECORD_TYPE_TWO,id);
		Map list3=service.nodeCount(Statics.PLAYRECORD_TYPE_THE,id);
		Map list4=service.nodeCount(Statics.PLAYRECORD_TYPE_FOR,id);
		Map list5=service.nodeCount(Statics.PLAYRECORD_TYPE_FIV,id);
		mav.addObject("list1", list1);
		mav.addObject("list2", list2);
		mav.addObject("list3", list3);
		mav.addObject("list4", list4);
		mav.addObject("list5", list5);
		mav.setViewName("video/void_plyer_count");
		return mav;
	}
	
	
}
 
