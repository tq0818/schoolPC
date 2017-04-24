package com.yuxin.wx.system.mapper;

import com.yuxin.wx.common.BaseMapper;
import com.yuxin.wx.model.system.SysRegisterRequestLog;

/**
 * Service Interface:Users
 * @author chopin
 * @date 2015-3-12
 */
public interface SysRegisterRequestLogMapper extends BaseMapper<SysRegisterRequestLog> {
	
	void updateByUuid(SysRegisterRequestLog search);
}