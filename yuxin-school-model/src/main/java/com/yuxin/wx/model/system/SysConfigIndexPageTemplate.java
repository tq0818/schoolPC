package com.yuxin.wx.model.system;

import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:SysConfigIndexPageTemplate
 * 
 * @author chopin
 * @date 2017-3-24
 */
@SuppressWarnings("serial")
public class SysConfigIndexPageTemplate extends BaseEntity {
	
	
	private String	moduleName;		 /* 模块名称 */ 
	private String	customName;		 /* 自定义名称 */ 
	private Integer	status;		 /* 状态 */ 
	private Integer	moduleType;		 /* 模块类型： 0-公开课 1-推荐课程 2-课程 3-推荐课程包 4-名师专区 5-会员 6-学员动态 7-问答社区 8-新闻资讯 9-学员心声 10-广告 */ 
	private Integer	widthSetting;		 /* 显示宽度设置 ： 0-非通屏 1-通屏 */ 
	private String	link;		 /* 链接 */ 
	private String	picPath;		 /* 图片路径 */ 
	private String	itemOneIdList;		 /* 学科列表: 存储学科ID以逗号分隔 */ 
	private String	dataSortBy;		 /* 数据排序条件： order by xxx */ 
	private Integer	dataLimitNum;		 /* 数据显示数量 */ 
	private Integer	companyId;		
	private Integer	schoolId;		
	private Integer	displaySeq;		 /* 显示顺序 */ 

	// Constructor
	public SysConfigIndexPageTemplate() {
	}
	
	/**
	 * full Constructor
	 */
	public SysConfigIndexPageTemplate(Integer id, String moduleName, String customName, Integer status, Integer moduleType, Integer widthSetting, String link, String picPath, String itemOneIdList, String dataSortBy, Integer dataLimitNum, Integer companyId, Integer schoolId, Integer displaySeq) {
		setId(id);
		this.moduleName = moduleName;
		this.customName = customName;
		this.status = status;
		this.moduleType = moduleType;
		this.widthSetting = widthSetting;
		this.link = link;
		this.picPath = picPath;
		this.itemOneIdList = itemOneIdList;
		this.dataSortBy = dataSortBy;
		this.dataLimitNum = dataLimitNum;
		this.companyId = companyId;
		this.schoolId = schoolId;
		this.displaySeq = displaySeq;
	}

	// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为SysConfigIndexPageTemplate可以实现连缀设置属性
	
	public String getModuleName() {
		return moduleName;
	}

	public SysConfigIndexPageTemplate setModuleName(String moduleName) {
		this.moduleName = moduleName;
		return this;
	}
	
	
	public String getCustomName() {
		return customName;
	}

	public SysConfigIndexPageTemplate setCustomName(String customName) {
		this.customName = customName;
		return this;
	}
	
	
	public Integer getStatus() {
		return status;
	}

	public SysConfigIndexPageTemplate setStatus(Integer status) {
		this.status = status;
		return this;
	}
	
	
	public Integer getModuleType() {
		return moduleType;
	}

	public SysConfigIndexPageTemplate setModuleType(Integer moduleType) {
		this.moduleType = moduleType;
		return this;
	}
	
	
	public Integer getWidthSetting() {
		return widthSetting;
	}

	public SysConfigIndexPageTemplate setWidthSetting(Integer widthSetting) {
		this.widthSetting = widthSetting;
		return this;
	}
	
	
	public String getLink() {
		return link;
	}

	public SysConfigIndexPageTemplate setLink(String link) {
		this.link = link;
		return this;
	}
	
	
	public String getPicPath() {
		return picPath;
	}

	public SysConfigIndexPageTemplate setPicPath(String picPath) {
		this.picPath = picPath;
		return this;
	}
	
	
	public String getItemOneIdList() {
		return itemOneIdList;
	}

	public SysConfigIndexPageTemplate setItemOneIdList(String itemOneIdList) {
		this.itemOneIdList = itemOneIdList;
		return this;
	}
	
	
	public String getDataSortBy() {
		return dataSortBy;
	}

	public SysConfigIndexPageTemplate setDataSortBy(String dataSortBy) {
		this.dataSortBy = dataSortBy;
		return this;
	}
	
	
	public Integer getDataLimitNum() {
		return dataLimitNum;
	}

	public SysConfigIndexPageTemplate setDataLimitNum(Integer dataLimitNum) {
		this.dataLimitNum = dataLimitNum;
		return this;
	}
	
	
	public Integer getCompanyId() {
		return companyId;
	}

	public SysConfigIndexPageTemplate setCompanyId(Integer companyId) {
		this.companyId = companyId;
		return this;
	}
	
	
	public Integer getSchoolId() {
		return schoolId;
	}

	public SysConfigIndexPageTemplate setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
		return this;
	}
	
	
	public Integer getDisplaySeq() {
		return displaySeq;
	}

	public SysConfigIndexPageTemplate setDisplaySeq(Integer displaySeq) {
		this.displaySeq = displaySeq;
		return this;
	}
	
	@Override
	public String toString() {
		return "SysConfigIndexPageTemplate [" + "id=" + getId() + ", moduleName=" + moduleName + ", customName=" + customName + ", status=" + status + ", moduleType=" + moduleType + ", widthSetting=" + widthSetting + ", link=" + link + ", picPath=" + picPath + ", itemOneIdList=" + itemOneIdList + ", dataSortBy=" + dataSortBy + ", dataLimitNum=" + dataLimitNum + ", companyId=" + companyId + ", schoolId=" + schoolId + ", displaySeq=" + displaySeq +  "]";
	}
}
