package com.yuxin.wx.vo.company;

import java.util.Date;
import java.util.List;

import com.yuxin.wx.common.BaseEntity;
import com.yuxin.wx.model.classes.ClassModule;
import com.yuxin.wx.model.classes.ClassType;
import com.yuxin.wx.model.course.CourseRemote;
import com.yuxin.wx.model.course.CourseVideoChapter;

@SuppressWarnings("serial")
public class MemberLevelAndClassTypeVo extends BaseEntity {
	//MemberLevel
	private String	memberLevel;		 /* 会员等级 */ 
	private Double	memberDiscount;		 /* 会员折扣 */ 
	private Integer	memberId;		 /* 会员ID */ 
	private Integer	classTypeId;		 /* 课程ID */ 
	private Integer	companyId;		 /* 公司ID */ 
	private Integer discountType;
	public Integer getDiscountType() {
		return discountType;
	}

	public void setDiscountType(Integer discountType) {
		this.discountType = discountType;
	}


	//ClassType
	private String	name;		 /* 班型名称 */ 
	private String	typeCode;		 /* 班型类型代码（普通班；远程班），使用字典表数据 */ 
	private Double	originalPrice;		 /* 原始价格 */ 
	private Double	realPrice;		 /* 真实价格（优惠之后的价格）保留字段，目前和original_price存一样的值 */ 
	private String	schoolsId;		 /* 所属分校主键，以逗号分隔开 */ 
	private Integer	itemOneId;		 /* 一级项目主键 */ 
	private Integer	itemSecondId;		 /* 二级项目主键 */ 
	private String	itemOneName;		 /* 一级项目name */ 
	private String	itemSecondName;		 /* 二级项目name */
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
	private Integer faceFlag; /* 是否属于面授 */
	private Integer liveFlag; /* 是否属于直播 */
	private Integer videoFlag; /* 是否属于视频 */
	private Integer remoteFlag; /* 是否属于远程 */
	
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


	private Integer memberFlag;  /* 是否允许使用会员*/
	
	
	public MemberLevelAndClassTypeVo() {
		super();
	}
	
	public String getMemberLevel() {
		return memberLevel;
	}


	public void setMemberLevel(String memberLevel) {
		this.memberLevel = memberLevel;
	}


	public Double getMemberDiscount() {
		return memberDiscount;
	}


	public void setMemberDiscount(Double memberDiscount) {
		this.memberDiscount = memberDiscount;
	}


	public Integer getMemberId() {
		return memberId;
	}


	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}


	public Integer getClassTypeId() {
		return classTypeId;
	}


	public void setClassTypeId(Integer classTypeId) {
		this.classTypeId = classTypeId;
	}


	public Integer getCompanyId() {
		return companyId;
	}


	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
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

	public Integer getMemberFlag() {
		return memberFlag;
	}


	public void setMemberFlag(Integer memberFlag) {
		this.memberFlag = memberFlag;
	}


	

}
