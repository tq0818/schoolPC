package com.yuxin.wx.api.course;

import java.util.List;

import com.yuxin.wx.model.course.CourseVideoMarquee;
import com.yuxin.wx.vo.course.CourseVideoMarqueeVo;
/**
 * Service Interface:CourseVideoMarquee
 * @author wang.zx
 * @date 2015-10-12
 */
public interface ICourseVideoMarqueeService  {
	/**
	 * 
	* @Title: saveCourseVideoMarquee
	* @Description: 新增CourseVideoMarquee
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-10-12
	* @user by wangzx
	 */
	void insert(CourseVideoMarquee entity);
	
	/**
	 * 
	* @Title: batchSaveCourseVideoMarquee 
	* @Description: 批量新增CourseVideoMarquee
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-10-12
	* @user by wangzx
	 */
	void batchInsert(List<CourseVideoMarquee> list);
	
	/**
	 * 
	* @Title: updateCourseVideoMarquee 
	* @Description: 编辑CourseVideoMarquee
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-10-12
	* @user by wangzx
	 */
	void update(CourseVideoMarquee entity);
	
	/**
	 * 
	* @Title: deleteCourseVideoMarqueeById 
	* @Description: 根据id删除CourseVideoMarquee
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-10-12
	* @user by wangzx
	 */
	void deleteCourseVideoMarqueeById(Integer id);
	
	/**
	 * 
	* @Title: deleteCourseVideoMarqueeByIds 
	* @Description: 根据id批量删除CourseVideoMarquee
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-10-12
	* @user by wangzx
	 */
	void deleteCourseVideoMarqueeByIds(Integer[] ids);
	
	/**
	 * 
	* @Title: findCourseVideoMarqueeById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-10-12
	* @user by wangzx
	 */
	CourseVideoMarquee findCourseVideoMarqueeById(Integer id);
	
	/**
	 * 
	* @Title: findCourseVideoMarqueeByPage 
	* @Description: 分页查询
	* @return
	* @return List<CourseVideoMarquee>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-10-12
	* @user by wangzx
	 */
	List<CourseVideoMarquee> findCourseVideoMarqueeByPage(CourseVideoMarquee search);
	
	/**
	 * 
	 * Class Name: ICourseVideoMarqueeService.java
	 * @Description: 根据公司id查询
	 * @author 周文斌
	 * @date 2015-10-12 下午12:44:29
	 * @version 1.0
	 * @param companyId
	 * @return
	 */
	CourseVideoMarquee findMarqueeByCompanyId(CourseVideoMarquee cvm);

	/**
	 * @Description: 根据公司ID获取该公司配置的相关跑马灯的设置
	 * @author zx.wang
	 * @date 2015-10-12 下午12:24:04
	 * @version 2.0
	 * @param companyId
	 * @return
	 */
	CourseVideoMarqueeVo findCourseVideoMarqueeVoByCompanyId(Integer companyId);
}