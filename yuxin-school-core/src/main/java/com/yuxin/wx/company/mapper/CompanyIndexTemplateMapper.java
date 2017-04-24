package com.yuxin.wx.company.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yuxin.wx.common.BaseMapper;
import com.yuxin.wx.model.company.CompanyIndexTemplate;

/**
 * Service Interface:Users
 * @author chopin
 * @date 2015-3-12
 */
public interface CompanyIndexTemplateMapper extends BaseMapper<CompanyIndexTemplate> {
	
	List<CompanyIndexTemplate> findTemplateByCompany(CompanyIndexTemplate templete);
	void copyToCompany(CompanyIndexTemplate templete);
	
	void updateStatusAllFalse(@Param(value="companyId") Integer companyId,@Param(value="schoolId") Integer schoolId);
	int findByName(CompanyIndexTemplate template);
	List<CompanyIndexTemplate> findTemplateByCompanyDesc(CompanyIndexTemplate search);
}