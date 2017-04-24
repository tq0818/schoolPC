package com.yuxin.wx.model.company;


import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:CompanyLiveWarmupZs
 * 
 * @author wang.zx
 * @date 2015-12-11
 */
@SuppressWarnings("serial")
public class CompanyLiveWarmupZs extends BaseEntity {
	
	
	private Integer	recordId;		 /* 展示互动录播件ID */ 
	private String	recordName;		 /* 展示互动录播件名称 */ 
	private Integer	companyId;		 /* 公司ID */ 
	private Integer	schoolId;		 /* 分校ID */ 

	// Constructor
	public CompanyLiveWarmupZs() {
	}
	
	/**
	 * full Constructor
	 */
	public CompanyLiveWarmupZs(Integer id, Integer recordId, String recordName, Integer companyId, Integer schoolId) {
		setId(id);
		this.recordId = recordId;
		this.recordName = recordName;
		this.companyId = companyId;
		this.schoolId = schoolId;
	}

	// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为CompanyLiveWarmupZs可以实现连缀设置属性
	
	public Integer getRecordId() {
		return recordId;
	}

	public CompanyLiveWarmupZs setRecordId(Integer recordId) {
		this.recordId = recordId;
		return this;
	}
	
	
	public String getRecordName() {
		return recordName;
	}

	public CompanyLiveWarmupZs setRecordName(String recordName) {
		this.recordName = recordName;
		return this;
	}
	
	
	public Integer getCompanyId() {
		return companyId;
	}

	public CompanyLiveWarmupZs setCompanyId(Integer companyId) {
		this.companyId = companyId;
		return this;
	}
	
	
	public Integer getSchoolId() {
		return schoolId;
	}

	public CompanyLiveWarmupZs setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
		return this;
	}
	
	@Override
	public String toString() {
		return "CompanyLiveWarmupZs [" + "id=" + getId() + ", recordId=" + recordId + ", recordName=" + recordName + ", companyId=" + companyId + ", schoolId=" + schoolId +  "]";
	}
}
