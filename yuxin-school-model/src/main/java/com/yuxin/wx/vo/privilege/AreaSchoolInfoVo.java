package com.yuxin.wx.vo.privilege;

public class AreaSchoolInfoVo {
	//机构代码
	private String code;
	//机构名称
	private String codeName;
	//区或者校，0代表区，1代表学校
	private String isAreaOrSchool;
	//父级机构代码
	private String parentCode;
	//父级机构名称
	private String parentName;
	public String getParentName() {
		return parentName;
	}
	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getCodeName() {
		return codeName;
	}
	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}
	public String getIsAreaOrSchool() {
		return isAreaOrSchool;
	}
	public void setIsAreaOrSchool(String isAreaOrSchool) {
		this.isAreaOrSchool = isAreaOrSchool;
	}
	public String getParentCode() {
		return parentCode;
	}
	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}
	
	
}
