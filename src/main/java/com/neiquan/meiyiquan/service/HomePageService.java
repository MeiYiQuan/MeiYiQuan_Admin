package com.neiquan.meiyiquan.service;

import com.neiquan.meiyiquan.code.Code;
import com.neiquan.meiyiquan.pojo.HomePage;
import com.qc.util.Condition.Cs;

/**
 * 作者：齐潮
 * 创建日期：2017年3月2日
 * 类说明：处理有关推荐管理的业务逻辑
 */
public interface HomePageService {

	/**
	 * 条件，分页获取推荐集合
	 * @param cs
	 * @param page
	 * @param size
	 * @return
	 */
	Code getCoupons(Cs cs, int page, int size);
	
	/**
	 * 新增推荐信息
	 * @param hp
	 * @param isTop
	 * @return
	 */
	Code save(HomePage hp,int isTop);
	
	/**
	 * 获得置顶数值
	 * @return
	 */
	int getTopNext();
	
	/**
	 * 获得homepage
	 * @param id
	 * @return
	 */
	Code getHomePage(String id);
	
	/**
	 * 更新homepage
	 * @param id
	 * @param name
	 * @param type
	 * @param relation_id
	 * @param isTop
	 * @param pic_url
	 * @return
	 */
	Code update(String id,String name,String type,String relation_id,String isTop,String pic_url);
	
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	Code delete(String id);
	
	/**
	 * 启用禁用
	 * @param id
	 * @param status
	 * @return
	 */
	Code updateStatus(String id,int status);
}
