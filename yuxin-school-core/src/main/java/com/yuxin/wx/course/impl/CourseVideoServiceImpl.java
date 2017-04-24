package com.yuxin.wx.course.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;import com.yuxin.wx.common.BaseServiceImpl;

import java.util.List;

import com.yuxin.wx.api.course.ICourseVideoService;
import com.yuxin.wx.model.course.CourseVideo;
import com.yuxin.wx.vo.course.CourseVideoVo;
import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.course.mapper.CourseVideoMapper;

/**
 * Service Implementation:CourseVideo
 * @author wang.zx
 * @date 2014-12-5
 */
@Service
@Transactional
public class CourseVideoServiceImpl extends BaseServiceImpl implements ICourseVideoService {

	@Autowired
	private CourseVideoMapper courseVideoMapper;
	
	/**
	 * 
	* @Title: saveCourseVideo
	* @Description: 新增CourseVideo
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	@Override
	public void insert(CourseVideo courseVideo){
		courseVideoMapper.insert(courseVideo);
	}
	
	/**
	 * 
	* @Title: batchSaveCourseVideo 
	* @Description: 批量新增CourseVideo
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	@Override
	public void batchInsert(List<CourseVideo> courseVideos){
		if(courseVideos != null && !courseVideos.isEmpty()){
			courseVideoMapper.batchInsert(courseVideos);
		}
	}
	
	/**
	 * 
	* @Title: updateCourseVideo 
	* @Description: 编辑CourseVideo
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	@Override
	public void update(CourseVideo courseVideo){
		courseVideoMapper.update(courseVideo);
	}
	
	/**
	 * 
	* @Title: deleteCourseVideoById 
	* @Description: 根据id删除CourseVideo
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	@Override
	public void deleteCourseVideoById(Integer id){
		courseVideoMapper.deleteById(id);
	}
	
	/**
	 * 
	* @Title: findCourseVideoByModuleId 
	* @Description: 根据id查询
	* @param ModuleId
	* @return List<CourseVideo>    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by chopin
	 */
	public List<CourseVideo> findCourseVideoByModuleId(Integer moduleId){
		return courseVideoMapper.findListByModuleId(moduleId);
	}
	
	/**
	 * 
	* @Title: deleteCourseVideoByIds 
	* @Description: 根据id批量删除CourseVideo
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	@Override
	public void deleteCourseVideoByIds(Integer[] ids){
		courseVideoMapper.deleteByIds(ids);
	}
	
	/**
	 * 
	* @Title: findCourseVideoById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	@Override
	public CourseVideo findCourseVideoById(Integer id){
		return courseVideoMapper.findById(id);
	}
	
	/**
	 * 
	* @Title: findCourseVideoByPage 
	* @Description: 分页查询
	* @return
	* @return List<CourseVideo>    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	@Override
	public List<CourseVideo> findCourseVideoByPage(CourseVideo search){
		Integer totalRecords = courseVideoMapper.pageCount(search);
		search.setTotalRecords(totalRecords);
		return courseVideoMapper.page(search);
	}

	@Override
	public PageFinder<CourseVideoVo> queryVideoByKeys(CourseVideo search) {
		List<CourseVideoVo> data=courseVideoMapper.queryVideoByKeys(search);
		int rowCount=courseVideoMapper.pageCount(search);
		PageFinder<CourseVideoVo> pageFinder=new PageFinder<CourseVideoVo>(search.getPage(), search.getPageSize(), rowCount, data);
		return pageFinder;
	}
	
	@Override
	public CourseVideoVo findCourseVideoVoByVideoId(Integer videoId){
		CourseVideo search = new CourseVideo();
		CourseVideoVo  videoVo = null;
		search.setId(videoId);
		List<CourseVideoVo> data = courseVideoMapper.queryVideoByKeys(search);
		if(data != null && data.size() > 0){
			videoVo = data.get(0);
		}
		return videoVo;
	}
}
