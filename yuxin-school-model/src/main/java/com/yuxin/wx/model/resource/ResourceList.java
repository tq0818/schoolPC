package com.yuxin.wx.model.resource;

import java.util.Date;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import com.yuxin.wx.util.ShortDateSerializer;

import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:ResourceList
 * 
 * @author wang.zx
 * @date 2016-9-1
 */
@SuppressWarnings("serial")
public class ResourceList extends BaseEntity {
	
	
	private String	fileName;		 /* 文件名称 */ 
	private String	fileType;		 /* 文件类型  xls、xlsx、doc、docx、pdf、swf、flv、ppt、pptx、zip */ 
	private String	filePath;		 /* 文件路径 */ 
	private String	fileSize;		 /* 文件大小，单位字节 */ 
	private Date	uploadTime;		 /* 上传时间 */ 
	private Integer	uploadUserId;		 /* 上传人 */ 
	private String uploadUserName;	 /* 上传人名字*/
	private Integer uploadType;		/* 上传类型 0-资源  1-资料*/
	private Integer	delFlag;		 /* 删除标记 */ 
	private String	uuid;		
	private Integer	companyId;		
	private String	fileCategory;		 /* 文件分类   flash、video、doc、ppt、audio */ 
	private Date	delTime;		 /* 删除时间 */ 
	private Integer sysItemOne;
	private Integer sysItemSecond;
	private Integer schoolId;
	private String tag;
	private String sourcePath;
	private String sourceSize;
	private Integer updator;
	private Date updateTime;
	private Integer oldData;		/* 1 : 旧数据*/
	private Integer uploadFrontId;

	// Constructor
	public ResourceList() {
	}
	public ResourceList(String fileName, String fileType, String filePath,
			String fileSize, Date uploadTime, Integer uploadUserId,
			String uploadUserName, Integer uploadType, Integer delFlag,
			String uuid, Integer companyId, String fileCategory, Date delTime,
			Integer sysItemOne, Integer sysItemSecond, Integer schoolId,
			String tag, String sourcePath, String sourceSize) {
		super();
		this.fileName = fileName;
		this.fileType = fileType;
		this.filePath = filePath;
		this.fileSize = fileSize;
		this.uploadTime = uploadTime;
		this.uploadUserId = uploadUserId;
		this.uploadUserName = uploadUserName;
		this.uploadType = uploadType;
		this.delFlag = delFlag;
		this.uuid = uuid;
		this.companyId = companyId;
		this.fileCategory = fileCategory;
		this.delTime = delTime;
		this.sysItemOne = sysItemOne;
		this.sysItemSecond = sysItemSecond;
		this.schoolId = schoolId;
		this.tag = tag;
		this.sourcePath = sourcePath;
		this.sourceSize = sourceSize;
	}


	// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为ResourceList可以实现连缀设置属性
	
	public String getFileName() {
		return fileName;
	}

	public ResourceList setFileName(String fileName) {
		this.fileName = fileName;
		return this;
	}
	
	
	public String getFileType() {
		return fileType;
	}

	public ResourceList setFileType(String fileType) {
		this.fileType = fileType;
		return this;
	}
	
	
	public String getFilePath() {
		return filePath;
	}

	public ResourceList setFilePath(String filePath) {
		this.filePath = filePath;
		return this;
	}
	
	
	public String getFileSize() {
		return fileSize;
	}

	public ResourceList setFileSize(String fileSize) {
		this.fileSize = fileSize;
		return this;
	}
	
	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getUploadTime() {
		return uploadTime;
	}

	public ResourceList setUploadTime(Date uploadTime) {
		this.uploadTime = uploadTime;
		return this;
	}
	
	
	public Integer getDelFlag() {
		return delFlag;
	}

	public ResourceList setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
		return this;
	}
	
	
	public String getUuid() {
		return uuid;
	}

	public ResourceList setUuid(String uuid) {
		this.uuid = uuid;
		return this;
	}
	
	
	public Integer getCompanyId() {
		return companyId;
	}

	public ResourceList setCompanyId(Integer companyId) {
		this.companyId = companyId;
		return this;
	}
	
	
	public String getFileCategory() {
		return fileCategory;
	}

	public ResourceList setFileCategory(String fileCategory) {
		this.fileCategory = fileCategory;
		return this;
	}
	
	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getDelTime() {
		return delTime;
	}

	public ResourceList setDelTime(Date delTime) {
		this.delTime = delTime;
		return this;
	}

	public Integer getSysItemOne() {
		return sysItemOne;
	}

	public void setSysItemOne(Integer sysItemOne) {
		this.sysItemOne = sysItemOne;
	}

	public Integer getSysItemSecond() {
		return sysItemSecond;
	}

	public void setSysItemSecond(Integer sysItemSecond) {
		this.sysItemSecond = sysItemSecond;
	}

	public Integer getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getSourcePath() {
		return sourcePath;
	}

	public void setSourcePath(String sourcePath) {
		this.sourcePath = sourcePath;
	}

	public String getSourceSize() {
		return sourceSize;
	}

	public void setSourceSize(String sourceSize) {
		this.sourceSize = sourceSize;
	}
	public Integer getUploadUserId() {
		return uploadUserId;
	}
	public void setUploadUserId(Integer uploadUserId) {
		this.uploadUserId = uploadUserId;
	}
	public String getUploadUserName() {
		return uploadUserName;
	}
	public void setUploadUserName(String uploadUserName) {
		this.uploadUserName = uploadUserName;
	}
	public Integer getUploadType() {
		return uploadType;
	}
	public void setUploadType(Integer uploadType) {
		this.uploadType = uploadType;
	}
	public Integer getUpdator() {
		return updator;
	}
	public void setUpdator(Integer updator) {
		this.updator = updator;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public Integer getOldData() {
		return oldData;
	}
	public void setOldData(Integer oldData) {
		this.oldData = oldData;
	}
	public Integer getUploadFrontId() {
		return uploadFrontId;
	}
	public void setUploadFrontId(Integer uploadFrontId) {
		this.uploadFrontId = uploadFrontId;
	}
}
