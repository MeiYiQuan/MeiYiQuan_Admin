package com.neiquan.meiyiquan.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neiquan.meiyiquan.code.Code;
import com.neiquan.meiyiquan.dao.ObjectDao;
import com.neiquan.meiyiquan.dao.support.AskCourseDaoSupport;
import com.neiquan.meiyiquan.dao.support.CouponDaoSupport;
import com.neiquan.meiyiquan.pojo.UserVideoRequest;
import com.neiquan.meiyiquan.service.AskCourseService;
import com.neiquan.meiyiquan.service.PushService;
import com.neiquan.meiyiquan.util.ExtraSpringHibernateTemplate;
import com.neiquan.meiyiquan.util.Paging;
import com.neiquan.meiyiquan.util.Statics;
import com.neiquan.meiyiquan.util.TimeCleanUtil;

/**
 * 作者：温尉棨
 * 创建日期：2017年1月21日
 * 类说明：求教程
 */
@Service
public class AskCourseServiceImpl  implements AskCourseService{

	@Autowired
	private ExtraSpringHibernateTemplate esht;
	@Autowired
	private ObjectDao od;
	@Autowired
	private PushService push;
	
	@Resource(name="projectProperties")
    private Properties properties;
	/**
	 * 求教程通过列表
	 */
	@Override
	public Code getAskList(String page,String size,String count,String kaystatus,String kaytxt,
			String createBegin,String createEnd,String status,String tree,String orderBy,String collation,String channelid) {
		String sql=AskCourseDaoSupport.asklistSql(kaystatus, kaytxt, createBegin, createEnd, status, tree,orderBy,collation,channelid);
		int pageindex = Integer.valueOf(page).intValue();
		int pagesize = Integer.valueOf(size).intValue();
		Paging<Map>	p= esht.createSQLQueryfindPage(sql, (pageindex-1)*pagesize, pagesize);
		Object list= p.getList();
		TimeCleanUtil.clean(list,"request_time");

		return Code.init(0, null, p);
	}
	/**
	 * 修改虚拟投票
	 */
	@Override
	public Code addVote(String id,String number) {
		UserVideoRequest u=	esht.findFirstOneByPropEq(UserVideoRequest.class, "id", id);
		int v=u.getVirtualvote();
		int o = Integer.valueOf(number).intValue();
		v=v+o;
		u.setVirtualvote(v);
		//u.setVirtualvote(o);
		esht.getHibernateTemplate().update(u);
		return null;
	}
	/**
	 * 求教程审核列表
	 */
	@Override
	public Code review(String page, String size, String count, String kaystatus, String kaytxt,String createBegin,String createEnd
			 ,String status) {
		String sql=AskCourseDaoSupport.voteList(kaystatus, kaytxt, createBegin, createEnd, status);
		int pageindex = Integer.valueOf(page).intValue();
		int pagesize = Integer.valueOf(size).intValue();
		Paging<Map>	p= esht.createSQLQueryfindPage(sql, (pageindex-1)*pagesize, pagesize);
		Object list= p.getList();
		TimeCleanUtil.clean(list,"request_time");
		return Code.init(0, null, p);
	}
	/**
	 * 榜首列表
	 */
	@Override
	public Code top(String page, String size, String count, String createBegin, String createEnd, String kaystatus, String kaytxt) {
		String sql=AskCourseDaoSupport.topList(kaystatus, kaytxt, createBegin, createEnd);
		int pageindex = Integer.valueOf(page).intValue();
		int pagesize = Integer.valueOf(size).intValue();
		Paging<Map>	p= esht.createSQLQueryfindPage(sql, (pageindex-1)*pagesize, pagesize);
		Object list= p.getList();
		TimeCleanUtil.clean(list,"request_time");
		
		
		return Code.init(0, null, p);
	}
	/**
	 * 获取详情
	 */
	@Override
	public Code getInfo(String id) {
		String sql=AskCourseDaoSupport.infoSql(id);
		Paging<Map>	p= esht.createSQLQueryfindPage(sql, 0, 9);
		Object list= p.getList();
		TimeCleanUtil.clean(list,"request_time");
		return Code.init(0, null, p);
	}
	/**
	 * 修改求教程详情
	 */
	@Override
	public Integer updateInfo(String id, String firstCate, String cate, String teacher_id,
			String question, String course_name, String feedback, String feedback_status, String pic_url) {
		Map<String,Object> map = new HashMap();
		
		map.put("course_name", course_name);
		map.put("top_channel_id", firstCate);
		map.put("channel_id", cate);
		map.put("question", question);
		map.put("feedback", feedback);
		map.put("feedback_status", feedback_status);
		map.put("teacher_id",teacher_id);
		map.put("pic_url", pic_url);
		
		Integer r=od.updateById(UserVideoRequest.class, id, map);
		
		UserVideoRequest u=	esht.findFirstOneByPropEq(UserVideoRequest.class, "id", id);
		String uid= u.getUser_id();
		push.pushEveryOne("", "求教程反馈",Statics.PUSH_TYPE_FORTHWITH_NOTABLE, feedback, null, uid);
		return r;
	}
	/**
	 * 评论列表
	 */
	@Override
	public Code commentList(String page,String size,String count,String name) {
		String sql=AskCourseDaoSupport.commentList(name);
		int pageindex = Integer.valueOf(page).intValue();
		int pagesize = Integer.valueOf(size).intValue();
		Paging<Map>	p= esht.createSQLQueryfindPage(sql, (pageindex-1)*pagesize, pagesize);
		Object list= p.getList();
		TimeCleanUtil.clean(list,"request_time");
		return Code.init(0, null, p);
	}
	/**
	 * 添加求教程
	 */
	@Override
	public String save(String firstCate, String cate, String teacher_id, String question, String course_name,
			String feedback,  String pic_url,String uid) {
		Map<String,Object> map = new HashMap();
		Date now = new Date(); 
		long lSysTime1 = now.getTime();
		map.put("request_time", lSysTime1);
		map.put("course_name", course_name);
		map.put("top_channel_id", firstCate);
		map.put("channel_id", cate);
		map.put("question", question);
		map.put("feedback_status", "3");
		map.put("teacher_id",teacher_id);
		map.put("pic_url", pic_url);
		map.put("user_id", uid);
		map.put("createdTime", lSysTime1);
		String id=od.save(UserVideoRequest.class, map);
		
		String url = new String(properties.getProperty("share.url.prefix"));
		String shareU = url.replace("${id}", id).replace("${type}", "8");
		Map<String,Object> settings = new HashMap<String,Object>();
		settings.put("share_url", shareU);
		od.updateById(UserVideoRequest.class, id.toString(), settings);
		
		return id;
	}
	/**
	 * 评论二级列表
	 */
	@Override
	public Code commentInfo(String id) {
		String sql=AskCourseDaoSupport.commentInfo(id);
		Paging<Map>	p= esht.createSQLQueryfindPage(sql, 0, 999);
		Object list= p.getList();
		return Code.init(0, null, p);
	}
	/**
	 *   类型统计
	 * @param type 是否是平台
	 * @param typee  是普通用户 还是讲师
	 * @return
	 */
	@Override
	public long typeTongji(String type, String typee) {
		String sql=AskCourseDaoSupport.typeTongji(type, typee);
		long i=esht.createSQLQueryCount(sql);
		return i;
	}
	/**
	 * 统计  点击量  投票量。。。
	 */
	@Override
	public Code numTongji(String type, String typee) {
		String sql=AskCourseDaoSupport.numTongji(type, typee);
		Paging<Map>	p= esht.createSQLQueryfindPage(sql, 0, 5);
		return Code.init(0, null, p);
	}
	/**
	 * 推送
	 */
	@Override
	public Code push(String id) {
		 Map<String, Object> params=new HashMap<>();
		//推送求课程人员
		String sql = "SELECT uvr.*  FROM tb_user_video_request uvr WHERE uvr.id='"+id+"' ";
		/*int count = od.getObjCountBySql(sql, params);
		if(count==0){
			return Code.init(-1, "没有查到用户！");
		}*/
		List<Map<String, Object>> users = od.getListBySql(sql, null, null, null);
		int l=users.size();
		for(int i=0;i<l;i++){
			Map map = (Map)users.get(i);
			String uid= map.get("user_id").toString();
			String name= map.get("course_name").toString();
			push.pushEveryOne("", "求教程回复",Statics.PUSH_TYPE_FORTHWITH_NOTABLE, "您所求课程"+name+"已至投票榜榜首,现在为您联系讲师发布课程", null, uid);
		}
		//推送投票人员
		String sql1 = "SELECT uvr.* ,u.course_name FROM tb_user_request uvr"
				+ "   LEFT JOIN tb_user_video_request u ON uvr.requestId=u.id"
				+ "  WHERE uvr.requestId='"+id+"' ";
		/*int count1 = od.getObjCountBySql(sql1,params);
		if(count1==0){
			return Code.init(-1, "没有查到用户！");
		}*/
		List<Map<String, Object>> users1 = od.getListBySql(sql1, null, null, null);
		int l1=users1.size();
		for(int i=0;i<l1;i++){
			Map map1 = (Map)users1.get(i);
			String uid= map1.get("userId").toString();
			String name =map1.get("course_name").toString();
			push.pushEveryOne("", "求教程回复",Statics.PUSH_TYPE_FORTHWITH_NOTABLE, "您所投票的求课程"+name+"已至投票榜榜首,现在为您联系讲师发布课程", null, uid);
		}

		return Code.init(0, "发送成功！");
	}
	/**
	 * 定时任务
	 */
	@Override
	public void time() {
		/*String sql="update tb_user_video_request n   SET  n.`feedback_status`= 4 ";
		int l=esht.saveOrUpdate(sql); */
		
		String sql1=AskCourseDaoSupport.updateTop();
		Paging<Map>	p1= esht.createSQLQueryfindPage(sql1, 0, Integer.MAX_VALUE);
		Map map1 =p1.getList().get(0);
		String id=map1.get("id").toString();	
		UserVideoRequest uvr=esht.findFirstOneById(UserVideoRequest.class, id);
		uvr.setTop_type(1);
		esht.getHibernateTemplate().update(uvr);
		
	}
	

}
