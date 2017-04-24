package com.yuxin.wx.system.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;import com.yuxin.wx.common.BaseServiceImpl;

import com.yuxin.wx.api.system.ISysConfigVideoPriceService;
import com.yuxin.wx.model.system.SysConfigVideoPrice;
import com.yuxin.wx.system.mapper.SysConfigVideoPriceMapper;

/**
 * Service Implementation:SysConfigVideoPrice
 * @author wang.zx
 * @date 2015-4-28
 */
@Service
@Transactional
public class SysConfigVideoPriceServiceImpl extends BaseServiceImpl implements ISysConfigVideoPriceService {

	@Autowired
	private SysConfigVideoPriceMapper sysConfigVideoPriceMapper;
	
	/**
	 * 
	* @Title: saveSysConfigVideoPrice
	* @Description: 新增SysConfigVideoPrice
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-4-28
	* @user by chopin
	 */
	@Override
	public void insert(SysConfigVideoPrice entity){
		sysConfigVideoPriceMapper.insert(entity);
	};
	
	/**
	 * 
	* @Title: batchSaveSysConfigVideoPrice 
	* @Description: 批量新增SysConfigVideoPrice
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-4-28
	* @user by chopin
	 */
	@Override
	public void batchInsert(List<SysConfigVideoPrice> sysConfigVideoPrice){
		sysConfigVideoPriceMapper.batchInsert(sysConfigVideoPrice);
	};
	
	/**
	 * 
	* @Title: updateSysConfigVideoPrice 
	* @Description: 编辑SysConfigVideoPrice
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-4-28
	* @user by chopin
	 */
	@Override
	public void update(SysConfigVideoPrice sysConfigVideoPrice){
		sysConfigVideoPriceMapper.update(sysConfigVideoPrice);
	};
	
	/**
	 * 
	* @Title: deleteSysConfigVideoPriceById 
	* @Description: 根据id删除SysConfigVideoPrice
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-4-28
	* @user by chopin
	 */
	 @Override
	public void deleteSysConfigVideoPriceById(Integer id){
		sysConfigVideoPriceMapper.deleteById(id);
	};
	
	/**
	 * 
	* @Title: deleteSysConfigVideoPriceByIds 
	* @Description: 根据id批量删除SysConfigVideoPrice
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-4-28
	* @user by chopin
	 */
	@Override
	public void deleteSysConfigVideoPriceByIds(Integer[] ids){
		sysConfigVideoPriceMapper.deleteByIds(ids);
	};
	
	/**
	 * 
	* @Title: findSysConfigVideoPriceById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-4-28
	* @user by chopin
	 */
	@Override
	public SysConfigVideoPrice findSysConfigVideoPriceById(Integer id){
		return sysConfigVideoPriceMapper.findById(id);
	};
	
	/**
	 * 
	* @Title: findSysConfigVideoPriceByPage 
	* @Description: 分页查询
	* @return
	* @return List<SysConfigVideoPrice>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-4-28
	* @user by chopin
	 */
	@Override
	public List<SysConfigVideoPrice> findSysConfigVideoPriceByPage(SysConfigVideoPrice search){
		return sysConfigVideoPriceMapper.page(search);
	}

	@Override
	public List<SysConfigVideoPrice> findSysConfigVideoPriceList() {
		// TODO Auto-generated method stub
		return sysConfigVideoPriceMapper.findSysConfigVideoPriceList();
	};
	
	@Override
	public List<SysConfigVideoPrice> findSysConfigVideoPriceLowerEastWithSameService() {
		List<SysConfigVideoPrice> prices = sysConfigVideoPriceMapper.findSysConfigVideoPriceLowerEastWithSameService();
		return prices;
	}
}
