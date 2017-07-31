package com.neiquan.meiyiquan.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neiquan.meiyiquan.code.Code;
import com.neiquan.meiyiquan.dao.support.AskCourseDaoSupport;
import com.neiquan.meiyiquan.dao.support.ShareDaoSupport;
import com.neiquan.meiyiquan.service.ShareService;
import com.neiquan.meiyiquan.util.ExtraSpringHibernateTemplate;
import com.neiquan.meiyiquan.util.Paging;

/**
 * 作者：温尉棨
 * 创建日期：2017年2月19日
 * 类说明：
 */
@Service
public class ShareServiceimpl  implements  ShareService{

	@Autowired
	private ExtraSpringHibernateTemplate esht;

	/**
	 * 分享列表
	 */
	@Override
	public Code shareList(String page,String size) {
		String sql=ShareDaoSupport.shareList();
		int pageindex = Integer.valueOf(page).intValue();
		int pagesize = Integer.valueOf(size).intValue();
		Paging<Map>	p= esht.createSQLQueryfindPage(sql, (pageindex-1)*pagesize, pagesize);
		return Code.init(0, null, p);
	}
}
