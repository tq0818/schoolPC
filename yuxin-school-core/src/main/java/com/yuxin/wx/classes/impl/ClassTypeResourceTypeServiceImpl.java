package com.yuxin.wx.classes.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;import com.yuxin.wx.common.BaseServiceImpl;

import com.yuxin.wx.api.classes.IClassTypeResourceTypeService;
import com.yuxin.wx.classes.mapper.ClassTypeResourceTypeMapper;
import com.yuxin.wx.model.classes.ClassTypeResourceType;

/**
 * Service Implementation:ClassTypeResourceType
 * @author wang.zx
 * @date 2015-8-11
 */
@Service
@Transactional
public class ClassTypeResourceTypeServiceImpl extends BaseServiceImpl implements IClassTypeResourceTypeService {

	@Autowired
	private ClassTypeResourceTypeMapper classTypeResourceTypeMapper;
	
	/**
	 * 
	* @Title: saveClassTypeResourceType
	* @Description: 新增ClassTypeResourceType
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-8-11
	* @user by chopin
	 */
	@Override
	public void insert(ClassTypeResourceType entity){
		classTypeResourceTypeMapper.insert(entity);
	};
	
	/**
	 * 
	* @Title: batchSaveClassTypeResourceType 
	* @Description: 批量新增ClassTypeResourceType
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-8-11
	* @user by chopin
	 */
	@Override
	public void batchInsert(List<ClassTypeResourceType> entity){
		classTypeResourceTypeMapper.batchInsert(entity);
	};
	
	/**
	 * 
	* @Title: updateClassTypeResourceType 
	* @Description: 编辑ClassTypeResourceType
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-8-11
	* @user by chopin
	 */
	@Override
	public void update(ClassTypeResourceType entity){
		classTypeResourceTypeMapper.update(entity);
	};
	
	/**
	 * 
	* @Title: deleteClassTypeResourceTypeById 
	* @Description: 根据id删除ClassTypeResourceType
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-8-11
	* @user by chopin
	 */
	 @Override
	public void deleteClassTypeResourceTypeById(Integer id){
		classTypeResourceTypeMapper.deleteById(id);
	};
	
	/**
	 * 
	* @Title: deleteClassTypeResourceTypeByIds 
	* @Description: 根据id批量删除ClassTypeResourceType
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-8-11
	* @user by chopin
	 */
	@Override
	public void deleteClassTypeResourceTypeByIds(Integer[] ids){
		classTypeResourceTypeMapper.deleteByIds(ids);
	};
	
	/**
	 * 
	* @Title: findClassTypeResourceTypeById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-8-11
	* @user by chopin
	 */
	@Override
	public ClassTypeResourceType findClassTypeResourceTypeById(Integer id){
		return classTypeResourceTypeMapper.findById(id);
	};
	
	/**
	 * 
	* @Title: findClassTypeResourceTypeByPage 
	* @Description: 分页查询
	* @return
	* @return List<ClassTypeResourceType>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-8-11
	* @user by chopin
	 */
	@Override
	public List<ClassTypeResourceType> findClassTypeResourceTypeByPage(ClassTypeResourceType search){
		return classTypeResourceTypeMapper.page(search);
	}

	@Override
	public List<ClassTypeResourceType> findResourceTypeByCompanpyId(
			Integer companyId) {
		// TODO Auto-generated method stub
		return classTypeResourceTypeMapper.findResourceTypeByCompanpyId(companyId);
	}

	@Override
	public ClassTypeResourceType findResourceTypeBy(ClassTypeResourceType ctrt) {
		// TODO Auto-generated method stub
		return classTypeResourceTypeMapper.findResourceTypeBy(ctrt);
	};
}
