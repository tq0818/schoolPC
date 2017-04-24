package com.yuxin.wx.model.company;

import java.util.Date;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import com.yuxin.wx.util.ShortDateSerializer;

import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:CompanyStudyCardLib
 * 
 * @author chopin
 * @date 2017-3-14
 */
@SuppressWarnings("serial")
public class CompanyStudyCardLib extends BaseEntity {
	
	
	private String	code;		 /* 学习卡兑换码 */ 
	private Integer	status;		 /* 状态 0-未兑换 1-已兑换 */ 
	private Integer	companyId;		
	private Integer	proxyOrganId;		 /* 代理机构ID */ 
	private Integer	stuId;		 /* 学员ID */ 
	private Date	useTime;		 /* 兑换时间 */ 
	private Integer	cardId;		 /* 学习卡ID */ 

	// Constructor
	public CompanyStudyCardLib() {
	}
	
	/**
	 * full Constructor
	 */
	public CompanyStudyCardLib(Integer id, String code, Integer status, Integer companyId, Integer proxyOrganId, Integer stuId, Date useTime, Integer cardId) {
		setId(id);
		this.code = code;
		this.status = status;
		this.companyId = companyId;
		this.proxyOrganId = proxyOrganId;
		this.stuId = stuId;
		this.useTime = useTime;
		this.cardId = cardId;
	}

	// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为CompanyStudyCardLib可以实现连缀设置属性
	
	public String getCode() {
		return code;
	}

	public CompanyStudyCardLib setCode(String code) {
		this.code = code;
		return this;
	}
	
	
	public Integer getStatus() {
		return status;
	}

	public CompanyStudyCardLib setStatus(Integer status) {
		this.status = status;
		return this;
	}
	
	
	public Integer getCompanyId() {
		return companyId;
	}

	public CompanyStudyCardLib setCompanyId(Integer companyId) {
		this.companyId = companyId;
		return this;
	}
	
	
	public Integer getProxyOrganId() {
		return proxyOrganId;
	}

	public CompanyStudyCardLib setProxyOrganId(Integer proxyOrganId) {
		this.proxyOrganId = proxyOrganId;
		return this;
	}
	
	
	public Integer getStuId() {
		return stuId;
	}

	public CompanyStudyCardLib setStuId(Integer stuId) {
		this.stuId = stuId;
		return this;
	}
	
	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getUseTime() {
		return useTime;
	}

	public CompanyStudyCardLib setUseTime(Date useTime) {
		this.useTime = useTime;
		return this;
	}
	
	
	public Integer getCardId() {
		return cardId;
	}

	public CompanyStudyCardLib setCardId(Integer cardId) {
		this.cardId = cardId;
		return this;
	}
	
	@Override
	public String toString() {
		return "CompanyStudyCardLib [" + "id=" + getId() + ", code=" + code + ", status=" + status + ", companyId=" + companyId + ", proxyOrganId=" + proxyOrganId + ", stuId=" + stuId + ", useTime=" + useTime + ", cardId=" + cardId +  "]";
	}
}
