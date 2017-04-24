package com.yuxin.wx.vo.student;

import java.util.List;

import com.yuxin.wx.model.classes.ClassModule;
import com.yuxin.wx.model.course.CourseVideo;
import com.yuxin.wx.model.student.StudentAgentMaterial;
import com.yuxin.wx.model.student.StudentFeeStage;

public class TestVo {
	
	private List<ClassModule> modules; /*班型所包含的模块 */
	private List<CourseVideo> videos; /*班型所包含的视频*/
	private List<StudentFeeStage> stage;/*分期信息*/
	private List<StudentAgentMaterial> material;/*资料*/
	public List<ClassModule> getModules() {
		return modules;
	}
	public void setModules(List<ClassModule> modules) {
		this.modules = modules;
	}
	public List<CourseVideo> getVideos() {
		return videos;
	}
	public void setVideos(List<CourseVideo> videos) {
		this.videos = videos;
	}
	public List<StudentFeeStage> getStage() {
		return stage;
	}
	public void setStage(List<StudentFeeStage> stage) {
		this.stage = stage;
	}
	public List<StudentAgentMaterial> getMaterial() {
		return material;
	}
	public void setMaterial(List<StudentAgentMaterial> material) {
		this.material = material;
	}
	
	
	

}
