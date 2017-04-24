package com.yuxin.wx.company.mapper;

import java.util.List;

import com.yuxin.wx.common.BaseMapper;
import com.yuxin.wx.model.company.CompanyConfigCustompageTemplate;

/**
 * Service Interface:Users
 * @author chopin
 * @date 2015-3-12
 */
public interface CompanyConfigCustompageTemplateMapper extends BaseMapper<CompanyConfigCustompageTemplate> {
	
	List<CompanyConfigCustompageTemplate> queryCustomList(CompanyConfigCustompageTemplate search);
}