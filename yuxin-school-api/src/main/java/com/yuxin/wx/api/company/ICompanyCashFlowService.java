package com.yuxin.wx.api.company;

import java.util.List;

import com.yuxin.wx.model.company.CompanyCashFlow;
/**
 * Service Interface:CompanyCashFlow
 * @author chopin
 * @date 2016-5-17
 */
public interface ICompanyCashFlowService  {
	/**
	 * 
	* @Title: saveCompanyCashFlow
	* @Description: 新增CompanyCashFlow
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-5-17
	* @user by wangzx
	 */
	void insert(CompanyCashFlow entity);
	
	/**
	 * 
	* @Title: batchSaveCompanyCashFlow 
	* @Description: 批量新增CompanyCashFlow
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-5-17
	* @user by wangzx
	 */
	void batchInsert(List<CompanyCashFlow> list);
	
	/**
	 * 
	* @Title: updateCompanyCashFlow 
	* @Description: 编辑CompanyCashFlow
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-5-17
	* @user by wangzx
	 */
	void update(CompanyCashFlow entity);
	
	/**
	 * 
	* @Title: deleteCompanyCashFlowById 
	* @Description: 根据id删除CompanyCashFlow
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-5-17
	* @user by wangzx
	 */
	void deleteCompanyCashFlowById(Integer id);
	
	/**
	 * 
	* @Title: deleteCompanyCashFlowByIds 
	* @Description: 根据id批量删除CompanyCashFlow
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-5-17
	* @user by wangzx
	 */
	void deleteCompanyCashFlowByIds(Integer[] ids);
	
	/**
	 * 
	* @Title: findCompanyCashFlowById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-5-17
	* @user by wangzx
	 */
	CompanyCashFlow findCompanyCashFlowById(Integer id);
	
	/**
	 * 
	* @Title: findCompanyCashFlowByPage 
	* @Description: 分页查询
	* @return
	* @return List<CompanyCashFlow>    返回类型 
	* @throws 
	* @exception 
	* @date 2016-5-17
	* @user by wangzx
	 */
	List<CompanyCashFlow> findCompanyCashFlowByPage(CompanyCashFlow search);
}