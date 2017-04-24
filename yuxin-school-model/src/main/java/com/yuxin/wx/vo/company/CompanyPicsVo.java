package com.yuxin.wx.vo.company;


import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:companypics
 * 
 * @author zhang.zx
 * @date 2015-5-11
 */
@SuppressWarnings("serial")
public class CompanyPicsVo extends BaseEntity {
	
	private String picName;
	private String picOriginalUrl;
	private String picBigUrl;
	private String picMiddleUrl;
	private String picSmallUrl;
	private String picTag;
	private String picType;
	private Integer itemOneId;
	private Integer companyId;
	private String realPath;
	
	public String getPicName() {
		return picName;
	}
	public void setPicName(String picName) {
		this.picName = picName;
	}
	public String getPicOriginalUrl() {
		return picOriginalUrl;
	}
	public void setPicOriginalUrl(String picOriginalUrl) {
		this.picOriginalUrl = picOriginalUrl;
	}
	public String getPicBigUrl() {
		return picBigUrl;
	}
	public void setPicBigUrl(String picBigUrl) {
		this.picBigUrl = picBigUrl;
	}
	public String getPicMiddleUrl() {
		return picMiddleUrl;
	}
	public void setPicMiddleUrl(String picMiddleUrl) {
		this.picMiddleUrl = picMiddleUrl;
	}
	public String getPicSmallUrl() {
		return picSmallUrl;
	}
	public void setPicSmallUrl(String picSmallUrl) {
		this.picSmallUrl = picSmallUrl;
	}
	public String getPicTag() {
		return picTag;
	}
	public void setPicTag(String picTag) {
		this.picTag = picTag;
	}
	public String getPicType() {
		return picType;
	}
	public void setPicType(String picType) {
		this.picType = picType;
	}
	
	
	public String getRealPath() {
		return realPath;
	}
	public void setRealPath(String realPath) {
		this.realPath = realPath;
	}
	public Integer getItemOneId() {
		return itemOneId;
	}
	public void setItemOneId(Integer itemOneId) {
		this.itemOneId = itemOneId;
	}
	public Integer getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}
	
}
