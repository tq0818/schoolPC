package com.yuxin.wx.vo.student;

public class StuClassTypeVoList {
	
	private Integer payMasterId;
	private Integer moduleId;
	private Integer	itemOneId;		 /* 一级项目id */ 
	private String	itemOneName;		 /* 一级项目名称 */ 
	private Integer	itemSecondId;		 /* 二级项目id */ 
	private String	itemSecondName;		 /* 二级项目名称 */ 
	private Integer	classTypeId;		 /* 班型id */ 
	private String	classTypeName;		 /* 班型名称 */ 
	private Integer	stuId;		 /* 学生id */ 
	private String stuName;
	private String mobile;
	private Integer	schoolId;		 /* 分校id */ 
	private String	isAgent;		 /* 是否代报考（1：是；0：否） */ 
	private Integer	isAgentMaterialComplete;		 /* 代报考资料是否齐全（1：是；0：否） */ 
	private String	isAgentComplete;		 /* 是否完成代报考（1：是；0：否） */ 
	private Integer companyId;
	private Integer classTypeHour;
	private String examName;
	private String moduleName;
	private String teacherMethod;
	
	public StuClassTypeVoList() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public StuClassTypeVoList(Integer payMasterId, Integer moduleId,
			Integer itemOneId, String itemOneName, Integer itemSecondId,
			String itemSecondName, Integer classTypeId, String classTypeName,
			Integer stuId, String stuName, String mobile, Integer schoolId,
			String isAgent, Integer isAgentMaterialComplete,
			String isAgentComplete, Integer companyId, Integer classTypeHour,
			String examName, String moduleName, String teacherMethod) {
		super();
		this.payMasterId = payMasterId;
		this.moduleId = moduleId;
		this.itemOneId = itemOneId;
		this.itemOneName = itemOneName;
		this.itemSecondId = itemSecondId;
		this.itemSecondName = itemSecondName;
		this.classTypeId = classTypeId;
		this.classTypeName = classTypeName;
		this.stuId = stuId;
		this.stuName = stuName;
		this.mobile = mobile;
		this.schoolId = schoolId;
		this.isAgent = isAgent;
		this.isAgentMaterialComplete = isAgentMaterialComplete;
		this.isAgentComplete = isAgentComplete;
		this.companyId = companyId;
		this.classTypeHour = classTypeHour;
		this.examName = examName;
		this.moduleName = moduleName;
		this.teacherMethod = teacherMethod;
	}


	public Integer getPayMasterId() {
		return payMasterId;
	}
	public void setPayMasterId(Integer payMasterId) {
		this.payMasterId = payMasterId;
	}
	public Integer getModuleId() {
		return moduleId;
	}
	public void setModuleId(Integer moduleId) {
		this.moduleId = moduleId;
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
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public Integer getSchoolId() {
		return schoolId;
	}
	public void setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
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
	public Integer getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}
	public Integer getClassTypeHour() {
		return classTypeHour;
	}
	public void setClassTypeHour(Integer classTypeHour) {
		this.classTypeHour = classTypeHour;
	}
	public String getExamName() {
		return examName;
	}
	public void setExamName(String examName) {
		this.examName = examName;
	}
	public String getModuleName() {
		return moduleName;
	}
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
	public String getTeacherMethod() {
		return teacherMethod;
	}
	public void setTeacherMethod(String teacherMethod) {
		this.teacherMethod = teacherMethod;
	}
	
	
}
