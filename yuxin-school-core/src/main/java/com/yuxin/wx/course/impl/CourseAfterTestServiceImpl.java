package com.yuxin.wx.course.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;import com.yuxin.wx.common.BaseServiceImpl;

import com.yuxin.wx.api.course.ICourseAfterTestService;
import com.yuxin.wx.course.mapper.CourseAfterTestMapper;
import com.yuxin.wx.model.course.CourseAfterTest;


/**
 * Service Implementation:CourseAfterTest
 * @author chopin
 * @date 2016-9-1
 */
@Service
@Transactional
public class CourseAfterTestServiceImpl extends BaseServiceImpl implements ICourseAfterTestService {

	@Autowired
	private CourseAfterTestMapper courseAfterTestMapper;
	
	/**
	 * 
	* @Title: saveCourseAfterTest
	* @Description: 新增CourseAfterTest
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-9-1
	* @user by chopin
	 */
	@Override
	public void insert(CourseAfterTest entity){
		courseAfterTestMapper.insert(entity);
	};
	
	/**
	 * 
	* @Title: batchSaveCourseAfterTest 
	* @Description: 批量新增CourseAfterTest
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-9-1
	* @user by chopin
	 */
	@Override
	public void batchInsert(List<CourseAfterTest> entity){
		courseAfterTestMapper.batchInsert(entity);
	};
	
	/**
	 * 
	* @Title: updateCourseAfterTest 
	* @Description: 编辑CourseAfterTest
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-9-1
	* @user by chopin
	 */
	@Override
	public void update(CourseAfterTest entity){
		courseAfterTestMapper.update(entity);
	};
	
	/**
	 * 
	* @Title: deleteCourseAfterTestById 
	* @Description: 根据id删除CourseAfterTest
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-9-1
	* @user by chopin
	 */
	 @Override
	public void deleteCourseAfterTestById(Integer id){
		courseAfterTestMapper.deleteById(id);
	};
	
	/**
	 * 
	* @Title: deleteCourseAfterTestByIds 
	* @Description: 根据id批量删除CourseAfterTest
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-9-1
	* @user by chopin
	 */
	@Override
	public void deleteCourseAfterTestByIds(Integer[] ids){
		courseAfterTestMapper.deleteByIds(ids);
	};
	
	/**
	 * 
	* @Title: findCourseAfterTestById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-9-1
	* @user by chopin
	 */
	@Override
	public CourseAfterTest findCourseAfterTestById(Integer id){
		return courseAfterTestMapper.findById(id);
	};
	
	/**
	 * 
	* @Title: findCourseAfterTestByPage 
	* @Description: 分页查询
	* @return
	* @return List<CourseAfterTest>    返回类型 
	* @throws 
	* @exception 
	* @date 2016-9-1
	* @user by chopin
	 */
	@Override
	public List<CourseAfterTest> findCourseAfterTestByPage(CourseAfterTest search){
		return courseAfterTestMapper.page(search);
	}

	@Override
	public List<CourseAfterTest> findTestListByChapterId(Integer chapterId) {
		// TODO Auto-generated method stub
		return courseAfterTestMapper.findTestListByChapterId(chapterId);
	}

	@Override
	public CourseAfterTest mvTest(Integer chapterId, Integer testId) {
		// TODO Auto-generated method stub
		Map<String,Integer> map=new HashMap<String,Integer>();
		map.put("target", chapterId);
		map.put("test", testId);
		return courseAfterTestMapper.mvTest(map);
	};
}
