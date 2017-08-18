package com.yuxin.wx.commodity.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.yuxin.wx.common.BaseMapper;
import com.yuxin.wx.model.commodity.Commodity;
import com.yuxin.wx.vo.commodity.CommodityVo;
/**
 * Service Interface:ClassModule
 * @author wang.zx
 * @date 2014-12-5
 */
public interface CommodityMapper extends BaseMapper<Commodity> {
	List<CommodityVo> queryCommodity(CommodityVo commodity);
	List<CommodityVo> queryProduct(CommodityVo commodity);
	int queryProductCount(CommodityVo commodity);
	List<CommodityVo> findProduct(Map map);
	List<CommodityVo> findProduct(CommodityVo map);
	/**
	 * 根据班型ID获取商品
	 * @param commodity
	 * @return
	 */
	List<CommodityVo> findCommodityByClassTypeId(Map map);
	
	/**
	 * 根据商品ID更改购买人数
	 */
	void updateBuyNumById(Integer id);
	
	List<Commodity> querycommByCompanyId(Integer companyId);

	/**
	 * 
	 * Class Name: ICommodityService.java
	 * @Description: 查询分校下 学科下 商品id
	 * @author 周文斌
	 * @date 2015-12-22 下午2:55:07
	 * @version 1.0
	 * @param commodity
	 * @return
	 */
	List<Integer> findComId(Commodity commodity);

	/**
	 * 
	 * Class Name: ICommodityService.java
	 * @Description: 批量修改
	 * @author 周文斌
	 * @date 2015-12-22 下午3:15:31
	 * @version 1.0
	 * @param param
	 */
	void updateStatus(Map<String, Object> param);
	
	Integer findCommodityIdByClassTypeId(Integer classTypeId);
	
	String findCommodityName(@Param("id") Integer id);
	
	List<CommodityVo> queryCourseByTeacherIds(Map<String,Object> map);
}