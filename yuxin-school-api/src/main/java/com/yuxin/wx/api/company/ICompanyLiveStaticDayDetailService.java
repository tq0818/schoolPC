package com.yuxin.wx.api.company;

import java.util.List;
import java.util.Map;

import com.yuxin.wx.model.company.CompanyLiveStaticDayDetail;
/**
 * Service Interface:CompanyLiveStaticDayDetail
 * @author wang.zx
 * @date 2015-6-8
 */
public interface ICompanyLiveStaticDayDetailService  {
	/**
	 * 
	* @Title: saveCompanyLiveStaticDayDetail
	* @Description: 新增CompanyLiveStaticDayDetail
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-6-8
	* @user by wangzx
	 */
	void insert(CompanyLiveStaticDayDetail entity);
	
	/**
	 * 
	* @Title: batchSaveCompanyLiveStaticDayDetail 
	* @Description: 批量新增CompanyLiveStaticDayDetail
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-6-8
	* @user by wangzx
	 */
	void batchInsert(List<CompanyLiveStaticDayDetail> list);
	
	/**
	 * 
	* @Title: updateCompanyLiveStaticDayDetail 
	* @Description: 编辑CompanyLiveStaticDayDetail
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-6-8
	* @user by wangzx
	 */
	void update(CompanyLiveStaticDayDetail entity);
	
	/**
	 * 
	* @Title: deleteCompanyLiveStaticDayDetailById 
	* @Description: 根据id删除CompanyLiveStaticDayDetail
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-6-8
	* @user by wangzx
	 */
	void deleteCompanyLiveStaticDayDetailById(Integer id);
	
	/**
	 * 
	* @Title: deleteCompanyLiveStaticDayDetailByIds 
	* @Description: 根据id批量删除CompanyLiveStaticDayDetail
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-6-8
	* @user by wangzx
	 */
	void deleteCompanyLiveStaticDayDetailByIds(Integer[] ids);
	
	/**
	 * 
	* @Title: findCompanyLiveStaticDayDetailById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-6-8
	* @user by wangzx
	 */
	CompanyLiveStaticDayDetail findCompanyLiveStaticDayDetailById(Integer id);
	
	/**
	 * 
	* @Title: findCompanyLiveStaticDayDetailByPage 
	* @Description: 分页查询
	* @return
	* @return List<CompanyLiveStaticDayDetail>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-6-8
	* @user by wangzx
	 */
	List<CompanyLiveStaticDayDetail> findCompanyLiveStaticDayDetailByPage(CompanyLiveStaticDayDetail search);
	
	/**
	 * 
	 * Class Name: ICompanyLiveStaticDayDetailService.java
	 * @Description: 根据id  日期 查询最大并发 
	 * @author 周文斌
	 * @date 2015-6-8 下午9:01:23
	 * @version 1.0
	 * @param companyId
	 * @return
	 */
	Integer findByCompanyId(CompanyLiveStaticDayDetail companyLiveStaticDayDetail);
	
	/**
	 * 
	 * Class Name: ICompanyLiveStaticDayDetailService.java
	 * @Description: 查询当月最大并发
	 * @author 周文斌
	 * @date 2015-7-13 下午7:10:05
	 * @version 1.0
	 * @param param
	 * @return
	 */
	Integer findByDateAndCompanyId(Map<String,Object> param);
}