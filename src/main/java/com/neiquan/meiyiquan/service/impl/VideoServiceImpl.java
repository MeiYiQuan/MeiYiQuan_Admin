package com.neiquan.meiyiquan.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neiquan.meiyiquan.code.Code;
import com.neiquan.meiyiquan.dao.ObjectDao;
import com.neiquan.meiyiquan.dao.support.CourseDaoSupport;
import com.neiquan.meiyiquan.pojo.Activity;
import com.neiquan.meiyiquan.pojo.Video;
import com.neiquan.meiyiquan.service.IStatisticsService;
import com.neiquan.meiyiquan.service.VideoService;
import com.neiquan.meiyiquan.util.ExtraSpringHibernateTemplate;
import com.neiquan.meiyiquan.util.Paging;
import com.neiquan.meiyiquan.util.Statics;
import com.neiquan.meiyiquan.util.StringUtil;

/**
 * 作者：刘丹 创建日期：2017年1月21日 类说明：视频管理模块
 */
@Service
public class VideoServiceImpl implements VideoService {

	@Autowired
	private ExtraSpringHibernateTemplate esht;
	@Autowired 
	private  IStatisticsService iStatisticsService;
	
	@Autowired
	private ObjectDao od;
	
	@Resource(name="projectProperties")
    private Properties properties;
	@Override 
	public Code add(String title,String course_id,String time_long,String video_save_url
			,String video_pic_url,String remark,Double per_cost,String video_size
			,Integer free, Integer freeTime,Integer canUseCoupon, HttpSession session) {
		Video nd = new Video();
		nd.setTitle(title);
		nd.setCourse_id(course_id);
		nd.setCreate_time(System.currentTimeMillis());
		nd.setFree(free);
		Map map=new HashMap();
		map.put("key", "course_id");
		map.put("value", course_id);
		Integer 	order_num=getTatleMax("tb_video","order_num",map);
		nd.setOrder_num(order_num);
		if(free==1){
			nd.setPer_cost(0);
			nd.setFreeTime(0);
		}else{
			nd.setPer_cost(per_cost);
			nd.setFreeTime(freeTime*1000);
		}
		nd.setTime_long(Integer.valueOf(time_long));
		nd.setRemark(remark);
		nd.setVideo_pic_url(video_pic_url);
		nd.setVideo_save_url(video_save_url);
		nd.setStatus(Statics.NO_INT);
		nd.setUpdate_time(System.currentTimeMillis());
		nd.setCanUseCoupon(canUseCoupon);
		nd.setVideo_size(video_size);
		esht.getHibernateTemplate().save(nd);
		
		
		String id=	nd.getId().toString();
		String url = new String(properties.getProperty("share.url.prefix"));
		String shareU = url.replace("${id}", course_id).replace("${type}", "3");
		Map<String,Object> settings = new HashMap<String,Object>();
		settings.put("share_url", shareU);
		od.updateById(Video.class, id.toString(), settings);
		//iStatisticsService.addStatistics("", type, course_id);
		return Code.init(0, "添加成功", null);
	}

	@Override
	public Code upStatus(String id, String status, HttpSession session) {
		Video nd = esht.findFirstOneByPropEq(Video.class, "id", id);
		if (nd == null)
			return Code.init(1, "未找到此节点", null);
		nd.setStatus(Integer.valueOf(status));
		nd.setUpdate_time(System.currentTimeMillis());
		esht.getHibernateTemplate().update(nd);
		return Code.init(0, "执行成功", null);
	}
	@Override
	public Code getListByCourseId(String id) {
		String[] values = { id };
		StringBuffer sb = new StringBuffer();
		sb.append(" SELECT");
		sb.append(" tb_video.*,");
		sb.append(" tb_statistics.click_count,");
		sb.append(" tb_statistics.coupon_count,");
		sb.append(" tb_statistics.signdays,");
		sb.append(" tb_statistics.click_expect_count,");
		sb.append(" tb_statistics.collect_count,");
		sb.append(" tb_statistics.collect_expect_count,");
		sb.append(" tb_statistics.comment_count,");
		sb.append(" tb_statistics.comment_expect_count,");
		sb.append(" tb_statistics.dislike_expect_count,");
		sb.append(" tb_statistics.like_count,");
		sb.append(" tb_statistics.like_expect_count,");
		sb.append(" tb_statistics.play_count,");
		sb.append(" tb_statistics.play_expect_count,");
		sb.append(" tb_statistics.share_count,");
		sb.append(" tb_statistics.share_expect_count,");
		sb.append(" tb_statistics.follow_count,");
		sb.append(" tb_statistics.follow_expect_count,");
		sb.append(" tb_statistics.type,COUNT(tb_order.id) as orderNum");
		sb.append(" FROM");
		sb.append(" tb_video");
		sb.append(" LEFT JOIN tb_statistics ON tb_video.id=tb_statistics.type_id");
		sb.append(" LEFT JOIN tb_order ON tb_order.video_id =tb_video.id AND tb_order.`status`=1 ");
		sb.append(" WHERE tb_video.course_id=? order by tb_video.order_num desc");
		List<Map> list = esht.createSQLQueryFindAll(sb.toString(), values);
		return Code.init(0, null, list);
	}
	@Override
	public Code getListByCourseId(String id,String orderBy,String collation) {
		String[] values = { id };
		StringBuffer sb = new StringBuffer();
		sb.append(" SELECT");
		sb.append(" tb_video.*,");
		sb.append(" tb_statistics.click_count,");
		sb.append(" tb_statistics.coupon_count,");
		sb.append(" tb_statistics.signdays,");
		sb.append(" tb_statistics.click_expect_count,");
		sb.append(" tb_statistics.collect_count,");
		sb.append(" tb_statistics.collect_expect_count,");
		sb.append(" tb_statistics.comment_count,");
		sb.append(" tb_statistics.comment_expect_count,");
		sb.append(" tb_statistics.dislike_expect_count,");
		sb.append(" tb_statistics.like_count,");
		sb.append(" tb_statistics.like_expect_count,");
		sb.append(" tb_statistics.play_count,");
		sb.append(" tb_statistics.play_expect_count,");
		sb.append(" tb_statistics.share_count,");
		sb.append(" tb_statistics.share_expect_count,");
		sb.append(" tb_statistics.follow_count,");
		sb.append(" tb_statistics.follow_expect_count,");
		sb.append(" (SELECT COUNT(tb_order.id) FROM tb_order WHERE tb_order.video_id = tb_video.id");
		sb.append(" AND tb_order.`status` = 1) as orderNum ,");
		sb.append(" (SELECT COUNT(tb_shared.id) FROM tb_shared WHERE tb_shared.shareId = tb_video.id) as shard ");
		sb.append(" FROM");
		sb.append(" tb_video");
		sb.append(" LEFT JOIN tb_statistics ON tb_video.id=tb_statistics.type_id");
		sb.append(" WHERE tb_video.course_id=? ");
		sb.append(" ORDER BY ");
		sb.append(orderBy);//排序判断
		sb.append(" ");//排序判断
		sb.append(collation);
		List<Map> list = esht.createSQLQueryFindAll(sb.toString(), values);
		return Code.init(0, null, list);
	}

	@Override
	public Code getVideoById(String id) {
		String[] values = { id };
		StringBuffer sb = new StringBuffer();
		sb.append(" SELECT");
		sb.append(" tb_video.*");
		sb.append(" FROM");
		sb.append(" tb_video");
		sb.append(" WHERE tb_video.id=?");
		Map list = esht.createSQLQueryFindFirstOne(sb.toString(), values);
		return Code.init(0, null, list);
	}

	@Override
	public Code delete(String id, HttpSession session) {
		Video video = esht.findFirstOneById(Video.class, id);
		if (video == null) {
			return Code.init(1, "视频不存在");
		}
		esht.getHibernateTemplate().delete(video);
		return Code.init(0, "操作成功");
	}

	@Override
	public Code update(String title,String id,String time_long,String video_save_url
			,String video_pic_url,String remark,Double per_cost,String video_size,Integer free,Integer freeTime,Integer canUseCoupon,HttpSession session
			,String fileData) {
		Video oVideo = esht.findFirstOneById(Video.class, id);
		if (oVideo == null) {
			return Code.init(1, "视频不存在");
		}
		if(free==1){
			oVideo.setPer_cost(0);
			oVideo.setFreeTime(0);
		}else{
			oVideo.setPer_cost(per_cost);
			oVideo.setFreeTime(freeTime*1000);
		}
		oVideo.setTitle(title);
		oVideo.setFree(free);
		oVideo.setTime_long(Integer.valueOf(time_long));
		oVideo.setRemark(remark);
		oVideo.setVideo_pic_url(video_pic_url);
		oVideo.setVideo_save_url(video_save_url);
		oVideo.setUpdate_time(System.currentTimeMillis());
		oVideo.setCanUseCoupon(canUseCoupon);
		oVideo.setVideo_size(video_size);
		esht.getHibernateTemplate().update(oVideo);
		return Code.init(0, "操作成功");
	}
	
	
@Override
	public Code overhead(String id) {
		Video oVideo = esht.findFirstOneById(Video.class, id);
		Map map=new HashMap();
		map.put("key", "course_id");
		map.put("value", oVideo.getCourse_id());
		Integer 	order_num=getTatleMax("tb_video","order_num",map);
		oVideo.setOrder_num(order_num);
		esht.getHibernateTemplate().update(oVideo);
		return Code.init(0, "操作成功");
	}
@Override
public Code del(String id) {
	Video oVideo = esht.findFirstOneById(Video.class, id);
	esht.getHibernateTemplate().delete(oVideo);
	return Code.init(0, "操作成功");
}
	public Integer getTatleMax(String tatlename,String fieldName,Object...objects) {
		Integer max=0;
		StringBuffer sb=new StringBuffer();
		sb.append(" SELECT ");
		sb.append(" Max(");
		sb.append(tatlename);
		sb.append(".");
		sb.append(fieldName);
		sb.append(")  as maxNum");
		sb.append(" FROM ");
		sb.append(tatlename);
		sb.append(" WHERE");
		sb.append(" 1=1");
		for(int i=0;i<objects.length;i++){
			Map map=(Map)objects[i];
			sb.append(" and ");
			sb.append(map.get("key"));
			sb.append("='");
			sb.append(map.get("value"));
			sb.append("'  ");
		}
	Map map=	esht.createSQLQueryFindFirstOne(sb.toString());
		if(map!=null){
			if(map.get("maxNum")!=null){
				max=(Integer)map.get("maxNum")+1;
			}
		}
		return max;
	}

	/**
	 * 播放节点统计
	 */
	@Override
	public Map nodeCount(String type,String id) {
		String sql =CourseDaoSupport.nodeCount(type,id);
		Paging<Map>	p= esht.createSQLQueryfindPage(sql, 0, 2);
		List list= p.getList();
		Map map =(Map) list.get(0);
		return map;
	}
}
