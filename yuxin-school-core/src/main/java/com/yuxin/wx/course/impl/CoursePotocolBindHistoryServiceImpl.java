package com.yuxin.wx.course.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;import com.yuxin.wx.common.BaseServiceImpl;

import com.yuxin.wx.api.course.ICoursePotocolBindHistoryService;
import com.yuxin.wx.course.mapper.CoursePotocolBindHistoryMapper;
import com.yuxin.wx.model.course.CoursePotocolBindHistory;

/**
 * Service Implementation:CoursePotocolBindHistory
 * @author chopin
 * @date 2016-11-1
 */
@Service
@Transactional
public class CoursePotocolBindHistoryServiceImpl extends BaseServiceImpl implements ICoursePotocolBindHistoryService {

	@Autowired
	private CoursePotocolBindHistoryMapper coursePotocolBindHistoryMapper;
	
	/**
	 * 
	* @Title: saveCoursePotocolBindHistory
	* @Description: 新增CoursePotocolBindHistory
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-11-1
	* @user by chopin
	 */
	@Override
	public void insert(CoursePotocolBindHistory entity){
		coursePotocolBindHistoryMapper.insert(entity);
	};
	
	/**
	 * 
	* @Title: batchSaveCoursePotocolBindHistory 
	* @Description: 批量新增CoursePotocolBindHistory
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-11-1
	* @user by chopin
	 */
	@Override
	public void batchInsert(List<CoursePotocolBindHistory> entity){
		coursePotocolBindHistoryMapper.batchInsert(entity);
	};
	
	/**
	 * 
	* @Title: updateCoursePotocolBindHistory 
	* @Description: 编辑CoursePotocolBindHistory
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-11-1
	* @user by chopin
	 */
	@Override
	public void update(CoursePotocolBindHistory entity){
		coursePotocolBindHistoryMapper.update(entity);
	};
	
	/**
	 * 
	* @Title: deleteCoursePotocolBindHistoryById 
	* @Description: 根据id删除CoursePotocolBindHistory
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-11-1
	* @user by chopin
	 */
	 @Override
	public void deleteCoursePotocolBindHistoryById(Integer id){
		coursePotocolBindHistoryMapper.deleteById(id);
	};
	
	/**
	 * 
	* @Title: deleteCoursePotocolBindHistoryByIds 
	* @Description: 根据id批量删除CoursePotocolBindHistory
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-11-1
	* @user by chopin
	 */
	@Override
	public void deleteCoursePotocolBindHistoryByIds(Integer[] ids){
		coursePotocolBindHistoryMapper.deleteByIds(ids);
	};
	
	/**
	 * 
	* @Title: findCoursePotocolBindHistoryById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-11-1
	* @user by chopin
	 */
	@Override
	public CoursePotocolBindHistory findCoursePotocolBindHistoryById(Integer id){
		return coursePotocolBindHistoryMapper.findById(id);
	};
	
	/**
	 * 
	* @Title: findCoursePotocolBindHistoryByPage 
	* @Description: 分页查询
	* @return
	* @return List<CoursePotocolBindHistory>    返回类型 
	* @throws 
	* @exception 
	* @date 2016-11-1
	* @user by chopin
	 */
	@Override
	public List<CoursePotocolBindHistory> findCoursePotocolBindHistoryByPage(CoursePotocolBindHistory search){
		return coursePotocolBindHistoryMapper.page(search);
	};
}
