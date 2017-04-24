package com.yuxin.wx.api.tiku;

import java.util.List;

import com.yuxin.wx.model.tiku.TikuUserBatchTopic;
/**
 * Service Interface:TikuUserBatchTopic
 * @author wang.zx
 * @date 2015-8-24
 */
public interface ITikuUserBatchTopicService  {
	/**
	 * 
	* @Title: saveTikuUserBatchTopic
	* @Description: 新增TikuUserBatchTopic
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-8-24
	* @user by wangzx
	 */
	void insert(TikuUserBatchTopic entity);
	
	/**
	 * 
	* @Title: batchSaveTikuUserBatchTopic 
	* @Description: 批量新增TikuUserBatchTopic
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-8-24
	* @user by wangzx
	 */
	void batchInsert(List<TikuUserBatchTopic> list);
	
	/**
	 * 
	* @Title: updateTikuUserBatchTopic 
	* @Description: 编辑TikuUserBatchTopic
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-8-24
	* @user by wangzx
	 */
	void update(TikuUserBatchTopic entity);
	
	/**
	 * 
	* @Title: deleteTikuUserBatchTopicById 
	* @Description: 根据id删除TikuUserBatchTopic
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-8-24
	* @user by wangzx
	 */
	void deleteTikuUserBatchTopicById(Integer id);
	
	/**
	 * 
	* @Title: deleteTikuUserBatchTopicByIds 
	* @Description: 根据id批量删除TikuUserBatchTopic
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-8-24
	* @user by wangzx
	 */
	void deleteTikuUserBatchTopicByIds(Integer[] ids);
	
	/**
	 * 
	* @Title: findTikuUserBatchTopicById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-8-24
	* @user by wangzx
	 */
	TikuUserBatchTopic findTikuUserBatchTopicById(Integer id);
	
	/**
	 * 
	* @Title: findTikuUserBatchTopicByPage 
	* @Description: 分页查询
	* @return
	* @return List<TikuUserBatchTopic>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-8-24
	* @user by wangzx
	 */
	List<TikuUserBatchTopic> findTikuUserBatchTopicByPage(TikuUserBatchTopic search);
	
	/**
	 * 
	 * Class Name: ITikuUserBatchTopicService.java
	 * @Description: 查询当前做到第几题
	 * @author 周文斌
	 * @date 2015-8-31 下午5:11:27
	 * @version 1.0
	 * @param id
	 * @return
	 */
	Integer findCurrentById(Integer id);
}