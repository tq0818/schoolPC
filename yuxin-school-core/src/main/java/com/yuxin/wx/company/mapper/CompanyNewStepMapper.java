package com.yuxin.wx.company.mapper;

import java.util.List;

import com.yuxin.wx.common.BaseMapper;
import com.yuxin.wx.model.company.CompanyNewStep;

/**
 * Service Interface:Users
 * @author chopin
 * @date 2015-3-12
 */
public interface CompanyNewStepMapper extends BaseMapper<CompanyNewStep> {

	int selectCount(Integer companyId);

	List<CompanyNewStep> findCompanyNewStepByCompany(Integer companyId);
	
	
}