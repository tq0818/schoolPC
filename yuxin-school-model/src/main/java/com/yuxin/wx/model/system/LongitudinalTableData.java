package com.yuxin.wx.model.system;


import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:LongitudinalTableData
 * 
 * @author wang.zx
 * @date 2016-1-13
 */
@SuppressWarnings("serial")
public class LongitudinalTableData extends BaseEntity {
	
	
	private Integer	colId;		 /* 字段编号 */ 
	private String	colName;		 /* 字段名称 */ 
	private String	colValue;		 /* 字段值 */ 
	private Integer	companyId;		 /* 公司ID */ 
	private Integer	schoolId;		 /* 分校ID */
	private Integer rowId;			/*行号*/
	private String tableName;		/*虚拟表名*/

	// Constructor
	public LongitudinalTableData() {
	}
	
	/**
	 * full Constructor
	 */
	public LongitudinalTableData(Integer id, Integer colId, String colName, String colValue, Integer companyId, Integer schoolId) {
		setId(id);
		this.colId = colId;
		this.colName = colName;
		this.colValue = colValue;
		this.companyId = companyId;
		this.schoolId = schoolId;
	}

	// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为LongitudinalTableData可以实现连缀设置属性
	
	public Integer getColId() {
		return colId;
	}

	public LongitudinalTableData setColId(Integer colId) {
		this.colId = colId;
		return this;
	}
	
	
	public String getColName() {
		return colName;
	}

	public LongitudinalTableData setColName(String colName) {
		this.colName = colName;
		return this;
	}
	
	
	public String getColValue() {
		return colValue;
	}

	public LongitudinalTableData setColValue(String colValue) {
		this.colValue = colValue;
		return this;
	}
	
	
	public Integer getCompanyId() {
		return companyId;
	}

	public LongitudinalTableData setCompanyId(Integer companyId) {
		this.companyId = companyId;
		return this;
	}
	
	
	public Integer getSchoolId() {
		return schoolId;
	}

	public LongitudinalTableData setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
		return this;
	}
	
	@Override
	public String toString() {
		return "LongitudinalTableData [" + "id=" + getId() + ", colId=" + colId + ", colName=" + colName + ", colValue=" + colValue + ", companyId=" + companyId + ", schoolId=" + schoolId +  "]";
	}

	public Integer getRowId() {
		return rowId;
	}

	public void setRowId(Integer rowId) {
		this.rowId = rowId;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
}
