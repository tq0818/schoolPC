package com.yuxin.wx.api.company;


import java.util.List;

import com.yuxin.wx.model.company.CompanyHardbindData;

/**
 * Service Interface:CompanyHardbindData
 * @author chopin
 * @date 2016-9-1
 */
public interface ICompanyHardbindDataService  {
	/**
	 * 
	* @Title: saveCompanyHardbindData
	* @Description: 新增CompanyHardbindData
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-9-1
	* @user by wangzx
	 */
	void insert(CompanyHardbindData entity);
	
	/**
	 * 
	* @Title: batchSaveCompanyHardbindData 
	* @Description: 批量新增CompanyHardbindData
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-9-1
	* @user by wangzx
	 */
	void batchInsert(List<CompanyHardbindData> list);
	
	/**
	 * 
	* @Title: updateCompanyHardbindData 
	* @Description: 编辑CompanyHardbindData
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-9-1
	* @user by wangzx
	 */
	void update(CompanyHardbindData entity);
	
	/**
	 * 
	* @Title: deleteCompanyHardbindDataById 
	* @Description: 根据id删除CompanyHardbindData
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-9-1
	* @user by wangzx
	 */
	void deleteCompanyHardbindDataById(Integer id);
	
	/**
	 * 
	* @Title: deleteCompanyHardbindDataByIds 
	* @Description: 根据id批量删除CompanyHardbindData
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-9-1
	* @user by wangzx
	 */
	void deleteCompanyHardbindDataByIds(Integer[] ids);
	
	/**
	 * 
	* @Title: findCompanyHardbindDataById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-9-1
	* @user by wangzx
	 */
	CompanyHardbindData findCompanyHardbindDataById(Integer id);
	
	/**
	 * 
	* @Title: findCompanyHardbindDataByPage 
	* @Description: 分页查询
	* @return
	* @return List<CompanyHardbindData>    返回类型 
	* @throws 
	* @exception 
	* @date 2016-9-1
	* @user by wangzx
	 */
	List<CompanyHardbindData> findCompanyHardbindDataByPage(CompanyHardbindData search);
	
	public Integer findCompanyHardbindDataCountByPage(CompanyHardbindData search);
}