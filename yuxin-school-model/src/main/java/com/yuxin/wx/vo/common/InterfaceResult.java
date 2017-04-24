package com.yuxin.wx.vo.common;

import com.yuxin.wx.model.student.Student;
import com.yuxin.wx.model.user.UsersFront;

public class InterfaceResult {

	private String operRes;
	private String accessKey;
	private String token;
	private String error;
	private String message;
	private Student student;
	private UsersFront user;
	private String timestamp;
	private String companyId;
	private String schoolId;

	
	public String getAccessKey() {
		return accessKey;
	}
	public void setAccessKey(String accessKey) {
		this.accessKey = accessKey;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getOperRes() {
		return operRes;
	}
	public void setOperRes(String operRes) {
		this.operRes = operRes;
	}
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	public UsersFront getUser() {
		return user;
	}
	public void setUser(UsersFront user) {
		this.user = user;
	}
	
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public String getCompanyId() {
		return companyId;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	public String getSchoolId() {
		return schoolId;
	}
	public void setSchoolId(String schoolId) {
		this.schoolId = schoolId;
	}
	@Override
	public String toString() {
		return "InterfaceResult [operRes=" + operRes + ", accessKey="
				+ accessKey + ", token=" + token + ", error=" + error
				+ ", message=" + message + ", student=" + student + ", user="
				+ user + ", timestamp=" + timestamp + ", companyId="
				+ companyId + ", schoolId=" + schoolId + "]";
	}

}
