package com.neiquan.meiyiquan.util;

import com.pingplusplus.Pingpp;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;


/**
 * 作者：齐潮
 * 创建日期：2016年12月02日
 * 类说明：用于获得ping++支付用的map
 */
public class PingPlusPlusUtil {
	
	@Autowired
	private static Properties properties;
	
	/**
	 * app的id
	 */
	private static String appId;
	
	/**
	 * 是否已经对Pingpp的属性赋值的标识
	 */
	private static boolean index = false;
	
	/**
	 * 获取用于获得charge的基map，这个map不是最终的用来获取charge的map，需要添加其他订单信息
	 * @return
	 */
    public static Map<String,Object> getSendMap() {
        if(!index){
        	Pingpp.apiKey = properties.getProperty("ping.apiKey");
			Pingpp.privateKeyPath = properties.getProperty("ping.keyPath");
			appId = properties.getProperty("ping.appId");
			index = true;
        }
    	Map<String, Object> chargeParams = new HashMap<String, Object>();
        Map<String, String> app = new HashMap<String, String>();
        app.put("id", appId);//APP ID
        chargeParams.put("app", app);
//      chargeParams.put("client_ip", "127.0.0.1");//发起支付的IP
        chargeParams.put("currency","cny");
//      Charge charge = Charge.create(chargeParams);
        return chargeParams;
    }
    
    
}
