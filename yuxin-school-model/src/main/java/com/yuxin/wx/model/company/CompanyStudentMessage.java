package com.yuxin.wx.model.company;

import java.util.Date;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import com.yuxin.wx.util.ShortDateSerializer;

import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:CompanyStudentMessage
 * 
 * @author wang.zx
 * @date 2015-6-4
 */
@SuppressWarnings("serial")
public class CompanyStudentMessage extends BaseEntity {
	
	
	private String	title;		 /* 通知标题 */ 
	private String	content;		 /* 通知内容 */ 
	private String	messageType;		 /* 通知类型：课程通知:class_type，班号通知:module_no，指定通知:special，分组student_group */ 
	private String	messageMethod;		 /* 通知方式：短信通知：mobile_message，站内信通知：web_message，邮件 email_message */ 
	private Integer	itemOneId;		
	private Integer	itemSecondId;		
	private Integer	classTypeId;		 /* 班型id */ 
	private String	classTypeName;		 /* 班型名称 */ 
	private Integer	moduleNoId;		 /* 班号id */ 
	private String	moduleNoName;		 /* 班号名称 */ 
	private Integer	creator;		 /* 操作人 */ 
	private Date	createTime;		 /* 操作时间 */ 
	private Integer	messageCost;		 /* 花费短信条数 */ 
	private Integer	sendNum;		 /* 发信人数 */ 
	private Integer	failNum;		 /* 发送失败人数 */ 
	private String	messageStatus;		 /* 通知状态：未提交发送；已提交发送；已执行 */ 
	private Integer	schoolId;		
	private Integer	companyId;		
	private String	creatorName;	/* 操作人姓名 */
	private String	username;		/* 操作人姓名*/
	private String	mobile;			/* 操作人手机 */
	private Integer umId;			/*users_message主键*/
	private Integer readFlag;		/*阅读标记（1：已读；0：未读）*/
	
	private Integer groupOneId;
	private Integer groupTwoId;
	private String emailTitle;
	
	private String groupOneName;
	private String groupTwoName;
	private String	itemOneCode;
	private String	itemSecondCode;
	private String	itemThirdCode;
	// Constructor
	public CompanyStudentMessage() {
	}
	
	/**
	 * full Constructor
	 */
	public CompanyStudentMessage(Integer id, String title, String content, String messageType, String messageMethod, Integer itemOneId, Integer itemSecondId, Integer classTypeId, String classTypeName, Integer moduleNoId, String moduleNoName, Integer creator, Date createTime, Integer messageCost, Integer sendNum, Integer failNum, String messageStatus, Integer schoolId, Integer companyId) {
		setId(id);
		this.title = title;
		this.content = content;
		this.messageType = messageType;
		this.messageMethod = messageMethod;
		this.itemOneId = itemOneId;
		this.itemSecondId = itemSecondId;
		this.classTypeId = classTypeId;
		this.classTypeName = classTypeName;
		this.moduleNoId = moduleNoId;
		this.moduleNoName = moduleNoName;
		this.creator = creator;
		this.createTime = createTime;
		this.messageCost = messageCost;
		this.sendNum = sendNum;
		this.failNum = failNum;
		this.messageStatus = messageStatus;
		this.schoolId = schoolId;
		this.companyId = companyId;
	}

	// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为CompanyStudentMessage可以实现连缀设置属性
	
	public Integer getGroupOneId() {
		return groupOneId;
	}

	public String getGroupOneName() {
		return groupOneName;
	}

	public void setGroupOneName(String groupOneName) {
		this.groupOneName = groupOneName;
	}

	public String getGroupTwoName() {
		return groupTwoName;
	}

	public void setGroupTwoName(String groupTwoName) {
		this.groupTwoName = groupTwoName;
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

	public String getEmailTitle() {
		return emailTitle;
	}

	public void setEmailTitle(String emailTitle) {
		this.emailTitle = emailTitle;
	}

	public String getTitle() {
		return title;
	}

	public CompanyStudentMessage setTitle(String title) {
		this.title = title;
		return this;
	}
	
	
	public String getContent() {
		return content;
	}

	public CompanyStudentMessage setContent(String content) {
		this.content = content;
		return this;
	}
	
	
	public String getMessageType() {
		return messageType;
	}

	public CompanyStudentMessage setMessageType(String messageType) {
		this.messageType = messageType;
		return this;
	}
	
	
	public String getMessageMethod() {
		return messageMethod;
	}

	public CompanyStudentMessage setMessageMethod(String messageMethod) {
		this.messageMethod = messageMethod;
		return this;
	}
	
	
	public Integer getItemOneId() {
		return itemOneId;
	}

	public CompanyStudentMessage setItemOneId(Integer itemOneId) {
		this.itemOneId = itemOneId;
		return this;
	}
	
	
	public Integer getItemSecondId() {
		return itemSecondId;
	}

	public CompanyStudentMessage setItemSecondId(Integer itemSecondId) {
		this.itemSecondId = itemSecondId;
		return this;
	}
	
	
	public Integer getClassTypeId() {
		return classTypeId;
	}

	public CompanyStudentMessage setClassTypeId(Integer classTypeId) {
		this.classTypeId = classTypeId;
		return this;
	}
	
	
	public String getClassTypeName() {
		return classTypeName;
	}

	public CompanyStudentMessage setClassTypeName(String classTypeName) {
		this.classTypeName = classTypeName;
		return this;
	}
	
	
	public Integer getModuleNoId() {
		return moduleNoId;
	}

	public CompanyStudentMessage setModuleNoId(Integer moduleNoId) {
		this.moduleNoId = moduleNoId;
		return this;
	}
	
	
	public String getModuleNoName() {
		return moduleNoName;
	}

	public CompanyStudentMessage setModuleNoName(String moduleNoName) {
		this.moduleNoName = moduleNoName;
		return this;
	}
	
	
	public Integer getCreator() {
		return creator;
	}

	public CompanyStudentMessage setCreator(Integer creator) {
		this.creator = creator;
		return this;
	}
	
	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getCreateTime() {
		return createTime;
	}

	public CompanyStudentMessage setCreateTime(Date createTime) {
		this.createTime = createTime;
		return this;
	}
	
	
	public Integer getMessageCost() {
		return messageCost;
	}

	public CompanyStudentMessage setMessageCost(Integer messageCost) {
		this.messageCost = messageCost;
		return this;
	}
	
	
	public Integer getSendNum() {
		return sendNum;
	}

	public CompanyStudentMessage setSendNum(Integer sendNum) {
		this.sendNum = sendNum;
		return this;
	}
	
	
	public Integer getFailNum() {
		return failNum;
	}

	public CompanyStudentMessage setFailNum(Integer failNum) {
		this.failNum = failNum;
		return this;
	}
	
	
	public String getMessageStatus() {
		return messageStatus;
	}

	public CompanyStudentMessage setMessageStatus(String messageStatus) {
		this.messageStatus = messageStatus;
		return this;
	}
	
	
	public Integer getSchoolId() {
		return schoolId;
	}

	public CompanyStudentMessage setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
		return this;
	}
	
	
	public Integer getCompanyId() {
		return companyId;
	}

	public CompanyStudentMessage setCompanyId(Integer companyId) {
		this.companyId = companyId;
		return this;
	}
	
	@Override
	public String toString() {
		return "CompanyStudentMessage [" + "id=" + getId() + ", title=" + title + ", content=" + content + ", messageType=" + messageType + ", messageMethod=" + messageMethod + ", itemOneId=" + itemOneId + ", itemSecondId=" + itemSecondId + ", classTypeId=" + classTypeId + ", classTypeName=" + classTypeName + ", moduleNoId=" + moduleNoId + ", moduleNoName=" + moduleNoName + ", creator=" + creator + ", createTime=" + createTime + ", messageCost=" + messageCost + ", sendNum=" + sendNum + ", failNum=" + failNum + ", messageStatus=" + messageStatus + ", schoolId=" + schoolId + ", companyId=" + companyId +  "]";
	}

	public String getCreatorName() {
		return creatorName;
	}

	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}

	public Integer getUmId() {
		return umId;
	}

	public void setUmId(Integer umId) {
		this.umId = umId;
	}

	public Integer getReadFlag() {
		return readFlag;
	}

	public void setReadFlag(Integer readFlag) {
		this.readFlag = readFlag;
	}

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

	public String getItemOneCode() {
		return itemOneCode;
	}

	public void setItemOneCode(String itemOneCode) {
		this.itemOneCode = itemOneCode;
	}

	public String getItemSecondCode() {
		return itemSecondCode;
	}

	public void setItemSecondCode(String itemSecondCode) {
		this.itemSecondCode = itemSecondCode;
	}

	public String getItemThirdCode() {
		return itemThirdCode;
	}

	public void setItemThirdCode(String itemThirdCode) {
		this.itemThirdCode = itemThirdCode;
	}
}
