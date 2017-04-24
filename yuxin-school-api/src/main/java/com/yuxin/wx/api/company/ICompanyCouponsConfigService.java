package com.yuxin.wx.api.company;

import java.util.List;

import com.yuxin.wx.model.company.CompanyCouponsConfig;
/**
 * Service Interface:CompanyCouponsConfig
 * @author chopin
 * @date 2016-6-20
 */
public interface ICompanyCouponsConfigService  {
	/**
	 * 
	* @Title: saveCompanyCouponsConfig
	* @Description: 新增CompanyCouponsConfig
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-6-20
	* @user by wangzx
	 */
	void insert(CompanyCouponsConfig entity);
	
	/**
	 * 
	* @Title: batchSaveCompanyCouponsConfig 
	* @Description: 批量新增CompanyCouponsConfig
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-6-20
	* @user by wangzx
	 */
	void batchInsert(List<CompanyCouponsConfig> list);
	
	/**
	 * 
	* @Title: updateCompanyCouponsConfig 
	* @Description: 编辑CompanyCouponsConfig
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-6-20
	* @user by wangzx
	 */
	void update(CompanyCouponsConfig entity);
	
	/**
	 * 
	* @Title: deleteCompanyCouponsConfigById 
	* @Description: 根据id删除CompanyCouponsConfig
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-6-20
	* @user by wangzx
	 */
	void deleteCompanyCouponsConfigById(Integer id);
	
	/**
	 * 
	* @Title: deleteCompanyCouponsConfigByIds 
	* @Description: 根据id批量删除CompanyCouponsConfig
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-6-20
	* @user by wangzx
	 */
	void deleteCompanyCouponsConfigByIds(Integer[] ids);
	
	/**
	 * 
	* @Title: findCompanyCouponsConfigById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-6-20
	* @user by wangzx
	 */
	CompanyCouponsConfig findCompanyCouponsConfigById(Integer id);
	
	/**
	 * 
	* @Title: findCompanyCouponsConfigByPage 
	* @Description: 分页查询
	* @return
	* @return List<CompanyCouponsConfig>    返回类型 
	* @throws 
	* @exception 
	* @date 2016-6-20
	* @user by wangzx
	 */
	List<CompanyCouponsConfig> findCompanyCouponsConfigByPage(CompanyCouponsConfig search);
	
	/**
	 * 
	 * Class Name: ICompanyCouponsConfigService.java
	 * @Description: byCompanyId
	 * @author dongshuai
	 * @date 2016年6月20日
	 * @param search
	 * @return
	 */
	CompanyCouponsConfig findByCompanyId(CompanyCouponsConfig search);
}