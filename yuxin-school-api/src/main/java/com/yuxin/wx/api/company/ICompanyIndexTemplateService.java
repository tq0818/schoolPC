package com.yuxin.wx.api.company;

import java.util.List;

import com.yuxin.wx.model.company.CompanyIndexTemplate;
/**
 * Service Interface:CompanyIndexTemplate
 * @author chopin
 * @date 2015-5-18
 */
public interface ICompanyIndexTemplateService  {
	/**
	 * 
	* @Title: saveCompanyIndexTemplate
	* @Description: 新增CompanyIndexTemplate
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-5-18
	* @user by wangzx
	 */
	void insert(CompanyIndexTemplate entity);
	
	/**
	 * 
	* @Title: batchSaveCompanyIndexTemplate 
	* @Description: 批量新增CompanyIndexTemplate
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-5-18
	* @user by wangzx
	 */
	void batchInsert(List<CompanyIndexTemplate> list);
	
	/**
	 * 
	* @Title: updateCompanyIndexTemplate 
	* @Description: 编辑CompanyIndexTemplate
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-5-18
	* @user by wangzx
	 */
	void update(CompanyIndexTemplate entity);
	
	/**
	 * 
	* @Title: deleteCompanyIndexTemplateById 
	* @Description: 根据id删除CompanyIndexTemplate
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-5-18
	* @user by wangzx
	 */
	void deleteCompanyIndexTemplateById(Integer id);
	
	/**
	 * 
	* @Title: deleteCompanyIndexTemplateByIds 
	* @Description: 根据id批量删除CompanyIndexTemplate
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-5-18
	* @user by wangzx
	 */
	void deleteCompanyIndexTemplateByIds(Integer[] ids);
	
	/**
	 * 
	* @Title: findCompanyIndexTemplateById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-5-18
	* @user by wangzx
	 */
	CompanyIndexTemplate findCompanyIndexTemplateById(Integer id);
	
	/**
	 * 
	* @Title: findCompanyIndexTemplateByPage 
	* @Description: 分页查询
	* @return
	* @return List<CompanyIndexTemplate>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-5-18
	* @user by wangzx
	 */
	List<CompanyIndexTemplate> findCompanyIndexTemplateByPage(CompanyIndexTemplate search);
	
	/**
	 * 
	* @Title: findCompanyIndexTemplateByPage 
	* @Description: 查询公司模板
	* @return
	* @return List<CompanyIndexTemplate>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-5-18
	* @user by chopin
	 */
	public List<CompanyIndexTemplate> findTemplateByCompany(Integer companyId,Integer schoolId);
	
	/**
	 * 
	* @Title: findCompanyIndexTemplateByPage 
	* @Description: 复制系统模板到公司模板
	* @return
	* @return List<CompanyIndexTemplate>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-5-18
	* @user by chopin
	 */
	public CompanyIndexTemplate copyToCompany(Integer companyId,Integer schoolId,Integer id);
	
	
	/**
	 * 
	* @Title: findCompanyIndexTemplateByPage 
	* @Description: 复制系统模板到公司模板
	* @return
	* @return List<CompanyIndexTemplate>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-5-18
	* @user by chopin
	 */
	public CompanyIndexTemplate copyToCompany2(Integer companyId,Integer schoolId,Integer id);
	
	/**
	 * 
	* @Title: findCompanyIndexTemplateByPage 
	* @Description: 使用模板
	* @return
	* @return List<CompanyIndexTemplate>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-5-18
	* @user by chopin
	 */
	public void useTemplate(Integer companyId,Integer schoolId,Integer templateId);
	
	/**
	 * 
	* @Title: useTemplate 
	* @Description: 使用模板
	* @return
	* @return List<CompanyIndexTemplate>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-5-18
	* @user by chopin
	 */
	public void unUseTemplate(Integer companyId,Integer schoolId);
	
	/**
	 * 
	* @Title: useTemplate 
	* @Description: 使用模板
	* @return
	* @return List<CompanyIndexTemplate>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-5-18
	* @user by chopin
	 */
	public Boolean checkByName(CompanyIndexTemplate search);

	List<CompanyIndexTemplate> findTemplateByCompanyDesc(Integer companyId, Integer schoolId);
}