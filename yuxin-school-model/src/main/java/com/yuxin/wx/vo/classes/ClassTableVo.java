package com.yuxin.wx.vo.classes;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.yuxin.wx.util.ShortDateSerializer;
import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:ClassModuleLesson
 * @author wang.zx
 * @date 2014-12-5
 */
@SuppressWarnings("serial")
public class ClassTableVo extends BaseEntity {
	
	private String classTypeName;	/* 课程名*/
	private String	lessonName;		 /* 课次名称 */ 
	private Date	lessonDate;		 /* 上课日期 */ 
	private String	lessonTimeStart;		 /* 课次开始时间 */ 
	private String	lessonTimeEnd;		 /* 课次结束日期 */ 
	
	private Integer comId;				/* 商品id*/
	
	public ClassTableVo() {
		super();
	}

	public String getLessonName() {
		return lessonName;
	}

	public void setLessonName(String lessonName) {
		this.lessonName = lessonName;
	}

	public Date getLessonDate() {
		return lessonDate;
	}

	public void setLessonDate(Date lessonDate) {
		this.lessonDate = lessonDate;
	}

	public String getLessonTimeStart() {
		return lessonTimeStart;
	}

	public void setLessonTimeStart(String lessonTimeStart) {
		this.lessonTimeStart = lessonTimeStart;
	}

	public String getLessonTimeEnd() {
		return lessonTimeEnd;
	}

	public void setLessonTimeEnd(String lessonTimeEnd) {
		this.lessonTimeEnd = lessonTimeEnd;
	}

	public String getClassTypeName() {
		return classTypeName;
	}

	public void setClassTypeName(String classTypeName) {
		this.classTypeName = classTypeName;
	}

	public Integer getComId() {
		return comId;
	}

	public void setComId(Integer comId) {
		this.comId = comId;
	}
}
