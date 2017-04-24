package com.yuxin.wx.api.company;

import java.util.List;

import com.yuxin.wx.model.company.CompanyVideoConfig;
/**
 * Service Interface:CompanyVideoConfig
 * @author wang.zx
 * @date 2017-3-22
 */
public interface ICompanyVideoConfigService  {
	/**
	 * 
	* @Title: saveCompanyVideoConfig
	* @Description: 新增CompanyVideoConfig
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2017-3-22
	* @user by wangzx
	 */
	void insert(CompanyVideoConfig entity);
	
	/**
	 * 
	* @Title: batchSaveCompanyVideoConfig 
	* @Description: 批量新增CompanyVideoConfig
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2017-3-22
	* @user by wangzx
	 */
	void batchInsert(List<CompanyVideoConfig> list);
	
	/**
	 * 
	* @Title: updateCompanyVideoConfig 
	* @Description: 编辑CompanyVideoConfig
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2017-3-22
	* @user by wangzx
	 */
	void update(CompanyVideoConfig entity);
	
	/**
	 * 
	* @Title: deleteCompanyVideoConfigById 
	* @Description: 根据id删除CompanyVideoConfig
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2017-3-22
	* @user by wangzx
	 */
	void deleteCompanyVideoConfigById(Integer id);
	
	/**
	 * 
	* @Title: deleteCompanyVideoConfigByIds 
	* @Description: 根据id批量删除CompanyVideoConfig
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2017-3-22
	* @user by wangzx
	 */
	void deleteCompanyVideoConfigByIds(Integer[] ids);
	
	/**
	 * 
	* @Title: findCompanyVideoConfigById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2017-3-22
	* @user by wangzx
	 */
	CompanyVideoConfig findCompanyVideoConfigById(Integer id);
	
	/**
	 * 
	* @Title: findCompanyVideoConfigByPage 
	* @Description: 分页查询
	* @return
	* @return List<CompanyVideoConfig>    返回类型 
	* @throws 
	* @exception 
	* @date 2017-3-22
	* @user by wangzx
	 */
	List<CompanyVideoConfig> findCompanyVideoConfigByPage(CompanyVideoConfig search);
	
	/**
	 * 
	 * Class Name: ICompanyVideoConfigService.java
	 * @Description: 查询公司配置
	 * @author 周文斌
	 * @date 2017-3-22 下午2:23:11
	 * @modify	2017-3-22 下午2:23:11
	 * @version 
	 * @param companyId
	 * @return
	 */
	CompanyVideoConfig findConfigByCompanyId(Integer companyId);
}