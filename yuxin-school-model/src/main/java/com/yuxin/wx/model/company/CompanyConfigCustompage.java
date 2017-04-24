package com.yuxin.wx.model.company;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.yuxin.wx.util.LongDateSerializer;
import com.yuxin.wx.util.ShortDateSerializer;
import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:CompanyConfigCustompage
 * 
 * @author chopin
 * @date 2016-4-25
 */
@SuppressWarnings("serial")
public class CompanyConfigCustompage extends BaseEntity {
	
	
	private String	title;		 /* 标题 */ 
	private String	url;		 /* 链接 */ 
	private String	content;		 /* 内容 */ 
	private Integer	status;		 /* 状态 */ 
	private Integer	creater;		 /* 发布人 */ 
	private Date	createTime;		 /* 发布时间 */ 
	private Integer	companyId;		 /* 公司编号 */ 
	private String	groupId;		 /* 组ID */ 
	private Integer	templateId;		 /* 模板ID */ 
	private Integer	includeHeadFoot;		 /* 是否包含页头页尾 */ 
	private Integer	sort;		 /* 顺序 */ 
	private Date updateTime; /* 修改时间 */
	private Integer updator; /* 修改人 */
	private Integer screenFlag;
	
	private String creaters;
	private String groupName;

	// Constructor
	public CompanyConfigCustompage() {
	}
	
	/**
	 * full Constructor
	 */
	public CompanyConfigCustompage(Integer id, String title, String url, String content, Integer status, Integer creater, Date createTime, Integer companyId, String groupId, Integer templateId, Integer includeHeadFoot, Integer sort) {
		setId(id);
		this.title = title;
		this.url = url;
		this.content = content;
		this.status = status;
		this.creater = creater;
		this.createTime = createTime;
		this.companyId = companyId;
		this.groupId = groupId;
		this.templateId = templateId;
		this.includeHeadFoot = includeHeadFoot;
		this.sort = sort;
	}

	// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为CompanyConfigCustompage可以实现连缀设置属性
	
	public String getTitle() {
		return title;
	}

	public CompanyConfigCustompage setTitle(String title) {
		this.title = title;
		return this;
	}
	
	
	public String getUrl() {
		return url;
	}

	public CompanyConfigCustompage setUrl(String url) {
		this.url = url;
		return this;
	}
	
	
	public String getContent() {
		return content;
	}

	public CompanyConfigCustompage setContent(String content) {
		this.content = content;
		return this;
	}
	
	
	public Integer getStatus() {
		return status;
	}

	public CompanyConfigCustompage setStatus(Integer status) {
		this.status = status;
		return this;
	}
	
	
	public Integer getCreater() {
		return creater;
	}

	public CompanyConfigCustompage setCreater(Integer creater) {
		this.creater = creater;
		return this;
	}
	
	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getCreateTime() {
		return createTime;
	}

	public CompanyConfigCustompage setCreateTime(Date createTime) {
		this.createTime = createTime;
		return this;
	}
	
	
	public Integer getCompanyId() {
		return companyId;
	}

	public CompanyConfigCustompage setCompanyId(Integer companyId) {
		this.companyId = companyId;
		return this;
	}
	
	
	public String getGroupId() {
		return groupId;
	}

	public CompanyConfigCustompage setGroupId(String groupId) {
		this.groupId = groupId;
		return this;
	}
	
	
	public Integer getTemplateId() {
		return templateId;
	}

	public CompanyConfigCustompage setTemplateId(Integer templateId) {
		this.templateId = templateId;
		return this;
	}
	
	
	public Integer getIncludeHeadFoot() {
		return includeHeadFoot;
	}

	public CompanyConfigCustompage setIncludeHeadFoot(Integer includeHeadFoot) {
		this.includeHeadFoot = includeHeadFoot;
		return this;
	}
	
	
	public Integer getSort() {
		return sort;
	}

	public CompanyConfigCustompage setSort(Integer sort) {
		this.sort = sort;
		return this;
	}
	
	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getUpdator() {
		return updator;
	}

	public void setUpdator(Integer updator) {
		this.updator = updator;
	}
	
	public String getCreaters() {
		return creaters;
	}

	public void setCreaters(String creaters) {
		this.creaters = creaters;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public Integer getScreenFlag() {
		return screenFlag;
	}

	public void setScreenFlag(Integer screenFlag) {
		this.screenFlag = screenFlag;
	}

	@Override
	public String toString() {
		return "CompanyConfigCustompage [" + "id=" + getId() + ", title=" + title + ", url=" + url + ", content=" + content + ", status=" + status + ", creater=" + creater + ", createTime=" + createTime + ", companyId=" + companyId + ", groupId=" + groupId + ", templateId=" + templateId + ", includeHeadFoot=" + includeHeadFoot + ", sort=" + sort +  "]";
	}
}
