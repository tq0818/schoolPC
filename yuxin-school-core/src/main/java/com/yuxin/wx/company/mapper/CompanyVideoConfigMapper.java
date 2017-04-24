package com.yuxin.wx.company.mapper;

import org.apache.ibatis.annotations.Param;

import com.yuxin.wx.common.BaseMapper;
import com.yuxin.wx.model.company.CompanyVideoConfig;

/**
 * Service Interface:Users
 * @author chopin
 * @date 2015-3-12
 */
public interface CompanyVideoConfigMapper extends BaseMapper<CompanyVideoConfig> {

	/**
	 * 
	 * Class Name: ICompanyVideoConfigService.java
	 * @Description: 查询公司配置
	 * @author 周文斌
	 * @date 2017-3-22 下午2:23:11
	 * @modify	2017-3-22 下午2:23:11
	 * @version 
	 * @param companyId
	 * @return
	 */
	CompanyVideoConfig findConfigByCompanyId(@Param("companyId") Integer companyId);
	
}