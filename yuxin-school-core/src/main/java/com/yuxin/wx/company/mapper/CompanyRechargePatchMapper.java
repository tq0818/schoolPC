package com.yuxin.wx.company.mapper;

import java.util.List;

import com.yuxin.wx.common.BaseMapper;
import com.yuxin.wx.model.company.CompanyRechargePatch;
import com.yuxin.wx.vo.company.CompanyRechargePatchVo;

/**
 * Service Interface:Users
 * @author chopin
 * @date 2015-3-12
 */
public interface CompanyRechargePatchMapper extends BaseMapper<CompanyRechargePatch> {
	
	int queryPrefixCount(CompanyRechargePatch search);
	
	int queryPatchListCount(CompanyRechargePatchVo search);
	List<CompanyRechargePatchVo> queryPatchList(CompanyRechargePatchVo search);
	
	List<CompanyRechargePatchVo> queryPatchsList(CompanyRechargePatchVo search);
}