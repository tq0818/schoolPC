package com.yuxin.wx.api.student;

import java.util.List;

import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.model.student.StudentStar;
/**
 * Service Interface:StudentStar
 * @author chopin
 * @date 2017-3-24
 */
public interface IStudentStarService  {
	/**
	 * 
	* @Title: saveStudentStar
	* @Description: 新增StudentStar
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2017-3-24
	* @user by wangzx
	 */
	void insert(StudentStar entity);
	
	/**
	 * 
	* @Title: batchSaveStudentStar 
	* @Description: 批量新增StudentStar
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2017-3-24
	* @user by wangzx
	 */
	void batchInsert(List<StudentStar> list);
	
	/**
	 * 
	* @Title: updateStudentStar 
	* @Description: 编辑StudentStar
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2017-3-24
	* @user by wangzx
	 */
	void update(StudentStar entity);
	
	/**
	 * 
	* @Title: deleteStudentStarById 
	* @Description: 根据id删除StudentStar
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2017-3-24
	* @user by wangzx
	 */
	void deleteStudentStarById(Integer id);
	
	/**
	 * 
	* @Title: deleteStudentStarByIds 
	* @Description: 根据id批量删除StudentStar
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2017-3-24
	* @user by wangzx
	 */
	void deleteStudentStarByIds(Integer[] ids);
	
	/**
	 * 
	* @Title: findStudentStarById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2017-3-24
	* @user by wangzx
	 */
	StudentStar findStudentStarById(Integer id);
	
	/**
	 * 
	* @Title: findStudentStarByPage 
	* @Description: 分页查询
	* @return
	* @return List<StudentStar>    返回类型 
	* @throws 
	* @exception 
	* @date 2017-3-24
	* @user by wangzx
	 */
	List<StudentStar> findStudentStarByPage(StudentStar search);

	PageFinder<StudentStar> page(StudentStar studentStar);
}