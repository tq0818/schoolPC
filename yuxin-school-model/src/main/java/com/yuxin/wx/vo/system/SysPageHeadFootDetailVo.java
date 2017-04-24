package com.yuxin.wx.vo.system;

import java.util.Date;

import com.yuxin.wx.common.BaseEntity;

/**
 * @ClassName: ConfigPageAndHeadVo
 * @Description:首页的页头和页尾
 * @author zhang.zx
 * @date 2015-4-11
 * @version 1.0
 */
@SuppressWarnings("serial")
public class SysPageHeadFootDetailVo extends BaseEntity{

	private Integer companyId;
	private String companyName;
	private String companyLogo; /* 公司的logo */
	private String companyIco; /* 公司的ico图标 */
	private String	weixinPic;		 /* 微信二维码地址 */ 
	private String	weiboUrl;		 /* 微博地址 */ 
	private String	weiboPic;		 /* 微博二维码地址 */ 
	private String	servicePhone;		 /* 客服电话 */
	private String	qqUrl;
	private String	weixinNo;		 /* 微信公众号 */ 
	private String	weiboNo;		 /* 微博号码 */ 
	public SysPageHeadFootDetailVo() {
		
	}
	public SysPageHeadFootDetailVo(Integer companyId, String companyName,
			String companyLogo, String companyIco, String weixinPic,
			String weiboUrl, String servicePhone, String qqUrl) {
		this.companyId = companyId;
		this.companyName = companyName;
		this.companyLogo = companyLogo;
		this.companyIco = companyIco;
		this.weixinPic = weixinPic;
		this.weiboUrl = weiboUrl;
		this.servicePhone = servicePhone;
		this.qqUrl = qqUrl;
	}
	public Integer getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getCompanyLogo() {
		return companyLogo;
	}
	public void setCompanyLogo(String companyLogo) {
		this.companyLogo = companyLogo;
	}
	public String getCompanyIco() {
		return companyIco;
	}
	public void setCompanyIco(String companyIco) {
		this.companyIco = companyIco;
	}
	public String getWeixinPic() {
		return weixinPic;
	}
	public void setWeixinPic(String weixinPic) {
		this.weixinPic = weixinPic;
	}
	public String getWeiboUrl() {
		return weiboUrl;
	}
	public void setWeiboUrl(String weiboUrl) {
		this.weiboUrl = weiboUrl;
	}
	public String getServicePhone() {
		return servicePhone;
	}
	public void setServicePhone(String servicePhone) {
		this.servicePhone = servicePhone;
	}
	public String getQqUrl() {
		return qqUrl;
	}
	public void setQqUrl(String qqUrl) {
		this.qqUrl = qqUrl;
	}
	public String getWeixinNo() {
		return weixinNo;
	}
	public void setWeixinNo(String weixinNo) {
		this.weixinNo = weixinNo;
	}
	public String getWeiboNo() {
		return weiboNo;
	}
	public void setWeiboNo(String weiboNo) {
		this.weiboNo = weiboNo;
	}
	public String getWeiboPic() {
		return weiboPic;
	}
	public void setWeiboPic(String weiboPic) {
		this.weiboPic = weiboPic;
	}
	
}
