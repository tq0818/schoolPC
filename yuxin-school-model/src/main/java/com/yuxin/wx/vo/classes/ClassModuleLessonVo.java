package com.yuxin.wx.vo.classes;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.yuxin.wx.util.ShortDateSerializer;
import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:ClassModuleLesson
 * @author wang.zx
 * @date 2014-12-5
 */
@SuppressWarnings("serial")
public class ClassModuleLessonVo extends BaseEntity {
	
	
	private String	lessonName;		 /* 课次名称 */ 
	private Integer	moduleNoId;		 /* 班号id */ 
	private Date	lessonDate;		 /* 上课日期 */ 
	private String	scope;		 /* 上课时段：上午；下午；晚上；全天*/
	private Integer	itemOneId;		 /* 一级项目id */ 
	private Integer	itemSecondId;		 /* 二级项目id */ 
	private String	itemOneName;		 /* 一级项目name */ 
	private String	itemSecondName;		 /* 二级项目name */
	private Integer	teacherId;		 /* 二级项目id */ 
	private String	teachersName;		 /* 二级项目name */
	private String	weekType;		 /* 星期：上课日期所对应的周几 */ 
	private String	lessonTimeStart;		 /* 课次开始时间 */ 
	private String	lessonTimeEnd;		 /* 课次结束日期 */ 
	private String	lessonHour;		 /* 课次所需课时数 */ 
	private String	teachers;		 /* 老师，存老师的id，可以有多个老师，之间用逗号分隔 */ 
	private String	assistants;		 /* 助教，存助教的id，可以有多个助教，之间用逗号分隔 */ 
	private String	masters;		 /* 班主任，存班主任的id，可以有多个班主任，之间用逗号分隔 */ 
	private Integer classroomId;	 /* 教室ID */ 
	private String	classroom;		 /* 教室名字，面授课使用该字段 */ 
	private String	liveRoom;		 /* 直播教室id，直播课使用该字段 */ 
	private String	remark;		 /* 备注 */ 
	private Integer delFlag;		/* 删除标示 */
	private String startTime;
	private String endTime;
	private String moduleName;
	private String moduleNoName;
	private Integer schoolId;
	private String schoolName;
	private String isOpen;
	private Integer barrage;		/* 弹幕*/
	private Integer modetype;		/* 模式*/
	
	public String getIsOpen() {
		return isOpen;
	}
	public void setIsOpen(String isOpen) {
		this.isOpen = isOpen;
	}
	public String getLessonName() {
		return lessonName;
	}
	public void setLessonName(String lessonName) {
		this.lessonName = lessonName;
	}
	public Integer getModuleNoId() {
		return moduleNoId;
	}
	public void setModuleNoId(Integer moduleNoId) {
		this.moduleNoId = moduleNoId;
	}
	public Date getLessonDate() {
		return lessonDate;
	}
	public void setLessonDate(Date lessonDate) {
		this.lessonDate = lessonDate;
	}
	public String getScope() {
		return scope;
	}
	public void setScope(String scope) {
		this.scope = scope;
	}
	public Integer getItemOneId() {
		return itemOneId;
	}
	public void setItemOneId(Integer itemOneId) {
		this.itemOneId = itemOneId;
	}
	public Integer getItemSecondId() {
		return itemSecondId;
	}
	public void setItemSecondId(Integer itemSecondId) {
		this.itemSecondId = itemSecondId;
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
	public Integer getTeacherId() {
		return teacherId;
	}
	public void setTeacherId(Integer teacherId) {
		this.teacherId = teacherId;
	}
	public String getteachersName() {
		return teachersName;
	}
	public void setteachersName(String teachersName) {
		this.teachersName = teachersName;
	}
	public String getWeekType() {
		return weekType;
	}
	public void setWeekType(String weekType) {
		this.weekType = weekType;
	}
	public String getLessonTimeStart() {
		return lessonTimeStart;
	}
	public void setLessonTimeStart(String lessonTimeStart) {
		this.lessonTimeStart = lessonTimeStart;
	}
	public String getLessonTimeEnd() {
		return lessonTimeEnd;
	}
	public void setLessonTimeEnd(String lessonTimeEnd) {
		this.lessonTimeEnd = lessonTimeEnd;
	}
	public String getLessonHour() {
		return lessonHour;
	}
	public void setLessonHour(String lessonHour) {
		this.lessonHour = lessonHour;
	}
	public String getTeachers() {
		return teachers;
	}
	public void setTeachers(String teachers) {
		this.teachers = teachers;
	}
	public String getAssistants() {
		return assistants;
	}
	public void setAssistants(String assistants) {
		this.assistants = assistants;
	}
	public String getMasters() {
		return masters;
	}
	public void setMasters(String masters) {
		this.masters = masters;
	}
	public Integer getClassroomId() {
		return classroomId;
	}
	public void setClassroomId(Integer classroomId) {
		this.classroomId = classroomId;
	}
	public String getClassroom() {
		return classroom;
	}
	public void setClassroom(String classroom) {
		this.classroom = classroom;
	}
	public String getLiveRoom() {
		return liveRoom;
	}
	public void setLiveRoom(String liveRoom) {
		this.liveRoom = liveRoom;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Integer getDelFlag() {
		return delFlag;
	}
	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getModuleName() {
		return moduleName;
	}
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
	public String getModuleNoName() {
		return moduleNoName;
	}
	public void setModuleNoName(String moduleNoName) {
		this.moduleNoName = moduleNoName;
	}
	public Integer getSchoolId() {
		return schoolId;
	}
	public void setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
	}
	public String getSchoolName() {
		return schoolName;
	}
	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}
	public ClassModuleLessonVo(String lessonName, Integer moduleNoId,
			Date lessonDate, String scope, Integer itemOneId,
			Integer itemSecondId, String itemOneName, String itemSecondName,
			Integer teacherId, String teachersName, String weekType,
			String lessonTimeStart, String lessonTimeEnd, String lessonHour,
			String teachers, String assistants, String masters,
			Integer classroomId, String classroom, String liveRoom,
			String remark, Integer delFlag, String startTime, String endTime,
			String moduleName, String moduleNoName, Integer schoolId,
			String schoolName, String isOpen) {
		super();
		this.lessonName = lessonName;
		this.moduleNoId = moduleNoId;
		this.lessonDate = lessonDate;
		this.scope = scope;
		this.itemOneId = itemOneId;
		this.itemSecondId = itemSecondId;
		this.itemOneName = itemOneName;
		this.itemSecondName = itemSecondName;
		this.teacherId = teacherId;
		this.teachersName = teachersName;
		this.weekType = weekType;
		this.lessonTimeStart = lessonTimeStart;
		this.lessonTimeEnd = lessonTimeEnd;
		this.lessonHour = lessonHour;
		this.teachers = teachers;
		this.assistants = assistants;
		this.masters = masters;
		this.classroomId = classroomId;
		this.classroom = classroom;
		this.liveRoom = liveRoom;
		this.remark = remark;
		this.delFlag = delFlag;
		this.startTime = startTime;
		this.endTime = endTime;
		this.moduleName = moduleName;
		this.moduleNoName = moduleNoName;
		this.schoolId = schoolId;
		this.schoolName = schoolName;
		this.isOpen = isOpen;
	}
	public ClassModuleLessonVo() {
		super();
	}
	@Override
	public String toString() {
		return "ClassModuleLessonVo [lessonName=" + lessonName
				+ ", moduleNoId=" + moduleNoId + ", lessonDate=" + lessonDate
				+ ", scope=" + scope + ", itemOneId=" + itemOneId
				+ ", itemSecondId=" + itemSecondId + ", itemOneName="
				+ itemOneName + ", itemSecondName=" + itemSecondName
				+ ", teacherId=" + teacherId + ", teachersName=" + teachersName
				+ ", weekType=" + weekType + ", lessonTimeStart="
				+ lessonTimeStart + ", lessonTimeEnd=" + lessonTimeEnd
				+ ", lessonHour=" + lessonHour + ", teachers=" + teachers
				+ ", assistants=" + assistants + ", masters=" + masters
				+ ", classroomId=" + classroomId + ", classroom=" + classroom
				+ ", liveRoom=" + liveRoom + ", remark=" + remark
				+ ", delFlag=" + delFlag + ", startTime=" + startTime
				+ ", endTime=" + endTime + ", moduleName=" + moduleName
				+ ", moduleNoName=" + moduleNoName + ", schoolId=" + schoolId
				+ ", schoolName=" + schoolName + ", isOpen=" + isOpen + "]";
	}
	public String getTeachersName() {
		return teachersName;
	}
	public void setTeachersName(String teachersName) {
		this.teachersName = teachersName;
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
	
}
