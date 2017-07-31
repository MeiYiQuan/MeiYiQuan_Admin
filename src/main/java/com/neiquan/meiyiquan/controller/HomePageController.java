package com.neiquan.meiyiquan.controller;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.neiquan.meiyiquan.code.Code;
import com.neiquan.meiyiquan.dao.support.HomePageSupport;
import com.neiquan.meiyiquan.pojo.HomePage;
import com.neiquan.meiyiquan.service.HomePageService;
import com.neiquan.meiyiquan.util.Constant;
import com.neiquan.meiyiquan.util.Statics;
import com.qc.util.Condition.Cs;
import com.qc.util.MathUtil;
import com.qc.util.MathUtil.IsInt;
import com.qc.util.tag.QcPageType;

/**
 * 作者：齐潮
 * 创建日期：2017年3月2日
 * 类说明：处理有关推荐管理的请求
 */
@Controller
@RequestMapping(value="homepage")
public class HomePageController {
	
	@Resource(name="qcpagetype")
	private QcPageType pageType;
	
	@Autowired
	private HomePageService hs;
	
	/**
	 * 通过左侧导航栏进入推荐管理列表
	 * @return
	 */
	@RequestMapping(value="getHomePages")
	public ModelAndView getHomePages(){
		ModelAndView mav = toAll(0, Statics.EACHROWS_DEFAULT, null, null, null);
		mav.setViewName("homepage/homepage_list");
		return mav;
	}
	
	
	/**
	 * 通过条件搜索，刷新，分页进入推荐管理列表
	 * @param page
	 * @param size
	 * @param type
	 * @param status
	 * @param order
	 * @return
	 */
	@RequestMapping(value="getList")
	public ModelAndView getHomePages(int page,int size,String type,String status,String order){
		ModelAndView mav = toAll(page, size, type, status, order);
		mav.setViewName("homepage/homepage_list");
		return mav;
	}
	
	
	/**
	 * 获取推荐列表的核心方法
	 * @param page
	 * @param size
	 * @param type
	 * @param status
	 * @param order
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private ModelAndView toAll(int page,int size,String type,String status,String order){
		Cs cs = HomePageSupport.getCs(type, status, order);
		Code code = hs.getCoupons(cs, page, size);
		ModelAndView mav = new ModelAndView();
		mav.addAllObjects(code.getMapData());
		mav.addObject("pageType", pageType);
		mav.addObject("type", type);
		mav.addObject("status", status);
		mav.addObject("order", order);
		return mav;
	}
	
	/**
	 * 预添加
	 * @return
	 */
	@RequestMapping(value="toadd")
	public ModelAndView willAdd(){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("homepage/homepage_add");
		return mav;
	}
	
	/**
	 * 添加一个homepage
	 * @param name
	 * @param type
	 * @param relation_id
	 * @param isTop
	 * @param pic_url
	 * @return
	 */
	@RequestMapping(value="add")
	public ModelAndView add(String name,String type,String relation_id,String isTop,String pic_url){
		ModelAndView mav = null;
		name="";
		Code code = validate(name, type, relation_id, isTop, pic_url);
		if(code.getCode()!=0){
			mav = willAdd();
			mav.addObject("message", code.getMessage());
			return mav;
		}
		long now = System.currentTimeMillis();
		HomePage hp = new HomePage();
		hp.setCreatTime(now);
		hp.setName(name);
		hp.setPic_url(pic_url);
		hp.setRelation_id(relation_id);
		hp.setStatus(Constant.NO_INT);
		hp.setType(Integer.parseInt(type));
		hp.setUpdateTime(now);
		code = hs.save(hp, Integer.parseInt(isTop));
		mav = getHomePages();
		mav.addObject("message", code.getMessage());
		return mav;
	}
	
	/**
	 * 预修改
	 * @return
	 */
	@RequestMapping(value="willUpdate")
	public ModelAndView willUpdate(String id){
		ModelAndView mav = null;
		if(id==null||id.trim().equals("")){
			mav = getHomePages();
			mav.addObject("message", "参数缺失！");
			return mav;
		}
		Code code = hs.getHomePage(id);
		if(code.getCode()!=0){
			mav = getHomePages();
			mav.addObject("message", code.getMessage());
			return mav;
		}
		mav = new ModelAndView();
		mav.addObject("object", code.getData());
		mav.setViewName("homepage/homepage_edit");
		return mav;
	}
	
	/**
	 * 修改
	 * @param id
	 * @param name
	 * @param type
	 * @param relation_id
	 * @param isTop
	 * @param pic_url
	 * @return
	 */
	@RequestMapping(value="update")
	public ModelAndView update(String id,String name,String type,String relation_id,String isTop,String pic_url){
		ModelAndView mav = null;
		if(id==null||id.trim().equals("")){
			mav = getHomePages();
			mav.addObject("message", "参数缺失！");
			return mav;
		}
		Code code = validate(name, type, relation_id, isTop, pic_url);
		if(code.getCode()!=0){
			mav = willUpdate(id);
			mav.addObject("message", code.getMessage());
			return mav;
		}
		code = hs.update(id, name, type, relation_id, isTop, pic_url);
		mav = getHomePages();
		mav.addObject("message", code.getMessage());
		return mav;
	}
	
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="delete")
	public Code delete(String id){
		if(id==null||id.trim().equals(""))
			return Code.init(-1, "参数缺失！");
		Code code = hs.delete(id);
		return code;
	}
	
	/**
	 * 启用和禁用
	 * @param id
	 * @param status
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="updateStatus")
	public Code updateStatus(String id,int status){
		if(id==null||id.trim().equals(""))
			return Code.init(-1, "参数缺失！");
		if(status!=Constant.YES_INT&&status!=Constant.NO_INT)
			return Code.init(-1, "参数不合法！");
		Code code = hs.updateStatus(id, status);
		return code;
	}
	
	/**
	 * 添加和修改时的验证
	 * @param name
	 * @param type
	 * @param relation_id
	 * @param isTop
	 * @param pic_url
	 * @return
	 */
	private Code validate(String name,String type,String relation_id,String isTop,String pic_url){
		/*if(name==null||name.trim().equals(""))
			return Code.init(-1, "推荐名称不能为空！");*/
		if(type==null||type.trim().equals(""))
			return Code.init(-1, "推荐类型不能为空！");
		IsInt typeI = MathUtil.isToInteger(type);
		if(typeI.value!=1&&typeI.value!=2&&typeI.value!=3&&typeI.value!=4)
			return Code.init(-1, "推荐类型不合法！");
		if(relation_id==null||relation_id.trim().equals(""))
			return Code.init(-1, "关联ID不能为空！");
		if(isTop==null||isTop.trim().equals(""))
			return Code.init(-1, "置顶参数不能为空！");
		IsInt isTopI = MathUtil.isToInteger(isTop);
		if(isTopI.value!=1&&isTopI.value!=2)
			return Code.init(-1, "置顶参数不合法！");
		if(pic_url==null||pic_url.trim().equals(""))
			return Code.init(-1, "图片地址不能为空！");
		return Code.init(0, null);
	}
	
}
