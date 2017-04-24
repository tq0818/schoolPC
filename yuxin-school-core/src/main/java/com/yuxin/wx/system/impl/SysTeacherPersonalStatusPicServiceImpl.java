package com.yuxin.wx.system.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;import com.yuxin.wx.common.BaseServiceImpl;

import com.yuxin.wx.api.system.ISysTeacherPersonalStatusPicService;
import com.yuxin.wx.model.system.SysTeacherPersonalStatusPic;
import com.yuxin.wx.system.mapper.SysTeacherPersonalStatusPicMapper;

/**
 * Service Implementation:SysTeacherPersonalStatusPic
 * @author chopin
 * @date 2015-10-28
 */
@Service
@Transactional
public class SysTeacherPersonalStatusPicServiceImpl extends BaseServiceImpl implements ISysTeacherPersonalStatusPicService {

	@Autowired
	private SysTeacherPersonalStatusPicMapper sysTeacherPersonalStatusPicMapper;
	
	/**
	 * 
	* @Title: saveSysTeacherPersonalStatusPic
	* @Description: 新增SysTeacherPersonalStatusPic
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-10-28
	* @user by chopin
	 */
	@Override
	public void insert(SysTeacherPersonalStatusPic entity){
		sysTeacherPersonalStatusPicMapper.insert(entity);
	};
	
	/**
	 * 
	* @Title: batchSaveSysTeacherPersonalStatusPic 
	* @Description: 批量新增SysTeacherPersonalStatusPic
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-10-28
	* @user by chopin
	 */
	@Override
	public void batchInsert(List<SysTeacherPersonalStatusPic> entity){
		sysTeacherPersonalStatusPicMapper.batchInsert(entity);
	};
	
	/**
	 * 
	* @Title: updateSysTeacherPersonalStatusPic 
	* @Description: 编辑SysTeacherPersonalStatusPic
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-10-28
	* @user by chopin
	 */
	@Override
	public void update(SysTeacherPersonalStatusPic entity){
		sysTeacherPersonalStatusPicMapper.update(entity);
	};
	
	/**
	 * 
	* @Title: deleteSysTeacherPersonalStatusPicById 
	* @Description: 根据id删除SysTeacherPersonalStatusPic
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-10-28
	* @user by chopin
	 */
	 @Override
	public void deleteSysTeacherPersonalStatusPicById(Integer id){
		sysTeacherPersonalStatusPicMapper.deleteById(id);
	};
	
	/**
	 * 
	* @Title: deleteSysTeacherPersonalStatusPicByIds 
	* @Description: 根据id批量删除SysTeacherPersonalStatusPic
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-10-28
	* @user by chopin
	 */
	@Override
	public void deleteSysTeacherPersonalStatusPicByIds(Integer[] ids){
		sysTeacherPersonalStatusPicMapper.deleteByIds(ids);
	};
	
	/**
	 * 
	* @Title: findSysTeacherPersonalStatusPicById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-10-28
	* @user by chopin
	 */
	@Override
	public SysTeacherPersonalStatusPic findSysTeacherPersonalStatusPicById(Integer id){
		return sysTeacherPersonalStatusPicMapper.findById(id);
	};
	
	/**
	 * 
	* @Title: findSysTeacherPersonalStatusPicByPage 
	* @Description: 分页查询
	* @return
	* @return List<SysTeacherPersonalStatusPic>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-10-28
	* @user by chopin
	 */
	@Override
	public List<SysTeacherPersonalStatusPic> findSysTeacherPersonalStatusPicByPage(SysTeacherPersonalStatusPic search){
		return sysTeacherPersonalStatusPicMapper.page(search);
	};
}
