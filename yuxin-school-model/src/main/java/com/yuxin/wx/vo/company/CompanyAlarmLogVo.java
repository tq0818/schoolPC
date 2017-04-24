package com.yuxin.wx.vo.company;


import java.util.Date;

import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:CompanyMemberService
 * 
 * @author zhang.zx
 * @date 2015-4-23
 */
@SuppressWarnings("serial")
public class CompanyAlarmLogVo extends BaseEntity {
	private Integer companyId;
	private String alarmBusinessType; /* 警告业务类型*/
	private String alarmSendType; /* 警告发送类型*/
	private Date sendTime; /* 发送时间*/
	
	public CompanyAlarmLogVo() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public CompanyAlarmLogVo(Integer companyId, String alarmBusinessType,
			String alarmSendType, Date sendTime) {
		super();
		this.companyId = companyId;
		this.alarmBusinessType = alarmBusinessType;
		this.alarmSendType = alarmSendType;
		this.sendTime = sendTime;
	}


	public Integer getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}
	public String getAlarmBusinessType() {
		return alarmBusinessType;
	}
	public void setAlarmBusinessType(String alarmBusinessType) {
		this.alarmBusinessType = alarmBusinessType;
	}
	public String getAlarmSendType() {
		return alarmSendType;
	}
	public void setAlarmSendType(String alarmSendType) {
		this.alarmSendType = alarmSendType;
	}
	public Date getSendTime() {
		return sendTime;
	}
	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}
	
	
}
