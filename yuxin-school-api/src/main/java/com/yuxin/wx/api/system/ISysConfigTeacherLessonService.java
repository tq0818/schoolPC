package com.yuxin.wx.api.system;

import java.util.List;

import com.yuxin.wx.model.system.SysConfigTeacherLesson;
import com.yuxin.wx.vo.system.SysConfigTeacherLessonVo;
/**
 * Service Interface:SysConfigTeacherLesson
 * @author wang.zx
 * @date 2014-12-5
 */
public interface ISysConfigTeacherLessonService  {
	/**
	 * 
	* @Title: saveSysConfigTeacherLesson
	* @Description: 新增SysConfigTeacherLesson
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	void insert(SysConfigTeacherLesson sysConfigTeacherLesson);
	
	/**
	 * 
	* @Title: batchSaveSysConfigTeacherLesson 
	* @Description: 批量新增SysConfigTeacherLesson
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	void batchInsert(List<SysConfigTeacherLesson> sysConfigTeacherLesson);
	
	/**
	 * 
	* @Title: updateSysConfigTeacherLesson 
	* @Description: 编辑SysConfigTeacherLesson
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	void update(SysConfigTeacherLesson sysConfigTeacherLesson);
	
	/**
	 * 
	* @Title: deleteSysConfigTeacherLessonById 
	* @Description: 根据id删除SysConfigTeacherLesson
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	void deleteSysConfigTeacherLessonById(Integer id);
	
	/**
	 * 
	* @Title: deleteSysConfigTeacherLessonByIds 
	* @Description: 根据id批量删除SysConfigTeacherLesson
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	void deleteSysConfigTeacherLessonByIds(Integer[] ids);
	
	/**
	 * 
	* @Title: findSysConfigTeacherLessonById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	SysConfigTeacherLesson findSysConfigTeacherLessonById(Integer id);
	/**
	 * 
	 * Class Name: ISysConfigTeacherLessonService.java
	 * @Description: 根据老师id查询
	 * @author yuchanglong
	 * @date 2015年11月17日 下午7:52:07
	 * @version 1.0
	 * @param id
	 * @return
	 */
	SysConfigTeacherLesson findSysConfigTeacherLessonByTeaId(Integer id);
	
	/**
	 * 
	* @Title: findSysConfigTeacherLessonByPage 
	* @Description: 分页查询
	* @return
	* @return List<SysConfigTeacherLesson>    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	List<SysConfigTeacherLesson> findSysConfigTeacherLessonByPage(SysConfigTeacherLesson search);
	
	/**
	 * Class Name: ISysConfigTeacherLessonService.java
	 * @Description: 根据教师id查询授课信息
	 * @author Kevin
	 * @date 2015年1月18日
	 * @version 1.0
	 * @param teacherId
	 * @return
	 */
	List<SysConfigTeacherLessonVo> findByTeacherId(Integer teacherId);
	/**
	 * 根据老师id删除相关授课信息
	 * Class Name: ISysConfigTeacherLessonService.java
	 * @Description: TODO
	 * @author Kevin
	 * @date 2015年1月18日
	 * @version 1.0
	 * @param teacherId
	 */
	void deleteByTeacherId(Integer teacherId);
}