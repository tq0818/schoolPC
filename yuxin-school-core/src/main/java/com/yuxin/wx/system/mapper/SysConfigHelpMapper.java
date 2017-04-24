package com.yuxin.wx.system.mapper;

import com.yuxin.wx.common.BaseMapper;
import com.yuxin.wx.model.system.SysConfigHelp;

/**
 * Service Interface:Users
 * @author chopin
 * @date 2015-3-12
 */
public interface SysConfigHelpMapper extends BaseMapper<SysConfigHelp> {
	SysConfigHelp findByLocation(String location);
	
}