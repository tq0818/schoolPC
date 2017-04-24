package com.yuxin.wx.model.queAns;


import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:QuestionClassifyRelation
 * 
 * @author wang.zx
 * @date 2015-12-14
 */
@SuppressWarnings("serial")
public class QuestionClassifyRelation extends BaseEntity {
	
	
	private Integer	questionId;		 /* 问题ID */ 
	private Integer	secondeItemId;		 /* 问题对应的学科小类的ID */ 
	private String	secondeName;		 /* 学科小类名称，如果有别名则保存别名 */ 
	private Integer classifyId;		 /*标签表id*/
	// Constructor
	public QuestionClassifyRelation() {
	}
	
	/**
	 * full Constructor
	 */
	public QuestionClassifyRelation(Integer id, Integer questionId, Integer secondeItemId, String secondeName,Integer classifyId) {
		setId(id);
		this.questionId = questionId;
		this.secondeItemId = secondeItemId;
		this.secondeName = secondeName;
		this.classifyId = classifyId;
	}

	// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为QuestionClassifyRelation可以实现连缀设置属性
	
	public Integer getQuestionId() {
		return questionId;
	}

	public QuestionClassifyRelation setQuestionId(Integer questionId) {
		this.questionId = questionId;
		return this;
	}
	
	
	public Integer getSecondeItemId() {
		return secondeItemId;
	}

	public QuestionClassifyRelation setSecondeItemId(Integer secondeItemId) {
		this.secondeItemId = secondeItemId;
		return this;
	}
	
	
	public String getSecondeName() {
		return secondeName;
	}

	public QuestionClassifyRelation setSecondeName(String secondeName) {
		this.secondeName = secondeName;
		return this;
	}
	
	@Override
	public String toString() {
		return "QuestionClassifyRelation [" + "id=" + getId() + ", questionId=" + questionId + ", secondeItemId=" + secondeItemId + ", secondeName=" + secondeName +  "]";
	}

	public Integer getClassifyId() {
		return classifyId;
	}

	public void setClassifyId(Integer classifyId) {
		this.classifyId = classifyId;
	}
}
