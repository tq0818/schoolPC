package com.yuxin.wx.api.course;

import java.util.List;
import java.util.Map;

import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.model.course.LiveOpenCourse;
import com.yuxin.wx.vo.classes.CmlVo;
/**
 * Service Interface:LiveOpenCourse
 * @author wang.zx
 * @date 2015-9-25
 */
public interface ILiveOpenCourseService  {
	/**
	 * 
	* @Title: saveLiveOpenCourse
	* @Description: 新增LiveOpenCourse
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-9-25
	* @user by wangzx
	 */
	void insert(LiveOpenCourse entity);
	
	/**
	 * 
	* @Title: batchSaveLiveOpenCourse 
	* @Description: 批量新增LiveOpenCourse
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-9-25
	* @user by wangzx
	 */
	void batchInsert(List<LiveOpenCourse> list);
	
	/**
	 * 
	* @Title: updateLiveOpenCourse 
	* @Description: 编辑LiveOpenCourse
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-9-25
	* @user by wangzx
	 */
	void update(LiveOpenCourse entity);
	
	/**
	 * 
	* @Title: deleteLiveOpenCourseById 
	* @Description: 根据id删除LiveOpenCourse
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-9-25
	* @user by wangzx
	 */
	void deleteLiveOpenCourseById(Integer id);
	
	/**
	 * 
	* @Title: deleteLiveOpenCourseByIds 
	* @Description: 根据id批量删除LiveOpenCourse
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-9-25
	* @user by wangzx
	 */
	void deleteLiveOpenCourseByIds(Integer[] ids);
	
	/**
	 * 
	* @Title: findLiveOpenCourseById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-9-25
	* @user by wangzx
	 */
	LiveOpenCourse findLiveOpenCourseById(Integer id);
	
	/**
	 * 
	* @Title: findLiveOpenCourseByPage 
	* @Description: 分页查询
	* @return
	* @return List<LiveOpenCourse>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-9-25
	* @user by wangzx
	 */
	PageFinder<LiveOpenCourse> findLiveOpenCourseByPage(LiveOpenCourse search);
	
	/**
	 * 
	 * Class Name: ILiveOpenCourseService.java
	 * @Description: 
	 * @author yuchanglong
	 * @date 2015年9月29日 下午3:41:43
	 * @version 1.0
	 * @param course
	 * @return
	 */
	LiveOpenCourse findByLiveOpenCourse(LiveOpenCourse course);
	
	/**
	 * 
	 * Class Name: ILiveOpenCourseService.java
	 * @Description: 查询直播教室id 展示统计用
	 * @author 周文斌
	 * @date 2015-12-24 上午11:10:24
	 * @version 1.0
	 * @param param
	 * @return
	 */
	List<CmlVo> findLiveRoomIds(Map<String, Object> param);
	
	/**
	 * 
	 * Class Name: ILiveOpenCourseService.java
	 * @Description: 修改公开课状态
	 * @author 周文斌
	 * @date 2015-12-31 下午4:10:59
	 * @version 1.0
	 * @param param
	 */
	void updateOpenclassByItem(Map<String, Object> param);
	
	LiveOpenCourse findByLiveRoomId(String roomId);

	List<LiveOpenCourse> findAfterTodayByPage(LiveOpenCourse course);
}