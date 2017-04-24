package com.yuxin.wx.api.course;

import java.util.List;

import com.yuxin.wx.model.course.CourseAfterTest;


/**
 * Service Interface:CourseAfterTest
 * @author chopin
 * @date 2016-9-1
 */
public interface ICourseAfterTestService  {
	/**
	 * 
	* @Title: saveCourseAfterTest
	* @Description: 新增CourseAfterTest
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-9-1
	* @user by wangzx
	 */
	void insert(CourseAfterTest entity);
	
	/**
	 * 
	* @Title: batchSaveCourseAfterTest 
	* @Description: 批量新增CourseAfterTest
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-9-1
	* @user by wangzx
	 */
	void batchInsert(List<CourseAfterTest> list);
	
	/**
	 * 
	* @Title: updateCourseAfterTest 
	* @Description: 编辑CourseAfterTest
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-9-1
	* @user by wangzx
	 */
	void update(CourseAfterTest entity);
	
	/**
	 * 
	* @Title: deleteCourseAfterTestById 
	* @Description: 根据id删除CourseAfterTest
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-9-1
	* @user by wangzx
	 */
	void deleteCourseAfterTestById(Integer id);
	
	/**
	 * 
	* @Title: deleteCourseAfterTestByIds 
	* @Description: 根据id批量删除CourseAfterTest
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-9-1
	* @user by wangzx
	 */
	void deleteCourseAfterTestByIds(Integer[] ids);
	
	/**
	 * 
	* @Title: findCourseAfterTestById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-9-1
	* @user by wangzx
	 */
	CourseAfterTest findCourseAfterTestById(Integer id);
	
	/**
	 * 
	* @Title: findCourseAfterTestByPage 
	* @Description: 分页查询
	* @return
	* @return List<CourseAfterTest>    返回类型 
	* @throws 
	* @exception 
	* @date 2016-9-1
	* @user by wangzx
	 */
	List<CourseAfterTest> findCourseAfterTestByPage(CourseAfterTest search);
	
	//根据章的id查询所有的测验
	List<CourseAfterTest> findTestListByChapterId(Integer chapterId);
	
	//改变测验所属的章
	CourseAfterTest mvTest(Integer chapterId,Integer testId);
}