package com.neiquan.meiyiquan;

import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.hibernate4.HibernateTransactionManager;

import com.neiquan.meiyiquan.util.OSSClientUtil;

/**
 * 作者：齐潮
 * 创建日期：2017年2月18日
 * 类说明：
 */
public class Testqc {

	public static void main(String[] args) throws Exception {
		List<String> list = new ArrayList<String>();
		list.add("1");
		list.add("2");
		list.add("3");
		System.out.println(list);
		HibernateTransactionManager mm = null;
	}
	
}
