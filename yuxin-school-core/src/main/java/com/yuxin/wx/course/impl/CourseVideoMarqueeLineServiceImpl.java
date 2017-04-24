package com.yuxin.wx.course.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;import com.yuxin.wx.common.BaseServiceImpl;

import com.yuxin.wx.api.course.ICourseVideoMarqueeLineService;
import com.yuxin.wx.course.mapper.CourseVideoMarqueeLineMapper;
import com.yuxin.wx.model.course.CourseVideoMarqueeLine;

/**
 * Service Implementation:CourseVideoMarqueeLine
 * @author wang.zx
 * @date 2015-10-12
 */
@Service
@Transactional
public class CourseVideoMarqueeLineServiceImpl extends BaseServiceImpl implements ICourseVideoMarqueeLineService {

	@Autowired
	private CourseVideoMarqueeLineMapper courseVideoMarqueeLineMapper;
	
	/**
	 * 
	* @Title: saveCourseVideoMarqueeLine
	* @Description: 新增CourseVideoMarqueeLine
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-10-12
	* @user by chopin
	 */
	@Override
	public void insert(CourseVideoMarqueeLine entity){
		courseVideoMarqueeLineMapper.insert(entity);
	};
	
	/**
	 * 
	* @Title: batchSaveCourseVideoMarqueeLine 
	* @Description: 批量新增CourseVideoMarqueeLine
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-10-12
	* @user by chopin
	 */
	@Override
	public void batchInsert(List<CourseVideoMarqueeLine> T){
		courseVideoMarqueeLineMapper.batchInsert(T);
	};
	
	/**
	 * 
	* @Title: updateCourseVideoMarqueeLine 
	* @Description: 编辑CourseVideoMarqueeLine
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-10-12
	* @user by chopin
	 */
	@Override
	public void update(CourseVideoMarqueeLine T){
		courseVideoMarqueeLineMapper.update(T);
	};
	
	/**
	 * 
	* @Title: deleteCourseVideoMarqueeLineById 
	* @Description: 根据id删除CourseVideoMarqueeLine
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-10-12
	* @user by chopin
	 */
	 @Override
	public void deleteCourseVideoMarqueeLineById(Integer id){
		courseVideoMarqueeLineMapper.deleteById(id);
	};
	
	/**
	 * 
	* @Title: deleteCourseVideoMarqueeLineByIds 
	* @Description: 根据id批量删除CourseVideoMarqueeLine
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-10-12
	* @user by chopin
	 */
	@Override
	public void deleteCourseVideoMarqueeLineByIds(Integer[] ids){
		courseVideoMarqueeLineMapper.deleteByIds(ids);
	};
	
	/**
	 * 
	* @Title: findCourseVideoMarqueeLineById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-10-12
	* @user by chopin
	 */
	@Override
	public CourseVideoMarqueeLine findCourseVideoMarqueeLineById(Integer id){
		return courseVideoMarqueeLineMapper.findById(id);
	};
	
	/**
	 * 
	* @Title: findCourseVideoMarqueeLineByPage 
	* @Description: 分页查询
	* @return
	* @return List<CourseVideoMarqueeLine>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-10-12
	* @user by chopin
	 */
	@Override
	public List<CourseVideoMarqueeLine> findCourseVideoMarqueeLineByPage(CourseVideoMarqueeLine search){
		return courseVideoMarqueeLineMapper.page(search);
	};
}
