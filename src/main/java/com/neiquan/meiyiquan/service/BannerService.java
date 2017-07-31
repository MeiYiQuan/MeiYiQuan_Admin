package com.neiquan.meiyiquan.service;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.neiquan.meiyiquan.code.Code;
import com.qc.util.Condition.Cs;

/**
 * 作者：齐潮
 * 创建日期：2016年12月12日
 * 类说明：用于处理首页的业务逻辑
 */
public interface BannerService {
	
	/**
	 * 条件，分页，动态排序去获取首页banners集合
	 * @param cs
	 * @param page
	 * @param size
	 * @return
	 */
	public Code getBanners(Cs cs, int page, int size);
	/**
	 * 条件，分页，动态排序去获取发现banners集合
	 * @param cs
	 * @param page
	 * @param size
	 * @return
	 */
	public Code getFBanners(Cs cs, int page, int size);
	/**
	 * 根据id获取详情
	 * @param id
	 * @return
	 */
	public Code infoBanners(String id);
	/**
	 * 上传图片至oss
	 * @param file
	 * @return
	 */
	String updateHead(MultipartFile file ) throws IOException;
	/**
	 * 添加banner
	 * @param name
	 * @param order_num
	 * @param pic_save_url
	 * @param remark
	 * @param jump_type
	 * @param jump_id
	 * @param showtype
	 * @return
	 */
	public Code addbanner(String name,Integer order_num,String pic_save_url,String remark,
			String jump_type,String jump_id,String type);
	/**
	 * 获取当前图片总数
	 * @param order_num
	 * @return
	 */
	public int  order_num(String order_num);
	/**
	 * 平台参数
	 * @return
	 */
	public Code public_paratemer();
	/**
	 * 平台详情
	 * @param id
	 * @return
	 */
	public Code publicInfo(String id);
	/**
	 * 修改公共参数
	 * @param id
	 * @param parameter_name
	 * @param pic_url
	 * @return
	 */
	public long publicUpdate(String id,String parameter_name,String pic_url);
	/**
	 * 修改banner图
	 * @param id
	 * @param name
	 * @param order_num
	 * @param pic_save_url
	 * @param remark
	 * @param jump_type
	 * @param jump_id
	 * @param showtype
	 * @return
	 */
	public Code updateBanner(String id,String name,Integer order_num,String pic_save_url,String remark,
			String jump_type,String jump_id,String showtype);
}
