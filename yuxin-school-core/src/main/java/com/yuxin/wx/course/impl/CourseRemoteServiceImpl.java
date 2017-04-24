package com.yuxin.wx.course.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;import com.yuxin.wx.common.BaseServiceImpl;

import java.util.List;

import com.yuxin.wx.api.course.ICourseRemoteService;
import com.yuxin.wx.model.course.CourseRemote;
import com.yuxin.wx.system.mapper.SysConfigSchoolMapper;
import com.yuxin.wx.vo.course.CourseRemoteVo;
import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.course.mapper.CourseRemoteMapper;

/**
 * Service Implementation:CourseRemote
 * @author wang.zx
 * @date 2014-12-5
 */
@Service
@Transactional
public class CourseRemoteServiceImpl extends BaseServiceImpl implements ICourseRemoteService {

	@Autowired
	private CourseRemoteMapper courseRemoteMapper;
	/**
	 * 
	* @Title: saveCourseRemote
	* @Description: 新增CourseRemote
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	@Override
	public void insert(CourseRemote courseRemote){
		courseRemoteMapper.insert(courseRemote);
	}
	
	/**
	 * 
	* @Title: batchSaveCourseRemote 
	* @Description: 批量新增CourseRemote
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	@Override
	public void batchInsert(List<CourseRemote> courseRemotes){
		if(courseRemotes != null && !courseRemotes.isEmpty()){
			courseRemoteMapper.batchInsert(courseRemotes);
		}
	}
	
	/**
	 * 
	* @Title: updateCourseRemote 
	* @Description: 编辑CourseRemote
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	@Override
	public void update(CourseRemote courseRemote){
		courseRemoteMapper.update(courseRemote);
	}
	
	/**
	 * 
	* @Title: deleteCourseRemoteById 
	* @Description: 根据id删除CourseRemote
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	@Override
	public void deleteCourseRemoteById(Integer id){
		courseRemoteMapper.deleteById(id);
	}
	
	/**
	 * 
	* @Title: deleteCourseRemoteByIds 
	* @Description: 根据id批量删除CourseRemote
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	@Override
	public void deleteCourseRemoteByIds(Integer[] ids){
		courseRemoteMapper.deleteByIds(ids);
	}
	
	/**
	 * 
	* @Title: findCourseRemoteById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	@Override
	public CourseRemote findCourseRemoteById(Integer id){
		return courseRemoteMapper.findById(id);
	}
	
	/**
	 * 
	* @Title: findCourseRemoteByPage 
	* @Description: 分页查询
	* @return
	* @return List<CourseRemote>    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	@Override
	public List<CourseRemote> findCourseRemoteByPage(CourseRemote search){
		Integer totalRecords = courseRemoteMapper.pageCount(search);
		search.setTotalRecords(totalRecords);
		return courseRemoteMapper.page(search);
	}
	
	
	//自定义分页
	@Override
	public PageFinder<CourseRemoteVo> queryCourseRemoteVoByKeys(
			CourseRemote search) {
		search.setPageSize(5);
		Integer rowCount = courseRemoteMapper.pageCount(search);
		List<CourseRemoteVo> data=courseRemoteMapper.queryCourseRemoteVoByKeys(search);
		PageFinder<CourseRemoteVo> pageFinder=new PageFinder<CourseRemoteVo>(search.getPage(), search.getPageSize(), rowCount, data);
		return pageFinder;
	}

	@Override
	public List<CourseRemote> findRemotesByClassTypeId(Integer classTypeId) {
		return courseRemoteMapper.findRemotesByClassTypeId(classTypeId);
	}

	@Override
	public CourseRemoteVo findByOneId(Integer id) {
		return courseRemoteMapper.findByOneId(id);
	}

	@Override
	public Integer checkStopById(Integer id) {
		return courseRemoteMapper.checkStopById(id);
	}

	@Override
	public List<CourseRemote> findByMajor(String major, Integer companyId,String schoolName) {
		CourseRemote remote = new CourseRemote();
		remote.setMajor(major);
		remote.setCompanyId(companyId);
		remote.setName(schoolName);
		return courseRemoteMapper. findByMajor(remote);
	}

	@Override
	public List<CourseRemote> findCourseByItem(CourseRemote courseRemote) {
		// TODO Auto-generated method stub
		return courseRemoteMapper.queryCourseByItem(courseRemote);
	}

	
}
