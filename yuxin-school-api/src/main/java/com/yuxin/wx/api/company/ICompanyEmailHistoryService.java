package com.yuxin.wx.api.company;

import java.util.List;
import com.yuxin.wx.model.company.CompanyEmailHistory;
/**
 * Service Interface:CompanyEmailHistory
 * @author chopin
 * @date 2015-4-23
 */
public interface ICompanyEmailHistoryService  {
	/**
	 * 
	* @Title: saveCompanyEmailHistory
	* @Description: 新增CompanyEmailHistory
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-4-23
	* @user by wangzx
	 */
	void insert(CompanyEmailHistory entity);
	
	/**
	 * 
	* @Title: batchSaveCompanyEmailHistory 
	* @Description: 批量新增CompanyEmailHistory
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-4-23
	* @user by wangzx
	 */
	void batchInsert(List<CompanyEmailHistory> list);
	
	/**
	 * 
	* @Title: updateCompanyEmailHistory 
	* @Description: 编辑CompanyEmailHistory
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-4-23
	* @user by wangzx
	 */
	void update(CompanyEmailHistory entity);
	
	/**
	 * 
	* @Title: deleteCompanyEmailHistoryById 
	* @Description: 根据id删除CompanyEmailHistory
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-4-23
	* @user by wangzx
	 */
	void deleteCompanyEmailHistoryById(Integer id);
	
	/**
	 * 
	* @Title: deleteCompanyEmailHistoryByIds 
	* @Description: 根据id批量删除CompanyEmailHistory
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-4-23
	* @user by wangzx
	 */
	void deleteCompanyEmailHistoryByIds(Integer[] ids);
	
	/**
	 * 
	* @Title: findCompanyEmailHistoryById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-4-23
	* @user by wangzx
	 */
	CompanyEmailHistory findCompanyEmailHistoryById(Integer id);
	
	/**
	 * 
	* @Title: findCompanyEmailHistoryByPage 
	* @Description: 分页查询
	* @return
	* @return List<CompanyEmailHistory>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-4-23
	* @user by wangzx
	 */
	List<CompanyEmailHistory> findCompanyEmailHistoryByPage(CompanyEmailHistory search);
	
	/**
	 * 
	 * Class Name: ICompanyEmailHistoryService.java
	 * @Description: 定时任务 查询 昨天使用的邮件数量 根据 公司id
	 * @author 周文斌
	 * @date 2015-5-21 下午6:07:22
	 * @version 1.0
	 * @param companyId
	 * @return
	 */
	Integer findEmailCount(CompanyEmailHistory email);

	Integer findByUserCount(CompanyEmailHistory cmh);
}