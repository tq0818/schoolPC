package com.yuxin.wx.course.mapper;

import java.util.List;
import java.util.Map;

import com.yuxin.wx.model.course.CourseVideoChapter;
import com.yuxin.wx.vo.course.ChapterAndLectureListVo;
import com.yuxin.wx.common.BaseMapper;
/**
 * Service Interface:CourseVideoChapter
 * @author wang.zx
 * @date 2014-12-5
 */
public interface CourseVideoChapterMapper extends BaseMapper<CourseVideoChapter> {
	
	List<ChapterAndLectureListVo> findChapterAndLectureByVideoId(Integer courseVideoId);

	List<CourseVideoChapter> findByClassId(Map<String, String> map);
	List<ChapterAndLectureListVo> findChapterAndLectureByModuleId(Integer moduleId);
	List<CourseVideoChapter> findByModuleId(Integer moduleId);
	
	List<Integer> findIdByModuleId(Integer id);

	CourseVideoChapter findByChapterNameAndModuleId(CourseVideoChapter chapter);
}