package com.yuxin.wx.company.mapper;

import java.util.List;

import com.yuxin.wx.common.BaseMapper;
import com.yuxin.wx.model.company.CompanyMessageHistory;

/**
 * Service Interface:Users
 * @author chopin
 * @date 2015-3-12
 */
public interface CompanyMessageHistoryMapper extends BaseMapper<CompanyMessageHistory> {

	/**
	 * 
	 * Class Name: ICompanyMessageHistoryService.java
	 * @Description: 定时任务 查询短信使用量
	 * @author 周文斌
	 * @date 2015-5-22 下午5:26:19
	 * @version 1.0
	 * @param cmh
	 * @return
	 */
	Integer findMessageByDateAndCompanyId(CompanyMessageHistory cmh);

	/**
	 * 
	 * Class Name: ICompanyMessageHistoryService.java
	 * @Description: 查询学员通知结果
	 * @author 周文斌
	 * @date 2015-6-3 下午8:42:58
	 * @version 1.0
	 * @param cmh
	 * @return
	 */
	String findResult(Integer id);

	/**
	 * 
	 * Class Name: IStudentService.java
	 * @Description: 查询失败人数
	 * @author 周文斌
	 * @date 2015-6-8 下午2:28:03
	 * @version 1.0
	 * @param companyMessageHistory
	 * @return
	 */
	Integer findByUserCount(CompanyMessageHistory companyMessageHistory);
}