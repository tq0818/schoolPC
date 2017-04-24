package com.yuxin.wx.homework.mapper;

import java.util.List;

import com.yuxin.wx.common.BaseMapper;
import com.yuxin.wx.model.homework.HomeworkStudentComplete;
import com.yuxin.wx.vo.homework.StudentHomeWorkVo;
import com.yuxin.wx.vo.homework.StudentHomeworkAttachmentVo;

/**
 * Service Interface:Users
 * @author chopin
 * @date 2015-3-12
 */
public interface HomeworkStudentCompleteMapper extends BaseMapper<HomeworkStudentComplete> {
	
	List<HomeworkStudentComplete> findByHomeworkId(StudentHomeWorkVo homeworkId);
	
	List<StudentHomeWorkVo> queryStudentHomeworkList(StudentHomeWorkVo shv);
	
	int queryStudentHomeworkListCount(StudentHomeWorkVo shv);
	
	List<StudentHomeWorkVo> queryAllStudentHomework(StudentHomeWorkVo shv);
	
	List<StudentHomeworkAttachmentVo> queryStudentHomeworkAndReadList(StudentHomeworkAttachmentVo shav);
}