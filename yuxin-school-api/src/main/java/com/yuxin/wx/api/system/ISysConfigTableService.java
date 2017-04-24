package com.yuxin.wx.api.system;

import java.util.List;
import com.yuxin.wx.model.system.SysConfigTable;
/**
 * Service Interface:SysConfigTable
 * @author wang.zx
 * @date 2014-12-5
 */
public interface ISysConfigTableService  {
	/**
	 * 
	* @Title: saveSysConfigTable
	* @Description: 新增SysConfigTable
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	void insert(SysConfigTable sysConfigTable);
	
	/**
	 * 
	* @Title: batchSaveSysConfigTable 
	* @Description: 批量新增SysConfigTable
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	void batchInsert(List<SysConfigTable> sysConfigTable);
	
	/**
	 * 
	* @Title: updateSysConfigTable 
	* @Description: 编辑SysConfigTable
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	void update(SysConfigTable sysConfigTable);
	
	/**
	 * 
	* @Title: deleteSysConfigTableById 
	* @Description: 根据id删除SysConfigTable
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	void deleteSysConfigTableByTableNameAndColumn( String tableName, String columnName);
	
	/**
	 * 
	* @Title: deleteSysConfigTableByIds 
	* @Description: 根据id批量删除SysConfigTable
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	void deleteSysConfigTableByIds(Integer[] ids);
	
	/**
	 * 
	* @Title: findSysConfigTableById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	SysConfigTable findSysConfigTableById(Integer id);
	
	/**
	 * 
	* @Title: findSysConfigTableByPage 
	* @Description: 分页查询
	* @return
	* @return List<SysConfigTable>    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	List<SysConfigTable> findSysConfigTableByPage(SysConfigTable search);
	
	/**
	 * Class Name: ISysConfigTableService.java
	 * @Description:(查询所有的是否必填字段配置)
	 * @author wang.zx 
	 * @date 2014-12-9 下午3:39:43
	 * @version 1.0
	 * @return
	 */
	List<SysConfigTable> queryAll();
	
	/**
	 * 
	* @Title: deleteSysConfigTableByTableNameAndColumn 
	* @Description: 根据tableName, columnName获取SysConfigTable
	* @param id
	* @return SysConfigTable 返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	SysConfigTable findSysConfigTableByTableNameAndColumn(String tableName, String columnName);
	
}