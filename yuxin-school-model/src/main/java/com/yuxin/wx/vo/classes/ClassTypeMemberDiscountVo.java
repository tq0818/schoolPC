package com.yuxin.wx.vo.classes;


import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:ClassTypeMemberDiscount
 * 
 * @author chopin
 * @date 2016-5-17
 */
@SuppressWarnings("serial")
public class ClassTypeMemberDiscountVo extends BaseEntity {
	
	
	private String	memberLevel;		 /* 会员等级 */ 
	private Integer	memberDiscount;		 /* 会员折扣 */ 
	private Integer	memberId;		 /* 会员ID */ 
	private Integer	classTypeId;		 /* 课程ID */ 
	private Integer	companyId;		 /* 公司ID */ 
	private double memberPrice;

	private String privceMark;
	private String ico;
	
	// Constructor
	public ClassTypeMemberDiscountVo() {
	}
	
	/**
	 * full Constructor
	 */
	public ClassTypeMemberDiscountVo(Integer id, String memberLevel, Integer memberDiscount, Integer memberId, Integer classTypeId, Integer companyId) {
		setId(id);
		this.memberLevel = memberLevel;
		this.memberDiscount = memberDiscount;
		this.memberId = memberId;
		this.classTypeId = classTypeId;
		this.companyId = companyId;
	}

	// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为ClassTypeMemberDiscount可以实现连缀设置属性
	
	public String getMemberLevel() {
		return memberLevel;
	}

	public ClassTypeMemberDiscountVo setMemberLevel(String memberLevel) {
		this.memberLevel = memberLevel;
		return this;
	}
	
	
	public Integer getMemberDiscount() {
		return memberDiscount;
	}

	public ClassTypeMemberDiscountVo setMemberDiscount(Integer memberDiscount) {
		this.memberDiscount = memberDiscount;
		return this;
	}
	
	
	public Integer getMemberId() {
		return memberId;
	}

	public ClassTypeMemberDiscountVo setMemberId(Integer memberId) {
		this.memberId = memberId;
		return this;
	}
	
	
	public Integer getClassTypeId() {
		return classTypeId;
	}

	public ClassTypeMemberDiscountVo setClassTypeId(Integer classTypeId) {
		this.classTypeId = classTypeId;
		return this;
	}
	
	
	public Integer getCompanyId() {
		return companyId;
	}

	public ClassTypeMemberDiscountVo setCompanyId(Integer companyId) {
		this.companyId = companyId;
		return this;
	}
	
	public double getMemberPrice() {
		return memberPrice;
	}

	public void setMemberPrice(double memberPrice) {
		this.memberPrice = memberPrice;
	}

	public String getPrivceMark() {
		return privceMark;
	}

	public void setPrivceMark(String privceMark) {
		this.privceMark = privceMark;
	}

	public String getIco() {
		return ico;
	}

	public void setIco(String ico) {
		this.ico = ico;
	}

	@Override
	public String toString() {
		return "ClassTypeMemberDiscount [" + "id=" + getId() + ", memberLevel=" + memberLevel + ", memberDiscount=" + memberDiscount + ", memberId=" + memberId + ", classTypeId=" + classTypeId + ", companyId=" + companyId +  "]";
	}
}
