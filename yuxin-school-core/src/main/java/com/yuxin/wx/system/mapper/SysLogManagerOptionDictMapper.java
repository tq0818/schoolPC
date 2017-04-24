package com.yuxin.wx.system.mapper;

import com.yuxin.wx.common.BaseMapper;
import com.yuxin.wx.model.system.SysLogManagerOptionDict;

/**
 * Service Interface:Users
 * @author chopin
 * @date 2015-3-12
 */
public interface SysLogManagerOptionDictMapper extends BaseMapper<SysLogManagerOptionDict> {
	
	String queryOperationByAction(String action);
	
	SysLogManagerOptionDict queryByAction(String action);
}