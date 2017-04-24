package com.yuxin.wx.model.system;


import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:SysSpecialRecommend
 * 
 * @author chopin
 * @date 2015-3-12
 */
@SuppressWarnings("serial")
public class SysSpecialRecommend extends BaseEntity {
	
	
	private Integer	companyId;		 /* 公司id */ 
	private Integer	schoolId;		 /* 分校id */ 
	private String	specialType;		 /* 特色推荐类型 */ 
	private String	specialUrl;		 /* 特色推荐所打开的url地址 */ 
	private String	specialName;		 /* 特殊推荐名称 */ 
	private String	fileUrl;		 /* 特殊推荐在图片服务器上的目录，相对路径 */ 

	// Constructor
	public SysSpecialRecommend() {
	}
	
	/**
	 * full Constructor
	 */
	public SysSpecialRecommend(Integer id, Integer companyId, Integer schoolId, String specialType, String specialUrl, String specialName, String fileUrl) {
		setId(id);
		this.companyId = companyId;
		this.schoolId = schoolId;
		this.specialType = specialType;
		this.specialUrl = specialUrl;
		this.specialName = specialName;
		this.fileUrl = fileUrl;
	}

	// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为SysSpecialRecommend可以实现连缀设置属性
	
	public Integer getCompanyId() {
		return companyId;
	}

	public SysSpecialRecommend setCompanyId(Integer companyId) {
		this.companyId = companyId;
		return this;
	}
	
	
	public Integer getSchoolId() {
		return schoolId;
	}

	public SysSpecialRecommend setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
		return this;
	}
	
	
	public String getSpecialType() {
		return specialType;
	}

	public SysSpecialRecommend setSpecialType(String specialType) {
		this.specialType = specialType;
		return this;
	}
	
	
	public String getSpecialUrl() {
		return specialUrl;
	}

	public SysSpecialRecommend setSpecialUrl(String specialUrl) {
		this.specialUrl = specialUrl;
		return this;
	}
	
	
	public String getSpecialName() {
		return specialName;
	}

	public SysSpecialRecommend setSpecialName(String specialName) {
		this.specialName = specialName;
		return this;
	}
	
	
	public String getFileUrl() {
		return fileUrl;
	}

	public SysSpecialRecommend setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
		return this;
	}
	
	@Override
	public String toString() {
		return "SysSpecialRecommend [" + "id=" + getId() + ", companyId=" + companyId + ", schoolId=" + schoolId + ", specialType=" + specialType + ", specialUrl=" + specialUrl + ", specialName=" + specialName + ", fileUrl=" + fileUrl +  "]";
	}
}
