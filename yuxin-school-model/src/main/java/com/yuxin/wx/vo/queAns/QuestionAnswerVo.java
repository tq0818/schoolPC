package com.yuxin.wx.vo.queAns;

import java.util.Date;
import java.util.List;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.yuxin.wx.model.queAns.QuestionAnswer;
import com.yuxin.wx.util.ShortDateSerializer;
import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:QuestionAnswerVoVo
 * 
 * @author wang.zx
 * @date 2015-12-9
 */
@SuppressWarnings("serial")
public class QuestionAnswerVo extends BaseEntity {
	
	
	private String	questionTitle;		 /* 提问标题 */ 
	private String	questionDesc;		 /* 问题描述 */ 
	private Integer	itemOneId;		 /* 学科ID */ 
	private Integer	itemSecondId;		 /* 学科小类ID */ 
	private Integer	classifyId;		 /* 所属分类ID */ 
	private Integer	userId;		 /*  用户id */ 
	private Integer	courseFlag;		 /* 是否课程提问（0：非课程问答  1：课程问答） */ 
	private Integer	courseId;		 /* 所属课程ID */ 
	private String	courseName;		 /* 所属课程的名称 */ 
	private Integer	courseLectureId;		 /*  所属课次的ID */ 
	private String	courseLectureName;		 /* 所属课次的名称 */ 
	private Integer	answerCount;		 /*  回答量 */ 
	private Integer	scanCount;		 /* 浏览量 */ 
	private Integer	adoptFlag;		 /* 是否采纳答案标示 */ 
	private Integer	adoptAnswerId;		 /*  已采纳的回答ID */ 
	private Integer	topFlag;		 /* 是否置顶帖（1：置顶帖） */ 
	private Integer	essenceFlag;		 /* 精华贴（1：精华帖） */ 
	private Date	createTime;		 /* 创建时间 */ 
	private Date	updateTime;		 /* 更新时间 */ 
	private Integer	delFlag;		 /* 是否删除( 0： 已删除  1：未删除) */
	private String questionType;	/*问题类型*/
	private Integer companyId;		/*公司id*/
	private Integer schoolId;		/*分校id*/
	
	private Integer biaoSecItemId;	/*右侧标签的id*/
	private List<Integer> questionIds;/*根据右侧标签查出的问题id集合*/

	private String username;	/*用户名*/
	private String headimg;		/*用户头像*/
	private String queTime;		/*提问时间*/
	private String ansTime;		/*最后回答*/
	private QuestionAnswer answer; /*最后回答*/
	// Constructor
	public QuestionAnswerVo() {
	}
	
	/**
	 * full Constructor
	 */
	public QuestionAnswerVo(Integer id, String questionTitle, String questionDesc, Integer itemOneId, Integer itemSecondId, Integer classifyId, Integer userId, Integer courseFlag, Integer courseId, String courseName, Integer courseLectureId, String courseLectureName, Integer answerCount, Integer scanCount, Integer adoptFlag, Integer adoptAnswerId, Integer topFlag, Integer essenceFlag, Date createTime, Date updateTime, Integer delFlag) {
		setId(id);
		this.questionTitle = questionTitle;
		this.questionDesc = questionDesc;
		this.itemOneId = itemOneId;
		this.itemSecondId = itemSecondId;
		this.classifyId = classifyId;
		this.userId = userId;
		this.courseFlag = courseFlag;
		this.courseId = courseId;
		this.courseName = courseName;
		this.courseLectureId = courseLectureId;
		this.courseLectureName = courseLectureName;
		this.answerCount = answerCount;
		this.scanCount = scanCount;
		this.adoptFlag = adoptFlag;
		this.adoptAnswerId = adoptAnswerId;
		this.topFlag = topFlag;
		this.essenceFlag = essenceFlag;
		this.createTime = createTime;
		this.updateTime = updateTime;
		this.delFlag = delFlag;
	}

	// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为QuestionAnswerVo可以实现连缀设置属性
	
	public String getQuestionTitle() {
		return questionTitle;
	}

	public QuestionAnswerVo setQuestionTitle(String questionTitle) {
		this.questionTitle = questionTitle;
		return this;
	}
	
	
	public String getQuestionDesc() {
		return questionDesc;
	}

	public QuestionAnswerVo setQuestionDesc(String questionDesc) {
		this.questionDesc = questionDesc;
		return this;
	}
	
	
	public Integer getItemOneId() {
		return itemOneId;
	}

	public QuestionAnswerVo setItemOneId(Integer itemOneId) {
		this.itemOneId = itemOneId;
		return this;
	}
	
	
	public Integer getItemSecondId() {
		return itemSecondId;
	}

	public QuestionAnswerVo setItemSecondId(Integer itemSecondId) {
		this.itemSecondId = itemSecondId;
		return this;
	}
	
	
	public Integer getClassifyId() {
		return classifyId;
	}

	public QuestionAnswerVo setClassifyId(Integer classifyId) {
		this.classifyId = classifyId;
		return this;
	}
	
	
	public Integer getUserId() {
		return userId;
	}

	public QuestionAnswerVo setUserId(Integer userId) {
		this.userId = userId;
		return this;
	}
	
	
	public Integer getCourseFlag() {
		return courseFlag;
	}

	public QuestionAnswerVo setCourseFlag(Integer courseFlag) {
		this.courseFlag = courseFlag;
		return this;
	}
	
	
	public Integer getCourseId() {
		return courseId;
	}

	public QuestionAnswerVo setCourseId(Integer courseId) {
		this.courseId = courseId;
		return this;
	}
	
	
	public String getCourseName() {
		return courseName;
	}

	public QuestionAnswerVo setCourseName(String courseName) {
		this.courseName = courseName;
		return this;
	}
	
	
	public Integer getCourseLectureId() {
		return courseLectureId;
	}

	public QuestionAnswerVo setCourseLectureId(Integer courseLectureId) {
		this.courseLectureId = courseLectureId;
		return this;
	}
	
	
	public String getCourseLectureName() {
		return courseLectureName;
	}

	public QuestionAnswerVo setCourseLectureName(String courseLectureName) {
		this.courseLectureName = courseLectureName;
		return this;
	}
	
	
	public Integer getAnswerCount() {
		return answerCount;
	}

	public QuestionAnswerVo setAnswerCount(Integer answerCount) {
		this.answerCount = answerCount;
		return this;
	}
	
	
	public Integer getScanCount() {
		return scanCount;
	}

	public QuestionAnswerVo setScanCount(Integer scanCount) {
		this.scanCount = scanCount;
		return this;
	}
	
	
	public Integer getAdoptFlag() {
		return adoptFlag;
	}

	public QuestionAnswerVo setAdoptFlag(Integer adoptFlag) {
		this.adoptFlag = adoptFlag;
		return this;
	}
	
	
	public Integer getAdoptAnswerId() {
		return adoptAnswerId;
	}

	public QuestionAnswerVo setAdoptAnswerId(Integer adoptAnswerId) {
		this.adoptAnswerId = adoptAnswerId;
		return this;
	}
	
	
	public Integer getTopFlag() {
		return topFlag;
	}

	public QuestionAnswerVo setTopFlag(Integer topFlag) {
		this.topFlag = topFlag;
		return this;
	}
	
	
	public Integer getEssenceFlag() {
		return essenceFlag;
	}

	public QuestionAnswerVo setEssenceFlag(Integer essenceFlag) {
		this.essenceFlag = essenceFlag;
		return this;
	}
	
	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getCreateTime() {
		return createTime;
	}

	public QuestionAnswerVo setCreateTime(Date createTime) {
		this.createTime = createTime;
		return this;
	}
	
	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getUpdateTime() {
		return updateTime;
	}

	public QuestionAnswerVo setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
		return this;
	}
	
	
	public Integer getDelFlag() {
		return delFlag;
	}

	public QuestionAnswerVo setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
		return this;
	}
	
	@Override
	public String toString() {
		return "QuestionAnswerVo [" + "id=" + getId() + ", questionTitle=" + questionTitle + ", questionDesc=" + questionDesc + ", itemOneId=" + itemOneId + ", itemSecondId=" + itemSecondId + ", classifyId=" + classifyId + ", userId=" + userId + ", courseFlag=" + courseFlag + ", courseId=" + courseId + ", courseName=" + courseName + ", courseLectureId=" + courseLectureId + ", courseLectureName=" + courseLectureName + ", answerCount=" + answerCount + ", scanCount=" + scanCount + ", adoptFlag=" + adoptFlag + ", adoptAnswerId=" + adoptAnswerId + ", topFlag=" + topFlag + ", essenceFlag=" + essenceFlag + ", createTime=" + createTime + ", updateTime=" + updateTime + ", delFlag=" + delFlag +  "]";
	}

	public Integer getBiaoSecItemId() {
		return biaoSecItemId;
	}

	public void setBiaoSecItemId(Integer biaoSecItemId) {
		this.biaoSecItemId = biaoSecItemId;
	}

	public List<Integer> getQuestionIds() {
		return questionIds;
	}

	public void setQuestionIds(List<Integer> questionIds) {
		this.questionIds = questionIds;
	}

	public String getQuestionType() {
		return questionType;
	}

	public void setQuestionType(String questionType) {
		this.questionType = questionType;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getHeadimg() {
		return headimg;
	}

	public void setHeadimg(String headimg) {
		this.headimg = headimg;
	}

	public String getQueTime() {
		return queTime;
	}

	public void setQueTime(String queTime) {
		this.queTime = queTime;
	}

	public String getAnsTime() {
		return ansTime;
	}

	public void setAnsTime(String ansTime) {
		this.ansTime = ansTime;
	}

	public QuestionAnswer getAnswer() {
		return answer;
	}

	public void setAnswer(QuestionAnswer answer) {
		this.answer = answer;
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
}
