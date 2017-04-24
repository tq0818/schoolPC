package com.yuxin.wx.api.course;

import java.util.List;

import com.yuxin.wx.model.course.CourseVideoLookAuth;
/**
 * Service Interface:CourseVideoLookAuth
 * @author wang.zx
 * @date 2016-3-4
 */
public interface ICourseVideoLookAuthService  {
	/**
	 * 
	* @Title: saveCourseVideoLookAuth
	* @Description: 新增CourseVideoLookAuth
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-3-4
	* @user by wangzx
	 */
	void insert(CourseVideoLookAuth entity);
	
	/**
	 * 
	* @Title: batchSaveCourseVideoLookAuth 
	* @Description: 批量新增CourseVideoLookAuth
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-3-4
	* @user by wangzx
	 */
	void batchInsert(List<CourseVideoLookAuth> list);
	
	/**
	 * 
	* @Title: updateCourseVideoLookAuth 
	* @Description: 编辑CourseVideoLookAuth
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-3-4
	* @user by wangzx
	 */
	void update(CourseVideoLookAuth entity);
	
	/**
	 * 
	* @Title: deleteCourseVideoLookAuthById 
	* @Description: 根据id删除CourseVideoLookAuth
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-3-4
	* @user by wangzx
	 */
	void deleteCourseVideoLookAuthById(Integer id);
	
	/**
	 * 
	* @Title: deleteCourseVideoLookAuthByIds 
	* @Description: 根据id批量删除CourseVideoLookAuth
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-3-4
	* @user by wangzx
	 */
	void deleteCourseVideoLookAuthByIds(Integer[] ids);
	
	/**
	 * 
	* @Title: findCourseVideoLookAuthById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-3-4
	* @user by wangzx
	 */
	CourseVideoLookAuth findCourseVideoLookAuthById(Integer id);
	
	/**
	 * 
	* @Title: findCourseVideoLookAuthByPage 
	* @Description: 分页查询
	* @return
	* @return List<CourseVideoLookAuth>    返回类型 
	* @throws 
	* @exception 
	* @date 2016-3-4
	* @user by wangzx
	 */
	List<CourseVideoLookAuth> findCourseVideoLookAuthByPage(CourseVideoLookAuth search);

	/**
	 * 
	 * Class Name: ICourseVideoLookAuthService.java
	 * @Description: 查看权限根据公司id
	 * @author 周文斌
	 * @date 2016-3-7 下午12:03:54
	 * @version 1.0
	 * @param companyId
	 * @return
	 */
	CourseVideoLookAuth findAuthByCompanyId(Integer companyId);
}