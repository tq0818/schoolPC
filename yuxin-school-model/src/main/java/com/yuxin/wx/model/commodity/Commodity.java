package com.yuxin.wx.model.commodity;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.yuxin.wx.util.ShortDateSerializer;
import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:Commodity
 * 
 * @author wang.zx
 * @date 2015-3-14
 */
@SuppressWarnings("serial")
public class Commodity extends BaseEntity {
	
	
	private String	name;		 /* 商品名称 */ 
	private String	coverUrl;		 /* 商品封面图url */ 
	private String	overview;		 /* 商品概述 */ 
	private Double	originalPrice;		 /* 商品原始价格 */ 
	private Double	realPrice;		 /* 商品的真实价格（优惠后价格） */ 
	private String	type;		 /* 商品类型：班型、书籍、组合商品，字典表数据 */ 
	private Integer	itemOneId;		
	private Integer	itemSecondId;		
	private Integer	schoolId;		
	private Integer	companyId;		
	private Integer	creator;		
	private Date	cerateTime;		
	private Integer	updator;		
	private Date	updateTime;		
	private String	status;		 /* 商品上架状态（1：上架；0：未上架） */ 
	private String	classType;		 /* 班级授课类型：直播、面授、视频 */ 
	private Integer	baseNum;		 /*购买基数  */ 
	private String	lableType;		 /* 班型标签 */
	private Integer faceFlag;		 /* 是否属于面授 */
	private Integer liveFlag;		 /* 是否属于直播 */
	private Integer videoFlag;		 /* 是否属于视频 */
	private Integer remoteFlag;		 /* 是否属于远程 */
	private Integer recommendFlag; /* 推荐课程标记(1：推荐课程；0：非推荐课程) */
	private Integer buyNum;    /* 商品购买人数*/
	private String itemTag;
	private String tagName;
	
	private Integer integralFlag; /* 是否允许使用积分*/
	private Integer memberFlag;  /* 是否允许使用会员*/
	private String itemOneCode;
	private String itemSecondCode;
	private String itemThirdCode;
	private String itemFourthCode;
	private Integer isMicroClass; /* 是否属于微课标签，1:是；0：否 */
	
	private Integer originType;
 	private Integer cddsStatus;//数字学校上下架状态
	private Integer cddsRecommendFlag;//数校推荐状态
	private Double publicPrice;
	
	public Double getPublicPrice() {
		return publicPrice;
	}

	public void setPublicPrice(Double publicPrice) {
		this.publicPrice = publicPrice;
	}

	public Integer getOriginType() {
		return originType;
	}

	public void setOriginType(Integer originType) {
		this.originType = originType;
	}

	public Integer getCddsStatus() {
		return cddsStatus;
	}

	public void setCddsStatus(Integer cddsStatus) {
		this.cddsStatus = cddsStatus;
	}

	public Integer getCddsRecommendFlag() {
		return cddsRecommendFlag;
	}

	public void setCddsRecommendFlag(Integer cddsRecommendFlag) {
		this.cddsRecommendFlag = cddsRecommendFlag;
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

	// Constructor
	public Commodity() {
	}

	public Commodity(String name, String coverUrl, String overview,
			Double originalPrice, Double realPrice, String type,
			Integer itemOneId, Integer itemSecondId, Integer schoolId,
			Integer companyId, Integer creator, Date cerateTime,
			Integer updator, Date updateTime, String status, String classType,
			Integer baseNum, String lableType,Integer faceFlag,Integer liveFlag,
			Integer videoFlag,Integer remoteFlag) {
		super();
		this.name = name;
		this.coverUrl = coverUrl;
		this.overview = overview;
		this.originalPrice = originalPrice;
		this.realPrice = realPrice;
		this.type = type;
		this.itemOneId = itemOneId;
		this.itemSecondId = itemSecondId;
		this.schoolId = schoolId;
		this.companyId = companyId;
		this.creator = creator;
		this.cerateTime = cerateTime;
		this.updator = updator;
		this.updateTime = updateTime;
		this.status = status;
		this.classType = classType;
		this.baseNum = baseNum;
		this.lableType = lableType;
		this.faceFlag=faceFlag;
		this.liveFlag=liveFlag;
		this.remoteFlag=remoteFlag;
		this.videoFlag=videoFlag;
	}

	// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为Commodity可以实现连缀设置属性
	
	public String getName() {
		return name;
	}

	public Commodity setName(String name) {
		this.name = name;
		return this;
	}
	
	
	public String getCoverUrl() {
		return coverUrl;
	}

	public Commodity setCoverUrl(String coverUrl) {
		this.coverUrl = coverUrl;
		return this;
	}
	
	
	public String getOverview() {
		return overview;
	}

	public Commodity setOverview(String overview) {
		this.overview = overview;
		return this;
	}
	
	
	public Double getOriginalPrice() {
		return originalPrice;
	}

	public Commodity setOriginalPrice(Double originalPrice) {
		this.originalPrice = originalPrice;
		return this;
	}
	
	public Double getRealPrice() {
		return realPrice;
	}

	public void setRealPrice(Double realPrice) {
		this.realPrice = realPrice;
	}

	public String getType() {
		return type;
	}

	public Commodity setType(String type) {
		this.type = type;
		return this;
	}
	
	
	public Integer getItemOneId() {
		return itemOneId;
	}

	public Commodity setItemOneId(Integer itemOneId) {
		this.itemOneId = itemOneId;
		return this;
	}
	
	
	public Integer getItemSecondId() {
		return itemSecondId;
	}

	public Commodity setItemSecondId(Integer itemSecondId) {
		this.itemSecondId = itemSecondId;
		return this;
	}
	
	
	public Integer getSchoolId() {
		return schoolId;
	}

	public Commodity setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
		return this;
	}
	
	
	public Integer getCompanyId() {
		return companyId;
	}

	public Commodity setCompanyId(Integer companyId) {
		this.companyId = companyId;
		return this;
	}
	
	
	public Integer getCreator() {
		return creator;
	}

	public Commodity setCreator(Integer creator) {
		this.creator = creator;
		return this;
	}
	
	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getCerateTime() {
		return cerateTime;
	}

	public Commodity setCerateTime(Date cerateTime) {
		this.cerateTime = cerateTime;
		return this;
	}
	
	
	public Integer getUpdator() {
		return updator;
	}

	public Commodity setUpdator(Integer updator) {
		this.updator = updator;
		return this;
	}
	
	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getUpdateTime() {
		return updateTime;
	}

	public Commodity setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
		return this;
	}
	
	
	public String getStatus() {
		return status;
	}

	public Commodity setStatus(String status) {
		this.status = status;
		return this;
	}
	
	
	public String getClassType() {
		return classType;
	}

	public Commodity setClassType(String classType) {
		this.classType = classType;
		return this;
	}
	
	
	public Integer getFaceFlag() {
		return faceFlag;
	}

	public Commodity setFaceFlag(Integer faceFlag) {
		this.faceFlag = faceFlag;
		return this;
	}

	public Integer getLiveFlag() {
		return liveFlag;
	}

	public Commodity setLiveFlag(Integer liveFlag) {
		this.liveFlag = liveFlag;
		return this;
	}

	public Integer getVideoFlag() {
		return videoFlag;
	}

	public Commodity setVideoFlag(Integer videoFlag) {
		this.videoFlag = videoFlag;
		return this;
	}

	public Integer getRemoteFlag() {
		return remoteFlag;
	}

	public Commodity setRemoteFlag(Integer remoteFlag) {
		this.remoteFlag = remoteFlag;
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

	@Override
	public String toString() {
		return "Commodity [name=" + name + ", coverUrl=" + coverUrl
				+ ", overview=" + overview + ", originalPrice=" + originalPrice
				+ ", realPrice=" + realPrice + ", type=" + type
				+ ", itemOneId=" + itemOneId + ", itemSecondId=" + itemSecondId
				+ ", schoolId=" + schoolId + ", companyId=" + companyId
				+ ", creator=" + creator + ", cerateTime=" + cerateTime
				+ ", updator=" + updator + ", updateTime=" + updateTime
				+ ", status=" + status + ", classType=" + classType
				+ ", baseNum=" + baseNum + ", lableType=" + lableType
				+ ", faceFlag=" + faceFlag + ", liveFlag=" + liveFlag
				+ ", videoFlag=" + videoFlag + ", remoteFlag=" + remoteFlag
				+ "]";
	}
	
}
