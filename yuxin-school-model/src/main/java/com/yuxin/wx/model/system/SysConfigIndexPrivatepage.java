package com.yuxin.wx.model.system;


import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:SysConfigIndexPrivatepage
 * 
 * @author chopin
 * @date 2015-3-17
 */
@SuppressWarnings("serial")
public class SysConfigIndexPrivatepage extends BaseEntity {
	
	private Integer	id;		
	private Integer	schoolId;		 /* 哪个分校的DIV模板 */ 
	private Integer	modelId;		 /* 关联的标准模板ID*/ 
	private String	sort;		 /* 排序 */ 
	private String	type;		 /* 模板的定义类型：展示项目project、展示具体班型class、自定义图片文案custom、自定义代码code四种方式 */ 
	private String	divcode;		 /* 自定义的DIV代码，冗余字段，当type为code时此字段有值。 */ 
	private Integer	companyId;		 /* 公司ID */ 
	private Integer configId;    /*配置信息的ID*/

	
	// Constructor
	public SysConfigIndexPrivatepage() {
	}
	
	/**
	 * full Constructor
	 */
	public SysConfigIndexPrivatepage(Integer id, Integer schoolId, Integer modelId, String sort, String type, String divcode, Integer companyId) {
		this.id = id;
		this.schoolId = schoolId;
		this.modelId = modelId;
		this.sort = sort;
		this.type = type;
		this.divcode = divcode;
		this.companyId = companyId;
	}

	
	public Integer getSchoolId() {
		return schoolId;
	}

	public SysConfigIndexPrivatepage setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
		return this;
	}
	
	
	public Integer getModelId() {
		return modelId;
	}

	public SysConfigIndexPrivatepage setModelId(Integer modelId) {
		this.modelId = modelId;
		return this;
	}
	
	
	public String getSort() {
		return sort;
	}

	public SysConfigIndexPrivatepage setSort(String sort) {
		this.sort = sort;
		return this;
	}
	
	
	public String getType() {
		return type;
	}

	public SysConfigIndexPrivatepage setType(String type) {
		this.type = type;
		return this;
	}
	
	
	public String getDivcode() {
		return divcode;
	}

	public SysConfigIndexPrivatepage setDivcode(String divcode) {
		this.divcode = divcode;
		return this;
	}
	
	
	public Integer getCompanyId() {
		return companyId;
	}

	public SysConfigIndexPrivatepage setCompanyId(Integer companyId) {
		this.companyId = companyId;
		return this;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getConfigId() {
		return configId;
	}

	public void setConfigId(Integer configId) {
		this.configId = configId;
	}

	@Override
	public String toString() {
		return "SysConfigIndexPrivatepage [" + "id=" + id + ", schoolId=" + schoolId + ", modelId=" + modelId + ", sort=" + sort + ", type=" + type + ", divcode=" + divcode + ", companyId=" + companyId +  "]";
	}
}
