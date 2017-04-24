package com.yuxin.wx.model.company;


import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:CompanyVideoConfig
 * 
 * @author wang.zx
 * @date 2017-3-22
 */
@SuppressWarnings("serial")
public class CompanyVideoConfig extends BaseEntity {
	
	
	private String	userid;		
	private String	writeToken;		
	private String	secretKey;		
	private String	readToken;		
	private String	accessKey;		
	private String	buketName;		
	private Integer	companyId;		
	private String	videoType;		

	// Constructor
	public CompanyVideoConfig() {
	}
	
	/**
	 * full Constructor
	 */
	public CompanyVideoConfig(Integer id, String userid, String writeToken, String secretKey, String readToken, String accessKey, String buketName, Integer companyId, String videoType) {
		setId(id);
		this.userid = userid;
		this.writeToken = writeToken;
		this.secretKey = secretKey;
		this.readToken = readToken;
		this.accessKey = accessKey;
		this.buketName = buketName;
		this.companyId = companyId;
		this.videoType = videoType;
	}

	// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为CompanyVideoConfig可以实现连缀设置属性
	
	public String getUserid() {
		return userid;
	}

	public CompanyVideoConfig setUserid(String userid) {
		this.userid = userid;
		return this;
	}
	
	
	public String getWriteToken() {
		return writeToken;
	}

	public CompanyVideoConfig setWriteToken(String writeToken) {
		this.writeToken = writeToken;
		return this;
	}
	
	
	public String getSecretKey() {
		return secretKey;
	}

	public CompanyVideoConfig setSecretKey(String secretKey) {
		this.secretKey = secretKey;
		return this;
	}
	
	
	public String getReadToken() {
		return readToken;
	}

	public CompanyVideoConfig setReadToken(String readToken) {
		this.readToken = readToken;
		return this;
	}
	
	
	public String getAccessKey() {
		return accessKey;
	}

	public CompanyVideoConfig setAccessKey(String accessKey) {
		this.accessKey = accessKey;
		return this;
	}
	
	
	public String getBuketName() {
		return buketName;
	}

	public CompanyVideoConfig setBuketName(String buketName) {
		this.buketName = buketName;
		return this;
	}
	
	
	public Integer getCompanyId() {
		return companyId;
	}

	public CompanyVideoConfig setCompanyId(Integer companyId) {
		this.companyId = companyId;
		return this;
	}
	
	
	public String getVideoType() {
		return videoType;
	}

	public CompanyVideoConfig setVideoType(String videoType) {
		this.videoType = videoType;
		return this;
	}
	
	@Override
	public String toString() {
		return "CompanyVideoConfig [" + "id=" + getId() + ", userid=" + userid + ", writeToken=" + writeToken + ", secretKey=" + secretKey + ", readToken=" + readToken + ", accessKey=" + accessKey + ", buketName=" + buketName + ", companyId=" + companyId + ", videoType=" + videoType +  "]";
	}
}
