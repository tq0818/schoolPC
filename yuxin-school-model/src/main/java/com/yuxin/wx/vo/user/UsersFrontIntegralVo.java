package com.yuxin.wx.vo.user;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.yuxin.wx.common.BaseEntity;
import com.yuxin.wx.util.ShortDateSerializer;

@SuppressWarnings("serial")
public class UsersFrontIntegralVo extends BaseEntity {

	private Integer userId;
	private Integer integralRemaining;
	private Integer stuId;
	private String stuName;
	private String mobile;
	private Integer companyId;
	private String username;
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public UsersFrontIntegralVo() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public UsersFrontIntegralVo(Integer userId, Integer integralRemaining,
			Integer stuId, String stuName, String mobile, Integer companyId,String username) {
		super();
		this.userId = userId;
		this.integralRemaining = integralRemaining;
		this.stuId = stuId;
		this.stuName = stuName;
		this.mobile = mobile;
		this.companyId = companyId;
		this.username = username;
	}

	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getIntegralRemaining() {
		return integralRemaining;
	}
	public void setIntegralRemaining(Integer integralRemaining) {
		this.integralRemaining = integralRemaining;
	}
	public Integer getStuId() {
		return stuId;
	}
	public void setStuId(Integer stuId) {
		this.stuId = stuId;
	}
	public String getStuName() {
		return stuName;
	}
	public void setStuName(String stuName) {
		this.stuName = stuName;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public Integer getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}
	
}
