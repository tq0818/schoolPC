package com.yuxin.wx.model.certificate;





import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.yuxin.wx.common.BaseEntity;
import com.yuxin.wx.util.ShortDateSerializer;

/**
 * POJO:CertificateUserRelation * 
 * @author chopin
 * @date 2016-9-22
 */
@SuppressWarnings("serial")
public class CertificateUserRelation extends BaseEntity {
	
	
	private Integer	userId;		
	private Integer	cerId;		
	private Integer	status;		 /* 0-未查看 1-已查看 2-已打印、下载 */ 
	private Integer	companyId;		
	private Integer	courseId;	
	private Date receiveTime;

	// Constructor
	public CertificateUserRelation() {
	}
	
	/**
	 * full Constructor
	 */
	public CertificateUserRelation(Integer id, Integer userId, Integer cerId, Integer status, Integer companyId, Integer courseId ,Date receiveTime) {
		setId(id);
		this.userId = userId;
		this.cerId = cerId;
		this.status = status;
		this.companyId = companyId;
		this.courseId = courseId;
		this.receiveTime = receiveTime;
	}

	// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为CertificateUserRelation可以实现连缀设置属性
	
	public Integer getUserId() {
		return userId;
	}

	public CertificateUserRelation setUserId(Integer userId) {
		this.userId = userId;
		return this;
	}
	
	
	public Integer getCerId() {
		return cerId;
	}

	public CertificateUserRelation setCerId(Integer cerId) {
		this.cerId = cerId;
		return this;
	}
	
	
	public Integer getStatus() {
		return status;
	}

	public CertificateUserRelation setStatus(Integer status) {
		this.status = status;
		return this;
	}
	
	
	public Integer getCompanyId() {
		return companyId;
	}

	public CertificateUserRelation setCompanyId(Integer companyId) {
		this.companyId = companyId;
		return this;
	}
	
	
	public Integer getCourseId() {
		return courseId;
	}

	public CertificateUserRelation setCourseId(Integer courseId) {
		this.courseId = courseId;
		return this;
	}
	
	@Override
	public String toString() {
		return "CertificateUserRelation [" + "id=" + getId() + ", userId=" + userId + ", cerId=" + cerId + ", status=" + status + ", companyId=" + companyId + ", courseId=" + courseId +  ", receiveTime=" + receiveTime +  "]";
	}
	
	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getReceiveTime() {
		return receiveTime;
	}

	public CertificateUserRelation setReceiveTime(Date receiveTime) {
		this.receiveTime = receiveTime;
		return this;
	}
}
