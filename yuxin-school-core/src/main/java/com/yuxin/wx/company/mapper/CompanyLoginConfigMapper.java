package com.yuxin.wx.company.mapper;

import com.yuxin.wx.common.BaseMapper;
import com.yuxin.wx.model.company.CompanyLoginConfig;

/**
 * Service Interface:Users
 * @author chopin
 * @date 2015-3-12
 */
public interface CompanyLoginConfigMapper extends BaseMapper<CompanyLoginConfig> {
	
	CompanyLoginConfig queryByCompanyId(CompanyLoginConfig search);
}