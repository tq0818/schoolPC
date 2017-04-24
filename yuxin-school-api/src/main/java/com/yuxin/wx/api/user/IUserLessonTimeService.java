package com.yuxin.wx.api.user;

import java.util.List;
import java.util.Map;

import com.yuxin.wx.model.user.UserLessonTime;
/**
 * Service Interface:UserLessonTime
 * @author wang.zx
 * @date 2015-9-25
 */
public interface IUserLessonTimeService  {
	/**
	 * 
	* @Title: saveUserLessonTime
	* @Description: 新增UserLessonTime
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-9-25
	* @user by wangzx
	 */
	void insert(UserLessonTime entity);
	
	/**
	 * 
	* @Title: batchSaveUserLessonTime 
	* @Description: 批量新增UserLessonTime
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-9-25
	* @user by wangzx
	 */
	void batchInsert(List<UserLessonTime> list);
	
	/**
	 * 
	* @Title: updateUserLessonTime 
	* @Description: 编辑UserLessonTime
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-9-25
	* @user by wangzx
	 */
	void update(UserLessonTime entity);
	
	/**
	 * 
	* @Title: deleteUserLessonTimeById 
	* @Description: 根据id删除UserLessonTime
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-9-25
	* @user by wangzx
	 */
	void deleteUserLessonTimeById(Integer id);
	
	/**
	 * 
	* @Title: deleteUserLessonTimeByIds 
	* @Description: 根据id批量删除UserLessonTime
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-9-25
	* @user by wangzx
	 */
	void deleteUserLessonTimeByIds(Integer[] ids);
	
	/**
	 * 
	* @Title: findUserLessonTimeById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-9-25
	* @user by wangzx
	 */
	UserLessonTime findUserLessonTimeById(Integer id);
	
	/**
	 * 
	* @Title: findUserLessonTimeByPage 
	* @Description: 分页查询
	* @return
	* @return List<UserLessonTime>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-9-25
	* @user by wangzx
	 */
	List<UserLessonTime> findUserLessonTimeByPage(UserLessonTime search);
	
	/**
	 * 
	 * Class Name: IUserLessonTimeService.java
	 * @Description: 根据学生ID和lessonID删除记录
	 * @author dongshuai
	 * @date 2016年8月31日 下午5:51:05
	 * @modifier
	 * @modify-date 2016年8月31日 下午5:51:05
	 * @version 1.0
	 * @param map
	 */
	void deleteByUserIdAndLessonId(Map<String, Integer> map);
	
	/**
	 * 
	 * Class Name: IUserLessonTimeService.java
	 * @Description: 根据学生ID和lessonID查询记录
	 * @author dongshuai
	 * @date 2016年8月31日 下午6:31:21
	 * @modifier
	 * @modify-date 2016年8月31日 下午6:31:21
	 * @version 1.0
	 * @param map
	 * @return
	 */
	List<UserLessonTime> findByUserIdAndLessonId(Map<String, Integer> map);
}