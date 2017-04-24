package com.yuxin.wx.classes.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;import com.yuxin.wx.common.BaseServiceImpl;
import java.util.List;
import com.yuxin.wx.api.classes.IClassModuleVideoRelationService;
import com.yuxin.wx.model.classes.ClassModuleVideoRelation;
import com.yuxin.wx.classes.mapper.ClassModuleVideoRelationMapper;

/**
 * Service Implementation:ClassModuleVideoRelation
 * @author wang.zx
 * @date 2014-12-5
 */
@Service
@Transactional
public class ClassModuleVideoRelationServiceImpl extends BaseServiceImpl implements IClassModuleVideoRelationService {

	@Autowired
	private ClassModuleVideoRelationMapper classModuleVideoRelationMapper;
	
	/**
	 * 
	* @Title: saveClassModuleVideoRelation
	* @Description: 新增ClassModuleVideoRelation
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	@Override
	public void insert(ClassModuleVideoRelation classModuleVideoRelation){
		classModuleVideoRelationMapper.insert(classModuleVideoRelation);
	}
	
	/**
	 * 
	* @Title: batchSaveClassModuleVideoRelation 
	* @Description: 批量新增ClassModuleVideoRelation
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	@Override
	public void batchInsert(List<ClassModuleVideoRelation> classModuleVideoRelations){
		if(classModuleVideoRelations != null && !classModuleVideoRelations.isEmpty()){
			classModuleVideoRelationMapper.batchInsert(classModuleVideoRelations);
		}
	}
	
	/**
	 * 
	* @Title: updateClassModuleVideoRelation 
	* @Description: 编辑ClassModuleVideoRelation
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	@Override
	public void update(ClassModuleVideoRelation classModuleVideoRelation){
		classModuleVideoRelationMapper.update(classModuleVideoRelation);
	}
	
	/**
	 * 
	* @Title: deleteClassModuleVideoRelationById 
	* @Description: 根据id删除ClassModuleVideoRelation
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	@Override
	public void deleteClassModuleVideoRelationById(Integer id){
		classModuleVideoRelationMapper.deleteById(id);
	}
	
	/**
	 * 
	* @Title: deleteClassModuleVideoRelationByIds 
	* @Description: 根据id批量删除ClassModuleVideoRelation
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	@Override
	public void deleteClassModuleVideoRelationByIds(Integer[] ids){
		classModuleVideoRelationMapper.deleteByIds(ids);
	}
	
	/**
	 * 
	* @Title: findClassModuleVideoRelationById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	@Override
	public ClassModuleVideoRelation findClassModuleVideoRelationById(Integer id){
		return classModuleVideoRelationMapper.findById(id);
	}
	
	/**
	 * 
	* @Title: findClassModuleVideoRelationByPage 
	* @Description: 分页查询
	* @return
	* @return List<ClassModuleVideoRelation>    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	@Override
	public List<ClassModuleVideoRelation> findClassModuleVideoRelationByPage(ClassModuleVideoRelation search){
		Integer totalRecords = classModuleVideoRelationMapper.pageCount(search);
		search.setTotalRecords(totalRecords);
		return classModuleVideoRelationMapper.page(search);
	}

	@Override
	public void deleteByModuleId(String moduleId) {
		classModuleVideoRelationMapper.deleteByModuleId(moduleId);
	}
	
	
}
