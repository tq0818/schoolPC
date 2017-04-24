package com.yuxin.wx.model.company;


import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:CompanyIndexModelPrivatepage
 * 
 * @author chopin
 * @date 2015-5-18
 */
@SuppressWarnings("serial")
public class CompanyIndexModelPrivatepage extends BaseEntity {
	
	
	private Integer	schoolId;		 /* 哪个分校的DIV模板 */ 
	private Integer	modelId;		 /* 关联的div模板id */ 
	private String	sort;		 /* 各子配置的显示顺序 */ 
	private String	type;		 /* 子配置类型，项目、班型、自定义和新闻 */ 
	private String	divcode;		 /* 用户自定义的div，使用此字段时model_id字段不用了 */ 
	private Integer	configId;		 /* 子配置的id，项目、班型、自定义和新闻的id */ 
	private Integer	companyId;		 /* 公司id */ 
	private Integer	templateId;		 /* 公司模板id */ 

	// Constructor
	public CompanyIndexModelPrivatepage() {
	}
	
	/**
	 * full Constructor
	 */
	public CompanyIndexModelPrivatepage(Integer id, Integer schoolId, Integer modelId, String sort, String type, String divcode, Integer configId, Integer companyId, Integer templateId) {
		setId(id);
		this.schoolId = schoolId;
		this.modelId = modelId;
		this.sort = sort;
		this.type = type;
		this.divcode = divcode;
		this.configId = configId;
		this.companyId = companyId;
		this.templateId = templateId;
	}

	// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为CompanyIndexModelPrivatepage可以实现连缀设置属性
	
	public Integer getSchoolId() {
		return schoolId;
	}

	public CompanyIndexModelPrivatepage setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
		return this;
	}
	
	
	public Integer getModelId() {
		return modelId;
	}

	public CompanyIndexModelPrivatepage setModelId(Integer modelId) {
		this.modelId = modelId;
		return this;
	}
	
	
	public String getSort() {
		return sort;
	}

	public CompanyIndexModelPrivatepage setSort(String sort) {
		this.sort = sort;
		return this;
	}
	
	
	public String getType() {
		return type;
	}

	public CompanyIndexModelPrivatepage setType(String type) {
		this.type = type;
		return this;
	}
	
	
	public String getDivcode() {
		return divcode;
	}

	public CompanyIndexModelPrivatepage setDivcode(String divcode) {
		this.divcode = divcode;
		return this;
	}
	
	
	public Integer getConfigId() {
		return configId;
	}

	public CompanyIndexModelPrivatepage setConfigId(Integer configId) {
		this.configId = configId;
		return this;
	}
	
	
	public Integer getCompanyId() {
		return companyId;
	}

	public CompanyIndexModelPrivatepage setCompanyId(Integer companyId) {
		this.companyId = companyId;
		return this;
	}
	
	
	public Integer getTemplateId() {
		return templateId;
	}

	public CompanyIndexModelPrivatepage setTemplateId(Integer templateId) {
		this.templateId = templateId;
		return this;
	}
	
	@Override
	public String toString() {
		return "CompanyIndexModelPrivatepage [" + "id=" + getId() + ", schoolId=" + schoolId + ", modelId=" + modelId + ", sort=" + sort + ", type=" + type + ", divcode=" + divcode + ", configId=" + configId + ", companyId=" + companyId + ", templateId=" + templateId +  "]";
	}
}
