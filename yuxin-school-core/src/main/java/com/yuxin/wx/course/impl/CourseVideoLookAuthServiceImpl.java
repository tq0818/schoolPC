package com.yuxin.wx.course.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;import com.yuxin.wx.common.BaseServiceImpl;

import com.yuxin.wx.api.course.ICourseVideoLookAuthService;
import com.yuxin.wx.course.mapper.CourseVideoLookAuthMapper;
import com.yuxin.wx.model.course.CourseVideoLookAuth;

/**
 * Service Implementation:CourseVideoLookAuth
 * @author wang.zx
 * @date 2016-3-4
 */
@Service
@Transactional
public class CourseVideoLookAuthServiceImpl extends BaseServiceImpl implements ICourseVideoLookAuthService {

	@Autowired
	private CourseVideoLookAuthMapper courseVideoLookAuthMapper;
	
	/**
	 * 
	* @Title: saveCourseVideoLookAuth
	* @Description: 新增CourseVideoLookAuth
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-3-4
	* @user by chopin
	 */
	@Override
	public void insert(CourseVideoLookAuth entity){
		courseVideoLookAuthMapper.insert(entity);
	};
	
	/**
	 * 
	* @Title: batchSaveCourseVideoLookAuth 
	* @Description: 批量新增CourseVideoLookAuth
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-3-4
	* @user by chopin
	 */
	@Override
	public void batchInsert(List<CourseVideoLookAuth> T){
		courseVideoLookAuthMapper.batchInsert(T);
	};
	
	/**
	 * 
	* @Title: updateCourseVideoLookAuth 
	* @Description: 编辑CourseVideoLookAuth
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-3-4
	* @user by chopin
	 */
	@Override
	public void update(CourseVideoLookAuth T){
		courseVideoLookAuthMapper.update(T);
	};
	
	/**
	 * 
	* @Title: deleteCourseVideoLookAuthById 
	* @Description: 根据id删除CourseVideoLookAuth
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-3-4
	* @user by chopin
	 */
	 @Override
	public void deleteCourseVideoLookAuthById(Integer id){
		courseVideoLookAuthMapper.deleteById(id);
	};
	
	/**
	 * 
	* @Title: deleteCourseVideoLookAuthByIds 
	* @Description: 根据id批量删除CourseVideoLookAuth
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-3-4
	* @user by chopin
	 */
	@Override
	public void deleteCourseVideoLookAuthByIds(Integer[] ids){
		courseVideoLookAuthMapper.deleteByIds(ids);
	};
	
	/**
	 * 
	* @Title: findCourseVideoLookAuthById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-3-4
	* @user by chopin
	 */
	@Override
	public CourseVideoLookAuth findCourseVideoLookAuthById(Integer id){
		return courseVideoLookAuthMapper.findById(id);
	};
	
	/**
	 * 
	* @Title: findCourseVideoLookAuthByPage 
	* @Description: 分页查询
	* @return
	* @return List<CourseVideoLookAuth>    返回类型 
	* @throws 
	* @exception 
	* @date 2016-3-4
	* @user by chopin
	 */
	@Override
	public List<CourseVideoLookAuth> findCourseVideoLookAuthByPage(CourseVideoLookAuth search){
		return courseVideoLookAuthMapper.page(search);
	}

	@Override
	public CourseVideoLookAuth findAuthByCompanyId(Integer companyId) {
		// TODO Auto-generated method stub
		return courseVideoLookAuthMapper.findAuthByCompanyId(companyId);
	};
}
