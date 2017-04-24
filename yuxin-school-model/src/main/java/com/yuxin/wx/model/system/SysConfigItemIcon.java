package com.yuxin.wx.model.system;

import com.yuxin.wx.common.BaseEntity;

public class SysConfigItemIcon extends BaseEntity {
	
	private String iconName; /* 图标名*/
	private String iconUrl;	/* 图标连接*/
	private Integer companyId;	/* 公司id*/
	private Integer validSign;	/* 状态 1：可用 0：不能用*/
	private String iconBackUrl; /*新图标*/
	
	public String getIconName() {
		return iconName;
	}
	public void setIconName(String iconName) {
		this.iconName = iconName;
	}
	public String getIconUrl() {
		return iconUrl;
	}
	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}
	public Integer getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}
	public Integer getValidSign() {
		return validSign;
	}
	public void setValidSign(Integer validSign) {
		this.validSign = validSign;
	}
	
	
	public SysConfigItemIcon() {
		super();
	}
	public SysConfigItemIcon(String iconName, String iconUrl,
			Integer companyId, Integer validSign) {
		super();
		this.iconName = iconName;
		this.iconUrl = iconUrl;
		this.companyId = companyId;
		this.validSign = validSign;
	}
	public String getIconBackUrl() {
		return iconBackUrl;
	}
	public void setIconBackUrl(String iconBackUrl) {
		this.iconBackUrl = iconBackUrl;
	}
}
