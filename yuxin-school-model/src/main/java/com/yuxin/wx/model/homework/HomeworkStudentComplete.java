package com.yuxin.wx.model.homework;

import java.util.Date;

import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:HomeworkStudentComplete
 *
 * @author chopin
 * @date 2016-12-15
 */
@SuppressWarnings("serial")
public class HomeworkStudentComplete extends BaseEntity {

    private Integer homeworkId; /* 作业ID */
    private Integer stuId;
    private String content; /* 回答 */
    private Integer status; /* 状态 0-未提交 1-已提交带批阅 2-待重新提交作业 3-已批阅 */
    private String resourceId; /* 附件路径 */
    private Integer paperId; /* 试卷id */
    private Integer completeFlag; /* 试卷作业完成标记 0-未完成 1-完成 */
    private Date completeTime; /* 完成时间 */
    private Integer companyId;
    private Integer exerciseId;// 练习id

    // Constructor
    public HomeworkStudentComplete() {
    }

    public HomeworkStudentComplete(Integer homeworkId, Integer stuId, String content, Integer status, String resourceId, Integer paperId, Integer completeFlag,
            Date completeTime, Integer companyId, Integer exerciseId) {
        super();
        this.homeworkId = homeworkId;
        this.stuId = stuId;
        this.content = content;
        this.status = status;
        this.resourceId = resourceId;
        this.paperId = paperId;
        this.completeFlag = completeFlag;
        this.completeTime = completeTime;
        this.companyId = companyId;
        this.exerciseId = exerciseId;
    }

    public Integer getHomeworkId() {
        return this.homeworkId;
    }

    public void setHomeworkId(Integer homeworkId) {
        this.homeworkId = homeworkId;
    }

    public Integer getStuId() {
        return this.stuId;
    }

    public void setStuId(Integer stuId) {
        this.stuId = stuId;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getStatus() {
        return this.status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getResourceId() {
        return this.resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public Integer getPaperId() {
        return this.paperId;
    }

    public void setPaperId(Integer paperId) {
        this.paperId = paperId;
    }

    public Integer getCompleteFlag() {
        return this.completeFlag;
    }

    public void setCompleteFlag(Integer completeFlag) {
        this.completeFlag = completeFlag;
    }

    public Date getCompleteTime() {
        return this.completeTime;
    }

    public void setCompleteTime(Date completeTime) {
        this.completeTime = completeTime;
    }

    public Integer getCompanyId() {
        return this.companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public Integer getExerciseId() {
        return this.exerciseId;
    }

    public void setExerciseId(Integer exerciseId) {
        this.exerciseId = exerciseId;
    }

    @Override
    public String toString() {
        return "HomeworkStudentComplete [homeworkId=" + this.homeworkId + ", stuId=" + this.stuId + ", content=" + this.content + ", status=" + this.status
                + ", resourceId=" + this.resourceId + ", paperId=" + this.paperId + ", completeFlag=" + this.completeFlag + ", completeTime="
                + this.completeTime + ", companyId=" + this.companyId + ", exerciseId=" + this.exerciseId + "]";
    }

}
