package com.yuxin.wx.vo.company;

public class Record {

	private String	id;		
	private String	name;		
	private Long	recordStartTime;		
	private Long	recordEndTime;		
	private Long	createdTime;		
	private String	size;		
	private String	roomId;		
	private String	creator;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getRecordStartTime() {
		return recordStartTime;
	}
	public void setRecordStartTime(Long recordStartTime) {
		this.recordStartTime = recordStartTime;
	}
	public Long getRecordEndTime() {
		return recordEndTime;
	}
	public void setRecordEndTime(Long recordEndTime) {
		this.recordEndTime = recordEndTime;
	}
	public Long getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Long createdTime) {
		this.createdTime = createdTime;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String getRoomId() {
		return roomId;
	}
	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}		
}
