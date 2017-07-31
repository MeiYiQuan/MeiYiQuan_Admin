package com.neiquan.meiyiquan.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

public class PrimaryKeyUtil {
	public static String getUUID(String id) {
		return id + UUID.randomUUID().toString().replaceAll("-", "");
	}
	
	/**
	 * 消费吗串
	 * @param id
	 * @return
	 */
	public static String getPayCodeRandStr(String id){
		StringBuffer buf = new StringBuffer();
		Random random = new Random();
		buf.append(id);
		for(int i = 0; i < 4; i++){
            buf.append(random.nextInt(10));//取四个随机数追加到StringBuffer
	    }
		for(int i = 0; i < 4; i++){
            buf.append(random.nextInt(10));//取四个随机数追加到StringBuffer
	    }
		for(int i = 0; i < 4; i++){
            buf.append(random.nextInt(10));//取四个随机数追加到StringBuffer
	    }
	    return buf.toString();
	}
	
	/**
	 * 时间戳Ms+8位随机串
	 * @param id
	 * @return
	 */
	public static String getTimeRandStr8(String id){
		StringBuffer buf = new StringBuffer();
		Random random = new Random();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		String time = sdf.format(new Date());
		buf.append(id);
		buf.append(time);
		for(int i = 0; i < 4; i++){
            buf.append(random.nextInt(10));//取四个随机数追加到StringBuffer
	    }
		for(int i = 0; i < 4; i++){
            buf.append(random.nextInt(10));//取四个随机数追加到StringBuffer
	    }
	    return buf.toString();
	}
	
	/**
	 * 时间戳Ms+8位随机串
	 * @param id
	 * @return
	 */
	public static String getTimeRandStr10(String id){
		StringBuffer buf = new StringBuffer();
		Random random = new Random();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		String time = sdf.format(new Date());
		buf.append(id);
		buf.append(time);
		for(int i = 0; i < 5; i++){
            buf.append(random.nextInt(10));//取四个随机数追加到StringBuffer
	    }
		for(int i = 0; i < 5; i++){
            buf.append(random.nextInt(10));//取四个随机数追加到StringBuffer
	    }
	    return buf.toString();
	}
	
	/**
	 * 时间戳Ms+4位随机串
	 * @param id
	 * @return
	 */
	public static String getTimeRandStr4(String id){
		StringBuffer buf = new StringBuffer();
		Random random = new Random();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		String time = sdf.format(new Date());
		buf.append(id);
		buf.append(time);
		for(int i = 0; i < 4; i++){
            buf.append(random.nextInt(10));//取四个随机数追加到StringBuffer
	    }
	    return buf.toString();
	}
	/**
	 * 时间戳Ms+4位随机串
	 * @param id
	 * @return
	 */
	public static String getTimeRandStr(String id){
		StringBuffer buf = new StringBuffer();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String time = sdf.format(new Date());
		buf.append(id);
		buf.append(time);
	    return buf.toString();
	}
	/**
	 * 4位随机串
	 * @param id
	 * @return
	 */
	public static String getRandStr4(){
		StringBuffer buf = new StringBuffer();
		Random random = new Random();
		for(int i = 0; i < 4; i++){
            buf.append(random.nextInt(10));//取四个随机数追加到StringBuffer
	    }
	    return buf.toString();
	}
	/**
	 * 获取当前时间戳MS
	 * @param id
	 * @return
	 */
	public static String getCurrTimeMsStr(String id){
		StringBuffer buf = new StringBuffer();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		String time = sdf.format(new Date());
		buf.append(id);
		buf.append(time);
	    return buf.toString();
	}
	public static void main(String[] args) {
		System.out.println(getTimeRandStr8(""));
		System.out.println(getTimeRandStr8(""));
		for (int i = 0; i < 100; i++) {
			System.out.println(getTimeRandStr8(""));
		}
		
	}
}
