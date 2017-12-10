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
 * @author chopin
 * @date 2014-12-5
 */
@SuppressWarnings("serial")
public class ClassTypeVo extends BaseEntity {
	
	private String	name;		 /* 班型名称 */ 
	private String	typeCode;		 /* 班型类型代码（普通班；远程班），使用字典表数据 */ 
	private Double	originalPrice;		 /* 原始价格 */ 
	private Double	realPrice;		 /* 真实价格（优惠之后的价格）保留字段，目前和original_price存一样的值 */ 
	private String	schoolsId;		 /* 所属分校主键，以逗号分隔开 */ 
	private Integer	itemOneId;		 /* 一级项目主键 */ 
	private Integer	itemSecondId;		 /* 二级项目主键 */ 
	private String	itemOneName;		 /* 一级项目name */ 
	private String	itemSecondName;		 /* 二级项目name */
	private String	itemOneCode;		 /* 一级项目code */
	private String	itemSecondCode;		 /* 二级项目code */
	private String	itemThirdName;		 /* 三级项目name */
	private String	itemFourthName;		 /* 四级项目name */
	private String	itemThirdCode;		 /* 三级项目code */
	private String	itemFourthCode;		 /* 四级项目code */
	private String	description;		 /* 班型描述 */
	private String	publishStatus;		 /* 发布状态（在售；停售；未发布；）字典表数据 */ 
	private Date	publishTime;		 /* 发布时间 */ 
	private Integer	isSale;		 /* 是否在线售卖（1：是；0：否） */ 
	private String	cover;		 /* 班型的封面，是一个url地址，如果在线售卖时启用该字段 */ 
	private String	subTitle;		 /* 班型的副标题，如果在线售卖时启用该字段 */ 
	private String	detailDesc;		 /* 班型详细描述信息，如果在线售卖时启用该字段 */ 
	private Date	createTime;		 /* 创建时间 */ 
	private Integer	creator;		 /* 创建人 */ 
	private Date	updateTime;		 /* 修改时间 */ 
	private Integer	updator;		 /* 修改人 */ 
	private Integer	delFlag;		 /* 删除标记：1：已删除；0：未删除 */
	private List<ClassModule> modules;
	private List<CourseVideoChapter> videos;
	private List<CourseRemote> remotes;
	private Integer totalClass;
	private Integer	baseNum;		 /*购买基数  */
	private Integer actualNum;       /*购买数量  */
	private String	lableType;		 /* 班型标签 */ 
	private String	teacherId;		 /* 班型标签 */ 
	private Integer faceFlag; /* 是否属于面授 */
	private Integer liveFlag; /* 是否属于直播 */
	private Integer videoFlag; /* 是否属于视频 */
	private Integer remoteFlag; /* 是否属于远程 */
	private Integer validityDay;	/* 有效天数*/
	private Date validityDate;	/* 有效到期*/
	private Integer videoWatchCount; /* 该班型下视频课 有效次数*/
	private Integer liveWatchCount;	/* 该班型下直播u有效次数*/
	
	private Integer recommendFlag; /* 推荐课程标记(1：推荐课程；0：非推荐课程) */
	private Integer createSchoolId; /*创建分校id */
	private String itemTag;
	private String tagName;
	
	private Integer relationId;  /* 课程包与课程关系id*/
	private Integer classPackageCourseId;
	private Integer integralFlag; /* 是否允许使用积分*/
	private Integer memberFlag;  /* 是否允许使用会员*/
	private Integer buyNumMax;  /* 课程最大购买人数*/
	private Integer protocolId;	//协议id
	
	private Integer commodityId;//商品id
	private Integer subjectClassOrder;//学科课程排序
	private String  iconLable;
	private Integer isMicroClass;	//是否微课

    private Integer isOutSource;//是否购买的外校课程
    private Integer isPublic;  //是否公开课程
    private Double publicPrice; //公开课程价格
	
	private String schoolName;
	private String teacherName;
	private String paperDescription;//试卷描述
	private String domain;
	private Integer cddsStatus;//数校上下架状态
	private Integer cddsRecommendFlag;//数校是否推荐
	
	private Integer companyId;
	
	public Integer getCddsRecommendFlag() {
		return cddsRecommendFlag;
	}
	public void setCddsRecommendFlag(Integer cddsRecommendFlag) {
		this.cddsRecommendFlag = cddsRecommendFlag;
	}
	public Integer getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}
	public Integer getCddsStatus() {
		return cddsStatus;
	}
	public void setCddsStatus(Integer cddsStatus) {
		this.cddsStatus = cddsStatus;
	}
	public Integer getIsOutSource() {
		return isOutSource;
	}
	public void setIsOutSource(Integer isOutSource) {
		this.isOutSource = isOutSource;
	}
	public Integer getIsPublic() {
		return isPublic;
	}
	public void setIsPublic(Integer isPublic) {
		this.isPublic = isPublic;
	}
	public Double getPublicPrice() {
		return publicPrice;
	}
	public void setPublicPrice(Double publicPrice) {
		this.publicPrice = publicPrice;
	}
	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	public String getPaperDescription() {
		return paperDescription;
	}
	public void setPaperDescription(String paperDescription) {
		this.paperDescription = paperDescription;
	}
	public Integer getCommodityId() {
		return commodityId;
	}
	public void setCommodityId(Integer commodityId) {
		this.commodityId = commodityId;
	}
	public Integer getProtocolId() {
		return protocolId;
	}
	public void setProtocolId(Integer protocolId) {
		this.protocolId = protocolId;
	}
	public String getTeacherId() {
		return teacherId;
	}
	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId;
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
	public List<ClassModule> getModules() {
		return modules;
	}
	public void setModules(List<ClassModule> modules) {
		this.modules = modules;
	}
	public List<CourseVideoChapter> getVideos() {
		return videos;
	}
	public void setVideos(List<CourseVideoChapter> videos) {
		this.videos = videos;
	}
	
	public List<CourseRemote> getRemotes() {
		return remotes;
	}
	public void setRemotes(List<CourseRemote> remotes) {
		this.remotes = remotes;
	}
	public Integer getTotalClass() {
		return totalClass;
	}
	public void setTotalClass(Integer totalClass) {
		this.totalClass = totalClass;
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
	
	
	public Integer getActualNum() {
		return actualNum;
	}
	public void setActualNum(Integer actualNum) {
		this.actualNum = actualNum;
	}
	
	public ClassTypeVo(String name, String typeCode, Double originalPrice,
			Double realPrice, String schoolsId, Integer itemOneId,
			Integer itemSecondId, String itemOneName, String itemSecondName,
			String description, String publishStatus, Date publishTime,
			Integer isSale, String cover, String subTitle, String detailDesc,
			Date createTime, Integer creator, Date updateTime, Integer updator,
			Integer delFlag, List<ClassModule> modules,
			List<CourseVideoChapter> videos, List<CourseRemote> remotes,
			Integer totalClass, Integer baseNum, Integer actualNum,
			String lableType, String teacherId, Integer faceFlag,
			Integer liveFlag, Integer videoFlag, Integer remoteFlag,
			Integer recommendFlag, Integer createSchoolId) {
		this.name = name;
		this.typeCode = typeCode;
		this.originalPrice = originalPrice;
		this.realPrice = realPrice;
		this.schoolsId = schoolsId;
		this.itemOneId = itemOneId;
		this.itemSecondId = itemSecondId;
		this.itemOneName = itemOneName;
		this.itemSecondName = itemSecondName;
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
		this.modules = modules;
		this.videos = videos;
		this.remotes = remotes;
		this.totalClass = totalClass;
		this.baseNum = baseNum;
		this.actualNum = actualNum;
		this.lableType = lableType;
		this.teacherId = teacherId;
		this.faceFlag = faceFlag;
		this.liveFlag = liveFlag;
		this.videoFlag = videoFlag;
		this.remoteFlag = remoteFlag;
		this.recommendFlag = recommendFlag;
		this.createSchoolId = createSchoolId;
	}
	public ClassTypeVo() {

	}
	@Override
	public String toString() {
		return "ClassTypeVo [name=" + name + ", typeCode=" + typeCode
				+ ", originalPrice=" + originalPrice + ", realPrice="
				+ realPrice + ", schoolsId=" + schoolsId + ", itemOneId="
				+ itemOneId + ", itemSecondId=" + itemSecondId
				+ ", itemOneName=" + itemOneName + ", itemSecondName="
				+ itemSecondName + ", description=" + description
				+ ", publishStatus=" + publishStatus + ", publishTime="
				+ publishTime + ", isSale=" + isSale + ", cover=" + cover
				+ ", subTitle=" + subTitle + ", detailDesc=" + detailDesc
				+ ", createTime=" + createTime + ", creator=" + creator
				+ ", updateTime=" + updateTime + ", updator=" + updator
				+ ", delFlag=" + delFlag + ", modules=" + modules + ", videos="
				+ videos + ", totalClass=" + totalClass + ", baseNum="
				+ baseNum + ", lableType=" + lableType + ", teacherId="
				+ teacherId + "]";
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
	
	public String getItemTag() {
		return itemTag;
	}
	public void setItemTag(String itemTag) {
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
	public Integer getRelationId() {
		return relationId;
	}
	public void setRelationId(Integer relationId) {
		this.relationId = relationId;
	}
	public Integer getClassPackageCourseId() {
		return classPackageCourseId;
	}
	public void setClassPackageCourseId(Integer classPackageCourseId) {
		this.classPackageCourseId = classPackageCourseId;
	}
	public Integer getIntegralFlag() {
		return integralFlag;
	}
	public void setIntegralFlag(Integer integralFlag) {
		this.integralFlag = integralFlag;
	}
	public Integer getMemberFlag() {
		return memberFlag;
	}
	public void setMemberFlag(Integer memberFlag) {
		this.memberFlag = memberFlag;
	}
	public Integer getBuyNumMax() {
		return buyNumMax;
	}
	public void setBuyNumMax(Integer buyNumMax) {
		this.buyNumMax = buyNumMax;
	}
	public Integer getSubjectClassOrder() {
		return subjectClassOrder;
	}
	public void setSubjectClassOrder(Integer subjectClassOrder) {
		this.subjectClassOrder = subjectClassOrder;
	}
	public String getIconLable() {
		return iconLable;
	}

	public void setIconLable(String iconLable) {
		this.iconLable = iconLable;
	}

	public String getItemOneCode() {
		return itemOneCode;
	}

	public void setItemOneCode(String itemOneCode) {
		this.itemOneCode = itemOneCode;
	}

	public String getItemSecondCode() {
		return itemSecondCode;
	}

	public void setItemSecondCode(String itemSecondCode) {
		this.itemSecondCode = itemSecondCode;
	}

	public String getItemThirdName() {
		return itemThirdName;
	}

	public void setItemThirdName(String itemThirdName) {
		this.itemThirdName = itemThirdName;
	}

	public String getItemFourthName() {
		return itemFourthName;
	}

	public void setItemFourthName(String itemFourthName) {
		this.itemFourthName = itemFourthName;
	}

	public String getItemThirdCode() {
		return itemThirdCode;
	}

	public void setItemThirdCode(String itemThirdCode) {
		this.itemThirdCode = itemThirdCode;
	}

	public String getItemFourthCode() {
		return itemFourthCode;
	}

	public void setItemFourthCode(String itemFourthCode) {
		this.itemFourthCode = itemFourthCode;
	}

	public Integer getIsMicroClass() {
		return isMicroClass;
	}

	public void setIsMicroClass(Integer isMicroClass) {
		this.isMicroClass = isMicroClass;
	}
	

	
	public String getTeacherName() {
		return teacherName;
	}
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
	public String getSchoolName() {
		return schoolName;
	}
	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}
}