package com.yuxin.wx.api.company;

import java.util.List;

import com.yuxin.wx.model.company.CompanyAppAuth;
/**
 * Service Interface:CompanyAppAuth
 * @author chopin
 * @date 2016-5-27
 */
public interface ICompanyAppAuthService  {
	/**
	 * 
	* @Title: saveCompanyAppAuth
	* @Description: 新增CompanyAppAuth
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-5-27
	* @user by wangzx
	 */
	void insert(CompanyAppAuth entity);
	
	/**
	 * 
	* @Title: batchSaveCompanyAppAuth 
	* @Description: 批量新增CompanyAppAuth
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-5-27
	* @user by wangzx
	 */
	void batchInsert(List<CompanyAppAuth> list);
	
	/**
	 * 
	* @Title: updateCompanyAppAuth 
	* @Description: 编辑CompanyAppAuth
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-5-27
	* @user by wangzx
	 */
	void update(CompanyAppAuth entity);
	
	/**
	 * 
	* @Title: deleteCompanyAppAuthById 
	* @Description: 根据id删除CompanyAppAuth
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-5-27
	* @user by wangzx
	 */
	void deleteCompanyAppAuthById(Integer id);
	
	/**
	 * 
	* @Title: deleteCompanyAppAuthByIds 
	* @Description: 根据id批量删除CompanyAppAuth
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-5-27
	* @user by wangzx
	 */
	void deleteCompanyAppAuthByIds(Integer[] ids);
	
	/**
	 * 
	* @Title: findCompanyAppAuthById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-5-27
	* @user by wangzx
	 */
	CompanyAppAuth findCompanyAppAuthById(Integer id);
	
	/**
	 * 
	* @Title: findCompanyAppAuthByPage 
	* @Description: 分页查询
	* @return
	* @return List<CompanyAppAuth>    返回类型 
	* @throws 
	* @exception 
	* @date 2016-5-27
	* @user by wangzx
	 */
	List<CompanyAppAuth> findCompanyAppAuthByPage(CompanyAppAuth search);
	
	/**
	 * 
	 * Class Name: ICompanyAppAuthService.java
	 * @Description: 查询app版本
	 * @author zhang.zx
	 * @date 2016年5月27日 下午5:38:41
	 * @modifier
	 * @modify-date 2016年5月27日 下午5:38:41
	 * @version 1.0
	 * @param caa
	 * @return
	 */
	CompanyAppAuth findByParams(CompanyAppAuth caa);
}