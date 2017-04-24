package com.yuxin.wx.course.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;import com.yuxin.wx.common.BaseServiceImpl;

import com.yuxin.wx.api.course.ICourseExerciseService;
import com.yuxin.wx.course.mapper.CourseExerciseMapper;
import com.yuxin.wx.model.course.CourseExercise;
import com.yuxin.wx.vo.course.CourseExerciseVo;


/**
 * Service Implementation:CourseExercise
 * @author wang.zx
 * @date 2015-12-29
 */
@Service
@Transactional
public class CourseExerciseServiceImpl extends BaseServiceImpl implements ICourseExerciseService {

	@Autowired
	private CourseExerciseMapper courseExerciseMapper;
	
	/**
	 * 
	* @Title: saveCourseExercise
	* @Description: 新增CourseExercise
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-12-29
	* @user by chopin
	 */
	@Override
	public void insert(CourseExercise entity){
		courseExerciseMapper.insert(entity);
	};
	
	/**
	 * 
	* @Title: batchSaveCourseExercise 
	* @Description: 批量新增CourseExercise
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-12-29
	* @user by chopin
	 */
	@Override
	public void batchInsert(List<CourseExercise> T){
		courseExerciseMapper.batchInsert(T);
	};
	
	/**
	 * 
	* @Title: updateCourseExercise 
	* @Description: 编辑CourseExercise
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-12-29
	* @user by chopin
	 */
	@Override
	public void update(CourseExercise T){
		courseExerciseMapper.update(T);
	};
	
	/**
	 * 
	* @Title: deleteCourseExerciseById 
	* @Description: 根据id删除CourseExercise
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-12-29
	* @user by chopin
	 */
	 @Override
	public void deleteCourseExerciseById(Integer id){
		courseExerciseMapper.deleteById(id);
	};
	
	/**
	 * 
	* @Title: deleteCourseExerciseByIds 
	* @Description: 根据id批量删除CourseExercise
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-12-29
	* @user by chopin
	 */
	@Override
	public void deleteCourseExerciseByIds(Integer[] ids){
		courseExerciseMapper.deleteByIds(ids);
	};
	
	/**
	 * 
	* @Title: findCourseExerciseById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-12-29
	* @user by chopin
	 */
	@Override
	public CourseExercise findCourseExerciseById(Integer id){
		return courseExerciseMapper.findById(id);
	};
	
	/**
	 * 
	* @Title: findCourseExerciseByPage 
	* @Description: 分页查询
	* @return
	* @return List<CourseExercise>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-12-29
	* @user by chopin
	 */
	@Override
	public List<CourseExercise> findCourseExerciseByPage(CourseExercise search){
		return courseExerciseMapper.page(search);
	};
	
	/**
	 * 
	* @Title: findCourseExerciseByPage 
	* @Description: 分页查询
	* @return
	* @return List<CourseExercise>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-12-29
	* @user by chopin
	 */
	@Override
	public List<CourseExerciseVo> searchCourseExercise(CourseExercise search){
		return courseExerciseMapper.page2(search);
	}
}
