package com.yuxin.wx.model.classes;

import java.util.Date;

import com.yuxin.wx.model.course.CourseExercise;
import com.yuxin.wx.model.tiku.TikuPaper;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.yuxin.wx.util.ShortDateSerializer;
import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:ClassModuleLesson
 * 
 * @author wang.zx
 * @date 2015-5-18
 */
@SuppressWarnings("serial")
public class ClassModuleLesson extends BaseEntity {
	
	
	private String	lessonName;		 /* 课次名称 */ 
	private Integer	moduleNoId;		 /* 班号id */ 
	private Date	lessonDate;		 /* 上课日期 */ 
	private String	scope;		 /* 上课时段：上午；下午；晚上；全天字典表数据 */ 
	private String	weekType;		 /* 星期：上课日期所对应的周几 */ 
	private String	lessonTimeStart;		 /* 课次开始时间 */ 
	private String	lessonTimeEnd;		 /* 课次结束日期 */ 
	private String	lessonHour;		 /* 课次所需课时数 */ 
	private String	teachers;		 /* 老师，存老师的id，可以有多个老师，之间用逗号分隔 */ 
	private String	assistants;		 /* 助教，存助教的id，可以有多个助教，之间用逗号分隔 */ 
	private String	masters;		 /* 班主任，存班主任的id，可以有多个班主任，之间用逗号分隔 */ 
	private String	teachersName;		 /* 老师，存老师的名字，可以有多个老师，之间用逗号分隔 */
	private String	schoolShortName;		 /* 机构简称，存老师机构的名字，可以有多个老师，之间用逗号分隔 */
	private String	assistantsName;		 /* 助教，存助教的名字，可以有多个助教，之间用逗号分隔 */ 
	private String	mastersName;		 /* 班主任，存班主任的名字，可以有多个班主任，之间用逗号分隔 */ 
	private Integer	classroomId;		 /* 教室id */ 
	private String	classroom;		 /* 教室名字，面授课使用该字段 */ 
	private String	liveRoom;		 /* 直播教室id，直播课使用该字段 */ 
	private String	remark;		 /* 备注 */ 
	private String	liveCompanyType;		 /* 使用的直播公司类型（光慧：gh；展视：zs） */ 
	private String	liveroomIdGh;		
	private String	studentUrlGh;		 /* 光慧学生观看地址 */ 
	private String	teacherUrlGh;		 /* 光慧老师讲课地址 */ 
	private String	assistantUrlGh;		 /* 光慧助教登陆地址 */ 
	private String	replayUrlGh;		 /* 光慧回看地址 */ 
	private Integer	delFlag;
	private Integer barrage;			 /* 弹幕，默认0:不开启*/
	private Integer modetype;			 /* 模式,默认3:大班课，1:语音,5:小班课*/
	private String beforeStudyUrl;		/*直播预习*/
	private String afterStudyUrl;		/*直播回看*/
	//当前课程播放状态
	private Integer status;
	private Integer sort;    /*排序字段*/
	private Integer chapterFlag;  /*标记是否为章1或0*/
	
	private Integer supportMobile;/*是否支持手机端*/
	private String liveClassType; /* 展示互动大讲堂或者是小班课*/
	
	private String supportMobileFlag;  /* 接受前台传过来的字段， 勿动*/
	private String liveClassTypeFlag;  /* 接受前台传过来的字段， 勿动*/
	
	
	private String dayStatus;  /* 课程时间状态  today:今日课程   tommorow:后续课程   yestoday:历史回顾*/
	private Date createTime;
	private Integer creator;
	
	//4.4课后作业添加的字段
	private Integer moduleId;
	private String ModuleNoName;
	private long dateTime;
	private Integer hasHomework;
	private Integer homeworkId;
	private Integer homeworkType;
	private CourseExercise courseExercise;
	private TikuPaper tikuPaper;
    private Date lessonDateTime;
    private Integer isOutSource;
    
	public Integer getIsOutSource() {
		return isOutSource;
	}

	public void setIsOutSource(Integer isOutSource) {
		this.isOutSource = isOutSource;
	}

	public TikuPaper getTikuPaper() {
		return tikuPaper;
	}

	public void setTikuPaper(TikuPaper tikuPaper) {
		this.tikuPaper = tikuPaper;
	}

	public CourseExercise getCourseExercise() {
		return courseExercise;
	}

	public void setCourseExercise(CourseExercise courseExercise) {
		this.courseExercise = courseExercise;
	}

	public Integer getHomeworkType() {
		return homeworkType;
	}

	public void setHomeworkType(Integer homeworkType) {
		this.homeworkType = homeworkType;
	}

	public Integer getModuleId() {
		return moduleId;
	}

	public void setModuleId(Integer moduleId) {
		this.moduleId = moduleId;
	}

	// Constructor
	public ClassModuleLesson() {
	}
	
	/**
	 * full Constructor
	 */
	public ClassModuleLesson(Integer id, String lessonName, Integer moduleNoId, Date lessonDate, String scope, String weekType, String lessonTimeStart, String lessonTimeEnd, String lessonHour, String teachers, String assistants, String masters, String teachersName, String assistantsName, String mastersName, Integer classroomId, String classroom, String liveRoom, String remark, String liveCompanyType, String liveroomIdGh, String studentUrlGh, String teacherUrlGh, 
			String assistantUrlGh, String replayUrlGh, Integer delFlag, Integer supportMobile, String liveClassType) {
		setId(id);
		this.lessonName = lessonName;
		this.moduleNoId = moduleNoId;
		this.lessonDate = lessonDate;
		this.scope = scope;
		this.weekType = weekType;
		this.lessonTimeStart = lessonTimeStart;
		this.lessonTimeEnd = lessonTimeEnd;
		this.lessonHour = lessonHour;
		this.teachers = teachers;
		this.assistants = assistants;
		this.masters = masters;
		this.teachersName = teachersName;
		this.assistantsName = assistantsName;
		this.mastersName = mastersName;
		this.classroomId = classroomId;
		this.classroom = classroom;
		this.liveRoom = liveRoom;
		this.remark = remark;
		this.liveCompanyType = liveCompanyType;
		this.liveroomIdGh = liveroomIdGh;
		this.studentUrlGh = studentUrlGh;
		this.teacherUrlGh = teacherUrlGh;
		this.assistantUrlGh = assistantUrlGh;
		this.replayUrlGh = replayUrlGh;
		this.delFlag = delFlag;
		this.supportMobile = supportMobile;
		this.liveClassType = liveClassType;
	}

	// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为ClassModuleLesson可以实现连缀设置属性
	
	public long getDateTime() {
		return dateTime;
	}

	public void setDateTime(long dateTime) {
		this.dateTime = dateTime;
	}

	public String getLessonName() {
		return lessonName;
	}

	public String getModuleNoName() {
		return ModuleNoName;
	}

	public void setModuleNoName(String moduleNoName) {
		ModuleNoName = moduleNoName;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public ClassModuleLesson setLessonName(String lessonName) {
		this.lessonName = lessonName;
		return this;
	}
	
	
	public Integer getModuleNoId() {
		return moduleNoId;
	}

	public ClassModuleLesson setModuleNoId(Integer moduleNoId) {
		this.moduleNoId = moduleNoId;
		return this;
	}
	
	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getLessonDate() {
		return lessonDate;
	}

	public ClassModuleLesson setLessonDate(Date lessonDate) {
		this.lessonDate = lessonDate;
		return this;
	}
	
	
	public String getScope() {
		return scope;
	}

	public ClassModuleLesson setScope(String scope) {
		this.scope = scope;
		return this;
	}
	
	
	public String getWeekType() {
		return weekType;
	}

	public ClassModuleLesson setWeekType(String weekType) {
		this.weekType = weekType;
		return this;
	}
	
	
	public String getLessonTimeStart() {
		return lessonTimeStart;
	}

	public ClassModuleLesson setLessonTimeStart(String lessonTimeStart) {
		this.lessonTimeStart = lessonTimeStart;
		return this;
	}
	
	
	public String getLessonTimeEnd() {
		return lessonTimeEnd;
	}

	public ClassModuleLesson setLessonTimeEnd(String lessonTimeEnd) {
		this.lessonTimeEnd = lessonTimeEnd;
		return this;
	}
	
	
	public String getLessonHour() {
		return lessonHour;
	}

	public ClassModuleLesson setLessonHour(String lessonHour) {
		this.lessonHour = lessonHour;
		return this;
	}
	
	
	public String getTeachers() {
		return teachers;
	}

	public ClassModuleLesson setTeachers(String teachers) {
		this.teachers = teachers;
		return this;
	}
	
	
	public String getAssistants() {
		return assistants;
	}

	public ClassModuleLesson setAssistants(String assistants) {
		this.assistants = assistants;
		return this;
	}
	
	
	public String getMasters() {
		return masters;
	}

	public ClassModuleLesson setMasters(String masters) {
		this.masters = masters;
		return this;
	}
	
	
	public String getTeachersName() {
		return teachersName;
	}

	public ClassModuleLesson setTeachersName(String teachersName) {
		this.teachersName = teachersName;
		return this;
	}
	
	
	public String getAssistantsName() {
		return assistantsName;
	}

	public ClassModuleLesson setAssistantsName(String assistantsName) {
		this.assistantsName = assistantsName;
		return this;
	}
	
	
	public String getMastersName() {
		return mastersName;
	}

	public ClassModuleLesson setMastersName(String mastersName) {
		this.mastersName = mastersName;
		return this;
	}
	
	
	public Integer getClassroomId() {
		return classroomId;
	}

	public ClassModuleLesson setClassroomId(Integer classroomId) {
		this.classroomId = classroomId;
		return this;
	}
	
	
	public String getClassroom() {
		return classroom;
	}

	public ClassModuleLesson setClassroom(String classroom) {
		this.classroom = classroom;
		return this;
	}
	
	
	public String getLiveRoom() {
		return liveRoom;
	}

	public ClassModuleLesson setLiveRoom(String liveRoom) {
		this.liveRoom = liveRoom;
		return this;
	}
	
	
	public String getRemark() {
		return remark;
	}

	public ClassModuleLesson setRemark(String remark) {
		this.remark = remark;
		return this;
	}
	
	
	public String getLiveCompanyType() {
		return liveCompanyType;
	}

	public ClassModuleLesson setLiveCompanyType(String liveCompanyType) {
		this.liveCompanyType = liveCompanyType;
		return this;
	}
	
	
	public String getLiveroomIdGh() {
		return liveroomIdGh;
	}

	public ClassModuleLesson setLiveroomIdGh(String liveroomIdGh) {
		this.liveroomIdGh = liveroomIdGh;
		return this;
	}
	
	
	public String getStudentUrlGh() {
		return studentUrlGh;
	}

	public ClassModuleLesson setStudentUrlGh(String studentUrlGh) {
		this.studentUrlGh = studentUrlGh;
		return this;
	}
	
	
	public String getTeacherUrlGh() {
		return teacherUrlGh;
	}

	public ClassModuleLesson setTeacherUrlGh(String teacherUrlGh) {
		this.teacherUrlGh = teacherUrlGh;
		return this;
	}
	
	
	public String getAssistantUrlGh() {
		return assistantUrlGh;
	}

	public ClassModuleLesson setAssistantUrlGh(String assistantUrlGh) {
		this.assistantUrlGh = assistantUrlGh;
		return this;
	}
	
	
	public String getReplayUrlGh() {
		return replayUrlGh;
	}

	public ClassModuleLesson setReplayUrlGh(String replayUrlGh) {
		this.replayUrlGh = replayUrlGh;
		return this;
	}
	
	
	public Integer getDelFlag() {
		return delFlag;
	}

	public ClassModuleLesson setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
		return this;
	}
	
	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	
	public Integer getChapterFlag() {
		return chapterFlag;
	}

	public void setChapterFlag(Integer chapterFlag) {
		this.chapterFlag = chapterFlag;
	}
	
	public Integer getSupportMobile() {
		return supportMobile;
	}

	public void setSupportMobile(Integer supportMobile) {
		this.supportMobile = supportMobile;
	}

	public String getSupportMobileFlag() {
		return supportMobileFlag;
	}

	public void setSupportMobileFlag(String supportMobileFlag) {
		this.supportMobileFlag = supportMobileFlag;
	}

	public String getDayStatus() {
		return dayStatus;
	}

	public void setDayStatus(String dayStatus) {
		this.dayStatus = dayStatus;
	}

	public String getLiveClassType() {
		return liveClassType;
	}

	public void setLiveClassType(String liveClassType) {
		this.liveClassType = liveClassType;
	}

	public String getLiveClassTypeFlag() {
		return liveClassTypeFlag;
	}

	public void setLiveClassTypeFlag(String liveClassTypeFlag) {
		this.liveClassTypeFlag = liveClassTypeFlag;
	}
	
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getCreator() {
		return creator;
	}

	public void setCreator(Integer creator) {
		this.creator = creator;
	}

	@Override
	public String toString() {
		return "ClassModuleLesson [" + "id=" + getId() + ", lessonName=" + lessonName + ", moduleNoId=" + moduleNoId + ", lessonDate=" + lessonDate + ", scope=" + scope + ", weekType=" + weekType + ", lessonTimeStart=" + lessonTimeStart + ", lessonTimeEnd=" + lessonTimeEnd + ", lessonHour=" + lessonHour + ", teachers=" + teachers + ", assistants=" + assistants + ", masters=" + masters + ", teachersName=" + teachersName + ", assistantsName=" + assistantsName + ", mastersName=" + mastersName + ", classroomId=" + classroomId + ", classroom=" + classroom + ", liveRoom=" + liveRoom + ", remark=" + remark + ", liveCompanyType=" + liveCompanyType + ", liveroomIdGh=" + liveroomIdGh + ", studentUrlGh=" + studentUrlGh + ", teacherUrlGh=" + teacherUrlGh + ", assistantUrlGh=" + assistantUrlGh + ", replayUrlGh=" + replayUrlGh + ", delFlag=" + delFlag + ", status=" + status + ", supportMobile=" + supportMobile + ", supportMobileFlag=" + supportMobileFlag + "]";
	}

	public Integer getBarrage() {
		return barrage;
	}

	public void setBarrage(Integer barrage) {
		this.barrage = barrage;
	}

	public Integer getModetype() {
		return modetype;
	}

	public void setModetype(Integer modetype) {
		this.modetype = modetype;
	}
	
	public Integer getHasHomework() {
		return hasHomework;
	}

	public void setHasHomework(Integer hasHomework) {
		this.hasHomework = hasHomework;
	}

	public Integer getHomeworkId() {
		return homeworkId;
	}

	public void setHomeworkId(Integer homeworkId) {
		this.homeworkId = homeworkId;
	}

	public String getSchoolShortName() {
		return schoolShortName;
	}

	public void setSchoolShortName(String schoolShortName) {
		this.schoolShortName = schoolShortName;
	}

	public Date getLessonDateTime() {
		return lessonDateTime;
	}

	public void setLessonDateTime(Date lessonDateTime) {
		this.lessonDateTime = lessonDateTime;
	}

	public String getBeforeStudyUrl() {
		return beforeStudyUrl;
	}

	public void setBeforeStudyUrl(String beforeStudyUrl) {
		this.beforeStudyUrl = beforeStudyUrl;
	}

	public String getAfterStudyUrl() {
		return afterStudyUrl;
	}

	public void setAfterStudyUrl(String afterStudyUrl) {
		this.afterStudyUrl = afterStudyUrl;
	}
}
