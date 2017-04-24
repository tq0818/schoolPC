package com.yuxin.wx.homework.mapper;

import com.yuxin.wx.common.BaseMapper;
import com.yuxin.wx.model.homework.HomeworkTeacherRead;

/**
 * Service Interface:Users
 * @author chopin
 * @date 2015-3-12
 */
public interface HomeworkTeacherReadMapper extends BaseMapper<HomeworkTeacherRead> {
	
	void deleteByHSCId(Integer id);
	
	HomeworkTeacherRead queryTeacherHomeworkRead(HomeworkTeacherRead search);
	
	HomeworkTeacherRead findByResourceId(Integer id);
}