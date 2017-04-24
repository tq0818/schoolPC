package com.yuxin.wx.vo.classes;

import java.util.List;

import com.yuxin.wx.model.classes.ClassModuleLesson;
import com.yuxin.wx.model.classes.ClassModuleNo;

public class ModuleNoAndLessonVo {
	
	private ClassModuleNo moduleNo;
	private List<ClassModuleLesson> lesson;
	
	public ClassModuleNo getModuleNo() {
		return moduleNo;
	}
	public void setModuleNo(ClassModuleNo moduleNo) {
		this.moduleNo = moduleNo;
	}
	public List<ClassModuleLesson> getLesson() {
		return lesson;
	}
	public void setLesson(List<ClassModuleLesson> lesson) {
		this.lesson = lesson;
	}
	
}
