package com.yuxin.wx.classes.impl;

import com.yuxin.wx.company.mapper.CompanyLiveConfigMapper;
import com.yuxin.wx.model.company.CompanyLiveConfig;
import com.yuxin.wx.user.mapper.UsersFrontMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;import com.yuxin.wx.common.BaseServiceImpl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yuxin.wx.api.classes.IClassModuleLessonService;
import com.yuxin.wx.model.classes.ClassModuleLesson;
import com.yuxin.wx.vo.classes.ClassModuleLessonVo;
import com.yuxin.wx.vo.classes.CmlVo;
import com.yuxin.wx.vo.classes.LessonVo;
import com.yuxin.wx.classes.mapper.ClassModuleLessonMapper;
import com.yuxin.wx.common.PageFinder;

/**
 * Service Implementation:ClassModuleLesson
 * @author wang.zx
 * @date 2014-12-5
 */
@Service
@Transactional
public class ClassModuleLessonServiceImpl extends BaseServiceImpl implements IClassModuleLessonService {

	@Autowired
	private ClassModuleLessonMapper classModuleLessonMapper;

	@Autowired
	private CompanyLiveConfigMapper companyLiveConfigMapper;

	@Autowired
	private UsersFrontMapper usersFrontMapper;



	/**
	 * 
	* @Title: saveClassModuleLesson
	* @Description: 新增ClassModuleLesson
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	@Override
	public void insert(ClassModuleLesson classModuleLesson){
		classModuleLessonMapper.insert(classModuleLesson);
	}
	
	@Override
	public void insert1(ClassModuleLesson classModuleLesson){
		classModuleLessonMapper.insert1(classModuleLesson);
	}

	/**
	 * 
	* @Title: batchSaveClassModuleLesson 
	* @Description: 批量新增ClassModuleLesson
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	@Override
	public void batchInsert(List<ClassModuleLesson> classModuleLessons){
		if(classModuleLessons != null && !classModuleLessons.isEmpty()){
			classModuleLessonMapper.batchInsert(classModuleLessons);
		}
	}
	
	/**
	 * 
	* @Title: updateClassModuleLesson 
	* @Description: 编辑ClassModuleLesson
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	@Override
	public void update(ClassModuleLesson classModuleLesson){
		classModuleLessonMapper.update(classModuleLesson);
	}
	
	/**
	 * 
	* @Title: deleteClassModuleLessonById 
	* @Description: 根据id删除ClassModuleLesson
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	@Override
	public void deleteClassModuleLessonById(Integer id){
		classModuleLessonMapper.deleteById(id);
	}
	
	/**
	 * 
	* @Title: deleteClassModuleLessonByIds 
	* @Description: 根据id批量删除ClassModuleLesson
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	@Override
	public void deleteClassModuleLessonByIds(Integer[] ids){
		classModuleLessonMapper.deleteByIds(ids);
	}
	
	/**
	 * 
	* @Title: findClassModuleLessonById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	@Override
	public ClassModuleLesson findClassModuleLessonById(Integer id){
		return classModuleLessonMapper.findById(id);
	}
	
	/**
	 * 
	* @Title: findClassModuleLessonByPage 
	* @Description: 分页查询
	* @return
	* @return List<ClassModuleLesson>    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	@Override
	public List<ClassModuleLesson> findClassModuleLessonByPage(ClassModuleLesson search){
		Integer totalRecords = classModuleLessonMapper.pageCount(search);
		search.setTotalRecords(totalRecords);
		return classModuleLessonMapper.page(search);
	}

	@Override
	public List<ClassModuleLesson> findClassModuleLessonByClassroomId(
			Integer classroomId) {
		return classModuleLessonMapper.findClassModuleLessonByClassroomId(classroomId);
	}
	
	@Override
	public List<ClassModuleLesson> findClassModuleLessonByModuleNoId(
			Integer moduleNoId) {
		return classModuleLessonMapper.findClassModuleLessonByModuleNoId(moduleNoId);
	}
	
	@Override
	public List<ClassModuleLesson> findClassModuleLessonByModuleNoId1(
			Integer moduleNoId) {
		return classModuleLessonMapper.findClassModuleLessonByModuleNoId1(moduleNoId);
	}

	@Override
	public List<ClassModuleLesson> findcmlByModuleNoId(Integer moduleNoId) {
		return classModuleLessonMapper.findcmlByModuleNoId(moduleNoId);
	}
	
	@Override
	public PageFinder<ClassModuleLessonVo> findClassModuleLessonByKeys(
			ClassModuleLessonVo search) {
		List<ClassModuleLessonVo> classModuleLessonVos=classModuleLessonMapper.findClassModuleLessonByKeys(search);
		int rowCount=classModuleLessonMapper.findClassModuleLessonByKeysCount(search);
		PageFinder<ClassModuleLessonVo> pageFinder=new PageFinder<ClassModuleLessonVo>(search.getPage(), search.getPageSize(), rowCount, classModuleLessonVos);
		return pageFinder;
	}

	@Override
	public ClassModuleLessonVo findClassModuleLessonById(
			ClassModuleLessonVo search) {
		ClassModuleLessonVo classModuleLessonVo=classModuleLessonMapper.findClassModuleLessonById(search);
		return classModuleLessonVo;
	}

	@Override
	public List<ClassModuleLesson> findLessonsByRoomIdAndDate(Integer roomId,Date startDate, Date endDate) {
		return classModuleLessonMapper.findLessonsByRoomIdAndDate(roomId, startDate, endDate);
	}

	@Override
	public List<ClassModuleLesson> findLessonInfoByRoomIdAndDate(
			ClassModuleLesson cml) {
		// TODO Auto-generated method stub
		return classModuleLessonMapper.findLessonInfoByRoomIdAndDate(cml);
	}
	@Override
	public List<ClassModuleLesson> findLessonByTeachers(
			ClassModuleLesson classModuleLesson) {
		// TODO Auto-generated method stub
		return classModuleLessonMapper.findLessonByTeachers(classModuleLesson);
	}

	@Override
	public List<LessonVo> findByDate(String date) {
		// TODO Auto-generated method stub
		return classModuleLessonMapper.findByDate(date);
	}

	@Override
	public List<String> findLiveByTime(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return classModuleLessonMapper.findLiveByTime(param);
	}

	@Override
	public void mvLesson(Map<String, Object> param) {
		// TODO Auto-generated method stub
		classModuleLessonMapper.mvLesson(param);
	}

	@Override
	public List<ClassModuleLesson> findclassLessonByName(String lessonName,Integer moduleNo) {
		// TODO Auto-generated method stub
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("lessonName", lessonName);
		map.put("moduleNo", moduleNo);
		return classModuleLessonMapper.findclassLessonByName(map);
	}

	@Override
	public List<CmlVo> findZsLiveByTime(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return classModuleLessonMapper.findZsLiveByTime(param);
	}

	@Override
	public List<Integer> findClassModuleLessonIdsByModuleNoId(Integer id) {
		return classModuleLessonMapper.findClassModuleLessonIdsByModuleNoId(id);
	}

	@Override
	public List<ClassModuleLesson> findLessonByCommodityId(Integer id) {
		Map<String,Object> map = new HashMap<>();
		map.put("id",id);
		return classModuleLessonMapper.findLessonByCommodityId(map);
	}

	@Override
	public List<ClassModuleLesson> findLessonByCommodityIdNotDel(Integer id) {
		Map<String,Object> map = new HashMap<>();
		map.put("id",id);
		return classModuleLessonMapper.findLessonByCommodityIdNotDel(map);
	}

	@Override
	public List<ClassModuleLesson> findLessonByCommodityIds(String[] ids) {
		return classModuleLessonMapper.findLessonByCommodityIds(ids);
	}

	@Override
	public CompanyLiveConfig queryCompanyLiveConfigByCompanyId(String companyId) {
		if(companyId==null||companyId=="") return null;
		return companyLiveConfigMapper.findByCompanyId(Integer.valueOf(companyId));
	}

	@Override
	public String findNickNameByUserFrontId(String userid) {
		return usersFrontMapper.findNickNameByUserFrontId(userid);
	}

}
