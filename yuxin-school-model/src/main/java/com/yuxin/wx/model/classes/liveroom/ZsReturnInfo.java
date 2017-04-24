package com.yuxin.wx.model.classes.liveroom;

public class ZsReturnInfo {
	
	private String id; 		//实时课堂ID
	private String number ; // 课堂编号
	private String assistantToken ; // 助教口令
	private String studentToken ; 	// Web端学员口令
	private String teacherToken ; 	// 老师口令
	private String studentClientToken ; // 学生客户端口令
	private long startDate; 	//  预期开始时间
	private String webJoin ; 	// 是否允许web端学生加入
	private String clientJoin ; // 是否允许客户端学生加入
	private String invalidDate; // 失效日期（详情见3.3）
	private String teacherJoinUrl ; // 老师和助教加入URL
	private String studentJoinUrl ; // 学员加入URL
	private Integer scene; 		// 0:大讲堂，1：小班课，2：私教课  
	private String code ; 		// 返回结果代码（详情见3.5） 
	private String message ; 	// 结果说明 
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getAssistantToken() {
		return assistantToken;
	}
	public void setAssistantToken(String assistantToken) {
		this.assistantToken = assistantToken;
	}
	public String getStudentToken() {
		return studentToken;
	}
	public void setStudentToken(String studentToken) {
		this.studentToken = studentToken;
	}
	public String getTeacherToken() {
		return teacherToken;
	}
	public void setTeacherToken(String teacherToken) {
		this.teacherToken = teacherToken;
	}
	public String getStudentClientToken() {
		return studentClientToken;
	}
	public void setStudentClientToken(String studentClientToken) {
		this.studentClientToken = studentClientToken;
	}
	public long getStartDate() {
		return startDate;
	}
	public void setStartDate(long startDate) {
		this.startDate = startDate;
	}
	public String getWebJoin() {
		return webJoin;
	}
	public void setWebJoin(String webJoin) {
		this.webJoin = webJoin;
	}
	public String getClientJoin() {
		return clientJoin;
	}
	public void setClientJoin(String clientJoin) {
		this.clientJoin = clientJoin;
	}
	public String getInvalidDate() {
		return invalidDate;
	}
	public void setInvalidDate(String invalidDate) {
		this.invalidDate = invalidDate;
	}
	public String getTeacherJoinUrl() {
		return teacherJoinUrl;
	}
	public void setTeacherJoinUrl(String teacherJoinUrl) {
		this.teacherJoinUrl = teacherJoinUrl;
	}
	public String getStudentJoinUrl() {
		return studentJoinUrl;
	}
	public void setStudentJoinUrl(String studentJoinUrl) {
		this.studentJoinUrl = studentJoinUrl;
	}
	public Integer getScene() {
		return scene;
	}
	public void setScene(Integer scene) {
		this.scene = scene;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}
