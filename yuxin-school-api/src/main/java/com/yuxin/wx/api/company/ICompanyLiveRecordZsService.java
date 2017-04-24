package com.yuxin.wx.api.company;

import java.util.List;

import com.yuxin.wx.model.company.CompanyLiveRecordZs;
/**
 * Service Interface:CompanyLiveRecordZs
 * @author wang.zx
 * @date 2015-12-7
 */
public interface ICompanyLiveRecordZsService  {
	/**
	 * 
	* @Title: saveCompanyLiveRecordZs
	* @Description: 新增CompanyLiveRecordZs
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-12-7
	* @user by wangzx
	 */
	void insert(CompanyLiveRecordZs entity);
	
	/**
	 * 
	* @Title: batchSaveCompanyLiveRecordZs 
	* @Description: 批量新增CompanyLiveRecordZs
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-12-7
	* @user by wangzx
	 */
	void batchInsert(List<CompanyLiveRecordZs> list);
	
	/**
	 * 
	* @Title: updateCompanyLiveRecordZs 
	* @Description: 编辑CompanyLiveRecordZs
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-12-7
	* @user by wangzx
	 */
	void update(CompanyLiveRecordZs entity);
	
	/**
	 * 
	* @Title: deleteCompanyLiveRecordZsById 
	* @Description: 根据id删除CompanyLiveRecordZs
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-12-7
	* @user by wangzx
	 */
	void deleteCompanyLiveRecordZsById(Integer id);
	
	/**
	 * 
	* @Title: deleteCompanyLiveRecordZsByIds 
	* @Description: 根据id批量删除CompanyLiveRecordZs
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-12-7
	* @user by wangzx
	 */
	void deleteCompanyLiveRecordZsByIds(Integer[] ids);
	
	/**
	 * 
	* @Title: findCompanyLiveRecordZsById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-12-7
	* @user by wangzx
	 */
	CompanyLiveRecordZs findCompanyLiveRecordZsById(Integer id);
	
	/**
	 * 
	* @Title: findCompanyLiveRecordZsByPage 
	* @Description: 分页查询
	* @return
	* @return List<CompanyLiveRecordZs>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-12-7
	* @user by wangzx
	 */
	List<CompanyLiveRecordZs> findCompanyLiveRecordZsByPage(CompanyLiveRecordZs search);
	
	/**
	 * 
	 * Class Name: ICompanyLiveRecordZsService.java
	 * @Description: 查询资源
	 * @author 周文斌
	 * @date 2015-12-7 下午7:12:37
	 * @version 1.0
	 * @param name
	 * @return
	 */
	List<CompanyLiveRecordZs> findRecord(CompanyLiveRecordZs lrzs);
	
	/**
	 * 
	 * Class Name: ICompanyLiveRecordZsService.java
	 * @Description: 查询资源总数
	 * @author 周文斌
	 * @date 2015-12-7 下午7:12:37
	 * @version 1.0
	 * @param name
	 * @return
	 */
	Integer findCountRecord(CompanyLiveRecordZs lrzs);
	
	/**
	 * 
	 * Class Name: ICompanyLiveRecordZsService.java
	 * @Description: 根据recordID 查询name
	 * @author 周文斌
	 * @date 2015-12-11 下午6:59:57
	 * @version 1.0
	 * @param recordId
	 * @return
	 */
	String findNameByRecordId(String recordId);
}