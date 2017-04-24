package com.yuxin.wx.course.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;import com.yuxin.wx.common.BaseServiceImpl;

import com.yuxin.wx.api.course.ICourseVideoMarqueeService;
import com.yuxin.wx.course.mapper.CourseVideoMarqueeMapper;
import com.yuxin.wx.model.course.CourseVideoMarquee;
import com.yuxin.wx.vo.course.CourseVideoMarqueeVo;

/**
 * Service Implementation:CourseVideoMarquee
 * @author wang.zx
 * @date 2015-10-12
 */
@Service
@Transactional
public class CourseVideoMarqueeServiceImpl extends BaseServiceImpl implements ICourseVideoMarqueeService {

	@Autowired
	private CourseVideoMarqueeMapper courseVideoMarqueeMapper;
	
	/**
	 * 
	* @Title: saveCourseVideoMarquee
	* @Description: 新增CourseVideoMarquee
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-10-12
	* @user by chopin
	 */
	@Override
	public void insert(CourseVideoMarquee entity){
		courseVideoMarqueeMapper.insert(entity);
	};
	
	/**
	 * 
	* @Title: batchSaveCourseVideoMarquee 
	* @Description: 批量新增CourseVideoMarquee
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-10-12
	* @user by chopin
	 */
	@Override
	public void batchInsert(List<CourseVideoMarquee> T){
		courseVideoMarqueeMapper.batchInsert(T);
	};
	
	/**
	 * 
	* @Title: updateCourseVideoMarquee 
	* @Description: 编辑CourseVideoMarquee
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-10-12
	* @user by chopin
	 */
	@Override
	public void update(CourseVideoMarquee T){
		courseVideoMarqueeMapper.update(T);
	};
	
	/**
	 * 
	* @Title: deleteCourseVideoMarqueeById 
	* @Description: 根据id删除CourseVideoMarquee
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-10-12
	* @user by chopin
	 */
	 @Override
	public void deleteCourseVideoMarqueeById(Integer id){
		courseVideoMarqueeMapper.deleteById(id);
	};
	
	/**
	 * 
	* @Title: deleteCourseVideoMarqueeByIds 
	* @Description: 根据id批量删除CourseVideoMarquee
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-10-12
	* @user by chopin
	 */
	@Override
	public void deleteCourseVideoMarqueeByIds(Integer[] ids){
		courseVideoMarqueeMapper.deleteByIds(ids);
	};
	
	/**
	 * 
	* @Title: findCourseVideoMarqueeById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-10-12
	* @user by chopin
	 */
	@Override
	public CourseVideoMarquee findCourseVideoMarqueeById(Integer id){
		return courseVideoMarqueeMapper.findById(id);
	};
	
	/**
	 * 
	* @Title: findCourseVideoMarqueeByPage 
	* @Description: 分页查询
	* @return
	* @return List<CourseVideoMarquee>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-10-12
	* @user by chopin
	 */
	@Override
	public List<CourseVideoMarquee> findCourseVideoMarqueeByPage(CourseVideoMarquee search){
		return courseVideoMarqueeMapper.page(search);
	}

	@Override
	public CourseVideoMarquee findMarqueeByCompanyId(CourseVideoMarquee cvm) {
		// TODO Auto-generated method stub
		return courseVideoMarqueeMapper.findMarqueeByCompanyId(cvm);
	}

	@Override
	public CourseVideoMarqueeVo findCourseVideoMarqueeVoByCompanyId(
			Integer companyId) {
		// TODO Auto-generated method stub
		return courseVideoMarqueeMapper.findCourseVideoMarqueeVoByCompanyId(companyId);
	};
}
