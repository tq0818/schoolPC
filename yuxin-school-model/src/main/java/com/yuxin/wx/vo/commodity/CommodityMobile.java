package com.yuxin.wx.vo.commodity;

import java.util.Date;
import java.util.List;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.yuxin.wx.model.classes.ClassModule;
import com.yuxin.wx.model.classes.ClassModuleLesson;
import com.yuxin.wx.util.ShortDateSerializer;
import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:ClassType
 * 
 * @author wang.zx
 * @date 2014-12-5
 */
@SuppressWarnings("serial")
public class CommodityMobile extends BaseEntity {

	private String name; /* 班型名称 */
	private String typeCode; /* 班型类型代码（普通班；远程班），使用字典表数据 */
	private Double originalPrice; /* 原始价格 */
	private Double realPrice; /*
								 * 真实价格（优惠之后的价格） 保留字段，目前和original_price存一样的值
								 */
	private Integer itemOneId; /* 一级项目主键 */
	private Integer itemSecondId; /* 二级项目主键 */
	private String description; /* 班型描述 */
	private String publishStatus; /*
								 * 发布状态（在售；停售；未发布；） 字典表数据
								 */
	private Date publishTime; /* 发布时间 */
	private Integer isSale; /* 是否在线售卖（1：是；0：否） */
	private String cover; /* 班型的封面，是一个url地址，如果在线售卖时启用该字段 */
	private String subTitle; /* 班型的副标题，如果在线售卖时启用该字段 */
	private String detailDesc; /* 班型详细描述信息，如果在线售卖时启用该字段 */
	private Date createTime; /* 创建时间 */
	private Integer creator; /* 创建人 */
	private Date updateTime; /* 修改时间 */
	private Integer updator; /* 修改人 */
	private Integer delFlag; /* 删除标记：1：已删除；0：未删除 */
	private Integer companyId; /* 删除标记：1：已删除；0：未删除 */
	private Integer baseNum; /* 购买基数 */
	private String lableType; /* 班型标签 */
	private String teacherId; /* 授课老师 */
	private String teacherName; /* 老师名字*/
	private Integer faceFlag; /* 是否属于面授 */
	private Integer liveFlag; /* 是否属于直播 */
	private Integer videoFlag; /* 是否属于视频 */
	private Integer remoteFlag; /* 是否属于远程 */
	
	private Integer recommendFlag; /* 推荐课程标记(1：推荐课程；0：非推荐课程) */
	private Integer createSchoolId; /*创建分校id */
	private String itemOneName;
	private String itemSecondName;
	private Integer validityDay;	/* 有效天数*/
	private Date validityDate;	/* 有效到期*/
	private Integer videoWatchCount; /* 该班型下视频课 有效次数*/
	private Integer liveWatchCount;	/* 该班型下直播u有效次数*/
	
	private Integer itemTag;
	private String tagName;
	
	private Integer paymasterId;
	private Integer stuId;
	private Integer campusId;
	private Integer classTypeHour;
	private String payStatusCode;
	private Double totalAmount;
	
	private Integer buyflag;   /* 用户购买标记*/
	private Integer classTypeId;
	private Integer schoolId;
	private Integer actualNum;
	
	private Integer memberFlag;
	private String iconLable;
	public CommodityMobile() {

	}

	
	public CommodityMobile(String name, String typeCode, Double originalPrice,
			Double realPrice, Integer itemOneId,
			Integer itemSecondId, String description, String publishStatus,
			Date publishTime, Integer isSale, String cover, String subTitle,
			String detailDesc, Date createTime, Integer creator,
			Date updateTime, Integer updator, Integer delFlag,
			Integer companyId, Integer baseNum, String lableType,
			String teacherId, String teacherName, Integer faceFlag,
			Integer liveFlag, Integer videoFlag, Integer remoteFlag,
			Integer recommendFlag, Integer createSchoolId, String itemOneName,
			String itemSecondName, Integer validityDay, Date validityDate,
			Integer videoWatchCount, Integer liveWatchCount, Integer itemTag,
			String tagName, Integer paymasterId, Integer stuId,
			Integer campusId, Integer classTypeHour, String payStatusCode,
			Double totalAmount, Integer buyflag) {
		this.name = name;
		this.typeCode = typeCode;
		this.originalPrice = originalPrice;
		this.realPrice = realPrice;
		this.itemOneId = itemOneId;
		this.itemSecondId = itemSecondId;
		this.description = description;
		this.publishStatus = publishStatus;
		this.publishTime = publishTime;
		this.isSale = isSale;
		this.cover = cover;
		this.subTitle = subTitle;
		this.detailDesc = detailDesc;
		this.createTime = createTime;
		this.creator = creator;
		this.updateTime = updateTime;
		this.updator = updator;
		this.delFlag = delFlag;
		this.companyId = companyId;
		this.baseNum = baseNum;
		this.lableType = lableType;
		this.teacherId = teacherId;
		this.teacherName = teacherName;
		this.faceFlag = faceFlag;
		this.liveFlag = liveFlag;
		this.videoFlag = videoFlag;
		this.remoteFlag = remoteFlag;
		this.recommendFlag = recommendFlag;
		this.createSchoolId = createSchoolId;
		this.itemOneName = itemOneName;
		this.itemSecondName = itemSecondName;
		this.validityDay = validityDay;
		this.validityDate = validityDate;
		this.videoWatchCount = videoWatchCount;
		this.liveWatchCount = liveWatchCount;
		this.itemTag = itemTag;
		this.tagName = tagName;
		this.paymasterId = paymasterId;
		this.stuId = stuId;
		this.campusId = campusId;
		this.classTypeHour = classTypeHour;
		this.payStatusCode = payStatusCode;
		this.totalAmount = totalAmount;
		this.buyflag = buyflag;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPublishStatus() {
		return publishStatus;
	}

	public void setPublishStatus(String publishStatus) {
		this.publishStatus = publishStatus;
	}

	public Date getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}

	public Integer getIsSale() {
		return isSale;
	}

	public void setIsSale(Integer isSale) {
		this.isSale = isSale;
	}

	public String getCover() {
		return cover;
	}

	public void setCover(String cover) {
		this.cover = cover;
	}

	public String getSubTitle() {
		return subTitle;
	}

	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}

	public String getDetailDesc() {
		return detailDesc;
	}

	public void setDetailDesc(String detailDesc) {
		this.detailDesc = detailDesc;
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

	public Integer getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	public Integer getBaseNum() {
		return baseNum;
	}

	public void setBaseNum(Integer baseNum) {
		this.baseNum = baseNum;
	}

	public String getLableType() {
		return lableType;
	}

	public void setLableType(String lableType) {
		this.lableType = lableType;
	}

	public String getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public Integer getFaceFlag() {
		return faceFlag;
	}

	public void setFaceFlag(Integer faceFlag) {
		this.faceFlag = faceFlag;
	}

	public Integer getLiveFlag() {
		return liveFlag;
	}

	public void setLiveFlag(Integer liveFlag) {
		this.liveFlag = liveFlag;
	}

	public Integer getVideoFlag() {
		return videoFlag;
	}

	public void setVideoFlag(Integer videoFlag) {
		this.videoFlag = videoFlag;
	}

	public Integer getRemoteFlag() {
		return remoteFlag;
	}

	public void setRemoteFlag(Integer remoteFlag) {
		this.remoteFlag = remoteFlag;
	}

	public Integer getRecommendFlag() {
		return recommendFlag;
	}

	public void setRecommendFlag(Integer recommendFlag) {
		this.recommendFlag = recommendFlag;
	}

	public Integer getCreateSchoolId() {
		return createSchoolId;
	}

	public void setCreateSchoolId(Integer createSchoolId) {
		this.createSchoolId = createSchoolId;
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

	public Integer getValidityDay() {
		return validityDay;
	}

	public void setValidityDay(Integer validityDay) {
		this.validityDay = validityDay;
	}

	public Date getValidityDate() {
		return validityDate;
	}

	public void setValidityDate(Date validityDate) {
		this.validityDate = validityDate;
	}

	public Integer getVideoWatchCount() {
		return videoWatchCount;
	}

	public void setVideoWatchCount(Integer videoWatchCount) {
		this.videoWatchCount = videoWatchCount;
	}

	public Integer getLiveWatchCount() {
		return liveWatchCount;
	}

	public void setLiveWatchCount(Integer liveWatchCount) {
		this.liveWatchCount = liveWatchCount;
	}

	public Integer getItemTag() {
		return itemTag;
	}

	public void setItemTag(Integer itemTag) {
		this.itemTag = itemTag;
	}

	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	public Integer getPaymasterId() {
		return paymasterId;
	}

	public void setPaymasterId(Integer paymasterId) {
		this.paymasterId = paymasterId;
	}

	public Integer getStuId() {
		return stuId;
	}

	public void setStuId(Integer stuId) {
		this.stuId = stuId;
	}

	public Integer getCampusId() {
		return campusId;
	}

	public void setCampusId(Integer campusId) {
		this.campusId = campusId;
	}

	public Integer getClassTypeHour() {
		return classTypeHour;
	}

	public void setClassTypeHour(Integer classTypeHour) {
		this.classTypeHour = classTypeHour;
	}

	public String getPayStatusCode() {
		return payStatusCode;
	}

	public void setPayStatusCode(String payStatusCode) {
		this.payStatusCode = payStatusCode;
	}

	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Integer getBuyflag() {
		return buyflag;
	}

	public void setBuyflag(Integer buyflag) {
		this.buyflag = buyflag;
	}


	public Integer getClassTypeId() {
		return classTypeId;
	}


	public void setClassTypeId(Integer classTypeId) {
		this.classTypeId = classTypeId;
	}


	public Integer getSchoolId() {
		return schoolId;
	}


	public void setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
	}


	public Integer getActualNum() {
		return actualNum;
	}


	public void setActualNum(Integer actualNum) {
		this.actualNum = actualNum;
	}


	public Integer getMemberFlag() {
		return memberFlag;
	}


	public void setMemberFlag(Integer memberFlag) {
		this.memberFlag = memberFlag;
	}

	public String getIconLable() {
		return iconLable;
	}

	public void setIconLable(String iconLable) {
		this.iconLable = iconLable;
	}
}
