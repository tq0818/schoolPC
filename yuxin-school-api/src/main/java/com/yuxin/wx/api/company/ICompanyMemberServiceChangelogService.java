package com.yuxin.wx.api.company;

import java.util.List;

import com.yuxin.wx.model.company.CompanyMemberServiceChangelog;
/**
 * Service Interface:CompanyMemberServiceChangelog
 * @author wang.zx
 * @date 2015-6-25
 */
public interface ICompanyMemberServiceChangelogService  {
	/**
	 * 
	* @Title: saveCompanyMemberServiceChangelog
	* @Description: 新增CompanyMemberServiceChangelog
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-6-25
	* @user by wangzx
	 */
	void insert(CompanyMemberServiceChangelog entity);
	
	/**
	 * 
	* @Title: batchSaveCompanyMemberServiceChangelog 
	* @Description: 批量新增CompanyMemberServiceChangelog
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-6-25
	* @user by wangzx
	 */
	void batchInsert(List<CompanyMemberServiceChangelog> list);
	
	/**
	 * 
	* @Title: updateCompanyMemberServiceChangelog 
	* @Description: 编辑CompanyMemberServiceChangelog
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-6-25
	* @user by wangzx
	 */
	void update(CompanyMemberServiceChangelog entity);
	
	/**
	 * 
	* @Title: deleteCompanyMemberServiceChangelogById 
	* @Description: 根据id删除CompanyMemberServiceChangelog
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-6-25
	* @user by wangzx
	 */
	void deleteCompanyMemberServiceChangelogById(Integer id);
	
	/**
	 * 
	* @Title: deleteCompanyMemberServiceChangelogByIds 
	* @Description: 根据id批量删除CompanyMemberServiceChangelog
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-6-25
	* @user by wangzx
	 */
	void deleteCompanyMemberServiceChangelogByIds(Integer[] ids);
	
	/**
	 * 
	* @Title: findCompanyMemberServiceChangelogById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-6-25
	* @user by wangzx
	 */
	CompanyMemberServiceChangelog findCompanyMemberServiceChangelogById(Integer id);
	
	/**
	 * 
	* @Title: findCompanyMemberServiceChangelogByPage 
	* @Description: 分页查询
	* @return
	* @return List<CompanyMemberServiceChangelog>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-6-25
	* @user by wangzx
	 */
	List<CompanyMemberServiceChangelog> findCompanyMemberServiceChangelogByPage(CompanyMemberServiceChangelog search);
}