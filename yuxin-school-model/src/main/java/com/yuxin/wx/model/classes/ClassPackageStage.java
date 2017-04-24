package com.yuxin.wx.model.classes;

import java.util.Date;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import com.yuxin.wx.util.ShortDateSerializer;

import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:ClassPackageStage
 * 
 * @author chopin
 * @date 2016-3-21
 */
@SuppressWarnings("serial")
public class ClassPackageStage extends BaseEntity {
	
	
	private Integer	classPackageId;		 /* 课程包ID */ 
	private String	title;		 /* 标题 */ 
	private Integer	creator;		 /* 创建人 */ 
	private Date	createTime;		 /* 创建时间 */ 
	private Integer	updator;		 /* 更新人 */ 
	private Date	updateTime;		 /* 更新时间 */ 
	private String	description;		 /* 描述 */ 
	private Integer	companyId;		 /* 公司ID */ 
	private Integer	schoolId;		 /* 分校ID */ 
	private Integer	sort;		 /* 阶段 */ 
	private Double originalPrice; /* 原价*/
	private Double realPrice;  /* 现价*/
	private String publishStatus;
	private Integer delFlag; 
	private String updateFlag; /* 是否更新标记*/

	// Constructor
	public ClassPackageStage() {
	}
	
	/**
	 * full Constructor
	 */
	public ClassPackageStage(Integer id, Integer classPackageId, String title, Integer creator, Date createTime, Integer updator, Date updateTime, String description, Integer companyId, Integer schoolId, Integer sort) {
		setId(id);
		this.classPackageId = classPackageId;
		this.title = title;
		this.creator = creator;
		this.createTime = createTime;
		this.updator = updator;
		this.updateTime = updateTime;
		this.description = description;
		this.companyId = companyId;
		this.schoolId = schoolId;
		this.sort = sort;
	}

	// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为ClassPackageStage可以实现连缀设置属性
	
	public Integer getClassPackageId() {
		return classPackageId;
	}

	public ClassPackageStage setClassPackageId(Integer classPackageId) {
		this.classPackageId = classPackageId;
		return this;
	}
	
	
	public String getTitle() {
		return title;
	}

	public ClassPackageStage setTitle(String title) {
		this.title = title;
		return this;
	}
	
	
	public Integer getCreator() {
		return creator;
	}

	public ClassPackageStage setCreator(Integer creator) {
		this.creator = creator;
		return this;
	}
	
	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getCreateTime() {
		return createTime;
	}

	public ClassPackageStage setCreateTime(Date createTime) {
		this.createTime = createTime;
		return this;
	}
	
	
	public Integer getUpdator() {
		return updator;
	}

	public ClassPackageStage setUpdator(Integer updator) {
		this.updator = updator;
		return this;
	}
	
	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getUpdateTime() {
		return updateTime;
	}

	public ClassPackageStage setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
		return this;
	}
	
	
	public String getDescription() {
		return description;
	}

	public ClassPackageStage setDescription(String description) {
		this.description = description;
		return this;
	}
	
	
	public Integer getCompanyId() {
		return companyId;
	}

	public ClassPackageStage setCompanyId(Integer companyId) {
		this.companyId = companyId;
		return this;
	}
	
	
	public Integer getSchoolId() {
		return schoolId;
	}

	public ClassPackageStage setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
		return this;
	}
	
	
	public Integer getSort() {
		return sort;
	}

	public ClassPackageStage setSort(Integer sort) {
		this.sort = sort;
		return this;
	}
	
	public Double getOriginalPrice() {
		return originalPrice;
	}

	public void setOriginalPrice(Double originalPrice) {
		this.originalPrice = originalPrice;
	}

	public Double getRealPrice() {
		return realPrice;
	}

	public void setRealPrice(Double realPrice) {
		this.realPrice = realPrice;
	}

	public String getPublishStatus() {
		return publishStatus;
	}

	public void setPublishStatus(String publishStatus) {
		this.publishStatus = publishStatus;
	}

	public Integer getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}
	
	public String getUpdateFlag() {
		return updateFlag;
	}

	public void setUpdateFlag(String updateFlag) {
		this.updateFlag = updateFlag;
	}

	@Override
	public String toString() {
		return "ClassPackageStage [" + "id=" + getId() + ", classPackageId=" + classPackageId + ", title=" + title + ", creator=" + creator + ", createTime=" + createTime + ", updator=" + updator + ", updateTime=" + updateTime + ", description=" + description + ", companyId=" + companyId + ", schoolId=" + schoolId + ", sort=" + sort +  "]";
	}
}
