package com.neiquan.meiyiquan.service;

import java.util.List;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.neiquan.meiyiquan.code.Code;
import com.qc.util.Condition.Cs;

/**
 * 作者：齐潮
 * 创建日期：2016年12月14日
 * 类说明：用于处理频道管理的逻辑
 */
public interface ChannelService {
	
	/**
	 * 条件，分页，动态排序去获取channels集合
	 * @param cs
	 * @param page
	 * @param size
	 * @return
	 */
	public Code getChannels(Cs cs, int page, int size);
	/**
	 * 联动查询
	 * @param type
	 * @param upId
	 * @return
	 */
	public List getchannelList(String  type,String upId);
	/**
	 * 一级频道
	 * @return
	 */
	public List getFchannel();
	/**
	 * 二级频道列表
	 * @return
	 */
	public List getSchannel();
	/**
	 * 获取详情
	 * @param id
	 * @return
	 */
	public Code channelInfo(String id);
	/**
	 * 添加
	 * @param name
	 * @param logo_url
	 * @param firstCate
	 * @param secondCate
	 * @return
	 */
	public String  add(String name,String logo_url,String cate,String aid);
	/**
	 * 修改
	 * @param id
	 * @param name
	 * @param logo_url
	 * @param firstCate
	 * @param secondCate
	 * @param file0
	 * @return
	 */
	public String update(String id, String name,String logo_url,String cate,String aid);
	/**
	 * 统计列表	
	 */
	public Code tongji();
	
	/**
	 * 禁用启用频道
	 * @param id
	 * @param status
	 * @param adminId
	 * @return
	 */
	Code updateStatus(String id,int status,String adminId);
	/**
	 * 课程列表
	 * @param id
	 * @return
	 */
	Code courseList(String id,String page,String size,String name);
	
	

}
