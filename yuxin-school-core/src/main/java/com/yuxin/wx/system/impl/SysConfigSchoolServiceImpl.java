package com.yuxin.wx.system.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;import com.yuxin.wx.common.BaseServiceImpl;

import java.util.List;
import java.util.Map;

import com.yuxin.wx.api.system.ISysConfigSchoolService;
import com.yuxin.wx.model.system.SysConfigSchool;
import com.yuxin.wx.system.mapper.SysConfigSchoolMapper;

/**
 * Service Implementation:SysConfigSchool
 * @author wang.zx
 * @date 2014-12-5
 */
@Service
@Transactional
public class SysConfigSchoolServiceImpl extends BaseServiceImpl implements ISysConfigSchoolService {

	@Autowired
	private SysConfigSchoolMapper sysConfigSchoolMapper;
	
	/**
	 * 
	* @Title: saveSysConfigSchool
	* @Description: 新增SysConfigSchool
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	@Override
	public void insert(SysConfigSchool sysConfigSchool){
		sysConfigSchoolMapper.insert(sysConfigSchool);
	}
	
	/**
	 * 
	* @Title: batchSaveSysConfigSchool 
	* @Description: 批量新增SysConfigSchool
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	@Override
	public void batchInsert(List<SysConfigSchool> sysConfigSchools){
		if(sysConfigSchools != null && !sysConfigSchools.isEmpty()){
			sysConfigSchoolMapper.batchInsert(sysConfigSchools);
		}
	}
	
	/**
	 * 
	* @Title: updateSysConfigSchool 
	* @Description: 编辑SysConfigSchool
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	@Override
	public void update(SysConfigSchool sysConfigSchool){
		sysConfigSchoolMapper.update(sysConfigSchool);
	}
	
	/**
	 * 
	* @Title: deleteSysConfigSchoolById 
	* @Description: 根据id删除SysConfigSchool
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	@Override
	public void deleteSysConfigSchoolById(Integer id){
		sysConfigSchoolMapper.deleteById(id);
	}
	
	/**
	 * 
	* @Title: deleteSysConfigSchoolByIds 
	* @Description: 根据id批量删除SysConfigSchool
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	@Override
	public void deleteSysConfigSchoolByIds(Integer[] ids){
		sysConfigSchoolMapper.deleteByIds(ids);
	}
	
	/**
	 * 
	* @Title: findSysConfigSchoolById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	@Override
	public SysConfigSchool findSysConfigSchoolById(Integer id){
		return sysConfigSchoolMapper.findById(id);
	}
	
	/**
	 * 
	* @Title: findSysConfigSchoolByPage 
	* @Description: 分页查询
	* @return
	* @return List<SysConfigSchool>    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	@Override
	public List<SysConfigSchool> findSysConfigSchoolByPage(SysConfigSchool search){
		Integer totalRecords = sysConfigSchoolMapper.pageCount(search);
		search.setTotalRecords(totalRecords);
		return sysConfigSchoolMapper.page(search);
	}

	@Override
	public List<SysConfigSchool> findAllSysConfigSchool(Integer companyId) {
		return sysConfigSchoolMapper.queryAll(companyId);
	}

	/**
	 * 
	* @Title: findSysConfigSchoolByCompanyId 
	* @Description: 根据公司id查询分校
	* @return
	* @return List<SysConfigSchool>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-4-13
	* @user zhang.zx
	 */
	@Override
	public List<SysConfigSchool> findSysConfigSchoolByCompanyId(
			Integer companyId) {
		return sysConfigSchoolMapper.findSchoolByCompanyId(companyId);
	}

	@Override
	public Integer findDefaultSchool(Integer companyId) {
		return sysConfigSchoolMapper.queryDefaultSchool(companyId);
	}

	@Override
	public SysConfigSchool findSchoolByMap(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return sysConfigSchoolMapper.findSchoolByMap(param);
	}

	@Override
	public Integer findUnquieSchool(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return sysConfigSchoolMapper.findUnquieSchool(param);
	}

	@Override
	public Integer findSchoolCountByCompanyId(Integer companyId) {
		// TODO Auto-generated method stub
		return sysConfigSchoolMapper.findSchoolCountByCompanyId(companyId);
	}

	@Override
	public List<SysConfigSchool> queryAllByCompanyId(Integer companyId) {
		// TODO Auto-generated method stub
		return sysConfigSchoolMapper.queryAllByCompanyId(companyId);
	}

	@Override
	public List<Integer> findExistBySuffix(SysConfigSchool scs) {
		// TODO Auto-generated method stub
		return sysConfigSchoolMapper.findExistBySuffix(scs);
	}
	
}
