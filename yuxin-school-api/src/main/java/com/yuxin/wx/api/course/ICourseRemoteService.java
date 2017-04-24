package com.yuxin.wx.api.course;

import java.util.List;

import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.model.course.CourseRemote;
import com.yuxin.wx.vo.course.CourseRemoteVo;
/**
 * Service Interface:CourseRemote
 * @author wang.zx
 * @date 2014-12-5
 */
public interface ICourseRemoteService  {
	/**
	 * 
	* @Title: saveCourseRemote
	* @Description: 新增CourseRemote
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	void insert(CourseRemote courseRemote);
	
	/**
	 * 
	* @Title: batchSaveCourseRemote 
	* @Description: 批量新增CourseRemote
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	void batchInsert(List<CourseRemote> courseRemote);
	
	/**
	 * 
	* @Title: updateCourseRemote 
	* @Description: 编辑CourseRemote
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	void update(CourseRemote courseRemote);
	
	/**
	 * 
	* @Title: deleteCourseRemoteById 
	* @Description: 根据id删除CourseRemote
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	void deleteCourseRemoteById(Integer id);
	
	/**
	 * 
	* @Title: deleteCourseRemoteByIds 
	* @Description: 根据id批量删除CourseRemote
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	void deleteCourseRemoteByIds(Integer[] ids);
	
	/**
	 * 
	* @Title: findCourseRemoteById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	CourseRemote findCourseRemoteById(Integer id);
	
	/**
	 * 
	* @Title: findCourseRemoteByPage 
	* @Description: 分页查询
	* @return
	* @return List<CourseRemote>    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	List<CourseRemote> findCourseRemoteByPage(CourseRemote search);
	
	/**
	 * 自定义Vo分页查询
	 * @param search
	 * @return
	 */
	PageFinder<CourseRemoteVo> queryCourseRemoteVoByKeys(CourseRemote search);
	
	/**
	 *  根据班型id获取绑定远程教育 
	 * Class Name: ICourseRemoteService.java
	 * @Description: TODO
	 * @author Kevin
	 * @date 2014年12月31日
	 * @version 1.0
	 * @param classTypeId
	 * @return
	 */
	List<CourseRemote> findRemotesByClassTypeId(Integer classTypeId);
	
	/**
	 * 
	 * Class Name: ICourseRemoteService.java
	 * @Description: 根据远程教育id获取远程教育修改信息
	 * @author Kevin
	 * @date 2015年1月4日
	 * @version 1.0
	 * @param id
	 * @return
	 */
	CourseRemoteVo findByOneId(Integer id);
	
	
	Integer checkStopById(Integer id);

	List<CourseRemote> findByMajor(String major,Integer companyId,String schoolName);
	
	/**
	 * 
	 * Class Name: ICourseRemoteService.java
	 * @Description: 根据一二级项目查询远程
	 * @author zhang.zx
	 * @date 2015-5-12
	 * @version 1.0
	 * @param courseRemote
	 * @return
	 */
	List<CourseRemote> findCourseByItem(CourseRemote courseRemote);
}