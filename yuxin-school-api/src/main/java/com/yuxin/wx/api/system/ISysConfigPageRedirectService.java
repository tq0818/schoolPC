package com.yuxin.wx.api.system;

import java.util.List;

import com.yuxin.wx.model.system.SysConfigPageRedirect;
/**
 * Service Interface:SysConfigPageRedirect
 * @author wang.zx
 * @date 2015-10-9
 */
public interface ISysConfigPageRedirectService  {
	/**
	 * 
	* @Title: saveSysConfigPageRedirect
	* @Description: 新增SysConfigPageRedirect
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-10-9
	* @user by wangzx
	 */
	void insert(SysConfigPageRedirect entity);
	
	/**
	 * 
	* @Title: batchSaveSysConfigPageRedirect 
	* @Description: 批量新增SysConfigPageRedirect
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-10-9
	* @user by wangzx
	 */
	void batchInsert(List<SysConfigPageRedirect> list);
	
	/**
	 * 
	* @Title: updateSysConfigPageRedirect 
	* @Description: 编辑SysConfigPageRedirect
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-10-9
	* @user by wangzx
	 */
	void update(SysConfigPageRedirect entity);
	
	/**
	 * 
	* @Title: deleteSysConfigPageRedirectById 
	* @Description: 根据id删除SysConfigPageRedirect
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-10-9
	* @user by wangzx
	 */
	void deleteSysConfigPageRedirectById(Integer id);
	
	/**
	 * 
	* @Title: deleteSysConfigPageRedirectByIds 
	* @Description: 根据id批量删除SysConfigPageRedirect
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-10-9
	* @user by wangzx
	 */
	void deleteSysConfigPageRedirectByIds(Integer[] ids);
	
	/**
	 * 
	* @Title: findSysConfigPageRedirectById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-10-9
	* @user by wangzx
	 */
	SysConfigPageRedirect findSysConfigPageRedirectById(Integer id);
	
	/**
	 * 
	* @Title: findSysConfigPageRedirectByPage 
	* @Description: 分页查询
	* @return
	* @return List<SysConfigPageRedirect>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-10-9
	* @user by wangzx
	 */
	List<SysConfigPageRedirect> findSysConfigPageRedirectByPage(SysConfigPageRedirect search);
	
	/**
	 * 
	 * Class Name: ISysConfigPageRedirectService.java
	 * @Description: 查询是否存在
	 * @author 周文斌
	 * @date 2015-10-9 下午12:45:33
	 * @version 1.0
	 * @param scpr
	 * @return
	 */
	SysConfigPageRedirect findPageRedirect(SysConfigPageRedirect scpr);
	
	List<SysConfigPageRedirect> findBySearch(SysConfigPageRedirect scpr);
	/**
	 * 
	* @Title: findSysConfigPageRedirectByPage 
	* @Description: 根据公司ID和模板ID 更新主题
	* @return
	* @return List<SysConfigPageRedirect>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-12-3
	* @user by chopin
	 */
	public void updateByTempleteId(SysConfigPageRedirect redirect);
}