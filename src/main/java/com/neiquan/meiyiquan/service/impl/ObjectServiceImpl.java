package com.neiquan.meiyiquan.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neiquan.meiyiquan.dao.ObjectDao;
import com.neiquan.meiyiquan.service.ObjectService;
import com.qc.util.DateFormate;

/**
 * 作者：齐潮
 * 创建日期：2016年12月14日
 * 类说明：
 */
@Service
public class ObjectServiceImpl implements ObjectService {

	@Autowired
	private ObjectDao od;
	
	@Override
	public int update(Class clas, String id, Map<String, Object> params) {
		int result = od.updateById(clas, id, params);
		return result;
	}

	@Override
	public int delete(Class clas, String id) {
		int result = od.deleteById(clas, id);
		return result;
	}

	@Override
	public String create(Object obj) {
		String id = od.save(obj);
		return id;
	}

	@Override
	public String updateReturnDate(Class clas, String id, Map<String, Object> params, String dateProName) {
		long now = System.currentTimeMillis();
		params.put(dateProName, now);
		int result = od.updateById(clas, id, params);
		if(result==1)
			return DateFormate.getDateFormateCH(now);
		return null;
	}

	@Override
	public String updateOneProReturnDate(Class clas, String id, String proName, Object value, String dateProName) {
		Map<String,Object> params = new HashMap<String,Object>();
		long now = System.currentTimeMillis();
		params.put(proName, value);
		params.put(dateProName, now);
		int result = od.updateById(clas, id, params);
		if(result==1)
			return DateFormate.getDateFormateCH(now);
		return null;
	}

	@Override
	public List<Map<String, Object>> getAll(Class clas) {
		List<Map<String, Object>> result = od.getPosForMap(clas, null, null, null);
		return result;
	}

	@Override
	public List<Map<String, Object>> getAllBySql(String sql, Map<String, Object> params) {
		List<Map<String, Object>> result = od.getListBySql(sql, params, null, null);
		return result;
	}


}
