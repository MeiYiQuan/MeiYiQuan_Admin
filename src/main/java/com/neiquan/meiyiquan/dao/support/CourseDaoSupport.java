package com.neiquan.meiyiquan.dao.support;

import com.neiquan.meiyiquan.util.DateUtil;
import com.neiquan.meiyiquan.util.Statics;
import com.neiquan.meiyiquan.util.StringUtil;

/**
 * 作者：温尉棨
 * 创建日期：2017年1月20日
 * 类说明：
 */
public class CourseDaoSupport {

	public final static String getList(String createBegin,String createEnd, String orderBy,String collation,String pid){
		String  and="";
		if(!StringUtil.isNullOrBlank(pid)){
			and=" AND c.channel_id='"+pid+"'   ";
		}
		String creat="";
		if(!StringUtil.isNullOrBlank(createBegin)){
			long i=DateUtil.dateStrToMillis(createBegin,"yyyy-MM-dd");
			creat=" and c.create_time>"+i+" ";
		}
		String end="";
		if(!StringUtil.isNullOrBlank(createEnd)){
			long i=DateUtil.dateStrToMillis(createEnd,"yyyy-MM-dd");
			end=" and c.create_time<"+i+"";
		}
		String sql="SELECT c.id,c.title,  "
				+ "	c.create_time ,"
				+ "	s.click_count click_count,"
				+ "	s.like_count like_count,"
				+ "	s.dislike_count,"
				+ "	s.share_count share_count"
				+ ", c.playing_time"
				+ " FROM  tb_course c"
				+ " LEFT JOIN tb_statistics s ON c.id=s.type_id"
				+ "  WHERE 1=1 and c.playing=0 "
				+ " "+and+" "+creat+"  "+end+" "
				+ "  ORDER BY  "+orderBy+"  "+collation+" ";
		return sql;
	}
	/**
	 * 评论列表
	 * @param title
	 * @return
	 */
	public final static String commentCouserList(String title){
		String  and="";
		if(!StringUtil.isNullOrBlank(title)){
			and=" AND c.title='"+title+"'   ";
		}
		String sql="SELECT  cm.*,c.title,u.username   from tb_comment cm"
				+ " LEFT JOIN tb_course c  ON c.id=cm.commed_id"
				+ " LEFT JOIN tb_user u ON cm.user_id=u.id"
				+ " WHERE 1=1 and c.playing=0 "+and+"";
		return sql;
	}
	/**
	 * 统计即将上映
	 * @param title
	 * @return
	 */
	public final static String tongji(String createBegin,String createEnd){
		String creat="";
		if(!StringUtil.isNullOrBlank(createBegin)){
			long i=DateUtil.dateStrToMillis(createBegin,"yyyy-MM-dd");
			creat=" and c.create_time>"+i+" ";
		}
		String end="";
		if(!StringUtil.isNullOrBlank(createEnd)){
			long i=DateUtil.dateStrToMillis(createEnd,"yyyy-MM-dd");
			end=" and c.create_time<"+i+"";
		}
		
		String sql="SELECT "
					+ "SUM(s.click_count) click_count"
					+ ",SUM(s.comment_count) comment_count"
					+ ",SUM(s.like_count) like_count"
					+ ",SUM(s.dislike_count) dislike_count"
					+ ",SUM(s.play_count) play_count"
					+ ",SUM(s.share_count) share_count"
					+ ",SUM(s.collect_count) collect_count"
					+ " FROM tb_statistics s"
					+ " LEFT JOIN tb_course c  ON s.type_id=c.id"
					+ " WHERE c.playing=0"
					+ " "+creat+" "+end+" ";
		return sql;
	}
	/**
	 * 课程数据统计
	 * @param orderby
	 * @return
	 */
	public final static String commentCount(String orderby){
		String sql="SELECT "
				+ "  c.id," 
				+ "  c.title," 
				+ " IFNULL( SUM(s.click_count),0) cli," 
				+ " IFNULL( co.com,0) comm ," 
				+ " IFNULL( sh.com,0) shar ," 
				+ " IFNULL( lik.com,0) lik," 
				+ " IFNULL( play.com,0) pla," 
				+ " IFNULL( col.com,0) coll ," 
				+ " IFNULL( timelong.com,0) timelong ," 
				+ " IFNULL( FORMAT(videosize.com,2),0) coidsize " 
				+ "  FROM tb_course c  " 
				+ "  LEFT JOIN  tb_statistics s ON s.type_id=c.id " 
				+ "  LEFT JOIN (SELECT COUNT(c.id) com ,co.id FROM tb_comment c LEFT JOIN tb_video v ON c.commed_id=v.id LEFT JOIN tb_course co ON co.id=v.course_id GROUP BY co.id) AS co ON co.id=c.id" 
				+ "  LEFT JOIN (SELECT COUNT(s.id) com ,co.id FROM tb_shared s LEFT JOIN tb_course co ON co.id=s.shareId GROUP BY co.id) AS  sh  ON sh.id=c.id" 
				+ "  LEFT JOIN (SELECT sum(s.like_count) com ,co.id FROM tb_statistics  s LEFT JOIN tb_course co ON s.type_id=co.id GROUP BY co.id) AS  lik   ON lik.id=c.id" 
				+ "  LEFT JOIN (SELECT sum(s.play_count) com ,co.id FROM tb_statistics  s LEFT JOIN tb_video v ON s.type_id=v.id  LEFT JOIN tb_course co ON co.id=v.course_id GROUP BY co.id) AS  play   ON play.id=c.id" 
				+ "  LEFT JOIN (SELECT sum(s.collect_count) com ,co.id FROM tb_statistics  s LEFT JOIN tb_video v ON s.type_id=v.id  LEFT JOIN tb_course co ON co.id=v.course_id GROUP BY co.id) AS  col    ON col.id=c.id" 
				+ "  LEFT JOIN ( SELECT SUM(v.time_long ) com ,co.id FROM tb_video v LEFT JOIN tb_course co ON v.course_id=co.id GROUP BY co.id ) AS timelong ON timelong.id=c.id" 
				+ " LEFT JOIN ( SELECT SUM(v.video_size ) com ,co.id FROM tb_video v LEFT JOIN tb_course co ON v.course_id=co.id GROUP BY co.id ) AS videosize ON videosize.id=c.id" 
				+ " GROUP BY c.id ORDER BY "+orderby+" DESC" ;
		return sql;
	}
	/** 播放节点统计
	 * 
	 * @param type 1 一分钟内 2 一到五分钟 3 五到十分钟 4十到二十分钟 5二十分钟以上
	 * @return
	 */
	public final static String nodeCount(String type,String id){
		String and="";
		if(type.equals(Statics.PLAYRECORD_TYPE_ONE)){
			and=" and p.continue_time<60";
		}
		if(type.equals(Statics.PLAYRECORD_TYPE_TWO)){
			and=" and p.continue_time>60 and p.continue_time<300";
		}
		if(type.equals(Statics.PLAYRECORD_TYPE_THE)){
			and=" and p.continue_time>300 and p.continue_time<600";
		}
		if(type.equals(Statics.PLAYRECORD_TYPE_FOR)){
			and=" and p.continue_time>600 and p.continue_time<1200";
		}
		if(type.equals(Statics.PLAYRECORD_TYPE_FIV)){
			and=" and p.continue_time>1200";
		}
		
		String sql="SELECT  IFNULL(COUNT(v.id),0) AS co  "
				+ "  FROM  tb_video v "
				+ " LEFT JOIN tb_playrecord p ON p.video_id=v.id"
				+ " WHERE 1=1 "+and+" and v.id='"+id+"' ";
		return sql;
	}
}
