package com.yuxin.wx.company.mapper;


import java.util.List;

import com.yuxin.wx.common.BaseMapper;
import com.yuxin.wx.model.company.CompanyAppBarConfig;

/**
 * Service Interface:Users
 * @author chopin
 * @date 2015-3-12
 */
public interface CompanyAppBarConfigMapper extends BaseMapper<CompanyAppBarConfig> {

	List<CompanyAppBarConfig> findByCompanyAppBarConfig(CompanyAppBarConfig search);
	
	
}