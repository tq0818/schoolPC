package com.yuxin.wx.vo.classes;

import java.util.Date;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import com.yuxin.wx.util.ShortDateSerializer;

import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:ClassPackage
 * 
 * @author chopin
 * @date 2016-3-21
 */
@SuppressWarnings("serial")
public class ClassPackageConditionVo extends BaseEntity {
	
	
	private String	name;		 /* 名称 */ 
	private Double	originalPrice;		 /* 原价 */ 
	private Double	realPrice;		 /* 现价 */ 
	private Integer	baseNum;		 /* 购买基数 */ 
	private String	description;		 /* 描述 */ 
	private String	categoryCode;		 /* 分类编码 */ 
	private String	publishStatus;		 /* 发布状态,字典 */ 
	private Date	publishTime;		 /* 发布时间 */ 
	private Integer	companyId;		 /* 公司ID */ 
	private Integer	schoolId;		 /* 分校ID */ 
	private Integer	creator;		 /* 创建人 */ 
	private Date	createTime;		 /* 创建时间 */ 
	private Date	updateTime;		 /* 更新时间 */ 
	private Integer	updator;		 /* 更新人 */ 
	private Integer	delFlag;		 /* 删除标记 */ 
	private String	cover;		 /* 版型封面，图片url地址 */
	private Integer recommendFlag; /* 推荐标记*/
	private Integer stageId;  /* 阶段id*/
	private Integer classTypeId;
	
	// Constructor
	public ClassPackageConditionVo() {
	}
	
	/**
	 * full Constructor
	 */
	public ClassPackageConditionVo(Integer id, String name, Double originalPrice, Double realPrice, Integer baseNum, String description, String categoryCode, String publishStatus, Date publishTime, Integer companyId, Integer schoolId, Integer creator, Date createTime, Date updateTime, Integer updator, Integer delFlag, String cover) {
		setId(id);
		this.name = name;
		this.originalPrice = originalPrice;
		this.realPrice = realPrice;
		this.baseNum = baseNum;
		this.description = description;
		this.categoryCode = categoryCode;
		this.publishStatus = publishStatus;
		this.publishTime = publishTime;
		this.companyId = companyId;
		this.schoolId = schoolId;
		this.creator = creator;
		this.createTime = createTime;
		this.updateTime = updateTime;
		this.updator = updator;
		this.delFlag = delFlag;
		this.cover = cover;
	}

	// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为ClassPackage可以实现连缀设置属性
	
	public String getName() {
		return name;
	}

	public ClassPackageConditionVo setName(String name) {
		this.name = name;
		return this;
	}
	
	
	public Double getOriginalPrice() {
		return originalPrice;
	}

	public ClassPackageConditionVo setOriginalPrice(Double originalPrice) {
		this.originalPrice = originalPrice;
		return this;
	}
	
	
	public Double getRealPrice() {
		return realPrice;
	}

	public ClassPackageConditionVo setRealPrice(Double realPrice) {
		this.realPrice = realPrice;
		return this;
	}
	
	
	public Integer getBaseNum() {
		return baseNum;
	}

	public ClassPackageConditionVo setBaseNum(Integer baseNum) {
		this.baseNum = baseNum;
		return this;
	}
	
	
	public String getDescription() {
		return description;
	}

	public ClassPackageConditionVo setDescription(String description) {
		this.description = description;
		return this;
	}
	
	public String getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

	public String getPublishStatus() {
		return publishStatus;
	}

	public ClassPackageConditionVo setPublishStatus(String publishStatus) {
		this.publishStatus = publishStatus;
		return this;
	}
	
	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getPublishTime() {
		return publishTime;
	}

	public ClassPackageConditionVo setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
		return this;
	}
	
	
	public Integer getCompanyId() {
		return companyId;
	}

	public ClassPackageConditionVo setCompanyId(Integer companyId) {
		this.companyId = companyId;
		return this;
	}
	
	
	public Integer getSchoolId() {
		return schoolId;
	}

	public ClassPackageConditionVo setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
		return this;
	}
	
	
	public Integer getCreator() {
		return creator;
	}

	public ClassPackageConditionVo setCreator(Integer creator) {
		this.creator = creator;
		return this;
	}
	
	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getCreateTime() {
		return createTime;
	}

	public ClassPackageConditionVo setCreateTime(Date createTime) {
		this.createTime = createTime;
		return this;
	}
	
	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getUpdateTime() {
		return updateTime;
	}

	public ClassPackageConditionVo setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
		return this;
	}
	
	
	public Integer getUpdator() {
		return updator;
	}

	public ClassPackageConditionVo setUpdator(Integer updator) {
		this.updator = updator;
		return this;
	}
	
	
	public Integer getDelFlag() {
		return delFlag;
	}

	public ClassPackageConditionVo setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
		return this;
	}
	
	
	public String getCover() {
		return cover;
	}

	public ClassPackageConditionVo setCover(String cover) {
		this.cover = cover;
		return this;
	}
	
	public Integer getRecommendFlag() {
		return recommendFlag;
	}

	public void setRecommendFlag(Integer recommendFlag) {
		this.recommendFlag = recommendFlag;
	}

	public Integer getStageId() {
		return stageId;
	}

	public void setStageId(Integer stageId) {
		this.stageId = stageId;
	}
	
	public Integer getClassTypeId() {
		return classTypeId;
	}

	public void setClassTypeId(Integer classTypeId) {
		this.classTypeId = classTypeId;
	}

	@Override
	public String toString() {
		return "ClassPackage [" + "id=" + getId() + ", name=" + name + ", originalPrice=" + originalPrice + ", realPrice=" + realPrice + ", baseNum=" + baseNum + ", description=" + description + ", categoryCode=" + categoryCode + ", publishStatus=" + publishStatus + ", publishTime=" + publishTime + ", companyId=" + companyId + ", schoolId=" + schoolId + ", creator=" + creator + ", createTime=" + createTime + ", updateTime=" + updateTime + ", updator=" + updator + ", delFlag=" + delFlag + ", cover=" + cover +  "]";
	}
}
