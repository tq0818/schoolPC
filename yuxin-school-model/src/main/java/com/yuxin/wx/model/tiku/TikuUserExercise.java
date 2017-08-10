package com.yuxin.wx.model.tiku;

import java.util.Date;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import com.yuxin.wx.util.ShortDateSerializer;

import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:TikuUserExercise
 * 
 * @author wang.zx
 * @date 2015-8-24
 */
@SuppressWarnings("serial")
public class TikuUserExercise extends BaseEntity {
	
	
	private Integer	exerciseId;		 /* 练习的批次id，paper表id或者batch表id */ 
	private Integer	userId;		 /* 用户id */ 
	private String	exerciseType;		 /* 联系类型：试卷、快速做题、章节练习（TIKU_EXERCISE_TYPE） */ 
	private Date	startTime;		 /* 开始时间 */ 
	private Date	endTime;		 /* 结束时间 */ 
	private Integer	useTime;		 /* 答题试卷，单位秒 */ 
	private Double	exerciseScore;		 /* 得分 */ 
	private String	status;		 /* 答题状态，字典表（TIKU_EXERCISE_STATUS）：答题中、暂停、结束 */ 
	private Integer	totalTopic;		 /* 总题数 */ 
	private Integer	correctTopic;		 /* 做对题数,交卷时维护 */ 
	private Integer	errorTopic;		 /* 做错题数,交卷时维护 */ 
	private Integer	categoryId;		 /* 题库类别id */ 
	private Integer	subjectId;		 /* 题库科目id */ 
	private Integer chapterId; 		 /* 章id */ 
	private Integer	companyId;		
	private String eduArea;		/* 区域 */
	private String eduSchool;		/* 学校 */
	private String eduClass;		/* 班级 */
	private String eduYear;		/* 入学年份 */

	//其他
	private Integer topCount;/*多少到题*/
	private String useTimes;

	// Constructor
	public TikuUserExercise() {
	}
	
	/**
	 * full Constructor
	 */
	public TikuUserExercise(Integer id, Integer exerciseId, Integer userId, String exerciseType, Date startTime, Date endTime, Integer useTime, Double exerciseScore, String status, Integer totalTopic, Integer correctTopic, Integer errorTopic, Integer categoryId, Integer subjectId, Integer chapterId, Integer companyId) {
		setId(id);
		this.exerciseId = exerciseId;
		this.userId = userId;
		this.exerciseType = exerciseType;
		this.startTime = startTime;
		this.endTime = endTime;
		this.useTime = useTime;
		this.exerciseScore = exerciseScore;
		this.status = status;
		this.totalTopic = totalTopic;
		this.correctTopic = correctTopic;
		this.errorTopic = errorTopic;
		this.categoryId = categoryId;
		this.subjectId = subjectId;
		this.chapterId = chapterId;
		this.companyId = companyId;
	}

	public String getEduArea() {
		return eduArea;
	}

	public void setEduArea(String eduArea) {
		this.eduArea = eduArea;
	}

	public String getEduSchool() {
		return eduSchool;
	}

	public void setEduSchool(String eduSchool) {
		this.eduSchool = eduSchool;
	}

	public String getEduClass() {
		return eduClass;
	}

	public void setEduClass(String eduClass) {
		this.eduClass = eduClass;
	}

// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为TikuUserExercise可以实现连缀设置属性
	
	public Integer getExerciseId() {
		return exerciseId;
	}

	public TikuUserExercise setExerciseId(Integer exerciseId) {
		this.exerciseId = exerciseId;
		return this;
	}
	
	
	public Integer getUserId() {
		return userId;
	}

	public TikuUserExercise setUserId(Integer userId) {
		this.userId = userId;
		return this;
	}
	
	
	public String getExerciseType() {
		return exerciseType;
	}

	public TikuUserExercise setExerciseType(String exerciseType) {
		this.exerciseType = exerciseType;
		return this;
	}
	
	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getStartTime() {
		return startTime;
	}

	public TikuUserExercise setStartTime(Date startTime) {
		this.startTime = startTime;
		return this;
	}
	
	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getEndTime() {
		return endTime;
	}

	public TikuUserExercise setEndTime(Date endTime) {
		this.endTime = endTime;
		return this;
	}
	
	
	public Integer getUseTime() {
		return useTime;
	}

	public TikuUserExercise setUseTime(Integer useTime) {
		this.useTime = useTime;
		return this;
	}
	
	
	public Double getExerciseScore() {
		return exerciseScore;
	}

	public TikuUserExercise setExerciseScore(Double exerciseScore) {
		this.exerciseScore = exerciseScore;
		return this;
	}
	
	
	public String getStatus() {
		return status;
	}

	public TikuUserExercise setStatus(String status) {
		this.status = status;
		return this;
	}
	
	
	public Integer getTotalTopic() {
		return totalTopic;
	}

	public TikuUserExercise setTotalTopic(Integer totalTopic) {
		this.totalTopic = totalTopic;
		return this;
	}
	
	
	public Integer getCorrectTopic() {
		return correctTopic;
	}

	public TikuUserExercise setCorrectTopic(Integer correctTopic) {
		this.correctTopic = correctTopic;
		return this;
	}
	
	
	public Integer getErrorTopic() {
		return errorTopic;
	}

	public TikuUserExercise setErrorTopic(Integer errorTopic) {
		this.errorTopic = errorTopic;
		return this;
	}
	
	
	public Integer getCategoryId() {
		return categoryId;
	}

	public TikuUserExercise setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
		return this;
	}
	
	
	public Integer getSubjectId() {
		return subjectId;
	}

	public TikuUserExercise setSubjectId(Integer subjectId) {
		this.subjectId = subjectId;
		return this;
	}
	
	
	public Integer getCompanyId() {
		return companyId;
	}

	public TikuUserExercise setCompanyId(Integer companyId) {
		this.companyId = companyId;
		return this;
	}
	
	public Integer getChapterId() {
		return chapterId;
	}

	public void setChapterId(Integer chapterId) {
		this.chapterId = chapterId;
	}

	@Override
	public String toString() {
		return "TikuUserExercise [" + "id=" + getId() + ", exerciseId=" + exerciseId + ", userId=" + userId + ", exerciseType=" + exerciseType + ", startTime=" + startTime + ", endTime=" + endTime + ", useTime=" + useTime + ", exerciseScore=" + exerciseScore + ", status=" + status + ", totalTopic=" + totalTopic + ", correctTopic=" + correctTopic + ", errorTopic=" + errorTopic + ", categoryId=" + categoryId + ", subjectId=" + subjectId + ", companyId=" + companyId +  "]";
	}

	public Integer getTopCount() {
		return topCount;
	}

	public void setTopCount(Integer topCount) {
		this.topCount = topCount;
	}

	public String getUseTimes() {
		return useTimes;
	}

	public void setUseTimes(String useTimes) {
		this.useTimes = useTimes;
	}

	public String getEduYear() {
		return eduYear;
	}

	public void setEduYear(String eduYear) {
		this.eduYear = eduYear;
	}
}
