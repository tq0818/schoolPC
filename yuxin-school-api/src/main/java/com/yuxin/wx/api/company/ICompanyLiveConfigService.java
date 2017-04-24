package com.yuxin.wx.api.company;

import java.util.List;

import com.yuxin.wx.model.company.CompanyLiveConfig;
/**
 * Service Interface:CompanyLiveConfig
 * @author wang.zx
 * @date 2016-2-29
 */
public interface ICompanyLiveConfigService  {
	/**
	 * 
	* @Title: saveCompanyLiveConfig
	* @Description: 新增CompanyLiveConfig
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-2-29
	* @user by wangzx
	 */
	void insert(CompanyLiveConfig entity);
	
	/**
	 * 
	* @Title: batchSaveCompanyLiveConfig 
	* @Description: 批量新增CompanyLiveConfig
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-2-29
	* @user by wangzx
	 */
	void batchInsert(List<CompanyLiveConfig> list);
	
	/**
	 * 
	* @Title: updateCompanyLiveConfig 
	* @Description: 编辑CompanyLiveConfig
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-2-29
	* @user by wangzx
	 */
	void update(CompanyLiveConfig entity);
	
	/**
	 * 
	* @Title: deleteCompanyLiveConfigById 
	* @Description: 根据id删除CompanyLiveConfig
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-2-29
	* @user by wangzx
	 */
	void deleteCompanyLiveConfigById(Integer id);
	
	/**
	 * 
	* @Title: deleteCompanyLiveConfigByIds 
	* @Description: 根据id批量删除CompanyLiveConfig
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-2-29
	* @user by wangzx
	 */
	void deleteCompanyLiveConfigByIds(Integer[] ids);
	
	/**
	 * 
	* @Title: findCompanyLiveConfigById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-2-29
	* @user by wangzx
	 */
	CompanyLiveConfig findCompanyLiveConfigById(Integer id);
	
	/**
	 * 
	* @Title: findCompanyLiveConfigByPage 
	* @Description: 分页查询
	* @return
	* @return List<CompanyLiveConfig>    返回类型 
	* @throws 
	* @exception 
	* @date 2016-2-29
	* @user by wangzx
	 */
	List<CompanyLiveConfig> findCompanyLiveConfigByPage(CompanyLiveConfig search);

	/**
	 * 
	 * Class Name: ICompanyLiveConfigService.java
	 * @Description: 根据公司id 查询
	 * @author 周文斌
	 * @date 2016-3-1 上午11:41:40
	 * @version 1.0
	 * @param companyId
	 * @return
	 */
	CompanyLiveConfig findByCompanyId(Integer companyId);
}