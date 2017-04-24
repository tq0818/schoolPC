package com.yuxin.wx.vo.company;

public class SpaceVo {
	
	private	Double total;	/* 总空间*/
	private Double remain;	/* 剩余空间*/
	private Double used;	/* 使用空间*/
	
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
	
	public SpaceVo() {
		super();
	}
	public SpaceVo(Double total, Double remain, Double used) {
		super();
		this.total = total;
		this.remain = remain;
		this.used = used;
	}
	
}
