package com.yuxin.wx.model.user;

import java.util.Date;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import com.yuxin.wx.util.ShortDateSerializer;

import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:UserCollect
 * 
 * @author chopin
 * @date 2015-3-12
 */
@SuppressWarnings("serial")
public class UserCollect extends BaseEntity {
	
	
	private Integer	userId;		
	private Date	collectDate;		 /* 收藏日期 */ 
	private Integer	commodityId;		 /* 商品id */ 
	private String commodityType;
	private String companyId;
	

	// Constructor
	public UserCollect() {
	}
	
	

	public UserCollect(Integer userId, Date collectDate, Integer commodityId, String commodityType) {
		super();
		this.userId = userId;
		this.collectDate = collectDate;
		this.commodityId = commodityId;
		this.commodityType = commodityType;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为UserCollect可以实现连缀设置属性
	
	public String getCommodityType() {
		return commodityType;
	}



	public void setCommodityType(String commodityType) {
		this.commodityType = commodityType;
	}



	public Integer getUserId() {
		return userId;
	}

	public UserCollect setUserId(Integer userId) {
		this.userId = userId;
		return this;
	}
	
	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getCollectDate() {
		return collectDate;
	}

	public UserCollect setCollectDate(Date collectDate) {
		this.collectDate = collectDate;
		return this;
	}
	
	
	public Integer getCommodityId() {
		return commodityId;
	}

	public UserCollect setCommodityId(Integer commodityId) {
		this.commodityId = commodityId;
		return this;
	}
	
	@Override
	public String toString() {
		return "UserCollect [" + "id=" + getId() + ", userId=" + userId + ", collectDate=" + collectDate + ", commodityId=" + commodityId +  "]";
	}
}
