package com.yuxin.wx.model.commodity;


import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:CommodityProductRealtion
 * 
 * @author wang.zx
 * @date 2015-3-14
 */
@SuppressWarnings("serial")
public class CommodityProductRealtion extends BaseEntity {
	
	
	private Integer	comId;		 /* 商品id */ 
	private Integer	productId;		 /* 产品id */ 
	private String	productType;		 /* 商品类型：1：班型；2 */ 

	// Constructor
	public CommodityProductRealtion() {
	}
	
	/**
	 * full Constructor
	 */
	public CommodityProductRealtion(Integer id, Integer comId, Integer productId, String productType) {
		setId(id);
		this.comId = comId;
		this.productId = productId;
		this.productType = productType;
	}

	// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为CommodityProductRealtion可以实现连缀设置属性
	
	public Integer getComId() {
		return comId;
	}

	public CommodityProductRealtion setComId(Integer comId) {
		this.comId = comId;
		return this;
	}
	
	
	public Integer getProductId() {
		return productId;
	}

	public CommodityProductRealtion setProductId(Integer productId) {
		this.productId = productId;
		return this;
	}
	
	
	public String getProductType() {
		return productType;
	}

	public CommodityProductRealtion setProductType(String productType) {
		this.productType = productType;
		return this;
	}
	
	@Override
	public String toString() {
		return "CommodityProductRealtion [" + "id=" + getId() + ", comId=" + comId + ", productId=" + productId + ", productType=" + productType +  "]";
	}
}
