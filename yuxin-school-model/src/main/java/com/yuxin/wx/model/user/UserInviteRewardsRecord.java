package com.yuxin.wx.model.user;

import java.util.Date;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import com.yuxin.wx.util.ShortDateSerializer;

import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:UserInviteRewardsRecord
 * 
 * @author chopin
 * @date 2016-7-29
 */
@SuppressWarnings("serial")
public class UserInviteRewardsRecord extends BaseEntity {
	
	
	private Integer	userId;		 /* 被邀请人ID */ 
	private Integer	parentId;		/*邀请人id*/
	private String	inviteCode;		 /* 邀请码 */ 
	private Date	createTime;		 /* 创建时间 */ 
	private Integer	rewardsType;		 /* 奖励类型 0-积分 1-代金券 */ 
	private Double	rewardsMoney;		 /* 奖励代金券的金额 */ 
	private String	rewardsCode;		 /* 奖励代金券的code值 */ 
	private Integer	rewardsIntegral;		 /* 奖励积分 */ 
	private Integer	reason;		 /* 原因：0-邀请注册 1-被邀请人注册， 2-被邀请人首次消费，3-二级被邀请人注册 4-二级被邀请人首次消费 */ 
	private Integer companyId;
	private Integer cid;			/*下级被邀请人*/
	private Integer ccid;			/*下下级被邀请人*/
	
	
	private String username;
	private String mobile;
	private String be_userName;
	private String be_mobile;
	private String mark;
	private String searchName;
	private double totalMoney;
	private int totalIntegral;
	private int inviteRegNumber;
	private int inviteConNumber;
	private int searchDateType;
	private String type;
	private String parentUsername;
	private String userName;
	private Integer twoInviteFlag;
	private String parentMobile;
	
	
	public String getParentMobile() {
		return parentMobile;
	}

	public void setParentMobile(String parentMobile) {
		this.parentMobile = parentMobile;
	}

	public Integer getTwoInviteFlag() {
		return twoInviteFlag;
	}

	public void setTwoInviteFlag(Integer twoInviteFlag) {
		this.twoInviteFlag = twoInviteFlag;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getParentUsername() {
		return parentUsername;
	}

	public void setParentUsername(String parentUsername) {
		this.parentUsername = parentUsername;
	}

	public String getType() {
		return type;
	}

	public Integer getCid() {
		return cid;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}

	public Integer getCcid() {
		return ccid;
	}

	public void setCcid(Integer ccid) {
		this.ccid = ccid;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getSearchDateType() {
		return searchDateType;
	}

	public void setSearchDateType(int searchDateType) {
		this.searchDateType = searchDateType;
	}

	public double getTotalMoney() {
		return totalMoney;
	}

	public void setTotalMoney(double totalMoney) {
		this.totalMoney = totalMoney;
	}

	public int getTotalIntegral() {
		return totalIntegral;
	}

	public void setTotalIntegral(int totalIntegral) {
		this.totalIntegral = totalIntegral;
	}

	public int getInviteRegNumber() {
		return inviteRegNumber;
	}

	public void setInviteRegNumber(int inviteRegNumber) {
		this.inviteRegNumber = inviteRegNumber;
	}

	public int getInviteConNumber() {
		return inviteConNumber;
	}

	public void setInviteConNumber(int inviteConNumber) {
		this.inviteConNumber = inviteConNumber;
	}

	// Constructor
	public UserInviteRewardsRecord() {
	}
	
	/**
	 * full Constructor
	 */
	public UserInviteRewardsRecord(Integer id, Integer userId, Integer parentId, String inviteCode, Date createTime, Integer rewardsType, Double rewardsMoney, String rewardsCode, Integer rewardsIntegral, Integer reason,Integer companyId) {
		setId(id);
		this.userId = userId;
		this.parentId = parentId;
		this.inviteCode = inviteCode;
		this.createTime = createTime;
		this.rewardsType = rewardsType;
		this.rewardsMoney = rewardsMoney;
		this.rewardsCode = rewardsCode;
		this.rewardsIntegral = rewardsIntegral;
		this.reason = reason;
		this.companyId = companyId;
	}

	// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为UserInviteRewardsRecord可以实现连缀设置属性
	
	public Integer getUserId() {
		return userId;
	}

	public UserInviteRewardsRecord setUserId(Integer userId) {
		this.userId = userId;
		return this;
	}
	
	
	public Integer getParentId() {
		return parentId;
	}

	public UserInviteRewardsRecord setParentId(Integer parentId) {
		this.parentId = parentId;
		return this;
	}
	
	
	public String getInviteCode() {
		return inviteCode;
	}

	public UserInviteRewardsRecord setInviteCode(String inviteCode) {
		this.inviteCode = inviteCode;
		return this;
	}
	
	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getCreateTime() {
		return createTime;
	}

	public UserInviteRewardsRecord setCreateTime(Date createTime) {
		this.createTime = createTime;
		return this;
	}
	
	
	public Integer getRewardsType() {
		return rewardsType;
	}

	public UserInviteRewardsRecord setRewardsType(Integer rewardsType) {
		this.rewardsType = rewardsType;
		return this;
	}
	
	
	public Double getRewardsMoney() {
		return rewardsMoney;
	}

	public UserInviteRewardsRecord setRewardsMoney(Double rewardsMoney) {
		this.rewardsMoney = rewardsMoney;
		return this;
	}
	
	
	public String getRewardsCode() {
		return rewardsCode;
	}

	public UserInviteRewardsRecord setRewardsCode(String rewardsCode) {
		this.rewardsCode = rewardsCode;
		return this;
	}
	
	
	public Integer getRewardsIntegral() {
		return rewardsIntegral;
	}

	public UserInviteRewardsRecord setRewardsIntegral(Integer rewardsIntegral) {
		this.rewardsIntegral = rewardsIntegral;
		return this;
	}
	
	
	public Integer getReason() {
		return reason;
	}

	public UserInviteRewardsRecord setReason(Integer reason) {
		this.reason = reason;
		return this;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getBe_userName() {
		return be_userName;
	}

	public void setBe_userName(String be_userName) {
		this.be_userName = be_userName;
	}

	public String getBe_mobile() {
		return be_mobile;
	}

	public void setBe_mobile(String be_mobile) {
		this.be_mobile = be_mobile;
	}

	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

	public String getSearchName() {
		return searchName;
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}

	@Override
	public String toString() {
		return "UserInviteRewardsRecord [" + "id=" + getId() + ", userId=" + userId + ", parentId=" + parentId + ", inviteCode=" + inviteCode + ", createTime=" + createTime + ", rewardsType=" + rewardsType + ", rewardsMoney=" + rewardsMoney + ", rewardsCode=" + rewardsCode + ", rewardsIntegral=" + rewardsIntegral + ", reason=" + reason +  "]";
	}
}
