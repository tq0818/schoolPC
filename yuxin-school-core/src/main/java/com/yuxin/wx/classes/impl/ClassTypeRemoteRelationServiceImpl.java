package com.yuxin.wx.classes.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;import com.yuxin.wx.common.BaseServiceImpl;

import java.util.List;

import com.yuxin.wx.api.classes.IClassTypeRemoteRelationService;
import com.yuxin.wx.model.classes.ClassTypeRemoteRelation;
import com.yuxin.wx.vo.classes.ModuleRemoteVo;
import com.yuxin.wx.classes.mapper.ClassTypeRemoteRelationMapper;

/**
 * Service Implementation:ClassTypeRemoteRelation
 * @author wang.zx
 * @date 2014-12-5
 */
@Service
@Transactional
public class ClassTypeRemoteRelationServiceImpl extends BaseServiceImpl implements IClassTypeRemoteRelationService {

	@Autowired
	private ClassTypeRemoteRelationMapper classTypeRemoteRelationMapper;
	
	/**
	 * 
	* @Title: saveClassTypeRemoteRelation
	* @Description: 新增ClassTypeRemoteRelation
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	@Override
	public void insert(ClassTypeRemoteRelation classTypeRemoteRelation){
		classTypeRemoteRelationMapper.insert(classTypeRemoteRelation);
	}
	
	/**
	 * 
	* @Title: batchSaveClassTypeRemoteRelation 
	* @Description: 批量新增ClassTypeRemoteRelation
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	@Override
	public void batchInsert(List<ClassTypeRemoteRelation> classTypeRemoteRelations){
		if(classTypeRemoteRelations != null && !classTypeRemoteRelations.isEmpty()){
			classTypeRemoteRelationMapper.batchInsert(classTypeRemoteRelations);
		}
	}
	
	/**
	 * 
	* @Title: updateClassTypeRemoteRelation 
	* @Description: 编辑ClassTypeRemoteRelation
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	@Override
	public void update(ClassTypeRemoteRelation classTypeRemoteRelation){
		classTypeRemoteRelationMapper.update(classTypeRemoteRelation);
	}
	
	/**
	 * 
	* @Title: deleteClassTypeRemoteRelationById 
	* @Description: 根据id删除ClassTypeRemoteRelation
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	@Override
	public void deleteClassTypeRemoteRelationById(Integer id){
		classTypeRemoteRelationMapper.deleteById(id);
	}
	
	/**
	 * 
	* @Title: deleteClassTypeRemoteRelationByIds 
	* @Description: 根据id批量删除ClassTypeRemoteRelation
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	@Override
	public void deleteClassTypeRemoteRelationByIds(Integer[] ids){
		classTypeRemoteRelationMapper.deleteByIds(ids);
	}
	
	/**
	 * 
	* @Title: findClassTypeRemoteRelationById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	@Override
	public ClassTypeRemoteRelation findClassTypeRemoteRelationById(Integer id){
		return classTypeRemoteRelationMapper.findById(id);
	}
	
	/**
	 * 
	* @Title: findClassTypeRemoteRelationByPage 
	* @Description: 分页查询
	* @return
	* @return List<ClassTypeRemoteRelation>    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	@Override
	public List<ClassTypeRemoteRelation> findClassTypeRemoteRelationByPage(ClassTypeRemoteRelation search){
		Integer totalRecords = classTypeRemoteRelationMapper.pageCount(search);
		search.setTotalRecords(totalRecords);
		return classTypeRemoteRelationMapper.page(search);
	}

	@Override
	public void deleteByClassTypeId(String classTypeId) {
		classTypeRemoteRelationMapper.deleteByClassTypeId(classTypeId);
	}

	@Override
	public List<ModuleRemoteVo> queryRomoteListByClassTypeId(Integer classTypeId) {
		// TODO Auto-generated method stub
		return classTypeRemoteRelationMapper.queryRomoteListByClassTypeId(classTypeId);
	}
	
	
}
