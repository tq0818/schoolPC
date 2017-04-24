package com.yuxin.wx.model.system;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.yuxin.wx.common.BaseEntity;
import com.yuxin.wx.util.ShortDateSerializer;

/**
 * POJO:SysCyclePic
 * 
 * @author chopin
 * @date 2015-4-11
 */
@SuppressWarnings("serial")
public class SysCyclePic extends BaseEntity {
	
	
	private String	picTitle;		 /* 图片的标题 */ 
	private String	picDesc;		 /* 图片详细描述 */ 
	private String	picUrl;		 /* 图片存放路径 */ 
	private String	clickUrl;		 /* 点击图片打开的地址 */ 
	private Integer	validFlag;		 /* 图片有效标记（1：有效；0：无效） */ 
	private String	picType;		 /* 轮播图类型（首页轮播图：homepage；） */ 
	private Integer	companyId;		
	private Integer	schoolId;		
	private Integer	creator;		
	private Date	createTime;		
	private Integer	updator;		
	private Date	updateTime;
	private Integer picSequence;

	private String typeName;
	// Constructor
	public SysCyclePic() {
	}
	
	/**
	 * full Constructor
	 */
	public SysCyclePic(Integer id, String picTitle, String picDesc, String picUrl, String clickUrl, Integer validFlag, String picType, Integer companyId, Integer schoolId, Integer creator, Date createTime, Integer updator, Date updateTime,Integer picSequence) {
		setId(id);
		this.picTitle = picTitle;
		this.picDesc = picDesc;
		this.picUrl = picUrl;
		this.clickUrl = clickUrl;
		this.validFlag = validFlag;
		this.picType = picType;
		this.companyId = companyId;
		this.schoolId = schoolId;
		this.creator = creator;
		this.createTime = createTime;
		this.updator = updator;
		this.updateTime = updateTime;
		this.picSequence = picSequence;
	}

	// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为SysCyclePic可以实现连缀设置属性
	
	public String getPicTitle() {
		return picTitle;
	}

	public SysCyclePic setPicTitle(String picTitle) {
		this.picTitle = picTitle;
		return this;
	}
	
	
	public String getPicDesc() {
		return picDesc;
	}

	public SysCyclePic setPicDesc(String picDesc) {
		this.picDesc = picDesc;
		return this;
	}
	
	
	public String getPicUrl() {
		return picUrl;
	}

	public SysCyclePic setPicUrl(String picUrl) {
		this.picUrl = picUrl;
		return this;
	}
	
	
	public String getClickUrl() {
		return clickUrl;
	}

	public SysCyclePic setClickUrl(String clickUrl) {
		this.clickUrl = clickUrl;
		return this;
	}
	
	
	public Integer getValidFlag() {
		return validFlag;
	}

	public SysCyclePic setValidFlag(Integer validFlag) {
		this.validFlag = validFlag;
		return this;
	}
	
	
	public String getPicType() {
		return picType;
	}

	public SysCyclePic setPicType(String picType) {
		this.picType = picType;
		return this;
	}
	
	
	public Integer getCompanyId() {
		return companyId;
	}

	public SysCyclePic setCompanyId(Integer companyId) {
		this.companyId = companyId;
		return this;
	}
	
	
	public Integer getSchoolId() {
		return schoolId;
	}

	public SysCyclePic setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
		return this;
	}
	
	
	public Integer getCreator() {
		return creator;
	}

	public SysCyclePic setCreator(Integer creator) {
		this.creator = creator;
		return this;
	}
	
	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getCreateTime() {
		return createTime;
	}

	public SysCyclePic setCreateTime(Date createTime) {
		this.createTime = createTime;
		return this;
	}
	
	
	public Integer getUpdator() {
		return updator;
	}

	public SysCyclePic setUpdator(Integer updator) {
		this.updator = updator;
		return this;
	}
	
	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getUpdateTime() {
		return updateTime;
	}

	public SysCyclePic setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
		return this;
	}

	public Integer getPicSequence() {
		return picSequence;
	}

	public void setPicSequence(Integer picSequence) {
		this.picSequence = picSequence;
	}
	
	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	@Override
	public String toString() {
		return "SysCyclePic [" + "id=" + getId() + ", picTitle=" + picTitle + ", picDesc=" + picDesc + ", picUrl=" + picUrl + ", clickUrl=" + clickUrl + ", validFlag=" + validFlag + ", picType=" + picType + ", companyId=" + companyId + ", schoolId=" + schoolId + ", creator=" + creator + ", createTime=" + createTime + ", updator=" + updator + ", updateTime=" + updateTime +  "]";
	}
}
