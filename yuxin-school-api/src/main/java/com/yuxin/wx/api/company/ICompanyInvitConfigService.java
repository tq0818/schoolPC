package com.yuxin.wx.api.company;

import java.util.List;

import com.yuxin.wx.model.company.CompanyInvitConfig;
/**
 * Service Interface:CompanyInvitConfig
 * @author chopin
 * @date 2016-7-29
 */
public interface ICompanyInvitConfigService  {
	/**
	 * 
	* @Title: saveCompanyInvitConfig
	* @Description: 新增CompanyInvitConfig
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-7-29
	* @user by wangzx
	 */
	void insert(CompanyInvitConfig entity);
	
	/**
	 * 
	* @Title: batchSaveCompanyInvitConfig 
	* @Description: 批量新增CompanyInvitConfig
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-7-29
	* @user by wangzx
	 */
	void batchInsert(List<CompanyInvitConfig> list);
	
	/**
	 * 
	* @Title: updateCompanyInvitConfig 
	* @Description: 编辑CompanyInvitConfig
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-7-29
	* @user by wangzx
	 */
	void update(CompanyInvitConfig entity);
	
	/**
	 * 
	* @Title: deleteCompanyInvitConfigById 
	* @Description: 根据id删除CompanyInvitConfig
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-7-29
	* @user by wangzx
	 */
	void deleteCompanyInvitConfigById(Integer id);
	
	/**
	 * 
	* @Title: deleteCompanyInvitConfigByIds 
	* @Description: 根据id批量删除CompanyInvitConfig
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-7-29
	* @user by wangzx
	 */
	void deleteCompanyInvitConfigByIds(Integer[] ids);
	
	/**
	 * 
	* @Title: findCompanyInvitConfigById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-7-29
	* @user by wangzx
	 */
	CompanyInvitConfig findCompanyInvitConfigById(Integer id);
	
	/**
	 * 
	* @Title: findCompanyInvitConfigByPage 
	* @Description: 分页查询
	* @return
	* @return List<CompanyInvitConfig>    返回类型 
	* @throws 
	* @exception 
	* @date 2016-7-29
	* @user by wangzx
	 */
	List<CompanyInvitConfig> findCompanyInvitConfigByPage(CompanyInvitConfig search);
	
	CompanyInvitConfig findInvitConfigByCompanyId(Integer companyId);
}