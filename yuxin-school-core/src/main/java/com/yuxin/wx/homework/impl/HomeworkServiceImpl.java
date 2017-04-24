package com.yuxin.wx.homework.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;import com.yuxin.wx.common.BaseServiceImpl;

import com.yuxin.wx.api.homework.IHomeworkService;
import com.yuxin.wx.homework.mapper.HomeworkMapper;
import com.yuxin.wx.homework.mapper.HomeworkStudentCompleteMapper;
import com.yuxin.wx.homework.mapper.HomeworkTeacherReadMapper;
import com.yuxin.wx.model.homework.Homework;
import com.yuxin.wx.model.homework.HomeworkStudentComplete;
import com.yuxin.wx.vo.homework.HomeWorkVo;
import com.yuxin.wx.vo.student.StudentPaySlaveVo;


/**
 * Service Implementation:Homework
 * @author chopin
 * @date 2016-12-15
 */
@Service
@Transactional
public class HomeworkServiceImpl extends BaseServiceImpl implements IHomeworkService {

	@Autowired
	private HomeworkMapper homeworkMapper;
	@Autowired
	private HomeworkStudentCompleteMapper homeworkStudentCompleteMapper;
	@Autowired
	private HomeworkTeacherReadMapper homeworkTeacherReadMapper;
	
	/**
	 * 
	* @Title: saveHomework
	* @Description: 新增Homework
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-12-15
	* @user by chopin
	 */
	@Override
	public void insert(Homework entity){
		homeworkMapper.insert(entity);
	};
	
	/**
	 * 
	* @Title: batchSaveHomework 
	* @Description: 批量新增Homework
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-12-15
	* @user by chopin
	 */
	@Override
	public void batchInsert(List<Homework> entity){
		homeworkMapper.batchInsert(entity);
	};
	
	/**
	 * 
	* @Title: updateHomework 
	* @Description: 编辑Homework
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-12-15
	* @user by chopin
	 */
	@Override
	public void update(Homework entity){
		homeworkMapper.update(entity);
	};
	
	/**
	 * 
	* @Title: deleteHomeworkById 
	* @Description: 根据id删除Homework
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-12-15
	* @user by chopin
	 */
	 @Override
	public void deleteHomeworkById(Integer id){
		homeworkMapper.deleteById(id);
	};
	
	/**
	 * 
	* @Title: deleteHomeworkByIds 
	* @Description: 根据id批量删除Homework
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-12-15
	* @user by chopin
	 */
	@Override
	public void deleteHomeworkByIds(Integer[] ids){
		homeworkMapper.deleteByIds(ids);
	};
	
	/**
	 * 
	* @Title: findHomeworkById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-12-15
	* @user by chopin
	 */
	@Override
	public Homework findHomeworkById(Integer id){
		return homeworkMapper.findById(id);
	};
	
	/**
	 * 
	* @Title: findHomeworkByPage 
	* @Description: 分页查询
	* @return
	* @return List<Homework>    返回类型 
	* @throws 
	* @exception 
	* @date 2016-12-15
	* @user by chopin
	 */
	@Override
	public List<Homework> findHomeworkByPage(Homework search){
		return homeworkMapper.page(search);
	}

	@Override
	public Homework findHomeworkByLessonId(HomeWorkVo lessonId) {
		return homeworkMapper.findHomeworkByLessonId(lessonId);
	}

	@Override
	public void deleteHomeworkHistory(Map<String, Object> map) {
		Integer stuId = (Integer) map.get("stuId");
		Integer courseId = (Integer) map.get("courseId");
		Integer companyId = (Integer) map.get("companyId");
		
		//课程作业
		Map<String, Object> homeworkMap = new HashMap<String, Object>();
		homeworkMap.put("companyId", companyId);
		homeworkMap.put("courseId", courseId);
		homeworkMap.put("stuId", stuId);
		List<HomeworkStudentComplete> stuHomeworklist = homeworkMapper.findHomeworkByCourseIdAndStuId(homeworkMap);
		for (HomeworkStudentComplete hsc : stuHomeworklist) {
			//1.删除学员作业
			homeworkStudentCompleteMapper.deleteById(hsc.getId());
			//2.删除教师批改
			homeworkTeacherReadMapper.deleteByHSCId(hsc.getId());
		}
	};
	
	@Override
	public Integer findLessonStuByCount(HomeWorkVo sps) {
		return homeworkMapper.findLessonStuByCount(sps);
	};
}
