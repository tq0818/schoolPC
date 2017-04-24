package com.yuxin.wx.vo.classes;

public class liveDetailVo {
	
	private Integer liveId; /* 直播id*/
	private Integer maxOnlineNum; /* 最大并发数*/
	private Integer num; /* 观看人数*/
	private Integer pv;	/* 观看人次*/
	
	public Integer getLiveId() {
		return liveId;
	}
	public void setLiveId(Integer liveId) {
		this.liveId = liveId;
	}
	public Integer getMaxOnlineNum() {
		return maxOnlineNum;
	}
	public void setMaxOnlineNum(Integer maxOnlineNum) {
		this.maxOnlineNum = maxOnlineNum;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public Integer getPv() {
		return pv;
	}
	public void setPv(Integer pv) {
		this.pv = pv;
	}
	
	
	public liveDetailVo() {
		super();
	}
	public liveDetailVo(Integer liveId, Integer maxOnlineNum, Integer num,
			Integer pv) {
		super();
		this.liveId = liveId;
		this.maxOnlineNum = maxOnlineNum;
		this.num = num;
		this.pv = pv;
	}
	
}
