package com.yuxin.wx.student.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;import com.yuxin.wx.common.BaseServiceImpl;
import java.util.List;
import com.yuxin.wx.api.student.IStudentPayLogService;
import com.yuxin.wx.model.student.StudentPayLog;
import com.yuxin.wx.student.mapper.StudentPayLogMapper;

/**
 * Service Implementation:StudentPayLog
 * @author wang.zx
 * @date 2014-12-5
 */
@Service
@Transactional
public class StudentPayLogServiceImpl extends BaseServiceImpl implements IStudentPayLogService {

	@Autowired
	private StudentPayLogMapper studentPayLogMapper;
	
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
	@Override
	public void insert(StudentPayLog studentPayLog){
		studentPayLogMapper.insert(studentPayLog);
	}
	
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
	@Override
	public void batchInsert(List<StudentPayLog> studentPayLogs){
		if(studentPayLogs != null && !studentPayLogs.isEmpty()){
			studentPayLogMapper.batchInsert(studentPayLogs);
		}
	}
	
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
	@Override
	public void update(StudentPayLog studentPayLog){
		studentPayLogMapper.update(studentPayLog);
	}
	
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
	@Override
	public void deleteStudentPayLogById(Integer id){
		studentPayLogMapper.deleteById(id);
	}
	
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
	@Override
	public void deleteStudentPayLogByIds(Integer[] ids){
		studentPayLogMapper.deleteByIds(ids);
	}
	
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
	@Override
	public StudentPayLog findStudentPayLogById(Integer id){
		return studentPayLogMapper.findById(id);
	}
	
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
	@Override
	public List<StudentPayLog> findStudentPayLogByPage(StudentPayLog search){
		Integer totalRecords = studentPayLogMapper.pageCount(search);
		search.setTotalRecords(totalRecords);
		return studentPayLogMapper.page(search);
	}
	
	
}
