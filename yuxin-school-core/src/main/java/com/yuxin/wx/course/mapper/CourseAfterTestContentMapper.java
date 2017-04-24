package com.yuxin.wx.course.mapper;

import java.util.List;

import com.yuxin.wx.common.BaseMapper;
import com.yuxin.wx.model.course.CourseAfterTestContent;

/**
 * Service Interface:Users
 * @author chopin
 * @date 2015-3-12
 */
public interface CourseAfterTestContentMapper extends BaseMapper<CourseAfterTestContent> {
	
	public List<CourseAfterTestContent> findContentListByTestId(Integer testId);
	
	public List<CourseAfterTestContent> findContentListByTestId2(Integer testId);
	
	void deleteByTestId(Integer testId);
	
	void deleteByTestId2(Integer testId);
}