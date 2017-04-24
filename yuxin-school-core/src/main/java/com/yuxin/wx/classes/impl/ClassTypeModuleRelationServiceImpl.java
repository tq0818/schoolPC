package com.yuxin.wx.classes.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;import com.yuxin.wx.common.BaseServiceImpl;

import com.yuxin.wx.api.classes.IClassTypeModuleRelationService;
import com.yuxin.wx.classes.mapper.ClassTypeModuleRelationMapper;
import com.yuxin.wx.model.classes.ClassType;
import com.yuxin.wx.model.classes.ClassTypeModuleRelation;
import com.yuxin.wx.vo.classes.ClassTypeModuleRelationVo;

/**
 * Service Implementation:ClassTypeModuleRelation
 * @author wang.zx
 * @date 2014-12-5
 */
@Service
@Transactional
public class ClassTypeModuleRelationServiceImpl extends BaseServiceImpl implements IClassTypeModuleRelationService {

	@Autowired
	private ClassTypeModuleRelationMapper classTypeModuleRelationMapper;
	
	/**
	 * 
	* @Title: saveClassTypeModuleRelation
	* @Description: 新增ClassTypeModuleRelation
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	@Override
	public void insert(ClassTypeModuleRelation classTypeModuleRelation){
		classTypeModuleRelationMapper.insert(classTypeModuleRelation);
	}
	
	/**
	 * 
	* @Title: batchSaveClassTypeModuleRelation 
	* @Description: 批量新增ClassTypeModuleRelation
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	@Override
	public void batchInsert(List<ClassTypeModuleRelation> classTypeModuleRelations){
		if(classTypeModuleRelations != null && !classTypeModuleRelations.isEmpty()){
			classTypeModuleRelationMapper.batchInsert(classTypeModuleRelations);
		}
	}
	
	/**
	 * 
	* @Title: updateClassTypeModuleRelation 
	* @Description: 编辑ClassTypeModuleRelation
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	@Override
	public void update(ClassTypeModuleRelation classTypeModuleRelation){
		classTypeModuleRelationMapper.update(classTypeModuleRelation);
	}
	
	/**
	 * 
	* @Title: deleteClassTypeModuleRelationById 
	* @Description: 根据id删除ClassTypeModuleRelation
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	@Override
	public void deleteClassTypeModuleRelationById(Integer id){
		classTypeModuleRelationMapper.deleteById(id);
	}
	
	/**
	 * 
	* @Title: deleteClassTypeModuleRelationByIds 
	* @Description: 根据id批量删除ClassTypeModuleRelation
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	@Override
	public void deleteClassTypeModuleRelationByIds(Integer[] ids){
		classTypeModuleRelationMapper.deleteByIds(ids);
	}
	
	/**
	 * 
	* @Title: findClassTypeModuleRelationById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	@Override
	public ClassTypeModuleRelation findClassTypeModuleRelationById(Integer id){
		return classTypeModuleRelationMapper.findById(id);
	}
	
	/**
	 * 
	* @Title: findClassTypeModuleRelationByPage 
	* @Description: 分页查询
	* @return
	* @return List<ClassTypeModuleRelation>    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	@Override
	public List<ClassTypeModuleRelation> findClassTypeModuleRelationByPage(ClassTypeModuleRelation search){
		Integer totalRecords = classTypeModuleRelationMapper.pageCount(search);
		search.setTotalRecords(totalRecords);
		return classTypeModuleRelationMapper.page(search);
	}

	@Override
	public void deleteByClassTypeId(String classTypeId) {
		classTypeModuleRelationMapper.deleteByClassTypeId(classTypeId);
	}

	@Override
	public List<Integer> findModelIdByClassTypeId(List<ClassType> classTypes) {
		// TODO Auto-generated method stub
		return classTypeModuleRelationMapper.findModelIdByClassTypeId(classTypes);
	}

	@Override
	public void updateModuleByClassTypeId(ClassTypeModuleRelation classTypeModuleRelation) {
		classTypeModuleRelationMapper.updateModuleByClassTypeId(classTypeModuleRelation);
	}
	
	@Override
	public List<ClassTypeModuleRelationVo> findClassModuleRelationByClassTypeId(
			HashMap map) {
		return classTypeModuleRelationMapper.findClassModuleRelationByClassTypeId(map);
	}
	
	@Override
	public List<Integer> findClassModuleIdsByClassTypeId(Integer id) {
		return classTypeModuleRelationMapper.findClassModuleIdsByClassTypeId(id);
	}
}
