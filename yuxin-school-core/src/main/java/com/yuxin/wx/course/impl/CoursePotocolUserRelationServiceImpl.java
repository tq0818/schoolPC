package com.yuxin.wx.course.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;import com.yuxin.wx.common.BaseServiceImpl;

import com.yuxin.wx.api.course.ICoursePotocolUserRelationService;
import com.yuxin.wx.course.mapper.CoursePotocolUserRelationMapper;
import com.yuxin.wx.model.course.CoursePotocolUserRelation;
import com.yuxin.wx.model.course.CourseProtocolConfig;
import com.yuxin.wx.vo.course.ProtocolCourseOrPackageRelation;

/**
 * Service Implementation:CoursePotocolUserRelation
 * @author chopin
 * @date 2016-11-1
 */
@Service
@Transactional
public class CoursePotocolUserRelationServiceImpl extends BaseServiceImpl implements ICoursePotocolUserRelationService {

	@Autowired
	private CoursePotocolUserRelationMapper coursePotocolUserRelationMapper;
	
	/**
	 * 
	* @Title: saveCoursePotocolUserRelation
	* @Description: 新增CoursePotocolUserRelation
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-11-1
	* @user by chopin
	 */
	@Override
	public void insert(CoursePotocolUserRelation entity){
		coursePotocolUserRelationMapper.insert(entity);
	};
	
	/**
	 * 
	* @Title: batchSaveCoursePotocolUserRelation 
	* @Description: 批量新增CoursePotocolUserRelation
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-11-1
	* @user by chopin
	 */
	@Override
	public void batchInsert(List<CoursePotocolUserRelation> entity){
		coursePotocolUserRelationMapper.batchInsert(entity);
	};
	
	/**
	 * 
	* @Title: updateCoursePotocolUserRelation 
	* @Description: 编辑CoursePotocolUserRelation
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-11-1
	* @user by chopin
	 */
	@Override
	public void update(CoursePotocolUserRelation entity){
		coursePotocolUserRelationMapper.update(entity);
	};
	
	/**
	 * 
	* @Title: deleteCoursePotocolUserRelationById 
	* @Description: 根据id删除CoursePotocolUserRelation
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-11-1
	* @user by chopin
	 */
	 @Override
	public void deleteCoursePotocolUserRelationById(Integer id){
		coursePotocolUserRelationMapper.deleteById(id);
	};
	
	/**
	 * 
	* @Title: deleteCoursePotocolUserRelationByIds 
	* @Description: 根据id批量删除CoursePotocolUserRelation
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-11-1
	* @user by chopin
	 */
	@Override
	public void deleteCoursePotocolUserRelationByIds(Integer[] ids){
		coursePotocolUserRelationMapper.deleteByIds(ids);
	};
	
	/**
	 * 
	* @Title: findCoursePotocolUserRelationById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-11-1
	* @user by chopin
	 */
	@Override
	public CoursePotocolUserRelation findCoursePotocolUserRelationById(Integer id){
		return coursePotocolUserRelationMapper.findById(id);
	};
	
	/**
	 * 
	* @Title: findCoursePotocolUserRelationByPage 
	* @Description: 分页查询
	* @return
	* @return List<CoursePotocolUserRelation>    返回类型 
	* @throws 
	* @exception 
	* @date 2016-11-1
	* @user by chopin
	 */
	@Override
	public List<CoursePotocolUserRelation> findCoursePotocolUserRelationByPage(CoursePotocolUserRelation search){
		return coursePotocolUserRelationMapper.page(search);
	}

	@Override
	public List<ProtocolCourseOrPackageRelation> queryProtocolRelationCourseOrCoursePackage(
			CourseProtocolConfig search) {
		return coursePotocolUserRelationMapper.queryProtocolRelationCourseOrCoursePackage(search);
	}

	@Override
	public int queryProtocolRelationCourseOrCoursePackageCount(CourseProtocolConfig search) {
		return coursePotocolUserRelationMapper.queryProtocolRelationCourseOrCoursePackageCount(search);
	}

	@Override
	public List<ProtocolCourseOrPackageRelation> queryHistoryBindCourseOrPackage(CourseProtocolConfig search) {
		return coursePotocolUserRelationMapper.queryHistoryBindCourseOrPackage(search);
	}

	@Override
	public int queryHistoryBindCourseOrPackageCount(CourseProtocolConfig search) {
		return coursePotocolUserRelationMapper.queryHistoryBindCourseOrPackageCount(search);
	};
}
