package com.yuxin.wx.model.system;


import java.util.Date;

import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:SysConfigService
 * 
 * @author chopin
 * @date 2015-8-12
 */
@SuppressWarnings("serial")
public class SysConfigService extends BaseEntity {
	
	
	private String	groupCode;		 /* 服务组编码 */ 
	private Integer	updator;		 /* 操作人 */ 
	private Date	updateTime;		 /* 操作时间 */ 
	private Integer	companyId;		 /* 公司ID */ 
	private Integer	delFlag;		 /* 删除标记 */ 
	private Integer groupSequence; /*组合排序标记*/
	private Integer zhuCompanyId;
	private Integer headFlag;   /* 页头是否显示*/

	// Constructor
	public SysConfigService() {
	}
	
	/**
	 * full Constructor
	 */
	public SysConfigService(Integer id, String groupCode, Integer updator, Date updateTime, Integer companyId, Integer delFlag) {
		setId(id);
		this.groupCode = groupCode;
		this.updator = updator;
		this.updateTime = updateTime;
		this.companyId = companyId;
		this.delFlag = delFlag;
	}

	// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为SysConfigService可以实现连缀设置属性
	
	public String getGroupCode() {
		return groupCode;
	}

	public SysConfigService setGroupCode(String groupCode) {
		this.groupCode = groupCode;
		return this;
	}
	
	
	public Integer getUpdator() {
		return updator;
	}

	public SysConfigService setUpdator(Integer updator) {
		this.updator = updator;
		return this;
	}
	
	
	public Date getUpdateTime() {
		return updateTime;
	}

	public SysConfigService setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
		return this;
	}
	
	
	public Integer getCompanyId() {
		return companyId;
	}

	public SysConfigService setCompanyId(Integer companyId) {
		this.companyId = companyId;
		return this;
	}
	
	
	public Integer getDelFlag() {
		return delFlag;
	}

	public SysConfigService setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
		return this;
	}
	
	public Integer getGroupSequence() {
		return groupSequence;
	}

	public void setGroupSequence(Integer groupSequence) {
		this.groupSequence = groupSequence;
	}

	public Integer getHeadFlag() {
		return headFlag;
	}

	public void setHeadFlag(Integer headFlag) {
		this.headFlag = headFlag;
	}

	
	
    public Integer getZhuCompanyId() {
    	return zhuCompanyId;
    }

	
    public void setZhuCompanyId(Integer zhuCompanyId) {
    	this.zhuCompanyId = zhuCompanyId;
    }

	@Override
	public String toString() {
		return "SysConfigService [" + "id=" + getId() + ", groupCode=" + groupCode + ", updator=" + updator + ", updateTime=" + updateTime + ", companyId=" + companyId + ", delFlag=" + delFlag +  "]";
	}

}
