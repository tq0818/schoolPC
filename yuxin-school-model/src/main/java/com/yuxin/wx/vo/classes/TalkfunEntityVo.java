package com.yuxin.wx.vo.classes;

public class TalkfunEntityVo {
	
	private String partner_id;		/* 合作方id*/
	private String bid;				/* 主播id*/
	private String course_name;		/* 课程名称*/
	private String start_time;		/* 开始时间*/
	private String end_time;		/* 结束时间*/
	private String zhubo_key;		/* 主播登录密码*/
	private String admin_key;		/* 助教登录密码*/
	private String user_key;		/* 学生登录密码*/
	private String departmentID;	/* */
	private String add_time;		/* 添加时间*/
	private String course_id; 		/* 课程id*/
	
	public TalkfunEntityVo() {
		super();
	}
	public TalkfunEntityVo(String partner_id, String bid, String course_name,
			String start_time, String end_time, String zhubo_key,
			String admin_key, String user_key, String departmentID,
			String add_time, String course_id) {
		super();
		this.partner_id = partner_id;
		this.bid = bid;
		this.course_name = course_name;
		this.start_time = start_time;
		this.end_time = end_time;
		this.zhubo_key = zhubo_key;
		this.admin_key = admin_key;
		this.user_key = user_key;
		this.departmentID = departmentID;
		this.add_time = add_time;
		this.course_id = course_id;
	}
	
	public String getPartner_id() {
		return partner_id;
	}
	public void setPartner_id(String partner_id) {
		this.partner_id = partner_id;
	}
	public String getBid() {
		return bid;
	}
	public void setBid(String bid) {
		this.bid = bid;
	}
	public String getCourse_name() {
		return course_name;
	}
	public void setCourse_name(String course_name) {
		this.course_name = course_name;
	}
	public String getStart_time() {
		return start_time;
	}
	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}
	public String getEnd_time() {
		return end_time;
	}
	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}
	public String getZhubo_key() {
		return zhubo_key;
	}
	public void setZhubo_key(String zhubo_key) {
		this.zhubo_key = zhubo_key;
	}
	public String getAdmin_key() {
		return admin_key;
	}
	public void setAdmin_key(String admin_key) {
		this.admin_key = admin_key;
	}
	public String getUser_key() {
		return user_key;
	}
	public void setUser_key(String user_key) {
		this.user_key = user_key;
	}
	public String getDepartmentID() {
		return departmentID;
	}
	public void setDepartmentID(String departmentID) {
		this.departmentID = departmentID;
	}
	public String getAdd_time() {
		return add_time;
	}
	public void setAdd_time(String add_time) {
		this.add_time = add_time;
	}
	public String getCourse_id() {
		return course_id;
	}
	public void setCourse_id(String course_id) {
		this.course_id = course_id;
	}
}
