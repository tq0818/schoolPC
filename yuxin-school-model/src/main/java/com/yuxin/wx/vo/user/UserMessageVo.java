package com.yuxin.wx.vo.user;

import com.yuxin.wx.common.BaseEntity;
import com.yuxin.wx.vo.company.CompanyStudentMessageVo;

public class UserMessageVo extends BaseEntity {

	private Integer	userId;		 /* 用户表id */ 
	private Integer	messageId;		 /* 学员通知表id */ 
	private Integer	readFlag;		 /* 阅读标记（1：已读；0：未读） */ 
	
	private CompanyStudentMessageVo message;	/* 信息标题*/

	public Integer getUserId() {
		return userId;
	}
	
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getMessageId() {
		return messageId;
	}

	public void setMessageId(Integer messageId) {
		this.messageId = messageId;
	}

	public Integer getReadFlag() {
		return readFlag;
	}

	public void setReadFlag(Integer readFlag) {
		this.readFlag = readFlag;
	}

	public CompanyStudentMessageVo getMessage() {
		return message;
	}

	public void setMessage(CompanyStudentMessageVo message) {
		this.message = message;
	}
	
}
