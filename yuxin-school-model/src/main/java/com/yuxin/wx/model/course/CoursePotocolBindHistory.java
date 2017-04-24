package com.yuxin.wx.model.course;

import java.util.Date;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import com.yuxin.wx.util.ShortDateSerializer;

import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:CoursePotocolBindHistory
 * 
 * @author chopin
 * @date 2016-11-1
 */
@SuppressWarnings("serial")
public class CoursePotocolBindHistory extends BaseEntity {
	
	
	private Integer	courseId;		 /* 课程ID */ 
	private Integer	potocolId;		 /* 协议ID */ 
	private Date	bindDate;		 /* 绑定时间 */ 
	private Integer	binder;		 /* 绑定人 */ 
	private Integer	companyId;		 /* 公司ID */ 
	private Integer	classPackageId;		

	// Constructor
	public CoursePotocolBindHistory() {
	}
	
	/**
	 * full Constructor
	 */
	public CoursePotocolBindHistory(Integer id, Integer courseId, Integer potocolId, Date bindDate, Integer binder, Integer companyId, Integer classPackageId) {
		setId(id);
		this.courseId = courseId;
		this.potocolId = potocolId;
		this.bindDate = bindDate;
		this.binder = binder;
		this.companyId = companyId;
		this.classPackageId = classPackageId;
	}

	// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为CoursePotocolBindHistory可以实现连缀设置属性
	
	public Integer getCourseId() {
		return courseId;
	}

	public CoursePotocolBindHistory setCourseId(Integer courseId) {
		this.courseId = courseId;
		return this;
	}
	
	
	public Integer getPotocolId() {
		return potocolId;
	}

	public CoursePotocolBindHistory setPotocolId(Integer potocolId) {
		this.potocolId = potocolId;
		return this;
	}
	
	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getBindDate() {
		return bindDate;
	}

	public CoursePotocolBindHistory setBindDate(Date bindDate) {
		this.bindDate = bindDate;
		return this;
	}
	
	
	public Integer getBinder() {
		return binder;
	}

	public CoursePotocolBindHistory setBinder(Integer binder) {
		this.binder = binder;
		return this;
	}
	
	
	public Integer getCompanyId() {
		return companyId;
	}

	public CoursePotocolBindHistory setCompanyId(Integer companyId) {
		this.companyId = companyId;
		return this;
	}
	
	
	public Integer getClassPackageId() {
		return classPackageId;
	}

	public CoursePotocolBindHistory setClassPackageId(Integer classPackageId) {
		this.classPackageId = classPackageId;
		return this;
	}
	
	@Override
	public String toString() {
		return "CoursePotocolBindHistory [" + "id=" + getId() + ", courseId=" + courseId + ", potocolId=" + potocolId + ", bindDate=" + bindDate + ", binder=" + binder + ", companyId=" + companyId + ", classPackageId=" + classPackageId +  "]";
	}
}
