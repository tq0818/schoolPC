package com.yuxin.wx.student.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;import com.yuxin.wx.common.BaseServiceImpl;

import com.yuxin.wx.api.student.IStudentGroupService;
import com.yuxin.wx.model.student.StudentGroup;
import com.yuxin.wx.student.mapper.StudentGroupMapper;


/**
 * Service Implementation:StudentGroup
 * @author chopin
 * @date 2016-7-29
 */
@Service
@Transactional
public class StudentGroupServiceImpl extends BaseServiceImpl implements IStudentGroupService {

	@Autowired
	private StudentGroupMapper studentGroupMapper;
	
	/**
	 * 
	* @Title: saveStudentGroup
	* @Description: 新增StudentGroup
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-7-29
	* @user by chopin
	 */
	@Override
	public void insert(StudentGroup entity){
		studentGroupMapper.insert(entity);
	};
	
	/**
	 * 
	* @Title: batchSaveStudentGroup 
	* @Description: 批量新增StudentGroup
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-7-29
	* @user by chopin
	 */
	@Override
	public void batchInsert(List<StudentGroup> entity){
		studentGroupMapper.batchInsert(entity);
	};
	
	/**
	 * 
	* @Title: updateStudentGroup 
	* @Description: 编辑StudentGroup
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-7-29
	* @user by chopin
	 */
	@Override
	public void update(StudentGroup entity){
		studentGroupMapper.update(entity);
	};
	
	/**
	 * 
	* @Title: deleteStudentGroupById 
	* @Description: 根据id删除StudentGroup
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-7-29
	* @user by chopin
	 */
	 @Override
	public void deleteStudentGroupById(Integer id){
		studentGroupMapper.deleteById(id);
	};
	
	/**
	 * 
	* @Title: deleteStudentGroupByIds 
	* @Description: 根据id批量删除StudentGroup
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-7-29
	* @user by chopin
	 */
	@Override
	public void deleteStudentGroupByIds(Integer[] ids){
		studentGroupMapper.deleteByIds(ids);
	};
	
	/**
	 * 
	* @Title: findStudentGroupById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-7-29
	* @user by chopin
	 */
	@Override
	public StudentGroup findStudentGroupById(Integer id){
		return studentGroupMapper.findById(id);
	};
	
	/**
	 * 
	* @Title: findStudentGroupByPage 
	* @Description: 分页查询
	* @return
	* @return List<StudentGroup>    返回类型 
	* @throws 
	* @exception 
	* @date 2016-7-29
	* @user by chopin
	 */
	@Override
	public List<StudentGroup> findStudentGroupByPage(StudentGroup search){
		return studentGroupMapper.page(search);
	}

	@Override
	public List<StudentGroup>  findStudentGroup1ByCompanyId(StudentGroup search) {
		return studentGroupMapper.findStudentGroup1ByCompanyId(search);
	}

	@Override
	public List<StudentGroup>  findStudentGroup2ByCompanyIdAndPId(StudentGroup search) {
		return studentGroupMapper.findStudentGroup2ByCompanyIdAndPId(search);
	}

	@Override
	public Integer checkGroupName1(StudentGroup search) {
		return studentGroupMapper.checkGroupName1(search);
	}
	
	@Override
	public Integer checkGroupName2(StudentGroup search) {
		return studentGroupMapper.checkGroupName2(search);
	}

	@Override
	public void deleteStudentGroupByParentId(Integer id) {
		studentGroupMapper.deleteByParentId(id);
	};
}
