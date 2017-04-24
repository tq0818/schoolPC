package com.yuxin.wx.course.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;import com.yuxin.wx.common.BaseServiceImpl;

import com.yuxin.wx.api.course.ICourseProtocolConfigService;
import com.yuxin.wx.course.mapper.CourseProtocolConfigMapper;
import com.yuxin.wx.model.course.CourseProtocolConfig;

/**
 * Service Implementation:CourseProtocolConfig
 * @author chopin
 * @date 2016-10-31
 */
@Service
@Transactional
public class CourseProtocolConfigServiceImpl extends BaseServiceImpl implements ICourseProtocolConfigService {

	@Autowired
	private CourseProtocolConfigMapper courseProtocolConfigMapper;
	
	/**
	 * 
	* @Title: saveCourseProtocolConfig
	* @Description: 新增CourseProtocolConfig
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-10-31
	* @user by chopin
	 */
	@Override
	public void insert(CourseProtocolConfig entity){
		courseProtocolConfigMapper.insert(entity);
	};
	
	/**
	 * 
	* @Title: batchSaveCourseProtocolConfig 
	* @Description: 批量新增CourseProtocolConfig
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-10-31
	* @user by chopin
	 */
	@Override
	public void batchInsert(List<CourseProtocolConfig> entity){
		courseProtocolConfigMapper.batchInsert(entity);
	};
	
	/**
	 * 
	* @Title: updateCourseProtocolConfig 
	* @Description: 编辑CourseProtocolConfig
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-10-31
	* @user by chopin
	 */
	@Override
	public void update(CourseProtocolConfig entity){
		courseProtocolConfigMapper.update(entity);
	};
	
	/**
	 * 
	* @Title: deleteCourseProtocolConfigById 
	* @Description: 根据id删除CourseProtocolConfig
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-10-31
	* @user by chopin
	 */
	 @Override
	public void deleteCourseProtocolConfigById(Integer id){
		courseProtocolConfigMapper.deleteById(id);
	};
	
	/**
	 * 
	* @Title: deleteCourseProtocolConfigByIds 
	* @Description: 根据id批量删除CourseProtocolConfig
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-10-31
	* @user by chopin
	 */
	@Override
	public void deleteCourseProtocolConfigByIds(Integer[] ids){
		courseProtocolConfigMapper.deleteByIds(ids);
	};
	
	/**
	 * 
	* @Title: findCourseProtocolConfigById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-10-31
	* @user by chopin
	 */
	@Override
	public CourseProtocolConfig findCourseProtocolConfigById(Integer id){
		return courseProtocolConfigMapper.findById(id);
	};
	
	/**
	 * 
	* @Title: findCourseProtocolConfigByPage 
	* @Description: 分页查询
	* @return
	* @return List<CourseProtocolConfig>    返回类型 
	* @throws 
	* @exception 
	* @date 2016-10-31
	* @user by chopin
	 */
	@Override
	public List<CourseProtocolConfig> findCourseProtocolConfigByPage(CourseProtocolConfig search){
		return courseProtocolConfigMapper.page(search);
	}

	@Override
	public List<CourseProtocolConfig> findBySearch(CourseProtocolConfig config) {
		return courseProtocolConfigMapper.findBySearch(config);
	}

	@Override
	public Integer pageCount(CourseProtocolConfig config) {
		return courseProtocolConfigMapper.pageCount(config);
	}

	@Override
	public int checkNameExist(CourseProtocolConfig config) {
		return courseProtocolConfigMapper.checkNameExist(config);
	};
}
