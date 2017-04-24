package com.yuxin.wx.api.company;

import java.util.List;

import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.model.company.CompanyConfigCustompage;
/**
 * Service Interface:CompanyConfigCustompage
 * @author chopin
 * @date 2016-4-25
 */
public interface ICompanyConfigCustompageService  {
	/**
	 * 
	* @Title: saveCompanyConfigCustompage
	* @Description: 新增CompanyConfigCustompage
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-4-25
	* @user by wangzx
	 */
	void insert(CompanyConfigCustompage entity);
	
	/**
	 * 
	* @Title: batchSaveCompanyConfigCustompage 
	* @Description: 批量新增CompanyConfigCustompage
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-4-25
	* @user by wangzx
	 */
	void batchInsert(List<CompanyConfigCustompage> list);
	
	/**
	 * 
	* @Title: updateCompanyConfigCustompage 
	* @Description: 编辑CompanyConfigCustompage
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-4-25
	* @user by wangzx
	 */
	void update(CompanyConfigCustompage entity);
	
	/**
	 * 
	* @Title: deleteCompanyConfigCustompageById 
	* @Description: 根据id删除CompanyConfigCustompage
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-4-25
	* @user by wangzx
	 */
	void deleteCompanyConfigCustompageById(Integer id);
	
	/**
	 * 
	* @Title: deleteCompanyConfigCustompageByIds 
	* @Description: 根据id批量删除CompanyConfigCustompage
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-4-25
	* @user by wangzx
	 */
	void deleteCompanyConfigCustompageByIds(Integer[] ids);
	
	/**
	 * 
	* @Title: findCompanyConfigCustompageById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-4-25
	* @user by wangzx
	 */
	CompanyConfigCustompage findCompanyConfigCustompageById(Integer id);
	
	/**
	 * 
	* @Title: findCompanyConfigCustompageByPage 
	* @Description: 分页查询
	* @return
	* @return List<CompanyConfigCustompage>    返回类型 
	* @throws 
	* @exception 
	* @date 2016-4-25
	* @user by wangzx
	 */
	List<CompanyConfigCustompage> findCompanyConfigCustompageByPage(CompanyConfigCustompage search);
	
	/**
	 * 
	 * Class Name: ICompanyConfigCustompageService.java
	 * @Description: 查询自定义页面列表
	 * @author zhang.zx
	 * @date 2016年4月25日 下午4:32:27
	 * @modifier
	 * @modify-date 2016年4月25日 下午4:32:27
	 * @version 1.0
	 * @param search
	 * @return
	 */
	PageFinder<CompanyConfigCustompage> queryCompanyCustomList(CompanyConfigCustompage search);
	
	/**
	 * 
	 * Class Name: ICompanyConfigCustompageService.java
	 * @Description: 查询模板下页面数量
	 * @author zhang.zx
	 * @date 2016年4月27日 上午10:06:16
	 * @modifier
	 * @modify-date 2016年4月27日 上午10:06:16
	 * @version 1.0
	 * @param search
	 * @return
	 */
	Integer templeteCount(CompanyConfigCustompage search);
	
	/**
	 * 
	 * Class Name: ICompanyConfigCustompageService.java
	 * @Description: 条件查询页面列表
	 * @author zhang.zx
	 * @date 2016年4月27日 下午8:52:35
	 * @modifier
	 * @modify-date 2016年4月27日 下午8:52:35
	 * @version 1.0
	 * @param search
	 * @return
	 */
	List<CompanyConfigCustompage> queryCompanyCustomListByCondition(CompanyConfigCustompage search);

}