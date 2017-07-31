package com.neiquan.meiyiquan.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neiquan.meiyiquan.code.Code;
import com.neiquan.meiyiquan.dao.ObjectDao;
import com.neiquan.meiyiquan.dao.support.SuggestionDaoSupport;
import com.neiquan.meiyiquan.pojo.Activity;
import com.neiquan.meiyiquan.pojo.Banner;
import com.neiquan.meiyiquan.pojo.Channel;
import com.neiquan.meiyiquan.pojo.District;
import com.neiquan.meiyiquan.pojo.Suggestion;
import com.neiquan.meiyiquan.pojo.SystemInfo;
import com.neiquan.meiyiquan.service.SuggestionService;
import com.neiquan.meiyiquan.util.ExtraSpringHibernateTemplate;
import com.neiquan.meiyiquan.util.Paging;
import com.neiquan.meiyiquan.util.Statics;

/**
 * 作者：温尉棨
 * 创建日期：2017年2月5日
 * 类说明：
 */
@Service
public class SuggestionServiceImpl implements SuggestionService{
	@Autowired 
	ExtraSpringHibernateTemplate esht;
	@Autowired
	private ObjectDao od;
	
	/**
	 * 反馈列表
	 */
	@Override
	public Code getList(String page, String size, String kaytxt, String createBegin,
			 String tree,String type,String status) {
		String sql=SuggestionDaoSupport.listSql(kaytxt, createBegin,  tree,type,status);
		int pageindex = Integer.valueOf(page).intValue();
		int pagesize = Integer.valueOf(size).intValue();
		Paging<Map>	p= esht.createSQLQueryfindPage(sql, (pageindex-1)*pagesize, pagesize);
		return Code.init(0, null, p);
	}

	/**
	 * 回访反馈详情
	 */
	@Override
	public Code getInfo(String id) {
		String sql="SELECT s.*,u.username  FROM tb_suggestion s LEFT JOIN tb_user u ON s.user_id=u.id  WHERE s.id='"+id+"' ";
		Paging<Map>	p= esht.createSQLQueryfindPage(sql, 0, 9);
		return Code.init(0, null, p);
	}

	/** 
	 * 回复反馈，添加消息
	 */
	@Override
	public Code updatCount(String recount, String id,String user_id) {
		Map<String,Object> map = new HashMap();
		map.put("user_id", user_id);
		map.put("status", Statics.YES_INT);
		map.put("recount_id",recount);
		map.put("back_time", System.currentTimeMillis());
		od.updateById(Suggestion.class, id, map);
		
	
		return Code.init(1, "添加成功");
	}

	/** 
	 * 反馈统计查询
	 */
	@Override
	public Code tongji(String timeBegin, String timeEnd,String type) {
		String sql=SuggestionDaoSupport.tongji(type,timeBegin, timeEnd);
		Paging<Map>	p= esht.createSQLQueryfindPage(sql,0, 99);
		return Code.init(0, null, p);
	}

	/**
	 * 根据状态统计
	 */
	@Override
	public Code zongtongji(String timeBegin, String timeEnd) {
		String sql=SuggestionDaoSupport.zongtongji("1", timeBegin, timeEnd);
		String sql2=SuggestionDaoSupport.zongtongji("0", timeBegin, timeEnd);
		Long count=esht.createSQLQueryCount(sql);
		Long count2=esht.createSQLQueryCount(sql2);
		Map<String,Object> map= new HashMap<String,Object>();
		map.put("countt", count);
		map.put("countt2", count2);
		return Code.init(0, null, map);
	}

}
