package com.yuxin.wx.model.student;

import java.util.Date;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import com.yuxin.wx.util.ShortDateSerializer;

import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:StudentGroup
 * 
 * @author chopin
 * @date 2016-7-29
 */
@SuppressWarnings("serial")
public class StudentGroup extends BaseEntity {
	
	
	private String	groupName;		
	private Integer	parentId;		
	private Date	createTime;		
	private Integer	updator;	
	private Integer companyId;


	// Constructor
	public StudentGroup() {
	}
	
	/**
	 * full Constructor
	 */
	public StudentGroup(Integer id, String groupName, Integer parentId, Date createTime, Integer updator,Integer companyId) {
		setId(id);
		this.groupName = groupName;
		this.parentId = parentId;
		this.createTime = createTime;
		this.updator = updator;
		this.companyId=companyId;
	}

	// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为StudentGroup可以实现连缀设置属性
	
	public Integer getCompanyId() {
		return companyId;
	}

	public StudentGroup setCompanyId(Integer companyId) {
		this.companyId = companyId;
		return this;
	}
	public String getGroupName() {
		return groupName;
	}

	public StudentGroup setGroupName(String groupName) {
		this.groupName = groupName;
		return this;
	}
	
	
	public Integer getParentId() {
		return parentId;
	}

	public StudentGroup setParentId(Integer parentId) {
		this.parentId = parentId;
		return this;
	}
	
	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getCreateTime() {
		return createTime;
	}

	public StudentGroup setCreateTime(Date createTime) {
		this.createTime = createTime;
		return this;
	}
	
	
	public Integer getUpdator() {
		return updator;
	}

	public StudentGroup setUpdator(Integer updator) {
		this.updator = updator;
		return this;
	}
	
	@Override
	public String toString() {
		return "StudentGroup [" + "id=" + getId() + ", groupName=" + groupName + ", parentId=" + parentId + ", createTime=" + createTime + ", updator=" + updator + ", companyId=" + companyId +  "]";
	}
}
