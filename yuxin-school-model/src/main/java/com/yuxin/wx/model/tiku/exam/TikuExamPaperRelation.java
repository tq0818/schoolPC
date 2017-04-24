package com.yuxin.wx.model.tiku.exam;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.yuxin.wx.common.BaseEntity;
import com.yuxin.wx.util.ShortDateSerializer;

/**
 * POJO:TikuExamPaperRelation
 * 
 * @author wang.zx
 * @date 2016-2-17
 */
@SuppressWarnings("serial")
public class TikuExamPaperRelation extends BaseEntity {
	
	
	private Integer	tikuExamId;		 /* 题库考试表ID */ 
	private Integer	tikuPaperId;		 /* 题库试卷表ID */ 
	private Integer	status;		 /* 状态（1. 使用中  2. 已删除） */ 
	private Date	createTime;		 /* 创建时间 */ 
	private Integer	creator;		 /* 创建人 */ 
	private Date	updateTime;		 /* 修改时间 */ 
	private Integer	updator;		 /* 修改人 */ 

	// Constructor
	public TikuExamPaperRelation() {
	}
	
	/**
	 * full Constructor
	 */
	public TikuExamPaperRelation(Integer id, Integer tikuExamId, Integer tikuPaperId, Integer status, Date createTime, Integer creator) {
		setId(id);
		this.tikuExamId = tikuExamId;
		this.tikuPaperId = tikuPaperId;
		this.status = status;
		this.createTime = createTime;
		this.creator = creator;
	}

	// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为TikuExamPaperRelation可以实现连缀设置属性
	
	public Integer getTikuExamId() {
		return tikuExamId;
	}

	public TikuExamPaperRelation setTikuExamId(Integer tikuExamId) {
		this.tikuExamId = tikuExamId;
		return this;
	}
	
	
	public Integer getTikuPaperId() {
		return tikuPaperId;
	}

	public TikuExamPaperRelation setTikuPaperId(Integer tikuPaperId) {
		this.tikuPaperId = tikuPaperId;
		return this;
	}
	
	
	public Integer getStatus() {
		return status;
	}

	public TikuExamPaperRelation setStatus(Integer status) {
		this.status = status;
		return this;
	}
	
	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getCreateTime() {
		return createTime;
	}

	public TikuExamPaperRelation setCreateTime(Date createTime) {
		this.createTime = createTime;
		return this;
	}
	
	
	public Integer getCreator() {
		return creator;
	}

	public TikuExamPaperRelation setCreator(Integer creator) {
		this.creator = creator;
		return this;
	}
	
	@Override
	public String toString() {
		return "TikuExamPaperRelation [" + "id=" + getId() + ", tikuExamId=" + tikuExamId + ", tikuPaperId=" + tikuPaperId + ", status=" + status + ", createTime=" + createTime + ", creator=" + creator +  "]";
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getUpdator() {
		return updator;
	}

	public void setUpdator(Integer updator) {
		this.updator = updator;
	}
}
