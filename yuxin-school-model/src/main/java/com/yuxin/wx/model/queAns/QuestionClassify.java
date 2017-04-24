package com.yuxin.wx.model.queAns;

import java.util.Date;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import com.yuxin.wx.util.ShortDateSerializer;

import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:QuestionClassify
 * 
 * @author wang.zx
 * @date 2015-12-9
 */
@SuppressWarnings("serial")
public class QuestionClassify extends BaseEntity {
	
	
	private String	itemId;		 /* 学科小类ID */ 
	private String	classifyName;		 /* 分类的别名 */ 
	private Integer	companyId;		 /* 公司ID */ 
	private Integer	schoolId;		 /* 分校ID */ 
	private Integer	classType;		 /* 分类类型（1. 自定义分类  2. 学科分类） */ 
	private Integer	delFlag;		 /* 是否启用 */ 
	private Date	createTime;		 /* 创建时间 */ 
	
	private String 	itemName;		/* 学科小类名*/

	// Constructor
	public QuestionClassify() {
	}
	
	/**
	 * full Constructor
	 */
	public QuestionClassify(Integer id, String itemId, String classifyName, Integer companyId, Integer schoolId, Integer classType, Integer delFlag, Date createTime) {
		setId(id);
		this.itemId = itemId;
		this.classifyName = classifyName;
		this.companyId = companyId;
		this.schoolId = schoolId;
		this.classType = classType;
		this.delFlag = delFlag;
		this.createTime = createTime;
	}

	// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为QuestionClassify可以实现连缀设置属性
	
	public String getItemId() {
		return itemId;
	}

	public QuestionClassify setItemId(String itemId) {
		this.itemId = itemId;
		return this;
	}
	
	
	public String getClassifyName() {
		return classifyName;
	}

	public QuestionClassify setClassifyName(String classifyName) {
		this.classifyName = classifyName;
		return this;
	}
	
	
	public Integer getCompanyId() {
		return companyId;
	}

	public QuestionClassify setCompanyId(Integer companyId) {
		this.companyId = companyId;
		return this;
	}
	
	
	public Integer getSchoolId() {
		return schoolId;
	}

	public QuestionClassify setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
		return this;
	}
	
	
	public Integer getClassType() {
		return classType;
	}

	public QuestionClassify setClassType(Integer classType) {
		this.classType = classType;
		return this;
	}
	
	
	public Integer getDelFlag() {
		return delFlag;
	}

	public QuestionClassify setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
		return this;
	}
	
	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getCreateTime() {
		return createTime;
	}

	public QuestionClassify setCreateTime(Date createTime) {
		this.createTime = createTime;
		return this;
	}
	
	@Override
	public String toString() {
		return "QuestionClassify [" + "id=" + getId() + ", itemId=" + itemId + ", classifyName=" + classifyName + ", companyId=" + companyId + ", schoolId=" + schoolId + ", classType=" + classType + ", delFlag=" + delFlag + ", createTime=" + createTime +  "]";
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
}
