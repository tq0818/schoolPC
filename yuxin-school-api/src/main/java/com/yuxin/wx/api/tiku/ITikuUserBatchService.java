package com.yuxin.wx.api.tiku;

import java.util.List;

import com.yuxin.wx.model.tiku.TikuUserBatch;
import com.yuxin.wx.model.tiku.TikuUserBatchTopic;
import com.yuxin.wx.model.tiku.TikuUserExercise;
/**
 * Service Interface:TikuUserBatch
 * @author wang.zx
 * @date 2015-8-24
 */
public interface ITikuUserBatchService  {
	/**
	 * 
	* @Title: saveTikuUserBatch
	* @Description: 新增TikuUserBatch
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-8-24
	* @user by wangzx
	 */
	void insert(TikuUserBatch entity);
	
	/**
	 * 
	* @Title: batchSaveTikuUserBatch 
	* @Description: 批量新增TikuUserBatch
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-8-24
	* @user by wangzx
	 */
	void batchInsert(List<TikuUserBatch> list);
	
	/**
	 * 
	* @Title: updateTikuUserBatch 
	* @Description: 编辑TikuUserBatch
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-8-24
	* @user by wangzx
	 */
	void update(TikuUserBatch entity);
	
	/**
	 * 
	* @Title: deleteTikuUserBatchById 
	* @Description: 根据id删除TikuUserBatch
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-8-24
	* @user by wangzx
	 */
	void deleteTikuUserBatchById(Integer id);
	
	/**
	 * 
	* @Title: deleteTikuUserBatchByIds 
	* @Description: 根据id批量删除TikuUserBatch
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-8-24
	* @user by wangzx
	 */
	void deleteTikuUserBatchByIds(Integer[] ids);
	
	/**
	 * 
	* @Title: findTikuUserBatchById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-8-24
	* @user by wangzx
	 */
	TikuUserBatch findTikuUserBatchById(Integer id);
	
	/**
	 * 
	* @Title: findTikuUserBatchByPage 
	* @Description: 分页查询
	* @return
	* @return List<TikuUserBatch>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-8-24
	* @user by wangzx
	 */
	List<TikuUserBatch> findTikuUserBatchByPage(TikuUserBatch search);
	
	void insertTopic(TikuUserBatch batch,List<TikuUserBatchTopic> batchTopics,TikuUserExercise exercise);
	
	/**
	 * 
	 * Class Name: ITikuUserBatchService.java
	 * @Description: 查询名字
	 * @author 周文斌
	 * @date 2015-8-31 下午4:41:12
	 * @version 1.0
	 * @param id
	 * @return
	 */
	String findNameById(Integer id);
}