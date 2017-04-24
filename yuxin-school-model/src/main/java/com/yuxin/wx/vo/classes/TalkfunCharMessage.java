package com.yuxin.wx.vo.classes;

import com.yuxin.wx.common.BaseEntity;

@SuppressWarnings("serial")
public class TalkfunCharMessage extends BaseEntity {
	
	private String liveid;		/* 直播id*/
	private String course_id;	/* 课程id*/
	private String xid;			
	private String uid;			/* 用户uid*/
	private String nickname;	/* 用户昵称*/
	private String role;		/* 用户权限*/
	private String time;		/* 消息时间*/
	private String timestamp;	/* 时间戳*/
	private String msg;			/* 消息内容*/
	private String cmd;			/* 命令*/
	private String timeline;
	
	public TalkfunCharMessage() {
		super();
	}
	public TalkfunCharMessage(String liveid, String course_id, String xid,
			String uid, String nickname, String role, String time,
			String timestamp, String msg, String cmd, String timeline) {
		super();
		this.liveid = liveid;
		this.course_id = course_id;
		this.xid = xid;
		this.uid = uid;
		this.nickname = nickname;
		this.role = role;
		this.time = time;
		this.timestamp = timestamp;
		this.msg = msg;
		this.cmd = cmd;
		this.timeline = timeline;
	}
	public String getLiveid() {
		return liveid;
	}
	public void setLiveid(String liveid) {
		this.liveid = liveid;
	}
	public String getCourse_id() {
		return course_id;
	}
	public void setCourse_id(String course_id) {
		this.course_id = course_id;
	}
	public String getXid() {
		return xid;
	}
	public void setXid(String xid) {
		this.xid = xid;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getCmd() {
		return cmd;
	}
	public void setCmd(String cmd) {
		this.cmd = cmd;
	}
	public String getTimeline() {
		return timeline;
	}
	public void setTimeline(String timeline) {
		this.timeline = timeline;
	}	
	
}
