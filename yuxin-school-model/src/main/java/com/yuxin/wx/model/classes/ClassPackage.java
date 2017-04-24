package com.yuxin.wx.model.classes;

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
public class ClassPackage extends BaseEntity {
	
	
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

	private Integer buyNum;  /* 真实购买人数*/
	private String introduce;	/* 介绍    */
	private Integer protocolId;	//协议id
	
	private Integer stageCount;
	private Integer classTypeCount;
	
	private Integer commodityId;
	private String commodityName;
	
	// Constructor
	public ClassPackage() {
	}
	
	public Integer getProtocolId() {
		return protocolId;
	}

	public void setProtocolId(Integer protocolId) {
		this.protocolId = protocolId;
	}

	/**
	 * full Constructor
	 */
	public ClassPackage(Integer id, String name, Double originalPrice, Double realPrice, Integer baseNum, String description, String categoryCode, String publishStatus, Date publishTime, Integer companyId, Integer schoolId, Integer creator, Date createTime, Date updateTime, Integer updator, Integer delFlag, String cover) {
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
	
	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

	public String getName() {
		return name;
	}

	public ClassPackage setName(String name) {
		this.name = name;
		return this;
	}
	
	
	public Double getOriginalPrice() {
		return originalPrice;
	}

	public ClassPackage setOriginalPrice(Double originalPrice) {
		this.originalPrice = originalPrice;
		return this;
	}
	
	
	public Double getRealPrice() {
		return realPrice;
	}

	public ClassPackage setRealPrice(Double realPrice) {
		this.realPrice = realPrice;
		return this;
	}
	
	
	public Integer getBaseNum() {
		return baseNum;
	}

	public ClassPackage setBaseNum(Integer baseNum) {
		this.baseNum = baseNum;
		return this;
	}
	
	
	public String getDescription() {
		return description;
	}

	public ClassPackage setDescription(String description) {
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

	public ClassPackage setPublishStatus(String publishStatus) {
		this.publishStatus = publishStatus;
		return this;
	}
	
	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getPublishTime() {
		return publishTime;
	}

	public ClassPackage setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
		return this;
	}
	
	
	public Integer getCompanyId() {
		return companyId;
	}

	public ClassPackage setCompanyId(Integer companyId) {
		this.companyId = companyId;
		return this;
	}
	
	
	public Integer getSchoolId() {
		return schoolId;
	}

	public ClassPackage setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
		return this;
	}
	
	
	public Integer getCreator() {
		return creator;
	}

	public ClassPackage setCreator(Integer creator) {
		this.creator = creator;
		return this;
	}
	
	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getCreateTime() {
		return createTime;
	}

	public ClassPackage setCreateTime(Date createTime) {
		this.createTime = createTime;
		return this;
	}
	
	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getUpdateTime() {
		return updateTime;
	}

	public ClassPackage setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
		return this;
	}
	
	
	public Integer getUpdator() {
		return updator;
	}

	public ClassPackage setUpdator(Integer updator) {
		this.updator = updator;
		return this;
	}
	
	
	public Integer getDelFlag() {
		return delFlag;
	}

	public ClassPackage setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
		return this;
	}
	
	
	public String getCover() {
		return cover;
	}

	public ClassPackage setCover(String cover) {
		this.cover = cover;
		return this;
	}
	
	public Integer getRecommendFlag() {
		return recommendFlag;
	}

	public void setRecommendFlag(Integer recommendFlag) {
		this.recommendFlag = recommendFlag;
	}
	
	public Integer getBuyNum() {
		return buyNum;
	}

	public void setBuyNum(Integer buyNum) {
		this.buyNum = buyNum;
	}

	@Override
	public String toString() {
		return "ClassPackage [" + "id=" + getId() + ", name=" + name + ", originalPrice=" + originalPrice + ", realPrice=" + realPrice + ", baseNum=" + baseNum + ", description=" + description + ", categoryCode=" + categoryCode + ", publishStatus=" + publishStatus + ", publishTime=" + publishTime + ", companyId=" + companyId + ", schoolId=" + schoolId + ", creator=" + creator + ", createTime=" + createTime + ", updateTime=" + updateTime + ", updator=" + updator + ", delFlag=" + delFlag + ", cover=" + cover +  "]";
	}

	public Integer getStageCount() {
		return stageCount;
	}

	public void setStageCount(Integer stageCount) {
		this.stageCount = stageCount;
	}

	public Integer getClassTypeCount() {
		return classTypeCount;
	}

	public void setClassTypeCount(Integer classTypeCount) {
		this.classTypeCount = classTypeCount;
	}

	public Integer getCommodityId() {
		return commodityId;
	}

	public void setCommodityId(Integer commodityId) {
		this.commodityId = commodityId;
	}

	public String getCommodityName() {
		return commodityName;
	}

	public void setCommodityName(String commodityName) {
		this.commodityName = commodityName;
	}
}
