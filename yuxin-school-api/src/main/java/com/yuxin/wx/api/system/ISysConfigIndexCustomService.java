package com.yuxin.wx.api.system;

import java.util.List;

import com.yuxin.wx.model.system.SysConfigIndexCustom;
/**
 * Service Interface:SysConfigIndexCustom
 * @author chopin
 * @date 2015-3-17
 */
public interface ISysConfigIndexCustomService  {
	/**
	 * 
	* @Title: saveSysConfigIndexCustom
	* @Description: 新增SysConfigIndexCustom
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-3-17
	* @user by wangzx
	 */
	void insert(SysConfigIndexCustom entity);
	
	/**
	 * 
	* @Title: batchSaveSysConfigIndexCustom 
	* @Description: 批量新增SysConfigIndexCustom
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-3-17
	* @user by wangzx
	 */
	void batchInsert(List<SysConfigIndexCustom> list);
	
	/**
	 * 
	* @Title: updateSysConfigIndexCustom 
	* @Description: 编辑SysConfigIndexCustom
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-3-17
	* @user by wangzx
	 */
	void update(SysConfigIndexCustom entity);
	
	/**
	 * 
	* @Title: deleteSysConfigIndexCustomById 
	* @Description: 根据id删除SysConfigIndexCustom
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-3-17
	* @user by wangzx
	 */
	void deleteSysConfigIndexCustomById(Integer id);
	
	/**
	 * 
	* @Title: deleteSysConfigIndexCustomByIds 
	* @Description: 根据id批量删除SysConfigIndexCustom
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-3-17
	* @user by wangzx
	 */
	void deleteSysConfigIndexCustomByIds(Integer[] ids);
	
	/**
	 * 
	* @Title: findSysConfigIndexCustomById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-3-17
	* @user by wangzx
	 */
	SysConfigIndexCustom findSysConfigIndexCustomById(Integer id);
	
	/**
	 * 
	* @Title: findSysConfigIndexCustomByPage 
	* @Description: 分页查询
	* @return
	* @return List<SysConfigIndexCustom>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-3-17
	* @user by wangzx
	 */
	List<SysConfigIndexCustom> findSysConfigIndexCustomByPage(SysConfigIndexCustom search);
	
	/**
	* @Title: findSysConfigIndexCustomByPage 
	* @Description: 查询全部
	* @return
	* @return List<SysConfigIndexCustom>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-3-17
	* @user by chopin
	 */
	List<SysConfigIndexCustom> findAll();
}