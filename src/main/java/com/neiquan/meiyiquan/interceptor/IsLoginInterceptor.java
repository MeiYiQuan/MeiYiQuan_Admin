package com.neiquan.meiyiquan.interceptor;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.neiquan.meiyiquan.util.RedisUtil;
import com.neiquan.meiyiquan.util.Statics;



/**
 * 作者：齐潮
 * 创建日期：2016年10月27日
 * 类说明：登陆拦截器
 */
public class IsLoginInterceptor implements HandlerInterceptor {

	@Autowired
	private RedisUtil re ;
	
	/**
	 * 登陆拦截器
	 */
	@SuppressWarnings("unchecked")
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		String uri=request.getRequestURI();
		if(uri.indexOf("/shareurl/shareurl.do")!=-1){
			return true;
		}
		HttpSession session = request.getSession();
		if(session==null){
			request.getRequestDispatcher("/app/login.jsp").forward(request, response);
			return false;
		}
		if(session.getAttribute("user")==null){
			
			request.setAttribute("message", "非法操作！");
			request.getRequestDispatcher("/app/login.jsp").forward(request, response);
			return false;
		}
		Object tokenOBJ = session.getAttribute("token");
		if(tokenOBJ==null){
			request.setAttribute("message", "非法操作！");
			request.getRequestDispatcher("/app/login.jsp").forward(request, response);
			return true;
		}
		Map<String,Object> user = (Map<String, Object>) session.getAttribute("user");
		String userId = user.get("id").toString();
		String oldToken = re.getHashValue(Statics.CMS_REDIS_TOKEN_NAME, userId);
		if(!tokenOBJ.toString().equals(oldToken)){
			request.setAttribute("message", "此账户已经在别处登陆！");
			request.getRequestDispatcher("/app/login.jsp").forward(request, response);
			return false;
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

}
