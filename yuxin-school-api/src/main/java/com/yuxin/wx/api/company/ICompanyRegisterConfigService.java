package com.yuxin.wx.api.company;

import java.util.List;

import com.yuxin.wx.model.company.CompanyRegisterConfig;
/**
 * Service Interface:CompanyRegisterConfig
 * @author chopin
 * @date 2016-7-4
 */
public interface ICompanyRegisterConfigService  {
	/**
	 * 
	* @Title: saveCompanyRegisterConfig
	* @Description: 新增CompanyRegisterConfig
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-7-4
	* @user by wangzx
	 */
	void insert(CompanyRegisterConfig entity);
	
	/**
	 * 
	* @Title: batchSaveCompanyRegisterConfig 
	* @Description: 批量新增CompanyRegisterConfig
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-7-4
	* @user by wangzx
	 */
	void batchInsert(List<CompanyRegisterConfig> list);
	
	/**
	 * 
	* @Title: updateCompanyRegisterConfig 
	* @Description: 编辑CompanyRegisterConfig
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-7-4
	* @user by wangzx
	 */
	void update(CompanyRegisterConfig entity);
	
	/**
	 * 
	* @Title: deleteCompanyRegisterConfigById 
	* @Description: 根据id删除CompanyRegisterConfig
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-7-4
	* @user by wangzx
	 */
	void deleteCompanyRegisterConfigById(Integer id);
	
	/**
	 * 
	* @Title: deleteCompanyRegisterConfigByIds 
	* @Description: 根据id批量删除CompanyRegisterConfig
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-7-4
	* @user by wangzx
	 */
	void deleteCompanyRegisterConfigByIds(Integer[] ids);
	
	/**
	 * 
	* @Title: findCompanyRegisterConfigById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-7-4
	* @user by wangzx
	 */
	CompanyRegisterConfig findCompanyRegisterConfigById(Integer id);
	
	/**
	 * 
	* @Title: findCompanyRegisterConfigByPage 
	* @Description: 分页查询
	* @return
	* @return List<CompanyRegisterConfig>    返回类型 
	* @throws 
	* @exception 
	* @date 2016-7-4
	* @user by wangzx
	 */
	List<CompanyRegisterConfig> findCompanyRegisterConfigByPage(CompanyRegisterConfig search);
	
	
	CompanyRegisterConfig queryByCompanyId(CompanyRegisterConfig crc);
}