package com.yuxin.wx.company.mapper;

import java.util.List;
import java.util.Map;

import com.yuxin.wx.common.BaseMapper;
import com.yuxin.wx.model.company.CompanyConfigCustompageGroup;

/**
 * Service Interface:Users
 * @author chopin
 * @date 2015-3-12
 */
public interface CompanyConfigCustompageGroupMapper extends BaseMapper<CompanyConfigCustompageGroup> {
	
	List<CompanyConfigCustompageGroup> queryCustomGroupList(CompanyConfigCustompageGroup search);
	
	List<CompanyConfigCustompageGroup> queryCustomGroupByCondition(Map<String, Object> map);
}