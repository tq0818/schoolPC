package com.yuxin.wx.course.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;import com.yuxin.wx.common.BaseServiceImpl;

import com.yuxin.wx.api.course.ILiveOpenCourseService;
import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.course.mapper.LiveOpenCourseMapper;
import com.yuxin.wx.model.course.LiveOpenCourse;
import com.yuxin.wx.vo.classes.CmlVo;
import com.yuxin.wx.vo.course.CourseVideoVo;

/**
 * Service Implementation:LiveOpenCourse
 * @author wang.zx
 * @date 2015-9-25
 */
@Service
@Transactional
public class LiveOpenCourseServiceImpl extends BaseServiceImpl implements ILiveOpenCourseService {

	@Autowired
	private LiveOpenCourseMapper liveOpenCourseMapper;
	
	/**
	 * 
	* @Title: saveLiveOpenCourse
	* @Description: 新增LiveOpenCourse
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-9-25
	* @user by chopin
	 */
	@Override
	public void insert(LiveOpenCourse entity){
		liveOpenCourseMapper.insert(entity);
	};
	
	/**
	 * 
	* @Title: batchSaveLiveOpenCourse 
	* @Description: 批量新增LiveOpenCourse
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-9-25
	* @user by chopin
	 */
	@Override
	public void batchInsert(List<LiveOpenCourse> T){
		liveOpenCourseMapper.batchInsert(T);
	};
	
	/**
	 * 
	* @Title: updateLiveOpenCourse 
	* @Description: 编辑LiveOpenCourse
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-9-25
	* @user by chopin
	 */
	@Override
	public void update(LiveOpenCourse T){
		liveOpenCourseMapper.update(T);
	};
	
	/**
	 * 
	* @Title: deleteLiveOpenCourseById 
	* @Description: 根据id删除LiveOpenCourse
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-9-25
	* @user by chopin
	 */
	 @Override
	public void deleteLiveOpenCourseById(Integer id){
		liveOpenCourseMapper.deleteById(id);
	};
	
	/**
	 * 
	* @Title: deleteLiveOpenCourseByIds 
	* @Description: 根据id批量删除LiveOpenCourse
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-9-25
	* @user by chopin
	 */
	@Override
	public void deleteLiveOpenCourseByIds(Integer[] ids){
		liveOpenCourseMapper.deleteByIds(ids);
	};
	
	/**
	 * 
	* @Title: findLiveOpenCourseById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-9-25
	* @user by chopin
	 */
	@Override
	public LiveOpenCourse findLiveOpenCourseById(Integer id){
		return liveOpenCourseMapper.findById(id);
	};
	
	/**
	 * 
	* @Title: findLiveOpenCourseByPage 
	* @Description: 分页查询
	* @return
	* @return List<LiveOpenCourse>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-9-25
	* @user by chopin
	 */
	@Override
	public PageFinder<LiveOpenCourse> findLiveOpenCourseByPage(LiveOpenCourse search){
		List<LiveOpenCourse> data=liveOpenCourseMapper.page(search);
		int rowCount=liveOpenCourseMapper.pageCount(search);
		PageFinder<LiveOpenCourse> pageFinder=new PageFinder<LiveOpenCourse>(search.getPage(), search.getPageSize(), rowCount, data);
		return pageFinder;
	}

	@Override
	public LiveOpenCourse findByLiveOpenCourse(LiveOpenCourse course) {
		// TODO Auto-generated method stub
		return liveOpenCourseMapper.findByLiveOpenCourse(course);
	}

	@Override
	public List<CmlVo> findLiveRoomIds(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return liveOpenCourseMapper.findLiveRoomIds(param);
	}

	@Override
	public void updateOpenclassByItem(Map<String, Object> param) {
		// TODO Auto-generated method stub
		liveOpenCourseMapper.updateOpenclassByItem(param);
	}

	@Override
	public LiveOpenCourse findByLiveRoomId(String roomId) {
		// TODO Auto-generated method stub
		return liveOpenCourseMapper.findByLiveRoomId(roomId);
	};
	
	@Override
	public List<LiveOpenCourse> findAfterTodayByPage(LiveOpenCourse search) {
		// TODO Auto-generated method stub
		return liveOpenCourseMapper.findAfterTodayByPage(search);
	}
}
