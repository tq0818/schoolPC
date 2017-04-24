package com.yuxin.wx.company.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;import com.yuxin.wx.common.BaseServiceImpl;

import com.yuxin.wx.api.company.ICompanyVideoStaticCcService;
import com.yuxin.wx.company.mapper.CompanyVideoStaticCcMapper;
import com.yuxin.wx.model.company.CompanyVideoStaticCc;

@Service
@Transactional
public class CompanyVideoStaticCcServiceImpl extends BaseServiceImpl implements
		ICompanyVideoStaticCcService {
	
	@Autowired
	private CompanyVideoStaticCcMapper companyVideoStaticCcMapper;

	@Override
	public void insert(CompanyVideoStaticCc companyVideoStaticCc) {
		// TODO Auto-generated method stub
		companyVideoStaticCcMapper.insert(companyVideoStaticCc);
	}

	@Override
	public CompanyVideoStaticCc findByDate(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return companyVideoStaticCcMapper.findByDate(param);
	}

	@Override
	public void update(CompanyVideoStaticCc companyVideoStaticCc) {
		// TODO Auto-generated method stub
		companyVideoStaticCcMapper.update(companyVideoStaticCc);
	}

	@Override
	public CompanyVideoStaticCc findByUpDate(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return companyVideoStaticCcMapper.findByUpDate(param);
	}

	@Override
	public List<CompanyVideoStaticCc> findByCompanyId(Integer companyId) {
		// TODO Auto-generated method stub
		return companyVideoStaticCcMapper.findByCompanyId(companyId);
	}

}
