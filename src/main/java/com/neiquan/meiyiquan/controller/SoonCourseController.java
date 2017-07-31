package com.neiquan.meiyiquan.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.neiquan.meiyiquan.code.Code;
import com.neiquan.meiyiquan.pojo.Channel;
import com.neiquan.meiyiquan.pojo.Comment;
import com.neiquan.meiyiquan.pojo.Course;
import com.neiquan.meiyiquan.pojo.User;
import com.neiquan.meiyiquan.pojo.UserVideoRequest;
import com.neiquan.meiyiquan.service.AskCourseService;
import com.neiquan.meiyiquan.service.BannerService;
import com.neiquan.meiyiquan.service.ChannelService;
import com.neiquan.meiyiquan.service.SoonCourseService;
import com.neiquan.meiyiquan.util.ExtraSpringHibernateTemplate;
import com.neiquan.meiyiquan.util.Statics;
import com.neiquan.meiyiquan.util.StringUtil;


/**
 * 作者：温尉棨
 * 创建日期：2017年1月20日
 * 类说明：即将上映
 */
@Controller
@RequestMapping(value="sooncourse")
public class SoonCourseController {
	@Autowired
	private SoonCourseService scs;
	@Autowired
	private ExtraSpringHibernateTemplate esht;
	@Autowired
	private ChannelService chs;
	@Autowired
	private BannerService bs;
	@Autowired
	private AskCourseService as;
	/**
	 * 即将上映列表
	 * @param page
	 * @param size
	 * @param count
	 * @param createBegin
	 * @param createEnd
	 * @param pid
	 * @param orderBy
	 * @param collation
	 * @return
	 */
	@RequestMapping(value="sooncourse")
	public ModelAndView sooncourse(String page,String size,String count,String createBegin,String createEnd, String pid,String orderBy,String collation
			,String firstCate,String secondCate,String thirdCate,String tree){
		pid=StringUtil.isNullOrBlank(pid)?"":pid;
		firstCate=StringUtil.isNullOrBlank(firstCate)?"":firstCate;
		secondCate=StringUtil.isNullOrBlank(secondCate)?"":secondCate;
		thirdCate=StringUtil.isNullOrBlank(thirdCate)?"":thirdCate;
		page=	StringUtil.isNullOrBlank(page)?"1":page;
		size=StringUtil.isNullOrBlank(size)?"10":size;
		orderBy=StringUtil.isNullOrBlank(orderBy)?" s.click_count":orderBy;//默认点击量排序
		collation=StringUtil.isNullOrBlank(collation)?" DESC":collation;//默认倒序
		String cate="";
		String secCate="";
		String tirCate="";
		if(!firstCate.equals("na")||!secondCate.equals("na")||!thirdCate.equals("na")){
			if(!firstCate.equals("na")){
				cate=firstCate;
			}
			if(!secondCate.equals("na")&&!secondCate.equals("")){
				cate=secondCate;
				Channel cn	=esht.findFirstOneByPropEq(Channel.class,"id" ,secondCate);
				secCate=cn.getName();
			}
			if(!thirdCate.equals("na")&&!thirdCate.equals("")){
				cate=thirdCate;
				Channel cn	=esht.findFirstOneByPropEq(Channel.class,"id" ,thirdCate);
				tirCate=cn.getName();
			}
		}
		
		ModelAndView mav = new ModelAndView();
		mav.addObject(scs.getSoonList(page, size, count, createBegin, createEnd, orderBy, collation, tree));
		List list=chs.getFchannel();
		mav.addObject("list",list);
		mav.addObject("page", page);
		mav.addObject("size", size);
		mav.addObject("count", count);
		mav.addObject("createBegin", createBegin);
		mav.addObject("createEnd", createEnd);
		mav.addObject("pid", pid);
		mav.addObject("firstCate", firstCate);
		mav.addObject("secondCate", secondCate);
		mav.addObject("secCate", secCate);
		mav.addObject("thirdCate", thirdCate);
		mav.addObject("tirCate", tirCate);
		mav.addObject("tree", tree);
		mav.setViewName("soon/coursesoon/coursesoon_list");
		return mav;
	}
	
	@RequestMapping(value="toaddSoon")
	public ModelAndView toaddSoon(){
		
		ModelAndView mav =new ModelAndView();
		mav.setViewName("soon/coursesoon/coursesoon_add");
		return mav;
	}
	
	/**
	 * 添加
	 * @param playing_time
	 * @param title
	 * @param pic_big_url
	 * @param pic_small_url
	 * @param teacher_id
	 * @param cost
	 * @param channel_id
	 * @return
	 */
	@RequestMapping(value="addSoon")
	public ModelAndView  addSoon(String playing_time,String title,String pic_big_url,String pic_small_url,
			String teacher_id,String cost,String channel_id,String fileData,String course_compaign_type,
			String descriptionn,@RequestParam MultipartFile file0){
	
		playing_time=StringUtil.isNullOrBlank(playing_time)?"":playing_time;
		title=StringUtil.isNullOrBlank(title)?"":title;
		pic_big_url=StringUtil.isNullOrBlank(pic_big_url)?"":pic_big_url;
		pic_small_url=StringUtil.isNullOrBlank(pic_small_url)?"":pic_small_url;
		teacher_id=StringUtil.isNullOrBlank(teacher_id)?"":teacher_id;
		cost=StringUtil.isNullOrBlank(cost)?"":cost;
		channel_id=StringUtil.isNullOrBlank(channel_id)?"":channel_id;
		fileData=StringUtil.isNullOrBlank(fileData)?"":fileData;
		course_compaign_type=StringUtil.isNullOrBlank(course_compaign_type)?"":course_compaign_type;
		descriptionn=StringUtil.isNullOrBlank(descriptionn)?"":descriptionn;
		
		if(pic_small_url.contains("blob")){
			try {
			String urlpath=bs.updateHead(file0);
			pic_small_url=urlpath;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else{
		}
		/*if(pic_big_url.contains("blob")){
			try {
			String urlpath=bs.updateHead(file1);
			pic_big_url=urlpath;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else{
		}*/
		
		Code result=scs.addSoon(playing_time, title, pic_big_url, pic_small_url, teacher_id, cost, channel_id, fileData, course_compaign_type, descriptionn);
		ModelAndView mav = new ModelAndView();
		mav= new ModelAndView("redirect:/sooncourse/sooncourse.do");
		return mav;
	}
	/**
	 * 修改即将上映
	 * @param id
	 * @param playing_time
	 * @param title
	 * @param pic_big_url
	 * @param pic_small_url
	 * @param teacher_id
	 * @param cost
	 * @param channel_id
	 * @param fileData
	 * @param course_compaign_type
	 * @param description
	 * @param file0
	 * @param file1
	 * @return
	 */
	@RequestMapping(value="upoateSoon")
	public ModelAndView upoateSoon(String id,String playing_time,String title,String pic_big_url,String pic_small_url,
			String teacher_id,String cost,String channel_id,String fileData,String course_compaign_type,
			String descriptionn,@RequestParam MultipartFile file0){
		playing_time=StringUtil.isNullOrBlank(playing_time)?"":playing_time;
		title=StringUtil.isNullOrBlank(title)?"":title;
		pic_big_url=StringUtil.isNullOrBlank(pic_big_url)?"":pic_big_url;
		pic_small_url=StringUtil.isNullOrBlank(pic_small_url)?"":pic_small_url;
		teacher_id=StringUtil.isNullOrBlank(teacher_id)?"":teacher_id;
		cost=StringUtil.isNullOrBlank(cost)?"":cost;
		channel_id=StringUtil.isNullOrBlank(channel_id)?"":channel_id;
		fileData=StringUtil.isNullOrBlank(fileData)?"":fileData;
		course_compaign_type=StringUtil.isNullOrBlank(course_compaign_type)?"":course_compaign_type;
		descriptionn=StringUtil.isNullOrBlank(descriptionn)?"":descriptionn;
		
		if(pic_small_url.contains("blob")){
			try {
			String urlpath=bs.updateHead(file0);
			pic_small_url=urlpath;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else{
		}
		/*if(pic_big_url.contains("blob")){
			try {
			String urlpath=bs.updateHead(file1);
			pic_big_url=urlpath;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else{
		}*/
		
		Code result=scs.updateSoon(id, playing_time, title, pic_big_url, pic_small_url, teacher_id, cost, channel_id, fileData, course_compaign_type, descriptionn);
		ModelAndView mav = new ModelAndView();
		mav= new ModelAndView("redirect:/sooncourse/sooncourse.do");
		return mav;
	}
	/**
	 * 获取即将上映详情
	 * @param id
	 * @return
	 */
	@RequestMapping(value="soonInfo")
	public ModelAndView soonInfo(String id){
		ModelAndView mav = new ModelAndView();
		mav.addObject(scs.soonInfo(id));
		mav.setViewName("soon/coursesoon/coursesoon_update");
		return mav;
	}
	
	/**
	 * 删除
	 */
	@RequestMapping(value="delete")
	public ModelAndView delete(String id){
		ModelAndView mav = new ModelAndView();
		mav.addObject(scs.delAll(id));
		mav= new ModelAndView("redirect:/sooncourse/sooncourse.do");
		return mav;
	}
	/**
	 * 评论列表
	 * @return
	 */
	@RequestMapping(value="commentReplay")
	public ModelAndView commentReplay(String title,String page,String size,String count){
		ModelAndView mav = new ModelAndView();
		page=StringUtil.isNullOrBlank(page)?"1":page;
		size=StringUtil.isNullOrBlank(size)?"5":size;
		title=StringUtil.isNullOrBlank(title)?"":title;
		mav.addObject(scs.commentReplay(title, page, size, count));
		mav.addObject("page",page);  
		mav.addObject("size",size);
		mav.addObject("count",count);
		mav.addObject("title",title);
		mav.setViewName("soon/coursesoon/soon_comment_list");
		return mav;
	}
	/**
	 * 评论详情
	 * @param id
	 * @return
	 */
	@RequestMapping(value="commentInfo")
	public ModelAndView  commentInfo(String id){
		Comment com=esht.findFirstOneByPropEq(Comment.class, "id", id);
		String comm_content=com.getComm_content();
		String comm_id=com.getCommed_id();
		String user_id=com.getUser_id();
		User user=esht.findFirstOneByPropEq(User.class,"id", user_id);
		String user_name=user.getUsername();
		Course uvr=esht.findFirstOneByPropEq(Course.class, "id", comm_id);
		String course_name=uvr.getTitle();
		Integer status=com.getStatus();
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("topid",id);
		mav.addObject(as.commentInfo(id));
		mav.addObject("comm_content",comm_content);
		mav.addObject("course_name",course_name);
		mav.addObject("status",status);
		mav.addObject("user_name",user_name);
		mav.setViewName("soon/coursesoon/soon_comment_info");
		return mav;
	}
	
	/**
	 * 拉黑评论
	 * @return
	 */
	@RequestMapping(value="blackComent")
	public ModelAndView blackComent(String id,String topid){
		ModelAndView mav =new ModelAndView();
		Comment com= esht.findFirstOneByPropEq(Comment.class, "id", id);
		com.setStatus(Statics.NO_INT);
		esht.getHibernateTemplate().update(com);
		mav= new ModelAndView("redirect:/sooncourse/commentInfo.do?id="+topid+" ");
		return mav;
	}
	/**
	 * 恢复评论
	 * @return
	 */
	@RequestMapping(value="reginaComent")
	public ModelAndView reginaComent(String id,String topid){
		ModelAndView mav =new ModelAndView();
		Comment com= esht.findFirstOneByPropEq(Comment.class, "id", id);
		com.setStatus(Statics.YES_INT);
		esht.getHibernateTemplate().update(com);
		mav= new ModelAndView("redirect:/sooncourse/commentInfo.do?id="+topid+" ");
		return mav;
	}
	
	/**
	 * 删除评论
	 * @param id
	 * @return
	 */
	@RequestMapping(value="deletesoon",method=RequestMethod.POST)
	public ModelAndView deletesoon(String id){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("soon/coursesoon/coursesoon_list");
		mav.addObject(scs.delAll(id));
		return mav;
	}
	/**
	 * 修改虚拟量
	 * @param id
	 * @return
	 */
	@RequestMapping(value="updatesoon",method=RequestMethod.POST)
	public ModelAndView updatesoon(String id,String like_expect_count){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("soon/coursesoon/coursesoon_list");
		mav.addObject(scs.delAll(id));
		return mav;
	}
	/**
	 * 见数据分析附件（主要涉及会员行为日志统计,时间段用户量,点击习惯,
	 *  点击量排名 ,虚拟点击量区分，播放节点统计等 点击量，
	 *  评论量，转发量，好评量，差评量，视频播放量，收藏量）
	 * @param id
	 * @param like_expect_count
	 * @return
	 */
	@RequestMapping(value="comment")
	public ModelAndView comment(String createBegin,String createEnd){
		ModelAndView mav =new ModelAndView();
		
		return mav;
	}
	/**
	 * 转换课程至上映时详情页面
	 * @param id
	 * @return
	 */
	@RequestMapping(value="courseinfoo")
	public ModelAndView courseinfoo(String id){
		ModelAndView mav =new ModelAndView();
		mav.addObject(scs.soonInfo(id));
		mav.setViewName("soon/coursesoon/show_course");
		return mav;
	}
	
	@RequestMapping(value="showCourse")
	public ModelAndView showCourse(String id,String descriptionn,String channel_id){
		ModelAndView mav =new ModelAndView();
		Course co=esht.findFirstOneByPropEq(Course.class, "id",id);
		co.setDescription(descriptionn);
		co.setChannel_id(channel_id);
		co.setPlaying(Statics.COURSE_PLAYING_SHOW);
		esht.getHibernateTemplate().update(co);
		
		mav= new ModelAndView("redirect:/sooncourse/sooncourse");
		return mav;
	}
	
}
