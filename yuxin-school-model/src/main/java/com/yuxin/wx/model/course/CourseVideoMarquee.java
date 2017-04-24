package com.yuxin.wx.model.course;


import java.util.Date;

import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:CourseVideoMarquee
 * 
 * @author wang.zx
 * @date 2015-10-12
 */
@SuppressWarnings("serial")
public class CourseVideoMarquee extends BaseEntity {
	
	
	private Integer	cycleTime;		 /* 跑马灯循环次数， -1代表无限次循环 */ 
	private Integer	singleCostTime;		 /* 跑马灯循环一次花费的时间 */ 
	private String	content;		 /* 跑马灯内容 */ 
	private Integer	fontSize;		 /* 跑马灯字体大小 */ 
	private String	fontColor;		 /* 跑马灯颜色 */ 
	private Integer	marqueeLineId;		 /* 跑马灯对应的路线ID */ 
	private Integer	companyId;		 /* 公司ID */ 
	private Integer	stauts;		 /* 状态（0：未开通， 1：已开通） */ 
	private Date createTime;    /* 创建时间 */
	private Integer creator;    /* 创建人 */

	// Constructor
	public CourseVideoMarquee() {
	}
	
	/**
	 * full Constructor
	 */
	public CourseVideoMarquee(Integer id, Integer cycleTime, Integer singleCostTime, String content, Integer fontSize, String fontColor, Integer marqueeLineId, Integer companyId, 
			Integer stauts, Date createTime, Integer creator) {
		setId(id);
		this.cycleTime = cycleTime;
		this.singleCostTime = singleCostTime;
		this.content = content;
		this.fontSize = fontSize;
		this.fontColor = fontColor;
		this.marqueeLineId = marqueeLineId;
		this.companyId = companyId;
		this.stauts = stauts;
		this.createTime = createTime;
		this.creator = creator;
	}

	// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为CourseVideoMarquee可以实现连缀设置属性
	
	public Integer getCycleTime() {
		return cycleTime;
	}

	public CourseVideoMarquee setCycleTime(Integer cycleTime) {
		this.cycleTime = cycleTime;
		return this;
	}
	
	
	public Integer getSingleCostTime() {
		return singleCostTime;
	}

	public CourseVideoMarquee setSingleCostTime(Integer singleCostTime) {
		this.singleCostTime = singleCostTime;
		return this;
	}
	
	
	public String getContent() {
		return content;
	}

	public CourseVideoMarquee setContent(String content) {
		this.content = content;
		return this;
	}
	
	
	public Integer getFontSize() {
		return fontSize;
	}

	public CourseVideoMarquee setFontSize(Integer fontSize) {
		this.fontSize = fontSize;
		return this;
	}
	
	
	public String getFontColor() {
		return fontColor;
	}

	public CourseVideoMarquee setFontColor(String fontColor) {
		this.fontColor = fontColor;
		return this;
	}
	
	
	public Integer getMarqueeLineId() {
		return marqueeLineId;
	}

	public CourseVideoMarquee setMarqueeLineId(Integer marqueeLineId) {
		this.marqueeLineId = marqueeLineId;
		return this;
	}
	
	
	public Integer getCompanyId() {
		return companyId;
	}

	public CourseVideoMarquee setCompanyId(Integer companyId) {
		this.companyId = companyId;
		return this;
	}
	
	
	public Integer getStauts() {
		return stauts;
	}

	public CourseVideoMarquee setStauts(Integer stauts) {
		this.stauts = stauts;
		return this;
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

	@Override
	public String toString() {
		return "CourseVideoMarquee [" + "id=" + getId() + ", cycleTime=" + cycleTime + ", singleCostTime=" + singleCostTime + ", content=" + content + ", fontSize=" + fontSize + ", fontColor=" + fontColor + ", marqueeLineId=" + marqueeLineId + ", companyId=" + companyId + ", stauts=" + stauts + ", createTime=" + createTime + ", creator=" + creator + "]";
	}
}
