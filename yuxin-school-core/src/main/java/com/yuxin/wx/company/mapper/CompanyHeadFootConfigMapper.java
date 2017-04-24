package com.yuxin.wx.company.mapper;

import java.util.List;

import com.yuxin.wx.common.BaseMapper;
import com.yuxin.wx.model.company.CompanyHeadFootConfig;

/**
 * Service Interface:Users
 * @author chopin
 * @date 2015-3-12
 */
public interface CompanyHeadFootConfigMapper extends BaseMapper<CompanyHeadFootConfig> {
	
	CompanyHeadFootConfig findFootConfigByCompany(CompanyHeadFootConfig search);
	
	List<CompanyHeadFootConfig> findTemplatesById(Integer companyId);
}