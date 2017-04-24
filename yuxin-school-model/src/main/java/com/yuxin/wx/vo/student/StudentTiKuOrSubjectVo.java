package com.yuxin.wx.vo.student;

public class StudentTiKuOrSubjectVo {
	/**
	 * 题库、科目的ID
	 */
	private Integer id;
	/**
	 * 题库、科目的名字
	 */
	private String name;
	/**
	 * 查询类型 0:题库，1:科目
	 */
	private Integer type;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	
}
