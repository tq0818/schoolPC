package com.yuxin.wx.system.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;import com.yuxin.wx.common.BaseServiceImpl;

import com.yuxin.wx.api.system.ISysCcAccountService;
import com.yuxin.wx.model.system.SysCcAccount;
import com.yuxin.wx.system.mapper.SysCcAccountMapper;

/**
 * Service Implementation:SysCcAccount
 * @author wang.zx
 * @date 2015-6-17
 */
@Service
@Transactional
public class SysCcAccountServiceImpl extends BaseServiceImpl implements ISysCcAccountService {

	@Autowired
	private SysCcAccountMapper sysCcAccountMapper;
	
	/**
	 * 
	* @Title: saveSysCcAccount
	* @Description: 新增SysCcAccount
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-6-17
	* @user by chopin
	 */
	@Override
	public void insert(SysCcAccount entity){
		sysCcAccountMapper.insert(entity);
	};
	
	/**
	 * 
	* @Title: batchSaveSysCcAccount 
	* @Description: 批量新增SysCcAccount
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-6-17
	* @user by chopin
	 */
	@Override
	public void batchInsert(List<SysCcAccount> entity){
		sysCcAccountMapper.batchInsert(entity);
	};
	
	/**
	 * 
	* @Title: updateSysCcAccount 
	* @Description: 编辑SysCcAccount
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-6-17
	* @user by chopin
	 */
	@Override
	public void update(SysCcAccount entity){
		sysCcAccountMapper.update(entity);
	};
	
	/**
	 * 
	* @Title: deleteSysCcAccountById 
	* @Description: 根据id删除SysCcAccount
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-6-17
	* @user by chopin
	 */
	 @Override
	public void deleteSysCcAccountById(Integer id){
		sysCcAccountMapper.deleteById(id);
	};
	
	/**
	 * 
	* @Title: deleteSysCcAccountByIds 
	* @Description: 根据id批量删除SysCcAccount
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-6-17
	* @user by chopin
	 */
	@Override
	public void deleteSysCcAccountByIds(Integer[] ids){
		sysCcAccountMapper.deleteByIds(ids);
	};
	
	/**
	 * 
	* @Title: findSysCcAccountById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-6-17
	* @user by chopin
	 */
	@Override
	public SysCcAccount findSysCcAccountById(Integer id){
		return sysCcAccountMapper.findById(id);
	};
	
	/**
	 * 
	* @Title: findSysCcAccountByPage 
	* @Description: 分页查询
	* @return
	* @return List<SysCcAccount>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-6-17
	* @user by chopin
	 */
	@Override
	public List<SysCcAccount> findSysCcAccountByPage(SysCcAccount search){
		return sysCcAccountMapper.page(search);
	}

	@Override
	public SysCcAccount findOne() {
		// TODO Auto-generated method stub
		return sysCcAccountMapper.findOne();
	};
}
