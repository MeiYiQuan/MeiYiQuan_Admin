package com.neiquan.meiyiquan.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.neiquan.meiyiquan.code.Code;
import com.neiquan.meiyiquan.dao.ObjectDao;
import com.neiquan.meiyiquan.dao.support.AskCourseDaoSupport;
import com.neiquan.meiyiquan.dao.support.ChannelDaoSupport;
import com.neiquan.meiyiquan.pojo.Admin;
import com.neiquan.meiyiquan.pojo.Banner;
import com.neiquan.meiyiquan.pojo.Channel;
import com.neiquan.meiyiquan.pojo.UserVideoRequest;
import com.neiquan.meiyiquan.service.ChannelService;
import com.neiquan.meiyiquan.util.ExtraSpringHibernateTemplate;
import com.neiquan.meiyiquan.util.Paging;
import com.neiquan.meiyiquan.util.Statics;
import com.neiquan.meiyiquan.util.TimeCleanUtil;
import com.qc.util.Condition.Cs;
import com.qc.util.DateFormate;

/**
 * 作者：齐潮
 * 创建日期：2016年12月14日
 * 类说明：频道管理
 */
@Service
public class ChannelServiceImpl implements ChannelService {

	@Autowired
	private ObjectDao od;
	@Autowired
	private ExtraSpringHibernateTemplate esht;

	@SuppressWarnings("unchecked")
	@Override
	public Code getChannels(Cs cs, int page, int size) {
		String countSql = ChannelDaoSupport.getCountSql(cs.getConditions());
		String selectSql = ChannelDaoSupport.getResultSql(cs.getConditions()) + cs.getOrderSql();
		Code result = od.getObjects(countSql, selectSql, cs.getParams(), page, size);
		Map<String,Object> map = result.getMapData();
		Object list = map.get("result");
		TimeCleanUtil.clean(list,"create_time","update_time");
		return result;
	}

	/**
	 * 联动查询
	 */
	@Override
	public List getchannelList(String type, String upId) {
		
		String sql="SELECT  c.`name` as na,c.id FROM tb_channel c  WHERE c.pid='"+upId+"'";
		List list=esht.createSQLQueryFindAll(sql);
		return list;
	}

	/**
	 * 一级频道
	 */
	@Override
	public List getFchannel() {
		String sql="SELECT  c.`name`,c.id FROM tb_channel c  WHERE c.pid= '0' ";
		List list=esht.createSQLQueryFindAll(sql);
		return list;
	}

	/**
	 * 获取详情
	 */
	@Override
	public Code channelInfo(String id) {
		Map<String, Object> channel = od.getObjByIdForMap(Channel.class, id);
		if(channel==null)
			return Code.init(564, "该轮播图已经不存在！");
		return Code.init(1, null,channel);
	}

	/**
	 * 添加
	 */
	@Override
	public String add(String name, String logo_url, String cate,String aid) {
		Map<String,Object> map = new HashMap();
		Date now = new Date(); 
		long lSysTime1 = now.getTime();
		map.put("create_time", lSysTime1);
		map.put("update_time", lSysTime1);
		map.put("name", name);
		map.put("logo_url", logo_url);
		map.put("pid", cate);
		map.put("update_admin_id", aid);
		map.put("enable", Statics.YES_INT);
		String id=od.save(Channel.class, map);
		return id;
	}

	/**
	 * 修改
	 */
	@Override
	public String update(String id, String name, String logo_url, String cate,String aid) {
		Channel chn=esht.findFirstOneById(Channel.class, id);
		
		Date now = new Date(); 
		long lSysTime1 = now.getTime();
		chn.setUpdate_time(lSysTime1);
		chn.setName(name);
		chn.setPid(cate);
		chn.setLogo_url(logo_url);
	    chn.setUpdate_admin_id(aid);
		esht.getHibernateTemplate().update(chn);
		return "";
	}

	/**
	 * 统计列表
	 */
	@Override
	public Code tongji() {
		String sql=ChannelDaoSupport.tongji();
		Paging<Map>	p= esht.createSQLQueryfindPage(sql, 0, 99);
		return Code.init(0, null, p);
	}

	@Override
	public Code updateStatus(String id, int status,String adminId) {
		long now = System.currentTimeMillis();
		Map<String,Object> settings = new HashMap<String,Object>();
		settings.put("enable", status);
		settings.put("update_admin_id", adminId);
		settings.put("update_time", now);
		int result = od.updateById(Channel.class, id, settings);
		if(result!=1)
			return Code.init(-1, "更新失败，请刷新页面后重试！");
		Admin admin = od.getObjById(Admin.class, adminId);
		String adminName = admin==null?"":admin.getLoginname();
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("adminName", adminName);
		map.put("upTime", DateFormate.getDateFormateCH(now));
		map.put("status", status);
		return Code.init(0, "更新成功！", map);
	}

	/**
	 * 频道下课程列表
	 */
	@Override
	public Code courseList(String id,String page,String size,String name) {
		String sql=ChannelDaoSupport.courseList(id,name);
		int pageindex = Integer.valueOf(page).intValue();
		int pagesize = Integer.valueOf(size).intValue();
		Paging<Map>	p= esht.createSQLQueryfindPage(sql, (pageindex-1)*pagesize, pagesize);
		Object list= p.getList();
		TimeCleanUtil.clean(list,"request_time");

		return Code.init(0, null, p);
		
	}

	/**
	 * 二级频道列表
	 */
	@Override
	public List getSchannel() {
		String sql="SELECT * FROM tb_channel channel  where channel.pid <> '67850e9f91524c6d9ac8c8b6ce3533ce' and channel.pid <> 'fe82dcb588314eaa94282f8b88b9d9b1' and  channel.pid <> '0'";
		List list=esht.createSQLQueryFindAll(sql);
		return list;
	}
	
}
