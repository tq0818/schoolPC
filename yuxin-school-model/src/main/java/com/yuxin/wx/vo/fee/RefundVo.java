package com.yuxin.wx.vo.fee;


import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.yuxin.wx.util.ShortDateSerializer;
import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:StudentFeeRefund
 * 
 * @author chopin
 * @date 2015-5-7
 */
@SuppressWarnings("serial")
public class RefundVo extends BaseEntity {
	
	private Integer	stuId;		 /* 学生id */ 
	private Integer	payMasterId;		 /* 主订单id */ 
	private Date	refundDate;		 /* 退费日期 */ 
	private Double	refundFee;		 /* 分期费用 */ 
	private Double	posRefund;		 /* pos退费 */ 
	private Double	cashRefund;		 /* 现金退费 */ 
	private Double	checkRefund;		 /* 支票退费 */ 
	private Double	remitRefund;		 /* 汇款退费 */ 
	private String	remark;		
	private String	createType;		 /* 退费生成方式：解约、转班 */ 
	private Date	createTime;		 /* 生成日期 */ 
	private Integer	creator;	
	
	private Integer	itemOneId;		 /* 一级项目id */ 
	private String	itemOneName;		 /* 一级项目名称 */ 
	private Integer	itemSecondId;		 /* 二级项目id */ 
	private String	itemSecondName;		 /* 二级项目名称 */ 
	private Integer	classTypeId;		 /* 班型id */ 
	private String	classTypeName;		 /* 班型名称 */ 
	private Integer	classTypeHour;		 /* 班型总课时 */ 
	private Integer	schoolId;		 /* 分校id */ 
    
    private String stuName;   /* 姓名 */ 
    private String sex;   /* 性别（男：male；女：female） */ 
    private String identityTypeCode;   /* 证件类型（身份证、护照、港澳通行证、台胞证、军官证、士官证等），字典表 */ 
    private String identityId;   /* 证件号码（身份证号、护照、港澳通行证、台胞证、军官证、士官证等） */ 
    private Integer age;   /* 年龄 */ 
    private Date birthday;   /* 出生年月 */ 
    private String educationCode;   /* 最高学历，字典表 */ 
    private String hkAddress;   /* 户口所在地 */ 
    private String mobile;   /* 手机号 */ 
    private String email;   /* 邮箱 */ 
    private String qq;   /* QQ号码 */ 
    private String homePhone;   /* 家庭电话 */ 
    private String officePhone;   /* 办公电话 */ 
    private String weixinId;   /* 微信号 */ 
    private String emergencyContact;   /* 紧急联系人 */ 
    private String emergencyPhone;   /* 紧急联系电话 */
	private Integer stageStatus;
	private Integer examTermId;
	private Integer companyId;
	private String applyChannelCode;
	
	private String startDate;
	private String endDate;
	
	private Integer timeLen;
	private String timeMark;
	private String schoolName;

	// Constructor
	public RefundVo() {
	}
	
	/**
	 * full Constructor
	 */
	public RefundVo(Integer id, Integer stuId, Integer payMasterId, Date refundDate, Double refundFee, Double posRefund, Double cashRefund, Double checkRefund, Double remitRefund, String remark, String createType, Date createTime, Integer creator) {
		setId(id);
		this.stuId = stuId;
		this.payMasterId = payMasterId;
		this.refundDate = refundDate;
		this.refundFee = refundFee;
		this.posRefund = posRefund;
		this.cashRefund = cashRefund;
		this.checkRefund = checkRefund;
		this.remitRefund = remitRefund;
		this.remark = remark;
		this.createType = createType;
		this.createTime = createTime;
		this.creator = creator;
	}

	// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为StudentFeeRefund可以实现连缀设置属性
	
	public Integer getStuId() {
		return stuId;
	}

	public RefundVo setStuId(Integer stuId) {
		this.stuId = stuId;
		return this;
	}
	
	
	public Integer getPayMasterId() {
		return payMasterId;
	}

	public RefundVo setPayMasterId(Integer payMasterId) {
		this.payMasterId = payMasterId;
		return this;
	}
	
	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getRefundDate() {
		return refundDate;
	}

	public RefundVo setRefundDate(Date refundDate) {
		this.refundDate = refundDate;
		return this;
	}
	
	
	public Double getRefundFee() {
		return refundFee;
	}

	public RefundVo setRefundFee(Double refundFee) {
		this.refundFee = refundFee;
		return this;
	}
	
	
	public Double getPosRefund() {
		return posRefund;
	}

	public RefundVo setPosRefund(Double posRefund) {
		this.posRefund = posRefund;
		return this;
	}
	
	
	public Double getCashRefund() {
		return cashRefund;
	}

	public RefundVo setCashRefund(Double cashRefund) {
		this.cashRefund = cashRefund;
		return this;
	}
	
	
	public Double getCheckRefund() {
		return checkRefund;
	}

	public RefundVo setCheckRefund(Double checkRefund) {
		this.checkRefund = checkRefund;
		return this;
	}
	
	
	public Double getRemitRefund() {
		return remitRefund;
	}

	public RefundVo setRemitRefund(Double remitRefund) {
		this.remitRefund = remitRefund;
		return this;
	}
	
	
	public String getRemark() {
		return remark;
	}

	public RefundVo setRemark(String remark) {
		this.remark = remark;
		return this;
	}
	
	
	public String getCreateType() {
		return createType;
	}

	public RefundVo setCreateType(String createType) {
		this.createType = createType;
		return this;
	}
	
	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getCreateTime() {
		return createTime;
	}

	public RefundVo setCreateTime(Date createTime) {
		this.createTime = createTime;
		return this;
	}
	
	
	public Integer getCreator() {
		return creator;
	}

	public RefundVo setCreator(Integer creator) {
		this.creator = creator;
		return this;
	}
	
	
	public Integer getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
	}


	public String getApplyChannelCode() {
		return applyChannelCode;
	}

	public void setApplyChannelCode(String applyChannelCode) {
		this.applyChannelCode = applyChannelCode;
	}

	public String getStuName() {
		return stuName;
	}

	public void setStuName(String stuName) {
		this.stuName = stuName;
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
	
	public Integer getItemOneId() {
		return itemOneId;
	}

	public void setItemOneId(Integer itemOneId) {
		this.itemOneId = itemOneId;
	}

	public String getItemOneName() {
		return itemOneName;
	}

	public void setItemOneName(String itemOneName) {
		this.itemOneName = itemOneName;
	}

	public Integer getItemSecondId() {
		return itemSecondId;
	}

	public void setItemSecondId(Integer itemSecondId) {
		this.itemSecondId = itemSecondId;
	}

	public String getItemSecondName() {
		return itemSecondName;
	}

	public void setItemSecondName(String itemSecondName) {
		this.itemSecondName = itemSecondName;
	}

	public Integer getClassTypeId() {
		return classTypeId;
	}

	public void setClassTypeId(Integer classTypeId) {
		this.classTypeId = classTypeId;
	}

	public String getClassTypeName() {
		return classTypeName;
	}

	public void setClassTypeName(String classTypeName) {
		this.classTypeName = classTypeName;
	}

	public Integer getClassTypeHour() {
		return classTypeHour;
	}

	public void setClassTypeHour(Integer classTypeHour) {
		this.classTypeHour = classTypeHour;
	}

	public Integer getStageStatus() {
		return stageStatus;
	}

	public void setStageStatus(Integer stageStatus) {
		this.stageStatus = stageStatus;
	}

	public Integer getExamTermId() {
		return examTermId;
	}

	public void setExamTermId(Integer examTermId) {
		this.examTermId = examTermId;
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
	public Integer getTimeLen() {
		return timeLen;
	}

	public void setTimeLen(Integer timeLen) {
		this.timeLen = timeLen;
	}
	
	public String getTimeMark() {
		return timeMark;
	}

	public void setTimeMark(String timeMark) {
		this.timeMark = timeMark;
	}

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	@Override
	public String toString() {
		return "StudentFeeRefund [" + "id=" + getId() + ", stuId=" + stuId + ", payMasterId=" + payMasterId + ", refundDate=" + refundDate + ", refundFee=" + refundFee + ", posRefund=" + posRefund + ", cashRefund=" + cashRefund + ", checkRefund=" + checkRefund + ", remitRefund=" + remitRefund + ", remark=" + remark + ", createType=" + createType + ", createTime=" + createTime + ", creator=" + creator +  "]";
	}
}
