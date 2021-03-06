package com.yuxin.wx.model.system;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.yuxin.wx.common.BaseEntity;
import com.yuxin.wx.util.ShortDateSerializer;

public class SysConfigTeacherModel extends BaseEntity{
	private String	name;		 /* 老师姓名 */ 
	private String	teacherType;		 /* 老师类型（老师；助教；跟班生;主播）
字典表 */ 
	private String	sex;		 /* 性别（男：male；女：female） */ 
	private String schoolCode;
	private String	mobile;		 /* 手机号码 */ 
	private String	homePhone;		 /* 家庭电话 */ 
	private Date	birthday;		 /* 出生日期 */ 
	private String	idNumber;		 /* 身份证号 */ 
	private String	email;		 /* 邮箱地址 */ 
	private String	jobType;		 /* 工作类型，字典表 */ 
	private String	homeAddress;		 /* 家庭住址 */ 
	private String	address;		 /* 现居住地址 */ 
	private String	company;		 /* 工作单位 */ 
	private String	post;		 /* 公司职务 */ 
	private String	workPhone;		 /* 办公电话 */ 
	private String	emergencyContactName;		 /* 紧急联系人 */ 
	private String	emergencyContactPhone;		 /* 紧急联系人电话 */ 
	private String	workTime;		 /* 兼职时间段 */ 
	private Integer	schoolId;		
	private Integer	itemOneId;		 /* 所属一级项目 */ 
	private Integer	moduleId;		 
	private String	itemSecondId;		 /* 所属二级项目 */ 
	private String	unionpayNo;		 /* 银联号 */ 
	private String	bankName;		 /* 开户行 */ 
	private String	bankAccountName;		 /* 开户名 */ 
	private String	bankAccountNum;		 /* 银行卡号 */ 
	private String	statusCode;		 /* 状态(停用,正常,未开课)
字典表 */ 
	private Integer	delFlag;		 /* 删除标记，默认值为0
1：已删除；0：未删除 */ 
	private Date	createTime;		
	private Integer	creator;		
	private Date	updateTime;		
	private Integer	updator;
	private Integer	companyId;		 /* 所属一级项目 */
	private Integer	teacherId;		 /* 授课老师 */
	private String resume;    /* 个人简介 */
	private String educationCode;  /*  最高学历 */
	
	private String headpicUrl; /*头像地址*/ 
	private Integer userId;
	private Integer isDistinguished;/*是否是名师*/
	
	private String inviteCode; //邀请码
	
	//其他
	private String userName;/*用户名*/
	private String pwd;/*密码*/
	private String pwdNext;
	private String teaOrAdu;
	private Integer courseCount;//开课数量
	private Integer stuCount;	//学习人数

	private String teacherLevel;
	private String teacherArea;
	
	private Integer sortId;

	private String remark;		//摘要
	/**
	 * 学校简称
	 */
	private String schoolShortName;

	private String schoolName;
	
	private String eduAreaSchool;

	private String isInsertUserId;

	public String getIsInsertUserId() {
		return isInsertUserId;
	}

	public void setIsInsertUserId(String isInsertUserId) {
		this.isInsertUserId = isInsertUserId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getBirthday() {
		return birthday;
	}

	public SysConfigTeacherModel setBirthday(String birthday) throws ParseException {
		SimpleDateFormat f=new SimpleDateFormat("yyyy-MM-dd");
		this.birthday = f.parse(birthday);
		return this;
	}
	public String getTeacherType() {
		return teacherType;
	}

	public void setTeacherType(String teacherType) {
		this.teacherType = teacherType;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getSchoolCode() {
		return schoolCode;
	}

	public void setSchoolCode(String schoolCode) {
		this.schoolCode = schoolCode;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getHomePhone() {
		return homePhone;
	}

	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
	}


	public String getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getJobType() {
		return jobType;
	}

	public void setJobType(String jobType) {
		this.jobType = jobType;
	}

	public String getHomeAddress() {
		return homeAddress;
	}

	public void setHomeAddress(String homeAddress) {
		this.homeAddress = homeAddress;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getPost() {
		return post;
	}

	public void setPost(String post) {
		this.post = post;
	}

	public String getWorkPhone() {
		return workPhone;
	}

	public void setWorkPhone(String workPhone) {
		this.workPhone = workPhone;
	}

	public String getEmergencyContactName() {
		return emergencyContactName;
	}

	public void setEmergencyContactName(String emergencyContactName) {
		this.emergencyContactName = emergencyContactName;
	}

	public String getEmergencyContactPhone() {
		return emergencyContactPhone;
	}

	public void setEmergencyContactPhone(String emergencyContactPhone) {
		this.emergencyContactPhone = emergencyContactPhone;
	}

	public String getWorkTime() {
		return workTime;
	}

	public void setWorkTime(String workTime) {
		this.workTime = workTime;
	}

	public Integer getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
	}

	public Integer getItemOneId() {
		return itemOneId;
	}

	public void setItemOneId(Integer itemOneId) {
		this.itemOneId = itemOneId;
	}

	public Integer getModuleId() {
		return moduleId;
	}

	public void setModuleId(Integer moduleId) {
		this.moduleId = moduleId;
	}

	public String getItemSecondId() {
		return itemSecondId;
	}

	public void setItemSecondId(String itemSecondId) {
		this.itemSecondId = itemSecondId;
	}

	public String getUnionpayNo() {
		return unionpayNo;
	}

	public void setUnionpayNo(String unionpayNo) {
		this.unionpayNo = unionpayNo;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBankAccountName() {
		return bankAccountName;
	}

	public void setBankAccountName(String bankAccountName) {
		this.bankAccountName = bankAccountName;
	}

	public String getBankAccountNum() {
		return bankAccountNum;
	}

	public void setBankAccountNum(String bankAccountNum) {
		this.bankAccountNum = bankAccountNum;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public Integer getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getCreator() {
		return creator;
	}

	public void setCreator(Integer creator) {
		this.creator = creator;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getUpdator() {
		return updator;
	}

	public void setUpdator(Integer updator) {
		this.updator = updator;
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	public Integer getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(Integer teacherId) {
		this.teacherId = teacherId;
	}

	public String getResume() {
		return resume;
	}

	public void setResume(String resume) {
		this.resume = resume;
	}

	public String getEducationCode() {
		return educationCode;
	}

	public void setEducationCode(String educationCode) {
		this.educationCode = educationCode;
	}

	public String getHeadpicUrl() {
		return headpicUrl;
	}

	public void setHeadpicUrl(String headpicUrl) {
		this.headpicUrl = headpicUrl;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getIsDistinguished() {
		return isDistinguished;
	}

	public void setIsDistinguished(Integer isDistinguished) {
		this.isDistinguished = isDistinguished;
	}

	public String getInviteCode() {
		return inviteCode;
	}

	public void setInviteCode(String inviteCode) {
		this.inviteCode = inviteCode;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getPwdNext() {
		return pwdNext;
	}

	public void setPwdNext(String pwdNext) {
		this.pwdNext = pwdNext;
	}

	public String getTeaOrAdu() {
		return teaOrAdu;
	}

	public void setTeaOrAdu(String teaOrAdu) {
		this.teaOrAdu = teaOrAdu;
	}

	public Integer getCourseCount() {
		return courseCount;
	}

	public void setCourseCount(Integer courseCount) {
		this.courseCount = courseCount;
	}

	public Integer getStuCount() {
		return stuCount;
	}

	public void setStuCount(Integer stuCount) {
		this.stuCount = stuCount;
	}

	public String getTeacherLevel() {
		return teacherLevel;
	}

	public void setTeacherLevel(String teacherLevel) {
		this.teacherLevel = teacherLevel;
	}

	public String getTeacherArea() {
		return teacherArea;
	}

	public void setTeacherArea(String teacherArea) {
		this.teacherArea = teacherArea;
	}

	public Integer getSortId() {
		return sortId;
	}

	public void setSortId(Integer sortId) {
		this.sortId = sortId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getSchoolShortName() {
		return schoolShortName;
	}

	public void setSchoolShortName(String schoolShortName) {
		this.schoolShortName = schoolShortName;
	}

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	public String getEduAreaSchool() {
		return eduAreaSchool;
	}

	public void setEduAreaSchool(String eduAreaSchool) {
		this.eduAreaSchool = eduAreaSchool;
	}
	
}
