package com.yuxin.wx.api.company;

import java.util.List;

import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.model.company.CompanyRechargePatch;
/**
 * Service Interface:CompanyRechargePatch
 * @author chopin
 * @date 2017-4-10
 */
import com.yuxin.wx.vo.company.CompanyRechargePatchVo;
public interface ICompanyRechargePatchService  {
	/**
	 * 
	* @Title: saveCompanyRechargePatch
	* @Description: 新增CompanyRechargePatch
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2017-4-10
	* @user by wangzx
	 */
	void insert(CompanyRechargePatch entity);
	
	/**
	 * 
	* @Title: batchSaveCompanyRechargePatch 
	* @Description: 批量新增CompanyRechargePatch
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2017-4-10
	* @user by wangzx
	 */
	void batchInsert(List<CompanyRechargePatch> list);
	
	/**
	 * 
	* @Title: updateCompanyRechargePatch 
	* @Description: 编辑CompanyRechargePatch
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2017-4-10
	* @user by wangzx
	 */
	void update(CompanyRechargePatch entity);
	
	/**
	 * 
	* @Title: deleteCompanyRechargePatchById 
	* @Description: 根据id删除CompanyRechargePatch
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2017-4-10
	* @user by wangzx
	 */
	void deleteCompanyRechargePatchById(Integer id);
	
	/**
	 * 
	* @Title: deleteCompanyRechargePatchByIds 
	* @Description: 根据id批量删除CompanyRechargePatch
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2017-4-10
	* @user by wangzx
	 */
	void deleteCompanyRechargePatchByIds(Integer[] ids);
	
	/**
	 * 
	* @Title: findCompanyRechargePatchById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2017-4-10
	* @user by wangzx
	 */
	CompanyRechargePatch findCompanyRechargePatchById(Integer id);
	
	/**
	 * 
	* @Title: findCompanyRechargePatchByPage 
	* @Description: 分页查询
	* @return
	* @return List<CompanyRechargePatch>    返回类型 
	* @throws 
	* @exception 
	* @date 2017-4-10
	* @user by wangzx
	 */
	List<CompanyRechargePatch> findCompanyRechargePatchByPage(CompanyRechargePatch search);
	
	int queryPrefixCount(CompanyRechargePatch search);
	
	boolean createRechargeLibs(CompanyRechargePatch crp);
	
	PageFinder<CompanyRechargePatchVo> queryPatchList(CompanyRechargePatchVo search);
	
	List<CompanyRechargePatchVo> queryPatchsList(CompanyRechargePatchVo search);
}