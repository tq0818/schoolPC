package com.yuxin.wx.api.student;

import java.util.List;
import com.yuxin.wx.model.student.StudentPayChangeInfo;
/**
 * Service Interface:StudentPayChangeInfo
 * @author wang.zx
 * @date 2014-12-5
 */
public interface IStudentPayChangeInfoService  {
	/**
	 * 
	* @Title: saveStudentPayChangeInfo
	* @Description: 新增StudentPayChangeInfo
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	void insert(StudentPayChangeInfo studentPayChangeInfo);
	
	/**
	 * 
	* @Title: batchSaveStudentPayChangeInfo 
	* @Description: 批量新增StudentPayChangeInfo
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	void batchInsert(List<StudentPayChangeInfo> studentPayChangeInfo);
	
	/**
	 * 
	* @Title: updateStudentPayChangeInfo 
	* @Description: 编辑StudentPayChangeInfo
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	void update(StudentPayChangeInfo studentPayChangeInfo);
	
	/**
	 * 
	* @Title: deleteStudentPayChangeInfoById 
	* @Description: 根据id删除StudentPayChangeInfo
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	void deleteStudentPayChangeInfoById(Integer id);
	
	/**
	 * 
	* @Title: deleteStudentPayChangeInfoByIds 
	* @Description: 根据id批量删除StudentPayChangeInfo
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	void deleteStudentPayChangeInfoByIds(Integer[] ids);
	
	/**
	 * 
	* @Title: findStudentPayChangeInfoById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	StudentPayChangeInfo findStudentPayChangeInfoById(Integer id);
	
	/**
	 * 
	* @Title: findStudentPayChangeInfoByPage 
	* @Description: 分页查询
	* @return
	* @return List<StudentPayChangeInfo>    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	List<StudentPayChangeInfo> findStudentPayChangeInfoByPage(StudentPayChangeInfo search);
}