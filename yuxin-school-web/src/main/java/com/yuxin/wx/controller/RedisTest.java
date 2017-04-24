package com.yuxin.wx.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.shiro.session.Session;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import com.yuxin.wx.classes.impl.EketangLiveRoomServiceImpl;
import com.yuxin.wx.common.BaseEntity;
import com.yuxin.wx.model.RedisModel;
import com.yuxin.wx.model.RedisModelCompare;
import com.yuxin.wx.shiro.service.RedisCache;
import com.yuxin.wx.shiro.service.RedisManager;
import com.yuxin.wx.util.JedisUtil;
import com.yuxin.wx.util.ListTranscoder;
import com.yuxin.wx.util.ObjectTranscoder;


@Controller
@RequestMapping("/redis")
public class RedisTest {
	
	private Logger logger = Logger.getLogger(RedisTest.class);
	
	public static void main(String[] args) {
		String host = "775fe82af23011e4.m.cnbja.kvstore.aliyuncs.com";// 控制台上的“内网地址”
		Integer port = 6379; // 默认端口 6379，不用改
		String username = "775fe82af23011e4";// 控制台上的“访问账号”
		String password = "Yuuxin2015";// 邮件中提供的“密码”
		Jedis jedis = new Jedis(host, port);
		jedis.auth(username+":"+password);//instance_id:password
		jedis.select(0);
		for(int i=0;i<100;i++){
			ObjectTranscoder coder=new ObjectTranscoder();
			Object t= coder.deserialize(jedis.get("shiro_redis_session:5ef94dfb-3b08-4574-be5f-598a28bdec7b".getBytes()));
			System.out.println(t);
		}
		jedis.quit();
		jedis.close();
	}
	
	@RequestMapping(value="/dataAna",method=RequestMethod.GET)
	public String dataAna(HttpServletRequest request,Model model) throws UnsupportedEncodingException{
		String host = "775fe82af23011e4.m.cnbja.kvstore.aliyuncs.com";// 控制台上的“内网地址”
		Integer port = 6379; // 默认端口 6379，不用改
		String username = "775fe82af23011e4";// 控制台上的“访问账号”
		String password = "Yuuxin2015";// 邮件中提供的“密码”
		@SuppressWarnings("resource")
		Jedis jedis = new Jedis(host, port);
		jedis.auth(username+":"+password);
		List<RedisModel> list = new ArrayList<RedisModel>();
		//Set<byte[]> keys2 = jedis.keys("*".getBytes());
		//logger.info("序列化了"+keys2.size());
		
		Set<String> keys = jedis.keys("*");
		long totalMem = 0;
		Long dbSize = jedis.dbSize();
		logger.info("共缓存了 "+dbSize+"个键");
		if(keys!= null && keys.size()>0){
			 Iterator<String> iterator = keys.iterator();
			while(iterator.hasNext()) {
				 String key = iterator.next();
				Object value = null;
				try {
					value = jedis.get(key);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					try {
						value = jedis.get(key.getBytes());
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						try {
							value = jedis.hmget(key, new String[0]);
						} catch (Exception e2) {
							// TODO Auto-generated catch block
							value = jedis.hgetAll(key);
							e2.printStackTrace();
						}
						e1.printStackTrace();
					}
					e.printStackTrace();
				}
				if(value != null ){
					RedisModel redisModel = new RedisModel();
					redisModel.setKey(key);
					list.add(redisModel);
					if(value != null ){
						redisModel.setKey(key);
						if(value instanceof String){
							redisModel.setValueSize(((String)value).getBytes().length);
							totalMem+=((String)value).getBytes().length;
						}else if(value instanceof Collection ||value instanceof BaseEntity){
							byte[] result=jedis.get(key.getBytes());
							redisModel.setValueSize(result.length);
							totalMem+=result.length;
						}else if(value instanceof Map){
							Map<String, String> map = jedis.hgetAll(key);
							redisModel.setValueSize(toByteArray(map).length);
							totalMem+=redisModel.getValueSize();
						}else if(value instanceof byte[]){
							redisModel.setValueSize(((byte[])value).length);
							totalMem+=redisModel.getValueSize();
						}
					}
				}
			}
		}
		 
		 
		 System.out.println("====================统计总共集合数量为： "+list.size());
		 
		 logger.info("====================统计总共集合数量为： "+list.size());
		 Collections.sort(list, new RedisModelCompare());
		 
		 model.addAttribute("list", list);
		 model.addAttribute("totalMem", totalMem);
		
		jedis.quit();
		jedis.close();
		return "/redis/redisdata";
	}
	
	public byte[] toByteArray (Object obj) {      
        byte[] bytes = null;      
        ByteArrayOutputStream bos = new ByteArrayOutputStream();      
        try {        
            ObjectOutputStream
            oos = new ObjectOutputStream(bos);         
            oos.writeObject(obj);        
            oos.flush();         
            bytes = bos.toByteArray ();      
            oos.close();         
            bos.close();        
        } catch (IOException ex) {        
            ex.printStackTrace();   
        }      
        return bytes;    
    }   
}
