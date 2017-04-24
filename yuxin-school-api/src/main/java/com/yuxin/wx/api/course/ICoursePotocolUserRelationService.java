package com.yuxin.wx.api.course;


import java.util.List;

import com.yuxin.wx.model.course.CoursePotocolUserRelation;
import com.yuxin.wx.model.course.CourseProtocolConfig;
import com.yuxin.wx.vo.course.ProtocolCourseOrPackageRelation;
/**
 * Service Interface:CoursePotocolUserRelation
 * @author chopin
 * @date 2016-11-1
 */
public interface ICoursePotocolUserRelationService  {
	/**
	 * 
	* @Title: saveCoursePotocolUserRelation
	* @Description: 新增CoursePotocolUserRelation
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-11-1
	* @user by wangzx
	 */
	void insert(CoursePotocolUserRelation entity);
	
	/**
	 * 
	* @Title: batchSaveCoursePotocolUserRelation 
	* @Description: 批量新增CoursePotocolUserRelation
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-11-1
	* @user by wangzx
	 */
	void batchInsert(List<CoursePotocolUserRelation> list);
	
	/**
	 * 
	* @Title: updateCoursePotocolUserRelation 
	* @Description: 编辑CoursePotocolUserRelation
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-11-1
	* @user by wangzx
	 */
	void update(CoursePotocolUserRelation entity);
	
	/**
	 * 
	* @Title: deleteCoursePotocolUserRelationById 
	* @Description: 根据id删除CoursePotocolUserRelation
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-11-1
	* @user by wangzx
	 */
	void deleteCoursePotocolUserRelationById(Integer id);
	
	/**
	 * 
	* @Title: deleteCoursePotocolUserRelationByIds 
	* @Description: 根据id批量删除CoursePotocolUserRelation
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-11-1
	* @user by wangzx
	 */
	void deleteCoursePotocolUserRelationByIds(Integer[] ids);
	
	/**
	 * 
	* @Title: findCoursePotocolUserRelationById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-11-1
	* @user by wangzx
	 */
	CoursePotocolUserRelation findCoursePotocolUserRelationById(Integer id);
	
	/**
	 * 
	* @Title: findCoursePotocolUserRelationByPage 
	* @Description: 分页查询
	* @return
	* @return List<CoursePotocolUserRelation>    返回类型 
	* @throws 
	* @exception 
	* @date 2016-11-1
	* @user by wangzx
	 */
	List<CoursePotocolUserRelation> findCoursePotocolUserRelationByPage(CoursePotocolUserRelation search);

	public List<ProtocolCourseOrPackageRelation> queryProtocolRelationCourseOrCoursePackage(CourseProtocolConfig search);

	public int queryProtocolRelationCourseOrCoursePackageCount(CourseProtocolConfig search);

	public List<ProtocolCourseOrPackageRelation> queryHistoryBindCourseOrPackage(CourseProtocolConfig search);
	
	public int queryHistoryBindCourseOrPackageCount(CourseProtocolConfig search);
}