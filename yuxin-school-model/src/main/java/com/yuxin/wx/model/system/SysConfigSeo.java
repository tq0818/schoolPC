package com.yuxin.wx.model.system;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.yuxin.wx.util.ShortDateSerializer;
import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:SysConfigSeo
 * 
 * @author chopin
 * @date 2015-12-3
 */
@SuppressWarnings("serial")
public class SysConfigSeo extends BaseEntity {
	
	private String	type;		 /* seo类型：见字典表 SEO_TYPE  */ 
	private Date	createTime;		
	private Integer	companyId;		 /* 公司ID */ 
	private String	title;		 /* 标题 */ 
	private String	keywords;		 /* 关键词 */ 
	private String	description;		 /* 简介 */ 
	private Integer	status;		 /* 状态：0-禁用 1-启用，空值按禁用处理 */ 
	private String	statType;		 /* 统计工具类型：见字典表 SEO_STAT_TYPE */ 
	private String	statKey;		 /* 统计－js编号 */ 
	private Integer	updator;		 /* 更新人 */ 
	private Date	updateTime;		 /* 更新时间 */ 

	// Constructor
	public SysConfigSeo() {
	}
	
	public SysConfigSeo(String type, Date createTime, Integer companyId,
			String title, String keywords, String description, Integer status,
			String statType, String statKey, Integer updator, Date updateTime) {
		this.type = type;
		this.createTime = createTime;
		this.companyId = companyId;
		this.title = title;
		this.keywords = keywords;
		this.description = description;
		this.status = status;
		this.statType = statType;
		this.statKey = statKey;
		this.updator = updator;
		this.updateTime = updateTime;
	}



	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getStatType() {
		return statType;
	}

	public void setStatType(String statType) {
		this.statType = statType;
	}

	public String getStatKey() {
		return statKey;
	}

	public void setStatKey(String statKey) {
		this.statKey = statKey;
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

	
}
