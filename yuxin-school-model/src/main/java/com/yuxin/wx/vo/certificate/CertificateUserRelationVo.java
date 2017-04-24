package com.yuxin.wx.vo.certificate;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.yuxin.wx.common.BaseEntity;
import com.yuxin.wx.util.ShortDateSerializer;

public class CertificateUserRelationVo extends BaseEntity  {
	
	//发放信息
	private Integer id;
	private Integer	userId;		
	private Integer	cerId;		
	private Integer	status;		 /* 0-未查看 1-已查看 2-已打印、下载 */ 
	private Integer	companyId;		
	private Integer	courseId;	
	private Date receiveTime;

	//证书信息
	private String	name;		 /* 证书名称 */ 
	private String	passConditions;		 /* 证书颁发条件： 0-考试通过 1-学完课程 */
	
	//学生信息
	private String userName;
	private String mobile;
	private String stuName;
	
	//课程信息
	private String classTypeName;
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getCerId() {
		return cerId;
	}
	public void setCerId(Integer cerId) {
		this.cerId = cerId;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}
	public Integer getCourseId() {
		return courseId;
	}
	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassConditions() {
		return passConditions;
	}
	public void setPassConditions(String passConditions) {
		this.passConditions = passConditions;
	}
	
	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getReceiveTime() {
		return receiveTime;
	}
	public void setReceiveTime(Date receiveTime) {
		this.receiveTime = receiveTime;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getStuName() {
		return stuName;
	}
	public void setStuName(String stuName) {
		this.stuName = stuName;
	}
	public String getClassTypeName() {
		return classTypeName;
	}
	public void setClassTypeName(String classTypeName) {
		this.classTypeName = classTypeName;
	} 
	
}
