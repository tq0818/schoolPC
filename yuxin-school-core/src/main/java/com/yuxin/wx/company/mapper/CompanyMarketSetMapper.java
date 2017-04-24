package com.yuxin.wx.company.mapper;

import java.util.List;
import java.util.Map;

import com.yuxin.wx.common.BaseMapper;
import com.yuxin.wx.model.company.CompanyMarketSet;
import com.yuxin.wx.vo.company.CompanyTotalVo;
import com.yuxin.wx.vo.query.MarketingVo;

/**
 * Service Interface:Users
 * @author chopin
 * @date 2015-3-12
 */
public interface CompanyMarketSetMapper extends BaseMapper<CompanyMarketSet> {
	CompanyMarketSet findByCompanyId(Integer companyId);
	List<MarketingVo> findMarketingPageByDate(CompanyTotalVo map);
	List<MarketingVo> findMarketingByDate(CompanyTotalVo map);
	int pageCount2(CompanyTotalVo map);
}