package com.yuxin.wx.api.company;

import java.util.List;

import com.yuxin.wx.model.company.CompanyLiveWarmupZs;
/**
 * Service Interface:CompanyLiveWarmupZs
 * @author wang.zx
 * @date 2015-12-11
 */
public interface ICompanyLiveWarmupZsService  {
	/**
	 * 
	* @Title: saveCompanyLiveWarmupZs
	* @Description: 新增CompanyLiveWarmupZs
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-12-11
	* @user by wangzx
	 */
	void insert(CompanyLiveWarmupZs entity);
	
	/**
	 * 
	* @Title: batchSaveCompanyLiveWarmupZs 
	* @Description: 批量新增CompanyLiveWarmupZs
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-12-11
	* @user by wangzx
	 */
	void batchInsert(List<CompanyLiveWarmupZs> list);
	
	/**
	 * 
	* @Title: updateCompanyLiveWarmupZs 
	* @Description: 编辑CompanyLiveWarmupZs
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-12-11
	* @user by wangzx
	 */
	void update(CompanyLiveWarmupZs entity);
	
	/**
	 * 
	* @Title: deleteCompanyLiveWarmupZsById 
	* @Description: 根据id删除CompanyLiveWarmupZs
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-12-11
	* @user by wangzx
	 */
	void deleteCompanyLiveWarmupZsById(Integer id);
	
	/**
	 * 
	* @Title: deleteCompanyLiveWarmupZsByIds 
	* @Description: 根据id批量删除CompanyLiveWarmupZs
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-12-11
	* @user by wangzx
	 */
	void deleteCompanyLiveWarmupZsByIds(Integer[] ids);
	
	/**
	 * 
	* @Title: findCompanyLiveWarmupZsById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-12-11
	* @user by wangzx
	 */
	CompanyLiveWarmupZs findCompanyLiveWarmupZsById(Integer id);
	
	/**
	 * 
	* @Title: findCompanyLiveWarmupZsByPage 
	* @Description: 分页查询
	* @return
	* @return List<CompanyLiveWarmupZs>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-12-11
	* @user by wangzx
	 */
	List<CompanyLiveWarmupZs> findCompanyLiveWarmupZsByPage(CompanyLiveWarmupZs search);
}