package com.yuxin.wx.api.homework;

import java.util.List;
import java.util.Map;

import com.yuxin.wx.model.homework.Homework;
import com.yuxin.wx.vo.homework.HomeWorkVo;
import com.yuxin.wx.vo.student.StudentPaySlaveVo;
/**
 * Service Interface:Homework
 * @author chopin
 * @date 2016-12-15
 */
public interface IHomeworkService  {
	/**
	 * 
	* @Title: saveHomework
	* @Description: 新增Homework
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-12-15
	* @user by wangzx
	 */
	void insert(Homework entity);
	
	/**
	 * 
	* @Title: batchSaveHomework 
	* @Description: 批量新增Homework
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-12-15
	* @user by wangzx
	 */
	void batchInsert(List<Homework> list);
	
	/**
	 * 
	* @Title: updateHomework 
	* @Description: 编辑Homework
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-12-15
	* @user by wangzx
	 */
	void update(Homework entity);
	
	/**
	 * 
	* @Title: deleteHomeworkById 
	* @Description: 根据id删除Homework
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-12-15
	* @user by wangzx
	 */
	void deleteHomeworkById(Integer id);
	
	/**
	 * 
	* @Title: deleteHomeworkByIds 
	* @Description: 根据id批量删除Homework
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-12-15
	* @user by wangzx
	 */
	void deleteHomeworkByIds(Integer[] ids);
	
	/**
	 * 
	* @Title: findHomeworkById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-12-15
	* @user by wangzx
	 */
	Homework findHomeworkById(Integer id);
	
	/**
	 * 
	* @Title: findHomeworkByPage 
	* @Description: 分页查询
	* @return
	* @return List<Homework>    返回类型 
	* @throws 
	* @exception 
	* @date 2016-12-15
	* @user by wangzx
	 */
	List<Homework> findHomeworkByPage(Homework search);

	Homework findHomeworkByLessonId(HomeWorkVo hw);

	Integer findLessonStuByCount(HomeWorkVo hw);
	
	/**
	 * 
	 * Class Name: IHomeworkService.java
	 * @Description: 删除作业记录
	 * @author dongshuai
	 * @date 2016年12月27日 下午7:21:40
	 * @modifier
	 * @modify-date 2016年12月27日 下午7:21:40
	 * @version 1.0
	 * @param map stuId courseId
	 * @return
	 */
	void deleteHomeworkHistory(Map<String, Object> map);
}