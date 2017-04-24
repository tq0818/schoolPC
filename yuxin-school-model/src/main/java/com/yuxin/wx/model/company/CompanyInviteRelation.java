package com.yuxin.wx.model.company;

import java.util.Date;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import com.yuxin.wx.util.ShortDateSerializer;

import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:CompanyInviteRelation
 * 
 * @author chopin
 * @date 2016-7-29
 */
@SuppressWarnings("serial")
public class CompanyInviteRelation extends BaseEntity {
	
	
	private Integer	userId;		 /* 用户ID */ 
	private Integer	parentId;		 /* 父ID */ 
	private Integer	companyId;		 /* 公司ID */ 
	private Date	createTime;		 /* 邀请时间 */ 

	// Constructor
	public CompanyInviteRelation() {
	}
	
	/**
	 * full Constructor
	 */
	public CompanyInviteRelation(Integer id, Integer userId, Integer parentId, Integer companyId, Date createTime) {
		setId(id);
		this.userId = userId;
		this.parentId = parentId;
		this.companyId = companyId;
		this.createTime = createTime;
	}

	// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为CompanyInviteRelation可以实现连缀设置属性
	
	public Integer getUserId() {
		return userId;
	}

	public CompanyInviteRelation setUserId(Integer userId) {
		this.userId = userId;
		return this;
	}
	
	
	public Integer getParentId() {
		return parentId;
	}

	public CompanyInviteRelation setParentId(Integer parentId) {
		this.parentId = parentId;
		return this;
	}
	
	
	public Integer getCompanyId() {
		return companyId;
	}

	public CompanyInviteRelation setCompanyId(Integer companyId) {
		this.companyId = companyId;
		return this;
	}
	
	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getCreateTime() {
		return createTime;
	}

	public CompanyInviteRelation setCreateTime(Date createTime) {
		this.createTime = createTime;
		return this;
	}
	
	@Override
	public String toString() {
		return "CompanyInviteRelation [" + "id=" + getId() + ", userId=" + userId + ", parentId=" + parentId + ", companyId=" + companyId + ", createTime=" + createTime +  "]";
	}
}
