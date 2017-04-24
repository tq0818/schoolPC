package com.yuxin.wx.model.statistics;

import java.io.Serializable;
import java.util.Date;

import com.yuxin.wx.common.BaseEntity;

public class Statistics extends BaseEntity implements Serializable {

	private static final long serialVersionUID = -8543023462022222616L;
	private Integer classLessionId;
	private String mobile;
	private String userName;
	private String nickName;
	private String name;
	private String email;
	private Date login_time;

	
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Integer getClassLessionId() {
		return classLessionId;
	}

	public void setClassLessionId(Integer classLessionId) {
		this.classLessionId = classLessionId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getLogin_time() {
		return login_time;
	}

	public void setLogin_time(Date login_time) {
		this.login_time = login_time;
	}

}
