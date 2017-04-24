package com.yuxin.wx.course.mapper;

import java.util.List;

import com.yuxin.wx.common.BaseMapper;
import com.yuxin.wx.model.course.VideoCourseComment;
import com.yuxin.wx.vo.system.TeacherCommentVo;

/**
 * Service Interface:Users
 * @author chopin
 * @date 2015-3-12
 */
public interface VideoCourseCommentMapper extends BaseMapper<VideoCourseComment> {

	List<TeacherCommentVo> findByTeacherId(TeacherCommentVo teacherCommentVo);

	Integer commentCount(TeacherCommentVo teacherCommentVo);
	
	
}