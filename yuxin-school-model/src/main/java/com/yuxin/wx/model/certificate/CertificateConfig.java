package com.yuxin.wx.model.certificate;

import java.util.Date;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import com.yuxin.wx.util.ShortDateSerializer;

import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:CertificateConfig
 * 
 * @author chopin
 * @date 2016-9-22
 */
@SuppressWarnings("serial")
public class CertificateConfig extends BaseEntity {
	
	
	private String	name;		 /* 证书名称 */ 
	private String	passConditions;		 /* 证书颁发条件： 0-考试通过 1-学完课程 */ 
	private Integer	status;		 /* 状态 */ 
	private Date	createTime;		 /* 创建时间 */ 
	private Integer	creator;		 /* 创建人：用户ID */ 
	private Integer	delFlag;
	private Integer companyId;

	

	// Constructor
	public CertificateConfig() {
	}
	
	/**
	 * full Constructor
	 */
	public CertificateConfig(Integer id, String name, String passConditions, Integer status, Date createTime, Integer creator, Integer delFlag) {
		setId(id);
		this.name = name;
		this.passConditions = passConditions;
		this.status = status;
		this.createTime = createTime;
		this.creator = creator;
		this.delFlag = delFlag;
	}

	// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为CertificateConfig可以实现连缀设置属性
	
	public String getName() {
		return name;
	}

	public CertificateConfig setName(String name) {
		this.name = name;
		return this;
	}
	

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	
	public String getPassConditions() {
		return passConditions;
	}

	public CertificateConfig setPassConditions(String passConditions) {
		this.passConditions = passConditions;
		return this;
	}
	
	
	public Integer getStatus() {
		return status;
	}

	public CertificateConfig setStatus(Integer status) {
		this.status = status;
		return this;
	}
	
	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getCreateTime() {
		return createTime;
	}

	public CertificateConfig setCreateTime(Date createTime) {
		this.createTime = createTime;
		return this;
	}
	
	
	public Integer getCreator() {
		return creator;
	}

	public CertificateConfig setCreator(Integer creator) {
		this.creator = creator;
		return this;
	}
	
	
	public Integer getDelFlag() {
		return delFlag;
	}

	public CertificateConfig setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
		return this;
	}
	
	@Override
	public String toString() {
		return "CertificateConfig [" + "id=" + getId() + ", name=" + name + ", passConditions=" + passConditions + ", status=" + status + ", createTime=" + createTime + ", creator=" + creator + ", delFlag=" + delFlag +  "]";
	}
}
