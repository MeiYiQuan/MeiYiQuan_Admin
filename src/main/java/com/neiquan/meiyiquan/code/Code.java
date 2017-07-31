package com.neiquan.meiyiquan.code;

import java.util.List;
import java.util.Map;

/**
 * 作者：齐潮
 * 创建日期：2016年12月8日
 * 类说明：用于传递信息
 */
public class Code {

	private int code;
	
	private String message;
	
	private Object data;
	
	private Code(){}
	
	public static Code init(int code,String message){
		Code sc = new Code();
		sc.code = code;
		sc.message = message;
		return sc;
	}
	
	public static Code init(int code,String message,Object data){
		Code sc = new Code();
		sc.code = code;
		sc.message = message;
		sc.data = data;
		return sc;
	}
	
	
	public int getCode() {
		// TODO Auto-generated method stub
		return code;
	}

	public String getMessage() {
		// TODO Auto-generated method stub
		return message;
	}

	@SuppressWarnings("rawtypes")
	public List getListData() {
		// TODO Auto-generated method stub
		return (List) data;
	}

	@SuppressWarnings("rawtypes")
	public Map getMapData() {
		// TODO Auto-generated method stub
		return (Map) data;
	}

	public Object getData() {
		// TODO Auto-generated method stub
		return data;
	}

}
