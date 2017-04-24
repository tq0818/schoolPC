package com.yuxin.wx.system.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;import com.yuxin.wx.common.BaseServiceImpl;

import com.yuxin.wx.api.system.ISysConfigLivePriceService;
import com.yuxin.wx.model.system.SysConfigLivePrice;
import com.yuxin.wx.system.mapper.SysConfigLivePriceMapper;

/**
 * Service Implementation:SysConfigLivePrice
 * @author wang.zx
 * @date 2015-4-28
 */
@Service
@Transactional
public class SysConfigLivePriceServiceImpl extends BaseServiceImpl implements ISysConfigLivePriceService {

	@Autowired
	private SysConfigLivePriceMapper sysConfigLivePriceMapper;
	
	/**
	 * 
	* @Title: saveSysConfigLivePrice
	* @Description: 新增SysConfigLivePrice
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-4-28
	* @user by chopin
	 */
	@Override
	public void insert(SysConfigLivePrice entity){
		sysConfigLivePriceMapper.insert(entity);
	};
	
	/**
	 * 
	* @Title: batchSaveSysConfigLivePrice 
	* @Description: 批量新增SysConfigLivePrice
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-4-28
	* @user by chopin
	 */
	@Override
	public void batchInsert(List<SysConfigLivePrice> sysConfigLivePrice){
		sysConfigLivePriceMapper.batchInsert(sysConfigLivePrice);
	};
	
	/**
	 * 
	* @Title: updateSysConfigLivePrice 
	* @Description: 编辑SysConfigLivePrice
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-4-28
	* @user by chopin
	 */
	@Override
	public void update(SysConfigLivePrice sysConfigLivePrice){
		sysConfigLivePriceMapper.update(sysConfigLivePrice);
	};
	
	/**
	 * 
	* @Title: deleteSysConfigLivePriceById 
	* @Description: 根据id删除SysConfigLivePrice
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-4-28
	* @user by chopin
	 */
	 @Override
	public void deleteSysConfigLivePriceById(Integer id){
		sysConfigLivePriceMapper.deleteById(id);
	};
	
	/**
	 * 
	* @Title: deleteSysConfigLivePriceByIds 
	* @Description: 根据id批量删除SysConfigLivePrice
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-4-28
	* @user by chopin
	 */
	@Override
	public void deleteSysConfigLivePriceByIds(Integer[] ids){
		sysConfigLivePriceMapper.deleteByIds(ids);
	};
	
	/**
	 * 
	* @Title: findSysConfigLivePriceById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-4-28
	* @user by chopin
	 */
	@Override
	public SysConfigLivePrice findSysConfigLivePriceById(Integer id){
		return sysConfigLivePriceMapper.findById(id);
	};
	
	/**
	 * 
	* @Title: findSysConfigLivePriceByPage 
	* @Description: 分页查询
	* @return
	* @return List<SysConfigLivePrice>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-4-28
	* @user by chopin
	 */
	@Override
	public List<SysConfigLivePrice> findSysConfigLivePriceByPage(SysConfigLivePrice search){
		return sysConfigLivePriceMapper.page(search);
	}

	@Override
	public List<SysConfigLivePrice> findList() {
		// TODO Auto-generated method stub
		return sysConfigLivePriceMapper.findList();
	};
	
	@Override
	public List<SysConfigLivePrice> findLivesLowerEast(){
		return sysConfigLivePriceMapper.findLivesLowerEast();
	}
}
