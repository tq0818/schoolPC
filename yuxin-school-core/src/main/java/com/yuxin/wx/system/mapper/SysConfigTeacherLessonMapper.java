package com.yuxin.wx.system.mapper;

import java.util.List;

import com.yuxin.wx.model.system.SysConfigTeacherLesson;
import com.yuxin.wx.vo.system.SysConfigTeacherLessonVo;
import com.yuxin.wx.common.BaseMapper;
/**
 * Service Interface:SysConfigTeacherLesson
 * @author wang.zx
 * @date 2014-12-5
 */
public interface SysConfigTeacherLessonMapper extends BaseMapper<SysConfigTeacherLesson> {
	List<SysConfigTeacherLessonVo> findByTeacherId(Integer teacherId);
	void deleteByTeacherId(Integer teacherId);
	SysConfigTeacherLesson findSysConfigTeacherLessonByTeaId(Integer id);
}