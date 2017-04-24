package com.yuxin.wx.vo.tiku;


import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:TikuCategory
 * 
 * @author wang.zx
 * @date 2015-7-8
 */
@SuppressWarnings("serial")
public class TikuCategoryVo extends BaseEntity {
	
	
	private String	tikuName;		 /* 题库分类名称 */ 
	private String	tikuDesc;		 /* 题库分类描述 */ 
	private Integer	itemOneId;		 /* 一级项目id */ 
	private Integer	itemSecondId;		 /* 二级项目id */ 
	private Integer	companyId;		
	private String iconUrl;			/*题库图标*/

	private Integer subjectNo;		/*包含几个科目*/
	private Integer paperNo;		/*包含几套试卷*/
	private Integer topicNo;		/*包含多少试题*/
	private Integer delFlag;		/*禁用*/
	
	// Constructor
	public TikuCategoryVo() {
	}
	
	/**
	 * full Constructor
	 */
	public TikuCategoryVo(Integer id, String tikuName, String tikuDesc, Integer itemOneId, Integer itemSecondId, Integer companyId) {
		setId(id);
		this.tikuName = tikuName;
		this.tikuDesc = tikuDesc;
		this.itemOneId = itemOneId;
		this.itemSecondId = itemSecondId;
		this.companyId = companyId;
	}

	// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为TikuCategory可以实现连缀设置属性
	
	public String getTikuName() {
		return tikuName;
	}

	public TikuCategoryVo setTikuName(String tikuName) {
		this.tikuName = tikuName;
		return this;
	}
	
	
	public String getTikuDesc() {
		return tikuDesc;
	}

	public TikuCategoryVo setTikuDesc(String tikuDesc) {
		this.tikuDesc = tikuDesc;
		return this;
	}
	
	
	public Integer getItemOneId() {
		return itemOneId;
	}

	public TikuCategoryVo setItemOneId(Integer itemOneId) {
		this.itemOneId = itemOneId;
		return this;
	}
	
	
	public Integer getItemSecondId() {
		return itemSecondId;
	}

	public TikuCategoryVo setItemSecondId(Integer itemSecondId) {
		this.itemSecondId = itemSecondId;
		return this;
	}
	
	
	public Integer getCompanyId() {
		return companyId;
	}

	public TikuCategoryVo setCompanyId(Integer companyId) {
		this.companyId = companyId;
		return this;
	}
	
	@Override
	public String toString() {
		return "TikuCategory [" + "id=" + getId() + ", tikuName=" + tikuName + ", tikuDesc=" + tikuDesc + ", itemOneId=" + itemOneId + ", itemSecondId=" + itemSecondId + ", companyId=" + companyId +  "]";
	}

	public Integer getSubjectNo() {
		return subjectNo;
	}

	public void setSubjectNo(Integer subjectNo) {
		this.subjectNo = subjectNo;
	}

	public Integer getPaperNo() {
		return paperNo;
	}

	public void setPaperNo(Integer paperNo) {
		this.paperNo = paperNo;
	}

	public Integer getTopicNo() {
		return topicNo;
	}

	public void setTopicNo(Integer topicNo) {
		this.topicNo = topicNo;
	}

	public String getIconUrl() {
		return iconUrl;
	}

	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}

	public Integer getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}
}
