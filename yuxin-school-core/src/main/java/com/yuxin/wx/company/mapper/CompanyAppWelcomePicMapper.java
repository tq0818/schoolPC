package com.yuxin.wx.company.mapper;

import com.yuxin.wx.common.BaseMapper;
import com.yuxin.wx.model.company.CompanyAppWelcomePic;

/**
 * Service Interface:Users
 * @author chopin
 * @date 2015-3-12
 */
public interface CompanyAppWelcomePicMapper extends BaseMapper<CompanyAppWelcomePic> {
	
	CompanyAppWelcomePic findByparam(Integer companyId);
}