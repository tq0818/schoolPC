package com.yuxin.wx.api.course;

import java.util.List;
import com.yuxin.wx.model.course.CourseVideoChapter;
import com.yuxin.wx.vo.course.ChapterAndLectureListVo;
/**
 * Service Interface:CourseVideoChapter
 * @author wang.zx
 * @date 2014-12-5
 */
public interface ICourseVideoChapterService  {
	/**
	 * 
	* @Title: saveCourseVideoChapter
	* @Description: 新增CourseVideoChapter
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	void insert(CourseVideoChapter courseVideoChapter);
	
	/**
	 * 
	* @Title: batchSaveCourseVideoChapter 
	* @Description: 批量新增CourseVideoChapter
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	void batchInsert(List<CourseVideoChapter> courseVideoChapter);
	
	/**
	 * 
	* @Title: updateCourseVideoChapter 
	* @Description: 编辑CourseVideoChapter
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	void update(CourseVideoChapter courseVideoChapter);
	
	/**
	 * 
	* @Title: deleteCourseVideoChapterById 
	* @Description: 根据id删除CourseVideoChapter
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	void deleteCourseVideoChapterById(Integer id);
	
	/**
	 * 
	* @Title: deleteCourseVideoChapterByIds 
	* @Description: 根据id批量删除CourseVideoChapter
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	void deleteCourseVideoChapterByIds(Integer[] ids);
	
	/**
	 * 
	* @Title: findCourseVideoChapterById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	CourseVideoChapter findCourseVideoChapterById(Integer id);
	
	/**
	 * @Description:(根据视频课程ID，查询对应的章，跟节)
	 * @author wang.zx 
	 * @date 2015-2-2 下午2:30:52
	 * @version 1.0
	 * @param id
	 * @return
	 */
	List<ChapterAndLectureListVo> findChapterAndLectureByVideoId(Integer id);
	
	/**
	 * 
	* @Title: findCourseVideoChapterByPage 
	* @Description: 分页查询
	* @return
	* @return List<CourseVideoChapter>    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	List<CourseVideoChapter> findCourseVideoChapterByPage(CourseVideoChapter search);
	
	/**
	 * 
	* @Title: findCourseVideoChapterByPage 
	* @Description: 分页查询
	* @return
	* @return List<CourseVideoChapter>    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by chopin
	 */
	public List<ChapterAndLectureListVo> findChapterAndLectureByModuleId(Integer moduleId);
	
	/**
	 * 
	 * Class Name: ICourseVideoChapterService.java
	 * @Description: 根据模块id查询章的信息
	 * @author zhang.zx
	 * @date 2015年8月11日 下午4:53:56
	 * @modifier
	 * @modify-date 2015年8月11日 下午4:53:56
	 * @version 1.0
	 * @param moduleId
	 * @return
	 */
	public List<CourseVideoChapter> findChapterByModuleId(Integer moduleId);
	
	/**
	 * 
	 * Class Name: ICourseVideoChapterService.java
	 * @Description: 根据ModuleID查询IDList
	 * @author dongshuai
	 * @date 2016年8月31日 下午5:16:11
	 * @modifier
	 * @modify-date 2016年8月31日 下午5:16:11
	 * @version 1.0
	 * @param id
	 * @return
	 */
	List<Integer> findIdByModuleId(Integer id);

	CourseVideoChapter findByChapterNameAndModuleId(CourseVideoChapter chapter);
}