package com.neiquan.meiyiquan.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neiquan.meiyiquan.code.Code;
import com.neiquan.meiyiquan.dao.ObjectDao;
import com.neiquan.meiyiquan.dao.support.HomePageSupport;
import com.neiquan.meiyiquan.pojo.HomePage;
import com.neiquan.meiyiquan.service.HomePageService;
import com.neiquan.meiyiquan.util.Constant;
import com.neiquan.meiyiquan.util.TimeCleanUtil;
import com.qc.util.Condition.Cs;
import com.qc.util.DateFormate;

/**
 * 作者：齐潮
 * 创建日期：2017年3月2日
 * 类说明：
 */
@Service
public class HomePageServiceImpl implements HomePageService {

	@Autowired
	private ObjectDao od;
	
	@SuppressWarnings("unchecked")
	@Override
	public Code getCoupons(Cs cs, int page, int size) {
		String countSql = HomePageSupport.getCount(cs.getConditions());
		String selectSql = HomePageSupport.getSelect(cs.getConditions() + cs.getOrderSql());
		Code code = od.getObjects(countSql, selectSql, cs.getParams(), page, size);
		Map<String,Object> map = code.getMapData();
		Object list = map.get("result");
		TimeCleanUtil.clean(list,"creatTime","updateTime");
		return code;
	}

	@Override
	public Code save(HomePage hp,int isTop) {
		if(isTop==Constant.YES_INT){
			hp.setTop_num(getTopNext());
		}else{
			hp.setTop_num(0);
		}
		String id = od.save(hp);
		if(id==null)
			return Code.init(-1, "添加失败！请稍后再试！");
		return Code.init(0, "添加成功！");
	}

	@Override
	public int getTopNext() {
		String sql = "select ifnull(max(`top_num`),0) as `max` from `tb_homepage`";
		Map<String, Object> map = od.getObjectBySql(sql, null);
		int max = Integer.parseInt(map.get("max").toString());
		return (max + 1 );
	}

	@Override
	public Code getHomePage(String id) {
		String sql = "select `home`.*,"
						+ "if(`home`.`top_num` = (select ifnull(max(`top_num`),0) from `tb_homepage`) and `home`.`top_num` != 0,1,2) as `isTop` "
						+ "from `tb_homepage` as `home` "
						+ "where `home`.`id` = :id";
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("id", id);
		Map<String,Object> home = od.getObjectBySql(sql, params);
		if(home==null)
			return Code.init(-1, "该推荐信息不存在！");
		return Code.init(0, "", home);
	}

	@Override
	public Code update(String id, String name, String type, String relation_id, String isTop, String pic_url) {
		HomePage hp = od.getObjById(HomePage.class, id);
		if(hp==null)
			return Code.init(-1, "该推荐信息已经不存在！");
		if(Integer.parseInt(isTop)==Constant.YES_INT){
			hp.setTop_num(getTopNext());
		}else{
			hp.setTop_num(0);
		}
		hp.setName(name);
		hp.setPic_url(pic_url);
		hp.setRelation_id(relation_id);
		hp.setType(Integer.parseInt(type));
		hp.setUpdateTime(System.currentTimeMillis());
		od.update(hp);
		return Code.init(0, "修改成功！");
	}

	@Override
	public Code delete(String id) {
		int result = od.deleteById(HomePage.class, id);
		if(result!=1)
			return Code.init(-1, "删除失败！请稍后再试！");
		return Code.init(0, "删除成功！请刷新页面！");
	}

	@Override
	public Code updateStatus(String id, int status) {
		long now = System.currentTimeMillis();
		Map<String,Object> settings = new HashMap<String,Object>();
		settings.put("status", status);
		settings.put("updateTime", now);
		int result = od.updateById(HomePage.class, id, settings);
		if(result!=1)
			return Code.init(-1, "修改失败！请稍后再试！");
		return Code.init(0, "修改成功！请刷新页面！",DateFormate.getDateFormateCH(now));
	}

}
