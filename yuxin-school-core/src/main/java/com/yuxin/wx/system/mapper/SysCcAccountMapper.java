package com.yuxin.wx.system.mapper;

import com.yuxin.wx.common.BaseMapper;
import com.yuxin.wx.model.system.SysCcAccount;

/**
 * Service Interface:Users
 * @author chopin
 * @date 2015-3-12
 */
public interface SysCcAccountMapper extends BaseMapper<SysCcAccount> {
	SysCcAccount findOne();
	
}