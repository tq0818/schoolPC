package com.yuxin.wx.model.course;

import java.util.Date;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import com.yuxin.wx.util.ShortDateSerializer;

import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:CourseVideo
 * @author wang.zx
 * @date 2014-12-5
 */
@SuppressWarnings("serial")
public class CourseVideo extends BaseEntity {
	
	
	private String	name;		 /* 视频课程名称 */ 
	private Integer	itemOneId;		 /* 一级项目主键 */ 
	private Integer	itemSecondId;		 /* 二级项目主键 */ 
	private String	description;		 /* 课程描述 */ 
	private String	cover;		 /* 视频课程封面url，保留字段，暂时不用 */ 
	private Integer schoolId;	/* 学校ID */
	private Integer companyId;	/*  公司ID */
	private String teachers;	/* 讲师，多个讲师之间用逗号隔开 */
	private String	publishStatus;		 /* 视频课程发布状态取字典表数据 */ 
	private Date	publishDate;		
	private Integer	delFlage;		 /* 删除标记：1：已删除；0：未删除 */ 
	private Date	createTime;		
	private Integer	creator;		
	private Date	updateTime;		
	private Integer	updator;		

	// Constructor
	public CourseVideo() {
	}
	
	/**
	 * full Constructor
	 */
	public CourseVideo(Integer id, String name, Integer itemOneId, Integer itemSecondId, String description, String cover, Integer schoolId,Integer companyId, String teachers, String publishStatus, Date publishDate, 
			Integer delFlage, Date createTime, Integer creator, Date updateTime, Integer updator) {
		setId(id);
		this.name = name;
		this.itemOneId = itemOneId;
		this.itemSecondId = itemSecondId;
		this.description = description;
		this.cover = cover;
		this.schoolId = schoolId;
		this.companyId = companyId;
		this.teachers = teachers;
		this.publishStatus = publishStatus;
		this.publishDate = publishDate;
		this.delFlage = delFlage;
		this.createTime = createTime;
		this.creator = creator;
		this.updateTime = updateTime;
		this.updator = updator;
	}

	// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为CourseVideo可以实现连缀设置属性
	
	public String getName() {
		return name;
	}

	public CourseVideo setName(String name) {
		this.name = name;
		return this;
	}
	
	
	public Integer getItemOneId() {
		return itemOneId;
	}

	public CourseVideo setItemOneId(Integer itemOneId) {
		this.itemOneId = itemOneId;
		return this;
	}
	
	
	public Integer getItemSecondId() {
		return itemSecondId;
	}

	public CourseVideo setItemSecondId(Integer itemSecondId) {
		this.itemSecondId = itemSecondId;
		return this;
	}
	
	
	public String getDescription() {
		return description;
	}

	public CourseVideo setDescription(String description) {
		this.description = description;
		return this;
	}
	
	
	public String getCover() {
		return cover;
	}

	public CourseVideo setCover(String cover) {
		this.cover = cover;
		return this;
	}
	
	
	public String getPublishStatus() {
		return publishStatus;
	}

	public CourseVideo setPublishStatus(String publishStatus) {
		this.publishStatus = publishStatus;
		return this;
	}
	
	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getPublishDate() {
		return publishDate;
	}

	public CourseVideo setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
		return this;
	}
	
	
	public Integer getDelFlage() {
		return delFlage;
	}

	public CourseVideo setDelFlage(Integer delFlage) {
		this.delFlage = delFlage;
		return this;
	}
	
	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getCreateTime() {
		return createTime;
	}

	public CourseVideo setCreateTime(Date createTime) {
		this.createTime = createTime;
		return this;
	}
	
	
	public Integer getCreator() {
		return creator;
	}

	public CourseVideo setCreator(Integer creator) {
		this.creator = creator;
		return this;
	}
	
	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getUpdateTime() {
		return updateTime;
	}

	public CourseVideo setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
		return this;
	}
	
	
	public Integer getUpdator() {
		return updator;
	}

	public CourseVideo setUpdator(Integer updator) {
		this.updator = updator;
		return this;
	}
	
	public Integer getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
	}

	public String getTeachers() {
		return teachers;
	}

	public void setTeachers(String teachers) {
		this.teachers = teachers;
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	@Override
	public String toString() {
		return "CourseVideo [" + "id=" + getId() + ", name=" + name + ", itemOneId=" + itemOneId + ", itemSecondId=" + itemSecondId + ", description=" + description + ", cover=" + cover + ", schoolId=" + schoolId  + ", companyId=" + companyId + ", teachers=" + teachers +", publishStatus=" + publishStatus + ", publishDate=" + publishDate + ", delFlage=" + delFlage + ", createTime=" + createTime + ", creator=" + creator + ", updateTime=" + updateTime + ", updator=" + updator +  "]";
	}
}
