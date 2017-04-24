package com.yuxin.wx.model.tiku;


import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:TikuCategory
 * 
 * @author wang.zx
 * @date 2015-7-8
 */
@SuppressWarnings("serial")
public class TikuCategory extends BaseEntity {
	
	
	private String	tikuName;		 /* 题库分类名称 */ 
	private String	tikuDesc;		 /* 题库分类描述 */ 
	private Integer	itemOneId;		 /* 一级项目id */ 
	private Integer	itemSecondId;		 /* 二级项目id */ 
	private Integer	companyId;		
	private String iconUrl;			/*题库图标地址*/
	private Integer delFlag;		/*禁用*/
	private String iconBackUrl;/*题库图标地址*/
	

	// Constructor
	public TikuCategory() {
	}
	
	/**
	 * full Constructor
	 */
	public TikuCategory(Integer id, String tikuName, String tikuDesc, Integer itemOneId, Integer itemSecondId, Integer companyId) {
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

	public TikuCategory setTikuName(String tikuName) {
		this.tikuName = tikuName;
		return this;
	}
	
	
	public String getTikuDesc() {
		return tikuDesc;
	}

	public TikuCategory setTikuDesc(String tikuDesc) {
		this.tikuDesc = tikuDesc;
		return this;
	}
	
	
	public Integer getItemOneId() {
		return itemOneId;
	}

	public TikuCategory setItemOneId(Integer itemOneId) {
		this.itemOneId = itemOneId;
		return this;
	}
	
	
	public Integer getItemSecondId() {
		return itemSecondId;
	}

	public TikuCategory setItemSecondId(Integer itemSecondId) {
		this.itemSecondId = itemSecondId;
		return this;
	}
	
	
	public Integer getCompanyId() {
		return companyId;
	}

	public TikuCategory setCompanyId(Integer companyId) {
		this.companyId = companyId;
		return this;
	}
	
	@Override
	public String toString() {
		return "TikuCategory [" + "id=" + getId() + ", tikuName=" + tikuName + ", tikuDesc=" + tikuDesc + ", itemOneId=" + itemOneId + ", itemSecondId=" + itemSecondId + ", companyId=" + companyId +  "]";
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

	public String getIconBackUrl() {
		return iconBackUrl;
	}

	public void setIconBackUrl(String iconBackUrl) {
		this.iconBackUrl = iconBackUrl;
	}
}
