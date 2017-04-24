package com.yuxin.wx.model.system;

import java.util.Date;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import com.yuxin.wx.util.ShortDateSerializer;

import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:SysNewsType
 * 
 * @author chopin
 * @date 2015-12-3
 */
@SuppressWarnings("serial")
public class SysNewsType extends BaseEntity {
	
	
	private String	name;		 /* 姓名 */ 
	private String	companyId;		 /* 公司ID */ 
	private String	schoolId;		 /* 分校ID */ 
	private String	delFlag;		 /* 删除标记 0-未删除 1-已删除 */ 
	private Integer	creater;		
	private Date	createTime;		

	// Constructor
	public SysNewsType() {
	}
	
	/**
	 * full Constructor
	 */
	public SysNewsType(Integer id, String name, String companyId, String schoolId, String delFlag, Integer creater, Date createTime) {
		setId(id);
		this.name = name;
		this.companyId = companyId;
		this.schoolId = schoolId;
		this.delFlag = delFlag;
		this.creater = creater;
		this.createTime = createTime;
	}

	// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为SysNewsType可以实现连缀设置属性
	
	public String getName() {
		return name;
	}

	public SysNewsType setName(String name) {
		this.name = name;
		return this;
	}
	
	
	public String getCompanyId() {
		return companyId;
	}

	public SysNewsType setCompanyId(String companyId) {
		this.companyId = companyId;
		return this;
	}
	
	
	public String getSchoolId() {
		return schoolId;
	}

	public SysNewsType setSchoolId(String schoolId) {
		this.schoolId = schoolId;
		return this;
	}
	
	
	public String getDelFlag() {
		return delFlag;
	}

	public SysNewsType setDelFlag(String delFlag) {
		this.delFlag = delFlag;
		return this;
	}
	
	
	public Integer getCreater() {
		return creater;
	}

	public SysNewsType setCreater(Integer creater) {
		this.creater = creater;
		return this;
	}
	
	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getCreateTime() {
		return createTime;
	}

	public SysNewsType setCreateTime(Date createTime) {
		this.createTime = createTime;
		return this;
	}
	
	@Override
	public String toString() {
		return "SysNewsType [" + "id=" + getId() + ", name=" + name + ", companyId=" + companyId + ", schoolId=" + schoolId + ", delFlag=" + delFlag + ", creater=" + creater + ", createTime=" + createTime +  "]";
	}
}
