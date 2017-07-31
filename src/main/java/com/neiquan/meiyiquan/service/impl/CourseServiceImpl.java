package com.neiquan.meiyiquan.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.neiquan.meiyiquan.code.Code;
import com.neiquan.meiyiquan.dao.ObjectDao;
import com.neiquan.meiyiquan.dao.support.AskCourseDaoSupport;
import com.neiquan.meiyiquan.dao.support.CourseDaoSupport;
import com.neiquan.meiyiquan.pojo.Comment;
import com.neiquan.meiyiquan.pojo.Course;
import com.neiquan.meiyiquan.pojo.User;
import com.neiquan.meiyiquan.pojo.Video;
import com.neiquan.meiyiquan.pojo.VideoStandard;
import com.neiquan.meiyiquan.service.CourseService;
import com.neiquan.meiyiquan.util.Constant;
import com.neiquan.meiyiquan.util.DateUtil;
import com.neiquan.meiyiquan.util.ExtraSpringHibernateTemplate;
import com.neiquan.meiyiquan.util.Paging;
import com.neiquan.meiyiquan.util.Statics;
import com.neiquan.meiyiquan.util.StringUtil;


/**
 * 作者：刘丹
 * 创建日期：2017年1月7日
 * 类说明：课程管理模块
 */
@Service
public class CourseServiceImpl implements CourseService {
	
	@Autowired
	private ExtraSpringHibernateTemplate esht;
	
	@Autowired
	private ObjectDao od;
	
	@Resource(name="projectProperties")
    private Properties properties;
	

	@Override
	public Code getCourseList(String keywords,String status ,Integer pageIndex,Integer pageSize,
			String timeStar,String timeEnd,String orderBy,String collation,String channel) {
		
		String left="LEFT JOIN ("
				+ "	SELECT"
				+ "		ifnull(`videoCount`.`id`, '') AS `id`,"
				+ " sum(`videoCount`.`playCount`) AS `sum`"
				+ "	FROM"
				+ "	("
				+ "			SELECT"
				+ "			`course`.`id` AS `id`,"
				+ "			`statics`.`play_count` AS `playCount`"
				+ "		FROM"
				+ "			`tb_course` AS `course`"
				+ "	LEFT JOIN `tb_video` AS `video` ON `video`.`course_id` = `course`.`id`"
				+ "LEFT JOIN `tb_statistics` AS `statics` ON `statics`.`type_id` = `video`.`id`"
				+ "	WHERE"
				+ "		`statics`.`type` = 3"
				+ "	GROUP BY"
				+ "		`video`.`id`"
				+ " ) AS `videoCount`"
			    + " GROUP BY"
				+ " `videoCount`.`id`"
				+ " ) AS `otherTable` ON `otherTable`.`id` = `tb_course`.`id`";
		
		StringBuffer sql=new StringBuffer();
		sql.append(" SELECT");
		sql.append(" tb_course.*,s.click_count, ");
		sql.append(" ifnull(`otherTable`.`sum`,0)  AS videonum ");
		sql.append(" ,tb_teacher.`name` ");
		sql.append(" ,ifnull(oo.cou,0) orderNum ");
		sql.append(" ,ifnull(oo.su,0) orderPrice ");
		sql.append(" FROM");
		sql.append(" tb_course ");
		sql.append(left);
		sql.append(" LEFT JOIN tb_statistics s ON s.type_id=tb_course.id ");
		sql.append(" LEFT JOIN (SELECT SUM(o.price) su,COUNT(o.id) cou,co.id  FROM tb_course co LEFT JOIN tb_video v ON v.course_id=co.id LEFT JOIN tb_order o ON o.video_id=v.id WHERE o.`status`=1 GROUP BY co.id) AS oo ON oo.id=tb_course.id");
		sql.append(" LEFT JOIN tb_teacher ON tb_teacher.teacher_id = tb_course.teacher_id ");
		sql.append(" LEFT JOIN tb_video ON tb_course.id=tb_video.course_id ");
		sql.append(" LEFT JOIN tb_order ON tb_video.id=tb_order.video_id AND tb_order.`status`=1 ");
		sql.append(" WHERE tb_course.title LIKE '%"+keywords+"%' and tb_course.`status` LIKE '%"+status+"%' ");
		if(!StringUtil.isNullOrBlank(timeStar)){//判断开始时间
			sql.append(" AND tb_course.create_time > ");
			sql.append(DateUtil.dateStrToMillis(timeStar,"yyyy-MM-dd"));
		}
		if(!StringUtil.isNullOrBlank(timeEnd)){//判断结束时间
			sql.append(" AND tb_course.create_time < ");
			sql.append(DateUtil.dateStrToMillis(timeEnd,"yyyy-MM-dd"));
		}
		if(!StringUtil.isNullOrBlank(channel)){//判断结束时间
			sql.append(" AND tb_course.channel_id ='");
			sql.append(channel);
			sql.append("' ");
		}
		sql.append("  AND tb_course.playing IN (1,2) ");
		sql.append(" GROUP BY tb_course.id ");
		sql.append(" ORDER BY ");
		sql.append(orderBy);//排序判断
		sql.append(" ");//排序判断
		sql.append(collation);
		
		
		Paging<Map>	p= esht.createSQLQueryfindPage(sql.toString(), (pageIndex-1)*pageSize, pageSize);
		return Code.init(0, null, p);
	}
	
	
	
	@Override
	public Code getCourseStandardList(String keywords,String status ,Integer pageIndex,Integer pageSize,
			String timeStar,String timeEnd,String orderBy,String collation,String channel) {
		StringBuffer sql=new StringBuffer();
		sql.append(" SELECT");
		sql.append(" tb_course.*, tb_video_standard.id as sid,");
		sql.append(" tb_video_standard.standard ");
		sql.append(" ,COUNT(tb_order.id) orderNum,");
		sql.append(" tb_video_standard.finish_time, ");
		sql.append(" FROM_UNIXTIME(tb_course.create_time/1000,'%Y-%m-%d') course_time, ");
		sql.append(" date_format(date_add(FROM_UNIXTIME(tb_course.create_time/1000,'%Y-%m-%d'), interval tb_video_standard.standard day),'%Y-%m-%d') AS standard_time,");
		sql.append(" (CASE WHEN standard<=COUNT(tb_order.id) THEN 0 ELSE 1 END) isStandard ");
		sql.append(" FROM ");
		sql.append(" tb_video_standard ");
		sql.append(" LEFT JOIN tb_course ON tb_course.id=tb_video_standard.correlation_id ");
		sql.append(" LEFT JOIN tb_video ON tb_course.id=tb_video.course_id ");
		sql.append("  LEFT JOIN tb_order ON tb_video.id=tb_order.video_id  ");
		sql.append("  AND tb_order.`status`=1   ");
		sql.append(" AND FROM_UNIXTIME(tb_order.create_time/1000,'%Y-%m-%d')<=date_add(FROM_UNIXTIME(tb_course.create_time/1000,'%Y-%m-%d'), interval tb_video_standard.standard day)  ");
		sql.append("  WHERE  ");
		sql.append("  tb_course.title LIKE '%%'  ");
		sql.append("  AND tb_course.`status` LIKE '%%' AND tb_course.playing IN (1,2) ");
		sql.append("  GROUP BY tb_course.id  ");
		sql.append(" ORDER BY ");
		sql.append(orderBy);//排序判断
		sql.append(" ");//排序判断
		Paging<Map>	p= esht.createSQLQueryfindPage(sql.toString(), (pageIndex-1)*pageSize, pageSize);
		return Code.init(0, null, p);
	}
	
	@Override
	public Code getCourseById(String id) {
		Course nd=esht.findFirstOneByPropEq(Course.class, "id", id);
		return Code.init(0, null, nd);
	}
	
	@Override
	public Code add(Course course ,HttpSession session) {
		course.setCreate_time(System.currentTimeMillis());
		course.setStatus(Statics.NO_INT);
		course.setUpdate_time(System.currentTimeMillis());
		course.setPlaying_time(System.currentTimeMillis());
		Object id = esht.getHibernateTemplate().save(course);
		
		String url = new String(properties.getProperty("share.url.prefix"));
		String shareU = url.replace("${id}", id.toString()).replace("${type}", "3");
		Map<String,Object> settings = new HashMap<String,Object>();
		settings.put("share_url", shareU);
		od.updateById(Course.class, id.toString(), settings);
		
		return Code.init(0, "添加成功", id);
	}
	@Override
	public Code update(String id,String title,String description,String remark,Integer course_compaign_type,
			String course_compaign_video_url,String pic_big_url,String channel_id,String teacher_id,HttpSession session) {
		Course course=esht.findFirstOneByPropEq(Course.class, "id",id);
		if(course==null)return Code.init(1, "无此视频", null);
		course.setTitle(title);
		course.setDescription(description);
		course.setRemark(remark);
		course.setCourse_compaign_type(course_compaign_type);
		course.setCourse_compaign_video_url(course_compaign_video_url);
		course.setPic_big_url(pic_big_url);
		course.setChannel_id(channel_id);
		course.setTeacher_id(teacher_id);
		esht.getHibernateTemplate().update(course);
		return Code.init(0, "修改成功", null);
	}
	
	@Override
	public Code upStatus(String id,String  status,HttpSession session) {
		Course nd	=	esht.findFirstOneByPropEq(Course.class,"id" ,id);
		if(nd==null)return Code.init(1, "未找到此节点", null);
		nd.setStatus(Integer.valueOf(status));
		nd.setUpdate_time(System.currentTimeMillis());
		esht.getHibernateTemplate().update(nd);
		return Code.init(0, "执行成功", null);
	}
	@Override
	public Code del(String id,HttpSession session) {
		Course nd	=	esht.findFirstOneByPropEq(Course.class,"id" ,id);
		if(nd==null)return Code.init(1, "未找到此节点", null);
		nd.setUpdate_time(System.currentTimeMillis());
		esht.getHibernateTemplate().update(nd);
		return Code.init(0, "执行成功", null);
	}
	
	
	@Override
	public Code updateStandard(VideoStandard v ,HttpSession session) {
		VideoStandard nd=	esht.findFirstOneByPropEq(VideoStandard.class,"id" ,v.getId().toString());
		VideoStandard d=(VideoStandard)esht.modify(nd,v);
		esht.getHibernateTemplate().update(d);
		return Code.init(0, null, null);
	}
	@Override
	public Code addStandard(VideoStandard v ,HttpSession session) {
		esht.getHibernateTemplate().save(v);
		return Code.init(0, null, null);
	}
	
	@Override
	public Code commnet(String id,Integer  firstResult,Integer  maxResults,HttpSession session) {
		StringBuffer sb=new StringBuffer();
		sb.append(" SELECT");
		sb.append(" 	tb_comment.*,");
		sb.append(" tb_video.title,");
		sb.append(" tb_user.username");
		sb.append(" FROM");
		sb.append(" tb_comment");
		sb.append(" LEFT JOIN tb_video ON tb_video.id=tb_comment.commed_id");
		sb.append(" LEFT JOIN tb_user ON tb_user.id=tb_comment.user_id");
		sb.append(" WHERE");
		sb.append("  tb_comment.type=1");
		sb.append(" ORDER BY");
		sb.append(" tb_comment.comm_time DESC");
		Paging<Map> p=esht.createSQLQueryfindPage(sb.toString(), (firstResult-1)*maxResults, maxResults );
		return Code.init(0, null, p);
	}



	/**
	 * 课程统计
	 */
	@Override
	public Code courseCount(String orderby) {
		
		String sql=CourseDaoSupport.commentCount(orderby);
		
		Paging<Map>	p= esht.createSQLQueryfindPage(sql, 0, 10);
		return Code.init(0, null, p);
	}
	

	
}
