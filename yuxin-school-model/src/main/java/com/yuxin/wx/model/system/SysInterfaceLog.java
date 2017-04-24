package com.yuxin.wx.model.system;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.yuxin.wx.common.BaseEntity;
import com.yuxin.wx.util.ShortDateSerializer;

/**
 * POJO:SysInterfaceLog
 * 
 * @author wang.zx
 * @date 2016-1-26
 */
@SuppressWarnings("serial")
public class SysInterfaceLog extends BaseEntity {
	
	
	private String	ip;		
	private String	domain;		
	private Date	requestDate;		
	private Integer	companyId;		
	private String	parameters;		
	private String	resultFlag;		
	private String	result;		
	private String	errMsg;		
	private String apiName;

	// Constructor
	public SysInterfaceLog() {
	}
	
	/**
	 * full Constructor
	 */
	public SysInterfaceLog(Integer id, String ip, String domain, Date requestDate, Integer companyId, String parameters, String resultFlag, String result, String errMsg) {
		setId(id);
		this.ip = ip;
		this.domain = domain;
		this.requestDate = requestDate;
		this.companyId = companyId;
		this.parameters = parameters;
		this.resultFlag = resultFlag;
		this.result = result;
		this.errMsg = errMsg;
	}

	// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为SysInterfaceLog可以实现连缀设置属性
	
	public String getIp() {
		return ip;
	}

	public SysInterfaceLog setIp(String ip) {
		this.ip = ip;
		return this;
	}
	
	
	public String getDomain() {
		return domain;
	}

	public SysInterfaceLog setDomain(String domain) {
		this.domain = domain;
		return this;
	}
	
	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getRequestDate() {
		return requestDate;
	}

	public SysInterfaceLog setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
		return this;
	}
	
	
	public Integer getCompanyId() {
		return companyId;
	}

	public SysInterfaceLog setCompanyId(Integer companyId) {
		this.companyId = companyId;
		return this;
	}
	
	
	public String getParameters() {
		return parameters;
	}

	public SysInterfaceLog setParameters(String parameters) {
		this.parameters = parameters;
		return this;
	}
	
	
	public String getResultFlag() {
		return resultFlag;
	}

	public SysInterfaceLog setResultFlag(String resultFlag) {
		this.resultFlag = resultFlag;
		return this;
	}
	
	
	public String getResult() {
		return result;
	}

	public SysInterfaceLog setResult(String result) {
		this.result = result;
		return this;
	}
	
	
	public String getErrMsg() {
		return errMsg;
	}

	public SysInterfaceLog setErrMsg(String errMsg) {
		this.errMsg = errMsg;
		return this;
	}
	
	
	public String getApiName() {
		return apiName;
	}

	public void setApiName(String apiName) {
		this.apiName = apiName;
	}

	@Override
	public String toString() {
		return "{" + "\"id\":\"" + getId() + "\", \"ip\":\"" + ip + "\", \"domain\"=\"" + domain + "\", \"requestDate\"=\"" + requestDate + "\", \"companyId\"=\"" + companyId + "\", \"parameters\"=\"" + parameters + "\", \"resultFlag\"=\"" + resultFlag + "\", \"result\"=\"" + result + "\", \"errMsg\"=" + errMsg +  "\"}";
	}
}
