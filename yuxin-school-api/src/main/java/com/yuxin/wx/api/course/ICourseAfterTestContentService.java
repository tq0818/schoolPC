package com.yuxin.wx.api.course;

import java.util.List;

import com.yuxin.wx.model.course.CourseAfterTestContent;

/**
 * Service Interface:CourseAfterTestContent
 * @author chopin
 * @date 2016-9-1
 */
public interface ICourseAfterTestContentService  {
	/**
	 * 
	* @Title: saveCourseAfterTestContent
	* @Description: 新增CourseAfterTestContent
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-9-1
	* @user by wangzx
	 */
	void insert(CourseAfterTestContent entity);
	
	/**
	 * 
	* @Title: batchSaveCourseAfterTestContent 
	* @Description: 批量新增CourseAfterTestContent
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-9-1
	* @user by wangzx
	 */
	void batchInsert(List<CourseAfterTestContent> list);
	
	/**
	 * 
	* @Title: updateCourseAfterTestContent 
	* @Description: 编辑CourseAfterTestContent
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-9-1
	* @user by wangzx
	 */
	void update(CourseAfterTestContent entity);
	
	/**
	 * 
	* @Title: deleteCourseAfterTestContentById 
	* @Description: 根据id删除CourseAfterTestContent
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-9-1
	* @user by wangzx
	 */
	void deleteCourseAfterTestContentById(Integer id);
	
	/**
	 * 
	* @Title: deleteCourseAfterTestContentByIds 
	* @Description: 根据id批量删除CourseAfterTestContent
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-9-1
	* @user by wangzx
	 */
	void deleteCourseAfterTestContentByIds(Integer[] ids);
	
	/**
	 * 
	* @Title: findCourseAfterTestContentById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-9-1
	* @user by wangzx
	 */
	CourseAfterTestContent findCourseAfterTestContentById(Integer id);
	
	/**
	 * 
	* @Title: findCourseAfterTestContentByPage 
	* @Description: 分页查询
	* @return
	* @return List<CourseAfterTestContent>    返回类型 
	* @throws 
	* @exception 
	* @date 2016-9-1
	* @user by wangzx
	 */
	List<CourseAfterTestContent> findCourseAfterTestContentByPage(CourseAfterTestContent search);
	
	List<CourseAfterTestContent> findContentListByTestId(Integer testId);
	
	List<CourseAfterTestContent> findContentListByTestId2(Integer testId);
	
	void deleteByTestId(Integer testId);
	void deleteByTestId2(Integer testId);
}