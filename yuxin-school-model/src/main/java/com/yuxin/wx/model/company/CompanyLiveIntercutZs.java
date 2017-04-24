package com.yuxin.wx.model.company;


import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:CompanyLiveIntercutZs
 * 
 * @author wang.zx
 * @date 2015-12-11
 */
@SuppressWarnings("serial")
public class CompanyLiveIntercutZs extends BaseEntity {
	
	
	public CompanyLiveIntercutZs() {
		super();
	}

	public CompanyLiveIntercutZs(Integer recordId, String recordName,
			Integer classModuleLessionId, Integer recordType,
			Integer companyId, Integer schoolId) {
		super();
		this.recordId = recordId;
		this.recordName = recordName;
		this.classModuleLessionId = classModuleLessionId;
		this.recordType = recordType;
		this.companyId = companyId;
		this.schoolId = schoolId;
	}

	private Integer	recordId;		 /* 展示互动录播件ID */ 
	private String	recordName;		 /* 展示互动录播件名称 */ 
	private Integer	classModuleLessionId;		 /* 课次ID */ 
	private Integer	recordType;		 /* 课件类型（1、插播  2、暖场） */ 
	private Integer	companyId;		 /* 公司ID */ 
	private Integer	schoolId;		 /* 分校ID */ 


	// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为CompanyLiveIntercutZs可以实现连缀设置属性
	
	public Integer getRecordId() {
		return recordId;
	}

	public CompanyLiveIntercutZs setRecordId(Integer recordId) {
		this.recordId = recordId;
		return this;
	}
	
	
	public String getRecordName() {
		return recordName;
	}

	public CompanyLiveIntercutZs setRecordName(String recordName) {
		this.recordName = recordName;
		return this;
	}
	
	
	public Integer getClassModuleLessionId() {
		return classModuleLessionId;
	}

	public CompanyLiveIntercutZs setClassModuleLessionId(Integer classModuleLessionId) {
		this.classModuleLessionId = classModuleLessionId;
		return this;
	}
	
	
	public Integer getRecordType() {
		return recordType;
	}

	public CompanyLiveIntercutZs setRecordType(Integer recordType) {
		this.recordType = recordType;
		return this;
	}
	
	
	public Integer getCompanyId() {
		return companyId;
	}

	public CompanyLiveIntercutZs setCompanyId(Integer companyId) {
		this.companyId = companyId;
		return this;
	}
	
	
	public Integer getSchoolId() {
		return schoolId;
	}

	public CompanyLiveIntercutZs setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
		return this;
	}
	
	@Override
	public String toString() {
		return "CompanyLiveIntercutZs [" + "id=" + getId() + ", recordId=" + recordId + ", recordName=" + recordName + ", classModuleLessionId=" + classModuleLessionId + ", recordType=" + recordType + ", companyId=" + companyId + ", schoolId=" + schoolId +  "]";
	}
}
