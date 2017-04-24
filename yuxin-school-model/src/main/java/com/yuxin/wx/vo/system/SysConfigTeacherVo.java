package com.yuxin.wx.vo.system;

import java.util.Date;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import com.yuxin.wx.util.ShortDateSerializer;

import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:SysConfigTeacher
 * @author wang.zx
 * @date 2014-12-5
 */
@SuppressWarnings("serial")
public class SysConfigTeacherVo extends BaseEntity {
	
	
	private String	name;		 /* 老师姓名 */ 
	private String	teacherType;		 /* 老师类型（老师；助教；跟班生;主播）
字典表 */ 
	private String	sex;		 /* 性别（男：male；女：female） */ 
	private String	mobile;		 /* 手机号码 */ 
	private String	homePhone;		 /* 家庭电话 */ 
	private Date	birthday;		 /* 出生日期 */ 
	private String	idNumber;		 /* 身份证号 */ 
	private String	email;		 /* 邮箱地址 */ 
	private String	jobType;		 /* 工作类型，字典表 */ 
	private String	homeAddress;		 /* 家庭住址 */ 
	private String	address;		 /* 现居住地址 */ 
	private String	company;		 /* 工作单位 */ 
	private String	post;		 /* 公司职务 */ 
	private String	workPhone;		 /* 办公电话 */ 
	private String	emergencyContactName;		 /* 紧急联系人 */ 
	private String	emergencyContactPhone;		 /* 紧急联系人电话 */ 
	private String	workTime;		 /* 兼职时间段 */ 
	private Integer	schoolId;		
	private Integer	itemOneId;		 /* 所属一级项目 */ 
	private String	itemSecondId;		 /* 所属二级项目 */ 
	private String	unionpayNo;		 /* 银联号 */ 
	private String	bankName;		 /* 开户行 */ 
	private String	bankAccountName;		 /* 开户名 */ 
	private String	bankAccountNum;		 /* 银行卡号 */ 
	private String	statusCode;		 /* 状态(停用,正常,未开课)
字典表 */ 
	private Integer	delFlag;		 /* 删除标记，默认值为0
1：已删除；0：未删除 */ 
	private Date	createTime;		
	private Integer	creator;		
	private Date	updateTime;		
	private Integer	updator;		

}
