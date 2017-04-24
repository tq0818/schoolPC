package com.yuxin.wx.vo.classes;

import java.util.Date;

import com.yuxin.wx.common.BaseEntity;

public class ClasspackageVo extends BaseEntity {
    private static final long serialVersionUID = 1L;
    private String name; /* 名称 */
    private Double originalPrice; /* 原价 */
    private Double realPrice; /* 现价 */
    private Integer baseNum; /* 购买基数 */
    private String description; /* 描述 */
    private String categoryCode; /* 分类编码 */
    private String publishStatus; /* 发布状态,字典 */
    private Date publishTime; /* 发布时间 */
    private Integer companyId; /* 公司ID */
    private Integer schoolId; /* 分校ID */
    private Integer creator; /* 创建人 */
    private Date createTime; /* 创建时间 */
    private Date updateTime; /* 更新时间 */
    private Integer updator; /* 更新人 */
    private Integer delFlag; /* 删除标记 */
    private String cover; /* 版型封面，图片url地址 */
    private Integer recommendFlag;/* 是否推荐 */

    private Integer comId; // 商品id
    private Integer payCount; // 实际学习人数
    private Integer stageCount; // 阶段总数
    private Integer classTypeCount; // 课程总数

    private String orderBy; // 排序类型

    private Integer userId;// 用户id
    private Integer stuId;// 学生id

    private Integer colId;// 收藏id

    private String categoryName;

    private String introduce;

    private Integer totalCount; // 招生人数
    private Integer protocolId; // 协议id

    public Integer getTotalCount() {
        return this.totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public String getIntroduce() {
        return this.introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public Integer getUserId() {
        return this.userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getColId() {
        return this.colId;
    }

    public void setColId(Integer colId) {
        this.colId = colId;
    }

    public Integer getStuId() {
        return this.stuId;
    }

    public void setStuId(Integer stuId) {
        this.stuId = stuId;
    }

    public Integer getComId() {
        return this.comId;
    }

    public void setComId(Integer comId) {
        this.comId = comId;
    }

    public String getOrderBy() {
        return this.orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public Integer getRecommendFlag() {
        return this.recommendFlag;
    }

    public void setRecommendFlag(Integer recommendFlag) {
        this.recommendFlag = recommendFlag;
    }

    public Integer getStageCount() {
        return this.stageCount;
    }

    public void setStageCount(Integer stageCount) {
        this.stageCount = stageCount;
    }

    public Integer getClassTypeCount() {
        return this.classTypeCount;
    }

    public void setClassTypeCount(Integer classTypeCount) {
        this.classTypeCount = classTypeCount;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Integer getBaseNum() {
        return this.baseNum;
    }

    public void setBaseNum(Integer baseNum) {
        this.baseNum = baseNum;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategoryCode() {
        return this.categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

    public String getPublishStatus() {
        return this.publishStatus;
    }

    public void setPublishStatus(String publishStatus) {
        this.publishStatus = publishStatus;
    }

    public Date getPublishTime() {
        return this.publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    public Integer getCompanyId() {
        return this.companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public Integer getSchoolId() {
        return this.schoolId;
    }

    public void setSchoolId(Integer schoolId) {
        this.schoolId = schoolId;
    }

    public Integer getCreator() {
        return this.creator;
    }

    public void setCreator(Integer creator) {
        this.creator = creator;
    }

    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return this.updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getUpdator() {
        return this.updator;
    }

    public void setUpdator(Integer updator) {
        this.updator = updator;
    }

    public Integer getDelFlag() {
        return this.delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    public String getCover() {
        return this.cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public Integer getPayCount() {
        return this.payCount;
    }

    public void setPayCount(Integer payCount) {
        this.payCount = payCount;
    }

    public String getCategoryName() {
        return this.categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Integer getProtocolId() {
        return this.protocolId;
    }

    public void setProtocolId(Integer protocolId) {
        this.protocolId = protocolId;
    }

}
