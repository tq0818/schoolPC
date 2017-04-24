package com.yuxin.wx.vo.course;

import com.yuxin.wx.common.BaseEntity;

public class ProtocolCourseOrPackageRelation extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 14141414L;

	private Integer protocolId;
	private Integer classTypeId;
	private Integer classPackageId;
	private String classTypeName;
	private String classPackageName;
	private String createTime;
	private Integer createUserId;
	private String createUserName;
	private Integer releaseCount;
	
	
	public Integer getProtocolId() {
		return protocolId;
	}
	public void setProtocolId(Integer protocolId) {
		this.protocolId = protocolId;
	}
	public Integer getClassTypeId() {
		return classTypeId;
	}
	public void setClassTypeId(Integer classTypeId) {
		this.classTypeId = classTypeId;
	}
	public Integer getClassPackageId() {
		return classPackageId;
	}
	public void setClassPackageId(Integer classPackageId) {
		this.classPackageId = classPackageId;
	}
	public String getClassTypeName() {
		return classTypeName;
	}
	public void setClassTypeName(String classTypeName) {
		this.classTypeName = classTypeName;
	}
	public String getClassPackageName() {
		return classPackageName;
	}
	public void setClassPackageName(String classPackageName) {
		this.classPackageName = classPackageName;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public Integer getCreateUserId() {
		return createUserId;
	}
	public void setCreateUserId(Integer createUserId) {
		this.createUserId = createUserId;
	}
	public String getCreateUserName() {
		return createUserName;
	}
	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}
	public Integer getReleaseCount() {
		return releaseCount;
	}
	public void setReleaseCount(Integer releaseCount) {
		this.releaseCount = releaseCount;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
