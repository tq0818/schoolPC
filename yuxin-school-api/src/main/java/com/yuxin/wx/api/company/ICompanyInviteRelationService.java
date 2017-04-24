package com.yuxin.wx.api.company;

import java.util.List;

import com.yuxin.wx.model.company.CompanyInviteRelation;
/**
 * Service Interface:CompanyInviteRelation
 * @author chopin
 * @date 2016-7-29
 */
public interface ICompanyInviteRelationService  {
	/**
	 * 
	* @Title: saveCompanyInviteRelation
	* @Description: 新增CompanyInviteRelation
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-7-29
	* @user by wangzx
	 */
	void insert(CompanyInviteRelation entity);
	
	/**
	 * 
	* @Title: batchSaveCompanyInviteRelation 
	* @Description: 批量新增CompanyInviteRelation
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-7-29
	* @user by wangzx
	 */
	void batchInsert(List<CompanyInviteRelation> list);
	
	/**
	 * 
	* @Title: updateCompanyInviteRelation 
	* @Description: 编辑CompanyInviteRelation
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-7-29
	* @user by wangzx
	 */
	void update(CompanyInviteRelation entity);
	
	/**
	 * 
	* @Title: deleteCompanyInviteRelationById 
	* @Description: 根据id删除CompanyInviteRelation
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-7-29
	* @user by wangzx
	 */
	void deleteCompanyInviteRelationById(Integer id);
	
	/**
	 * 
	* @Title: deleteCompanyInviteRelationByIds 
	* @Description: 根据id批量删除CompanyInviteRelation
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-7-29
	* @user by wangzx
	 */
	void deleteCompanyInviteRelationByIds(Integer[] ids);
	
	/**
	 * 
	* @Title: findCompanyInviteRelationById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-7-29
	* @user by wangzx
	 */
	CompanyInviteRelation findCompanyInviteRelationById(Integer id);
	
	/**
	 * 
	* @Title: findCompanyInviteRelationByPage 
	* @Description: 分页查询
	* @return
	* @return List<CompanyInviteRelation>    返回类型 
	* @throws 
	* @exception 
	* @date 2016-7-29
	* @user by wangzx
	 */
	List<CompanyInviteRelation> findCompanyInviteRelationByPage(CompanyInviteRelation search);
}