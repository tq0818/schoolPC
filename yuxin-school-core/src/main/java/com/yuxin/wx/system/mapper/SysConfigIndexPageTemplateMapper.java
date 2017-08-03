package com.yuxin.wx.system.mapper;

import java.util.List;

import com.yuxin.wx.common.BaseMapper;
import com.yuxin.wx.model.system.SysConfigIndexPageTemplate;

/**
 * Service Interface:Users
 * @author chopin
 * @date 2015-3-12
 */
public interface SysConfigIndexPageTemplateMapper extends BaseMapper<SysConfigIndexPageTemplate> {

	List<SysConfigIndexPageTemplate> findBySearch(SysConfigIndexPageTemplate search);
	
	
	List<SysConfigIndexPageTemplate> findBySearchBymoduleType(SysConfigIndexPageTemplate search);
	
}