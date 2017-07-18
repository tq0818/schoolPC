package com.yuxin.wx.model.classes;

import java.util.Date;
import java.util.List;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.yuxin.wx.common.BaseEntity;
import com.yuxin.wx.util.ShortDateSerializer;

/**
 * POJO:ClassType
 *
 * @author wang.zx
 * @date 2014-12-5
 */
@SuppressWarnings("serial")
public class ClassType extends BaseEntity {

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
    private Integer createSchoolId; /* 创建分校id */
    private String itemOneName;
    private String itemSecondName;
    private Integer validityDay; /* 有效天数 */
    private Date validityDate; /* 有效到期 */
    private Integer videoWatchCount; /* 该班型下视频课 有效次数 */
    private Integer liveWatchCount; /* 该班型下直播u有效次数 */
    private Integer buyNumMax; /* 课程最大购买人数限制 */

    private Integer userId;

    // 模块
    private List<ClassModule> classModuleList;
    private String itemTag;
    private String tagName;
    private String itemTag2;

    private String teachersId;
    private Integer commodityId;

    private Integer integralFlag; /* 是否允许使用积分 */
    private Integer memberFlag; /* 是否允许使用会员 */
    private Integer relationId;
    private Integer protocolId; // 协议id
    private Integer subjectClassOrder;//学科课程排序

    private String iconLable;//封面标签

    public Integer getRelationId() {
        return this.relationId;
    }

    public void setRelationId(Integer relationId) {
        this.relationId = relationId;
    }

    public String getTeacherId() {
        return this.teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public Integer getUserId() {
        return this.userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    // Constructor
    public ClassType() {
    }

    public ClassType(String name, String typeCode, Double originalPrice, Double realPrice, String schoolsId, Integer itemOneId, Integer itemSecondId,
            String description, String publishStatus, Date publishTime, Integer isSale, String cover, String subTitle, String detailDesc, Date createTime,
            Integer creator, Date updateTime, Integer updator, Integer delFlag, Integer companyId, Integer baseNum, String lableType, String teacherId,
            Integer faceFlag, Integer liveFlag, Integer videoFlag, Integer remoteFlag, Integer recommendFlag) {
        super();
        this.name = name;
        this.typeCode = typeCode;
        this.originalPrice = originalPrice;
        this.realPrice = realPrice;
        this.schoolsId = schoolsId;
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
        this.faceFlag = faceFlag;
        this.liveFlag = liveFlag;
        this.videoFlag = videoFlag;
        this.remoteFlag = remoteFlag;
        this.recommendFlag = recommendFlag;
    }

    // getter && setter
    // 在setter方法最后加上"return this;"并把返回参数改为ClassType可以实现连缀设置属性

    public String getName() {
        return this.name;
    }

    public Integer getBaseNum() {
        return this.baseNum;
    }

    public void setBaseNum(Integer baseNum) {
        this.baseNum = baseNum;
    }

    public String getLableType() {
        return this.lableType;
    }

    public void setLableType(String lableType) {
        this.lableType = lableType;
    }

    public ClassType setName(String name) {
        this.name = name;
        return this;
    }

    public String getTypeCode() {
        return this.typeCode;
    }

    public ClassType setTypeCode(String typeCode) {
        this.typeCode = typeCode;
        return this;
    }

    public Double getOriginalPrice() {
        return this.originalPrice;
    }

    public void setOriginalPrice(Double originalPrice) {
        this.originalPrice = originalPrice;
    }

    public Double getRealPrice() {
        return this.realPrice;
    }

    public ClassType setRealPrice(Double realPrice) {
        this.realPrice = realPrice;
        return this;
    }

    public String getSchoolsId() {
        return this.schoolsId;
    }

    public ClassType setSchoolsId(String schoolsId) {
        this.schoolsId = schoolsId;
        return this;
    }

    public Integer getItemOneId() {
        return this.itemOneId;
    }

    public ClassType setItemOneId(Integer itemOneId) {
        this.itemOneId = itemOneId;
        return this;
    }

    public Integer getItemSecondId() {
        return this.itemSecondId;
    }

    public ClassType setItemSecondId(Integer itemSecondId) {
        this.itemSecondId = itemSecondId;
        return this;
    }

    public String getDescription() {
        return this.description;
    }

    public ClassType setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getPublishStatus() {
        return this.publishStatus;
    }

    public ClassType setPublishStatus(String publishStatus) {
        this.publishStatus = publishStatus;
        return this;
    }

    @JsonSerialize(using = ShortDateSerializer.class)
    public Date getPublishTime() {
        return this.publishTime;
    }

    public ClassType setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
        return this;
    }

    public Integer getIsSale() {
        return this.isSale;
    }

    public ClassType setIsSale(Integer isSale) {
        this.isSale = isSale;
        return this;
    }

    public String getCover() {
        return this.cover;
    }

    public ClassType setCover(String cover) {
        this.cover = cover;
        return this;
    }

    public String getSubTitle() {
        return this.subTitle;
    }

    public ClassType setSubTitle(String subTitle) {
        this.subTitle = subTitle;
        return this;
    }

    public String getDetailDesc() {
        return this.detailDesc;
    }

    public ClassType setDetailDesc(String detailDesc) {
        this.detailDesc = detailDesc;
        return this;
    }

    @JsonSerialize(using = ShortDateSerializer.class)
    public Date getCreateTime() {
        return this.createTime;
    }

    public ClassType setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public Integer getCreator() {
        return this.creator;
    }

    public ClassType setCreator(Integer creator) {
        this.creator = creator;
        return this;
    }

    @JsonSerialize(using = ShortDateSerializer.class)
    public Date getUpdateTime() {
        return this.updateTime;
    }

    public ClassType setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public Integer getUpdator() {
        return this.updator;
    }

    public ClassType setUpdator(Integer updator) {
        this.updator = updator;
        return this;
    }

    public Integer getDelFlag() {
        return this.delFlag;
    }

    public ClassType setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
        return this;
    }

    public Integer getCompanyId() {
        return this.companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    @Override
    public String toString() {
        return "ClassType [name=" + this.name + ", typeCode=" + this.typeCode + ", originalPrice=" + this.originalPrice + ", realPrice=" + this.realPrice
                + ", schoolsId=" + this.schoolsId + ", itemOneId=" + this.itemOneId + ", itemSecondId=" + this.itemSecondId + ", description="
                + this.description + ", publishStatus=" + this.publishStatus + ", publishTime=" + this.publishTime + ", isSale=" + this.isSale + ", cover="
                + this.cover + ", subTitle=" + this.subTitle + ", detailDesc=" + this.detailDesc + ", createTime=" + this.createTime + ", creator="
                + this.creator + ", updateTime=" + this.updateTime + ", updator=" + this.updator + ", delFlag=" + this.delFlag + ", companyId=" + this.companyId
                + ", baseNum=" + this.baseNum + ", lableType=" + this.lableType + ", teacherId=" + this.teacherId + ", faceFlag=" + this.faceFlag + ",liveFlag="
                + this.liveFlag + ",videoFlag=" + this.videoFlag + ",remoteFlag=" + this.remoteFlag + "]";
    }

    public Integer getFaceFlag() {
        return this.faceFlag;
    }

    public ClassType setFaceFlag(Integer faceFlag) {
        this.faceFlag = faceFlag;
        return this;
    }

    public Integer getLiveFlag() {
        return this.liveFlag;
    }

    public ClassType setLiveFlag(Integer liveFlag) {
        this.liveFlag = liveFlag;
        return this;
    }

    public Integer getVideoFlag() {
        return this.videoFlag;
    }

    public ClassType setVideoFlag(Integer videoFlag) {
        this.videoFlag = videoFlag;
        return this;
    }

    public Integer getRemoteFlag() {
        return this.remoteFlag;
    }

    public ClassType setRemoteFlag(Integer remoteFlag) {
        this.remoteFlag = remoteFlag;
        return this;
    }

    public Integer getRecommendFlag() {
        return this.recommendFlag;
    }

    public void setRecommendFlag(Integer recommendFlag) {
        this.recommendFlag = recommendFlag;
    }

    public Integer getCreateSchoolId() {
        return this.createSchoolId;
    }

    public void setCreateSchoolId(Integer createSchoolId) {
        this.createSchoolId = createSchoolId;
    }

    public String getItemOneName() {
        return this.itemOneName;
    }

    public void setItemOneName(String itemOneName) {
        this.itemOneName = itemOneName;
    }

    public String getItemSecondName() {
        return this.itemSecondName;
    }

    public void setItemSecondName(String itemSecondName) {
        this.itemSecondName = itemSecondName;
    }

    public List<ClassModule> getClassModuleList() {
        return this.classModuleList;
    }

    public void setClassModuleList(List<ClassModule> classModuleList) {
        this.classModuleList = classModuleList;
    }

    public String getItemTag() {
        return this.itemTag;
    }

    public void setItemTag(String itemTag) {
        this.itemTag = itemTag;
    }

    public String getTagName() {
        return this.tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public Integer getValidityDay() {
        return this.validityDay;
    }

    public void setValidityDay(Integer validityDay) {
        this.validityDay = validityDay;
    }

    public Date getValidityDate() {
        return this.validityDate;
    }

    public void setValidityDate(Date validityDate) {
        this.validityDate = validityDate;
    }

    public Integer getVideoWatchCount() {
        return this.videoWatchCount;
    }

    public void setVideoWatchCount(Integer videoWatchCount) {
        this.videoWatchCount = videoWatchCount;
    }

    public Integer getLiveWatchCount() {
        return this.liveWatchCount;
    }

    public void setLiveWatchCount(Integer liveWatchCount) {
        this.liveWatchCount = liveWatchCount;
    }

    public String getTeachersId() {
        return this.teachersId;
    }

    public void setTeachersId(String teachersId) {
        this.teachersId = teachersId;
    }

    public Integer getCommodityId() {
        return this.commodityId;
    }

    public void setCommodityId(Integer commodityId) {
        this.commodityId = commodityId;
    }

    public Integer getIntegralFlag() {
        return this.integralFlag;
    }

    public void setIntegralFlag(Integer integralFlag) {
        this.integralFlag = integralFlag;
    }

    public Integer getMemberFlag() {
        return this.memberFlag;
    }

    public void setMemberFlag(Integer memberFlag) {
        this.memberFlag = memberFlag;
    }

    public String getItemTag2() {
        return this.itemTag2;
    }

    public void setItemTag2(String itemTag2) {
        this.itemTag2 = itemTag2;
    }

    public Integer getBuyNumMax() {
        return this.buyNumMax;
    }

    public void setBuyNumMax(Integer buyNumMax) {
        this.buyNumMax = buyNumMax;
    }

    public Integer getProtocolId() {
        return this.protocolId;
    }

    public void setProtocolId(Integer protocolId) {
        this.protocolId = protocolId;
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
    

}
