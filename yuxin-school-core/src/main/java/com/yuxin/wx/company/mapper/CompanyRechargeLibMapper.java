package com.yuxin.wx.company.mapper;

import java.util.List;

import com.yuxin.wx.common.BaseMapper;
import com.yuxin.wx.model.company.CompanyRechargeLib;
import com.yuxin.wx.vo.company.CompanyRechargeLibVo;

/**
 * Service Interface:Users
 * @author chopin
 * @date 2015-3-12
 */
public interface CompanyRechargeLibMapper extends BaseMapper<CompanyRechargeLib> {
	
	List<CompanyRechargeLibVo> queryByPatchId(CompanyRechargeLibVo search);
	
	int queryCountByPatchId(CompanyRechargeLibVo search);
	
	List<CompanyRechargeLibVo> queryListByPatchId(CompanyRechargeLibVo search);
}