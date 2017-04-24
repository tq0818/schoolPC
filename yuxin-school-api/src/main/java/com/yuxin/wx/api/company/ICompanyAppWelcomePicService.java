package com.yuxin.wx.api.company;

import java.util.List;

import com.yuxin.wx.model.company.CompanyAppWelcomePic;
/**
 * Service Interface:CompanyAppWelcomePic
 * @author chopin
 * @date 2016-5-27
 */
public interface ICompanyAppWelcomePicService  {
	/**
	 * 
	* @Title: saveCompanyAppWelcomePic
	* @Description: 新增CompanyAppWelcomePic
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-5-27
	* @user by wangzx
	 */
	void insert(CompanyAppWelcomePic entity);
	
	/**
	 * 
	* @Title: batchSaveCompanyAppWelcomePic 
	* @Description: 批量新增CompanyAppWelcomePic
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-5-27
	* @user by wangzx
	 */
	void batchInsert(List<CompanyAppWelcomePic> list);
	
	/**
	 * 
	* @Title: updateCompanyAppWelcomePic 
	* @Description: 编辑CompanyAppWelcomePic
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-5-27
	* @user by wangzx
	 */
	void update(CompanyAppWelcomePic entity);
	
	/**
	 * 
	* @Title: deleteCompanyAppWelcomePicById 
	* @Description: 根据id删除CompanyAppWelcomePic
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-5-27
	* @user by wangzx
	 */
	void deleteCompanyAppWelcomePicById(Integer id);
	
	/**
	 * 
	* @Title: deleteCompanyAppWelcomePicByIds 
	* @Description: 根据id批量删除CompanyAppWelcomePic
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-5-27
	* @user by wangzx
	 */
	void deleteCompanyAppWelcomePicByIds(Integer[] ids);
	
	/**
	 * 
	* @Title: findCompanyAppWelcomePicById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-5-27
	* @user by wangzx
	 */
	CompanyAppWelcomePic findCompanyAppWelcomePicById(Integer id);
	
	/**
	 * 
	* @Title: findCompanyAppWelcomePicByPage 
	* @Description: 分页查询
	* @return
	* @return List<CompanyAppWelcomePic>    返回类型 
	* @throws 
	* @exception 
	* @date 2016-5-27
	* @user by wangzx
	 */
	List<CompanyAppWelcomePic> findCompanyAppWelcomePicByPage(CompanyAppWelcomePic search);
	
	/**
	 * 
	 * Class Name: ICompanyAppWelcomePicService.java
	 * @Description: 查询公司启动图
	 * @author zhang.zx
	 * @date 2016年5月30日 上午11:42:10
	 * @modifier
	 * @modify-date 2016年5月30日 上午11:42:10
	 * @version 1.0
	 * @param companyId
	 * @return
	 */
	CompanyAppWelcomePic findByparam(Integer companyId);
}