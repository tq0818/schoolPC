package com.yuxin.wx.model.system;

import com.yuxin.wx.common.BaseEntity;


public class SysKnowledgeTreeStatistics extends BaseEntity {
    private Integer userId;//用户ID
    private Integer knowledgeTreeId;//树节点ID
    private String  itemSecondCode;//年级
    private String  itemThreeCode;//学科
    private Integer commodityId;//商品ID
    private Integer classtypeId;//课程ID
    private Integer lessonId;//课次ID
    private Integer liveFlag;//直播观看状态 0：未看  1:30分钟  2:大于30分钟
    private Integer videoLectrueId;//录播ID
    private Integer videoFlag;//录播观看状态 0：未看  1:70%  2:大于70%
    private Integer videoLectrueWeikeId;//微课ID
    private Integer videoWeikeFlag;//微课观看状态 0：未看  1:80%  2:大于80%
    private Integer paperId;//试卷ID
    private Integer paperFlag;//试卷状态 0：未做  1：做了 2: 全错

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getKnowledgeTreeId() {
        return knowledgeTreeId;
    }

    public void setKnowledgeTreeId(Integer knowledgeTreeId) {
        this.knowledgeTreeId = knowledgeTreeId;
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

    public Integer getLiveFlag() {
        return liveFlag;
    }

    public void setLiveFlag(Integer liveFlag) {
        this.liveFlag = liveFlag;
    }

    public Integer getVideoLectrueId() {
        return videoLectrueId;
    }

    public void setVideoLectrueId(Integer videoLectrueId) {
        this.videoLectrueId = videoLectrueId;
    }

    public Integer getVideoFlag() {
        return videoFlag;
    }

    public void setVideoFlag(Integer videoFlag) {
        this.videoFlag = videoFlag;
    }

    public Integer getVideoLectrueWeikeId() {
        return videoLectrueWeikeId;
    }

    public void setVideoLectrueWeikeId(Integer videoLectrueWeikeId) {
        this.videoLectrueWeikeId = videoLectrueWeikeId;
    }

    public Integer getVideoWeikeFlag() {
        return videoWeikeFlag;
    }

    public void setVideoWeikeFlag(Integer videoWeikeFlag) {
        this.videoWeikeFlag = videoWeikeFlag;
    }

    public Integer getPaperId() {
        return paperId;
    }

    public void setPaperId(Integer paperId) {
        this.paperId = paperId;
    }

    public Integer getPaperFlag() {
        return paperFlag;
    }

    public void setPaperFlag(Integer paperFlag) {
        this.paperFlag = paperFlag;
    }
}
