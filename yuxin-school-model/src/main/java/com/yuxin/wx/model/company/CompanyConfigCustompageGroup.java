package com.yuxin.wx.model.company;


import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:CompanyConfigCustompageGroup
 * 
 * @author chopin
 * @date 2016-4-25
 */
@SuppressWarnings("serial")
public class CompanyConfigCustompageGroup extends BaseEntity {
	
	
	private Integer	companyId;		 /* 公司ID */ 
	private String	groupName;		 /* 组名 */ 
	private Integer	delFlag;		 /* 删除标记 */ 
	private Integer	templateId;		 /* 模板 */ 

	// Constructor
	public CompanyConfigCustompageGroup() {
	}
	
	/**
	 * full Constructor
	 */
	public CompanyConfigCustompageGroup(Integer id, Integer companyId, String groupName, Integer delFlag, Integer templateId) {
		setId(id);
		this.companyId = companyId;
		this.groupName = groupName;
		this.delFlag = delFlag;
		this.templateId = templateId;
	}

	// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为CompanyConfigCustompageGroup可以实现连缀设置属性
	
	public Integer getCompanyId() {
		return companyId;
	}

	public CompanyConfigCustompageGroup setCompanyId(Integer companyId) {
		this.companyId = companyId;
		return this;
	}
	
	
	public String getGroupName() {
		return groupName;
	}

	public CompanyConfigCustompageGroup setGroupName(String groupName) {
		this.groupName = groupName;
		return this;
	}
	
	
	public Integer getDelFlag() {
		return delFlag;
	}

	public CompanyConfigCustompageGroup setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
		return this;
	}
	
	
	public Integer getTemplateId() {
		return templateId;
	}

	public CompanyConfigCustompageGroup setTemplateId(Integer templateId) {
		this.templateId = templateId;
		return this;
	}
	
	@Override
	public String toString() {
		return "CompanyConfigCustompageGroup [" + "id=" + getId() + ", companyId=" + companyId + ", groupName=" + groupName + ", delFlag=" + delFlag + ", templateId=" + templateId +  "]";
	}
}
