package com.yuxin.wx.api.system;

import java.util.List;

import com.yuxin.wx.model.system.SysConfigHelp;
/**
 * Service Interface:SysConfigHelp
 * @author chopin
 * @date 2015-7-14
 */
public interface ISysConfigHelpService  {
	/**
	 * 
	* @Title: saveSysConfigHelp
	* @Description: 新增SysConfigHelp
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-7-14
	* @user by wangzx
	 */
	void insert(SysConfigHelp entity);
	
	/**
	 * 
	* @Title: batchSaveSysConfigHelp 
	* @Description: 批量新增SysConfigHelp
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-7-14
	* @user by wangzx
	 */
	void batchInsert(List<SysConfigHelp> list);
	
	/**
	 * 
	* @Title: updateSysConfigHelp 
	* @Description: 编辑SysConfigHelp
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-7-14
	* @user by wangzx
	 */
	void update(SysConfigHelp entity);
	
	/**
	 * 
	* @Title: deleteSysConfigHelpById 
	* @Description: 根据id删除SysConfigHelp
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-7-14
	* @user by wangzx
	 */
	void deleteSysConfigHelpById(Integer id);
	
	/**
	 * 
	* @Title: deleteSysConfigHelpByIds 
	* @Description: 根据id批量删除SysConfigHelp
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-7-14
	* @user by wangzx
	 */
	void deleteSysConfigHelpByIds(Integer[] ids);
	
	/**
	 * 
	* @Title: findSysConfigHelpById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-7-14
	* @user by wangzx
	 */
	SysConfigHelp findSysConfigHelpById(Integer id);
	
	/**
	 * 
	* @Title: findSysConfigHelpByPage 
	* @Description: 分页查询
	* @return
	* @return List<SysConfigHelp>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-7-14
	* @user by wangzx
	 */
	List<SysConfigHelp> findSysConfigHelpByPage(SysConfigHelp search);
	
	/**
	 * 
	* @Title: findSysConfigHelpByPage 
	* @Description: 根据当前路径查找对应帮助
	* @return
	* @return List<SysConfigHelp>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-7-14
	* @user by wangzx
	 */
	SysConfigHelp findByLocation(String location);
}