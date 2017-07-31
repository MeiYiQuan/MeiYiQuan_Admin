package com.neiquan.meiyiquan.dao.support;

import com.neiquan.meiyiquan.util.DateUtil;
import com.neiquan.meiyiquan.util.Statics;
import com.neiquan.meiyiquan.util.StringUtil;

/**
 * 作者：wenweiqi32
 * 创建日期：2017年1月13日
 * 类说明：
 */
public class ActiveDaoSupport {
	/**
	 * 活动管理列表条件查询
	 * @param kaystatus
	 * @param kaytxt
	 * @param createBegin
	 * @param createEnd
	 * @param orderBy
	 * @param collation
	 * @param status
	 * @return
	 */
	public final static String listSql(String kaystatus,String kaytxt,
			String createBegin,String createEnd,String orderBy,String collation,String status,String tree){
		String adress="";
		if(!StringUtil.isNullOrBlank(tree)){
			adress=" and  ac.district like '%"+tree+"%' ";
		}
		String kay="";
		if(!StringUtil.isNullOrBlank(kaystatus)||!StringUtil.isNullOrBlank(kaytxt)){
			kay=" and "+kaystatus+" like '%"+kaytxt+"%'  ";
		}
		String creat="";
		if(!StringUtil.isNullOrBlank(createBegin)){
			long i=DateUtil.dateStrToMillis(createBegin + " 00:00:00","yyyy-MM-dd HH:mm:ss");
			creat=" and ac.activity_time>"+i+" ";
		}
		String end="";
		if(!StringUtil.isNullOrBlank(createEnd)){
			long i=DateUtil.dateStrToMillis(createEnd + " 23:59:59","yyyy-MM-dd HH:mm:ss");
			end=" and ac.activity_time<"+i+"";
		}
		long creatTime = System.currentTimeMillis();
		String sta="";
		if(!StringUtil.isNullOrBlank(status)){
			if(status.equals("1")){
				sta=" and acs.create_time<"+creatTime+" and  "+creatTime+"<acs.enroll_begin_time";
			}
			if(status.equals("2")){
				sta=" and acs.enroll_begin_time<"+creatTime+" and  "+creatTime+"<acs.enroll_end_time";
			}
			if(status.equals("3")){
				sta=" and acs.enroll_end_time<"+creatTime+" and  "+creatTime+"<acs.activity_start_time";
			}
			if(status.equals("4")){
				sta=" and acs.activity_start_time<"+creatTime+" and "+creatTime+"<acs.activity_end_time";
			}
			if(status.equals("5")){
				sta=" and acs.activity_end_time<"+creatTime+"";
			}
			if(status.equals("6")){
				sta=" and acs.isCancel=1";
			}
		}
		String sql ="SELECT ac.title,ifnull(count(o.video_id),0) as videosum "
				+ ",ifnull(SUM(o.price),0) as pricesum,s.click_count click_count"
				+ ",s.collect_count collect_count,s.comment_count comment_count"
				+ ",s.share_count share_count,ac.cost,from_unixtime(ac.activity_time/1000) activity_time,ac.district"
				+ " ,case WHEN acs.create_time<"+creatTime+"  and  "+creatTime+"  <acs.enroll_begin_time THEN '1'"
				+ "  WHEN  acs.enroll_begin_time<"+creatTime+" and  "+creatTime+"  <acs.enroll_end_time THEN '2'"
				+ "  WHEN  acs.enroll_end_time<"+creatTime+" and  "+creatTime+"  <acs.activity_start_time THEN '3'"
				+ "  WHEN  acs.activity_start_time<"+creatTime+" and  "+creatTime+"  <acs.activity_end_time THEN '4'"
				+ "  WHEN acs.activity_end_time<"+creatTime+" THEN '5'"
				+ "  WHEN acs.isCancel='2' then '6' end as sta  " 
				+ " ,t.`name` as `name` ,ac.id,ac.price,ac.address"
				+ ",ac.canUseCoupon  from  tb_activity ac"
				+ " LEFT JOIN tb_statistics s ON  s.type_id=ac.id and s.type=2 "
				+ " LEFT JOIN tb_order o ON ac.id=o.video_id AND o.`status`=1 "
				+ " LEFT JOIN tb_teacher t ON t.id=ac.teacher_id"
				+ " LEFT JOIN tb_activity_status acs ON acs.activity_id =ac.id"
				+ " where 1=1 "+kay+""+creat+""+end+""+sta+" "
				+ " GROUP BY ac.title "
				+ "  ORDER BY "+orderBy+" "+collation+"";
		return  sql;
	}
	/**
	 * 活动详情
	 * @param id
	 * @return
	 */
	public final static String getInfo(String id){
		String sql="SELECT  ac.*,acst.activity_end_time,"
				+ "acst.activity_start_time,acst.cancel_time,"
				+ "acst.enroll_begin_time,"
				+ "acst.enroll_end_time,acst.isCancel,"
				+ "acst.prepare_end_time,acst.prepare_start_time"
				+ "  from  tb_activity ac  LEFT JOIN"
				+ " tb_activity_status acst ON"
				+ " ac.id=acst.activity_id  WHERE ac.id='"+id+"'";
		return sql;
	}
	
 
	/** * 活动参与列表
	 * @param title
	 * @return
	 */
	public final static String getPeoList(String id){
		String  like="";
		if(!StringUtil.isNullOrBlank(id)){
			like=" and ac.id = '"+id+"'  ";
 
		}
		String sql="SELECT  acu.*,ac.title,us.username  FROM "
				+ " tb_activity_user_relationship acu "
				+ " LEFT JOIN tb_activity ac ON ac.id=acu.activity_id"
				+ " LEFT JOIN tb_user us ON acu.user_id=us.id"
				+ " where  1=1 "+like+" and acu.man_type!=2";
		return sql;
	}
	
	/**  活动嘉宾列表
	 * @param title
	 * @return
	 */
	public final static String jiabin(String id){
		String  like="";
		if(!StringUtil.isNullOrBlank(id)){
			like=" and ac.id = '"+id+"'  ";
 
		}
		String sql="SELECT  acu.*,ac.title,us.username  FROM "
				+ " tb_activity_user_relationship acu "
				+ " LEFT JOIN tb_activity ac ON ac.id=acu.activity_id"
				+ " LEFT JOIN tb_user us ON acu.user_id=us.id"
				+ " where  1=1 "+like+" and acu.man_type="+Statics.ACTIVITY_USER_TYPE_TOP+"";
		return sql;
	}
	/**
	 * 活动收益列表
	 * @param kaystatus
	 * @param kaytxt
	 * @param createBegin
	 * @param createEnd
	 * @param orderBy
	 * @param collation
	 * @param tree
	 * @return
	 */
	public final static String incomeList(String kaystatus, String kaytxt, String createBegin,
			String createEnd, String orderBy, String collation,String tree){
		String adress="";
		if(!StringUtil.isNullOrBlank(tree)){
			adress=" and  ac.district like '%"+tree+"%' ";
		}
		String kay="";
		if(!StringUtil.isNullOrBlank(kaystatus)||!StringUtil.isNullOrBlank(kaytxt)){
			kay=" and "+kaystatus+"like '%"+kaytxt+"%'  ";
		}
		String creat="";
		if(!StringUtil.isNullOrBlank(createBegin)){
			long i=DateUtil.dateStrToMillis(createBegin,"yyyy-MM-dd");
			creat=" and ac.activity_time>"+i+"";
		}
		String end="";
		if(!StringUtil.isNullOrBlank(createEnd)){
			long i=DateUtil.dateStrToMillis(createEnd,"yyyy-MM-dd");
			end=" and ac.activity_time<"+i+"";
		}
		long creatTime = System.currentTimeMillis();
		String sql="SELECT"
				+ " ac.title, "
				+ "	SUM(o.price) hdsy ,"
				+ "	SUM(o.oldPrice) ffje,"
				+ "	ac.cost"
				+ ",ac.id"
				+ "	 FROM "
				+ "	 tb_activity ac"
				+ "	 LEFT JOIN tb_statistics s ON  s.type_id=ac.id "
				+ "	 LEFT JOIN tb_order o ON ac.id=o.video_id and  o.`status`=1  "
				+ "	 where 1=1  "
				+ " "+kay+""+creat+""+end+""+adress+""
				+ "	 GROUP BY ac.title  "+orderBy+" "+collation+" ";
		return sql;
	}
	
	public final static String orderList(String title){
		String and="";
		if(!StringUtil.isNullOrBlank(title)){
			and=" and  ac.district like '%"+title+"%' ";
		}
		String sql="SELECT  o.*,a.title   FROM tb_order o LEFT JOIN tb_activity a ON o.video_id=a.id"
				+ " where 1=1 "+and+" ";
		return sql;
	}
	/**
	 * 评论列表
	 * @param name
	 * @return
	 */
	public final static String commentList(String name){
		String and="";
		if(!StringUtil.isNullOrBlank(name)){
			and=" and uvr.course_name  like '%"+name+"%'";
		}
		String sql ="SELECT  c.*,uvr.course_name,u.username  FROM tb_comment c " 
					+ " LEFT JOIN tb_user_video_request uvr ON uvr.id=c.comm_content_id "
					+ " LEFT JOIN tb_user u ON c.user_id=u.id"
					+ " WHERE 1=1 "+and+""
					+ " and c.commed_type='3' "
					+ " and c.type= "+Statics.COMMENT_TYPE_REQUEST+"";
		return sql;
	}
	/**
	 * 评论详情
	 * @param id
	 * @return
	 */
	public final static String commentInfo(String id){
		/*if(){
			
		*/
		String sql="SELECT  c.*,uvr.course_name,u.username  FROM tb_comment c " 
				+ " LEFT JOIN tb_user_video_request uvr ON uvr.id=c.comm_content_id "
				+ " LEFT JOIN tb_user u ON c.user_id=u.id"
				+ " WHERE 1=1 "
				+ " and c.commed_type='3' "
				+ " and c.type= "+Statics.COMMENT_TYPE_REQUEST+"";
		return sql;
	}
	
	
}
