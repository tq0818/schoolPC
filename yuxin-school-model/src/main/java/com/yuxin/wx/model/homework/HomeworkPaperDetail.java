package com.yuxin.wx.model.homework;

import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:homeworkPaperDetail
 *
 * @author chopin
 * @date 2016-12-15
 */
@SuppressWarnings("serial")
public class HomeworkPaperDetail extends BaseEntity {

    private Integer paperId; /* 试卷id */
    private Integer topicId; /* 题id */
    private Integer score; /* 分数 */
    private Integer homeworkSId;/* 学员作业id */
    private Integer homeworkTid;/* 老师作业id */
    private Integer homeworkId;/* 作业id */
    private String content;

    // Constructor
    public HomeworkPaperDetail() {
    }

    public HomeworkPaperDetail(Integer paperId, Integer topicId, Integer score, Integer homeworkSId, Integer homeworkTid, Integer homeworkId, String content) {
        super();
        this.paperId = paperId;
        this.topicId = topicId;
        this.score = score;
        this.homeworkSId = homeworkSId;
        this.homeworkTid = homeworkTid;
        this.homeworkId = homeworkId;
        this.content = content;
    }

    public Integer getPaperId() {
        return this.paperId;
    }

    public void setPaperId(Integer paperId) {
        this.paperId = paperId;
    }

    public Integer getTopicId() {
        return this.topicId;
    }

    public void setTopicId(Integer topicId) {
        this.topicId = topicId;
    }

    public Integer getScore() {
        return this.score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getHomeworkSId() {
        return this.homeworkSId;
    }

    public void setHomeworkSId(Integer homeworkSId) {
        this.homeworkSId = homeworkSId;
    }

    public Integer getHomeworkTid() {
        return this.homeworkTid;
    }

    public void setHomeworkTid(Integer homeworkTid) {
        this.homeworkTid = homeworkTid;
    }

    public Integer getHomeworkId() {
        return this.homeworkId;
    }

    public void setHomeworkId(Integer homeworkId) {
        this.homeworkId = homeworkId;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "HomeworkPaperDetail [paperId=" + this.paperId + ", topicId=" + this.topicId + ", score=" + this.score + ", homeworkSId=" + this.homeworkSId
                + ", homeworkTid=" + this.homeworkTid + ", homeworkId=" + this.homeworkId + ", content=" + this.content + "]";
    }

}
