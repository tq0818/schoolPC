package com.yuxin.wx.api.tiku;

import java.util.List;

import com.yuxin.wx.model.tiku.TikuTopicExampoint;
/**
 * Service Interface:TikuTopicExampoint
 * @author wang.zx
 * @date 2015-7-8
 */
public interface ITikuTopicExampointService  {
	/**
	 * 
	* @Title: saveTikuTopicExampoint
	* @Description: 新增TikuTopicExampoint
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-7-8
	* @user by wangzx
	 */
	void insert(TikuTopicExampoint entity);
	
	/**
	 * 
	* @Title: batchSaveTikuTopicExampoint 
	* @Description: 批量新增TikuTopicExampoint
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-7-8
	* @user by wangzx
	 */
	void batchInsert(List<TikuTopicExampoint> list);
	
	/**
	 * 
	* @Title: updateTikuTopicExampoint 
	* @Description: 编辑TikuTopicExampoint
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-7-8
	* @user by wangzx
	 */
	void update(TikuTopicExampoint entity);
	
	/**
	 * 
	* @Title: deleteTikuTopicExampointById 
	* @Description: 根据id删除TikuTopicExampoint
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-7-8
	* @user by wangzx
	 */
	void deleteTikuTopicExampointById(Integer id);
	
	/**
	 * 
	* @Title: deleteTikuTopicExampointByIds 
	* @Description: 根据id批量删除TikuTopicExampoint
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-7-8
	* @user by wangzx
	 */
	void deleteTikuTopicExampointByIds(Integer[] ids);
	
	/**
	 * 
	* @Title: findTikuTopicExampointById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-7-8
	* @user by wangzx
	 */
	TikuTopicExampoint findTikuTopicExampointById(Integer id);
	
	/**
	 * 
	* @Title: findTikuTopicExampointByPage 
	* @Description: 分页查询
	* @return
	* @return List<TikuTopicExampoint>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-7-8
	* @user by wangzx
	 */
	List<TikuTopicExampoint> findTikuTopicExampointByPage(TikuTopicExampoint search);
	
	/**
	 * 
	 * Class Name: ITikuTopicExampointService.java
	 * @Description: 查询试题章节信息
	 * @author 周文斌
	 * @date 2015-7-10 下午12:41:07
	 * @version 1.0
	 * @param topicId
	 * @return
	 */
	TikuTopicExampoint findPointByTopicId(Integer topicId);
	
	/**
	 * 
	 * Class Name: ITikuTopicExampointService.java
	 * @Description: 查询关系id试题与 章节考点
	 * @author 周文斌
	 * @date 2015-7-16 下午12:32:13
	 * @version 1.0
	 * @param topicId
	 * @return
	 */
	Integer findIdByTopicId(Integer topicId);
	
	/**
	 * 
	 * Class Name: ITikuTopicExampointService.java
	 * @Description: 删除试题相关
	 * @author 周文斌
	 * @date 2015-7-17 下午2:23:38
	 * @version 1.0
	 * @param topicId
	 */
	void deleteByTopicId(List<Integer> list);
}