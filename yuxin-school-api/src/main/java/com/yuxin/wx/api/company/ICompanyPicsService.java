package com.yuxin.wx.api.company;

import java.util.List;

import com.yuxin.wx.model.company.CompanyPics;
/**
 * Service Interface:CompanyPics
 * @author chopin
 * @date 2015-5-14
 */
public interface ICompanyPicsService  {
	/**
	 * 
	* @Title: saveCompanyPics
	* @Description: 新增CompanyPics
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-5-14
	* @user by wangzx
	 */
	void insert(CompanyPics entity);
	
	/**
	 * 
	* @Title: batchSaveCompanyPics 
	* @Description: 批量新增CompanyPics
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-5-14
	* @user by wangzx
	 */
	void batchInsert(List<CompanyPics> list);
	
	/**
	 * 
	* @Title: updateCompanyPics 
	* @Description: 编辑CompanyPics
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-5-14
	* @user by wangzx
	 */
	void update(CompanyPics entity);
	
	/**
	 * 
	* @Title: deleteCompanyPicsById 
	* @Description: 根据id删除CompanyPics
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-5-14
	* @user by wangzx
	 */
	void deleteCompanyPicsById(Integer id);
	
	/**
	 * 
	* @Title: deleteCompanyPicsByIds 
	* @Description: 根据id批量删除CompanyPics
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-5-14
	* @user by wangzx
	 */
	void deleteCompanyPicsByIds(Integer[] ids);
	
	/**
	 * 
	* @Title: findCompanyPicsById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-5-14
	* @user by wangzx
	 */
	CompanyPics findCompanyPicsById(Integer id);
	
	/**
	 * 
	* @Title: findCompanyPicsByPage 
	* @Description: 分页查询
	* @return
	* @return List<CompanyPics>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-5-14
	* @user by wangzx
	 */
	List<CompanyPics> findCompanyPicsByPage(CompanyPics search);
}