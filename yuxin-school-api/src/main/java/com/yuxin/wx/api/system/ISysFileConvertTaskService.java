package com.yuxin.wx.api.system;

import java.util.List;

import com.yuxin.wx.model.system.SysFileConvertTask;
/**
 * Service Interface:SysFileConvertTask
 * @author wang.zx
 * @date 2016-10-31
 */
public interface ISysFileConvertTaskService  {
	/**
	 * 
	* @Title: saveSysFileConvertTask
	* @Description: 新增SysFileConvertTask
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-10-31
	* @user by wangzx
	 */
	void insert(SysFileConvertTask entity);
	
	/**
	 * 
	* @Title: batchSaveSysFileConvertTask 
	* @Description: 批量新增SysFileConvertTask
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-10-31
	* @user by wangzx
	 */
	void batchInsert(List<SysFileConvertTask> list);
	
	/**
	 * 
	* @Title: updateSysFileConvertTask 
	* @Description: 编辑SysFileConvertTask
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-10-31
	* @user by wangzx
	 */
	void update(SysFileConvertTask entity);
	
	/**
	 * 
	* @Title: deleteSysFileConvertTaskById 
	* @Description: 根据id删除SysFileConvertTask
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-10-31
	* @user by wangzx
	 */
	void deleteSysFileConvertTaskById(Integer id);
	
	/**
	 * 
	* @Title: deleteSysFileConvertTaskByIds 
	* @Description: 根据id批量删除SysFileConvertTask
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-10-31
	* @user by wangzx
	 */
	void deleteSysFileConvertTaskByIds(Integer[] ids);
	
	/**
	 * 
	* @Title: findSysFileConvertTaskById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-10-31
	* @user by wangzx
	 */
	SysFileConvertTask findSysFileConvertTaskById(Integer id);
	
	/**
	 * 
	* @Title: findSysFileConvertTaskByPage 
	* @Description: 分页查询
	* @return
	* @return List<SysFileConvertTask>    返回类型 
	* @throws 
	* @exception 
	* @date 2016-10-31
	* @user by wangzx
	 */
	List<SysFileConvertTask> findSysFileConvertTaskByPage(SysFileConvertTask search);
	
	/**
	 * 
	* @Title: findSysFileConvertTaskByPage 
	* @Description: 分页查询
	* @return
	* @return List<SysFileConvertTask>    返回类型 
	* @throws 
	* @exception 
	* @date 2016-10-31
	* @user by wangzx
	 */
	List<SysFileConvertTask> pageThreeHundred(Integer version);
	
	/**
	 * 
	* @Title: updateSysFileConvertTask 
	* @Description: 编辑SysFileConvertTask
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-10-31
	* @user by wangzx
	 */
	Integer updateReturn(SysFileConvertTask entity);
}