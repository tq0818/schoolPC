package com.yuxin.wx.system.mapper;

import org.apache.ibatis.annotations.Param;

import com.yuxin.wx.model.system.SysConfigTable;
import com.yuxin.wx.common.BaseMapper;
/**
 * Service Interface:SysConfigTable
 * @author wang.zx
 * @date 2014-12-5
 */
public interface SysConfigTableMapper extends BaseMapper<SysConfigTable> {
	/**
	 * Class Name: SysConfigTableMapper.java
	 * @Description:(根据表名以及字段名称删除该数据)
	 * @author wang.zx 
	 * @date 2014-12-9 下午6:00:21
	 * @version 1.0
	 * @param tableName
	 * @param columnName
	 */
	void deleteSysConfigTableByTableNameAndColumn(@Param("tableName") String tableName, @Param("columnName") String columnName);
	
	SysConfigTable findSysConfigTableByTableNameAndColumn(@Param("tableName") String tableName, @Param("columnName") String columnName);
}