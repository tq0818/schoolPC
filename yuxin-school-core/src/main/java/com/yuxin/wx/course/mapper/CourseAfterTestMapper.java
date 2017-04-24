package com.yuxin.wx.course.mapper;

import java.util.List;
import java.util.Map;

import com.yuxin.wx.common.BaseMapper;
import com.yuxin.wx.model.course.CourseAfterTest;

/**
 * Service Interface:Users
 * @author chopin
 * @date 2015-3-12
 */
public interface CourseAfterTestMapper extends BaseMapper<CourseAfterTest> {
	
	//根据章id查询所有测验
	public List<CourseAfterTest> findTestListByChapterId(Integer chapterId);
	
	//改变测验所属的章id
	CourseAfterTest mvTest(Map<String, Integer> map);
}