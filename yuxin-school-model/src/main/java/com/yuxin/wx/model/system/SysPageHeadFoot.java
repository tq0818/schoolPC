package com.yuxin.wx.model.system;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.yuxin.wx.common.BaseEntity;
import com.yuxin.wx.util.ShortDateSerializer;

/**
 * POJO:SysPageHeadFoot
 * 
 * @author chopin
 * @date 2015-4-11
 */
@SuppressWarnings("serial")
public class SysPageHeadFoot extends BaseEntity {
	
	
	private String	name;		 /* 导航名称 */ 
	private String	url;		 /* 导航打开地址 */ 
	private Integer	parentId;		 /* 父节点id */ 
	private String	pageType;		 /* 页面类型（页头：head；页尾：foot） */ 
	private String	fileUrl;		 /* 手动上传的文件在图片服务器上的地址，相对地址 */ 
	private String	content;		 /* 页面内容 */ 
	private Integer	companyId;		 /* 所属公司id */ 
	private Integer	schoolId;		 /* 所属分校id */ 
	private Integer	validFlag;		 /* 有效标记（1：有效；0：无效） */
	private String  openType;        /* 打开方式*/
	private Date	createTime;		
	private Integer	creator;		 /* 操作员ID */ 
	private Date	updateTime;		
	private Integer	updator;	
	private Integer sequence;
	private String pageHeadType;
	
	private Integer configId;
	private String urlType;

	// Constructor
	public SysPageHeadFoot() {
	}
	
	/**
	 * full Constructor
	 */
	public SysPageHeadFoot(Integer id, String name, String url, Integer parentId, String pageType, String fileUrl, String content, Integer companyId, Integer schoolId, Integer validFlag, String openType, Date createTime, Integer creator, Date updateTime, Integer updator) {
		setId(id);
		this.name = name;
		this.url = url;
		this.parentId = parentId;
		this.pageType = pageType;
		this.fileUrl = fileUrl;
		this.content = content;
		this.companyId = companyId;
		this.schoolId = schoolId;
		this.validFlag = validFlag;
		this.openType = openType;
		this.createTime = createTime;
		this.creator = creator;
		this.updateTime = updateTime;
		this.updator = updator;
	}

	// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为SysPageHeadFoot可以实现连缀设置属性
	
	public String getName() {
		return name;
	}

	public SysPageHeadFoot setName(String name) {
		this.name = name;
		return this;
	}
	
	
	public String getUrl() {
		return url;
	}

	public SysPageHeadFoot setUrl(String url) {
		this.url = url;
		return this;
	}
	
	
	public Integer getParentId() {
		return parentId;
	}

	public SysPageHeadFoot setParentId(Integer parentId) {
		this.parentId = parentId;
		return this;
	}
	
	
	public String getPageType() {
		return pageType;
	}

	public SysPageHeadFoot setPageType(String pageType) {
		this.pageType = pageType;
		return this;
	}
	
	
	public String getFileUrl() {
		return fileUrl;
	}

	public SysPageHeadFoot setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
		return this;
	}
	
	
	public String getContent() {
		return content;
	}

	public SysPageHeadFoot setContent(String content) {
		this.content = content;
		return this;
	}
	
	
	public Integer getCompanyId() {
		return companyId;
	}

	public SysPageHeadFoot setCompanyId(Integer companyId) {
		this.companyId = companyId;
		return this;
	}
	
	
	public Integer getSchoolId() {
		return schoolId;
	}

	public SysPageHeadFoot setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
		return this;
	}
	
	
	public Integer getValidFlag() {
		return validFlag;
	}

	public SysPageHeadFoot setValidFlag(Integer validFlag) {
		this.validFlag = validFlag;
		return this;
	}
	
	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getCreateTime() {
		return createTime;
	}
	
	public String getOpenType() {
		return openType;
	}

	public void setOpenType(String openType) {
		this.openType = openType;
	}

	public SysPageHeadFoot setCreateTime(Date createTime) {
		this.createTime = createTime;
		return this;
	}
	
	
	public Integer getCreator() {
		return creator;
	}

	public SysPageHeadFoot setCreator(Integer creator) {
		this.creator = creator;
		return this;
	}
	
	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getUpdateTime() {
		return updateTime;
	}

	public SysPageHeadFoot setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
		return this;
	}
	
	
	public Integer getUpdator() {
		return updator;
	}

	public SysPageHeadFoot setUpdator(Integer updator) {
		this.updator = updator;
		return this;
	}
	
	public Integer getSequence() {
		return sequence;
	}

	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}

	public String getPageHeadType() {
		return pageHeadType;
	}

	public void setPageHeadType(String pageHeadType) {
		this.pageHeadType = pageHeadType;
	}

	public Integer getConfigId() {
		return configId;
	}

	public void setConfigId(Integer configId) {
		this.configId = configId;
	}

	public String getUrlType() {
		return urlType;
	}

	public void setUrlType(String urlType) {
		this.urlType = urlType;
	}

	@Override
	public String toString() {
		return "SysPageHeadFoot [" + "id=" + getId() + ", name=" + name + ", url=" + url + ", parentId=" + parentId + ", pageType=" + pageType + ", fileUrl=" + fileUrl + ", content=" + content + ", companyId=" + companyId + ", schoolId=" + schoolId + ", validFlag=" + validFlag + ", createTime=" + createTime + ", creator=" + creator + ", updateTime=" + updateTime + ", updator=" + updator +  "]";
	}
}
