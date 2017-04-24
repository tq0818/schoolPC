package com.yuxin.wx.model.resource;

import java.util.Date;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import com.yuxin.wx.util.ShortDateSerializer;

import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:ResourceUseRecord
 * 
 * @author wang.zx
 * @date 2016-9-1
 */
@SuppressWarnings("serial")
public class ResourceUseRecord extends BaseEntity {
	
	
	private Integer	fileId;		 /* 文件ID */ 
	private Integer	useType;		 /* 使用类型  0-preview 1-download */ 
	private String	useFlow;		 /* 使用流量 */ 
	private Date	useTime;		 /* 使用时间戳 */ 
	private String	userId;		 /* 用户 前后台用户都有 */ 
	private Integer companyId;

	// Constructor
	public ResourceUseRecord() {
	}
	
	/**
	 * full Constructor
	 */
	public ResourceUseRecord(Integer id, Integer fileId, Integer useType, String useFlow, Date useTime, String userId) {
		setId(id);
		this.fileId = fileId;
		this.useType = useType;
		this.useFlow = useFlow;
		this.useTime = useTime;
		this.userId = userId;
	}

	// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为ResourceUseRecord可以实现连缀设置属性
	
	public Integer getFileId() {
		return fileId;
	}

	public ResourceUseRecord setFileId(Integer fileId) {
		this.fileId = fileId;
		return this;
	}
	
	
	public Integer getUseType() {
		return useType;
	}

	public ResourceUseRecord setUseType(Integer useType) {
		this.useType = useType;
		return this;
	}
	
	
	public String getUseFlow() {
		return useFlow;
	}

	public ResourceUseRecord setUseFlow(String useFlow) {
		this.useFlow = useFlow;
		return this;
	}
	
	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getUseTime() {
		return useTime;
	}

	public ResourceUseRecord setUseTime(Date useTime) {
		this.useTime = useTime;
		return this;
	}
	
	
	public String getUserId() {
		return userId;
	}

	public ResourceUseRecord setUserId(String userId) {
		this.userId = userId;
		return this;
	}
	
	@Override
	public String toString() {
		return "ResourceUseRecord [" + "id=" + getId() + ", fileId=" + fileId + ", useType=" + useType + ", useFlow=" + useFlow + ", useTime=" + useTime + ", userId=" + userId +  "]";
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}
}
