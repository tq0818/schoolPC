package com.yuxin.wx.vo.student;

import java.util.ArrayList;
import java.util.List;

import com.yuxin.wx.common.BaseEntity;

public class StudentAll4CompanyVo extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	List<String> mobiles 	 = new ArrayList<String>();		/* 存网校学员手机号 */
	List<String> usernames 	 = new ArrayList<String>();		/* 存网校学员用户名 */
	List<String> identityIds = new ArrayList<String>();		/* 存网校学员身份证号 */
	List<String> emails 	 = new ArrayList<String>();		/* 存网校学员邮箱 */
	List<String> qqs 		 = new ArrayList<String>();		/* 存网校学员QQ */
	
	@Override
	public String toString() {
		return "StudentAll4CompanyVo [mobiles=" + mobiles + ", usernames=" + usernames + ", identityIds=" + identityIds
				+ ", emails=" + emails + ", qqs=" + qqs + "]";
	}
	
	public List<String> getMobiles() {
		return mobiles;
	}
	public void setMobiles(List<String> mobiles) {
		this.mobiles = mobiles;
	}
	public List<String> getUsernames() {
		return usernames;
	}
	public void setUsernames(List<String> usernames) {
		this.usernames = usernames;
	}
	public List<String> getIdentityIds() {
		return identityIds;
	}
	public void setIdentityIds(List<String> identityIds) {
		this.identityIds = identityIds;
	}
	public List<String> getEmails() {
		return emails;
	}
	public void setEmails(List<String> emails) {
		this.emails = emails;
	}
	public List<String> getQqs() {
		return qqs;
	}
	public void setQqs(List<String> qqs) {
		this.qqs = qqs;
	}
}
