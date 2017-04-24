package com.yuxin.wx.api.system;

import java.util.List;

import com.yuxin.wx.model.system.SysConfigIndexModel;
/**
 * Service Interface:SysConfigIndexModel
 * @author chopin
 * @date 2015-3-17
 */
public interface ISysConfigIndexModelService  {
	/**
	 * 
	* @Title: saveSysConfigIndexModel
	* @Description: 新增SysConfigIndexModel
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-3-17
	* @user by wangzx
	 */
	void insert(SysConfigIndexModel entity);
	
	/**
	 * 
	* @Title: batchSaveSysConfigIndexModel 
	* @Description: 批量新增SysConfigIndexModel
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-3-17
	* @user by wangzx
	 */
	void batchInsert(List<SysConfigIndexModel> list);
	
	/**
	 * 
	* @Title: updateSysConfigIndexModel 
	* @Description: 编辑SysConfigIndexModel
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-3-17
	* @user by wangzx
	 */
	void update(SysConfigIndexModel entity);
	
	/**
	 * 
	* @Title: deleteSysConfigIndexModelById 
	* @Description: 根据id删除SysConfigIndexModel
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-3-17
	* @user by wangzx
	 */
	void deleteSysConfigIndexModelById(Integer id);
	
	/**
	 * 
	* @Title: deleteSysConfigIndexModelByIds 
	* @Description: 根据id批量删除SysConfigIndexModel
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-3-17
	* @user by wangzx
	 */
	void deleteSysConfigIndexModelByIds(Integer[] ids);
	
	/**
	 * 
	* @Title: findSysConfigIndexModelById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-3-17
	* @user by wangzx
	 */
	SysConfigIndexModel findSysConfigIndexModelById(Integer id);
	
	/**
	 * 
	* @Title: findSysConfigIndexModelByPage 
	* @Description: 分页查询
	* @return
	* @return List<SysConfigIndexModel>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-3-17
	* @user by wangzx
	 */
	List<SysConfigIndexModel> findSysConfigIndexModelByPage(SysConfigIndexModel search);
	/**
	 * 
	* @Title: findSysConfigIndexModelByPage 
	* @Description: 查询全部
	* @return
	* @return List<SysConfigIndexModel>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-3-17
	* @user by chopin
	 */
	List<SysConfigIndexModel> findAll();
}