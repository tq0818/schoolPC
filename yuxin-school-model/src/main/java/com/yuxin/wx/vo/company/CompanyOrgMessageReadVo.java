package com.yuxin.wx.vo.company;

import java.util.Date;

import com.yuxin.wx.common.BaseEntity;

@SuppressWarnings("serial")
public class CompanyOrgMessageReadVo extends BaseEntity {
	
	private String messageType;
	private String title;
	private String content;
	private Integer status;
	private Date sendTime;
	private String sender;
	
	private Integer companyId;
	private Integer orgMessageId;
	private Integer readFlag;
	private Integer delFlag;
	
	private String registTime;
	public CompanyOrgMessageReadVo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CompanyOrgMessageReadVo(Integer companyId, Integer orgMessageId,
			Integer readFlag, Integer delFlag) {
		super();
		this.companyId = companyId;
		this.orgMessageId = orgMessageId;
		this.readFlag = readFlag;
		this.delFlag = delFlag;
	}
	public Integer getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}
	public Integer getOrgMessageId() {
		return orgMessageId;
	}
	public void setOrgMessageId(Integer orgMessageId) {
		this.orgMessageId = orgMessageId;
	}
	public Integer getReadFlag() {
		return readFlag;
	}
	public void setReadFlag(Integer readFlag) {
		this.readFlag = readFlag;
	}
	public Integer getDelFlag() {
		return delFlag;
	}
	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}
	public String getMessageType() {
		return messageType;
	}
	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Date getSendTime() {
		return sendTime;
	}
	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getRegistTime() {
		return registTime;
	}
	public void setRegistTime(String registTime) {
		this.registTime = registTime;
	}
	
}
