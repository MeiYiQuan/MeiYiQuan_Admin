package com.neiquan.meiyiquan.controller;

import java.io.IOException;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.neiquan.meiyiquan.code.Code;
import com.neiquan.meiyiquan.pojo.Comment;
import com.neiquan.meiyiquan.pojo.Teacher;
import com.neiquan.meiyiquan.pojo.User;
import com.neiquan.meiyiquan.service.BannerService;
import com.neiquan.meiyiquan.service.MessageService;
import com.neiquan.meiyiquan.service.TeacherService;
import com.neiquan.meiyiquan.util.ExtraSpringHibernateTemplate;
import com.neiquan.meiyiquan.util.StringUtil;
import com.qc.util.MathUtil;
import com.qc.util.MathUtil.IsDouble;
import com.qc.util.MathUtil.IsInt;

/**
 * 作者：刘丹
 * 创建日期：2017年1月13日
 * 类说明：讲师管理
 */
@Controller
@RequestMapping(value="teacher")
public class TeacherController {

	@Autowired
	private TeacherService ds;
	@Autowired
	private ExtraSpringHibernateTemplate esht;
	@Autowired
	private MessageService ms;
	@Autowired
	private BannerService bs;
	/**
	 * 讲师多条件查询
	 *
	 * @param id 
	 * @return
	 */
	@RequestMapping(value="getTeacherList")
	public ModelAndView getCourseList(String keywords,String status ,String pageIndex,String pageSize,
			String timeStar,String timeEnd ,String orderBy,String collation,String tree){
		keywords=StringUtil.isNullOrBlank(keywords)?"":keywords;
		status=StringUtil.isNullOrBlank(status)?"":status;
		pageIndex=	StringUtil.isNullOrBlank(pageIndex)?"1":pageIndex;
		Integer	pIndex=Integer.valueOf(pageIndex);
		pageSize=StringUtil.isNullOrBlank(pageSize)?"10":pageSize;
		Integer	pSize=Integer.valueOf(pageSize);
		orderBy=StringUtil.isNullOrBlank(orderBy)?" s.click_count":orderBy;//默认点击量排序
		collation=StringUtil.isNullOrBlank(collation)?" DESC":collation;//默认倒序
		tree=StringUtil.isNullOrBlank(tree)?"":tree;//默认倒序
		ModelAndView mav = new ModelAndView();
		mav.setViewName("teacher/teacher_list");
		mav.addObject(ds.getTeacherList( keywords,status ,pIndex,pSize, timeStar, timeEnd,orderBy,collation,tree));
		mav.addObject("keywords", keywords);
		mav.addObject("status", status);
		mav.addObject("pageIndex", pageIndex);
		mav.addObject("pageSize", pageSize);
		mav.addObject("timeStar", timeStar);
		mav.addObject("timeEnd", timeEnd);
		mav.addObject("orderBy", orderBy);
		mav.addObject("collation", collation);
		mav.addObject("tree", tree);
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
		mav.setViewName("teacher/comment");
		mav.addObject("pageIndex",pageIndex);
		mav.addObject("pageSize",pageSize);
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
		String comm_id=com.getCommed_id();
		String user_id=com.getUser_id();
		User user=esht.findFirstOneByPropEq(User.class,"id", user_id);
		Teacher teacher=esht.findFirstOneByPropEq(Teacher.class, "id", comm_id);
		  StringBuffer sb=new StringBuffer();
		  sb.append(" SELECT ");
		  sb.append(" tb_comment.*,   tb_teacher.`name` AS title, ");
		  sb.append(" tb_user.username ");
		  sb.append(" FROM ");
		  sb.append(" tb_comment ");
		  sb.append(" LEFT JOIN tb_user ON tb_user.id = tb_comment.user_id ");
		  sb.append(" LEFT JOIN tb_teacher ON tb_teacher.teacher_id = tb_comment.commed_id  ");
		  sb.append(" WHERE ");
		  sb.append(" tb_comment.commed_id=? ");
		  sb.append(" ORDER BY ");
		  sb.append(" tb_comment.comm_time DESC ");
		  String[] v={id};
		List<Comment> list=esht.createSQLQueryFindAll(sb.toString(),v);
		ModelAndView mav = new ModelAndView();
		mav.addObject("id",id);
		mav.addObject("list",list);
		mav.addObject("content",com);
		mav.addObject("teacher",teacher);
		mav.addObject("user",user);
		mav.setViewName("course/comment_info");
		return mav;
	}
	/**
	 * 添加跳转
	 * @return
	 */
	@RequestMapping(value="addPage")
	public ModelAndView addPage(){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("teacher/teacher_add");
		mav.addObject("job", ms.job_list("2"));
		return mav;
	}
	/**
	 * 修改跳转
	 * @return
	 */
	@RequestMapping(value="teacherPage")
	public ModelAndView teacherPage(String id,HttpSession session){
		ModelAndView mav = new ModelAndView();
		mav.addObject("data",ds.getTeacherById(id));
		Code code = ms.job_list("2");
		mav.addObject("job", code);
		mav.setViewName("teacher/teacher_update");
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
		public Code update(Long phone ,String password ,String username ,String remark 
				,String tree,HttpSession session,String first_word,String head_url,String job
				,String percent,String make_type,String make_account
				,String top_type,String top_pic_url,String fileData
				){
			if(StringUtil.isNullOrBlank(phone))	return Code.init(1, "手机号不能为空");
			boolean b = Pattern.compile("^1[3|4|5|8|7]\\d{9}$").matcher(String.valueOf(phone)).find();
			if(!b)return Code.init(1, "手机号格式不正确");
			boolean bpass = Pattern.compile("^(?![0-9]+$)(?![a-zA-Z]+$)[a-zA-Z0-9]{6,18}").matcher(password).find();
			if(!bpass) return Code.init( 1, "密码只能是6-18位的数字和字母的组合！");
			if(first_word.length()>1)return Code.init(1, "首字母过长");
			if(StringUtil.isNullOrBlank(first_word))return Code.init(1, "首字母不能为空");
			Code code = validate(percent, make_type, make_account,job);
			
			fileData=StringUtil.isNullOrBlank(fileData)?"":fileData;
			top_pic_url=StringUtil.isNullOrBlank(top_pic_url)?"":top_pic_url;
			if(code.getCode()!=0)
				return code;
			return ds.add(phone , password , username ,  remark,session,tree,first_word,head_url,job, percent, make_type, make_account,top_type,top_pic_url,fileData);
	}
	/**
	 * 修改
	 * @param name 名称
	 * @param pid 父ID
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="update")
	public Code add( String id,String username ,String remark 
			,String tree,HttpSession session,String first_word,String head_url,String job
			,String percent,String make_type,String make_account
			,String top_type,String top_pic_url,String fileData){
		if(first_word.length()>1)return Code.init(1, "首字母过长");
		if(StringUtil.isNullOrBlank(first_word))return Code.init(1, "首字母不能为空");
		Code code = validate(percent, make_type, make_account,job);
		
		fileData=StringUtil.isNullOrBlank(fileData)?"":fileData;
		top_pic_url=StringUtil.isNullOrBlank(top_pic_url)?"":top_pic_url;
		if(code.getCode()!=0)
			return code;
		return ds.update(id, username ,  remark,session,tree,first_word,head_url,job,percent,make_type,make_account,top_type,top_pic_url,fileData);
	}
	
	/**
	 * 添加和修改的部分参数验证
	 * @param percent
	 * @param make_type
	 * @param make_account
	 * @return
	 */
	private Code validate(String percent,String make_type,String make_account,String job){
		if(percent==null||percent.trim().equals(""))
			return Code.init(1, "讲师抽取百分比不能为空！如果使用平台默认百分比，请填写-1");
		if(job==null||job.trim().equals(""))
			return Code.init(1, "讲师等级不能为空！");
		if(make_type==null||make_type.trim().equals(""))
			return Code.init(1, "讲师提现类型不能为空！");
		if(make_account==null||make_account.trim().equals(""))
			return Code.init(1, "讲师提现账号不能为空！");
		IsDouble perIs = MathUtil.isToDouble(percent);
		if(!perIs.is||(perIs.value!=-1&&(perIs.value<0||perIs.value>1)))
			return Code.init(1, "讲师抽取百分比不合法！可以填写0-1之间的数值，默认百分比填写-1。");
		IsInt typeIs = MathUtil.isToInteger(make_type);
		if(!typeIs.is||typeIs.value<1||typeIs.value>5)
			return Code.init(1, "讲师提现类型不合法！");
		return Code.init(0, "");
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
	 * 启用禁用
	 * @param id
	 * @param status
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="updateWorth")
	public Code updateWorth(String id,String worth,HttpSession session){
		return ds.upWorth(id,worth,session);
	}
	/**
	 * 讲师统计
	 * @param order
	 * @return
	 */
	@RequestMapping(value="countTeacher")
	public ModelAndView countTeacher(String orderBy){
		orderBy=StringUtil.isNullOrBlank(orderBy)?"s.click_count":orderBy;
		ModelAndView mav=new ModelAndView();
		mav.addObject(ds.countTeacher(orderBy));
		mav.setViewName("teacher/teacher_count");
		return mav;
	}
	
}
 
