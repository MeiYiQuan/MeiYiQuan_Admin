package com.neiquan.meiyiquan.controller;

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
import com.neiquan.meiyiquan.dao.ObjectDao;
import com.neiquan.meiyiquan.dao.support.ActiveDaoSupport;
import com.neiquan.meiyiquan.pojo.ActivityUser;
import com.neiquan.meiyiquan.pojo.Channel;
import com.neiquan.meiyiquan.pojo.Comment;
import com.neiquan.meiyiquan.pojo.Course;
import com.neiquan.meiyiquan.pojo.CourseRecommend;
import com.neiquan.meiyiquan.pojo.User;
import com.neiquan.meiyiquan.pojo.Video;
import com.neiquan.meiyiquan.pojo.VideoStandard;
import com.neiquan.meiyiquan.service.AskCourseService;
import com.neiquan.meiyiquan.service.CourseService;
import com.neiquan.meiyiquan.service.ObjectService;
import com.neiquan.meiyiquan.service.TeacherService;
import com.neiquan.meiyiquan.util.Constant;
import com.neiquan.meiyiquan.util.ExtraSpringHibernateTemplate;
import com.neiquan.meiyiquan.util.Paging;
import com.neiquan.meiyiquan.util.Statics;
import com.neiquan.meiyiquan.util.StringUtil;

/**
 * 作者：刘丹
 * 创建日期：2017年1月7日
 * 类说明：后台课程管理
 */
@Controller
@RequestMapping(value="course")
public class CourseController {

	@Autowired
	private CourseService ds;
	@Autowired
	private ExtraSpringHibernateTemplate esht;
	@Autowired
	private AskCourseService as;
	@Autowired
	private TeacherService teacherService;
	@Autowired
	private ObjectService os;
	
	
	
	/**
	 * 课程多条件查询
	 *
	 * @param id 
	 * @return
	 */
	@RequestMapping(value="getCourseList")
	public ModelAndView getCourseList(String keywords,String status ,String pageIndex,String pageSize,
			String timeStar,String timeEnd ,String orderBy,String collation,String channel){
		keywords=StringUtil.isNullOrBlank(keywords)?"":keywords;
		status=StringUtil.isNullOrBlank(status)?"":status;
		pageIndex=	StringUtil.isNullOrBlank(pageIndex)?"1":pageIndex;
		Integer	pIndex=Integer.valueOf(pageIndex);
		pageSize=StringUtil.isNullOrBlank(pageSize)?"10":pageSize;
		Integer	pSize=Integer.valueOf(pageSize);
		orderBy=StringUtil.isNullOrBlank(orderBy)?" s.click_count":orderBy;//默认点击量排序
		collation=StringUtil.isNullOrBlank(collation)?" DESC":collation;//默认倒序
		ModelAndView mav = new ModelAndView();
		mav.setViewName("course/course_list");
		mav.addObject(ds.getCourseList( keywords,status ,pIndex,pSize, timeStar, timeEnd,orderBy,collation, channel));
		mav.addObject("keywords", keywords);
		mav.addObject("status", status);
		mav.addObject("pageIndex", pageIndex);
		mav.addObject("pageSize", pageSize);
		mav.addObject("timeStar", timeStar);
		mav.addObject("timeEnd", timeEnd);
		mav.addObject("orderBy", orderBy);
		mav.addObject("collation", collation);
		return mav;
	}

	/**
	 * 课程多条件查询
	 *
	 * @param id 
	 * @return
	 */
	@RequestMapping(value="getStandardList")
	public ModelAndView getStandardList(String keywords,String status ,String pageIndex,String pageSize,
			String timeStar,String timeEnd ,String orderBy,String collation,String channel){
		keywords=StringUtil.isNullOrBlank(keywords)?"":keywords;
		status=StringUtil.isNullOrBlank(status)?"":status;
		pageIndex=	StringUtil.isNullOrBlank(pageIndex)?"1":pageIndex;
		Integer	pIndex=Integer.valueOf(pageIndex);
		pageSize=StringUtil.isNullOrBlank(pageSize)?"10":pageSize;
		Integer	pSize=Integer.valueOf(pageSize);
		orderBy=StringUtil.isNullOrBlank(orderBy)?" finish_time":orderBy;//默认点击量排序
		collation=StringUtil.isNullOrBlank(collation)?" DESC":collation;//默认倒序
		ModelAndView mav = new ModelAndView();
		mav.setViewName("course/course_standard_list");
		mav.addObject(ds.getCourseStandardList( keywords,status ,pIndex,pSize, timeStar, timeEnd,orderBy,collation, channel));
		mav.addObject("keywords", keywords);
		mav.addObject("status", status);
		mav.addObject("pageIndex", pageIndex);
		mav.addObject("pageSize", pageSize);
		mav.addObject("timeStar", timeStar);
		mav.addObject("timeEnd", timeEnd);
		mav.addObject("orderBy", orderBy);
		mav.addObject("collation", collation);
		return mav;
	}
	/**
	 * 添加跳转
	 * @return
	 */
	@RequestMapping(value="addPage")
	public ModelAndView addPage(){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("course/course_add");
		return mav;
	}
	/**
	 * 修改跳转
	 * @return
	 */
	@RequestMapping(value="updatePage")
	public ModelAndView updatePage(String id){
		ModelAndView mav = new ModelAndView();
		mav.addObject("data", ds.getCourseById(id));
		mav.addObject("id", id);
		/*Course c=	(Course)ds.getCourseById(id).getData();
		String channel_id=c.getChannel_id();
		Channel ch3=esht.findFirstOneByPropEq(Channel.class, "id", channel_id);
		Channel ch2=esht.findFirstOneByPropEq(Channel.class, "id", ch3.getPid());
		Channel ch1=esht.findFirstOneByPropEq(Channel.class, "id", ch2.getPid());
		mav.addObject("channel2",ch2);
		mav.addObject("channel1",ch1);
		mav.addObject("channel3",ch3);*/
		List<Channel> list=esht.findAllByPropEq(Channel.class, "pid", "0");
		mav.addObject("list",list);
		mav.setViewName("course/course_update");
		return mav;
	}
	/**
	 * 添加跳转
	 * @return
	 */
	@RequestMapping(value="videoPage")
	public ModelAndView videoPage(String id){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("course/video_list.jsp");
		return mav;
	}
	
	
	/**
	 * 评论跳转
	 * @return
	 */
	@RequestMapping(value="commentPage")
	public ModelAndView commentPage(String id,Integer pageIndex,Integer pageSize,HttpSession session){
		pageIndex=	StringUtil.isNullOrBlank(pageIndex)? 1:pageIndex;
		pageSize=	StringUtil.isNullOrBlank(pageSize)? 10:pageSize;
		ModelAndView mav = new ModelAndView();
		mav.addObject(ds.commnet(id, pageIndex, pageSize, session));
		mav.setViewName("course/comment");
		mav.addObject("pageIndex",pageIndex);
		mav.addObject("pageSize",pageSize);
		return mav;
	}
	/**
	 * 添加
	 * @param name 名称
	 * @param pid 父ID
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="add")
	public Code add(String title,String description,String remark,Integer course_compaign_type,
			String course_compaign_video_url,String pic_big_url,String channel_id,String teacher_id,HttpSession session){
		if(StringUtil.isNullOrBlank(title)) return Code.init(1, "标题必填", null);
		if(StringUtil.isNullOrBlank(course_compaign_type)) return Code.init(1, "封面类型必填", null);
		if(StringUtil.isNullOrBlank(teacher_id)) return Code.init(1, "讲师ID必填", null);
		if(teacherService.getTeacherById(teacher_id).getCode()==2)return Code.init(1, "讲师ID错误,没有此讲师", null);
		if(StringUtil.isNullOrBlank(pic_big_url))return Code.init(1, "封面图片必传", null);
		Course course=new Course();
		course.setTitle(title);
		course.setDescription(description);
		course.setRemark(remark);
		course.setCourse_compaign_type(course_compaign_type);
		course.setCourse_compaign_video_url(course_compaign_video_url);
		course.setPic_big_url(pic_big_url);
		course.setChannel_id(channel_id);
		course.setTeacher_id(teacher_id);
		course.setHomepage_show(Constant.YES_INT);
		course.setPlaying(Statics.COURSE_PLAYING_SHOW);
		course.setTo_home(Constant.YES_INT);
		Code code = ds.add(course,session);
		return code;
	}
	/**
	 * 修改滞销
	 * @param name 名称
	 * @param pid 父ID
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="updateStandard")
	public ModelAndView updateStandard(VideoStandard v,HttpSession session){
		ModelAndView mav = new ModelAndView();
		mav.addObject(ds.updateStandard(v,session));
		return mav;
	}
	/**
	 * 添加滞销
	 * @param name 名称
	 * @param pid 父ID
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="addStandard")
	public ModelAndView addStandard(VideoStandard v,HttpSession session){
		ModelAndView mav = new ModelAndView();
		mav.addObject(ds.addStandard(v,session));
		return mav;
	}
	/**
	 * 修改
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="update")
	public Code update(String id,String title,String description,String remark,Integer course_compaign_type,
			String course_compaign_video_url,String pic_big_url,String channel_id,String teacher_id,HttpSession session){
		if(StringUtil.isNullOrBlank(id)) return Code.init(1, "参数不正确", null);
		if(StringUtil.isNullOrBlank(title)) return Code.init(1, "标题必填", null);
		if(StringUtil.isNullOrBlank(course_compaign_type)) return Code.init(1, "封面类型必填", null);
		if(StringUtil.isNullOrBlank(teacher_id)) return Code.init(1, "讲师ID必填", null);
		if(teacherService.getTeacherById(teacher_id).getCode()==2)return Code.init(1, "讲师ID错误,没有此讲师", null);
		if(StringUtil.isNullOrBlank(pic_big_url))return Code.init(1, "封面图片必传", null);
		return ds.update(id,title,description,remark,course_compaign_type,
				course_compaign_video_url,pic_big_url,channel_id,teacher_id,session);
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
		return ds.upStatus(id,status,session);
	}
	
	
	/**
	 * 评论详情
	 * @param id
	 * @return
	 */
	@RequestMapping(value="commentInfo")
	public ModelAndView  commentInfo(String id){
		Comment com=esht.findFirstOneByPropEq(Comment.class, "id", id);
		String comm_id=com.getCommed_id();
		String user_id=com.getUser_id();
		User user=esht.findFirstOneByPropEq(User.class,"id", user_id);
		Video video=esht.findFirstOneByPropEq(Video.class, "id", comm_id);
		  StringBuffer sb=new StringBuffer();
		  sb.append(" SELECT ");
		  sb.append(" tb_comment.*, tb_video.title, ");
		  sb.append(" tb_user.username ");
		  sb.append(" FROM ");
		  sb.append(" tb_comment ");
		  sb.append(" LEFT JOIN tb_user ON tb_user.id = tb_comment.user_id ");
		  sb.append(" LEFT JOIN tb_video on tb_video.id=tb_comment.commed_id ");
		  sb.append(" WHERE ");
		  sb.append(" tb_comment.commed_id=? ");
		  sb.append(" ORDER BY ");
		  sb.append(" tb_comment.comm_time DESC ");
		  String[] v={id};
		List<Comment> list=esht.createSQLQueryFindAll(sb.toString(),v);
		ModelAndView mav = new ModelAndView();
		mav.addObject("topid",id);
		mav.addObject("list",list);
		mav.addObject("content",com);
		mav.addObject("video",video);
		mav.addObject("user",user);
		mav.setViewName("course/comment_info");
		return mav;
	}
	/**
	 * 课程统计
	 * @param orderby
	 * @return
	 */
	@RequestMapping(value="courseCount")
	public ModelAndView courseCount(String orderBy){
		orderBy=StringUtil.isNullOrBlank(orderBy)?"cli":orderBy;
		ModelAndView mav =new ModelAndView();
		mav.addObject(ds.courseCount(orderBy));
		mav.setViewName("video/void_count");
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
		mav= new ModelAndView("redirect:/course/commentInfo.do?id="+topid+" ");
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
		mav= new ModelAndView("redirect:/course/commentInfo.do?id="+topid+" ");
		return mav;
	}
	
	
	/**
	 * 相关推荐列表
	 * @param page
	 * @param size
	 * @param title
	 * @return
	 */
	@RequestMapping(value="recommendList")
	public ModelAndView recommendList(String page,String size,String count,String id){
		page=StringUtil.isNullOrBlank(page)?"1":page;
		size=StringUtil.isNullOrBlank(size)?"5":size;
		id=StringUtil.isNullOrBlank(id)?"":id;
		ModelAndView mav = new ModelAndView();
		mav.setViewName("line/linepeople/activepeo_list");
		String sql="SELECT c.*,cr.id cid,cr.course_id crid FROM tb_course_recommend cr"
				+ " LEFT JOIN tb_course c  ON c.id=cr.recommend_course_id"
				+ " WHERE cr.course_id ='"+id+"' ";
		int pageindex = Integer.valueOf(page).intValue();
		int pagesize = Integer.valueOf(size).intValue();
		Paging<Map>	p= esht.createSQLQueryfindPage(sql, (pageindex-1)*pagesize, pagesize);
		
		
		mav.addObject("paging",p);
		mav.addObject("id", id);
		mav.addObject("page", page);
		mav.addObject("size", size);
		mav.addObject("count", count);
		mav.setViewName("course/course_recome");
		return mav;
	}

	
	/**
	 * 根据id去删除相关课程
	 * @param id
	 * @return
	 */
	@RequestMapping(value="deleteRecommend")
	public ModelAndView deletejiabin(String id,String cid){
		ModelAndView mav=new ModelAndView();
		int result = os.delete(CourseRecommend.class, id);
		mav= new ModelAndView("redirect:/course/recommendList.do?id="+cid+"");
		return mav;
	}
	/**
	 * 跳转添加相关推荐
	 * @param id
	 * @return
	 */
	@RequestMapping(value="toAddRecommend")
	public ModelAndView toAddpeoActive(String id){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("course/course_recome_add");
		mav.addObject("id",id);
		return mav;
	}
	/**
	 * 添加相关推荐
	 * @param id
	 * @param uid
	 * @param money
	 * @return
	 */
	@RequestMapping(value="saveRecommend")
	public ModelAndView saveJiabin(String cid,String ccid){
		ModelAndView mav = new ModelAndView();
		CourseRecommend cr = new  CourseRecommend();
		cr.setCourse_id(cid);
		cr.setRecommend_course_id(ccid);
		cr.setRecommend_time(System.currentTimeMillis());
		esht.getHibernateTemplate().save(cr);
		
		mav= new ModelAndView("redirect:/course/recommendList.do?id="+cid+"");
		return mav;
	}
	
	
	
}
 
