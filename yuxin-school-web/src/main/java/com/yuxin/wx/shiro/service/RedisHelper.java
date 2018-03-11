package com.yuxin.wx.shiro.service;

import java.util.Map;

import org.apache.log4j.Logger;

import com.yuxin.wx.vo.redis.ClassLectureVO;

/**
 * 封装业务相关的redis方法
 * @author liutingrong
 *
 */
public class RedisHelper {
	private static RedisHelper instance = null;
	Logger logger = Logger.getLogger(RedisHelper.class);
	private RedisHelper(){
		
	}
	
	public static RedisHelper getInstance(){
		if(instance == null){
			instance = new RedisHelper();
		}
		return instance;
	}
	
	/**
	 * 
	 * 1.缓存当前用户的课程列表信息
	 * 2.key = String.valueOf(stepCode+item_second_code+item_third_code+companyId+userId) 以唯一确定列表
	 * 3.缓存过期时间为 10min
	 * @param userId	当前用户id
	 * @param companyId	所属学校id
	 * @param item_second_code	年级code
	 * @param stepCode	stepCode
	 * @param map	lessonMap
	 * @param item_third_code 科目code
	 */
	public void putClassLectureMap(Long userId,Long companyId,String item_second_code,String stepCode,String item_third_code ,Map<Integer,ClassLectureVO> map){
		RedisManager redisManager = new RedisManager();
		if(null == map){
			logger.error("====> map is null , refuse to put to redis ");
			return;
		}
		redisManager.set(String.valueOf(stepCode+item_second_code+item_third_code+companyId+userId).getBytes(), SerializeUtils.serialize(map), 10*60*1000);
	}
	
	/**
	 * 获取缓存中的lesson信息,如果没有，则返回null
	 * @param userId
	 * @param companyId
	 * @param item_second_code
	 * @param stepCode
	 * @param item_third_code
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Map<Integer,ClassLectureVO> getClassLectureMap(Long userId,Long companyId,String item_second_code,String stepCode,String item_third_code){
		RedisManager redisManager = new RedisManager();
		byte[] bytes =  redisManager.get(String.valueOf(stepCode+item_second_code+item_third_code+companyId+userId).getBytes());
		return null == bytes ? null : (Map<Integer,ClassLectureVO>)SerializeUtils.deserialize(bytes);
	}
	
	
}
