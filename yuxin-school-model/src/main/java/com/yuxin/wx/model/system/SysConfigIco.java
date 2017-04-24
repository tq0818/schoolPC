package com.yuxin.wx.model.system;

import java.util.Date;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import com.yuxin.wx.util.ShortDateSerializer;

import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:SysConfigIco
 * 
 * @author chopin
 * @date 2015-12-4
 */
@SuppressWarnings("serial")
public class SysConfigIco extends BaseEntity {
	
	
	private String	path;		 /* 图标路径	 */ 
	private Integer	companyId;		 /* 公司id */ 
	private Integer	status;		 /* 状态 0－禁用 1-启用 */ 
	private Date	createTime;		 /* 创建时间 */ 

	// Constructor
	public SysConfigIco() {
	}
	
	/**
	 * full Constructor
	 */
	public SysConfigIco(Integer id, String path, Integer companyId, Integer status, Date createTime) {
		setId(id);
		this.path = path;
		this.companyId = companyId;
		this.status = status;
		this.createTime = createTime;
	}

	// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为SysConfigIco可以实现连缀设置属性
	
	public String getPath() {
		return path;
	}

	public SysConfigIco setPath(String path) {
		this.path = path;
		return this;
	}
	
	
	public Integer getCompanyId() {
		return companyId;
	}

	public SysConfigIco setCompanyId(Integer companyId) {
		this.companyId = companyId;
		return this;
	}
	
	
	public Integer getStatus() {
		return status;
	}

	public SysConfigIco setStatus(Integer status) {
		this.status = status;
		return this;
	}
	
	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getCreateTime() {
		return createTime;
	}

	public SysConfigIco setCreateTime(Date createTime) {
		this.createTime = createTime;
		return this;
	}
	
	@Override
	public String toString() {
		return "SysConfigIco [" + "id=" + getId() + ", path=" + path + ", companyId=" + companyId + ", status=" + status + ", createTime=" + createTime +  "]";
	}
}
