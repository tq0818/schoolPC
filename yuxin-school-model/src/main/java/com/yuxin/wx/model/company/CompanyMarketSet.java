package com.yuxin.wx.model.company;

import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:CompanyMarketSet
 *
 * @author xiongbao
 * @date 2016-8-15
 */
@SuppressWarnings("serial")
public class CompanyMarketSet extends BaseEntity {

	private Integer companyId;
	private String qqFlag; /* 营销qq使用标记，1：使用；0：禁用 */
	private String qqType; /* qq类型：个人qq，企业qq，营销qq */
	private String qqNum; /* qq号码 */
	private String qqKey; /* 营销qqkey值 */
	private Integer weixinFlag; /* 微信标记，1：使用；0：禁用 */
	private String weixinNo; /* 微信公众号 */
	private String weixinPic; /* 微信二维码地址 */
	private Integer weiboFlag; /* 新浪微博标记，1：使用；0：禁用 */
	private String weiboNo; /* 微博号码 */
	private String weiboUrl; /* 微博地址 */
	private String weiboPic; /* 微博二维码地址 */
	private Integer leyuFlag; /* 乐语使用标记，1：使用；0：禁用 */
	private String leyuJsUrl; /* 乐语js文件地址 */
	private String leyuNo; /* 乐语客服组号码 */
	private Integer serviceFlag; /* 客服使用标记，1：使用；0：禁用 */
	private String servicePhone; /* 客服电话 */
	private String serviceTime;
	private Integer bdsqFlag; /* 百度商桥 */
	private String bdsqJsUrl; /* 百度商桥js地址 */
	private Integer bdsqType; /* 百度商桥类型 */

	// Constructor
	public CompanyMarketSet() {
	}

	/**
	 * full Constructor
	 */
	public CompanyMarketSet(Integer id, Integer companyId, String qqFlag, String qqType, String qqNum, String qqKey, Integer weixinFlag, String weixinNo,
	        String weixinPic, Integer weiboFlag, String weiboNo, String weiboUrl, String weiboPic, Integer leyuFlag, String leyuJsUrl, String leyuNo,
	        Integer serviceFlag, String servicePhone, String serviceTime, Integer bdsqFlag, String bdsqJsUrl, Integer bdsqType) {
		setId(id);
		this.companyId = companyId;
		this.qqFlag = qqFlag;
		this.qqType = qqType;
		this.qqNum = qqNum;
		this.qqKey = qqKey;
		this.weixinFlag = weixinFlag;
		this.weixinNo = weixinNo;
		this.weixinPic = weixinPic;
		this.weiboFlag = weiboFlag;
		this.weiboNo = weiboNo;
		this.weiboUrl = weiboUrl;
		this.weiboPic = weiboPic;
		this.leyuFlag = leyuFlag;
		this.leyuJsUrl = leyuJsUrl;
		this.leyuNo = leyuNo;
		this.serviceFlag = serviceFlag;
		this.servicePhone = servicePhone;
		this.serviceTime = serviceTime;
		this.bdsqFlag = bdsqFlag;
		this.bdsqJsUrl = bdsqJsUrl;
		this.bdsqType = bdsqType;
	}

	// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为CompanyMarketSet可以实现连缀设置属性

	public Integer getCompanyId() {
		return companyId;
	}

	public CompanyMarketSet setCompanyId(Integer companyId) {
		this.companyId = companyId;
		return this;
	}

	public String getQqFlag() {
		return qqFlag;
	}

	public CompanyMarketSet setQqFlag(String qqFlag) {
		this.qqFlag = qqFlag;
		return this;
	}

	public String getQqType() {
		return qqType;
	}

	public CompanyMarketSet setQqType(String qqType) {
		this.qqType = qqType;
		return this;
	}

	public String getQqNum() {
		return qqNum;
	}

	public CompanyMarketSet setQqNum(String qqNum) {
		this.qqNum = qqNum;
		return this;
	}

	public String getQqKey() {
		return qqKey;
	}

	public CompanyMarketSet setQqKey(String qqKey) {
		this.qqKey = qqKey;
		return this;
	}

	public Integer getWeixinFlag() {
		return weixinFlag;
	}

	public CompanyMarketSet setWeixinFlag(Integer weixinFlag) {
		this.weixinFlag = weixinFlag;
		return this;
	}

	public String getWeixinNo() {
		return weixinNo;
	}

	public CompanyMarketSet setWeixinNo(String weixinNo) {
		this.weixinNo = weixinNo;
		return this;
	}

	public String getWeixinPic() {
		return weixinPic;
	}

	public CompanyMarketSet setWeixinPic(String weixinPic) {
		this.weixinPic = weixinPic;
		return this;
	}

	public Integer getWeiboFlag() {
		return weiboFlag;
	}

	public CompanyMarketSet setWeiboFlag(Integer weiboFlag) {
		this.weiboFlag = weiboFlag;
		return this;
	}

	public String getWeiboNo() {
		return weiboNo;
	}

	public CompanyMarketSet setWeiboNo(String weiboNo) {
		this.weiboNo = weiboNo;
		return this;
	}

	public String getWeiboUrl() {
		return weiboUrl;
	}

	public CompanyMarketSet setWeiboUrl(String weiboUrl) {
		this.weiboUrl = weiboUrl;
		return this;
	}

	public String getWeiboPic() {
		return weiboPic;
	}

	public CompanyMarketSet setWeiboPic(String weiboPic) {
		this.weiboPic = weiboPic;
		return this;
	}

	public Integer getLeyuFlag() {
		return leyuFlag;
	}

	public CompanyMarketSet setLeyuFlag(Integer leyuFlag) {
		this.leyuFlag = leyuFlag;
		return this;
	}

	public String getLeyuJsUrl() {
		return leyuJsUrl;
	}

	public CompanyMarketSet setLeyuJsUrl(String leyuJsUrl) {
		this.leyuJsUrl = leyuJsUrl;
		return this;
	}

	public String getLeyuNo() {
		return leyuNo;
	}

	public CompanyMarketSet setLeyuNo(String leyuNo) {
		this.leyuNo = leyuNo;
		return this;
	}

	public Integer getServiceFlag() {
		return serviceFlag;
	}

	public CompanyMarketSet setServiceFlag(Integer serviceFlag) {
		this.serviceFlag = serviceFlag;
		return this;
	}

	public String getServicePhone() {
		return servicePhone;
	}

	public CompanyMarketSet setServicePhone(String servicePhone) {
		this.servicePhone = servicePhone;
		return this;
	}

	public String getServiceTime() {
		return serviceTime;
	}

	public CompanyMarketSet setServiceTime(String serviceTime) {
		this.serviceTime = serviceTime;
		return this;
	}

	public Integer getBdsqFlag() {
		return bdsqFlag;
	}

	public CompanyMarketSet setBdsqFlag(Integer bdsqFlag) {
		this.bdsqFlag = bdsqFlag;
		return this;
	}

	public String getBdsqJsUrl() {
		return bdsqJsUrl;
	}

	public CompanyMarketSet setBdsqJsUrl(String bdsqJsUrl) {
		this.bdsqJsUrl = bdsqJsUrl;
		return this;
	}

	public Integer getBdsqType() {
		return bdsqType;
	}

	public CompanyMarketSet setBdsqType(Integer bdsqType) {
		this.bdsqType = bdsqType;
		return this;
	}

	@Override
	public String toString() {
		return "CompanyMarketSet [" + "id=" + getId() + ", companyId=" + companyId + ", qqFlag=" + qqFlag + ", qqType=" + qqType + ", qqNum=" + qqNum
		        + ", qqKey=" + qqKey + ", weixinFlag=" + weixinFlag + ", weixinNo=" + weixinNo + ", weixinPic=" + weixinPic + ", weiboFlag=" + weiboFlag
		        + ", weiboNo=" + weiboNo + ", weiboUrl=" + weiboUrl + ", weiboPic=" + weiboPic + ", leyuFlag=" + leyuFlag + ", leyuJsUrl=" + leyuJsUrl
		        + ", leyuNo=" + leyuNo + ", serviceFlag=" + serviceFlag + ", servicePhone=" + servicePhone + ", serviceTime=" + serviceTime + ", bdsqFlag="
		        + bdsqFlag + ", bdsqJsUrl=" + bdsqJsUrl + ", bdsqType=" + bdsqType + "]";
	}
}
