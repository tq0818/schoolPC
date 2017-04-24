package com.yuxin.wx.api.system;

import java.util.List;

import com.yuxin.wx.model.system.SysConfigIndexNews;
/**
 * Service Interface:SysConfigIndexNews
 * @author chopin
 * @date 2015-5-18
 */
public interface ISysConfigIndexNewsService  {
	/**
	 * 
	* @Title: saveSysConfigIndexNews
	* @Description: 新增SysConfigIndexNews
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-5-18
	* @user by wangzx
	 */
	void insert(SysConfigIndexNews entity);
	
	/**
	 * 
	* @Title: batchSaveSysConfigIndexNews 
	* @Description: 批量新增SysConfigIndexNews
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-5-18
	* @user by wangzx
	 */
	void batchInsert(List<SysConfigIndexNews> list);
	
	/**
	 * 
	* @Title: updateSysConfigIndexNews 
	* @Description: 编辑SysConfigIndexNews
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-5-18
	* @user by wangzx
	 */
	void update(SysConfigIndexNews entity);
	
	/**
	 * 
	* @Title: deleteSysConfigIndexNewsById 
	* @Description: 根据id删除SysConfigIndexNews
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-5-18
	* @user by wangzx
	 */
	void deleteSysConfigIndexNewsById(Integer id);
	
	/**
	 * 
	* @Title: deleteSysConfigIndexNewsByIds 
	* @Description: 根据id批量删除SysConfigIndexNews
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-5-18
	* @user by wangzx
	 */
	void deleteSysConfigIndexNewsByIds(Integer[] ids);
	
	/**
	 * 
	* @Title: findSysConfigIndexNewsById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-5-18
	* @user by wangzx
	 */
	SysConfigIndexNews findSysConfigIndexNewsById(Integer id);
	
	/**
	 * 
	* @Title: findSysConfigIndexNewsByPage 
	* @Description: 分页查询
	* @return
	* @return List<SysConfigIndexNews>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-5-18
	* @user by wangzx
	 */
	List<SysConfigIndexNews> findSysConfigIndexNewsByPage(SysConfigIndexNews search);
}