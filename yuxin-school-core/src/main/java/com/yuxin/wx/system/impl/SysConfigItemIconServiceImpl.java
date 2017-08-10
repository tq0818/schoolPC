package com.yuxin.wx.system.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;import com.yuxin.wx.common.BaseServiceImpl;

import com.yuxin.wx.api.system.ISysConfigItemIconService;
import com.yuxin.wx.model.system.SysConfigItemIcon;
import com.yuxin.wx.system.mapper.SysConfigItemIconMapper;

@Service
@Transactional
public class SysConfigItemIconServiceImpl extends BaseServiceImpl implements ISysConfigItemIconService {

	@Autowired
	private SysConfigItemIconMapper sysConfigItemIconMapper;
	
	@Override
	public List<SysConfigItemIcon> findByPage(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return sysConfigItemIconMapper.findByPage(param);
	}

	@Override
	public Integer findByPageCount(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return sysConfigItemIconMapper.findByPageCount(param);
	}

	@Override
	public SysConfigItemIcon findUrlById(Integer id) {
		// TODO Auto-generated method stub
		return sysConfigItemIconMapper.findUrlById(id);
	}

	public SysConfigItemIconMapper getSysConfigItemIconMapper() {
		return sysConfigItemIconMapper;
	}
}
