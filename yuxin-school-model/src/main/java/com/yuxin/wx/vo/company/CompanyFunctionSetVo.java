package com.yuxin.wx.vo.company;


import java.util.Date;

import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:Company
 * 
 * @author zhang.zx
 * @date 
 */
@SuppressWarnings("serial")
public class CompanyFunctionSetVo extends BaseEntity {
	
	private Integer companyId;
	private String functionCode;
	private String functionName;
	private String content;
	private String status;
	private String column1;
	private String column2;
	public CompanyFunctionSetVo() {

	}
	public CompanyFunctionSetVo(Integer companyId, String functionCode,
			String functionName, String content, String status, String column1,
			String column2) {
		this.companyId = companyId;
		this.functionCode = functionCode;
		this.functionName = functionName;
		this.content = content;
		this.status = status;
		this.column1 = column1;
		this.column2 = column2;
	}
	public Integer getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}
	public String getFunctionCode() {
		return functionCode;
	}
	public void setFunctionCode(String functionCode) {
		this.functionCode = functionCode;
	}
	public String getFunctionName() {
		return functionName;
	}
	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getColumn1() {
		return column1;
	}
	public void setColumn1(String column1) {
		this.column1 = column1;
	}
	public String getColumn2() {
		return column2;
	}
	public void setColumn2(String column2) {
		this.column2 = column2;
	}
}
