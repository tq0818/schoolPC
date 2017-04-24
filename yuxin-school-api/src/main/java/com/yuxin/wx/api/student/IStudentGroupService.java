package com.yuxin.wx.api.student;

import java.util.List;

import com.yuxin.wx.model.student.StudentGroup;
/**
 * Service Interface:StudentGroup
 * @author chopin
 * @date 2016-7-29
 */
public interface IStudentGroupService  {
	/**
	 * 
	* @Title: saveStudentGroup
	* @Description: 新增StudentGroup
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-7-29
	* @user by wangzx
	 */
	void insert(StudentGroup entity);
	
	/**
	 * 
	* @Title: batchSaveStudentGroup 
	* @Description: 批量新增StudentGroup
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-7-29
	* @user by wangzx
	 */
	void batchInsert(List<StudentGroup> list);
	
	/**
	 * 
	* @Title: updateStudentGroup 
	* @Description: 编辑StudentGroup
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-7-29
	* @user by wangzx
	 */
	void update(StudentGroup entity);
	
	/**
	 * 
	* @Title: deleteStudentGroupById 
	* @Description: 根据id删除StudentGroup
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-7-29
	* @user by wangzx
	 */
	void deleteStudentGroupById(Integer id);
	
	/**
	 * 
	* @Title: deleteStudentGroupByIds 
	* @Description: 根据id批量删除StudentGroup
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-7-29
	* @user by wangzx
	 */
	void deleteStudentGroupByIds(Integer[] ids);
	
	/**
	 * 
	* @Title: findStudentGroupById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-7-29
	* @user by wangzx
	 */
	StudentGroup findStudentGroupById(Integer id);
	
	/**
	 * 
	* @Title: findStudentGroupByPage 
	* @Description: 分页查询
	* @return
	* @return List<StudentGroup>    返回类型 
	* @throws 
	* @exception 
	* @date 2016-7-29
	* @user by wangzx
	 */
	List<StudentGroup> findStudentGroupByPage(StudentGroup search);
	
	/**
	 * 
	 * Class Name: IStudentGroupService.java
	 * @Description: 查询一级分组
	 * @author dongshuai
	 * @date 2016年8月1日 下午2:23:03
	 * @modifier
	 * @modify-date 2016年8月1日 下午2:23:03
	 * @version 1.0
	 * @param search
	 * @return
	 */
	List<StudentGroup>  findStudentGroup1ByCompanyId(StudentGroup search);
	
	/**
	 * 
	 * Class Name: IStudentGroupService.java
	 * @Description: 查询二级分组
	 * @author dongshuai
	 * @date 2016年8月1日 下午2:23:46
	 * @modifier
	 * @modify-date 2016年8月1日 下午2:23:46
	 * @version 1.0
	 * @param search
	 * @return
	 */
	List<StudentGroup>  findStudentGroup2ByCompanyIdAndPId(StudentGroup search);
	
	/**
	 * 
	 * Class Name: IStudentGroupService.java
	 * @Description: 检查分组重名
	 * @author dongshuai
	 * @date 2016年8月1日 下午9:14:55
	 * @modifier
	 * @modify-date 2016年8月1日 下午9:14:55
	 * @version 1.0
	 * @param search
	 * @return
	 */
	Integer checkGroupName1(StudentGroup search);
	
	/**
	 * 
	 * Class Name: IStudentGroupService.java
	 * @Description: 检查分组重名
	 * @author dongshuai
	 * @date 2016年8月1日 下午9:14:55
	 * @modifier
	 * @modify-date 2016年8月1日 下午9:14:55
	 * @version 1.0
	 * @param search
	 * @return
	 */
	Integer checkGroupName2(StudentGroup search);
	
	/**
	 * 
	 * Class Name: IStudentGroupService.java
	 * @Description: 删除二级分组
	 * @author dongshuai
	 * @date 2016年8月4日 下午6:35:59
	 * @modifier
	 * @modify-date 2016年8月4日 下午6:35:59
	 * @version 1.0
	 * @param id
	 */
	void deleteStudentGroupByParentId(Integer id);
}