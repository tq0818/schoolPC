package com.yuxin.wx.course.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;import com.yuxin.wx.common.BaseServiceImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import com.yuxin.wx.api.course.ICourseVideoChapterService;
import com.yuxin.wx.model.course.CourseAfterTest;
import com.yuxin.wx.model.course.CourseVideoChapter;
import com.yuxin.wx.model.course.CourseVideoLecture;
import com.yuxin.wx.vo.course.ChapterAndLectureListVo;
import com.yuxin.wx.vo.course.LectureAndTestVo;
import com.yuxin.wx.course.mapper.CourseAfterTestMapper;
import com.yuxin.wx.course.mapper.CourseVideoChapterMapper;
import com.yuxin.wx.course.mapper.CourseVideoLectureMapper;

/**
 * Service Implementation:CourseVideoChapter
 * @author wang.zx
 * @date 2014-12-5
 */
@Service
@Transactional
public class CourseVideoChapterServiceImpl extends BaseServiceImpl implements ICourseVideoChapterService {

	@Autowired
	private CourseVideoChapterMapper courseVideoChapterMapper;
	
	@Autowired
	private CourseVideoLectureMapper courseVideoLectureMapper;
	
	@Autowired
	private CourseAfterTestMapper courseAfterTestMapper;
	
	/**
	 * 
	* @Title: saveCourseVideoChapter
	* @Description: 新增CourseVideoChapter
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	@Override
	public void insert(CourseVideoChapter courseVideoChapter){
		courseVideoChapterMapper.insert(courseVideoChapter);
	}
	
	/**
	 * 
	* @Title: batchSaveCourseVideoChapter 
	* @Description: 批量新增CourseVideoChapter
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	@Override
	public void batchInsert(List<CourseVideoChapter> courseVideoChapters){
		if(courseVideoChapters != null && !courseVideoChapters.isEmpty()){
			courseVideoChapterMapper.batchInsert(courseVideoChapters);
		}
	}
	
	/**
	 * 
	* @Title: updateCourseVideoChapter 
	* @Description: 编辑CourseVideoChapter
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	@Override
	public void update(CourseVideoChapter courseVideoChapter){
		courseVideoChapterMapper.update(courseVideoChapter);
	}
	
	/**
	 * 
	* @Title: deleteCourseVideoChapterById 
	* @Description: 根据id删除CourseVideoChapter
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	@Override
	public void deleteCourseVideoChapterById(Integer id){
		courseVideoChapterMapper.deleteById(id);
	}
	
	/**
	 * 
	* @Title: deleteCourseVideoChapterByIds 
	* @Description: 根据id批量删除CourseVideoChapter
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	@Override
	public void deleteCourseVideoChapterByIds(Integer[] ids){
		courseVideoChapterMapper.deleteByIds(ids);
	}
	
	/**
	 * 
	* @Title: findCourseVideoChapterById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	@Override
	public CourseVideoChapter findCourseVideoChapterById(Integer id){
		return courseVideoChapterMapper.findById(id);
	}
	
	/**
	 * 
	* @Title: findCourseVideoChapterByPage 
	* @Description: 分页查询
	* @return
	* @return List<CourseVideoChapter>    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	@Override
	public List<CourseVideoChapter> findCourseVideoChapterByPage(CourseVideoChapter search){
		Integer totalRecords = courseVideoChapterMapper.pageCount(search);
		search.setTotalRecords(totalRecords);
		return courseVideoChapterMapper.page(search);
	}

	@Override
	public List<ChapterAndLectureListVo> findChapterAndLectureByVideoId(
			Integer id) {
		return courseVideoChapterMapper.findChapterAndLectureByVideoId(id);
	}
	
	/**
	 * 
	* @Title: findCourseVideoChapterByPage 
	* @Description: 分页查询
	* @return
	* @return List<CourseVideoChapter>    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by chopin
	 */
	@Override
	public List<ChapterAndLectureListVo> findChapterAndLectureByModuleId(Integer id) {
		List<CourseVideoChapter> chapters=courseVideoChapterMapper.findByModuleId(id);
		List<ChapterAndLectureListVo> vos=new ArrayList<ChapterAndLectureListVo> ();
		for(CourseVideoChapter chapter : chapters){
			ChapterAndLectureListVo vo=new ChapterAndLectureListVo();
			List<LectureAndTestVo> lasv = new ArrayList<LectureAndTestVo>();
			List<CourseVideoLecture> lectures=courseVideoLectureMapper.findCourseVideoLectureByChapterId(chapter.getId());
			for (CourseVideoLecture cl : lectures) {
				LectureAndTestVo lt = new LectureAndTestVo();
				if(cl != null){
					lt.setType(1);
					lt.setId(cl.getId());
					lt.setChapterId(cl.getChapterId());
					lt.setOrder(cl.getLectureOrder());
					lt.setName(cl.getLectureName());
					lt.setUpdater(cl.getUpdator());
					lt.setUpdateTime(cl.getUpdateTime());
					lt.setDelFlag(cl.getDelFlag());
					lt.setVideoId(cl.getVideoId());
					lt.setPublishStatus(cl.getPublishStatus());
					lt.setPublishDate(cl.getPublishDate());
					lt.setFreeFlag(cl.getFreeFlag());
					lt.setCreator(cl.getCreator());
					lt.setCreateTime(cl.getCreateTime());
					lt.setFileId(cl.getFileId());
					lt.setFileType(cl.getFileType());
					lasv.add(lt);
				}
			}
			List<CourseAfterTest> tests = courseAfterTestMapper.findTestListByChapterId(chapter.getId());
			for (CourseAfterTest ct : tests) {
				LectureAndTestVo lt = new LectureAndTestVo();
				if(ct != null){
					lt.setId(ct.getId());
					lt.setChapterId(ct.getChapterId());
					lt.setOrder(ct.getSort());
					lt.setType(2);
					lt.setName(ct.getTestName());
					lt.setUpdater(ct.getUpdater());
					lt.setUpdateTime(ct.getUpdateTime());
					lt.setDelFlag(ct.getDelFlag());
					lt.setTestScore(ct.getTestScore());
					lt.setTestTotalNum(ct.getTestTotalNum());
					lt.setAllowContinue(ct.getAllowContinue());
					lt.setScore(ct.getScore());
					lt.setPassFlag(ct.getPassFlag());
					lt.setTestType(ct.getTestType());
					lasv.add(lt);
				}
			}
			Collections.sort(lasv);
			vo.setId(chapter.getId());
			vo.setChapterName(chapter.getChapterName());
			vo.setChapterOrder(chapter.getChapterOrder());
			vo.setModuleId(chapter.getModuleId());
			vo.setLectures(lectures);
			vo.setLectureAndTests(lasv);
			vos.add(vo);
		}
		return vos;
	}

	@Override
	public List<CourseVideoChapter> findChapterByModuleId(Integer moduleId) {
		// TODO Auto-generated method stub
		return courseVideoChapterMapper.findByModuleId(moduleId);
	}

	@Override
	public List<Integer> findIdByModuleId(Integer id) {
		return courseVideoChapterMapper.findIdByModuleId(id);
	}

	@Override
	public CourseVideoChapter findByChapterNameAndModuleId(CourseVideoChapter chapter) {
		return courseVideoChapterMapper.findByChapterNameAndModuleId(chapter);
	}
}
