package com.yuxin.wx.api.system;

import java.util.List;

import com.yuxin.wx.model.system.SysConfigIndexClasstype;
/**
 * Service Interface:SysConfigIndexClasstype
 * @author chopin
 * @date 2015-3-17
 */
public interface ISysConfigIndexClasstypeService  {
	/**
	 * 
	* @Title: saveSysConfigIndexClasstype
	* @Description: 新增SysConfigIndexClasstype
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-3-17
	* @user by wangzx
	 */
	void insert(SysConfigIndexClasstype entity);
	
	/**
	 * 
	* @Title: batchSaveSysConfigIndexClasstype 
	* @Description: 批量新增SysConfigIndexClasstype
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-3-17
	* @user by wangzx
	 */
	void batchInsert(List<SysConfigIndexClasstype> list);
	
	/**
	 * 
	* @Title: updateSysConfigIndexClasstype 
	* @Description: 编辑SysConfigIndexClasstype
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-3-17
	* @user by wangzx
	 */
	void update(SysConfigIndexClasstype entity);
	
	/**
	 * 
	* @Title: deleteSysConfigIndexClasstypeById 
	* @Description: 根据id删除SysConfigIndexClasstype
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-3-17
	* @user by wangzx
	 */
	void deleteSysConfigIndexClasstypeById(Integer id);
	
	/**
	 * 
	* @Title: deleteSysConfigIndexClasstypeByIds 
	* @Description: 根据id批量删除SysConfigIndexClasstype
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-3-17
	* @user by wangzx
	 */
	void deleteSysConfigIndexClasstypeByIds(Integer[] ids);
	
	/**
	 * 
	* @Title: findSysConfigIndexClasstypeById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-3-17
	* @user by wangzx
	 */
	SysConfigIndexClasstype findSysConfigIndexClasstypeById(Integer id);
	
	/**
	 * 
	* @Title: findSysConfigIndexClasstypeByPage 
	* @Description: 分页查询
	* @return
	* @return List<SysConfigIndexClasstype>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-3-17
	* @user by wangzx
	 */
	List<SysConfigIndexClasstype> findSysConfigIndexClasstypeByPage(SysConfigIndexClasstype search);
	/**
	 * 
	* @Title: findAll 
	* @Description: 查询全部
	* @return
	* @return List<SysConfigIndexClasstype>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-3-17
	* @user by chopin
	 */
	List<SysConfigIndexClasstype> findAll();
}