package com.yuxin.wx.company.mapper;

import java.util.List;
import java.util.Map;

import com.yuxin.wx.common.BaseMapper;
import com.yuxin.wx.model.company.CompanyConfigProxyOrg;

/**
 * Service Interface:Users
 * 
 * @author chopin
 * @date 2015-3-12
 */
public interface CompanyConfigProxyOrgMapper extends BaseMapper<CompanyConfigProxyOrg> {

    List<CompanyConfigProxyOrg> findProxysList(CompanyConfigProxyOrg search);

    int findProxysListCount(CompanyConfigProxyOrg search);

    List<CompanyConfigProxyOrg> queryProxyByCompanyId(CompanyConfigProxyOrg search);

    int queryCountByNameOrPrefix(Map<String, Object> map);

    CompanyConfigProxyOrg findByInviteCode(CompanyConfigProxyOrg search);
    
    List<CompanyConfigProxyOrg> queryCompanyUnsetRateOrg(Integer companyId);
}