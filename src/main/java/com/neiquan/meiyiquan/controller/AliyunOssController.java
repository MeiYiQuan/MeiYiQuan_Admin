package com.neiquan.meiyiquan.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.neiquan.meiyiquan.service.BannerService;
import com.neiquan.meiyiquan.util.StringUtil;

import net.sf.json.JSONObject;
 
 
@Controller
@RequestMapping(value = "alOss")
public class AliyunOssController {
	@Autowired
	private BannerService bs;
	@ResponseBody
	@RequestMapping("aliUploader")
	public String aliUploader(@RequestParam(value = "file", required = false) MultipartFile file,@RequestParam(value = "file1", required = false) MultipartFile file1) {
		   //保存  
		String pic_redirect_url="";
		try {
			pic_redirect_url=bs.updateHead(file);
			} catch (IOException e) {
				e.printStackTrace();
			}
		return StringUtil.getSuccessMessage(pic_redirect_url);
	}
	@ResponseBody
	@RequestMapping("kUploader")
	public String kUploader(@RequestParam(value = "imgFile", required = false) MultipartFile file,@RequestParam(value = "file1", required = false) MultipartFile file1) {
		   //保存  
		JSONObject js=new JSONObject();
		String pic_redirect_url="";
		try {
			pic_redirect_url=bs.updateHead(file);
			} catch (IOException e) {
				e.printStackTrace();
				js.put("error", 1);
				js.put("message", "上传失败");
				return js.toString();
			}
		
		js.put("url", pic_redirect_url);
		js.put("error", 0);
		return js.toString();
	}
	@ResponseBody
	@RequestMapping("show_pic_url")
	public String show_pic_url(@RequestParam(value = "file", required = false) MultipartFile file,@RequestParam(value = "file1", required = false) MultipartFile file1) {
		   //保存  
		String show_pic_url="";
		try {
			show_pic_url=bs.updateHead(file);
			} catch (IOException e) {
				e.printStackTrace();
			}
		return StringUtil.getSuccessMessage(show_pic_url);
	}
	
	
}
