package com.yuxin.wx.api.company;


import java.util.List;

import com.yuxin.wx.model.company.CompanyAppBarConfig;
/**
 * Service Interface:CompanyAppBarConfig
 * @author chopin
 * @date 2016-5-5
 */
public interface ICompanyAppBarConfigService  {
	/**
	 * 
	* @Title: saveCompanyAppBarConfig
	* @Description: 新增CompanyAppBarConfig
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-5-5
	* @user by wangzx
	 */
	void insert(CompanyAppBarConfig entity);
	
	/**
	 * 
	* @Title: batchSaveCompanyAppBarConfig 
	* @Description: 批量新增CompanyAppBarConfig
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-5-5
	* @user by wangzx
	 */
	void batchInsert(List<CompanyAppBarConfig> list);
	
	/**
	 * 
	* @Title: updateCompanyAppBarConfig 
	* @Description: 编辑CompanyAppBarConfig
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-5-5
	* @user by wangzx
	 */
	void update(CompanyAppBarConfig entity);
	
	/**
	 * 
	* @Title: deleteCompanyAppBarConfigById 
	* @Description: 根据id删除CompanyAppBarConfig
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-5-5
	* @user by wangzx
	 */
	void deleteCompanyAppBarConfigById(Integer id);
	
	/**
	 * 
	* @Title: deleteCompanyAppBarConfigByIds 
	* @Description: 根据id批量删除CompanyAppBarConfig
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-5-5
	* @user by wangzx
	 */
	void deleteCompanyAppBarConfigByIds(Integer[] ids);
	
	/**
	 * 
	* @Title: findCompanyAppBarConfigById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-5-5
	* @user by wangzx
	 */
	CompanyAppBarConfig findCompanyAppBarConfigById(Integer id);
	
	/**
	 * 
	* @Title: findCompanyAppBarConfigByPage 
	* @Description: 分页查询
	* @return
	* @return List<CompanyAppBarConfig>    返回类型 
	* @throws 
	* @exception 
	* @date 2016-5-5
	* @user by wangzx
	 */
	List<CompanyAppBarConfig> findCompanyAppBarConfigByPage(CompanyAppBarConfig search);

	List<CompanyAppBarConfig> findByCompanyAppBarConfig(CompanyAppBarConfig search);
}