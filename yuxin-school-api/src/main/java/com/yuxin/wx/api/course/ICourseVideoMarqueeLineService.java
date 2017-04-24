package com.yuxin.wx.api.course;

import java.util.List;

import com.yuxin.wx.model.course.CourseVideoMarqueeLine;
/**
 * Service Interface:CourseVideoMarqueeLine
 * @author wang.zx
 * @date 2015-10-12
 */
public interface ICourseVideoMarqueeLineService  {
	/**
	 * 
	* @Title: saveCourseVideoMarqueeLine
	* @Description: 新增CourseVideoMarqueeLine
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-10-12
	* @user by wangzx
	 */
	void insert(CourseVideoMarqueeLine entity);
	
	/**
	 * 
	* @Title: batchSaveCourseVideoMarqueeLine 
	* @Description: 批量新增CourseVideoMarqueeLine
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-10-12
	* @user by wangzx
	 */
	void batchInsert(List<CourseVideoMarqueeLine> list);
	
	/**
	 * 
	* @Title: updateCourseVideoMarqueeLine 
	* @Description: 编辑CourseVideoMarqueeLine
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-10-12
	* @user by wangzx
	 */
	void update(CourseVideoMarqueeLine entity);
	
	/**
	 * 
	* @Title: deleteCourseVideoMarqueeLineById 
	* @Description: 根据id删除CourseVideoMarqueeLine
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-10-12
	* @user by wangzx
	 */
	void deleteCourseVideoMarqueeLineById(Integer id);
	
	/**
	 * 
	* @Title: deleteCourseVideoMarqueeLineByIds 
	* @Description: 根据id批量删除CourseVideoMarqueeLine
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-10-12
	* @user by wangzx
	 */
	void deleteCourseVideoMarqueeLineByIds(Integer[] ids);
	
	/**
	 * 
	* @Title: findCourseVideoMarqueeLineById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-10-12
	* @user by wangzx
	 */
	CourseVideoMarqueeLine findCourseVideoMarqueeLineById(Integer id);
	
	/**
	 * 
	* @Title: findCourseVideoMarqueeLineByPage 
	* @Description: 分页查询
	* @return
	* @return List<CourseVideoMarqueeLine>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-10-12
	* @user by wangzx
	 */
	List<CourseVideoMarqueeLine> findCourseVideoMarqueeLineByPage(CourseVideoMarqueeLine search);
}