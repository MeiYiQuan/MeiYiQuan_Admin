package com.neiquan.meiyiquan.service;

import com.neiquan.meiyiquan.code.Code;
import com.qc.util.Condition.Cs;

/**
 * 作者：温尉棨
 * 创建日期：2017年1月4日
 * 类说明：
 * 
 */
public interface RecommendCelService {
	/**
	 * 
	 * @param page
	 * @param size
	 * @return
	 */
	public Code  getTeacher(int page, int size);
	/**
	 * 已添至推荐首页的数据
	 * @param page
	 * @param size
	 * @return
	 */
	public Code getHompage(int page, int size);
	/**
	 * 添加推荐数据
	 * @return
	 */
	public Code saveRecommend(String name,String type,String relation_id,int top_num,String pic_url);
	/**
	 * 修改推荐数据
	 * @param name
	 * @param type
	 * @param relation_id
	 * @param top_num
	 * @param pic_url
	 * @return
	 */
	public Code updateHome(String id,String name,String type,String relation_id,int top_num,String pic_url);
	/**
	 * 获取排序值
	 * @param top_num
	 * @return
	 */
	public int top_num(String top_num);
	/**
	 * 推荐首页列表
	 * @param page
	 * @param size
	 * @param type
	 * @return
	 */
	public Code homepage(String page,String size,String type);
	
    /**
     * 推荐详情
     * @param id
     * @return
     */
	public Code homeInfo(String id);
}
