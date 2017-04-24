package com.yuxin.wx.api.system;

import java.util.List;

import com.yuxin.wx.model.system.SysRegisterRequestLog;
/**
 * Service Interface:SysRegisterRequestLog
 * @author chopin
 * @date 2015-11-3
 */
public interface ISysRegisterRequestLogService  {
	/**
	 * 
	* @Title: saveSysRegisterRequestLog
	* @Description: 新增SysRegisterRequestLog
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-11-3
	* @user by wangzx
	 */
	void insert(SysRegisterRequestLog entity);
	
	/**
	 * 
	* @Title: batchSaveSysRegisterRequestLog 
	* @Description: 批量新增SysRegisterRequestLog
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-11-3
	* @user by wangzx
	 */
	void batchInsert(List<SysRegisterRequestLog> list);
	
	/**
	 * 
	* @Title: updateSysRegisterRequestLog 
	* @Description: 编辑SysRegisterRequestLog
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-11-3
	* @user by wangzx
	 */
	void update(SysRegisterRequestLog entity);
	
	/**
	 * 
	* @Title: deleteSysRegisterRequestLogById 
	* @Description: 根据id删除SysRegisterRequestLog
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-11-3
	* @user by wangzx
	 */
	void deleteSysRegisterRequestLogById(Integer id);
	
	/**
	 * 
	* @Title: deleteSysRegisterRequestLogByIds 
	* @Description: 根据id批量删除SysRegisterRequestLog
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-11-3
	* @user by wangzx
	 */
	void deleteSysRegisterRequestLogByIds(Integer[] ids);
	
	/**
	 * 
	* @Title: findSysRegisterRequestLogById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-11-3
	* @user by wangzx
	 */
	SysRegisterRequestLog findSysRegisterRequestLogById(Integer id);
	
	/**
	 * 
	* @Title: findSysRegisterRequestLogByPage 
	* @Description: 分页查询
	* @return
	* @return List<SysRegisterRequestLog>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-11-3
	* @user by wangzx
	 */
	List<SysRegisterRequestLog> findSysRegisterRequestLogByPage(SysRegisterRequestLog search);
	/**
	 * 
	* @Title: updateSysRegisterRequestLog 
	* @Description: 编辑SysRegisterRequestLog
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-11-3
	* @user by chopin
	 */
	public void updateByUuid(SysRegisterRequestLog entity);
}