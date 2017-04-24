package com.yuxin.wx.classes.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;import com.yuxin.wx.common.BaseServiceImpl;

import com.yuxin.wx.api.classes.IClassPackageCategoryService;
import com.yuxin.wx.classes.mapper.ClassPackageCategoryMapper;
import com.yuxin.wx.classes.mapper.ClassPackageMapper;
import com.yuxin.wx.model.classes.ClassPackage;
import com.yuxin.wx.model.classes.ClassPackageCategory;

/**
 * Service Implementation:ClassPackageCategory
 * @author chopin
 * @date 2016-3-21
 */
@Service
@Transactional
public class ClassPackageCategoryServiceImpl extends BaseServiceImpl implements IClassPackageCategoryService {

	@Autowired
	private ClassPackageCategoryMapper classPackageCategoryMapper;
	@Autowired
	private ClassPackageMapper classPackageMapper;
	
	/**
	 * 
	* @Title: saveClassPackageCategory
	* @Description: 新增ClassPackageCategory
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-3-21
	* @user by chopin
	 */
	@Override
	public void insert(ClassPackageCategory entity){
		classPackageCategoryMapper.insert(entity);
	};
	
	/**
	 * 
	* @Title: batchSaveClassPackageCategory 
	* @Description: 批量新增ClassPackageCategory
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-3-21
	* @user by chopin
	 */
	@Override
	public void batchInsert(List<ClassPackageCategory> entity){
		classPackageCategoryMapper.batchInsert(entity);
	};
	
	/**
	 * 
	* @Title: updateClassPackageCategory 
	* @Description: 编辑ClassPackageCategory
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-3-21
	* @user by chopin
	 */
	@Override
	public void update(ClassPackageCategory entity){
		classPackageCategoryMapper.update(entity);
	};
	
	/**
	 * 
	* @Title: deleteClassPackageCategoryById 
	* @Description: 根据id删除ClassPackageCategory
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-3-21
	* @user by chopin
	 */
	 @Override
	public void deleteClassPackageCategoryById(Integer id){
		classPackageCategoryMapper.deleteById(id);
	};
	
	/**
	 * 
	* @Title: deleteClassPackageCategoryByIds 
	* @Description: 根据id批量删除ClassPackageCategory
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-3-21
	* @user by chopin
	 */
	@Override
	public void deleteClassPackageCategoryByIds(Integer[] ids){
		classPackageCategoryMapper.deleteByIds(ids);
	};
	
	/**
	 * 
	* @Title: findClassPackageCategoryById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-3-21
	* @user by chopin
	 */
	@Override
	public ClassPackageCategory findClassPackageCategoryById(Integer id){
		return classPackageCategoryMapper.findById(id);
	};
	
	/**
	 * 
	* @Title: findClassPackageCategoryByPage 
	* @Description: 分页查询
	* @return
	* @return List<ClassPackageCategory>    返回类型 
	* @throws 
	* @exception 
	* @date 2016-3-21
	* @user by chopin
	 */
	@Override
	public List<ClassPackageCategory> findClassPackageCategoryByPage(ClassPackageCategory search){
		return classPackageCategoryMapper.page(search);
	}

	@Override
	public List<ClassPackageCategory> queryClassCategoryLists(
			ClassPackageCategory search) {
		// TODO Auto-generated method stub
		return classPackageCategoryMapper.queryClassCategoryLists(search);
	}

	@Override
	public Integer queryMaxIdByCondition(ClassPackageCategory search) {
		// TODO Auto-generated method stub
		return classPackageCategoryMapper.queryMaxIdByCompanyId(search);
	}

	@Override
	public Integer queryIsExistClassPackage(ClassPackage search) {
		// TODO Auto-generated method stub
		return classPackageMapper.queryClassPackageCount(search);
	};
	@Override
	public List<ClassPackageCategory> findAll(Integer companyId){
		return classPackageCategoryMapper.queryAll(companyId);
	}

	@Override
	public List<ClassPackageCategory> queryClassCategoryCodeByWhere(
			ClassPackageCategory search) {
		// TODO Auto-generated method stub
		return classPackageCategoryMapper.queryClassCategoryCodeByWhere(search);
	}

	@Override
	public ClassPackageCategory queryClassPackageCategoryByCode(ClassPackageCategory search) {
		return classPackageCategoryMapper.queryClassPackageCategoryByCode(search);
	}
}
