package com.yuxin.wx.model.system;


import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:SysConfigPageRedirect
 * 
 * @author chopin
 * @date 2015-9-25
 */
@SuppressWarnings("serial")
public class SysConfigPageRedirect extends BaseEntity {
	
	
	private String	link;		 /* 页面相对路径 */ 
	private Integer	companyId;		 /* 公司ID */ 
	private Integer	status;		 /* 状态：0：禁用 1-启用 */ 
	private Integer	templateId;		 /* 模板ID */ 
	private String	bussinessType;		 /* 业务类型：字典表 */ 
	private Integer	sysType;		 /* 系统类型：0-前台 1-后台 */
	private Integer schoolId;		/*分校id*/

	// Constructor
	public SysConfigPageRedirect() {
	}
	
	
	
	public SysConfigPageRedirect(String link, Integer companyId, Integer status,
			Integer templateId, String bussinessType, Integer sysType) {
		super();
		this.link = link;
		this.companyId = companyId;
		this.status = status;
		this.templateId = templateId;
		this.bussinessType = bussinessType;
		this.sysType = sysType;
	}

	public String getLink() {
		return link;
	}



	public void setLink(String link) {
		this.link = link;
	}



	public Integer getCompanyId() {
		return companyId;
	}



	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}



	public Integer getStatus() {
		return status;
	}



	public void setStatus(Integer status) {
		this.status = status;
	}



	public Integer getTemplateId() {
		return templateId;
	}



	public void setTemplateId(Integer templateId) {
		this.templateId = templateId;
	}



	public String getBussinessType() {
		return bussinessType;
	}



	public void setBussinessType(String bussinessType) {
		this.bussinessType = bussinessType;
	}



	public Integer getSysType() {
		return sysType;
	}



	public void setSysType(Integer sysType) {
		this.sysType = sysType;
	}



	@Override
	public String toString() {
		return "SysConfigPageRedirect [" + "id=" + getId() + ", link=" + link + ", companyId=" + companyId + ", status=" + status + ", templateId=" + templateId + ", bussinessType=" + bussinessType + ", sysType=" + sysType +  "]";
	}



	public Integer getSchoolId() {
		return schoolId;
	}



	public void setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
	}
}
