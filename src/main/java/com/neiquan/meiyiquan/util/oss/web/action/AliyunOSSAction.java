package com.neiquan.meiyiquan.util.oss.web.action;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.aliyun.oss.OSSClient;
import com.neiquan.meiyiquan.code.Code;
import com.neiquan.meiyiquan.util.AliyunOssUploader;
import com.neiquan.meiyiquan.util.ExtraSpringHibernateTemplate;
import com.neiquan.meiyiquan.util.PrimaryKeyUtil;
import com.neiquan.meiyiquan.util.PropertiesUitl;


@Controller
@RequestMapping(value = "oss", produces = { "text/json;charset=UTF-8" })
public class AliyunOSSAction {
	@Autowired
	private ExtraSpringHibernateTemplate extraSpringHibernateTemplate;

	@ResponseBody
	@RequestMapping("aliUploader")
	public Code aliUploader(@RequestParam(value = "file", required = false) MultipartFile file) {
		String	key=PrimaryKeyUtil.getTimeRandStr8("")+"."+file.getOriginalFilename().split("\\.")[1];
		String accessKeyId = PropertiesUitl.getProperty("accessKeyId")  ;
		String accessKeySecret =PropertiesUitl.getProperty("accessKeySecret");
		String endpoint =PropertiesUitl.getProperty("endpoint");
		//endpoint=endpoint+"/defaultimage";
		String bucketName = PropertiesUitl.getProperty("bucketName");
		OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
		String imgFilePath = PropertiesUitl.getProperty("fileTempPath");// 新生成的图片
		File targetFile = new File(imgFilePath,key);  
		   //保存  
        try {  
            file.transferTo(targetFile);  
        } catch (Exception e) {  
            e.printStackTrace();  
        } 
        key=PropertiesUitl.getProperty("defaultimage")+key ;
		AliyunOssUploader.uploadAndShutdown(ossClient, bucketName, key,targetFile);
		String picname=PropertiesUitl.getProperty("imgFilePath")+key;
		return Code.init(0, null, picname);
	}
	
	@ResponseBody
	@RequestMapping("aliUploaderAudio")
	public Code aliUploaderAudio(@RequestParam(value = "fileAudio", required = false) MultipartFile file) {
		String	key=PrimaryKeyUtil.getTimeRandStr8("")+".mp3";
		String accessKeyId = PropertiesUitl.getProperty("accessKeyId")  ;
		String accessKeySecret =PropertiesUitl.getProperty("accessKeySecret");
		String endpoint =PropertiesUitl.getProperty("endpoint");
		String bucketName = PropertiesUitl.getProperty("bucketName");
		OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
		String imgFilePath = PropertiesUitl.getProperty("fileTempPath");// 新生成的图片
		File targetFile = new File(imgFilePath,key);  
		   //保存  
        try {  
            file.transferTo(targetFile);  
        } catch (Exception e) {  
            e.printStackTrace();  
        } 
        key=PropertiesUitl.getProperty("audiofile")+key ;
		AliyunOssUploader.uploadAndShutdown(ossClient, bucketName, key,targetFile);
		String picname=PropertiesUitl.getProperty("imgFilePath")+key;
		return Code.init(0, null, picname);
	}
}
