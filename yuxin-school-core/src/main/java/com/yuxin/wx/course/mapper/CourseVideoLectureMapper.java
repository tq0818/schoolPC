package com.yuxin.wx.course.mapper;

import java.util.List;
import java.util.Map;

import com.yuxin.wx.common.BaseMapper;
import com.yuxin.wx.model.course.CourseVideoLecture;
/**
 * Service Interface:CourseVideoLecture
 * @author wang.zx
 * @date 2014-12-5
 */
/**
 * Service Interface:CourseVideoLecture
 * @author wang.zx
 * @date 2014-12-5
 */
public interface CourseVideoLectureMapper extends BaseMapper<CourseVideoLecture> {
	List<CourseVideoLecture> findCourseVideoLectureByChapterId(Integer chapterId);
	void mvLecture(Map map);
	
	List<Integer> findIdByChapterId(Integer id);
	CourseVideoLecture findByChapterIdAndLecName(CourseVideoLecture lecture);
	List<CourseVideoLecture> findVideoLectureByChapterId(Integer chapterId);
}