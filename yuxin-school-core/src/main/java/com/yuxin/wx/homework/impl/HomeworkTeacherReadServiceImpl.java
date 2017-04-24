package com.yuxin.wx.homework.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;import com.yuxin.wx.common.BaseServiceImpl;

import com.yuxin.wx.api.homework.IHomeworkTeacherReadService;
import com.yuxin.wx.homework.mapper.HomeworkTeacherReadMapper;
import com.yuxin.wx.model.homework.HomeworkTeacherRead;


/**
 * Service Implementation:HomeworkTeacherRead
 * @author chopin
 * @date 2016-12-15
 */
@Service
@Transactional
public class HomeworkTeacherReadServiceImpl extends BaseServiceImpl implements IHomeworkTeacherReadService {

	@Autowired
	private HomeworkTeacherReadMapper homeworkTeacherReadMapper;
	
	/**
	 * 
	* @Title: saveHomeworkTeacherRead
	* @Description: 新增HomeworkTeacherRead
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-12-15
	* @user by chopin
	 */
	@Override
	public void insert(HomeworkTeacherRead entity){
		homeworkTeacherReadMapper.insert(entity);
	};
	
	/**
	 * 
	* @Title: batchSaveHomeworkTeacherRead 
	* @Description: 批量新增HomeworkTeacherRead
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-12-15
	* @user by chopin
	 */
	@Override
	public void batchInsert(List<HomeworkTeacherRead> entity){
		homeworkTeacherReadMapper.batchInsert(entity);
	};
	
	/**
	 * 
	* @Title: updateHomeworkTeacherRead 
	* @Description: 编辑HomeworkTeacherRead
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-12-15
	* @user by chopin
	 */
	@Override
	public void update(HomeworkTeacherRead entity){
		homeworkTeacherReadMapper.update(entity);
	};
	
	/**
	 * 
	* @Title: deleteHomeworkTeacherReadById 
	* @Description: 根据id删除HomeworkTeacherRead
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-12-15
	* @user by chopin
	 */
	 @Override
	public void deleteHomeworkTeacherReadById(Integer id){
		homeworkTeacherReadMapper.deleteById(id);
	};
	
	/**
	 * 
	* @Title: deleteHomeworkTeacherReadByIds 
	* @Description: 根据id批量删除HomeworkTeacherRead
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-12-15
	* @user by chopin
	 */
	@Override
	public void deleteHomeworkTeacherReadByIds(Integer[] ids){
		homeworkTeacherReadMapper.deleteByIds(ids);
	};
	
	/**
	 * 
	* @Title: findHomeworkTeacherReadById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-12-15
	* @user by chopin
	 */
	@Override
	public HomeworkTeacherRead findHomeworkTeacherReadById(Integer id){
		return homeworkTeacherReadMapper.findById(id);
	};
	
	/**
	 * 
	* @Title: findHomeworkTeacherReadByPage 
	* @Description: 分页查询
	* @return
	* @return List<HomeworkTeacherRead>    返回类型 
	* @throws 
	* @exception 
	* @date 2016-12-15
	* @user by chopin
	 */
	@Override
	public List<HomeworkTeacherRead> findHomeworkTeacherReadByPage(HomeworkTeacherRead search){
		return homeworkTeacherReadMapper.page(search);
	}

	@Override
	public HomeworkTeacherRead queryTeacherHomeworkRead(HomeworkTeacherRead search) {
		return homeworkTeacherReadMapper.queryTeacherHomeworkRead(search);
	}

	@Override
	public HomeworkTeacherRead findByResourceId(Integer id) {
		return homeworkTeacherReadMapper.findByResourceId(id);
	};
}
