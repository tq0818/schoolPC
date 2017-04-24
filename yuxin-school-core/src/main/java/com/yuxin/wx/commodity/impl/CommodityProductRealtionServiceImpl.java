package com.yuxin.wx.commodity.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;import com.yuxin.wx.common.BaseServiceImpl;

import com.yuxin.wx.model.commodity.CommodityProductRealtion;
import com.yuxin.wx.api.commodity.ICommodityProductRealtionService;
import com.yuxin.wx.commodity.mapper.CommodityMapper;
import com.yuxin.wx.commodity.mapper.CommodityProductRealtionMapper;
/**
 * Service Implementation:CommodityProductRealtion
 * @author wang.zx
 * @date 2015-3-14
 */
@Service
@Transactional
public class CommodityProductRealtionServiceImpl extends BaseServiceImpl implements ICommodityProductRealtionService {

	@Autowired
	private  CommodityProductRealtionMapper  commodityProductRealtionMapper;
	
	@Override
	public void insert(CommodityProductRealtion T) {
		// TODO Auto-generated method stub
		commodityProductRealtionMapper.insert(T);
	}

	@Override
	public void batchInsert(List<CommodityProductRealtion> T) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(CommodityProductRealtion T) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteCommodityProductRealtionById(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteCommodityProductRealtionByIds(Integer[] ids) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public CommodityProductRealtion findCommodityProductRealtionById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CommodityProductRealtion> findCommodityProductRealtionByPage(
			CommodityProductRealtion search) {
		return commodityProductRealtionMapper.page(search);
	}

	@Override
	public CommodityProductRealtion findByProduyctId(
			CommodityProductRealtion CcommodityProductRealtion) {
		return commodityProductRealtionMapper.findByProduyctId(CcommodityProductRealtion);
	}

	@Override
	public CommodityProductRealtion findByClassTypeId(String id) {
		// TODO Auto-generated method stub
		return commodityProductRealtionMapper.findByClassTypeId(id);
	}

	@Override
	public CommodityProductRealtion findByClassPackageId(String id) {
		// TODO Auto-generated method stub
		return commodityProductRealtionMapper.findByClassPackageId(id);
	}
	
	
}
