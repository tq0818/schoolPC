package com.yuxin.wx.system.mapper;

import java.util.List;

import com.yuxin.wx.common.BaseMapper;
import com.yuxin.wx.model.system.SysConfigIco;

/**
 * Service Interface:Users
 * @author chopin
 * @date 2015-3-12
 */
public interface SysConfigIcoMapper extends BaseMapper<SysConfigIco> {
	
	List<SysConfigIco> queryAllIcoLists(SysConfigIco search);
	
	List<SysConfigIco> queryUseIco(Integer companyId);
}