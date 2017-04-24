package com.yuxin.wx.vo.homework;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.yuxin.wx.common.BaseEntity;
import com.yuxin.wx.util.LongDateSerializer;

public class StudentHomeworkAttachmentVo  extends BaseEntity{
		
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private Integer homeworkId;
	private Integer stuId;
	private String stuContent;
	private Integer status;
	private String stuResourceId;
	private Integer paperId;
	private Integer completeFlag;
	private Date completeTime;
	private Integer companyId;
	
	private Integer reader;
	private String tContent;
	private String score;
	private String tResourceId;
	private Date readTime;
	
	
	public Integer getHomeworkId() {
		return homeworkId;
	}
	public void setHomeworkId(Integer homeworkId) {
		this.homeworkId = homeworkId;
	}
	public Integer getStuId() {
		return stuId;
	}
	public void setStuId(Integer stuId) {
		this.stuId = stuId;
	}
	public String getStuContent() {
		return stuContent;
	}
	public void setStuContent(String stuContent) {
		this.stuContent = stuContent;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getPaperId() {
		return paperId;
	}
	public void setPaperId(Integer paperId) {
		this.paperId = paperId;
	}
	public Integer getCompleteFlag() {
		return completeFlag;
	}
	public void setCompleteFlag(Integer completeFlag) {
		this.completeFlag = completeFlag;
	}
	@JsonSerialize(using = LongDateSerializer.class)
	public Date getCompleteTime() {
		return completeTime;
	}
	public void setCompleteTime(Date completeTime) {
		this.completeTime = completeTime;
	}
	public Integer getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}
	public Integer getReader() {
		return reader;
	}
	public void setReader(Integer reader) {
		this.reader = reader;
	}
	public String gettContent() {
		return tContent;
	}
	public void settContent(String tContent) {
		this.tContent = tContent;
	}
	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}
	@JsonSerialize(using = LongDateSerializer.class)
	public Date getReadTime() {
		return readTime;
	}
	public void setReadTime(Date readTime) {
		this.readTime = readTime;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getStuResourceId() {
		return stuResourceId;
	}
	public void setStuResourceId(String stuResourceId) {
		this.stuResourceId = stuResourceId;
	}
	public String gettResourceId() {
		return tResourceId;
	}
	public void settResourceId(String tResourceId) {
		this.tResourceId = tResourceId;
	}
}
