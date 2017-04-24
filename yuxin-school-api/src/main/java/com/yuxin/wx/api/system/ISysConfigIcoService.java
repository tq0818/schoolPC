package com.yuxin.wx.api.system;

import java.util.List;

import com.yuxin.wx.model.system.SysConfigIco;
/**
 * Service Interface:SysConfigIco
 * @author chopin
 * @date 2015-12-4
 */
public interface ISysConfigIcoService  {
	/**
	 * 
	* @Title: saveSysConfigIco
	* @Description: 新增SysConfigIco
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-12-4
	* @user by wangzx
	 */
	void insert(SysConfigIco entity);
	
	/**
	 * 
	* @Title: batchSaveSysConfigIco 
	* @Description: 批量新增SysConfigIco
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-12-4
	* @user by wangzx
	 */
	void batchInsert(List<SysConfigIco> list);
	
	/**
	 * 
	* @Title: updateSysConfigIco 
	* @Description: 编辑SysConfigIco
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-12-4
	* @user by wangzx
	 */
	void update(SysConfigIco entity);
	
	/**
	 * 
	* @Title: deleteSysConfigIcoById 
	* @Description: 根据id删除SysConfigIco
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-12-4
	* @user by wangzx
	 */
	void deleteSysConfigIcoById(Integer id);
	
	/**
	 * 
	* @Title: deleteSysConfigIcoByIds 
	* @Description: 根据id批量删除SysConfigIco
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-12-4
	* @user by wangzx
	 */
	void deleteSysConfigIcoByIds(Integer[] ids);
	
	/**
	 * 
	* @Title: findSysConfigIcoById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-12-4
	* @user by wangzx
	 */
	SysConfigIco findSysConfigIcoById(Integer id);
	
	/**
	 * 
	* @Title: findSysConfigIcoByPage 
	* @Description: 分页查询
	* @return
	* @return List<SysConfigIco>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-12-4
	* @user by wangzx
	 */
	List<SysConfigIco> findSysConfigIcoByPage(SysConfigIco search);
	
	/**
	 * 
	 * Class Name: ISysConfigIcoService.java
	 * @Description: 查询公司ico文件
	 * @author zhang.zx
	 * @date 2015年12月4日 上午11:36:12
	 * @modifier
	 * @modify-date 2015年12月4日 上午11:36:12
	 * @version 1.0
	 * @param search
	 * @return
	 */
	List<SysConfigIco> queryAllIcoLists(SysConfigIco search);
	
	/**
	 * 
	 * Class Name: ISysConfigIcoService.java
	 * @Description: 查询当前公司开启的ico文件
	 * @author zhang.zx
	 * @date 2015年12月4日 上午11:41:04
	 * @modifier
	 * @modify-date 2015年12月4日 上午11:41:04
	 * @version 1.0
	 * @param companyId
	 * @return
	 */
	List<SysConfigIco> queryUseIco(Integer companyId);
}