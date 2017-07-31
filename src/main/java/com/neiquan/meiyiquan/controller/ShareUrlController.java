package com.neiquan.meiyiquan.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.neiquan.meiyiquan.dao.ObjectDao;
import com.neiquan.meiyiquan.util.Statics;
import com.neiquan.meiyiquan.util.TimeCleanUtil;

/**
 * 作者：温尉棨
 * 创建日期：2017年3月4日
 * 类说明：返回给app端分享页面
 */
@Controller
@RequestMapping(value="shareurl")
public class ShareUrlController {

	
	@Autowired
	private ObjectDao od;
	
	@Resource(name="projectProperties")
    private Properties properties;
	
	
	
	/**
	 * app端点击分享时访问的接口
	 * @param id
	 * @param type
	 * @return
	 */
	@RequestMapping(value="shareurl")
	public ModelAndView shareurl(String id,int type){
		ModelAndView mav = new ModelAndView();
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("id", id);
		String sql = null;
		Map<String,Object> result = null;
		// list用于转换日期格式
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		switch(type){
			case 3:
				// 视频
				sql = getCourseSql(id);
				result = od.getObjectBySql(sql, params);
				list.add(result);
				TimeCleanUtil.clean(list,"playing_time");
				mav.setViewName("share/url/couse");
				break;
			case 4:
				// 活动
				sql = getActivitySql(id);
				result = od.getObjectBySql(sql, params);
				list.add(result);
				TimeCleanUtil.clean(list,"activity_time");
				mav.setViewName("share/url/activity");
				break;
			case 7:
				// 课程
				sql = getCourseSql(id);
				result = od.getObjectBySql(sql, params);
				list.add(result);
				TimeCleanUtil.clean(list,"playing_time");
				mav.setViewName("share/url/couse");
				break;
			case 8:
				// 求课程，暂时按讲师处理，需要在生成求课程的时候把讲师的teacher_id给拼接到分享地址，而不是求课程id
				sql = getTeacherSql(id);
				result = od.getObjectBySql(sql, params);
				mav.setViewName("share/url/teacher");
				break;
			default:
				// 一个普通的jsp页面即可，提示出错误信息
				mav.setViewName("share/url/error");
		}
		mav.addObject("data", result);
		mav.addObject("downloadUrl", properties.getProperty("downloadUrl"));
		return mav;
	}
	
	/**
	 * 处理课程分享页面的sql
	 * @param id
	 * @return
	 */
	private String getCourseSql(String id){
		String sql = "select `course`.*,ifnull(`static`.`click_count` + `static`.`click_expect_count`,0) as `lookers` "
						+ "from `tb_course` as `course` "
						+ "LEFT JOIN `tb_statistics` as `static` on `static`.`type_id` = `course`.`id` and `static`.`type` = " + Statics.STATICS_TYPE_KC + " "
						+ "where `course`.`id` = :id";
		return sql;
	}
	
	/**
	 * 获取活动sql
	 * @param id
	 * @return
	 */
	private String getActivitySql(String id){
		String sql = "select * from `tb_activity` where `id` = :id";
		return sql;
	}
	
	/**
	 * 获取讲师sql
	 * @param id
	 * @return
	 */
	private String getTeacherSql(String id){
		String sql="SELECT t.`name`,conn.co con,uvr.course_name,t.head_url  FROM tb_user_video_request uvr"
				+ " LEFT JOIN tb_teacher t ON uvr.teacher_id=t.teacher_id"
				+ " LEFT JOIN"
				+ " (SELECT  COUNT(ur.id) co,uvr.id   FROM  tb_user_video_request uvr"
				+ " LEFT JOIN tb_user_request ur ON uvr.id=ur.requestId GROUP BY uvr.id) AS conn"
				+ " ON conn.id=uvr.id"
				+ "  WHERE uvr.id =:id"
				+ "  GROUP BY  uvr.id";
		/*String sql = "select `teacher`.*,"
						+ "(select ifnull(count(`id`),0) from `tb_follow` where `follow_type` = " + 3 + " and `follow_type_id` = `teacher`.`teacher_id`) as `followCount` "
						+ "from `tb_teacher` as `teacher` "
						+ "where `teacher`.`teacher_id` = :id";*/
		return sql;
	}
	
}
