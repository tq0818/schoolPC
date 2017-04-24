package com.yuxin.wx.company.mapper;

import com.yuxin.wx.common.BaseMapper;
import com.yuxin.wx.model.company.CompanyInvitConfigOrg;

/**
 * Service Interface:Users
 * 
 * @author chopin
 * @date 2015-3-12
 */
public interface CompanyInvitConfigOrgMapper extends BaseMapper<CompanyInvitConfigOrg> {

    CompanyInvitConfigOrg findByCompanyId(Integer companyId);
}