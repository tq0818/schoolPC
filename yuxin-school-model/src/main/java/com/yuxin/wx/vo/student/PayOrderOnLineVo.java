package com.yuxin.wx.vo.student;
/**
 * 
 * @ClassName: PayOrderOnLineVo
 * @Description: 线上订单Vo
 * @author 权飞虎
 * @date 2015年5月15日 上午11:24:15
 * @modifier
 * @modify-date 2015年5月15日 上午11:24:15
 * @version 1.0
 */
public class PayOrderOnLineVo {
	private Integer id;
	private Integer	userId;			/*订单用户*/
	private String name;	//姓名
	private String email;		//邮箱
	private Integer itemOneId;      /*所属一级项目*/
	private Integer itemSecondId;   /*所属二级项目*/
	private String	payStatus;      /*支付状态*/
	private Integer	classTypeId;      /*班型*/
	private String classTypeName;	//班型名称
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getItemOneId() {
		return itemOneId;
	}
	public void setItemOneId(Integer itemOneId) {
		this.itemOneId = itemOneId;
	}
	public Integer getItemSecondId() {
		return itemSecondId;
	}
	public void setItemSecondId(Integer itemSecondId) {
		this.itemSecondId = itemSecondId;
	}
	public String getPayStatus() {
		return payStatus;
	}
	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}
	public Integer getClassTypeId() {
		return classTypeId;
	}
	public void setClassTypeId(Integer classTypeId) {
		this.classTypeId = classTypeId;
	}
	
	
	
	

}
