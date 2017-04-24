package com.yuxin.wx.model.tiku.exam;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.yuxin.wx.common.BaseEntity;
import com.yuxin.wx.util.ShortDateSerializer;

/**
 * POJO:TikuExamUserRelation
 * 
 * @author wang.zx
 * @date 2016-2-17
 */
@SuppressWarnings("serial")
public class TikuExamUserRelation extends BaseEntity {
	
	
	private Integer	tikuExamId;		 /* 考试表ID */ 
	private Integer	tikuPaperId;		 /* 试卷ID */ 
	private Integer tikuUserExerciseId;/*tikuUserExercise表id*/
	private Integer	userId;		 /* 当前用户ID */ 
	private Integer	status;		 /* 是否通过考试（0. 未通过 1. 通过） */ 
	private Double	score;		 /* 用户考试得分 */ 
	private Date	createTime;		 /* 创建时间 */ 
	private Integer	creator;		 /* 创建人 */ 
	

	// Constructor
	public TikuExamUserRelation() {
	}
	
	/**
	 * full Constructor
	 */
	public TikuExamUserRelation(Integer id, Integer tikuExamId, Integer tikuPaperId, Integer userId, Integer status, Double score, Date createTime, Integer creator) {
		setId(id);
		this.tikuExamId = tikuExamId;
		this.tikuPaperId = tikuPaperId;
		this.userId = userId;
		this.status = status;
		this.score = score;
		this.createTime = createTime;
		this.creator = creator;
	}

	// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为TikuExamUserRelation可以实现连缀设置属性
	
	public Integer getTikuExamId() {
		return tikuExamId;
	}

	public TikuExamUserRelation setTikuExamId(Integer tikuExamId) {
		this.tikuExamId = tikuExamId;
		return this;
	}
	
	
	public Integer getTikuPaperId() {
		return tikuPaperId;
	}

	public TikuExamUserRelation setTikuPaperId(Integer tikuPaperId) {
		this.tikuPaperId = tikuPaperId;
		return this;
	}
	
	
	public Integer getUserId() {
		return userId;
	}

	public TikuExamUserRelation setUserId(Integer userId) {
		this.userId = userId;
		return this;
	}
	
	
	public Integer getStatus() {
		return status;
	}

	public TikuExamUserRelation setStatus(Integer status) {
		this.status = status;
		return this;
	}
	
	
	public Double getScore() {
		return score;
	}

	public TikuExamUserRelation setScore(Double score) {
		this.score = score;
		return this;
	}
	
	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getCreateTime() {
		return createTime;
	}

	public TikuExamUserRelation setCreateTime(Date createTime) {
		this.createTime = createTime;
		return this;
	}
	
	
	public Integer getCreator() {
		return creator;
	}

	public TikuExamUserRelation setCreator(Integer creator) {
		this.creator = creator;
		return this;
	}
	
	@Override
	public String toString() {
		return "TikuExamUserRelation [" + "id=" + getId() + ", tikuExamId=" + tikuExamId + ", tikuPaperId=" + tikuPaperId + ", userId=" + userId + ", status=" + status + ", score=" + score + ", createTime=" + createTime + ", creator=" + creator +  "]";
	}

	public Integer getTikuUserExerciseId() {
		return tikuUserExerciseId;
	}

	public void setTikuUserExerciseId(Integer tikuUserExerciseId) {
		this.tikuUserExerciseId = tikuUserExerciseId;
	}
}
