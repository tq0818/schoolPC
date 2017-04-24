package com.yuxin.wx.api.course;

import java.util.List;

import com.yuxin.wx.model.course.CourseExercise;
import com.yuxin.wx.vo.course.CourseExerciseVo;
/**
 * Service Interface:CourseExercise
 * @author wang.zx
 * @date 2015-12-29
 */
public interface ICourseExerciseService  {
	/**
	 * 
	* @Title: saveCourseExercise
	* @Description: 新增CourseExercise
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-12-29
	* @user by wangzx
	 */
	void insert(CourseExercise entity);
	
	/**
	 * 
	* @Title: batchSaveCourseExercise 
	* @Description: 批量新增CourseExercise
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-12-29
	* @user by wangzx
	 */
	void batchInsert(List<CourseExercise> list);
	
	/**
	 * 
	* @Title: updateCourseExercise 
	* @Description: 编辑CourseExercise
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-12-29
	* @user by wangzx
	 */
	void update(CourseExercise entity);
	
	/**
	 * 
	* @Title: deleteCourseExerciseById 
	* @Description: 根据id删除CourseExercise
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-12-29
	* @user by wangzx
	 */
	void deleteCourseExerciseById(Integer id);
	
	/**
	 * 
	* @Title: deleteCourseExerciseByIds 
	* @Description: 根据id批量删除CourseExercise
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-12-29
	* @user by wangzx
	 */
	void deleteCourseExerciseByIds(Integer[] ids);
	
	/**
	 * 
	* @Title: findCourseExerciseById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-12-29
	* @user by wangzx
	 */
	CourseExercise findCourseExerciseById(Integer id);
	
	/**
	 * 
	* @Title: findCourseExerciseByPage 
	* @Description: 分页查询
	* @return
	* @return List<CourseExercise>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-12-29
	* @user by wangzx
	 */
	List<CourseExercise> findCourseExerciseByPage(CourseExercise search);
	
	/**
	 * 
	* @Title: findCourseExerciseByPage 
	* @Description: 分页查询
	* @return
	* @return List<CourseExercise>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-12-29
	* @user by chopin
	 */
	public List<CourseExerciseVo> searchCourseExercise(CourseExercise search);
}