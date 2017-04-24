package com.yuxin.wx.vo.company;

public class TrafficVo {
	
	private	Double total;	/* 总流量*/
	private Double remain;	/* 剩余流量*/
	private Double used;	/* 使用流量*/
	
	public Double getTotal() {
		return total;
	}
	public void setTotal(Double total) {
		this.total = total;
	}
	public Double getRemain() {
		return remain;
	}
	public void setRemain(Double remain) {
		this.remain = remain;
	}
	public Double getUsed() {
		return used;
	}
	public void setUsed(Double used) {
		this.used = used;
	}
	
	public TrafficVo() {
		super();
	}
	public TrafficVo(Double total, Double remain, Double used) {
		super();
		this.total = total;
		this.remain = remain;
		this.used = used;
	}
	
}
