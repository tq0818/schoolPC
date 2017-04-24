package com.yuxin.wx.api.system;

import java.util.List;

import com.yuxin.wx.model.system.SysLogManagerOptionDict;
/**
 * Service Interface:SysLogManagerOptionDict
 * @author chopin
 * @date 2017-3-13
 */
public interface ISysLogManagerOptionDictService  {
	/**
	 * 
	* @Title: saveSysLogManagerOptionDict
	* @Description: 新增SysLogManagerOptionDict
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2017-3-13
	* @user by wangzx
	 */
	void insert(SysLogManagerOptionDict entity);
	
	/**
	 * 
	* @Title: batchSaveSysLogManagerOptionDict 
	* @Description: 批量新增SysLogManagerOptionDict
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2017-3-13
	* @user by wangzx
	 */
	void batchInsert(List<SysLogManagerOptionDict> list);
	
	/**
	 * 
	* @Title: updateSysLogManagerOptionDict 
	* @Description: 编辑SysLogManagerOptionDict
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2017-3-13
	* @user by wangzx
	 */
	void update(SysLogManagerOptionDict entity);
	
	/**
	 * 
	* @Title: deleteSysLogManagerOptionDictById 
	* @Description: 根据id删除SysLogManagerOptionDict
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2017-3-13
	* @user by wangzx
	 */
	void deleteSysLogManagerOptionDictById(Integer id);
	
	/**
	 * 
	* @Title: deleteSysLogManagerOptionDictByIds 
	* @Description: 根据id批量删除SysLogManagerOptionDict
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2017-3-13
	* @user by wangzx
	 */
	void deleteSysLogManagerOptionDictByIds(Integer[] ids);
	
	/**
	 * 
	* @Title: findSysLogManagerOptionDictById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2017-3-13
	* @user by wangzx
	 */
	SysLogManagerOptionDict findSysLogManagerOptionDictById(Integer id);
	
	/**
	 * 
	* @Title: findSysLogManagerOptionDictByPage 
	* @Description: 分页查询
	* @return
	* @return List<SysLogManagerOptionDict>    返回类型 
	* @throws 
	* @exception 
	* @date 2017-3-13
	* @user by wangzx
	 */
	List<SysLogManagerOptionDict> findSysLogManagerOptionDictByPage(SysLogManagerOptionDict search);
	
	String queryOperationByAction(String action);
	
	SysLogManagerOptionDict queryByAction(String action);
}