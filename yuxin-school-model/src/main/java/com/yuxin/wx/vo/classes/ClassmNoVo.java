package com.yuxin.wx.vo.classes;

import java.util.Date;
import java.util.List;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.yuxin.wx.model.classes.ClassModuleLesson;
import com.yuxin.wx.util.ShortDateSerializer;
import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:ClassModuleNo
 * @author wang.zx
 * @date 2014-12-5
 */
@SuppressWarnings("serial")
public class ClassmNoVo extends BaseEntity {
	
	
	private String	name;		 /* 模块号名称 */ 
	private Integer	itemOneId;		 /* 一级项目 */ 
	private Integer	itemSecondId;		 /* 二级项目 */ 
	private Integer	moduleId;		 /* 班号所属模块id */ 
	private String	examTerm;		 /* 目标考期 */ 
	private Date	startDate;		 /* 开课点，即第一次上课日期 */ 
	private String	lessonType;		 /* 班别：周末班；脱产班；晚班；周六班；周日班取字典表数据 */ 
	private String	lessonDay;		 /* 需要排课的天，周一至周日的任意组合，每个周几之间用逗号分开 */ 
	private String	lessonScope;		 /* 上课时段：上午；下午；晚上；全天取字典表数据 */ 
	private String	startTime;		 /* 课次开始时间 */ 
	private String	endTime;		 /* 课次结束时间 */ 
	private String	lessonHour;		 /* 每课次课时 */ 
	private String	teachers;		 /* 老师，存老师的id，可以有多个老师，之间用逗号分隔 */ 
	private String	masters;		 /* 班主任，存班主任的id，可以有多个班主任，之间用逗号分隔 */ 
	private String	assistants;		 /* 助教，存助教的id，可以有多个助教，之间用逗号分隔 */ 
	private Integer	campusId;		 /* 所属分校id，授课方式为面授时使用该字段 */ 
	private String	classroom;		 /* 上课教室，授课方式为面授时使用该字段 */ 
	private String	liveRoom;		 /* 直播教室，直播课时使用该字段 */ 
	private String	device;		 /* 上课所需要用的辅助设备，字典表数据投影仪；投影幕；电脑；白板；扩音器；实物投影仪；音频线；录音笔 */ 
	private Integer	planEnrollStudents;		 /* 计划招生人数 */ 
	private Integer	enrollYetStudents;		 /* 实际招生人数 */ 
	private String	publishStatus;		 /* 发布状态：已创建；可售；已下架取字典表数据 */ 
	private Integer	totalHours;		 /* 班号总课时 */ 
	private String	classTeachType;		 /* 授课方式：面授；直播取字典表数据 */ 
	private String	description;		 /* 班号描述 */ 
	private Date	createTime;		
	private Integer	creator;		
	private Date	updateTime;		
	private Integer	updator;
	private Integer companyId;   /* 公司Id */
	private Integer delFlag;   /* 是否删除 0:未删除  1:已删除 */
	private Integer lessonTotal; /* 班号对应的总课次 */
	private Integer lessonPlan; /* 已经安排过的课次 */
	
	private Integer classTypeId;	//所属班型
	private Integer schoolId;	
	
	private List<ClassModuleLesson> classModuleLessons;
	
	// Constructor
	public ClassmNoVo() {
	}
	
	/**
	 * full Constructor
	 */
	public ClassmNoVo(Integer id, String name, Integer itemOneId, Integer itemSecondId, Integer moduleId, String examTerm, Date startDate, String lessonType, String lessonDay, String lessonScope, String startTime, String endTime, String lessonHour, String teachers, String masters, String assistants, Integer campusId, String classroom, String liveRoom, String device, Integer planEnrollStudents, Integer enrollYetStudents, String publishStatus, 
			Integer totalHours, String classTeachType, String description, Date createTime, Integer creator, Date updateTime, Integer updator, Integer companyId, Integer delFlag, Integer lessonTotal, Integer lessonPlan ) {
		setId(id);
		this.name = name;
		this.itemOneId = itemOneId;
		this.itemSecondId = itemSecondId;
		this.moduleId = moduleId;
		this.examTerm = examTerm;
		this.startDate = startDate;
		this.lessonType = lessonType;
		this.lessonDay = lessonDay;
		this.lessonScope = lessonScope;
		this.startTime = startTime;
		this.endTime = endTime;
		this.lessonHour = lessonHour;
		this.teachers = teachers;
		this.masters = masters;
		this.assistants = assistants;
		this.campusId = campusId;
		this.classroom = classroom;
		this.liveRoom = liveRoom;
		this.device = device;
		this.planEnrollStudents = planEnrollStudents;
		this.enrollYetStudents = enrollYetStudents;
		this.publishStatus = publishStatus;
		this.totalHours = totalHours;
		this.classTeachType = classTeachType;
		this.description = description;
		this.createTime = createTime;
		this.creator = creator;
		this.updateTime = updateTime;
		this.updator = updator;
		this.companyId = companyId;
		this.delFlag = delFlag;
		this.lessonTotal = lessonTotal;
		this.lessonPlan = lessonPlan;
	}

	// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为ClassModuleNo可以实现连缀设置属性
	
	public String getName() {
		return name;
	}

	public ClassmNoVo setName(String name) {
		this.name = name;
		return this;
	}
	
	
	public Integer getItemOneId() {
		return itemOneId;
	}

	public ClassmNoVo setItemOneId(Integer itemOneId) {
		this.itemOneId = itemOneId;
		return this;
	}
	
	
	public Integer getItemSecondId() {
		return itemSecondId;
	}

	public ClassmNoVo setItemSecondId(Integer itemSecondId) {
		this.itemSecondId = itemSecondId;
		return this;
	}
	
	
	public Integer getModuleId() {
		return moduleId;
	}

	public ClassmNoVo setModuleId(Integer moduleId) {
		this.moduleId = moduleId;
		return this;
	}
	
	
	public String getExamTerm() {
		return examTerm;
	}

	public ClassmNoVo setExamTerm(String examTerm) {
		this.examTerm = examTerm;
		return this;
	}
	
	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getStartDate() {
		return startDate;
	}

	public ClassmNoVo setStartDate(Date startDate) {
		this.startDate = startDate;
		return this;
	}
	
	public String getlessonType() {
		return lessonType;
	}

	public void setlessonType(String lessonType) {
		this.lessonType = lessonType;
	}

	public String getlessonDay() {
		return lessonDay;
	}

	public void setlessonDay(String lessonDay) {
		this.lessonDay = lessonDay;
	}

	public String getlessonScope() {
		return lessonScope;
	}

	public void setlessonScope(String lessonScope) {
		this.lessonScope = lessonScope;
	}

	public String getStartTime() {
		return startTime;
	}

	public ClassmNoVo setStartTime(String startTime) {
		this.startTime = startTime;
		return this;
	}
	
	
	public String getEndTime() {
		return endTime;
	}

	public ClassmNoVo setEndTime(String endTime) {
		this.endTime = endTime;
		return this;
	}
	
	
	
	
	public String getTeachers() {
		return teachers;
	}

	public ClassmNoVo setTeachers(String teachers) {
		this.teachers = teachers;
		return this;
	}
	
	
	public String getMasters() {
		return masters;
	}

	public ClassmNoVo setMasters(String masters) {
		this.masters = masters;
		return this;
	}
	
	
	public String getAssistants() {
		return assistants;
	}

	public ClassmNoVo setAssistants(String assistants) {
		this.assistants = assistants;
		return this;
	}
	
	
	public Integer getClassTypeId() {
		return classTypeId;
	}

	public void setClassTypeId(Integer classTypeId) {
		this.classTypeId = classTypeId;
	}

	public Integer getCampusId() {
		return campusId;
	}

	public ClassmNoVo setCampusId(Integer campusId) {
		this.campusId = campusId;
		return this;
	}
	
	
	public String getClassroom() {
		return classroom;
	}

	public ClassmNoVo setClassroom(String classroom) {
		this.classroom = classroom;
		return this;
	}
	
	
	public String getLiveRoom() {
		return liveRoom;
	}

	public ClassmNoVo setLiveRoom(String liveRoom) {
		this.liveRoom = liveRoom;
		return this;
	}
	
	
	public String getDevice() {
		return device;
	}

	public ClassmNoVo setDevice(String device) {
		this.device = device;
		return this;
	}
	
	
	public Integer getPlanEnrollStudents() {
		return planEnrollStudents;
	}

	public ClassmNoVo setPlanEnrollStudents(Integer planEnrollStudents) {
		this.planEnrollStudents = planEnrollStudents;
		return this;
	}
	
	
	public Integer getEnrollYetStudents() {
		return enrollYetStudents;
	}

	public ClassmNoVo setEnrollYetStudents(Integer enrollYetStudents) {
		this.enrollYetStudents = enrollYetStudents;
		return this;
	}
	
	
	public String getPublishStatus() {
		return publishStatus;
	}

	public ClassmNoVo setPublishStatus(String publishStatus) {
		this.publishStatus = publishStatus;
		return this;
	}
	
	
	public Integer getTotalHours() {
		return totalHours;
	}

	public ClassmNoVo setTotalHours(Integer totalHours) {
		this.totalHours = totalHours;
		return this;
	}
	
	
	public String getClassTeachType() {
		return classTeachType;
	}

	public ClassmNoVo setClassTeachType(String classTeachType) {
		this.classTeachType = classTeachType;
		return this;
	}
	
	
	public String getDescription() {
		return description;
	}

	public ClassmNoVo setDescription(String description) {
		this.description = description;
		return this;
	}
	
	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getCreateTime() {
		return createTime;
	}

	public ClassmNoVo setCreateTime(Date createTime) {
		this.createTime = createTime;
		return this;
	}
	
	
	public Integer getCreator() {
		return creator;
	}

	public ClassmNoVo setCreator(Integer creator) {
		this.creator = creator;
		return this;
	}
	
	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getUpdateTime() {
		return updateTime;
	}

	public ClassmNoVo setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
		return this;
	}
	
	
	public Integer getUpdator() {
		return updator;
	}

	public ClassmNoVo setUpdator(Integer updator) {
		this.updator = updator;
		return this;
	}
	
	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	public Integer getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}

	public Integer getLessonTotal() {
		return lessonTotal;
	}

	public void setLessonTotal(Integer lessonTotal) {
		this.lessonTotal = lessonTotal;
	}

	public Integer getLessonPlan() {
		return lessonPlan;
	}

	public void setLessonPlan(Integer lessonPlan) {
		this.lessonPlan = lessonPlan;
	}

	public String getLessonHour() {
		return lessonHour;
	}

	public void setLessonHour(String lessonHour) {
		this.lessonHour = lessonHour;
	}

	@Override
	public String toString() {
		return "ClassModuleNo [" + "id=" + getId() + ", name=" + name + ", itemOneId=" + itemOneId + ", itemSecondId=" + itemSecondId + ", moduleId=" + moduleId + ", examTerm=" + examTerm + ", startDate=" + startDate + ", lessonType=" + lessonType + ", lessonDay=" + lessonDay + ", lessonScope=" + lessonScope + ", startTime=" + startTime + ", endTime=" + endTime + ", lessonHour=" + lessonHour + ", teachers=" + teachers + ", masters=" + masters + ", assistants=" + assistants + ", campusId=" + campusId + ", classroom=" + classroom + ", liveRoom=" + liveRoom + ", device=" + device + ", planEnrollStudents=" + 
	planEnrollStudents + ", enrollYetStudents=" + enrollYetStudents + ", publishStatus=" + publishStatus + ", totalHours=" + totalHours + ", classTeachType=" + classTeachType + ", description=" + description + ", createTime=" + createTime + ", creator=" + creator + ", updateTime=" + updateTime + ", updator=" + updator + ", companyId=" + companyId + ", delFlag=" + delFlag + ", lessonTotal=" + lessonTotal + ", lessonPlan=" + lessonPlan + "]";
	}

	public Integer getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
	}

	public List<ClassModuleLesson> getClassModuleLessons() {
		return classModuleLessons;
	}

	public void setClassModuleLessons(List<ClassModuleLesson> classModuleLessons) {
		this.classModuleLessons = classModuleLessons;
	}
	
	
}
