package com.neiquan.meiyiquan.service.impl;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neiquan.meiyiquan.code.Code;
import com.neiquan.meiyiquan.dao.support.AskCourseDaoSupport;
import com.neiquan.meiyiquan.pojo.Order;
import com.neiquan.meiyiquan.pojo.User;
import com.neiquan.meiyiquan.pojo.UserSurvey;
import com.neiquan.meiyiquan.service.UserService;
import com.neiquan.meiyiquan.util.DateUtil;
import com.neiquan.meiyiquan.util.ExtraSpringHibernateTemplate;
import com.neiquan.meiyiquan.util.Paging;
import com.neiquan.meiyiquan.util.SessionUtil;
import com.neiquan.meiyiquan.util.StringUtil;


/**
 * 作者：刘丹
 * 创建日期：2017年1月13日
 * 类说明：讲师管理模块
 */
@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private ExtraSpringHibernateTemplate esht;

	@Override
	public Code getList(String keywords,String user_type ,Integer pageIndex,Integer pageSize,
			String timeStar,String timeEnd,String orderBy,String collation ,String district,
			String  zodiac, String gender, String  user_state,String job) {
		StringBuffer sql=new StringBuffer();
		sql.append(" SELECT");
		sql.append(" * ");
		sql.append(" FROM");
		sql.append(" tb_user");
		sql.append(" where 1=1 ");
		if(!StringUtil.isNullOrBlank(keywords)){
			sql.append(" and  (tb_user.phone like'%");
			sql.append(keywords);
			sql.append("%' ");
			sql.append(" or tb_user.username like'%");
			sql.append(keywords);
			sql.append("%' )");
		}
		if(!StringUtil.isNullOrBlank(timeStar)){
			Long l=	DateUtil.getTimeLong(timeStar, "yyyy-MM-dd");
			sql.append(" and tb_user.create_time >");
			sql.append(l);
			sql.append(" ");
		}
		if(!StringUtil.isNullOrBlank(timeEnd)){
			Long l=DateUtil.getTimeLong(timeEnd, "yyyy-MM-dd");
			sql.append(" and tb_user.create_time <");
			sql.append(l);
			sql.append(" ");
		}
		if(!StringUtil.isNullOrBlank(user_type)){
			sql.append(" and tb_user.user_type ='");
			sql.append(user_type);
			sql.append("' ");
		}
		if(!StringUtil.isNullOrBlank(user_state)){
			sql.append(" and tb_user.user_state ='");
			sql.append(user_state);
			sql.append("' ");
		}
		if(!StringUtil.isNullOrBlank(gender)){
			sql.append(" and tb_user.gender ='");
			sql.append(gender);
			sql.append("' ");
		}
		if(!StringUtil.isNullOrBlank(zodiac)){
			sql.append(" and tb_user.zodiac ='");
			sql.append(zodiac);
			sql.append("' ");
		}
		if(!StringUtil.isNullOrBlank(district)){
			sql.append(" and tb_user.district ='");
			sql.append(zodiac);
			sql.append("' ");
		}
		if(!StringUtil.isNullOrBlank(job)){
			sql.append(" and tb_user.job ='");
			sql.append(job);
			sql.append("' ");
		}
		sql.append(" ORDER BY ");
		sql.append(orderBy);//排序判断
		sql.append(" ");//排序判断
		sql.append(collation);
		Paging<Map>	p= esht.createSQLQueryfindPage(sql.toString(), (pageIndex-1)*pageSize, pageSize);
		return Code.init(0, null, p);
	}
	@Override
	public Code upDate(String id,String  status,HttpSession session) {
		User nd	=	esht.findFirstOneByPropEq(User.class,"id" ,id);
		if(nd==null)return Code.init(1, "未找到此节点", null);
		nd.setUser_state(Integer.valueOf(status));
		esht.getHibernateTemplate().update(nd);
		return Code.init(0, "执行成功", null);
	}
	
	@Override
	public Code verPhone(Long phone) {
		User nd	=	esht.findFirstOneByPropEq(User.class,"phone" ,phone);
		if(nd==null){
			return Code.init(0, "手机号可使用");
		}
		return Code.init(1, "手机号重复");
	}
	
	@Override
	public Code upComment(String id,Integer  status,HttpSession session) {
		User nd	=	esht.findFirstOneByPropEq(User.class,"id" ,id);
		if(nd==null)return Code.init(1, "未找到此节点", null);
		nd.setIsComment(status);
		esht.getHibernateTemplate().update(nd);
		return Code.init(0, "执行成功", null);
	}
	@Override
	public User getUserById(String id) {
		User user=	esht.findFirstOneById(User.class, id);
		return user;
	}
	@Override
	public Code getUserOrder(String id) {
		List<Order> or=	esht.findAllByPropEq(Order.class, "user_id", id);
		return Code.init(0, "执行成功", or);
	}
	
	
	@Override
	public Code getSurveyList(String keywords,String user_type ,Integer pageIndex,Integer pageSize,
			String timeStar,String timeEnd,String orderBy,String collation ,String district,
			String  zodiac, String gender, String  user_state,String job) {
		StringBuffer sql=new StringBuffer();
		sql.append(" SELECT");
		sql.append(" *, ");
		sql.append(" (SELECT count(tb_user_survey.id) FROM tb_user_survey WHERE tb_user.id=tb_user_survey.userId) AS surveyNum ");
		sql.append(" FROM");
		sql.append(" tb_user ");
		sql.append(" where 1=1 ");
		if(!StringUtil.isNullOrBlank(keywords)){
			sql.append(" and  (tb_user.phone like'%");
			sql.append(keywords);
			sql.append("%' ");
			sql.append(" or tb_user.username like'%");
			sql.append(keywords);
			sql.append("%' )");
		}
		if(!StringUtil.isNullOrBlank(timeStar)){
			Long l=	DateUtil.getTimeLong(timeStar, "yyyy-MM-dd");
			sql.append(" and tb_user.create_time >");
			sql.append(l);
			sql.append(" ");
		}
		if(!StringUtil.isNullOrBlank(timeEnd)){
			Long l=DateUtil.getTimeLong(timeEnd, "yyyy-MM-dd");
			sql.append(" and tb_user.create_time <");
			sql.append(l);
			sql.append(" ");
		}
		if(!StringUtil.isNullOrBlank(user_type)){
			sql.append(" and tb_user.user_type ='");
			sql.append(user_type);
			sql.append("' ");
		}
		if(!StringUtil.isNullOrBlank(user_state)){
			sql.append(" and tb_user.user_state ='");
			sql.append(user_state);
			sql.append("' ");
		}
		if(!StringUtil.isNullOrBlank(gender)){
			sql.append(" and tb_user.gender ='");
			sql.append(gender);
			sql.append("' ");
		}
		if(!StringUtil.isNullOrBlank(zodiac)){
			sql.append(" and tb_user.zodiac ='");
			sql.append(zodiac);
			sql.append("' ");
		}
		if(!StringUtil.isNullOrBlank(district)){
			sql.append(" and tb_user.district ='");
			sql.append(zodiac);
			sql.append("' ");
		}
		if(!StringUtil.isNullOrBlank(job)){
			sql.append(" and tb_user.job ='");
			sql.append(job);
			sql.append("' ");
		}
		sql.append(" ORDER BY ");
		sql.append(orderBy);//排序判断
		sql.append(" ");//排序判断
		sql.append(collation);
		Paging<Map>	p= esht.createSQLQueryfindPage(sql.toString(), (pageIndex-1)*pageSize, pageSize);
		return Code.init(0, null, p);
	}
	@Override
	public Code getUserSurvey(String id,Integer pageIndex,Integer pageSize,String orderBy,String collation) {
		StringBuffer sb=new StringBuffer();
		sb.append("SELECT * FROM tb_user_survey  ");
		sb.append(" ORDER BY ");
		sb.append(orderBy);//排序判断
		sb.append(" ");//排序判断
		sb.append(collation);
		Paging<Map>	 p=esht.createSQLQueryfindPage(sb.toString(), (pageIndex-1)*pageSize, pageSize);
		return  Code.init(0, null, p);
	}
	@Override
	public Code addUserSurvey(String id,String remark,HttpSession session ) {
		UserSurvey us=new UserSurvey();
		us.setCreate_time(System.currentTimeMillis());
		us.setRemark(remark);
		us.setUserId(id);
		us.setAdminId(SessionUtil.getUserId(session));
		esht.getHibernateTemplate().save(us);
		return  Code.init(0, "成功");
	}
	/**
	 * 会员统计
	 */
	@Override
	public Code userCount(String orderby) {
		
		long i=System.currentTimeMillis();
		String sql=" SELECT "
					+ " u.username"
					+ "	,COUNT(uvr.id) ask "
					+ "	,COUNT(com.id) comm "
					+ "	,COUNT(s.id) shar"
					+ "	 ,COUNT(col.id) coll"
					+ "	 ,sum(p.point_quantum) poi "
					+ "	,COUNT(o1.id) cou  "
					+ "	, COUNT(o2.id) act "
					+ "	FROM tb_user u"
					+ "	 LEFT JOIN tb_user_video_request uvr ON u.id=uvr.user_id"
					+ "	 LEFT JOIN tb_comment com  ON  com.user_id=u.id"
					+ "	 LEFT JOIN tb_shared s ON  s.userId=u.id"
					+ "	 LEFT JOIN tb_collect col ON col.user_id=u.id"
					+ "	 LEFT JOIN  tb_point p ON p.user_id=u.id AND p.`status`=1 AND p.expire_time<"+i+""
					+ "	 LEFT JOIN tb_order o1 ON  o1.user_id=u.id AND o1.isUseCoupon=1 AND o1.status=1"
					+ "	 LEFT JOIN  tb_order o2 ON  o2.user_id=u.id AND o2.type=2 AND o2.status=1"
					+ "	 GROUP BY u.id ORDER BY "+orderby+" DESC";
			Paging<Map>	p= esht.createSQLQueryfindPage(sql, 0, 10);
			return Code.init(0, null, p);
	}
}
