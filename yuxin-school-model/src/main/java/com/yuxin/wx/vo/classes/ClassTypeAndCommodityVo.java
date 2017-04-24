package com.yuxin.wx.vo.classes;

import java.util.Date;
import java.util.List;

import com.yuxin.wx.common.BaseEntity;
import com.yuxin.wx.model.classes.ClassModule;
import com.yuxin.wx.model.course.CourseRemote;
import com.yuxin.wx.model.course.CourseVideo;
import com.yuxin.wx.model.course.CourseVideoChapter;

/**
 * POJO:ClassTypeVo
 * @author zhang.zx
 * @date 2014-12-5
 */
@SuppressWarnings("serial")
public class ClassTypeAndCommodityVo extends BaseEntity {
	
	private String name; /* 班型名称 */
	private String typeCode; /* 班型类型代码（普通班；远程班），使用字典表数据 */
	private Double originalPrice; /* 原始价格 */
	private Double realPrice; /*
								 * 真实价格（优惠之后的价格） 保留字段，目前和original_price存一样的值
								 */
	private String schoolsId; /* 所属分校主键，以逗号分隔开 */
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
	private Integer faceFlag; /* 是否属于面授 */
	private Integer liveFlag; /* 是否属于直播 */
	private Integer videoFlag; /* 是否属于视频 */
	private Integer remoteFlag; /* 是否属于远程 */
	
	private Integer recommendFlag; /* 推荐课程标记(1：推荐课程；0：非推荐课程) */
	private Integer createSchoolId; /*创建分校id */
	
	//模块
	private String	mname;		 /* 模块名称 */ 
	private Integer	totalClassHour;		 /* 模块总课时 */ 
	private String	teachMethod;		 /* 授课方式（面授；直播；视频）字典表数据 */ 
	private String	moduleType;		 /* 模块类型，保留字段；前导课；理论课；实操课；串讲课；.。。。取字典表数据 */ 
	private String	moduleDesc;		 /* 模块的描述 */ 
	private String	mpublishStatus;		 /* 模块发布状态（已发布；未发布；停用）取字典表数据 */ 
	private Date	mpublishTime;		 /* 模块发布时间 */ 
	private Integer	mdelFlag;		 /* 删除标记 */
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
	public String getSchoolsId() {
		return schoolsId;
	}
	public void setSchoolsId(String schoolsId) {
		this.schoolsId = schoolsId;
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
	public String getMname() {
		return mname;
	}
	public void setMname(String mname) {
		this.mname = mname;
	}
	public Integer getTotalClassHour() {
		return totalClassHour;
	}
	public void setTotalClassHour(Integer totalClassHour) {
		this.totalClassHour = totalClassHour;
	}
	public String getTeachMethod() {
		return teachMethod;
	}
	public void setTeachMethod(String teachMethod) {
		this.teachMethod = teachMethod;
	}
	public String getModuleType() {
		return moduleType;
	}
	public void setModuleType(String moduleType) {
		this.moduleType = moduleType;
	}
	public String getModuleDesc() {
		return moduleDesc;
	}
	public void setModuleDesc(String moduleDesc) {
		this.moduleDesc = moduleDesc;
	}
	public String getMpublishStatus() {
		return mpublishStatus;
	}
	public void setMpublishStatus(String mpublishStatus) {
		this.mpublishStatus = mpublishStatus;
	}
	public Date getMpublishTime() {
		return mpublishTime;
	}
	public void setMpublishTime(Date mpublishTime) {
		this.mpublishTime = mpublishTime;
	}
	public Integer getMdelFlag() {
		return mdelFlag;
	}
	public void setMdelFlag(Integer mdelFlag) {
		this.mdelFlag = mdelFlag;
	} 

}