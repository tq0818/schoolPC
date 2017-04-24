package com.yuxin.wx.vo.company;


import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:CompanyMemberLevelDetail
 * 
 * @author chopin
 * @date 2016-5-17
 */
@SuppressWarnings("serial")
public class CompanyMemberLevelDetailVo extends BaseEntity {
	
	
	private Integer	memberId;		 /* 会员ID */ 
	private Integer	length;		 /* 会员时长，单位月 */ 
	private Double	price;		 /* 价格 */ 
	private String	name;		 /* 一个月、二个月 */ 
	private Integer	status;		 /* 状态 0-无效 1-有效 */ 
	private String  unit;  /*单位 integral 积分 ， money  元 */
	// Constructor
	public CompanyMemberLevelDetailVo() {
	}
	
	/**
	 * full Constructor
	 */

	// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为CompanyMemberLevelDetail可以实现连缀设置属性
	public CompanyMemberLevelDetailVo(Integer memberId, Integer length, Double price, String name, Integer status,
			String unit) {
		super();
		this.memberId = memberId;
		this.length = length;
		this.price = price;
		this.name = name;
		this.status = status;
		this.unit = unit;
	}
	
	
	public Integer getMemberId() {
		return memberId;
	}


	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public CompanyMemberLevelDetailVo setMemberId(Integer memberId) {
		this.memberId = memberId;
		return this;
	}
	
	
	public Integer getLength() {
		return length;
	}

	public CompanyMemberLevelDetailVo setLength(Integer length) {
		this.length = length;
		return this;
	}
	
	
	public Double getPrice() {
		return price;
	}

	public CompanyMemberLevelDetailVo setPrice(Double price) {
		this.price = price;
		return this;
	}
	
	
	public String getName() {
		return name;
	}

	public CompanyMemberLevelDetailVo setName(String name) {
		this.name = name;
		return this;
	}
	
	
	public Integer getStatus() {
		return status;
	}

	public CompanyMemberLevelDetailVo setStatus(Integer status) {
		this.status = status;
		return this;
	}
	
	@Override
	public String toString() {
		return "CompanyMemberLevelDetail [" + "id=" + getId() + ", memberId=" + memberId + ", length=" + length + ", price=" + price + ", name=" + name + ", status=" + status +  "]";
	}
}
