package com.neiquan.meiyiquan.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

import com.neiquan.meiyiquan.code.Code;
import com.neiquan.meiyiquan.dao.ObjectDao;
import com.neiquan.meiyiquan.util.Page;
import com.neiquan.meiyiquan.util.Sql;
import com.qc.dao.BD;
import com.qc.util.Table;
import com.qc.util.Tables;

/**
 * 作者：齐潮
 * 创建日期：2016年12月26日
 * 类说明：
 */
@Repository
public class ObjectDaoImpl extends BD implements ObjectDao {

	@Override
	public List<Map<String, Object>> getListBySql(String sql, Map<String, Object> params, Integer start, Integer size) {
		return getManyTablesBySql(sql, params, start, size);
	}

	@Override
	public int getObjCountBySql(String sql, Map<String, Object> params) {
		return getCountBySql(sql,params);
	}

	@Override
	public Map<String, Object> getObjectBySql(String sql, Map<String, Object> params) {
		return getManyTablesFirstOneBySql(sql, params);
	}

	@Override
	public String save(Class clas, Map<String, Object> pojo) {
		return savePojoForDefault(clas, pojo);
	}

	@Override
	public int update(Class clas, Map<String, Object> conditions, Map<String, Object> settings) {
		return updatePojoByConditions(clas, conditions, settings);
	}

	@Override
	public int updateById(Class clas, String id, Map<String, Object> settings) {
		return updatePojoById(clas, id, settings);
	}

	@Override
	public int delete(Class clas, Map<String, Object> conditions) {
		Table table = Tables.getTable(clas);
		String tableName = "`" + table.getTableName() + "`";
		StringBuffer sql = new StringBuffer("delete from " + tableName + " where 1 = 1 ");
		Set<Entry<String,Object>> set = conditions.entrySet();
		for(Entry<String,Object> en:set){
			String proName = en.getKey();
			String colName = "`" + table.getColName(proName) + "`";
			sql.append("and " + colName + " = :" + proName + " ");
		}
		int result = updateBySql(sql.toString(), conditions);
		return result;
	}

	@Override
	public int deleteById(Class clas, String id) {
		Table table = Tables.getTable(clas);
		String tableName = "`" + table.getTableName() + "`";
		String idProName = table.getIdName();
		String idColName = "`" + table.getColName(idProName) + "`";
		String sql = "delete from " + tableName + " where " + idColName + " = :" + idProName;
		Map<String,Object> params = new HashMap<String,Object>();
		params.put(idProName, id);
		int result = updateBySql(sql, params);
		return result;
	}

	@Override
	public <T> List<T> getPos(Class<T> clas, Map<String, Object> params, Integer start, Integer size,Order... orders) {
		return getPojosListByParams(clas, params, start, size, orders);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public int getPosCount(Class clas, Map<String, Object> params) {
		return getPojosCountByParams(clas, params);
	}

	@Override
	public List<Map<String, Object>> getPosForMap(Class clas, Map<String, Object> params, Integer start, Integer size,
			Order... orders) {
		return getPojosListByParamsForMap(clas, params, start, size, orders);
	}

	@Override
	public Code getObjects(String countSql,String selectSql,Map<String,Object> params, int page,int eachRows) {
		int count = getCountBySql(countSql,params);
		Page p = Page.init(page, eachRows, count);
		List<Map<String, Object>> list = getManyTablesBySql(selectSql, params, p.getStartIndex(), p.getEachRows());
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("page", p);
		map.put("result", list);
		Code result = Code.init(1, null, map);
		return result;
	}

	@Override
	public String save(Object obj) {
		String id = savePojo(obj);
		return id;
	}

	@Override
	public Map<String, Object> getObjByIdForMap(Class clas, String id) {
		return getPojoByIdForMap(clas, id);
	}

	@Override
	public <T> T getObjById(Class<T> clas, String id) {
		return getPojoById(clas, id);
	}

	@Override
	public <T> T getObjByParams(Class<T> clas, Map<String, Object> params) {
		return getPojoByParams(clas, params);
	}

	@Override
	public void update(Object obj) {
		getTemplate().update(obj);
	}

	@Override
	public int updateBySql(Sql sql) {
		return updateBySql(sql.getSql(), sql.getParams());
	}
	
	@Override
	public int saveObjects(Class clas,List<Map<String, Object>> objs) {
		return savePojosForDefault(clas, objs);
	}
}
