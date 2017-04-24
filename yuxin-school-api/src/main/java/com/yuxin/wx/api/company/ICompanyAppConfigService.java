package com.yuxin.wx.api.company;

import java.util.List;

import com.yuxin.wx.model.company.CompanyAppConfig;
/**
 * Service Interface:CompanyAppConfig
 * @author chopin
 * @date 2016-5-27
 */
public interface ICompanyAppConfigService  {
	/**
	 * 
	* @Title: saveCompanyAppConfig
	* @Description: 新增CompanyAppConfig
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-5-27
	* @user by wangzx
	 */
	void insert(CompanyAppConfig entity);
	
	/**
	 * 
	* @Title: batchSaveCompanyAppConfig 
	* @Description: 批量新增CompanyAppConfig
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-5-27
	* @user by wangzx
	 */
	void batchInsert(List<CompanyAppConfig> list);
	
	/**
	 * 
	* @Title: updateCompanyAppConfig 
	* @Description: 编辑CompanyAppConfig
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-5-27
	* @user by wangzx
	 */
	void update(CompanyAppConfig entity);
	
	/**
	 * 
	* @Title: deleteCompanyAppConfigById 
	* @Description: 根据id删除CompanyAppConfig
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-5-27
	* @user by wangzx
	 */
	void deleteCompanyAppConfigById(Integer id);
	
	/**
	 * 
	* @Title: deleteCompanyAppConfigByIds 
	* @Description: 根据id批量删除CompanyAppConfig
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-5-27
	* @user by wangzx
	 */
	void deleteCompanyAppConfigByIds(Integer[] ids);
	
	/**
	 * 
	* @Title: findCompanyAppConfigById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-5-27
	* @user by wangzx
	 */
	CompanyAppConfig findCompanyAppConfigById(Integer id);
	
	/**
	 * 
	* @Title: findCompanyAppConfigByPage 
	* @Description: 分页查询
	* @return
	* @return List<CompanyAppConfig>    返回类型 
	* @throws 
	* @exception 
	* @date 2016-5-27
	* @user by wangzx
	 */
	List<CompanyAppConfig> findCompanyAppConfigByPage(CompanyAppConfig search);
	
	/**
	 * 
	 * Class Name: ICompanyAppConfigService.java
	 * @Description: 查询公司app配置
	 * @author zhang.zx
	 * @date 2016年5月27日 下午6:45:17
	 * @modifier
	 * @modify-date 2016年5月27日 下午6:45:17
	 * @version 1.0
	 * @param companyId
	 * @return
	 */
	CompanyAppConfig queryCompanyConfig(Integer companyId);
}