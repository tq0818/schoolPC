package com.yuxin.wx.vo.classes;

public class LiveRoomInfo {

	private Integer code;
	private String msg;
	private String id;
	private String liveRoomId;
	private String studentUrl;
	private String teacherUrl;
	private String assistantUrl;
	private String replayUrl;
	
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getLiveRoomId() {
		return liveRoomId;
	}
	public void setLiveRoomId(String liveRoomId) {
		this.liveRoomId = liveRoomId;
	}
	public String getStudentUrl() {
		return studentUrl;
	}
	public void setStudentUrl(String studentUrl) {
		this.studentUrl = studentUrl;
	}
	public String getTeacherUrl() {
		return teacherUrl;
	}
	public void setTeacherUrl(String teacherUrl) {
		this.teacherUrl = teacherUrl;
	}
	public String getAssistantUrl() {
		return assistantUrl;
	}
	public void setAssistantUrl(String assistantUrl) {
		this.assistantUrl = assistantUrl;
	}
	public String getReplayUrl() {
		return replayUrl;
	}
	public void setReplayUrl(String replayUrl) {
		this.replayUrl = replayUrl;
	}
	
}
