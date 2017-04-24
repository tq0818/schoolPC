package com.yuxin.wx.api.system;

import java.util.List;
import com.yuxin.wx.model.system.SysConfigItemCourse;
/**
 * Service Interface:SysConfigItemCourse
 * @author wang.zx
 * @date 2015-9-23
 */
public interface ISysConfigItemCourseService  {
	/**
	 * 
	* @Title: saveSysConfigItemCourse
	* @Description: 新增SysConfigItemCourse
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-9-23
	* @user by wangzx
	 */
	void insert(SysConfigItemCourse entity);
	
	/**
	 * 
	* @Title: batchSaveSysConfigItemCourse 
	* @Description: 批量新增SysConfigItemCourse
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-9-23
	* @user by wangzx
	 */
	void batchInsert(List<SysConfigItemCourse> list);
	
	/**
	 * 
	* @Title: updateSysConfigItemCourse 
	* @Description: 编辑SysConfigItemCourse
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-9-23
	* @user by wangzx
	 */
	void update(SysConfigItemCourse entity);
	
	void updateByItemId(SysConfigItemCourse entity);
	
	/**
	 * 
	* @Title: deleteSysConfigItemCourseById 
	* @Description: 根据id删除SysConfigItemCourse
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-9-23
	* @user by wangzx
	 */
	void deleteSysConfigItemCourseById(Integer id);
	
	/**
	 * 
	* @Title: deleteSysConfigItemCourseByIds 
	* @Description: 根据id批量删除SysConfigItemCourse
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-9-23
	* @user by wangzx
	 */
	void deleteSysConfigItemCourseByIds(Integer[] ids);
	
	/**
	 * 
	* @Title: findSysConfigItemCourseById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-9-23
	* @user by wangzx
	 */
	SysConfigItemCourse findSysConfigItemCourseById(Integer id);
	
	/**
	 * 
	* @Title: findSysConfigItemCourseByPage 
	* @Description: 分页查询
	* @return
	* @return List<SysConfigItemCourse>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-9-23
	* @user by wangzx
	 */
	List<SysConfigItemCourse> findSysConfigItemCourseByPage(SysConfigItemCourse search);
	/**
	 * 
	 * Class Name: ISysConfigItemCourseService.java
	 * @Description: 条件查询，不分页
	 * @author yuchanglong
	 * @date 2015年9月23日 下午7:02:07
	 * @version 1.0
	 * @param search
	 * @return
	 */
	List<SysConfigItemCourse> findSysConfigItemCourse(SysConfigItemCourse search);
	
	SysConfigItemCourse findByItemIdAndComId(SysConfigItemCourse search);
	
	/**
	 * 
	 * Class Name: ISysConfigItemCourseService.java
	 * @Description: 修改删除状态
	 * @author 周文斌
	 * @date 2015-9-24 下午7:29:47
	 * @version 1.0
	 * @param scic
	 */
	void updateStatus(SysConfigItemCourse scic);
	/**
	 * 
	 * Class Name: ISysConfigItemCourseService.java
	 * @Description: 根据学科启用禁用查询
	 * @author yuchanglong
	 * @date 2016年1月4日 上午11:36:06
	 * @version 1.0
	 * @param search
	 * @return
	 */
	List<SysConfigItemCourse> findByItem(SysConfigItemCourse search);
}