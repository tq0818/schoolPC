package com.yuxin.wx.api.commodity;

import java.util.List;

import com.yuxin.wx.model.commodity.CommodityProductRealtion;

/**
 * Service Interface:CommodityProductRealtion
 * @author wang.zx
 * @date 2015-3-14
 */
public interface ICommodityProductRealtionService  {
	/**
	 * 
	* @Title: saveCommodityProductRealtion
	* @Description: 新增CommodityProductRealtion
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-3-14
	* @user by wangzx
	 */
	void insert(CommodityProductRealtion T);
	
	/**
	 * 
	* @Title: batchSaveCommodityProductRealtion 
	* @Description: 批量新增CommodityProductRealtion
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-3-14
	* @user by wangzx
	 */
	void batchInsert(List<CommodityProductRealtion> T);
	
	/**
	 * 
	* @Title: updateCommodityProductRealtion 
	* @Description: 编辑CommodityProductRealtion
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-3-14
	* @user by wangzx
	 */
	void update(CommodityProductRealtion T);
	
	/**
	 * 
	* @Title: deleteCommodityProductRealtionById 
	* @Description: 根据id删除CommodityProductRealtion
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-3-14
	* @user by wangzx
	 */
	void deleteCommodityProductRealtionById(Integer id);
	
	/**
	 * 
	* @Title: deleteCommodityProductRealtionByIds 
	* @Description: 根据id批量删除CommodityProductRealtion
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-3-14
	* @user by wangzx
	 */
	void deleteCommodityProductRealtionByIds(Integer[] ids);
	
	/**
	 * 
	* @Title: findCommodityProductRealtionById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-3-14
	* @user by wangzx
	 */
	CommodityProductRealtion findCommodityProductRealtionById(Integer id);
	
	/**
	 * 
	* @Title: findCommodityProductRealtionByPage 
	* @Description: 分页查询
	* @return
	* @return List<CommodityProductRealtion>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-3-14
	* @user by wangzx
	 */
	List<CommodityProductRealtion> findCommodityProductRealtionByPage(CommodityProductRealtion search);
	
	
	public CommodityProductRealtion findByProduyctId(CommodityProductRealtion CcommodityProductRealtion);
	
	/**
	 * 
	 * Class Name: ICommodityProductRealtionService.java
	 * @Description: 根据班型id查询商品id
	 * @author zhang.zx
	 * @date 2015年5月26日 下午4:43:09
	 * @modifier
	 * @modify-date 2015年5月26日 下午4:43:09
	 * @version 1.0
	 * @param id
	 * @return
	 */
	CommodityProductRealtion findByClassTypeId(String id);
	
	/**
	 * 
	 * Class Name: ICommodityProductRealtionService.java
	 * @Description: 查询课程包商品
	 * @author zhang.zx
	 * @date 2016年3月28日 下午2:44:18
	 * @modifier
	 * @modify-date 2016年3月28日 下午2:44:18
	 * @version 1.0
	 * @param id
	 * @return
	 */
	CommodityProductRealtion findByClassPackageId(String id);
	
}