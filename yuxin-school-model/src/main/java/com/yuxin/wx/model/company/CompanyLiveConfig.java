package com.yuxin.wx.model.company;


import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:CompanyLiveConfig
 * 
 * @author wang.zx
 * @date 2016-2-29
 */
@SuppressWarnings("serial")
public class CompanyLiveConfig extends BaseEntity {
	
	
	private String	domain;		
	private String	teacherToken;		 /* 进入展示互动老师密钥 */ 
	private String	assistantToken;		 /* 进入展示互动助教密钥 */ 
	private String	studentWebToken;		 /* 进入展示互动学生密钥（web端） */ 
	private String	studentClientToken;		 /* 进入展示互动学生密钥（客户端） */ 
	private String	loginName;		 /* 展示互动登陆账号 */ 
	private String	password;		 /* 展示互动密码 */ 
	private Integer	liveType;		 /* 1：展示互动  2：E课堂 3：欢拓 4：CC */
	private Integer companyId;		

	// Constructor
	public CompanyLiveConfig() {
	}
	
	/**
	 * full Constructor
	 */
	public CompanyLiveConfig(Integer id, String domain, String teacherToken, String assistantToken, String studentWebToken, String studentClientToken, String loginName, String password, Integer liveType) {
		setId(id);
		this.domain = domain;
		this.teacherToken = teacherToken;
		this.assistantToken = assistantToken;
		this.studentWebToken = studentWebToken;
		this.studentClientToken = studentClientToken;
		this.loginName = loginName;
		this.password = password;
		this.liveType = liveType;
	}

	// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为CompanyLiveConfig可以实现连缀设置属性
	
	public String getDomain() {
		return domain;
	}

	public CompanyLiveConfig setDomain(String domain) {
		this.domain = domain;
		return this;
	}
	
	
	public String getTeacherToken() {
		return teacherToken;
	}

	public CompanyLiveConfig setTeacherToken(String teacherToken) {
		this.teacherToken = teacherToken;
		return this;
	}
	
	
	public String getAssistantToken() {
		return assistantToken;
	}

	public CompanyLiveConfig setAssistantToken(String assistantToken) {
		this.assistantToken = assistantToken;
		return this;
	}
	
	
	public String getStudentWebToken() {
		return studentWebToken;
	}

	public CompanyLiveConfig setStudentWebToken(String studentWebToken) {
		this.studentWebToken = studentWebToken;
		return this;
	}
	
	
	public String getStudentClientToken() {
		return studentClientToken;
	}

	public CompanyLiveConfig setStudentClientToken(String studentClientToken) {
		this.studentClientToken = studentClientToken;
		return this;
	}
	
	
	public String getLoginName() {
		return loginName;
	}

	public CompanyLiveConfig setLoginName(String loginName) {
		this.loginName = loginName;
		return this;
	}
	
	
	public String getPassword() {
		return password;
	}

	public CompanyLiveConfig setPassword(String password) {
		this.password = password;
		return this;
	}
	
	
	public Integer getLiveType() {
		return liveType;
	}

	public CompanyLiveConfig setLiveType(Integer liveType) {
		this.liveType = liveType;
		return this;
	}
	
	@Override
	public String toString() {
		return "CompanyLiveConfig [" + "id=" + getId() + ", domain=" + domain + ", teacherToken=" + teacherToken + ", assistantToken=" + assistantToken + ", studentWebToken=" + studentWebToken + ", studentClientToken=" + studentClientToken + ", loginName=" + loginName + ", password=" + password + ", liveType=" + liveType +  "]";
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}
}
