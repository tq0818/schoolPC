package com.yuxin.wx.model.course;

import java.util.Date;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import com.yuxin.wx.util.ShortDateSerializer;

import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:VideoCourseNote
 * 
 * @author wang.zx
 * @date 2015-9-29
 */
@SuppressWarnings("serial")
public class VideoCourseNote extends BaseEntity {
	
	
	private String	notesContent;		 /* 笔记内容 */ 
	private String	notesTime;		 /* 当前比较播放时间 */ 
	private Integer	videoLectureId;		 /* 笔记对应的课次 */ 
	private Integer	userId;		 /* 笔记所属人 */ 
	private Integer	schoolId;		
	private Integer	companyId;		
	private Date	createTime;		 /* 创建时间 */ 
	private Integer classTypeId;
	private String timeString;

	private String username;		/* 用户名*/	
	private String className;		/*课程名*/
	private String lecName;			/* 章节名*/
	private Integer moduleId;		/*模块 id*/
	private String noteContent;		/* 笔记内容备用*/
	// Constructor
	public VideoCourseNote() {
	}
	
	/**
	 * full Constructor
	 */
	public VideoCourseNote(Integer id, String notesContent, String notesTime, Integer videoLectureId, Integer userId, Integer schoolId, Integer companyId, Date createTime) {
		setId(id);
		this.notesContent = notesContent;
		this.notesTime = notesTime;
		this.videoLectureId = videoLectureId;
		this.userId = userId;
		this.schoolId = schoolId;
		this.companyId = companyId;
		this.createTime = createTime;
	}

	// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为VideoCourseNote可以实现连缀设置属性
	
	public String getNotesContent() {
		return notesContent;
	}

	public VideoCourseNote setNotesContent(String notesContent) {
		this.notesContent = notesContent;
		return this;
	}
	
	
	public String getNotesTime() {
		return notesTime;
	}

	public VideoCourseNote setNotesTime(String notesTime) {
		this.notesTime = notesTime;
		return this;
	}
	
	
	public Integer getVideoLectureId() {
		return videoLectureId;
	}

	public VideoCourseNote setVideoLectureId(Integer videoLectureId) {
		this.videoLectureId = videoLectureId;
		return this;
	}
	
	
	public Integer getUserId() {
		return userId;
	}

	public VideoCourseNote setUserId(Integer userId) {
		this.userId = userId;
		return this;
	}
	
	
	public Integer getSchoolId() {
		return schoolId;
	}

	public VideoCourseNote setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
		return this;
	}
	
	
	public Integer getCompanyId() {
		return companyId;
	}

	public VideoCourseNote setCompanyId(Integer companyId) {
		this.companyId = companyId;
		return this;
	}
	
	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getCreateTime() {
		return createTime;
	}

	public VideoCourseNote setCreateTime(Date createTime) {
		this.createTime = createTime;
		return this;
	}
	
	@Override
	public String toString() {
		return "VideoCourseNote [" + "id=" + getId() + ", notesContent=" + notesContent + ", notesTime=" + notesTime + ", videoLectureId=" + videoLectureId + ", userId=" + userId + ", schoolId=" + schoolId + ", companyId=" + companyId + ", createTime=" + createTime +  "]";
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Integer getClassTypeId() {
		return classTypeId;
	}

	public void setClassTypeId(Integer classTypeId) {
		this.classTypeId = classTypeId;
	}

	public String getTimeString() {
		return timeString;
	}

	public void setTimeString(String timeString) {
		this.timeString = timeString;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getLecName() {
		return lecName;
	}

	public void setLecName(String lecName) {
		this.lecName = lecName;
	}

	public Integer getModuleId() {
		return moduleId;
	}

	public void setModuleId(Integer moduleId) {
		this.moduleId = moduleId;
	}

	public String getNoteContent() {
		return noteContent;
	}

	public void setNoteContent(String noteContent) {
		this.noteContent = noteContent;
	}
}
