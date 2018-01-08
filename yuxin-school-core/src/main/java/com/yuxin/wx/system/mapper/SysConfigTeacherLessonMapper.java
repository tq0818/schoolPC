package com.yuxin.wx.system.mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	void deleteByTeacherId(Integer teacherID);
	void deleteByTeacherIdNew(Map<String, Integer> map);
	SysConfigTeacherLesson findSysConfigTeacherLessonByTeaId(Integer id);
}