package com.yuxin.wx.vo.student;

import java.util.Date;

import com.yuxin.wx.common.BaseEntity;

public class StudentVo extends BaseEntity{
	
	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	*/
		
	//private static final long serialVersionUID = 1L;
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
	private Integer companyId;
	
	/***************订单*******************/
	private Integer pid; 
	private String	applyTime;		 /* 报名时间。开单时可以调整。 */ 
	private Integer	examTermId;		 /* 考期id */ 
	private String	examTermName;		 /* 考期名称 */ 
	private Integer	itemOneId;		 /* 一级项目id */ 
	private String	itemOneName;		 /* 一级项目名称 */ 
	private Integer	itemSecondId;		 /* 二级项目id */ 
	private String	itemSecondName;		 /* 二级项目名称 */ 
	private Integer	classTypeId;		 /* 班型id */ 
	private String	classTypeName;		 /* 班型名称 */ 
	private Integer	classTypeHour;		 /* 班型总课时 */ 
	private Integer	stuId;		 /* 学生id */ 
	private Integer	schoolId;		 /* 分校id */ 
	private Integer	campusId;		 /* 校区id */ 
	private Integer	applyPlaceId;		 /* 报名点代码 */ 
	private String	applyChannelCode;		 /* 报名渠道代码：在线报名、分校报名字典表 */ 
	private String	payStatusCode;		 /* 订单状态｛未支付、部分支付，已支付，已转人，已转班型，已延期，已冻结，已完成，已作废｝如果是分期缴费的，所有分期付完后更新该字段的状态为已支付，否则一直是部分支付；字典表 */ 
	private String	bizStatusCode;		 /* 订单业务类型(新报名、重修、转人、转班型。)，字典表 */ 
	private String	isAgent;		 /* 是否代报考（1：是；0：否） */ 
	private String	isAgentMaterialComplete;		 /* 代报考资料是否齐全（1：是；0：否） */ 
	private String	isAgentComplete;		 /* 是否完成代报考（1：是；0：否） */ 
	private String	agentRemark;		 /* 代报考备注 */ 
	private Double	trainingFee;		 /* 培训费用=班型定价 */ 
	private Double	examAgentFee;		 /* 代报费用 */ 
	private String	offerTypeCode;		 /* 优惠类型：优惠卷字典表，保留字段，暂时不用 */ 
	private Double	offerAmount;		 /* 优惠总额。有可能是多张优惠券的合计金额也有可能是内部员工学习、特批学员的优惠。保留字段，暂时不用 */ 
	private Double	totalAmount;		 /* 订单合计金额。订单合计=培训费用+代报费-优惠金额。 */ 
	private String	paymentTypeCode;		 /* 付款方式（全款、分期、贷款），字典表 */ 
	private Integer	relatedPayId;		 /* 相关联的订单id，转人转班型可能会用上。 */
	
	/*************子订单******************/
	private Integer	payMasterId;		 /* 主订单id */ 
	private String	resourceType;		 /* 资源类型：直播、面授、视频、远程教育字典表 */ 
	private String	resourceId;		 /* 资 */ 
	private String	slaveStatusCode;		 /* 子订单状态，字典表 */ 
	
	/**********班型***************/
	private String	tname;		 /* 班型名称 */ 
	private String	typeCode;		 /* 班型类型代码（普通班；远程班），使用字典表数据 */ 
	private Integer	originalPrice;		 /* 原始价格 */ 
	private Integer	realPrice;		 /* 真实价格（优惠之后的价格）保留字段，目前和original_price存一样的值 */ 
	private String	schoolsId;		 /* 所属分校主键，以逗号分隔开 */ 
	private String	description;		 /* 班型描述 */ 
	private String	tpublishStatus;		 /* 发布状态（在售；停售；未发布；）字典表数据 */ 
	private Date	publishTime;		 /* 发布时间 */ 
	private Integer	isSale;		 /* 是否在线售卖（1：是；0：否） */ 
	private String	cover;		 /* 班型的封面，是一个url地址，如果在线售卖时启用该字段 */ 
	private String	subTitle;		 /* 班型的副标题，如果在线售卖时启用该字段 */ 
	private String	detailDesc;		 /* 班型详细描述信息，如果在线售卖时启用该字段 */ 
	
	/***********班号**************/
	private String	nname;		 /* 模块号名称 */ 
	private Integer	moduleId;		 /* 班号所属模块id */ 
	private String	examTerm;		 /* 目标考期 */ 
	private Date	startDate;		 /* 开课点，即第一次上课日期 */ 
	private String	classType;		 /* 班别：周末班；脱产班；晚班；周六班；周日班取字典表数据 */ 
	private String	classDay;		 /* 需要排课的天，周一至周日的任意组合，每个周几之间用逗号分开 */ 
	private String	classScope;		 /* 上课时段：上午；下午；晚上；全天取字典表数据 */ 
	private String	startTime;		 /* 课次开始时间 */ 
	private String	endTime;		 /* 课次结束时间 */ 
	private Integer	classHour;		 /* 每课次课时 */ 
	private String	teachers;		 /* 老师，存老师的id，可以有多个老师，之间用逗号分隔 */ 
	private String	masters;		 /* 班主任，存班主任的id，可以有多个班主任，之间用逗号分隔 */ 
	private String	assistants;		 /* 助教，存助教的id，可以有多个助教，之间用逗号分隔 */ 
	private String	classroom;		 /* 上课n室，授课方式为面授时使用该字段 */ 
	private String	liveRoom;		 /* 直播教室，直播课时使用该字段 */ 
	private String	device;		 /* 上课所需要用的辅助设备，字典表数据投影仪；投影幕；电脑；白板；扩音器；实物投影仪；音频线；录音笔 */ 
	private Integer	planEnrollStudents;		 /* 计划招生人数 */ 
	private Integer	enrollYetStudents;		 /* 实际招生人数 */ 
	private String	npublishStatus;		 /* 发布状态：已创建；可售；已下架取字典表数据 */ 
	private Integer	totalHours;		 /* 班号总课时 */ 
	private String	classTeachType;		 /* 授课方式：面授；直播取字典表数据 */ 
	
	private String options;
	
	/***********usersfront**************/
	private String username;
	
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getOptions() {
		return options;
	}

	public void setOptions(String options) {
		this.options = options;
	}

	public StudentVo(){}
	
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
	public String getApplyTime() {
		return applyTime;
	}
	public void setApplyTime(String applyTime) {
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
	public Integer getStuId() {
		return stuId;
	}
	public void setStuId(Integer stuId) {
		this.stuId = stuId;
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
	public String getIsAgentMaterialComplete() {
		return isAgentMaterialComplete;
	}
	public void setIsAgentMaterialComplete(String isAgentMaterialComplete) {
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public String getTname() {
		return tname;
	}

	public void setTname(String tname) {
		this.tname = tname;
	}

	public String getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

	public Integer getOriginalPrice() {
		return originalPrice;
	}

	public void setOriginalPrice(Integer originalPrice) {
		this.originalPrice = originalPrice;
	}

	public Integer getRealPrice() {
		return realPrice;
	}

	public void setRealPrice(Integer realPrice) {
		this.realPrice = realPrice;
	}

	public String getSchoolsId() {
		return schoolsId;
	}

	public void setSchoolsId(String schoolsId) {
		this.schoolsId = schoolsId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


	public Date getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}

	public Integer getIsSale() {
		return isSale;
	}

	public void setIsSale(Integer isSale) {
		this.isSale = isSale;
	}

	public String getCover() {
		return cover;
	}

	public void setCover(String cover) {
		this.cover = cover;
	}

	public String getSubTitle() {
		return subTitle;
	}

	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}

	public String getDetailDesc() {
		return detailDesc;
	}

	public void setDetailDesc(String detailDesc) {
		this.detailDesc = detailDesc;
	}

	public Integer getPayMasterId() {
		return payMasterId;
	}

	public void setPayMasterId(Integer payMasterId) {
		this.payMasterId = payMasterId;
	}

	public String getResourceType() {
		return resourceType;
	}

	public void setResourceType(String resourceType) {
		this.resourceType = resourceType;
	}

	public String getResourceId() {
		return resourceId;
	}

	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}

	public String getSlaveStatusCode() {
		return slaveStatusCode;
	}

	public void setSlaveStatusCode(String slaveStatusCode) {
		this.slaveStatusCode = slaveStatusCode;
	}

	public String getTpublishStatus() {
		return tpublishStatus;
	}

	public void setTpublishStatus(String tpublishStatus) {
		this.tpublishStatus = tpublishStatus;
	}

	public String getNname() {
		return nname;
	}

	public void setNname(String nname) {
		this.nname = nname;
	}

	public Integer getModuleId() {
		return moduleId;
	}

	public void setModuleId(Integer moduleId) {
		this.moduleId = moduleId;
	}

	public String getExamTerm() {
		return examTerm;
	}

	public void setExamTerm(String examTerm) {
		this.examTerm = examTerm;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public String getClassType() {
		return classType;
	}

	public void setClassType(String classType) {
		this.classType = classType;
	}

	public String getClassDay() {
		return classDay;
	}

	public void setClassDay(String classDay) {
		this.classDay = classDay;
	}

	public String getClassScope() {
		return classScope;
	}

	public void setClassScope(String classScope) {
		this.classScope = classScope;
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

	public Integer getClassHour() {
		return classHour;
	}

	public void setClassHour(Integer classHour) {
		this.classHour = classHour;
	}

	public String getTeachers() {
		return teachers;
	}

	public void setTeachers(String teachers) {
		this.teachers = teachers;
	}

	public String getMasters() {
		return masters;
	}

	public void setMasters(String masters) {
		this.masters = masters;
	}

	public String getAssistants() {
		return assistants;
	}

	public void setAssistants(String assistants) {
		this.assistants = assistants;
	}

	public String getClassroom() {
		return classroom;
	}

	public void setClassroom(String classroom) {
		this.classroom = classroom;
	}

	public String getLiveRoom() {
		return liveRoom;
	}

	public void setLiveRoom(String liveRoom) {
		this.liveRoom = liveRoom;
	}

	public String getDevice() {
		return device;
	}

	public void setDevice(String device) {
		this.device = device;
	}

	public Integer getPlanEnrollStudents() {
		return planEnrollStudents;
	}

	public void setPlanEnrollStudents(Integer planEnrollStudents) {
		this.planEnrollStudents = planEnrollStudents;
	}

	public Integer getEnrollYetStudents() {
		return enrollYetStudents;
	}

	public void setEnrollYetStudents(Integer enrollYetStudents) {
		this.enrollYetStudents = enrollYetStudents;
	}

	public String getNpublishStatus() {
		return npublishStatus;
	}

	public void setNpublishStatus(String npublishStatus) {
		this.npublishStatus = npublishStatus;
	}

	public Integer getTotalHours() {
		return totalHours;
	}

	public void setTotalHours(Integer totalHours) {
		this.totalHours = totalHours;
	}

	public String getClassTeachType() {
		return classTeachType;
	}

	public void setClassTeachType(String classTeachType) {
		this.classTeachType = classTeachType;
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	
}
