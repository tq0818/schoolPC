package com.yuxin.wx.model.company;

import java.util.Date;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import com.yuxin.wx.util.ShortDateSerializer;

import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:CompanyConfigProxyOrg
 * 
 * @author chopin
 * @date 2017-3-13
 */
@SuppressWarnings("serial")
public class CompanyConfigProxyOrg extends BaseEntity {
	
	
	private String	name;		 /* 名称 */ 
	private Date	createDate;		 /* 创建日期 */ 
	private String	inviteCode;		 /* 邀请码 */ 
	private String	companyName;		 /* 公司名称 */ 
	private String	address;		 /* 地址 */ 
	private String	contractNumber;		 /* 联系电话 */ 
	private String	contracter;		 /* 联系人 */ 
	private String	contractEmail;		 /* 联系邮箱 */ 
	private String	remark;		 /* 备注 */ 
	private Double	commissionRate;		 /* 提成比例 */ 
	private String 	prefix;
	private Integer	companyId;		 /* 公司ID */ 
	private Integer province;
	private Integer city;
	private String 	detailAddress;
	private Integer status;
	private Integer delFlag;
	private Integer rateSort;
	private String startTime;
	private String endTime;
	private String pname;
	private String cname;

	

	// Constructor
	public CompanyConfigProxyOrg() {
	}
	
	/**
	 * full Constructor
	 */
	public CompanyConfigProxyOrg(Integer id, String name, Date createDate, String inviteCode, String companyName, String address, String contractNumber, String contracter, String contractEmail, String remark, Double commissionRate, Integer companyId) {
		setId(id);
		this.name = name;
		this.createDate = createDate;
		this.inviteCode = inviteCode;
		this.companyName = companyName;
		this.address = address;
		this.contractNumber = contractNumber;
		this.contracter = contracter;
		this.contractEmail = contractEmail;
		this.remark = remark;
		this.commissionRate = commissionRate;
		this.companyId = companyId;
	}

	// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为CompanyConfigProxyOrg可以实现连缀设置属性
	
	public String getName() {
		return name;
	}

	public CompanyConfigProxyOrg setName(String name) {
		this.name = name;
		return this;
	}
	
	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getCreateDate() {
		return createDate;
	}

	public CompanyConfigProxyOrg setCreateDate(Date createDate) {
		this.createDate = createDate;
		return this;
	}
	
	
	public String getInviteCode() {
		return inviteCode;
	}

	public CompanyConfigProxyOrg setInviteCode(String inviteCode) {
		this.inviteCode = inviteCode;
		return this;
	}
	
	
	public String getCompanyName() {
		return companyName;
	}

	public CompanyConfigProxyOrg setCompanyName(String companyName) {
		this.companyName = companyName;
		return this;
	}
	
	
	public String getAddress() {
		return address;
	}

	public CompanyConfigProxyOrg setAddress(String address) {
		this.address = address;
		return this;
	}
	
	
	public String getContractNumber() {
		return contractNumber;
	}

	public CompanyConfigProxyOrg setContractNumber(String contractNumber) {
		this.contractNumber = contractNumber;
		return this;
	}
	
	
	public String getContracter() {
		return contracter;
	}

	public CompanyConfigProxyOrg setContracter(String contracter) {
		this.contracter = contracter;
		return this;
	}
	
	
	public String getContractEmail() {
		return contractEmail;
	}

	public CompanyConfigProxyOrg setContractEmail(String contractEmail) {
		this.contractEmail = contractEmail;
		return this;
	}
	
	
	public String getRemark() {
		return remark;
	}

	public CompanyConfigProxyOrg setRemark(String remark) {
		this.remark = remark;
		return this;
	}
	
	
	public Double getCommissionRate() {
		return commissionRate;
	}

	public CompanyConfigProxyOrg setCommissionRate(Double commissionRate) {
		this.commissionRate = commissionRate;
		return this;
	}
	
	
	public Integer getCompanyId() {
		return companyId;
	}

	public CompanyConfigProxyOrg setCompanyId(Integer companyId) {
		this.companyId = companyId;
		return this;
	}
	
	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public Integer getProvince() {
		return province;
	}

	public void setProvince(Integer province) {
		this.province = province;
	}

	public Integer getCity() {
		return city;
	}

	public void setCity(Integer city) {
		this.city = city;
	}

	public String getDetailAddress() {
		return detailAddress;
	}

	public void setDetailAddress(String detailAddress) {
		this.detailAddress = detailAddress;
	}
	
	
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}

	public Integer getRateSort() {
		return rateSort;
	}

	public void setRateSort(Integer rateSort) {
		this.rateSort = rateSort;
	}
	
	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	
	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	@Override
	public String toString() {
		return "CompanyConfigProxyOrg [name=" + name + ", createDate=" + createDate + ", inviteCode=" + inviteCode
				+ ", companyName=" + companyName + ", address=" + address + ", contractNumber=" + contractNumber
				+ ", contracter=" + contracter + ", contractEmail=" + contractEmail + ", remark=" + remark
				+ ", commissionRate=" + commissionRate + ", prefix=" + prefix + ", companyId=" + companyId
				+ ", province=" + province + ", city=" + city + ", detailAddress=" + detailAddress + ", status="
				+ status + ", delFlag=" + delFlag + ", rateSort=" + rateSort + ", startTime=" + startTime + ", endTime="
				+ endTime + ", pname=" + pname + ", cname=" + cname + "]";
	}

	
}
