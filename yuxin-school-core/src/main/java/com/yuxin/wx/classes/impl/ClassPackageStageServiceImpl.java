package com.yuxin.wx.classes.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;import com.yuxin.wx.common.BaseServiceImpl;

import com.yuxin.wx.api.classes.IClassPackageStageService;
import com.yuxin.wx.classes.mapper.ClassPackageStageMapper;
import com.yuxin.wx.model.classes.ClassPackageStage;

/**
 * Service Implementation:ClassPackageStage
 * @author chopin
 * @date 2016-3-21
 */
@Service
@Transactional
public class ClassPackageStageServiceImpl extends BaseServiceImpl implements IClassPackageStageService {

	@Autowired
	private ClassPackageStageMapper classPackageStageMapper;
	
	/**
	 * 
	* @Title: saveClassPackageStage
	* @Description: 新增ClassPackageStage
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-3-21
	* @user by chopin
	 */
	@Override
	public void insert(ClassPackageStage entity){
		classPackageStageMapper.insert(entity);
	};
	
	/**
	 * 
	* @Title: batchSaveClassPackageStage 
	* @Description: 批量新增ClassPackageStage
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-3-21
	* @user by chopin
	 */
	@Override
	public void batchInsert(List<ClassPackageStage> entity){
		classPackageStageMapper.batchInsert(entity);
	};
	
	/**
	 * 
	* @Title: updateClassPackageStage 
	* @Description: 编辑ClassPackageStage
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-3-21
	* @user by chopin
	 */
	@Override
	public void update(ClassPackageStage entity){
		classPackageStageMapper.update(entity);
	};
	
	/**
	 * 
	* @Title: deleteClassPackageStageById 
	* @Description: 根据id删除ClassPackageStage
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-3-21
	* @user by chopin
	 */
	 @Override
	public void deleteClassPackageStageById(Integer id){
		classPackageStageMapper.deleteById(id);
	};
	
	/**
	 * 
	* @Title: deleteClassPackageStageByIds 
	* @Description: 根据id批量删除ClassPackageStage
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-3-21
	* @user by chopin
	 */
	@Override
	public void deleteClassPackageStageByIds(Integer[] ids){
		classPackageStageMapper.deleteByIds(ids);
	};
	
	/**
	 * 
	* @Title: findClassPackageStageById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-3-21
	* @user by chopin
	 */
	@Override
	public ClassPackageStage findClassPackageStageById(Integer id){
		return classPackageStageMapper.findById(id);
	};
	
	/**
	 * 
	* @Title: findClassPackageStageByPage 
	* @Description: 分页查询
	* @return
	* @return List<ClassPackageStage>    返回类型 
	* @throws 
	* @exception 
	* @date 2016-3-21
	* @user by chopin
	 */
	@Override
	public List<ClassPackageStage> findClassPackageStageByPage(ClassPackageStage search){
		return classPackageStageMapper.page(search);
	}

	@Override
	public List<ClassPackageStage> queryClassPackageStages(
			ClassPackageStage search) {
		// TODO Auto-generated method stub
		return classPackageStageMapper.queryClassPackageStages(search);
	};
	
	
	public List<ClassPackageStage> findAll(Integer companyId){
		return classPackageStageMapper.queryAll(companyId);
	}
}
