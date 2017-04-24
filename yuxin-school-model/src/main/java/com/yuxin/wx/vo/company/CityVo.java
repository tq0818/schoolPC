package com.yuxin.wx.vo.company;

import java.io.Serializable;
import java.util.List;

@SuppressWarnings("serial")
public class CityVo implements Serializable {
	
	private String n;
	private List<AddressVo> a;
	
	public CityVo() {
		super();
	}
	public String getN() {
		return n;
	}
	public void setN(String n) {
		this.n = n;
	}
	public List<AddressVo> getA() {
		return a;
	}
	public void setA(List<AddressVo> a) {
		this.a = a;
	}
}
