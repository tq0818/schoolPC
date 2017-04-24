package com.yuxin.wx.common;

/**
 * 
 * @ClassName: SysConfigConstant
 * @Description: 资源管理的常量类
 * @author liuxindong
 * @date 2014-12-13 下午12:14:35
 * @version 1.0
 */
public interface SysConfigConstant {
	
	/** 新增标识 **/
	public static final String OPERATE_ADD = "add";
	
	/** 修改标识 **/
	public static final String OPERATE_EDIT = "edit";
	
	/** 未删除 **/
	public static final Integer NO_DELTE_FLAG = 0;
	
	/** 已删除 **/
	public static final Integer HAS_DELTE_FLAG = 1;
	
	/** 标记 **/
	public static final String SHOW_fLAG = "show";
	
	/** 标记 **/
	public static final String HIDE_fLAG = "hide";
	/********************* 项目管理常量类开始  ***********************/
	
	/** 项目类型 一级项目 **/
	public static final String ITEMTYPE_FIRST = "1";
	
	/** 项目类型 二级项目 **/
	public static final String ITEMTYPE_SECOND = "2";
	
	/** 项目状态 停用 **/
	public static final String ITEMSTATUS_ENABLE = "1";
	
	/** 项目状态 启用 **/
	public static final String ITEMSTATUS_DISABLE = "0";
	
	/********************* 项目管理常量类结束  ***********************/
	
}
