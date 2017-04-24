package com.yuxin.wx.api.company;

import java.util.List;

import com.yuxin.wx.model.company.CompanyLoginConfig;
/**
 * Service Interface:CompanyLoginConfig
 * @author chopin
 * @date 2016-7-4
 */
public interface ICompanyLoginConfigService  {
	/**
	 * 
	* @Title: saveCompanyLoginConfig
	* @Description: 新增CompanyLoginConfig
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-7-4
	* @user by wangzx
	 */
	void insert(CompanyLoginConfig entity);
	
	/**
	 * 
	* @Title: batchSaveCompanyLoginConfig 
	* @Description: 批量新增CompanyLoginConfig
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-7-4
	* @user by wangzx
	 */
	void batchInsert(List<CompanyLoginConfig> list);
	
	/**
	 * 
	* @Title: updateCompanyLoginConfig 
	* @Description: 编辑CompanyLoginConfig
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-7-4
	* @user by wangzx
	 */
	void update(CompanyLoginConfig entity);
	
	/**
	 * 
	* @Title: deleteCompanyLoginConfigById 
	* @Description: 根据id删除CompanyLoginConfig
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-7-4
	* @user by wangzx
	 */
	void deleteCompanyLoginConfigById(Integer id);
	
	/**
	 * 
	* @Title: deleteCompanyLoginConfigByIds 
	* @Description: 根据id批量删除CompanyLoginConfig
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-7-4
	* @user by wangzx
	 */
	void deleteCompanyLoginConfigByIds(Integer[] ids);
	
	/**
	 * 
	* @Title: findCompanyLoginConfigById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-7-4
	* @user by wangzx
	 */
	CompanyLoginConfig findCompanyLoginConfigById(Integer id);
	
	/**
	 * 
	* @Title: findCompanyLoginConfigByPage 
	* @Description: 分页查询
	* @return
	* @return List<CompanyLoginConfig>    返回类型 
	* @throws 
	* @exception 
	* @date 2016-7-4
	* @user by wangzx
	 */
	List<CompanyLoginConfig> findCompanyLoginConfigByPage(CompanyLoginConfig search);
	
	/**
	 * 
	 * Class Name: ICompanyLoginConfigService.java
	 * @Description: 查询登录配置
	 * @author dongshuai
	 * @date 2016年7月5日 上午11:34:13
	 * @modifier
	 * @modify-date 2016年7月5日 上午11:34:13
	 * @version 1.0
	 * @param search
	 * @return
	 */
	CompanyLoginConfig queryByCompanyId(CompanyLoginConfig search);
}