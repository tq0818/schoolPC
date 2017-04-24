package com.yuxin.wx.api.system;

import java.util.List;

import com.yuxin.wx.model.system.SysConfigService;
import com.yuxin.wx.model.system.SysConfigServiceGroup;
/**
 * Service Interface:SysConfigServiceGroup
 * @author chopin
 * @date 2015-8-12
 */
public interface ISysConfigServiceGroupService  {
	/**
	 * 
	* @Title: saveSysConfigServiceGroup
	* @Description: 新增SysConfigServiceGroup
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-8-12
	* @user by wangzx
	 */
	void insert(SysConfigServiceGroup entity);
	
	/**
	 * 
	* @Title: batchSaveSysConfigServiceGroup 
	* @Description: 批量新增SysConfigServiceGroup
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-8-12
	* @user by wangzx
	 */
	void batchInsert(List<SysConfigServiceGroup> list);
	
	/**
	 * 
	* @Title: updateSysConfigServiceGroup 
	* @Description: 编辑SysConfigServiceGroup
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-8-12
	* @user by wangzx
	 */
	void update(SysConfigServiceGroup entity);
	
	/**
	 * 
	* @Title: deleteSysConfigServiceGroupById 
	* @Description: 根据id删除SysConfigServiceGroup
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-8-12
	* @user by wangzx
	 */
	void deleteSysConfigServiceGroupById(Integer id);
	
	/**
	 * 
	* @Title: deleteSysConfigServiceGroupByIds 
	* @Description: 根据id批量删除SysConfigServiceGroup
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-8-12
	* @user by wangzx
	 */
	void deleteSysConfigServiceGroupByIds(Integer[] ids);
	
	/**
	 * 
	* @Title: findSysConfigServiceGroupById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-8-12
	* @user by wangzx
	 */
	SysConfigServiceGroup findSysConfigServiceGroupById(Integer id);
	
	/**
	 * 
	* @Title: findSysConfigServiceGroupByPage 
	* @Description: 分页查询
	* @return
	* @return List<SysConfigServiceGroup>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-8-12
	* @user by wangzx
	 */
	List<SysConfigServiceGroup> findSysConfigServiceGroupByPage(SysConfigServiceGroup search);
	
	List<SysConfigServiceGroup> findByCode(String code);

}