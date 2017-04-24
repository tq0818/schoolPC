package com.yuxin.wx.vo.system;

import java.io.Serializable;

@SuppressWarnings("serial")
public class SysServiceDredgeVo implements Serializable {
	
	private String name;		/* 菜单名*/
	private Integer status;		/* 状态*/
	
	public SysServiceDredgeVo() {
		super();
	}
	public SysServiceDredgeVo(String name, Integer status) {
		super();
		this.name = name;
		this.status = status;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
}
