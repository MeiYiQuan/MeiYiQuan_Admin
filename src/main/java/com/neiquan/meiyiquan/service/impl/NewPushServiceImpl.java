package com.neiquan.meiyiquan.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neiquan.meiyiquan.code.Code;
import com.neiquan.meiyiquan.dao.ObjectDao;
import com.neiquan.meiyiquan.dao.support.CouponDaoSupport;
import com.neiquan.meiyiquan.pojo.User;
import com.neiquan.meiyiquan.service.NewPushService;
import com.neiquan.meiyiquan.service.PushService;
import com.neiquan.meiyiquan.util.ExtraSpringHibernateTemplate;
import com.neiquan.meiyiquan.util.Statics;
import com.qc.util.Condition.Cs;

/**
 * 作者：温尉棨
 * 创建日期：2017年2月15日
 * 类说明：消息推送
 */
@Service
public class NewPushServiceImpl implements NewPushService{

	@Autowired
	private ObjectDao od;
	@Autowired
	private ExtraSpringHibernateTemplate esht;
	@Autowired
	private PushService push;
	/**
	 * 
	 */
	@Override
	public Code send(String title, String content, String ageid, String zodicid, String sexid, String jobid,
			String tree, String type) {
		
		/*
		String[] propName = {"ageId", "zodiacIndex", "gender","jobId","district","user_type"};
		int sex = Integer.valueOf(sexid).intValue();
		int typee = Integer.valueOf(type).intValue();
		Object[] propValue = {ageid, zodicid, sex,jobid,tree,typee};
		List<Map<String ,Object>> list= esht.findAllByPropEq(User.class, propName, propValue);
		*/
		Cs cs = CouponDaoSupport.getSendUserCs(null, ageid, zodicid, sexid, jobid, tree, type);
		String sql = CouponDaoSupport.getSendUserCount(cs.getConditions());
		int count = od.getObjCountBySql(sql, cs.getParams());
		if(count==0){
			return Code.init(-1, "没有查到用户！");
		}
		sql = CouponDaoSupport.getSentUsers(cs.getConditions());
		List<Map<String, Object>> users = od.getListBySql(sql, cs.getParams(), null, null);
		int l=users.size();
		for(int i=0;i<l;i++){
			Map map = (Map)users.get(i);
			String uid= map.get("id").toString();
			push.pushEveryOne("", title,Statics.PUSH_TYPE_FORTHWITH_NOTABLE, content, null, uid);
		}
		
		return Code.init(0, "发送成功！");
	}

	
	@Override
	public Code count(String title, String content, String ageid, String zodicid, String sexid, String jobid,
			String tree, String type) {
		
		String[] propName = {"ageId", "zodiacIndex", "gender","jobId","district","user_type"};
		int sex = Integer.valueOf(sexid).intValue();
		int typee = Integer.valueOf(type).intValue();
		Object[] propValue = {ageid, zodicid, sex,jobid,tree,typee};
		List<Map<String ,Object>> list= esht.findAllByPropEq(User.class, propName, propValue);
		int l=list.size();
	
		
		return Code.init(0, null,l);
	}
	
}
