package com.yuxin.wx.api.course;


import java.util.List;

import com.yuxin.wx.model.course.CourseProtocolConfig;
/**
 * Service Interface:CourseProtocolConfig
 * @author chopin
 * @date 2016-10-31
 */
public interface ICourseProtocolConfigService  {
	/**
	 * 
	* @Title: saveCourseProtocolConfig
	* @Description: 新增CourseProtocolConfig
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-10-31
	* @user by wangzx
	 */
	void insert(CourseProtocolConfig entity);
	
	/**
	 * 
	* @Title: batchSaveCourseProtocolConfig 
	* @Description: 批量新增CourseProtocolConfig
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-10-31
	* @user by wangzx
	 */
	void batchInsert(List<CourseProtocolConfig> list);
	
	/**
	 * 
	* @Title: updateCourseProtocolConfig 
	* @Description: 编辑CourseProtocolConfig
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-10-31
	* @user by wangzx
	 */
	void update(CourseProtocolConfig entity);
	
	/**
	 * 
	* @Title: deleteCourseProtocolConfigById 
	* @Description: 根据id删除CourseProtocolConfig
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-10-31
	* @user by wangzx
	 */
	void deleteCourseProtocolConfigById(Integer id);
	
	/**
	 * 
	* @Title: deleteCourseProtocolConfigByIds 
	* @Description: 根据id批量删除CourseProtocolConfig
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-10-31
	* @user by wangzx
	 */
	void deleteCourseProtocolConfigByIds(Integer[] ids);
	
	/**
	 * 
	* @Title: findCourseProtocolConfigById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-10-31
	* @user by wangzx
	 */
	CourseProtocolConfig findCourseProtocolConfigById(Integer id);
	
	/**
	 * 
	* @Title: findCourseProtocolConfigByPage 
	* @Description: 分页查询
	* @return
	* @return List<CourseProtocolConfig>    返回类型 
	* @throws 
	* @exception 
	* @date 2016-10-31
	* @user by wangzx
	 */
	List<CourseProtocolConfig> findCourseProtocolConfigByPage(CourseProtocolConfig search);

	List<CourseProtocolConfig> findBySearch(CourseProtocolConfig config);
	
	Integer pageCount(CourseProtocolConfig config);
	
	int checkNameExist(CourseProtocolConfig config);
}