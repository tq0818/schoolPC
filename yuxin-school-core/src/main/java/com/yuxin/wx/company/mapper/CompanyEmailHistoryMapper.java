package com.yuxin.wx.company.mapper;

import com.yuxin.wx.common.BaseMapper;
import com.yuxin.wx.model.company.CompanyEmailHistory;

/**
 * Service Interface:Users
 * @author chopin
 * @date 2015-3-12
 */
public interface CompanyEmailHistoryMapper extends BaseMapper<CompanyEmailHistory> {

	/**
	 * 
	 * Class Name: ICompanyEmailHistoryService.java
	 * @Description: 定时任务 查询 昨天使用的邮件数量 根据 公司id
	 * @author 周文斌
	 * @date 2015-5-21 下午6:07:22
	 * @version 1.0
	 * @param companyId
	 * @return
	 */
	Integer findEmailCount(CompanyEmailHistory email);

	Integer findByUserCount(CompanyEmailHistory cmh);
	
}