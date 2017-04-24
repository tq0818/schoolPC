package com.yuxin.wx.vo.system;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.yuxin.wx.util.ShortDateSerializer;
import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:SysNews
 * 
 * @author wang.zx
 * @date 2015-3-14
 */
@SuppressWarnings("serial")
public class SysNewsVo extends BaseEntity {
	
	
	private String	newsTitle;		 /* 新闻标题 */
	private String  summary;         /* 新闻概述*/
	private String	newsStatus;		 /* 新闻的状态（1：已发布:0：未发布） */ 
	private String	newsContent;		 /* 新闻详细内容 */
	private String  newsType;        /* 新闻类型*/
	private String	newsPic;		 /* 新闻图片 */ 
	private Date	publishTime;		 /* 发布时间 */ 
	private Integer	publishUser;		 /* 发布者 */ 
	private Integer	creator;
	private Date	createTime;		
	private Integer	updator;		
	private Date	updateTime;		
	private Integer	companyId;		
	private Integer	schoolId;
	private Date startTime;
	private Date endTime;
	private String userName;
	private Integer recommendFlag;
	private String  orderType;
	private Integer delFlag;
	private String cusorder;
	private String cuslimit;
	private Integer clickCount;
	private String newsTypeName;
	private String createName;
	
	private String orderBy;
	// Constructor
	public SysNewsVo() {
	}
	
	/**
	 * full Constructor
	 */
	public SysNewsVo(Integer id, String newsTitle, String summary, String newsStatus, String newsContent, String newsType, String newsPic, Date publishTime, Integer publishUser, Integer creator, Date createTime, Integer updator, Date updateTime, Integer companyId, Integer schoolId) {
		setId(id);
		this.newsTitle = newsTitle;
		this.summary= summary;
		this.newsStatus = newsStatus;
		this.newsContent = newsContent;
		this.newsType = newsType;
		this.newsPic = newsPic;
		this.publishTime = publishTime;
		this.publishUser = publishUser;
		this.creator = creator;
		this.createTime = createTime;
		this.updator = updator;
		this.updateTime = updateTime;
		this.companyId = companyId;
		this.schoolId = schoolId;
	}

	// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为SysNews可以实现连缀设置属性
	
	public String getNewsTitle() {
		return newsTitle;
	}

	public SysNewsVo setNewsTitle(String newsTitle) {
		this.newsTitle = newsTitle;
		return this;
	}
	
	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getNewsStatus() {
		return newsStatus;
	}

	public SysNewsVo setNewsStatus(String newsStatus) {
		this.newsStatus = newsStatus;
		return this;
	}
	
	
	public String getNewsContent() {
		return newsContent;
	}

	public SysNewsVo setNewsContent(String newsContent) {
		this.newsContent = newsContent;
		return this;
	}
	
	public String getNewsType() {
		return newsType;
	}

	public void setNewsType(String newsType) {
		this.newsType = newsType;
	}

	public String getNewsPic() {
		return newsPic;
	}

	public SysNewsVo setNewsPic(String newsPic) {
		this.newsPic = newsPic;
		return this;
	}
	
	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getPublishTime() {
		return publishTime;
	}

	public SysNewsVo setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
		return this;
	}
	
	
	public Integer getPublishUser() {
		return publishUser;
	}

	public SysNewsVo setPublishUser(Integer publishUser) {
		this.publishUser = publishUser;
		return this;
	}
	
	
	public Integer getCreator() {
		return creator;
	}

	public SysNewsVo setCreator(Integer creator) {
		this.creator = creator;
		return this;
	}
	
	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getCreateTime() {
		return createTime;
	}

	public SysNewsVo setCreateTime(Date createTime) {
		this.createTime = createTime;
		return this;
	}
	
	
	public Integer getUpdator() {
		return updator;
	}

	public SysNewsVo setUpdator(Integer updator) {
		this.updator = updator;
		return this;
	}
	
	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getUpdateTime() {
		return updateTime;
	}

	public SysNewsVo setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
		return this;
	}
	
	
	public Integer getCompanyId() {
		return companyId;
	}

	public SysNewsVo setCompanyId(Integer companyId) {
		this.companyId = companyId;
		return this;
	}
	
	
	public Integer getSchoolId() {
		return schoolId;
	}

	public SysNewsVo setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
		return this;
	}
	
	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getCusorder() {
		return cusorder;
	}

	public void setCusorder(String cusorder) {
		this.cusorder = cusorder;
	}

	public String getCuslimit() {
		return cuslimit;
	}

	public void setCuslimit(String cuslimit) {
		this.cuslimit = cuslimit;
	}

	@Override
	public String toString() {
		return "SysNews [" + "id=" + getId() + ", newsTitle=" + newsTitle + ", newsStatus=" + newsStatus + ", newsContent=" + newsContent + ", newsPic=" + newsPic + ", publishTime=" + publishTime + ", publishUser=" + publishUser + ", creator=" + creator + ", createTime=" + createTime + ", updator=" + updator + ", updateTime=" + updateTime + ", companyId=" + companyId + ", schoolId=" + schoolId +  "]";
	}

	public Integer getClickCount() {
		return clickCount;
	}

	public void setClickCount(Integer clickCount) {
		this.clickCount = clickCount;
	}

	public Integer getRecommendFlag() {
		return recommendFlag;
	}

	public void setRecommendFlag(Integer recommendFlag) {
		this.recommendFlag = recommendFlag;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public Integer getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}

	public String getNewsTypeName() {
		return newsTypeName;
	}

	public void setNewsTypeName(String newsTypeName) {
		this.newsTypeName = newsTypeName;
	}

	public String getCreateName() {
		return createName;
	}

	public void setCreateName(String createName) {
		this.createName = createName;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

}
