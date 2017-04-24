package com.yuxin.wx.api.company;

import java.util.List;

import com.yuxin.wx.model.company.CompanyFootInfo;
/**
 * Service Interface:CompanyFootInfo
 * @author chopin
 * @date 2016-2-29
 */
public interface ICompanyFootInfoService  {
	/**
	 * 
	* @Title: saveCompanyFootInfo
	* @Description: 新增CompanyFootInfo
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-2-29
	* @user by wangzx
	 */
	void insert(CompanyFootInfo entity);
	
	/**
	 * 
	* @Title: batchSaveCompanyFootInfo 
	* @Description: 批量新增CompanyFootInfo
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-2-29
	* @user by wangzx
	 */
	void batchInsert(List<CompanyFootInfo> list);
	
	/**
	 * 
	* @Title: updateCompanyFootInfo 
	* @Description: 编辑CompanyFootInfo
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-2-29
	* @user by wangzx
	 */
	void update(CompanyFootInfo entity);
	
	/**
	 * 
	* @Title: deleteCompanyFootInfoById 
	* @Description: 根据id删除CompanyFootInfo
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-2-29
	* @user by wangzx
	 */
	void deleteCompanyFootInfoById(Integer id);
	
	/**
	 * 
	* @Title: deleteCompanyFootInfoByIds 
	* @Description: 根据id批量删除CompanyFootInfo
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-2-29
	* @user by wangzx
	 */
	void deleteCompanyFootInfoByIds(Integer[] ids);
	
	/**
	 * 
	* @Title: findCompanyFootInfoById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-2-29
	* @user by wangzx
	 */
	CompanyFootInfo findCompanyFootInfoById(Integer id);
	
	/**
	 * 
	* @Title: findCompanyFootInfoByPage 
	* @Description: 分页查询
	* @return
	* @return List<CompanyFootInfo>    返回类型 
	* @throws 
	* @exception 
	* @date 2016-2-29
	* @user by wangzx
	 */
	List<CompanyFootInfo> findCompanyFootInfoByPage(CompanyFootInfo search);
	
	/**
	 * 
	* @Title: findByCompanyId 
	* @Description: 查询公司备案信息
	* @return
	* @return List<CompanyFootInfo>    返回类型 
	* @throws 
	* @exception 
	* @date 2016-2-29
	* @user by zhang.zx
	 */
	CompanyFootInfo findByCompanyId(Integer companyId);
	
	
	/**
	 * wz
	 * 根据公司id修改页尾信息
	 * @param entity
	 */
	void updateByCompanyId(CompanyFootInfo entity);
}