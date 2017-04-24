package com.yuxin.wx.model.company;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.yuxin.wx.common.BaseEntity;
import com.yuxin.wx.util.ShortDateSerializer;

/**
 * POJO:CompanyEmailHistory
 * 
 * @author chopin
 * @date 2015-4-23
 */
@SuppressWarnings("serial")
public class CompanyEmailHistory extends BaseEntity {
	
	
	private Integer	receiverUserId;		 /* 接收者用户id */ 
	private String	receiverEmail;		 /* 接收者邮箱 */ 
	private Date	sendTime;		 /* 发送时间 */ 
	private String	title;		 /* 邮件标题 */ 
	private String	content;		 /* 邮件内容 */ 
	private Integer schoolId;	/* 学校id*/
	private Integer companyId;	/* 公司id*/
	private Integer sendStatus;/*发送状态：成功1；失败0*/
	private String sendResult;/*发送返回结果*/
	private String businessType;/*业务类型*/
	private Integer	messageId;		 /* 学员通知表id，学员通知时使用该字段 */ 
	
	private Date	startTime;	/* 开始时间*/
	private Date	endTime;	/* 结束时间*/

	// Constructor
	public CompanyEmailHistory() {
	}
	
	/**
	 * full Constructor
	 */
	public CompanyEmailHistory(Integer id, Integer receiverUserId, String receiverEmail, Date sendTime, String title, String content) {
		setId(id);
		this.receiverUserId = receiverUserId;
		this.receiverEmail = receiverEmail;
		this.sendTime = sendTime;
		this.title = title;
		this.content = content;
	}

	// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为CompanyEmailHistory可以实现连缀设置属性
	
	public Integer getReceiverUserId() {
		return receiverUserId;
	}

	public CompanyEmailHistory setReceiverUserId(Integer receiverUserId) {
		this.receiverUserId = receiverUserId;
		return this;
	}
	
	
	public String getReceiverEmail() {
		return receiverEmail;
	}

	public CompanyEmailHistory setReceiverEmail(String receiverEmail) {
		this.receiverEmail = receiverEmail;
		return this;
	}
	
	public Integer getMessageId() {
		return messageId;
	}

	public void setMessageId(Integer messageId) {
		this.messageId = messageId;
	}

	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getSendTime() {
		return sendTime;
	}

	public CompanyEmailHistory setSendTime(Date sendTime) {
		this.sendTime = sendTime;
		return this;
	}
	
	
	public String getTitle() {
		return title;
	}

	public CompanyEmailHistory setTitle(String title) {
		this.title = title;
		return this;
	}
	
	
	public String getContent() {
		return content;
	}

	public CompanyEmailHistory setContent(String content) {
		this.content = content;
		return this;
	}
	
	@Override
	public String toString() {
		return "CompanyEmailHistory [" + "id=" + getId() + ", receiverUserId=" + receiverUserId + ", receiverEmail=" + receiverEmail + ", sendTime=" + sendTime + ", title=" + title + ", content=" + content +  "]";
	}

	public Integer getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Integer getSendStatus() {
		return sendStatus;
	}

	public void setSendStatus(Integer sendStatus) {
		this.sendStatus = sendStatus;
	}

	public String getSendResult() {
		return sendResult;
	}

	public void setSendResult(String sendResult) {
		this.sendResult = sendResult;
	}

	public String getBusinessType() {
		return businessType;
	}

	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}
}
