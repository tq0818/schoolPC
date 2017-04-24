package com.yuxin.wx.model.course;


import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:CourseVideoMarqueeLine
 * 
 * @author wang.zx
 * @date 2015-10-12
 */
@SuppressWarnings("serial")
public class CourseVideoMarqueeLine extends BaseEntity {
	
	
	private Float	startXpos;		 /* 开始X轴位置 */ 
	private Float	startYpos;		 /* 开始Y轴位置 */ 
	private Float	endXpos;		 /* 结束X轴位置 */ 
	private Float	endYPos;		 /* 结束Y轴位置 */ 

	// Constructor
	public CourseVideoMarqueeLine() {
	}
	
	/**
	 * full Constructor
	 */
	public CourseVideoMarqueeLine(Integer id, Float startXpos, Float startYpos, Float endXpos, Float endYPos) {
		setId(id);
		this.startXpos = startXpos;
		this.startYpos = startYpos;
		this.endXpos = endXpos;
		this.endYPos = endYPos;
	}

	// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为CourseVideoMarqueeLine可以实现连缀设置属性
	
	public Float getStartXpos() {
		return startXpos;
	}

	public CourseVideoMarqueeLine setStartXpos(Float startXpos) {
		this.startXpos = startXpos;
		return this;
	}
	
	
	public Float getStartYpos() {
		return startYpos;
	}

	public CourseVideoMarqueeLine setStartYpos(Float startYpos) {
		this.startYpos = startYpos;
		return this;
	}
	
	
	public Float getEndXpos() {
		return endXpos;
	}

	public CourseVideoMarqueeLine setEndXpos(Float endXpos) {
		this.endXpos = endXpos;
		return this;
	}
	
	
	public Float getEndYPos() {
		return endYPos;
	}

	public CourseVideoMarqueeLine setEndYPos(Float endYPos) {
		this.endYPos = endYPos;
		return this;
	}
	
	@Override
	public String toString() {
		return "CourseVideoMarqueeLine [" + "id=" + getId() + ", startXpos=" + startXpos + ", startYpos=" + startYpos + ", endXpos=" + endXpos + ", endYPos=" + endYPos +  "]";
	}
}
