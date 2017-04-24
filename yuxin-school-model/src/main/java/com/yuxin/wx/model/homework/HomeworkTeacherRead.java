package com.yuxin.wx.model.homework;


import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.yuxin.wx.common.BaseEntity;
import com.yuxin.wx.util.LongDateSerializer;

/**
 * POJO:HomeworkTeacherRead
 * 
 * @author chopin
 * @date 2016-12-15
 */
@SuppressWarnings("serial")
public class HomeworkTeacherRead extends BaseEntity {
	
	
	private Integer	reader;		 /* 批阅人  老师ID */ 
	private String	content;		
	private String	score;		
	private Integer	homeworkId;		
	private Integer	homeworkSId;		 /* 学员完成作业表ID */ 
	private String	resourceId;		 /* 附件地址 */ 
	private Date readTime;

	// Constructor
	public HomeworkTeacherRead() {
	}
	
	/**
	 * full Constructor
	 */
	public HomeworkTeacherRead(Integer id, Integer reader, String content, String score, Integer homeworkId, Integer homeworkSId, String resourceId) {
		setId(id);
		this.reader = reader;
		this.content = content;
		this.score = score;
		this.homeworkId = homeworkId;
		this.homeworkSId = homeworkSId;
		this.resourceId = resourceId;
	}

	// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为HomeworkTeacherRead可以实现连缀设置属性
	
	public Integer getReader() {
		return reader;
	}

	public HomeworkTeacherRead setReader(Integer reader) {
		this.reader = reader;
		return this;
	}
	
	
	public String getContent() {
		return content;
	}

	public HomeworkTeacherRead setContent(String content) {
		this.content = content;
		return this;
	}
	
	
	public String getScore() {
		return score;
	}

	public HomeworkTeacherRead setScore(String score) {
		this.score = score;
		return this;
	}
	
	
	public Integer getHomeworkId() {
		return homeworkId;
	}

	public HomeworkTeacherRead setHomeworkId(Integer homeworkId) {
		this.homeworkId = homeworkId;
		return this;
	}
	
	@Override
	public String toString() {
		return "HomeworkTeacherRead [" + "id=" + getId() + ", reader=" + reader + ", content=" + content + ", score=" + score + ", homeworkId=" + homeworkId + ", homeworkSId=" + homeworkSId + ", resourceId=" + resourceId +  "]";
	}

	@JsonSerialize(using = LongDateSerializer.class)
	public Date getReadTime() {
		return readTime;
	}

	public void setReadTime(Date readTime) {
		this.readTime = readTime;
	}

	public Integer getHomeworkSId() {
		return homeworkSId;
	}

	public HomeworkTeacherRead setHomeworkSId(Integer homeworkSId) {
		this.homeworkSId = homeworkSId;
		return this;
	}

	public String getResourceId() {
		return resourceId;
	}

	public HomeworkTeacherRead setResourceId(String resourceId) {
		this.resourceId = resourceId;
		return this;
	}
}
