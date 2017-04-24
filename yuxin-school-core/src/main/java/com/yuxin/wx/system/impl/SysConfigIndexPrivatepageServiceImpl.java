package com.yuxin.wx.system.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;import com.yuxin.wx.common.BaseServiceImpl;

import com.yuxin.wx.api.system.ISysConfigIndexPrivatepageService;
import com.yuxin.wx.model.system.SysConfigIndexPrivatepage;
import com.yuxin.wx.system.mapper.SysConfigIndexPrivatepageMapper;
import com.yuxin.wx.vo.system.SysConfigPrivatePageVo;

/**
 * Service Implementation:SysConfigIndexPrivatepage
 * @author chopin
 * @date 2015-3-17
 */
@Service
@Transactional
public class SysConfigIndexPrivatepageServiceImpl extends BaseServiceImpl implements ISysConfigIndexPrivatepageService {

	@Autowired
	private SysConfigIndexPrivatepageMapper sysConfigIndexPrivatepageMapper;
	
	/**
	 * 
	* @Title: saveSysConfigIndexPrivatepage
	* @Description: 新增SysConfigIndexPrivatepage
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-3-17
	* @user by chopin
	 */
	@Override
	public void insert(SysConfigIndexPrivatepage entity){
		sysConfigIndexPrivatepageMapper.insert(entity);
	};
	
	/**
	 * 
	* @Title: batchSaveSysConfigIndexPrivatepage 
	* @Description: 批量新增SysConfigIndexPrivatepage
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-3-17
	* @user by chopin
	 */
	@Override
	public void batchInsert(List<SysConfigIndexPrivatepage> entity){
		sysConfigIndexPrivatepageMapper.batchInsert(entity);
	};
	
	/**
	 * 
	* @Title: updateSysConfigIndexPrivatepage 
	* @Description: 编辑SysConfigIndexPrivatepage
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-3-17
	* @user by chopin
	 */
	@Override
	public void update(SysConfigIndexPrivatepage entity){
		sysConfigIndexPrivatepageMapper.update(entity);
	};
	
	/**
	 * 
	* @Title: deleteSysConfigIndexPrivatepageById 
	* @Description: 根据id删除SysConfigIndexPrivatepage
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-3-17
	* @user by chopin
	 */
	 @Override
	public void deleteSysConfigIndexPrivatepageById(Integer id){
		sysConfigIndexPrivatepageMapper.deleteById(id);
	};
	
	/**
	 * 
	* @Title: deleteSysConfigIndexPrivatepageByIds 
	* @Description: 根据id批量删除SysConfigIndexPrivatepage
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-3-17
	* @user by chopin
	 */
	@Override
	public void deleteSysConfigIndexPrivatepageByIds(Integer[] ids){
		sysConfigIndexPrivatepageMapper.deleteByIds(ids);
	};
	
	/**
	 * 
	* @Title: findSysConfigIndexPrivatepageById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-3-17
	* @user by chopin
	 */
	@Override
	public SysConfigIndexPrivatepage findSysConfigIndexPrivatepageById(Integer id){
		return sysConfigIndexPrivatepageMapper.findById(id);
	};
	
	/**
	 * 
	* @Title: findSysConfigIndexPrivatepageByPage 
	* @Description: 分页查询
	* @return
	* @return List<SysConfigIndexPrivatepage>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-3-17
	* @user by chopin
	 */
	@Override
	public List<SysConfigIndexPrivatepage> findSysConfigIndexPrivatepageByPage(SysConfigIndexPrivatepage search){
		return sysConfigIndexPrivatepageMapper.page(search);
	};
	/**
	 * 
	* @Title: findSysConfigIndexPrivatepageByPage 
	* @Description: 查询分校首页配置数据
	* @return
	* @return List<SysConfigIndexPrivatepage>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-3-17
	* @user by chopin
	 */
	@Override
	public List<SysConfigIndexPrivatepage> findSchoolConfigData(Integer schoolId){
		return sysConfigIndexPrivatepageMapper.findBySchoolId(schoolId);
	}
	
	/**
	 * 
	* @Title: findSysConfigIndexPrivatepageByPage 
	* @Description: 查询分校首页配置数据
	* @return
	* @return List<SysConfigIndexPrivatepage>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-3-17
	* @user by chopin
	 */
	@Override
	public List<SysConfigIndexPrivatepage> findAll(){
		return sysConfigIndexPrivatepageMapper.queryAll();
	}
	
	/**
	 * 
	* @Title: findSysConfigIndexPrivatepageByPage 
	* @Description: 查询分校首页配置数据
	* @return
	* @return List<SysConfigIndexPrivatepage>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-3-17
	* @user by chopin
	 */
	@Override
	public List<SysConfigPrivatePageVo> findList(SysConfigIndexPrivatepage search){
		return sysConfigIndexPrivatepageMapper.findList(search);
	}
	
	/**
	 * 
	* @Title: findSysConfigIndexPrivatepageByPage 
	* @Description: 查询分校首页配置数据
	* @return
	* @return List<SysConfigIndexPrivatepage>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-3-17
	* @user by chopin
	 */
	@Override
	public List<SysConfigIndexPrivatepage> findList2(SysConfigIndexPrivatepage search){
		return sysConfigIndexPrivatepageMapper.findList2(search);
	}
}
