package com.yuxin.wx.model.course;

import java.util.Date;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import com.yuxin.wx.util.ShortDateSerializer;

import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:CoursePotocolUserRelation
 * 
 * @author chopin
 * @date 2016-11-1
 */
@SuppressWarnings("serial")
public class CoursePotocolUserRelation extends BaseEntity {
	
	
	private Integer	potocolId;		
	private Integer	userId;		
	private Integer	courseId;		
	private Date	recordTime;		
	private Integer	companyId;		
	private Integer	classPackageId;		

	// Constructor
	public CoursePotocolUserRelation() {
	}
	
	/**
	 * full Constructor
	 */
	public CoursePotocolUserRelation(Integer id, Integer potocolId, Integer userId, Integer courseId, Date recordTime, Integer companyId, Integer classPackageId) {
		setId(id);
		this.potocolId = potocolId;
		this.userId = userId;
		this.courseId = courseId;
		this.recordTime = recordTime;
		this.companyId = companyId;
		this.classPackageId = classPackageId;
	}

	// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为CoursePotocolUserRelation可以实现连缀设置属性
	
	public Integer getPotocolId() {
		return potocolId;
	}

	public CoursePotocolUserRelation setPotocolId(Integer potocolId) {
		this.potocolId = potocolId;
		return this;
	}
	
	
	public Integer getUserId() {
		return userId;
	}

	public CoursePotocolUserRelation setUserId(Integer userId) {
		this.userId = userId;
		return this;
	}
	
	
	public Integer getCourseId() {
		return courseId;
	}

	public CoursePotocolUserRelation setCourseId(Integer courseId) {
		this.courseId = courseId;
		return this;
	}
	
	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getRecordTime() {
		return recordTime;
	}

	public CoursePotocolUserRelation setRecordTime(Date recordTime) {
		this.recordTime = recordTime;
		return this;
	}
	
	
	public Integer getCompanyId() {
		return companyId;
	}

	public CoursePotocolUserRelation setCompanyId(Integer companyId) {
		this.companyId = companyId;
		return this;
	}
	
	
	public Integer getClassPackageId() {
		return classPackageId;
	}

	public CoursePotocolUserRelation setClassPackageId(Integer classPackageId) {
		this.classPackageId = classPackageId;
		return this;
	}
	
	@Override
	public String toString() {
		return "CoursePotocolUserRelation [" + "id=" + getId() + ", potocolId=" + potocolId + ", userId=" + userId + ", courseId=" + courseId + ", recordTime=" + recordTime + ", companyId=" + companyId + ", classPackageId=" + classPackageId +  "]";
	}
}
