package com.yuxin.wx.model.tiku;

import java.util.Date;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import com.yuxin.wx.util.ShortDateSerializer;

import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:TikuPaper
 * 
 * @author wang.zx
 * @date 2015-7-8
 */
@SuppressWarnings("serial")
public class TikuPaper extends BaseEntity {
	
	
	private String	paperName;		 /* 试卷名称 */ 
	private String	paperType;		 /* 试卷类型，模拟题和真题，字典表（TIKU_PAPER_TYPE） */ 
	private Integer	examTime;		 /* 答题时间，单位分钟 */ 
	private Double	totalScore;		 /* 试卷总分 */ 
	private String	region;		 /* 所属地域 */ 
	private String	containTopicType;		 /* 包含的试题类型，用逗号分隔 */ 
	private String	paperStatus;		 /* 试卷状态，字典表（TIKU_PAPER_STATUS） */ 
	private Integer	tikuCategoryId;		 /* 题库分类id */ 
	private Integer	tkuSubjectId;		 /* 题库科目id */ 
	private Integer	companyId;		
	private Integer	totalTopicNum;		 /* 总共包含的题数 */ 
	private Integer	creator;		
	private Date	createTime;		
	private Integer	updator;		
	private Date	updateTime;		
	private Integer	auditor;		 /* 审核人员id */ 
	private Date	auditTime;		 /* 审核时间 */ 
	
	private String auditorName;
	
	private String creatorName;

	private Integer teacherId;

	private Integer doExam;
	// Constructor
	public TikuPaper() {
	}

	public Integer getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(Integer teacherId) {
		this.teacherId = teacherId;
	}

	/**
	 * full Constructor
	 */
	public TikuPaper(Integer id, String paperName, String paperType, Integer examTime, Double totalScore, String region, String containTopicType, String paperStatus, Integer tikuCategoryId, Integer tkuSubjectId, Integer companyId, Integer totalTopicNum, Integer creator, Date createTime, Integer updator, Date updateTime, Integer auditor, Date auditTime) {
		setId(id);
		this.paperName = paperName;
		this.paperType = paperType;
		this.examTime = examTime;
		this.totalScore = totalScore;
		this.region = region;
		this.containTopicType = containTopicType;
		this.paperStatus = paperStatus;
		this.tikuCategoryId = tikuCategoryId;
		this.tkuSubjectId = tkuSubjectId;
		this.companyId = companyId;
		this.totalTopicNum = totalTopicNum;
		this.creator = creator;
		this.createTime = createTime;
		this.updator = updator;
		this.updateTime = updateTime;
		this.auditor = auditor;
		this.auditTime = auditTime;
	}

	// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为TikuPaper可以实现连缀设置属性
	
	public String getPaperName() {
		return paperName;
	}

	public TikuPaper setPaperName(String paperName) {
		this.paperName = paperName;
		return this;
	}
	
	
	public String getPaperType() {
		return paperType;
	}

	public TikuPaper setPaperType(String paperType) {
		this.paperType = paperType;
		return this;
	}
	
	
	public Integer getExamTime() {
		return examTime;
	}

	public TikuPaper setExamTime(Integer examTime) {
		this.examTime = examTime;
		return this;
	}
	
	
	public Double getTotalScore() {
		return totalScore;
	}

	public TikuPaper setTotalScore(Double totalScore) {
		this.totalScore = totalScore;
		return this;
	}
	
	
	public String getRegion() {
		return region;
	}

	public TikuPaper setRegion(String region) {
		this.region = region;
		return this;
	}
	
	
	public String getContainTopicType() {
		return containTopicType;
	}

	public TikuPaper setContainTopicType(String containTopicType) {
		this.containTopicType = containTopicType;
		return this;
	}
	
	
	public String getPaperStatus() {
		return paperStatus;
	}

	public TikuPaper setPaperStatus(String paperStatus) {
		this.paperStatus = paperStatus;
		return this;
	}
	
	
	public Integer getTikuCategoryId() {
		return tikuCategoryId;
	}

	public TikuPaper setTikuCategoryId(Integer tikuCategoryId) {
		this.tikuCategoryId = tikuCategoryId;
		return this;
	}
	
	
	public Integer getTkuSubjectId() {
		return tkuSubjectId;
	}

	public TikuPaper setTkuSubjectId(Integer tkuSubjectId) {
		this.tkuSubjectId = tkuSubjectId;
		return this;
	}
	
	
	public Integer getCompanyId() {
		return companyId;
	}

	public TikuPaper setCompanyId(Integer companyId) {
		this.companyId = companyId;
		return this;
	}
	
	
	public Integer getTotalTopicNum() {
		return totalTopicNum;
	}

	public TikuPaper setTotalTopicNum(Integer totalTopicNum) {
		this.totalTopicNum = totalTopicNum;
		return this;
	}
	
	
	public Integer getCreator() {
		return creator;
	}

	public TikuPaper setCreator(Integer creator) {
		this.creator = creator;
		return this;
	}
	
	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getCreateTime() {
		return createTime;
	}

	public TikuPaper setCreateTime(Date createTime) {
		this.createTime = createTime;
		return this;
	}
	
	
	public Integer getUpdator() {
		return updator;
	}

	public TikuPaper setUpdator(Integer updator) {
		this.updator = updator;
		return this;
	}
	
	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getUpdateTime() {
		return updateTime;
	}

	public TikuPaper setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
		return this;
	}
	
	
	public Integer getAuditor() {
		return auditor;
	}

	public TikuPaper setAuditor(Integer auditor) {
		this.auditor = auditor;
		return this;
	}
	
	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getAuditTime() {
		return auditTime;
	}

	public TikuPaper setAuditTime(Date auditTime) {
		this.auditTime = auditTime;
		return this;
	}
	
	@Override
	public String toString() {
		return "TikuPaper [" + "id=" + getId() + ", paperName=" + paperName + ", paperType=" + paperType + ", examTime=" + examTime + ", totalScore=" + totalScore + ", region=" + region + ", containTopicType=" + containTopicType + ", paperStatus=" + paperStatus + ", tikuCategoryId=" + tikuCategoryId + ", tkuSubjectId=" + tkuSubjectId + ", companyId=" + companyId + ", totalTopicNum=" + totalTopicNum + ", creator=" + creator + ", createTime=" + createTime + ", updator=" + updator + ", updateTime=" + updateTime + ", auditor=" + auditor + ", auditTime=" + auditTime +  "]";
	}

	public String getAuditorName() {
		return auditorName;
	}

	public void setAuditorName(String auditorName) {
		this.auditorName = auditorName;
	}

	public String getCreatorName() {
		return creatorName;
	}

	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}

	public Integer getDoExam() {
		return doExam;
	}

	public void setDoExam(Integer doExam) {
		this.doExam = doExam;
	}
}
