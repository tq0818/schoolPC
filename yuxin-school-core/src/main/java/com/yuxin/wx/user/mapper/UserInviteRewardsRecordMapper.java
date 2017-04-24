package com.yuxin.wx.user.mapper;

import java.util.List;
import java.util.Map;

import com.yuxin.wx.common.BaseMapper;
import com.yuxin.wx.model.user.UserInviteRewardsRecord;
import com.yuxin.wx.vo.user.UserInviteRewardsRecordVo;

/**
 * Service Interface:Users
 * 
 * @author chopin
 * @date 2015-3-12
 */
public interface UserInviteRewardsRecordMapper extends BaseMapper<UserInviteRewardsRecord> {

    List<UserInviteRewardsRecord> queryUserWardRecord(UserInviteRewardsRecord search);

    Integer queryUserWardRecordCount(UserInviteRewardsRecord search);

    UserInviteRewardsRecordVo queryUserWardTotal(UserInviteRewardsRecord search);

    UserInviteRewardsRecord findRewardRecordBySearch(UserInviteRewardsRecord search);

    // 得到该机构发放的代金券总金额
    public double getTotalRewardsMoney(Integer companyId);

    // 得到该机构发放的总积分
    public int getTotalRewardsIntegral(Integer companyId);

    // 得到该机构下被邀请注册的总人数
    public int getTotalRegNumber(Integer companyId);

    // 得到该机构下被邀请消费的总人数
    public int getTotalConNumber(Integer companyId);

    // 查询所有
    public List<UserInviteRewardsRecord> queryForAll(UserInviteRewardsRecord search);

    public List<UserInviteRewardsRecord> findDetailRewardsRecordByParentId(UserInviteRewardsRecord search);

    public int findDetailRewardsRecordCountByParentId(UserInviteRewardsRecord search);

    List<UserInviteRewardsRecord> findByPage(UserInviteRewardsRecord search);

    List<UserInviteRewardsRecord> exprotWithoutPage(UserInviteRewardsRecord search);

    Map<String, Object> findInvitedTypeAndId(Map<String, Object> condition);
}