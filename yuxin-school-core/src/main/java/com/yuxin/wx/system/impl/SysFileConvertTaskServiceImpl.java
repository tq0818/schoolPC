package com.yuxin.wx.system.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;import com.yuxin.wx.common.BaseServiceImpl;

import com.yuxin.wx.api.system.ISysFileConvertTaskService;
import com.yuxin.wx.model.system.SysFileConvertTask;
import com.yuxin.wx.system.mapper.SysFileConvertTaskMapper;

/**
 * Service Implementation:SysFileConvertTask
 * @author wang.zx
 * @date 2016-10-31
 */
@Service
@Transactional
public class SysFileConvertTaskServiceImpl extends BaseServiceImpl implements ISysFileConvertTaskService {

	@Autowired
	private SysFileConvertTaskMapper sysFileConvertTaskMapper;
	
	/**
	 * 
	* @Title: saveSysFileConvertTask
	* @Description: 新增SysFileConvertTask
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-10-31
	* @user by chopin
	 */
	@Override
	public void insert(SysFileConvertTask entity){
		sysFileConvertTaskMapper.insert(entity);
	};
	
	/**
	 * 
	* @Title: batchSaveSysFileConvertTask 
	* @Description: 批量新增SysFileConvertTask
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-10-31
	* @user by chopin
	 */
	@Override
	public void batchInsert(List<SysFileConvertTask> T){
		sysFileConvertTaskMapper.batchInsert(T);
	};
	
	/**
	 * 
	* @Title: updateSysFileConvertTask 
	* @Description: 编辑SysFileConvertTask
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-10-31
	* @user by chopin
	 */
	@Override
	public void update(SysFileConvertTask T){
		sysFileConvertTaskMapper.update(T);
	};
	
	/**
	 * 
	* @Title: deleteSysFileConvertTaskById 
	* @Description: 根据id删除SysFileConvertTask
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-10-31
	* @user by chopin
	 */
	 @Override
	public void deleteSysFileConvertTaskById(Integer id){
		sysFileConvertTaskMapper.deleteById(id);
	};
	
	/**
	 * 
	* @Title: deleteSysFileConvertTaskByIds 
	* @Description: 根据id批量删除SysFileConvertTask
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-10-31
	* @user by chopin
	 */
	@Override
	public void deleteSysFileConvertTaskByIds(Integer[] ids){
		sysFileConvertTaskMapper.deleteByIds(ids);
	};
	
	/**
	 * 
	* @Title: findSysFileConvertTaskById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-10-31
	* @user by chopin
	 */
	@Override
	public SysFileConvertTask findSysFileConvertTaskById(Integer id){
		return sysFileConvertTaskMapper.findById(id);
	};
	
	/**
	 * 
	* @Title: findSysFileConvertTaskByPage 
	* @Description: 分页查询
	* @return
	* @return List<SysFileConvertTask>    返回类型 
	* @throws 
	* @exception 
	* @date 2016-10-31
	* @user by chopin
	 */
	@Override
	public List<SysFileConvertTask> findSysFileConvertTaskByPage(SysFileConvertTask search){
		return sysFileConvertTaskMapper.page(search);
	}

	@Override
	public List<SysFileConvertTask> pageThreeHundred(Integer version) {
		// TODO Auto-generated method stub
		return sysFileConvertTaskMapper.pageThreeHundred(version);
	}

	@Override
	public Integer updateReturn(SysFileConvertTask entity) {
		// TODO Auto-generated method stub
		return sysFileConvertTaskMapper.updateReturn(entity);
	};
}
