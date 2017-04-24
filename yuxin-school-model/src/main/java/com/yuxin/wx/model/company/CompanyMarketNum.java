package com.yuxin.wx.model.company;


import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:CompanyMarketNum
 * 
 * @author wang.zx
 * @date 2015-12-21
 */
@SuppressWarnings("serial")
public class CompanyMarketNum extends BaseEntity {
	
	
	private String	marketingNum;		 /* 营销号码 */ 
	private String	marketingType;		 /* 营销类型（见字典表） */ 
	private Integer	companyId;		 /* 所属公司 */ 
	private String	nickName;		/*昵称*/
	private Integer itemId;			/*学科id*/
	

	// Constructor
	public CompanyMarketNum() {
	}
	
	/**
	 * full Constructor
	 */
	public CompanyMarketNum(Integer id, String marketingNum, String marketingType, Integer companyId,String nickName) {
		setId(id);
		this.marketingNum = marketingNum;
		this.marketingType = marketingType;
		this.companyId = companyId;
		this.nickName = nickName;
	}

	// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为CompanyMarketNum可以实现连缀设置属性
	
	public String getMarketingNum() {
		return marketingNum;
	}

	public CompanyMarketNum setMarketingNum(String marketingNum) {
		this.marketingNum = marketingNum;
		return this;
	}
	
	
	public String getMarketingType() {
		return marketingType;
	}

	public CompanyMarketNum setMarketingType(String marketingType) {
		this.marketingType = marketingType;
		return this;
	}
	
	
	public Integer getCompanyId() {
		return companyId;
	}

	public CompanyMarketNum setCompanyId(Integer companyId) {
		this.companyId = companyId;
		return this;
	}
	
	@Override
	public String toString() {
		return "CompanyMarketNum [" + "id=" + getId() + ", marketingNum=" + marketingNum + ", marketingType=" + marketingType + ", companyId=" + companyId +  "]";
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}
}
