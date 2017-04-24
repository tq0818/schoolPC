package com.yuxin.wx.model.system;

import java.util.Date;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import com.yuxin.wx.util.ShortDateSerializer;

import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:SysTeacherPersonalStatus
 * 
 * @author chopin
 * @date 2015-10-26
 */
@SuppressWarnings("serial")
public class SysTeacherPersonalStatus extends BaseEntity {
	
	
	private String	content;		 /* 内容 */ 
	private Date	publishTime;		 /* 发布时间 */ 
	private Integer	teacherId;		 /* 教师编号 */ 
	private Integer	topFlag;		 /* 置顶标记 */ 
	private Integer	delFlag;		 /* 删除标记 */ 
	private Integer	schoolId;		
	private Integer	companyId;		

	// Constructor
	public SysTeacherPersonalStatus() {
	}
	
	/**
	 * full Constructor
	 */
	public SysTeacherPersonalStatus(Integer id, String content, Date publishTime, Integer teacherId, Integer topFlag, Integer delFlag, Integer schoolId, Integer companyId) {
		setId(id);
		this.content = content;
		this.publishTime = publishTime;
		this.teacherId = teacherId;
		this.topFlag = topFlag;
		this.delFlag = delFlag;
		this.schoolId = schoolId;
		this.companyId = companyId;
	}

	// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为SysTeacherPersonalStatus可以实现连缀设置属性
	
	public String getContent() {
		return content;
	}

	public SysTeacherPersonalStatus setContent(String content) {
		this.content = content;
		return this;
	}
	
	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getPublishTime() {
		return publishTime;
	}

	public SysTeacherPersonalStatus setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
		return this;
	}
	
	
	public Integer getTeacherId() {
		return teacherId;
	}

	public SysTeacherPersonalStatus setTeacherId(Integer teacherId) {
		this.teacherId = teacherId;
		return this;
	}
	
	
	public Integer getTopFlag() {
		return topFlag;
	}

	public SysTeacherPersonalStatus setTopFlag(Integer topFlag) {
		this.topFlag = topFlag;
		return this;
	}
	
	
	public Integer getDelFlag() {
		return delFlag;
	}

	public SysTeacherPersonalStatus setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
		return this;
	}
	
	
	public Integer getSchoolId() {
		return schoolId;
	}

	public SysTeacherPersonalStatus setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
		return this;
	}
	
	
	public Integer getCompanyId() {
		return companyId;
	}

	public SysTeacherPersonalStatus setCompanyId(Integer companyId) {
		this.companyId = companyId;
		return this;
	}
	
	@Override
	public String toString() {
		return "SysTeacherPersonalStatus [" + "id=" + getId() + ", content=" + content + ", publishTime=" + publishTime + ", teacherId=" + teacherId + ", topFlag=" + topFlag + ", delFlag=" + delFlag + ", schoolId=" + schoolId + ", companyId=" + companyId +  "]";
	}
}
