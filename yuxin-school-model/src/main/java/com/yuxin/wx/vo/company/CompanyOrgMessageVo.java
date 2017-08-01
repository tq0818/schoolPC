package com.yuxin.wx.vo.company;

import java.util.Date;

import com.yuxin.wx.common.BaseEntity;

@SuppressWarnings("serial")
public class CompanyOrgMessageVo extends BaseEntity {
	
	private String messageType;
	private String title;
	private String content;
	private Integer status;
	private Integer companyId;
	private Date sendTime;
	private String sender;
	private Date registTime;
	
	private Integer limit;
	
	private Date updateTime;
	
	private String senderName;
	
	
	
	public String getSenderName() {
		return senderName;
	}
	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public CompanyOrgMessageVo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CompanyOrgMessageVo(String messageType, String title,
			String content, Integer status, Date sendTime, String sender) {
		super();
		this.messageType = messageType;
		this.title = title;
		this.content = content;
		this.status = status;
		this.sendTime = sendTime;
		this.sender = sender;
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
	public Integer getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}
	public Date getRegistTime() {
		return registTime;
	}
	public void setRegistTime(Date registTime) {
		this.registTime = registTime;
	}
	public Integer getLimit() {
		return limit;
	}
	public void setLimit(Integer limit) {
		this.limit = limit;
	}
	
	
}
