package com.yuxin.wx.student.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;import com.yuxin.wx.common.BaseServiceImpl;

import com.yuxin.wx.api.student.IStudentStarService;
import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.model.student.StudentStar;
import com.yuxin.wx.student.mapper.StudentStarMapper;


/**
 * Service Implementation:StudentStar
 * @author chopin
 * @date 2017-3-24
 */
@Service
@Transactional
public class StudentStarServiceImpl extends BaseServiceImpl implements IStudentStarService {

	@Autowired
	private StudentStarMapper studentStarMapper;
	
	/**
	 * 
	* @Title: saveStudentStar
	* @Description: 新增StudentStar
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2017-3-24
	* @user by chopin
	 */
	@Override
	public void insert(StudentStar entity){
		studentStarMapper.insert(entity);
	};
	
	/**
	 * 
	* @Title: batchSaveStudentStar 
	* @Description: 批量新增StudentStar
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2017-3-24
	* @user by chopin
	 */
	@Override
	public void batchInsert(List<StudentStar> entity){
		studentStarMapper.batchInsert(entity);
	};
	
	/**
	 * 
	* @Title: updateStudentStar 
	* @Description: 编辑StudentStar
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2017-3-24
	* @user by chopin
	 */
	@Override
	public void update(StudentStar entity){
		studentStarMapper.update(entity);
	};
	
	/**
	 * 
	* @Title: deleteStudentStarById 
	* @Description: 根据id删除StudentStar
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2017-3-24
	* @user by chopin
	 */
	 @Override
	public void deleteStudentStarById(Integer id){
		studentStarMapper.deleteById(id);
	};
	
	/**
	 * 
	* @Title: deleteStudentStarByIds 
	* @Description: 根据id批量删除StudentStar
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2017-3-24
	* @user by chopin
	 */
	@Override
	public void deleteStudentStarByIds(Integer[] ids){
		studentStarMapper.deleteByIds(ids);
	};
	
	/**
	 * 
	* @Title: findStudentStarById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2017-3-24
	* @user by chopin
	 */
	@Override
	public StudentStar findStudentStarById(Integer id){
		return studentStarMapper.findById(id);
	};
	
	/**
	 * 
	* @Title: findStudentStarByPage 
	* @Description: 分页查询
	* @return
	* @return List<StudentStar>    返回类型 
	* @throws 
	* @exception 
	* @date 2017-3-24
	* @user by chopin
	 */
	@Override
	public List<StudentStar> findStudentStarByPage(StudentStar search){
		return studentStarMapper.page(search);
	};
	
	 @Override
	    public PageFinder<StudentStar> page(StudentStar search) {
	        List<StudentStar> al = studentStarMapper.page(search);
	        Integer rowCount = studentStarMapper.pageCount(search);
	        PageFinder<StudentStar> page = new PageFinder<StudentStar>(search.getPage(),
	                search.getPageSize(), rowCount, al);
	        return page;
	    }
}
