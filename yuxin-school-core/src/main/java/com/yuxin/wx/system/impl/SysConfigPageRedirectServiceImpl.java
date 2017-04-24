package com.yuxin.wx.system.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;import com.yuxin.wx.common.BaseServiceImpl;

import com.yuxin.wx.api.system.ISysConfigPageRedirectService;
import com.yuxin.wx.model.system.SysConfigPageRedirect;
import com.yuxin.wx.system.mapper.SysConfigPageRedirectMapper;

/**
 * Service Implementation:SysConfigPageRedirect
 * @author wang.zx
 * @date 2015-10-9
 */
@Service
@Transactional
public class SysConfigPageRedirectServiceImpl extends BaseServiceImpl implements ISysConfigPageRedirectService {

	@Autowired
	private SysConfigPageRedirectMapper sysConfigPageRedirectMapper;
	
	/**
	 * 
	* @Title: saveSysConfigPageRedirect
	* @Description: 新增SysConfigPageRedirect
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-10-9
	* @user by chopin
	 */
	@Override
	public void insert(SysConfigPageRedirect entity){
		sysConfigPageRedirectMapper.insert(entity);
	};
	
	/**
	 * 
	* @Title: batchSaveSysConfigPageRedirect 
	* @Description: 批量新增SysConfigPageRedirect
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-10-9
	* @user by chopin
	 */
	@Override
	public void batchInsert(List<SysConfigPageRedirect> T){
		sysConfigPageRedirectMapper.batchInsert(T);
	};
	
	/**
	 * 
	* @Title: updateSysConfigPageRedirect 
	* @Description: 编辑SysConfigPageRedirect
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-10-9
	* @user by chopin
	 */
	@Override
	public void update(SysConfigPageRedirect T){
		sysConfigPageRedirectMapper.update(T);
	};
	
	/**
	 * 
	* @Title: deleteSysConfigPageRedirectById 
	* @Description: 根据id删除SysConfigPageRedirect
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-10-9
	* @user by chopin
	 */
	 @Override
	public void deleteSysConfigPageRedirectById(Integer id){
		sysConfigPageRedirectMapper.deleteById(id);
	};
	
	/**
	 * 
	* @Title: deleteSysConfigPageRedirectByIds 
	* @Description: 根据id批量删除SysConfigPageRedirect
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-10-9
	* @user by chopin
	 */
	@Override
	public void deleteSysConfigPageRedirectByIds(Integer[] ids){
		sysConfigPageRedirectMapper.deleteByIds(ids);
	};
	
	/**
	 * 
	* @Title: findSysConfigPageRedirectById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-10-9
	* @user by chopin
	 */
	@Override
	public SysConfigPageRedirect findSysConfigPageRedirectById(Integer id){
		return sysConfigPageRedirectMapper.findById(id);
	};
	
	/**
	 * 
	* @Title: findSysConfigPageRedirectByPage 
	* @Description: 分页查询
	* @return
	* @return List<SysConfigPageRedirect>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-10-9
	* @user by chopin
	 */
	@Override
	public List<SysConfigPageRedirect> findSysConfigPageRedirectByPage(SysConfigPageRedirect search){
		return sysConfigPageRedirectMapper.page(search);
	}

	@Override
	public SysConfigPageRedirect findPageRedirect(SysConfigPageRedirect scpr) {
		// TODO Auto-generated method stub
		return sysConfigPageRedirectMapper.findPageRedirect(scpr);
	}
	/**
	 * 
	* @Title: findSysConfigPageRedirectByPage 
	* @Description: 根据公司ID和模板ID 更新主题
	* @return
	* @return List<SysConfigPageRedirect>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-12-3
	* @user by chopin
	 */
	public void updateByTempleteId(SysConfigPageRedirect redirect){
		sysConfigPageRedirectMapper.updateByTempleteId(redirect);
	}

	@Override
	public List<SysConfigPageRedirect> findBySearch(SysConfigPageRedirect scpr) {
		return sysConfigPageRedirectMapper.findBySearch(scpr);
	}

}
