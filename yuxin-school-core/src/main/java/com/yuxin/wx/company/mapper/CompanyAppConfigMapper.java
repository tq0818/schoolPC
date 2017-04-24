package com.yuxin.wx.company.mapper;

import com.yuxin.wx.common.BaseMapper;
import com.yuxin.wx.model.company.CompanyAppConfig;

/**
 * Service Interface:Users
 * @author chopin
 * @date 2015-3-12
 */
public interface CompanyAppConfigMapper extends BaseMapper<CompanyAppConfig> {
	CompanyAppConfig queryCompanyConfig(Integer companyId);
	
}