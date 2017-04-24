package com.yuxin.wx.vo.classes;

import java.util.Date;
import java.util.List;

import com.yuxin.wx.common.BaseEntity;


@SuppressWarnings("serial")
public class ClassVideoModuleVo extends BaseEntity {
	
	private Integer classTypeId;
	private Integer moduleId;
	private String name;
	private Integer itemOneId;
	private Integer itemSecondId;
	private Integer totalHour;
	
	public Integer getClassTypeId() {
		return classTypeId;
	}
	public void setClassTypeId(Integer classTypeId) {
		this.classTypeId = classTypeId;
	}
	public Integer getModuleId() {
		return moduleId;
	}
	public void setModuleId(Integer moduleId) {
		this.moduleId = moduleId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public Integer getTotalHour() {
		return totalHour;
	}
	public void setTotalHour(Integer totalHour) {
		this.totalHour = totalHour;
	}

}
