package com.yuxin.wx.system.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;import com.yuxin.wx.common.BaseServiceImpl;

import com.yuxin.wx.api.system.ISysConfigItemCourseService;
import com.yuxin.wx.model.system.SysConfigItemCourse;
import com.yuxin.wx.system.mapper.SysConfigItemCourseMapper;

/**
 * Service Implementation:SysConfigItemCourse
 * @author wang.zx
 * @date 2015-9-23
 */
@Service
@Transactional
public class SysConfigItemCourseServiceImpl extends BaseServiceImpl implements ISysConfigItemCourseService {

	@Autowired
	private SysConfigItemCourseMapper sysConfigItemCourseMapper;
	
	/**
	 * 
	* @Title: saveSysConfigItemCourse
	* @Description: 新增SysConfigItemCourse
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-9-23
	* @user by chopin
	 */
	@Override
	public void insert(SysConfigItemCourse entity){
		sysConfigItemCourseMapper.insert(entity);
	};
	
	/**
	 * 
	* @Title: batchSaveSysConfigItemCourse 
	* @Description: 批量新增SysConfigItemCourse
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-9-23
	* @user by chopin
	 */
	@Override
	public void batchInsert(List<SysConfigItemCourse> T){
		sysConfigItemCourseMapper.batchInsert(T);
	};
	
	/**
	 * 
	* @Title: updateSysConfigItemCourse 
	* @Description: 编辑SysConfigItemCourse
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-9-23
	* @user by chopin
	 */
	@Override
	public void update(SysConfigItemCourse T){
		sysConfigItemCourseMapper.update(T);
	}
	
	/**
	 * 
	* @Title: deleteSysConfigItemCourseById 
	* @Description: 根据id删除SysConfigItemCourse
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-9-23
	* @user by chopin
	 */
	 @Override
	public void deleteSysConfigItemCourseById(Integer id){
		sysConfigItemCourseMapper.deleteById(id);
	};
	
	/**
	 * 
	* @Title: deleteSysConfigItemCourseByIds 
	* @Description: 根据id批量删除SysConfigItemCourse
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-9-23
	* @user by chopin
	 */
	@Override
	public void deleteSysConfigItemCourseByIds(Integer[] ids){
		sysConfigItemCourseMapper.deleteByIds(ids);
	};
	
	/**
	 * 
	* @Title: findSysConfigItemCourseById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-9-23
	* @user by chopin
	 */
	@Override
	public SysConfigItemCourse findSysConfigItemCourseById(Integer id){
		return sysConfigItemCourseMapper.findById(id);
	};
	
	/**
	 * 
	* @Title: findSysConfigItemCourseByPage 
	* @Description: 分页查询
	* @return
	* @return List<SysConfigItemCourse>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-9-23
	* @user by chopin
	 */
	@Override
	public List<SysConfigItemCourse> findSysConfigItemCourseByPage(SysConfigItemCourse search){
		return sysConfigItemCourseMapper.page(search);
	}

	@Override
	public List<SysConfigItemCourse> findSysConfigItemCourse(
			SysConfigItemCourse search) {
		// TODO Auto-generated method stub
		return sysConfigItemCourseMapper.findSysConfigItemCourse(search);
	}

	@Override
	public void updateByItemId(SysConfigItemCourse entity) {
		// TODO Auto-generated method stub
		sysConfigItemCourseMapper.updateByItemId(entity);
	}

	@Override
	public SysConfigItemCourse findByItemIdAndComId(SysConfigItemCourse search) {
		// TODO Auto-generated method stub
		return sysConfigItemCourseMapper.findByItemIdAndComId(search);
	}

	@Override
	public void updateStatus(SysConfigItemCourse scic) {
		// TODO Auto-generated method stub
		sysConfigItemCourseMapper.updateStatus(scic);
	}

	@Override
	public List<SysConfigItemCourse> findByItem(SysConfigItemCourse search) {
		// TODO Auto-generated method stub
		return sysConfigItemCourseMapper.findByItem(search);
	};
}
