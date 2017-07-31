package com.neiquan.meiyiquan.service.impl;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.neiquan.meiyiquan.code.Code;
import com.neiquan.meiyiquan.dao.ObjectDao;
import com.neiquan.meiyiquan.dao.support.AskCourseDaoSupport;
import com.neiquan.meiyiquan.dao.support.BannerDaoSupport;
import com.neiquan.meiyiquan.dao.support.HomepageDaoSupport;
import com.neiquan.meiyiquan.pojo.Banner;
import com.neiquan.meiyiquan.pojo.PubicParameter;
import com.neiquan.meiyiquan.pojo.Sys;
import com.neiquan.meiyiquan.service.BannerService;
import com.neiquan.meiyiquan.service.ObjectService;
import com.neiquan.meiyiquan.util.Constant;
import com.neiquan.meiyiquan.util.ExtraSpringHibernateTemplate;
import com.neiquan.meiyiquan.util.OSSClientUtil;
import com.neiquan.meiyiquan.util.Paging;
import com.neiquan.meiyiquan.util.TimeCleanUtil;
import com.qc.util.Condition.Cs;


/**
 * 作者：齐潮
 * 创建日期：2016年12月12日
 * 类说明：
 */
@Service
public class BannerServiceImpl implements BannerService {
	
	@Autowired
	private ObjectDao od;
	@Autowired
	private OSSClientUtil ossClient;
	@Autowired
	private ExtraSpringHibernateTemplate esht;
	/*@Autowired
	private BannerDaoSupport bds;*/
	
	@Override
	public Code getBanners(Cs cs, int page, int size){
		String countSql = BannerDaoSupport.getCountSql(cs.getConditions());
		String selectSql = BannerDaoSupport.getResultSql(cs.getConditions()) + cs.getOrderSql();
		Code result = od.getObjects(countSql, selectSql, cs.getParams(), page, size);
		Object list = result.getMapData().get("result");
		TimeCleanUtil.clean(list,"create_time","update_time");
		return result;
	}
	/**
	 * 获取内容详情
	 */
	@Override
	public Code infoBanners(String id) {
		String sql = "select `banner`.*,"
						+ "if(`order_num` = (select ifnull(max(`order_num`),0) from `tb_banner`) and `order_num` != 0," + Constant.YES_INT + "," + Constant.NO_INT + ") as `isTop` "
						+ "from `tb_banner` as `banner` "
						+ "where `banner`.`id` = :id";
		Map<String, Object> params = new HashMap<String,Object>();
		params.put("id", id);
		Map<String, Object> banner = od.getObjectBySql(sql, params);
		if(banner==null)
			return Code.init(564, "该轮播图已经不存在！");
		return Code.init(1, null,banner);
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
	 * 添加
	 */
	@Override
	public Code addbanner(String name, Integer order_num, String pic_save_url, String remark,
			String jump_type, String jump_id,String type) {
		Banner ba= new Banner();
		ba.setJump_id(jump_id);
		int jump_typee=Integer.valueOf(jump_type).intValue();
		int typeee=Integer.valueOf(type).intValue();
		ba.setJump_type(jump_typee);
		ba.setName(name);
		ba.setPic_save_url(pic_save_url);
		ba.setOrder_num(order_num);
		ba.setRemark(remark);
		ba.setShowtype(typeee);
		ba.setCreate_time(System.currentTimeMillis());
		ba.setUpdate_time(System.currentTimeMillis());
		ba.setStatus(Constant.NO_INT);
		
		esht.getHibernateTemplate().save(ba);
		return null;
	}
	/**
	 * 获取当前总数量
	 */
	@SuppressWarnings("unused")
	@Override
	public int order_num(String order_num) {
		int  sum=0;
//		String sql=HomepageDaoSupport.getTopnum();
		String sql = "select ifnull(max(`order_num`),0) as `maxnum` from `tb_banner`";
		if(order_num.equals("1")){
			Map<String,Object> result = od.getObjectBySql(sql, null);
			String resultt= result.get("maxnum").toString();
			sum=Integer.valueOf(resultt).intValue();
			sum=sum+1;
		}
		if(order_num.equals("-1")){
			sum=0;
		}
		return sum;
	}
	
	@Override
	public Code getFBanners(Cs cs, int page, int size) {
		String countSql = BannerDaoSupport.getFCountSql(cs.getConditions());
		String selectSql = BannerDaoSupport.getFResultSql(cs.getConditions()) + cs.getOrderSql();
		Code result = od.getObjects(countSql, selectSql, cs.getParams(), page, size);
		Object list = result.getMapData().get("result");
		TimeCleanUtil.clean(list,"create_time","update_time");
		return result;
	}
	/**
	 * 公共参数列表
	 */
	@Override
	public Code public_paratemer() {
		String sql="SELECT * FROM tb_system";
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
	 * 修改轮播图
	 */
	@Override
	public Code updateBanner(String id, String name, Integer order_num, String pic_save_url, String remark,
			String jump_type, String jump_id,String showtype) {
		Banner ba= esht.findFirstOneById(Banner.class, id);
		Date now = new Date(); 
		long lSysTime1 = now.getTime();
		ba.setJump_id(jump_id);
		int jump_typee=Integer.valueOf(jump_type).intValue();
		int showtypee=Integer.valueOf(showtype).intValue();
		ba.setJump_type(jump_typee);
		ba.setName(name);
		ba.setPic_save_url(pic_save_url);
		ba.setOrder_num(order_num);
		ba.setRemark(remark);
		ba.setShowtype(showtypee);
		ba.setUpdate_time(lSysTime1);
		
		
		
		esht.getHibernateTemplate().update(ba);
		return null;
	}
	
}
