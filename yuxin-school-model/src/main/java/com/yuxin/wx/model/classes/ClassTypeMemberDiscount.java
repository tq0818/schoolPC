package com.yuxin.wx.model.classes;


import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:ClassTypeMemberDiscount
 * 
 * @author chopin
 * @date 2016-5-17
 */
@SuppressWarnings("serial")
public class ClassTypeMemberDiscount extends BaseEntity {
	
	
	private String	memberLevel;		 /* 会员等级 */ 
	private double	memberDiscount;		 /* 会员折扣 */ 
	private Integer	memberId;		 /* 会员ID */ 
	private Integer	classTypeId;		 /* 课程ID */ 
	private Integer	companyId;		 /* 公司ID */ 
	private Integer discountType;  /* 类型*/
	
	private String ico;//会员图标

	// Constructor
	public ClassTypeMemberDiscount() {
	}
	
	/**
	 * full Constructor
	 */
	public ClassTypeMemberDiscount(Integer id, String memberLevel, double memberDiscount, Integer memberId, Integer classTypeId, Integer companyId) {
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

	public ClassTypeMemberDiscount setMemberLevel(String memberLevel) {
		this.memberLevel = memberLevel;
		return this;
	}
	

	public double getMemberDiscount() {
		return memberDiscount;
	}

	public void setMemberDiscount(double memberDiscount) {
		this.memberDiscount = memberDiscount;
	}

	public Integer getMemberId() {
		return memberId;
	}

	public ClassTypeMemberDiscount setMemberId(Integer memberId) {
		this.memberId = memberId;
		return this;
	}
	
	
	public Integer getClassTypeId() {
		return classTypeId;
	}

	public ClassTypeMemberDiscount setClassTypeId(Integer classTypeId) {
		this.classTypeId = classTypeId;
		return this;
	}
	
	
	public Integer getCompanyId() {
		return companyId;
	}

	public ClassTypeMemberDiscount setCompanyId(Integer companyId) {
		this.companyId = companyId;
		return this;
	}
	
	public Integer getDiscountType() {
		return discountType;
	}

	public void setDiscountType(Integer discountType) {
		this.discountType = discountType;
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
