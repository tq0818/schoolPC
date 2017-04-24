package com.yuxin.wx.api.company;

import java.util.List;
import java.util.Map;

import com.yuxin.wx.model.company.CompanyConfigCustompageGroup;
/**
 * Service Interface:CompanyConfigCustompageGroup
 * @author chopin
 * @date 2016-4-25
 */
public interface ICompanyConfigCustompageGroupService  {
	/**
	 * 
	* @Title: saveCompanyConfigCustompageGroup
	* @Description: 新增CompanyConfigCustompageGroup
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-4-25
	* @user by wangzx
	 */
	void insert(CompanyConfigCustompageGroup entity);
	
	/**
	 * 
	* @Title: batchSaveCompanyConfigCustompageGroup 
	* @Description: 批量新增CompanyConfigCustompageGroup
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-4-25
	* @user by wangzx
	 */
	void batchInsert(List<CompanyConfigCustompageGroup> list);
	
	/**
	 * 
	* @Title: updateCompanyConfigCustompageGroup 
	* @Description: 编辑CompanyConfigCustompageGroup
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-4-25
	* @user by wangzx
	 */
	void update(CompanyConfigCustompageGroup entity);
	
	/**
	 * 
	* @Title: deleteCompanyConfigCustompageGroupById 
	* @Description: 根据id删除CompanyConfigCustompageGroup
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-4-25
	* @user by wangzx
	 */
	void deleteCompanyConfigCustompageGroupById(Integer id);
	
	/**
	 * 
	* @Title: deleteCompanyConfigCustompageGroupByIds 
	* @Description: 根据id批量删除CompanyConfigCustompageGroup
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-4-25
	* @user by wangzx
	 */
	void deleteCompanyConfigCustompageGroupByIds(Integer[] ids);
	
	/**
	 * 
	* @Title: findCompanyConfigCustompageGroupById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-4-25
	* @user by wangzx
	 */
	CompanyConfigCustompageGroup findCompanyConfigCustompageGroupById(Integer id);
	
	/**
	 * 
	* @Title: findCompanyConfigCustompageGroupByPage 
	* @Description: 分页查询
	* @return
	* @return List<CompanyConfigCustompageGroup>    返回类型 
	* @throws 
	* @exception 
	* @date 2016-4-25
	* @user by wangzx
	 */
	List<CompanyConfigCustompageGroup> findCompanyConfigCustompageGroupByPage(CompanyConfigCustompageGroup search);
	
	/**
	 * 
	 * Class Name: ICompanyConfigCustompageGroupService.java
	 * @Description: 查询自定义分组列表
	 * @author zhang.zx
	 * @date 2016年4月25日 下午6:58:27
	 * @modifier
	 * @modify-date 2016年4月25日 下午6:58:27
	 * @version 1.0
	 * @return
	 */
	List<CompanyConfigCustompageGroup> queryCustomGroupList(CompanyConfigCustompageGroup search);
	
	/**
	 * 
	 * Class Name: ICompanyConfigCustompageGroupService.java
	 * @Description: 查询自定义分组
	 * @author zhang.zx
	 * @date 2016年4月27日 下午4:03:09
	 * @modifier
	 * @modify-date 2016年4月27日 下午4:03:09
	 * @version 1.0
	 * @param map
	 * @return
	 */
	List<CompanyConfigCustompageGroup> queryCustomGroupByCondition(Map<String, Object> map);
}