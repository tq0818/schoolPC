package com.yuxin.wx.company.mapper;

import java.util.List;

import com.yuxin.wx.common.BaseMapper;
import com.yuxin.wx.model.company.CompanyStudyCardLib;
import com.yuxin.wx.vo.company.CompanyStudyCardLibVo;

/**
 * Service Interface:Users
 * @author chopin
 * @date 2015-3-12
 */
public interface CompanyStudyCardLibMapper extends BaseMapper<CompanyStudyCardLib> {
	
	List<CompanyStudyCardLibVo> queryStudyCardLibsListByCardId(CompanyStudyCardLibVo lib);
	
	List<CompanyStudyCardLibVo> queryStudyCardLibs(CompanyStudyCardLibVo search);
	
	int queryStudyCardLibsCount(CompanyStudyCardLibVo search);
}