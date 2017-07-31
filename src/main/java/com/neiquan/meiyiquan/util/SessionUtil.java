package com.neiquan.meiyiquan.util;

import java.util.Map;

import javax.servlet.http.HttpSession;

public class SessionUtil {
	
	public static String getUserId(HttpSession session) {
	/*	Map<String,Object> user =(Map<String, Object>)session.getAttribute("user");
		String update_admin_id=	user.get("id").toString();*/
		return "id1";
	}
}
