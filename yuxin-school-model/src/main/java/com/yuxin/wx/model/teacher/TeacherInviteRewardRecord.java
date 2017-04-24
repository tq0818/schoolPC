package com.yuxin.wx.model.teacher;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.yuxin.wx.common.BaseEntity;
import com.yuxin.wx.util.ShortDateSerializer;

/**
 * POJO:TeacherInviteRewardRecord
 * 
 * @author chopin
 * @date 2017-3-13
 */
@SuppressWarnings("serial")
public class TeacherInviteRewardRecord extends BaseEntity {

    private Integer stuId;
    private Integer teacherId;
    private String inviteCode; /* 邀请码 */
    private Date recordTime;
    private Double stuRewardsMoney; /* 奖励代金券的金额 */
    private String stuRewardsCode; /* 学员获得的奖励代金券的code值 */
    private Integer reason; /* 原因：1-被邀请人注册， 2-被邀请人首次消费， 3-被邀请人消费 */
    private Integer companyId; /* 公司ID */
    private Double rewardsMoney; /* 老师获得的奖励金额 */
    private Integer payOrderId; /* 订单id */

    // Constructor
    public TeacherInviteRewardRecord() {
    }

    // getter && setter
    // 在setter方法最后加上"return this;"并把返回参数改为TeacherInviteRewardRecord可以实现连缀设置属性

    public TeacherInviteRewardRecord(Integer stuId, Integer teacherId, String inviteCode, Date recordTime, Double stuRewardsMoney, String stuRewardsCode,
            Integer reason, Integer companyId, Double rewardsMoney, Integer payOrderId) {
        super();
        this.stuId = stuId;
        this.teacherId = teacherId;
        this.inviteCode = inviteCode;
        this.recordTime = recordTime;
        this.stuRewardsMoney = stuRewardsMoney;
        this.stuRewardsCode = stuRewardsCode;
        this.reason = reason;
        this.companyId = companyId;
        this.rewardsMoney = rewardsMoney;
        this.payOrderId = payOrderId;
    }

    public Integer getStuId() {
        return stuId;
    }

    public TeacherInviteRewardRecord setStuId(Integer stuId) {
        this.stuId = stuId;
        return this;
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public TeacherInviteRewardRecord setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
        return this;
    }

    public String getInviteCode() {
        return inviteCode;
    }

    public TeacherInviteRewardRecord setInviteCode(String inviteCode) {
        this.inviteCode = inviteCode;
        return this;
    }

    @JsonSerialize(using = ShortDateSerializer.class)
    public Date getRecordTime() {
        return recordTime;
    }

    public TeacherInviteRewardRecord setRecordTime(Date recordTime) {
        this.recordTime = recordTime;
        return this;
    }

    public Double getStuRewardsMoney() {
        return stuRewardsMoney;
    }

    public TeacherInviteRewardRecord setStuRewardsMoney(Double stuRewardsMoney) {
        this.stuRewardsMoney = stuRewardsMoney;
        return this;
    }

    public String getStuRewardsCode() {
        return stuRewardsCode;
    }

    public TeacherInviteRewardRecord setStuRewardsCode(String stuRewardsCode) {
        this.stuRewardsCode = stuRewardsCode;
        return this;
    }

    public Integer getReason() {
        return reason;
    }

    public TeacherInviteRewardRecord setReason(Integer reason) {
        this.reason = reason;
        return this;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public TeacherInviteRewardRecord setCompanyId(Integer companyId) {
        this.companyId = companyId;
        return this;
    }

    public Double getRewardsMoney() {
        return rewardsMoney;
    }

    public TeacherInviteRewardRecord setRewardsMoney(Double rewardsMoney) {
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
        return "TeacherInviteRewardRecord [stuId=" + stuId + ", teacherId=" + teacherId + ", inviteCode=" + inviteCode + ", recordTime=" + recordTime
                + ", stuRewardsMoney=" + stuRewardsMoney + ", stuRewardsCode=" + stuRewardsCode + ", reason=" + reason + ", companyId=" + companyId
                + ", rewardsMoney=" + rewardsMoney + ", payOrderId=" + payOrderId + "]";
    }
}