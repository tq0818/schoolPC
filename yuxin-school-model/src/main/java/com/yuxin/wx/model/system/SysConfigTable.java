package com.yuxin.wx.model.system;


import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:SysConfigTable
 * @author wang.zx
 * @date 2014-12-5
 */
@SuppressWarnings("serial")
public class SysConfigTable extends BaseEntity {
	
	private String	tableName;		 /* 表名 */ 
	private String	columnName;		 /* 字段名 */ 
	private Integer	isRequired;		 /* 是否必填，1：是；0：否 */ 
	private Integer	isSystem;		 /* 是否系统管控字段，1：是；0：否；是的话不允许用户编辑，否的话允许用户编辑该列是否必填 */ 

	// Constructor
	public SysConfigTable() {
	}
	
	/**
	 * full Constructor
	 */
	public SysConfigTable(String tableName, String columnName, Integer isRequired, Integer isSystem) {
		this.tableName = tableName;
		this.columnName = columnName;
		this.isRequired = isRequired;
		this.isSystem = isSystem;
	}

	// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为SysConfigTable可以实现连缀设置属性
	
	public String getTableName() {
		return tableName;
	}

	public SysConfigTable setTableName(String tableName) {
		this.tableName = tableName;
		return this;
	}
	
	
	public String getColumnName() {
		return columnName;
	}

	public SysConfigTable setColumnName(String columnName) {
		this.columnName = columnName;
		return this;
	}
	
	
	public Integer getIsRequired() {
		return isRequired;
	}

	public SysConfigTable setIsRequired(Integer isRequired) {
		this.isRequired = isRequired;
		return this;
	}
	
	
	public Integer getIsSystem() {
		return isSystem;
	}

	public SysConfigTable setIsSystem(Integer isSystem) {
		this.isSystem = isSystem;
		return this;
	}
	
	@Override
	public String toString() {
		return "SysConfigTable [" + "tableName=" + tableName + ", columnName=" + columnName + ", isRequired=" + isRequired + ", isSystem=" + isSystem +  "]";
	}
}
