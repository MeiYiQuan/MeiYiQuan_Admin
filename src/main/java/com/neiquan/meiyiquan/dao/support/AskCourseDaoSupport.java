package com.neiquan.meiyiquan.dao.support;

import java.util.Map;

import com.neiquan.meiyiquan.code.Code;
import com.neiquan.meiyiquan.util.DateUtil;
import com.neiquan.meiyiquan.util.Paging;
import com.neiquan.meiyiquan.util.Statics;
import com.neiquan.meiyiquan.util.StringUtil;

/**
 * 作者：温尉棨
 * 创建日期：2017年1月21日
 * 类说明：求教程
 */
public class AskCourseDaoSupport {
	/**
	 * 最新求教程列表
	 * @param kaystatus
	 * @param kaytxt
	 * @param createBegin
	 * @param createEnd
	 * @param status
	 * @param tree
	 * @return
	 */
	public final static String asklistSql(String kaystatus,String kaytxt,
			String createBegin,String createEnd,String status,String tree
			,String orderBy,String collation,String channelid){
		if(kaystatus.equals("1")){
			kaystatus=" u.username ";
		}
		if(kaystatus.equals("2")){
			kaystatus=" t.`name` ";
		}
		if(kaystatus.equals("3")){
			kaystatus=" uvr.course_name ";
		}
		String adress="";
		if(!StringUtil.isNullOrBlank(tree)){
			adress=" and  u.district  like '%"+tree+"%' ";
		}
		String kay="";
		if(!StringUtil.isNullOrBlank(kaystatus)||!StringUtil.isNullOrBlank(kaytxt)){
			kay=" and "+kaystatus+"like '%"+kaytxt+"%'  ";
		}
		String creat="";
		if(!StringUtil.isNullOrBlank(createBegin)){
			long i=DateUtil.dateStrToMillis(createBegin,"yyyy-MM-dd");
			creat=" and uvr.request_time>"+i+" ";
		}
		String end="";
		if(!StringUtil.isNullOrBlank(createEnd)){
			long i=DateUtil.dateStrToMillis(createEnd,"yyyy-MM-dd");
			end=" and uvr.request_time<"+i+"";
		}
		String channelidd="";
		if(!StringUtil.isNullOrBlank(channelid)){
			channelidd=" and c.id='"+channelid+"'";
		}
		String sql="SELECT  "
					+ " uvr.*"
					+ " ,s.click_count click_count"
					+ ",s.share_count  share_count"
					+ ",s.comment_count comment_count"
					+ " ,u.username"
					+ " ,t.name"
					+ "  "
					+ " FROM "
					+ "  tb_user_video_request uvr"
					+ " LEFT JOIN tb_user u ON u.id =uvr.user_id"
					+ " LEFT JOIN tb_teacher t ON t.teacher_id=uvr.teacher_id"
					+ " LEFT JOIN tb_user_request ur ON ur.requestId =uvr.id"
					+ " LEFT JOIN tb_statistics s ON s.type_id = uvr.id"
					+ " LEFT JOIN tb_channel c ON c.id = uvr.channel_id"
					+ "  WHERE 1=1"
					+ " "+creat+" "
					+ " "+end+" "
					+ " "+adress+""
					+ " "+kay+""
					+ " "+channelidd+""
					+ " AND  uvr.feedback_status =3"
					+ " ORDER BY "+orderBy+" "+collation+" ";
		return sql;
	}
	/**
	 * 求教程审核列表（全部展示）
	 * @param kaystatus
	 * @param kaytxt
	 * @param createBegin
	 * @param createEnd
	 * @param orderBy
	 * @param collation
	 * @param status
	 * @param tree
	 * @return
	 */
	public final static String  voteList(String kaystatus,String kaytxt,
			String createBegin,String createEnd ,String status){
		String statuss="";
		if(!StringUtil.isNullOrBlank(status)){
			statuss=" and uvr.feedback_status="+status+" ";
		}
		
		if(kaystatus.equals("1")){
			kaystatus=" u.username ";
		}
		if(kaystatus.equals("2")){
			kaystatus=" t.`name` ";
		}
		if(kaystatus.equals("3")){
			kaystatus=" uvr.course_name ";
		}
		
		String kay="";
		if(!StringUtil.isNullOrBlank(kaystatus)||!StringUtil.isNullOrBlank(kaytxt)){
			kay=" and "+kaystatus+"like '%"+kaytxt+"%'  ";
		}
		String creat="";
		if(!StringUtil.isNullOrBlank(createBegin)){
			long i=DateUtil.dateStrToMillis(createBegin,"yyyy-MM-dd");
			creat=" and uvr.request_time>"+i+" ";
		}
		String end="";
		if(!StringUtil.isNullOrBlank(createEnd)){
			long i=DateUtil.dateStrToMillis(createEnd,"yyyy-MM-dd");
			end=" and uvr.request_time<"+i+"";
		}
		String sql="SELECT  "
					+ " uvr.*"
					+ " ,if(uvr.add_type = 2,u.username,'平台添加') as `username`"
					+ " ,t.name"
					+ " FROM "
					+ "  tb_user_video_request uvr"
					+ " LEFT JOIN tb_user u ON u.id =uvr.user_id"
					+ " LEFT JOIN tb_teacher t ON t.teacher_id=uvr.teacher_id"
					+ " LEFT JOIN tb_user_request ur ON ur.requestId =uvr.id "
					+ "  WHERE 1=1"
					+ " "+creat+" "
					+ " "+end+" "
					+ " "+kay+""
					+ " "+statuss+""
					+ " ORDER BY  uvr.request_time DESC ";
		return sql;
	}
	
	public final static String topList(String kaystatus,String kaytxt,
			String createBegin,String createEnd){
		String creat="";
		if(!StringUtil.isNullOrBlank(createBegin)){
			long i=DateUtil.dateStrToMillis(createBegin,"yyyy-MM-dd");
			creat=" and uvr.request_time>"+i+" ";
		}
		String end="";
		if(!StringUtil.isNullOrBlank(createEnd)){
			long i=DateUtil.dateStrToMillis(createEnd,"yyyy-MM-dd");
			end=" and uvr.request_time<"+i+"";
		}
		/*String sql="select"
					+ " from_unixtime(uvr.feedback_time,'%Y-%u') weeks"
					+ ",uvr.*  "
					+ " ,if(uvr.add_type = 2,u.username,'平台添加') as `username`"
					+ " ,t.`name` "
					+ "from "
					+ " tb_user_video_request uvr "
					+ "  LEFT JOIN tb_user u ON uvr.user_id=u.id"
					+ "  LEFT JOIN tb_teacher t ON t.teacher_id=uvr.teacher_id"
					+ " WHERE uvr.vote=(SELECT MAX(uvr1.vote) FROM tb_user_video_request uvr1 "
					+ " WHERE  from_unixtime(uvr.feedback_time,'%Y-%u')= from_unixtime(uvr1.feedback_time,'%Y-%u') )"
					+ " "+creat+""
					+ " "+end+""
					+ "  group by weeks "
					+ " ORDER BY  uvr.feedback_time DESC ";*/
		String sql="select"
				+ " from_unixtime(uvr.request_time/1000,'%Y-%u') weeks"
				+ ",uvr.*  "
				+ " ,if(uvr.add_type = 2,u.username,'平台添加') as `username`"
				+ " ,t.`name` "
				+ "from "
				+ " tb_user_video_request uvr "
				+ "  LEFT JOIN tb_user u ON uvr.user_id=u.id"
				+ "  LEFT JOIN tb_teacher t ON t.teacher_id=uvr.teacher_id"
				+ " where uvr.top_type=1"
				+ " "+creat+""
				+ " "+end+""
				+ "  group by weeks "
				+ " ORDER BY  uvr.request_time DESC ";
		return sql;
	}
	/**
	 * 修改求课程榜首
	 * @return
	 */
	public final static String updateTop(){
		String sql="select"
				+ " from_unixtime(uvr.request_time/1000,'%Y-%u') weeks"
				+ ",uvr.*  "
				+ " ,if(uvr.add_type = 2,u.username,'平台添加') as `username`"
				+ " ,t.`name` "
				+ "from "
				+ " tb_user_video_request uvr "
				+ "  LEFT JOIN tb_user u ON uvr.user_id=u.id"
				+ "  LEFT JOIN tb_teacher t ON t.teacher_id=uvr.teacher_id"
				+ " where uvr.top_type=1"
				+ "  group by weeks "
				+ " ORDER BY  uvr.request_time DESC ";;
		return sql;
	}
	
	/**
	 * 求教程详情
	 * @param id
	 * @return
	 */
	public final static String infoSql(String id){
		String sql="select * from tb_user_video_request where id='"+id+"'";
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
					+ " LEFT JOIN tb_user_video_request uvr ON uvr.id=c.commed_id "
					+ " LEFT JOIN tb_user u ON c.user_id=u.id"
					+ " WHERE 1=1 "+and+""
					+ " and c.type= "+Statics.COMMENT_TYPE_REQUEST+"";
		return sql;
	}
	/**
	 * 评论详情
	 * @param id
	 * @return
	 */
	public final static String commentInfo(String id){
		
		String sql="SELECT  c.*,uvr.course_name,u.username  FROM tb_comment c " 
				+ " LEFT JOIN tb_user_video_request uvr ON uvr.id=c.commed_id "
				+ " LEFT JOIN tb_user u ON c.user_id=u.id"
				+ " WHERE 1=1 "
				+ " and c.commed_id='"+id+"'";
		return sql;
	}
	/**
	 * 点击率  投票率  。。。
	 * @param type 1是2否是平台
	 * @param typee  是0普通用户 还是1讲师
	 * @return
	 */
	public final static String typeTongji (String type, String typee){
		String and="";
		String andd="";
		if(type.equals("1")){
			and="and uvr.add_type="+type+" ";
		}else{
			and=" and uvr.add_type= "+type+" ";
			andd=" and u.user_type="+typee+"";
		}
		String sql="SELECT  uvr.id "
					+ " FROM tb_user_video_request uvr "
					+ " LEFT JOIN tb_user u ON uvr.user_id=u.id "
					+ " LEFT JOIN  tb_statistics s ON s.type_id= uvr.id "
					+ " WHERE  1=1 "
					+ " "+and+" "
					+ " "+andd+" ";
		return sql;
	}
	/**
	 * 统计  点击量  投票量。。。
	 * @param type
	 * @param typee
	 * @return
	 */
	public final static String numTongji(String type, String typee){
		String and="";
		String andd="";
		if(type.equals("1")){
			and="and uvr.add_type="+type+" ";
		}else{
			and=" and uvr.add_type= "+type+" ";
			andd=" and u.user_type="+typee+"";
		}
		String sql="SELECT  "
					+ " sum(uvr.vote) vote,"
					+ "SUM(s.click_count) click_count,"
					+ "sum(s.share_count) share_count,"
					+ "SUM(s.comment_count) comment_count,"
					+ "SUM(s.collect_count)  collect_count "
					+ "FROM tb_user_video_request uvr "
					+ " LEFT JOIN tb_user u ON uvr.user_id=u.id "
					+ " LEFT JOIN  tb_statistics s ON s.type_id= uvr.id "
					+ " WHERE  1=1 "
					+ " "+and+" "
					+ " "+andd+" ";
		return sql;
	}
}
