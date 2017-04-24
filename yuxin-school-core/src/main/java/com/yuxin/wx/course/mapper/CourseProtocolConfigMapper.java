package com.yuxin.wx.course.mapper;


import java.util.List;

import com.yuxin.wx.common.BaseMapper;
import com.yuxin.wx.model.course.CourseProtocolConfig;

/**
 * Service Interface:Users
 * @author chopin
 * @date 2015-3-12
 */
public interface CourseProtocolConfigMapper extends BaseMapper<CourseProtocolConfig> {

	List<CourseProtocolConfig> findBySearch(CourseProtocolConfig config);
	
	Integer pageCount(CourseProtocolConfig config);
	
	int checkNameExist(CourseProtocolConfig config);
}