package com.yuxin.wx.vo.homework;

import com.yuxin.wx.model.homework.Homework;

public class HomeWorkVo  extends Homework {
	private String classType;			//课程类型
	private Integer moduleId;			//模块id
	private String classModuleNoName;	//班号名称
	private Integer morl;			//直播的班号的id、录播的模块id
	
	private Integer stuNum;				//学员数量
	private Integer commitedHomeWork;	//已交作业数量
	private Integer notCommitedHomeWork;//未交作业数量
	private Integer readedHomeWork;		//已批阅作业的数量
	private Integer notReadedHomeWork;	//未批阅作业数量
	
	private Integer homeworkStatus;		//作业状态 0未留作业 1未购买 2批阅完成 3未批阅
	
	
	public Integer getModuleId() {
		return moduleId;
	}
	public void setModuleId(Integer moduleId) {
		this.moduleId = moduleId;
	}
	
	public Integer getMorl() {
		return morl;
	}
	public void setMorl(Integer morl) {
		this.morl = morl;
	}
	public Integer getNotCommitedHomeWork() {
		return notCommitedHomeWork;
	}
	public void setNotCommitedHomeWork(Integer notCommitedHomeWork) {
		this.notCommitedHomeWork = notCommitedHomeWork;
	}
	public Integer getHomeworkStatus() {
		return homeworkStatus;
	}
	public void setHomeworkStatus(Integer homeworkStatus) {
		this.homeworkStatus = homeworkStatus;
	}
	public String getClassType() {
		return classType;
	}
	public void setClassType(String classType) {
		this.classType = classType;
	}
	public String getClassModuleNoName() {
		return classModuleNoName;
	}
	public void setClassModuleNoName(String classModuleNoName) {
		this.classModuleNoName = classModuleNoName;
	}
	public Integer getStuNum() {
		return stuNum;
	}
	public void setStuNum(Integer stuNum) {
		this.stuNum = stuNum;
	}
	public Integer getCommitedHomeWork() {
		return commitedHomeWork;
	}
	public void setCommitedHomeWork(Integer commitedHomeWork) {
		this.commitedHomeWork = commitedHomeWork;
	}
	public Integer getReadedHomeWork() {
		return readedHomeWork;
	}
	public void setReadedHomeWork(Integer readedHomeWork) {
		this.readedHomeWork = readedHomeWork;
	}
	public Integer getNotReadedHomeWork() {
		return notReadedHomeWork;
	}
	public void setNotReadedHomeWork(Integer notReadedHomeWork) {
		this.notReadedHomeWork = notReadedHomeWork;
	}
	
	
	
	
}
