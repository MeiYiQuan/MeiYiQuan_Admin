package com.neiquan.meiyiquan.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.neiquan.meiyiquan.code.Code;
import com.neiquan.meiyiquan.pojo.Menu;
import com.neiquan.meiyiquan.service.LoginService;
import com.neiquan.meiyiquan.util.GenerateUtil;
import com.neiquan.meiyiquan.util.ImageCodeUtil;
import com.neiquan.meiyiquan.util.RedisUtil;
import com.neiquan.meiyiquan.util.Statics;



/**
 * 作者：齐潮
 * 创建日期：2016年12月8日
 * 类说明：用于处理登录的请求
 */
@Controller
@RequestMapping(value="/login")
public class LoginController {

	@Autowired
	private LoginService ls;
	
	@Autowired
	private RedisUtil redis;
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/login")
	public ModelAndView login(String loginName,String password,String code,HttpSession session){
		// 处理页面刷新
		ModelAndView mav = new ModelAndView();
		Object reUser = session.getAttribute("user");
		Object reToken = session.getAttribute("token");
		if(reUser!=null&&reToken!=null){
			Map<String,Object> user = (Map<String, Object>) reUser;
			String token = redis.getHashValue(Statics.CMS_REDIS_TOKEN_NAME, user.get("id").toString());
			if(token.equals(reToken)){
				// 登录没过期以后的刷新
				mav.addObject("message", "登陆成功！");
				mav.setViewName("index");
				return mav;
			}else{
				// 登录过期以后的刷新
				mav.addObject("message", "此账号已经在别处登录！");
				mav.setViewName("login");
				return mav;
			}
		}
		mav.addObject("loginName", loginName);
		mav.addObject("password", password);
		mav.addObject("code", code);
		if(loginName==null||loginName.trim().equals("")){
			// 账号为空
			mav.addObject("message", "账号不能为空！");
			session.removeAttribute("loginCode");
			mav.setViewName("login");
			return mav;
		}
		if(password==null||password.trim().equals("")){
			// 密码为空
			mav.addObject("message", "密码不能为空！");
			session.removeAttribute("loginCode");
			mav.setViewName("login");
			return mav;
		}
		/*
		if(code==null||code.trim().equals("")){
			// 验证码为空
			mav.addObject("message", "验证码不能为空！");
			session.removeAttribute("loginCode");
			mav.setViewName("login");
			return mav;
		}
		Object sessionCode = session.getAttribute("loginCode");
		if(sessionCode==null){
			// 验证码失效
			mav.addObject("message", "验证码失效，请重新获取登陆！");
			mav.setViewName("login");
			return mav;
		}
		if(!code.equalsIgnoreCase(sessionCode.toString())){
			// 验证码错误
			mav.addObject("message", "验证码错误！");
			session.removeAttribute("loginCode");
			mav.setViewName("login");
			return mav;
		}
		*/
		Code admin = ls.login(loginName, password);
		mav.addObject("message", admin.getMessage());
		if(admin.getCode()!=1){
			mav.setViewName("login");
			return mav;
		}
		Map<String,Object> map = admin.getMapData();
		List<Menu> allmenus = (List<Menu>) map.get("allMenus");
		List<String> menuIds = (List<String>) map.get("menuIds");
		mav.addObject("allMenus", allmenus);
		mav.addObject("menuIds", menuIds);
		map.remove("allMenus");
		map.remove("menuIds");
		session.setAttribute("user", map);
		// 用于保持同一个用户只能有一个人使用
		String token = GenerateUtil.generateToken(map.get("id").toString());
		session.setAttribute("token", token);
		redis.setHashValue(Statics.CMS_REDIS_TOKEN_NAME, map.get("id").toString(), token);
		session.removeAttribute("loginCode");
		mav.setViewName("index");
		return mav;
	}
	
	
	/**
	 * 安全退出
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/exit",method=RequestMethod.GET)
	public ModelAndView exit(HttpSession session){
		Object userobj = session.getAttribute("user");
		if(userobj!=null){
			Map<String, Object> admin = (Map<String, Object>) userobj;
			redis.delHashValue(Statics.CMS_REDIS_TOKEN_NAME, admin.get("id").toString());
			session.removeAttribute("user");
		}
		Object tokenobj = session.getAttribute("token");
		if(tokenobj!=null){
			session.removeAttribute("token");
		}
		ModelAndView mav = new ModelAndView();
		mav.setViewName("login");
		mav.addObject("message", "成功退出！");
		return mav;
	}
	
	/**
	 * 获取验证码
	 * @param session
	 * @throws IOException 
	 */
	@RequestMapping(value="/codeImage")
	public void getCode(HttpSession session,HttpServletResponse resp) throws IOException{
		// 获取验证码字符串并放入session
		String code = ImageCodeUtil.getStringCode();
		session.setAttribute("loginCode", code);
		// 发送图片验证码
		resp.setHeader("pragma", "No-cache");
		resp.setHeader("Cache-C bontrol", "No-cache");
		resp.setDateHeader("expres", 0);
		resp.setContentType("image/jpeg");
		BufferedImage image = ImageCodeUtil.createImageCode(code);
		ByteArrayOutputStream out=new ByteArrayOutputStream();
		ImageIO.write(image, "JPEG", out);
		byte[] buf =out.toByteArray();
		resp.setContentLength(buf.length);
	    ServletOutputStream output=resp.getOutputStream();
	    output.write(buf);
	    output.flush();
	    output.close();
	    out.close();
	}
}
