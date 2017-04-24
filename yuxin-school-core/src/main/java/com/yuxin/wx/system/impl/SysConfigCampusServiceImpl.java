package com.yuxin.wx.system.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;import com.yuxin.wx.common.BaseServiceImpl;
import java.util.List;
import java.util.Map;

import com.yuxin.wx.api.system.ISysConfigCampusService;
import com.yuxin.wx.common.SysConfigConstant;
import com.yuxin.wx.model.system.SysConfigCampus;
import com.yuxin.wx.system.mapper.SysConfigCampusMapper;
import com.yuxin.wx.vo.system.ConfigCampusVo;

/**
 * Service Implementation:SysConfigCampus
 * @author wang.zx
 * @date 2014-12-5
 */
@Service
@Transactional
public class SysConfigCampusServiceImpl extends BaseServiceImpl implements ISysConfigCampusService {

	@Autowired
	private SysConfigCampusMapper sysConfigCampusMapper;
	
	/**
	 * 
	* @Title: saveSysConfigCampus
	* @Description: 新增SysConfigCampus
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	@Override
	public void insert(SysConfigCampus sysConfigCampus){
		sysConfigCampus.setDelFlag(SysConfigConstant.NO_DELTE_FLAG);
		sysConfigCampusMapper.insert(sysConfigCampus);
	}
	
	/**
	 * 
	* @Title: batchSaveSysConfigCampus 
	* @Description: 批量新增SysConfigCampus
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	@Override
	public void batchInsert(List<SysConfigCampus> sysConfigCampuss){
		if(sysConfigCampuss != null && !sysConfigCampuss.isEmpty()){
			sysConfigCampusMapper.batchInsert(sysConfigCampuss);
		}
	}
	
	/**
	 * 
	* @Title: updateSysConfigCampus 
	* @Description: 编辑SysConfigCampus
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	@Override
	public void update(SysConfigCampus sysConfigCampus){
		sysConfigCampusMapper.update(sysConfigCampus);
	}
	
	/**
	 * 
	* @Title: deleteSysConfigCampusById 
	* @Description: 根据id删除SysConfigCampus
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	@Override
	public void deleteSysConfigCampusById(Integer id){
		sysConfigCampusMapper.deleteById(id);
	}
	
	/**
	 * 
	* @Title: deleteSysConfigCampusByIds 
	* @Description: 根据id批量删除SysConfigCampus
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	@Override
	public void deleteSysConfigCampusByIds(Integer[] ids){
		sysConfigCampusMapper.deleteByIds(ids);
	}
	
	/**
	 * 
	* @Title: findSysConfigCampusById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	@Override
	public SysConfigCampus findSysConfigCampusById(Integer id){
		return sysConfigCampusMapper.findById(id);
	}
	
	/**
	 * 
	* @Title: findSysConfigCampusByPage 
	* @Description: 分页查询
	* @return
	* @return List<SysConfigCampus>    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	@Override
	public List<SysConfigCampus> findSysConfigCampusByPage(SysConfigCampus search){
		Integer totalRecords = sysConfigCampusMapper.pageCount(search);
		search.setTotalRecords(totalRecords);
		return sysConfigCampusMapper.page(search);
	}

	/**
	 * 
	 * Class Name: ISysConfigCampusService.java
	 * @Description: 根据条件查询校区，不分页
	 * @author liuxindong
	 * @date 2014-12-13 下午4:22:16
	 * @version 1.0
	 * @param search
	 * @return
	 */
	@Override
	public List<SysConfigCampus> findCampus(SysConfigCampus search) {
		return sysConfigCampusMapper.queryCampus(search);
	}

	@Override
	public Integer findCampusBySchoolId(Integer schoolId) {
		// TODO Auto-generated method stub
		return sysConfigCampusMapper.findCampusBySchoolId(schoolId);
	}

	@Override
	public List<ConfigCampusVo> findCampusVo(SysConfigCampus search) {
		// TODO Auto-generated method stub
		return sysConfigCampusMapper.findCampusVo(search);
	}

	@Override
	public List<SysConfigCampus> findCampusBySchoolCompanyId(
			Map<String, Object> param) {
		// TODO Auto-generated method stub
		return sysConfigCampusMapper.findCampusBySchoolCompanyId(param);
	}

	@Override
	public List<SysConfigCampus> queryCampusBySchoolId(Integer schoolId) {
		return sysConfigCampusMapper.queryCampusBySchoolId(schoolId);
	}

	@Override
	public List<SysConfigCampus> checkedCampus(SysConfigCampus search) {
		// TODO Auto-generated method stub
		return sysConfigCampusMapper.checkedCampus(search);
	}
	
}
