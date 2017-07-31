package com.neiquan.meiyiquan.service.impl;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neiquan.meiyiquan.code.Code;
import com.neiquan.meiyiquan.dao.ObjectDao;
import com.neiquan.meiyiquan.dao.support.CourseDaoSupport;
import com.neiquan.meiyiquan.pojo.Banner;
import com.neiquan.meiyiquan.pojo.Comment;
import com.neiquan.meiyiquan.pojo.Course;
import com.neiquan.meiyiquan.pojo.District;
import com.neiquan.meiyiquan.pojo.Statistics;
import com.neiquan.meiyiquan.service.ActiveService;
import com.neiquan.meiyiquan.service.ObjectService;
import com.neiquan.meiyiquan.service.SoonCourseService;
import com.neiquan.meiyiquan.util.DateUtil;
import com.neiquan.meiyiquan.util.ExtraSpringHibernateTemplate;
import com.neiquan.meiyiquan.util.Paging;

/**
 * 作者：温尉棨
 * 创建日期：2017年1月20日
 * 类说明：
 */
@Service
public class SoonCourseServiceImpl  implements SoonCourseService{
	@Autowired
	private ExtraSpringHibernateTemplate esht;
	@Autowired
	private ObjectDao od;
	@Autowired
	private ObjectService os;
	/**
	 * 即将上映课程列表
	 */
	@Override
	public Code getSoonList(String page,String size,String count,String createBegin,String createEnd, String orderBy,String collation,String pid) {
		String sql=CourseDaoSupport.getList(createBegin, createEnd, orderBy, collation, pid);
		int pageindex = Integer.valueOf(page).intValue();
		int pagesize = Integer.valueOf(size).intValue();
		Paging<Map>	p= esht.createSQLQueryfindPage(sql, (pageindex-1)*pagesize, pagesize);
		return Code.init(0, null, p);
	}
	/**
	 * 添加
	 */
	@Override
	public Code addSoon(String playing_time,String title,String pic_big_url,String pic_small_url,
			String teacher_id,String cost,String channel_id,String fileData,String course_compaign_type,
			String description) {
		Course co=new Course();
		co.setPlaying(0);
		long i=DateUtil.dateStrToMillis(playing_time,"yyyy-MM-dd");
		co.setPlaying_time(i);
		co.setPic_big_url(pic_small_url);
		co.setPic_small_url(pic_small_url);
		co.setTeacher_id(teacher_id);
		double d = Double.parseDouble(cost);
		co.setCreate_time(System.currentTimeMillis());
		co.setCost(d);
		co.setChannel_id(channel_id);
		co.setCourse_compaign_video_url(fileData);
		co.setTitle(title);
		co.setStatus(1);
		co.setRemark(description);
		co.setCreate_time(System.currentTimeMillis());
		int course_compaign_typee = Integer.valueOf(course_compaign_type).intValue();
		co.setCourse_compaign_type(course_compaign_typee);
		esht.getHibernateTemplate().save(co);
		
		return null;
	}
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	@Override
	public Code delAll(String id ) {
		int result = os.delete(Course.class, id);
		if(result==0)return Code.init(1, "未找此视频", null);
		return Code.init(0, "删除成功", null);
	}
	/**
	 * 评论列表
	 */
	@Override
	public Code commentReplay(String title,String page,String size,String count) {
		String sql=CourseDaoSupport.commentCouserList(title);
		int pageindex = Integer.valueOf(page).intValue();
		int pagesize = Integer.valueOf(size).intValue();
		Paging<Map>	p= esht.createSQLQueryfindPage(sql, (pageindex-1)*pagesize, pagesize);
		
		return Code.init(0, null, p);
	}
	/**
	 * 删除评论
	 */
	@Override
	public Code delete(String id) {
		//TODO 减少一条评论量
		esht.deleteFirstOneByPropEq(Comment.class, "id", id);
		
		Statistics s=	esht.findFirstOneByPropEq(Statistics.class, "type_id", id);
		int i=s.getComment_count();
		i=i-1;
		return Code.init(1, "删除成功");
	}
	/**
	 * 修改评论虚拟点赞
	 */
	@Override
	public Code updatesoon(String id, String like_expect_count) {
		Statistics s=	esht.findFirstOneByPropEq(Statistics.class, "type_id", id);
		int i=Integer.parseInt(like_expect_count);
		s.setLike_expect_count(i);
		return Code.init(1, "修改成功！");
	}
	/**
	 * 获取即将上映详情
	 */
	@Override
	public Code soonInfo(String id) {
		
		
		String sql="SELECT * FROM tb_course c WHERE c.playing=0 AND c.id='"+id+"'";
		Paging<Map>	p= esht.createSQLQueryfindPage(sql, 0, 10);
		System.out.println(p.getList());
		for (int i = 0; i < p.getList().size(); i++) {
		   Map<String,Object> map=p.getList().get(i);
		   BigInteger playing_time1=(BigInteger) map.get("playing_time");
		   Long playing_time=playing_time1.longValue();
		   SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
		   Date date=new Date(playing_time);
		   map.put("playing_time",format.format(date));
		}
		return Code.init(0, null, p);
	}
	/* (non-Javadoc)
	 * @see com.neiquan.meiyiquan.service.SoonCourseService#updateSoon(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public Code updateSoon(String id, String playing_time, String title, String pic_big_url, String pic_small_url,
			String teacher_id, String cost, String channel_id, String fileData, String course_compaign_type,
			String description) {
		Course co=	esht.findFirstOneByPropEq(Course.class, "id", id);
		co.setPlaying(0);
		long i=DateUtil.dateStrToMillis(playing_time,"yyyy-MM-dd");
		co.setPlaying_time(i);
		co.setPic_big_url(pic_small_url);
		co.setPic_small_url(pic_small_url);
		co.setTeacher_id(teacher_id);
		double d = Double.parseDouble(cost);
		co.setCost(d);
		co.setUpdate_time(System.currentTimeMillis());
		co.setChannel_id(channel_id);
		co.setCourse_compaign_video_url(fileData);
		co.setTitle(title);
		co.setRemark(description);
		int course_compaign_typee = Integer.valueOf(course_compaign_type).intValue();
		co.setCourse_compaign_type(course_compaign_typee);
		esht.getHibernateTemplate().update(co);
		return null;
	}
	
	
	
}
