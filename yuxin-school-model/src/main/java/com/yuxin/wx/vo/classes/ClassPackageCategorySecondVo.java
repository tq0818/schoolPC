package com.yuxin.wx.vo.classes;

import java.util.List;

import com.yuxin.wx.common.BaseEntity;
import com.yuxin.wx.model.classes.ClassPackageCategory;

public class ClassPackageCategorySecondVo extends BaseEntity {
	
	private String name; /* 二级分类名*/
	private String code; /* code*/
	private List<ClassPackageCategory> list; /* 三级分类*/
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<ClassPackageCategory> getList() {
		return list;
	}
	public void setList(List<ClassPackageCategory> list) {
		this.list = list;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
}
