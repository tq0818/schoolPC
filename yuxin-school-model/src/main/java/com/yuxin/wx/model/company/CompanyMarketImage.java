package com.yuxin.wx.model.company;


import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:CompanyMarketImage
 * 
 * @author wang.zx
 * @date 2015-12-21
 */
@SuppressWarnings("serial")
public class CompanyMarketImage extends BaseEntity {
	
	
	private String	marketingImage;		 /* 推广公开课页面广告图片 */ 
	private String	marketingUrl;		/*链接地址*/
	private Integer	companyId;		 /* 图片所属的公司 */ 

	// Constructor
	public CompanyMarketImage() {
	}
	
	/**
	 * full Constructor
	 */
	public CompanyMarketImage(Integer id, String marketingImage, Integer companyId, String marketingUrl) {
		setId(id);
		this.marketingImage = marketingImage;
		this.companyId = companyId;
		this.marketingUrl = marketingUrl;
	}

	// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为CompanyMarketImage可以实现连缀设置属性
	
	public String getMarketingImage() {
		return marketingImage;
	}

	public CompanyMarketImage setMarketingImage(String marketingImage) {
		this.marketingImage = marketingImage;
		return this;
	}
	
	
	public Integer getCompanyId() {
		return companyId;
	}

	public CompanyMarketImage setCompanyId(Integer companyId) {
		this.companyId = companyId;
		return this;
	}
	
	@Override
	public String toString() {
		return "CompanyMarketImage [" + "id=" + getId() + ", marketingImage=" + marketingImage + ", companyId=" + companyId +  "]";
	}

	public String getMarketingUrl() {
		return marketingUrl;
	}

	public CompanyMarketImage setMarketingUrl(String marketingUrl) {
		this.marketingUrl = marketingUrl;
		return this;
	}
}
