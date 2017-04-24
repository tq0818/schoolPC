package com.yuxin.wx.api.company;

import java.util.List;

import com.yuxin.wx.model.company.CompanyLiveCoursewareZs;
/**
 * Service Interface:CompanyLiveCoursewareZs
 * @author wang.zx
 * @date 2015-11-28
 */
public interface ICompanyLiveCoursewareZsService  {
	/**
	 * 
	* @Title: saveCompanyLiveCoursewareZs
	* @Description: 新增CompanyLiveCoursewareZs
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-11-28
	* @user by wangzx
	 */
	void insert(CompanyLiveCoursewareZs entity);
	
	/**
	 * 
	* @Title: batchSaveCompanyLiveCoursewareZs 
	* @Description: 批量新增CompanyLiveCoursewareZs
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-11-28
	* @user by wangzx
	 */
	void batchInsert(List<CompanyLiveCoursewareZs> list);
	
	/**
	 * 
	* @Title: updateCompanyLiveCoursewareZs 
	* @Description: 编辑CompanyLiveCoursewareZs
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-11-28
	* @user by wangzx
	 */
	void update(CompanyLiveCoursewareZs entity);
	
	/**
	 * 
	* @Title: deleteCompanyLiveCoursewareZsById 
	* @Description: 根据id删除CompanyLiveCoursewareZs
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-11-28
	* @user by wangzx
	 */
	void deleteCompanyLiveCoursewareZsById(Integer id);
	
	/**
	 * 
	* @Title: deleteCompanyLiveCoursewareZsByIds 
	* @Description: 根据id批量删除CompanyLiveCoursewareZs
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-11-28
	* @user by wangzx
	 */
	void deleteCompanyLiveCoursewareZsByIds(Integer[] ids);
	
	/**
	 * 
	* @Title: findCompanyLiveCoursewareZsById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-11-28
	* @user by wangzx
	 */
	CompanyLiveCoursewareZs findCompanyLiveCoursewareZsById(Integer id);
	
	/**
	 * 
	* @Title: findCompanyLiveCoursewareZsByPage 
	* @Description: 分页查询
	* @return
	* @return List<CompanyLiveCoursewareZs>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-11-28
	* @user by wangzx
	 */
	List<CompanyLiveCoursewareZs> findCompanyLiveCoursewareZsByPage(CompanyLiveCoursewareZs search);
	
	/**
	 * 
	 * Class Name: ICompanyLiveCoursewareZsService.java
	 * @Description: 查询插拨件
	 * @author 周文斌
	 * @date 2015-12-4 下午3:07:09
	 * @version 1.0
	 * @param zs
	 * @return
	 */
	List<CompanyLiveCoursewareZs> findCourse(CompanyLiveCoursewareZs zs);
	
	/**
	 * 
	 * Class Name: ICompanyLiveCoursewareZsService.java
	 * @Description: 查询插拨件总数
	 * @author 周文斌
	 * @date 2015-12-4 下午3:07:09
	 * @version 1.0
	 * @param zs
	 * @return
	 */
	Integer findCountCourse(CompanyLiveCoursewareZs zs);
}