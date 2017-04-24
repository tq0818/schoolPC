package com.yuxin.wx.student.mapper;

import java.util.List;

import com.yuxin.wx.common.BaseMapper;
import com.yuxin.wx.model.student.StudentGroup;

/**
 * Service Interface:Users
 * @author chopin
 * @date 2015-3-12
 */
public interface StudentGroupMapper extends BaseMapper<StudentGroup> {
	
	List<StudentGroup> findStudentGroup1ByCompanyId(StudentGroup search);
	
	List<StudentGroup> findStudentGroup2ByCompanyIdAndPId(StudentGroup search);
	
	Integer checkGroupName1(StudentGroup search);
	
	Integer checkGroupName2(StudentGroup search);
	
	void deleteByParentId(Integer id);
}