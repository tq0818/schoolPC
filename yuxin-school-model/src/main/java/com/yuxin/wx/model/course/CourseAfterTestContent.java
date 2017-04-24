package  com.yuxin.wx.model.course;


import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:CourseAfterTestContent
 * 
 * @author chopin
 * @date 2016-9-1
 */
@SuppressWarnings("serial")
public class CourseAfterTestContent extends BaseEntity {
	
	
	private Integer	chapterId;		 /* 章ID */ 
	private Integer	lectureId;		 /* 节ID */ 
	private Integer	testType;		 /* 测验类型 */ 
	private Integer	paperId;		 /* 试卷ID */ 
	private Integer	categoryId;		 /* 题库ID */ 
	private Integer	subjectId;		 /* 科目ID */ 
	private Integer	topicNum;		 /* 抽屉数量 */ 
	private Integer testId;          /* 测验id */ 
	private String chapterName;
	private String sectionName;
	private String paperName;
	private Integer delIndex;

	

	
	

	// Constructor
	public CourseAfterTestContent() {
	}
	
	/**
	 * full Constructor
	 */
	public CourseAfterTestContent(Integer id, Integer chapterId, Integer lectureId, Integer testType, Integer paperId, Integer categoryId, Integer subjectId, Integer topicNum) {
		setId(id);
		this.chapterId = chapterId;
		this.lectureId = lectureId;
		this.testType = testType;
		this.paperId = paperId;
		this.categoryId = categoryId;
		this.subjectId = subjectId;
		this.topicNum = topicNum;
	}

	// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为CourseAfterTestContent可以实现连缀设置属性
	
	public String getChapterName() {
		return chapterName;
	}

	public void setChapterName(String chapterName) {
		this.chapterName = chapterName;
	}
	
	
	public Integer getDelIndex() {
		return delIndex;
	}

	public void setDelIndex(Integer delIndex) {
		this.delIndex = delIndex;
	}
	
	public String getPaperName() {
		return paperName;
	}

	public void setPaperName(String paperName) {
		this.paperName = paperName;
	}

	public String getSectionName() {
		return sectionName;
	}

	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}

	
	
	public Integer getTestId() {
		return testId;
	}

	public void setTestId(Integer testId) {
		this.testId = testId;
	}
	
	public Integer getChapterId() {
		return chapterId;
	}

	public CourseAfterTestContent setChapterId(Integer chapterId) {
		this.chapterId = chapterId;
		return this;
	}
	
	
	public Integer getLectureId() {
		return lectureId;
	}

	public CourseAfterTestContent setLectureId(Integer lectureId) {
		this.lectureId = lectureId;
		return this;
	}
	
	
	public Integer getTestType() {
		return testType;
	}

	public CourseAfterTestContent setTestType(Integer testType) {
		this.testType = testType;
		return this;
	}
	
	
	public Integer getPaperId() {
		return paperId;
	}

	public CourseAfterTestContent setPaperId(Integer paperId) {
		this.paperId = paperId;
		return this;
	}
	
	
	public Integer getCategoryId() {
		return categoryId;
	}

	public CourseAfterTestContent setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
		return this;
	}
	
	
	public Integer getSubjectId() {
		return subjectId;
	}

	public CourseAfterTestContent setSubjectId(Integer subjectId) {
		this.subjectId = subjectId;
		return this;
	}
	
	
	public Integer getTopicNum() {
		return topicNum;
	}

	public CourseAfterTestContent setTopicNum(Integer topicNum) {
		this.topicNum = topicNum;
		return this;
	}
	
	@Override
	public String toString() {
		return "CourseAfterTestContent [chapterId=" + chapterId + ", lectureId=" + lectureId + ", testType=" + testType
				+ ", paperId=" + paperId + ", categoryId=" + categoryId + ", subjectId=" + subjectId + ", topicNum="
				+ topicNum + ", testId=" + testId + "]";
	}
}
