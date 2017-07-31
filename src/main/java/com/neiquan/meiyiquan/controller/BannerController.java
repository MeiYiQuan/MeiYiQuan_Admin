package com.neiquan.meiyiquan.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.neiquan.meiyiquan.code.Code;
import com.neiquan.meiyiquan.dao.support.BannerDaoSupport;
import com.neiquan.meiyiquan.pojo.Banner;
import com.neiquan.meiyiquan.service.BannerService;
import com.neiquan.meiyiquan.service.ObjectService;
import com.neiquan.meiyiquan.util.Statics;
import com.qc.util.Condition.Cs;
import com.qc.util.tag.QcPageType;

/**
 * 作者：齐潮
 * 创建日期：2016年12月12日
 * 类说明：处理有关主页的请求
 */
@Controller
@RequestMapping(value="banner")
public class BannerController {

	@Autowired
	private BannerService bs;
	
	@Autowired
	private ObjectService os;
	
	
	
	@Resource(name="qcpagetype")
	private QcPageType qctype;
	
	/**
	 * 从左侧导航栏点击首页管理
	 * @return
	 */
	@RequestMapping(value="toBanners",method=RequestMethod.GET)
	public ModelAndView toBanners(){
		ModelAndView mav = toAll(1, Statics.EACHROWS_DEFAULT, null, null, null,null, null, null, null, null, null,null);
		mav.setViewName("banner/banner/banner_list");
		return mav;
	}
	
	/**
	 * 条件分页排序查询banner结果集
	 * @param page
	 * @param size
	 * @param bannerType
	 * @param status
	 * @param createBegin
	 * @param createEnd
	 * @param updateBegin
	 * @param updateEnd
	 * @return
	 */
	@RequestMapping(value="banners",method=RequestMethod.GET)
	public ModelAndView getBanners(int page,int size,String orderNum,String createTime,String updateTime,String bannerType,String status,String createBegin,String createEnd,
			String updateBegin,String updateEnd,String type){
		ModelAndView mav = toAll(page, size,orderNum,createTime,updateTime, bannerType, status, createBegin, createEnd, updateBegin, updateEnd,type);
		mav.setViewName("banner/banner/banner_list");
		return mav;
	}
	
	
	/**
	 * 首页页面条件查询的基方法
	 * @param page
	 * @param size
	 * @param bannerType
	 * @param status
	 * @param createBegin
	 * @param createEnd
	 * @param updateBegin
	 * @param updateEnd
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private ModelAndView toAll(int page,int size,String orderNum,String createTime,String updateTime,String bannerType,String status,String createBegin,String createEnd,
			String updateBegin,String updateEnd,String type){
		Cs cs = BannerDaoSupport.getCs(orderNum,createTime,updateTime,bannerType, status, createBegin, createEnd, updateBegin, updateEnd,type);
		Code code = bs.getBanners(cs, page, size);
		ModelAndView mav = new ModelAndView();
		mav.addAllObjects(code.getMapData());
		mav.addObject("orderNum", orderNum);
		mav.addObject("createTime", createTime);
		mav.addObject("updateTime", updateTime);
		mav.addObject("pageType", qctype);
		mav.addObject("bannerType", bannerType);
		mav.addObject("status", status);
		mav.addObject("type", type);
		mav.addObject("createBegin", createBegin);
		mav.addObject("createEnd", createEnd);
		mav.addObject("updateBegin", updateBegin);
		mav.addObject("updateEnd", updateEnd);
		return mav;
	}
	
	/**
	 * 发现页面条件分页排序查询banner结果集
	 * @param page
	 * @param size
	 * @param bannerType
	 * @param status
	 * @param createBegin
	 * @param createEnd
	 * @param updateBegin
	 * @param updateEnd
	 * @return
	 */
	@RequestMapping(value="fbanners",method=RequestMethod.GET)
	public ModelAndView getFBanners(Integer page,Integer size,String orderNum,String createTime,String updateTime,String bannerType,String status,String createBegin,String createEnd,
			String updateBegin,String updateEnd,String type){
		if(page==null||size==null){
			page=1;
			size=Statics.EACHROWS_DEFAULT;
		}
		ModelAndView mav = toFAll(page, size,orderNum,createTime,updateTime, bannerType, status, createBegin, createEnd, updateBegin, updateEnd,type);
		mav.setViewName("banner/banner/banner_find_list");
		return mav;
	}
	
	/**
	 * 发现页面条件查询的基方法
	 * @param page
	 * @param size
	 * @param bannerType
	 * @param status
	 * @param createBegin
	 * @param createEnd
	 * @param updateBegin
	 * @param updateEnd
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private ModelAndView toFAll(int page,int size,String orderNum,String createTime,String updateTime,String bannerType,String status,String createBegin,String createEnd,
			String updateBegin,String updateEnd,String type){
		Cs cs = BannerDaoSupport.getCs(orderNum,createTime,updateTime,bannerType, status, createBegin, createEnd, updateBegin, updateEnd,type);
		Code code = bs.getFBanners(cs, page, size);
		ModelAndView mav = new ModelAndView();
		mav.addAllObjects(code.getMapData());
		mav.addObject("orderNum", orderNum);
		mav.addObject("createTime", createTime);
		mav.addObject("updateTime", updateTime);
		mav.addObject("pageType", qctype);
		mav.addObject("bannerType", bannerType);
		mav.addObject("status", status);
		mav.addObject("type", type);
		mav.addObject("createBegin", createBegin);
		mav.addObject("createEnd", createEnd);
		mav.addObject("updateBegin", updateBegin);
		mav.addObject("updateEnd", updateEnd);
		return mav;
	}
	
	/**
	 * 更新banner的status，启用和禁用的状态
	 * @param id
	 * @param status
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="upStatus",method=RequestMethod.POST)
	public Code upStatus(String id,int status){
		String updateTime = os.updateOneProReturnDate(Banner.class, id, "status", status, "update_time");
		if(updateTime==null)
			return Code.init(-1, "更新失败！请刷新页面重试！");
		return Code.init(1, "修改成功！", updateTime);
	}
	
	/**
	 * 删除一个banner
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="deleteBanner",method=RequestMethod.POST)
	public Code delete(String id){
		int result = os.delete(Banner.class, id);
		if(result<1)
			return Code.init(-1, "删除失败！请刷新页面重试！");
		return Code.init(1, "删除成功！");
	}
	/**
	 * 通过id获取详情
	 * @param id
	 * @return
	 */
	@RequestMapping(value="toEditBanner")
	public  ModelAndView toEditBanner(String id){
		ModelAndView mav = new ModelAndView();
		Code result = bs.infoBanners(id);
		if(result.getCode()!=1){
			mav.setViewName("jsp");
			mav.addObject("message", result.getMessage());
			return mav;
		}
		mav.addAllObjects(result.getMapData());
		mav.setViewName("banner/banner/banner_edit");
		
		return mav;
	}
	/**
	 * 根据id修改banner详情
	 * @param id
	 * @param name
	 * @return
	 * @RequestParam(value = "file", required = false) MultipartFile file,HttpServletRequest request
	 */
	@RequestMapping(value="EditBanner",method=RequestMethod.POST)
	public ModelAndView  upAll(String id,String name,@RequestParam MultipartFile file0,
			HttpServletRequest request,String order_num,String pic_save_url,String remark,
			String jump_type,String jump_id,String showtype){
		
		ModelAndView mav = new ModelAndView();
	/*	Map<String,Object> map = new HashMap();
		map.put("name",name );
		map.put("jump_id", jump_id);
		map.put("remark",remark );
		int order_numm=bs.order_num(order_num);
		map.put("order_num",order_numm );
		int i=Integer.valueOf(jump_type).intValue();
		map.put("jump_type",i );*/
		
		int order_numm=bs.order_num(order_num);
		
		if(pic_save_url.contains("blob")){
			try {
			String urlpath=bs.updateHead(file0);
			pic_save_url=urlpath;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		bs.updateBanner(id, name, order_numm, pic_save_url, remark, jump_type, jump_id,showtype);
		mav= toBanners();
		mav.addObject("message", "修改成功！");
		return mav;
		
	}
	/**
	 * 跳转页面
	 * @return
	 */
	@RequestMapping(value="toadd",method=RequestMethod.GET)
	public String toadd(){
		return "banner/banner/banner_add";
	}
	/**
	 * 新增
	 * @param id
	 * @param name
	 * @param file0
	 * @param request
	 * @param order_num
	 * @param pic_save_url
	 * @param remark
	 * @param jump_type
	 * @param pic_redirect_url
	 * @return
	 */
	@RequestMapping(value="addBanner")
	public ModelAndView addBanner(String id,String name,@RequestParam MultipartFile file0,
			HttpServletRequest request,String order_num,String pic_save_url,String remark,
			String jump_type,String jump_id,String type){
		ModelAndView mav = new ModelAndView();
		
		int order_numm=bs.order_num(order_num);
		
		if(pic_save_url.contains("blob")){
			try {
			String urlpath=bs.updateHead(file0);
			pic_save_url=urlpath;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		bs.addbanner(name, order_numm, pic_save_url, remark, jump_type, jump_id,type);
		mav= new ModelAndView("redirect:/banner/toBanners.do");
		return mav;
		
	}
	
	/**
	 * 公共参数列表
	 * @return
	 */
	@RequestMapping(value="publicList")
	public ModelAndView public_parameter(){
		ModelAndView mav= new ModelAndView();
		mav.addObject(bs.public_paratemer());
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
		mav.addAllObjects(bs.publicInfo(id).getMapData());
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
	public ModelAndView publicUpdat(String id,String title,String sys_value,@RequestParam MultipartFile file0,String type){
		ModelAndView mav=new ModelAndView();
		if(type.equals("1")){
			if(sys_value.contains("blob")){
				try {
					sys_value=bs.updateHead(file0);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		long i=bs.publicUpdate(id, title, sys_value); 
		mav= new ModelAndView("redirect:/banner/publicList.do");
		return mav;
	}

}

