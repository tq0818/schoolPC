package com.yuxin.wx.vo.company;

import java.io.Serializable;
import java.util.List;

@SuppressWarnings("serial")
public class ProvinceVo implements Serializable {

	private String p;
	private List<CityVo> c;
	
	public ProvinceVo() {
		super();
	}
	public String getP() {
		return p;
	}
	public void setP(String p) {
		this.p = p;
	}
	public List<CityVo> getC() {
		return c;
	}
	public void setC(List<CityVo> c) {
		this.c = c;
	}
}
