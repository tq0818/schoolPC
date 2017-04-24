package com.yuxin.wx.classes.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;import com.yuxin.wx.common.BaseServiceImpl;

import com.yuxin.wx.api.classes.IClassTypeResourceService;
import com.yuxin.wx.classes.mapper.ClassTypeResourceMapper;
import com.yuxin.wx.model.classes.ClassTypeResource;
import com.yuxin.wx.vo.classes.ClassTypeResourceVo;

/**
 * Service Implementation:ClassTypeResource
 * @author wang.zx
 * @date 2015-8-11
 */
@Service
@Transactional
public class ClassTypeResourceServiceImpl extends BaseServiceImpl implements IClassTypeResourceService {

	@Autowired
	private ClassTypeResourceMapper classTypeResourceMapper;
	
	/**
	 * 
	* @Title: saveClassTypeResource
	* @Description: 新增ClassTypeResource
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-8-11
	* @user by chopin
	 */
	@Override
	public void insert(ClassTypeResource entity){
		classTypeResourceMapper.insert(entity);
	};
	
	/**
	 * 
	* @Title: batchSaveClassTypeResource 
	* @Description: 批量新增ClassTypeResource
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-8-11
	* @user by chopin
	 */
	@Override
	public void batchInsert(List<ClassTypeResource> entity){
		classTypeResourceMapper.batchInsert(entity);
	};
	
	/**
	 * 
	* @Title: updateClassTypeResource 
	* @Description: 编辑ClassTypeResource
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-8-11
	* @user by chopin
	 */
	@Override
	public void update(ClassTypeResource entity){
		classTypeResourceMapper.update(entity);
	};
	
	/**
	 * 
	* @Title: deleteClassTypeResourceById 
	* @Description: 根据id删除ClassTypeResource
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-8-11
	* @user by chopin
	 */
	 @Override
	public void deleteClassTypeResourceById(Integer id){
		classTypeResourceMapper.deleteById(id);
	};
	
	/**
	 * 
	* @Title: deleteClassTypeResourceByIds 
	* @Description: 根据id批量删除ClassTypeResource
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-8-11
	* @user by chopin
	 */
	@Override
	public void deleteClassTypeResourceByIds(Integer[] ids){
		classTypeResourceMapper.deleteByIds(ids);
	};
	
	/**
	 * 
	* @Title: findClassTypeResourceById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-8-11
	* @user by chopin
	 */
	@Override
	public ClassTypeResource findClassTypeResourceById(Integer id){
		return classTypeResourceMapper.findById(id);
	};
	
	/**
	 * 
	* @Title: findClassTypeResourceByPage 
	* @Description: 分页查询
	* @return
	* @return List<ClassTypeResource>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-8-11
	* @user by chopin
	 */
	@Override
	public List<ClassTypeResource> findClassTypeResourceByPage(ClassTypeResource search){
		return classTypeResourceMapper.page(search);
	}

	@Override
	public List<ClassTypeResourceVo> findResBy(ClassTypeResource res) {
		// TODO Auto-generated method stub
		return classTypeResourceMapper.findResBy(res);
	}

	@Override
	public Integer findResCountBy(ClassTypeResource res) {
		// TODO Auto-generated method stub
		return classTypeResourceMapper.findResCountBy(res);
	}

	@Override
	public List<ClassTypeResourceVo> findResByCondition(ClassTypeResource res) {
		// TODO Auto-generated method stub
		return classTypeResourceMapper.findResByCondition(res);
	};
}
