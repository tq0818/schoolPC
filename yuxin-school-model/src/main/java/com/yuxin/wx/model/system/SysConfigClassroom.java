package com.yuxin.wx.model.system;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.yuxin.wx.common.BaseEntity;
import com.yuxin.wx.util.ShortDateSerializer;

/**
 * POJO:SysConfigClassroom
 * @author wang.zx
 * @date 2014-12-5
 */
@SuppressWarnings("serial")
public class SysConfigClassroom extends BaseEntity {
	
	
	private String	roomName;		 /* 教室名称 */ 
	private String	roomAttrCode;		 /* 教室属性(长期教室，固定教室，临租教室) 
字典表 */ 
	private String	roomTypeCode;		 /* 教室类型(普通教室,机房)
字典表 */ 
	private String	roomLevelCode;		 /* 教室级别(1星，2星，3星)，字典表 */ 
	private String	seatNumCode;		 /* 座位数（1-49；50-99；100-199；200-299；300以上），字典表 */ 
	private String	rentScope;		 /* 租用时段（周末；工作日；不限），字典表 */ 
	private String	address;		 /* 教室地址 */ 
	private String	busLine;		 /* 公交线路 */ 
	private String	remark;		 /* 备注 */ 
	private Integer	schoolId;		 /* 所属分校 */ 
	private Integer	campusId;		 /* 所属校区 */ 
	private Integer	status;		 /* 教室状态(正常；停用)，字典表 */ 
	private Integer	delType;		 /* 删除标记：
1：已删除；0：未删除 */ 
	private Date	createTime;		
	private Integer	creator;		 /* 操作员ID */ 
	private Date	updateTime;		
	private Integer	updator;	
	private	Integer companyId;		/* 公司id*/

	// Constructor
	public SysConfigClassroom() {
	}
	
	/**
	 * full Constructor
	 */
	public SysConfigClassroom(Integer id, String roomName, String roomAttrCode, String roomTypeCode, String roomLevelCode, String seatNumCode, String rentScope, String address, String busLine, String remark, Integer schoolId, Integer campusId, Integer status, Integer delType, Date createTime, Integer creator, Date updateTime, Integer updator) {
		setId(id);
		this.roomName = roomName;
		this.roomAttrCode = roomAttrCode;
		this.roomTypeCode = roomTypeCode;
		this.roomLevelCode = roomLevelCode;
		this.seatNumCode = seatNumCode;
		this.rentScope = rentScope;
		this.address = address;
		this.busLine = busLine;
		this.remark = remark;
		this.schoolId = schoolId;
		this.campusId = campusId;
		this.status = status;
		this.delType = delType;
		this.createTime = createTime;
		this.creator = creator;
		this.updateTime = updateTime;
		this.updator = updator;
	}

	// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为SysConfigClassroom可以实现连缀设置属性
	
	public String getRoomName() {
		return roomName;
	}

	public SysConfigClassroom setRoomName(String roomName) {
		this.roomName = roomName;
		return this;
	}
	
	
	public String getRoomAttrCode() {
		return roomAttrCode;
	}

	public SysConfigClassroom setRoomAttrCode(String roomAttrCode) {
		this.roomAttrCode = roomAttrCode;
		return this;
	}
	
	
	public String getRoomTypeCode() {
		return roomTypeCode;
	}

	public SysConfigClassroom setRoomTypeCode(String roomTypeCode) {
		this.roomTypeCode = roomTypeCode;
		return this;
	}
	
	
	public String getRoomLevelCode() {
		return roomLevelCode;
	}

	public SysConfigClassroom setRoomLevelCode(String roomLevelCode) {
		this.roomLevelCode = roomLevelCode;
		return this;
	}
	
	
	public String getSeatNumCode() {
		return seatNumCode;
	}

	public SysConfigClassroom setSeatNumCode(String seatNumCode) {
		this.seatNumCode = seatNumCode;
		return this;
	}
	
	
	public String getRentScope() {
		return rentScope;
	}

	public SysConfigClassroom setRentScope(String rentScope) {
		this.rentScope = rentScope;
		return this;
	}
	
	
	public String getAddress() {
		return address;
	}

	public SysConfigClassroom setAddress(String address) {
		this.address = address;
		return this;
	}
	
	
	public String getBusLine() {
		return busLine;
	}

	public SysConfigClassroom setBusLine(String busLine) {
		this.busLine = busLine;
		return this;
	}
	
	
	public String getRemark() {
		return remark;
	}

	public SysConfigClassroom setRemark(String remark) {
		this.remark = remark;
		return this;
	}
	
	
	public Integer getSchoolId() {
		return schoolId;
	}

	public SysConfigClassroom setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
		return this;
	}
	
	
	public Integer getCampusId() {
		return campusId;
	}

	public SysConfigClassroom setCampusId(Integer campusId) {
		this.campusId = campusId;
		return this;
	}
	
	
	public Integer getStatus() {
		return status;
	}

	public SysConfigClassroom setStatus(Integer status) {
		this.status = status;
		return this;
	}
	
	
	public Integer getDelType() {
		return delType;
	}

	public SysConfigClassroom setDelType(Integer delType) {
		this.delType = delType;
		return this;
	}
	
	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getCreateTime() {
		return createTime;
	}

	public SysConfigClassroom setCreateTime(Date createTime) {
		this.createTime = createTime;
		return this;
	}
	
	
	public Integer getCreator() {
		return creator;
	}

	public SysConfigClassroom setCreator(Integer creator) {
		this.creator = creator;
		return this;
	}
	
	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getUpdateTime() {
		return updateTime;
	}

	public SysConfigClassroom setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
		return this;
	}
	
	
	public Integer getUpdator() {
		return updator;
	}

	public SysConfigClassroom setUpdator(Integer updator) {
		this.updator = updator;
		return this;
	}
	
	@Override
	public String toString() {
		return "SysConfigClassroom [" + "id=" + getId() + ", roomName=" + roomName + ", roomAttrCode=" + roomAttrCode + ", roomTypeCode=" + roomTypeCode + ", roomLevelCode=" + roomLevelCode + ", seatNumCode=" + seatNumCode + ", rentScope=" + rentScope + ", address=" + address + ", busLine=" + busLine + ", remark=" + remark + ", schoolId=" + schoolId + ", campusId=" + campusId + ", status=" + status + ", delType=" + delType + ", createTime=" + createTime + ", creator=" + creator + ", updateTime=" + updateTime + ", updator=" + updator +  "]";
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}
}
