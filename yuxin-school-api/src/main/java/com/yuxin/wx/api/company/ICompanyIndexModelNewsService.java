package com.yuxin.wx.api.company;

import java.util.List;

import com.yuxin.wx.model.company.CompanyIndexModelNews;
/**
 * Service Interface:CompanyIndexModelNews
 * @author chopin
 * @date 2015-5-18
 */
public interface ICompanyIndexModelNewsService  {
	/**
	 * 
	* @Title: saveCompanyIndexModelNews
	* @Description: 新增CompanyIndexModelNews
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-5-18
	* @user by wangzx
	 */
	void insert(CompanyIndexModelNews entity);
	
	/**
	 * 
	* @Title: batchSaveCompanyIndexModelNews 
	* @Description: 批量新增CompanyIndexModelNews
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-5-18
	* @user by wangzx
	 */
	void batchInsert(List<CompanyIndexModelNews> list);
	
	/**
	 * 
	* @Title: updateCompanyIndexModelNews 
	* @Description: 编辑CompanyIndexModelNews
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-5-18
	* @user by wangzx
	 */
	void update(CompanyIndexModelNews entity);
	
	/**
	 * 
	* @Title: deleteCompanyIndexModelNewsById 
	* @Description: 根据id删除CompanyIndexModelNews
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-5-18
	* @user by wangzx
	 */
	void deleteCompanyIndexModelNewsById(Integer id);
	
	/**
	 * 
	* @Title: deleteCompanyIndexModelNewsByIds 
	* @Description: 根据id批量删除CompanyIndexModelNews
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-5-18
	* @user by wangzx
	 */
	void deleteCompanyIndexModelNewsByIds(Integer[] ids);
	
	/**
	 * 
	* @Title: findCompanyIndexModelNewsById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-5-18
	* @user by wangzx
	 */
	CompanyIndexModelNews findCompanyIndexModelNewsById(Integer id);
	
	/**
	 * 
	* @Title: findCompanyIndexModelNewsByPage 
	* @Description: 分页查询
	* @return
	* @return List<CompanyIndexModelNews>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-5-18
	* @user by wangzx
	 */
	List<CompanyIndexModelNews> findCompanyIndexModelNewsByPage(CompanyIndexModelNews search);
}