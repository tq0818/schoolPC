package com.yuxin.wx.classes.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;import com.yuxin.wx.common.BaseServiceImpl;

import com.yuxin.wx.api.classes.IClassPackageRelationService;
import com.yuxin.wx.classes.mapper.ClassPackageRelationMapper;
import com.yuxin.wx.model.classes.ClassPackageRelation;

/**
 * Service Implementation:ClassPackageRelation
 * @author chopin
 * @date 2016-3-21
 */
@Service
@Transactional
public class ClassPackageRelationServiceImpl extends BaseServiceImpl implements IClassPackageRelationService {

	@Autowired
	private ClassPackageRelationMapper classPackageRelationMapper;
	
	/**
	 * 
	* @Title: saveClassPackageRelation
	* @Description: 新增ClassPackageRelation
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-3-21
	* @user by chopin
	 */
	@Override
	public void insert(ClassPackageRelation entity){
		classPackageRelationMapper.insert(entity);
	};
	
	/**
	 * 
	* @Title: batchSaveClassPackageRelation 
	* @Description: 批量新增ClassPackageRelation
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-3-21
	* @user by chopin
	 */
	@Override
	public void batchInsert(List<ClassPackageRelation> entity){
		classPackageRelationMapper.batchInsert(entity);
	};
	
	/**
	 * 
	* @Title: updateClassPackageRelation 
	* @Description: 编辑ClassPackageRelation
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-3-21
	* @user by chopin
	 */
	@Override
	public void update(ClassPackageRelation entity){
		classPackageRelationMapper.update(entity);
	};
	
	/**
	 * 
	* @Title: deleteClassPackageRelationById 
	* @Description: 根据id删除ClassPackageRelation
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-3-21
	* @user by chopin
	 */
	 @Override
	public void deleteClassPackageRelationById(Integer id){
		classPackageRelationMapper.deleteById(id);
	};
	
	/**
	 * 
	* @Title: deleteClassPackageRelationByIds 
	* @Description: 根据id批量删除ClassPackageRelation
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-3-21
	* @user by chopin
	 */
	@Override
	public void deleteClassPackageRelationByIds(Integer[] ids){
		classPackageRelationMapper.deleteByIds(ids);
	};
	
	/**
	 * 
	* @Title: findClassPackageRelationById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-3-21
	* @user by chopin
	 */
	@Override
	public ClassPackageRelation findClassPackageRelationById(Integer id){
		return classPackageRelationMapper.findById(id);
	};
	
	/**
	 * 
	* @Title: findClassPackageRelationByPage 
	* @Description: 分页查询
	* @return
	* @return List<ClassPackageRelation>    返回类型 
	* @throws 
	* @exception 
	* @date 2016-3-21
	* @user by chopin
	 */
	@Override
	public List<ClassPackageRelation> findClassPackageRelationByPage(ClassPackageRelation search){
		return classPackageRelationMapper.page(search);
	}

	@Override
	public List<ClassPackageRelation> queryClassPackageStageRelation(
			ClassPackageRelation search) {
		// TODO Auto-generated method stub
		return classPackageRelationMapper.queryClassPackageStageRelation(search);
	}

	@Override
	public List<ClassPackageRelation> findClassPackageRelations(
			ClassPackageRelation search) {
		// TODO Auto-generated method stub
		return classPackageRelationMapper.findClassPackageRelations(search);
	};
}
