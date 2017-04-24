package com.yuxin.wx.api.company;

import java.util.List;

import com.yuxin.wx.model.company.CompanyConfigCustompageTemplate;

/**
 * Service Interface:CompanyConfigCustompageTemplate
 * @author chopin
 * @date 2016-4-25
 */
public interface ICompanyConfigCustompageTemplateService  {
	/**
	 * 
	* @Title: saveCompanyConfigCustompageTemplate
	* @Description: 新增CompanyConfigCustompageTemplate
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-4-25
	* @user by wangzx
	 */
	void insert(CompanyConfigCustompageTemplate entity);
	
	/**
	 * 
	* @Title: batchSaveCompanyConfigCustompageTemplate 
	* @Description: 批量新增CompanyConfigCustompageTemplate
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-4-25
	* @user by wangzx
	 */
	void batchInsert(List<CompanyConfigCustompageTemplate> list);
	
	/**
	 * 
	* @Title: updateCompanyConfigCustompageTemplate 
	* @Description: 编辑CompanyConfigCustompageTemplate
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-4-25
	* @user by wangzx
	 */
	void update(CompanyConfigCustompageTemplate entity);
	
	/**
	 * 
	* @Title: deleteCompanyConfigCustompageTemplateById 
	* @Description: 根据id删除CompanyConfigCustompageTemplate
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-4-25
	* @user by wangzx
	 */
	void deleteCompanyConfigCustompageTemplateById(Integer id);
	
	/**
	 * 
	* @Title: deleteCompanyConfigCustompageTemplateByIds 
	* @Description: 根据id批量删除CompanyConfigCustompageTemplate
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-4-25
	* @user by wangzx
	 */
	void deleteCompanyConfigCustompageTemplateByIds(Integer[] ids);
	
	/**
	 * 
	* @Title: findCompanyConfigCustompageTemplateById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-4-25
	* @user by wangzx
	 */
	CompanyConfigCustompageTemplate findCompanyConfigCustompageTemplateById(Integer id);
	
	/**
	 * 
	* @Title: findCompanyConfigCustompageTemplateByPage 
	* @Description: 分页查询
	* @return
	* @return List<CompanyConfigCustompageTemplate>    返回类型 
	* @throws 
	* @exception 
	* @date 2016-4-25
	* @user by wangzx
	 */
	List<CompanyConfigCustompageTemplate> findCompanyConfigCustompageTemplateByPage(CompanyConfigCustompageTemplate search);
	
	/**
	 * 
	 * Class Name: ICompanyConfigCustompageTemplateService.java
	 * @Description: 查询类型列表
	 * @author zhang.zx
	 * @date 2016年4月25日 下午6:41:08
	 * @modifier
	 * @modify-date 2016年4月25日 下午6:41:08
	 * @version 1.0
	 * @param search
	 * @return
	 */
	List<CompanyConfigCustompageTemplate> queryCustomList(CompanyConfigCustompageTemplate search);
}