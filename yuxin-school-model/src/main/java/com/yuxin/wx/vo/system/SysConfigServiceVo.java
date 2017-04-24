package com.yuxin.wx.vo.system;

import java.util.Date;

public class SysConfigServiceVo {
	private String	groupCode;		 /* 服务组编码 */ 
	private Integer	updator;		 /* 操作人 */ 
	private Date	updateTime;		 /* 操作时间 */ 
	private Integer	companyId;		 /* 公司ID */ 
	private Integer	delFlag;		 /* 删除标记 */ 
	private Integer privilegeId;  /*权限ID*/
	private String privilegeName; /*权限名称*/
	private String groupName;    /*服务组名称*/
	
	public SysConfigServiceVo(){
		
	}
	public SysConfigServiceVo(String groupCode, Integer updator,
			Date updateTime, Integer companyId, Integer delFlag,
			Integer privilegeId, String privilegeName, String groupName) {
		super();
		this.groupCode = groupCode;
		this.updator = updator;
		this.updateTime = updateTime;
		this.companyId = companyId;
		this.delFlag = delFlag;
		this.privilegeId = privilegeId;
		this.privilegeName = privilegeName;
		this.groupName = groupName;
	}
	
	public String getGroupCode() {
		return groupCode;
	}
	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}
	public Integer getUpdator() {
		return updator;
	}
	public void setUpdator(Integer updator) {
		this.updator = updator;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public Integer getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}
	public Integer getDelFlag() {
		return delFlag;
	}
	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}
	public Integer getPrivilegeId() {
		return privilegeId;
	}
	public void setPrivilegeId(Integer privilegeId) {
		this.privilegeId = privilegeId;
	}
	public String getPrivilegeName() {
		return privilegeName;
	}
	public void setPrivilegeName(String privilegeName) {
		this.privilegeName = privilegeName;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	@Override
	public String toString() {
		return "SysConfigServiceVo [groupCode=" + groupCode + ", updator="
				+ updator + ", updateTime=" + updateTime + ", companyId="
				+ companyId + ", delFlag=" + delFlag + ", privilegeId="
				+ privilegeId + ", privilegeName=" + privilegeName
				+ ", groupName=" + groupName + "]";
	}

	
}
