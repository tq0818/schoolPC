package com.yuxin.wx.vo.commodity;

import java.util.Date;
import java.util.List;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.yuxin.wx.common.BaseEntity;
import com.yuxin.wx.util.ShortDateSerializer;

@SuppressWarnings("serial")
public class CommodityVo extends BaseEntity {

    private Integer comId;/* 商品ID */
    private Integer collectId;/* 收藏表里的ID */
    private String name; /* 商品名称 */
    private String coverUrl; /* 商品封面图url */
    private String overview; /* 商品概述 */
    private Double originalPrice; /* 商品原始价格 */
    private Double realPrice; /* 商品的真实价格（优惠后价格） */
    private String type; /* 商品类型：班型、书籍、组合商品，字典表数据 */
    private Integer itemOneId;
    private Integer itemSecondId;
    private Integer schoolId;
    private Integer companyId;
    private Integer creator;
    private Date cerateTime;
    private Integer updator;
    private Date updateTime;
    private String status; /* 商品上架状态（1：上架；0：未上架） */
    private String classType; /* 班级授课类型：直播、面授、视频 */
    private Integer classTypeId; /* 班型Id */
    private Integer productId; /* 产品类型 */
    private Integer productType; /* 产品类型 */
    private Integer baseNum; /* 售卖基数 */
    private Integer actualNum; /* 实际售卖数 */
    private Integer totalNum; /* 售卖总数 */
    private Integer collectCount;/* 被收藏次数 */
    private String lableType; /* 班级授课类型：直播、面授、视频 */
    private Integer faceFlag; /* 是否属于面授标签，1:是；0：否 */
    private Integer liveFlag; /* 是否属于直播标签，1:是；0：否 */
    private Integer videoFlag; /* 是否属于视频标签，1:是；0：否 */
    private Integer remoteFlag; /* 是否属于远程标签，1:是；0：否 */
    private Integer isMicroClass; /* 是否属于微课标签，1:是；0：否 */
    private String cusorder;
    private String cuslimit;
    private String isCollect;/* 判断该商品是否被用户收藏 */
    private String itemTag;
    private String itemTag2;
    private String tagName;
    private String teacherName;
    private Integer buyNum;/* 购买人数 */

    private Integer teacherId;

    private Integer bugFlag; /* 学员购买课程标记 */
    private Integer masterId; /* 订单id */

    private Integer mouduleNoId;
    private Date startDate;
    private String starttime;
    private String endtime;
    private Integer stauts;

    private Integer recommendFlag;/* 是否为推荐class_type */
    private List<Integer> comIds;/* 商品Id集合，问答前台相关课程专用 */

    private Integer integralFlag; /* 是否允许使用积分 */
    private Integer memberFlag; /* 是否允许使用会员 */
    private String schoolName;
    private String schoolIds;
    private Integer buyNumMax;
    private String isOver;

    private Integer protocolId;

    private String itemOneName;
    private String itemSecondName;
    private List<Integer> itemOneIds;
    private String itemOneIdStrs;//学科id字符串集合
    private String comIdStr;//商品id字符串集合
    
    private String orderBy;
    private String iconLable;
    private String schoolShortName;
    private String itemOneCode;
    private String itemSecondCode;
    private String itemThirdCode;
    private String itemFourthCode;

    private String lessonDate;
    private String lessonTimeStart;
    private String lessonTimeEnd;
    private String lessonName;
    private String  paperDescription;
    private Integer userId;

    private Date previewDate;//预览时间
    private String range;//范围

    private Integer specialOrder;//针对专题详情课程排序
    


    public Date getPreviewDate() {
		return previewDate;
	}

	public void setPreviewDate(Date previewDate) {
		this.previewDate = previewDate;
	}

	public String getSchoolShortName() {
		return schoolShortName;
	}

	public void setSchoolShortName(String schoolShortName) {
		this.schoolShortName = schoolShortName;
	}

	public CommodityVo() {

    }
    
	public String getComIdStr() {
		return comIdStr;
	}

	public void setComIdStr(String comIdStr) {
		this.comIdStr = comIdStr;
	}

	public String getItemOneIdStrs() {
		return itemOneIdStrs;
	}

	public void setItemOneIdStrs(String itemOneIdStrs) {
		this.itemOneIdStrs = itemOneIdStrs;
	}

	public List<Integer> getItemOneIds() {
		return itemOneIds;
	}



	public void setItemOneIds(List<Integer> itemOneIds) {
		this.itemOneIds = itemOneIds;
	}



	public CommodityVo(Integer id, String name, String coverUrl, String overview, Double originalPrice, Double realPrice, String type, Integer itemOneId,
            Integer itemSecondId, Integer schoolId, Integer companyId, Integer creator, Date cerateTime, Integer updator, Date updateTime, String status,
            String classType, Integer classTypeId, Integer productId, Integer productType, Integer baseNum, Integer actualNum, Integer totalNum,
            Integer collectCount, String lableType, Integer faceFlag, Integer liveFlag, Integer videoFlag, Integer remoteFlag) {
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
        this.productId = productId;
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
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCoverUrl() {
        return this.coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    public String getOverview() {
        return this.overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
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

    public void setRealPrice(Double realPrice) {
        this.realPrice = realPrice;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getItemOneId() {
        return this.itemOneId;
    }

    public void setItemOneId(Integer itemOneId) {
        this.itemOneId = itemOneId;
    }

    public Integer getItemSecondId() {
        return this.itemSecondId;
    }

    public void setItemSecondId(Integer itemSecondId) {
        this.itemSecondId = itemSecondId;
    }

    public Integer getSchoolId() {
        return this.schoolId;
    }

    public void setSchoolId(Integer schoolId) {
        this.schoolId = schoolId;
    }

    public Integer getCompanyId() {
        return this.companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public Integer getCreator() {
        return this.creator;
    }

    public void setCreator(Integer creator) {
        this.creator = creator;
    }

    public Date getCerateTime() {
        return this.cerateTime;
    }

    public void setCerateTime(Date cerateTime) {
        this.cerateTime = cerateTime;
    }

    public Integer getUpdator() {
        return this.updator;
    }

    public void setUpdator(Integer updator) {
        this.updator = updator;
    }

    public Date getUpdateTime() {
        return this.updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getClassType() {
        return this.classType;
    }

    public void setClassType(String classType) {
        this.classType = classType;
    }

    public Integer getClassTypeId() {
        return this.classTypeId;
    }

    public void setClassTypeId(Integer classTypeId) {
        this.classTypeId = classTypeId;
    }

    public Integer getBaseNum() {
        return this.baseNum;
    }

    public void setBaseNum(Integer baseNum) {
        this.baseNum = baseNum;
    }

    public Integer getActualNum() {
        return this.actualNum;
    }

    public void setActualNum(Integer actualNum) {
        this.actualNum = actualNum;
    }

    public Integer getTotalNum() {
        return this.totalNum;
    }

    public void setTotalNum(Integer totalNum) {
        this.totalNum = totalNum;
    }

    public Integer getProductId() {
        return this.productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getProductType() {
        return this.productType;
    }

    public void setProductType(Integer productType) {
        this.productType = productType;
    }

    public Integer getCollectCount() {
        return this.collectCount;
    }

    public void setCollectCount(Integer collectCount) {
        this.collectCount = collectCount;
    }

    public String getLableType() {
        return this.lableType;
    }

    public void setLableType(String lableType) {
        this.lableType = lableType;
    }

    public Integer getFaceFlag() {
        return this.faceFlag;
    }

    public void setFaceFlag(Integer faceFlag) {
        this.faceFlag = faceFlag;
    }

    public Integer getLiveFlag() {
        return this.liveFlag;
    }

    public void setLiveFlag(Integer liveFlag) {
        this.liveFlag = liveFlag;
    }

    public Integer getVideoFlag() {
        return this.videoFlag;
    }

    public void setVideoFlag(Integer videoFlag) {
        this.videoFlag = videoFlag;
    }

    public Integer getRemoteFlag() {
        return this.remoteFlag;
    }

    public void setRemoteFlag(Integer remoteFlag) {
        this.remoteFlag = remoteFlag;
    }

    public Integer getComId() {
        return this.comId;
    }

    public void setComId(Integer comId) {
        this.comId = comId;
    }

    public Integer getCollectId() {
        return this.collectId;
    }

    public void setCollectId(Integer collectId) {
        this.collectId = collectId;
    }

    public String getCusorder() {
        return this.cusorder;
    }

    public void setCusorder(String cusorder) {
        this.cusorder = cusorder;
    }

    public String getCuslimit() {
        return this.cuslimit;
    }

    public void setCuslimit(String cuslimit) {
        this.cuslimit = cuslimit;
    }

    public String getIsCollect() {
        return this.isCollect;
    }

    public void setIsCollect(String isCollect) {
        this.isCollect = isCollect;
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

    public String getTeacherName() {
        return this.teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public Integer getBuyNum() {
        return this.buyNum;
    }

    public void setBuyNum(Integer buyNum) {
        this.buyNum = buyNum;
    }

    public Integer getTeacherId() {
        return this.teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public Integer getBugFlag() {
        return this.bugFlag;
    }

    public void setBugFlag(Integer bugFlag) {
        this.bugFlag = bugFlag;
    }

    public Integer getMasterId() {
        return this.masterId;
    }

    public void setMasterId(Integer masterId) {
        this.masterId = masterId;
    }

    public Integer getMouduleNoId() {
        return this.mouduleNoId;
    }

    public void setMouduleNoId(Integer mouduleNoId) {
        this.mouduleNoId = mouduleNoId;
    }

    @JsonSerialize(using = ShortDateSerializer.class)
    public Date getStartDate() {
        return this.startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public String getStarttime() {
        return this.starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public String getEndtime() {
        return this.endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    public Integer getStauts() {
        return this.stauts;
    }

    public void setStauts(Integer stauts) {
        this.stauts = stauts;
    }

    public Integer getRecommendFlag() {
        return this.recommendFlag;
    }

    public void setRecommendFlag(Integer recommendFlag) {
        this.recommendFlag = recommendFlag;
    }

    public List<Integer> getComIds() {
        return this.comIds;
    }

    public void setComIds(List<Integer> comIds) {
        this.comIds = comIds;
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

    public String getSchoolName() {
        return this.schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getSchoolIds() {
        return this.schoolIds;
    }

    public void setSchoolIds(String schoolIds) {
        this.schoolIds = schoolIds;
    }

    public Integer getBuyNumMax() {
        return this.buyNumMax;
    }

    public void setBuyNumMax(Integer buyNumMax) {
        this.buyNumMax = buyNumMax;
    }

    public String getIsOver() {
        return this.isOver;
    }

    public void setIsOver(String isOver) {
        this.isOver = isOver;
    }

    public Integer getProtocolId() {
        return this.protocolId;
    }

    public void setProtocolId(Integer protocolId) {
        this.protocolId = protocolId;
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

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
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

    public String getLessonDate() {
        return lessonDate;
    }

    public void setLessonDate(String lessonDate) {
        this.lessonDate = lessonDate;
    }

    public String getLessonTimeStart() {
        return lessonTimeStart;
    }

    public void setLessonTimeStart(String lessonTimeStart) {
        this.lessonTimeStart = lessonTimeStart;
    }

    public String getLessonTimeEnd() {
        return lessonTimeEnd;
    }

    public void setLessonTimeEnd(String lessonTimeEnd) {
        this.lessonTimeEnd = lessonTimeEnd;
    }

    public String getLessonName() {
        return lessonName;
    }

    public void setLessonName(String lessonName) {
        this.lessonName = lessonName;
    }

    public Integer getIsMicroClass() {
        return isMicroClass;
    }

    public void setIsMicroClass(Integer isMicroClass) {
        this.isMicroClass = isMicroClass;
    }

    public String getPaperDescription() {
        return paperDescription;
    }

    public void setPaperDescription(String paperDescription) {
        this.paperDescription = paperDescription;
    }


    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getRange() {
        return range;
    }

    public void setRange(String range) {
        this.range = range;
    }

	public Integer getSpecialOrder() {
		return specialOrder;
	}

	public void setSpecialOrder(Integer specialOrder) {
		this.specialOrder = specialOrder;
	}


}
