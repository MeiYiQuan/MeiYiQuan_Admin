package com.neiquan.meiyiquan.service.impl;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.neiquan.meiyiquan.code.Code;
import com.neiquan.meiyiquan.dao.ObjectDao;
import com.neiquan.meiyiquan.dao.support.ActiveDaoSupport;
import com.neiquan.meiyiquan.dao.support.AskCourseDaoSupport;
import com.neiquan.meiyiquan.pojo.Activity;
import com.neiquan.meiyiquan.pojo.ActivityStatus;
import com.neiquan.meiyiquan.pojo.Banner;
import com.neiquan.meiyiquan.pojo.Course;
import com.neiquan.meiyiquan.service.ActiveService;
import com.neiquan.meiyiquan.util.DateUtil;
import com.neiquan.meiyiquan.util.ExtraSpringHibernateTemplate;
import com.neiquan.meiyiquan.util.OSSClientUtil;
import com.neiquan.meiyiquan.util.Paging;
import com.neiquan.meiyiquan.util.Statics;
import com.neiquan.meiyiquan.util.StringUtil;
import com.neiquan.meiyiquan.util.TimeCleanUtil;

/**
 * 作者：温尉棨
 * 创建日期：2017年1月13日
 * 类说明：
 */
@Service
public class ActiveServiceImpl implements ActiveService {
	@Autowired
	private ExtraSpringHibernateTemplate esht;
	@Autowired
	private OSSClientUtil ossClient;
	@Autowired
	private ObjectDao od;
	
	@Resource(name="projectProperties")
    private Properties properties;
	/**
	 * 活动列表
	 */
	@Override
	public Code getList(String page,String size,String count,String kaystatus,String kaytxt,
			String createBegin,String createEnd,String orderBy,String collation,String status,String tree) {
		String sql=ActiveDaoSupport.listSql(kaystatus, kaytxt, createBegin, createEnd, orderBy, collation, status,tree);
		int pageindex = Integer.valueOf(page).intValue();
		int pagesize = Integer.valueOf(size).intValue();
		Paging<Map>	p= esht.createSQLQueryfindPage(sql, (pageindex-1)*pagesize, pagesize);
		return Code.init(0, null, p);
	}
	/**
 
	 * 活动收益列表
	 */
	@Override
	public Code incomeList(String page, String size, String count, String kaystatus, String kaytxt, String createBegin,
			String createEnd, String orderBy, String collation,String tree) {
		String sql=ActiveDaoSupport.incomeList(kaystatus, kaytxt, createBegin, createEnd, orderBy, collation, tree);
		int pageindex = Integer.valueOf(page).intValue();
		int pagesize = Integer.valueOf(size).intValue();
		Paging<Map>	p=esht.createSQLQueryfindPage(sql, (pageindex-1)*pagesize, pagesize);
		return Code.init(0, null, p);
	}
	/**
	 * 活动评论列表
	 */
	@Override
	public Code getcomment(String page, String size, String title) {
		String and="";
		if(!StringUtil.isNullOrBlank(title)){
			and=" and uvr.course_name  like '%"+title+"%'";
		}
		String sql ="SELECT  c.*, a.title atitle,u.username  FROM tb_comment c " 
					+ " LEFT JOIN tb_activity a ON a.id=c.commed_id "
					+ " LEFT JOIN tb_user u ON c.user_id=u.id"
					+ " WHERE 1=1 "+and+""
					+ " and c.commed_type='1' "
					+ " and c.type= "+Statics.COMMENT_TYPE_ACTIVITY+"";
		int pageindex = Integer.valueOf(page).intValue();
		int pagesize = Integer.valueOf(size).intValue();
		Paging<Map>	p= esht.createSQLQueryfindPage(sql, (pageindex-1)*pagesize, pagesize);
		return Code.init(0, null, p);
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
	 * oss上传
	 */
	@Override
	public String updateHead(MultipartFile file) throws IOException {
		  String name="";
			try {
				name = ossClient.uploadImg2Oss(file);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    String imgUrl = ossClient.getImgUrl(name);
		    return imgUrl;
	}
	/**
	 * 添加活动
	 */
	@Override
	public String  saveActive(String tital, String enroll_begin_time, String enroll_end_time, String prepare_start_time,
			String prepare_end_time, String activity_start_time, String activity_end_time,
			String show_type, String show_pic_url, String remark, String cost, String most_man, String appearance,
			String canUseCoupon,String fileData,String price,String address,String organiser) {
		
		Date now=new Date();
		
		Map<String,Object> map = new HashMap();
		
		if(Integer.parseInt(show_type)==Statics.ACTIVITY_SHOWTYPE_PIC){
			map.put("show_pic_url", show_pic_url);
			map.put("show_video_picurl", "");
			map.put("show_video_url", "");
		}else if(Integer.parseInt(show_type)==Statics.ACTIVITY_SHOWTYPE_VIDEO){
			map.put("show_pic_url", "");
			map.put("show_video_picurl", show_pic_url);
			map.put("show_video_url", fileData);
		}else{
			return "1";
		}
		
		long enroll_begin_time_long = DateUtil.dateStrToMillis(enroll_begin_time + " 00:00:00","yyyy-MM-dd HH:mm:ss");
		long enroll_end_time_long = DateUtil.dateStrToMillis(enroll_end_time + " 23:59:59","yyyy-MM-dd HH:mm:ss");
		
		if(enroll_begin_time_long>=enroll_end_time_long)
			return "2";
		
		long prepare_start_time_long = DateUtil.dateStrToMillis(prepare_start_time + " 00:00:00","yyyy-MM-dd HH:mm:ss");
		long prepare_end_time_long = DateUtil.dateStrToMillis(prepare_end_time + " 23:59:59","yyyy-MM-dd HH:mm:ss");
		
		if(prepare_start_time_long>=prepare_end_time_long)
			return "3";
		
		long activity_start_time_long = DateUtil.dateStrToMillis(activity_start_time + " 00:00:00","yyyy-MM-dd HH:mm:ss");
		long activity_end_time_long = DateUtil.dateStrToMillis(activity_end_time + " 23:59:59","yyyy-MM-dd HH:mm:ss");
		
		if(activity_start_time_long>=activity_end_time_long)
			return "4";
		
		if(enroll_end_time_long>prepare_start_time_long)
			return "5";
		
		if(prepare_end_time_long>activity_start_time_long)
			return "6";
		
		map.put("remark", remark);
		map.put("cost", cost);
		map.put("price", price);
		map.put("most_man", most_man);
		map.put("appearance", 0);
		map.put("canUseCoupon", canUseCoupon);
		map.put("create_time", now.getTime());
		map.put("update_time", now.getTime());
		map.put("title", tital);
		map.put("show_type", show_type);
		map.put("activity_time", activity_start_time_long);
		map.put("address", address);
		map.put("organiser", organiser);
		
		Map<String,Object> map1 = new HashMap();
		
		map1.put("enroll_begin_time", enroll_begin_time_long);
		map1.put("enroll_end_time", enroll_end_time_long);
		map1.put("prepare_start_time", prepare_start_time_long);
		map1.put("prepare_end_time", prepare_end_time_long);
		map1.put("activity_start_time", activity_start_time_long);
		map1.put("activity_end_time", activity_end_time_long);
		map1.put("cancel_time", 0);
		
		map1.put("create_time", now.getTime());
		map1.put("isCancel", 2);
		String id=od.save(Activity.class, map);
		
		String url = new String(properties.getProperty("share.url.prefix"));
		String shareU = url.replace("${id}", id.toString()).replace("${type}", "4");
		Map<String,Object> settings = new HashMap<String,Object>();
		settings.put("share_url", shareU);
		od.updateById(Activity.class, id.toString(), settings);
		
		map1.put("activity_id", id);
		String bool=od.save(ActivityStatus.class, map1);
		
		return bool;
	}
	@Override
	public String updateActive(String id, String tital, String enroll_begin_time, String enroll_end_time,
			String prepare_start_time, String prepare_end_time, String activity_start_time, String activity_end_time,
			String show_type, String show_pic_url, String remark, String cost, String most_man, String appearance,
			String canUseCoupon,String fileData,String price,String address,String organiser) {
		Map<String,Object> map = new HashMap();
		map.put("title", tital);
		map.put("show_type", show_type);
		
		if(Integer.parseInt(show_type)==Statics.ACTIVITY_SHOWTYPE_PIC){
			map.put("show_pic_url", show_pic_url);
			map.put("show_video_picurl", "");
			map.put("show_video_url", "");
		}else if(Integer.parseInt(show_type)==Statics.ACTIVITY_SHOWTYPE_VIDEO){
			map.put("show_pic_url", "");
			map.put("show_video_picurl", show_pic_url);
			map.put("show_video_url", fileData);
		}else{
			return "1";
		}
		
		map.put("remark", remark);
		map.put("price", price);
		map.put("cost", cost);
		map.put("most_man", most_man);
		map.put("appearance", appearance);
		map.put("canUseCoupon", canUseCoupon);
		map.put("address", address);
		map.put("organiser",organiser);
		
		Map<String,Object> map1 = new HashMap();
		
		long enroll_begin_time_long = DateUtil.dateStrToMillis(enroll_begin_time + " 00:00:00","yyyy-MM-dd HH:mm:ss");
		long enroll_end_time_long = DateUtil.dateStrToMillis(enroll_end_time + " 23:59:59","yyyy-MM-dd HH:mm:ss");
		
		if(enroll_begin_time_long>=enroll_end_time_long)
			return "2";
		
		long prepare_start_time_long = DateUtil.dateStrToMillis(prepare_start_time + " 00:00:00","yyyy-MM-dd HH:mm:ss");
		long prepare_end_time_long = DateUtil.dateStrToMillis(prepare_end_time + " 23:59:59","yyyy-MM-dd HH:mm:ss");
		
		if(prepare_start_time_long>=prepare_end_time_long)
			return "3";
		
		long activity_start_time_long = DateUtil.dateStrToMillis(activity_start_time + " 00:00:00","yyyy-MM-dd HH:mm:ss");
		long activity_end_time_long = DateUtil.dateStrToMillis(activity_end_time + " 23:59:59","yyyy-MM-dd HH:mm:ss");
		
		if(activity_start_time_long>=activity_end_time_long)
			return "4";
		
		if(enroll_end_time_long>prepare_start_time_long)
			return "5";
		
		if(prepare_end_time_long>activity_start_time_long)
			return "6";
		
		
		map1.put("enroll_begin_time", enroll_begin_time_long);
		map1.put("enroll_end_time", enroll_end_time_long);
		map1.put("prepare_start_time", prepare_start_time_long);
		map1.put("prepare_end_time", prepare_end_time_long);
		map1.put("activity_start_time", activity_start_time_long);
		map1.put("activity_end_time", activity_end_time_long);
		
//		map1.put("enroll_begin_time", DateUtil.dateStrToMillis(enroll_begin_time,"yyyy-MM-dd"));
//		map1.put("enroll_end_time", DateUtil.dateStrToMillis(enroll_end_time,"yyyy-MM-dd"));
//		map1.put("prepare_start_time", DateUtil.dateStrToMillis(prepare_start_time,"yyyy-MM-dd"));
//		map1.put("prepare_end_time", DateUtil.dateStrToMillis(prepare_end_time,"yyyy-MM-dd"));
//		map1.put("activity_start_time", DateUtil.dateStrToMillis(activity_start_time,"yyyy-MM-dd"));
//		map1.put("activity_end_time", DateUtil.dateStrToMillis(activity_end_time,"yyyy-MM-dd"));
		Map<String,Object> map2 = new HashMap();
		map2.put("activity_id", id);
		Integer l=od.update(ActivityStatus.class, map2, map1);
		Integer i=od.updateById(Activity.class, id, map);
		if(i!=1||l!=1)
			return "7";
		return "";
	}
	/**
	 * 获取详情
	 */
	@Override
	public Map<String,Object> infoActive(String id) {
		String sql=ActiveDaoSupport.getInfo(id);
		Map map= od.getObjectBySql(sql,null);
		ArrayList<Map> temp=new ArrayList<>();
		temp.add(map);
		TimeCleanUtil.clean2(temp,"activity_end_time","activity_start_time","cancel_time","enroll_begin_time","enroll_end_time","prepare_end_time","prepare_start_time");
		
		return map;
	}
	/**
	 * 获取时间详情
	 */
	@Override
	public Map<String, Object> infoActivetime(String id) {
		String sql="SELECT "
				+ " from_unixtime(a.activity_start_time/1000) activity_start_time,"
				+ " from_unixtime(a.activity_end_time/1000) activity_end_time,"
				+ " from_unixtime(a.enroll_begin_time/1000) enroll_begin_time,"
				+ " from_unixtime(a.enroll_end_time/1000) enroll_end_time,"
				+ " from_unixtime(a.prepare_start_time/1000) prepare_start_time,"
				+ " from_unixtime(a.prepare_end_time/1000) prepare_end_time "
				+ " FROM tb_activity_status a WHERE a.activity_id='"+id+"' ";
		Map<String,Object> map = new HashMap();
		map= od.getObjectBySql(sql,null);
		return map;
	}
	
	/**
	 * 活动参与人数列表
	 */
	@Override
	public Code getPeoList(String page, String size, String id) {
		String sql=ActiveDaoSupport.getPeoList(id);
		int pageindex = Integer.valueOf(page).intValue();
		int pagesize = Integer.valueOf(size).intValue();
		Paging<Map>	p= esht.createSQLQueryfindPage(sql, (pageindex-1)*pagesize, pagesize);
		return Code.init(0, null, p);
	}
	/**
	 * 订单列表
	 */
	@Override
	public Code getActiveOrder(String oid,String page, String size, String aid,String phone) {
		String and="";
		if(!aid.equals("")){
			and=" and a.id='"+aid+"'";
		}
		String and1="";
		if(!oid.equals("")){
			and1=" and  o.id='"+oid+"'";
		}
		String and2="";
		if(!phone.equals("")){
			and2=" and  u.phone  like '%"+phone+"%'";
		}
		String sql="SELECT o.order_num,o.create_time,a.id aid,o.id oid,o.status ostatus"
				+ ",u.username,u.phone " 
				+ " FROM tb_order o "
				+ " LEFT JOIN tb_activity a ON o.video_id=a.id "
				+ " LEFT JOIN tb_user u ON u.id=o.user_id "
				+ " WHERE 1=1  "+and+" "+and1+"  "+and2+"";
		int pageindex = Integer.valueOf(page).intValue();
		int pagesize = Integer.valueOf(size).intValue();
		Paging<Map>	p= esht.createSQLQueryfindPage(sql, (pageindex-1)*pagesize, pagesize);
		List<Map> list= p.getList();
		TimeCleanUtil.clean2(list,"create_time");

		/*for(int i=0;i<list.size();i++){
			Map map=list.get(i);
			String creat=map.get("create_time").toString();
			long create_time = Long.valueOf(creat).intValue();
			Date date = new Date(create_time);
	        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        map.put("create_time", sd.format(date));
		}*/

		
		return Code.init(0, null, p);
	}
	/**
	 * 嘉宾列表
	 */
	@Override
	public Code jabinList(String page, String size, String id) {
		String sql=ActiveDaoSupport.jiabin(id);
		int pageindex = Integer.valueOf(page).intValue();
		int pagesize = Integer.valueOf(size).intValue();
		Paging<Map>	p= esht.createSQLQueryfindPage(sql, (pageindex-1)*pagesize, pagesize);
		return Code.init(0, null, p);
	}
	/**
	 * 活动嘉宾详情
	 */
	@Override
	public Code jiabinInfo(String id) {
		String sql="select * from tb_activity_user_relationship a where a.id='"+id+"' ";
		
		Paging<Map>	p= esht.createSQLQueryfindPage(sql, 0, 9);
		return Code.init(0, null, p);
	}
	@Override
	public int deleteById(String id) {
		int result1 = od.deleteById(Activity.class, id);
		if(result1!=1)
			return -1;
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("activity_id", id);
		result1 = od.delete(ActivityStatus.class, params);
		if(result1!=1)
			return -1;
		return 0;
	}
	
	public static void main(String[] args) {
		String time="1489284989470";
		Long create_time = Long.valueOf(time);
		Date date = new Date(create_time);
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println( sd.format(date));
	}
}
