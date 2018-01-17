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
public class SimpleCommodity extends BaseEntity {

	private String name; /* 班型名称 */
	private String typeCode; /* 班型类型代码（普通班；远程班），使用字典表数据 */
	private Double originalPrice; /* 原始价格 */
	private Double realPrice; /*
								 * 真实价格（优惠之后的价格） 保留字段，目前和original_price存一样的值
								 */
	private String schoolsId; /* 所属分校主键，以逗号分隔开 */
	private Integer itemOneId; /* 一级项目主键 */
	private Integer itemSecondId; /* 二级项目主键 */
	private String overview; /* 班型描述 */
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
	
	private ClassModuleLesson lesson;
	private Integer isless;
	private String dateString;
	private long lessonDayStart;
	private long lessonDayEnd;
	private Integer classtypeId;
	private Integer actualNum;
	private Integer buyNumMax;
	private Integer buyNum;
	private String isCollect;
	private String itemSecondCode;

	public String getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId;
	}

	// Constructor
	public SimpleCommodity() {
	}

	public SimpleCommodity(String name, String typeCode, Double originalPrice,
			Double realPrice, String schoolsId, Integer itemOneId,
			Integer itemSecondId, String publishStatus,
			Date publishTime, Integer isSale, String cover, String subTitle,
			String detailDesc, Date createTime, Integer creator,
			Date updateTime, Integer updator, Integer delFlag,
			Integer companyId, Integer baseNum, String lableType,
			String teacherId, Integer faceFlag, Integer liveFlag,
			Integer videoFlag, Integer remoteFlag, Integer recommendFlag) {
		super();
		this.name = name;
		this.typeCode = typeCode;
		this.originalPrice = originalPrice;
		this.realPrice = realPrice;
		this.schoolsId = schoolsId;
		this.itemOneId = itemOneId;
		this.itemSecondId = itemSecondId;
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
		this.faceFlag = faceFlag;
		this.liveFlag = liveFlag;
		this.videoFlag = videoFlag;
		this.remoteFlag = remoteFlag;
		this.recommendFlag = recommendFlag;
	}

	// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为ClassType可以实现连缀设置属性

	public String getName() {
		return name;
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

	public SimpleCommodity setName(String name) {
		this.name = name;
		return this;
	}

	public String getTypeCode() {
		return typeCode;
	}

	public SimpleCommodity setTypeCode(String typeCode) {
		this.typeCode = typeCode;
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

	public SimpleCommodity setRealPrice(Double realPrice) {
		this.realPrice = realPrice;
		return this;
	}

	public String getSchoolsId() {
		return schoolsId;
	}

	public SimpleCommodity setSchoolsId(String schoolsId) {
		this.schoolsId = schoolsId;
		return this;
	}

	public Integer getItemOneId() {
		return itemOneId;
	}

	public SimpleCommodity setItemOneId(Integer itemOneId) {
		this.itemOneId = itemOneId;
		return this;
	}

	public Integer getItemSecondId() {
		return itemSecondId;
	}

	public SimpleCommodity setItemSecondId(Integer itemSecondId) {
		this.itemSecondId = itemSecondId;
		return this;
	}
	
	public String getOverview() {
		return overview;
	}

	public void setOverview(String overview) {
		this.overview = overview;
	}

	public String getPublishStatus() {
		return publishStatus;
	}

	public SimpleCommodity setPublishStatus(String publishStatus) {
		this.publishStatus = publishStatus;
		return this;
	}

	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getPublishTime() {
		return publishTime;
	}

	public SimpleCommodity setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
		return this;
	}

	public Integer getIsSale() {
		return isSale;
	}

	public SimpleCommodity setIsSale(Integer isSale) {
		this.isSale = isSale;
		return this;
	}

	public String getCover() {
		return cover;
	}

	public SimpleCommodity setCover(String cover) {
		this.cover = cover;
		return this;
	}

	public String getSubTitle() {
		return subTitle;
	}

	public SimpleCommodity setSubTitle(String subTitle) {
		this.subTitle = subTitle;
		return this;
	}

	public String getDetailDesc() {
		return detailDesc;
	}

	public SimpleCommodity setDetailDesc(String detailDesc) {
		this.detailDesc = detailDesc;
		return this;
	}

	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getCreateTime() {
		return createTime;
	}

	public SimpleCommodity setCreateTime(Date createTime) {
		this.createTime = createTime;
		return this;
	}

	public Integer getCreator() {
		return creator;
	}

	public SimpleCommodity setCreator(Integer creator) {
		this.creator = creator;
		return this;
	}

	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getUpdateTime() {
		return updateTime;
	}

	public SimpleCommodity setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
		return this;
	}

	public Integer getUpdator() {
		return updator;
	}

	public SimpleCommodity setUpdator(Integer updator) {
		this.updator = updator;
		return this;
	}

	public Integer getDelFlag() {
		return delFlag;
	}

	public SimpleCommodity setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
		return this;
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	public Integer getFaceFlag() {
		return faceFlag;
	}

	public SimpleCommodity setFaceFlag(Integer faceFlag) {
		this.faceFlag = faceFlag;
		return this;
	}

	public Integer getLiveFlag() {
		return liveFlag;
	}

	public SimpleCommodity setLiveFlag(Integer liveFlag) {
		this.liveFlag = liveFlag;
		return this;
	}

	public Integer getVideoFlag() {
		return videoFlag;
	}

	public SimpleCommodity setVideoFlag(Integer videoFlag) {
		this.videoFlag = videoFlag;
		return this;
	}

	public Integer getRemoteFlag() {
		return remoteFlag;
	}

	public SimpleCommodity setRemoteFlag(Integer remoteFlag) {
		this.remoteFlag = remoteFlag;
		return this;
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

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
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

	public ClassModuleLesson getLesson() {
		return lesson;
	}

	public void setLesson(ClassModuleLesson lesson) {
		this.lesson = lesson;
	}

	public Integer getIsless() {
		return isless;
	}

	public void setIsless(Integer isless) {
		this.isless = isless;
	}

	public String getDateString() {
		return dateString;
	}

	public void setDateString(String dateString) {
		this.dateString = dateString;
	}

	public long getLessonDayStart() {
		return lessonDayStart;
	}

	public void setLessonDayStart(long lessonDayStart) {
		this.lessonDayStart = lessonDayStart;
	}

	public long getLessonDayEnd() {
		return lessonDayEnd;
	}

	public void setLessonDayEnd(long lessonDayEnd) {
		this.lessonDayEnd = lessonDayEnd;
	}

	public Integer getClasstypeId() {
		return classtypeId;
	}

	public void setClasstypeId(Integer classtypeId) {
		this.classtypeId = classtypeId;
	}

	public Integer getActualNum() {
		return actualNum;
	}

	public void setActualNum(Integer actualNum) {
		this.actualNum = actualNum;
	}

	public Integer getBuyNumMax() {
		return buyNumMax;
	}

	public void setBuyNumMax(Integer buyNumMax) {
		this.buyNumMax = buyNumMax;
	}

	public Integer getBuyNum() {
		return buyNum;
	}

	public void setBuyNum(Integer buyNum) {
		this.buyNum = buyNum;
	}

	public String getIsCollect() {
		return isCollect;
	}

	public void setIsCollect(String isCollect) {
		this.isCollect = isCollect;
	}

	public String getItemSecondCode() {
		return itemSecondCode;
	}

	public void setItemSecondCode(String itemSecondCode) {
		this.itemSecondCode = itemSecondCode;
	}
	
}
