package com.yuxin.wx.vo.user;


public class InitDataVo{
	
	private Integer companyId;
	private Integer schoolId;
	private Integer itemOneId;
	private Integer itemSecondId;
	private Integer campusId;
	private Integer userId;
	public InitDataVo() {

	}
	public InitDataVo(Integer companyId, Integer schoolId, Integer itemOneId,
			Integer itemSecondId, Integer campusId, Integer userId) {

		this.companyId = companyId;
		this.schoolId = schoolId;
		this.itemOneId = itemOneId;
		this.itemSecondId = itemSecondId;
		this.campusId = campusId;
		this.userId = userId;
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
	public Integer getCampusId() {
		return campusId;
	}
	public void setCampusId(Integer campusId) {
		this.campusId = campusId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
}
