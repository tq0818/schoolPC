package com.yuxin.wx.api.system;

import java.util.List;

import com.yuxin.wx.model.system.SysTaskLog;
/**
 * Service Interface:SysTaskLog
 * @author wang.zx
 * @date 2015-6-17
 */
public interface ISysTaskLogService  {
	/**
	 * 
	* @Title: saveSysTaskLog
	* @Description: 新增SysTaskLog
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-6-17
	* @user by wangzx
	 */
	void insert(SysTaskLog entity);
	
	/**
	 * 
	* @Title: batchSaveSysTaskLog 
	* @Description: 批量新增SysTaskLog
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-6-17
	* @user by wangzx
	 */
	void batchInsert(List<SysTaskLog> list);
	
	/**
	 * 
	* @Title: updateSysTaskLog 
	* @Description: 编辑SysTaskLog
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-6-17
	* @user by wangzx
	 */
	void update(SysTaskLog entity);
	
	/**
	 * 
	* @Title: deleteSysTaskLogById 
	* @Description: 根据id删除SysTaskLog
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-6-17
	* @user by wangzx
	 */
	void deleteSysTaskLogById(Integer id);
	
	/**
	 * 
	* @Title: deleteSysTaskLogByIds 
	* @Description: 根据id批量删除SysTaskLog
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-6-17
	* @user by wangzx
	 */
	void deleteSysTaskLogByIds(Integer[] ids);
	
	/**
	 * 
	* @Title: findSysTaskLogById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-6-17
	* @user by wangzx
	 */
	SysTaskLog findSysTaskLogById(Integer id);
	
	/**
	 * 
	* @Title: findSysTaskLogByPage 
	* @Description: 分页查询
	* @return
	* @return List<SysTaskLog>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-6-17
	* @user by wangzx
	 */
	List<SysTaskLog> findSysTaskLogByPage(SysTaskLog search);
}