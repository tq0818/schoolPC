package com.yuxin.wx.system.mapper;

import java.util.List;
import java.util.Map;

import com.yuxin.wx.common.BaseMapper;
import com.yuxin.wx.model.system.SysConfigItem;
import com.yuxin.wx.model.system.SysConfigItemTag;

/**
 * Service Interface:Users
 * @author chopin
 * @date 2015-3-12
 */
public interface SysConfigItemTagMapper extends BaseMapper<SysConfigItemTag> {
	List<SysConfigItemTag> queryTagsBycondition(SysConfigItemTag search);
	
	List<SysConfigItemTag> queryTags(SysConfigItemTag search);
	
	Integer checkTag(SysConfigItemTag search);
	
	List<SysConfigItem> queryItemSecondIdList(Map<String, Integer> map);
}