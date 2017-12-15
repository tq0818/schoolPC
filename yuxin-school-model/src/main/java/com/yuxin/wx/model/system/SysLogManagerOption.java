package com.yuxin.wx.model.system;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.yuxin.wx.util.ShortDateSerializer;
import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:SysLogManagerOption
 * 
 * @author chopin
 * @date 2017-3-10
 */
@SuppressWarnings("serial")
public class SysLogManagerOption extends BaseEntity {
	
	
	private Integer	userId;		
	private Integer	companyId;		
	private Date	optionTime;		
	private String	operation;		
	private String	dataBefore;		
	private String	dataAfter;		
	private Integer zhuCompanyId;   
	// Constructor
	public SysLogManagerOption() {
	}
	
	/**
	 * full Constructor
	 */
	public SysLogManagerOption(Integer id, Integer userId, Integer companyId, Date optionTime, String operation, String dataBefore, String dataAfter) {
		setId(id);
		this.userId = userId;
		this.companyId = companyId;
		this.optionTime = optionTime;
		this.operation = operation;
		this.dataBefore = dataBefore;
		this.dataAfter = dataAfter;
	}

	// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为SysLogManagerOption可以实现连缀设置属性
	
	public Integer getUserId() {
		return userId;
	}

	public SysLogManagerOption setUserId(Integer userId) {
		this.userId = userId;
		return this;
	}
	
	
	public Integer getCompanyId() {
		return companyId;
	}

	public SysLogManagerOption setCompanyId(Integer companyId) {
		this.companyId = companyId;
		return this;
	}
	
	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getOptionTime() {
		return optionTime;
	}

	public SysLogManagerOption setOptionTime(Date optionTime) {
		this.optionTime = optionTime;
		return this;
	}
	
	
	public String getOperation() {
		return operation;
	}

	public SysLogManagerOption setOperation(String operation) {
		this.operation = operation;
		return this;
	}
	
	
	public String getDataBefore() {
		return dataBefore;
	}

	public SysLogManagerOption setDataBefore(String dataBefore) {
		this.dataBefore = dataBefore;
		return this;
	}
	
	
	public String getDataAfter() {
		return dataAfter;
	}

	public SysLogManagerOption setDataAfter(String dataAfter) {
		this.dataAfter = dataAfter;
		return this;
	}
	
	
    public Integer getZhuCompanyId() {
    	return zhuCompanyId;
    }

	
    public void setZhuCompanyId(Integer zhuCompanyId) {
    	this.zhuCompanyId = zhuCompanyId;
    }

	@Override
	public String toString() {
		return "SysLogManagerOption [" + "id=" + getId() + ", userId=" + userId + ", companyId=" + companyId + ", optionTime=" + optionTime + ", operation=" + operation + ", dataBefore=" + dataBefore + ", dataAfter=" + dataAfter +  "]";
	}
}
