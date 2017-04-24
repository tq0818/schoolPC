package com.yuxin.wx.commodity.mapper;

import com.yuxin.wx.model.commodity.CommodityProductRealtion;
import com.yuxin.wx.common.BaseMapper;
/**
 * Service Interface:ClassModule
 * @author wang.zx
 * @date 2014-12-5
 */
public interface CommodityProductRealtionMapper extends BaseMapper<CommodityProductRealtion> {
	CommodityProductRealtion findByProduyctId(CommodityProductRealtion CcommodityProductRealtion);
	CommodityProductRealtion findByClassTypeId(String id);
	CommodityProductRealtion findByClassPackageId(String id);
}