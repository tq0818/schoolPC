package com.yuxin.wx.company.mapper;

import com.yuxin.wx.common.BaseMapper;
import com.yuxin.wx.model.company.CompanyIntegralConfig;

/**
 * Service Interface:Users
 * @author chopin
 * @date 2015-3-12
 */
public interface CompanyIntegralConfigMapper extends BaseMapper<CompanyIntegralConfig> {
	
	CompanyIntegralConfig findByCompanyId(Integer companyId);
	
	CompanyIntegralConfig findIntegralConfigByWhere(CompanyIntegralConfig search);
}