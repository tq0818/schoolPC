package com.yuxin.wx.company.mapper;


import java.util.List;


import com.yuxin.wx.common.BaseMapper;
import com.yuxin.wx.model.company.OrganInviteRewardRecord;
import com.yuxin.wx.vo.company.OrgInviteRecordDetailListVo;
import com.yuxin.wx.vo.company.OrgInviteRecordListVo;

/**
 * Service Interface:Users
 * @author chopin
 * @date 2015-3-12
 */
public interface OrganInviteRewardRecordMapper extends BaseMapper<OrganInviteRewardRecord> {
	
	int getTotalInviteNumber(Integer companyId);
	
	int getTotalConsumerTimes(Integer companyId);
	
	double getTotalConsumeBalance(Integer companyId);
	
	double getTotalRewardBalance(Integer companyId);
	
	List<OrgInviteRecordListVo> queryRewardsRecordList(OrgInviteRecordListVo search);
	
	int queryRewardsRecordListCount(OrgInviteRecordListVo search);
	
	List<OrgInviteRecordListVo> exportRewardsRecordList(OrgInviteRecordListVo search);

	List<OrgInviteRecordDetailListVo> queryRewardsRecordDetailList(OrgInviteRecordDetailListVo search);
	
	int queryRewardsRecordDetailListCount(OrgInviteRecordDetailListVo search);
	
	int getProxyDetailInviteNumber(OrgInviteRecordDetailListVo search);
	
	int getProxyDetailConsumerTimes(OrgInviteRecordDetailListVo search);
	
	double getProxyDetailConsumeBalance(OrgInviteRecordDetailListVo search);
	
	double getProxyDetailRewardBalance(OrgInviteRecordDetailListVo search);
}