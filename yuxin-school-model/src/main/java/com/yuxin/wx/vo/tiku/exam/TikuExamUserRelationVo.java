package com.yuxin.wx.vo.tiku.exam;

import java.util.Date;

import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:TikuExamUserRelation
 *
 * @author wang.zx
 * @date 2016-2-17
 */
@SuppressWarnings("serial")
public class TikuExamUserRelationVo extends BaseEntity {

    private Integer tikuExamId; /* 考试表ID */
    private Integer tikuPaperId; /* 试卷ID */
    private Integer tikuUserExerciseId;/* tikuUserExercise表id */
    private Integer userId; /* 当前用户ID */
    private Integer status; /* 是否通过考试（0. 未通过 1. 通过） */
    private Double score; /* 用户考试得分 */
    private Double passScore; /* 本次考试通过的得分 */
    private Date createTime; /* 创建时间 */
    private Integer creator; /* 创建人 */
    private String tikuPaperName; /* 试卷名称 */
    private Integer classTypeId; /* 考试对应的班型ID */
    private String allowUserExam; /* 是否为学完之后才可以申请考试 */

    private Date startTime;// 开始日期范围
    private Date endTime;// 结束日期范围
    private String statusStr;// 值为：通过，未通过
    private String createTimeStr;// 考试时间string

    // 学员相关
    private String userName;/* 学员名称 */
    private String userMobile;/* 学员手机号 */
    private String paperName;/* 试卷名称 */

    private String sex;/* 性别 */
    private String addressDetail;/* 地址 */
    private String identityId;/* 证件号 */
    private String className;/* 所报课程名 */
    private String companyName;/* 单位名称 */
    private Integer stuId;/* 学生id */
    
    private String province; 
    private String city;
    private String county;

    // 考试对应试卷的相关信息
    private String totalScore; /* 总分 */
    private String examName; /* 考试名称 */
    private String examRange; /* 考试范围 */
    private String itemOneId; /* 一级学科 */
    private String itemSecondId; /* 二级学科 */
    private String introduction; /* 考试简介 */
    private Date examTime;/* 考试时间 */
    private String cover; /* 考试的封面 */
    private Integer examFlag; /* 当前用户是否参加过该考试 */

    // Constructor
    public TikuExamUserRelationVo() {
    }

    public TikuExamUserRelationVo(Integer tikuExamId, Integer tikuPaperId, Integer tikuUserExerciseId, Integer userId, Integer status, Double score,
            Double passScore, Date createTime, Integer creator, String tikuPaperName, Integer classTypeId, String allowUserExam, Date startTime, Date endTime,
            String statusStr, String createTimeStr, String userName, String userMobile, String paperName, String sex, String addressDetail, String identityId,
            String className, String companyName, Integer stuId, String totalScore, String examName, String examRange, String itemOneId, String itemSecondId,
            String introduction, Date examTime, String cover, Integer examFlag) {
        super();
        this.tikuExamId = tikuExamId;
        this.tikuPaperId = tikuPaperId;
        this.tikuUserExerciseId = tikuUserExerciseId;
        this.userId = userId;
        this.status = status;
        this.score = score;
        this.passScore = passScore;
        this.createTime = createTime;
        this.creator = creator;
        this.tikuPaperName = tikuPaperName;
        this.classTypeId = classTypeId;
        this.allowUserExam = allowUserExam;
        this.startTime = startTime;
        this.endTime = endTime;
        this.statusStr = statusStr;
        this.createTimeStr = createTimeStr;
        this.userName = userName;
        this.userMobile = userMobile;
        this.paperName = paperName;
        this.sex = sex;
        this.addressDetail = addressDetail;
        this.identityId = identityId;
        this.className = className;
        this.companyName = companyName;
        this.stuId = stuId;
        this.totalScore = totalScore;
        this.examName = examName;
        this.examRange = examRange;
        this.itemOneId = itemOneId;
        this.itemSecondId = itemSecondId;
        this.introduction = introduction;
        this.examTime = examTime;
        this.cover = cover;
        this.examFlag = examFlag;
    }

    public Integer getTikuExamId() {
        return this.tikuExamId;
    }

    public void setTikuExamId(Integer tikuExamId) {
        this.tikuExamId = tikuExamId;
    }

    public Integer getTikuPaperId() {
        return this.tikuPaperId;
    }

    public void setTikuPaperId(Integer tikuPaperId) {
        this.tikuPaperId = tikuPaperId;
    }

    public Integer getTikuUserExerciseId() {
        return this.tikuUserExerciseId;
    }

    public void setTikuUserExerciseId(Integer tikuUserExerciseId) {
        this.tikuUserExerciseId = tikuUserExerciseId;
    }

    public Integer getUserId() {
        return this.userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getStatus() {
        return this.status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Double getScore() {
        return this.score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public Double getPassScore() {
        return this.passScore;
    }

    public void setPassScore(Double passScore) {
        this.passScore = passScore;
    }

    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getCreator() {
        return this.creator;
    }

    public void setCreator(Integer creator) {
        this.creator = creator;
    }

    public String getTikuPaperName() {
        return this.tikuPaperName;
    }

    public void setTikuPaperName(String tikuPaperName) {
        this.tikuPaperName = tikuPaperName;
    }

    public Integer getClassTypeId() {
        return this.classTypeId;
    }

    public void setClassTypeId(Integer classTypeId) {
        this.classTypeId = classTypeId;
    }

    public String getAllowUserExam() {
        return this.allowUserExam;
    }

    public void setAllowUserExam(String allowUserExam) {
        this.allowUserExam = allowUserExam;
    }

    public Date getStartTime() {
        return this.startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return this.endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getStatusStr() {
        return this.statusStr;
    }

    public void setStatusStr(String statusStr) {
        this.statusStr = statusStr;
    }

    public String getCreateTimeStr() {
        return this.createTimeStr;
    }

    public void setCreateTimeStr(String createTimeStr) {
        this.createTimeStr = createTimeStr;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserMobile() {
        return this.userMobile;
    }

    public void setUserMobile(String userMobile) {
        this.userMobile = userMobile;
    }

    public String getPaperName() {
        return this.paperName;
    }

    public void setPaperName(String paperName) {
        this.paperName = paperName;
    }

    public String getSex() {
        return this.sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAddressDetail() {
        return this.addressDetail;
    }

    public void setAddressDetail(String addressDetail) {
        this.addressDetail = addressDetail;
    }

    public String getIdentityId() {
        return this.identityId;
    }

    public void setIdentityId(String identityId) {
        this.identityId = identityId;
    }

    public String getClassName() {
        return this.className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getCompanyName() {
        return this.companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Integer getStuId() {
        return this.stuId;
    }

    public void setStuId(Integer stuId) {
        this.stuId = stuId;
    }

    public String getTotalScore() {
        return this.totalScore;
    }

    public void setTotalScore(String totalScore) {
        this.totalScore = totalScore;
    }

    public String getExamName() {
        return this.examName;
    }

    public void setExamName(String examName) {
        this.examName = examName;
    }

    public String getExamRange() {
        return this.examRange;
    }

    public void setExamRange(String examRange) {
        this.examRange = examRange;
    }

    public String getItemOneId() {
        return this.itemOneId;
    }

    public void setItemOneId(String itemOneId) {
        this.itemOneId = itemOneId;
    }

    public String getItemSecondId() {
        return this.itemSecondId;
    }

    public void setItemSecondId(String itemSecondId) {
        this.itemSecondId = itemSecondId;
    }

    public String getIntroduction() {
        return this.introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public Date getExamTime() {
        return this.examTime;
    }

    public void setExamTime(Date examTime) {
        this.examTime = examTime;
    }

    public String getCover() {
        return this.cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public Integer getExamFlag() {
        return this.examFlag;
    }

    public void setExamFlag(Integer examFlag) {
        this.examFlag = examFlag;
    }

    @Override
    public String toString() {
        return "TikuExamUserRelationVo [tikuExamId=" + this.tikuExamId + ", tikuPaperId=" + this.tikuPaperId + ", tikuUserExerciseId=" + this.tikuUserExerciseId
                + ", userId=" + this.userId + ", status=" + this.status + ", score=" + this.score + ", passScore=" + this.passScore + ", createTime="
                + this.createTime + ", creator=" + this.creator + ", tikuPaperName=" + this.tikuPaperName + ", classTypeId=" + this.classTypeId
                + ", allowUserExam=" + this.allowUserExam + ", startTime=" + this.startTime + ", endTime=" + this.endTime + ", statusStr=" + this.statusStr
                + ", createTimeStr=" + this.createTimeStr + ", userName=" + this.userName + ", userMobile=" + this.userMobile + ", paperName=" + this.paperName
                + ", sex=" + this.sex + ", addressDetail=" + this.addressDetail + ", identityId=" + this.identityId + ", className=" + this.className
                + ", companyName=" + this.companyName + ", stuId=" + this.stuId + ", totalScore=" + this.totalScore + ", examName=" + this.examName
                + ", examRange=" + this.examRange + ", itemOneId=" + this.itemOneId + ", itemSecondId=" + this.itemSecondId + ", introduction="
                + this.introduction + ", examTime=" + this.examTime + ", cover=" + this.cover + ", examFlag=" + this.examFlag + "]";
    }

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

}
