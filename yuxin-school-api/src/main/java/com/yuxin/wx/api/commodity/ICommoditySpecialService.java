package com.yuxin.wx.api.commodity;

import java.util.List;

import com.yuxin.wx.model.commodity.CommoditySpecial;

/**
 * Service Interface:Commodity
 * @author wang.zx
 * @date 2015-3-14
 */
public interface ICommoditySpecialService  {
	/**
	 * 
	* @Title: saveCommodity
	* @Description: 新增Commodity
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-3-14
	* @user by wangzx
	 */
	public void insert(CommoditySpecial T);
	
	/**
	 * 专题列表分页
	 * @param special
	 * @return
	 */
    public List<CommoditySpecial> findSpecialByPage(CommoditySpecial special);
	
    /**
     * 根据ID查询专题
     * @param special
     * @return
     */
	public CommoditySpecial findSpecialById(CommoditySpecial special);
	
	/**
	 * 标题总数
	 * @return
	 */
	public int findSpecialByPageCount();
	
	/**
	 * 修改专题
	 * @param special
	 * @return
	 */
	public int updateSpecial(CommoditySpecial special);
}