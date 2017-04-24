package com.yuxin.wx.company.mapper;

import java.util.List;

import com.yuxin.wx.common.BaseMapper;
import com.yuxin.wx.model.company.CompanyConfigCustompage;

/**
 * Service Interface:Users
 * @author chopin
 * @date 2015-3-12
 */
public interface CompanyConfigCustompageMapper extends BaseMapper<CompanyConfigCustompage> {
	
	List<CompanyConfigCustompage> queryCompanyCustomList(CompanyConfigCustompage search);
	Integer queryCompanyCustomListCount(CompanyConfigCustompage search);
	
	Integer templeteCount(CompanyConfigCustompage search);
	
	List<CompanyConfigCustompage> queryCompanyCustomListByCondition(CompanyConfigCustompage search);
}