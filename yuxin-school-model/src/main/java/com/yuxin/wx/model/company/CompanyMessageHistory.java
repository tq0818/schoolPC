package com.yuxin.wx.model.company;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.yuxin.wx.common.BaseEntity;
import com.yuxin.wx.util.ShortDateSerializer;

/**
 * POJO:CompanyMessageHistory
 * 
 * @author chopin
 * @date 2015-4-23
 */
@SuppressWarnings("serial")
public class CompanyMessageHistory extends BaseEntity {
	
	
	private String	receiverUserId;		/* 接收者用户id */ 
	private String	receiverMobile;		/* 接收者手机号 */ 
	private Date	sendTime;			/* 发送时间 */ 
	private String	content;			/* 发送内容 */ 
	private Integer	sendStatus;			/* 发送状态*/
	private	String	sendResult;			/* 返回结果*/
	private	String	businessType;		/* 业务类型*/
	private	Integer costNum;			/* 花费短信条数*/
	private	Integer	companyId;			/* 公司id*/
	private	Integer	schoolId;			/* 学校id*/
	private Integer	messageId;			/* 学院通知id*/
	
	private Date	startTime;			/* 开始时间*/
	private Date	endTime;			/* 结束时间*/

	// Constructor
	public CompanyMessageHistory() {
	}
	
	/**
	 * full Constructor
	 */
	public CompanyMessageHistory(Integer id, String receiverUserId, String receiverMobile, Date sendTime, String content) {
		setId(id);
		this.receiverUserId = receiverUserId;
		this.receiverMobile = receiverMobile;
		this.sendTime = sendTime;
		this.content = content;
	}

	// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为CompanyMessageHistory可以实现连缀设置属性
	
	public String getReceiverUserId() {
		return receiverUserId;
	}

	public CompanyMessageHistory setReceiverUserId(String receiverUserId) {
		this.receiverUserId = receiverUserId;
		return this;
	}
	
	
	public String getReceiverMobile() {
		return receiverMobile;
	}

	public CompanyMessageHistory setReceiverMobile(String receiverMobile) {
		this.receiverMobile = receiverMobile;
		return this;
	}
	
	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getSendTime() {
		return sendTime;
	}

	public CompanyMessageHistory setSendTime(Date sendTime) {
		this.sendTime = sendTime;
		return this;
	}
	
	
	public String getContent() {
		return content;
	}

	public CompanyMessageHistory setContent(String content) {
		this.content = content;
		return this;
	}
	
	@Override
	public String toString() {
		return "CompanyMessageHistory [" + "id=" + getId() + ", receiverUserId=" + receiverUserId + ", receiverMobile=" + receiverMobile + ", sendTime=" + sendTime + ", content=" + content +  "]";
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

	public Integer getCostNum() {
		return costNum;
	}

	public void setCostNum(Integer costNum) {
		this.costNum = costNum;
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	public Integer getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
	}

	public Integer getMessageId() {
		return messageId;
	}

	public void setMessageId(Integer messageId) {
		this.messageId = messageId;
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
}
