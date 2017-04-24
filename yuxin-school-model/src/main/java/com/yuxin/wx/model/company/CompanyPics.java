package com.yuxin.wx.model.company;


import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:CompanyPics
 * 
 * @author chopin
 * @date 2015-5-14
 */
@SuppressWarnings("serial")
public class CompanyPics extends BaseEntity {
	
	
	private String	picName;		 /* 图片名字 */ 
	private String	picOriginalUrl;		 /* 图片存储路径 */ 
	private String	picBigUrl;		 /* 大图存储路径（446*241） */ 
	private String	picMiddleUrl;		 /* 中图存储路径（255*138） */ 
	private String	picSmallUrl;		 /* 小图存储路径（181*96） */ 
	private String	picTag;		 /* 图片所属标签，公有图片使用该字段 */ 
	private String	picType;		 /* 图片类型：班型 */ 
	private Integer	itemOneId;		 /* 图片所属项目，私有图片使用该字段 */ 
	private Integer	companyId;		 /* 所属公司id，默认值为0，属于所有公司公有图片 */ 

	// Constructor
	public CompanyPics() {
	}
	
	/**
	 * full Constructor
	 */
	public CompanyPics(Integer id, String picName, String picOriginalUrl, String picBigUrl, String picMiddleUrl, String picSmallUrl, String picTag, String picType, Integer itemOneId, Integer companyId) {
		setId(id);
		this.picName = picName;
		this.picOriginalUrl = picOriginalUrl;
		this.picBigUrl = picBigUrl;
		this.picMiddleUrl = picMiddleUrl;
		this.picSmallUrl = picSmallUrl;
		this.picTag = picTag;
		this.picType = picType;
		this.itemOneId = itemOneId;
		this.companyId = companyId;
	}

	// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为CompanyPics可以实现连缀设置属性
	
	public String getPicName() {
		return picName;
	}

	public CompanyPics setPicName(String picName) {
		this.picName = picName;
		return this;
	}
	
	
	public String getPicOriginalUrl() {
		return picOriginalUrl;
	}

	public CompanyPics setPicOriginalUrl(String picOriginalUrl) {
		this.picOriginalUrl = picOriginalUrl;
		return this;
	}
	
	
	public String getPicBigUrl() {
		return picBigUrl;
	}

	public CompanyPics setPicBigUrl(String picBigUrl) {
		this.picBigUrl = picBigUrl;
		return this;
	}
	
	
	public String getPicMiddleUrl() {
		return picMiddleUrl;
	}

	public CompanyPics setPicMiddleUrl(String picMiddleUrl) {
		this.picMiddleUrl = picMiddleUrl;
		return this;
	}
	
	
	public String getPicSmallUrl() {
		return picSmallUrl;
	}

	public CompanyPics setPicSmallUrl(String picSmallUrl) {
		this.picSmallUrl = picSmallUrl;
		return this;
	}
	
	
	public String getPicTag() {
		return picTag;
	}

	public CompanyPics setPicTag(String picTag) {
		this.picTag = picTag;
		return this;
	}
	
	
	public String getPicType() {
		return picType;
	}

	public CompanyPics setPicType(String picType) {
		this.picType = picType;
		return this;
	}
	
	
	public Integer getItemOneId() {
		return itemOneId;
	}

	public CompanyPics setItemOneId(Integer itemOneId) {
		this.itemOneId = itemOneId;
		return this;
	}
	
	
	public Integer getCompanyId() {
		return companyId;
	}

	public CompanyPics setCompanyId(Integer companyId) {
		this.companyId = companyId;
		return this;
	}
	
	@Override
	public String toString() {
		return "CompanyPics [" + "id=" + getId() + ", picName=" + picName + ", picOriginalUrl=" + picOriginalUrl + ", picBigUrl=" + picBigUrl + ", picMiddleUrl=" + picMiddleUrl + ", picSmallUrl=" + picSmallUrl + ", picTag=" + picTag + ", picType=" + picType + ", itemOneId=" + itemOneId + ", companyId=" + companyId +  "]";
	}
}
