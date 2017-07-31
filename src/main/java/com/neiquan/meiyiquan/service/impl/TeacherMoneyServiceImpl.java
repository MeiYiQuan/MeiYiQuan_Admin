package com.neiquan.meiyiquan.service.impl;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neiquan.meiyiquan.code.Code;
import com.neiquan.meiyiquan.dao.ObjectDao;
import com.neiquan.meiyiquan.dao.support.AskCourseDaoSupport;
import com.neiquan.meiyiquan.dao.support.TercherMoneyDaoSupport;
import com.neiquan.meiyiquan.pojo.TeacherSend;
import com.neiquan.meiyiquan.service.TeacherMoneyService;
import com.neiquan.meiyiquan.util.ExtraSpringHibernateTemplate;
import com.neiquan.meiyiquan.util.Paging;

/**
 * 作者：温尉棨
 * 创建日期：2017年2月27日
 * 类说明：
 */
@Service
public class TeacherMoneyServiceImpl implements TeacherMoneyService {

	@Autowired
	private ExtraSpringHibernateTemplate esht;
	@Autowired
	private ObjectDao od;
	/**
	 * 讲师收益列表
	 */
	@Override
	public Code tmList(String page, String size, String name) {
		String sql=TercherMoneyDaoSupport.tmList(name);
		int pageindex = Integer.valueOf(page).intValue();
		int pagesize = Integer.valueOf(size).intValue();
		Paging<Map>	p= esht.createSQLQueryfindPage(sql, (pageindex-1)*pagesize, pagesize);
		return Code.init(0, null, p);
	}
	/**
	 * 添加申请表
	 */
	@Override
	public Code tmsend(String id, String money, String zong, String aid) {
		TeacherSend ts=new TeacherSend();
		ts.setApply_admin_id(aid);
		Date now = new Date(); 
		long lSysTime1 = now.getTime();
		ts.setApply_time(lSysTime1);
		int moneyy = Integer.valueOf(money).intValue();
		ts.setSend_money(moneyy);
		ts.setStatus(1);
		ts.setTeacher_id(id);
		esht.getHibernateTemplate().save(ts);
		
		return null;
	}
}
