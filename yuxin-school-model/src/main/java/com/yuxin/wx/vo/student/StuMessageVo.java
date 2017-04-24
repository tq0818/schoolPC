package com.yuxin.wx.vo.student;

import java.util.Date;

import com.yuxin.wx.common.BaseEntity;

public class StuMessageVo extends BaseEntity{

	private String	name;			/* 学员姓名*/
	private String	phone;			/* 手机号*/
	private String	email;			/* 邮箱 */
	private Date	applyTime;		/* 报名时间*/
	
	private Integer	companyId;		/* 公司id*/
	private Integer	schoolId;		/* 学校id*/
	private Integer	classTypeId;	/* 班型id*/
	private Integer	moduleNoId;		/* 班号id*/
	private Integer	itemOneId;		/* 一级项目id*/
	private Integer	itemSecondId;	/* 二级项目id*/
	private Integer	status;			/* 发送状态*/
	private Integer messageId;		/* 消息id*/
	
	
	private String	messageMethod;		 /* 通知方式：短信通知：mobile_message，站内信通知：web_message，邮件 email_message */ 
	private Integer groupOneId;
	private Integer groupTwoId;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Date getApplyTime() {
		return applyTime;
	}
	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
	}
	
	public String getMessageMethod() {
		return messageMethod;
	}
	public void setMessageMethod(String messageMethod) {
		this.messageMethod = messageMethod;
	}
	public Integer getGroupOneId() {
		return groupOneId;
	}
	public void setGroupOneId(Integer groupOneId) {
		this.groupOneId = groupOneId;
	}
	public Integer getGroupTwoId() {
		return groupTwoId;
	}
	public void setGroupTwoId(Integer groupTwoId) {
		this.groupTwoId = groupTwoId;
	}
	public StuMessageVo() {
		super();
	}
	public StuMessageVo(Integer id, String name, String phone, Date applyTime) {
		super();
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.applyTime = applyTime;
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
	public Integer getClassTypeId() {
		return classTypeId;
	}
	public void setClassTypeId(Integer classTypeId) {
		this.classTypeId = classTypeId;
	}
	public Integer getModuleNoId() {
		return moduleNoId;
	}
	public void setModuleNoId(Integer moduleNoId) {
		this.moduleNoId = moduleNoId;
	}
	public Integer getItemOneId() {
		return itemOneId;
	}
	public void setItemOneId(Integer itemOneId) {
		this.itemOneId = itemOneId;
	}
	public Integer getItemSecondId() {
		return itemSecondId;
	}
	public void setItemSecondId(Integer itemSecondId) {
		this.itemSecondId = itemSecondId;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getMessageId() {
		return messageId;
	}
	public void setMessageId(Integer messageId) {
		this.messageId = messageId;
	}
}
