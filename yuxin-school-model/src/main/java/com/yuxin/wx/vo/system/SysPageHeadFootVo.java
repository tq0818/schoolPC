package com.yuxin.wx.vo.system;

import java.util.Date;
import java.util.List;

import com.yuxin.wx.common.BaseEntity;

/**
 * @ClassName: ConfigPageAndHeadVo
 * @Description:首页的页头和页尾
 * @author zhang.zx
 * @date 2015-4-11
 * @version 1.0
 */
@SuppressWarnings("serial")
public class SysPageHeadFootVo extends BaseEntity{

	private String name;  /* 导航名称*/
	private String url;   /* 导航打开地址*/
	private Integer parentId;   /* 父节点id*/
	private String pageType;  /* 页面类型（页头：head；页尾：foot）*/
	private String fileUrl;  /* 手动上传的文件在图片服务器上的地址，相对地址*/
	private String content;  /* 页面内容 */
	private Integer companyId;  /* 所属公司id */ 
	private Integer schoolId;   /* 所属分校id */ 
	private Integer validFlag;	/* 有效标记（1：有效；0：无效）*/
	private String  openType;   /* 打开方式*/
	private Date	createTime;	
	private Integer	creator;			
	private Date	updateTime;	
	private Integer	updator;
	
	private String copyRight;
	private String regitNo;
	private Integer count;
	private Integer sequence;
	private String pageHeadType;
	
	private Integer configId;
	private String urlType;
	
	private List<SysPageHeadFootVo> list;
	
	private String companyConfigIdj;
	
	public SysPageHeadFootVo() {
	}

	public SysPageHeadFootVo(String name, String url, Integer parentId,
			String pageType, String fileUrl, String content, Integer companyId,
			Integer schoolId, Integer validFlag, String openType, Date createTime,
			Integer creator, Date updateTime, Integer updator) {
		super();
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

	public String getCompanyConfigIdj() {
		return companyConfigIdj;
	}

	public void setCompanyConfigIdj(String companyConfigIdj) {
		this.companyConfigIdj = companyConfigIdj;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public String getPageType() {
		return pageType;
	}

	public void setPageType(String pageType) {
		this.pageType = pageType;
	}

	public String getFileUrl() {
		return fileUrl;
	}

	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	public Integer getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
	}

	public Integer getValidFlag() {
		return validFlag;
	}

	public void setValidFlag(Integer validFlag) {
		this.validFlag = validFlag;
	}
	
	public String getOpenType() {
		return openType;
	}

	public void setOpenType(String openType) {
		this.openType = openType;
	}

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

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getUpdator() {
		return updator;
	}

	public void setUpdator(Integer updator) {
		this.updator = updator;
	}

	public String getCopyRight() {
		return copyRight;
	}

	public void setCopyRight(String copyRight) {
		this.copyRight = copyRight;
	}

	public String getRegitNo() {
		return regitNo;
	}

	public void setRegitNo(String regitNo) {
		this.regitNo = regitNo;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
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

	public List<SysPageHeadFootVo> getList() {
		return list;
	}

	public void setList(List<SysPageHeadFootVo> list) {
		this.list = list;
	}
	
}
