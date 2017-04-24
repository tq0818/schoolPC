package com.yuxin.wx.student.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;import com.yuxin.wx.common.BaseServiceImpl;

import java.util.Date;
import java.util.List;

import com.yuxin.wx.api.student.IStudentAgentMaterialService;
import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.model.student.StudentAgentMaterial;
import com.yuxin.wx.model.student.StudentPayMaster;
import com.yuxin.wx.student.mapper.StudentAgentMaterialMapper;
import com.yuxin.wx.student.mapper.StudentPayMasterMapper;

/**
 * Service Implementation:StudentAgentMaterial
 * @author wang.zx
 * @date 2014-12-5
 */
@Service
@Transactional
public class StudentAgentMaterialServiceImpl extends BaseServiceImpl implements IStudentAgentMaterialService {

	@Autowired
	private StudentAgentMaterialMapper studentAgentMaterialMapper;
	@Autowired
	private StudentPayMasterMapper studentPayMasterMapper;
	
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
	@Override
	public void insert(StudentAgentMaterial studentAgentMaterial){
		studentAgentMaterialMapper.insert(studentAgentMaterial);
	}
	
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
	@Override
	public void batchInsert(List<StudentAgentMaterial> studentAgentMaterials){
		if(studentAgentMaterials != null && !studentAgentMaterials.isEmpty()){
			studentAgentMaterialMapper.batchInsert(studentAgentMaterials);
		}
	}
	
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
	@Override
	public void update(StudentAgentMaterial studentAgentMaterial){
		studentAgentMaterialMapper.update(studentAgentMaterial);
	}
	
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
	@Override
	public void deleteStudentAgentMaterialById(Integer id){
		studentAgentMaterialMapper.deleteById(id);
	}
	
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
	@Override
	public void deleteStudentAgentMaterialByIds(Integer[] ids){
		studentAgentMaterialMapper.deleteByIds(ids);
	}
	
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
	@Override
	public StudentAgentMaterial findStudentAgentMaterialById(Integer id){
		return studentAgentMaterialMapper.findById(id);
	}
	
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
	@Override
	public List<StudentAgentMaterial> findStudentAgentMaterialByPage(StudentAgentMaterial search){
		Integer totalRecords = studentAgentMaterialMapper.pageCount(search);
		search.setTotalRecords(totalRecords);
		return studentAgentMaterialMapper.page(search);
	}
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
	@Override
	public List<StudentAgentMaterial> findStudentAgentMaterialByStuId (Integer stuId, Integer payMasterId){
	    StudentAgentMaterial search =new StudentAgentMaterial();
	    search.setStuId(""+stuId);
	    search.setPayMasterId(payMasterId);
	    return studentAgentMaterialMapper.findStudentAgentMaterialByStuId(search);
	}
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
 @Override
	public String repayMaterial(List<StudentAgentMaterial> materials,StudentPayMaster master,Integer userid){
	 	if(materials !=null){
	 		for(StudentAgentMaterial material : materials){
		        material.setUpdateTime(new Date());
		        material.setUpdator(userid);
		        studentAgentMaterialMapper.update(material);
		    }
	 	}
	    studentPayMasterMapper.update(master);
	    return "success";
	}

	@Override
	public void insertMaterial(StudentAgentMaterial material) {
		studentAgentMaterialMapper.insertMaterial(material);
	}

	@Override
	public Integer findMaterialExit(StudentAgentMaterial material) {
	    return studentAgentMaterialMapper.queryMaterialExit(material);
	}
	
	
}
