package com.neiquan.meiyiquan.util;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NetworkUtil {
	
	private final static Logger logger = LoggerFactory.getLogger(NetworkUtil.class);
	
	/**
	 * 通过传进来的request来获取请求者的ip
	 * @param request
	 * @return
	 */
	public static String getIp(HttpServletRequest request) {
		String ip = null;
		try {
			ip = request.getHeader("x-forwarded-for");
			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
				ip = request.getHeader("Proxy-Client-IP");
			}
			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
				ip = request.getHeader("WL-Proxy-Client-IP");
			}
			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
				ip = request.getRemoteAddr();
			}
		} catch (Exception e) {
			PrintUtil.printException(logger, e);
		}
		return ip;
	}

}
