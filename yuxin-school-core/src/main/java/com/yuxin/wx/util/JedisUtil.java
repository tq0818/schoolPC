package com.yuxin.wx.util;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.yuxin.wx.common.BaseEntity;
import com.yuxin.wx.model.system.SysConfigIndexModel;
import com.yuxin.wx.system.mapper.SysConfigIndexClasstypeMapper;
import com.yuxin.wx.system.mapper.SysConfigIndexCustomMapper;
import com.yuxin.wx.system.mapper.SysConfigIndexItemMapper;
import com.yuxin.wx.system.mapper.SysConfigIndexModelMapper;
import com.yuxin.wx.system.mapper.SysConfigIndexNewsMapper;
import com.yuxin.wx.system.mapper.SysConfigIndexPrivatepageMapper;
import com.yuxin.wx.util.ListTranscoder;
import com.yuxin.wx.util.EntityTranscoder;

import redis.clients.jedis.Jedis;

@Service
public class JedisUtil {
	private static Log log= LogFactory.getLog("log");
	
	private static Jedis jedis=null;
	private static void init(){
		Properties props = new Properties();
		Resource resource = new ClassPathResource("/config.properties");
		String host = null;// 控制台上的“内网地址”
		int timeout = 10000;
		int port = 6379	; // 默认端口 11211，不用改
		String username = null;// 控制台上的“访问账号”
		String password = null;
		try {
			if(host==null){
				props=PropertiesLoaderUtils.loadProperties(resource);
				host = props.getProperty("yunduoketang.redis.host");// 控制台上的“内网地址”
				port = 6379; // 默认端口 6379，不用改
				//username = props.getProperty("yunduoketang.redis.username");// 控制台上的“访问账号”
				password = props.getProperty("yunduoketang.redis.password");// 邮件中提供的“密码”
			}
			jedis = new Jedis(host, port,timeout);
			jedis.auth(password);//instance_id:password
			jedis.select(0);
		}catch(Exception e){
			log.error("--------------初始化redis链接异常，原因["+e+"]---------------",e);
			e.printStackTrace();
		}
	}
	
	private static void relase(){
		jedis.quit();
		jedis.close();
	}

	public static void put(String key,String value){
		init();
		String result=jedis.set(key, value);
		log.info("存-返回值:"+result);
		relase();
	}
	
	public static void put(String key,String value,Integer seconds){
		init();
		String result=jedis.setex(key, seconds, value);
		log.info("存-返回值:"+result);
		relase();
	}
	
	public static void put(byte[] key,byte[]  value){
		init();
		String result=jedis.set(key,value);
		log.info("存-返回值:"+result);
		relase();
	}
	public static void put(byte[] key,byte[]  value,Integer seconds){
		init();
		String result=jedis.setex(key,seconds,value);
		log.info("存-返回值:"+result);
		relase();
	}
	
	public static <T> void put(String key,List<T> value){
		init();
		JObjectTranscoder<List<T>> coder = new JObjectTranscoder<List<T>>();
		String result = jedis.set(key.getBytes(), coder.serialize(value));
		log.info("存-返回值:"+result);
		relase();
	}
	public static <T> void put(String key,List<T> value,Integer seconds){
		init();
		JObjectTranscoder<List<T>> coder = new JObjectTranscoder<List<T>>();
		String result = jedis.setex(key.getBytes(),seconds, coder.serialize(value));
		log.info("存-返回值:"+result);
		relase();
	}
	public static <T extends BaseEntity> void put(String key, T value){
		init();
		JObjectTranscoder<T> coder = new JObjectTranscoder<T>();
		String result = jedis.set(key.getBytes(), coder.serialize(value));
		log.info("存-返回值:"+result);
		relase();
	}
	
	public static <T extends BaseEntity> void put(String key, T value,Integer seconds){
		init();
		JObjectTranscoder<T> coder = new JObjectTranscoder<T>();
		String result = jedis.setex(key.getBytes(),seconds, coder.serialize(value));
		log.info("存-返回值:"+result);
		relase();
	}
	
	public static <K, V> void put(String key, Map<K,V> map){
		init();
		JObjectTranscoder<Map<K,V>> coder = new JObjectTranscoder<Map<K,V>>();
		String result = jedis.set(key.getBytes(), coder.serialize(map));
		log.info("存-返回值:"+result);
		relase();
	}
	
	public static <K, V> void put(String key, Map<K,V> map,Integer seconds){
		init();
		JObjectTranscoder<Map<K,V>> coder = new JObjectTranscoder<Map<K,V>>();
		String result = jedis.setex(key.getBytes(), seconds,coder.serialize(map));
		log.info("存-返回值:"+result);
		relase();
	}
	
	public static String getString(String key){
		init();
		String result=jedis.get(key);
		relase();
		return result;
	}
	
	public static <T> List<T> getList(String key){
		init();
		JObjectTranscoder<List<T>> coder = new JObjectTranscoder<List<T>>();
		byte[] result=jedis.get(key.getBytes());
		log.info("byte:"+result);
		List<T> al = coder.deserialize(result);
		log.info("key:["+key+"]-------result:["+JSONArray.toJSONString(al)+"]");
		relase();
		return al;
	}
	
	/**
	 * 加上指定的增量值之后， key 的值。
	 * @param key
	 * @param integer
	 * @return
	 */
	public static Long incrBy(String key ,Integer integer){
		init();
		Long t= jedis.incrBy(key, integer);
		relase();
		return t;
	}
	
	public static <T> T getObject(String key){
		init();
		JObjectTranscoder<T> coder = new JObjectTranscoder<T>();
		T t = coder.deserialize(jedis.get(key.getBytes()));
		relase();
		return t;
	}
	
	public static <T> T getMap(String key){
		init();
		JObjectTranscoder<T> coder = new JObjectTranscoder<T>();
		T t = coder.deserialize(jedis.get(key.getBytes()));
		relase();
		return t;
	}
	
	public static <T extends BaseEntity> T getEntity(String key){
		init();
		JObjectTranscoder<T> coder = new JObjectTranscoder<T>();
		T t = coder.deserialize(jedis.get(key.getBytes()));
		relase();
		return t;
	}
	
	public static byte[] getObject(byte[] key){
		init();
		byte[]  t= jedis.get(key);
		relase();
		return t;
	}
	
	public static void deleteByKey(String key){
		init();
		jedis.del(key.getBytes());
		relase();
	}
	
	public static void deleteByPattern(byte[] pattern){
		init();
		jedis.del(pattern);
		relase();
	}
	
	public static Set<String> getKeysByPattern(String pattern){
		init();
		Set<String> keys=jedis.keys(pattern);
		relase();
		return keys;
	}
	
	public static Set<byte[]> getKeysByPattern(byte[] pattern){
		init();
		Set<byte[]> keys=jedis.keys(pattern);
		relase();
		return keys;
	}
	public static List<String> hmget(String key,String ...value){
		init();
		List<String> list = jedis.hmget(key,value);
		relase();
		return list;
	}
	public static Map<String, String> hgetAll(String key){
		init();
		Map<String, String> map = jedis.hgetAll(key);
		relase();
		return map;
	}

	public static void hmset(String key,Map<String, String> map){
		init();
		jedis.hmset(key,map);
		relase();
	}
	public static void main(String[] args) {
		try {
			String host = "775fe82af23011e4.m.cnbja.kvstore.aliyuncs.com";//控制台显示访问地址
			int port = 6379;
			Jedis jedis = new Jedis(host, port);
			//鉴权信息由用户名:密码拼接而成
			jedis.auth("775fe82af23011e4:beckham1");//instance_id:password
			String key = "redis";
			String value = "aliyun-redis";
			//select db默认为0
			jedis.select(1);
			//set一个key
			jedis.set(key, value);
			System.out.println("Set Key " + key + " Value: " + value);
			//get 设置进去的key
			String getvalue = jedis.get(key);
			System.out.println("Get Key " + key + " ReturnValue: " + getvalue);
			jedis.quit();
			jedis.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
