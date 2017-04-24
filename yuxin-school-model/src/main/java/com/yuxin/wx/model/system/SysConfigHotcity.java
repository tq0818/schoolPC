package com.yuxin.wx.model.system;


import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:SysConfigHotcity
 * 
 * @author chopin
 * @date 2016-7-14
 */
@SuppressWarnings("serial")
public class SysConfigHotcity extends BaseEntity {
	
	
	private Integer	companyId;		
	private Integer	cityCode;		
	private Integer	seq;		

	// Constructor
	public SysConfigHotcity() {
	}
	
	/**
	 * full Constructor
	 */
	public SysConfigHotcity(Integer id, Integer companyId, Integer cityCode, Integer seq) {
		setId(id);
		this.companyId = companyId;
		this.cityCode = cityCode;
		this.seq = seq;
	}

	// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为SysConfigHotcity可以实现连缀设置属性
	
	public Integer getCompanyId() {
		return companyId;
	}

	public SysConfigHotcity setCompanyId(Integer companyId) {
		this.companyId = companyId;
		return this;
	}
	
	
	public Integer getCityCode() {
		return cityCode;
	}

	public SysConfigHotcity setCityCode(Integer cityCode) {
		this.cityCode = cityCode;
		return this;
	}
	
	
	public Integer getSeq() {
		return seq;
	}

	public SysConfigHotcity setSeq(Integer seq) {
		this.seq = seq;
		return this;
	}
	
	@Override
	public String toString() {
		return "SysConfigHotcity [" + "id=" + getId() + ", companyId=" + companyId + ", cityCode=" + cityCode + ", seq=" + seq +  "]";
	}
}
