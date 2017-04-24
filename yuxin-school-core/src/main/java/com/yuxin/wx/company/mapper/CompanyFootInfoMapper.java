package com.yuxin.wx.company.mapper;

import com.yuxin.wx.common.BaseMapper;
import com.yuxin.wx.model.company.CompanyFootInfo;

/**
 * Service Interface:Users
 * @author chopin
 * @date 2015-3-12
 */
public interface CompanyFootInfoMapper extends BaseMapper<CompanyFootInfo> {
	
	CompanyFootInfo findByCompanyId(Integer companyId);
	
	void updateByCompanyId(CompanyFootInfo companyFootInfo);
}