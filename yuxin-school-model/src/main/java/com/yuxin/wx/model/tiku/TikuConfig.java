package com.yuxin.wx.model.tiku;


import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:TikuConfig
 * 
 * @author yuchanglong
 * @date 2016-3-2
 */
@SuppressWarnings("serial")
public class TikuConfig extends BaseEntity {
	
	
	private String	configType;		 /* 数据类型（见字典表） */ 
	private String	configValue;		 /* 题库设置的值（见字典表） */ 
	private String	otherValue;		 /* 其他特殊需求的值 */ 
	private String	anotherValue;		 /* 备用数据存储字段 */ 
	private Integer	companyId;		 /* 所属公司ID */ 
	private Integer delFlag; 		/* 是否删除 */

	// Constructor
	public TikuConfig() {
	}
	
	/**
	 * full Constructor
	 */
	public TikuConfig(Integer id, String configType, String configValue, String otherValue, String anotherValue, Integer companyId, Integer delFlag) {
		setId(id);
		this.configType = configType;
		this.configValue = configValue;
		this.otherValue = otherValue;
		this.anotherValue = anotherValue;
		this.companyId = companyId;
		this.delFlag = delFlag;
	}

	// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为TikuConfig可以实现连缀设置属性
	
	public String getConfigType() {
		return configType;
	}

	public TikuConfig setConfigType(String configType) {
		this.configType = configType;
		return this;
	}
	
	
	public String getConfigValue() {
		return configValue;
	}

	public TikuConfig setConfigValue(String configValue) {
		this.configValue = configValue;
		return this;
	}
	
	
	public String getOtherValue() {
		return otherValue;
	}

	public TikuConfig setOtherValue(String otherValue) {
		this.otherValue = otherValue;
		return this;
	}
	
	
	public String getAnotherValue() {
		return anotherValue;
	}

	public TikuConfig setAnotherValue(String anotherValue) {
		this.anotherValue = anotherValue;
		return this;
	}
	
	
	public Integer getCompanyId() {
		return companyId;
	}

	public TikuConfig setCompanyId(Integer companyId) {
		this.companyId = companyId;
		return this;
	}

	@Override
	public String toString() {
		return "TikuConfig [" + "id=" + getId() + ", configType=" + configType + ", configValue=" + configValue + ", otherValue=" + otherValue + ", anotherValue=" + anotherValue + ", companyId=" + companyId +  ", delFlag=" + delFlag + "]";
	}

	public Integer getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}
}
