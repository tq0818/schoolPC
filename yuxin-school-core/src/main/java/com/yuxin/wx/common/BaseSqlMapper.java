package com.yuxin.wx.common;

import java.util.List;
import java.util.Map;

public interface BaseSqlMapper extends BaseMapper<Map> {

	
	/**
	 * 
	* @Title: queryDetail
	* @Description: 执行原生SQL查询
	* @param param
	* @return
	* @return Integer    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-04 下午12:12:51
	* @user by chopin
	 */
	List<Map> executeQuery(String sql);
	
	/**
	 * 
	* @Title: queryDetail
	* @Description: 执行原生SQL查询
	* @param param
	* @return
	* @return Integer    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-04 下午12:12:51
	* @user by chopin
	 */
	List<Map> executeInsert(String sql);
	
	/**
	 * 
	* @Title: queryDetail
	* @Description: 执行原生SQL查询
	* @param param
	* @return
	* @return Integer    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-04 下午12:12:51
	* @user by chopin
	 */
	List<Map> executeDelete(String sql);
	
	/**
	 * 
	* @Title: queryDetail
	* @Description: 执行原生SQL查询
	* @param param
	* @return
	* @return Integer    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-04 下午12:12:51
	* @user by chopin
	 */
	List<Map> executeUpdate(String sql);
	
}
