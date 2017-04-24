package com.yuxin.wx.model.system;


import java.util.List;
import java.util.Map;

import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:LongitudinalTableColDefine
 * 
 * @author wang.zx
 * @date 2016-1-13
 */
@SuppressWarnings("serial")
public class LongitudinalTableColDefine extends BaseEntity {
	
	
	private String	colName;		 /* 名称 */ 
	private String	colDataType;		 /* 数据类型 */ 
	private String	colComment;		 /* 字段描述 */ 
	private Integer	colIsPk;		 /* 是否为主键 */ 
	private Integer	colAutoIncream;		 /* 自增长 */ 
	private Integer	colAllowNull;		 /* 是否允许为空 */ 
	private Integer	companyId;		 /* 公司ID */ 
	private Integer	allowModify;		 /* 是否允许修改 */ 
	private String	tableName;		 /* 表名 */ 
	private Integer sort;			/*排序*/
	private String tldType;       /*标签形式*/
	private Integer stuAllowRead;  /*允许学员读取*/
	private Integer stuAllowWrite; /*允许学员修改*/
	private Integer orgAllowRead;  /*允许机构读取*/
	private Integer orgAllowWrite; /*允许机构修改*/
	private String styleCss;      /*自定义样式*/
	private String styleClass;    /*自定义的class名称*/
	private Integer defaultVlaue;  /*默认值*/
	private String itemsName;     /*列表中的显示名称*/
	private String itemsValue;    /*列表中的值*/ 
	private String items;    /*列表中的值*/ 
	private List<Map> itemsData;
	// Constructor
	public LongitudinalTableColDefine() {
	}

	// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为LongitudinalTableColDefine可以实现连缀设置属性
	
	public LongitudinalTableColDefine(String colName, String colDataType, String colComment, Integer colIsPk,
			Integer colAutoIncream, Integer colAllowNull, Integer companyId, Integer allowModify, String tableName,
			Integer sort, String tldType, Integer stuAllowRead, Integer stuAllowWrite, Integer orgAllowRead,
			Integer orgAllowWrite, String styleCss, String styleClass, Integer defaultVlaue, String itemsName,
			String itemsValue,String items) {
		super();
		this.colName = colName;
		this.colDataType = colDataType;
		this.colComment = colComment;
		this.colIsPk = colIsPk;
		this.colAutoIncream = colAutoIncream;
		this.colAllowNull = colAllowNull;
		this.companyId = companyId;
		this.allowModify = allowModify;
		this.tableName = tableName;
		this.sort = sort;
		this.tldType = tldType;
		this.stuAllowRead = stuAllowRead;
		this.stuAllowWrite = stuAllowWrite;
		this.orgAllowRead = orgAllowRead;
		this.orgAllowWrite = orgAllowWrite;
		this.styleCss = styleCss;
		this.styleClass = styleClass;
		this.defaultVlaue = defaultVlaue;
		this.itemsName = itemsName;
		this.itemsValue = itemsValue;
		this.items=items;
	}



	public String getColName() {
		return colName;
	}

	public LongitudinalTableColDefine setColName(String colName) {
		this.colName = colName;
		return this;
	}
	
	
	public String getColDataType() {
		return colDataType;
	}

	public LongitudinalTableColDefine setColDataType(String colDataType) {
		this.colDataType = colDataType;
		return this;
	}
	
	
	public String getColComment() {
		return colComment;
	}

	public LongitudinalTableColDefine setColComment(String colComment) {
		this.colComment = colComment;
		return this;
	}
	
	
	public Integer getColIsPk() {
		return colIsPk;
	}

	public LongitudinalTableColDefine setColIsPk(Integer colIsPk) {
		this.colIsPk = colIsPk;
		return this;
	}
	
	
	public Integer getColAutoIncream() {
		return colAutoIncream;
	}

	public LongitudinalTableColDefine setColAutoIncream(Integer colAutoIncream) {
		this.colAutoIncream = colAutoIncream;
		return this;
	}
	
	
	public Integer getColAllowNull() {
		return colAllowNull;
	}

	public LongitudinalTableColDefine setColAllowNull(Integer colAllowNull) {
		this.colAllowNull = colAllowNull;
		return this;
	}
	
	
	public Integer getCompanyId() {
		return companyId;
	}

	public LongitudinalTableColDefine setCompanyId(Integer companyId) {
		this.companyId = companyId;
		return this;
	}
	
	
	public Integer getAllowModify() {
		return allowModify;
	}

	public LongitudinalTableColDefine setAllowModify(Integer allowModify) {
		this.allowModify = allowModify;
		return this;
	}
	
	
	public String getTableName() {
		return tableName;
	}

	public LongitudinalTableColDefine setTableName(String tableName) {
		this.tableName = tableName;
		return this;
	}
	

	@Override
	public String toString() {
		return "LongitudinalTableColDefine [colName=" + colName + ", colDataType=" + colDataType + ", colComment="
				+ colComment + ", colIsPk=" + colIsPk + ", colAutoIncream=" + colAutoIncream + ", colAllowNull="
				+ colAllowNull + ", companyId=" + companyId + ", allowModify=" + allowModify + ", tableName="
				+ tableName + ", sort=" + sort + ", tldType=" + tldType + ", stuAllowRead=" + stuAllowRead
				+ ", stuAllowWrite=" + stuAllowWrite + ", orgAllowRead=" + orgAllowRead + ", orgAllowWrite="
				+ orgAllowWrite + ", styleCss=" + styleCss + ", styleClass=" + styleClass + ", defaultVlaue="
				+ defaultVlaue + ", itemsName=" + itemsName + ", itemsValue=" + itemsValue + "]";
	}



	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public String getTldType() {
		return tldType;
	}

	public void setTldType(String tldType) {
		this.tldType = tldType;
	}

	public Integer getStuAllowRead() {
		return stuAllowRead;
	}

	public void setStuAllowRead(Integer stuAllowRead) {
		this.stuAllowRead = stuAllowRead;
	}

	public Integer getStuAllowWrite() {
		return stuAllowWrite;
	}

	public void setStuAllowWrite(Integer stuAllowWrite) {
		this.stuAllowWrite = stuAllowWrite;
	}

	public Integer getOrgAllowRead() {
		return orgAllowRead;
	}

	public void setOrgAllowRead(Integer orgAllowRead) {
		this.orgAllowRead = orgAllowRead;
	}

	public Integer getOrgAllowWrite() {
		return orgAllowWrite;
	}

	public void setOrgAllowWrite(Integer orgAllowWrite) {
		this.orgAllowWrite = orgAllowWrite;
	}

	public String getStyleCss() {
		return styleCss;
	}

	public void setStyleCss(String styleCss) {
		this.styleCss = styleCss;
	}

	public String getStyleClass() {
		return styleClass;
	}

	public void setStyleClass(String styleClass) {
		this.styleClass = styleClass;
	}

	public Integer getDefaultVlaue() {
		return defaultVlaue;
	}

	public void setDefaultVlaue(Integer defaultVlaue) {
		this.defaultVlaue = defaultVlaue;
	}

	public String getItemsName() {
		return itemsName;
	}

	public void setItemsName(String itemsName) {
		this.itemsName = itemsName;
	}

	public String getItemsValue() {
		return itemsValue;
	}

	public void setItemsValue(String itemsValue) {
		this.itemsValue = itemsValue;
	}

	public String getItems() {
		return items;
	}

	public void setItems(String items) {
		this.items = items;
	}

	public List<Map> getItemsData() {
		return itemsData;
	}

	public void setItemsData(List<Map> itemsData) {
		this.itemsData = itemsData;
	}
	
	
}
