package com.yuxin.wx.vo.system;


import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:SysConfigTeacherLesson
 * @author wang.zx
 * @date 2014-12-5
 */
@SuppressWarnings("serial")
public class SysConfigTeacherLessonVo extends BaseEntity {
	private Integer	teacherId;		
	private Integer	itemOneId;		 /* 所属一级项目 */ 
	private Integer	itemSecondId;		 /* 所属二级项目 */ 
	private String	itemOneName;		 /* 所属一级项目 名称*/ 
	private String	itemSecondName;		 /* 所属二级项目 名称*/ 
	private Integer	moduleId;		 /* 授课模块 */ 
	private String	moduleName;		 /* 授课模块 */ 
	private Double	lessonFee;		 /* 课时费 */ 
	private Integer lessonCount;    /* 模块对应的可次数量*/
	private String teachMethod;   /* 模块授课方式*/
	private Integer totalHours;   /* 模块对应的总课时 */

	// Constructor
	public SysConfigTeacherLessonVo() {
	}

	public SysConfigTeacherLessonVo(Integer teacherId, Integer itemOneId,
			Integer itemSecondId, String itemOneName, String itemSecondName,
			Integer moduleId, String moduleName, Double lessonFee, Integer lessonCount, String teachMethod, Integer totalHours) {
		super();
		this.teacherId = teacherId;
		this.itemOneId = itemOneId;
		this.itemSecondId = itemSecondId;
		this.itemOneName = itemOneName;
		this.itemSecondName = itemSecondName;
		this.moduleId = moduleId;
		this.moduleName = moduleName;
		this.lessonFee = lessonFee;
		this.lessonCount = lessonCount;
		this.teachMethod = teachMethod;
		this.totalHours = totalHours;
	}

	public Integer getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(Integer teacherId) {
		this.teacherId = teacherId;
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

	public Integer getModuleId() {
		return moduleId;
	}

	public void setModuleId(Integer moduleId) {
		this.moduleId = moduleId;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public Double getLessonFee() {
		return lessonFee;
	}

	public void setLessonFee(Double lessonFee) {
		this.lessonFee = lessonFee;
	}

	public Integer getLessonCount() {
		return lessonCount;
	}

	public void setLessonCount(Integer lessonCount) {
		this.lessonCount = lessonCount;
	}

	public String getTeachMethod() {
		return teachMethod;
	}

	public void setTeachMethod(String teachMethod) {
		this.teachMethod = teachMethod;
	}

	public Integer getTotalHours() {
		return totalHours;
	}

	public void setTotalHours(Integer totalHours) {
		this.totalHours = totalHours;
	}

	@Override
	public String toString() {
		return "SysConfigTeacherLessonVo [teacherId=" + teacherId
				+ ", itemOneId=" + itemOneId + ", itemSecondId=" + itemSecondId
				+ ", itemOneName=" + itemOneName + ", itemSecondName="
				+ itemSecondName + ", moduleId=" + moduleId + ", moduleName="
				+ moduleName + ", lessonFee=" + lessonFee + ", lessonCount=" + lessonCount  
				+ ", teachMethod=" + teachMethod + ", totalHours=" + totalHours +"]";
	}
}
