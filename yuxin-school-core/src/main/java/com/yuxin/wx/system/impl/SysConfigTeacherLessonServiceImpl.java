package com.yuxin.wx.system.impl;

import org.apache.shiro.crypto.hash.Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;import com.yuxin.wx.common.BaseServiceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yuxin.wx.api.system.ISysConfigTeacherLessonService;
import com.yuxin.wx.model.system.SysConfigTeacherLesson;
import com.yuxin.wx.system.mapper.SysConfigTeacherLessonMapper;
import com.yuxin.wx.vo.system.SysConfigTeacherLessonVo;

import javax.print.DocFlavor;

/**
 * Service Implementation:SysConfigTeacherLesson
 * @author wang.zx
 * @date 2014-12-5
 */
@Service
@Transactional
public class SysConfigTeacherLessonServiceImpl extends BaseServiceImpl implements ISysConfigTeacherLessonService {

	@Autowired
	private SysConfigTeacherLessonMapper sysConfigTeacherLessonMapper;
	
	/**
	 * 
	* @Title: saveSysConfigTeacherLesson
	* @Description: 新增SysConfigTeacherLesson
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	@Override
	public void insert(SysConfigTeacherLesson sysConfigTeacherLesson){
		sysConfigTeacherLessonMapper.insert(sysConfigTeacherLesson);
	}
	
	/**
	 * 
	* @Title: batchSaveSysConfigTeacherLesson 
	* @Description: 批量新增SysConfigTeacherLesson
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	@Override
	public void batchInsert(List<SysConfigTeacherLesson> sysConfigTeacherLessons){
		if(sysConfigTeacherLessons != null && !sysConfigTeacherLessons.isEmpty()){
			sysConfigTeacherLessonMapper.batchInsert(sysConfigTeacherLessons);
		}
	}
	
	/**
	 * 
	* @Title: updateSysConfigTeacherLesson 
	* @Description: 编辑SysConfigTeacherLesson
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	@Override
	public void update(SysConfigTeacherLesson sysConfigTeacherLesson){
		sysConfigTeacherLessonMapper.update(sysConfigTeacherLesson);
	}
	
	/**
	 * 
	* @Title: deleteSysConfigTeacherLessonById 
	* @Description: 根据id删除SysConfigTeacherLesson
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	@Override
	public void deleteSysConfigTeacherLessonById(Integer id){
		sysConfigTeacherLessonMapper.deleteById(id);
	}
	
	/**
	 * 
	* @Title: deleteSysConfigTeacherLessonByIds 
	* @Description: 根据id批量删除SysConfigTeacherLesson
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	@Override
	public void deleteSysConfigTeacherLessonByIds(Integer[] ids){
		sysConfigTeacherLessonMapper.deleteByIds(ids);
	}
	
	/**
	 * 
	* @Title: findSysConfigTeacherLessonById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	@Override
	public SysConfigTeacherLesson findSysConfigTeacherLessonById(Integer id){
		return sysConfigTeacherLessonMapper.findById(id);
	}
	
	/**
	 * 
	* @Title: findSysConfigTeacherLessonByPage 
	* @Description: 分页查询
	* @return
	* @return List<SysConfigTeacherLesson>    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	@Override
	public List<SysConfigTeacherLesson> findSysConfigTeacherLessonByPage(SysConfigTeacherLesson search){
		Integer totalRecords = sysConfigTeacherLessonMapper.pageCount(search);
		search.setTotalRecords(totalRecords);
		return sysConfigTeacherLessonMapper.page(search);
	}

	@Override
	public List<SysConfigTeacherLessonVo> findByTeacherId(Integer teacherId) {
		List<SysConfigTeacherLessonVo> sysConfigTeacherLessonVos = sysConfigTeacherLessonMapper.findByTeacherId(teacherId);
		return sysConfigTeacherLessonVos;
	}

	@Override
	public void deleteByTeacherId(Integer teacherId) {
		sysConfigTeacherLessonMapper.deleteByTeacherId(teacherId);
	}
	@Override
	public void deleteByTeacherIdNew(Map<String, Integer> map) {
		sysConfigTeacherLessonMapper.deleteByTeacherIdNew(map);
	}

	@Override
	public SysConfigTeacherLesson findSysConfigTeacherLessonByTeaId(Integer id,Integer companyId) {
		// TODO Auto-generated method stub
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("companyId",companyId);
		params.put("id",id);
		return sysConfigTeacherLessonMapper.findSysConfigTeacherLessonByTeaId(params);
	}
	
	
}
