package com.yuxin.wx.vo.company;




import com.yuxin.wx.common.BaseEntity;

@SuppressWarnings("serial")
public class TeacherInviteRecordDetailListVo extends BaseEntity {
	
	private Integer teacherId;
	private Integer companyId;
	private Integer	stuId;		
	private String	name;
	private String	mobile;
	private String	commodityName;
	private String	inviteDate;
	private String	consumeDate;
	private double	consumeBalance;
	private double	rewardBalance;
	private String	sortName;
	private String	sortType;
	private Integer consumeFlag;
	private Integer reason;
	private Integer payOrderId;
	
	public Integer getPayOrderId() {
		return payOrderId;
	}
	public void setPayOrderId(Integer payOrderId) {
		this.payOrderId = payOrderId;
	}
	public Integer getStuId() {
		return stuId;
	}
	public void setStuId(Integer stuId) {
		this.stuId = stuId;
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
	public String getCommodityName() {
		return commodityName;
	}
	public void setCommodityName(String commodityName) {
		this.commodityName = commodityName;
	}
	public String getInviteDate() {
		return inviteDate;
	}
	public void setInviteDate(String inviteDate) {
		this.inviteDate = inviteDate;
	}
	public String getConsumeDate() {
		return consumeDate;
	}
	public void setConsumeDate(String consumeDate) {
		this.consumeDate = consumeDate;
	}
	public double getConsumeBalance() {
		return consumeBalance;
	}
	public void setConsumeBalance(double consumeBalance) {
		this.consumeBalance = consumeBalance;
	}
	public double getRewardBalance() {
		return rewardBalance;
	}
	public void setRewardBalance(double rewardBalance) {
		this.rewardBalance = rewardBalance;
	}
	public Integer getTeacherId() {
		return teacherId;
	}
	public void setTeacherId(Integer teacherId) {
		this.teacherId = teacherId;
	}
	public Integer getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}
	public String getSortName() {
		return sortName;
	}
	public void setSortName(String sortName) {
		this.sortName = sortName;
	}
	public String getSortType() {
		return sortType;
	}
	public void setSortType(String sortType) {
		this.sortType = sortType;
	}
	public Integer getConsumeFlag() {
		return consumeFlag;
	}
	public void setConsumeFlag(Integer consumeFlag) {
		this.consumeFlag = consumeFlag;
	}
	public Integer getReason() {
		return reason;
	}
	public void setReason(Integer reason) {
		this.reason = reason;
	}
	
	
}
