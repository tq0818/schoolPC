package com.yuxin.wx.api.system;

import java.util.List;

import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.model.system.SysTeacherPersonalStatus;
import com.yuxin.wx.vo.system.TeacherDynamicsVo;
/**
 * Service Interface:SysTeacherPersonalStatus
 * @author chopin
 * @date 2015-10-26
 */
public interface ISysTeacherPersonalStatusService  {
	/**
	 * 
	* @Title: saveSysTeacherPersonalStatus
	* @Description: 新增SysTeacherPersonalStatus
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-10-26
	* @user by wangzx
	 */
	void insert(SysTeacherPersonalStatus entity);
	
	/**
	 * 
	* @Title: batchSaveSysTeacherPersonalStatus 
	* @Description: 批量新增SysTeacherPersonalStatus
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-10-26
	* @user by wangzx
	 */
	void batchInsert(List<SysTeacherPersonalStatus> list);
	
	/**
	 * 
	* @Title: updateSysTeacherPersonalStatus 
	* @Description: 编辑SysTeacherPersonalStatus
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-10-26
	* @user by wangzx
	 */
	void update(SysTeacherPersonalStatus entity);
	
	/**
	 * 
	* @Title: deleteSysTeacherPersonalStatusById 
	* @Description: 根据id删除SysTeacherPersonalStatus
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-10-26
	* @user by wangzx
	 */
	void deleteSysTeacherPersonalStatusById(Integer id);
	
	/**
	 * 
	* @Title: deleteSysTeacherPersonalStatusByIds 
	* @Description: 根据id批量删除SysTeacherPersonalStatus
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-10-26
	* @user by wangzx
	 */
	void deleteSysTeacherPersonalStatusByIds(Integer[] ids);
	
	/**
	 * 
	* @Title: findSysTeacherPersonalStatusById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-10-26
	* @user by wangzx
	 */
	SysTeacherPersonalStatus findSysTeacherPersonalStatusById(Integer id);
	
	/**
	 * 
	* @Title: findSysTeacherPersonalStatusByPage 
	* @Description: 分页查询
	* @return
	* @return List<SysTeacherPersonalStatus>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-10-26
	* @user by wangzx
	 */
	List<SysTeacherPersonalStatus> findSysTeacherPersonalStatusByPage(SysTeacherPersonalStatus search);
	/**
	 * 
	 * @fileName : ISysTeacherPersonalStatusService.java
	 * @author   : 杨延博
	 * @date     : 2015年11月4日下午7:02:46
	 * @description :教师查询动态
	 */
	PageFinder<TeacherDynamicsVo> findStatusByTeacherId(
			TeacherDynamicsVo teacherDynamicsVo);
	/**
	 * 
	 * @fileName : ISysTeacherPersonalStatusService.java
	 * @author   : 杨延博
	 * @date     : 2015年11月4日下午7:03:03
	 * @description :机构查询动态
	 */
	PageFinder<TeacherDynamicsVo> findManageStatusByTeacherId(
			TeacherDynamicsVo teacherDynamicsVo, Integer searchType);
}