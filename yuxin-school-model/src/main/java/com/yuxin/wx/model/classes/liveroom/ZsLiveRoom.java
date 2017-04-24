package com.yuxin.wx.model.classes.liveroom;

public class ZsLiveRoom {
	
	private Integer lessionId;			//当前课次的ID
	private String subject;				//实时课堂主题（长度：1-250） 
	private String teacherToken;		//老师加入口令（长度：6-15）（会自动生 成随机数） 
	private String studentToken;		//Web端学生加入口令（长度：最大 15） 
	private String studentClientToken;	//客户端学生加入口令 
	private String startDate; 			//开始日期
	private String invalidDate; 		//失效时间
	private String assistantToken;		//助教加入口令（长度：6-15）（会自动生 成随机数）
	private String speakerInfo;			//老师介绍 
	private String scheduleInfo;		//课程介绍 
	private boolean clientJoin;			//是否支持Web端学生加入,值为true或者 false。默认值为true, 当 scene的值为 2 时该属性值为false,该值得设置无效  
	private boolean webJoin;			//是否支持客户端端学生加入,值为 true或 者 false。默认值为true，当 scene的值 为 2时该属性值为 true，该值得设置无效 
	private String description;			//课堂介绍 
	private String duration;			//课堂持续时长（单位为分钟） 
	private Integer uiMode;				//Web 端学生界面设置(1 是三分屏，2是 文档/视频为主，3是两分屏，4：互动增 加) 
	private Integer uiColor; 			//三分屏颜色选择（blue, default, green）， 默认是default 
	private Integer scene;				//0:大讲堂，1：小班课，2：私教课，默认 值：0。当值scene为2，clientJoin,必须 为 true,同时 webJoin 为false 
	private Integer uiWindow;			//uiMode等于 2时候，设置是否显示小窗 口。 
	private Integer uiVideo;			//uiMode等于 2时候，设置是否视频为主
	private boolean upgrade ;			//是否允许web升级到客户端 
	private String sec;					//true:表示密码是经过加密的。 
	private boolean realtime;			//true:表示实时的，false：表示非实时的， 默认是false 
	private Integer maxAttendees;		//课堂最大并发数。 只有站点开启指定并发数功能，才能够设 置。否则即使传入数据也无效
	private String loginName;			//登录名 
	private String password;			//密码 
	
	public Integer getLessionId() {
		return lessionId;
	}
	public void setLessionId(Integer lessionId) {
		this.lessionId = lessionId;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getTeacherToken() {
		return teacherToken;
	}
	public void setTeacherToken(String teacherToken) {
		this.teacherToken = teacherToken;
	}
	public String getStudentToken() {
		return studentToken;
	}
	public void setStudentToken(String studentToken) {
		this.studentToken = studentToken;
	}
	public String getStudentClientToken() {
		return studentClientToken;
	}
	public void setStudentClientToken(String studentClientToken) {
		this.studentClientToken = studentClientToken;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getInvalidDate() {
		return invalidDate;
	}
	public void setInvalidDate(String invalidDate) {
		this.invalidDate = invalidDate;
	}
	public String getAssistantToken() {
		return assistantToken;
	}
	public void setAssistantToken(String assistantToken) {
		this.assistantToken = assistantToken;
	}
	public String getSpeakerInfo() {
		return speakerInfo;
	}
	public void setSpeakerInfo(String speakerInfo) {
		this.speakerInfo = speakerInfo;
	}
	public String getScheduleInfo() {
		return scheduleInfo;
	}
	public void setScheduleInfo(String scheduleInfo) {
		this.scheduleInfo = scheduleInfo;
	}
	public boolean getClientJoin() {
		return clientJoin;
	}
	public void setClientJoin(boolean clientJoin) {
		this.clientJoin = clientJoin;
	}
	public boolean getWebJoin() {
		return webJoin;
	}
	public void setWebJoin(boolean webJoin) {
		this.webJoin = webJoin;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public Integer getUiMode() {
		return uiMode;
	}
	public void setUiMode(Integer uiMode) {
		this.uiMode = uiMode;
	}
	public Integer getUiColor() {
		return uiColor;
	}
	public void setUiColor(Integer uiColor) {
		this.uiColor = uiColor;
	}
	public Integer getScene() {
		return scene;
	}
	public void setScene(Integer scene) {
		this.scene = scene;
	}
	public Integer getUiWindow() {
		return uiWindow;
	}
	public void setUiWindow(Integer uiWindow) {
		this.uiWindow = uiWindow;
	}
	public Integer getUiVideo() {
		return uiVideo;
	}
	public void setUiVideo(Integer uiVideo) {
		this.uiVideo = uiVideo;
	}
	public boolean isUpgrade() {
		return upgrade;
	}
	public void setUpgrade(boolean upgrade) {
		this.upgrade = upgrade;
	}
	public String getSec() {
		return sec;
	}
	public void setSec(String sec) {
		this.sec = sec;
	}
	public Integer getMaxAttendees() {
		return maxAttendees;
	}
	public void setMaxAttendees(Integer maxAttendees) {
		this.maxAttendees = maxAttendees;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean getRealtime() {
		return realtime;
	}
	public void setRealtime(boolean realtime) {
		this.realtime = realtime;
	}
	
}
