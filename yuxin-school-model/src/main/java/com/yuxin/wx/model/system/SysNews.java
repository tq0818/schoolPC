package com.yuxin.wx.model.system;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.yuxin.wx.common.BaseEntity;
import com.yuxin.wx.util.ShortDateSerializer;

/**
 * POJO:SysNews
 * 
 * @author wang.zx
 * @date 2015-3-14
 */
@SuppressWarnings("serial")
public class SysNews extends BaseEntity {
	
	
	private String	newsTitle;		 /* 新闻标题 */
	private String  summary;
	private String	newsStatus;		 /* 新闻的状态（1：已发布:0：未发布） */ 
	private String	newsContent;     /* 新闻详细内容 */ 
	private String  newsType;
	private String	newsPic;		 /* 新闻图片 */ 
	private Date	publishTime;		 /* 发布时间 */ 
	private Integer	publishUser;		 /* 发布者 */ 
	private Integer	creator;		
	private Date	createTime;		
	private Integer	updator;		
	private Date	updateTime;		
	private Integer	companyId;		
	private Integer	schoolId;
	private String createName;
	private Integer recommendFlag;
	private Integer  clickCount;
	private Integer	delFlag;
	private String sortType;/*0-热门新闻 1-最新新闻 2-综合(排序用)*/
	private String newsTypeStr;
	private String secondTitle;//副标题
	private String keyWord;//关键词
	private String keyWord1;//关键词1
	private String keyWord2;//关键词2
	private String keyWord3;//关键词3
	private String keyWord4;//关键词4
	private String keyWord5;//关键词5
	private String author;//作者
	private Integer readBaseIndex;//阅读基数

	// Constructor
	public SysNews() {
	}
	
	/**
	 * full Constructor
	 */
	public SysNews(Integer id, String newsTitle, String summary, String newsStatus, String newsContent, String newsType, String newsPic, Date publishTime, Integer publishUser, Integer creator, Date createTime, Integer updator, Date updateTime, Integer companyId, Integer schoolId,Integer  clickCount) {
		setId(id);
		this.newsTitle = newsTitle;
		this.summary=summary;
		this.newsStatus = newsStatus;
		this.newsContent = newsContent;
		this.newsTitle= newsType;
		this.newsPic = newsPic;
		this.publishTime = publishTime;
		this.publishUser = publishUser;
		this.creator = creator;
		this.createTime = createTime;
		this.updator = updator;
		this.updateTime = updateTime;
		this.companyId = companyId;
		this.schoolId = schoolId;
		this.clickCount=clickCount;
	}

	// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为SysNews可以实现连缀设置属性
	
	public String getNewsTitle() {
		return newsTitle;
	}

	public SysNews setNewsTitle(String newsTitle) {
		this.newsTitle = newsTitle;
		return this;
	}
	
	
	public String getNewsStatus() {
		return newsStatus;
	}

	public SysNews setNewsStatus(String newsStatus) {
		this.newsStatus = newsStatus;
		return this;
	}
	
	
	public String getNewsContent() {
		return newsContent;
	}

	public SysNews setNewsContent(String newsContent) {
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

	public SysNews setNewsPic(String newsPic) {
		this.newsPic = newsPic;
		return this;
	}
	
	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getPublishTime() {
		return publishTime;
	}

	public SysNews setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
		return this;
	}
	
	
	public Integer getPublishUser() {
		return publishUser;
	}

	public SysNews setPublishUser(Integer publishUser) {
		this.publishUser = publishUser;
		return this;
	}
	
	
	public Integer getCreator() {
		return creator;
	}

	public SysNews setCreator(Integer creator) {
		this.creator = creator;
		return this;
	}
	
	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getCreateTime() {
		return createTime;
	}

	public SysNews setCreateTime(Date createTime) {
		this.createTime = createTime;
		return this;
	}
	
	
	public Integer getUpdator() {
		return updator;
	}

	public SysNews setUpdator(Integer updator) {
		this.updator = updator;
		return this;
	}
	
	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getUpdateTime() {
		return updateTime;
	}

	public SysNews setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
		return this;
	}
	
	
	public Integer getCompanyId() {
		return companyId;
	}

	public SysNews setCompanyId(Integer companyId) {
		this.companyId = companyId;
		return this;
	}
	
	
	public Integer getSchoolId() {
		return schoolId;
	}

	public SysNews setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
		return this;
	}
	
	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}
	
	public String getCreateName() {
		return createName;
	}

	public void setCreateName(String createName) {
		this.createName = createName;
	}

	public Integer getRecommendFlag() {
		return recommendFlag;
	}

	public void setRecommendFlag(Integer recommendFlag) {
		this.recommendFlag = recommendFlag;
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

	public Integer getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}

	public String getSortType() {
		return sortType;
	}

	public void setSortType(String sortType) {
		this.sortType = sortType;
	}
	
	public String getNewsTypeStr() {
		return newsTypeStr;
	}

	public void setNewsTypeStr(String newsTypeStr) {
		this.newsTypeStr = newsTypeStr;
	}

	public String getSecondTitle() {
		return secondTitle;
	}

	public void setSecondTitle(String secondTitle) {
		this.secondTitle = secondTitle;
	}

	public String getKeyWord() {
		return keyWord;
	}

	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Integer getReadBaseIndex() {
		return readBaseIndex;
	}

	public void setReadBaseIndex(Integer readBaseIndex) {
		this.readBaseIndex = readBaseIndex;
	}

	public String getKeyWord1() {
		return keyWord1;
	}

	public void setKeyWord1(String keyWord1) {
		this.keyWord1 = keyWord1;
	}

	public String getKeyWord2() {
		return keyWord2;
	}

	public void setKeyWord2(String keyWord2) {
		this.keyWord2 = keyWord2;
	}

	public String getKeyWord3() {
		return keyWord3;
	}

	public void setKeyWord3(String keyWord3) {
		this.keyWord3 = keyWord3;
	}

	public String getKeyWord4() {
		return keyWord4;
	}

	public void setKeyWord4(String keyWord4) {
		this.keyWord4 = keyWord4;
	}

	public String getKeyWord5() {
		return keyWord5;
	}

	public void setKeyWord5(String keyWord5) {
		this.keyWord5 = keyWord5;
	}
}
