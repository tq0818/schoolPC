package com.yuxin.wx.api.company;

import java.util.List;

import com.yuxin.wx.model.company.CompanyIndexModelItem;
/**
 * Service Interface:CompanyIndexModelItem
 * @author chopin
 * @date 2015-5-18
 */
public interface ICompanyIndexModelItemService  {
	/**
	 * 
	* @Title: saveCompanyIndexModelItem
	* @Description: 新增CompanyIndexModelItem
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-5-18
	* @user by wangzx
	 */
	void insert(CompanyIndexModelItem entity);
	
	/**
	 * 
	* @Title: batchSaveCompanyIndexModelItem 
	* @Description: 批量新增CompanyIndexModelItem
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-5-18
	* @user by wangzx
	 */
	void batchInsert(List<CompanyIndexModelItem> list);
	
	/**
	 * 
	* @Title: updateCompanyIndexModelItem 
	* @Description: 编辑CompanyIndexModelItem
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-5-18
	* @user by wangzx
	 */
	void update(CompanyIndexModelItem entity);
	
	/**
	 * 
	* @Title: deleteCompanyIndexModelItemById 
	* @Description: 根据id删除CompanyIndexModelItem
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-5-18
	* @user by wangzx
	 */
	void deleteCompanyIndexModelItemById(Integer id);
	
	/**
	 * 
	* @Title: deleteCompanyIndexModelItemByIds 
	* @Description: 根据id批量删除CompanyIndexModelItem
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-5-18
	* @user by wangzx
	 */
	void deleteCompanyIndexModelItemByIds(Integer[] ids);
	
	/**
	 * 
	* @Title: findCompanyIndexModelItemById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-5-18
	* @user by wangzx
	 */
	CompanyIndexModelItem findCompanyIndexModelItemById(Integer id);
	
	/**
	 * 
	* @Title: findCompanyIndexModelItemByPage 
	* @Description: 分页查询
	* @return
	* @return List<CompanyIndexModelItem>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-5-18
	* @user by wangzx
	 */
	List<CompanyIndexModelItem> findCompanyIndexModelItemByPage(CompanyIndexModelItem search);
	
	/**
	 * 
	* @Title: findByConfigId 
	* @Description: 分页查询
	* @return
	* @return List<CompanyIndexModelItem>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-5-18
	* @user by chopin
	 */
	public CompanyIndexModelItem findByConfigId(Integer configId);
}