package com.yuxin.wx.api.system;

import java.util.List;
import java.util.Map;

import com.yuxin.wx.model.system.SysPageHeadFooterTemplate;
/**
 * Service Interface:SysPageHeadFooterTemplate
 * @author chopin
 * @date 2016-2-29
 */
public interface ISysPageHeadFooterTemplateService  {
	/**
	 * 
	* @Title: saveSysPageHeadFooterTemplate
	* @Description: 新增SysPageHeadFooterTemplate
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-2-29
	* @user by wangzx
	 */
	void insert(SysPageHeadFooterTemplate entity);
	
	/**
	 * 
	* @Title: batchSaveSysPageHeadFooterTemplate 
	* @Description: 批量新增SysPageHeadFooterTemplate
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-2-29
	* @user by wangzx
	 */
	void batchInsert(List<SysPageHeadFooterTemplate> list);
	
	/**
	 * 
	* @Title: updateSysPageHeadFooterTemplate 
	* @Description: 编辑SysPageHeadFooterTemplate
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-2-29
	* @user by wangzx
	 */
	void update(SysPageHeadFooterTemplate entity);
	
	/**
	 * 
	* @Title: deleteSysPageHeadFooterTemplateById 
	* @Description: 根据id删除SysPageHeadFooterTemplate
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-2-29
	* @user by wangzx
	 */
	void deleteSysPageHeadFooterTemplateById(Integer id);
	
	/**
	 * 
	* @Title: deleteSysPageHeadFooterTemplateByIds 
	* @Description: 根据id批量删除SysPageHeadFooterTemplate
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-2-29
	* @user by wangzx
	 */
	void deleteSysPageHeadFooterTemplateByIds(Integer[] ids);
	
	/**
	 * 
	* @Title: findSysPageHeadFooterTemplateById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-2-29
	* @user by wangzx
	 */
	SysPageHeadFooterTemplate findSysPageHeadFooterTemplateById(Integer id);
	
	/**
	 * 
	* @Title: findSysPageHeadFooterTemplateByPage 
	* @Description: 分页查询
	* @return
	* @return List<SysPageHeadFooterTemplate>    返回类型 
	* @throws 
	* @exception 
	* @date 2016-2-29
	* @user by wangzx
	 */
	List<SysPageHeadFooterTemplate> findSysPageHeadFooterTemplateByPage(SysPageHeadFooterTemplate search);
	
	/**
	 * 
	* @Title: findFootTempletes 
	* @Description: 查询某种类型的模板信息
	* @return
	* @return List<SysPageHeadFooterTemplate>    返回类型 
	* @throws 
	* @exception 
	* @date 2016-2-29
	* @user by zhang.zx
	 */
	List<SysPageHeadFooterTemplate> findFootTempletes(String templateType);
	
	/**
	 * 
	 * Class Name: ISysPageHeadFooterTemplateService.java
	 * @Description: 查询模板下有效页尾导航链接数
	 * @author zhang.zx
	 * @date 2016年4月22日 下午2:14:35
	 * @modifier
	 * @modify-date 2016年4月22日 下午2:14:35
	 * @version 1.0
	 * @param map
	 * @return
	 */
	Integer queryIsUseingLink(Map<String, Object> map);
}