package com.yuxin.wx.model.company;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.yuxin.wx.common.BaseEntity;
import com.yuxin.wx.util.ShortDateSerializer;

/**
 * POJO:OrganInviteRewardRecord
 * 
 * @author chopin
 * @date 2017-3-13
 */
@SuppressWarnings("serial")
public class OrganInviteRewardRecord extends BaseEntity {

    private Integer stuId;
    private Integer proxyOrganId; /* 代理机构ID */
    private String inviteCode;
    private Date recordTime;
    private Double stuRewardsMoney;
    private String stuRewardsCode;
    private Integer reason; /* 原因：1-被邀请人注册， 2-被邀请人首次消费， 3-被邀请人消费 */
    private Integer companyId;
    private Double rewardsMoney; /* 机构获得的奖励金额 */
    private Integer payOrderId; /* 订单id */

    // Constructor
    public OrganInviteRewardRecord() {
    }

    public OrganInviteRewardRecord(Integer stuId, Integer proxyOrganId, String inviteCode, Date recordTime, Double stuRewardsMoney, String stuRewardsCode,
            Integer reason, Integer companyId, Double rewardsMoney, Integer payOrderId) {
        super();
        this.stuId = stuId;
        this.proxyOrganId = proxyOrganId;
        this.inviteCode = inviteCode;
        this.recordTime = recordTime;
        this.stuRewardsMoney = stuRewardsMoney;
        this.stuRewardsCode = stuRewardsCode;
        this.reason = reason;
        this.companyId = companyId;
        this.rewardsMoney = rewardsMoney;
        this.payOrderId = payOrderId;
    }

    // getter && setter
    // 在setter方法最后加上"return this;"并把返回参数改为OrganInviteRewardRecord可以实现连缀设置属性

    public Integer getStuId() {
        return stuId;
    }

    public OrganInviteRewardRecord setStuId(Integer stuId) {
        this.stuId = stuId;
        return this;
    }

    public Integer getProxyOrganId() {
        return proxyOrganId;
    }

    public OrganInviteRewardRecord setProxyOrganId(Integer proxyOrganId) {
        this.proxyOrganId = proxyOrganId;
        return this;
    }

    public String getInviteCode() {
        return inviteCode;
    }

    public OrganInviteRewardRecord setInviteCode(String inviteCode) {
        this.inviteCode = inviteCode;
        return this;
    }

    @JsonSerialize(using = ShortDateSerializer.class)
    public Date getRecordTime() {
        return recordTime;
    }

    public OrganInviteRewardRecord setRecordTime(Date recordTime) {
        this.recordTime = recordTime;
        return this;
    }

    public Double getStuRewardsMoney() {
        return stuRewardsMoney;
    }

    public OrganInviteRewardRecord setStuRewardsMoney(Double stuRewardsMoney) {
        this.stuRewardsMoney = stuRewardsMoney;
        return this;
    }

    public String getStuRewardsCode() {
        return stuRewardsCode;
    }

    public OrganInviteRewardRecord setStuRewardsCode(String stuRewardsCode) {
        this.stuRewardsCode = stuRewardsCode;
        return this;
    }

    public Integer getReason() {
        return reason;
    }

    public OrganInviteRewardRecord setReason(Integer reason) {
        this.reason = reason;
        return this;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public OrganInviteRewardRecord setCompanyId(Integer companyId) {
        this.companyId = companyId;
        return this;
    }

    public Double getRewardsMoney() {
        return rewardsMoney;
    }

    public OrganInviteRewardRecord setRewardsMoney(Double rewardsMoney) {
        this.rewardsMoney = rewardsMoney;
        return this;
    }

    public Integer getPayOrderId() {
        return payOrderId;
    }

    public void setPayOrderId(Integer payOrderId) {
        this.payOrderId = payOrderId;
    }

    @Override
    public String toString() {
        return "OrganInviteRewardRecord [stuId=" + stuId + ", proxyOrganId=" + proxyOrganId + ", inviteCode=" + inviteCode + ", recordTime=" + recordTime
                + ", stuRewardsMoney=" + stuRewardsMoney + ", stuRewardsCode=" + stuRewardsCode + ", reason=" + reason + ", companyId=" + companyId
                + ", rewardsMoney=" + rewardsMoney + ", payOrderId=" + payOrderId + "]";
    }
}