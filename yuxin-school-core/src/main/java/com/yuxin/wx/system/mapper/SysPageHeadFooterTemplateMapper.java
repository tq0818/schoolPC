package com.yuxin.wx.system.mapper;

import java.util.List;
import java.util.Map;

import com.yuxin.wx.common.BaseMapper;
import com.yuxin.wx.model.system.SysPageHeadFooterTemplate;

/**
 * Service Interface:Users
 * @author chopin
 * @date 2015-3-12
 */
public interface SysPageHeadFooterTemplateMapper extends BaseMapper<SysPageHeadFooterTemplate> {
	
	List<SysPageHeadFooterTemplate> findFootTempletes(String templateType);
	Integer queryIsUseingLink(Map<String, Object> map);
	
}