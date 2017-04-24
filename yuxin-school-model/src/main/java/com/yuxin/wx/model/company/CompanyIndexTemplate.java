package com.yuxin.wx.model.company;


import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:CompanyIndexTemplate
 * 
 * @author chopin
 * @date 2015-5-18
 */
@SuppressWarnings("serial")
public class CompanyIndexTemplate extends BaseEntity {
	
	
	private String	templateName;		 /* 模板名称 */ 
	private String	templateStatus;		 /* 模板状态：0：未使用、1：已使用 */ 
	private Integer	companyId;		 /* 公司id，默认是0,0代表是我们提供的公共模板 */ 
	private Integer	schoolId;		 /* 所属分校id */ 

	// Constructor
	public CompanyIndexTemplate() {
	}
	
	/**
	 * full Constructor
	 */
	public CompanyIndexTemplate(Integer id, String templateName, String templateStatus, Integer companyId, Integer schoolId) {
		setId(id);
		this.templateName = templateName;
		this.templateStatus = templateStatus;
		this.companyId = companyId;
		this.schoolId = schoolId;
	}

	// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为CompanyIndexTemplate可以实现连缀设置属性
	
	public String getTemplateName() {
		return templateName;
	}

	public CompanyIndexTemplate setTemplateName(String templateName) {
		this.templateName = templateName;
		return this;
	}
	
	
	public String getTemplateStatus() {
		return templateStatus;
	}

	public CompanyIndexTemplate setTemplateStatus(String templateStatus) {
		this.templateStatus = templateStatus;
		return this;
	}
	
	
	public Integer getCompanyId() {
		return companyId;
	}

	public CompanyIndexTemplate setCompanyId(Integer companyId) {
		this.companyId = companyId;
		return this;
	}
	
	
	public Integer getSchoolId() {
		return schoolId;
	}

	public CompanyIndexTemplate setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
		return this;
	}
	
	@Override
	public String toString() {
		return "CompanyIndexTemplate [" + "id=" + getId() + ", templateName=" + templateName + ", templateStatus=" + templateStatus + ", companyId=" + companyId + ", schoolId=" + schoolId +  "]";
	}
}
