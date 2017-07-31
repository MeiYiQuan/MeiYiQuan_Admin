package com.neiquan.meiyiquan.util;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Properties;
import java.util.Random;
import java.util.UUID;

/**
 * 用于生成一个随机性的东西，比如验证码、订单编号等等
 * @author Administrator
 *
 */
public class GenerateUtil {
	
	@Autowired
	private static Properties properties;
	
	/**
	 * 生成token
	 * @param userName
	 * @return
	 */
	public static String generateToken(String userName){
		UUID uuid = UUID.randomUUID();
		Random random = new Random();
		String chars = uuid.toString()+userName+System.currentTimeMillis()+random.nextFloat();
		String token = MD5Util.GetMD5Code(chars);
		String tokenBase64 = Base64.encodeBase64URLSafeString(token.getBytes());
		return tokenBase64;
	}
	
	/**
	 * 生成手机验证码
	 * @return
	 */
	public static String generateAuthCode(){
		String uuid = UUID.randomUUID().toString();
		return uuid.substring(uuid.length()-6);
	}
	
	/**
	 * 生成数字验证码
	 * 
	 * @param count
	 *            指定验证码位数
	 * @return
	 */
	public static String generateNumberCode(Integer count) {
		String numberStr = "1";
		for (int i = 0; i < count; i++) {
			numberStr += "0";
		}
		Random random = new Random();
		int number = 0;
		do {
			number = random.nextInt(Integer.parseInt(numberStr));
		} while (number < Integer.parseInt(numberStr.substring(0, numberStr.length() - 1)));
		return number + "";
	}
	
	/**
	 * 生成订单编号
	 * @return
	 */
	public static String generateOrderNumer(){
		String orderNumber = properties.getProperty("indentNum.head");
		orderNumber+= (System.currentTimeMillis()+"").substring(5);
		orderNumber+= UUID.randomUUID().toString().substring(27,32).toUpperCase();
		return orderNumber;
	}
	
}
