package com.yuxin.wx.commodity.mapper;

import java.util.List;

import com.yuxin.wx.common.BaseMapper;
import com.yuxin.wx.model.commodity.CommoditySpecial;
/**
 * Service Interface:ClassModule
 * @author wang.zx
 * @date 2014-12-5
 */
public interface CommoditySpecialMapper extends BaseMapper<CommoditySpecial> {
	
	public List<CommoditySpecial> findSpecialByPage(CommoditySpecial special);
	
	public CommoditySpecial findSpecialById(CommoditySpecial special);
	
	public int findSpecialByPageCount();
	
	public int updateSpecial(CommoditySpecial special);
	
	public int deleteSpecialOrder(Integer specialId);
}