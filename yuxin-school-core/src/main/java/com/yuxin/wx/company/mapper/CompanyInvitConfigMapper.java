package com.yuxin.wx.company.mapper;

import com.yuxin.wx.common.BaseMapper;
import com.yuxin.wx.model.company.CompanyInvitConfig;

/**
 * Service Interface:Users
 * @author chopin
 * @date 2015-3-12
 */
public interface CompanyInvitConfigMapper extends BaseMapper<CompanyInvitConfig> {
	CompanyInvitConfig findInvitConfigByCompanyId(Integer companyId);
}