package com.yuxin.wx.course.mapper;

import java.util.List;

import com.yuxin.wx.common.BaseMapper;
import com.yuxin.wx.model.course.CourseExercise;
import com.yuxin.wx.vo.course.CourseExerciseVo;

/**
 * Service Interface:Users
 * @author chopin
 * @date 2015-3-12
 */
public interface CourseExerciseMapper extends BaseMapper<CourseExercise> {
	public List<CourseExerciseVo> page2(CourseExercise search);
}