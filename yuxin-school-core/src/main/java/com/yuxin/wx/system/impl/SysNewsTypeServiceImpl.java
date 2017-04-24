package com.yuxin.wx.system.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;import com.yuxin.wx.common.BaseServiceImpl;

import com.yuxin.wx.api.system.ISysNewsTypeService;
import com.yuxin.wx.model.system.SysNewsType;
import com.yuxin.wx.system.mapper.SysNewsTypeMapper;
import com.yuxin.wx.vo.system.SysNewsTypeVo;


/**
 * Service Implementation:SysNewsType
 * @author chopin
 * @date 2015-12-3
 */
@Service
@Transactional
public class SysNewsTypeServiceImpl extends BaseServiceImpl implements ISysNewsTypeService {

	@Autowired
	private SysNewsTypeMapper sysNewsTypeMapper;
	
	/**
	 * 
	* @Title: saveSysNewsType
	* @Description: 新增SysNewsType
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-12-3
	* @user by chopin
	 */
	@Override
	public void insert(SysNewsType entity){
		sysNewsTypeMapper.insert(entity);
	};
	
	/**
	 * 
	* @Title: batchSaveSysNewsType 
	* @Description: 批量新增SysNewsType
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-12-3
	* @user by chopin
	 */
	@Override
	public void batchInsert(List<SysNewsType> entity){
		sysNewsTypeMapper.batchInsert(entity);
	};
	
	/**
	 * 
	* @Title: updateSysNewsType 
	* @Description: 编辑SysNewsType
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-12-3
	* @user by chopin
	 */
	@Override
	public void update(SysNewsType entity){
		sysNewsTypeMapper.update(entity);
	};
	
	/**
	 * 
	* @Title: deleteSysNewsTypeById 
	* @Description: 根据id删除SysNewsType
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-12-3
	* @user by chopin
	 */
	 @Override
	public void deleteSysNewsTypeById(Integer id){
		sysNewsTypeMapper.deleteById(id);
	};
	
	/**
	 * 
	* @Title: deleteSysNewsTypeByIds 
	* @Description: 根据id批量删除SysNewsType
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-12-3
	* @user by chopin
	 */
	@Override
	public void deleteSysNewsTypeByIds(Integer[] ids){
		sysNewsTypeMapper.deleteByIds(ids);
	};
	
	/**
	 * 
	* @Title: findSysNewsTypeById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-12-3
	* @user by chopin
	 */
	@Override
	public SysNewsType findSysNewsTypeById(Integer id){
		return sysNewsTypeMapper.findById(id);
	};
	
	/**
	 * 
	* @Title: findSysNewsTypeByPage 
	* @Description: 分页查询
	* @return
	* @return List<SysNewsType>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-12-3
	* @user by chopin
	 */
	@Override
	public List<SysNewsType> findSysNewsTypeByPage(SysNewsType search){
		return sysNewsTypeMapper.page(search);
	}

	@Override
	public SysNewsType findByName(SysNewsType sysNewsType) {
		return sysNewsTypeMapper.findByName(sysNewsType);
	}

	@Override
	public List<SysNewsTypeVo> queryNewsType(SysNewsType sysNewsType) {
		return sysNewsTypeMapper.queryNewsType(sysNewsType);
	};
}
