package com.yuxin.wx.model.system;


import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:SysConfigServiceGroup
 * 
 * @author chopin
 * @date 2015-8-12
 */
@SuppressWarnings("serial")
public class SysConfigServiceGroup extends BaseEntity {
	
	
	private String	groupName;		 /* 组名称 */ 
	private Integer	privilegeId;		 /* 权限ID */ 
	private String	privilegeName;		 /* 权限编码 */ 
	private Integer	delFlag;		 /* 删除标记 */ 
	private String	groupCode;		 /* 服务组编码 */ 

	// Constructor
	public SysConfigServiceGroup() {
	}
	
	/**
	 * full Constructor
	 */
	public SysConfigServiceGroup(Integer id, String groupName, Integer privilegeId, String privilegeName, Integer delFlag, String groupCode) {
		setId(id);
		this.groupName = groupName;
		this.privilegeId = privilegeId;
		this.privilegeName = privilegeName;
		this.delFlag = delFlag;
		this.groupCode = groupCode;
	}

	// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为SysConfigServiceGroup可以实现连缀设置属性
	
	public String getGroupName() {
		return groupName;
	}

	public SysConfigServiceGroup setGroupName(String groupName) {
		this.groupName = groupName;
		return this;
	}
	
	
	public Integer getPrivilegeId() {
		return privilegeId;
	}

	public SysConfigServiceGroup setPrivilegeId(Integer privilegeId) {
		this.privilegeId = privilegeId;
		return this;
	}
	
	
	public String getPrivilegeName() {
		return privilegeName;
	}

	public SysConfigServiceGroup setPrivilegeName(String privilegeName) {
		this.privilegeName = privilegeName;
		return this;
	}
	
	
	public Integer getDelFlag() {
		return delFlag;
	}

	public SysConfigServiceGroup setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
		return this;
	}
	
	
	public String getGroupCode() {
		return groupCode;
	}

	public SysConfigServiceGroup setGroupCode(String groupCode) {
		this.groupCode = groupCode;
		return this;
	}
	
	@Override
	public String toString() {
		return "SysConfigServiceGroup [" + "id=" + getId() + ", groupName=" + groupName + ", privilegeId=" + privilegeId + ", privilegeName=" + privilegeName + ", delFlag=" + delFlag + ", groupCode=" + groupCode +  "]";
	}
}
