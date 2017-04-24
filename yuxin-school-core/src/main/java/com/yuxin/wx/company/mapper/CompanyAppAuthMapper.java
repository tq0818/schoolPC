package com.yuxin.wx.company.mapper;

import com.yuxin.wx.common.BaseMapper;
import com.yuxin.wx.model.company.CompanyAppAuth;

/**
 * Service Interface:Users
 * @author chopin
 * @date 2015-3-12
 */
public interface CompanyAppAuthMapper extends BaseMapper<CompanyAppAuth> {

	CompanyAppAuth findByParams(CompanyAppAuth caa);
	
	
}