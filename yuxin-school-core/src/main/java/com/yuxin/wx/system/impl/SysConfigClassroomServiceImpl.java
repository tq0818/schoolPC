package com.yuxin.wx.system.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;import com.yuxin.wx.common.BaseServiceImpl;

import java.util.List;
import java.util.Map;

import com.yuxin.wx.api.system.ISysConfigClassroomService;
import com.yuxin.wx.common.SysConfigConstant;
import com.yuxin.wx.model.system.SysConfigClassroom;
import com.yuxin.wx.system.mapper.SysConfigClassroomMapper;

/**
 * Service Implementation:SysConfigClassroom
 * @author wang.zx
 * @date 2014-12-5
 */
@Service
@Transactional
public class SysConfigClassroomServiceImpl extends BaseServiceImpl implements ISysConfigClassroomService {

	@Autowired
	private SysConfigClassroomMapper sysConfigClassroomMapper;
	
	/**
	 * 
	* @Title: saveSysConfigClassroom
	* @Description: 新增SysConfigClassroom
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	@Override
	public void insert(SysConfigClassroom sysConfigClassroom){
		sysConfigClassroom.setDelType(SysConfigConstant.NO_DELTE_FLAG);
		sysConfigClassroomMapper.insert(sysConfigClassroom);
	}
	
	/**
	 * 
	* @Title: batchSaveSysConfigClassroom 
	* @Description: 批量新增SysConfigClassroom
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	@Override
	public void batchInsert(List<SysConfigClassroom> sysConfigClassrooms){
		if(sysConfigClassrooms != null && !sysConfigClassrooms.isEmpty()){
			sysConfigClassroomMapper.batchInsert(sysConfigClassrooms);
		}
	}
	
	/**
	 * 
	* @Title: updateSysConfigClassroom 
	* @Description: 编辑SysConfigClassroom
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	@Override
	public void update(SysConfigClassroom sysConfigClassroom){
		sysConfigClassroomMapper.update(sysConfigClassroom);
	}
	
	/**
	 * 
	* @Title: deleteSysConfigClassroomById 
	* @Description: 根据id删除SysConfigClassroom
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	@Override
	public void deleteSysConfigClassroomById(Integer id){
		sysConfigClassroomMapper.deleteById(id);
	}
	
	/**
	 * 
	* @Title: deleteSysConfigClassroomByIds 
	* @Description: 根据id批量删除SysConfigClassroom
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	@Override
	public void deleteSysConfigClassroomByIds(Integer[] ids){
		sysConfigClassroomMapper.deleteByIds(ids);
	}
	
	/**
	 * 
	* @Title: findSysConfigClassroomById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	@Override
	public SysConfigClassroom findSysConfigClassroomById(Integer id){
		return sysConfigClassroomMapper.findById(id);
	}
	
	/**
	 * 
	* @Title: findSysConfigClassroomByPage 
	* @Description: 分页查询
	* @return
	* @return List<SysConfigClassroom>    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	@Override
	public List<SysConfigClassroom> findSysConfigClassroomByPage(SysConfigClassroom search){
		Integer totalRecords = sysConfigClassroomMapper.pageCount(search);
		search.setTotalRecords(totalRecords);
		return sysConfigClassroomMapper.page(search);
	}

	/**
	 * 
	 * Class Name: ISysConfigClassroomService.java
	 * @Description: 根据条件获取教室，不分页
	 * @author liuxindong
	 * @date 2014-12-21 下午2:38:17
	 * @version 1.0
	 * @param search
	 * @return
	 */
	@Override
	public List<SysConfigClassroom> findClassroom(SysConfigClassroom search) {
		return sysConfigClassroomMapper.queryClassroom(search);
	}

	@Override
	public Integer findClassroomBySchoolId(Integer schoolId) {
		// TODO Auto-generated method stub
		return sysConfigClassroomMapper.findClassroomBySchoolId(schoolId);
	}

	@Override
	public List<SysConfigClassroom> findClassroomByCampusId(Integer campusId) {
		// TODO Auto-generated method stub
		return sysConfigClassroomMapper.findClassroomByCampusId(campusId);
	}

	@Override
	public List<SysConfigClassroom> findClassroomByconditions(
			Map<String, Object> map) {
		// TODO Auto-generated method stub
		return sysConfigClassroomMapper.findClassroomByconditions(map);
	}
	
}
