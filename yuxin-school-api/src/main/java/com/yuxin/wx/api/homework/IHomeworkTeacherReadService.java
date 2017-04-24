package com.yuxin.wx.api.homework;

import java.util.List;

import com.yuxin.wx.model.homework.HomeworkTeacherRead;
/**
 * Service Interface:HomeworkTeacherRead
 * @author chopin
 * @date 2016-12-15
 */
public interface IHomeworkTeacherReadService  {
	/**
	 * 
	* @Title: saveHomeworkTeacherRead
	* @Description: 新增HomeworkTeacherRead
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-12-15
	* @user by wangzx
	 */
	void insert(HomeworkTeacherRead entity);
	
	/**
	 * 
	* @Title: batchSaveHomeworkTeacherRead 
	* @Description: 批量新增HomeworkTeacherRead
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-12-15
	* @user by wangzx
	 */
	void batchInsert(List<HomeworkTeacherRead> list);
	
	/**
	 * 
	* @Title: updateHomeworkTeacherRead 
	* @Description: 编辑HomeworkTeacherRead
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-12-15
	* @user by wangzx
	 */
	void update(HomeworkTeacherRead entity);
	
	/**
	 * 
	* @Title: deleteHomeworkTeacherReadById 
	* @Description: 根据id删除HomeworkTeacherRead
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-12-15
	* @user by wangzx
	 */
	void deleteHomeworkTeacherReadById(Integer id);
	
	/**
	 * 
	* @Title: deleteHomeworkTeacherReadByIds 
	* @Description: 根据id批量删除HomeworkTeacherRead
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-12-15
	* @user by wangzx
	 */
	void deleteHomeworkTeacherReadByIds(Integer[] ids);
	
	/**
	 * 
	* @Title: findHomeworkTeacherReadById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-12-15
	* @user by wangzx
	 */
	HomeworkTeacherRead findHomeworkTeacherReadById(Integer id);
	
	/**
	 * 
	* @Title: findHomeworkTeacherReadByPage 
	* @Description: 分页查询
	* @return
	* @return List<HomeworkTeacherRead>    返回类型 
	* @throws 
	* @exception 
	* @date 2016-12-15
	* @user by wangzx
	 */
	List<HomeworkTeacherRead> findHomeworkTeacherReadByPage(HomeworkTeacherRead search);
	
	/**
	 * 
	 * Class Name: IHomeworkTeacherReadService.java
	 * @Description: 查询教师批阅
	 * @author dongshuai
	 * @date 2016年12月30日 下午5:49:53
	 * @modifier
	 * @modify-date 2016年12月30日 下午5:49:53
	 * @version 1.0
	 * @param search
	 * @return
	 */
	HomeworkTeacherRead queryTeacherHomeworkRead(HomeworkTeacherRead search);
	
	/**
	 * 
	 * Class Name: IHomeworkTeacherReadService.java
	 * @Description: 根据resourceId查询教师批阅
	 * @author dongshuai
	 * @date 2016年12月30日 下午6:20:30
	 * @modifier
	 * @modify-date 2016年12月30日 下午6:20:30
	 * @version 1.0
	 * @param id
	 * @return
	 */
	HomeworkTeacherRead findByResourceId(Integer id);
}