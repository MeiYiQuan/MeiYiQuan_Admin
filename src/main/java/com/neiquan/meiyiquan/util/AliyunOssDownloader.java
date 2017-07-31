package com.neiquan.meiyiquan.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.aliyun.oss.OSSClient;

public class AliyunOssDownloader {
	private static Logger logger = LoggerFactory.getLogger(AliyunOssDownloader.class);

	public static void downloadAndShutdown(OSSClient client, String bucketName) {
		try {
			System.out.println(JSONObject.toJSONString(client.listObjects(bucketName)));
		} catch (Exception e) {
			logger.error("", e);
		} finally {
			if (client != null) {
				client.shutdown();
			}
		}
	}


}
