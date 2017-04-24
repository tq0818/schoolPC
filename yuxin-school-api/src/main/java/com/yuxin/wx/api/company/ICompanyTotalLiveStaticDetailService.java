package com.yuxin.wx.api.company;

import java.util.List;

import com.yuxin.wx.model.company.CompanyTotalLiveStaticDetail;
/**
 * Service Interface:CompanyTotalLiveStaticDetail
 * @author wang.zx
 * @date 2016-2-29
 */
public interface ICompanyTotalLiveStaticDetailService  {
	/**
	 * 
	* @Title: saveCompanyTotalLiveStaticDetail
	* @Description: 新增CompanyTotalLiveStaticDetail
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-2-29
	* @user by wangzx
	 */
	void insert(CompanyTotalLiveStaticDetail entity);
	
	/**
	 * 
	* @Title: batchSaveCompanyTotalLiveStaticDetail 
	* @Description: 批量新增CompanyTotalLiveStaticDetail
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-2-29
	* @user by wangzx
	 */
	void batchInsert(List<CompanyTotalLiveStaticDetail> list);
	
	/**
	 * 
	* @Title: updateCompanyTotalLiveStaticDetail 
	* @Description: 编辑CompanyTotalLiveStaticDetail
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-2-29
	* @user by wangzx
	 */
	void update(CompanyTotalLiveStaticDetail entity);
	
	/**
	 * 
	* @Title: deleteCompanyTotalLiveStaticDetailById 
	* @Description: 根据id删除CompanyTotalLiveStaticDetail
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-2-29
	* @user by wangzx
	 */
	void deleteCompanyTotalLiveStaticDetailById(Integer id);
	
	/**
	 * 
	* @Title: deleteCompanyTotalLiveStaticDetailByIds 
	* @Description: 根据id批量删除CompanyTotalLiveStaticDetail
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-2-29
	* @user by wangzx
	 */
	void deleteCompanyTotalLiveStaticDetailByIds(Integer[] ids);
	
	/**
	 * 
	* @Title: findCompanyTotalLiveStaticDetailById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-2-29
	* @user by wangzx
	 */
	CompanyTotalLiveStaticDetail findCompanyTotalLiveStaticDetailById(Integer id);
	
	/**
	 * 
	* @Title: findCompanyTotalLiveStaticDetailByPage 
	* @Description: 分页查询
	* @return
	* @return List<CompanyTotalLiveStaticDetail>    返回类型 
	* @throws 
	* @exception 
	* @date 2016-2-29
	* @user by wangzx
	 */
	List<CompanyTotalLiveStaticDetail> findCompanyTotalLiveStaticDetailByPage(CompanyTotalLiveStaticDetail search);
}