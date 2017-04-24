package com.yuxin.wx.company.mapper;

import java.util.List;

import com.yuxin.wx.common.BaseMapper;
import com.yuxin.wx.model.company.CompanyCouponsLib;
import com.yuxin.wx.vo.company.CompanyCouponsLibOrderVo;
import com.yuxin.wx.vo.company.CompanyCouponsLibVo;

/**
 * Service Interface:Users
 * @author chopin
 * @date 2015-3-12
 */
public interface CompanyCouponsLibMapper extends BaseMapper<CompanyCouponsLib> {
	
	List<CompanyCouponsLib> queryLibsListByPatchId(CompanyCouponsLib search);
	
	int queryLibsListCountByPatchId(CompanyCouponsLib search);
	
	List<CompanyCouponsLibOrderVo> queryLibsForUseOrder(CompanyCouponsLibVo search);
	
	int queryLibsForUseOrderCount(CompanyCouponsLibVo search);
	
	List<CompanyCouponsLib> queryLibsListByPatchIdExport(CompanyCouponsLib search);
	
	CompanyCouponsLib findOneByCode(String code);
} 