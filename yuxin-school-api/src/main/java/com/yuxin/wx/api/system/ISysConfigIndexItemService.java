package com.yuxin.wx.api.system;

import java.util.List;

import com.yuxin.wx.model.system.SysConfigIndexItem;
/**
 * Service Interface:SysConfigIndexItem
 * @author chopin
 * @date 2015-3-17
 */
public interface ISysConfigIndexItemService  {
	/**
	 * 
	* @Title: saveSysConfigIndexItem
	* @Description: 新增SysConfigIndexItem
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-3-17
	* @user by wangzx
	 */
	void insert(SysConfigIndexItem entity);
	
	/**
	 * 
	* @Title: batchSaveSysConfigIndexItem 
	* @Description: 批量新增SysConfigIndexItem
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-3-17
	* @user by wangzx
	 */
	void batchInsert(List<SysConfigIndexItem> list);
	
	/**
	 * 
	* @Title: updateSysConfigIndexItem 
	* @Description: 编辑SysConfigIndexItem
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-3-17
	* @user by wangzx
	 */
	void update(SysConfigIndexItem entity);
	
	/**
	 * 
	* @Title: deleteSysConfigIndexItemById 
	* @Description: 根据id删除SysConfigIndexItem
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-3-17
	* @user by wangzx
	 */
	void deleteSysConfigIndexItemById(Integer id);
	
	/**
	 * 
	* @Title: deleteSysConfigIndexItemByIds 
	* @Description: 根据id批量删除SysConfigIndexItem
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-3-17
	* @user by wangzx
	 */
	void deleteSysConfigIndexItemByIds(Integer[] ids);
	
	/**
	 * 
	* @Title: findSysConfigIndexItemById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-3-17
	* @user by wangzx
	 */
	SysConfigIndexItem findSysConfigIndexItemById(Integer id);
	
	/**
	 * 
	* @Title: findSysConfigIndexItemByPage 
	* @Description: 查询全部数据
	* @return
	* @return List<SysConfigIndexItem>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-3-17
	* @user by chopin
	 */
	public List<SysConfigIndexItem> findAll();
}