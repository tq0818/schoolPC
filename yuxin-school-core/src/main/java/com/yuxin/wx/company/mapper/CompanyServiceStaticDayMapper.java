package com.yuxin.wx.company.mapper;

import java.util.List;

import com.yuxin.wx.common.BaseMapper;
import com.yuxin.wx.model.company.CompanyEmailHistory;
import com.yuxin.wx.model.company.CompanyServiceStaticDay;

/**
 * Service Interface:Users
 * @author chopin
 * @date 2015-3-12
 */
public interface CompanyServiceStaticDayMapper extends BaseMapper<CompanyServiceStaticDay> {
	
	List<CompanyServiceStaticDay> findInfoByCompanyId(Integer companyId);

	/**
	 * 
	 * Class Name: ICompanyServiceStaticDayService.java
	 * @Description: 定时任务  查询 昨天的 服务
	 * @author 周文斌
	 * @date 2015-5-21 下午7:30:30
	 * @version 1.0
	 * @param cssd
	 * @return
	 */
	CompanyServiceStaticDay findByDateAndCompanyId(CompanyServiceStaticDay cssd);

	/**
	 * 
	 * Class Name: ICompanyServiceStaticDayService.java
	 * @Description: 定时任务 插入 表
	 * @author 周文斌
	 * @date 2015-5-21 下午8:18:26
	 * @version 1.0
	 * @param cssd
	 */
	void insert(CompanyServiceStaticDay cssd);
	
	List<CompanyServiceStaticDay> findInfoByDate(CompanyServiceStaticDay cssd);

	/**
	 * 
	 * Class Name: ICompanyServiceStaticDayService.java
	 * @Description: 统计公司所有使用流量总量
	 * @author 周文斌
	 * @date 2015-12-8 下午5:49:05
	 * @version 1.0
	 * @param companyId
	 * @return
	 */
	Double findFlowSum(Integer companyId);
	/**
	 * 
	 * Class Name: ICompanyServiceStaticDayService.java
	 * @Description: 修改根据公司id 和日期
	 * @author 周文斌
	 * @date 2015-12-8 下午8:43:01
	 * @version 1.0
	 * @param cssd
	 */
	void updateByCompanyIdDate(CompanyServiceStaticDay cssd);
}