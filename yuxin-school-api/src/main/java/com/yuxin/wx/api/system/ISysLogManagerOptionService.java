package com.yuxin.wx.api.system;

import java.util.List;

import com.yuxin.wx.model.system.SysLogManagerOption;
/**
 * Service Interface:SysLogManagerOption
 * @author chopin
 * @date 2017-3-10
 */
public interface ISysLogManagerOptionService  {
	/**
	 * 
	* @Title: saveSysLogManagerOption
	* @Description: 新增SysLogManagerOption
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2017-3-10
	* @user by wangzx
	 */
	void insert(SysLogManagerOption entity);
	
	/**
	 * 
	* @Title: batchSaveSysLogManagerOption 
	* @Description: 批量新增SysLogManagerOption
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2017-3-10
	* @user by wangzx
	 */
	void batchInsert(List<SysLogManagerOption> list);
	
	/**
	 * 
	* @Title: updateSysLogManagerOption 
	* @Description: 编辑SysLogManagerOption
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2017-3-10
	* @user by wangzx
	 */
	void update(SysLogManagerOption entity);
	
	/**
	 * 
	* @Title: deleteSysLogManagerOptionById 
	* @Description: 根据id删除SysLogManagerOption
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2017-3-10
	* @user by wangzx
	 */
	void deleteSysLogManagerOptionById(Integer id);
	
	/**
	 * 
	* @Title: deleteSysLogManagerOptionByIds 
	* @Description: 根据id批量删除SysLogManagerOption
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2017-3-10
	* @user by wangzx
	 */
	void deleteSysLogManagerOptionByIds(Integer[] ids);
	
	/**
	 * 
	* @Title: findSysLogManagerOptionById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2017-3-10
	* @user by wangzx
	 */
	SysLogManagerOption findSysLogManagerOptionById(Integer id);
	
	/**
	 * 
	* @Title: findSysLogManagerOptionByPage 
	* @Description: 分页查询
	* @return
	* @return List<SysLogManagerOption>    返回类型 
	* @throws 
	* @exception 
	* @date 2017-3-10
	* @user by wangzx
	 */
	List<SysLogManagerOption> findSysLogManagerOptionByPage(SysLogManagerOption search);
}