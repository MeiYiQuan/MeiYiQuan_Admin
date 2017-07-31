package com.neiquan.meiyiquan.dao.support;

import com.neiquan.meiyiquan.util.DateUtil;
import com.neiquan.meiyiquan.util.StringUtil;

/**
 * 作者：温尉棨
 * 创建日期：2017年2月5日
 * 类说明：客服投诉列表（未处理）
 */
public class SuggestionDaoSupport {
	//反馈列表
	public final static String listSql(String kaytxt,
			String createBegin,String tree,String type,String status){
		String adress="";
		if(!StringUtil.isNullOrBlank(tree)){
			adress=" and  u.district like '%"+tree+"%' ";
		}
		String kay="";
		if(!StringUtil.isNullOrBlank(kaytxt)){
			kay=" and u.username like '%"+kaytxt+"%'  ";
		}
		String creat="";
		if(!StringUtil.isNullOrBlank(createBegin)){
			long i=DateUtil.dateStrToMillis(createBegin,"yyyy-MM-dd");
			creat=" and s.back_time ="+i+" ";
		}
		
		String typee="";
		if(!StringUtil.isNullOrBlank(status)){
			typee=" and s.genre ="+status+" ";
		}
		String stauss="";
		if(!StringUtil.isNullOrBlank(type)){
			stauss=" and s.status="+type+" ";
		}
		
		String sql="select  s.*,u.district,u.username"
				+ ",u.job,u.user_type"
				+ " from  tb_suggestion s  "
				+ " left join tb_user u on s.user_id =u.id"
				+ " where 1=1 "+adress+" "+kay+" "+creat+"  "+typee+"  "+stauss+" ";
		return sql;
	}
	//统计列表
	public final static String tongji(String type, String timeBegin,String timeEnd){
		String begin="";
		if(!StringUtil.isNullOrBlank(timeBegin)){
			long i=DateUtil.dateStrToMillis(timeBegin,"yyyy-MM-dd");
			begin=" and s.back_time ="+i+" ";
		}
		String end="";
		if(!StringUtil.isNullOrBlank(timeEnd)){
			long i=DateUtil.dateStrToMillis(timeEnd,"yyyy-MM-dd");
			end=" and s.back_time ="+i+" ";
		}
		String sql="SELECT"
				+ " COUNT(tb_suggestion.id) countt ,"
				+ " tb_suggestion.genre  gener,"
				+ " 	tb_suggestion_type.`name`  na,"
				+ " 	from_unixtime(tb_suggestion.back_time)  time"
				+ " 	FROM"
				+ " 	tb_suggestion"
				+ " 	LEFT JOIN tb_suggestion_type ON tb_suggestion_type.genre=tb_suggestion.genre"
				+ " 	WHERE 1=1 "
				+ " 	 "+begin+" "+end+""
				+ "     and tb_suggestion.status="+type+" "
				+ " 	GROUP BY  tb_suggestion.genre ";
		return sql;
	}

	public final static String zongtongji(String type,String timeBegin,String timeEnd){
		String begin="";
		if(!StringUtil.isNullOrBlank(timeBegin)){
			long i=DateUtil.dateStrToMillis(timeBegin,"yyyy-MM-dd");
			begin=" and s.back_time ="+i+" ";
		}
		String end="";
		if(!StringUtil.isNullOrBlank(timeEnd)){
			long i=DateUtil.dateStrToMillis(timeEnd,"yyyy-MM-dd");
			end=" and s.back_time ="+i+" ";
		}
		String sql="select * from "
				+ " tb_suggestion s "
				+ " where 1=1"
				+ " "+begin+" "+end+""
				+ " and s.status="+type+"";
		return sql;
	}
}
