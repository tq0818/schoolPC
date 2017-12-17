package com.yuxin.wx.model.user;

import java.util.Date;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import com.yuxin.wx.util.ShortDateSerializer;

import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:Users
 * 
 * @author chopin
 * @date 2015-5-9
 */
@SuppressWarnings("serial")
public class Users extends BaseEntity {
	
	
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
	private String  userRole;
	private String  schoolName;
	private String domain;/*公司域名*/
	private Integer proxyOrgId;
	private String eduAreaSchool;
	private String isArea;
	
	private Integer IsUsed;
	// Constructor
	public Users() {
	}
	
	/**
	 * full Constructor
	 */
	public Users(Integer id, String username, String password, String realName, String mobile, String email, String qqNo, String phone, String userType, String sex, Integer schoolId, String identityTypeCode, String identityId, Integer age, Date birthday, String educationCode, String homePhone, String officePhone, String weixinId, Integer status, Integer companyId, String emergencyContactName, String emergencyContactMobile, String hkAddress, Integer emailAuthSign, Date emailAuthSendtime, String emailAuthCode, String userCity, String uuid, String headPicUrl) {
		setId(id);
		this.username = username;
		this.password = password;
		this.realName = realName;
		this.mobile = mobile;
		this.email = email;
		this.qqNo = qqNo;
		this.phone = phone;
		this.userType = userType;
		this.sex = sex;
		this.schoolId = schoolId;
		this.identityTypeCode = identityTypeCode;
		this.identityId = identityId;
		this.age = age;
		this.birthday = birthday;
		this.educationCode = educationCode;
		this.homePhone = homePhone;
		this.officePhone = officePhone;
		this.weixinId = weixinId;
		this.status = status;
		this.companyId = companyId;
		this.emergencyContactName = emergencyContactName;
		this.emergencyContactMobile = emergencyContactMobile;
		this.hkAddress = hkAddress;
		this.emailAuthSign = emailAuthSign;
		this.emailAuthSendtime = emailAuthSendtime;
		this.emailAuthCode = emailAuthCode;
		this.userCity = userCity;
		this.uuid = uuid;
		this.headPicUrl = headPicUrl;
	}

	// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为Users可以实现连缀设置属性
	
	public String getUsername() {
		return username;
	}

	public Integer getIsUsed() {
		return IsUsed;
	}

	public void setIsUsed(Integer isUsed) {
		IsUsed = isUsed;
	}

	public Users setUsername(String username) {
		this.username = username;
		return this;
	}
	
	
	public String getPassword() {
		return password;
	}

	public Users setPassword(String password) {
		this.password = password;
		return this;
	}
	
	
	public String getRealName() {
		return realName;
	}

	public Users setRealName(String realName) {
		this.realName = realName;
		return this;
	}
	
	
	public String getMobile() {
		return mobile;
	}

	public Users setMobile(String mobile) {
		this.mobile = mobile;
		return this;
	}
	
	
	public String getEmail() {
		return email;
	}

	public Users setEmail(String email) {
		this.email = email;
		return this;
	}
	
	
	public String getQqNo() {
		return qqNo;
	}

	public Users setQqNo(String qqNo) {
		this.qqNo = qqNo;
		return this;
	}
	
	
	public String getPhone() {
		return phone;
	}

	public Users setPhone(String phone) {
		this.phone = phone;
		return this;
	}
	
	
	public String getUserType() {
		return userType;
	}

	public Users setUserType(String userType) {
		this.userType = userType;
		return this;
	}
	
	
	public String getSex() {
		return sex;
	}

	public Users setSex(String sex) {
		this.sex = sex;
		return this;
	}
	
	
	public Integer getSchoolId() {
		return schoolId;
	}

	public Users setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
		return this;
	}
	
	
	public String getIdentityTypeCode() {
		return identityTypeCode;
	}

	public Users setIdentityTypeCode(String identityTypeCode) {
		this.identityTypeCode = identityTypeCode;
		return this;
	}
	
	
	public String getIdentityId() {
		return identityId;
	}

	public Users setIdentityId(String identityId) {
		this.identityId = identityId;
		return this;
	}
	
	
	public Integer getAge() {
		return age;
	}

	public Users setAge(Integer age) {
		this.age = age;
		return this;
	}
	
	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getBirthday() {
		return birthday;
	}

	public Users setBirthday(Date birthday) {
		this.birthday = birthday;
		return this;
	}
	
	
	public String getEducationCode() {
		return educationCode;
	}

	public Users setEducationCode(String educationCode) {
		this.educationCode = educationCode;
		return this;
	}
	
	
	public String getHomePhone() {
		return homePhone;
	}

	public Users setHomePhone(String homePhone) {
		this.homePhone = homePhone;
		return this;
	}
	
	
	public String getOfficePhone() {
		return officePhone;
	}

	public Users setOfficePhone(String officePhone) {
		this.officePhone = officePhone;
		return this;
	}
	
	
	public String getWeixinId() {
		return weixinId;
	}

	public Users setWeixinId(String weixinId) {
		this.weixinId = weixinId;
		return this;
	}
	
	
	public Integer getStatus() {
		return status;
	}

	public Users setStatus(Integer status) {
		this.status = status;
		return this;
	}
	
	
	public Integer getCompanyId() {
		return companyId;
	}

	public Users setCompanyId(Integer companyId) {
		this.companyId = companyId;
		return this;
	}
	
	
	public String getEmergencyContactName() {
		return emergencyContactName;
	}

	public Users setEmergencyContactName(String emergencyContactName) {
		this.emergencyContactName = emergencyContactName;
		return this;
	}
	
	
	public String getEmergencyContactMobile() {
		return emergencyContactMobile;
	}

	public Users setEmergencyContactMobile(String emergencyContactMobile) {
		this.emergencyContactMobile = emergencyContactMobile;
		return this;
	}
	
	
	public String getHkAddress() {
		return hkAddress;
	}

	public Users setHkAddress(String hkAddress) {
		this.hkAddress = hkAddress;
		return this;
	}
	
	
	public Integer getEmailAuthSign() {
		return emailAuthSign;
	}

	public Users setEmailAuthSign(Integer emailAuthSign) {
		this.emailAuthSign = emailAuthSign;
		return this;
	}
	
	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getEmailAuthSendtime() {
		return emailAuthSendtime;
	}

	public Users setEmailAuthSendtime(Date emailAuthSendtime) {
		this.emailAuthSendtime = emailAuthSendtime;
		return this;
	}
	
	
	public String getEmailAuthCode() {
		return emailAuthCode;
	}

	public Users setEmailAuthCode(String emailAuthCode) {
		this.emailAuthCode = emailAuthCode;
		return this;
	}
	
	
	public String getUserCity() {
		return userCity;
	}

	public Users setUserCity(String userCity) {
		this.userCity = userCity;
		return this;
	}
	
	
	public String getUuid() {
		return uuid;
	}

	public Users setUuid(String uuid) {
		this.uuid = uuid;
		return this;
	}
	
	
	public String getHeadPicUrl() {
		return headPicUrl;
	}

	public Users setHeadPicUrl(String headPicUrl) {
		this.headPicUrl = headPicUrl;
		return this;
	}
	
	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	
	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	@Override
	public String toString() {
		return "Users [" + "id=" + getId() + ", username=" + username + ", password=" + password + ", realName=" + realName + ", mobile=" + mobile + ", email=" + email + ", qqNo=" + qqNo + ", phone=" + phone + ", userType=" + userType + ", sex=" + sex + ", schoolId=" + schoolId + ", identityTypeCode=" + identityTypeCode + ", identityId=" + identityId + ", age=" + age + ", birthday=" + birthday + ", educationCode=" + educationCode + ", homePhone=" + homePhone + ", officePhone=" + officePhone + ", weixinId=" + weixinId + ", status=" + status + ", companyId=" + companyId + ", emergencyContactName=" + emergencyContactName + ", emergencyContactMobile=" + emergencyContactMobile + ", hkAddress=" + hkAddress + ", emailAuthSign=" + emailAuthSign + ", emailAuthSendtime=" + emailAuthSendtime + ", emailAuthCode=" + emailAuthCode + ", userCity=" + userCity + ", uuid=" + uuid + ", headPicUrl=" + headPicUrl +  "]";
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public Integer getProxyOrgId() {
		return proxyOrgId;
	}

	public void setProxyOrgId(Integer proxyOrgId) {
		this.proxyOrgId = proxyOrgId;
	}

	
    public String getEduAreaSchool() {
    	return eduAreaSchool;
    }

	
    public void setEduAreaSchool(String eduAreaSchool) {
    	this.eduAreaSchool = eduAreaSchool;
    }

	
    public String getIsArea() {
    	return isArea;
    }

	
    public void setIsArea(String isArea) {
    	this.isArea = isArea;
    }

 
	
}
