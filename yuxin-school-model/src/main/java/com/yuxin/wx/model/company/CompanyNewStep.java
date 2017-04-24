package com.yuxin.wx.model.company;


import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:CompanyNewStep
 * 
 * @author chopin
 * @date 2015-5-20
 */
@SuppressWarnings("serial")
public class CompanyNewStep extends BaseEntity {
	
	
	private Integer	companyAuthority;		 /* 机构认证 */ 
	private Integer	schoolHead;		 /* 网校页头导航 */ 
	private Integer	schoolFoot;		 /* 网校页尾导航 */ 
	private Integer	schoolIndex;		 /* 网校首页内容 */ 
	private Integer	itemAll;		 /* 学科设置 */ 
	private Integer	userCreate;		 /* 创建用户 */ 
	private Integer companyId;		/*所属公司*/
	private Integer newStepFlag;   /*第一次登录标记*/
	

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	// Constructor
	public CompanyNewStep() {
	}
	
	/**
	 * full Constructor
	 */
	public CompanyNewStep(Integer id, Integer companyAuthority, Integer schoolHead, Integer schoolFoot, Integer schoolIndex, Integer itemAll, Integer userCreate) {
		setId(id);
		this.companyAuthority = companyAuthority;
		this.schoolHead = schoolHead;
		this.schoolFoot = schoolFoot;
		this.schoolIndex = schoolIndex;
		this.itemAll = itemAll;
		this.userCreate = userCreate;
	}

	// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为CompanyNewStep可以实现连缀设置属性
	
	public Integer getCompanyAuthority() {
		return companyAuthority;
	}

	public CompanyNewStep setCompanyAuthority(Integer companyAuthority) {
		this.companyAuthority = companyAuthority;
		return this;
	}
	
	
	public Integer getSchoolHead() {
		return schoolHead;
	}

	public CompanyNewStep setSchoolHead(Integer schoolHead) {
		this.schoolHead = schoolHead;
		return this;
	}
	
	
	public Integer getSchoolFoot() {
		return schoolFoot;
	}

	public CompanyNewStep setSchoolFoot(Integer schoolFoot) {
		this.schoolFoot = schoolFoot;
		return this;
	}
	
	
	public Integer getSchoolIndex() {
		return schoolIndex;
	}

	public CompanyNewStep setSchoolIndex(Integer schoolIndex) {
		this.schoolIndex = schoolIndex;
		return this;
	}
	
	
	public Integer getItemAll() {
		return itemAll;
	}

	public CompanyNewStep setItemAll(Integer itemAll) {
		this.itemAll = itemAll;
		return this;
	}
	
	
	public Integer getUserCreate() {
		return userCreate;
	}

	public CompanyNewStep setUserCreate(Integer userCreate) {
		this.userCreate = userCreate;
		return this;
	}
	
	
	
	public Integer getNewStepFlag() {
		return newStepFlag;
	}

	public void setNewStepFlag(Integer newStepFlag) {
		this.newStepFlag = newStepFlag;
	}

	@Override
	public String toString() {
		return "CompanyNewStep [" + "id=" + getId() + ", companyAuthority=" + companyAuthority + ", schoolHead=" + schoolHead + ", schoolFoot=" + schoolFoot + ", schoolIndex=" + schoolIndex + ", itemAll=" + itemAll + ", userCreate=" + userCreate +  "]";
	}
}
