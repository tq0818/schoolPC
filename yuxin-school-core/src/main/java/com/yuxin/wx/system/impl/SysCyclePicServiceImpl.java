package com.yuxin.wx.system.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;import com.yuxin.wx.common.BaseServiceImpl;

import com.yuxin.wx.api.system.ISysCyclePicService;
import com.yuxin.wx.model.system.SysCyclePic;
import com.yuxin.wx.system.mapper.SysCyclePicMapper;



/**
 * Service Implementation:SysCyclePic
 * @author chopin
 * @date 2015-4-11
 */
@Service
@Transactional
public class SysCyclePicServiceImpl extends BaseServiceImpl implements ISysCyclePicService {

	@Autowired
	private SysCyclePicMapper sysCyclePicMapper;
	
	/**
	 * 
	* @Title: saveSysCyclePic
	* @Description: 新增SysCyclePic
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-4-11
	* @user by chopin
	 */
	@Override
	public void insert(SysCyclePic entity){
		sysCyclePicMapper.insert(entity);
	};
	
	
	/**
	 * 
	* @Title: deleteSysCyclePicById 
	* @Description: 根据id删除SysCyclePic
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-4-11
	* @user by chopin
	 */
	 @Override
	public void deleteSysCyclePicById(Integer id){
		sysCyclePicMapper.deleteById(id);
	};
	
	
	/**
	 * 
	* @Title: batchInsert
	* @Description: 批量SysCyclePic
	* @param list
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-4-11
	* @user by chopin
	 */
	@Override
	public void batchInsert(List<SysCyclePic> list) {
		sysCyclePicMapper.batchInsert(list);
	}

	/**
	 * 
	* @Title: update
	* @Description: 修改SysCyclePic
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-4-11
	* @user by chopin
	 */
	@Override
	public void update(SysCyclePic entity) {
		sysCyclePicMapper.update(entity);
	}
	

	
	/**
	 * 
	* @Title: deleteSysCyclePicByIds 
	* @Description: 根据id批量删除SysCyclePic
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-4-11
	* @user by chopin
	 */
	@Override
	public void deleteSysCyclePicByIds(Integer[] ids){
		sysCyclePicMapper.deleteByIds(ids);
	};
	
	/**
	 * 
	* @Title: findSysCyclePicById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-4-11
	* @user by chopin
	 */
	@Override
	public SysCyclePic findSysCyclePicById(Integer id){
		return sysCyclePicMapper.findById(id);
	};
	
	/**
	 * 
	* @Title: findSysCyclePicByPage 
	* @Description: 分页查询
	* @return
	* @return List<SysCyclePic>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-4-11
	* @user by chopin
	 */
	@Override
	public List<SysCyclePic> findSysCyclePicByPage(SysCyclePic search){
		return sysCyclePicMapper.page(search);
	}

	/**
	 * 
	* @Title: findSysCyclePic 
	* @Description: 条件查询
	* @return
	* @return List<SysCyclePic>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-4-13
	* @user by chopin
	 */
	@Override
	public List<SysCyclePic> findSysCyclePic(SysCyclePic sysCyclePic) {
		return sysCyclePicMapper.querySysCycles(sysCyclePic);
	}


	@Override
	public void addSysCyclePic(SysCyclePic sysCyclePic) {
		sysCyclePicMapper.addCyclePics(sysCyclePic);
	}


	@Override
	public void updateCycles(SysCyclePic sysCyclePic) {
		sysCyclePicMapper.updateCyclePic(sysCyclePic);
	}


	@Override
	public void updateSort(SysCyclePic entity) {
		sysCyclePicMapper.updateSort(entity);
	}


	@Override
	public int selectCount(Integer companyId) {
		return sysCyclePicMapper.selectCount(companyId);
	}


	@Override
	public List<SysCyclePic> findByCompanyId(Integer companyId) {
		// TODO Auto-generated method stub
		return sysCyclePicMapper.findByCompanyId(companyId);
	}

}
