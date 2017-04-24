package com.yuxin.wx.api.system;

import java.util.List;

import com.yuxin.wx.model.system.SysConfigServiceGroupFlag;
/**
 * Service Interface:SysConfigServiceGroupFlag
 * @author chopin
 * @date 2017-3-24
 */
public interface ISysConfigServiceGroupFlagService  {
	/**
	 * 
	* @Title: saveSysConfigServiceGroupFlag
	* @Description: 新增SysConfigServiceGroupFlag
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2017-3-24
	* @user by wangzx
	 */
	void insert(SysConfigServiceGroupFlag entity);
	
	/**
	 * 
	* @Title: batchSaveSysConfigServiceGroupFlag 
	* @Description: 批量新增SysConfigServiceGroupFlag
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2017-3-24
	* @user by wangzx
	 */
	void batchInsert(List<SysConfigServiceGroupFlag> list);
	
	/**
	 * 
	* @Title: updateSysConfigServiceGroupFlag 
	* @Description: 编辑SysConfigServiceGroupFlag
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2017-3-24
	* @user by wangzx
	 */
	void update(SysConfigServiceGroupFlag entity);
	
	/**
	 * 
	* @Title: deleteSysConfigServiceGroupFlagById 
	* @Description: 根据id删除SysConfigServiceGroupFlag
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2017-3-24
	* @user by wangzx
	 */
	void deleteSysConfigServiceGroupFlagById(Integer id);
	
	/**
	 * 
	* @Title: deleteSysConfigServiceGroupFlagByIds 
	* @Description: 根据id批量删除SysConfigServiceGroupFlag
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2017-3-24
	* @user by wangzx
	 */
	void deleteSysConfigServiceGroupFlagByIds(Integer[] ids);
	
	/**
	 * 
	* @Title: findSysConfigServiceGroupFlagById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2017-3-24
	* @user by wangzx
	 */
	SysConfigServiceGroupFlag findSysConfigServiceGroupFlagById(Integer id);
	
	/**
	 * 
	* @Title: findSysConfigServiceGroupFlagByPage 
	* @Description: 分页查询
	* @return
	* @return List<SysConfigServiceGroupFlag>    返回类型 
	* @throws 
	* @exception 
	* @date 2017-3-24
	* @user by wangzx
	 */
	List<SysConfigServiceGroupFlag> findSysConfigServiceGroupFlagByPage(SysConfigServiceGroupFlag search);

	List<SysConfigServiceGroupFlag> findAll();
}