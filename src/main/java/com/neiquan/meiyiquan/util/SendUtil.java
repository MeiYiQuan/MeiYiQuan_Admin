package com.neiquan.meiyiquan.util;

import com.alibaba.fastjson.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import sun.misc.BASE64Encoder;

import java.util.*;

/**
 * 处理有关签名以及信息交互
 * @author Administrator
 */
@SuppressWarnings("restriction")
public class SendUtil {
	
	private final static Logger logger = LoggerFactory.getLogger(SendUtil.class);

	@Autowired
	private static RedisUtil ru;

	/**
	 * 验证签名。
	 * 
	 * 验证签名要分接口类型：注册接口，登录接口，只有登录才能访问的接口，不登录也能访问的接口。
	 * 
	 * 注册接口：
	 * 		不会有userId，也不会有token。但是会有关于注册信息的参数，以及sign和timestamp。
	 * 
	 * 登录接口：
	 * 		同注册接口。
	 * 
	 * 只有登录才能访问的接口：
	 * 		有userId，token，sign，timestamp和其他参数。
	 * 
	 * 不登录也能访问的接口：
	 * 		同注册接口。
	 * 
	 * 思路：
	 * 		对于interceptor，要分两种验证，一种只对是否为本项目app的请求作出验证，而不再对用户是否已经登录，访问的是什么类型的接口做限制。
	 * 因为有些验证是相对于接口来说的，所以另一种就是对特定情况的接口(如必须登录才能访问的接口)做出限制。
	 * 
	 * 具体做法：
	 * 		在mvc.xml里设置两层interceptor。
	 * 一层的请求路径为:/nouser※/※.do,代表没有登录也可以访问的接口。
	 * 另外一层设置为:/user※/※.do,代表必须登录才能访问的接口。
	 * 这样做要保证所有的controller都是以这样的命名规则来命名，可以将controller包再分成两个包，一个表示需要登录才能访问的接口，另一个则不需要登录。
	 * 
	 * @param map
	 */
	public static boolean checkSign(Map<String, Object> map, String userId) {
		String remoteSign = (String) map.get("sign");
		Long timestamp = Long.parseLong(map.get("timestamp") + "");
		map.remove("token");
		map.remove("sign");
		map.remove(Statics.SIGN_USER_ID_NAME);
		if (System.currentTimeMillis() - timestamp > Statics.SEND_MAX_TIME)
			return false;
		String sign1 = "";
		String sign2 = "";
		if (map != null && map.size() > 0) {
			map.remove("timestamp");
			Set<String> keySet = map.keySet();
			List<String> list = new ArrayList<String>();
			for (String key : keySet) {
				list.add(key);
			}
			Collections.sort(list);
			StringBuffer strBuffer = new StringBuffer();
			for (String s : list) {
				strBuffer.append(s);
			}
			String argStr = strBuffer.toString();
			
			String token = Statics.SIGN_USER_DEFAULT_TOKEN;
			String firstTimeMD5 = MD5Util.GetMD5Code(argStr+"&" + token + "&" + timestamp);
			String secondContent = Statics.SIGN_HEAD + firstTimeMD5;
			String secondTimeMD5 = MD5Util.GetMD5Code(secondContent);
			sign1 = secondTimeMD5;

			String realtoken = ru.getHashValue(userId, "token");
			if (realtoken==null||realtoken.trim().equals("")){
				sign2 = sign1;
			}else{
				String realfirstTimeMD5 = MD5Util.GetMD5Code(argStr+"&" + realtoken + "&" + timestamp);
				String realsecondContent = Statics.SIGN_HEAD + realfirstTimeMD5;
				String realsecondTimeMD5 = MD5Util.GetMD5Code(realsecondContent);
				sign2 = realsecondTimeMD5;
			}
		} 
		if (sign2.equalsIgnoreCase(remoteSign) || sign1.equalsIgnoreCase(remoteSign)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 生成签名。
	 * 用于发送时传入的map最少有四个参数：code,message,data,timestamp。
	 * 当是app端发送来的请求时，会是userId,token,timestamp和其他需要携带的参数。
	 * 取出timestamp，清除掉map里的timestamp(如果是接收到信息进行验证时还会去掉userId和token)。
	 * 把剩余的部分(即具体的有效的参数名，当是向app端发送时，就是code,message,data，当是app端的请求时名称数量不固定，依接口而定)的key取出放到list里，吧这个list进行字典排序，把结果一一取出并拼接成字符串。
	 * 在进行验证时，由于是app端发来的信息，所以这个信息里会有userId，用这个userId去redis里获取token。
	 * 如果没有查到token，则用默认的token静态量来表示。
	 * 将之前拼接的字符串用&添加token和timestamp。
	 * 进行MD5加密。
	 * 以双方约定的SIGN_HEAD为开头拼接刚刚加密后的结果。
	 * 再进行MD5加密。
	 * 签名成功完毕，抛出。
	 * @param map
	 * @return
	 */
	public static String generateSign(Map<String, Object> map, String userId) {
		String sign = "";
		if (map != null && map.size() > 0) {
			String timestamp = map.get("timestamp") + "";
			map.remove("timestamp");
			map.remove(Statics.SIGN_USER_ID_NAME);
			map.remove("token");
			Set<String> keySet = map.keySet();
			List<String> list = new ArrayList<String>();
			for (String key : keySet) {
				list.add(key);
			}
			Collections.sort(list);
			StringBuffer strBuffer = new StringBuffer();
			for (String s : list) {
				strBuffer.append(s);
			}
			String token;// 临时设定的token，后面需要改掉
			token = ru.getHashValue(userId, "token");
			if (token==null||token.trim().equals(""))
				token = Statics.SIGN_USER_DEFAULT_TOKEN;
			strBuffer.append("&" + token + "&" + timestamp);
			String firstTimeMD5 = MD5Util.GetMD5Code(strBuffer.toString());
			String secondContent = Statics.SIGN_HEAD + firstTimeMD5;
			String secondTimeMD5 = MD5Util.GetMD5Code(secondContent);
			sign = secondTimeMD5;
		}
		return sign;
	}

	/**
	 * 将传进来的map转换为json并加密抛出。
	 * 传进来的map形式：
	 * 		{"code":20,"message":"验证码输入错误！","data":具体结果集}。
	 * 而在这个方法里会给map里先加入一个时间戳，再去调用生成签名的方法。
	 * 生成签名之后，这个map一定不会有的字段:timestamp,sign,token,userId。
	 * 即剩下了原始的map参数，需要再讲上面的4个参数加入到map里。
	 * 将这个map转化成json字符串。
	 * 将json字符串与双方约定的SEND_DES_KEY进行DES加密，会产生一个byte数组。
	 * 将这个byte数组进行base64编码，并将其中的\n转化为空字符串。
	 * 将编码结果放入一个新的map里，map的key就是与app端约定好的用于取出信息的字段。
	 * 将这个map抛出。
	 * @throws @since
	 *             1.8
	 */
	public static String getJsonString(Map<String, Object> map, String userId) {
		try {
			Long currentTime = System.currentTimeMillis();
			map.put("timestamp", currentTime);
			String sign = generateSign(map, userId);
			map.put("sign", sign);
			map.put("timestamp", currentTime);
			map.put("token","token");
			map.put(Statics.SIGN_USER_ID_NAME,userId);
			String json = JSONObject.toJSONString(map);
			byte[] byteArray = DESUtil.encrypt(json.getBytes("UTF-8"), Statics.SEND_DES_KEY);
			Map<String, String> sendMap = new HashMap<String, String>();
			sendMap.put(Statics.SEND_HEAD, new BASE64Encoder().encode(byteArray).replace("\n", ""));
			return JSONObject.toJSONString(sendMap);
		} catch (Exception e) {
			String jsonmap = map==null||map.size()<1?"map为空！":JSONObject.toJSONString(map);
			PrintUtil.printException("在进行将map加密抛出时出现了异常！userId=" + userId + ",DES加密之前的map为：\n\t" + jsonmap, logger, e);
		}
		return "";
	}

	public static RedisUtil getRu() {
		return ru;
	}

	public static void setRu(RedisUtil ru) {
		SendUtil.ru = ru;
	}

}
