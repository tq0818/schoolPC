package com.yuxin.wx.model.system;


import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:SysPageHeadFooterTemplate
 * 
 * @author chopin
 * @date 2016-2-29
 */
@SuppressWarnings("serial")
public class SysPageHeadFooterTemplate extends BaseEntity {
	
	
	private String	name;		 /* 模板名称 */ 
	private String	showPage;		 /* 展示页面 */ 
	private String	configPage;		 /* 配置页面 */ 
	private String	priviewPic;		 /* 预览图 */ 
	private Integer	sort;		 /* 顺序 */ 
	private String	templateType;		 /* 模板类型：见字典 */ 

	// Constructor
	public SysPageHeadFooterTemplate() {
	}
	
	/**
	 * full Constructor
	 */
	public SysPageHeadFooterTemplate(Integer id, String name, String showPage, String configPage, String priviewPic, Integer sort, String templateType) {
		setId(id);
		this.name = name;
		this.showPage = showPage;
		this.configPage = configPage;
		this.priviewPic = priviewPic;
		this.sort = sort;
		this.templateType = templateType;
	}

	// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为SysPageHeadFooterTemplate可以实现连缀设置属性
	
	public String getName() {
		return name;
	}

	public SysPageHeadFooterTemplate setName(String name) {
		this.name = name;
		return this;
	}
	
	
	public String getShowPage() {
		return showPage;
	}

	public SysPageHeadFooterTemplate setShowPage(String showPage) {
		this.showPage = showPage;
		return this;
	}
	
	
	public String getConfigPage() {
		return configPage;
	}

	public SysPageHeadFooterTemplate setConfigPage(String configPage) {
		this.configPage = configPage;
		return this;
	}
	
	
	public String getPriviewPic() {
		return priviewPic;
	}

	public SysPageHeadFooterTemplate setPriviewPic(String priviewPic) {
		this.priviewPic = priviewPic;
		return this;
	}
	
	
	public Integer getSort() {
		return sort;
	}

	public SysPageHeadFooterTemplate setSort(Integer sort) {
		this.sort = sort;
		return this;
	}
	
	
	public String getTemplateType() {
		return templateType;
	}

	public SysPageHeadFooterTemplate setTemplateType(String templateType) {
		this.templateType = templateType;
		return this;
	}
	
	@Override
	public String toString() {
		return "SysPageHeadFooterTemplate [" + "id=" + getId() + ", name=" + name + ", page=" + showPage + ", configPage=" + configPage + ", priviewPic=" + priviewPic + ", sort=" + sort + ", templateType=" + templateType +  "]";
	}
}
