package com.neiquan.meiyiquan.service;

import java.util.List;
import java.util.Map;

/**
 * 作者：齐潮
 * 创建日期：2016年12月14日
 * 类说明：处理一些公用的业务逻辑，比如条件获取集合，删除，修改，创建
 */
public interface ObjectService {
	
	/**
	 * 修改一个信息
	 * @param clas
	 * @param id
	 * @param params
	 * @return
	 */
	public int update(Class clas,String id,Map<String,Object> params);
	
	/**
	 * 修改一个信息，并直接返回这个信息的修改日期。
	 * 此方法不用在controller层填写更新日期的字段。
	 * 这里dateProName不能传入null。
	 * 如果返回null，表示没有更新成功。
	 * @param clas
	 * @param id
	 * @param params
	 * @param dateProName
	 * @return
	 */
	public String updateReturnDate(Class clas,String id,Map<String,Object> params,String dateProName);
	
	/**
	 * 修改一个信息，并直接返回这个信息的修改日期。
	 * 适用于修改单个字段。
	 * 此方法不用在controller层填写更新日期的字段。
	 * 这里dateProName不能传入null。
	 * 如果返回null，表示没有更新成功。
	 * @param clas
	 * @param id
	 * @param proName
	 * @param value
	 * @param dateProName
	 * @return
	 */
	public String updateOneProReturnDate(Class clas,String id,String proName,Object value,String dateProName);
	
	/**
	 * 通过id去删除一个信息
	 * @param clas
	 * @param id
	 * @return
	 */
	public int delete(Class clas,String id);
	
	/**
	 * 添加一条信息
	 * @param obj
	 * @return
	 */
	public String create(Object obj);
	
	/**
	 * 获得某表中所有的信息
	 * @param clas
	 * @return
	 */
	public List<Map<String,Object>> getAll(Class clas);
	
	/**
	 * 通过sql语句去获取所有的信息
	 * @param sql
	 * @param params
	 * @return
	 */
	public List<Map<String,Object>> getAllBySql(String sql,Map<String,Object> params);
}
