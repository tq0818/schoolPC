package com.yuxin.wx.model.system;

import java.util.Date;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import com.yuxin.wx.util.ShortDateSerializer;

import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:SysConfigItemCourse
 * 
 * @author wang.zx
 * @date 2015-9-23
 */
@SuppressWarnings("serial")
public class SysConfigItemCourse extends BaseEntity {
	
	
	private Integer	companyId;		 /* 公司ID */ 
	private Integer	configItemId;		
	private Integer	validityDay;		 /* 课程有效期天数 */ 
	private Date	validityDate;		 /* 课程到期日期 */ 
	private Integer	videoWatchCount;		 /* 视频观看次数 */ 
	private Integer	liveWatchCount;		 /* 直播观看次数 */ 
	private Integer	delFlag;		 /* 删除标记：1（已删除）；0（未删除） */ 
	
	private String itemName;/*学科名称*/

	// Constructor
	public SysConfigItemCourse() {
	}
	
	/**
	 * full Constructor
	 */
	public SysConfigItemCourse(Integer id, Integer companyId, Integer configItemId, Integer validityDay, Date validityDate, Integer videoWatchCount, Integer liveWatchCount, Integer delFlag) {
		setId(id);
		this.companyId = companyId;
		this.configItemId = configItemId;
		this.validityDay = validityDay;
		this.validityDate = validityDate;
		this.videoWatchCount = videoWatchCount;
		this.liveWatchCount = liveWatchCount;
		this.delFlag = delFlag;
	}

	// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为SysConfigItemCourse可以实现连缀设置属性
	
	public Integer getCompanyId() {
		return companyId;
	}

	public SysConfigItemCourse setCompanyId(Integer companyId) {
		this.companyId = companyId;
		return this;
	}
	
	
	public Integer getConfigItemId() {
		return configItemId;
	}

	public SysConfigItemCourse setConfigItemId(Integer configItemId) {
		this.configItemId = configItemId;
		return this;
	}
	
	
	public Integer getValidityDay() {
		return validityDay;
	}

	public SysConfigItemCourse setValidityDay(Integer validityDay) {
		this.validityDay = validityDay;
		return this;
	}
	
	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getValidityDate() {
		return validityDate;
	}

	public SysConfigItemCourse setValidityDate(Date validityDate) {
		this.validityDate = validityDate;
		return this;
	}
	
	
	public Integer getVideoWatchCount() {
		return videoWatchCount;
	}

	public SysConfigItemCourse setVideoWatchCount(Integer videoWatchCount) {
		this.videoWatchCount = videoWatchCount;
		return this;
	}
	
	
	public Integer getLiveWatchCount() {
		return liveWatchCount;
	}

	public SysConfigItemCourse setLiveWatchCount(Integer liveWatchCount) {
		this.liveWatchCount = liveWatchCount;
		return this;
	}
	
	
	public Integer getDelFlag() {
		return delFlag;
	}

	public SysConfigItemCourse setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
		return this;
	}
	
	@Override
	public String toString() {
		return "SysConfigItemCourse [" + "id=" + getId() + ", companyId=" + companyId + ", configItemId=" + configItemId + ", validityDay=" + validityDay + ", validityDate=" + validityDate + ", videoWatchCount=" + videoWatchCount + ", liveWatchCount=" + liveWatchCount + ", delFlag=" + delFlag +  "]";
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
}
