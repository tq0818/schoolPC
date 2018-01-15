package com.yuxin.wx.model.teacher;

import com.yuxin.wx.common.BaseEntity;

/**
 * 教师用户与学校关系表
 * 
 * @author cxl
 *
 */
@SuppressWarnings("serial")
public class UsersComanyRelation extends BaseEntity {
	private Integer id;
	private Integer userId;
	private Integer companyId;
	private Integer isUsed;

	public UsersComanyRelation() {

	}

	public UsersComanyRelation(Integer id, Integer userId, Integer companyId,
			Integer isUsed) {
		setId(id);
		this.id = id;
		this.userId = userId;
		this.companyId = companyId;
		this.isUsed = isUsed;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	public Integer getIsUsed() {
		return isUsed;
	}

	public void setIsUsed(Integer isUsed) {
		this.isUsed = isUsed;
	}

}
