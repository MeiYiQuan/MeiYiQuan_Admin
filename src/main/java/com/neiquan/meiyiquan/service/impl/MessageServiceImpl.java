package com.neiquan.meiyiquan.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neiquan.meiyiquan.code.Code;
import com.neiquan.meiyiquan.dao.ObjectDao;
import com.neiquan.meiyiquan.dao.support.AskCourseDaoSupport;
import com.neiquan.meiyiquan.pojo.Admin;
import com.neiquan.meiyiquan.pojo.Sys;
import com.neiquan.meiyiquan.service.MessageService;
import com.neiquan.meiyiquan.util.ExtraSpringHibernateTemplate;
import com.neiquan.meiyiquan.util.Paging;
import com.neiquan.meiyiquan.util.TimeCleanUtil;

/**
 * 作者：温尉棨
 * 创建日期：2017年2月14日
 * 类说明：
 */
@Service
public class MessageServiceImpl implements MessageService{

	@Autowired
	private ExtraSpringHibernateTemplate esht;
	@Autowired
	private ObjectDao od;
	/**
	 * 公共参数列表
	 */
	@Override
	public Code public_paratemer() {
		String sql="SELECT s.* FROM tb_system s where  s.type<>3";
		Paging<Map>	p= esht.createSQLQueryfindPage(sql, 0, 99);
		Object list= p.getList();
		return Code.init(0, null, p);
	}
	/**
	 * 平台参数详情
	 */
	@Override
	public Code publicInfo(String id) {
		Map<String, Object> ppt = od.getObjByIdForMap(Sys.class, id);
		if(ppt==null)
			return Code.init(564, "该平台参数无详情");
		return Code.init(1, null,ppt);
	}
	/**
	 * 修改平台参数值
	 */
	@Override
	public long publicUpdate(String id, String parameter_name, String pic_url) {
		Sys ppt= esht.findFirstOneById(Sys.class, id);
		ppt.setSys_value(pic_url);
		ppt.setTitle(parameter_name);
		esht.getHibernateTemplate().update(ppt);
		return 0;
	}
	/**
	 * 可修改平台参数列表
	 */
	@Override
	public Code job_list(String type) {
		String sql="SELECT * FROM tb_job j where j.type="+type+" ";
		Paging<Map>	p= esht.createSQLQueryfindPage(sql, 0, 99);
		Object list= p.getList();
		return Code.init(0, null, p);
	}
	/**
	 * 管理员列表
	 */
	@Override
	public Code adminList(String page, String size,String name) {
		String and="";
		if(!name.equals("")){
			and=" and a.loginname='"+name+"'";
		}
		String sql="select a.*,r.roleName from tb_admin a  "
				+ " LEFT JOIN tb_role r ON a.roleId=r.id"
				+ " where 1=1 "+and+" and a.roleid<>'' ";
		int pageindex = Integer.valueOf(page).intValue();
		int pagesize = Integer.valueOf(size).intValue();
		Paging<Map>	p= esht.createSQLQueryfindPage(sql, (pageindex-1)*pagesize, pagesize);
		return Code.init(0, null, p);
	}
	/**
	 * 获取管理员详情
	 */
	@Override
	public Code adminInfo(String id) {
		String sql="select a.*,r.roleName,r.id roid from tb_admin a "
				+ " LEFT JOIN tb_role r ON a.roleId=r.id"
				+ " where a.id='"+id+"' ";
		Paging<Map>	p= esht.createSQLQueryfindPage(sql, 0, 10);
		return Code.init(0, null, p);
	}
	/**
	 * 添加管理员
	 */
	@Override
	public String addAdmin(String name, String password, String roleid) {
		Admin ad=new Admin();
		ad.setLoginname(name);	
		ad.setPassword(password);
		ad.setRoleId(roleid);
		ad.setUpdateTime(System.currentTimeMillis());
		ad.setHeadUrl("");
		esht.getHibernateTemplate().save(ad);
		return null;
	}
	/**
	 * 修改管理员
	 */
	@Override
	public String updateAdmin(String id, String name, String password, String roleid) {
		Admin ad= esht.findFirstOneById(Admin.class, id);
		ad.setLoginname(name);	
		ad.setPassword(password);
		ad.setRoleId(roleid);
		ad.setUpdateTime(System.currentTimeMillis());
		ad.setHeadUrl("");
		esht.getHibernateTemplate().update(ad);
		return null;
	}
	/**
	 * 权限列表
	 */
	@Override
	public Code role() {
		String sql="select * from tb_role ";
		Paging<Map>	p= esht.createSQLQueryfindPage(sql, 0, 10);
		return Code.init(0, null, p);
	}
	/**
	 * 讲师审核列表
	 */
	@Override
	public Code tmList(String page, String size, String name) {
		String and="";
		if(!name.equals("")){
			and=" and t.name='"+name+"'";
		}
		String sql="SELECT   ts.*,t.id tid,t.name,t.make_type,t.make_account, "
				+ "a.loginname AS apply_admin,"
				+ "a1.loginname AS verify_admin"
				+ " from tb_teacher_send ts"
				+ "  LEFT JOIN tb_teacher t ON ts.teacher_id=t.id"
				+ " LEFT JOIN tb_admin a ON ts.apply_admin_id = a.id"
				+ " LEFT JOIN tb_admin a1 ON ts.verify_admin_id = a1.id"
				+ " where  1=1  "+and+" ";
		int pageindex = Integer.valueOf(page).intValue();
		int pagesize = Integer.valueOf(size).intValue();
		Paging<Map>	p= esht.createSQLQueryfindPage(sql, (pageindex-1)*pagesize, pagesize);
		
		 TimeCleanUtil.clean( p.getList(),"apply_time","verify_time");

		return Code.init(0, null, p);
	}

}
