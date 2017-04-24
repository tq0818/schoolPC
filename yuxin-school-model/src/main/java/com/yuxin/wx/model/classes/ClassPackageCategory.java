package com.yuxin.wx.model.classes;

import java.util.Date;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import com.yuxin.wx.util.ShortDateSerializer;

import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:ClassPackageCategory
 * 
 * @author chopin
 * @date 2016-3-21
 */
@SuppressWarnings("serial")
public class ClassPackageCategory extends BaseEntity {
	
	
	private String	code;		 /* 分类号，每3位是一个级别 */ 
	private String	name;		 /* 名称 */ 
	private Integer	parentId;		 /* 父ID */ 
	private Integer	companyId;		 /* 公司ID */ 
	private Integer	schoolId;		 /* 分校ID */ 
	private Integer	status;		 /* 状态 */ 
	private Integer	delFlag;		 /* 删除标记 */ 
	private Integer	creator;		 /* 创建人 */ 
	private Date	createTime;		 /* 创建时间 */ 
	private Integer	updator;		 /* 更新人 */ 
	private Date	updateTime;		 /* 更新时间 */ 
	private Integer	sort;		 /* 排序 */ 
	
	private String leavl;  /* 分类基本first,second,third*/

	// Constructor
	public ClassPackageCategory() {
	}
	
	/**
	 * full Constructor
	 */
	public ClassPackageCategory(Integer id, String code, String name, Integer parentId, Integer companyId, Integer schoolId, Integer status, Integer delFlag, Integer creator, Date createTime, Integer updator, Date updateTime, Integer sort) {
		setId(id);
		this.code = code;
		this.name = name;
		this.parentId = parentId;
		this.companyId = companyId;
		this.schoolId = schoolId;
		this.status = status;
		this.delFlag = delFlag;
		this.creator = creator;
		this.createTime = createTime;
		this.updator = updator;
		this.updateTime = updateTime;
		this.sort = sort;
	}

	// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为ClassPackageCategory可以实现连缀设置属性
	
	public String getCode() {
		return code;
	}

	public ClassPackageCategory setCode(String code) {
		this.code = code;
		return this;
	}
	
	
	public String getName() {
		return name;
	}

	public ClassPackageCategory setName(String name) {
		this.name = name;
		return this;
	}
	
	
	public Integer getParentId() {
		return parentId;
	}

	public ClassPackageCategory setParentId(Integer parentId) {
		this.parentId = parentId;
		return this;
	}
	
	
	public Integer getCompanyId() {
		return companyId;
	}

	public ClassPackageCategory setCompanyId(Integer companyId) {
		this.companyId = companyId;
		return this;
	}
	
	
	public Integer getSchoolId() {
		return schoolId;
	}

	public ClassPackageCategory setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
		return this;
	}
	
	
	public Integer getStatus() {
		return status;
	}

	public ClassPackageCategory setStatus(Integer status) {
		this.status = status;
		return this;
	}
	
	
	public Integer getDelFlag() {
		return delFlag;
	}

	public ClassPackageCategory setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
		return this;
	}
	
	
	public Integer getCreator() {
		return creator;
	}

	public ClassPackageCategory setCreator(Integer creator) {
		this.creator = creator;
		return this;
	}
	
	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getCreateTime() {
		return createTime;
	}

	public ClassPackageCategory setCreateTime(Date createTime) {
		this.createTime = createTime;
		return this;
	}
	
	
	public Integer getUpdator() {
		return updator;
	}

	public ClassPackageCategory setUpdator(Integer updator) {
		this.updator = updator;
		return this;
	}
	
	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getUpdateTime() {
		return updateTime;
	}

	public ClassPackageCategory setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
		return this;
	}
	
	
	public Integer getSort() {
		return sort;
	}

	public ClassPackageCategory setSort(Integer sort) {
		this.sort = sort;
		return this;
	}
	
	public String getLeavl() {
		return leavl;
	}

	public void setLeavl(String leavl) {
		this.leavl = leavl;
	}

	@Override
	public String toString() {
		return "ClassPackageCategory [" + "id=" + getId() + ", code=" + code + ", name=" + name + ", parentId=" + parentId + ", companyId=" + companyId + ", schoolId=" + schoolId + ", status=" + status + ", delFlag=" + delFlag + ", creator=" + creator + ", createTime=" + createTime + ", updator=" + updator + ", updateTime=" + updateTime + ", sort=" + sort +  "]";
	}
}
