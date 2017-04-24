package com.yuxin.wx.student.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;import com.yuxin.wx.common.BaseServiceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yuxin.wx.api.student.IStudentPaySlaveService;
import com.yuxin.wx.model.student.StudentPaySlave;
import com.yuxin.wx.student.mapper.StudentPaySlaveMapper;
import com.yuxin.wx.vo.student.StudentPaySlaveVo;

/**
 * Service Implementation:StudentPaySlave
 * @author wang.zx
 * @date 2014-12-5
 */
@Service
@Transactional
public class StudentPaySlaveServiceImpl extends BaseServiceImpl implements IStudentPaySlaveService {

	@Autowired
	private StudentPaySlaveMapper studentPaySlaveMapper;
	
	/**
	 * 
	* @Title: saveStudentPaySlave
	* @Description: 新增StudentPaySlave
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	@Override
	public void insert(StudentPaySlave studentPaySlave){
		studentPaySlaveMapper.insert(studentPaySlave);
	}
	
	/**
	 * 
	* @Title: batchSaveStudentPaySlave 
	* @Description: 批量新增StudentPaySlave
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	@Override
	public void batchInsert(List<StudentPaySlave> studentPaySlaves){
		if(studentPaySlaves != null && !studentPaySlaves.isEmpty()){
			studentPaySlaveMapper.batchInsert(studentPaySlaves);
		}
	}
	
	/**
	 * 
	* @Title: updateStudentPaySlave 
	* @Description: 编辑StudentPaySlave
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	@Override
	public void update(StudentPaySlave studentPaySlave){
		studentPaySlaveMapper.update(studentPaySlave);
	}
	
	@Override
	public void updateStatus(Map map){
		studentPaySlaveMapper.updateStatus(map);
	}
	
	/**
	 * 
	* @Title: deleteStudentPaySlaveById 
	* @Description: 根据id删除StudentPaySlave
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	@Override
	public void deleteStudentPaySlaveById(Integer id){
		studentPaySlaveMapper.deleteById(id);
	}
	
	/**
	 * 
	* @Title: deleteStudentPaySlaveByIds 
	* @Description: 根据id批量删除StudentPaySlave
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	@Override
	public void deleteStudentPaySlaveByIds(Integer[] ids){
		studentPaySlaveMapper.deleteByIds(ids);
	}
	
	/**
	 * 
	* @Title: findStudentPaySlaveById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	@Override
	public StudentPaySlave findStudentPaySlaveById(Integer id){
		return studentPaySlaveMapper.findById(id);
	}
	
	/**
	 * 
	* @Title: findStudentPaySlaveByStuId 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	@Override
	public List<StudentPaySlave> findStudentPaySlaveByStuId(Integer stuId){
		return studentPaySlaveMapper.findByStuId(stuId);
	}
	
	/**
	 * 
	* @Title: findStudentPaySlaveByPayId 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	@Override
	public List<StudentPaySlaveVo> findStudentPaySlaveByPayMasterId(Integer mid){
		return studentPaySlaveMapper.findByPayMasterId(mid);
	}
	@Override
	public List<StudentPaySlaveVo> findStudentPaySlaveByPayMasterId2(Integer mid){
		return studentPaySlaveMapper.findByPayMasterId2(mid);
	}
	
	/**
	 * 
	* @Title: findStudentPaySlaveByPage 
	* @Description: 分页查询
	* @return
	* @return List<StudentPaySlave>    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	@Override
	public List<StudentPaySlave> findStudentPaySlaveByPage(StudentPaySlave search){
		Integer totalRecords = studentPaySlaveMapper.pageCount(search);
		search.setTotalRecords(totalRecords);
		return studentPaySlaveMapper.page(search);
	}

	@Override
	public void update2(Integer payMasterId, String str) {
		Map<String,String> map = new HashMap<String, String>();
		map.put("payMasterId", payMasterId.toString());
		String[] s = str.split(":");
		map.put("moduleId", s[0]);
		map.put("moduleNoId", s[1]);
		studentPaySlaveMapper.update2(map);
	}

	@Override
	public Integer findCountByResourceId(Integer resourceId) {
		// TODO Auto-generated method stub
		return studentPaySlaveMapper.findCountByResourceId(resourceId);
	}

	@Override
	public List<StudentPaySlave> queryStuSlaveBywhere(StudentPaySlave search) {
		// TODO Auto-generated method stub
		return studentPaySlaveMapper.queryStuSlaveBywhere(search);
	}

	@Override
	public void updateStuPaySlave(Integer id) {
		// TODO Auto-generated method stub
		studentPaySlaveMapper.deleteStuPaySlave(id);
	}
	
}
