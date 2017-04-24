package com.yuxin.wx.model.company;

import java.util.Date;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import com.yuxin.wx.util.ShortDateSerializer;

import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:CompanyStudyCard
 * 
 * @author chopin
 * @date 2017-3-14
 */
@SuppressWarnings("serial")
public class CompanyStudyCard extends BaseEntity {
	
	
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
	
	private String startDate;
	private String endDate;

	// Constructor
	public CompanyStudyCard() {
	}
	
	/**
	 * full Constructor
	 */
	public CompanyStudyCard(Integer id, String name, String courseList, Integer courseType, Integer totalNum, Integer usedNum, Date useDateBegin, Date useDateEnd, String proxyOrgId, String proxyOrgName, String prefix, Double price, String description, Integer companyId) {
		setId(id);
		this.name = name;
		this.courseList = courseList;
		this.courseType = courseType;
		this.totalNum = totalNum;
		this.usedNum = usedNum;
		this.useDateBegin = useDateBegin;
		this.useDateEnd = useDateEnd;
		this.proxyOrgId = proxyOrgId;
		this.proxyOrgName = proxyOrgName;
		this.prefix = prefix;
		this.price = price;
		this.description = description;
		this.companyId = companyId;
	}

	// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为CompanyStudyCard可以实现连缀设置属性
	
	public String getName() {
		return name;
	}

	public CompanyStudyCard setName(String name) {
		this.name = name;
		return this;
	}
	
	
	public String getCourseList() {
		return courseList;
	}

	public CompanyStudyCard setCourseList(String courseList) {
		this.courseList = courseList;
		return this;
	}
	
	
	public Integer getCourseType() {
		return courseType;
	}

	public CompanyStudyCard setCourseType(Integer courseType) {
		this.courseType = courseType;
		return this;
	}
	
	
	public Integer getTotalNum() {
		return totalNum;
	}

	public CompanyStudyCard setTotalNum(Integer totalNum) {
		this.totalNum = totalNum;
		return this;
	}
	
	
	public Integer getUsedNum() {
		return usedNum;
	}

	public CompanyStudyCard setUsedNum(Integer usedNum) {
		this.usedNum = usedNum;
		return this;
	}
	
	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getUseDateBegin() {
		return useDateBegin;
	}

	public CompanyStudyCard setUseDateBegin(Date useDateBegin) {
		this.useDateBegin = useDateBegin;
		return this;
	}
	
	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getUseDateEnd() {
		return useDateEnd;
	}

	public CompanyStudyCard setUseDateEnd(Date useDateEnd) {
		this.useDateEnd = useDateEnd;
		return this;
	}
	
	
	public String getProxyOrgId() {
		return proxyOrgId;
	}

	public CompanyStudyCard setProxyOrgId(String proxyOrgId) {
		this.proxyOrgId = proxyOrgId;
		return this;
	}
	
	
	public String getProxyOrgName() {
		return proxyOrgName;
	}

	public CompanyStudyCard setProxyOrgName(String proxyOrgName) {
		this.proxyOrgName = proxyOrgName;
		return this;
	}
	
	
	public String getPrefix() {
		return prefix;
	}

	public CompanyStudyCard setPrefix(String prefix) {
		this.prefix = prefix;
		return this;
	}
	
	
	public Double getPrice() {
		return price;
	}

	public CompanyStudyCard setPrice(Double price) {
		this.price = price;
		return this;
	}
	
	
	public String getDescription() {
		return description;
	}

	public CompanyStudyCard setDescription(String description) {
		this.description = description;
		return this;
	}
	
	
	public Integer getCompanyId() {
		return companyId;
	}

	public CompanyStudyCard setCompanyId(Integer companyId) {
		this.companyId = companyId;
		return this;
	}
	
	@Override
	public String toString() {
		return "CompanyStudyCard [" + "id=" + getId() + ", name=" + name + ", courseList=" + courseList + ", courseType=" + courseType + ", totalNum=" + totalNum + ", usedNum=" + usedNum + ", useDateBegin=" + useDateBegin + ", useDateEnd=" + useDateEnd + ", proxyOrgId=" + proxyOrgId + ", proxyOrgName=" + proxyOrgName + ", prefix=" + prefix + ", price=" + price + ", description=" + description + ", companyId=" + companyId +  "]";
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
}
