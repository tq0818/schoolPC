package com.yuxin.wx.vo.student;

import com.yuxin.wx.common.BaseEntity;

public class SelectStudentOrUsersfrontVo extends BaseEntity {

	private static final long serialVersionUID = 1L;
	private String mobile;
	private String username;
	private Integer companyId;

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	@Override
	public String toString() {
		return "SelectStudentOrUsersfrontVo [mobile=" + mobile + ", username=" + username + ", companyId=" + companyId
				+ "]";
	}

}
