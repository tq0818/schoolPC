package com.yuxin.wx.vo.company;

import java.util.Date;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import com.yuxin.wx.util.ShortDateSerializer;

import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:CompanyMemberServiceChangelog
 * 
 * @author wang.zx
 * @date 2015-6-25
 */
@SuppressWarnings("serial")
public class CompanyMemberServiceChangelogVo extends BaseEntity {
	
	
	private String	tableName;		/* 修改的表名 */ 
	private String	columnName;		/* 修改的字段名 */ 
	private String	changeBefore;	/* 修改前的内容 */ 
	private String	changeAfter;	/* 修改后的内容 */ 
	private String	changeReason;	/* 修改的原因 */ 
	private Date	changeTime;		/* 修改时间 */ 
	private Integer companyId;		/* 公司Id*/
	private Integer updator;		/* 操作人*/

	private String name;			/* 名字*/
	// Constructor
	public CompanyMemberServiceChangelogVo() {
	}
	
	/**
	 * full Constructor
	 */
	public CompanyMemberServiceChangelogVo(Integer id, String tableName, String columnName, String changeBefore, String changeAfter, String changeReason, Date changeTime) {
		setId(id);
		this.tableName = tableName;
		this.columnName = columnName;
		this.changeBefore = changeBefore;
		this.changeAfter = changeAfter;
		this.changeReason = changeReason;
		this.changeTime = changeTime;
	}

	// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为CompanyMemberServiceChangelog可以实现连缀设置属性
	
	public String getTableName() {
		return tableName;
	}

	public CompanyMemberServiceChangelogVo setTableName(String tableName) {
		this.tableName = tableName;
		return this;
	}
	
	
	public String getColumnName() {
		return columnName;
	}

	public CompanyMemberServiceChangelogVo setColumnName(String columnName) {
		this.columnName = columnName;
		return this;
	}
	
	
	public String getChangeBefore() {
		return changeBefore;
	}

	public CompanyMemberServiceChangelogVo setChangeBefore(String changeBefore) {
		this.changeBefore = changeBefore;
		return this;
	}
	
	
	public String getChangeAfter() {
		return changeAfter;
	}

	public CompanyMemberServiceChangelogVo setChangeAfter(String changeAfter) {
		this.changeAfter = changeAfter;
		return this;
	}
	
	
	public String getChangeReason() {
		return changeReason;
	}

	public CompanyMemberServiceChangelogVo setChangeReason(String changeReason) {
		this.changeReason = changeReason;
		return this;
	}
	
	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getChangeTime() {
		return changeTime;
	}

	public CompanyMemberServiceChangelogVo setChangeTime(Date changeTime) {
		this.changeTime = changeTime;
		return this;
	}
	
	@Override
	public String toString() {
		return "CompanyMemberServiceChangelog [" + "id=" + getId() + ", tableName=" + tableName + ", columnName=" + columnName + ", changeBefore=" + changeBefore + ", changeAfter=" + changeAfter + ", changeReason=" + changeReason + ", changeTime=" + changeTime +  "]";
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	public Integer getUpdator() {
		return updator;
	}

	public void setUpdator(Integer updator) {
		this.updator = updator;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
