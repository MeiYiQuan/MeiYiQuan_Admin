package com.neiquan.meiyiquan.util;

import java.util.List;
import java.util.Map;

import com.qc.util.DateFormate;

/**
 * 作者：齐潮
 * 创建日期：2016年12月13日
 * 类说明：将传进来的集合中的某些表示日期的字段转化为正常显示的日期格式
 */
public class TimeCleanUtil {

	/**
	 * 将传进来的集合中的某些表示日期的字段转化为正常显示的日期格式
	 * @param list
	 * @param keyNames
	 */
	public static void clean(List<Map<String,Object>> list,String... keyNames){
		if(list!=null&&list.size()>0){
			for(Map<String,Object> map:list){
				for(String key:keyNames){
					long time = map.get(key)==null?0:Long.parseLong(map.get(key).toString());
					String timeStr = null;
					if(time==0){
						timeStr = "无";
					}else{
						timeStr = DateFormate.getDateFormateCH(time);
					}
					map.put(key, timeStr);
				}
			}
		}
	}
	
	/**
	 * 将传进来的集合中的某些表示日期的字段转化为正常显示的日期格式。自带类型强转
	 * @param objList
	 * @param keyNames
	 */
	@SuppressWarnings("unchecked")
	public static void clean(Object objList,String... keyNames){
		List<Map<String,Object>> list = (List<Map<String, Object>>) objList;
		clean(list, keyNames);
	}
	
	/**
	 * 将传进来的集合中的某些表示日期的字段转化为正常显示的日期格式。自带类型强转
	 * @param objList
	 * @param keyNames
	 */
	@SuppressWarnings("unchecked")
	public static void clean2(Object objList,String... keyNames){
		List<Map<String,Object>> list = (List<Map<String, Object>>) objList;
		clean2(list, keyNames);
	}
	
	public static void clean2(List<Map<String,Object>> list,String... keyNames){
		if(list!=null&&list.size()>0){
			for(Map<String,Object> map:list){
				for(String key:keyNames){
					long time = map.get(key)==null?0:Long.parseLong(map.get(key).toString());
					String timeStr = null;
					if(time==0){
						timeStr = "";
					}else{
						timeStr = DateFormate.getDateFormate(time);
					}
					map.put(key, timeStr);
				}
			}
		}
	}
	
	/**
	 * 将xx时xx分xx秒 转为秒
	 * @param time
	 * @return
	 */
	 public static long timeString(String time){
		 long s=0;
		 /*if(!time.contains("时")){
			 if(!time.contains("分")){
				 s+=Integer.parseInt(time.substring(0,time.indexOf("秒")));    //秒 
			 }else{
				 s+=Integer.parseInt(time.substring(0,time.indexOf("分")))*60;    //分钟
				 s+=Integer.parseInt(time.substring(time.indexOf("分")+1,time.indexOf("秒")));    //秒
			 }
			 if(!time.contains("秒")){
				 s+=Integer.parseInt(time.substring(0,time.indexOf("分")))*60;    //分钟
			 }else{
				 s+=Integer.parseInt(time.substring(0,time.indexOf("分")))*60;    //分钟
				 s+=Integer.parseInt(time.substring(time.indexOf("分")+1,time.indexOf("秒")));    //秒
			 }
		 }
		 
		 
		 if(!time.contains("分")){
			 if(!time.contains("秒")){
				 s=Integer.parseInt(time.substring(0,time.indexOf("时")))*3600;    //小时  
			 }
			 if(!time.contains("时")){
				 s+=Integer.parseInt(time.substring(0,time.indexOf("秒")+1));    //秒
			 }
		 } 
			 else{
				 s=Integer.parseInt(time.substring(0,time.indexOf("时")))*3600;    //小时 
				 s+=Integer.parseInt(time.substring(time.indexOf("时")+1,time.indexOf("秒")));    //秒
			 }
			 
		
		 if(!time.contains("秒")){
			 if(!time.contains("分")){
				 s=Integer.parseInt(time.substring(0,time.indexOf("时")))*3600;    //小时
			 }
			 if(!time.contains("时")){
				 s=Integer.parseInt(time.substring(0,time.indexOf("分")))*60;    //小时
			 }
		 }
		 else{
				 s=Integer.parseInt(time.substring(0,time.indexOf("时")))*3600;    //小时
				 s+=Integer.parseInt(time.substring(time.indexOf("时")+1,time.indexOf("分")))*60;    //分钟
			 }*/
			
		 
		 if(time.contains("秒")&&time.contains("时")&&time.contains("分")){
			 s=Integer.parseInt(time.substring(0,time.indexOf("时")))*3600;    //小时
			 s+=Integer.parseInt(time.substring(time.indexOf("分")+1,time.indexOf("秒")));    //秒
			 s+=Integer.parseInt(time.substring(time.indexOf("时")+1,time.indexOf("分")))*60;    //分钟
		 }
		 if(!time.contains("秒")&&time.contains("时")&&time.contains("分")){
			 s=Integer.parseInt(time.substring(0,time.indexOf("时")))*3600;    //小时
			 s+=Integer.parseInt(time.substring(time.indexOf("时")+1,time.indexOf("分")))*60;    //分钟
		 }
		 if(time.contains("秒")&&!time.contains("时")&&time.contains("分")){
			 s+=Integer.parseInt(time.substring(time.indexOf("分")+1,time.indexOf("秒")));    //秒
			 s+=Integer.parseInt(time.substring(0,time.indexOf("分")))*60;    //分钟
		 }
		 if(time.contains("秒")&&time.contains("时")&&!time.contains("分")){
			 s=Integer.parseInt(time.substring(0,time.indexOf("时")))*3600;    //小时
			 s+=Integer.parseInt(time.substring(time.indexOf("时")+1,time.indexOf("秒")));    //秒
		 }
		 if(time.contains("秒")&&!time.contains("时")&&!time.contains("分")){
			 s+=Integer.parseInt(time.substring(0,time.indexOf("秒")));    //秒
		 }
		 if(!time.contains("秒")&&!time.contains("时")&&time.contains("分")){
			 s+=Integer.parseInt(time.substring(0,time.indexOf("分")))*60;    //分钟
		 }
		 if(!time.contains("秒")&&time.contains("时")&&!time.contains("分")){
			 s+=Integer.parseInt(time.substring(0,time.indexOf("时")))*3600;    //分钟
		 }
		 return s;
	 }
}
