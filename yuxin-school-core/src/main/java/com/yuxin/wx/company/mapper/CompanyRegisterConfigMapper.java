package com.yuxin.wx.company.mapper;

import com.yuxin.wx.common.BaseMapper;
import com.yuxin.wx.model.company.CompanyRegisterConfig;

/**
 * Service Interface:Users
 * @author chopin
 * @date 2015-3-12
 */
public interface CompanyRegisterConfigMapper extends BaseMapper<CompanyRegisterConfig> {
	
	CompanyRegisterConfig queryByCompanyId(CompanyRegisterConfig crc);
}