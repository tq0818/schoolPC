package com.yuxin.wx.company.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yuxin.wx.api.company.ICompanyPayConfigService;
import com.yuxin.wx.common.BaseServiceImpl;
import com.yuxin.wx.company.mapper.CompanyPayConfigMapper;
import com.yuxin.wx.model.company.CompanyPayConfig;
import com.yuxin.wx.system.mapper.SysConfigDictMapper;

/**
 * Service Implementation:CompanyPayConfig
 * @author ycl
 * @date 2015-4-23
 */
@Service
@Transactional
public class CompanyPayConfigServiceImpl extends BaseServiceImpl implements ICompanyPayConfigService {

	@Autowired
	private CompanyPayConfigMapper payConfigMapper;
	@Autowired
	private SysConfigDictMapper sysConfigDictMapper;
	@Override
	public void insert(CompanyPayConfig companyPayConfig) {
		// TODO Auto-generated method stub
		payConfigMapper.insert(companyPayConfig);
	}
	@Override
	public List<CompanyPayConfig> findAllCC() {
		// TODO Auto-generated method stub
		return payConfigMapper.findAllCC();
	}
	@Override
	public List<CompanyPayConfig> findAllLetv() {
		// TODO Auto-generated method stub
		return payConfigMapper.findAllLetv();
	}
	@Override
	public CompanyPayConfig findByCompanyId(Integer companyId){
		// TODO Auto-generated method stub
		return payConfigMapper.findByCompanyId(companyId);
	}
	@Override
	public List<Integer> findAllCompanyId(){
		return payConfigMapper.findAllCompanyId();
	}
	@Override
	public void updateByCompanyId(CompanyPayConfig config) {
		// TODO Auto-generated method stub
		payConfigMapper.updateByCompanyId(config);
	}
	@Override
	public Integer findCountByCompanyId(Integer companyId) {
		// TODO Auto-generated method stub
		return payConfigMapper.findCountByCompanyId(companyId);
	}
	@Override
	public CompanyPayConfig findByComIdAndPayType(CompanyPayConfig config) {
		// TODO Auto-generated method stub
		return payConfigMapper.findByComIdAndPayType(config);
	}
	@Override
	public String findGetAPPDateMode() {
		return sysConfigDictMapper.findGetAPPDateMode();
	}
}

