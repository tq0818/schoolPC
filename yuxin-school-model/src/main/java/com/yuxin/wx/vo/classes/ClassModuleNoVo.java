package com.yuxin.wx.vo.classes;

import java.util.Date;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import com.yuxin.wx.util.ShortDateSerializer;

import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:ClassModuleNo
 * @author wang.zx
 * @date 2014-12-5
 */
@SuppressWarnings("serial")
public class ClassModuleNoVo extends BaseEntity {
	
	
	private String	name;		 /* 模块号名称 */ 
	private Integer	itemOneId;		 /* 一级项目 */ 
	private String itemOneName;
	private Integer	itemSecondId;		 /* 二级项目 */ 
	private String itemSecondName;
	private Integer	moduleId;		 /* 班号所属模块id */ 
	private String	examTerm;		 /* 目标考期 */ 
	private String	examTermName;		 /* 目标考期 */ 
	private Date	startDate;		 /* 开课点，即第一次上课日期 */ 
	private String	lessonType;		 /* 班别：周末班；脱产班；晚班；周六班；周日班取字典表数据 */ 
	private String	lessonDay;		 /* 需要排课的天，周一至周日的任意组合，每个周几之间用逗号分开 */ 
	private String	lessonScope;		 /* 上课时段：上午；下午；晚上；全天取字典表数据 */ 
	private String	startTime;		 /* 课次开始时间 */ 
	private String	endTime;		 /* 课次结束时间 */ 
	private Integer	lessonHour;		 /* 每课次课时 */ 
	private String	teachers;		 /* 老师，存老师的id，可以有多个老师，之间用逗号分隔 */ 
	private String	masters;		 /* 班主任，存班主任的id，可以有多个班主任，之间用逗号分隔 */ 
	private String	assistants;		 /* 助教，存助教的id，可以有多个助教，之间用逗号分隔 */ 
	private Integer	schoolId;		 /* 所属分校id，授课方式为面授时使用该字段 */ 
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
	private Integer campusId;
	private String campusName;

	// Constructor
	public ClassModuleNoVo() {
	}
	
	/**
	 * full Constructor
	 */
	public ClassModuleNoVo(Integer id, String name, Integer itemOneId, Integer itemSecondId, Integer moduleId, String examTerm, Date startDate, String lessonType, String lessonDay, String lessonScope, String startTime, String endTime, Integer lessonHour, String teachers, String masters, String assistants, Integer schoolId, String classroom, String liveRoom, String device, Integer planEnrollStudents, Integer enrollYetStudents, String publishStatus, Integer totalHours, String classTeachType, String description, Date createTime, Integer creator, Date updateTime, Integer updator) {
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
		this.schoolId = schoolId;
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
	}

	// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为ClassModuleNo可以实现连缀设置属性
	
	public String getName() {
		return name;
	}

	public ClassModuleNoVo setName(String name) {
		this.name = name;
		return this;
	}
	
	
	public Integer getItemOneId() {
		return itemOneId;
	}

	public ClassModuleNoVo setItemOneId(Integer itemOneId) {
		this.itemOneId = itemOneId;
		return this;
	}
	
	
	public Integer getItemSecondId() {
		return itemSecondId;
	}

	public ClassModuleNoVo setItemSecondId(Integer itemSecondId) {
		this.itemSecondId = itemSecondId;
		return this;
	}
	
	
	public Integer getModuleId() {
		return moduleId;
	}

	public ClassModuleNoVo setModuleId(Integer moduleId) {
		this.moduleId = moduleId;
		return this;
	}
	
	
	public String getExamTerm() {
		return examTerm;
	}

	public ClassModuleNoVo setExamTerm(String examTerm) {
		this.examTerm = examTerm;
		return this;
	}
	
	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getStartDate() {
		return startDate;
	}

	public ClassModuleNoVo setStartDate(Date startDate) {
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

	public Integer getlessonHour() {
		return lessonHour;
	}

	public void setlessonHour(Integer lessonHour) {
		this.lessonHour = lessonHour;
	}

	public String getStartTime() {
		return startTime;
	}

	public ClassModuleNoVo setStartTime(String startTime) {
		this.startTime = startTime;
		return this;
	}
	
	
	public String getEndTime() {
		return endTime;
	}

	public ClassModuleNoVo setEndTime(String endTime) {
		this.endTime = endTime;
		return this;
	}
	
	
	
	
	public String getTeachers() {
		return teachers;
	}

	public ClassModuleNoVo setTeachers(String teachers) {
		this.teachers = teachers;
		return this;
	}
	
	
	public String getMasters() {
		return masters;
	}

	public ClassModuleNoVo setMasters(String masters) {
		this.masters = masters;
		return this;
	}
	
	
	public String getAssistants() {
		return assistants;
	}

	public ClassModuleNoVo setAssistants(String assistants) {
		this.assistants = assistants;
		return this;
	}
	
	
	public Integer getSchoolId() {
		return schoolId;
	}

	public ClassModuleNoVo setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
		return this;
	}
	
	
	public String getClassroom() {
		return classroom;
	}

	public ClassModuleNoVo setClassroom(String classroom) {
		this.classroom = classroom;
		return this;
	}
	
	
	public String getLiveRoom() {
		return liveRoom;
	}

	public ClassModuleNoVo setLiveRoom(String liveRoom) {
		this.liveRoom = liveRoom;
		return this;
	}
	
	
	public String getDevice() {
		return device;
	}

	public ClassModuleNoVo setDevice(String device) {
		this.device = device;
		return this;
	}
	
	
	public Integer getPlanEnrollStudents() {
		return planEnrollStudents;
	}

	public ClassModuleNoVo setPlanEnrollStudents(Integer planEnrollStudents) {
		this.planEnrollStudents = planEnrollStudents;
		return this;
	}
	
	
	public Integer getEnrollYetStudents() {
		return enrollYetStudents;
	}

	public ClassModuleNoVo setEnrollYetStudents(Integer enrollYetStudents) {
		this.enrollYetStudents = enrollYetStudents;
		return this;
	}
	
	
	public String getPublishStatus() {
		return publishStatus;
	}

	public ClassModuleNoVo setPublishStatus(String publishStatus) {
		this.publishStatus = publishStatus;
		return this;
	}
	
	
	public Integer getTotalHours() {
		return totalHours;
	}

	public ClassModuleNoVo setTotalHours(Integer totalHours) {
		this.totalHours = totalHours;
		return this;
	}
	
	
	public String getClassTeachType() {
		return classTeachType;
	}

	public ClassModuleNoVo setClassTeachType(String classTeachType) {
		this.classTeachType = classTeachType;
		return this;
	}
	
	
	public String getDescription() {
		return description;
	}

	public ClassModuleNoVo setDescription(String description) {
		this.description = description;
		return this;
	}
	
	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getCreateTime() {
		return createTime;
	}

	public ClassModuleNoVo setCreateTime(Date createTime) {
		this.createTime = createTime;
		return this;
	}
	
	
	public Integer getCreator() {
		return creator;
	}

	public ClassModuleNoVo setCreator(Integer creator) {
		this.creator = creator;
		return this;
	}
	
	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getUpdateTime() {
		return updateTime;
	}

	public ClassModuleNoVo setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
		return this;
	}
	
	
	public Integer getUpdator() {
		return updator;
	}

	public ClassModuleNoVo setUpdator(Integer updator) {
		this.updator = updator;
		return this;
	}
	
	public String getExamTermName() {
		return examTermName;
	}

	public void setExamTermName(String examTermName) {
		this.examTermName = examTermName;
	}
	
	public void setCampusName(String campusName){
		this.campusName=campusName;
	}
	public String getCampusName(){
		return campusName;
	}

	public String getItemOneName() {
		return itemOneName;
	}

	public void setItemOneName(String itemOneName) {
		this.itemOneName = itemOneName;
	}

	public String getItemSecondName() {
		return itemSecondName;
	}

	public void setItemSecondName(String itemSecondName) {
		this.itemSecondName = itemSecondName;
	}

	public Integer getCampusId() {
		return campusId;
	}

	public void setCampusId(Integer campusId) {
		this.campusId = campusId;
	}

	@Override
	public String toString() {
		return "ClassModuleNo [" + "id=" + getId() + ", name=" + name + ", itemOneId=" + itemOneId + ", itemSecondId=" + itemSecondId + ", moduleId=" + moduleId + ", examTerm=" + examTerm + ", startDate=" + startDate + ", classType=" + lessonType + ", classDay=" + lessonDay + ", classScope=" + lessonScope + ", startTime=" + startTime + ", endTime=" + endTime + ", classHour=" + lessonHour + ", teachers=" + teachers + ", masters=" + masters + ", assistants=" + assistants + ", schoolId=" + schoolId + ", classroom=" + classroom + ", liveRoom=" + liveRoom + ", device=" + device + ", planEnrollStudents=" + planEnrollStudents + ", enrollYetStudents=" + enrollYetStudents + ", publishStatus=" + publishStatus + ", totalHours=" + totalHours + ", classTeachType=" + classTeachType + ", description=" + description + ", createTime=" + createTime + ", creator=" + creator + ", updateTime=" + updateTime + ", updator=" + updator +  "]";
	}
}
