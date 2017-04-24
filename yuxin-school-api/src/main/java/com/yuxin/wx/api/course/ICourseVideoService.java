package com.yuxin.wx.api.course;

import java.util.List;

import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.model.course.CourseVideo;
import com.yuxin.wx.vo.course.CourseVideoVo;
/**
 * Service Interface:CourseVideo
 * @author wang.zx
 * @date 2014-12-5
 */
public interface ICourseVideoService  {
	/**
	 * 
	* @Title: saveCourseVideo
	* @Description: 新增CourseVideo
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	void insert(CourseVideo courseVideo);
	
	/**
	 * 
	* @Title: batchSaveCourseVideo 
	* @Description: 批量新增CourseVideo
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	void batchInsert(List<CourseVideo> courseVideo);
	
	/**
	 * 
	* @Title: updateCourseVideo 
	* @Description: 编辑CourseVideo
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	void update(CourseVideo courseVideo);
	
	/**
	 * 
	* @Title: deleteCourseVideoById 
	* @Description: 根据id删除CourseVideo
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	void deleteCourseVideoById(Integer id);
	
	/**
	 * 
	* @Title: deleteCourseVideoByIds 
	* @Description: 根据id批量删除CourseVideo
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	void deleteCourseVideoByIds(Integer[] ids);
	
	/**
	 * 
	* @Title: findCourseVideoById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	CourseVideo findCourseVideoById(Integer id);
	
	/**
	 * 
	* @Title: findCourseVideoByModuleId 
	* @Description: 根据id查询
	* @param moduleId
	* @return List<CourseVideo>    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by chopin
	 */
	List<CourseVideo> findCourseVideoByModuleId(Integer moduleId);
	
	/**
	 * 
	* @Title: findCourseVideoByPage 
	* @Description: 分页查询
	* @return
	* @return List<CourseVideo>    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	List<CourseVideo> findCourseVideoByPage(CourseVideo search);
	
	/**
	 * 
	 * Class Name: ICourseVideoService.java
	 * @Description: 分页查询视频列表
	 * @author Keyn
	 * @date 2015-1-26 下午3:21:40
	 * @version 1.0
	 * @param search
	 * @return
	 */
	PageFinder<CourseVideoVo> queryVideoByKeys(CourseVideo search);
	
	/**
	 * @Description: 根据条件查询对于的视频VO
	 * @author wzx
	 * @date 2015-3-28 下午12:59:33
	 * @version 1.0
	 * @param search
	 * @return
	 */
	CourseVideoVo findCourseVideoVoByVideoId(Integer videoId);
}