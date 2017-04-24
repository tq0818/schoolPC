package com.yuxin.wx.model.company;



import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:CompanyAppBarConfig
 * 
 * @author chopin
 * @date 2016-5-5
 */
@SuppressWarnings("serial")
public class CompanyAppBarConfig extends BaseEntity {
	
	
	private String	name;		 /* 名称 */ 
	private String	pageType;		 /* 页面类型（页头：head；页尾：foot;页中：mid） */ 
	private Integer	pageCode;		 /* 页面编码  1.首页2.课程3.题库4.我的5.社区问答6.公开课 */ 
	private Integer	sequence;		 /* 顺序 */ 
	private Integer	status;		 /* 状态 */ 
	private Integer	companyId;		 /* 公司ID */ 
	private Integer	schoolId;		 /* 学校ID */ 

	// Constructor
	public CompanyAppBarConfig() {
	}
	
	/**
	 * full Constructor
	 */
	public CompanyAppBarConfig(Integer id, String name, String pageType, Integer pageCode, Integer sequence, Integer status, Integer companyId, Integer schoolId) {
		setId(id);
		this.name = name;
		this.pageType = pageType;
		this.pageCode = pageCode;
		this.sequence = sequence;
		this.status = status;
		this.companyId = companyId;
		this.schoolId = schoolId;
	}

	// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为CompanyAppBarConfig可以实现连缀设置属性
	
	public String getName() {
		return name;
	}

	public CompanyAppBarConfig setName(String name) {
		this.name = name;
		return this;
	}
	
	
	public String getPageType() {
		return pageType;
	}

	public CompanyAppBarConfig setPageType(String pageType) {
		this.pageType = pageType;
		return this;
	}
	
	
	public Integer getPageCode() {
		return pageCode;
	}

	public CompanyAppBarConfig setPageCode(Integer pageCode) {
		this.pageCode = pageCode;
		return this;
	}
	
	
	public Integer getSequence() {
		return sequence;
	}

	public CompanyAppBarConfig setSequence(Integer sequence) {
		this.sequence = sequence;
		return this;
	}
	
	
	public Integer getStatus() {
		return status;
	}

	public CompanyAppBarConfig setStatus(Integer status) {
		this.status = status;
		return this;
	}
	
	
	public Integer getCompanyId() {
		return companyId;
	}

	public CompanyAppBarConfig setCompanyId(Integer companyId) {
		this.companyId = companyId;
		return this;
	}
	
	
	public Integer getSchoolId() {
		return schoolId;
	}

	public CompanyAppBarConfig setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
		return this;
	}
	
	@Override
	public String toString() {
		return "CompanyAppBarConfig [" + "id=" + getId() + ", name=" + name + ", pageType=" + pageType + ", pageCode=" + pageCode + ", sequence=" + sequence + ", status=" + status + ", companyId=" + companyId + ", schoolId=" + schoolId +  "]";
	}
}
