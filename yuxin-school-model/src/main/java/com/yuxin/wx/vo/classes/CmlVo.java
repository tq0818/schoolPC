package com.yuxin.wx.vo.classes;

import com.yuxin.wx.common.BaseEntity;

public class CmlVo extends BaseEntity {

	private String roomId;
	private Integer companyId;
	private Integer schoolId;
	
	public String getRoomId() {
		return roomId;
	}
	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}
	public Integer getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}
	public Integer getSchoolId() {
		return schoolId;
	}
	public void setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
	}
}
