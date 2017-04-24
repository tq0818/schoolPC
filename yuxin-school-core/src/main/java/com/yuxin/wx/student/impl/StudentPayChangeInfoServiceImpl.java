package com.yuxin.wx.student.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;import com.yuxin.wx.common.BaseServiceImpl;
import java.util.List;
import com.yuxin.wx.api.student.IStudentPayChangeInfoService;
import com.yuxin.wx.model.student.StudentPayChangeInfo;
import com.yuxin.wx.student.mapper.StudentPayChangeInfoMapper;

/**
 * Service Implementation:StudentPayChangeInfo
 * @author wang.zx
 * @date 2014-12-5
 */
@Service
@Transactional
public class StudentPayChangeInfoServiceImpl extends BaseServiceImpl implements IStudentPayChangeInfoService {

	@Autowired
	private StudentPayChangeInfoMapper studentPayChangeInfoMapper;
	
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
	@Override
	public void insert(StudentPayChangeInfo studentPayChangeInfo){
		studentPayChangeInfoMapper.insert(studentPayChangeInfo);
	}
	
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
	@Override
	public void batchInsert(List<StudentPayChangeInfo> studentPayChangeInfos){
		if(studentPayChangeInfos != null && !studentPayChangeInfos.isEmpty()){
			studentPayChangeInfoMapper.batchInsert(studentPayChangeInfos);
		}
	}
	
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
	@Override
	public void update(StudentPayChangeInfo studentPayChangeInfo){
		studentPayChangeInfoMapper.update(studentPayChangeInfo);
	}
	
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
	@Override
	public void deleteStudentPayChangeInfoById(Integer id){
		studentPayChangeInfoMapper.deleteById(id);
	}
	
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
	@Override
	public void deleteStudentPayChangeInfoByIds(Integer[] ids){
		studentPayChangeInfoMapper.deleteByIds(ids);
	}
	
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
	@Override
	public StudentPayChangeInfo findStudentPayChangeInfoById(Integer id){
		return studentPayChangeInfoMapper.findById(id);
	}
	
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
	@Override
	public List<StudentPayChangeInfo> findStudentPayChangeInfoByPage(StudentPayChangeInfo search){
		Integer totalRecords = studentPayChangeInfoMapper.pageCount(search);
		search.setTotalRecords(totalRecords);
		return studentPayChangeInfoMapper.page(search);
	}
	
	
}
