package com.yuxin.wx.vo.user;


/**
 * POJO:UserInviteRewardsRecord
 * 
 * @author chopin
 * @date 2016-7-29
 */
public class UserInviteRewardsRecordVo {
	
	private double totalRewards;
	private Integer totalIntengral;
	private Integer inviteNum;
	
	public UserInviteRewardsRecordVo() {
		
	}

	public UserInviteRewardsRecordVo(double totalRewards, Integer totalIntengral) {
		this.totalRewards = totalRewards;
		this.totalIntengral = totalIntengral;
	}

	public double getTotalRewards() {
		return totalRewards;
	}

	public void setTotalRewards(double totalRewards) {
		this.totalRewards = totalRewards;
	}

	public Integer getTotalIntengral() {
		return totalIntengral;
	}

	public void setTotalIntengral(Integer totalIntengral) {
		this.totalIntengral = totalIntengral;
	}

	public Integer getInviteNum() {
		return inviteNum;
	}

	public void setInviteNum(Integer inviteNum) {
		this.inviteNum = inviteNum;
	}
	
	
}
