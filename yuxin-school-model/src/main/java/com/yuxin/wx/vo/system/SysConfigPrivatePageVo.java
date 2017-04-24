package com.yuxin.wx.vo.system;

import com.yuxin.wx.common.BaseEntity;
import com.yuxin.wx.model.company.CompanyIndexModelClasstype;
import com.yuxin.wx.model.company.CompanyIndexModelCustom;
import com.yuxin.wx.model.company.CompanyIndexModelItem;
import com.yuxin.wx.model.company.CompanyIndexModelNews;
import com.yuxin.wx.model.system.SysConfigIndexClasstype;
import com.yuxin.wx.model.system.SysConfigIndexCustom;
import com.yuxin.wx.model.system.SysConfigIndexItem;
import com.yuxin.wx.model.system.SysConfigIndexNews;

public class SysConfigPrivatePageVo extends BaseEntity{
	
	private Integer	schoolId;		 /* 哪个分校的DIV模板 */ 
	private Integer	modelId;		 /* 关联的div模板id */ 
	private String	sort;		 /* 各子配置的显示顺序 */ 
	private String	type;		 /* 子配置类型，项目、班型、自定义和新闻 */ 
	private String	divcode;		 /* 用户自定义的div，使用此字段时model_id字段不用了 */ 
	private Integer	configId;		 /* 子配置的id，项目、班型、自定义和新闻的id */ 
	private Integer	companyId;		 /* 公司id */ 
	private Integer	templateId;		 /* 公司模板id */ 
	private SysConfigIndexClasstype classtype; /*班型*/
	private SysConfigIndexCustom custom; /*自定义*/
	private SysConfigIndexItem item;   /*项目*/
	private SysConfigIndexNews news;   /*新闻*/

	// Constructor
	public SysConfigPrivatePageVo() {
	}
	
	/**
	 * full Constructor
	 */
	public SysConfigPrivatePageVo(Integer id, Integer schoolId, Integer modelId, String sort, String type, String divcode, Integer configId, Integer companyId, Integer templateId) {
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

	public SysConfigPrivatePageVo setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
		return this;
	}
	
	
	public Integer getModelId() {
		return modelId;
	}

	public SysConfigPrivatePageVo setModelId(Integer modelId) {
		this.modelId = modelId;
		return this;
	}
	
	
	public String getSort() {
		return sort;
	}

	public SysConfigPrivatePageVo setSort(String sort) {
		this.sort = sort;
		return this;
	}
	
	
	public String getType() {
		return type;
	}

	public SysConfigPrivatePageVo setType(String type) {
		this.type = type;
		return this;
	}
	
	
	public String getDivcode() {
		return divcode;
	}

	public SysConfigPrivatePageVo setDivcode(String divcode) {
		this.divcode = divcode;
		return this;
	}
	
	
	public Integer getConfigId() {
		return configId;
	}

	public SysConfigPrivatePageVo setConfigId(Integer configId) {
		this.configId = configId;
		return this;
	}
	
	
	public Integer getCompanyId() {
		return companyId;
	}

	public SysConfigPrivatePageVo setCompanyId(Integer companyId) {
		this.companyId = companyId;
		return this;
	}
	
	
	public Integer getTemplateId() {
		return templateId;
	}

	public SysConfigPrivatePageVo setTemplateId(Integer templateId) {
		this.templateId = templateId;
		return this;
	}
	
	public SysConfigIndexClasstype getClasstype() {
		return classtype;
	}

	public void setClasstype(SysConfigIndexClasstype classtype) {
		this.classtype = classtype;
	}

	public SysConfigIndexCustom getCustom() {
		return custom;
	}

	public void setCustom(SysConfigIndexCustom custom) {
		this.custom = custom;
	}

	public SysConfigIndexItem getItem() {
		return item;
	}

	public void setItem(SysConfigIndexItem item) {
		this.item = item;
	}

	public SysConfigIndexNews getNews() {
		return news;
	}

	public void setNews(SysConfigIndexNews news) {
		this.news = news;
	}

	@Override
	public String toString() {
		return "SysConfigPrivatePageVo [" + "id=" + getId() + ", schoolId=" + schoolId + ", modelId=" + modelId + ", sort=" + sort + ", type=" + type + ", divcode=" + divcode + ", configId=" + configId + ", companyId=" + companyId + ", templateId=" + templateId +  "]";
	}
}
