package com.yuxin.wx.company.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yuxin.wx.api.company.ICompanyManageService;
import com.yuxin.wx.common.BaseServiceImpl;
import com.yuxin.wx.common.PageFinder2;
import com.yuxin.wx.company.mapper.CompanyMapper;
import com.yuxin.wx.model.company.CompanyLiveConfig;
import com.yuxin.wx.model.company.CompanyServiceStaticDay;
import com.yuxin.wx.model.company.CompanyVo;
@Service
@Transactional
public class CompanyManageServiceImpl extends BaseServiceImpl implements
		ICompanyManageService {
	@Autowired
	private CompanyMapper companyMapper;
	
	@Override
	public PageFinder2<CompanyVo> queryCompanyVoListByCondition(CompanyVo search) {
		
		if(search==null) return null;
		//组装查询条件
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("eduArea", search.getEduArea());
		map.put("companyName", search.getCompanyName());
		map.put("startTime", search.getStartTime());
		map.put("endTime", search.getEndTime());
		//查询结果集
		List<CompanyVo> companyVoList=companyMapper.queryCompanyVoListByCondition(search);
		//查询结果集总数
		Integer counts=companyMapper.queryCompanyVoListByConditionCount(search);
		
		return new PageFinder2<CompanyVo>(
				search.getPage(), search.getPageSize(),counts,companyVoList);
	}
	@Override
	public CompanyVo queryCompanyVoByCondition(String brachCode) {
		if(brachCode==null||brachCode=="") return null;
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("brachCode",brachCode);
		return companyMapper.queryCompanyVoByCondition(map);
	}
	@Override
    public void addBerkeley(CompanyVo search, CompanyServiceStaticDay cssd, CompanyLiveConfig clc) {
	    
		 companyMapper.addBerkeley(search);
		 int ids=search.getId();
		 cssd.setCompanyId(ids);
		 companyMapper.addCompanyServiceStaticDay(cssd);
		 clc.setCompanyId(ids);
		 clc.setLiveType(1);
		 companyMapper.companyLiveConfig(clc);
    }
	
	
}
