package com.yuxin.wx.vo.privilege;

import java.util.List;

public class AreaInfoVo {
	//区县代码
	private String areaCode;
	//是否选中
	private Boolean selected=false;
	//区县名称
	private String areaName;
	//区县下所有的学校
	private List<SchoolInfoVo> schoolInfos;
	public String getAreaCode() {
		return areaCode;
	}
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
	public Boolean getSelected() {
		return selected;
	}
	public void setSelected(Boolean selected) {
		this.selected = selected;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	public List<SchoolInfoVo> getSchoolInfos() {
		return schoolInfos;
	}
	public void setSchoolInfos(List<SchoolInfoVo> schoolInfos) {
		this.schoolInfos = schoolInfos;
	}
	
}
