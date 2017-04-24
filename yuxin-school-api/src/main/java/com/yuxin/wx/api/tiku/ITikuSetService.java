package com.yuxin.wx.api.tiku;

import java.util.List;

import com.yuxin.wx.model.tiku.TikuSet;
/**
 * Service Interface:TikuSet
 * @author wang.zx
 * @date 2015-7-8
 */
public interface ITikuSetService  {
	/**
	 * 
	* @Title: saveTikuSet
	* @Description: 新增TikuSet
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-7-8
	* @user by wangzx
	 */
	void insert(TikuSet entity);
	
	/**
	 * 
	* @Title: batchSaveTikuSet 
	* @Description: 批量新增TikuSet
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-7-8
	* @user by wangzx
	 */
	void batchInsert(List<TikuSet> list);
	
	/**
	 * 
	* @Title: updateTikuSet 
	* @Description: 编辑TikuSet
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-7-8
	* @user by wangzx
	 */
	void update(TikuSet entity);
	
	/**
	 * 
	* @Title: deleteTikuSetById 
	* @Description: 根据id删除TikuSet
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-7-8
	* @user by wangzx
	 */
	void deleteTikuSetById(Integer id);
	
	/**
	 * 
	* @Title: deleteTikuSetByIds 
	* @Description: 根据id批量删除TikuSet
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-7-8
	* @user by wangzx
	 */
	void deleteTikuSetByIds(Integer[] ids);
	
	/**
	 * 
	* @Title: findTikuSetById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-7-8
	* @user by wangzx
	 */
	TikuSet findTikuSetById(Integer id);
	
	/**
	 * 
	* @Title: findTikuSetByPage 
	* @Description: 分页查询
	* @return
	* @return List<TikuSet>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-7-8
	* @user by wangzx
	 */
	List<TikuSet> findTikuSetByPage(TikuSet search);
	
	/**
	 * 
	 * Class Name: ITikuSetService.java
	 * @Description: 查询题库设置
	 * @author 周文斌
	 * @date 2015-7-9 下午2:27:40
	 * @version 1.0
	 * @param tiku
	 * @return
	 */
	TikuSet findSetByCompanyIdAndCategoryId(TikuSet tiku);
}