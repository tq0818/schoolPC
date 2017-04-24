package com.yuxin.wx.api.system;

import java.util.List;

import com.yuxin.wx.model.system.SysLiveReverseAuth;
/**
 * Service Interface:SysLiveReverseAuth
 * @author wang.zx
 * @date 2015-11-6
 */
public interface ISysLiveReverseAuthService  {
	/**
	 * 
	* @Title: saveSysLiveReverseAuth
	* @Description: 新增SysLiveReverseAuth
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-11-6
	* @user by wangzx
	 */
	void insert(SysLiveReverseAuth entity);
	
	/**
	 * 
	* @Title: batchSaveSysLiveReverseAuth 
	* @Description: 批量新增SysLiveReverseAuth
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-11-6
	* @user by wangzx
	 */
	void batchInsert(List<SysLiveReverseAuth> list);
	
	/**
	 * 
	* @Title: updateSysLiveReverseAuth 
	* @Description: 编辑SysLiveReverseAuth
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-11-6
	* @user by wangzx
	 */
	void update(SysLiveReverseAuth entity);
	
	/**
	 * 
	* @Title: deleteSysLiveReverseAuthById 
	* @Description: 根据id删除SysLiveReverseAuth
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-11-6
	* @user by wangzx
	 */
	void deleteSysLiveReverseAuthById(Integer id);
	
	/**
	 * 
	* @Title: deleteSysLiveReverseAuthByIds 
	* @Description: 根据id批量删除SysLiveReverseAuth
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-11-6
	* @user by wangzx
	 */
	void deleteSysLiveReverseAuthByIds(Integer[] ids);
	
	/**
	 * 
	* @Title: findSysLiveReverseAuthById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-11-6
	* @user by wangzx
	 */
	SysLiveReverseAuth findSysLiveReverseAuthById(Integer id);
	
	/**
	 * 
	* @Title: findSysLiveReverseAuthByPage 
	* @Description: 分页查询
	* @return
	* @return List<SysLiveReverseAuth>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-11-6
	* @user by wangzx
	 */
	List<SysLiveReverseAuth> findSysLiveReverseAuthByPage(SysLiveReverseAuth search);

}