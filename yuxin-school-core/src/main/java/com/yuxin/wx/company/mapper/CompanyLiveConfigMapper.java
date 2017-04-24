package com.yuxin.wx.company.mapper;

import com.yuxin.wx.common.BaseMapper;
import com.yuxin.wx.model.company.CompanyLiveConfig;

/**
 * Service Interface:Users
 * @author chopin
 * @date 2015-3-12
 */
public interface CompanyLiveConfigMapper extends BaseMapper<CompanyLiveConfig> {

	/**
	 * 
	 * Class Name: ICompanyLiveConfigService.java
	 * @Description: 根据公司id 查询
	 * @author 周文斌
	 * @date 2016-3-1 上午11:41:40
	 * @version 1.0
	 * @param companyId
	 * @return
	 */
	CompanyLiveConfig findByCompanyId(Integer companyId);
}