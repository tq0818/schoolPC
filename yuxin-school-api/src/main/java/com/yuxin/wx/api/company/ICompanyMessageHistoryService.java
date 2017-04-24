package com.yuxin.wx.api.company;

import java.util.List;
import com.yuxin.wx.model.company.CompanyMessageHistory;
/**
 * Service Interface:CompanyMessageHistory
 * @author chopin
 * @date 2015-4-23
 */
public interface ICompanyMessageHistoryService  {
	/**
	 * 
	* @Title: saveCompanyMessageHistory
	* @Description: 新增CompanyMessageHistory
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-4-23
	* @user by wangzx
	 */
	void insert(CompanyMessageHistory entity);
	
	/**
	 * 
	* @Title: batchSaveCompanyMessageHistory 
	* @Description: 批量新增CompanyMessageHistory
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-4-23
	* @user by wangzx
	 */
	void batchInsert(List<CompanyMessageHistory> list);
	
	/**
	 * 
	* @Title: updateCompanyMessageHistory 
	* @Description: 编辑CompanyMessageHistory
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-4-23
	* @user by wangzx
	 */
	void update(CompanyMessageHistory entity);
	
	/**
	 * 
	* @Title: deleteCompanyMessageHistoryById 
	* @Description: 根据id删除CompanyMessageHistory
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-4-23
	* @user by wangzx
	 */
	void deleteCompanyMessageHistoryById(Integer id);
	
	/**
	 * 
	* @Title: deleteCompanyMessageHistoryByIds 
	* @Description: 根据id批量删除CompanyMessageHistory
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-4-23
	* @user by wangzx
	 */
	void deleteCompanyMessageHistoryByIds(Integer[] ids);
	
	/**
	 * 
	* @Title: findCompanyMessageHistoryById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-4-23
	* @user by wangzx
	 */
	CompanyMessageHistory findCompanyMessageHistoryById(Integer id);
	
	/**
	 * 
	* @Title: findCompanyMessageHistoryByPage 
	* @Description: 分页查询
	* @return
	* @return List<CompanyMessageHistory>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-4-23
	* @user by wangzx
	 */
	List<CompanyMessageHistory> findCompanyMessageHistoryByPage(CompanyMessageHistory search);
	
	/**
	 * 
	 * Class Name: ICompanyMessageHistoryService.java
	 * @Description: 定时任务 查询短信使用量
	 * @author 周文斌
	 * @date 2015-5-22 下午5:26:19
	 * @version 1.0
	 * @param cmh
	 * @return
	 */
	Integer findMessageByDateAndCompanyId(CompanyMessageHistory cmh);
	
	/**
	 * 
	 * Class Name: ICompanyMessageHistoryService.java
	 * @Description: 查询学员通知结果
	 * @author 周文斌
	 * @date 2015-6-3 下午8:42:58
	 * @version 1.0
	 * @param cmh
	 * @return
	 */
	String findResult(Integer id);

	/**
	 * 
	 * Class Name: IStudentService.java
	 * @Description: 查询失败人数
	 * @author 周文斌
	 * @date 2015-6-8 下午2:28:03
	 * @version 1.0
	 * @param companyMessageHistory
	 * @return
	 */
	Integer findByUserCount(CompanyMessageHistory companyMessageHistory);
}