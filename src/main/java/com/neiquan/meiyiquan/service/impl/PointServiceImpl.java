package com.neiquan.meiyiquan.service.impl;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neiquan.meiyiquan.code.Code;
import com.neiquan.meiyiquan.pojo.Course;
import com.neiquan.meiyiquan.pojo.Point;
import com.neiquan.meiyiquan.pojo.PointMost;
import com.neiquan.meiyiquan.service.CourseService;
import com.neiquan.meiyiquan.service.PointService;
import com.neiquan.meiyiquan.util.DateUtil;
import com.neiquan.meiyiquan.util.ExtraSpringHibernateTemplate;
import com.neiquan.meiyiquan.util.FieldStatics;
import com.neiquan.meiyiquan.util.Paging;
import com.neiquan.meiyiquan.util.Statics;
import com.neiquan.meiyiquan.util.StringUtil;


/**
 * 作者：刘丹
 * 创建日期：2017年1月7日
 * 类说明：区域管理模块
 */
@Service
public class PointServiceImpl implements PointService {
	
	@Autowired
	private ExtraSpringHibernateTemplate esht;

	@Override
	public Code getPointList( ) {
		StringBuffer sql=new StringBuffer();
		sql.append(" SELECT");
		sql.append(" tb_point_way_most.id,");
		sql.append(" tb_point_way_most.remark,");
		sql.append(" tb_point_way_most.way_most,");
		sql.append(" tb_point_way_most.way_name,");
		sql.append(" tb_point_way_most.way_single,");
		sql.append(" tb_point_way_most.way_type");
		sql.append(" FROM tb_point_way_most ");
		List<Map>	p= esht.createSQLQueryFindAll(sql.toString());
		return Code.init(0, null, p);
	}

	@Override
	public Code upDate(String id, String str, HttpSession session) {
		PointMost point	=esht.findFirstOneByPropEq(PointMost.class, "id", id);
		if(StringUtil.isNullOrBlank(id)||StringUtil.isNullOrBlank(str)){
			return Code.init(1, "参数错误", null);
		}
		if(point==null){
			return Code.init(2, "无此数据", null);
		}
		point.setWay_single(Integer.valueOf(str));
		esht.getHibernateTemplate().update(point);
		return Code.init(0, "修改成功", null);
	}
}
