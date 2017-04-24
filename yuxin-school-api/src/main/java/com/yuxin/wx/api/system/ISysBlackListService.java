package com.yuxin.wx.api.system;

import java.util.List;

import com.yuxin.wx.model.system.SysBlackList;
/**
 * Service Interface:SysBlackList
 * @author chopin
 * @date 2015-9-15
 */
public interface ISysBlackListService  {
	/**
	 * 
	* @Title: saveSysBlackList
	* @Description: 新增SysBlackList
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-9-15
	* @user by wangzx
	 */
	void insert(SysBlackList entity);
	
	/**
	 * 
	* @Title: batchSaveSysBlackList 
	* @Description: 批量新增SysBlackList
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-9-15
	* @user by wangzx
	 */
	void batchInsert(List<SysBlackList> list);
	
	/**
	 * 
	* @Title: updateSysBlackList 
	* @Description: 编辑SysBlackList
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-9-15
	* @user by wangzx
	 */
	void update(SysBlackList entity);
	
	/**
	 * 
	* @Title: deleteSysBlackListById 
	* @Description: 根据id删除SysBlackList
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-9-15
	* @user by wangzx
	 */
	void deleteSysBlackListById(Integer id);
	
	/**
	 * 
	* @Title: deleteSysBlackListByIds 
	* @Description: 根据id批量删除SysBlackList
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-9-15
	* @user by wangzx
	 */
	void deleteSysBlackListByIds(Integer[] ids);
	
	/**
	 * 
	* @Title: findSysBlackListById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-9-15
	* @user by wangzx
	 */
	SysBlackList findSysBlackListById(Integer id);
	
	/**
	 * 
	* @Title: findSysBlackListByPage 
	* @Description: 分页查询
	* @return
	* @return List<SysBlackList>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-9-15
	* @user by wangzx
	 */
	List<SysBlackList> findSysBlackListByPage(SysBlackList search);
}