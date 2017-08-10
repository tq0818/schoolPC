package com.yuxin.wx.model.company;

import java.util.Date;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import com.yuxin.wx.util.ShortDateSerializer;

import com.yuxin.wx.common.BaseEntity;


@SuppressWarnings("serial")
public class CompanyVideoRecord extends BaseEntity {
	
	
	private Integer	userId;		 /* 前台用户ID */ 
	
	private Integer	companyId;		 /* 公司ID */ 
	
	private Date	previewDate;		 /* 直播日期 */ 
	
	private Integer courseId;		/* 课程id*/
	
    private Integer schoolId;
    
    private Integer lectureId;   //章节录播Id
    
    private Integer comId; // 商品ID
    
    
    
	public Integer getLectureId() {
		return lectureId;
	}
	public void setLectureId(Integer lectureId) {
		this.lectureId = lectureId;
	}
	public Integer getComId() {
		return comId;
	}
	public void setComId(Integer comId) {
		this.comId = comId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}
	
	
	
	public Date getPreviewDate() {
		return previewDate;
	}
	public void setPreviewDate(Date previewDate) {
		this.previewDate = previewDate;
	}
	public Integer getCourseId() {
		return courseId;
	}
	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}
	public Integer getSchoolId() {
		return schoolId;
	}
	public void setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
	}
	
}
