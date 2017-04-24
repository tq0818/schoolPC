package com.yuxin.wx.api.system;

import java.util.List;

import com.yuxin.wx.model.system.SysConfigSeo;
/**
 * Service Interface:SysConfigSeo
 * @author chopin
 * @date 2015-12-3
 */
public interface ISysConfigSeoService  {
	/**
	 * 
	* @Title: saveSysConfigSeo
	* @Description: 新增SysConfigSeo
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-12-3
	* @user by wangzx
	 */
	void insert(SysConfigSeo entity);
	
	/**
	 * 
	* @Title: batchSaveSysConfigSeo 
	* @Description: 批量新增SysConfigSeo
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-12-3
	* @user by wangzx
	 */
	void batchInsert(List<SysConfigSeo> list);
	
	/**
	 * 
	* @Title: updateSysConfigSeo 
	* @Description: 编辑SysConfigSeo
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-12-3
	* @user by wangzx
	 */
	void update(SysConfigSeo entity);
	
	/**
	 * 
	* @Title: deleteSysConfigSeoById 
	* @Description: 根据id删除SysConfigSeo
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-12-3
	* @user by wangzx
	 */
	void deleteSysConfigSeoById(Integer id);
	
	/**
	 * 
	* @Title: deleteSysConfigSeoByIds 
	* @Description: 根据id批量删除SysConfigSeo
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-12-3
	* @user by wangzx
	 */
	void deleteSysConfigSeoByIds(Integer[] ids);
	
	/**
	 * 
	* @Title: findSysConfigSeoById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-12-3
	* @user by wangzx
	 */
	SysConfigSeo findSysConfigSeoById(Integer id);
	
	/**
	 * 
	* @Title: findSysConfigSeoByPage 
	* @Description: 分页查询
	* @return
	* @return List<SysConfigSeo>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-12-3
	* @user by wangzx
	 */
	List<SysConfigSeo> findSysConfigSeoByPage(SysConfigSeo search);
	
	/**
	 * 
	 * Class Name: ISysConfigSeoService.java
	 * @Description: 查询seo相关内容
	 * @author zhang.zx
	 * @date 2015年12月3日 下午4:32:40
	 * @modifier
	 * @modify-date 2015年12月3日 下午4:32:40
	 * @version 1.0
	 * @param search
	 * @return
	 */
	List<SysConfigSeo> queryAllSeoContents(SysConfigSeo search);
}