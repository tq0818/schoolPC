package com.yuxin.wx.vo.company;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.yuxin.wx.common.BaseEntity;
import com.yuxin.wx.util.ShortDateSerializer;

public class CompanyStudyCardsVo extends BaseEntity{
	
	private static final long serialVersionUID = 1L;
	
	private String	name;		 /* 学习卡名称 */ 
	private String	courseList;		 /* 课程/课程包 id列表  以逗号分隔 */ 
	private Integer	courseType;		 /* 课程类型 0-课程 1-课程包 */ 
	private Integer	totalNum;		 /* 学习卡总量 */ 
	private Integer	usedNum;		 /* 已使用量 */ 
	private Date	useDateBegin;		 /* 使用期限自 */ 
	private Date	useDateEnd;		 /* 使用期限至 */ 
	private String	proxyOrgId;		 /* 代理商ID */ 
	private String	proxyOrgName;		 /* 代理商名称 */ 
	private String	prefix;		 /* 学习码前缀  必须是大写字母，最多4位 */ 
	private Double	price;		 /* 价格 */ 
	private String	description;		 /* 说明  */ 
	private Integer	companyId;		 /* 公司ID */ 
	private Date	createTime;		 /* 创建时间 */ 
	private Integer	creator;		 /* 创建人 */ 
	
	private String username;
	private String realName;
	
	private String	proxyName;		 /* 名称 */ 
	private String  pefix;			/* 代理前缀 */
	
	private String startDate;
	private String endDate;
	
	private String useDateBeginString;
	private String useDateEndString;
	private String createTimeString;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCourseList() {
		return courseList;
	}
	public void setCourseList(String courseList) {
		this.courseList = courseList;
	}
	public Integer getCourseType() {
		return courseType;
	}
	public void setCourseType(Integer courseType) {
		this.courseType = courseType;
	}
	public Integer getTotalNum() {
		return totalNum;
	}
	public void setTotalNum(Integer totalNum) {
		this.totalNum = totalNum;
	}
	public Integer getUsedNum() {
		return usedNum;
	}
	public void setUsedNum(Integer usedNum) {
		this.usedNum = usedNum;
	}
	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getUseDateBegin() {
		return useDateBegin;
	}
	public void setUseDateBegin(Date useDateBegin) {
		this.useDateBegin = useDateBegin;
	}
	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getUseDateEnd() {
		return useDateEnd;
	}
	public void setUseDateEnd(Date useDateEnd) {
		this.useDateEnd = useDateEnd;
	}
	public String getProxyOrgId() {
		return proxyOrgId;
	}
	public void setProxyOrgId(String proxyOrgId) {
		this.proxyOrgId = proxyOrgId;
	}
	public String getProxyOrgName() {
		return proxyOrgName;
	}
	public void setProxyOrgName(String proxyOrgName) {
		this.proxyOrgName = proxyOrgName;
	}
	public String getPrefix() {
		return prefix;
	}
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}
	public String getProxyName() {
		return proxyName;
	}
	public void setProxyName(String proxyName) {
		this.proxyName = proxyName;
	}
	public String getPefix() {
		return pefix;
	}
	public void setPefix(String pefix) {
		this.pefix = pefix;
	}
	@Override
	public String toString() {
		return "CompanyStudyCardsVo [name=" + name + ", courseList=" + courseList + ", courseType=" + courseType
				+ ", totalNum=" + totalNum + ", usedNum=" + usedNum + ", useDateBegin=" + useDateBegin + ", useDateEnd="
				+ useDateEnd + ", proxyOrgId=" + proxyOrgId + ", proxyOrgName=" + proxyOrgName + ", prefix=" + prefix
				+ ", price=" + price + ", description=" + description + ", companyId=" + companyId + ", proxyName="
				+ proxyName + ", pefix=" + pefix + "]";
	}
	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Integer getCreator() {
		return creator;
	}
	public void setCreator(Integer creator) {
		this.creator = creator;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getUseDateBeginString() {
		return useDateBeginString;
	}
	public void setUseDateBeginString(String useDateBeginString) {
		this.useDateBeginString = useDateBeginString;
	}
	public String getUseDateEndString() {
		return useDateEndString;
	}
	public void setUseDateEndString(String useDateEndString) {
		this.useDateEndString = useDateEndString;
	}
	public String getCreateTimeString() {
		return createTimeString;
	}
	public void setCreateTimeString(String createTimeString) {
		this.createTimeString = createTimeString;
	}

}
