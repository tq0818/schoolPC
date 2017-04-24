package com.yuxin.wx.vo.system;

import com.yuxin.wx.common.BaseEntity;


@SuppressWarnings("serial")
public class SysConfigVideoPriceVo extends BaseEntity {
	private String type;
	private String scope;
	private String free;
	private String vip;
	private String exceed;
	public String getScope() {
		return scope;
	}
	public void setScope(String scope) {
		this.scope = scope;
	}
	public String getFree() {
		return free;
	}
	public void setFree(String free) {
		this.free = free;
	}
	public String getVip() {
		return vip;
	}
	public void setVip(String vip) {
		this.vip = vip;
	}
	public String getExceed() {
		return exceed;
	}
	public void setExceed(String exceed) {
		this.exceed = exceed;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}
