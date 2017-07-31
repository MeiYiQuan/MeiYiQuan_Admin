package com.neiquan.meiyiquan.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.neiquan.meiyiquan.code.Code;
import com.neiquan.meiyiquan.pojo.Banner;
import com.neiquan.meiyiquan.pojo.Channel;
import com.neiquan.meiyiquan.pojo.Comment;
import com.neiquan.meiyiquan.pojo.Course;
import com.neiquan.meiyiquan.pojo.User;
import com.neiquan.meiyiquan.pojo.UserVideoRequest;
import com.neiquan.meiyiquan.service.AskCourseService;
import com.neiquan.meiyiquan.service.BannerService;
import com.neiquan.meiyiquan.service.ChannelService;
import com.neiquan.meiyiquan.service.ObjectService;
import com.neiquan.meiyiquan.util.ExtraSpringHibernateTemplate;
import com.neiquan.meiyiquan.util.Statics;
import com.neiquan.meiyiquan.util.StringUtil;


/**
 * 作者：温尉棨
 * 创建日期：2017年1月21日
 * 类说明：求教程
 */
@Controller
@RequestMapping(value="askcourse")
public class AskCourseController {
	@Autowired
	private AskCourseService as;
	@Autowired
	private ExtraSpringHibernateTemplate esht;
	@Autowired
	private ChannelService chs;
	@Autowired
	private BannerService bs;
	@Autowired
	private ObjectService os;
	/**
	 * 已通过求教程教程列表
	 * @param page
	 * @param size
	 * @param count
	 * @param kaystatus
	 * @param kaytxt
	 * @param createBegin
	 * @param createEnd
	 * @param orderBy
	 * @param collation
	 * @param status
	 * @param tree
	 * @return
	 */
	@RequestMapping(value="getAskList")
	public ModelAndView getAskList(String page,String size,String count,String kaystatus,String kaytxt,
			String createBegin,String createEnd,String status,String tree,String orderBy,String collation
			,String firstCate,String secondCate,String thirdCate,String channelid){
		page=StringUtil.isNullOrBlank(page)?"1":page;
		size=StringUtil.isNullOrBlank(size)?"5":size;
		kaystatus=StringUtil.isNullOrBlank(kaystatus)?"":kaystatus;
		kaytxt=StringUtil.isNullOrBlank(kaytxt)?"":kaytxt;
		createBegin=StringUtil.isNullOrBlank(createBegin)?"":createBegin;
		createEnd=StringUtil.isNullOrBlank(createEnd)?"":createEnd;
		orderBy=StringUtil.isNullOrBlank(orderBy)?"uvr.feedback_time":orderBy;
		collation=StringUtil.isNullOrBlank(collation)?"DESC":collation;
		tree=StringUtil.isNullOrBlank(tree)?"":tree;
		firstCate=StringUtil.isNullOrBlank(firstCate)?"":firstCate;
		secondCate=StringUtil.isNullOrBlank(secondCate)?"":secondCate;
		thirdCate=StringUtil.isNullOrBlank(thirdCate)?"":thirdCate;
		channelid=StringUtil.isNullOrBlank(channelid)?"":channelid;
		
		/*String cate="";
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
		}*/
		String channelname="";
		if(!channelid.equals("")){
			Channel cn	=esht.findFirstOneByPropEq(Channel.class,"id" ,channelid);
			channelname=cn.getName();
		}
		ModelAndView mav = new ModelAndView();
		mav.addObject(as.getAskList(page, size, count, kaystatus, kaytxt, createBegin, createEnd, status, tree,orderBy,collation,channelid));
		mav.addObject("page", page);
		mav.addObject("size", size);
		mav.addObject("count", count);
		mav.addObject("createBegin", createBegin);
		mav.addObject("createEnd", createEnd);
		mav.addObject("kaystatus", kaystatus);
		mav.addObject("kaytxt", kaytxt);
		mav.addObject("firstCate", firstCate);
		mav.addObject("status", status);
		List list=chs.getFchannel();
		List list2=chs.getSchannel();
		mav.addObject("list",list);
		mav.addObject("list2",list2);
		/*mav.addObject("firstCate", firstCate);
		mav.addObject("secondCate", secondCate);
		mav.addObject("secCate", secCate);
		mav.addObject("thirdCate", thirdCate);
		mav.addObject("tirCate", tirCate);*/
		mav.addObject("channelid", channelid);
		mav.addObject("channelname", channelname);
		mav.setViewName("ask/ask_course/ask_course_list");
		return mav;
				
	}
	/**
	 * 添加虚拟投票值
	 * @return
	 */
	@RequestMapping(value="addVote")
	public  ModelAndView addVote(String id,String number){
		ModelAndView mav = new ModelAndView();
		Code result=as.addVote(id, number);
		
		mav= new ModelAndView("redirect:/askcourse/getAskList.do");
		return mav;
	}
	/**
	 * 求教程全部列表
	 * @param page
	 * @param size
	 * @param count
	 * @param kaystatus
	 * @param kaytxt
	 * @param createBegin
	 * @param createEnd
	 * @param orderBy
	 * @param collation
	 * @param status
	 * @param tree
	 * @return
	 */
	@RequestMapping(value="getVoteList")
	public  ModelAndView getVoteList(String page,String size,String count,String kaystatus,String kaytxt,String createBegin,String createEnd
			,String status){
		page=StringUtil.isNullOrBlank(page)?"1":page;
		size=StringUtil.isNullOrBlank(size)?"5":size;
		kaystatus=StringUtil.isNullOrBlank(kaystatus)?"":kaystatus;
		kaytxt=StringUtil.isNullOrBlank(kaytxt)?"":kaytxt;
		status=StringUtil.isNullOrBlank(status)?"":status;
		createBegin=StringUtil.isNullOrBlank(createBegin)?"":createBegin;
		createEnd=StringUtil.isNullOrBlank(createEnd)?"":createEnd;
		ModelAndView mav = new ModelAndView();
		mav.addObject(as.review(page, size, count, kaystatus, kaytxt, createBegin, createEnd,status));
		mav.setViewName("ask/ask_course/ask_review_list");
		mav.addObject("page",page);
		mav.addObject("size",size);
		mav.addObject("count",count);
		mav.addObject("kaystatus",kaystatus);
		mav.addObject("kaytxt",kaytxt);
		mav.addObject("createBegin",createBegin);
		mav.addObject("createEnd",createEnd);
		mav.addObject("status",status);
		return mav;
	}
	/**
	 * 求教程每周榜首列表
	 * @param page
	 * @param size
	 * @param count
	 * @param kaystatus
	 * @param kaytxt
	 * @param createBegin
	 * @param createEnd
	 * @return
	 */
	@RequestMapping(value="getTopList")
	public  ModelAndView getTopList(String page,String size,String count,String kaystatus,String kaytxt,String createBegin,String createEnd){
		page=StringUtil.isNullOrBlank(page)?"1":page;
		size=StringUtil.isNullOrBlank(size)?"5":size;
		kaystatus=StringUtil.isNullOrBlank(kaystatus)?"":kaystatus;
		kaytxt=StringUtil.isNullOrBlank(kaytxt)?"":kaytxt;
		createBegin=StringUtil.isNullOrBlank(createBegin)?"":createBegin;
		createEnd=StringUtil.isNullOrBlank(createEnd)?"":createEnd;
		ModelAndView mav = new ModelAndView();
		mav.addObject(as.top(page, size, count, createBegin, createEnd, kaystatus, kaytxt));
		mav.setViewName("ask/ask_course/ask_top_list");
		mav.addObject("page",page);
		mav.addObject("size",size);
		mav.addObject("count",count);
		mav.addObject("kaystatus",kaystatus);
		mav.addObject("kaytxt",kaytxt);
		mav.addObject("createBegin",createBegin);
		mav.addObject("createEnd",createEnd);
		return mav;
	}
	/**
	 * 获取详情
	 * @param id
	 * @return
	 */
	@RequestMapping(value="getInfo")
	public  ModelAndView getInfo(String id ,String backaway){
		ModelAndView mav = new ModelAndView();
		mav.addObject(as.getInfo(id));
		UserVideoRequest uvr=esht.findFirstOneByPropEq(UserVideoRequest.class, "id", id);
		String channel_id=uvr.getChannel_id();
		Channel ch=esht.findFirstOneByPropEq(Channel.class, "id", channel_id);
		String channel_name=ch.getName();
		String secid=ch.getPid();
		Channel ch3=esht.findFirstOneByPropEq(Channel.class, "id", secid);
		String secname= ch3.getName();
		
		String top_channel_id=uvr.getTop_channel_id();
		Channel ch2=esht.findFirstOneByPropEq(Channel.class, "id", top_channel_id);
		String top_channel_name=ch2.getName();
		
		mav.addObject("top_channel_id",top_channel_id);
		mav.addObject("top_channel_name",top_channel_name);
		
		mav.addObject("secid",secid);
		mav.addObject("secname",secname);
		
		mav.addObject("channel_id",channel_id);
		mav.addObject("channel_name",channel_name);
		List list=chs.getFchannel();
		mav.addObject("list",list);
		mav.addObject("backaway", "0");
		mav.setViewName("ask/ask_course/ask_updat");
		return mav;
	}
	/**
	 * 修改求教程详情
	 * @param id
	 * @param firstCate
	 * @param secondCate
	 * @param thirdCate
	 * @param teacher_id
	 * @param question
	 * @param course_name
	 * @param feedback
	 * @param feedback_status
	 * @param pic_url
	 * @return
	 */
	@RequestMapping(value="updateInfo")
	public ModelAndView updateInfo(String id,String firstCate,String secondCate,String thirdCate,String teacher_id,String question
			,@RequestParam MultipartFile file0,String course_name,String feedback,String feedback_status,String pic_url){
		firstCate=StringUtil.isNullOrBlank(firstCate)?"":firstCate;
		secondCate=StringUtil.isNullOrBlank(secondCate)?"":secondCate;
		thirdCate=StringUtil.isNullOrBlank(thirdCate)?"":thirdCate;
		ModelAndView mav = new ModelAndView();
		String cate="";
		String firCate="";
		String secCate="";
		String tirCate="";
		if(!firstCate.equals("na")||!secondCate.equals("na")||!thirdCate.equals("na")){
			if(!firstCate.equals("na")){
				cate=firstCate;
				Channel cn	=esht.findFirstOneByPropEq(Channel.class,"id" ,firstCate);
				firCate=cn.getName();
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
		if(pic_url.contains("blob")){
			try {
			String urlpath=bs.updateHead(file0);
			pic_url=urlpath;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else{
		}
		Integer r=as.updateInfo(id, firstCate, cate, teacher_id, question, course_name, feedback, feedback_status, pic_url);
		if(r==null){
			mav= new ModelAndView("redirect:/askcourse/getInfo.do?id="+id+"&backaway=1 ");
		}
		mav= new ModelAndView("redirect:/askcourse/getVoteList.do");
		
		mav.addObject("firstCate", firstCate);
		mav.addObject("firCate",firCate);
		mav.addObject("secondCate", secondCate);
		mav.addObject("secCate", secCate);
		mav.addObject("thirdCate", thirdCate);
		mav.addObject("tirCate", tirCate);
		return mav;
	}
	/**
	 * 跳转添加页面
	 * @return
	 */
	@RequestMapping(value="tosave")
	public ModelAndView tosave(){
		ModelAndView mav= new ModelAndView();
		List list=chs.getFchannel();
		mav.addObject("list",list);
		mav.addObject("top_channel_id", "");
		mav.addObject("top_channel_name","");
		mav.addObject("secondCate", "");
		mav.addObject("secCate", "");
		mav.addObject("thirdCate", "");
		mav.addObject("tirCate", "");
		mav.setViewName("ask/ask_course/ask_add");
		return mav;
	}
	/**
	 * 添加求教程
	 * @param firstCate
	 * @param secondCate
	 * @param thirdCate
	 * @param teacher_id
	 * @param question
	 * @param file0
	 * @param course_name
	 * @param feedback
	 * @param feedback_status
	 * @param pic_url
	 * @return
	 */
	@RequestMapping(value="save")
	public ModelAndView  save(String firstCate,String secondCate,String thirdCate,String teacher_id,String question
			,@RequestParam MultipartFile file0,String course_name,String feedback,String pic_url,HttpSession session){
		ModelAndView mav = new ModelAndView();
		firstCate=StringUtil.isNullOrBlank(firstCate)?"":firstCate;
		secondCate=StringUtil.isNullOrBlank(secondCate)?"":secondCate;
		thirdCate=StringUtil.isNullOrBlank(thirdCate)?"":thirdCate;
		String cate="";
		String firCate="";
		String secCate="";
		String tirCate="";
		if(!firstCate.equals("na")||!secondCate.equals("na")||!thirdCate.equals("na")){
			if(!firstCate.equals("na")){
				cate=firstCate;
				Channel cn	=esht.findFirstOneByPropEq(Channel.class,"id" ,firstCate);
				firCate=cn.getName();
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
		if(pic_url.contains("blob")){
			try {
			String urlpath=bs.updateHead(file0);
			pic_url=urlpath;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else{
		}
		Map<String, Object> admin = (Map<String, Object>) session.getAttribute("user");
		String adminId = admin.get("id").toString();
		String r=as.save(firstCate, cate, teacher_id, question, course_name, feedback, pic_url,adminId);
		if(r==null){
			mav.setViewName("ask/ask_course/ask_add");
			mav.addObject("question",question);
			mav.addObject("course_name",course_name);
			mav.addObject("feedback",feedback);
			mav.addObject("pic_url",pic_url);
			mav.addObject("firstCate", firstCate);
			mav.addObject("firCate",firCate);
			mav.addObject("secondCate", secondCate);
			mav.addObject("secCate", secCate);
			mav.addObject("thirdCate", thirdCate);
			mav.addObject("tirCate", tirCate);
		}
		mav= new ModelAndView("redirect:/askcourse/getVoteList.do");
		return mav;
	}
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	@RequestMapping(value="delect")
	public ModelAndView delect(String id){
		ModelAndView mav=new ModelAndView();
		int result = os.delete(UserVideoRequest.class, id);
		mav= new ModelAndView("redirect:/askcourse/getVoteList.do");
		return mav;
	}
	
	/**
	 * 评论列表
	 * @param name
	 * @return
	 */
	@RequestMapping(value="commentlist")
	public ModelAndView commentlist(String page,String size,String count,String name){
		page=StringUtil.isNullOrBlank(page)?"1":page;
		size=StringUtil.isNullOrBlank(size)?"5":size;
		name=StringUtil.isNullOrBlank(name)?"":name;
		ModelAndView mav = new ModelAndView();
		mav.addObject(as.commentList(page, size, count, name));
		mav.setViewName("ask/ask_course/ask_comment_list");
		mav.addObject("page",page);
		mav.addObject("size",size);
		mav.addObject("count",count);
		mav.addObject("name",name);
		return mav;
	}
	/**
	 * 评论详情（含二级评论列表）	
	 * @param id
	 * @return
	 */
	@RequestMapping(value="commentInfo")
	public ModelAndView commentInfo(String id){
		Comment com=esht.findFirstOneByPropEq(Comment.class, "id", id);
		String comm_content=com.getComm_content();
		String comm_id=com.getCommed_id();
		String user_id=com.getUser_id();
		User user=esht.findFirstOneByPropEq(User.class,"id", user_id);
		String user_name=user.getUsername();
		UserVideoRequest uvr=esht.findFirstOneByPropEq(UserVideoRequest.class, "id", comm_id);
		String course_name=uvr.getCourse_name();
		Integer status=com.getStatus();
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("topid",id);
		mav.addObject(as.commentInfo(id));
		mav.addObject("comm_content",comm_content);
		mav.addObject("course_name",course_name);
		mav.addObject("status",status);
		mav.addObject("user_name",user_name);
		mav.setViewName("ask/ask_course/ask_comment_info");
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
		mav= new ModelAndView("redirect:/askcourse/commentInfo.do?id="+topid+" ");
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
		mav= new ModelAndView("redirect:/askcourse/commentInfo.do?id="+topid+" ");
		return mav;
	}
	/**
	 * 普通用户   讲师   平台  统计  
	 * @return
	 */
	@RequestMapping(value="typeTongji")
	public ModelAndView  typeTongji(){
		ModelAndView mav=new ModelAndView();
		//普通用户
		long pt=as.typeTongji("2","0");
		//讲师
		long js=as.typeTongji("2","1");
		//平台
		long p=as.typeTongji("1","0");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pt",pt );
		map.put("js",js );
		map.put("p",p );
		mav.addAllObjects(map);
		mav.setViewName("ask/ask_course/ask_tongji_type");
		return mav;
	}
	/**
	 * 点击量  转发量 统计
	 * @return
	 */
	@RequestMapping(value="numTongji")
	public ModelAndView numTongji(String type,String typee){
		ModelAndView mav=new ModelAndView();
		mav.addObject(as.numTongji(type, typee));
		mav.setViewName("ask/ask_course/ask_tongji_num");
		return mav;
	}
	/**
	 * 推送榜首求教程至用户
	 * @param id
	 * @return
	 */
	@RequestMapping(value="push")
	public ModelAndView push(String id){
		ModelAndView mav = getTopList(null, null, null, null, null, null, null);
		Code code = as.push(id);
		mav.addObject("message", code.getMessage());
		mav.addObject("status", "1");
		return mav;
	}
	/**
	 * 
	 * @return
	 */
	@RequestMapping(value="time")
	public ModelAndView time(String type){
		
		return null;
	}
	
}

