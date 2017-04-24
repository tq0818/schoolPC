package com.yuxin.wx.model.company;


import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:CompanyConfigCustompageTemplate
 * 
 * @author chopin
 * @date 2016-4-25
 */
@SuppressWarnings("serial")
public class CompanyConfigCustompageTemplate extends BaseEntity {
	
	
	private String	title;		 /* 标题 */ 
	private String	contents;		 /* 内容 */ 
	private Integer	status;		 /* 状态 */ 

	// Constructor
	public CompanyConfigCustompageTemplate() {
	}
	
	/**
	 * full Constructor
	 */
	public CompanyConfigCustompageTemplate(Integer id, String title, String contents, Integer status) {
		setId(id);
		this.title = title;
		this.contents = contents;
		this.status = status;
	}

	// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为CompanyConfigCustompageTemplate可以实现连缀设置属性
	
	public String getTitle() {
		return title;
	}

	public CompanyConfigCustompageTemplate setTitle(String title) {
		this.title = title;
		return this;
	}
	
	
	public String getContents() {
		return contents;
	}

	public CompanyConfigCustompageTemplate setContents(String contents) {
		this.contents = contents;
		return this;
	}
	
	
	public Integer getStatus() {
		return status;
	}

	public CompanyConfigCustompageTemplate setStatus(Integer status) {
		this.status = status;
		return this;
	}
	
	@Override
	public String toString() {
		return "CompanyConfigCustompageTemplate [" + "id=" + getId() + ", title=" + title + ", contents=" + contents + ", status=" + status +  "]";
	}
}
