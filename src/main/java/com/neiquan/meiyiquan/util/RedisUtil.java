package com.neiquan.meiyiquan.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;

/**
 * 用于处理对redis的操作
 * @author Administrator
 *
 */
public class RedisUtil {

	// 操作redis客户端
	private static Jedis jedis;
	
	@Autowired
	private JedisConnectionFactory jedisConnectionFactory;
	
	@Resource(name="projectProperties")
	private Properties properties;

	/**
	 * 获取一个jedis 客户端
	 * 
	 * @return
	 */
	private Jedis getJedis() {
		if (jedis == null) {
			Jedis jedis = jedisConnectionFactory.getShardInfo().createResource();
			jedis.select(Integer.parseInt(properties.getProperty("redis.databaseindex")));
			return jedis;
		}
		return jedis;
	}
	
	/**
	 * 设置Hash类型的值
	 * @param hashName
	 * @param key
	 * @param value
	 */
	public void setHashValue(String hashName, String key, String value){
		Map<String, String> map = new HashMap<String, String>();
		map.put(key, value);
		getJedis().hmset(hashName, map);
	}
	
	/**
	 * 获取Hash类型的值
	 * @param hashName
	 * @param key
	 * @return
	 */
	public String getHashValue(String hashName, String key){
		List<String> value = getJedis().hmget(hashName, key);
		return value.get(0);
	}

	/**
	 * 删除Hash值
	 * @param hashName
	 * @param key
	 * @return
	 */
	public Long delHashValue(String hashName, String key){
		Long rows = getJedis().hdel(hashName, key);
		return rows;
	}
}
