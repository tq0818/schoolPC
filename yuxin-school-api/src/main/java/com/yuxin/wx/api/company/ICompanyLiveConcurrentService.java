package com.yuxin.wx.api.company;

import java.util.List;
import java.util.Map;

import com.yuxin.wx.model.company.CompanyLiveConcurrent;
/**
 * Service Interface:CompanyLiveConcurrent
 * @author wang.zx
 * @date 2016-3-28
 */
public interface ICompanyLiveConcurrentService  {
	/**
	 * 
	* @Title: saveCompanyLiveConcurrent
	* @Description: 新增CompanyLiveConcurrent
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-3-28
	* @user by wangzx
	 */
	void insert(CompanyLiveConcurrent entity);
	
	/**
	 * 
	* @Title: batchSaveCompanyLiveConcurrent 
	* @Description: 批量新增CompanyLiveConcurrent
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-3-28
	* @user by wangzx
	 */
	void batchInsert(List<CompanyLiveConcurrent> list);
	
	/**
	 * 
	* @Title: updateCompanyLiveConcurrent 
	* @Description: 编辑CompanyLiveConcurrent
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-3-28
	* @user by wangzx
	 */
	void update(CompanyLiveConcurrent entity);
	
	/**
	 * 
	* @Title: deleteCompanyLiveConcurrentById 
	* @Description: 根据id删除CompanyLiveConcurrent
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-3-28
	* @user by wangzx
	 */
	void deleteCompanyLiveConcurrentById(Integer id);
	
	/**
	 * 
	* @Title: deleteCompanyLiveConcurrentByIds 
	* @Description: 根据id批量删除CompanyLiveConcurrent
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-3-28
	* @user by wangzx
	 */
	void deleteCompanyLiveConcurrentByIds(Integer[] ids);
	
	/**
	 * 
	* @Title: findCompanyLiveConcurrentById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-3-28
	* @user by wangzx
	 */
	CompanyLiveConcurrent findCompanyLiveConcurrentById(Integer id);
	
	/**
	 * 
	* @Title: findCompanyLiveConcurrentByPage 
	* @Description: 分页查询
	* @return
	* @return List<CompanyLiveConcurrent>    返回类型 
	* @throws 
	* @exception 
	* @date 2016-3-28
	* @user by wangzx
	 */
	List<CompanyLiveConcurrent> findCompanyLiveConcurrentByPage(CompanyLiveConcurrent search);
	
	/**
	 * 
	 * Class Name: ICompanyLiveConcurrentService.java
	 * @Description: 查询本月并发
	 * @author 周文斌
	 * @date 2016-3-28 下午4:02:12
	 * @version 1.0
	 * @param param
	 * @return
	 */
	CompanyLiveConcurrent findLiveByComidAndDate(Map<String, Object> param);
	
	/**
	 * 
	 * Class Name: ICompanyLiveConcurrentService.java
	 * @Description: 查询日期以后的集合
	 * @author 周文斌
	 * @date 2016-3-29 上午11:07:40
	 * @version 1.0
	 * @param param
	 * @return
	 */
	List<CompanyLiveConcurrent> findMoreByComidAndDate(Map<String, Object> param);
	
	/**
	 * 
	 * Class Name: ICompanyLiveConcurrentService.java
	 * @Description: 修改基础并发 
	 * @author 周文斌
	 * @date 2016-3-29 下午4:07:14
	 * @version 1.0
	 * @param param
	 */
	void updatelive(Map<String, Object> param);
}