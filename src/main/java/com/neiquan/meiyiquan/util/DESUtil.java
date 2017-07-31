package com.neiquan.meiyiquan.util;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.SecureRandom;

@SuppressWarnings("restriction")
public class DESUtil {
	
	private final static Logger logger = LoggerFactory.getLogger(DESUtil.class);
	
	/**
	 * 加密
	 * 
	 * @param datasource
	 *            byte[]
	 * @param password
	 *            String
	 * @return byte[]
	 */
	public static byte[] encrypt(byte[] datasource, String password) {
		try {
			SecureRandom random = new SecureRandom();
			DESKeySpec desKey = new DESKeySpec(password.getBytes());
			// 创建一个密匙工厂，然后用它把DESKeySpec转换成
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
			SecretKey securekey = keyFactory.generateSecret(desKey);
			// Cipher对象实际完成加密操作
			Cipher cipher = Cipher.getInstance("DES");
			// 用密匙初始化Cipher对象
			cipher.init(Cipher.ENCRYPT_MODE, securekey, random);
			// 现在，获取数据并加密
			// 正式执行加密操作
			return cipher.doFinal(datasource);
		} catch (Throwable e) {
			String length = datasource==null?"null":datasource.length+"";
			PrintUtil.printThrowable("在进行DES加密的时候出现了异常，加密的password为:" + password + ",byte数组长度为:" + length + "。", logger, e);
		}
		return null;
	}

	/**
	 * 解密
	 * 
	 * @param src
	 *            byte[]
	 * @param password
	 *            String
	 * @return byte[]
	 * @throws Exception
	 */
	public static byte[] decrypt(byte[] src, String password) {
		try{
			// DES算法要求有一个可信任的随机数源
			SecureRandom random = new SecureRandom();
			// 创建一个DESKeySpec对象
			DESKeySpec desKey = new DESKeySpec(password.getBytes());
			// 创建一个密匙工厂
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
			// 将DESKeySpec对象转换成SecretKey对象
			SecretKey securekey = keyFactory.generateSecret(desKey);
			// Cipher对象实际完成解密操作
			Cipher cipher = Cipher.getInstance("DES");
			// 用密匙初始化Cipher对象
			cipher.init(Cipher.DECRYPT_MODE, securekey, random);
			// 真正开始解密操作
			return cipher.doFinal(src);
		}catch(Exception e){
			String length = src==null?"null":src.length+"";
			PrintUtil.printException("在进行DES解密的时候出现了异常，解密的password为:" + password + ",byte数组长度为:" + length + "。", logger, e);
		}
		return null;
	}

	public static void main(String[] args){
		try {
			String json = "{\"type\":\"weibo\",\"id\":\"123984jkcbv\",\"name\":\"胖哒~么么哒~\",\"avatar\":\"http://img1.gtimg.com/digi/pics/hv1/104/220/21844179.jpg\"}";
			String content = json;
			byte[] passwdArray = encrypt(content.getBytes(), "双方约定的秘钥");
			String passwd = new BASE64Encoder().encode(passwdArray);
			System.out.println(passwd);

			String pa = passwd.replace("\n", "");
			byte[] base64Array = new BASE64Decoder().decodeBuffer(pa);
			byte[] contentArray = decrypt(base64Array, "ProjectConstants.TRANSFER_KEY");
			String content2 = new String(contentArray);
			System.out.println(content2);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}