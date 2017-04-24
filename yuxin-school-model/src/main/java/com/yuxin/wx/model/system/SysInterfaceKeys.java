package com.yuxin.wx.model.system;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.yuxin.wx.common.BaseEntity;
import com.yuxin.wx.util.ShortDateSerializer;

/**
 * POJO:SysInterfaceKeys
 * 
 * @author chopin
 * @date 2015-11-16
 */
@SuppressWarnings("serial")
public class SysInterfaceKeys extends BaseEntity {
	
	
	private Integer	companyId;		 /* 公司id */ 
	private String	accessKey;		 /* 通行证 */ 
	private Date	requestTime;		 /* 申请时间 */ 
	private String	ip;		 /* 申请ip */ 

	// Constructor
	public SysInterfaceKeys() {
	}
	
	/**
	 * full Constructor
	 */
	public SysInterfaceKeys(Integer id, Integer companyId, String accessKey, Date requestTime, String ip) {
		setId(id);
		this.companyId = companyId;
		this.accessKey = accessKey;
		this.requestTime = requestTime;
		this.ip = ip;
	}

	// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为SysInterfaceKeys可以实现连缀设置属性
	
	public Integer getCompanyId() {
		return companyId;
	}

	public SysInterfaceKeys setCompanyId(Integer companyId) {
		this.companyId = companyId;
		return this;
	}
	
	
	public String getAccessKey() {
		return accessKey;
	}

	public SysInterfaceKeys setAccessKey(String accessKey) {
		this.accessKey = accessKey;
		return this;
	}
	
	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getRequestTime() {
		return requestTime;
	}

	public SysInterfaceKeys setRequestTime(Date requestTime) {
		this.requestTime = requestTime;
		return this;
	}
	
	
	public String getIp() {
		return ip;
	}

	public SysInterfaceKeys setIp(String ip) {
		this.ip = ip;
		return this;
	}
	
	@Override
	public String toString() {
		return "SysInterfaceKeys [" + "id=" + getId() + ", companyId=" + companyId + ", accessKey=" + accessKey + ", requestTime=" + requestTime + ", ip=" + ip +  "]";
	}
}
