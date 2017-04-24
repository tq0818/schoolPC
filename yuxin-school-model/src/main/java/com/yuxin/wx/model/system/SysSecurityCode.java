package com.yuxin.wx.model.system;


import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:SysSecurityCode
 * 
 * @author chopin
 * @date 2015-9-15
 */
@SuppressWarnings("serial")
public class SysSecurityCode extends BaseEntity {
	
	
	private String	code;		 /* 编码 */ 
	private String	bussiness;		 /* 业务:  10-短信发送 20-邮件发送 30-非法访问 40-使用超限  50-恶意访问  60-禁用账户  */ 
	private String	comments;		 /* 备注 */ 

	// Constructor
	public SysSecurityCode() {
	}
	
	/**
	 * full Constructor
	 */
	public SysSecurityCode(Integer id, String code, String bussiness, String comments) {
		setId(id);
		this.code = code;
		this.bussiness = bussiness;
		this.comments = comments;
	}

	// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为SysSecurityCode可以实现连缀设置属性
	
	public String getCode() {
		return code;
	}

	public SysSecurityCode setCode(String code) {
		this.code = code;
		return this;
	}
	
	
	public String getBussiness() {
		return bussiness;
	}

	public SysSecurityCode setBussiness(String bussiness) {
		this.bussiness = bussiness;
		return this;
	}
	
	
	public String getComments() {
		return comments;
	}

	public SysSecurityCode setComments(String comments) {
		this.comments = comments;
		return this;
	}
	
	@Override
	public String toString() {
		return "SysSecurityCode [" + "id=" + getId() + ", code=" + code + ", bussiness=" + bussiness + ", comments=" + comments +  "]";
	}
}
