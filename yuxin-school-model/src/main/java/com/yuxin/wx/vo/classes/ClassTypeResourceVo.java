package com.yuxin.wx.vo.classes;

import java.util.Date;

import org.apache.commons.lang.StringUtils;

import com.yuxin.wx.common.BaseEntity;
import com.yuxin.wx.util.FilesizeUtil;

/**
 * POJO:ClassTypeResource
 * 
 * @author wang.zx
 * @date 2015-8-11
 */
@SuppressWarnings("serial")
public class ClassTypeResourceVo extends BaseEntity {
	
	
	private Integer	itemOneId;		 /* 学科 */ 
	private Integer	itemSecondId;		 /* 学科小类 */ 
	private Integer	classTypeId;		 /* 班型ID */ 
	private String	classTypeName;		 /* 班型名称 */ 
	private Integer	lectureId;		 /* 课次Id */ 
	private String	resourceType;		 /* 资料类型（） */ 	
	private Integer	companyId;		
	private String lectureType;    /* 资料类型*/
	private Integer fileId;
	
	private String name;
	private String fileSize;
	private String sourceSize;
	private String uploader;
	private Date uploadTime;
	private Integer oldData; 		/* 1 : 旧数据*/
	
	private String format;
	// Constructor
	public ClassTypeResourceVo() {
	}
	
	/**
	 * full Constructor
	 */
	public ClassTypeResourceVo(Integer id, Integer itemOneId, Integer itemSecondId, Integer classTypeId, String classTypeName, Integer lectureId, String resourceType, Integer companyId) {
		setId(id);
		this.itemOneId = itemOneId;
		this.itemSecondId = itemSecondId;
		this.classTypeId = classTypeId;
		this.classTypeName = classTypeName;
		this.lectureId = lectureId;
		this.resourceType = resourceType;
		this.companyId = companyId;
	}

	// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为ClassTypeResource可以实现连缀设置属性
	
	public Integer getItemOneId() {
		return itemOneId;
	}

	public ClassTypeResourceVo setItemOneId(Integer itemOneId) {
		this.itemOneId = itemOneId;
		return this;
	}
	
	
	public Integer getItemSecondId() {
		return itemSecondId;
	}

	public ClassTypeResourceVo setItemSecondId(Integer itemSecondId) {
		this.itemSecondId = itemSecondId;
		return this;
	}
	
	
	public Integer getClassTypeId() {
		return classTypeId;
	}

	public ClassTypeResourceVo setClassTypeId(Integer classTypeId) {
		this.classTypeId = classTypeId;
		return this;
	}
	
	
	public String getClassTypeName() {
		return classTypeName;
	}

	public ClassTypeResourceVo setClassTypeName(String classTypeName) {
		this.classTypeName = classTypeName;
		return this;
	}
	
	
	public Integer getLectureId() {
		return lectureId;
	}

	public ClassTypeResourceVo setLectureId(Integer lectureId) {
		this.lectureId = lectureId;
		return this;
	}
	
	
	public String getResourceType() {
		return resourceType;
	}

	public ClassTypeResourceVo setResourceType(String resourceType) {
		this.resourceType = resourceType;
		return this;
	}
	
	
	public Integer getCompanyId() {
		return companyId;
	}

	public ClassTypeResourceVo setCompanyId(Integer companyId) {
		this.companyId = companyId;
		return this;
	}

	public String getLectureType() {
		return lectureType;
	}

	public void setLectureType(String lectureType) {
		this.lectureType = lectureType;
	}

	public Integer getFileId() {
		return fileId;
	}

	public void setFileId(Integer fileId) {
		this.fileId = fileId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getFormat(){
		if(StringUtils.isNotBlank(this.name) && this.name.lastIndexOf(".")!=-1){
			return this.name.substring(this.name.lastIndexOf("."));
		}
		return "";
	}

	public String getUploader() {
		return uploader;
	}

	public void setUploader(String uploader) {
		this.uploader = uploader;
	}

	public Date getUploadTime() {
		return uploadTime;
	}

	public void setUploadTime(Date uploadTime) {
		this.uploadTime = uploadTime;
	}

	public String getFileSize() {
		return fileSize;
	}

	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}

	public String getSourceSize() {
		return sourceSize;
	}

	public void setSourceSize(String sourceSize) {
		this.sourceSize = sourceSize;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public Integer getOldData() {
		return oldData;
	}

	public void setOldData(Integer oldData) {
		this.oldData = oldData;
	}
}
