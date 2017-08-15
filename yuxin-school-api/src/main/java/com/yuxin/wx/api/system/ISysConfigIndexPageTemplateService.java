package com.yuxin.wx.api.system;

import java.util.List;

import com.yuxin.wx.model.system.SysConfigIndexPageTemplate;
/**
 * Service Interface:SysConfigIndexPageTemplate
 * @author chopin
 * @date 2017-3-24
 */
public interface ISysConfigIndexPageTemplateService  {
	/**
	 * 
	* @Title: saveSysConfigIndexPageTemplate
	* @Description: 新增SysConfigIndexPageTemplate
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2017-3-24
	* @user by wangzx
	 */
	void insert(SysConfigIndexPageTemplate entity);
	
	/**
	 * 
	* @Title: batchSaveSysConfigIndexPageTemplate 
	* @Description: 批量新增SysConfigIndexPageTemplate
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2017-3-24
	* @user by wangzx
	 */
	void batchInsert(List<SysConfigIndexPageTemplate> list);
	
	/**
	 * 
	* @Title: updateSysConfigIndexPageTemplate 
	* @Description: 编辑SysConfigIndexPageTemplate
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2017-3-24
	* @user by wangzx
	 */
	void update(SysConfigIndexPageTemplate entity);
	
	/**
	 * 
	* @Title: deleteSysConfigIndexPageTemplateById 
	* @Description: 根据id删除SysConfigIndexPageTemplate
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2017-3-24
	* @user by wangzx
	 */
	void deleteSysConfigIndexPageTemplateById(Integer id);
	
	/**
	 * 
	* @Title: deleteSysConfigIndexPageTemplateByIds 
	* @Description: 根据id批量删除SysConfigIndexPageTemplate
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2017-3-24
	* @user by wangzx
	 */
	void deleteSysConfigIndexPageTemplateByIds(Integer[] ids);
	
	/**
	 * 
	* @Title: findSysConfigIndexPageTemplateById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2017-3-24
	* @user by wangzx
	 */
	SysConfigIndexPageTemplate findSysConfigIndexPageTemplateById(Integer id);
	
	/**
	 * 
	* @Title: findSysConfigIndexPageTemplateByPage 
	* @Description: 分页查询
	* @return
	* @return List<SysConfigIndexPageTemplate>    返回类型 
	* @throws 
	* @exception 
	* @date 2017-3-24
	* @user by wangzx
	 */
	List<SysConfigIndexPageTemplate> findSysConfigIndexPageTemplateByPage(SysConfigIndexPageTemplate search);

	List<SysConfigIndexPageTemplate> findBySearch(SysConfigIndexPageTemplate search);
	
	List<SysConfigIndexPageTemplate> findBySearchBymoduleType(SysConfigIndexPageTemplate search);
}