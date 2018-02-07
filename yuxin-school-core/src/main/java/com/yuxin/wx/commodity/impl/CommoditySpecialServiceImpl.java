package com.yuxin.wx.commodity.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yuxin.wx.api.commodity.ICommoditySpecialService;
import com.yuxin.wx.commodity.mapper.CommoditySpecialMapper;
import com.yuxin.wx.common.BaseServiceImpl;
import com.yuxin.wx.model.commodity.CommoditySpecial;


@Service("CommoditySpecialService")
public class CommoditySpecialServiceImpl extends BaseServiceImpl implements ICommoditySpecialService {

	@Autowired
	private CommoditySpecialMapper commoditySpecialMapper;
	
	@Override
	public void insert(CommoditySpecial special) {
		commoditySpecialMapper.insert(special);
	}

	@Override
	public List<CommoditySpecial> findSpecialByPage(CommoditySpecial special) {
		List<CommoditySpecial> list = commoditySpecialMapper.findSpecialByPage(special);
		return list;
	}

	@Override
	public CommoditySpecial findSpecialById(CommoditySpecial special) {
		CommoditySpecial sp = commoditySpecialMapper.findSpecialById(special);
		return sp;
	}

	@Override
	public int findSpecialByPageCount(CommoditySpecial special) {
		int count = commoditySpecialMapper.findSpecialByPageCount(special);
		return count;
	}

	@Override
	public int updateSpecial(CommoditySpecial special) {
	    int row = commoditySpecialMapper.updateSpecial(special);
		return row;
	}

	@Override
	public int deleteSpecialOrder(Integer specialId) {
		int row  = commoditySpecialMapper.deleteSpecialOrder(specialId);
		return row;
	}

     
}
