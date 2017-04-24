package com.yuxin.wx.vo.tiku;


import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:TikuSection
 * 
 * @author wang.zx
 * @date 2015-7-8
 */
@SuppressWarnings("serial")
public class TikuSectionVo extends BaseEntity {
	
	
	private String	sectionName;		 /* 节的名称 */ 
	private Integer topicCount;          /* 节对应的题的数量 */ 
	// Constructor
	public TikuSectionVo() {
	}
	
	/**
	 * full Constructor
	 */
	public TikuSectionVo(Integer id, String sectionName, Integer topicCount) {
		setId(id);
		this.sectionName = sectionName;
		this.topicCount = topicCount;
	}
	
	public String getSectionName() {
		return sectionName;
	}

	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}

	public Integer getTopicCount() {
		return topicCount;
	}

	public void setTopicCount(Integer topicCount) {
		this.topicCount = topicCount;
	}

	@Override
	public String toString() {
		return "TikuSection [" + "id=" + getId() + ", sectionName=" + sectionName + ", topicCount=" + topicCount +  "]";
	}
}
