package com.yuxin.wx.course.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;import com.yuxin.wx.common.BaseServiceImpl;

import com.yuxin.wx.api.course.ICourseAfterTestContentService;
import com.yuxin.wx.course.mapper.CourseAfterTestContentMapper;
import com.yuxin.wx.model.course.CourseAfterTestContent;


/**
 * Service Implementation:CourseAfterTestContent
 * @author chopin
 * @date 2016-9-1
 */
@Service
@Transactional
public class CourseAfterTestContentServiceImpl extends BaseServiceImpl implements ICourseAfterTestContentService {

	@Autowired
	private CourseAfterTestContentMapper courseAfterTestContentMapper;
	
	/**
	 * 
	* @Title: saveCourseAfterTestContent
	* @Description: 新增CourseAfterTestContent
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-9-1
	* @user by chopin
	 */
	@Override
	public void insert(CourseAfterTestContent entity){
		courseAfterTestContentMapper.insert(entity);
	};
	
	/**
	 * 
	* @Title: batchSaveCourseAfterTestContent 
	* @Description: 批量新增CourseAfterTestContent
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-9-1
	* @user by chopin
	 */
	@Override
	public void batchInsert(List<CourseAfterTestContent> entity){
		courseAfterTestContentMapper.batchInsert(entity);
	};
	
	/**
	 * 
	* @Title: updateCourseAfterTestContent 
	* @Description: 编辑CourseAfterTestContent
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-9-1
	* @user by chopin
	 */
	@Override
	public void update(CourseAfterTestContent entity){
		courseAfterTestContentMapper.update(entity);
	};
	
	/**
	 * 
	* @Title: deleteCourseAfterTestContentById 
	* @Description: 根据id删除CourseAfterTestContent
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-9-1
	* @user by chopin
	 */
	 @Override
	public void deleteCourseAfterTestContentById(Integer id){
		courseAfterTestContentMapper.deleteById(id);
	};
	
	/**
	 * 
	* @Title: deleteCourseAfterTestContentByIds 
	* @Description: 根据id批量删除CourseAfterTestContent
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-9-1
	* @user by chopin
	 */
	@Override
	public void deleteCourseAfterTestContentByIds(Integer[] ids){
		courseAfterTestContentMapper.deleteByIds(ids);
	};
	
	/**
	 * 
	* @Title: findCourseAfterTestContentById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-9-1
	* @user by chopin
	 */
	@Override
	public CourseAfterTestContent findCourseAfterTestContentById(Integer id){
		return courseAfterTestContentMapper.findById(id);
	};
	
	/**
	 * 
	* @Title: findCourseAfterTestContentByPage 
	* @Description: 分页查询
	* @return
	* @return List<CourseAfterTestContent>    返回类型 
	* @throws 
	* @exception 
	* @date 2016-9-1
	* @user by chopin
	 */
	@Override
	public List<CourseAfterTestContent> findCourseAfterTestContentByPage(CourseAfterTestContent search){
		return courseAfterTestContentMapper.page(search);
	}

	@Override
	public List<CourseAfterTestContent> findContentListByTestId(Integer testId) {
		return courseAfterTestContentMapper.findContentListByTestId(testId);
	}

	@Override
	public List<CourseAfterTestContent> findContentListByTestId2(Integer testId) {
		return courseAfterTestContentMapper.findContentListByTestId2(testId);
	}

	@Override
	public void deleteByTestId(Integer testId) {
		courseAfterTestContentMapper.deleteByTestId(testId);
	}

	@Override
	public void deleteByTestId2(Integer testId) {
		courseAfterTestContentMapper.deleteByTestId2(testId);
	};
}
