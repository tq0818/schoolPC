package com.yuxin.wx.api.system;

import java.util.List;

import com.yuxin.wx.model.system.SysLogCouponUse;
/**
 * Service Interface:SysLogCouponUse
 * @author chopin
 * @date 2016-6-28
 */
public interface ISysLogCouponUseService  {
	/**
	 * 
	* @Title: saveSysLogCouponUse
	* @Description: 新增SysLogCouponUse
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-6-28
	* @user by wangzx
	 */
	void insert(SysLogCouponUse entity);
	
	/**
	 * 
	* @Title: batchSaveSysLogCouponUse 
	* @Description: 批量新增SysLogCouponUse
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-6-28
	* @user by wangzx
	 */
	void batchInsert(List<SysLogCouponUse> list);
	
	/**
	 * 
	* @Title: updateSysLogCouponUse 
	* @Description: 编辑SysLogCouponUse
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-6-28
	* @user by wangzx
	 */
	void update(SysLogCouponUse entity);
	
	/**
	 * 
	* @Title: deleteSysLogCouponUseById 
	* @Description: 根据id删除SysLogCouponUse
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-6-28
	* @user by wangzx
	 */
	void deleteSysLogCouponUseById(Integer id);
	
	/**
	 * 
	* @Title: deleteSysLogCouponUseByIds 
	* @Description: 根据id批量删除SysLogCouponUse
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-6-28
	* @user by wangzx
	 */
	void deleteSysLogCouponUseByIds(Integer[] ids);
	
	/**
	 * 
	* @Title: findSysLogCouponUseById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-6-28
	* @user by wangzx
	 */
	SysLogCouponUse findSysLogCouponUseById(Integer id);
	
	/**
	 * 
	* @Title: findSysLogCouponUseByPage 
	* @Description: 分页查询
	* @return
	* @return List<SysLogCouponUse>    返回类型 
	* @throws 
	* @exception 
	* @date 2016-6-28
	* @user by wangzx
	 */
	List<SysLogCouponUse> findSysLogCouponUseByPage(SysLogCouponUse search);
}