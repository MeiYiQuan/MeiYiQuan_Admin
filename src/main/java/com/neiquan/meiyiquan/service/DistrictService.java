package com.neiquan.meiyiquan.service;

import javax.servlet.http.HttpSession;

import com.neiquan.meiyiquan.code.Code;
/**
 * 作者：刘丹
 * 创建日期：2017年1月7日
 * 类说明：区域管理模块
 */
public interface DistrictService {
	
	/**
	 *  区域查询
	 * @param cs
	 * @return
	 */
	Code getDistrictsList(String keywords, String status);
	/**
	 *  区域查询子节点
	 * @param id
	 * @return
	 */
	Code getDistrictsChilds(String id);
	/**
	 * 添加
	 * @param name
	 * @return
	 */
	Code add(String  name,String pid ,HttpSession session);
	/**
	 * 修改
	 * @param name
	 * @return
	 */
	Code update(String name, String id, HttpSession session);
	/**
	 * 修改状态
	 * @param name
	 * @return
	 */
	 Code upStatus(String id,String  status,HttpSession session);
	/**
	 * 删除节点及全部子节点
	 * @param name 名称
	 * @param pid 父ID
	 * @return
	 */
	Code delAll(String id);
	
	
}
