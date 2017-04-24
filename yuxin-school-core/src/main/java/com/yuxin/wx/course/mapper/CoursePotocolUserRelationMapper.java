package com.yuxin.wx.course.mapper;


import java.util.List;

import com.yuxin.wx.common.BaseMapper;
import com.yuxin.wx.model.course.CoursePotocolUserRelation;
import com.yuxin.wx.model.course.CourseProtocolConfig;
import com.yuxin.wx.vo.course.ProtocolCourseOrPackageRelation;

/**
 * Service Interface:Users
 * @author chopin
 * @date 2015-3-12
 */
public interface CoursePotocolUserRelationMapper extends BaseMapper<CoursePotocolUserRelation> {
	
	public List<ProtocolCourseOrPackageRelation> queryProtocolRelationCourseOrCoursePackage(CourseProtocolConfig search);

	public int queryProtocolRelationCourseOrCoursePackageCount(CourseProtocolConfig search);

	public List<ProtocolCourseOrPackageRelation> queryHistoryBindCourseOrPackage(CourseProtocolConfig search);
	
	public int queryHistoryBindCourseOrPackageCount(CourseProtocolConfig search);
}