package com.yuxin.wx.api.system;


import java.util.List;

import com.yuxin.wx.model.system.SysLogInvitLog;
/**
 * Service Interface:SysLogInvitLog
 * @author chopin
 * @date 2016-8-12
 */
public interface ISysLogInvitLogService  {
	/**
	 * 
	* @Title: saveSysLogInvitLog
	* @Description: 新增SysLogInvitLog
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-8-12
	* @user by wangzx
	 */
	void insert(SysLogInvitLog entity);
	
	/**
	 * 
	* @Title: batchSaveSysLogInvitLog 
	* @Description: 批量新增SysLogInvitLog
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-8-12
	* @user by wangzx
	 */
	void batchInsert(List<SysLogInvitLog> list);
	
	/**
	 * 
	* @Title: updateSysLogInvitLog 
	* @Description: 编辑SysLogInvitLog
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-8-12
	* @user by wangzx
	 */
	void update(SysLogInvitLog entity);
	
	/**
	 * 
	* @Title: deleteSysLogInvitLogById 
	* @Description: 根据id删除SysLogInvitLog
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-8-12
	* @user by wangzx
	 */
	void deleteSysLogInvitLogById(Integer id);
	
	/**
	 * 
	* @Title: deleteSysLogInvitLogByIds 
	* @Description: 根据id批量删除SysLogInvitLog
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-8-12
	* @user by wangzx
	 */
	void deleteSysLogInvitLogByIds(Integer[] ids);
	
	/**
	 * 
	* @Title: findSysLogInvitLogById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-8-12
	* @user by wangzx
	 */
	SysLogInvitLog findSysLogInvitLogById(Integer id);
	
	/**
	 * 
	* @Title: findSysLogInvitLogByPage 
	* @Description: 分页查询
	* @return
	* @return List<SysLogInvitLog>    返回类型 
	* @throws 
	* @exception 
	* @date 2016-8-12
	* @user by wangzx
	 */
	List<SysLogInvitLog> findSysLogInvitLogByPage(SysLogInvitLog search);
}