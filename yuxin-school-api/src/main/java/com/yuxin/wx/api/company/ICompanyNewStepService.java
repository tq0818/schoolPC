package com.yuxin.wx.api.company;

import java.util.List;

import com.yuxin.wx.model.company.CompanyNewStep;
/**
 * Service Interface:CompanyNewStep
 * @author chopin
 * @date 2015-5-20
 */
public interface ICompanyNewStepService  {
	/**
	 * 
	* @Title: saveCompanyNewStep
	* @Description: 新增CompanyNewStep
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-5-20
	* @user by wangzx
	 */
	void insert(CompanyNewStep entity);
	
	/**
	 * 
	* @Title: batchSaveCompanyNewStep 
	* @Description: 批量新增CompanyNewStep
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-5-20
	* @user by wangzx
	 */
	void batchInsert(List<CompanyNewStep> list);
	
	/**
	 * 
	* @Title: updateCompanyNewStep 
	* @Description: 编辑CompanyNewStep
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-5-20
	* @user by wangzx
	 */
	void update(CompanyNewStep entity);
	
	/**
	 * 
	* @Title: deleteCompanyNewStepById 
	* @Description: 根据id删除CompanyNewStep
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-5-20
	* @user by wangzx
	 */
	void deleteCompanyNewStepById(Integer id);
	
	/**
	 * 
	* @Title: deleteCompanyNewStepByIds 
	* @Description: 根据id批量删除CompanyNewStep
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-5-20
	* @user by wangzx
	 */
	void deleteCompanyNewStepByIds(Integer[] ids);
	
	/**
	 * 
	* @Title: findCompanyNewStepById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-5-20
	* @user by wangzx
	 */
	CompanyNewStep findCompanyNewStepById(Integer id);
	
	/**
	 * 
	* @Title: findCompanyNewStepByPage 
	* @Description: 分页查询
	* @return
	* @return List<CompanyNewStep>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-5-20
	* @user by wangzx
	 */
	List<CompanyNewStep> findCompanyNewStepByPage(CompanyNewStep search);
	/**
	 * 
	 * Class Name: ICompanyNewStepService.java
	 * @Description: 查询是否验证通过
	 * @author 权飞虎
	 * @date 2015年5月20日 上午11:07:24
	 * @modifier
	 * @modify-date 2015年5月20日 上午11:07:24
	 * @version 1.0
	 * @param currentCompanyId
	 * @return
	 */
	int selectCount(Integer currentCompanyId);
	/**
	 * 
	 * Class Name: ICompanyNewStepService.java
	 * @Description: 根据公司id查询新手指导
	 * @author 权飞虎
	 * @date 2015年5月20日 上午11:17:15
	 * @modifier
	 * @modify-date 2015年5月20日 上午11:17:15
	 * @version 1.0
	 * @param currentCompanyId
	 * @return
	 */
	List<CompanyNewStep> findCompanyNewStepByCompany(Integer currentCompanyId);
	
	Boolean isPass(Integer companyId);
}