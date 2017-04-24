package com.yuxin.wx.api.company;

import java.util.List;

import com.yuxin.wx.model.company.CompanyIndexModelClasstype;
/**
 * Service Interface:CompanyIndexModelClasstype
 * @author chopin
 * @date 2015-5-18
 */
public interface ICompanyIndexModelClasstypeService  {
	/**
	 * 
	* @Title: saveCompanyIndexModelClasstype
	* @Description: 新增CompanyIndexModelClasstype
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-5-18
	* @user by wangzx
	 */
	void insert(CompanyIndexModelClasstype entity);
	
	/**
	 * 
	* @Title: batchSaveCompanyIndexModelClasstype 
	* @Description: 批量新增CompanyIndexModelClasstype
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-5-18
	* @user by wangzx
	 */
	void batchInsert(List<CompanyIndexModelClasstype> list);
	
	/**
	 * 
	* @Title: updateCompanyIndexModelClasstype 
	* @Description: 编辑CompanyIndexModelClasstype
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-5-18
	* @user by wangzx
	 */
	void update(CompanyIndexModelClasstype entity);
	
	/**
	 * 
	* @Title: deleteCompanyIndexModelClasstypeById 
	* @Description: 根据id删除CompanyIndexModelClasstype
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-5-18
	* @user by wangzx
	 */
	void deleteCompanyIndexModelClasstypeById(Integer id);
	
	/**
	 * 
	* @Title: deleteCompanyIndexModelClasstypeByIds 
	* @Description: 根据id批量删除CompanyIndexModelClasstype
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-5-18
	* @user by wangzx
	 */
	void deleteCompanyIndexModelClasstypeByIds(Integer[] ids);
	
	/**
	 * 
	* @Title: findCompanyIndexModelClasstypeById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-5-18
	* @user by wangzx
	 */
	CompanyIndexModelClasstype findCompanyIndexModelClasstypeById(Integer id);
	
	/**
	 * 
	* @Title: findCompanyIndexModelClasstypeByPage 
	* @Description: 分页查询
	* @return
	* @return List<CompanyIndexModelClasstype>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-5-18
	* @user by wangzx
	 */
	List<CompanyIndexModelClasstype> findCompanyIndexModelClasstypeByPage(CompanyIndexModelClasstype search);
}