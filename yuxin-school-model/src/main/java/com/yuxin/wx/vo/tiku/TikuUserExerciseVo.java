package com.yuxin.wx.vo.tiku;

import java.util.Date;

import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:TikuUserExerciseVo
 *
 * @author wang.zx
 * @date 2015-8-24
 */
@SuppressWarnings("serial")
public class TikuUserExerciseVo extends BaseEntity {

    private Integer exerciseId; /* 练习的批次id，paper表id或者batch表id */
    private String userPaperName; /* 用户生成试卷名称 */
    private Integer totalTopic; /* 用户试卷总题数 */
    private Integer doneTopic; /* 用户对该试卷做过多少道题 */
    private Integer currentNum; /* 当前做到第几题 */
    private Date startTime;/* 开始时间 */
    private String exerciseType;/* 做题类型 */

    // 用户导出试卷做题记录
    private String name;// 学生名
    private String userName;// 用户名
    private String mobile;
    private Integer exerciseScore;

    // Constructor
    public TikuUserExerciseVo() {
    }

    /**
     * full Constructor
     */
    public TikuUserExerciseVo(Integer id, Integer exerciseId, String userPaperName, Integer totalTopic, Integer doneTopic) {
        this.setId(id);
        this.exerciseId = exerciseId;
        this.userPaperName = userPaperName;
        this.totalTopic = totalTopic;
        this.doneTopic = doneTopic;
    }

    // getter && setter
    // 在setter方法最后加上"return this;"并把返回参数改为TikuUserExercise可以实现连缀设置属性

    public Integer getExerciseId() {
        return this.exerciseId;
    }

    public TikuUserExerciseVo setExerciseId(Integer exerciseId) {
        this.exerciseId = exerciseId;
        return this;
    }

    public String getUserPaperName() {
        return this.userPaperName;
    }

    public void setUserPaperName(String userPaperName) {
        this.userPaperName = userPaperName;
    }

    public Integer getTotalTopic() {
        return this.totalTopic;
    }

    public void setTotalTopic(Integer totalTopic) {
        this.totalTopic = totalTopic;
    }

    public Integer getDoneTopic() {
        return this.doneTopic;
    }

    public void setDoneTopic(Integer doneTopic) {
        this.doneTopic = doneTopic;
    }

    @Override
    public String toString() {
        return "TikuUserExercise [" + "id=" + this.getId() + "exerciseId=" + this.exerciseId + "userPaperName=" + this.userPaperName + "totalTopic="
                + this.totalTopic + "doneTopic=" + this.doneTopic + "]";
    }

    public Integer getCurrentNum() {
        return this.currentNum;
    }

    public void setCurrentNum(Integer currentNum) {
        this.currentNum = currentNum;
    }

    public Date getStartTime() {
        return this.startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public String getExerciseType() {
        return this.exerciseType;
    }

    public void setExerciseType(String exerciseType) {
        this.exerciseType = exerciseType;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMobile() {
        return this.mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Integer getExerciseScore() {
        return this.exerciseScore;
    }

    public void setExerciseScore(Integer exerciseScore) {
        this.exerciseScore = exerciseScore;
    }
}
