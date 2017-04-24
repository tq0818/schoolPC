package com.yuxin.wx.api.course;

import java.util.List;

import com.yuxin.wx.model.course.CourseVideoLecture;
/**
 * Service Interface:CourseVideoLecture
 * @author wang.zx
 * @date 2014-12-5
 */
public interface ICourseVideoLectureService  {
	/**
	 * 
	* @Title: saveCourseVideoLecture
	* @Description: 新增CourseVideoLecture
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	void insert(CourseVideoLecture courseVideoLecture);
	
	/**
	 * 
	* @Title: batchSaveCourseVideoLecture 
	* @Description: 批量新增CourseVideoLecture
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	void batchInsert(List<CourseVideoLecture> courseVideoLecture);
	
	/**
	 * 
	* @Title: updateCourseVideoLecture 
	* @Description: 编辑CourseVideoLecture
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	void update(CourseVideoLecture courseVideoLecture);
	
	/**
	 * 
	* @Title: deleteCourseVideoLectureById 
	* @Description: 根据id删除CourseVideoLecture
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	void deleteCourseVideoLectureById(Integer id);
	
	/**
	 * 
	* @Title: deleteCourseVideoLectureByIds 
	* @Description: 根据id批量删除CourseVideoLecture
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	void deleteCourseVideoLectureByIds(Integer[] ids);
	
	/**
	 * 
	* @Title: findCourseVideoLectureById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	CourseVideoLecture findCourseVideoLectureById(Integer id);
	
	/**
	 * 
	* @Title: findCourseVideoLectureByPage 
	* @Description: 分页查询
	* @return
	* @return List<CourseVideoLecture>    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	List<CourseVideoLecture> findCourseVideoLectureByPage(CourseVideoLecture search);
	
	/**
	 * 
	* @Title: mvLecture 
	* @Description: 改变节所属章
	* @return
	* @return List<CourseVideoLecture>    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	public void mvLecture(Integer target,Integer lecture);
	
	/**
	 * 
	 * Class Name: ICourseVideoLectureService.java
	 * @Description:根据章的信息查询节的信息
	 * @author zhang.zx
	 * @date 2015年8月11日 下午4:55:34
	 * @modifier
	 * @modify-date 2015年8月11日 下午4:55:34
	 * @version 1.0
	 * @param chapterId
	 * @return
	 */
	List<CourseVideoLecture> queryVideoLectureByChapterId(Integer chapterId);
	
	/**
	 * 
	 * Class Name: ICourseVideoLectureService.java
	 * @Description: 根据ChapterId查询IDList
	 * @author dongshuai
	 * @date 2016年8月31日 下午5:29:03
	 * @modifier
	 * @modify-date 2016年8月31日 下午5:29:03
	 * @version 1.0
	 * @param id
	 * @return
	 */
	List<Integer> findIdByChapterId(Integer id);

	CourseVideoLecture findByChapterIdAndLecName(CourseVideoLecture lecture);

	List<CourseVideoLecture> findVideoLectureByChapterId(Integer chapterId);
}