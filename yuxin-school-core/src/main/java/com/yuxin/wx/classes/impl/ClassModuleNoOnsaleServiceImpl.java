package com.yuxin.wx.classes.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;import com.yuxin.wx.common.BaseServiceImpl;

import com.yuxin.wx.api.classes.IClassModuleNoOnsaleService;
import com.yuxin.wx.classes.mapper.ClassModuleNoOnsaleMapper;
import com.yuxin.wx.model.classes.ClassModuleNoOnsale;

/**
 * Service Implementation:ClassModuleNoOnsale
 * @author wang.zx
 * @date 2015-8-14
 */
@Service
@Transactional
public class ClassModuleNoOnsaleServiceImpl extends BaseServiceImpl implements IClassModuleNoOnsaleService {

	@Autowired
	private ClassModuleNoOnsaleMapper classModuleNoOnsaleMapper;
	
	/**
	 * 
	* @Title: saveClassModuleNoOnsale
	* @Description: 新增ClassModuleNoOnsale
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-8-14
	* @user by chopin
	 */
	@Override
	public void insert(ClassModuleNoOnsale entity){
		classModuleNoOnsaleMapper.insert(entity);
	};
	
	/**
	 * 
	* @Title: batchSaveClassModuleNoOnsale 
	* @Description: 批量新增ClassModuleNoOnsale
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-8-14
	* @user by chopin
	 */
	@Override
	public void batchInsert(List<ClassModuleNoOnsale> entity){
		classModuleNoOnsaleMapper.batchInsert(entity);
	};
	
	/**
	 * 
	* @Title: updateClassModuleNoOnsale 
	* @Description: 编辑ClassModuleNoOnsale
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-8-14
	* @user by chopin
	 */
	@Override
	public void update(ClassModuleNoOnsale entity){
		classModuleNoOnsaleMapper.update(entity);
	};
	
	/**
	 * 
	* @Title: deleteClassModuleNoOnsaleById 
	* @Description: 根据id删除ClassModuleNoOnsale
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-8-14
	* @user by chopin
	 */
	 @Override
	public void deleteClassModuleNoOnsaleById(Integer id){
		classModuleNoOnsaleMapper.deleteById(id);
	};
	
	/**
	 * 
	* @Title: deleteClassModuleNoOnsaleByIds 
	* @Description: 根据id批量删除ClassModuleNoOnsale
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-8-14
	* @user by chopin
	 */
	@Override
	public void deleteClassModuleNoOnsaleByIds(Integer[] ids){
		classModuleNoOnsaleMapper.deleteByIds(ids);
	};
	
	/**
	 * 
	* @Title: findClassModuleNoOnsaleById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-8-14
	* @user by chopin
	 */
	@Override
	public ClassModuleNoOnsale findClassModuleNoOnsaleById(Integer id){
		return classModuleNoOnsaleMapper.findById(id);
	};
	
	/**
	 * 
	* @Title: findClassModuleNoOnsaleByPage 
	* @Description: 分页查询
	* @return
	* @return List<ClassModuleNoOnsale>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-8-14
	* @user by chopin
	 */
	@Override
	public List<ClassModuleNoOnsale> findClassModuleNoOnsaleByPage(ClassModuleNoOnsale search){
		return classModuleNoOnsaleMapper.page(search);
	}

	@Override
	public ClassModuleNoOnsale queryClassModuleOnSale(
			ClassModuleNoOnsale moduleOnsale) {
		// TODO Auto-generated method stub
		return classModuleNoOnsaleMapper.queryClassModuleOnSale(moduleOnsale);
	}

	@Override
	public ClassModuleNoOnsale queryClassNoOnSale(
			ClassModuleNoOnsale moduleOnsale) {
		// TODO Auto-generated method stub
		return classModuleNoOnsaleMapper.queryClassNoOnSale(moduleOnsale);
	};
	
	@Override
	public ClassModuleNoOnsale queryModuleOnSaleModuleNo(Integer moduleId) {
		return classModuleNoOnsaleMapper.queryModuleOnSaleModuleNo(moduleId);
	};
	
}
