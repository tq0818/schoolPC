package com.yuxin.wx.api.company;

import java.util.List;

import com.yuxin.wx.model.company.CompanyLiveStaticRecord;
/**
 * Service Interface:CompanyLiveStaticRecord
 * @author wang.zx
 * @date 2017-1-4
 */
public interface ICompanyLiveStaticRecordService  {
	/**
	 * 
	* @Title: saveCompanyLiveStaticRecord
	* @Description: 新增CompanyLiveStaticRecord
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2017-1-4
	* @user by wangzx
	 */
	void insert(CompanyLiveStaticRecord entity);
	
	/**
	 * 
	* @Title: batchSaveCompanyLiveStaticRecord 
	* @Description: 批量新增CompanyLiveStaticRecord
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2017-1-4
	* @user by wangzx
	 */
	void batchInsert(List<CompanyLiveStaticRecord> list);
	
	/**
	 * 
	* @Title: updateCompanyLiveStaticRecord 
	* @Description: 编辑CompanyLiveStaticRecord
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2017-1-4
	* @user by wangzx
	 */
	void update(CompanyLiveStaticRecord entity);
	
	/**
	 * 
	* @Title: deleteCompanyLiveStaticRecordById 
	* @Description: 根据id删除CompanyLiveStaticRecord
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2017-1-4
	* @user by wangzx
	 */
	void deleteCompanyLiveStaticRecordById(Integer id);
	
	/**
	 * 
	* @Title: deleteCompanyLiveStaticRecordByIds 
	* @Description: 根据id批量删除CompanyLiveStaticRecord
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2017-1-4
	* @user by wangzx
	 */
	void deleteCompanyLiveStaticRecordByIds(Integer[] ids);
	
	/**
	 * 
	* @Title: findCompanyLiveStaticRecordById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2017-1-4
	* @user by wangzx
	 */
	CompanyLiveStaticRecord findCompanyLiveStaticRecordById(Integer id);
	
	/**
	 * 
	* @Title: findCompanyLiveStaticRecordByPage 
	* @Description: 分页查询
	* @return
	* @return List<CompanyLiveStaticRecord>    返回类型 
	* @throws 
	* @exception 
	* @date 2017-1-4
	* @user by wangzx
	 */
	List<CompanyLiveStaticRecord> findCompanyLiveStaticRecordByPage(CompanyLiveStaticRecord search);
}