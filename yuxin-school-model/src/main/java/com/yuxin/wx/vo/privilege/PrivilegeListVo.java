package com.yuxin.wx.vo.privilege;

import java.util.Date;
import java.util.List;

import com.yuxin.wx.common.BaseEntity;
import com.yuxin.wx.model.auth.AuthPrivilege;

@SuppressWarnings("serial")
public class PrivilegeListVo extends BaseEntity{
	
	private String	categoryName;
	private Integer parentId;		
	private Integer	companyId;		 /* 公司id */ 
	private Date	createTime;		 /* 创建时间 */ 
	private String	creator;		 /* 创建人 */ 
	private Date	updateTime;		 /* 更新时间 */ 
	private String	updator;		 /* 更新人 */
	private List<AuthPrivilege> arr;
	
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	public Integer getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public String getUpdator() {
		return updator;
	}
	public void setUpdator(String updator) {
		this.updator = updator;
	}
	public List<AuthPrivilege> getArr() {
		return arr;
	}
	public void setArr(List<AuthPrivilege> arr) {
		this.arr = arr;
	}
	
}
