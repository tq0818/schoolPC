package com.yuxin.wx.api.course;


import java.util.List;

import com.yuxin.wx.model.course.CoursePotocolBindHistory;
/**
 * Service Interface:CoursePotocolBindHistory
 * @author chopin
 * @date 2016-11-1
 */
public interface ICoursePotocolBindHistoryService  {
	/**
	 * 
	* @Title: saveCoursePotocolBindHistory
	* @Description: 新增CoursePotocolBindHistory
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-11-1
	* @user by wangzx
	 */
	void insert(CoursePotocolBindHistory entity);
	
	/**
	 * 
	* @Title: batchSaveCoursePotocolBindHistory 
	* @Description: 批量新增CoursePotocolBindHistory
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-11-1
	* @user by wangzx
	 */
	void batchInsert(List<CoursePotocolBindHistory> list);
	
	/**
	 * 
	* @Title: updateCoursePotocolBindHistory 
	* @Description: 编辑CoursePotocolBindHistory
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-11-1
	* @user by wangzx
	 */
	void update(CoursePotocolBindHistory entity);
	
	/**
	 * 
	* @Title: deleteCoursePotocolBindHistoryById 
	* @Description: 根据id删除CoursePotocolBindHistory
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-11-1
	* @user by wangzx
	 */
	void deleteCoursePotocolBindHistoryById(Integer id);
	
	/**
	 * 
	* @Title: deleteCoursePotocolBindHistoryByIds 
	* @Description: 根据id批量删除CoursePotocolBindHistory
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-11-1
	* @user by wangzx
	 */
	void deleteCoursePotocolBindHistoryByIds(Integer[] ids);
	
	/**
	 * 
	* @Title: findCoursePotocolBindHistoryById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-11-1
	* @user by wangzx
	 */
	CoursePotocolBindHistory findCoursePotocolBindHistoryById(Integer id);
	
	/**
	 * 
	* @Title: findCoursePotocolBindHistoryByPage 
	* @Description: 分页查询
	* @return
	* @return List<CoursePotocolBindHistory>    返回类型 
	* @throws 
	* @exception 
	* @date 2016-11-1
	* @user by wangzx
	 */
	List<CoursePotocolBindHistory> findCoursePotocolBindHistoryByPage(CoursePotocolBindHistory search);
}