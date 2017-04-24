package com.yuxin.wx.vo.company;



import java.util.Date;

import com.yuxin.wx.common.BaseEntity;

@SuppressWarnings("serial")
public class TeacherInviteRecordListVo extends BaseEntity {
	
	private Integer	stuId;		
	private Integer	teacherId;		
	private String	inviteCode;		 /* 邀请码 */ 
	private Date	recordTime;		
	private Double	stuRewardsMoney;		 /* 奖励代金券的金额 */ 
	private String	stuRewardsCode;		 /* 学员获得的奖励代金券的code值 */ 
	private Integer	reason;		 /* 原因：1-被邀请人注册， 2-被邀请人首次消费， 3-被邀请人消费 */ 
	private Integer	companyId;		 /* 公司ID */ 
	private Double	rewardsMoney;		 /* 老师获得的奖励金额 */
	private String 	startTime;
	private String	endTime;
	private String	name;
	private String	mobile;
	private Integer	inviteNumber;
	private Integer consumeTimes;
	private Double	consumeBalance;
	private Double	rewardBalance;
	private Integer searchDateType;
	private String	sortType;
	private String 	sort;
	

	public TeacherInviteRecordListVo() {
		super();
	}

	public Integer getStuId() {
		return stuId;
	}

	public void setStuId(Integer stuId) {
		this.stuId = stuId;
	}

	public Integer getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(Integer teacherId) {
		this.teacherId = teacherId;
	}

	public String getInviteCode() {
		return inviteCode;
	}

	public void setInviteCode(String inviteCode) {
		this.inviteCode = inviteCode;
	}

	public Date getRecordTime() {
		return recordTime;
	}

	public void setRecordTime(Date recordTime) {
		this.recordTime = recordTime;
	}

	public Double getStuRewardsMoney() {
		return stuRewardsMoney;
	}

	public void setStuRewardsMoney(Double stuRewardsMoney) {
		this.stuRewardsMoney = stuRewardsMoney;
	}

	public String getStuRewardsCode() {
		return stuRewardsCode;
	}

	public void setStuRewardsCode(String stuRewardsCode) {
		this.stuRewardsCode = stuRewardsCode;
	}

	public Integer getReason() {
		return reason;
	}

	public void setReason(Integer reason) {
		this.reason = reason;
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	public Double getRewardsMoney() {
		return rewardsMoney;
	}

	public void setRewardsMoney(Double rewardsMoney) {
		this.rewardsMoney = rewardsMoney;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Integer getInviteNumber() {
		return inviteNumber;
	}

	public void setInviteNumber(Integer inviteNumber) {
		this.inviteNumber = inviteNumber;
	}

	public Integer getConsumeTimes() {
		return consumeTimes;
	}

	public void setConsumeTimes(Integer consumeTimes) {
		this.consumeTimes = consumeTimes;
	}

	public Double getConsumeBalance() {
		return consumeBalance;
	}

	public void setConsumeBalance(Double consumeBalance) {
		this.consumeBalance = consumeBalance;
	}

	public Double getRewardBalance() {
		return rewardBalance;
	}

	public void setRewardBalance(Double rewardBalance) {
		this.rewardBalance = rewardBalance;
	}

	public Integer getSearchDateType() {
		return searchDateType;
	}

	public void setSearchDateType(Integer searchDateType) {
		this.searchDateType = searchDateType;
	}

	public String getSortType() {
		return sortType;
	}

	public void setSortType(String sortType) {
		this.sortType = sortType;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

}
