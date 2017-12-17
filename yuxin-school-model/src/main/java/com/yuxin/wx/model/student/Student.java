package com.yuxin.wx.model.student;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.yuxin.wx.common.BaseEntity;
import com.yuxin.wx.util.ShortDateSerializer;

/**
 * POJO:Student
 *
 * @author wang.zx
 * @date 2014-12-5
 */
@SuppressWarnings("serial")
public class Student extends BaseEntity {

	private String name; /* 姓名 */
	private String sex; /* 性别（男：male；女：female） */
	private String identityTypeCode; /* 证件类型（身份证、护照、港澳通行证、台胞证、军官证、士官证等），字典表 */
	private String identityId; /* 证件号码（身份证号、护照、港澳通行证、台胞证、军官证、士官证等） */
	private Integer age; /* 年龄 */
	private Date birthday; /* 出生年月 */
	private String educationCode; /* 最高学历，字典表 */
	private String hkAddress; /* 户口所在地 */
	private String mobile; /* 手机号 */
	private String email; /* 邮箱 */
	private String qq; /* QQ号码 */
	private String homePhone; /* 家庭电话 */
	private String officePhone; /* 办公电话 */
	private String weixinId; /* 微信号 */
	private String emergencyContact; /* 紧急联系人 */
	private String emergencyPhone; /* 紧急联系电话 */
	private String remark; /* 学员备注 */
	private Date createTime;
	private Integer creator; /* 操作员 */
	private Date updateTime;
	private Integer updator;
	private Integer deleteFlag = 0; /* 删 */
	private Integer companyId;
	private Integer schoolId;
	private Integer userId;
	private String province;/* 省 */
	private String city;/* 市 */
	private String county;/* 县 */
	private String township;/* 乡镇 */
	private String addressDetail;/* 详细地址 */
	private String remarkName; /* 备注名 */

	private String dicSex;
	private String dicIdentityTypeCode;
	private String dicEducationCode;
	private String password;
	private String username;

	private String addFlag;
	/** mobile:只开通了手机号，username:只开通了用户名 all:用户名和手机号都开通 */

	private Integer groupOneId;
	private Integer groupTwoId;

	// userfront属性， 用于更新userfront中的属性
	private String userSign;
	private String nickName;
	private Integer proxyOrgId;//代理机构id

	private Integer eduIdentity;
	private String eduArea;
	private String eduSchool;
	private String eduStep;
	private String eduYear;
	private Integer isInSchool;

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
	// Constructor
	public Student() {
	}

	/**
	 * full Constructor
	 */
	

	// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为Student可以实现连缀设置属性

	public Integer getGroupOneId() {
		return groupOneId;
	}

	public Student(String name, String sex, String identityTypeCode, String identityId, Integer age, Date birthday,
            String educationCode, String hkAddress, String mobile, String email, String qq, String homePhone,
            String officePhone, String weixinId, String emergencyContact, String emergencyPhone, String remark,
            Date createTime, Integer creator, Date updateTime, Integer updator, Integer deleteFlag, Integer companyId,
            Integer schoolId, Integer userId, String province, String city, String county, String township,
            String addressDetail, String remarkName, String dicSex, String dicIdentityTypeCode, String dicEducationCode,
            String password, String username, String addFlag, Integer groupOneId, Integer groupTwoId, String userSign,
            String nickName, Integer proxyOrgId, Integer eduIdentity, String eduArea, String eduSchool, String eduStep,
            String eduYear, Integer isInSchool, Integer teacherFlag, String eduClass) {
	    super();
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
	    this.companyId = companyId;
	    this.schoolId = schoolId;
	    this.userId = userId;
	    this.province = province;
	    this.city = city;
	    this.county = county;
	    this.township = township;
	    this.addressDetail = addressDetail;
	    this.remarkName = remarkName;
	    this.dicSex = dicSex;
	    this.dicIdentityTypeCode = dicIdentityTypeCode;
	    this.dicEducationCode = dicEducationCode;
	    this.password = password;
	    this.username = username;
	    this.addFlag = addFlag;
	    this.groupOneId = groupOneId;
	    this.groupTwoId = groupTwoId;
	    this.userSign = userSign;
	    this.nickName = nickName;
	    this.proxyOrgId = proxyOrgId;
	    this.eduIdentity = eduIdentity;
	    this.eduArea = eduArea;
	    this.eduSchool = eduSchool;
	    this.eduStep = eduStep;
	    this.eduYear = eduYear;
	    this.isInSchool = isInSchool;
	    this.teacherFlag = teacherFlag;
	    this.eduClass = eduClass;
    }

	public Student setGroupOneId(Integer groupOneId) {
		this.groupOneId = groupOneId;
		return this;
	}

	public Integer getGroupTwoId() {
		return groupTwoId;
	}

	public Student setGroupTwoId(Integer groupTwoId) {
		this.groupTwoId = groupTwoId;
		return this;
	}

	public String getName() {
		return name;
	}

	public String getRemarkName() {
		return remarkName;
	}

	public void setRemarkName(String remarkName) {
		this.remarkName = remarkName;
	}

	public Student setName(String name) {
		this.name = name;
		return this;
	}

	public String getSex() {
		return sex;
	}

	public Student setSex(String sex) {
		this.sex = sex;
		return this;
	}

	public String getIdentityTypeCode() {
		return identityTypeCode;
	}

	public Student setIdentityTypeCode(String identityTypeCode) {
		this.identityTypeCode = identityTypeCode;
		return this;
	}

	public String getIdentityId() {
		return identityId;
	}

	public Student setIdentityId(String identityId) {
		this.identityId = identityId;
		return this;
	}

	public Integer getAge() {
		return age;
	}

	public Student setAge(Integer age) {
		this.age = age;
		return this;
	}

	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getBirthday() {
		return birthday;
	}

	public Student setBirthday(Date birthday) {
		this.birthday = birthday;
		return this;
	}

	public String getEducationCode() {
		return educationCode;
	}

	public Student setEducationCode(String educationCode) {
		this.educationCode = educationCode;
		return this;
	}

	public String getHkAddress() {
		return hkAddress;
	}

	public Student setHkAddress(String hkAddress) {
		this.hkAddress = hkAddress;
		return this;
	}

	public String getMobile() {
		return mobile;
	}

	public Student setMobile(String mobile) {
		this.mobile = mobile;
		return this;
	}

	public String getEmail() {
		return email;
	}

	public Student setEmail(String email) {
		this.email = email;
		return this;
	}

	public String getQq() {
		return qq;
	}

	public Student setQq(String qq) {
		this.qq = qq;
		return this;
	}

	public String getHomePhone() {
		return homePhone;
	}

	public Student setHomePhone(String homePhone) {
		this.homePhone = homePhone;
		return this;
	}

	public String getOfficePhone() {
		return officePhone;
	}

	public Student setOfficePhone(String officePhone) {
		this.officePhone = officePhone;
		return this;
	}

	public String getWeixinId() {
		return weixinId;
	}

	public Student setWeixinId(String weixinId) {
		this.weixinId = weixinId;
		return this;
	}

	public String getEmergencyContact() {
		return emergencyContact;
	}

	public Student setEmergencyContact(String emergencyContact) {
		this.emergencyContact = emergencyContact;
		return this;
	}

	public String getEmergencyPhone() {
		return emergencyPhone;
	}

	public Student setEmergencyPhone(String emergencyPhone) {
		this.emergencyPhone = emergencyPhone;
		return this;
	}

	public String getRemark() {
		return remark;
	}

	public Student setRemark(String remark) {
		this.remark = remark;
		return this;
	}

	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getCreateTime() {
		return createTime;
	}

	public Student setCreateTime(Date createTime) {
		this.createTime = createTime;
		return this;
	}

	public Integer getCreator() {
		return creator;
	}

	public Student setCreator(Integer creator) {
		this.creator = creator;
		return this;
	}

	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getUpdateTime() {
		return updateTime;
	}

	public Student setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
		return this;
	}

	public Integer getUpdator() {
		return updator;
	}

	public Student setUpdator(Integer updator) {
		this.updator = updator;
		return this;
	}

	public Integer getDeleteFlag() {
		return deleteFlag;
	}

	public Student setDeleteFlag(Integer deleteFlag) {
		this.deleteFlag = deleteFlag;
		return this;
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	// public Integer getSchoolId() {
	// return schoolId;
	// }
	//
	// public void setSchoolId(Integer schoolId) {
	// this.schoolId = schoolId;
	// }

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		StringBuffer str = new StringBuffer();
		str.append("{");
		if (getId() != null) {
			str.append("\"id\":\"").append(getId()).append("\",");
		}
		if (StringUtils.isNotBlank(name)) {
			str.append("\"name\":\"").append(name).append("\",");
		}
		if (StringUtils.isNotBlank(sex)) {
			str.append("\"sex\":\"").append(sex).append("\",");
		}
		if (StringUtils.isNotBlank(identityTypeCode)) {
			str.append("\"identityTypeCode\":\"").append(identityTypeCode).append("\",");
		}
		if (StringUtils.isNotBlank(identityId)) {
			str.append("\"identityId\":\"").append(identityId).append("\",");
		}
		if (birthday != null) {
			str.append("\"birthday\":\"").append(sdf.format(birthday)).append("\",");
		}
		if (StringUtils.isNotBlank(educationCode)) {
			str.append("\"educationCode\":\"").append(educationCode).append("\",");
		}
		if (age != null) {
			str.append("\"age\":\"").append(age).append("\",");
		}
		if (StringUtils.isNotBlank(hkAddress)) {
			str.append("\"hkAddress\":\"").append(hkAddress).append("\",");
		}
		if (StringUtils.isNotBlank(mobile)) {
			str.append("\"mobile\":\"").append(mobile).append("\",");
		}
		if (StringUtils.isNotBlank(email)) {
			str.append("\"email\":\"").append(email).append("\",");
		}
		if (StringUtils.isNotBlank(qq)) {
			str.append("\"qq\":\"").append(qq).append("\",");
		}
		if (StringUtils.isNotBlank(homePhone)) {
			str.append("\"homePhone\":\"").append(homePhone).append("\",");
		}
		if (StringUtils.isNotBlank(officePhone)) {
			str.append("\"officePhone\":\"").append(officePhone).append("\",");
		}
		if (StringUtils.isNotBlank(weixinId)) {
			str.append("\"weixinId\":\"").append(weixinId).append("\",");
		}
		if (StringUtils.isNotBlank(emergencyContact)) {
			str.append("\"emergencyContact\":\"").append(emergencyContact).append("\",");
		}
		if (StringUtils.isNotBlank(emergencyPhone)) {
			str.append("\"emergencyPhone\":\"").append(emergencyPhone).append("\",");
		}
		if (StringUtils.isNotBlank(remark)) {
			str.append("\"remark\":\"").append(remark).append("\",");
		}
		if (createTime != null) {
			str.append("\"createTime\":\"").append(sdf.format(createTime)).append("\",");
		}
		if (creator != null) {
			str.append("\"creator\":\"").append(creator).append("\",");
		}
		if (updateTime != null) {
			str.append("\"updateTime\":\"").append(sdf.format(updateTime)).append("\",");
		}
		if (updator != null) {
			str.append("\"updator\":\"").append(updator).append("\",");
		}
		if (deleteFlag != null) {
			str.append("\"deleteFlag\":\"").append(deleteFlag).append("\",");
		}
		if (companyId != null) {
			str.append("\"companyId\":\"").append(companyId).append("\",");
		}
		// if(schoolId!=null){
		// str.append("\"schoolId\":\"").append(schoolId).append("\",");
		// }
		if (userId != null) {
			str.append("\"userId\":\"").append(userId).append("\",");
		}
		if (groupOneId != null) {
			str.append("\"groupOneId\":\"").append(groupOneId).append("\",");
		}
		if (groupTwoId != null) {
			str.append("\"groupTwoId\":\"").append(groupTwoId).append("\",");
		}

		return str.substring(0, str.length() - 1) + "}";
	}

	public Integer getProxyOrgId() {
		return proxyOrgId;
	}

	public void setProxyOrgId(Integer proxyOrgId) {
		this.proxyOrgId = proxyOrgId;
	}

	public String getDicSex() {
		return dicSex;
	}

	public void setDicSex(String dicSex) {
		this.dicSex = dicSex;
	}

	public String getDicIdentityTypeCode() {
		return dicIdentityTypeCode;
	}

	public void setDicIdentityTypeCode(String dicIdentityTypeCode) {
		this.dicIdentityTypeCode = dicIdentityTypeCode;
	}

	public String getDicEducationCode() {
		return dicEducationCode;
	}

	public void setDicEducationCode(String dicEducationCode) {
		this.dicEducationCode = dicEducationCode;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
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

	public String getTownship() {
		return township;
	}

	public void setTownship(String township) {
		this.township = township;
	}

	public String getAddressDetail() {
		return addressDetail;
	}

	public void setAddressDetail(String addressDetail) {
		this.addressDetail = addressDetail;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getAddFlag() {
		return addFlag;
	}

	public void setAddFlag(String addFlag) {
		this.addFlag = addFlag;
	}

	public String getUserSign() {
		return userSign;
	}

	public void setUserSign(String userSign) {
		this.userSign = userSign;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	
    public Integer getIsInSchool() {
    	return isInSchool;
    }

	
    public void setIsInSchool(Integer isInSchool) {
    	this.isInSchool = isInSchool;
    }

}
