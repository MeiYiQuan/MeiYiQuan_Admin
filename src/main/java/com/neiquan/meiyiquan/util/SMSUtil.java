package com.neiquan.meiyiquan.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;

public class SMSUtil {
	private static Logger log = LoggerFactory.getLogger(SMSUtil.class);
	private static Properties prop = new Properties();
	private static String username;// 短信账号
	private static String pwd;// 密码
	private static String apiKey;// API密钥
	private static String content;// 短信模板
	// private static String sign;// 短信签名
	private static String encode;// 编码
	private static String sendSMSURL;// 发送短信请求地址

	static {
		try {
			prop.load(SMSUtil.class.getResourceAsStream("/sms.properties"));
			sendSMSURL = prop.getProperty("sendSMSURL");// 获取发送短信地址
			content = prop.getProperty("content");// 获取短信模板
			// sign = prop.getProperty("sign");// 获取短信签名
			// content = content.replace("${sign}", sign);// 替换模板中的签名
			username = prop.getProperty("username");
			pwd = prop.getProperty("pwd");
			apiKey = prop.getProperty("apikey");
			encode = prop.getProperty("encode");
			sendSMSURL = sendSMSURL.replace("${username}", username).replace("${pwd}", pwd).replace("${apikey}", apiKey)
					.replace("${encode}", encode);// 替换账号密码
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static Map<String, Object> sendSMS(String phone, String code) throws IOException {
//		if (ValidationUtil.isChinaPhoneNumber(phone) == false) {
//			return new ResponseMap(ApiErrorCode.phone_number_error).generateMap();
//		}
		String encodedContent = URLEncoder.encode(content.replace("${code}", code), encode);
		String sendUrl = sendSMSURL.replace("${mobile}", phone).replace("${content}", encodedContent);
		System.out.println(sendUrl);
		URL url = new URL(sendUrl);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		int status = connection.getResponseCode();
		if (status == HttpURLConnection.HTTP_OK) {
			// 请求成功发送
			InputStream inputStream = connection.getInputStream();
			int bytesRead = -1;
			byte[] buffer = new byte[1024];
			String responseStr = "";
			while ((bytesRead = inputStream.read(buffer)) != -1) {
				responseStr += new String(buffer, 0, bytesRead);
			}
			System.out.println(responseStr);
			String[] responseArray = responseStr.split(":");
			if (responseArray[0].equalsIgnoreCase("success")) {
				// 短信已成功发送
//				return new ResponseMap(ApiErrorCode.success).generateMap();
				return null;
			} else {
				// 短信因为账号原因发送失败
				log.error(JSONObject.toJSONString(responseArray));
//				return new ResponseMap(ApiErrorCode.sms_other_error).generateMap();
				return null;
			}
		} else {
			// 发送短信请求发送失败，网络问题
//			return new ResponseMap(ApiErrorCode.sms_network_error).generateMap();
			return null;
		}

	}

}
