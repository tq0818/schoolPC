package com.yuxin.wx.api.tiku;

import java.util.List;
import java.util.Map;

import com.yuxin.wx.model.tiku.TikuTopicOption;
/**
 * Service Interface:TikuTopicOption
 * @author wang.zx
 * @date 2015-7-8
 */
public interface ITikuTopicOptionService  {
	/**
	 * 
	* @Title: saveTikuTopicOption
	* @Description: 新增TikuTopicOption
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-7-8
	* @user by wangzx
	 */
	void insert(TikuTopicOption entity);
	
	/**
	 * 
	* @Title: batchSaveTikuTopicOption 
	* @Description: 批量新增TikuTopicOption
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-7-8
	* @user by wangzx
	 */
	void batchInsert(List<TikuTopicOption> list);
	
	/**
	 * 
	* @Title: updateTikuTopicOption 
	* @Description: 编辑TikuTopicOption
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-7-8
	* @user by wangzx
	 */
	void update(TikuTopicOption entity);
	
	/**
	 * 
	* @Title: deleteTikuTopicOptionById 
	* @Description: 根据id删除TikuTopicOption
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-7-8
	* @user by wangzx
	 */
	void deleteTikuTopicOptionById(Integer id);
	
	/**
	 * 
	* @Title: deleteTikuTopicOptionByIds 
	* @Description: 根据id批量删除TikuTopicOption
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-7-8
	* @user by wangzx
	 */
	void deleteTikuTopicOptionByIds(Integer[] ids);
	
	/**
	 * 
	* @Title: findTikuTopicOptionById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-7-8
	* @user by wangzx
	 */
	TikuTopicOption findTikuTopicOptionById(Integer id);
	
	/**
	 * 
	* @Title: findTikuTopicOptionByPage 
	* @Description: 分页查询
	* @return
	* @return List<TikuTopicOption>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-7-8
	* @user by wangzx
	 */
	List<TikuTopicOption> findTikuTopicOptionByPage(TikuTopicOption search);
	
	/**
	 * 
	 * Class Name: ITikuTopicOptionService.java
	 * @Description: 查询试题选项
	 * @author 周文斌
	 * @date 2015-7-10 上午10:24:45
	 * @version 1.0
	 * @param id
	 * @return
	 */
	List<TikuTopicOption> findOptionByTopicId(Integer topicId);
	
	/**
	 * 
	 * Class Name: ITikuTopicOptionService.java
	 * @Description: 删除试题 选项相关
	 * @author 周文斌
	 * @date 2015-7-17 下午2:26:45
	 * @version 1.0
	 * @param topicId
	 */
	void deleteByTopicId(List<Integer> list);
	
	
	/**
	 * 批量查询试题选项
	 * @param list
	 * @return
	 */
	List<TikuTopicOption> findOptionByListTopicId(Map<String, Object> param);
}