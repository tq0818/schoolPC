package com.yuxin.wx.api.company;

import java.util.List;

import com.yuxin.wx.model.company.CompanyIndexModelCustom;
/**
 * Service Interface:CompanyIndexModelCustom
 * @author chopin
 * @date 2015-5-18
 */
public interface ICompanyIndexModelCustomService  {
	/**
	 * 
	* @Title: saveCompanyIndexModelCustom
	* @Description: 新增CompanyIndexModelCustom
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-5-18
	* @user by wangzx
	 */
	void insert(CompanyIndexModelCustom entity);
	
	/**
	 * 
	* @Title: batchSaveCompanyIndexModelCustom 
	* @Description: 批量新增CompanyIndexModelCustom
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-5-18
	* @user by wangzx
	 */
	void batchInsert(List<CompanyIndexModelCustom> list);
	
	/**
	 * 
	* @Title: updateCompanyIndexModelCustom 
	* @Description: 编辑CompanyIndexModelCustom
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-5-18
	* @user by wangzx
	 */
	void update(CompanyIndexModelCustom entity);
	
	/**
	 * 
	* @Title: deleteCompanyIndexModelCustomById 
	* @Description: 根据id删除CompanyIndexModelCustom
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-5-18
	* @user by wangzx
	 */
	void deleteCompanyIndexModelCustomById(Integer id);
	
	/**
	 * 
	* @Title: deleteCompanyIndexModelCustomByIds 
	* @Description: 根据id批量删除CompanyIndexModelCustom
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-5-18
	* @user by wangzx
	 */
	void deleteCompanyIndexModelCustomByIds(Integer[] ids);
	
	/**
	 * 
	* @Title: findCompanyIndexModelCustomById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-5-18
	* @user by wangzx
	 */
	CompanyIndexModelCustom findCompanyIndexModelCustomById(Integer id);
	
	/**
	 * 
	* @Title: findCompanyIndexModelCustomByPage 
	* @Description: 分页查询
	* @return
	* @return List<CompanyIndexModelCustom>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-5-18
	* @user by wangzx
	 */
	List<CompanyIndexModelCustom> findCompanyIndexModelCustomByPage(CompanyIndexModelCustom search);
}