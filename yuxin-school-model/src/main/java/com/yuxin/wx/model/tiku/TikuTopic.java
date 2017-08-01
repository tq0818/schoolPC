package com.yuxin.wx.model.tiku;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.yuxin.wx.util.ShortDateSerializer;
import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:TikuTopic
 * 
 * @author wang.zx
 * @date 2015-7-8
 */
@SuppressWarnings("serial")
public class TikuTopic extends BaseEntity {
	
	
	private String	topicName;		 /* 题目名称 */ 
	private String  topicNameBlank;  /* 填空题目名称，存放原始的内容 */
	private String	topicType;		 /* 题目类型，见字典表（TIKU_TOPIC_TYPE） */ 
	private Double	score;		 /* 分数 */ 
	private String	answer;		 /* 正确答案：选择存ABCD；判断存正确错误；填空存所有答案合起来字符串，用$$f分隔；简答题存答案 */ 
	private String	difficulty;		 /* 难度，字典表数据 */ 
	private String	analyseWord;		 /* 文字解析 */ 
	private String	analyseVideoUrl;		 /* 视频解析地址 */ 
	private String	analyseAudioUrl;		 /* 语音解析地址 */ 
	private Integer	parentId;		 /* 父节点id，材料题的子题存材料题的id */ 
	private String	status;		 /* 试题状态，字典表数据 */ 
	private Integer	tikuSubjectId;		 /* 所属科目id */ 
	private Integer	tikuCategoryId;		 /* 所属题库类别id */ 
	private Integer	companyId;		
	private Integer	creator;		
	private String	creatorName;	/* 姓名*/
	private Date	createTime;		/* 创建时间*/
	private Integer	updator;		
	private String	updatorName;
	private Date	updateTime;		
	private Integer	auditor;	
	private String	auditorName;
	private Date	auditTime;		
	private Integer paperFlag;		/* 是否属于试卷标记，针对在试卷里增加材料子题，1是，0否 */
	private Integer childFlag;		/* 是否为材料题的子题：1是，0否 */
	private Integer correctFlag;	/* 标记对错*/
	private List<TikuTopic> topicList; /* 子题*/

	private List<TikuTopicOption> optionList = new ArrayList<TikuTopicOption>();//单选，多选，判断，不定向试题 的主题 
	
	
	public List<TikuTopicOption> getOptionList() {
		return optionList;
	}

	public void setOptionList(List<TikuTopicOption> optionList) {
		this.optionList = optionList;
	}

	// Constructor
	public TikuTopic() {
	}
	
	/**
	 * full Constructor
	 */
	public TikuTopic(Integer id, String topicName, String topicType, Double score, String answer, String difficulty, String analyseWord, String analyseVideoUrl, String analyseAudioUrl, Integer parentId, String status, Integer tikuSubjectId, Integer tikuCategoryId, Integer companyId, 
			Integer creator, Date createTime, Integer updator, Date updateTime, Integer auditor, Date auditTime, Integer paperFlag, Integer childFlag) {
		setId(id);
		this.topicName = topicName;
		this.topicType = topicType;
		this.score = score;
		this.answer = answer;
		this.difficulty = difficulty;
		this.analyseWord = analyseWord;
		this.analyseVideoUrl = analyseVideoUrl;
		this.analyseAudioUrl = analyseAudioUrl;
		this.parentId = parentId;
		this.status = status;
		this.tikuSubjectId = tikuSubjectId;
		this.tikuCategoryId = tikuCategoryId;
		this.companyId = companyId;
		this.creator = creator;
		this.createTime = createTime;
		this.updator = updator;
		this.updateTime = updateTime;
		this.auditor = auditor;
		this.auditTime = auditTime;
		this.paperFlag = paperFlag;
		this.childFlag = childFlag;
	}

	// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为TikuTopic可以实现连缀设置属性
	
	public String getTopicName() {
		return topicName;
	}

	public TikuTopic setTopicName(String topicName) {
		this.topicName = topicName;
		return this;
	}
	
	
	public String getTopicType() {
		return topicType;
	}

	public TikuTopic setTopicType(String topicType) {
		this.topicType = topicType;
		return this;
	}
	
	
	public Double getScore() {
		return score;
	}

	public TikuTopic setScore(Double score) {
		this.score = score;
		return this;
	}
	
	
	public String getAnswer() {
		return answer;
	}

	public TikuTopic setAnswer(String answer) {
		this.answer = answer;
		return this;
	}
	
	
	public String getDifficulty() {
		return difficulty;
	}

	public TikuTopic setDifficulty(String difficulty) {
		this.difficulty = difficulty;
		return this;
	}
	
	
	public String getAnalyseWord() {
		return analyseWord;
	}

	public TikuTopic setAnalyseWord(String analyseWord) {
		this.analyseWord = analyseWord;
		return this;
	}
	
	
	public String getAnalyseVideoUrl() {
		return analyseVideoUrl;
	}

	public TikuTopic setAnalyseVideoUrl(String analyseVideoUrl) {
		this.analyseVideoUrl = analyseVideoUrl;
		return this;
	}
	
	
	public String getAnalyseAudioUrl() {
		return analyseAudioUrl;
	}

	public TikuTopic setAnalyseAudioUrl(String analyseAudioUrl) {
		this.analyseAudioUrl = analyseAudioUrl;
		return this;
	}
	
	
	public Integer getParentId() {
		return parentId;
	}

	public TikuTopic setParentId(Integer parentId) {
		this.parentId = parentId;
		return this;
	}
	
	
	public String getStatus() {
		return status;
	}

	public TikuTopic setStatus(String status) {
		this.status = status;
		return this;
	}
	
	
	public Integer getTikuSubjectId() {
		return tikuSubjectId;
	}

	public TikuTopic setTikuSubjectId(Integer tikuSubjectId) {
		this.tikuSubjectId = tikuSubjectId;
		return this;
	}
	
	
	public Integer getTikuCategoryId() {
		return tikuCategoryId;
	}

	public TikuTopic setTikuCategoryId(Integer tikuCategoryId) {
		this.tikuCategoryId = tikuCategoryId;
		return this;
	}
	
	
	public Integer getCompanyId() {
		return companyId;
	}

	public TikuTopic setCompanyId(Integer companyId) {
		this.companyId = companyId;
		return this;
	}
	
	
	public Integer getCreator() {
		return creator;
	}

	public TikuTopic setCreator(Integer creator) {
		this.creator = creator;
		return this;
	}
	
	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getCreateTime() {
		return createTime;
	}

	public TikuTopic setCreateTime(Date createTime) {
		this.createTime = createTime;
		return this;
	}
	
	
	public Integer getUpdator() {
		return updator;
	}

	public TikuTopic setUpdator(Integer updator) {
		this.updator = updator;
		return this;
	}
	
	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getUpdateTime() {
		return updateTime;
	}

	public TikuTopic setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
		return this;
	}
	
	
	public Integer getAuditor() {
		return auditor;
	}

	public TikuTopic setAuditor(Integer auditor) {
		this.auditor = auditor;
		return this;
	}
	
	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getAuditTime() {
		return auditTime;
	}

	public TikuTopic setAuditTime(Date auditTime) {
		this.auditTime = auditTime;
		return this;
	}
	
	public String getTopicNameBlank() {
		return topicNameBlank;
	}

	public void setTopicNameBlank(String topicNameBlank) {
		this.topicNameBlank = topicNameBlank;
	}

	@Override
	public String toString() {
		return "TikuTopic [" + "id=" + getId() + ", topicName=" + topicName + ", topicNameBlank=" + topicNameBlank + ", topicType=" + topicType + ", score=" + score + ", answer=" + answer + ", difficulty=" + difficulty + ", analyseWord=" + analyseWord + ", analyseVideoUrl=" + analyseVideoUrl + ", analyseAudioUrl=" + analyseAudioUrl + ", parentId=" + parentId + ", status=" + status + ", tikuSubjectId=" + tikuSubjectId + ", tikuCategoryId=" + tikuCategoryId + ", companyId=" + companyId + ", creator=" + creator + ", creatorName=" + creatorName + ", createTime=" + createTime + ", updator=" + updator + ", updateTime=" + updateTime + ", auditor=" + auditor + ", auditTime=" + auditTime + ", paperFlag=" + paperFlag +", childFlag=" + childFlag + "]";
	}

	public String getCreatorName() {
		return creatorName;
	}

	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}

	public String getUpdatorName() {
		return updatorName;
	}

	public void setUpdatorName(String updatorName) {
		this.updatorName = updatorName;
	}

	public String getAuditorName() {
		return auditorName;
	}

	public void setAuditorName(String auditorName) {
		this.auditorName = auditorName;
	}

	public Integer getPaperFlag() {
		return paperFlag;
	}

	public void setPaperFlag(Integer paperFlag) {
		this.paperFlag = paperFlag;
	}

	public Integer getChildFlag() {
		return childFlag;
	}

	public void setChildFlag(Integer childFlag) {
		this.childFlag = childFlag;
	}

	public Integer getCorrectFlag() {
		return correctFlag;
	}

	public void setCorrectFlag(Integer correctFlag) {
		this.correctFlag = correctFlag;
	}

	public List<TikuTopic> getTopicList() {
		return topicList;
	}

	public void setTopicList(List<TikuTopic> topicList) {
		this.topicList = topicList;
	}
}
