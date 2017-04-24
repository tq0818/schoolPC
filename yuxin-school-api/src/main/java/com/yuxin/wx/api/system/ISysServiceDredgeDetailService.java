package com.yuxin.wx.api.system;

import java.util.List;

import com.yuxin.wx.model.system.SysServiceDredgeDetail;
/**
 * Service Interface:SysServiceDredgeDetail
 * @author wang.zx
 * @date 2016-12-12
 */
public interface ISysServiceDredgeDetailService  {
	/**
	 * 
	* @Title: saveSysServiceDredgeDetail
	* @Description: 新增SysServiceDredgeDetail
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-12-12
	* @user by wangzx
	 */
	void insert(SysServiceDredgeDetail entity);
	
	/**
	 * 
	* @Title: batchSaveSysServiceDredgeDetail 
	* @Description: 批量新增SysServiceDredgeDetail
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-12-12
	* @user by wangzx
	 */
	void batchInsert(List<SysServiceDredgeDetail> list);
	
	/**
	 * 
	* @Title: updateSysServiceDredgeDetail 
	* @Description: 编辑SysServiceDredgeDetail
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-12-12
	* @user by wangzx
	 */
	void update(SysServiceDredgeDetail entity);
	
	/**
	 * 
	* @Title: deleteSysServiceDredgeDetailById 
	* @Description: 根据id删除SysServiceDredgeDetail
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-12-12
	* @user by wangzx
	 */
	void deleteSysServiceDredgeDetailById(Integer id);
	
	/**
	 * 
	* @Title: deleteSysServiceDredgeDetailByIds 
	* @Description: 根据id批量删除SysServiceDredgeDetail
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-12-12
	* @user by wangzx
	 */
	void deleteSysServiceDredgeDetailByIds(Integer[] ids);
	
	/**
	 * 
	* @Title: findSysServiceDredgeDetailById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-12-12
	* @user by wangzx
	 */
	SysServiceDredgeDetail findSysServiceDredgeDetailById(Integer id);
	
	/**
	 * 
	* @Title: findSysServiceDredgeDetailByPage 
	* @Description: 分页查询
	* @return
	* @return List<SysServiceDredgeDetail>    返回类型 
	* @throws 
	* @exception 
	* @date 2016-12-12
	* @user by wangzx
	 */
	List<SysServiceDredgeDetail> findSysServiceDredgeDetailByPage(SysServiceDredgeDetail search);
}