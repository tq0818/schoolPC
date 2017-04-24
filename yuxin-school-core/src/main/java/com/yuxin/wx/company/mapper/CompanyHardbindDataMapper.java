package com.yuxin.wx.company.mapper;

import com.yuxin.wx.common.BaseMapper;
import com.yuxin.wx.model.company.CompanyHardbindData;


/**
 * Service Interface:Users
 * @author chopin
 * @date 2015-3-12
 */
public interface CompanyHardbindDataMapper extends BaseMapper<CompanyHardbindData> {
	
	Integer pageCount(CompanyHardbindData search);
}