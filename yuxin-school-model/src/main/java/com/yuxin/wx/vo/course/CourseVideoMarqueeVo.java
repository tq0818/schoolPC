package com.yuxin.wx.vo.course;


import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:CourseVideoMarquee
 * 
 * @author wang.zx
 * @date 2015-10-12
 */
@SuppressWarnings("serial")
public class CourseVideoMarqueeVo extends BaseEntity {
	
	
	private Integer	cycleTime;		 /* 跑马灯循环次数， -1代表无限次循环 */ 
	private Integer	singleCostTime;		 /* 跑马灯循环一次花费的时间 */ 
	private String	content;		 /* 跑马灯内容 */ 
	private Integer	fontSize;		 /* 跑马灯字体大小 */ 
	private String	fontColor;		 /* 跑马灯颜色 */ 
	private Integer	marqueeLineId;		 /* 跑马灯对应的路线ID */ 
	private Integer	companyId;		 /* 公司ID */ 
	private Integer	stauts;		 /* 状态（0：未开通， 1：已开通） */ 
	
	private Float startXpos;		 /* 开始X轴位置 */ 
	private Float	startYpos;		 /* 开始Y轴位置 */ 
	private Float	endXpos;		 /* 结束X轴位置 */ 
	private Float	endYPos;		 /* 结束Y轴位置 */ 

	// Constructor
	public CourseVideoMarqueeVo() {
	}
	
	/**
	 * full Constructor
	 */
	public CourseVideoMarqueeVo(Integer id, Integer cycleTime, Integer singleCostTime, String content, Integer fontSize, String fontColor, Integer marqueeLineId, Integer companyId, Integer stauts,
			Float startXpos, Float startYpos, Float endXpos, Float endYPos) {
		setId(id);
		this.cycleTime = cycleTime;
		this.singleCostTime = singleCostTime;
		this.content = content;
		this.fontSize = fontSize;
		this.fontColor = fontColor;
		this.marqueeLineId = marqueeLineId;
		this.companyId = companyId;
		this.stauts = stauts;
		this.startXpos = startXpos;
		this.startYpos = startYpos;
		this.endXpos = endXpos;
		this.endYPos = endYPos;
	}

	// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为CourseVideoMarquee可以实现连缀设置属性
	
	public Integer getCycleTime() {
		return cycleTime;
	}

	public CourseVideoMarqueeVo setCycleTime(Integer cycleTime) {
		this.cycleTime = cycleTime;
		return this;
	}
	
	
	public Integer getSingleCostTime() {
		return singleCostTime;
	}

	public CourseVideoMarqueeVo setSingleCostTime(Integer singleCostTime) {
		this.singleCostTime = singleCostTime;
		return this;
	}
	
	
	public String getContent() {
		return content;
	}

	public CourseVideoMarqueeVo setContent(String content) {
		this.content = content;
		return this;
	}
	
	
	public Integer getFontSize() {
		return fontSize;
	}

	public CourseVideoMarqueeVo setFontSize(Integer fontSize) {
		this.fontSize = fontSize;
		return this;
	}
	
	
	public String getFontColor() {
		return fontColor;
	}

	public CourseVideoMarqueeVo setFontColor(String fontColor) {
		this.fontColor = fontColor;
		return this;
	}
	
	
	public Integer getMarqueeLineId() {
		return marqueeLineId;
	}

	public CourseVideoMarqueeVo setMarqueeLineId(Integer marqueeLineId) {
		this.marqueeLineId = marqueeLineId;
		return this;
	}
	
	
	public Integer getCompanyId() {
		return companyId;
	}

	public CourseVideoMarqueeVo setCompanyId(Integer companyId) {
		this.companyId = companyId;
		return this;
	}
	
	
	public Integer getStauts() {
		return stauts;
	}

	public CourseVideoMarqueeVo setStauts(Integer stauts) {
		this.stauts = stauts;
		return this;
	}

	public Float getStartXpos() {
		return startXpos;
	}

	public void setStartXpos(Float startXpos) {
		this.startXpos = startXpos;
	}

	public Float getStartYpos() {
		return startYpos;
	}

	public void setStartYpos(Float startYpos) {
		this.startYpos = startYpos;
	}

	public Float getEndXpos() {
		return endXpos;
	}

	public void setEndXpos(Float endXpos) {
		this.endXpos = endXpos;
	}

	public Float getEndYPos() {
		return endYPos;
	}

	public void setEndYPos(Float endYPos) {
		this.endYPos = endYPos;
	}

	@Override
	public String toString() {
		return "CourseVideoMarquee [" + "id=" + getId() + ", cycleTime=" + cycleTime + ", singleCostTime=" + singleCostTime + ", content=" + content + ", fontSize=" + fontSize + ", fontColor=" + fontColor + ", marqueeLineId=" + marqueeLineId + ", companyId=" + companyId + ", stauts=" + stauts + 
				", startXpos=" + startXpos + ", startYpos=" + startYpos + ", endXpos=" + endXpos + ", endYPos=" + endYPos + "]";
	}
}
