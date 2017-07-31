package com.neiquan.meiyiquan.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.neiquan.meiyiquan.code.Code;
import com.neiquan.meiyiquan.dao.support.BannerDaoSupport;
import com.neiquan.meiyiquan.dao.support.RecommendCelDaoSupport;
import com.neiquan.meiyiquan.pojo.Banner;
import com.neiquan.meiyiquan.pojo.HomePage;
import com.neiquan.meiyiquan.service.BannerService;
import com.neiquan.meiyiquan.service.ObjectService;
import com.neiquan.meiyiquan.service.RecommendCelService;
import com.neiquan.meiyiquan.util.Statics;
import com.neiquan.meiyiquan.util.StringUtil;
import com.qc.util.Condition.Cs;
import com.qc.util.tag.QcPageType;

/**
 * 作者：温尉棨
 * 创建日期：2017年1月3日
 * 类说明：
 * 推荐管理
 */
@Controller
@Component
@RequestMapping(value="rdcelebrity")
public class RecommendCelebrityController {
	@Autowired
	private ObjectService os;
	@Autowired
	private BannerService bs;
	@Autowired
	private  RecommendCelService rcs;
	@Resource(name="qcpagetype")
	private QcPageType type;
	
	/**
	 * 从左侧导航栏点击推荐名人大佬
	 * @return
	 */
	@RequestMapping(value="toTeacher",method=RequestMethod.GET)
	public ModelAndView toTeacher(){
		ModelAndView mav = toAll(1, Statics.EACHROWS_DEFAULT);
		mav.setViewName("recommend/celebrity/recommend_cel_list");
		return mav;
	}
	/**
	 * 分页查询
	 * @param page
	 * @param size
	 * @return
	 */
	@RequestMapping(value="getTeacher",method=RequestMethod.GET)
	private ModelAndView getTeacher(int page,int size){
		ModelAndView mav = toAll(page, size);
		mav.setViewName("recommend/celebrity/recommend_cel_list");
		return mav;
	}
	/**
	 * 分页查询基方法
	 * @param page
	 * @param size
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private ModelAndView toAll(int page,int size){
		Code code = rcs.getHompage(page, size);
		ModelAndView mav = new ModelAndView();
		mav.addObject("pageType", type);
		mav.addAllObjects(code.getMapData());
		return mav;
	}
	/**
	 * 推荐列表
	 * @param page
	 * @param size
	 * @param count
	 * @param type
	 * @return
	 */
	@RequestMapping(value="homepageList")
	public ModelAndView  homepageList(String page ,String size ,String count,String type){
		page=StringUtil.isNullOrBlank(page)?"1":page;
		size=StringUtil.isNullOrBlank(size)?"5":size;
		type=StringUtil.isNullOrBlank(type)?"":type;
		ModelAndView mav=new ModelAndView();
		mav.addObject(rcs.homepage(page, size, type));
		mav.setViewName("recommend/celebrity/recommend_cel_list");
		mav.addObject("page", page);
		mav.addObject("count", count);
		mav.addObject("size", size);
		mav.addObject("type", type);
		return mav;
	}
	
	/**
	 * 列出未添加首页的讲师
	 * @param page
	 * @param size
	 * @return
	 */
	@RequestMapping(value="toadd",method=RequestMethod.GET)
	public ModelAndView toadd(Integer page,Integer size){
		ModelAndView mav = new ModelAndView();
		if(page==null||size==null){
			page=1;
			size=Statics.EACHROWS_DEFAULT;
		}
		Code code = rcs.getTeacher(page, size);
		mav.addObject("pageType", type);
		mav.addAllObjects(code.getMapData());
		mav.setViewName("recommend/celebrity/recommend_cel_tea");
		return mav;
	}
	/**
	 * 跳转添加页面
	 * @return
	 */
	@RequestMapping(value="tosave",method=RequestMethod.GET)
	public String  tosave(){
		
		return "recommend/celebrity/recommend_cel_add";
	}
	/**
	 * 添加推荐至首页的信息
	 * @param name
	 * @param file0
	 * @param request
	 * @param type
	 * @param relation_id
	 * @param top_num
	 * @param pic_url
	 * @return
	 */
	@RequestMapping(value="saveRecommend",method=RequestMethod.POST)
	public ModelAndView saveRecommend(String name,
			@RequestParam MultipartFile file0,HttpServletRequest request
			,String type,String relation_id,String top_num,String pic_url){
		ModelAndView mav = new ModelAndView();
		Map<String,Object> map = new HashMap();
		
		if(pic_url.contains("blob")){
			try {
				pic_url=bs.updateHead(file0);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else{
		}
		int sum =rcs.top_num(top_num);
		Code result = rcs.saveRecommend(name, type, relation_id, sum, pic_url);
		if(result.getCode()!=1){
			mav.setViewName("jsp");
			mav.addObject("message", result.getMessage());
			return mav;
		}
		mav= new ModelAndView("redirect:/rdcelebrity/homepageList.do");
		return mav;
	}
	/**
	 * 推荐详情
	 * @param id
	 * @return
	 */
	@RequestMapping(value="homeInfo")
	public ModelAndView homeInfo(String id){
		
		ModelAndView mav = new ModelAndView();
		mav.addObject(rcs.homeInfo(id));
		mav.setViewName("recommend/celebrity/recommend_cel_edit");
		return mav;
	}
	
	/**
	 * 修改推荐页面
	 * @param id
	 * @param name
	 * @param file0
	 * @param request
	 * @param type
	 * @param relation_id
	 * @param top_num
	 * @param pic_url
	 * @return
	 */
	@RequestMapping(value="updateHome")
	public ModelAndView updateHome(String id,String name,@RequestParam MultipartFile file0,HttpServletRequest request
			,String type,String relation_id,String top_num,String pic_url){
		
		int sum =rcs.top_num(top_num);
		ModelAndView mav=new ModelAndView();
		Code reslt= rcs.updateHome(id, name, type, relation_id, sum, pic_url);
		mav= new ModelAndView("redirect:/rdcelebrity/homepageList.do");
		return mav;
	}

	
	
	/**
	 * 更新hompage的status，启用和禁用的状态
	 * @param id
	 * @param status
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="upStatus",method=RequestMethod.POST)
	public Code upStatus(String id,int status){
		Map<String, Object> params=new HashMap<String, Object>();
		params.put("status", status);
		int updateTime = os.update(HomePage.class, id, params);
		if(updateTime!=1)
			return Code.init(-1, "更新失败！请刷新页面重试！");
		return Code.init(1, "修改成功！");
	}	
	
	
	
}
