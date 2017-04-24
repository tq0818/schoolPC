package com.yuxin.wx.system.mapper;

import java.util.List;

import com.yuxin.wx.common.BaseMapper;
import com.yuxin.wx.model.system.SysConfigServiceGroupFlag;

/**
 * Service Interface:Users
 * @author chopin
 * @date 2015-3-12
 */
public interface SysConfigServiceGroupFlagMapper extends BaseMapper<SysConfigServiceGroupFlag> {

	List<SysConfigServiceGroupFlag> findAll();
	
	
}