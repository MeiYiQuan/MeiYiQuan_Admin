package com.neiquan.meiyiquan.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

/**
 * 作者：齐潮
 * 创建日期：2016年12月8日
 * 类说明：后台异常以后跳转的地址
 */
public class ExceptionUrl extends SimpleMappingExceptionResolver {

	@Override
	protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object arg2,
			Exception ex) {
		ex.printStackTrace();
		ModelAndView mav = new ModelAndView();
		mav.setViewName("500");
		return mav;
	}
}
