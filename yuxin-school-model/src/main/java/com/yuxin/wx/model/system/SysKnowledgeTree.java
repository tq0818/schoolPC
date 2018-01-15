package com.yuxin.wx.model.system;

import com.yuxin.wx.common.BaseEntity;

import java.util.Date;
import java.util.List;

public class SysKnowledgeTree extends BaseEntity {
    private String eduYear;
    private String eduSeason;
    private String eduStep;
    private String itemSecondCode;
    private String itemThreeCode;
    private Integer commodityId;
    private Integer classtypeId;
    private Integer lessonId;
    private Integer commodityIdWeike;
    private Integer commodityIdHuikan;
    private Integer paperId;
    private Date createTime;
    private Integer creatorId;
    private String creator;
    private Integer companyId;

    public String getEduYear() {
        return eduYear;
    }

    public void setEduYear(String eduYear) {
        this.eduYear = eduYear;
    }

    public String getEduSeason() {
        return eduSeason;
    }

    public void setEduSeason(String eduSeason) {
        this.eduSeason = eduSeason;
    }

    public String getEduStep() {
        return eduStep;
    }

    public void setEduStep(String eduStep) {
        this.eduStep = eduStep;
    }

    public String getItemSecondCode() {
        return itemSecondCode;
    }

    public void setItemSecondCode(String itemSecondCode) {
        this.itemSecondCode = itemSecondCode;
    }

    public String getItemThreeCode() {
        return itemThreeCode;
    }

    public void setItemThreeCode(String itemThreeCode) {
        this.itemThreeCode = itemThreeCode;
    }

    public Integer getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(Integer commodityId) {
        this.commodityId = commodityId;
    }

    public Integer getClasstypeId() {
        return classtypeId;
    }

    public void setClasstypeId(Integer classtypeId) {
        this.classtypeId = classtypeId;
    }

    public Integer getLessonId() {
        return lessonId;
    }

    public void setLessonId(Integer lessonId) {
        this.lessonId = lessonId;
    }

    public Integer getCommodityIdWeike() {
        return commodityIdWeike;
    }

    public void setCommodityIdWeike(Integer commodityIdWeike) {
        this.commodityIdWeike = commodityIdWeike;
    }

    public Integer getCommodityIdHuikan() {
        return commodityIdHuikan;
    }

    public void setCommodityIdHuikan(Integer commodityIdHuikan) {
        this.commodityIdHuikan = commodityIdHuikan;
    }

    public Integer getPaperId() {
        return paperId;
    }

    public void setPaperId(Integer paperId) {
        this.paperId = paperId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Integer creatorId) {
        this.creatorId = creatorId;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }
}
