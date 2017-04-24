package com.yuxin.wx.system.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;import com.yuxin.wx.common.BaseServiceImpl;

import com.yuxin.wx.api.system.ISysServiceDredgeConfigService;
import com.yuxin.wx.model.system.SysServiceDredgeConfig;
import com.yuxin.wx.system.mapper.SysServiceDredgeConfigMapper;
import com.yuxin.wx.vo.system.SysServiceDredgeVo;

/**
 * Service Implementation:SysServiceDredgeConfig
 * @author wang.zx
 * @date 2016-12-12
 */
@Service
@Transactional
public class SysServiceDredgeConfigServiceImpl extends BaseServiceImpl implements ISysServiceDredgeConfigService {

	@Autowired
	private SysServiceDredgeConfigMapper sysServiceDredgeConfigMapper;
	
	/**
	 * 
	* @Title: saveSysServiceDredgeConfig
	* @Description: 新增SysServiceDredgeConfig
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-12-12
	* @user by chopin
	 */
	@Override
	public void insert(SysServiceDredgeConfig entity){
		sysServiceDredgeConfigMapper.insert(entity);
	};
	
	/**
	 * 
	* @Title: batchSaveSysServiceDredgeConfig 
	* @Description: 批量新增SysServiceDredgeConfig
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-12-12
	* @user by chopin
	 */
	@Override
	public void batchInsert(List<SysServiceDredgeConfig> T){
		sysServiceDredgeConfigMapper.batchInsert(T);
	};
	
	/**
	 * 
	* @Title: updateSysServiceDredgeConfig 
	* @Description: 编辑SysServiceDredgeConfig
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-12-12
	* @user by chopin
	 */
	@Override
	public void update(SysServiceDredgeConfig T){
		sysServiceDredgeConfigMapper.update(T);
	};
	
	/**
	 * 
	* @Title: deleteSysServiceDredgeConfigById 
	* @Description: 根据id删除SysServiceDredgeConfig
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-12-12
	* @user by chopin
	 */
	 @Override
	public void deleteSysServiceDredgeConfigById(Integer id){
		sysServiceDredgeConfigMapper.deleteById(id);
	};
	
	/**
	 * 
	* @Title: deleteSysServiceDredgeConfigByIds 
	* @Description: 根据id批量删除SysServiceDredgeConfig
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-12-12
	* @user by chopin
	 */
	@Override
	public void deleteSysServiceDredgeConfigByIds(Integer[] ids){
		sysServiceDredgeConfigMapper.deleteByIds(ids);
	};
	
	/**
	 * 
	* @Title: findSysServiceDredgeConfigById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-12-12
	* @user by chopin
	 */
	@Override
	public SysServiceDredgeConfig findSysServiceDredgeConfigById(Integer id){
		return sysServiceDredgeConfigMapper.findById(id);
	};
	
	/**
	 * 
	* @Title: findSysServiceDredgeConfigByPage 
	* @Description: 分页查询
	* @return
	* @return List<SysServiceDredgeConfig>    返回类型 
	* @throws 
	* @exception 
	* @date 2016-12-12
	* @user by chopin
	 */
	@Override
	public List<SysServiceDredgeConfig> findSysServiceDredgeConfigByPage(SysServiceDredgeConfig search){
		return sysServiceDredgeConfigMapper.page(search);
	}

	@Override
	public List<SysServiceDredgeVo> findDredgeByCom(Integer companyId) {
		// TODO Auto-generated method stub
		return sysServiceDredgeConfigMapper.findDredgeByCom(companyId);
	};
}
