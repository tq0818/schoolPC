package com.yuxin.wx.model.company;

import java.util.Date;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import com.yuxin.wx.util.ShortDateSerializer;

import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:CompanyRechargeLib
 * 
 * @author chopin
 * @date 2017-4-10
 */
@SuppressWarnings("serial")
public class CompanyRechargeLib extends BaseEntity {
	
	
	private String	code;		 /* 编码 */ 
	private Date	timeLimitFrom;		 /* 使用期限从 */ 
	private Date	timeLimitTo;		 /* 使用期限至 */ 
	private Integer	status;		 /* 状态 0-未使用 1-已使用 */ 
	private Integer	patchId;		 /* 批次ID */ 
	private Integer	userId;		 /* 使用人  user_front_id */ 
	private Date	useTime;		 /* 使用时间 */ 
	private Integer	companyId;		 /* 公司ID */ 

	// Constructor
	public CompanyRechargeLib() {
	}
	
	/**
	 * full Constructor
	 */
	public CompanyRechargeLib(Integer id, String code, Date timeLimitFrom, Date timeLimitTo, Integer status, Integer patchId, Integer userId, Date useTime, Integer companyId) {
		setId(id);
		this.code = code;
		this.timeLimitFrom = timeLimitFrom;
		this.timeLimitTo = timeLimitTo;
		this.status = status;
		this.patchId = patchId;
		this.userId = userId;
		this.useTime = useTime;
		this.companyId = companyId;
	}

	// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为CompanyRechargeLib可以实现连缀设置属性
	
	public String getCode() {
		return code;
	}

	public CompanyRechargeLib setCode(String code) {
		this.code = code;
		return this;
	}
	
	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getTimeLimitFrom() {
		return timeLimitFrom;
	}

	public CompanyRechargeLib setTimeLimitFrom(Date timeLimitFrom) {
		this.timeLimitFrom = timeLimitFrom;
		return this;
	}
	
	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getTimeLimitTo() {
		return timeLimitTo;
	}

	public CompanyRechargeLib setTimeLimitTo(Date timeLimitTo) {
		this.timeLimitTo = timeLimitTo;
		return this;
	}
	
	
	public Integer getStatus() {
		return status;
	}

	public CompanyRechargeLib setStatus(Integer status) {
		this.status = status;
		return this;
	}
	
	
	public Integer getPatchId() {
		return patchId;
	}

	public CompanyRechargeLib setPatchId(Integer patchId) {
		this.patchId = patchId;
		return this;
	}
	
	
	public Integer getUserId() {
		return userId;
	}

	public CompanyRechargeLib setUserId(Integer userId) {
		this.userId = userId;
		return this;
	}
	
	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getUseTime() {
		return useTime;
	}

	public CompanyRechargeLib setUseTime(Date useTime) {
		this.useTime = useTime;
		return this;
	}
	
	
	public Integer getCompanyId() {
		return companyId;
	}

	public CompanyRechargeLib setCompanyId(Integer companyId) {
		this.companyId = companyId;
		return this;
	}
	
	@Override
	public String toString() {
		return "CompanyRechargeLib [" + "id=" + getId() + ", code=" + code + ", timeLimitFrom=" + timeLimitFrom + ", timeLimitTo=" + timeLimitTo + ", status=" + status + ", patchId=" + patchId + ", userId=" + userId + ", useTime=" + useTime + ", companyId=" + companyId +  "]";
	}
}
