package com.yuxin.wx.api.student;

import java.util.List;

import com.yuxin.wx.model.student.StudentAgentMaterial;
import com.yuxin.wx.model.student.StudentPayMaster;
/**
 * Service Interface:StudentAgentMaterial
 * @author wang.zx
 * @date 2014-12-5
 */
public interface IStudentAgentMaterialService  {
	/**
	 * 
	* @Title: saveStudentAgentMaterial
	* @Description: 新增StudentAgentMaterial
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	void insert(StudentAgentMaterial studentAgentMaterial);
	
	/**
	 * 
	* @Title: batchSaveStudentAgentMaterial 
	* @Description: 批量新增StudentAgentMaterial
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	void batchInsert(List<StudentAgentMaterial> studentAgentMaterial);
	
	/**
	 * 
	* @Title: updateStudentAgentMaterial 
	* @Description: 编辑StudentAgentMaterial
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	void update(StudentAgentMaterial studentAgentMaterial);
	
	/**
	 * 
	* @Title: deleteStudentAgentMaterialById 
	* @Description: 根据id删除StudentAgentMaterial
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	void deleteStudentAgentMaterialById(Integer id);
	
	/**
	 * 
	* @Title: deleteStudentAgentMaterialByIds 
	* @Description: 根据id批量删除StudentAgentMaterial
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	void deleteStudentAgentMaterialByIds(Integer[] ids);
	
	/**
	 * 
	* @Title: findStudentAgentMaterialById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	StudentAgentMaterial findStudentAgentMaterialById(Integer id);
	
	/**
	 * 
	* @Title: findStudentAgentMaterialByPage 
	* @Description: 分页查询
	* @return
	* @return List<StudentAgentMaterial>    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	List<StudentAgentMaterial> findStudentAgentMaterialByPage(StudentAgentMaterial search);
 /**
  * 
 * @Title: findStudentAgentMaterialByPage 
 * @Description: 根据学员查资料
 * @return
 * @return List<StudentAgentMaterial>    返回类型 
 * @throws 
 * @exception 
 * @date 2014-12-5
 * @user by chopin
  */
	List<StudentAgentMaterial> findStudentAgentMaterialByStuId (Integer stuId, Integer payMasterId);
 /**
  * 
 * @Title: findStudentAgentMaterialByPage 
 * @Description: 补交资料
 * @return
 * @return List<StudentAgentMaterial>    返回类型 
 * @throws 
 * @exception 
 * @date 2014-12-5
 * @user by chopin
  */
	String repayMaterial(List<StudentAgentMaterial> materials,StudentPayMaster master,Integer userid);
	/**
	 * 
	 * Class Name: IStudentAgentMaterialService.java
	 * @Description: 添加报考材料
	 * @author zhang.zx
	 * @date 2015年4月25日 下午6:50:00
	 * @modifier
	 * @modify-date 2015年4月25日 下午6:50:00
	 * @version 1.0
	 * @param material
	 */
	void insertMaterial(StudentAgentMaterial material);
	
	Integer findMaterialExit(StudentAgentMaterial search);
}