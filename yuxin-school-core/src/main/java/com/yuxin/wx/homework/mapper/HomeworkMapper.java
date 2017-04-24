package com.yuxin.wx.homework.mapper;

import java.util.List;
import java.util.Map;

import com.yuxin.wx.common.BaseMapper;
import com.yuxin.wx.model.homework.Homework;
import com.yuxin.wx.model.homework.HomeworkStudentComplete;
import com.yuxin.wx.vo.homework.HomeWorkVo;
import com.yuxin.wx.vo.student.StudentPaySlaveVo;

/**
 * Service Interface:Users
 * @author chopin
 * @date 2015-3-12
 */
public interface HomeworkMapper extends BaseMapper<Homework> {

	Homework findHomeworkByLessonId(HomeWorkVo lessonId);

	Integer findLessonStuByCount(HomeWorkVo sps);
	
	List<HomeworkStudentComplete> findHomeworkByCourseIdAndStuId(Map<String, Object> map);
}