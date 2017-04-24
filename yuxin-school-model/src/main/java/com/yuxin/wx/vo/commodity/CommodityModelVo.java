package com.yuxin.wx.vo.commodity;

import java.util.Date;

import com.yuxin.wx.common.BaseEntity;
import com.yuxin.wx.model.system.SysConfigTeacher;

@SuppressWarnings("serial")
public class CommodityModelVo extends BaseEntity{
	
	private Integer comId;/*商品ID*/
	private Integer collectId;/*收藏表里的ID*/
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
	private Integer classTypeId;  /*班型Id*/
	private Integer productType; /*产品类型*/
	private Integer baseNum;   /*售卖基数*/
	private Integer actualNum;  /*实际售卖数*/
	private Integer totalNum;  /*售卖总数*/
	private Integer collectCount;/*被收藏次数*/
	private String lableType; /* 班级授课类型：直播、面授、视频 */ 
	private Integer faceFlag; /* 是否属于面授标签，1:是；0：否*/
	private Integer liveFlag; /* 是否属于直播标签，1:是；0：否*/
	private Integer videoFlag; /* 是否属于视频标签，1:是；0：否*/
	private Integer remoteFlag; /* 是否属于远程标签，1:是；0：否*/
	private String isCollect;/*判断该商品是否被用户收藏*/
	private Integer recommendFlag;  /* 推荐标记*/
	private Integer itemTag;
	private String tagName;
	private Integer buyNum;/*购买人数*/
	
	private Integer teacherId;
	
	private Integer bugFlag; /* 学员购买课程标记*/
	private Integer masterId; /* 订单id*/
	private SysConfigTeacher teachers; /*  老师对象 */
	
	private String cusorder;  /* 排序类型*/
	private String cuslimit; /* 查询条数*/

	public CommodityModelVo(){
		
	}
	
	public CommodityModelVo(Integer id, String name, String coverUrl,
			String overview, Double originalPrice, Double realPrice,
			String type, Integer itemOneId, Integer itemSecondId,
			Integer schoolId, Integer companyId, Integer creator,
			Date cerateTime, Integer updator, Date updateTime, String status,
			String classType, Integer classTypeId, Integer productType,
			Integer baseNum, Integer actualNum, Integer totalNum,
			Integer collectCount, String lableType, Integer faceFlag,
			Integer liveFlag, Integer videoFlag, Integer remoteFlag) {
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
		this.classTypeId = classTypeId;
		this.productType = productType;
		this.baseNum = baseNum;
		this.actualNum = actualNum;
		this.totalNum = totalNum;
		this.collectCount = collectCount;
		this.lableType = lableType;
		this.faceFlag = faceFlag;
		this.liveFlag = liveFlag;
		this.videoFlag = videoFlag;
		this.remoteFlag = remoteFlag;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCoverUrl() {
		return coverUrl;
	}
	public void setCoverUrl(String coverUrl) {
		this.coverUrl = coverUrl;
	}
	public String getOverview() {
		return overview;
	}
	public void setOverview(String overview) {
		this.overview = overview;
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

	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
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
	public Integer getSchoolId() {
		return schoolId;
	}
	public void setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
	}
	public Integer getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}
	public Integer getCreator() {
		return creator;
	}
	public void setCreator(Integer creator) {
		this.creator = creator;
	}
	public Date getCerateTime() {
		return cerateTime;
	}
	public void setCerateTime(Date cerateTime) {
		this.cerateTime = cerateTime;
	}
	public Integer getUpdator() {
		return updator;
	}
	public void setUpdator(Integer updator) {
		this.updator = updator;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getClassType() {
		return classType;
	}
	public void setClassType(String classType) {
		this.classType = classType;
	}
	public Integer getClassTypeId() {
		return classTypeId;
	}
	public void setClassTypeId(Integer classTypeId) {
		this.classTypeId = classTypeId;
	}
	public Integer getBaseNum() {
		return baseNum;
	}
	public void setBaseNum(Integer baseNum) {
		this.baseNum = baseNum;
	}
	public Integer getActualNum() {
		return actualNum;
	}
	public void setActualNum(Integer actualNum) {
		this.actualNum = actualNum;
	}
	public Integer getTotalNum() {
		return totalNum;
	}
	public void setTotalNum(Integer totalNum) {
		this.totalNum = totalNum;
	}
	public Integer getProductType() {
		return productType;
	}
	public void setProductType(Integer productType) {
		this.productType = productType;
	}

	public Integer getCollectCount() {
		return collectCount;
	}

	public void setCollectCount(Integer collectCount) {
		this.collectCount = collectCount;
	}


	public String getLableType() {
		return lableType;
	}

	public void setLableType(String lableType) {
		this.lableType = lableType;
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

	public Integer getComId() {
		return comId;
	}

	public void setComId(Integer comId) {
		this.comId = comId;
	}

	public Integer getCollectId() {
		return collectId;
	}

	public void setCollectId(Integer collectId) {
		this.collectId = collectId;
	}

	public String getCusorder() {
		return cusorder;
	}

	public void setCusorder(String cusorder) {
		this.cusorder = cusorder;
	}

	public String getCuslimit() {
		return cuslimit;
	}

	public void setCuslimit(String cuslimit) {
		this.cuslimit = cuslimit;
	}

	public String getIsCollect() {
		return isCollect;
	}

	public void setIsCollect(String isCollect) {
		this.isCollect = isCollect;
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

	public Integer getBuyNum() {
		return buyNum;
	}

	public void setBuyNum(Integer buyNum) {
		this.buyNum = buyNum;
	}

	public Integer getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(Integer teacherId) {
		this.teacherId = teacherId;
	}

	public Integer getBugFlag() {
		return bugFlag;
	}

	public void setBugFlag(Integer bugFlag) {
		this.bugFlag = bugFlag;
	}

	public Integer getMasterId() {
		return masterId;
	}

	public void setMasterId(Integer masterId) {
		this.masterId = masterId;
	}

	public SysConfigTeacher getTeachers() {
		return teachers;
	}

	public void setTeachers(SysConfigTeacher teachers) {
		this.teachers = teachers;
	}

	public Integer getRecommendFlag() {
		return recommendFlag;
	}

	public void setRecommendFlag(Integer recommendFlag) {
		this.recommendFlag = recommendFlag;
	}

}
