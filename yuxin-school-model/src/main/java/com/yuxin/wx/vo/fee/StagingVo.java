package com.yuxin.wx.vo.fee;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.yuxin.wx.common.BaseEntity;
import com.yuxin.wx.util.ShortDateSerializer;

public class StagingVo extends BaseEntity{

    private Integer payMasterId;
    private Date applyTime;   /* 报名时间。开单时可以调整。 */ 
    private Integer examTermId;   /* 考期id */ 
    private String examTermName;   /* 考期名称 */ 
    private Integer itemOneId;   /* 一级项目id */ 
    private String itemOneName;   /* 一级项目名称 */ 
    private Integer itemSecondId;   /* 二级项目id */ 
    private String itemSecondName;   /* 二级项目名称 */ 
    private Integer classTypeId;   /* 班型id */ 
    private String classTypeName;   /* 班型名称 */ 
    private Integer classTypeHour;   /* 班型总课时 */ 

    private Integer schoolId;   /* 分校id */ 
    private Integer campusId;   /* 校区id */ 
    private Integer applyPlaceId;   /* 报名点代码 */ 
    private String applyChannelCode;   /* 报名渠道代码：在线报名、分校报名 字典表 */ 
    private String payStatusCode;   /* 订单状态｛未支付、部分支付，已支付，已转人，已转班型，已延期，已冻结，已完成，已作废｝如果是分期缴费的，所有分期付完后更新该字段的状态为已支付，否则一直是部分支付；  字典表 */ 
    private String bizStatusCode;   /* 订单业务类型(新报名、重修、转人、转班型。)，字典表 */ 
    private String isAgent;   /* 是否代报考（1：是；0：否） */ 
    private Integer isAgentMaterialComplete;   /* 代报考资料是否齐全（1：是；0：否） */ 
    private String isAgentComplete;   /* 是否完成代报考（1：是；0：否） */ 
    private String agentRemark;   /* 代报考备注 */ 
    private Double trainingFee;   /* 培训费用=班型定价 */ 
    private Double examAgentFee;   /* 代报费用 */ 
    private String offerTypeCode;   /* 优惠类型：优惠卷字典表，保留字段，暂时不用 */ 
    private Double offerAmount;   /* 优惠总额。有可能是多张优惠券的合计金额也有可能是内部员工学习、特批学员的优惠。保留字段，暂时不用 */ 
    private Double totalAmount;   /* 订单合计金额。订单合计=培训费用+代报费-优惠金额。 */ 
    private String paymentTypeCode;   /* 付款方式（全款、分期、贷款），字典表 */ 
    private Integer relatedPayId;   /* 相关联的订单id，转人转班型可能会用上。 */ 
    
    private Integer stuId;   /* 学生id */ 
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
    
    private Integer stageId;
    private Date stageDate;   /* 分期日期 */ 
    private Double stageFee;   /* 分期费用 */ 
    private String stageStatus;   /* 分期支付状态（1：已缴费；0：未缴费） */ 
    private String payMethod;   /* 支付方式（pos；现金；支票；汇款），字典表 */ 
    private String remark;  
    private String createType;   /* 分期 */ 
    private Double posReal;
    private Double cashReal;
    private Double checkReal;
    private Double remitReal;
    private Date payDate;
    
    private Integer cntFee;
    private Integer cntNum;
    private Integer currtNum;
    
    private Integer companyId;
    private String schoolName;
    
    private String startDate;
	private String endDate;
	
	private Integer timeLen; /* 时间差*/
	private String timeMark;
    
	private String username;
    
    public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Integer getPayMasterId() {
        return payMasterId;
    }
    public void setPayMasterId(Integer payMasterId) {
        this.payMasterId = payMasterId;
    }
    @JsonSerialize(using = ShortDateSerializer.class)
    public Date getApplyTime() {
        return applyTime;
    }
    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
    }
    public Integer getExamTermId() {
        return examTermId;
    }
    public void setExamTermId(Integer examTermId) {
        this.examTermId = examTermId;
    }
    public String getExamTermName() {
        return examTermName;
    }
    public void setExamTermName(String examTermName) {
        this.examTermName = examTermName;
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
    public Integer getSchoolId() {
        return schoolId;
    }
    public void setSchoolId(Integer schoolId) {
        this.schoolId = schoolId;
    }
    public Integer getCampusId() {
        return campusId;
    }
    public void setCampusId(Integer campusId) {
        this.campusId = campusId;
    }
    public Integer getApplyPlaceId() {
        return applyPlaceId;
    }
    public void setApplyPlaceId(Integer applyPlaceId) {
        this.applyPlaceId = applyPlaceId;
    }
    public String getApplyChannelCode() {
        return applyChannelCode;
    }
    public void setApplyChannelCode(String applyChannelCode) {
        this.applyChannelCode = applyChannelCode;
    }
    public String getPayStatusCode() {
        return payStatusCode;
    }
    public void setPayStatusCode(String payStatusCode) {
        this.payStatusCode = payStatusCode;
    }
    public String getBizStatusCode() {
        return bizStatusCode;
    }
    public void setBizStatusCode(String bizStatusCode) {
        this.bizStatusCode = bizStatusCode;
    }
    public String getIsAgent() {
        return isAgent;
    }
    public void setIsAgent(String isAgent) {
        this.isAgent = isAgent;
    }
    public Integer getIsAgentMaterialComplete() {
        return isAgentMaterialComplete;
    }
    public void setIsAgentMaterialComplete(Integer isAgentMaterialComplete) {
        this.isAgentMaterialComplete = isAgentMaterialComplete;
    }
    public String getIsAgentComplete() {
        return isAgentComplete;
    }
    public void setIsAgentComplete(String isAgentComplete) {
        this.isAgentComplete = isAgentComplete;
    }
    public String getAgentRemark() {
        return agentRemark;
    }
    public void setAgentRemark(String agentRemark) {
        this.agentRemark = agentRemark;
    }
    public Double getTrainingFee() {
        return trainingFee;
    }
    public void setTrainingFee(Double trainingFee) {
        this.trainingFee = trainingFee;
    }
    public Double getExamAgentFee() {
        return examAgentFee;
    }
    public void setExamAgentFee(Double examAgentFee) {
        this.examAgentFee = examAgentFee;
    }
    public String getOfferTypeCode() {
        return offerTypeCode;
    }
    public void setOfferTypeCode(String offerTypeCode) {
        this.offerTypeCode = offerTypeCode;
    }
    public Double getOfferAmount() {
        return offerAmount;
    }
    public void setOfferAmount(Double offerAmount) {
        this.offerAmount = offerAmount;
    }
    public Double getTotalAmount() {
        return totalAmount;
    }
    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }
    public String getPaymentTypeCode() {
        return paymentTypeCode;
    }
    public void setPaymentTypeCode(String paymentTypeCode) {
        this.paymentTypeCode = paymentTypeCode;
    }
    public Integer getRelatedPayId() {
        return relatedPayId;
    }
    public void setRelatedPayId(Integer relatedPayId) {
        this.relatedPayId = relatedPayId;
    }
    public Integer getStuId() {
        return stuId;
    }
    public void setStuId(Integer stuId) {
        this.stuId = stuId;
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
    public Integer getStageId() {
        return stageId;
    }
    public void setStageId(Integer stageId) {
        this.stageId = stageId;
    }
    @JsonSerialize(using = ShortDateSerializer.class)
    public Date getStageDate() {
        return stageDate;
    }
    public void setStageDate(Date stageDate) {
        this.stageDate = stageDate;
    }
    public Double getStageFee() {
        return stageFee;
    }
    public void setStageFee(Double stageFee) {
        this.stageFee = stageFee;
    }
    public String getStageStatus() {
        return stageStatus;
    }
    public void setStageStatus(String stageStatus) {
        this.stageStatus = stageStatus;
    }
    public String getPayMethod() {
        return payMethod;
    }
    public void setPayMethod(String payMethod) {
        this.payMethod = payMethod;
    }
    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
    public String getCreateType() {
        return createType;
    }
    public void setCreateType(String createType) {
        this.createType = createType;
    }
    public Double getPosReal() {
        return posReal;
    }
    public void setPosReal(Double posReal) {
        this.posReal = posReal;
    }
    public Double getCashReal() {
        return cashReal;
    }
    public void setCashReal(Double cashReal) {
        this.cashReal = cashReal;
    }
    public Double getCheckReal() {
        return checkReal;
    }
    public void setCheckReal(Double checkReal) {
        this.checkReal = checkReal;
    }
    public Double getRemitReal() {
        return remitReal;
    }
    public void setRemitReal(Double remitReal) {
        this.remitReal = remitReal;
    }
    @JsonSerialize(using = ShortDateSerializer.class)
    public Date getPayDate() {
        return payDate;
    }
    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }
    public Integer getCntFee() {
        return cntFee;
    }
    public void setCntFee(Integer cntFee) {
        this.cntFee = cntFee;
    }
    public Integer getCntNum() {
        return cntNum;
    }
    public void setCntNum(Integer cntNum) {
        this.cntNum = cntNum;
    }
    public Integer getCurrtNum() {
        return currtNum;
    }
    public void setCurrtNum(Integer currtNum) {
        this.currtNum = currtNum;
    }
	public Integer getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}
	public String getSchoolName() {
		return schoolName;
	}
	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
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
	
}
