package com.yuxin.wx.model.tiku;


import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:TikuTopicOption
 * 
 * @author wang.zx
 * @date 2015-7-8
 */
@SuppressWarnings("serial")
public class TikuTopicOption extends BaseEntity {
	
	
	private Integer	topicId;		 /* 试题id */ 
	private String	optionNo;		 /* 选项编号：选型A、选型B */ 
	private String	optionName;		 /* 选项名称，存选择题的选项 */ 
	private Integer	correctFlag;		 /* 是否正确答案（1：是；0：否） */ 

	// Constructor
	public TikuTopicOption() {
	}
	
	/**
	 * full Constructor
	 */
	public TikuTopicOption(Integer id, Integer topicId, String optionNo, String optionName, Integer correctFlag) {
		setId(id);
		this.topicId = topicId;
		this.optionNo = optionNo;
		this.optionName = optionName;
		this.correctFlag = correctFlag;
	}

	// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为TikuTopicOption可以实现连缀设置属性
	
	public Integer getTopicId() {
		return topicId;
	}

	public TikuTopicOption setTopicId(Integer topicId) {
		this.topicId = topicId;
		return this;
	}
	
	
	public String getOptionNo() {
		return optionNo;
	}

	public TikuTopicOption setOptionNo(String optionNo) {
		this.optionNo = optionNo;
		return this;
	}
	
	
	public String getOptionName() {
		return optionName;
	}

	public TikuTopicOption setOptionName(String optionName) {
		this.optionName = optionName;
		return this;
	}
	
	
	public Integer getCorrectFlag() {
		return correctFlag;
	}

	public TikuTopicOption setCorrectFlag(Integer correctFlag) {
		this.correctFlag = correctFlag;
		return this;
	}
	
	@Override
	public String toString() {
		return "TikuTopicOption [" + "id=" + getId() + ", topicId=" + topicId + ", optionNo=" + optionNo + ", optionName=" + optionName + ", correctFlag=" + correctFlag +  "]";
	}
}
