package com.yuxin.wx.vo.query;

import com.yuxin.wx.common.BaseEntity;

public class MarketingVo extends BaseEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public MarketingVo() {
		// TODO Auto-generated constructor stub
	}
	
	private String utmUrl;
	private Integer pv;
	private Integer uv;
	public String getUtmUrl() {
		return utmUrl;
	}
	public void setUtmUrl(String utmUrl) {
		this.utmUrl = utmUrl;
	}
	public Integer getPv() {
		return pv;
	}
	public void setPv(Integer pv) {
		this.pv = pv;
	}
	public Integer getUv() {
		return uv;
	}
	public void setUv(Integer uv) {
		this.uv = uv;
	}
	@Override
	public String toString() {
		return "MarketingVo [utmUrl=" + utmUrl + ", pv=" + pv + ", uv=" + uv + "]";
	}

}
