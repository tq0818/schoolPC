package com.yuxin.wx.model.course;


import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:VideoTag
 * 
 * @author wang.zx
 * @date 2015-5-8
 */
@SuppressWarnings("serial")
public class VideoTag extends BaseEntity {
	
	
	private String	tagName;		
	private Integer	companyId;		

	// Constructor
	public VideoTag() {
	}
	
	/**
	 * full Constructor
	 */
	public VideoTag(Integer id, String tagName, Integer companyId) {
		setId(id);
		this.tagName = tagName;
		this.companyId = companyId;
	}

	// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为VideoTag可以实现连缀设置属性
	
	public String getTagName() {
		return tagName;
	}

	public VideoTag setTagName(String tagName) {
		this.tagName = tagName;
		return this;
	}
	
	
	public Integer getCompanyId() {
		return companyId;
	}

	public VideoTag setCompanyId(Integer companyId) {
		this.companyId = companyId;
		return this;
	}
	
	@Override
	public String toString() {
		return "VideoTag [" + "id=" + getId() + ", tagName=" + tagName + ", companyId=" + companyId +  "]";
	}
}
