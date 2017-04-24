package com.yuxin.wx.model.system;


import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:SysServiceDredgeConfig
 * 
 * @author wang.zx
 * @date 2016-12-12
 */
@SuppressWarnings("serial")
public class SysServiceDredgeConfig extends BaseEntity {
	
	private String name;			/* 代码名*/
	private String	privilegeCode;		 /* 权限编码 */ 
	private Integer	delFlag;		 /* 删除标记 */ 
	private Integer	price;		 /* 价格 */ 

	public SysServiceDredgeConfig() {
		super();
	}

	public SysServiceDredgeConfig(String name, String privilegeCode,
			Integer delFlag, Integer price) {
		super();
		this.name = name;
		this.privilegeCode = privilegeCode;
		this.delFlag = delFlag;
		this.price = price;
	}

	@Override
	public String toString() {
		return "SysServiceDredgeConfig [" + "id=" + getId() + ", privilegeCode=" + privilegeCode + ", delFlag=" + delFlag + ", price=" + price +  "]";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPrivilegeCode() {
		return privilegeCode;
	}
	
	public void setPrivilegeCode(String privilegeCode) {
		this.privilegeCode = privilegeCode;
	}

	public Integer getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}
}
