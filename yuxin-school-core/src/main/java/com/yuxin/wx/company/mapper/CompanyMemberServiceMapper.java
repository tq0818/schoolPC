package com.yuxin.wx.company.mapper;

import java.util.List;

import com.yuxin.wx.common.BaseMapper;
import com.yuxin.wx.model.company.Company;
import com.yuxin.wx.model.company.CompanyMemberService;
import com.yuxin.wx.vo.company.CompanyMemberServiceVo;
import com.yuxin.wx.vo.company.CompanyMemberServicesTotalVo;

/**
 * Service Interface:Users
 * @author chopin
 * @date 2015-3-12
 */
public interface CompanyMemberServiceMapper extends BaseMapper<CompanyMemberService> {
	
	CompanyMemberService findByCompanyId(Integer companyId);
	
	List<CompanyMemberServiceVo> findCompanyMemberServiceVoByPage(Company company);
	
	Integer findTotalCount(Company company);
	
	void updateByCompanyId(CompanyMemberService entity);
	
	CompanyMemberServiceVo findCompanyMemberInfoByCompanyId(Integer companyId);
	
	List<CompanyMemberServicesTotalVo> queryCopanyServices();
}