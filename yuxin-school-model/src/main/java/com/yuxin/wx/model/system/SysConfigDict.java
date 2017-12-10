package com.yuxin.wx.model.system;


import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:SysConfigDict
 * @author wang.zx
 * @date 2014-12-5
 */
@SuppressWarnings("serial")
public class SysConfigDict extends BaseEntity {
	
	
	private String	dictCode;		 /* 字典类型代码 */ 
	private String	dictName;		 /* 字典名称 */ 
	private String	itemCode;		 /* 字典项编码 */ 
	private String	itemValue;		 /* 字典项名称 */ 
	private Integer	parentItemId;		 /* 父节点id */ 
	private String	itemStatusCode;		 /* 字典项状态 */ 
	private Integer	displaySeq;		 /* 显示顺序 */ 
	private Integer companyId;
	private Integer isDirectly;		/* 是否直属校：1是，0否 */

	private Integer stepId;		/* 学校所属学段 */
	private Integer delFlag;
	private String groupCode;
	// Constructor
	public SysConfigDict() {
	}
	
	/**
	 * full Constructor
	 */
	public SysConfigDict(Integer id, String dictCode, String dictName, String itemCode, String itemValue, Integer parentItemId, String itemStatusCode, Integer displaySeq) {
		setId(id);
		this.dictCode = dictCode;
		this.dictName = dictName;
		this.itemCode = itemCode;
		this.itemValue = itemValue;
		this.parentItemId = parentItemId;
		this.itemStatusCode = itemStatusCode;
		this.displaySeq = displaySeq;
	}

	// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为SysConfigDict可以实现连缀设置属性
	
	public String getDictCode() {
		return dictCode;
	}

	public SysConfigDict setDictCode(String dictCode) {
		this.dictCode = dictCode;
		return this;
	}
	
	
	public String getDictName() {
		return dictName;
	}

	public SysConfigDict setDictName(String dictName) {
		this.dictName = dictName;
		return this;
	}
	
	
	public String getItemCode() {
		return itemCode;
	}

	public SysConfigDict setItemCode(String itemCode) {
		this.itemCode = itemCode;
		return this;
	}
	
	
	public String getItemValue() {
		return itemValue;
	}

	public SysConfigDict setItemValue(String itemValue) {
		this.itemValue = itemValue;
		return this;
	}
	
	
	public Integer getParentItemId() {
		return parentItemId;
	}

	public SysConfigDict setParentItemId(Integer parentItemId) {
		this.parentItemId = parentItemId;
		return this;
	}
	
	
	public String getItemStatusCode() {
		return itemStatusCode;
	}

	public SysConfigDict setItemStatusCode(String itemStatusCode) {
		this.itemStatusCode = itemStatusCode;
		return this;
	}
	
	
	public Integer getDisplaySeq() {
		return displaySeq;
	}

	public SysConfigDict setDisplaySeq(Integer displaySeq) {
		this.displaySeq = displaySeq;
		return this;
	}
	
	
	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	public Integer getIsDirectly() {
		return isDirectly;
	}

	public void setIsDirectly(Integer isDirectly) {
		this.isDirectly = isDirectly;
	}

	public Integer getStepId() {
		return stepId;
	}

	public void setStepId(Integer stepId) {
		this.stepId = stepId;
	}

	
    public Integer getDelFlag() {
    	return delFlag;
    }

	
    public void setDelFlag(Integer delFlag) {
    	this.delFlag = delFlag;
    }

	

	
    public String getGroupCode() {
    	return groupCode;
    }

	
    public void setGroupCode(String groupCode) {
    	this.groupCode = groupCode;
    }

	@Override
	public String toString() {
		return "SysConfigDict [" + "id=" + getId() + ", dictCode=" + dictCode + ", dictName=" + dictName + ", itemCode=" + itemCode + ", itemValue=" + itemValue + ", parentItemId=" + parentItemId + ", itemStatusCode=" + itemStatusCode + ", displaySeq=" + displaySeq +  "]";
	}
}
