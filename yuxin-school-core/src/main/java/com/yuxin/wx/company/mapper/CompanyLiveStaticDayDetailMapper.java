package com.yuxin.wx.company.mapper;

import java.util.Map;

import com.yuxin.wx.common.BaseMapper;
import com.yuxin.wx.model.company.CompanyLiveStaticDayDetail;

/**
 * Service Interface:Users
 * @author chopin
 * @date 2015-3-12
 */
public interface CompanyLiveStaticDayDetailMapper extends BaseMapper<CompanyLiveStaticDayDetail> {

	/**
	 * 
	 * Class Name: ICompanyLiveStaticDayDetailService.java
	 * @Description: 根据id  日期 查询最大并发 
	 * @author 周文斌
	 * @date 2015-6-8 下午9:01:23
	 * @version 1.0
	 * @param companyId
	 * @return
	 */
	Integer findByCompanyId(CompanyLiveStaticDayDetail companyLiveStaticDayDetail);

	/**
	 * 
	 * Class Name: ICompanyLiveStaticDayDetailService.java
	 * @Description: 查询当月最大并发
	 * @author 周文斌
	 * @date 2015-7-13 下午7:10:05
	 * @version 1.0
	 * @param param
	 * @return
	 */
	Integer findByDateAndCompanyId(Map<String,Object> param);
}