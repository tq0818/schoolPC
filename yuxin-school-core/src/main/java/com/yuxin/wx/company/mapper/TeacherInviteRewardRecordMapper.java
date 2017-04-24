package com.yuxin.wx.company.mapper;


import java.util.List;

import com.yuxin.wx.common.BaseMapper;
import com.yuxin.wx.model.teacher.TeacherInviteRewardRecord;
import com.yuxin.wx.vo.company.TeacherInviteRecordDetailListVo;
import com.yuxin.wx.vo.company.TeacherInviteRecordListVo;

/**
 * Service Interface:Users
 * @author chopin
 * @date 2015-3-12
 */
public interface TeacherInviteRewardRecordMapper extends BaseMapper<TeacherInviteRewardRecord> {
	
	int getTotalInviteNumber(Integer companyId);
	
	int getTotalConsumerTimes(Integer companyId);
	
	double getTotalConsumeBalance(Integer companyId);
	
	double getTotalRewardBalance(Integer companyId);
	
	List<TeacherInviteRecordListVo> queryRewardsRecordList(TeacherInviteRecordListVo search);
	
	int queryRewardsRecordListCount(TeacherInviteRecordListVo search);
	
	List<TeacherInviteRecordListVo> exportRewardsRecordList(TeacherInviteRecordListVo search);

	List<TeacherInviteRecordDetailListVo> queryRewardsRecordDetailList(TeacherInviteRecordDetailListVo search);
	
	int queryRewardsRecordDetailListCount(TeacherInviteRecordDetailListVo search);
	
	int getTeacherInviteNumber(TeacherInviteRecordDetailListVo search);
	
	int getTeacherConsumerTimes(TeacherInviteRecordDetailListVo search);
	
	double getTeacherConsumeBalance(TeacherInviteRecordDetailListVo search);
	
	double getTeacherRewardBalance(TeacherInviteRecordDetailListVo search);
}