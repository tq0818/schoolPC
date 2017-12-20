package com.yuxin.wx.vo.student;

import java.util.Date;
import java.util.List;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.yuxin.wx.common.BaseEntity;
import com.yuxin.wx.model.classes.EduMasterClass;
import com.yuxin.wx.util.LongDateSerializer;
import com.yuxin.wx.util.ShortDateSerializer;

@SuppressWarnings("serial")
public class StudentListVo extends BaseEntity{
	
	private Integer id;
	private String	name;		 /* 姓名 */ 
	private String	sex;		 /* 性别（男：male；女：female） */ 
	private String	identityTypeCode;		 /* 证件类型（身份证、护照、港澳通行证、台胞证、军官证、士官证等），字典表 */ 
	private String	identityId;		 /* 证件号码（身份证号、护照、港澳通行证、台胞证、军官证、士官证等） */ 
	private Integer	age;		 /* 年龄 */ 
	private Date	birthday;		 /* 出生年月 */ 
	private String	educationCode;		 /* 最高学历，字典表 */ 
	private String	hkAddress;		 /* 户口所在地 */ 
	private String	mobile;		 /* 手机号 */ 
	private String	email;		 /* 邮箱 */ 
	private String	qq;		 /* QQ号码 */ 
	private String	homePhone;		 /* 家庭电话 */ 
	private String	officePhone;		 /* 办公电话 */ 
	private String	weixinId;		 /* 微信号 */ 
	private String	emergencyContact;		 /* 紧急联系人 */ 
	private String	emergencyPhone;		 /* 紧急联系电话 */ 
	private String	remark;		 /* 学员备注 */ 
	private Date	createTime;		
	private Integer	creator;		 /* 操作员 */ 
	private Date	updateTime;		
	private String	updator;		
	private Integer	deleteFlag;		 /* 删 */ 
	private Integer	schoolId;		 /* 所属校区id */ 
	private Integer companyId;
	
	/** 用户信息*/
	private Integer userId;
	private String	username;		 /* 用户名 */ 
	private Integer	status;		 /* 用户状态（1：有效；0：无效），默认为1 */ 
	private Integer	emailAuthSign;		 /*  是否通过邮箱验证 */ 
	private Date	emailAuthSendtime;		 /* 发送邮箱验证时间 */ 
	private String	emailAuthCode;		 /* 发送的邮箱验证码 */ 
	private String	userCity;		 /* 用户所在城市，根据ip地址获取 */ 
	private String	uuid;		 /* 用户唯一标识，32位的字符串 */ 
	private String	headPicMin;		 /* 用户小头像地址 */ 
	private String	headPicMid;		 /* 用户中头像地址 */ 
	private String	headPicMax;		 /* 用户大头像地址 */ 
	private Date	registTime;		 /* 注册时间 */ 
	private String	interests;		 /* 兴趣标签，存储项目代码 */ 
	private Integer	vipFlag;		 /* vip标记，默认为0，购买课程后修改为1 */ 
	private Integer	registType;		 /* 注册类型，默认为1网页注册 */ 
	private String	promoteSource;		 /* 用户来源地址，从哪个网站过来的 */ 
	private String	promoteKeyword;		 /* 推广的关键字 */ 
	private String  userSign;			/*用户签名*/
	private Integer memberId;
	private String memberLevel;
	private Integer memberBuyLength;
	private Integer memberStatus;
	
	private String startTime;
	private String endTime;
	private Integer paymaterCount;  /* 报名状态 0:未报名 1:已报名*/
	private String isAgent;   /* 是否代报考 1：表示需要代报考 */
	private String ispay;     /* 是否补费 :为空表示不补费，不为空表示补费*/
	private String agentFlag;
	
	private Date signUpTime;
	private Integer classTypeId;
	private Date lastLoginTime;
	private String stusMobile;
	private Integer commodityId;
	private String province;
	private String city;
	private String county;
	private String addressDetail;
	private Integer finishCount;//已完成的课次数量
	private Integer totalCount;//课程总的课次数量
	private Integer truantCount;//旷课的课次数量
	
	private Integer isMember; //是否为会员 1:是0:否
	private String commodityType;
	
	
	private Integer groupOneId;
	private Integer groupTwoId;
	
	private Integer protocolId;//协议id
	private Integer protocolConfig;//机构是否开启课程协议 1为开启状态
	private Integer protocolStatus;//筛选条件，是否签署课程协议
	
	private Integer proxyOrgId;
	private String proxyOrgName;
	private Integer eduIdentity;
	private String eduArea;
	private String eduSchool;
	private String eduStep;
	private String eduYear;
	private Integer isStu; //是否学生，1是。0否
	private List<EduMasterClass> renke;
	public Integer getIsStu() {
		return isStu;
	}

	public void setIsStu(Integer isStu) {
		this.isStu = isStu;
	}

	public Integer getTeacherFlag() {
		return teacherFlag;
	}

	public void setTeacherFlag(Integer teacherFlag) {
		this.teacherFlag = teacherFlag;
	}

	private Integer teacherFlag;
	public String getEduClass() {
		return eduClass;
	}

	public void setEduClass(String eduClass) {
		this.eduClass = eduClass;
	}

	public Integer getEduIdentity() {
		return eduIdentity;
	}

	public void setEduIdentity(Integer eduIdentity) {
		this.eduIdentity = eduIdentity;
	}

	public String getEduArea() {
		return eduArea;
	}

	public void setEduArea(String eduArea) {
		this.eduArea = eduArea;
	}

	public String getEduSchool() {
		return eduSchool;
	}

	public void setEduSchool(String eduSchool) {
		this.eduSchool = eduSchool;
	}

	public String getEduStep() {
		return eduStep;
	}

	public void setEduStep(String eduStep) {
		this.eduStep = eduStep;
	}

	public String getEduYear() {
		return eduYear;
	}

	public void setEduYear(String eduYear) {
		this.eduYear = eduYear;
	}

	private String eduClass;
	public StudentListVo() {

	}
	public StudentListVo(Integer id, String name, String sex,
			String identityTypeCode, String identityId, Integer age,
			Date birthday, String educationCode, String hkAddress,
			String mobile, String email, String qq, String homePhone,
			String officePhone, String weixinId, String emergencyContact,
			String emergencyPhone, String remark, Date createTime,
			Integer creator, Date updateTime, String updator,
			Integer deleteFlag, Integer schoolId, Integer companyId,
			String username, Integer status, Integer emailAuthSign,
			Date emailAuthSendtime, String emailAuthCode, String userCity,
			String uuid, String headPicMin, String headPicMid,
			String headPicMax, Date registTime, String interests,
			Integer vipFlag, Integer registType, String promoteSource,
			String promoteKeyword, String userSign,Integer groupOneId,Integer groupTwoId) {
		this.id = id;
		this.name = name;
		this.sex = sex;
		this.identityTypeCode = identityTypeCode;
		this.identityId = identityId;
		this.age = age;
		this.birthday = birthday;
		this.educationCode = educationCode;
		this.hkAddress = hkAddress;
		this.mobile = mobile;
		this.email = email;
		this.qq = qq;
		this.homePhone = homePhone;
		this.officePhone = officePhone;
		this.weixinId = weixinId;
		this.emergencyContact = emergencyContact;
		this.emergencyPhone = emergencyPhone;
		this.remark = remark;
		this.createTime = createTime;
		this.creator = creator;
		this.updateTime = updateTime;
		this.updator = updator;
		this.deleteFlag = deleteFlag;
		this.schoolId = schoolId;
		this.companyId = companyId;
		this.username = username;
		this.status = status;
		this.emailAuthSign = emailAuthSign;
		this.emailAuthSendtime = emailAuthSendtime;
		this.emailAuthCode = emailAuthCode;
		this.userCity = userCity;
		this.uuid = uuid;
		this.headPicMin = headPicMin;
		this.headPicMid = headPicMid;
		this.headPicMax = headPicMax;
		this.registTime = registTime;
		this.interests = interests;
		this.vipFlag = vipFlag;
		this.registType = registType;
		this.promoteSource = promoteSource;
		this.promoteKeyword = promoteKeyword;
		this.userSign = userSign;
		this.groupOneId = groupOneId;
		this.groupTwoId = groupTwoId;
	}
	
	public Integer getProtocolConfig() {
		return protocolConfig;
	}
	public void setProtocolConfig(Integer protocolConfig) {
		this.protocolConfig = protocolConfig;
	}
	public Integer getProtocolStatus() {
		return protocolStatus;
	}
	public void setProtocolStatus(Integer protocolStatus) {
		this.protocolStatus = protocolStatus;
	}
	public Integer getProtocolId() {
		return protocolId;
	}
	public void setProtocolId(Integer protocolId) {
		this.protocolId = protocolId;
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
	public Integer getFinishCount() {
		return finishCount;
	}
	public void setFinishCount(Integer finishCount) {
		this.finishCount = finishCount;
	}
	public Integer getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}
	public Integer getTruantCount() {
		return truantCount;
	}
	public void setTruantCount(Integer truantCount) {
		this.truantCount = truantCount;
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
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
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
	public String getHkAddress() {
		return hkAddress;
	}
	public void setHkAddress(String hkAddress) {
		this.hkAddress = hkAddress;
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
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
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
	public String getEmergencyContact() {
		return emergencyContact;
	}
	public void setEmergencyContact(String emergencyContact) {
		this.emergencyContact = emergencyContact;
	}
	public String getEmergencyPhone() {
		return emergencyPhone;
	}
	public void setEmergencyPhone(String emergencyPhone) {
		this.emergencyPhone = emergencyPhone;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	@JsonSerialize(using = ShortDateSerializer.class)
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
	public String getUpdator() {
		return updator;
	}
	public void setUpdator(String updator) {
		this.updator = updator;
	}
	public Integer getDeleteFlag() {
		return deleteFlag;
	}
	public void setDeleteFlag(Integer deleteFlag) {
		this.deleteFlag = deleteFlag;
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
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
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
	public String getHeadPicMin() {
		return headPicMin;
	}
	public void setHeadPicMin(String headPicMin) {
		this.headPicMin = headPicMin;
	}
	public String getHeadPicMid() {
		return headPicMid;
	}
	public void setHeadPicMid(String headPicMid) {
		this.headPicMid = headPicMid;
	}
	public String getHeadPicMax() {
		return headPicMax;
	}
	public void setHeadPicMax(String headPicMax) {
		this.headPicMax = headPicMax;
	}
	
	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getRegistTime() {
		return registTime;
	}
	public void setRegistTime(Date registTime) {
		this.registTime = registTime;
	}
	public String getInterests() {
		return interests;
	}
	public void setInterests(String interests) {
		this.interests = interests;
	}
	public Integer getVipFlag() {
		return vipFlag;
	}
	public void setVipFlag(Integer vipFlag) {
		this.vipFlag = vipFlag;
	}
	public Integer getRegistType() {
		return registType;
	}
	public void setRegistType(Integer registType) {
		this.registType = registType;
	}
	public String getPromoteSource() {
		return promoteSource;
	}
	public void setPromoteSource(String promoteSource) {
		this.promoteSource = promoteSource;
	}
	public String getPromoteKeyword() {
		return promoteKeyword;
	}
	public void setPromoteKeyword(String promoteKeyword) {
		this.promoteKeyword = promoteKeyword;
	}
	public String getUserSign() {
		return userSign;
	}
	public void setUserSign(String userSign) {
		this.userSign = userSign;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public Integer getPaymaterCount() {
		return paymaterCount;
	}
	public void setPaymaterCount(Integer paymaterCount) {
		this.paymaterCount = paymaterCount;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getIsAgent() {
		return isAgent;
	}
	public void setIsAgent(String isAgent) {
		this.isAgent = isAgent;
	}
	public String getIspay() {
		return ispay;
	}
	public void setIspay(String ispay) {
		this.ispay = ispay;
	}
	public String getAgentFlag() {
		return agentFlag;
	}
	public void setAgentFlag(String agentFlag) {
		this.agentFlag = agentFlag;
	}
	@JsonSerialize(using = LongDateSerializer.class)
	public Date getSignUpTime() {
		return signUpTime;
	}
	public void setSignUpTime(Date signUpTime) {
		this.signUpTime = signUpTime;
	}
	@JsonSerialize(using = LongDateSerializer.class)
	public Date getLastLoginTime() {
		return lastLoginTime;
	}
	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}
	public Integer getClassTypeId() {
		return classTypeId;
	}
	public void setClassTypeId(Integer classTypeId) {
		this.classTypeId = classTypeId;
	}
	public String getStusMobile() {
		return stusMobile;
	}
	public void setStusMobile(String stusMobile) {
		this.stusMobile = stusMobile;
	}
	public Integer getCommodityId() {
		return commodityId;
	}
	public void setCommodityId(Integer commodityId) {
		this.commodityId = commodityId;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCounty() {
		return county;
	}
	public void setCounty(String county) {
		this.county = county;
	}
	public String getAddressDetail() {
		return addressDetail;
	}
	public void setAddressDetail(String addressDetail) {
		this.addressDetail = addressDetail;
	}
	public Integer getMemberId() {
		return memberId;
	}
	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}
	public String getMemberLevel() {
		return memberLevel;
	}
	public void setMemberLevel(String memberLevel) {
		this.memberLevel = memberLevel;
	}
	public Integer getIsMember() {
		return isMember;
	}
	public void setIsMember(Integer isMember) {
		this.isMember = isMember;
	}
	public Integer getMemberBuyLength() {
		return memberBuyLength;
	}
	public void setMemberBuyLength(Integer memberBuyLength) {
		this.memberBuyLength = memberBuyLength;
	}
	public Integer getMemberStatus() {
		return memberStatus;
	}
	public void setMemberStatus(Integer memberStatus) {
		this.memberStatus = memberStatus;
	}
	public String getCommodityType() {
		return commodityType;
	}
	public void setCommodityType(String commodityType) {
		this.commodityType = commodityType;
	}
	public Integer getProxyOrgId() {
		return proxyOrgId;
	}
	public void setProxyOrgId(Integer proxyOrgId) {
		this.proxyOrgId = proxyOrgId;
	}
	public String getProxyOrgName() {
		return proxyOrgName;
	}
	public void setProxyOrgName(String proxyOrgName) {
		this.proxyOrgName = proxyOrgName;
	}

	
    public List<EduMasterClass> getRenke() {
    	return renke;
    }

	
    public void setRenke(List<EduMasterClass> renke) {
    	this.renke = renke;
    }

}
