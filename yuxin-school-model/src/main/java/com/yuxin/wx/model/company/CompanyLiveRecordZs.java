package com.yuxin.wx.model.company;

import java.util.Date;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import com.yuxin.wx.util.ShortDateSerializer;

import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:CompanyLiveRecordZs
 * 
 * @author wang.zx
 * @date 2015-12-7
 */
@SuppressWarnings("serial")
public class CompanyLiveRecordZs extends BaseEntity {
	
	
	private String	recordId;		
	private String	name;		
	private Date	recordStartTime;		
	private Date	recordEndTime;		
	private Date	recordCreatedTime;		
	private String	recordSize;		
	private String	roomId;		
	private String	creator;		
	private Date	createTime;		
	private Integer schoolId;
	private Integer companyId;

	// Constructor
	public CompanyLiveRecordZs() {
	}
	
	/**
	 * full Constructor
	 */
	public CompanyLiveRecordZs(Integer id, String recordId, String name, Date recordStartTime, Date recordEndTime, Date recordCreatedTime, String recordSize, String roomId, String creator, Date createTime) {
		setId(id);
		this.recordId = recordId;
		this.name = name;
		this.recordStartTime = recordStartTime;
		this.recordEndTime = recordEndTime;
		this.recordCreatedTime = recordCreatedTime;
		this.recordSize = recordSize;
		this.roomId = roomId;
		this.creator = creator;
		this.createTime = createTime;
	}

	// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为CompanyLiveRecordZs可以实现连缀设置属性
	
	public String getRecordId() {
		return recordId;
	}

	public CompanyLiveRecordZs setRecordId(String recordId) {
		this.recordId = recordId;
		return this;
	}
	
	
	public String getName() {
		return name;
	}

	public CompanyLiveRecordZs setName(String name) {
		this.name = name;
		return this;
	}
	
	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getRecordStartTime() {
		return recordStartTime;
	}

	public CompanyLiveRecordZs setRecordStartTime(Date recordStartTime) {
		this.recordStartTime = recordStartTime;
		return this;
	}
	
	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getRecordEndTime() {
		return recordEndTime;
	}

	public CompanyLiveRecordZs setRecordEndTime(Date recordEndTime) {
		this.recordEndTime = recordEndTime;
		return this;
	}
	
	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getRecordCreatedTime() {
		return recordCreatedTime;
	}

	public CompanyLiveRecordZs setRecordCreatedTime(Date recordCreatedTime) {
		this.recordCreatedTime = recordCreatedTime;
		return this;
	}
	
	
	public String getRecordSize() {
		return recordSize;
	}

	public CompanyLiveRecordZs setRecordSize(String recordSize) {
		this.recordSize = recordSize;
		return this;
	}
	
	
	public String getRoomId() {
		return roomId;
	}

	public CompanyLiveRecordZs setRoomId(String roomId) {
		this.roomId = roomId;
		return this;
	}
	
	
	public String getCreator() {
		return creator;
	}

	public CompanyLiveRecordZs setCreator(String creator) {
		this.creator = creator;
		return this;
	}
	
	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getCreateTime() {
		return createTime;
	}

	public CompanyLiveRecordZs setCreateTime(Date createTime) {
		this.createTime = createTime;
		return this;
	}
	
	@Override
	public String toString() {
		return "CompanyLiveRecordZs [" + "id=" + getId() + ", recordId=" + recordId + ", name=" + name + ", recordStartTime=" + recordStartTime + ", recordEndTime=" + recordEndTime + ", recordCreatedTime=" + recordCreatedTime + ", recordSize=" + recordSize + ", roomId=" + roomId + ", creator=" + creator + ", createTime=" + createTime +  "]";
	}

	public Integer getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}
}
