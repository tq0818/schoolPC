package com.yuxin.wx.api.student;

import java.util.List;
import com.yuxin.wx.model.student.StudentPayLog;
/**
 * Service Interface:StudentPayLog
 * @author wang.zx
 * @date 2014-12-5
 */
public interface IStudentPayLogService  {
	/**
	 * 
	* @Title: saveStudentPayLog
	* @Description: 新增StudentPayLog
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	void insert(StudentPayLog studentPayLog);
	
	/**
	 * 
	* @Title: batchSaveStudentPayLog 
	* @Description: 批量新增StudentPayLog
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	void batchInsert(List<StudentPayLog> studentPayLog);
	
	/**
	 * 
	* @Title: updateStudentPayLog 
	* @Description: 编辑StudentPayLog
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	void update(StudentPayLog studentPayLog);
	
	/**
	 * 
	* @Title: deleteStudentPayLogById 
	* @Description: 根据id删除StudentPayLog
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	void deleteStudentPayLogById(Integer id);
	
	/**
	 * 
	* @Title: deleteStudentPayLogByIds 
	* @Description: 根据id批量删除StudentPayLog
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	void deleteStudentPayLogByIds(Integer[] ids);
	
	/**
	 * 
	* @Title: findStudentPayLogById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	StudentPayLog findStudentPayLogById(Integer id);
	
	/**
	 * 
	* @Title: findStudentPayLogByPage 
	* @Description: 分页查询
	* @return
	* @return List<StudentPayLog>    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	List<StudentPayLog> findStudentPayLogByPage(StudentPayLog search);
}