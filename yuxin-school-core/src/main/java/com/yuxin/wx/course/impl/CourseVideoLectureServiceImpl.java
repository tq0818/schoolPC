package com.yuxin.wx.course.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;import com.yuxin.wx.common.BaseServiceImpl;

import com.yuxin.wx.api.course.ICourseVideoLectureService;
import com.yuxin.wx.course.mapper.CourseVideoLectureMapper;
import com.yuxin.wx.model.course.CourseVideoLecture;

/**
 * Service Implementation:CourseVideoLecture
 * @author wang.zx
 * @date 2014-12-5
 */
@Service
@Transactional
public class CourseVideoLectureServiceImpl extends BaseServiceImpl implements ICourseVideoLectureService {

	@Autowired
	private CourseVideoLectureMapper courseVideoLectureMapper;
	
	/**
	 * 
	* @Title: saveCourseVideoLecture
	* @Description: 新增CourseVideoLecture
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	@Override
	public void insert(CourseVideoLecture courseVideoLecture){
		courseVideoLectureMapper.insert(courseVideoLecture);
	}
	
	/**
	 * 
	* @Title: batchSaveCourseVideoLecture 
	* @Description: 批量新增CourseVideoLecture
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	@Override
	public void batchInsert(List<CourseVideoLecture> courseVideoLectures){
		if(courseVideoLectures != null && !courseVideoLectures.isEmpty()){
			courseVideoLectureMapper.batchInsert(courseVideoLectures);
		}
	}
	
	/**
	 * 
	* @Title: updateCourseVideoLecture 
	* @Description: 编辑CourseVideoLecture
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	@Override
	public void update(CourseVideoLecture courseVideoLecture){
		courseVideoLectureMapper.update(courseVideoLecture);
	}
	
	/**
	 * 
	* @Title: deleteCourseVideoLectureById 
	* @Description: 根据id删除CourseVideoLecture
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	@Override
	public void deleteCourseVideoLectureById(Integer id){
		courseVideoLectureMapper.deleteById(id);
	}
	
	/**
	 * 
	* @Title: deleteCourseVideoLectureByIds 
	* @Description: 根据id批量删除CourseVideoLecture
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	@Override
	public void deleteCourseVideoLectureByIds(Integer[] ids){
		courseVideoLectureMapper.deleteByIds(ids);
	}
	
	/**
	 * 
	* @Title: findCourseVideoLectureById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	@Override
	public CourseVideoLecture findCourseVideoLectureById(Integer id){
		return courseVideoLectureMapper.findById(id);
	}
	
	/**
	 * 
	* @Title: findCourseVideoLectureByPage 
	* @Description: 分页查询
	* @return
	* @return List<CourseVideoLecture>    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	@Override
	public List<CourseVideoLecture> findCourseVideoLectureByPage(CourseVideoLecture search){
		Integer totalRecords = courseVideoLectureMapper.pageCount(search);
		search.setTotalRecords(totalRecords);
		return courseVideoLectureMapper.page(search);
	}
	/**
	 * 
	* @Title: mvLecture 
	* @Description: 改变节所属章
	* @return
	* @return List<CourseVideoLecture>    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	@Override
	public void mvLecture(Integer target,Integer lecture){
		Map<String,Integer> map=new HashMap<String,Integer>();
		map.put("target", target);
		map.put("lecture", lecture);
		courseVideoLectureMapper.mvLecture(map);
	}

	@Override
	public List<CourseVideoLecture> queryVideoLectureByChapterId(
			Integer chapterId) {
		// TODO Auto-generated method stub
		return courseVideoLectureMapper.findCourseVideoLectureByChapterId(chapterId);
	}
	
	@Override
	public List<CourseVideoLecture> findVideoLectureByChapterId(Integer chapterId) {
		return courseVideoLectureMapper.findVideoLectureByChapterId(chapterId);
	}

	@Override
	public List<Integer> findIdByChapterId(Integer id) {
		return courseVideoLectureMapper.findIdByChapterId(id);
	}

	@Override
	public CourseVideoLecture findByChapterIdAndLecName(CourseVideoLecture lecture) {
		return courseVideoLectureMapper.findByChapterIdAndLecName(lecture);
	}
	
}
