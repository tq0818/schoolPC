package com.yuxin.wx.vo.course;

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
public class CourseVideoVo extends BaseEntity {
	
	
	private String	name;		 /* 视频课程名称 */ 
	private Integer	itemOneId;		 /* 一级项目主键 */ 
	private Integer	itemSecondId;		 /* 二级项目主键 */
	private String	itemOneName;		 /* 一级项目主键 */ 
	private String	itemSecondName;		 /* 二级项目主键 */
	private String	description;		 /* 课程描述 */ 
	private String	cover;		 /* 视频课程封面url，保留字段，暂时不用 */ 
	private Integer schoolId;	/* 学校ID */
	private String teachers;	/* 讲师，多个讲师之间用逗号隔开 */
	private String	publishStatus;		 /* 视频课程发布状态取字典表数据 */ 
	private Date	publishDate;		
	private Integer	delFlage;		 /* 删除标记：1：已删除；0：未删除 */ 
	private Date	createTime;		
	private Integer	creator;		
	private Date	updateTime;		
	private Integer	updator;		
	private String creatorName;
	// Constructor
	public CourseVideoVo() {
	}
	public CourseVideoVo(String name, Integer itemOneId, Integer itemSecondId,
			String itemOneName, String itemSecondName, String description,
			String cover, Integer schoolId, String teachers,
			String publishStatus, Date publishDate, Integer delFlage,
			Date createTime, Integer creator, Date updateTime, Integer updator,
			String creatorName) {
		super();
		this.name = name;
		this.itemOneId = itemOneId;
		this.itemSecondId = itemSecondId;
		this.itemOneName = itemOneName;
		this.itemSecondName = itemSecondName;
		this.description = description;
		this.cover = cover;
		this.schoolId = schoolId;
		this.teachers = teachers;
		this.publishStatus = publishStatus;
		this.publishDate = publishDate;
		this.delFlage = delFlage;
		this.createTime = createTime;
		this.creator = creator;
		this.updateTime = updateTime;
		this.updator = updator;
		this.creatorName = creatorName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getItemOneId() {
		return itemOneId;
	}
	public void setItemOneId(Integer itemOneId) {
		this.itemOneId = itemOneId;
	}
	public Integer getItemSecondId() {
		return itemSecondId;
	}
	public void setItemSecondId(Integer itemSecondId) {
		this.itemSecondId = itemSecondId;
	}
	public String getItemOneName() {
		return itemOneName;
	}
	public void setItemOneName(String itemOneName) {
		this.itemOneName = itemOneName;
	}
	public String getItemSecondName() {
		return itemSecondName;
	}
	public void setItemSecondName(String itemSecondName) {
		this.itemSecondName = itemSecondName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCover() {
		return cover;
	}
	public void setCover(String cover) {
		this.cover = cover;
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
	public String getPublishStatus() {
		return publishStatus;
	}
	public void setPublishStatus(String publishStatus) {
		this.publishStatus = publishStatus;
	}
	public Date getPublishDate() {
		return publishDate;
	}
	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}
	public Integer getDelFlage() {
		return delFlage;
	}
	public void setDelFlage(Integer delFlage) {
		this.delFlage = delFlage;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Integer getCreator() {
		return creator;
	}
	public void setCreator(Integer creator) {
		this.creator = creator;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public Integer getUpdator() {
		return updator;
	}
	public void setUpdator(Integer updator) {
		this.updator = updator;
	}
	public String getCreatorName() {
		return creatorName;
	}
	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}
	@Override
	public String toString() {
		return "CourseVideoVo [name=" + name + ", itemOneId=" + itemOneId
				+ ", itemSecondId=" + itemSecondId + ", itemOneName="
				+ itemOneName + ", itemSecondName=" + itemSecondName
				+ ", description=" + description + ", cover=" + cover
				+ ", schoolId=" + schoolId + ", teachers=" + teachers
				+ ", publishStatus=" + publishStatus + ", publishDate="
				+ publishDate + ", delFlage=" + delFlage + ", createTime="
				+ createTime + ", creator=" + creator + ", updateTime="
				+ updateTime + ", updator=" + updator + ", creatorName="
				+ creatorName + "]";
	}
}
