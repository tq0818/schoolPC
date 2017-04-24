package com.yuxin.wx.model.queAns;

import java.util.Date;
import java.util.List;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.yuxin.wx.common.BaseEntity;
import com.yuxin.wx.util.ShortDateSerializer;

/**
 * POJO:QueQuestionVo
 *
 * @author wang.zx
 * @date 2015-12-9
 */
@SuppressWarnings("serial")
public class QueQuestion extends BaseEntity {

	private String questionTitle; /* 提问标题 */
	private String questionDesc; /* 问题描述 */
	private Integer itemOneId; /* 学科ID */
	private Integer itemSecondId; /* 学科小类ID */
	private Integer classifyId; /* 所属分类ID */
	private Integer userId; /* 用户id */
	private Integer courseFlag; /* 是否课程提问（0：非课程问答 1：课程问答） */
	private Integer courseId; /* 所属课程ID */
	private String courseName; /* 所属课程的名称 */
	private Integer courseLectureId; /* 所属课次的ID */
	private String courseLectureName; /* 所属课次的名称 */
	private Integer answerCount; /* 回答量 */
	private Integer scanCount; /* 浏览量 */
	private Integer adoptFlag; /* 是否采纳答案标示 */
	private Integer adoptAnswerId; /* 已采纳的回答ID */
	private Integer topFlag; /* 是否置顶帖（1：置顶帖） */
	private Integer essenceFlag; /* 精华贴（1：精华帖） */
	private Date createTime; /* 创建时间 */
	private Date updateTime; /* 更新时间 */
	private Integer delFlag; /* 是否删除( 0： 已删除 1：未删除) */
	private String questionType; /* 问题类型 */
	private Integer companyId; /* 公司id */
	private Integer schoolId; /* 分校id */

	private Integer biaoSecItemId; /* 右侧标签的id */
	private List<Integer> questionIds;/* 根据右侧标签查出的问题id集合 */

	private String username; /* 用户名 */
	private String headimg; /* 用户头像 */
	private String queTime; /* 提问时间 */
	private String ansTime; /* 最后回答 */

	private String classIsOpen; /* 课程问答是否开启 */
	private List<Integer> commodityIds;/* 学员购买课程的商品id集合 */
	private List<Integer> questionId3s;/* 查询出置顶的3条问答id集合 */
	private List<Integer> questionNotins;/* 课程分类时，标签=学科 */

	private String searchKey;// 搜索条件

	// Constructor
	public QueQuestion() {
	}

	/**
	 * full Constructor
	 */
	public QueQuestion(Integer id, String questionTitle, String questionDesc, Integer itemOneId, Integer itemSecondId, Integer classifyId, Integer userId,
	        Integer courseFlag, Integer courseId, String courseName, Integer courseLectureId, String courseLectureName, Integer answerCount, Integer scanCount,
	        Integer adoptFlag, Integer adoptAnswerId, Integer topFlag, Integer essenceFlag, Date createTime, Date updateTime, Integer delFlag) {
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
	// 在setter方法最后加上"return this;"并把返回参数改为QueQuestion可以实现连缀设置属性

	public String getQuestionTitle() {
		return questionTitle;
	}

	public QueQuestion setQuestionTitle(String questionTitle) {
		this.questionTitle = questionTitle;
		return this;
	}

	public String getQuestionDesc() {
		return questionDesc;
	}

	public QueQuestion setQuestionDesc(String questionDesc) {
		this.questionDesc = questionDesc;
		return this;
	}

	public Integer getItemOneId() {
		return itemOneId;
	}

	public QueQuestion setItemOneId(Integer itemOneId) {
		this.itemOneId = itemOneId;
		return this;
	}

	public Integer getItemSecondId() {
		return itemSecondId;
	}

	public QueQuestion setItemSecondId(Integer itemSecondId) {
		this.itemSecondId = itemSecondId;
		return this;
	}

	public Integer getClassifyId() {
		return classifyId;
	}

	public QueQuestion setClassifyId(Integer classifyId) {
		this.classifyId = classifyId;
		return this;
	}

	public Integer getUserId() {
		return userId;
	}

	public QueQuestion setUserId(Integer userId) {
		this.userId = userId;
		return this;
	}

	public Integer getCourseFlag() {
		return courseFlag;
	}

	public QueQuestion setCourseFlag(Integer courseFlag) {
		this.courseFlag = courseFlag;
		return this;
	}

	public Integer getCourseId() {
		return courseId;
	}

	public QueQuestion setCourseId(Integer courseId) {
		this.courseId = courseId;
		return this;
	}

	public String getCourseName() {
		return courseName;
	}

	public QueQuestion setCourseName(String courseName) {
		this.courseName = courseName;
		return this;
	}

	public Integer getCourseLectureId() {
		return courseLectureId;
	}

	public QueQuestion setCourseLectureId(Integer courseLectureId) {
		this.courseLectureId = courseLectureId;
		return this;
	}

	public String getCourseLectureName() {
		return courseLectureName;
	}

	public QueQuestion setCourseLectureName(String courseLectureName) {
		this.courseLectureName = courseLectureName;
		return this;
	}

	public Integer getAnswerCount() {
		return answerCount;
	}

	public QueQuestion setAnswerCount(Integer answerCount) {
		this.answerCount = answerCount;
		return this;
	}

	public Integer getScanCount() {
		return scanCount;
	}

	public QueQuestion setScanCount(Integer scanCount) {
		this.scanCount = scanCount;
		return this;
	}

	public Integer getAdoptFlag() {
		return adoptFlag;
	}

	public QueQuestion setAdoptFlag(Integer adoptFlag) {
		this.adoptFlag = adoptFlag;
		return this;
	}

	public Integer getAdoptAnswerId() {
		return adoptAnswerId;
	}

	public QueQuestion setAdoptAnswerId(Integer adoptAnswerId) {
		this.adoptAnswerId = adoptAnswerId;
		return this;
	}

	public Integer getTopFlag() {
		return topFlag;
	}

	public QueQuestion setTopFlag(Integer topFlag) {
		this.topFlag = topFlag;
		return this;
	}

	public Integer getEssenceFlag() {
		return essenceFlag;
	}

	public QueQuestion setEssenceFlag(Integer essenceFlag) {
		this.essenceFlag = essenceFlag;
		return this;
	}

	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getCreateTime() {
		return createTime;
	}

	public QueQuestion setCreateTime(Date createTime) {
		this.createTime = createTime;
		return this;
	}

	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getUpdateTime() {
		return updateTime;
	}

	public QueQuestion setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
		return this;
	}

	public Integer getDelFlag() {
		return delFlag;
	}

	public QueQuestion setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
		return this;
	}

	@Override
	public String toString() {
		return "QueQuestionVo [" + "id=" + getId() + ", questionTitle=" + questionTitle + ", questionDesc=" + questionDesc + ", itemOneId=" + itemOneId
		        + ", itemSecondId=" + itemSecondId + ", classifyId=" + classifyId + ", userId=" + userId + ", courseFlag=" + courseFlag + ", courseId="
		        + courseId + ", courseName=" + courseName + ", courseLectureId=" + courseLectureId + ", courseLectureName=" + courseLectureName
		        + ", answerCount=" + answerCount + ", scanCount=" + scanCount + ", adoptFlag=" + adoptFlag + ", adoptAnswerId=" + adoptAnswerId + ", topFlag="
		        + topFlag + ", essenceFlag=" + essenceFlag + ", createTime=" + createTime + ", updateTime=" + updateTime + ", delFlag=" + delFlag + "]";
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

	public String getClassIsOpen() {
		return classIsOpen;
	}

	public void setClassIsOpen(String classIsOpen) {
		this.classIsOpen = classIsOpen;
	}

	public List<Integer> getCommodityIds() {
		return commodityIds;
	}

	public void setCommodityIds(List<Integer> commodityIds) {
		this.commodityIds = commodityIds;
	}

	public List<Integer> getQuestionId3s() {
		return questionId3s;
	}

	public void setQuestionId3s(List<Integer> questionId3s) {
		this.questionId3s = questionId3s;
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

	public List<Integer> getQuestionNotins() {
		return questionNotins;
	}

	public void setQuestionNotins(List<Integer> questionNotins) {
		this.questionNotins = questionNotins;
	}

	public String getSearchKey() {
		return searchKey;
	}

	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}
}
