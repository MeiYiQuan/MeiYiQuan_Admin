package com.neiquan.meiyiquan.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neiquan.meiyiquan.code.Code;
import com.neiquan.meiyiquan.pojo.Order;
import com.neiquan.meiyiquan.pojo.Video;
import com.neiquan.meiyiquan.service.OrderService;
import com.neiquan.meiyiquan.util.ExtraSpringHibernateTemplate;
import com.neiquan.meiyiquan.util.Paging;
import com.neiquan.meiyiquan.util.TimeCleanUtil;


/**
 * 作者：刘丹
 * 创建日期：2017年2月9日
 * 类说明：订单管理模块
 */
@Service
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	private ExtraSpringHibernateTemplate esht;

	
	@Override
	public Code getList(String keywords, String status, Integer pageIndex,
			Integer pageSize, String timeStar, String timeEnd, String orderBy,
			String collation) {
		
		return null;
	}
	@Override
	public Code getOrder(String id) {
		Order order=esht.findFirstOneByPropEq(Order.class, "order_num", id);
		return Code.init(0, "", order);
	}
	@Override
	public Code getListByVideoId(String id,Integer pageIndex,
			Integer pageSize) {
		List<Video> list=esht.findAllByPropEq(Video.class, "course_id", id);
		String sql="''";
		for(int i=0;i<list.size();i++){
			sql+=",'"+list.get(i).getId().toString()+"'";
		}
		StringBuffer sb=new StringBuffer();
		sb.append(" SELECT");
		sb.append(" *");
		sb.append(" FROM");
		sb.append(" tb_order");
		sb.append(" WHERE 1=1 "); 
		sb.append(" and tb_order.video_id in (");
		sb.append(sql);
		sb.append(") ORDER BY tb_order.create_time DESC");
		List  order=	esht.createSQLQueryFindAll(sb.toString());
		Paging<Map>	p= esht.createSQLQueryfindPage(sb.toString(), (pageIndex-1)*pageSize, pageSize);
		TimeCleanUtil.clean(p.getList(),"create_time");
		return Code.init(0, null, p);
	}
 
}
