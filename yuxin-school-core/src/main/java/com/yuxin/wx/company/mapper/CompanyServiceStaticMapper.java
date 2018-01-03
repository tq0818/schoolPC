package com.yuxin.wx.company.mapper;

import java.util.List;

import com.yuxin.wx.common.BaseMapper;
import com.yuxin.wx.model.company.CompanyServiceStatic;
import com.yuxin.wx.vo.company.CompanyAlarmLogVo;
import com.yuxin.wx.vo.system.SystemConfigServiceVo;

/**
 * Service Interface:Users
 * @author chopin
 * @date 2015-3-12
 */
public interface CompanyServiceStaticMapper extends BaseMapper<CompanyServiceStatic> {
	//更新储存空间
	void updateByCompanyStatus(Integer companyId);
	
	CompanyServiceStatic findByCompanyId(Integer companyId);
	
	List<CompanyServiceStatic> queryCompanyServicesUsed();
	
	void insertCompanyAlarmLog(CompanyAlarmLogVo alarm);
	
	List<CompanyAlarmLogVo> queryCompanyServiceLogExit(CompanyAlarmLogVo alarm);
	
	List<SystemConfigServiceVo> queryCompanyNoService(Integer companyId);
	
	List<SystemConfigServiceVo> queryCompanyAllService(Integer companyId);
}