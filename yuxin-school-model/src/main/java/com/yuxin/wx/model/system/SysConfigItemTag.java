package com.yuxin.wx.model.system;

import java.util.Date;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import com.yuxin.wx.util.ShortDateSerializer;

import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:SysConfigItemTag
 * 
 * @author chopin
 * @date 2015-9-23
 */
@SuppressWarnings("serial")
public class SysConfigItemTag extends BaseEntity {
	
	
	private Integer	itemOneId;		
	private Integer	itemSecondId;		
	private Integer	companyId;		
	private Integer	schoolId;		
	private String	tagName;		
	private Date	createTime;		
	private Integer	creator;		
	private Date	updateTime;		
	private Integer	updator;
	private Integer level;

	// Constructor
	public SysConfigItemTag() {
	}
	
	/**
	 * full Constructor
	 */
	public SysConfigItemTag(Integer id, Integer itemOneId, Integer itemSecondId, Integer companyId, Integer schoolId, String tagName, Date createTime, Integer creator, Date updateTime, Integer updator) {
		setId(id);
		this.itemOneId = itemOneId;
		this.itemSecondId = itemSecondId;
		this.companyId = companyId;
		this.schoolId = schoolId;
		this.tagName = tagName;
		this.createTime = createTime;
		this.creator = creator;
		this.updateTime = updateTime;
		this.updator = updator;
	}

	// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为SysConfigItemTag可以实现连缀设置属性
	
	public Integer getItemOneId() {
		return itemOneId;
	}

	public SysConfigItemTag setItemOneId(Integer itemOneId) {
		this.itemOneId = itemOneId;
		return this;
	}
	
	
	public Integer getItemSecondId() {
		return itemSecondId;
	}

	public SysConfigItemTag setItemSecondId(Integer itemSecondId) {
		this.itemSecondId = itemSecondId;
		return this;
	}
	
	
	public Integer getCompanyId() {
		return companyId;
	}

	public SysConfigItemTag setCompanyId(Integer companyId) {
		this.companyId = companyId;
		return this;
	}
	
	
	public Integer getSchoolId() {
		return schoolId;
	}

	public SysConfigItemTag setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
		return this;
	}
	
	
	public String getTagName() {
		return tagName;
	}

	public SysConfigItemTag setTagName(String tagName) {
		this.tagName = tagName;
		return this;
	}
	
	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getCreateTime() {
		return createTime;
	}

	public SysConfigItemTag setCreateTime(Date createTime) {
		this.createTime = createTime;
		return this;
	}
	
	
	public Integer getCreator() {
		return creator;
	}

	public SysConfigItemTag setCreator(Integer creator) {
		this.creator = creator;
		return this;
	}
	
	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getUpdateTime() {
		return updateTime;
	}

	public SysConfigItemTag setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
		return this;
	}
	
	
	public Integer getUpdator() {
		return updator;
	}

	public SysConfigItemTag setUpdator(Integer updator) {
		this.updator = updator;
		return this;
	}
	
	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	@Override
	public String toString() {
		return "SysConfigItemTag [" + "id=" + getId() + ", itemOneId=" + itemOneId + ", itemSecondId=" + itemSecondId + ", companyId=" + companyId + ", schoolId=" + schoolId + ", tagName=" + tagName + ", createTime=" + createTime + ", creator=" + creator + ", updateTime=" + updateTime + ", updator=" + updator +  "]";
	}
}
