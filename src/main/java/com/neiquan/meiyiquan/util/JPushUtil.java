package com.neiquan.meiyiquan.util;

import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import cn.jpush.api.JPushClient;
import cn.jpush.api.common.resp.APIConnectionException;
import cn.jpush.api.common.resp.APIRequestException;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.Notification;

/**
 * 作者：齐潮
 * 创建日期：2016年12月02日
 * 类说明：极光推送工具类，4个方法，基本可以满足一般项目需求
 */
public class JPushUtil {
	
	private final static Logger logger = LoggerFactory.getLogger(JPushUtil.class);
	
    private static JPushClient jPushClient = null;
    
    @Autowired
    private static Properties properties;
    
    /**
     * 获取一个极光客户端
     * @return
     */
    private static JPushClient getClient(){
    	if(jPushClient==null){
    		String appKey = properties.getProperty("jpush.appKey");
    		String masterSecret = properties.getProperty("jpush.masterSecret");
    		jPushClient = new JPushClient(masterSecret,appKey);
    	}
    	return jPushClient;
    }
    
    /**
     * 推送信息给所有android设备
     * @param content
     */
    public static void pushToAllAndroid(String content,Map<String, String> params) {
        PushPayload payload = PushPayload.newBuilder()
        		.setPlatform(Platform.android())
        		.setAudience(Audience.all())
        		.setNotification(Notification.android(content, null, params))
        		.build();
        try {
        	getClient().sendPush(payload);
        } catch (APIConnectionException e) {
        	PrintUtil.printException("链接异常",logger, e);
        } catch (APIRequestException e) {
        	logger.error("出现了请求异常：" + e);
        	logger.error("HTTP Status:" + e.getStatus());
        	logger.error("Error Code: " + e.getErrorCode());
        	logger.error("Error Message: " + e.getErrorMessage());
        	e.printStackTrace();
        }
    }

    /**
     * 推送信息给所有的IOS设备
     * @param content
     */
    public static void pushToAllIOS(String content,Map<String, String> extras) {
        PushPayload payload = PushPayload.newBuilder()
        		.setPlatform(Platform.ios())
        		.setAudience(Audience.all())
        		.setNotification(Notification.ios(content, extras))
        		.build();
        try {
        	getClient().sendPush(payload);
        } catch (APIConnectionException e) {
        	PrintUtil.printException("链接异常",logger, e);
        } catch (APIRequestException e) {
        	logger.error("出现了请求异常：" + e);
        	logger.error("HTTP Status:" + e.getStatus());
        	logger.error("Error Code: " + e.getErrorCode());
        	logger.error("Error Message: " + e.getErrorMessage());
            e.printStackTrace();
        }
    }
    
    /**
     * 推送给指定安卓用户集合信息(携带参数)
     * @param content
     * @param extras
     * @param registIds
     */
    public static void pushRegistUsersForAndroid(String content,Map<String, String> extras,String... registIds){
    	try {
    		getClient().sendAndroidNotificationWithRegistrationID(null, content, extras, registIds);
		} catch (APIConnectionException e) {
			PrintUtil.printException("链接异常",logger, e);
        } catch (APIRequestException e) {
        	logger.error("出现了请求异常：" + e);
        	logger.error("HTTP Status:" + e.getStatus());
        	logger.error("Error Code: " + e.getErrorCode());
        	logger.error("Error Message: " + e.getErrorMessage());
            e.printStackTrace();
        }
    }
    
    /**
     * 推送给指定IOS用户集合信息(携带参数)
     * @param content
     * @param extras
     * @param registIds
     */
    public static void pushRegistUsersForIOS(String content,Map<String, String> extras,String... registIds){
    	try {
    		getClient().sendIosNotificationWithRegistrationID(content, extras, registIds);
		} catch (APIConnectionException e) {
			PrintUtil.printException("链接异常",logger, e);
        } catch (APIRequestException e) {
        	logger.error("出现了请求异常：" + e);
        	logger.error("HTTP Status:" + e.getStatus());
        	logger.error("Error Code: " + e.getErrorCode());
        	logger.error("Error Message: " + e.getErrorMessage());
            e.printStackTrace();
        }
    }
}
