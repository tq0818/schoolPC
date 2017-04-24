package com.yuxin.wx.api.company;

import java.util.List;

import com.yuxin.wx.model.company.CompanyHardbindConfig;

/**
 * Service Interface:CompanyHardbindConfig
 * @author wang.zx
 * @date 2016-2-18
 */
public interface ICompanyHardbindConfigService  {
	/**
	 * 
	* @Title: saveCompanyHardbindConfig
	* @Description: 新增CompanyHardbindConfig
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-2-18
	* @user by wangzx
	 */
	void insert(CompanyHardbindConfig entity);
	
	/**
	 * 
	* @Title: batchSaveCompanyHardbindConfig 
	* @Description: 批量新增CompanyHardbindConfig
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-2-18
	* @user by wangzx
	 */
	void batchInsert(List<CompanyHardbindConfig> entity);
	
	/**
	 * 
	* @Title: updateCompanyHardbindConfig 
	* @Description: 编辑CompanyHardbindConfig
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-2-18
	* @user by wangzx
	 */
	void update(CompanyHardbindConfig T);
	
	/**
	 * 
	* @Title: deleteCompanyHardbindConfigById 
	* @Description: 根据id删除CompanyHardbindConfig
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-2-18
	* @user by wangzx
	 */
	void deleteCompanyHardbindConfigById(Integer id);
	
	/**
	 * 
	* @Title: deleteCompanyHardbindConfigByIds 
	* @Description: 根据id批量删除CompanyHardbindConfig
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-2-18
	* @user by wangzx
	 */
	void deleteCompanyHardbindConfigByIds(Integer[] ids);
	
	/**
	 * 
	* @Title: findCompanyHardbindConfigById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-2-18
	* @user by wangzx
	 */
	CompanyHardbindConfig findCompanyHardbindConfigById(Integer id);
	
	/**
	 * 
	* @Title: findCompanyHardbindConfigByPage 
	* @Description: 分页查询
	* @return
	* @return List<CompanyHardbindConfig>    返回类型 
	* @throws 
	* @exception 
	* @date 2016-2-18
	* @user by wangzx
	 */
	List<CompanyHardbindConfig> findCompanyHardbindConfigByPage(CompanyHardbindConfig search);
}