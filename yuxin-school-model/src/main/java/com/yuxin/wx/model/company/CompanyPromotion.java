package com.yuxin.wx.model.company;

import java.util.Date;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import com.yuxin.wx.util.ShortDateSerializer;

import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:CompanyPromotion
 * 
 * @author wang.zx
 * @date 2016-1-13
 */
@SuppressWarnings("serial")
public class CompanyPromotion extends BaseEntity {
	
	
	private Integer	companyId;		
	private String	promotionType;		 /* 字典表：首页、公开课 */ 
	private String	promotionSource;		 /* 字典表：百度、360、网盟 */ 
	private String	promotionSourceUrl;		
	private String	promotionTerm;		
	private String	promotionUrl;		
	private Date	addDate;		
	private Date	addTime;		
	private String	userIp;		
	private String	userRegion;		

	// Constructor
	public CompanyPromotion() {
	}
	
	/**
	 * full Constructor
	 */
	public CompanyPromotion(Integer id, Integer companyId, String promotionType, String promotionSource, String promotionSourceUrl, String promotionTerm, String promotionUrl, Date addDate, Date addTime, String userIp, String userRegion) {
		setId(id);
		this.companyId = companyId;
		this.promotionType = promotionType;
		this.promotionSource = promotionSource;
		this.promotionSourceUrl = promotionSourceUrl;
		this.promotionTerm = promotionTerm;
		this.promotionUrl = promotionUrl;
		this.addDate = addDate;
		this.addTime = addTime;
		this.userIp = userIp;
		this.userRegion = userRegion;
	}

	// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为CompanyPromotion可以实现连缀设置属性
	
	public Integer getCompanyId() {
		return companyId;
	}

	public CompanyPromotion setCompanyId(Integer companyId) {
		this.companyId = companyId;
		return this;
	}
	
	
	public String getPromotionType() {
		return promotionType;
	}

	public CompanyPromotion setPromotionType(String promotionType) {
		this.promotionType = promotionType;
		return this;
	}
	
	
	public String getPromotionSource() {
		return promotionSource;
	}

	public CompanyPromotion setPromotionSource(String promotionSource) {
		this.promotionSource = promotionSource;
		return this;
	}
	
	
	public String getPromotionSourceUrl() {
		return promotionSourceUrl;
	}

	public CompanyPromotion setPromotionSourceUrl(String promotionSourceUrl) {
		this.promotionSourceUrl = promotionSourceUrl;
		return this;
	}
	
	
	public String getPromotionTerm() {
		return promotionTerm;
	}

	public CompanyPromotion setPromotionTerm(String promotionTerm) {
		this.promotionTerm = promotionTerm;
		return this;
	}
	
	
	public String getPromotionUrl() {
		return promotionUrl;
	}

	public CompanyPromotion setPromotionUrl(String promotionUrl) {
		this.promotionUrl = promotionUrl;
		return this;
	}
	
	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getAddDate() {
		return addDate;
	}

	public CompanyPromotion setAddDate(Date addDate) {
		this.addDate = addDate;
		return this;
	}
	
	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getAddTime() {
		return addTime;
	}

	public CompanyPromotion setAddTime(Date addTime) {
		this.addTime = addTime;
		return this;
	}
	
	
	public String getUserIp() {
		return userIp;
	}

	public CompanyPromotion setUserIp(String userIp) {
		this.userIp = userIp;
		return this;
	}
	
	
	public String getUserRegion() {
		return userRegion;
	}

	public CompanyPromotion setUserRegion(String userRegion) {
		this.userRegion = userRegion;
		return this;
	}
	
	@Override
	public String toString() {
		return "CompanyPromotion [" + "id=" + getId() + ", companyId=" + companyId + ", promotionType=" + promotionType + ", promotionSource=" + promotionSource + ", promotionSourceUrl=" + promotionSourceUrl + ", promotionTerm=" + promotionTerm + ", promotionUrl=" + promotionUrl + ", addDate=" + addDate + ", addTime=" + addTime + ", userIp=" + userIp + ", userRegion=" + userRegion +  "]";
	}
}
