package com.yuxin.wx.vo.user;

import java.util.Date;

import com.yuxin.wx.common.BaseEntity;

@SuppressWarnings("serial")
public class UsersVo extends BaseEntity{
	
	private String	username;		 /* 用户名 */ 
	private String	password;		 /* 密码 */ 
	private String	realName;		
	private String	mobile;		 /* 手机号 */ 
	private String	email;		 /* 邮箱 */ 
	private String	qqNo;		 /* qq号码 */ 
	private String	phone;		 /* 固定电话 */ 
	private String	userType;		 /* 用户类型（1:机构用户；2:普通用户） */ 
	private String	sex;		 /* 性别（男：male；女：female） */ 
	private Integer	schoolId;		 /* 所属校区id */ 
	private String	identityTypeCode;		 /* 证件类型（身份证、护照、港澳通行证、台胞证、军官证、士官证等），字典表（IDENTITY_TYPE） */ 
	private String	identityId;		 /* 证件号码（身份证号、护照、港澳通行证、台胞证、军官证、士官证等） */ 
	private Integer	age;		
	private Date	birthday;		
	private String	educationCode;		 /* 最高学历，字典表（EDUCATION） */ 
	private String	homePhone;		 /* 家庭电话 */ 
	private String	officePhone;		 /* 办公电话 */ 
	private String	weixinId;		 /* 微信号码 */ 
	private Integer	status;		 /* 用户状态（1：有效；0：无效），默认为1 */ 
	private Integer	companyId;		 /* 所属公司id */ 
	private String	emergencyContactName;		 /* 紧急联系人 */ 
	private String	emergencyContactMobile;		 /* 紧急联系人电话 */ 
	private String	hkAddress;		 /* 户口所在地 */ 
	private Integer	emailAuthSign;		 /*  是否通过邮箱验证 */ 
	private Date	emailAuthSendtime;		 /* 发送邮箱验证时间 */ 
	private String	emailAuthCode;		 /* 发送的邮箱验证码 */ 
	private String	userCity;		 /* 用户所在城市，根据ip地址获取 */ 
	private String	uuid;		 /* 用户唯一标识 */ 
	private String	headPicUrl;		 /* 用户头像地址 */ 
	private String userRole;
	private String schoolName;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getQqNo() {
		return qqNo;
	}
	public void setQqNo(String qqNo) {
		this.qqNo = qqNo;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public Integer getSchoolId() {
		return schoolId;
	}
	public void setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
	}
	public String getIdentityTypeCode() {
		return identityTypeCode;
	}
	public void setIdentityTypeCode(String identityTypeCode) {
		this.identityTypeCode = identityTypeCode;
	}
	public String getIdentityId() {
		return identityId;
	}
	public void setIdentityId(String identityId) {
		this.identityId = identityId;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getEducationCode() {
		return educationCode;
	}
	public void setEducationCode(String educationCode) {
		this.educationCode = educationCode;
	}
	public String getHomePhone() {
		return homePhone;
	}
	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
	}
	public String getOfficePhone() {
		return officePhone;
	}
	public void setOfficePhone(String officePhone) {
		this.officePhone = officePhone;
	}
	public String getWeixinId() {
		return weixinId;
	}
	public void setWeixinId(String weixinId) {
		this.weixinId = weixinId;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}
	public String getEmergencyContactName() {
		return emergencyContactName;
	}
	public void setEmergencyContactName(String emergencyContactName) {
		this.emergencyContactName = emergencyContactName;
	}
	public String getEmergencyContactMobile() {
		return emergencyContactMobile;
	}
	public void setEmergencyContactMobile(String emergencyContactMobile) {
		this.emergencyContactMobile = emergencyContactMobile;
	}
	public String getHkAddress() {
		return hkAddress;
	}
	public void setHkAddress(String hkAddress) {
		this.hkAddress = hkAddress;
	}
	public Integer getEmailAuthSign() {
		return emailAuthSign;
	}
	public void setEmailAuthSign(Integer emailAuthSign) {
		this.emailAuthSign = emailAuthSign;
	}
	public Date getEmailAuthSendtime() {
		return emailAuthSendtime;
	}
	public void setEmailAuthSendtime(Date emailAuthSendtime) {
		this.emailAuthSendtime = emailAuthSendtime;
	}
	public String getEmailAuthCode() {
		return emailAuthCode;
	}
	public void setEmailAuthCode(String emailAuthCode) {
		this.emailAuthCode = emailAuthCode;
	}
	public String getUserCity() {
		return userCity;
	}
	public void setUserCity(String userCity) {
		this.userCity = userCity;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getHeadPicUrl() {
		return headPicUrl;
	}
	public void setHeadPicUrl(String headPicUrl) {
		this.headPicUrl = headPicUrl;
	}
	public String getSchoolName() {
		return schoolName;
	}
	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}
	public String getUserRole() {
		return userRole;
	}
	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}
	
	
}
