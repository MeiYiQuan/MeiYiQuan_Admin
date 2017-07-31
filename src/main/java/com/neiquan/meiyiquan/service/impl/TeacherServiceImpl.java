package com.neiquan.meiyiquan.service.impl;

import java.security.NoSuchAlgorithmException;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neiquan.meiyiquan.code.Code;
import com.neiquan.meiyiquan.dao.ObjectDao;
import com.neiquan.meiyiquan.pojo.Job;
import com.neiquan.meiyiquan.pojo.Teacher;
import com.neiquan.meiyiquan.pojo.User;
import com.neiquan.meiyiquan.service.TeacherService;
import com.neiquan.meiyiquan.util.DateUtil;
import com.neiquan.meiyiquan.util.ExtraSpringHibernateTemplate;
import com.neiquan.meiyiquan.util.MD5Util;
import com.neiquan.meiyiquan.util.Paging;
import com.neiquan.meiyiquan.util.Statics;
import com.neiquan.meiyiquan.util.StringUtil;


/**
 * 作者：刘丹
 * 创建日期：2017年1月13日
 * 类说明：讲师管理模块
 */
@Service
public class TeacherServiceImpl implements TeacherService {
	
	@Autowired
	private ExtraSpringHibernateTemplate esht;
	
	@Autowired
	private ObjectDao od;

	@Override
	public Code getTeacherList(String keywords,String status ,Integer pageIndex,Integer pageSize,
			String timeStar,String timeEnd,String orderBy,String collation,String tree ) {
		StringBuffer sql=new StringBuffer();
		sql.append(" SELECT");
		sql.append(" tb_teacher.*, tb_user.district, ");
		sql.append(" count(tb_video.id) videoNum, ");
		sql.append(" SUM(tb_order.price) orderPrice ");
		sql.append(" FROM");
		sql.append(" tb_teacher");
		sql.append(" LEFT JOIN tb_statistics s ON s.type_id=tb_teacher.teacher_id ");
		sql.append(" LEFT JOIN tb_user ON tb_user.id=tb_teacher.teacher_id ");
		sql.append(" LEFT JOIN tb_course ON tb_course.teacher_id=tb_teacher.teacher_id ");
		sql.append(" LEFT JOIN tb_video ON tb_course.id=tb_video.course_id ");
		sql.append(" LEFT JOIN tb_order ON tb_video.id=tb_order.video_id AND tb_order.`status`=1 ");
		sql.append(" WHERE tb_teacher.name LIKE '%"+keywords+"%' and tb_teacher.`status` LIKE '%"+status+"%' ");
		if(!StringUtil.isNullOrBlank(tree)){//判断开始时间
			sql.append(" AND tb_user.district like '%"+tree+ "%' " );
		}
		if(!StringUtil.isNullOrBlank(timeStar)){//判断开始时间
			sql.append(" AND tb_teacher.create_time > ");
			sql.append(DateUtil.dateStrToMillis(timeStar,"yyyy-MM-dd"));
		}
		if(!StringUtil.isNullOrBlank(timeEnd)){//判断结束时间
			sql.append(" AND tb_teacher.create_time < ");
			sql.append(DateUtil.dateStrToMillis(timeEnd,"yyyy-MM-dd"));
		}
		sql.append(" GROUP BY tb_teacher.id ");
		sql.append(" ORDER BY ");
		sql.append(orderBy);//排序判断
		sql.append(" ");//排序判断
		sql.append(collation);
		Paging<Map>	p= esht.createSQLQueryfindPage(sql.toString(), (pageIndex-1)*pageSize, pageSize);
		return Code.init(0, null, p);
	}
	
	
	@Override
	public Code add(Long phone ,String password ,String username ,
			String remark ,HttpSession session,String tree,String first_word,String head_url,String job
			,String percent,String make_type,String make_account
			,String top_type,String top_pic_url,String fileData) {
		Code code=	verPhone(phone);
		if(code.getCode()==1){
			return code;
		}
		long now = System.currentTimeMillis();
		User user=new User();
		user.setPhone(phone);
		user.setDistrict(tree.replace("-", ""));
		try {
			user.setPassword(MD5Util.encrypt(password));
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		user.setJob("");
		user.setUsername(username);
		
		user.setAgeId("");
		user.setBirthday(0L);
		user.setBuy_count(0);
		user.setContinue_day(0);
		user.setCreate_time(now);
		user.setEqui_type(-1);
		user.setGender(2);
		user.setIosCoints(0);
		user.setIsComment(1);
		user.setJobId("");
		user.setLatest_login_time(0L);
		user.setLatest_sign_time(0L);
		user.setPic_save_url(head_url);
		user.setPush_token("");
		user.setSsoId("");
		user.setUpdate_time(now);
		user.setUser_state(1);
		user.setUser_type(1);
		user.setVchat_iden(0);
		user.setZodiac("");
		user.setZodiacIndex(-1);
		esht.getHibernateTemplate().save(user);
		
		
		
		Teacher nd=new Teacher();
		
		Job j = od.getObjById(Job.class, job);
		
		nd.setTeacher_id(user.getId().toString());	
		nd.setCreate_time(now);
		nd.setStatus(Statics.NO_INT);
		nd.setName(username);
		nd.setLevel_id(job);
		nd.setLevel(j.getJob_name());
		nd.setRemark(remark);
		nd.setFirst_word(first_word);
		nd.setUpdate_time(now);
		nd.setHead_url(head_url);
		Double percentt = Double.valueOf(percent);
		int make_typee = Integer.valueOf(make_type).intValue();
		nd.setPercent(percentt);
		nd.setMake_type(make_typee);
		nd.setMake_account(make_account);
		nd.setTop_pic_url(top_pic_url);
		int top_typee = Integer.valueOf(top_type).intValue();
		nd.setTop_type(top_typee);
		nd.setTop_video_url(fileData);
		
		esht.getHibernateTemplate().save(nd);
		return Code.init(0, "添加成功", null);
	}
	@Override
	public Code update(String id,String username ,String remark ,HttpSession session,String tree,
			String first_word,String head_url,String job,String percent,String make_type,String make_account
			,String top_type,String top_pic_url,String fileData) {
		Job j = od.getObjById(Job.class, job);
		Teacher nd=esht.findFirstOneByPropEq(Teacher.class, "id", id);
		nd.setName(username);
		nd.setRemark(remark);
		nd.setFirst_word(first_word);
		nd.setUpdate_time(System.currentTimeMillis());
		nd.setHead_url(head_url); 
		nd.setLevel(j.getJob_name());
		nd.setLevel_id(job);
		Double percentt = Double.valueOf(percent);
		int make_typee = Integer.valueOf(make_type).intValue();
		nd.setPercent(percentt);
		nd.setMake_type(make_typee);
		nd.setMake_account(make_account);
		nd.setTop_pic_url(top_pic_url);
		int top_typee = Integer.valueOf(top_type).intValue();
		nd.setTop_type(top_typee);
		nd.setTop_video_url(fileData);
		
		esht.getHibernateTemplate().update(nd);
		return Code.init(0, "修改成功", null);
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
	public Code getTeacherById(String id) {
		Teacher t	=	esht.findFirstOneByPropEq(Teacher.class,"teacher_id" ,id);
			 if(t==null){
					return Code.init(2, "数据不存在",null);
			 }
		return Code.init(0, "成功",t);
	}
	@Override
	public Code upStatus(String id,String  status,HttpSession session) {
		Teacher nd	=	esht.findFirstOneByPropEq(Teacher.class,"id" ,id);
		if(nd==null)return Code.init(1, "未找到此节点", null);
		nd.setStatus(Integer.valueOf(status));
		nd.setUpdate_time(System.currentTimeMillis());
		esht.getHibernateTemplate().update(nd);
		return Code.init(0, "执行成功", null);
	}
	@Override
	public Code upWorth(String id,String  worth,HttpSession session) {
		if(StringUtil.isNullOrBlank(worth))return Code.init(1,"参数不完整", null);
		if(worth==null)return Code.init(1, "未找到此节点", null);
		Teacher nd	=	esht.findFirstOneByPropEq(Teacher.class,"id" ,id);
		nd.setWorth((double)Double.valueOf(worth));
		nd.setUpdate_time(System.currentTimeMillis());
		esht.getHibernateTemplate().update(nd);
		return Code.init(0, "执行成功", null);
	}
 


	/**
	 * 讲师统计
	 */
	@Override
	public Code countTeacher(String order) {
		
		String sql="SELECT"
				+ " t.teacher_id,"
				+ " t.`name`,"
				+ " IFNULL(orr.cu,0) ffl"
				+ ",IFNULL(orr.su,0) ffje"
				+ ",IFNULL(zx.zxl,0) zxl"
				+ ",IFNULL(vi.co,0) video"
				+ ",IFNULL(cl.co,0) fs"
				+ ",IFNULL(sh.co,0) shar"
				+ ",IFNULL(com.co,0) comm "

			    + " FROM tb_teacher t "
			    + " LEFT JOIN (SELECT t.teacher_id id,COUNT(o.id) cu ,SUM(o.sum_money) su  FROM tb_teacher t"
			    + " LEFT JOIN tb_teacher_order o ON t.teacher_id=o.teacher_id GROUP BY t.teacher_id ) AS  orr ON orr.id=t.teacher_id"
			    + " LEFT JOIN (SELECT t.teacher_id,IF((COUNT(o.id)-vs.standard)<0,(COUNT(o.id)-vs.standard),0) as zxl FROM	tb_teacher t"
			    + " LEFT JOIN tb_course c ON t.teacher_id = c.teacher_id LEFT JOIN tb_video v ON c.id = v.course_id LEFT JOIN tb_order o ON v.id = o.video_id"
			    + " LEFT JOIN tb_video_standard vs ON c.id = vs.correlation_id AND o.create_time <vs.finish_time GROUP BY	t.teacher_id) zx ON zx.teacher_id=t.teacher_id"
			    + " LEFT JOIN (SELECT t.teacher_id,COUNT(v.id) co   FROM tb_teacher t LEFT JOIN tb_course c ON t.teacher_id=c.teacher_id"
			    + " LEFT JOIN tb_video v ON c.id=v.course_id GROUP BY t.teacher_id ) vi ON vi.teacher_id=t.teacher_id "
			    + " LEFT JOIN (SELECT t.teacher_id,COUNT(c.id) co   FROM tb_teacher t"
			    + " LEFT JOIN tb_collect c ON t.teacher_id=c.collect_type_id GROUP BY t.teacher_id ) cl ON cl.teacher_id=t.teacher_id"
			    + " LEFT JOIN (SELECT t.teacher_id,COUNT(s.id) co   FROM tb_teacher t LEFT JOIN tb_course c on c.teacher_id=t.teacher_id"
				+ " LEFT JOIN tb_shared s ON s.shareId=c.id GROUP BY t.id) sh ON sh.teacher_id=t.teacher_id"
				+ " LEFT JOIN (SELECT t.teacher_id,COUNT(cm.id) co   FROM tb_teacher t LEFT JOIN tb_course c ON c.teacher_id=t.teacher_id"
				+ " LEFT JOIN tb_video v ON v.course_id=c.id LEFT JOIN tb_comment cm ON cm.commed_id = v.id  GROUP BY t.teacher_id ) com ON com.teacher_id=t.teacher_id";
		
		Paging<Map>	p= esht.createSQLQueryfindPage(sql, 0, 10);
		return Code.init(0, null, p);
	}
 
	@Override
	public Code commnet(String id,Integer  firstResult,Integer  maxResults,HttpSession session) {
		StringBuffer sb=new StringBuffer();
		sb.append(" SELECT");
		sb.append(" 	tb_comment.*,");
		sb.append("  tb_teacher.`name` AS title,");
		sb.append(" tb_user.username");
		sb.append(" FROM");
		sb.append(" tb_comment");
		sb.append(" LEFT JOIN tb_teacher ON tb_teacher.teacher_id = tb_comment.commed_id  ");
		sb.append(" LEFT JOIN tb_user ON tb_user.id=tb_comment.user_id");
		sb.append(" WHERE");
		sb.append("  tb_comment.type=4");
		sb.append(" ORDER BY");
		sb.append(" tb_comment.comm_time DESC");
		Paging<Map> p=esht.createSQLQueryfindPage(sb.toString(), (firstResult-1)*maxResults, maxResults );
		return Code.init(0, null, p);
	}


	/* (non-Javadoc)
	 * @see com.neiquan.meiyiquan.service.TeacherService#add(java.lang.Long, java.lang.String, java.lang.String, java.lang.String, javax.servlet.http.HttpSession)
	 */
	@Override
	public Code add(Long phone, String password, String username, String remark, HttpSession session) {
		// TODO Auto-generated method stub
		return null;
	}
 
}
