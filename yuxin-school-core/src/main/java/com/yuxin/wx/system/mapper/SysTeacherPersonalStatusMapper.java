package com.yuxin.wx.system.mapper;

import java.util.List;

import com.yuxin.wx.common.BaseMapper;
import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.model.system.SysTeacherPersonalStatus;
import com.yuxin.wx.vo.system.TeacherDynamicsVo;

/**
 * Service Interface:Users
 * @author chopin
 * @date 2015-3-12
 */
public interface SysTeacherPersonalStatusMapper extends BaseMapper<SysTeacherPersonalStatus> {
	/**
	 * 
	 * @fileName : SysTeacherPersonalStatusMapper.java
	 * @author   : 杨延博
	 * @date     : 2015年11月4日下午5:37:45
	 * @description :搜索最新动态
	 */
	List<TeacherDynamicsVo> findStatusByTeacherId(
			TeacherDynamicsVo teacherDynamicsVo);
	/**
	 * 
	 * @fileName : SysTeacherPersonalStatusMapper.java
	 * @author   : 杨延博
	 * @date     : 2015年11月4日下午5:38:14
	 * @description :搜索最新动态数量
	 */
	int statusCount(TeacherDynamicsVo teacherDynamicsVo);
	/**
	 * 
	 * @fileName : SysTeacherPersonalStatusMapper.java
	 * @author   : 杨延博
	 * @date     : 2015年11月4日下午7:10:07
	 * @description :机构查询最新动态
	 */
	List<TeacherDynamicsVo> findManageStatusByTeacherId(
			TeacherDynamicsVo teacherDynamicsVo);
	/**
	 * 
	 * @fileName : SysTeacherPersonalStatusMapper.java
	 * @author   : 杨延博
	 * @date     : 2015年11月4日下午7:10:58
	 * @description :机构查询热门动态
	 */
	List<TeacherDynamicsVo> findManageHotStatusByTeacherId(
			TeacherDynamicsVo teacherDynamicsVo);
	/**
	 * 
	 * @fileName : SysTeacherPersonalStatusMapper.java
	 * @author   : 杨延博
	 * @date     : 2015年11月4日下午9:03:16
	 * @description :机构查询动态数量
	 */
	int manageStatusCount(TeacherDynamicsVo teacherDynamicsVo);
	
	
}