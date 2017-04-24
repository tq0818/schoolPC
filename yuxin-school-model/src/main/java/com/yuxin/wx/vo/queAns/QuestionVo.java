package com.yuxin.wx.vo.queAns;

import java.util.Date;
import java.util.List;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.yuxin.wx.model.queAns.QuestionAnswer;
import com.yuxin.wx.model.queAns.QuestionClassifyRelation;
import com.yuxin.wx.util.ShortDateSerializer;
import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:QuestionVo
 * 
 * @author wang.zx
 * @date 2015-12-9
 */
@SuppressWarnings("serial")
public class QuestionVo extends BaseEntity {
	
	
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
	private Integer companyId;		/*公司id*/
	private Integer schoolId;		/*分校id*/
	
	private String tiwenTime;		/*几分钟，秒，小时，天，时间之前*/
	private String lastAnsTime;		/*最后回答是几分钟，秒，小时，天，时间之前*/
	private String headPic;			/*头像地址*/
	private String userName;		/*用户名*/
	private String subQuestionDesc;	/*截取后的描述*/
	
	private Integer answerId; 		/*当前回答的ID */
	private String nowAnsName;		/*最新回答的用户名*/
	private String nowHeadPic;		/*最新回答的用户头像*/
	private Integer nowUserId;		/*最新回答的用户ID*/
	private String nowAnsDesc;		/*最新回答的评论内容*/
	private String questionType;	/*问题类型*/
	private String ansType;			/*回答的类型*/
	private Integer readFlag;       /*当前question中是否 */
	private List<QuestionClassifyRelation> relList;/*问题所含标签*/
	private List<QuestionAnswer> answerList;
	
	// Constructor
	public QuestionVo() {
	}
	
	/**
	 * full Constructor
	 */
	public QuestionVo(Integer id, Integer answerId, String questionTitle, String questionDesc, Integer itemOneId, Integer itemSecondId, Integer classifyId, Integer userId, Integer courseFlag, Integer courseId, String courseName, Integer courseLectureId, String courseLectureName, Integer answerCount, Integer scanCount, Integer adoptFlag, Integer adoptAnswerId, Integer topFlag, Integer essenceFlag, Date createTime, Date updateTime, Integer delFlag,
			Integer readFlag) {
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
		this.answerId = answerId;
		this.readFlag = readFlag;
	}

	// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为Question可以实现连缀设置属性
	
	public String getQuestionTitle() {
		return questionTitle;
	}

	public QuestionVo setQuestionTitle(String questionTitle) {
		this.questionTitle = questionTitle;
		return this;
	}
	
	
	public String getQuestionDesc() {
		return questionDesc;
	}

	public QuestionVo setQuestionDesc(String questionDesc) {
		this.questionDesc = questionDesc;
		return this;
	}
	
	
	public Integer getItemOneId() {
		return itemOneId;
	}

	public QuestionVo setItemOneId(Integer itemOneId) {
		this.itemOneId = itemOneId;
		return this;
	}
	
	
	public Integer getItemSecondId() {
		return itemSecondId;
	}

	public QuestionVo setItemSecondId(Integer itemSecondId) {
		this.itemSecondId = itemSecondId;
		return this;
	}
	
	
	public Integer getClassifyId() {
		return classifyId;
	}

	public QuestionVo setClassifyId(Integer classifyId) {
		this.classifyId = classifyId;
		return this;
	}
	
	
	public Integer getUserId() {
		return userId;
	}

	public QuestionVo setUserId(Integer userId) {
		this.userId = userId;
		return this;
	}
	
	
	public Integer getCourseFlag() {
		return courseFlag;
	}

	public QuestionVo setCourseFlag(Integer courseFlag) {
		this.courseFlag = courseFlag;
		return this;
	}
	
	
	public Integer getCourseId() {
		return courseId;
	}

	public QuestionVo setCourseId(Integer courseId) {
		this.courseId = courseId;
		return this;
	}
	
	
	public String getCourseName() {
		return courseName;
	}

	public QuestionVo setCourseName(String courseName) {
		this.courseName = courseName;
		return this;
	}
	
	
	public Integer getCourseLectureId() {
		return courseLectureId;
	}

	public QuestionVo setCourseLectureId(Integer courseLectureId) {
		this.courseLectureId = courseLectureId;
		return this;
	}
	
	
	public String getCourseLectureName() {
		return courseLectureName;
	}

	public QuestionVo setCourseLectureName(String courseLectureName) {
		this.courseLectureName = courseLectureName;
		return this;
	}
	
	
	public Integer getAnswerCount() {
		return answerCount;
	}

	public QuestionVo setAnswerCount(Integer answerCount) {
		this.answerCount = answerCount;
		return this;
	}
	
	
	public Integer getScanCount() {
		return scanCount;
	}

	public QuestionVo setScanCount(Integer scanCount) {
		this.scanCount = scanCount;
		return this;
	}
	
	
	public Integer getAdoptFlag() {
		return adoptFlag;
	}

	public QuestionVo setAdoptFlag(Integer adoptFlag) {
		this.adoptFlag = adoptFlag;
		return this;
	}
	
	
	public Integer getAdoptAnswerId() {
		return adoptAnswerId;
	}

	public QuestionVo setAdoptAnswerId(Integer adoptAnswerId) {
		this.adoptAnswerId = adoptAnswerId;
		return this;
	}
	
	
	public Integer getTopFlag() {
		return topFlag;
	}

	public QuestionVo setTopFlag(Integer topFlag) {
		this.topFlag = topFlag;
		return this;
	}
	
	
	public Integer getEssenceFlag() {
		return essenceFlag;
	}

	public QuestionVo setEssenceFlag(Integer essenceFlag) {
		this.essenceFlag = essenceFlag;
		return this;
	}
	
	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getCreateTime() {
		return createTime;
	}

	public QuestionVo setCreateTime(Date createTime) {
		this.createTime = createTime;
		return this;
	}
	
	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getUpdateTime() {
		return updateTime;
	}

	public QuestionVo setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
		return this;
	}
	
	
	public Integer getDelFlag() {
		return delFlag;
	}

	public QuestionVo setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
		return this;
	}
	
	@Override
	public String toString() {
		return "QuestionVo [" + "id=" + getId() + ", questionTitle=" + questionTitle + ", questionDesc=" + questionDesc + ", itemOneId=" + itemOneId + ", itemSecondId=" + itemSecondId + ", classifyId=" + classifyId + ", userId=" + userId + ", courseFlag=" + courseFlag + ", courseId=" + courseId + ", courseName=" + courseName + ", courseLectureId=" + courseLectureId + ", courseLectureName=" + courseLectureName + ", answerCount=" + answerCount + ", scanCount=" + scanCount + ", adoptFlag=" + adoptFlag + ", adoptAnswerId=" + adoptAnswerId + ", topFlag=" + topFlag + ", essenceFlag=" + essenceFlag + ", createTime=" + createTime + ", updateTime=" + updateTime + ", delFlag=" + delFlag +  "]";
	}

	public String getTiwenTime() {
		return tiwenTime;
	}

	public void setTiwenTime(String tiwenTime) {
		this.tiwenTime = tiwenTime;
	}

	public String getHeadPic() {
		return headPic;
	}

	public void setHeadPic(String headPic) {
		this.headPic = headPic;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getSubQuestionDesc() {
		return subQuestionDesc;
	}

	public void setSubQuestionDesc(String subQuestionDesc) {
		this.subQuestionDesc = subQuestionDesc;
	}

	public String getNowAnsName() {
		return nowAnsName;
	}

	public void setNowAnsName(String nowAnsName) {
		this.nowAnsName = nowAnsName;
	}

	public String getNowHeadPic() {
		return nowHeadPic;
	}

	public void setNowHeadPic(String nowHeadPic) {
		this.nowHeadPic = nowHeadPic;
	}

	public Integer getNowUserId() {
		return nowUserId;
	}

	public void setNowUserId(Integer nowUserId) {
		this.nowUserId = nowUserId;
	}

	public String getNowAnsDesc() {
		return nowAnsDesc;
	}

	public void setNowAnsDesc(String nowAnsDesc) {
		this.nowAnsDesc = nowAnsDesc;
	}

	public String getLastAnsTime() {
		return lastAnsTime;
	}

	public void setLastAnsTime(String lastAnsTime) {
		this.lastAnsTime = lastAnsTime;
	}

	public List<QuestionClassifyRelation> getRelList() {
		return relList;
	}

	public void setRelList(List<QuestionClassifyRelation> relList) {
		this.relList = relList;
	}

	public String getAnsType() {
		return ansType;
	}

	public void setAnsType(String ansType) {
		this.ansType = ansType;
	}

	public String getQuestionType() {
		return questionType;
	}

	public void setQuestionType(String questionType) {
		this.questionType = questionType;
	}

	public Integer getAnswerId() {
		return answerId;
	}

	public void setAnswerId(Integer answerId) {
		this.answerId = answerId;
	}

	public List<QuestionAnswer> getAnswerList() {
		return answerList;
	}

	public void setAnswerList(List<QuestionAnswer> answerList) {
		this.answerList = answerList;
	}

	public Integer getReadFlag() {
		return readFlag;
	}

	public void setReadFlag(Integer readFlag) {
		this.readFlag = readFlag;
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
