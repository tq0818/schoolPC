package com.yuxin.wx.system.mapper;

import java.util.List;

import com.yuxin.wx.common.BaseMapper;
import com.yuxin.wx.model.system.SysConfigItemCourse;

/**
 * Service Interface:Users
 * @author chopin
 * @date 2015-3-12
 */
public interface SysConfigItemCourseMapper extends BaseMapper<SysConfigItemCourse> {
	List<SysConfigItemCourse> findSysConfigItemCourse(SysConfigItemCourse search);
	void updateByItemId(SysConfigItemCourse entity);
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
	
	List<SysConfigItemCourse> findByItem(SysConfigItemCourse search);
}