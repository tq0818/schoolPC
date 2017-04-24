package com.yuxin.wx.vo.company;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.yuxin.wx.common.BaseEntity;
import com.yuxin.wx.util.ShortDateSerializer;

public class CompanyStudyCardLibVo extends BaseEntity{

	private static final long serialVersionUID = 1L;

	private String	code;		 /* 学习卡兑换码 */ 
	private Integer	status;		 /* 状态 0-未兑换 1-已兑换 */ 
	private Integer	companyId;		
	private Integer	proxyOrganId;		 /* 代理机构ID */ 
	private Integer	stuId;		 /* 学员ID */ 
	private Date	useTime;		 /* 兑换时间 */ 
	private Integer	cardId;		 /* 学习卡ID */ 
	
	private String name;
	private String username;
	private String mobile;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}
	public Integer getProxyOrganId() {
		return proxyOrganId;
	}
	public void setProxyOrganId(Integer proxyOrganId) {
		this.proxyOrganId = proxyOrganId;
	}
	public Integer getStuId() {
		return stuId;
	}
	public void setStuId(Integer stuId) {
		this.stuId = stuId;
	}
	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getUseTime() {
		return useTime;
	}
	public void setUseTime(Date useTime) {
		this.useTime = useTime;
	}
	public Integer getCardId() {
		return cardId;
	}
	public void setCardId(Integer cardId) {
		this.cardId = cardId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	@Override
	public String toString() {
		return "CompanyStudyCardLibVo [code=" + code + ", status=" + status + ", companyId=" + companyId
				+ ", proxyOrganId=" + proxyOrganId + ", stuId=" + stuId + ", useTime=" + useTime + ", cardId=" + cardId
				+ ", name=" + name + ", username=" + username + ", mobile=" + mobile + "]";
	}
	
}
