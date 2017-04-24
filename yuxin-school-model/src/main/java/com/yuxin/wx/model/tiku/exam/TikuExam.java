package com.yuxin.wx.model.tiku.exam;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.yuxin.wx.common.BaseEntity;
import com.yuxin.wx.util.ShortDateSerializer;

/**
 * POJO:TikuExam
 * 
 * @author wang.zx
 * @date 2016-2-17
 */
@SuppressWarnings("serial")
public class TikuExam extends BaseEntity {

    private String examName; /* 考试名称 */
    private String introduction; /* 考试简介（说明） */
    private String introductionStr; /* 截取后的考试简介 */
    private Integer examRange; /* 考试范围(1: 学科级别 2: 学科小类级别 3: 课程级别) */
    private Integer itemOneId; /* 学科ID */
    private Integer itemSecondId; /* 学科小类ID */
    private Integer classTypeId; /* 课程ID */
    private String cover; /* 考试封面URL */
    private String examType; /* 考试类型（详情见字典表，证书类考试等...） */
    private Integer examMode; /* 考试方式（1. 自由考试 2. 集中考试） */
    private Date beginTime; /* 考试开始时间 */
    private Date endTime; /* 考试结束时间 */
    private Integer examCount; /* 可允许考试的次数 */
    private Integer passScore; /* 考试通过分数 */
    private Integer status; /* 考试状态（1. 编辑 2. 发布成功 3. 删除） */
    private Date createTime; /* 创建时间 */
    private Date updateTime; /* 修改时间 */
    private Integer creator; /* 创建人 */
    private Integer updator; /* 修改人 */
    private Integer companyId; /* 公司id */
    private Integer schoolId; /* 分校id */
    private String creatorName; /* 创建人名字 */
    private String updatorName; /* 修改人名字 */

    private String successWord; /* 通过考试提示语 */
    private String failWord; /* 未通过考试提示语 */
    private String scanAnalysis; /* 是否允许查看解析 */
    private String allowUserExam; /* 用户考试资格 */

    // Constructor
    public TikuExam() {
    }

    /**
     * full Constructor
     */
    public TikuExam(Integer id, String examName, String introduction, Integer examRange, Integer itemOneId, Integer itemSecondId, Integer classTypeId,
            String cover, String examType, Integer examMode, Date beginTime, Date endTime, Integer examCount, Integer passScore, Integer status,
            Date createTime, Integer creator) {
        setId(id);
        this.examName = examName;
        this.introduction = introduction;
        this.examRange = examRange;
        this.itemOneId = itemOneId;
        this.itemSecondId = itemSecondId;
        this.classTypeId = classTypeId;
        this.cover = cover;
        this.examType = examType;
        this.examMode = examMode;
        this.beginTime = beginTime;
        this.endTime = endTime;
        this.examCount = examCount;
        this.passScore = passScore;
        this.status = status;
        this.createTime = createTime;
        this.creator = creator;
    }

    // getter && setter
    // 在setter方法最后加上"return this;"并把返回参数改为TikuExam可以实现连缀设置属性

    public String getExamName() {
        return examName;
    }

    public TikuExam setExamName(String examName) {
        this.examName = examName;
        return this;
    }

    public String getIntroduction() {
        return introduction;
    }

    public TikuExam setIntroduction(String introduction) {
        this.introduction = introduction;
        return this;
    }

    public Integer getExamRange() {
        return examRange;
    }

    public TikuExam setExamRange(Integer examRange) {
        this.examRange = examRange;
        return this;
    }

    public Integer getItemOneId() {
        return itemOneId;
    }

    public TikuExam setItemOneId(Integer itemOneId) {
        this.itemOneId = itemOneId;
        return this;
    }

    public Integer getItemSecondId() {
        return itemSecondId;
    }

    public TikuExam setItemSecondId(Integer itemSecondId) {
        this.itemSecondId = itemSecondId;
        return this;
    }

    public Integer getClassTypeId() {
        return classTypeId;
    }

    public TikuExam setClassTypeId(Integer classTypeId) {
        this.classTypeId = classTypeId;
        return this;
    }

    public String getCover() {
        return cover;
    }

    public TikuExam setCover(String cover) {
        this.cover = cover;
        return this;
    }

    public String getExamType() {
        return examType;
    }

    public TikuExam setExamType(String examType) {
        this.examType = examType;
        return this;
    }

    public Integer getExamMode() {
        return examMode;
    }

    public TikuExam setExamMode(Integer examMode) {
        this.examMode = examMode;
        return this;
    }

    @JsonSerialize(using = ShortDateSerializer.class)
    public Date getBeginTime() {
        return beginTime;
    }

    public TikuExam setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
        return this;
    }

    @JsonSerialize(using = ShortDateSerializer.class)
    public Date getEndTime() {
        return endTime;
    }

    public TikuExam setEndTime(Date endTime) {
        this.endTime = endTime;
        return this;
    }

    public Integer getExamCount() {
        return examCount;
    }

    public TikuExam setExamCount(Integer examCount) {
        this.examCount = examCount;
        return this;
    }

    public Integer getPassScore() {
        return passScore;
    }

    public TikuExam setPassScore(Integer passScore) {
        this.passScore = passScore;
        return this;
    }

    public Integer getStatus() {
        return status;
    }

    public TikuExam setStatus(Integer status) {
        this.status = status;
        return this;
    }

    @JsonSerialize(using = ShortDateSerializer.class)
    public Date getCreateTime() {
        return createTime;
    }

    public TikuExam setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public Integer getCreator() {
        return creator;
    }

    public TikuExam setCreator(Integer creator) {
        this.creator = creator;
        return this;
    }

    @Override
    public String toString() {
        return "TikuExam [" + "id=" + getId() + ", examName=" + examName + ", introduction=" + introduction + ", examRange=" + examRange + ", itemOneId="
                + itemOneId + ", itemSecondId=" + itemSecondId + ", classTypeId=" + classTypeId + ", cover=" + cover + ", examType=" + examType + ", examMode="
                + examMode + ", beginTime=" + beginTime + ", endTime=" + endTime + ", examCount=" + examCount + ", passScore=" + passScore + ", status="
                + status + ", createTime=" + createTime + ", creator=" + creator + "]";
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public Integer getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Integer schoolId) {
        this.schoolId = schoolId;
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

    public String getUpdatorName() {
        return updatorName;
    }

    public void setUpdatorName(String updatorName) {
        this.updatorName = updatorName;
    }

    public String getIntroductionStr() {
        if (introduction == null) {
            System.out.println("<name>" + examName + ":id:不清楚id为啥为空" + id);
            return introductionStr = "";
        } else if (introduction.length() > 100) {
            introductionStr = introduction.substring(0, 100) + "......";
        } else {
            introductionStr = introduction;
        }
        return introductionStr;
    }

    public void setIntroductionStr(String introductionStr) {
        this.introductionStr = introductionStr;
    }

    public String getSuccessWord() {
        return successWord;
    }

    public void setSuccessWord(String successWord) {
        this.successWord = successWord;
    }

    public String getFailWord() {
        return failWord;
    }

    public void setFailWord(String failWord) {
        this.failWord = failWord;
    }

    public String getScanAnalysis() {
        return scanAnalysis;
    }

    public void setScanAnalysis(String scanAnalysis) {
        this.scanAnalysis = scanAnalysis;
    }

    public String getAllowUserExam() {
        return allowUserExam;
    }

    public void setAllowUserExam(String allowUserExam) {
        this.allowUserExam = allowUserExam;
    }
}
