package com.yuxin.wx.vo.system;

import com.yuxin.wx.common.BaseEntity;

/**
 * 
 * @ClassName: ConfigClassroomVo
 * @Description: 用于教室列表和教室详情
 * @author liuxindong
 * @date 2014-12-21 下午2:39:38
 * @version 1.0
 */
public class ConfigClassroomVo extends BaseEntity {

	private static final long serialVersionUID = -806650422646035314L;
	
	// 校区名称
	private String campusName;

	// 教室名称
	private String roomName;

	// 教室属性
	private String roomAttrName;

	// 教室状态
	private Integer status;

	// 教室类别
	private String roomTypeName;

	// 租用时段
	private String rentScopeName;

	// 教室容量
	private String seatNumName;

	// 教室级别
	private String roomLevelName;
	
	// 教室地址
	private String	address;
	
	// 公交线路
	private String	busLine;
	
	// 备注
	private String	remark;
	
	public String getCampusName() {
		return campusName;
	}

	public void setCampusName(String campusName) {
		this.campusName = campusName;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public String getRoomAttrName() {
		return roomAttrName;
	}

	public void setRoomAttrName(String roomAttrName) {
		this.roomAttrName = roomAttrName;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getRoomTypeName() {
		return roomTypeName;
	}

	public void setRoomTypeName(String roomTypeName) {
		this.roomTypeName = roomTypeName;
	}

	public String getRentScopeName() {
		return rentScopeName;
	}

	public void setRentScopeName(String rentScopeName) {
		this.rentScopeName = rentScopeName;
	}

	public String getSeatNumName() {
		return seatNumName;
	}

	public void setSeatNumName(String seatNumName) {
		this.seatNumName = seatNumName;
	}

	public String getRoomLevelName() {
		return roomLevelName;
	}

	public void setRoomLevelName(String roomLevelName) {
		this.roomLevelName = roomLevelName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getBusLine() {
		return busLine;
	}

	public void setBusLine(String busLine) {
		this.busLine = busLine;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
