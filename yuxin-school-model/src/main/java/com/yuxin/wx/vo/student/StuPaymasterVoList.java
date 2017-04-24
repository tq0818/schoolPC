package com.yuxin.wx.vo.student;

public class StuPaymasterVoList {
	
	private Integer id;
	private Integer	itemOneId;		 /* 一级项目id */ 
	private String	itemOneName;		 /* 一级项目名称 */ 
	private Integer	itemSecondId;		 /* 二级项目id */ 
	private String	itemSecondName;		 /* 二级项目名称 */ 
	private Integer	commodityId;		 /* 班型id */ 
	private String	classTypeName;		 /* 班型名称 */ 
	private Integer	stuId;		 /* 学生id */ 
	private String stuName;
	private String mobile;
	private Integer	schoolId;		 /* 分校id */ 
	private String	isAgent;		 /* 是否代报考（1：是；0：否） */ 
	private Integer	isAgentMaterialComplete;		 /* 代报考资料是否齐全（1：是；0：否） */ 
	private String	isAgentComplete;		 /* 是否完成代报考（1：是；0：否） */ 
	private Integer companyId;
	
	public StuPaymasterVoList() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public StuPaymasterVoList(Integer id, Integer itemOneId,
			String itemOneName, Integer itemSecondId, String itemSecondName,
			Integer commodityId, String classTypeName, Integer stuId,
			Integer schoolId, String isAgent, Integer isAgentMaterialComplete,
			String isAgentComplete, Integer companyId) {
		super();
		this.id = id;
		this.itemOneId = itemOneId;
		this.itemOneName = itemOneName;
		this.itemSecondId = itemSecondId;
		this.itemSecondName = itemSecondName;
		this.commodityId = commodityId;
		this.classTypeName = classTypeName;
		this.stuId = stuId;
		this.schoolId = schoolId;
		this.isAgent = isAgent;
		this.isAgentMaterialComplete = isAgentMaterialComplete;
		this.isAgentComplete = isAgentComplete;
		this.companyId = companyId;
	}


	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	
	public Integer getCommodityId() {
		return commodityId;
	}

	public void setCommodityId(Integer commodityId) {
		this.commodityId = commodityId;
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
}
