package com.yuxin.wx.vo.tiku;


import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:TikuChapter
 * 
 * @author wang.zx
 * @date 2015-7-8
 */
@SuppressWarnings("serial")
public class TikuChapterVo extends BaseEntity {
	
	
	private String	chapterName;		 /* 章的名称 */ 
	private Integer topicCount;          /* 章对应的题的数量 */ 
	private Integer doneTopicCount;      /* 章对应用户做过题的数量 */ 
	private String isFinish;			 /* 最后一次做该章的题的试卷是否做完 */ 
	private Integer userExerciseId;      /* 用户生成题的试卷ID */ 

	// Constructor
	public TikuChapterVo() {
	}
	
	/**
	 * full Constructor
	 */
	public TikuChapterVo(Integer id, String chapterName, Integer topicCount, Integer doneTopicCount, String isFinish, Integer userExerciseId) {
		setId(id);
		this.chapterName = chapterName;
		this.topicCount = topicCount;
		this.doneTopicCount = doneTopicCount;
		this.isFinish = isFinish;
		this.userExerciseId = userExerciseId;
	}

	// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为TikuChapter可以实现连缀设置属性
	
	public String getChapterName() {
		return chapterName;
	}

	public TikuChapterVo setChapterName(String chapterName) {
		this.chapterName = chapterName;
		return this;
	}
	
	public Integer getTopicCount() {
		return topicCount;
	}

	public void setTopicCount(Integer topicCount) {
		this.topicCount = topicCount;
	}

	public Integer getDoneTopicCount() {
		return doneTopicCount;
	}

	public void setDoneTopicCount(Integer doneTopicCount) {
		this.doneTopicCount = doneTopicCount;
	}

	public String getIsFinish() {
		return isFinish;
	}

	public void setIsFinish(String isFinish) {
		this.isFinish = isFinish;
	}

	public Integer getUserExerciseId() {
		return userExerciseId;
	}

	public void setUserExerciseId(Integer userExerciseId) {
		this.userExerciseId = userExerciseId;
	}

	@Override
	public String toString() {
		return "TikuChapter [" + "id=" + getId() + ", chapterName=" + chapterName + ", topicCount=" + topicCount + ", doneTopicCount=" + doneTopicCount + ", isFinish=" + isFinish + ", userExerciseId=" + userExerciseId + "]";
	}
}
