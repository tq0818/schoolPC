package com.yuxin.wx.model.company;


import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:CompanyFunctionSet
 * 
 * @author wang.zx
 * @date 2015-8-19
 */
@SuppressWarnings("serial")
public class CompanyFunctionSet extends BaseEntity {
	
	
	private Integer	companyId;		
	private String	functionCode;		 /* 功能代码，字典表（COMPANY_FUNCTION_CODE） */ 
	private String	functionName;		 /* 功能名称 */ 
	private String	content;		 /* 内容 */ 
	private String	status;		 /* 启用状态，1：启用，0：禁用 */ 
	private String	column1;		 /* 备用字段1 */ 
	private String	column2;		 /* 备用字段2 */ 

	// Constructor
	public CompanyFunctionSet() {
	}
	
	/**
	 * full Constructor
	 */
	public CompanyFunctionSet(Integer id, Integer companyId, String functionCode, String functionName, String content, String status, String column1, String column2) {
		setId(id);
		this.companyId = companyId;
		this.functionCode = functionCode;
		this.functionName = functionName;
		this.content = content;
		this.status = status;
		this.column1 = column1;
		this.column2 = column2;
	}

	// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为CompanyFunctionSet可以实现连缀设置属性
	
	public Integer getCompanyId() {
		return companyId;
	}

	public CompanyFunctionSet setCompanyId(Integer companyId) {
		this.companyId = companyId;
		return this;
	}
	
	
	public String getFunctionCode() {
		return functionCode;
	}

	public CompanyFunctionSet setFunctionCode(String functionCode) {
		this.functionCode = functionCode;
		return this;
	}
	
	
	public String getFunctionName() {
		return functionName;
	}

	public CompanyFunctionSet setFunctionName(String functionName) {
		this.functionName = functionName;
		return this;
	}
	
	
	public String getContent() {
		return content;
	}

	public CompanyFunctionSet setContent(String content) {
		this.content = content;
		return this;
	}
	
	
	public String getStatus() {
		return status;
	}

	public CompanyFunctionSet setStatus(String status) {
		this.status = status;
		return this;
	}
	
	
	public String getColumn1() {
		return column1;
	}

	public CompanyFunctionSet setColumn1(String column1) {
		this.column1 = column1;
		return this;
	}
	
	
	public String getColumn2() {
		return column2;
	}

	public CompanyFunctionSet setColumn2(String column2) {
		this.column2 = column2;
		return this;
	}
	
	@Override
	public String toString() {
		return "CompanyFunctionSet [" + "id=" + getId() + ", companyId=" + companyId + ", functionCode=" + functionCode + ", functionName=" + functionName + ", content=" + content + ", status=" + status + ", column1=" + column1 + ", column2=" + column2 +  "]";
	}
}
