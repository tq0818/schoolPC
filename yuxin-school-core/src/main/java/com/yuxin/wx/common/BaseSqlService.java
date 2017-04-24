package com.yuxin.wx.common;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;import com.yuxin.wx.common.BaseServiceImpl;

@Service
@Transactional
public class BaseSqlService {
	@Autowired
	private BaseSqlMapper baseSqlMapper;
	
	public List<Map> executeQuery(String sql){
		return baseSqlMapper.executeQuery(sql);
	}
	public List<Map> executeInsert(String sql){
		return baseSqlMapper.executeInsert(sql);
	}
	public List<Map> executeUpdate(String sql){
		return baseSqlMapper.executeUpdate(sql);
	}
	public List<Map> executeDelete(String sql){
		return baseSqlMapper.executeDelete(sql);
	}
}
