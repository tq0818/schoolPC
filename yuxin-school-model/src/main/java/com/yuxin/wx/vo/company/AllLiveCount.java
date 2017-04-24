package com.yuxin.wx.vo.company;

import com.yuxin.wx.common.BaseEntity;

@SuppressWarnings("serial")
public class AllLiveCount extends BaseEntity {

	private Long time;		/* 结束时间*/
	private Integer count;	/*并发数*/
	private String liveRoom;
	
	public AllLiveCount() {
		super();
	}
	public Long getTime() {
		return time;
	}
	public void setTime(Long time) {
		this.time = time;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public String getLiveRoom() {
		return liveRoom;
	}
	public void setLiveRoom(String liveRoom) {
		this.liveRoom = liveRoom;
	}
	
}
