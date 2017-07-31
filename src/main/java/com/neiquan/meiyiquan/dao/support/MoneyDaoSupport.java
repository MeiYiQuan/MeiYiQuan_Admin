package com.neiquan.meiyiquan.dao.support;

import com.neiquan.meiyiquan.util.DateUtil;
import com.neiquan.meiyiquan.util.StringUtil;

/**
 * 作者：温尉棨
 * 创建日期：2017年2月16日
 * 类说明：
 */
public class MoneyDaoSupport {

	
	public final static String moneyInfo(String beginTime, String endTime, String tree, String orderNum, String zongshu,String pay_type){
		
		String adress="";
		if(!StringUtil.isNullOrBlank(tree)){
			adress="  AND u.district '%"+tree+"%' ";
		}
		String type="";
		if(!StringUtil.isNullOrBlank(zongshu)){
			if(!StringUtil.isNullOrBlank(pay_type)){
				type="AND o.pay_type="+pay_type+"";
			}
		}
		String order="";
		if(!StringUtil.isNullOrBlank(orderNum)){
			adress=" AND o.order_num ='"+orderNum+"' ";
		}
		
		String creat="";
		if(!StringUtil.isNullOrBlank(beginTime)){
			long i=DateUtil.dateStrToMillis(beginTime,"yyyy-MM-dd");
			creat=" and o.pay_time>"+i+" ";
		}
		String end="";
		if(!StringUtil.isNullOrBlank(endTime)){
			long i=DateUtil.dateStrToMillis(endTime,"yyyy-MM-dd");
			end=" and o.pay_time<"+i+"";
		}
		
		String sql="SELECT  IFNULL(COUNT(o.id),0) con,IFNULL(SUM(o.price),0) su FROM tb_order o"
					+ " LEFT JOIN tb_user u ON o.user_id=u.id"
					+ " WHERE  o.`status`=1"
					+ " "+creat+""
					+ " "+end+""
					+ " "+order+""
					+ " "+adress+" "
					+ " "+type+"";
		return sql;
	}
	/**
	 * 订单列表
	 * @param text
	 * @return
	 */
	public final static String orderlist(String text){
		String and="";
		if(!StringUtil.isNullOrBlank(text)){
			and=" and o.order_num= '"+text+"'";
		}
		String sql="SELECT  "
					+ " o.*"
					+ " ,u.username"
					+ " ,(o.price-o.achieve)/o.price shuilv"
					+ " FROM tb_order o"
					+ " LEFT JOIN tb_user u ON o.user_id=u.id"	
					+ " WHERE 1=1 "+and+""
					+ " and o.status=1";
		return sql;
	}
	
	/**
	 * 订单收益表
	 * @param beginTime
	 * @param endTime
	 * @param type
	 * @param dateType
	 * @return
	 */
	public final static String gainsList(String beginTime,String endTime,String dateType){
		
		
		String creat="";
		String end="";
		String day=" DATE_FORMAT( o.pay_time, '%Y-%m-%d')   ";
		if(dateType.equals("1")){
			if(!StringUtil.isNullOrBlank(beginTime)){
				long i=DateUtil.dateStrToMillis(beginTime,"yyyy-MM-dd");
				long j=i/1000;
				creat=" and "+day+">DATE_FORMAT(from_unixtime("+j+"), '%Y-%m-%d') ";
			}
			if(!StringUtil.isNullOrBlank(endTime)){
				long i=DateUtil.dateStrToMillis(endTime,"yyyy-MM-dd");
				long j=i/1000;
				end=" and "+day+"<DATE_FORMAT(from_unixtime("+j+"), '%Y-%m-%d')";
			}
		}
		if(dateType.equals("2")){
			day=" DATE_FORMAT( o.pay_time, '%Y-%m')  ";
			if(!StringUtil.isNullOrBlank(beginTime)){
				long i=DateUtil.dateStrToMillis(beginTime,"yyyy-MM");
				long j=i/1000;
				creat=" and "+day+">DATE_FORMAT(from_unixtime("+j+"), '%Y-%m') ";
			}
			if(!StringUtil.isNullOrBlank(endTime)){
				long i=DateUtil.dateStrToMillis(endTime,"yyyy-MM");
				long j=i/1000;
				end=" and "+day+"<DATE_FORMAT(from_unixtime("+j+"), '%Y-%m')";
			}
		}
		
		String sql="select "
				+ " "+day+" as  t"
				+ " ,COUNT(o.id) con"
				+ " ,SUM(o.price) su"
				+ " FROM tb_order o "
				+ " where 1=1"
				+ " "+creat+" "
				+ " "+end+" "
				+ " AND o.status=1"
				+ " GROUP BY t"
				+ "  ORDER BY t DESC";
		return sql;
	}
	/**
	 * ios收益列表
	 * @param beginTime
	 * @param endTime
	 * @param dateType
	 * @return
	 */
	public final static String gainsios(String beginTime,String endTime,String dateType){
		
		
		String creat="";
		String end="";
		String day=" DATE_FORMAT( u.buyTime, '%Y-%m-%d')   ";
		if(dateType.equals("1")){
			if(!StringUtil.isNullOrBlank(beginTime)){
				long i=DateUtil.dateStrToMillis(beginTime,"yyyy-MM-dd");
				long j=i/1000;
				creat=" and "+day+">DATE_FORMAT(from_unixtime("+j+"), '%Y-%m-%d')  ";
			}
			if(!StringUtil.isNullOrBlank(endTime)){
				long i=DateUtil.dateStrToMillis(endTime,"yyyy-MM-dd");
				long j=i/1000;
				end=" and "+day+"<DATE_FORMAT(from_unixtime("+j+"), '%Y-%m-%d') ";
			}
		}
		if(dateType.equals("2")){
			day=" DATE_FORMAT( u.buyTime, '%Y-%m') ";
			if(!StringUtil.isNullOrBlank(beginTime)){
				long i=DateUtil.dateStrToMillis(beginTime,"yyyy-MM");
				long j=i/1000;
				creat=" and "+day+">DATE_FORMAT(from_unixtime("+j+"), '%Y-%m')  ";
			}
			if(!StringUtil.isNullOrBlank(endTime)){
				long i=DateUtil.dateStrToMillis(endTime,"yyyy-MM");
				long j=i/1000;
				end=" and "+day+"<DATE_FORMAT(from_unixtime("+j+"), '%Y-%m') ";
			}
		}
		
		String sql="select "
				+ " "+day+" as t"
				+ " ,COUNT(u.id) con"
				+ " ,SUM(u.coin) su"
				+ " FROM tb_user_ioscoin u "
				+ " where 1=1"
				+ " "+creat+" "
				+ " "+end+" "
				+ " AND u.state=1"
				+ " GROUP BY t"
				+ "  ORDER BY t DESC";
		return sql;
	}
	
	/**
	 * 优惠券申请列表
	 * @param name
	 * @param status
	 * @return
	 */
	public  final static String shengqing(String name,String status){
		String sta="";
		if(!StringUtil.isNullOrBlank(status)){
			sta=" and wu.use_type= '"+status+"'";
		}
		String and="";
		if(!StringUtil.isNullOrBlank(name)){
			and=" and 	c.`name`= '"+name+"'";
		}
		String sql="select "
					+ " c.*,u.username,wu.req_time,wu.use_type,wu.id wid"
					+ " FROM tb_coupon_willsend_user wu "
					+ " LEFT JOIN tb_user u ON wu.user_id=u.id"
					+ " LEFT JOIN tb_coupon c ON wu.coupon_id=c.id"
					+ " WHERE 1=1 "
					+ " "+and+" "
					+ " "+sta+" ";
		return sql;
	}
}
