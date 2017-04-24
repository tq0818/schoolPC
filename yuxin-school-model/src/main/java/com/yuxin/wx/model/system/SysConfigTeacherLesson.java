package com.yuxin.wx.model.system;


import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:SysConfigTeacherLesson
 * @author wang.zx
 * @date 2014-12-5
 */
@SuppressWarnings("serial")
public class SysConfigTeacherLesson extends BaseEntity {
	
	
	private Integer	teacherId;		
	private Integer	itemOneId;		 /* 所属一级项目 */ 
	private Integer	itemSecondId;		 /* 所属二级项目 */ 
	private Integer	moduleId;		 /* 授课模块 */ 
	private Double	lessonFee;		 /* 课时费 */ 
	
	private String itemOneName;

	// Constructor
	public SysConfigTeacherLesson() {
	}
	
	/**
	 * full Constructor
	 */
	public SysConfigTeacherLesson(Integer id, Integer teacherId, Integer itemOneId, Integer itemSecondId, Integer moduleId, Double lessonFee) {
		setId(id);
		this.teacherId = teacherId;
		this.itemOneId = itemOneId;
		this.itemSecondId = itemSecondId;
		this.moduleId = moduleId;
		this.lessonFee = lessonFee;
	}

	// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为SysConfigTeacherLesson可以实现连缀设置属性
	
	public Integer getTeacherId() {
		return teacherId;
	}

	public SysConfigTeacherLesson setTeacherId(Integer teacherId) {
		this.teacherId = teacherId;
		return this;
	}
	
	
	public Integer getItemOneId() {
		return itemOneId;
	}

	public SysConfigTeacherLesson setItemOneId(Integer itemOneId) {
		this.itemOneId = itemOneId;
		return this;
	}
	
	
	public Integer getItemSecondId() {
		return itemSecondId;
	}

	public SysConfigTeacherLesson setItemSecondId(Integer itemSecondId) {
		this.itemSecondId = itemSecondId;
		return this;
	}
	
	
	public Integer getModuleId() {
		return moduleId;
	}

	public SysConfigTeacherLesson setModuleId(Integer moduleId) {
		this.moduleId = moduleId;
		return this;
	}
	
	
	public Double getLessonFee() {
		return lessonFee;
	}

	public SysConfigTeacherLesson setLessonFee(Double lessonFee) {
		this.lessonFee = lessonFee;
		return this;
	}
	
	public String getItemOneName() {
		return itemOneName;
	}

	public void setItemOneName(String itemOneName) {
		this.itemOneName = itemOneName;
	}

	@Override
	public String toString() {
		return "SysConfigTeacherLesson [" + "id=" + getId() + ", teacherId=" + teacherId + ", itemOneId=" + itemOneId + ", itemSecondId=" + itemSecondId + ", moduleId=" + moduleId + ", lessonFee=" + lessonFee +  "]";
	}
}
