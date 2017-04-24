package com.yuxin.wx.api.company;

import java.util.List;

import com.yuxin.wx.model.company.CompanyHeadFootConfig;
/**
 * Service Interface:CompanyHeadFootConfig
 * @author chopin
 * @date 2016-2-29
 */
public interface ICompanyHeadFootConfigService  {
	/**
	 * 
	* @Title: saveCompanyHeadFootConfig
	* @Description: 新增CompanyHeadFootConfig
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-2-29
	* @user by wangzx
	 */
	void insert(CompanyHeadFootConfig entity);
	
	/**
	 * 
	* @Title: batchSaveCompanyHeadFootConfig 
	* @Description: 批量新增CompanyHeadFootConfig
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-2-29
	* @user by wangzx
	 */
	void batchInsert(List<CompanyHeadFootConfig> list);
	
	/**
	 * 
	* @Title: updateCompanyHeadFootConfig 
	* @Description: 编辑CompanyHeadFootConfig
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-2-29
	* @user by wangzx
	 */
	void update(CompanyHeadFootConfig entity);
	
	/**
	 * 
	* @Title: deleteCompanyHeadFootConfigById 
	* @Description: 根据id删除CompanyHeadFootConfig
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-2-29
	* @user by wangzx
	 */
	void deleteCompanyHeadFootConfigById(Integer id);
	
	/**
	 * 
	* @Title: deleteCompanyHeadFootConfigByIds 
	* @Description: 根据id批量删除CompanyHeadFootConfig
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-2-29
	* @user by wangzx
	 */
	void deleteCompanyHeadFootConfigByIds(Integer[] ids);
	
	/**
	 * 
	* @Title: findCompanyHeadFootConfigById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-2-29
	* @user by wangzx
	 */
	CompanyHeadFootConfig findCompanyHeadFootConfigById(Integer id);
	
	/**
	 * 
	* @Title: findCompanyHeadFootConfigByPage 
	* @Description: 分页查询
	* @return
	* @return List<CompanyHeadFootConfig>    返回类型 
	* @throws 
	* @exception 
	* @date 2016-2-29
	* @user by wangzx
	 */
	List<CompanyHeadFootConfig> findCompanyHeadFootConfigByPage(CompanyHeadFootConfig search);
	
	/**
	 * 
	* @Title: findFootConfigByCompany 
	* @Description: 查询公司模板信息
	* @return
	* @return List<CompanyHeadFootConfig>    返回类型 
	* @throws 
	* @exception 
	* @date 2016-2-29
	* @user by zhang.zx
	 */
	CompanyHeadFootConfig findFootConfigByCompany(CompanyHeadFootConfig search);
	
	/**
	 * 
	* @Title: findTemplatesById 
	* @Description: 查询公司所有模板信息
	* @return
	* @return List<CompanyHeadFootConfig>    返回类型 
	* @throws 
	* @exception 
	* @date 2016-2-29
	* @user by zhang.zx
	 */
	List<CompanyHeadFootConfig> findTemplatesById(Integer companyId);
}